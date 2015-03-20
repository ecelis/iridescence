package org.compiere.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Ini;
import org.compiere.util.Utilerias;
import org.compiere.util.vo.InteractionOV;

import com.lexi.lexidata.api.dal.DAL;
import com.lexi.lexidata.api.objects.BaseAllergen;
import com.lexi.lexidata.api.objects.BaseDrug;
import com.lexi.lexidata.api.objects.Condition;
import com.lexi.lexidata.api.objects.DrugAllergyResult;
import com.lexi.lexidata.api.objects.DrugConditionInteractionResult;
import com.lexi.lexidata.api.objects.DrugDrugInteractionResult;
import com.lexi.lexidata.api.objects.DrugFoodInteractionResult;
import com.lexi.lexidata.api.objects.DuplicateTherapyResult;
import com.lexi.lexidata.api.objects.GenericDrug;
import com.lexi.lexidata.api.objects.GenericProduct;
import com.lexi.lexidata.api.objects.PackagedProduct;
import com.lexi.lexidata.api.objects.ScreeningContext;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

/**
 * Clase para manejo de screening del framework Drug Information (First Data Bank)
 * 
 * @author jcarranza
 * Modified by Lorena Lama (July 2013) PMD
 */
public class LEXIFramework {

	private static CLogger	slog	= CLogger.getCLogger(LEXIFramework.class);
	
	private static class DAL2 {
		private final Connection	connection;
		private DAL	dal;

		public DAL2() {
			this.connection = getConexion();
			if (connection == null) {
				slog.log(Level.WARNING, "LEXI getConexion FAILED!!");
			} else {
				this.dal = new DAL(connection);
			}
		}

		/** Closes the connection */
		public void closeConnection() {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final Exception e) {
				slog.log(Level.SEVERE, e.getMessage(), e);
			}
		}

		public Connection getConnection() {
			return connection;
		}
		
		public DAL getDAL() {
			if (dal == null) {
				this.dal = new DAL(connection);
			}
			return dal;
		}
	}

	/**
	 * Obtiene el objeto ScreenDrugs a partir de un arreglo de medicamentos
	 * 
	 * @param ctx
	 * @param dal
	 * @param codes
	 * @param code
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<BaseDrug> getObjScrnDrugs(final Properties ctx, final DAL dal, final String codes, final String code, final boolean isNDC) {

		final ArrayList<BaseDrug> drugList = new ArrayList<BaseDrug>();

		// String searchTexts[] = {"59741-307-12","0056-0168-90","0172-3984-60"}; //NDC
		try {
			final String searchTexts[] = StringUtils.isNotBlank(codes) ? codes.split("@@@") : new String[0];
			for (final String text : searchTexts) {

				if (StringUtils.isBlank(text)) {
					continue;
				}
				try {
					// PackagedProduct gdp = null;
					// if (isNDC) {
					// gdp = dal.getPackagedProduct(text);
					// } else {
					final GenericProduct genProd = dal.getGenericProduct(Integer.valueOf(text));
					// }
					// if(isNDC && gdp != null ){
					// gd = dal.getGenericDrug(gdp.getGenDrugID());
					// if(gd != null)
					// drugList.add(gd);
					// }else if(!isNDC && gp != null){
					// gd = dal.getGenericDrug(gp.getGenDrugID());
					// if(gd != null)
					// drugList.add(gd);
					// }
					if (genProd != null) {
						final GenericDrug genDrug = dal.getGenericDrug(genProd.getGenDrugID());
						if (genDrug != null) {
							drugList.add(genDrug);
						}
					}
				} catch (final Exception e) {
					slog.log(Level.SEVERE, Utilerias.getMsg(ctx, "fdm.screening.error"), e);
				}
			}

			if (StringUtils.isNotBlank(code)) {
				PackagedProduct packProd = null;
				GenericProduct genProd = null;
				try {
					if (isNDC) {
						packProd = dal.getPackagedProduct(code);
					} else {
						genProd = dal.getGenericProduct(Integer.valueOf(code));
					}
					GenericDrug genDrug = null;
					if (isNDC && packProd != null) {
						genDrug = dal.getGenericDrug(packProd.getGenDrugID());
					} else if (!isNDC && genProd != null) {
						genDrug = dal.getGenericDrug(genProd.getGenDrugID());
					}
					if (genDrug != null) {
						drugList.add(genDrug);
					}
				} catch (final Exception e) {
					slog.log(Level.SEVERE, Utilerias.getMsg(ctx, "fdm.screening.error"), e);
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}

		return drugList;
	}

	/**
	 * Obtiene DDIM (drug-drug) screening
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param level
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<String> doScreenDDIM(final Properties ctx, final String arrNDC, final String NDC, final String level, final boolean isNDC) {

		final ArrayList<String> msgDDIM = new ArrayList<String>();
		final DAL2 dal2 = new DAL2();
		try {
			
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);
				final ScreeningContext screen = new ScreeningContext();
				screen.setDrugs(drugs);
				final List<DrugDrugInteractionResult> arr = dal.getDrugDrugInteractions(screen, false, 0);

				// See if the screening produced any results
				for (final DrugDrugInteractionResult dar : arr) {
					if (dar.getSeverityID() >= Integer.valueOf(level)) {
						// Add an entry to the screen results listview control.
						if (StringUtils.isNotBlank(dar.getInteractionMessage())) {
							msgDDIM.add("Interaction Message: " + dar.getInteractionMessage());
						}
						if (StringUtils.isNotBlank(dar.getInteractionDescription())) {
							msgDDIM.add(Utilerias.getMsg(ctx, "fdm.screening.drugDrug.interaction") + ": " + dar.getInteractionDescription());
						}
						if (StringUtils.isNotBlank(dar.getSeverityDescription())) {
							msgDDIM.add(Utilerias.getMsg(ctx, "msj.etiqueta.severidad") + ": " + dar.getSeverityDescription());
						}
					}
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, getScreeningError(ctx), e);
		} finally {
			dal2.closeConnection();
		}

		return msgDDIM;
	}

	/**
	 * Obtiene DDIM (drug-drug) screening
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param level
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<InteractionOV> doScreenDDIMvo(final Properties ctx, final String arrNDC, final String NDC, final String level,
		final boolean isNDC) {

		final ArrayList<InteractionOV> msgDDIM = new ArrayList<InteractionOV>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);
				final ScreeningContext screen = new ScreeningContext();
				screen.setDrugs(drugs);
				final List<DrugDrugInteractionResult> arr = dal.getDrugDrugInteractions(screen, false, 0);

				// See if the screening produced any results
				for (final DrugDrugInteractionResult dar : arr) {
					if (dar.getSeverityID() >= Integer.valueOf(level)) {
						// Add an entry to the screen results listview control.
						final InteractionOV obj = new InteractionOV();
						if (StringUtils.isNotBlank(dar.getInteractionMessage())) {
							obj.setShortMsg("Interaction Message: " + dar.getInteractionMessage());
						}
						if (StringUtils.isNotBlank(dar.getInteractionDescription())) {
							obj.setLongMsg(Utilerias.getMsg(ctx, "fdm.screening.drugDrug.interaction") + ": " + dar.getInteractionDescription());
						}

						obj.setDrug1(dar.getDrug1().getGenericName());
						obj.setDrug2(dar.getDrug2().getGenericName());
						final String msg = Utilerias.getMsg(ctx, "fdm.screening.drugDrug");
						if (dar.getSeverityID() == 1) {
							obj.setType("Minor " + msg);
						} else if (dar.getSeverityID() == 2) {
							obj.setType("Moderate " + msg);
						} else if (dar.getSeverityID() == 3) {
							obj.setType("Major " + msg);
						} else {
							obj.setType(msg);
						}
						msgDDIM.add(obj);
					}
				}
			}

		} catch (final Exception e) {
			slog.log(Level.SEVERE, getScreeningError(ctx), e);
		} finally {
			dal2.closeConnection();
		}

		return msgDDIM;
	}

	/**
	 * Obtiene DDCMScreen (medical condition) screening
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param medConds
	 * @param level
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<String> doDDCMScreen(final Properties ctx, final String arrNDC, final String NDC, final List<String> medConds,
		final String level, final boolean isNDC) {

		final ArrayList<String> msgDDCM = new ArrayList<String>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);

				// build screening context
				final ScreeningContext screen = new ScreeningContext();
				screen.setDrugs(drugs);
				final ArrayList<Condition> condts = new ArrayList<Condition>();

				for (final String obj : medConds) {
					condts.addAll(dal.getICD9Conditions(obj));
				}
				screen.setConditions(condts);

				final List<DrugConditionInteractionResult> list = dal.getDrugConditionInteractions(screen, false, 0);

				for (final DrugConditionInteractionResult obj : list) {
					if (obj.getSeverityID() >= Integer.valueOf(level)) {
						msgDDCM.add(obj.getCondition().getDescription() + " vs " + obj.getDrug().getGenericName());
						msgDDCM.add(obj.getSeverityDescription());
						msgDDCM.add(obj.getInteractionTitle());
						msgDDCM.add(obj.getInteractionDescription());
						msgDDCM.add("");
					}

					// System.out.println("1: "+obj.getCondition().getDescription());
					// System.out.println("2: "+obj.getConditionInteractionID());
					// System.out.println("3: "+obj.getDrug().getGenDrugID());
					// System.out.println("4: "+obj.getInteractionDescription());
					// System.out.println("5: "+obj.getInteractionTitle());
					// System.out.println("6: "+obj.getSeverityDescription());
					//
					// 1: Asthma
					// 2: 732
					// 3: d03445
					// 4: It has been suggested that the anticholinergic effect of antihistamines may reduce the volume and cause thickening of
					// bronchial secretions, resulting in obstruction of respiratory tract. Some manufacturers and clinicians recommend that therapy
					// with antihistamines be administered cautiously in patients with asthma or chronic obstructive pulmonary disease.
					// 5: antihistamines - asthma/COPD
					// 6: Moderate Potential Hazard
					// 1: Asthma
					// 2: 558
					// 3: d00134
					// 4: In general, beta-adrenergic receptor blocking agents (i.e., beta-blockers) should not be used in patients with
					// bronchospastic diseases. Beta blockade may adversely affect pulmonary function by counteracting the bronchodilation produced by
					// catecholamine stimulation of beta-2 receptors. If beta-blocker therapy is necessary in these patients, an agent with beta-1
					// selectivity (e.g., atenolol, metoprolol, betaxolol) is considered safer, but should be used with caution nonetheless.
					// Cardioselectivity is not absolute and can be lost with larger doses.
					// 5: beta-blockers - asthma/COPD
					// 6: Severe Potential Hazard
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, getScreeningError(ctx), e);
		} finally {
			dal2.closeConnection();
		}
		return msgDDCM;
	}

	/**
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param trade
	 * @param classes
	 * @param groups
	 * @param drugSy
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<String> doDAMScreen(final Properties ctx, final String arrNDC, final String NDC, final List<String> trade,
		final List<String> classes, final List<String> groups, final List<String> drugSy, final boolean isNDC) {

		final ArrayList<String> msgDAM = new ArrayList<String>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);
				final ArrayList<BaseAllergen> allergyList = new ArrayList<BaseAllergen>();

				for (final String ob : trade) {
//					final BaseAllergen baseAller = dal.getGenericDrug(ob);
					// ba.setAllergySeverity("Major");
					// ba.setReaction("Rash");
					allergyList.add(dal.getGenericDrug(ob));
				}

				for (final String ob : classes) {
//					final BaseAllergen baseAller = dal.getAllergyClass(Integer.parseInt(ob));
					// ba.setAllergySeverity("Major");
					// ba.setReaction("Rash");
					allergyList.add(dal.getAllergyClass(Integer.parseInt(ob)));
				}

				/* for(String ob : groups){
				 * Aller ba = dal.getAllergyXRGroup(Integer.parseInt(ob)));
				 * //ba.setAllergySeverity("Major");
				 * //ba.setReaction("Rash");
				 * AllergyList.add(ba);
				 * } */

				for (final String ob : drugSy) {
//					final BaseAllergen baseAller = dal.getGenericDrug(ob);
					// ba.setAllergySeverity("Major");
					// ba.setReaction("Rash");
					allergyList.add(dal.getGenericDrug(ob));
				}

				final ScreeningContext screen = new ScreeningContext();
				screen.setAllergies(allergyList);
				screen.setDrugs(drugs);

				// if(baseIngs!=null && baseIngs.size() > 0){}
				// if(groups!=null && groups.size() > 0){}
				// if(drugs!=null && drugs.size() > 0){}

				final List<DrugAllergyResult> arr = dal.getDrugAllergyInteractions(screen, false);

				for (final DrugAllergyResult dar : arr) {
					if (dar.getAllergyMessage() != null) {
						msgDAM.add("Message: " + dar.getAllergyMessage());
					}
					if (dar.getAllergySeverity() != null) {
						msgDAM.add(Utilerias.getMsg(ctx, "msj.etiqueta.severidad") + ": " + dar.getAllergySeverity());
					}
					if (dar.getReaction() != null) {
						msgDAM.add(Utilerias.getMsg(ctx, "msj.etiqueta.reaccion") + ": " + dar.getReaction());
					}
				}
			}

		} catch (final Exception e) {
			slog.log(Level.SEVERE, getScreeningError(ctx), e);
		} catch (final Throwable e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			dal2.closeConnection();
		}
		return msgDAM;
	}

	/**
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param trade
	 * @param classes
	 * @param groups
	 * @param drugSy
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<InteractionOV> doDAMScreenOV(final Properties ctx, final String arrNDC, final String NDC, final List<String> trade,
		final List<String> classes, final List<String> groups, final List<String> drugSy, final boolean isNDC) {

		final ArrayList<InteractionOV> msgDAM = new ArrayList<InteractionOV>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);
				final ArrayList<BaseAllergen> allergyList = new ArrayList<BaseAllergen>();

				for (final String ob : trade) {
//					final BaseAllergen ba = dal.getGenericDrug(ob));
					// ba.setAllergySeverity("Major");
					// ba.setReaction("Rash");
					allergyList.add(dal.getGenericDrug(ob));
				}

				for (final String ob : classes) {
//					final BaseAllergen ba = dal.getAllergyClass(Integer.parseInt(ob)));
					// ba.setAllergySeverity("Major");
					// ba.setReaction("Rash");
					allergyList.add(dal.getAllergyClass(Integer.parseInt(ob)));
				}

				/* for(String ob : groups){
				 * Aller ba = dal.getAllergyXRGroup(Integer.parseInt(ob)));
				 * //ba.setAllergySeverity("Major");
				 * //ba.setReaction("Rash");
				 * AllergyList.add(ba);
				 * } */

				for (final String ob : drugSy) {
//					final BaseAllergen ba = dal.getGenericDrug(ob));
					// ba.setAllergySeverity("Major");
					// ba.setReaction("Rash");
					allergyList.add(dal.getGenericDrug(ob));
				}

				final ScreeningContext screen = new ScreeningContext();
				screen.setAllergies(allergyList);
				screen.setDrugs(drugs);

				// if(baseIngs!=null && baseIngs.size() > 0){}
				// if(groups!=null && groups.size() > 0){}
				// if(drugs!=null && drugs.size() > 0){}

				final List<DrugAllergyResult> arr = dal.getDrugAllergyInteractions(screen, false);
				for (final DrugAllergyResult dar : arr) {
					final InteractionOV obj = new InteractionOV();
					obj.setType(Utilerias.getMsg(ctx, "fdm.screening.drugAllergies"));
					if (dar.getAllergyMessage() != null) {
						obj.setLongMsg("Message: " + dar.getAllergyMessage());
					}

					if (dar.getReaction() != null) {
						obj.setType(obj.getType() + "/n" + Utilerias.getMsg(ctx, "msj.etiqueta.reaccion") + ": " + dar.getReaction());
					}

					obj.setDrug1(dar.getDrug().getGenericName());

					msgDAM.add(obj);
				}
			}

		} catch (final Exception e) {
			slog.log(Level.SEVERE, getScreeningError(ctx), e);
		} catch (final Throwable e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			dal2.closeConnection();
		}
		return msgDAM;
	}

	/**
	 * Obtiene DFIM (drug-food) screening
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param level
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<String> doScreenDFIM(final Properties ctx, final String arrNDC, final String NDC, final String level, final boolean isNDC) {

		final ArrayList<String> msgDFIM = new ArrayList<String>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);

				final List<DrugFoodInteractionResult> arr = dal.getDrugFoodInteractionResults(drugs.get(0), 0);
				final int levelInt = StringUtils.isNotEmpty(level) ? Integer.valueOf(level) : 0;// Lama: NumberFormatException
				for (final DrugFoodInteractionResult dar : arr) {
					if (StringUtils.isNotBlank(dar.getInteractionDescription()) && dar.getSeverityID() >= levelInt) {
						msgDFIM.add(dar.getInteractionDescription());
					}
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, Utilerias.getMsg(ctx, "fdm.screening.drugFood.error"), e);
		} finally {
			dal2.closeConnection();
		}
		return msgDFIM;
	}

	/**
	 * Obtiene DT (duplicate therapy) screening
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param allow
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<String> doScreenDT(final Properties ctx, final String arrNDC, final String NDC, final boolean allow, final boolean isNDC) {

		final ArrayList<String> msgDT = new ArrayList<String>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);

				final ScreeningContext screen = new ScreeningContext();
				screen.setDrugs(drugs);
				final List<DuplicateTherapyResult> arr = dal.getDuplicateTherapies(screen, false, 0);
				final StringBuilder aux = new StringBuilder();
				for (final DuplicateTherapyResult dar : arr) {
					final BaseDrug[] dtDrugs = dar.getDrugs();

					if (dtDrugs.length > 0) {
						aux.setLength(0);
						for (final BaseDrug drug : dtDrugs) {
							aux.append(" *").append(drug.getGenericName());
						}
						msgDT.add(Utilerias.getMsg(ctx, "msj.internacion.farmacos") + ": " + aux.toString());
						msgDT.add("Dup Therapy Class: " + dar.getClassName());
						msgDT.add("Max Dup Count: " + dar.getMaxDuplicationCount());
					}
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, Utilerias.getMsg(ctx, "fdm.screening.duplicateTherapy.error"), e);
		} finally {
			dal2.closeConnection();
		}
		return msgDT;
	}

	/**
	 * Obtiene DT (duplicate therapy) screening
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param allow
	 * @param isNDC
	 * @return
	 */
	public static ArrayList<InteractionOV> doScreenDTov(final Properties ctx, final String arrNDC, final String NDC, final boolean allow,
		final boolean isNDC) {
		final ArrayList<InteractionOV> msgDT = new ArrayList<InteractionOV>();
		final DAL2 dal2 = new DAL2();
		try {
			if (dal2.getConnection() != null) {
				final DAL dal = dal2.getDAL();
				final ArrayList<BaseDrug> drugs = getObjScrnDrugs(ctx, dal, arrNDC, NDC, isNDC);

				final ScreeningContext screen = new ScreeningContext();
				screen.setDrugs(drugs);
				final List<DuplicateTherapyResult> arr = dal.getDuplicateTherapies(screen, false, 0);

				for (final DuplicateTherapyResult dar : arr) {
					final InteractionOV obj = new InteractionOV();
					final BaseDrug[] dtDrugs = dar.getDrugs();
					if (dtDrugs.length > 0) {
						obj.setDrug1(dtDrugs[0].getGenericName());
						if (dtDrugs.length > 1) {
							obj.setDrug2(dtDrugs[1].getGenericName());
						}
					}
					obj.setType(Utilerias.getMsg(ctx, "fdm.screening.duplicateTherapy"));
					msgDT.add(obj);
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, Utilerias.getMsg(ctx, "fdm.screening.duplicateTherapy.error"), e);
		} finally {
			dal2.closeConnection();
		}
		return msgDT;
	}

	/**
	 * 
	 * @param ctx
	 * @param arrNDC
	 * @param NDC
	 * @param list
	 * @param config
	 * @param trade
	 * @param ings
	 * @param groups
	 * @param drugs
	 * @param isNDC
	 * @return
	 */
	public static boolean existeDIFwk(final Properties ctx, final String arrNDC, final String NDC, final List<String> list,
		final MEXMEConfigMedico config, final List<String> trade, final List<String> ings, final List<String> groups, final List<String> drugs,
		final boolean isNDC) {
		final boolean ret = config != null && (
		// Drug - Drug
			(config.isDrugDrug() && !doScreenDDIM(ctx, arrNDC, NDC, config.getDrugDrugLevel(), isNDC).isEmpty())
			// Medical Condition
				|| (config.isMedicalCondition() && !doDDCMScreen(ctx, arrNDC, NDC, list, config.getMedicalConditionLevel(), isNDC).isEmpty())
				// Drug Food
				|| (config.isDrugFood() && !doScreenDFIM(ctx, arrNDC, NDC, config.getDrugFoodLevel(), isNDC).isEmpty())
				// Drug Allergy
				|| (config.isDrugAller() && !doDAMScreen(ctx, null, NDC, trade, ings, groups, drugs, isNDC).isEmpty())
			// Duplicate therapy
			|| (config.isDuplicateTherapy() && !doScreenDT(ctx, arrNDC, NDC, config.isDuplicateTherapyAllow(), isNDC).isEmpty())
			//
			);
		return ret;
	}

	/** @return LEXI database connection */
	public static Connection getConexion() {
		slog.log(Level.FINEST, "LEXI getConexion");
		Connection con = null;
		try {
			final String propPath = Ini.getCompiereHome() + File.separator + "MedicalSupport.properties";
			final Properties properties = new Properties();
			final FileInputStream path = new FileInputStream(propPath);
			properties.load(path);
			final String host = properties.getProperty("MS_HOST");
			final String puerto = properties.getProperty("MS_PUERTO");
			final String sid = properties.getProperty("MS_SID");
			final String usr = properties.getProperty("MS_USR");
			final String pass = properties.getProperty("MS_PASS");

			// MySQL
			final MysqlConnectionPoolDataSource mysql_ds = new MysqlConnectionPoolDataSource();
			final StringBuilder url = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			url.append("jdbc:mysql://").append(host).append(":").append(puerto).append("/").append(sid);
			mysql_ds.setURL(url.toString());
			mysql_ds.setUser(usr);
			mysql_ds.setPassword(pass);
			mysql_ds.setPortNumber(Integer.valueOf(puerto));
			if (slog.isLoggable(Level.FINER)) {
				slog.log(Level.FINER, "URL: " + url.toString());
				slog.log(Level.FINER, "usr: " + usr);
				slog.log(Level.FINER, "pss: " + pass);
				slog.log(Level.FINER, "puerto: " + puerto);
			}
			con = mysql_ds.getConnection();
			slog.log(Level.FINEST, "LEXI getConexion OK!!");
		} catch (final Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return con;
	}

	private static String getScreeningError(final Properties ctx) {
		return Utilerias.getMsg(ctx, "fdm.screening.drugDrug.error");

	}

}
