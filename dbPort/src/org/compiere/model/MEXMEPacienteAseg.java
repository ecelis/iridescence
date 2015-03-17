package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;


/**
 * Clase de modelo de la tabla EXME_PacienteAseg
 * @author rsolorzano
 */
public class MEXMEPacienteAseg extends X_EXME_PacienteAseg implements PatientRelData {

	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger (MEXMEPacienteAseg.class);
	private boolean validatePriority = true;
	
	public MEXMEPacienteAseg(Properties ctx, int EXME_PacienteBP_ID, String trxName) {
		super(ctx, EXME_PacienteBP_ID, trxName);
	}

	public MEXMEPacienteAseg(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static boolean validateMain(Properties ctx, int EXME_Paciente_ID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_PacienteAseg_ID FROM EXME_PacienteAseg WHERE isActive = 'Y' AND EXME_Paciente_ID=? AND isMain='Y'  ");
		return DB.getSQLValue(null, sql.toString(), EXME_Paciente_ID) > 0;

	}	

	/**
	 * Solo busca las aseguradoras que se encuentra nivel paciente (es decir EXME_CtaPac_ID = 0) y que se encuentran activas
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return Lista con las aseguradoras encontradas
	 */
	public static List<MEXMEPacienteAseg> getAllByPatient(Properties ctx, int EXME_Paciente_ID) {
		return getAllByPatient(ctx, EXME_Paciente_ID,0, null);
	}
	/**
	 * Busca las aseguradoras activas del paciente y del encuentro que se asignaron en los parametros
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param EXME_CtaPac_ID Asignar 0 si se quieren solo las asignandas a nivel paciente
	 * @param trxName 
	 * @return Lista con las aseguradoras encontradas
	 */
	public static List<MEXMEPacienteAseg> getAllByPatient(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, String trxName) {
		return new Query(ctx, Table_Name, "EXME_Paciente_ID=? AND EXME_CtaPac_ID = ?", trxName)
				.setOnlyActiveRecords(true)
				.setParameters(EXME_Paciente_ID,EXME_CtaPac_ID).setOrderBy(" priority asc")
				.list();
	}
	
	/**
	 * Todas, incluyendo activas
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPacienteAseg> getAllByPatient(Properties ctx, int EXME_Paciente_ID, String trxName) {
		return new Query(ctx, Table_Name, "EXME_Paciente_ID=? ", trxName).setOnlyActiveRecords(false).setParameters(EXME_Paciente_ID).list();
	}
	
	/**
	 * Busca las aseguradoras activas del paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param EXME_CtaPac_ID Asignar 0 si se quieren solo las asignandas a nivel paciente
	 * @return Lista con las aseguradoras encontradas
	 */
	public static List<MEXMEPacienteAseg> getAllByPatientPartner(Properties ctx, int EXME_Paciente_ID, int C_BPartner_ID, String trxName) {
		return new Query(ctx, Table_Name, "EXME_Paciente_ID=? AND C_BPartner_ID = ?", trxName)
				.setOnlyActiveRecords(true)
				.setParameters(EXME_Paciente_ID,C_BPartner_ID).setOrderBy(" priority asc")
				.list();
	}
	
	
	/**
	 * Busca las PacAseg Activo de un C_BPartner
	 * @param ctx
	 * @param C_BPartner_ID  Aseguradora de busqueda
	 * @return Lista con las aseguradoras encontradas
	 */
	public static List<MEXMEPacienteAseg> getAllByPartner(Properties ctx, int C_BPartner_ID, String trxName) {
		return new Query(ctx, Table_Name, "C_BPartner_ID = ?", trxName)
				.setOnlyActiveRecords(true)
				.setParameters(C_BPartner_ID)
				.list();
	}
	
	/**
	 * Socio de negocios a nivel paciente
	 * @param ctx
	 * @param pacienteID
	 * @return
	 */
	public static List<KeyNamePair> getAllPartnerByPatient(final Properties ctx, final int pacienteID) {

		final List<KeyNamePair> array = new ArrayList<KeyNamePair>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT bp.Name as Name, pa.C_BPartner_ID ")
		.append(" FROM  EXME_PacienteAseg pa ")
		.append(" INNER JOIN C_BPartner   bp ON pa.C_BPartner_ID = bp.C_BPartner_ID ")
		.append(" WHERE pa.isActive = 'Y' ")
		.append(" AND   pa.EXME_Paciente_ID = ? ")
		.append(" AND   pa.EXME_CtaPac_ID   = 0 ")//TODO: Cero ????
		.append(" AND   pa.AD_Client_ID IN (0, ?) ")
		.append(" AND   pa.AD_Org_ID IN (0, ?) ")
		
		.append(" AND   bp.AD_Client_ID IN (0, ?) ")
		.append(" AND   bp.AD_Org_ID IN (0, ?) ");
//		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_EXME_PacienteAseg.Table_Name, "pa")));

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pacienteID);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, Env.getAD_Org_ID(ctx));
			
			pstmt.setInt(4, Env.getAD_Client_ID(ctx));
			pstmt.setInt(5, Env.getAD_Org_ID(ctx));
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new KeyNamePair(rs.getInt("C_BPartner_ID"), rs.getString("Name")));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, " MEXMEPacienteBP.validateMain = " + sql, e);
		}finally {
			DB.close(rs, pstmt);
		}

		return array;
	}
	
	/**
	 * Aseguradoras por encuentro por tipo
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return
	 */
	public static int getByCtaPacSupport(Properties ctx, int EXME_CtaPac_ID, String support) {

		int retVal = -1;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT count(*) ")
		.append(" FROM EXME_PacienteAseg pa ")
		.append(" WHERE pa.EXME_CtaPac_ID = ?  ")
		.append(" AND pa.isActive = 'Y'  ")
		.append(" AND pa.SupportBilling = ?  ");
		

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_CtaPac_ID);
			pstmt.setString(2, support);
			rs = pstmt.executeQuery();

			while (rs.next()){
				retVal = rs.getInt(1);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, " MEXMEPacienteAseg.getByCtaPacSupport " + sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}

		return retVal;
	}
	
	/**
	 * Socio de negocios a nivel encuentro
	 * @param ctx
	 * @param ctaPacID
	 * @return
	 */
	public static List<LabelValueBean> getAllPartnerByCtaPac(Properties ctx, int ctaPacID, boolean isMx) {

		final List<LabelValueBean> array = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if(!isMx){
			sql.append(" SELECT bp.Name || ' - (' ||")
			   .append(" CASE WHEN PA.SupportBilling =? THEN  ? ")
			   .append(" WHEN PA.SupportBilling = ? THEN ? END || ')' AS Name, ");
		}else{
			sql.append(" SELECT bp.Name,  ");	
		}
		
		sql.append(" pa.EXME_PacienteAseg_ID ")
		.append(" FROM EXME_PacienteAseg pa ")
		.append(" LEFT JOIN C_BPartner bp ON pa.C_BPartner_ID = bp.C_BPartner_ID ")
		.append(" WHERE pa.EXME_CtaPac_ID = ? AND pa.IsActive = 'Y' ")
		.append(" ORDER BY pa.Priority ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			if(!isMx){
				pstmt.setString(1, SUPPORTBILLING_Institucional);
				pstmt.setString(2, Utilerias.getMessage(ctx, null, "insurance.institutional"));
				pstmt.setString(3, SUPPORTBILLING_Professional);
				pstmt.setString(4, Utilerias.getMessage(ctx, null, "insurance.professional"));
				pstmt.setInt(5, ctaPacID);
			}else{
				pstmt.setInt(1, ctaPacID);
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, " MEXMEPacienteAseg.getAllPartnerByCtaPac " + sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}

		return array;
	}

	
	/**
	 * Socio de negocios a nivel encuentro
	 * @param ctx
	 * @param ctaPacID
	 * @return
	 */
	public static List<KeyNamePair> getAllBPartnerByCtaPac(Properties ctx, int ctaPacID) {

		final List<KeyNamePair> array = new ArrayList<KeyNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT bp.Name,  bp.C_BPartner_ID ")
		.append(" FROM  EXME_PacienteAseg pa ")
		.append(" INNER JOIN C_BPartner   bp ON pa.C_BPartner_ID = bp.C_BPartner_ID ")
		.append(" WHERE    pa.IsActive = 'Y' ")
		.append(" AND      pa.EXME_CtaPac_ID = ? ")
//		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_EXME_PacienteAseg.Table_Name, "pa")))
		.append(" AND   pa.AD_Client_ID IN (0, ?) ")
		.append(" AND   pa.AD_Org_ID IN (0, ?) ")
		
		.append(" AND   bp.AD_Client_ID IN (0, ?) ")
		.append(" AND   bp.AD_Org_ID IN (0, ?) ")
		.append(" ORDER BY pa.Priority ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPacID);
			
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, Env.getAD_Org_ID(ctx));
			
			pstmt.setInt(4, Env.getAD_Client_ID(ctx));
			pstmt.setInt(5, Env.getAD_Org_ID(ctx));
			
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, " MEXMEPacienteBP.validateMain " + sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}

		return array;
	}
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (final boolean newRecord) {
		
		/*
		 * Inicio busqueda de partners repetidos por paciente y encuentro
		 * para que una aseguradora este repedita se deben cumplir los siguientes criterios
		 * 1 - Apuntar hacia el mismo C_BPartner_ID
		 * 2 - Apuntar hacia el mismo EXME_PlanAseg_ID
		 * 3 - Pertener al mismo paciente
		 * 4 - Pertener al mismo encuentro
		 * 5 - Ser una Aseguradora Activa
		 * 6 - Aparte, si la aseguradora se encuentra a nivel encuentro (EXME_CtaPac_ID > 0)
		 * 		1 - Validar no haya dos aseguradoas iguales (es decir C_BPartner iguales) ACTIVAS con el MISMO SupportBilling
		 * 		
		 */	
		//Se buscan todas las aseguradas que tengan los mismos valores de la aseguradoa que se va a guardar y esten activas
		List<MEXMEPacienteAseg> listAsegs = getListAseg(getCtx(), getC_BPartner_ID(), getEXME_PlanAseg_ID(), getEXME_Paciente_ID(), getEXME_CtaPac_ID(),true, get_TrxName());
		for(MEXMEPacienteAseg aseg : listAsegs){		
			//Si la aseguradora tienen los mismo valores que la que se quiere guardar pero, su id es diferente, posiblemente sea repetida
			if (aseg != null && aseg.getEXME_PacienteAseg_ID() != getEXME_PacienteAseg_ID()) {
				//Si esta a nivel paciente (EXME_CtaPac_ID == 0 ) es repetida
				if(getEXME_CtaPac_ID() == 0 ){
					log.saveError(Utilerias.getMsg(getCtx(), "patient.insurance.error.same"), getC_BPartner().getName()
							+ (getEXME_PlanAseg_ID() > 0 ? " - " + getEXME_PlanAseg().getName() : ""));
					return false;
				//Si esta a nivel encuentro y coinciden en SUPPORTBILLING, es repetida 
				}else if( getEXME_CtaPac_ID() > 0 && aseg.getSupportBilling().equalsIgnoreCase(getSupportBilling())){
					String supportBillingName = MRefList.getListName(Env.getCtx(), MEXMEPacienteAseg.SUPPORTBILLING_AD_Reference_ID, getSupportBilling());
					log.saveError(Utilerias.getMsg(getCtx(), "patient.insurance.error.same"), getC_BPartner().getName()
							+ (getEXME_PlanAseg_ID() > 0 ? " - " + getEXME_PlanAseg().getName() : "") + Utilerias.getMsg(getCtx(), "msj.For").toLowerCase() + supportBillingName);
					return false;				
				}	
			}
		}
		// Fin de busqueda de partners repetidos
		if(getEXME_CtaPac_ID() > 0 && !validateSupportBilling()){
			log.saveError(Utilerias.getMsg(getCtx(), "error.bitacoraBanco.save"), Utilerias.getMsg(getCtx(), "insurance.error.justThree") + " "
						+ MRefList.getListName(Env.getCtx(), MEXMEPacienteAseg.SUPPORTBILLING_AD_Reference_ID, getSupportBilling())
						+ " " + Utilerias.getMsg(getCtx(), "insurance.by"));
				return false;
		}
//		if(getEXME_PacienteAseg_ID() > 0){
//			MEXMEPacienteAseg aseg = new MEXMEPacienteAseg(getCtx(), getEXME_PacienteAseg_ID(), null);
//			if(StringUtils.isNotEmpty(getSUPPORTBILLING()) && getSUPPORTBILLING().equalsIgnoreCase(aseg.getSUPPORTBILLING())){
//				supportBeforeChange = "";
//			}else{
//				supportBeforeChange = aseg.getSUPPORTBILLING();
//			}
//		}

		if (getPriority() > 0) {
			final List<MEXMEPacienteAseg> lista = getAllByPatient(getCtx(), getEXME_Paciente_ID(), getEXME_CtaPac_ID(), get_TrxName());
			if (lista != null) {
				if (newRecord) {
					for (MEXMEPacienteAseg pacAseg : lista) {
						// Esta validatePriority solo debe ser null si se graba desde WPatientForm o WRegistration
						if (getPriority() == pacAseg.getPriority() && isValidatePriority() && pacAseg.isActive()) {
							log.saveError("SaveError", Utilerias.getMsg(getCtx(), "msg.prioridad.aseg"));
							return false;
						} else {
							setIsMain(getPriority() == 1);
						}
					}
				} else {
					for (MEXMEPacienteAseg pacAseg : lista) {
						if (pacAseg.getEXME_PacienteAseg_ID() == getEXME_PacienteAseg_ID()) {
							if (getPriority() != pacAseg.getPriority()) {
								List<MEXMEPacienteAseg> lista2 = getAllByPatient(getCtx(), getEXME_Paciente_ID(), getEXME_CtaPac_ID(), null);
								for (MEXMEPacienteAseg pacAseg2 : lista2) {
									// Esta validatePriority solo debe ser null si se graba desde WPatientForm o WRegistration
									if (getPriority() == pacAseg2.getPriority() && isValidatePriority()) {
										log.saveError("SaveError", Utilerias.getMsg(getCtx(), "error.expediente.no"));
										return false;
									} else {
										if (getPriority() == 1) {
											setIsMain(true);
										}
									}
								}
								if (pacAseg.getPriority() == 1) {
									setIsMain(false);
								}

							}
						}
					}
				}
			}

		} else {
			log.saveError("SaveError", Utilerias.getMsg(getCtx(), "error.trasplante.prioridad"));
			return false;
		}

		setType2(MEXMEPacienteAseg.TYPE2_PolicyHolder);
		if (isPolicyHolder()) {
			final MEXMEPaciente pac = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());

			setPhone(pac.getTelParticular());
			setParticularComCode(pac.getParticularComCode());
			setCelular(pac.getTelCelular());
			setDriverLicense(pac.getCurp());
			setC_RegionDriverLic_ID(pac.getC_RegionDriverLic_ID());
			setC_Location_ID(pac.getC_Location_ID());
			setEXME_Employment_ID(pac.getEXME_Employment_ID());						
		}

		return true;
	}

	public static MEXMEPacienteAseg getAseg(Properties ctx, int asegID, Integer planID, int pacienteID){
		return getAseg(ctx,asegID,planID,pacienteID,0);
	}
	/**
	 * 
	 * @param ctx
	 * @param asegID
	 * @param planID
	 * @param pacienteID
	 * @param ctacPacID - 0 Para Aseguradoras a nivel paciente
	 * @return
	 */
	public static MEXMEPacienteAseg getAseg(Properties ctx, int asegID, Integer planID, int pacienteID, int ctacPacID) {
		final List<Object> lst = new ArrayList<Object>();
		lst.add(asegID);
		lst.add(pacienteID);
		lst.add(ctacPacID);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" C_BPartner_ID=? AND EXME_Paciente_ID=? AND EXME_CtaPac_ID = ?");
		if (planID != null) {
			sql.append(" AND EXME_PlanAseg_ID").append(planID > 0 ? " =? " : " is null ");
			if (planID > 0) {
				lst.add(planID);
			}
		}

		return new Query(ctx, Table_Name, sql.toString(), null)
				.setParameters(lst)
				.setOnlyActiveRecords(true)
				.first();
	}
	public static List<MEXMEPacienteAseg> getListAseg(Properties ctx, int asegID, Integer planID, int pacienteID, int ctacPacID, boolean onlyActive, String trxName) {
		final List<Object> lst = new ArrayList<Object>();
		lst.add(asegID);
		lst.add(pacienteID);
		lst.add(ctacPacID);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" C_BPartner_ID=? AND EXME_Paciente_ID=? AND EXME_CtaPac_ID = ?");
		if (planID != null) {
			sql.append(" AND EXME_PlanAseg_ID").append(planID > 0 ? " =? " : " is null ");
			if (planID > 0) {
				lst.add(planID);
			}
		}

		return new Query(ctx, Table_Name, sql.toString(), trxName)
				.setParameters(lst).setOnlyActiveRecords(onlyActive)
				.list();
	}

	/**
	 * 
	 * @param ctx
	 * @param pacienteID
	 * @param ctacPacID
	 * @return
	 */
	public static MEXMEPacienteAseg getAsegPrior(Properties ctx, int pacienteID, int ctacPacID, int priority, String confType) {
		final List<Object> lst = new ArrayList<Object>();
		lst.add(pacienteID);
		lst.add(ctacPacID);
		lst.add(priority);
		lst.add(confType.equals(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim) 
		? MEXMEPacienteAseg.SUPPORTBILLING_Institucional 
		: MEXMEPacienteAseg.SUPPORTBILLING_Professional);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_Paciente_ID=? AND EXME_CtaPac_ID = ? AND Priority=? AND SupportBilling=? ");
		
	
		return new Query(ctx, Table_Name, sql.toString(), null)
				.setParameters(lst)
				.setOnlyActiveRecords(true)
				.first();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param pacienteID
	 * @param ctacPacID
	 * @return
	 */
	public static List<MEXMEPacienteAseg> getAsegPriorList(Properties ctx, int pacienteID, int ctacPacID, int priority, String confType) {
		final List<Object> lst = new ArrayList<Object>();
		lst.add(pacienteID);
		lst.add(ctacPacID);
		lst.add(priority);
		lst.add(confType.equals(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim) 
		? MEXMEPacienteAseg.SUPPORTBILLING_Institucional 
		: MEXMEPacienteAseg.SUPPORTBILLING_Professional);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_Paciente_ID=? AND EXME_CtaPac_ID = ? AND Priority=? AND SupportBilling=? ");
		return new Query(ctx, Table_Name, sql.toString(), null)
				.setParameters(lst)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get the insurance according to the billing status
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	
	public static MEXMEPacienteAseg getBillingInsurance(Properties ctx, MEXMECtaPacExt ctaPacExt, String confType) {
		final int priority;
		String status = confType.equals(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim) || 
					    confType.equals(X_HL7_MessageConf.CONFTYPE_OutpatientProfessionalClaim) 
					    ? ctaPacExt.getProfessionalStep() : ctaPacExt.getInstitutionalStep();
		if (MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance.equals(status)) {
			priority = 1;
		} else if (MEXMECtaPacExt.INSTITUTIONALSTEP_SecondInsurance.equals(status)) {
			priority = 2;
		} else if (MEXMECtaPacExt.INSTITUTIONALSTEP_ThirdInsurance.equals(status)) {
			priority = 3;
		} else {
			priority = -1;
		}
		return new Query(ctx, Table_Name, " EXME_CtaPac_ID=? AND Priority=? AND SupportBilling=? ", null)
				.setParameters(ctaPacExt.getEXME_CtaPac_ID(), priority, 
						confType.equals(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim) 
							? MEXMEPacienteAseg.SUPPORTBILLING_Institucional 
							: MEXMEPacienteAseg.SUPPORTBILLING_Professional)
				.setOnlyActiveRecords(true)
				.first();
	}
	
	/**
	 * Get the insurance according to the billing status
	 * @param ctx
	 * @param Ext
	 * @return
	 */
	
	public static MEXMEPacienteAseg getBillingInsurance(Properties ctx, int ctaPacExtID, String confType) {
		final MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, ctaPacExtID, null);
		return getBillingInsurance(ctx, ctaPacExt, confType);
	}
	
	
	/**
	 * Get the insurance according to the billing status
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	
	public static MEXMEPacienteAseg getBillingInsuranceByPriority(Properties ctx, int ctaPacID, String confType, int priority) {
		return new Query(ctx, Table_Name, " EXME_CtaPac_ID=? AND Priority=? AND SupportBilling=? and IsActive ='Y' ", null)
		.setParameters(ctaPacID, priority, 
				confType.equals(Constantes.INST) 
					? MEXMEPacienteAseg.SUPPORTBILLING_Institucional 
					: MEXMEPacienteAseg.SUPPORTBILLING_Professional)
		.first();
	}
	
	
	/**
	 * Get the insurance according to the priority received
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	
	public static MEXMEPacienteAseg getBillingInsurance(Properties ctx, int EXME_CtaPac_ID, int priority) {
		final MEXMECtaPac ctapac = new MEXMECtaPac(ctx, EXME_CtaPac_ID, null);
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? AND Priority=? ", null)
				.setParameters(ctapac.getEXME_Paciente_ID(), priority)
				.setOnlyActiveRecords(true)
				.first();
	}

	/**
	 * Obtiene la EXME_PacienteAseg principal que este a nivel paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return
	 */
	public static MEXMEPacienteAseg getMain(Properties ctx, int EXME_Paciente_ID) {
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? AND isMain='Y' and EXME_CtaPac_ID = 0", null)
				.setParameters(EXME_Paciente_ID)
				.setOnlyActiveRecords(true)
				.first();
	}

	/**
	 * Obtiene la EXME_PacienteAseg principal que este a nivel paciente
	 * Se utiliza para facturacion Mexico
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return
	 */
	public static MEXMEPacienteAseg getAseguradora(Properties ctx, int EXME_Paciente_ID) {
		return new Query(ctx, Table_Name, " EXME_Paciente_ID = ? AND Priority = 1 AND ( EXME_CtaPac_ID IS NULL OR EXME_CtaPac_ID = 0) ", null)
				.setParameters(EXME_Paciente_ID)
				.setOnlyActiveRecords(true)
				.first();
	}
	
	public static void movePriorityTo(Properties ctx, MEXMEPacienteAseg asegOrigen, int newPriority){
		int priorityOrigen = asegOrigen.getPriority();
		int priorityDestino = newPriority;
		if(priorityOrigen != priorityDestino){
			String sql = "";
			if(asegOrigen.getEXME_CtaPac_ID() > 0){
				sql = COLUMNNAME_SupportBilling + " = '" + asegOrigen.getSupportBilling() + "'";
			}
			List<MEXMEPacienteAseg> asegs = MEXMEPacienteAseg.getAllbyCtaPac(ctx, asegOrigen.getEXME_CtaPac_ID(),sql);
	
			if(priorityOrigen > priorityDestino){
				for(int i = priorityDestino -1; i < priorityOrigen -1; i++){
					MEXMEPacienteAseg aseg = asegs.get(i);
					aseg.setValidatePriority(false);
					aseg.setPriority(i + 2); 	// Forzara a las priorities a se consecutivas SIEMPRE que se utilice este metodo	
//					aseg.setPriority(aseg.getPriority() +  1);	//TODO Esta linea es valida solo si las prioritys son consecutivas
					aseg.save();
				}
			}else{
				for(int i = priorityOrigen; i <= priorityDestino -1; i++){
					MEXMEPacienteAseg aseg = asegs.get(i);
					aseg.setValidatePriority(false);
					aseg.setPriority(i);
					aseg.save();
				}
			}
			
			asegOrigen.setValidatePriority(false);
			asegOrigen.setPriority(newPriority);
			asegOrigen.save();
		}
		
	}
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean afterSave(final boolean newRecord, boolean success) {
		if (!success) {
			return success;
		}
//		if(getEXME_CtaPac_ID() == 0){
//			MEXMEPaciente pac = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
//		
//			if (isMain()) {
//				if (pac.getC_BPartner_Seg_ID() != getC_BPartner_ID()) {
//					pac.setC_BPartner_Seg_ID(getC_BPartner_ID());
//					success = pac.save();
//				}
//			} else {
//				if (pac.getC_BPartner_Seg_ID() == getC_BPartner_ID()) {
//					pac.setC_BPartner_Seg_ID(0);
//					success = pac.save();
//				}
//			}
//		}else{
		if(getEXME_CtaPac_ID() != 0){
			if (isActive()){
				if(newRecord && getPriority() == 1){
					MEXMECtaPacExt ext = (MEXMECtaPacExt) getEXME_CtaPac().getEXME_CtaPacExt(); 
					if(getSupportBilling() != null){
						if(getSupportBilling().equalsIgnoreCase(SUPPORTBILLING_Institucional)){
							ext.setInstitutionalStep(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
						}else if(getSupportBilling().equalsIgnoreCase(SUPPORTBILLING_Professional)){
							ext.setProfessionalStep(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
						}else if(getSupportBilling().equalsIgnoreCase(SUPPORTBILLING_Both)){
							ext.setProfessionalStep(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
							ext.setInstitutionalStep(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
						}else{
							ext.setProfessionalStep(null);
							ext.setInstitutionalStep(null);
						}
					}else{
						ext.setProfessionalStep(null);
						ext.setInstitutionalStep(null);
					}
					success = ext.save(get_TrxName());
				}
				//Si la aseguradora cambio y el encuentro estaba codificado recrear claim
//				if (isCurrentBilling()){
//					if (getSUPPORTBILLING().equals(SUPPORTBILLING_Institucional) && 
//							!(getEXME_CtaPac().getEXME_CtaPacExt().getInstitutionalStatus().equals(
//									MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled)
//							  || getEXME_CtaPac().getEXME_CtaPacExt().getInstitutionalStatus().equals(
//									  MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable)
//							  || getEXME_CtaPac().getEXME_CtaPacExt().getInstitutionalStatus().equals(
//									  MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete))
//							){
//						updateInvoice(MHL7MessageConf.CONFTYPE_InstitutionalClaim);
//					}else if (getSUPPORTBILLING().equals(SUPPORTBILLING_Professional) && 
//								!(getEXME_CtaPac().getEXME_CtaPacExt().getProfessionalStatus().equals(
//										MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled)
//							  || getEXME_CtaPac().getEXME_CtaPacExt().getProfessionalStatus().equals(
//									  MEXMECtaPacExt.PROFESSIONALSTATUS_NotApplicable)
//							  || getEXME_CtaPac().getEXME_CtaPacExt().getProfessionalStatus().equals(
//									  MEXMECtaPacExt.PROFESSIONALSTATUS_CodingIncomplete))
//							){
//						updateInvoice(MHL7MessageConf.CONFTYPE_ProfessionalClaim);
//					}
//				}	
				
			}

		}
		
		
		if (success){
			if (newRecord && getEXME_CtaPac_ID()>0 ){
//	  		Si alguien ocupa la vista MEXMEPacienteCtaV y crea indices con subfijo descomentar lineas siguientes		
//				try {
//					QuickSearchTables.deleteIndexes(getCtx(), MEXMEPacienteCtaV.Table_Name, null);
//				} catch (Exception ex) {
//					log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
//				}

				MEXMEPacienteCtaV.updateSearchCta(p_ctx, getEXME_CtaPac_ID(), get_TrxName());
			}
		}
		
		// Si tiene cuetna, y es nuevo o cambio su estado se actualiza
		if (getEXME_CtaPac_ID() > 0 && (newRecord || is_ValueChanged(PO.COLUMN_IsActive))) {
			MEXMECtaPayer.checkIndex(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		}
		
		return success;
	}



//	private void updateInvoice(String confType) {
//		if (!MEXMEClaimMessage.generateMessage(Env.getCtx(), (MEXMECtaPac) getEXME_CtaPac(), 
//				confType, get_TrxName())){
////			Si no se genera mensaje mandamos informacion al log
//			log.log(Level.SEVERE, Utilerias.getMessage(Env.getCtx(), null, "claim.error.CreatingClaim")
//					+" Encounter Id: "+getEXME_CtaPac_ID()
//					+ " type = "+confType);
//		}
//		//Buscar Aseguradora desactivada en la misma prioridad,si tiene factura, actualizarla con el nuevo C_BPartner_ID
//		// marcar para postear y eliminar el posteo anterior (no se puede invocar el PostImmediate.
//		List<MEXMEPacienteAseg> asegLst = MEXMEPacienteAseg.getAsegPriorList(getCtx(), 
//				getEXME_Paciente_ID(),getEXME_CtaPac_ID(), getPriority(), confType, Boolean.FALSE);
//		
//		int invID = 0;
//		for (MEXMEPacienteAseg aseg : asegLst) {
//			invID = MInvoice.getByBPartner(getCtx(), confType, 
//					aseg.getC_BPartner_ID(), aseg.getEXME_CtaPac_ID(), get_TrxName());
//			if (invID>0){
//				break;
//			}
//		}
//		
//		
//		if (invID >0){
//			if (MFactAcct.delete(MInvoice.Table_ID, invID, get_TrxName()) < 0)
//				log.log(Level.SEVERE, Utilerias.getMessage(Env.getCtx(), null, "error.insulinas.registro.eliminar")
//						+" Posting, Encounter Id: "+getEXME_CtaPac_ID()
//						+ " type = "+confType);		//	could not delete
//			MInvoice invoice = MInvoice.get(getCtx(), invID, get_TrxName());
//			invoice.setBPartner(MBPartner.get(getCtx(), getC_BPartner_ID()));
//			invoice.setPosted(Boolean.FALSE);
//			invoice.setProcessed(Boolean.TRUE);
//			if (!invoice.save()){
//				log.log(Level.SEVERE, Utilerias.getMessage(Env.getCtx(), null, "error.caja.factura.noSave")
//						+" Updating Payer"
//						+" , Encounter Id: "+getEXME_CtaPac_ID()
//						+"  Payer Id: "+getC_BPartner_ID()
//						+"  Invoice Id: "+invID
//						+ " type = "+confType);		//	could not delete
//			}
//		}		
//	}

	public static MEXMEPacienteAseg getFirstPriority(Properties ctx, int pacienteID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? and EXME_CtaPac_ID = ? ", trxName)
				.setParameters(pacienteID, 0)
				.setOrderBy("priority")
				.setOnlyActiveRecords(true)
				.first();
	
	}
	
	public static MEXMEPacienteAseg getFirstPriority(Properties ctx, int pacienteID, int pCtaPacID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? and EXME_CtaPac_ID = ? ", trxName)
				.setParameters(pacienteID, pCtaPacID)
				.setOrderBy("priority")
				.setOnlyActiveRecords(true)
				.first();
	
	}
	
	public static MEXMEPacienteAseg getFromPriority(Properties ctx, int pacienteID, int priority, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? AND priority=? and EXME_CtaPac_ID = ? ", trxName)
				.setParameters(pacienteID, priority,0)
				.setOrderBy("priority")
				.setOnlyActiveRecords(true)
				.first();
	}

	/**
	 * Prioridad de paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return
	 */
	public static LabelValueBean validatePrioridad(Properties ctx, int EXME_Paciente_ID, int prioridad, int EXME_PacienteAseg_ID) {

		LabelValueBean exists = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT * FROM EXME_PacienteAseg WHERE EXME_Paciente_ID=? AND ( isMain='Y' OR Priority=? ) ");
		sql.append(" AND isActive = 'Y' ");
		sql.append(" AND EXME_PacienteAseg_ID <> ? ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setInt(2, prioridad);
			pstmt.setInt(3, EXME_PacienteAseg_ID);
			
			rs = pstmt.executeQuery();
			int count = 0;
			int equal = 0;
			while (rs.next()){
				final MEXMEPacienteAseg nuevo = new MEXMEPacienteAseg(ctx, rs, null);
				if(nuevo.isMain()) {
					count ++;
				}
				if(prioridad ==  nuevo.getPriority()) {
					equal ++;
				}
			}

			exists = new LabelValueBean(String.valueOf(count), String.valueOf(equal));
		} catch (Exception e) {
			slog.log(Level.SEVERE, " MEXMEPacienteBP.validateMain " + sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}

		return exists;
	}

	public boolean isValidatePriority() {
		return validatePriority;
	}

	public void setValidatePriority(final boolean validatePriority) {
		this.validatePriority = validatePriority;
	}	
	
	public String getPriorityName() {
		String label;
		if (getPriority() == 1) {
			label = Utilerias.getMsg(getCtx(), "patient.primaryInsurance");
		} else if (getPriority() == 2) {
			label = Utilerias.getMsg(getCtx(), "patient.secundaryInsurance");
		} else if (getPriority() == 3) {
			label = Utilerias.getMsg(getCtx(), "patient.tertiaryInsurance");
		} else {
			label = StringUtils.EMPTY;
		}
		return label;
	}
	public static int  getNextPriority(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, String supportBilling){
		List<MEXMEPacienteAseg> lista = new ArrayList<MEXMEPacienteAseg>();
		if(EXME_CtaPac_ID == 0){
			lista.addAll(getAllByPatient(ctx, EXME_Paciente_ID,EXME_CtaPac_ID, null));
		}else if (StringUtils.isNotEmpty(supportBilling) && !supportBilling.equalsIgnoreCase(SUPPORTBILLING_Both)){
			lista.addAll(getAllbyCtaPac(ctx, EXME_CtaPac_ID, COLUMNNAME_SupportBilling + " = '" + supportBilling + "'"));
		}else{
			return 0;  // Pasara solo cuando se intente guardar una asegurado a nivel encouentro con SUPPORTBILLING = Both, con esto se forzara a marcar error (priority nunca debe de ser 0)
		}
		int prior = 0;
		for (int i = 0; i < lista.size(); i++) {
			if(prior < lista.get(i).getPriority()){
				prior = lista.get(i).getPriority();
			}
		}
		return prior + 1;
		
	}
	
	public static List<MEXMEPacienteAseg> getAllbyCtaPac(Properties ctx,int  EXME_CtaPac_ID){
		return new Query(ctx, Table_Name, "EXME_CtaPac_ID=?", null)
		.setOnlyActiveRecords(true)
		.setParameters(EXME_CtaPac_ID).setOrderBy(" priority asc")
		.list();
	}
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param sql no incluir "WHERE" en la sentencia
	 * @return
	 */
	public static List<MEXMEPacienteAseg> getAllbyCtaPac(Properties ctx,int  EXME_CtaPac_ID, String where){
		StringBuilder sql = new StringBuilder("EXME_CtaPac_ID=?"); 
		if(StringUtils.isNotEmpty(where)){
			sql.append(" AND ").append(where);
		}
		 return new Query(ctx, Table_Name, sql.toString(), null)
		.setOnlyActiveRecords(true)
		.setParameters(EXME_CtaPac_ID).setOrderBy(" priority asc")
		.list();
	}
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param supportBillingType
	 * @return true si hay menos coincidencias 3 coincidencias (es decir si es valido guardar)
	 */
	public static boolean validateSupportBilling(Properties ctx,int  EXME_CtaPac_ID, String supportBillingType){
		int limit = 3;
		String base = "SUPPORTBILLING in ('" + SUPPORTBILLING_Both + "'";
		if(StringUtils.isNotEmpty(supportBillingType)){
			if(supportBillingType.equalsIgnoreCase(SUPPORTBILLING_Both)){
				String basePlus = base + ",'" + SUPPORTBILLING_Professional + "')";
				if (getAllbyCtaPac(ctx,EXME_CtaPac_ID,basePlus).size() >= limit){
					return false;
				}
				basePlus = base + ",'" + SUPPORTBILLING_Institucional + "')";
				if (getAllbyCtaPac(ctx,EXME_CtaPac_ID,basePlus).size() >= limit){
					return false;
				}
			}else{
				String basePlus = base + ",'" + supportBillingType + "')";
				if (getAllbyCtaPac(ctx,EXME_CtaPac_ID,basePlus).size() >= limit){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param supportBillingType
	 * @return true si hay menos coincidencias 3 coincidencias (es decir si es valido guardar)
	 */
	public boolean validateSupportBilling(){
		int limit = 3;
		//List<MEXMEPacienteAseg> asegs;		
		if(StringUtils.isNotEmpty(getSupportBilling())){
			if(getSupportBilling().equalsIgnoreCase(SUPPORTBILLING_Both)){
				if (!validateSupportBillingFor(SUPPORTBILLING_Professional,limit)){
					return false;
				}
				if(!validateSupportBillingFor(SUPPORTBILLING_Institucional,limit)){
					return false;
				}
			}else{
				if(!validateSupportBillingFor(getSupportBilling(),limit)){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validateSupportBillingFor(String supportBilling, int limit){
		String basePlus = "SUPPORTBILLING in ('" + SUPPORTBILLING_Both + "'" + ",'" + supportBilling + "')";
		List<MEXMEPacienteAseg> asegs = getAllbyCtaPac(getCtx(),getEXME_CtaPac_ID(),basePlus);
		if (asegs.size() >= limit){
			if(getEXME_PacienteAseg_ID() > 0){ //Es decir el registro no es nuevo
				//Checamos si alguna de las coincidencias es el mismo registro que tenemos
				for(MEXMEPacienteAseg aseg : asegs){
					//Si es el mismo registro
					if(aseg.getEXME_PacienteAseg_ID() == getEXME_PacienteAseg_ID()){
						//Checamos si sigue contanto con el mismo SupportBilling, en caso que no se regresa un falso
						if(StringUtils.isNotEmpty(aseg.getSupportBilling()) && aseg.getSupportBilling().equalsIgnoreCase(supportBilling)){
							return true;
						}
					}
				}
				return false;
			}else{
				return false;
			}			
		}
		return true;
	}
//	private void refreshPriorityOrderByCtaPac(String supportBilling){
//		String sql = COLUMNNAME_SUPPORTBILLING + " = '"+ supportBilling + "'";
//		List<MEXMEPacienteAseg> asegs = MEXMEPacienteAseg.getAllbyCtaPac(Env.getCtx(), getEXME_CtaPac_ID(), sql);
//		for(int i = 0; i < asegs.size(); i++){
//			MEXMEPacienteAseg asegItem = asegs.get(i);
//				asegItem.setPriority(i + 1);
//				asegItem.setValidatePriority(false);
//				if(!asegItem.save()){
//					break;
//				}											
//		}
//	}
	
	
	/**
	 * Que no tenga pagos relacionados al encuentro
	 * @param mCtaPac
	 * @return
	 */
	public boolean validCancel(final MEXMECtaPac mCtaPac) {
		// Tiene pagos
		List<MPayment> lstPagos = MPayment.getPayments(
				mCtaPac.getEXME_CtaPac_ID(), getC_BPartner_ID(),
				mCtaPac.getCtx());
		if (!lstPagos.isEmpty()) {
			slog.log(Level.SEVERE,
					"This insurance can not be deleted, already has a Payment transaction");
			return false;
		}

//		// Tiene claim
//		String whereClause = " AND Status = 'A' AND C_BPartner_ID = " + getC_BPartner_ID();
//		MEXMEClaimLog log = MEXMEClaimLog.getByCtaPac(mCtaPac.getCtx(),
//				mCtaPac.getEXME_CtaPac_ID(), whereClause, null);
//		if (log != null) {
//			slog.log(Level.SEVERE,
//					"This insurance can not be deleted, already has a Payment transaction");
//			return false;
//		}

		// Tiene factura
//		MInvoice invoice = MEXMEInvoice.getMInvoice(mCtaPac.getCtx(),
//				mCtaPac.getEXME_CtaPac_ID(), getC_BPartner_ID());
//		if (invoice != null) {
//			slog.log(Level.SEVERE,
//					"This insurance can not be deleted, already has a invoice transaction");
//			return false;
//		}
		return true;
	}
		
	/**
	 * Al quitar la aseguradora
	 * @param mCtaPac
	 * @return
	 */
	public boolean cancel(MEXMECtaPac ctaPac){
		//
		if(validCancel(ctaPac)){
			setIsActive(false);
			if (!save()) {
				slog.log(Level.SEVERE, " Failed to update the encounter step information ");
				return false;
			}
			boolean successReSorting = true; 
			String sql = COLUMNNAME_SupportBilling + " = '"+ getSupportBilling() + "'";
			List<MEXMEPacienteAseg> asegs = MEXMEPacienteAseg.getAllbyCtaPac(Env.getCtx(), getEXME_CtaPac_ID(), sql);
			if (asegs.size()==0){
				if(getSupportBilling().equals(SUPPORTBILLING_Professional)){
					
					ctaPac.getExtCero().setProfessionalStep(MEXMECtaPacExt.PROFESSIONALSTEP_Default);
					ctaPac.getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled);
					if(!ctaPac.getExtCero().save()){
						slog.log(Level.SEVERE, " Failed to update the encounter step information ");
						return false;
					}else{
						return true;
					}
					
				}else{
					ctaPac.getExtCero().setInstitutionalStep(MEXMECtaPacExt.INSTITUTIONALSTEP_Default);
					ctaPac.getExtCero().setInstitutionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled);
					if(!ctaPac.getExtCero().save()){
						slog.log(Level.SEVERE, " Failed to update the encounter step information ");
						return false;
					}else{
						return true;
					}					
				}
			}else{
				for(MEXMEPacienteAseg asegItem :asegs){
					if(asegItem.getPriority() > getPriority()){
						asegItem.setPriority(asegItem.getPriority() - 1);
						asegItem.setValidatePriority(false);
						if(!asegItem.save()){
							successReSorting = false;
							slog.log(Level.INFO, " if(!asegItem.save()) ");
							break;
						}
					}								
				}
				return successReSorting;
			}
			
		}else{
			slog.log(Level.SEVERE, "This insurance can not be deleted, already has a invoice transaction");
			return false;
		}
	}
	
	public boolean cancel(){
		return cancel(new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), null));		
	}
	
	
	/**
	 * Que no tenga pagos relacionados al encuentro
	 * @param mCtaPac
	 * @return
	 */
	public static boolean validCancelTest(Properties ctx, int ctapac, int socio) {
		// Tiene pagos
		List<MPayment> lstPagos = MPayment.getPayments(
				ctapac, socio,
				ctx);
		if (!lstPagos.isEmpty()) {
			slog.log(Level.SEVERE,
					"This insurance can not be deleted, already has a Payment transaction");
			return false;
		}

		// Tiene claim
		String whereClause = " AND Status = 'A' AND C_BPartner_ID = " + socio;
		MEXMEClaimLog log = MEXMEClaimLog.getByCtaPac(ctx,
				ctapac, whereClause, null);
		if (log != null) {
			slog.log(Level.SEVERE,
					"This insurance can not be deleted, already has a Payment transaction");
			return false;
		}

		// Tiene factura
		MInvoice invoice = MInvoice.getMInvoice(ctx,
				ctapac, socio);
		if (invoice == null || invoice.getC_Invoice_ID() == 0) {
			slog.log(Level.SEVERE,
					"This insurance can not be deleted, already has a invoice transaction");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param ctx
	 * @param pacienteID
	 * @param pCtaPacID
	 * @param confType
	 * @param trxName
	 * @return
	 */
	public static MEXMEPacienteAseg getFirstPriority(Properties ctx, int pacienteID, int pCtaPacID, String confType, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? and EXME_CtaPac_ID = ? AND supportbilling IN ('BO', ? ) ", trxName)
				.setParameters(pacienteID, pCtaPacID, confType)
				.setOrderBy("priority")
				.setOnlyActiveRecords(true)
				.first();
	
	}
	
	/**
	 * Número de aseguradoras del paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @return Número de aseguradoras o -1 de no tener
	 */
	public static int countAseg(Properties ctx, int pacId) {
		StringBuilder sqlAseg = new StringBuilder();
		sqlAseg.append("SELECT ");
		sqlAseg.append("  COUNT(*) ");
		sqlAseg.append("FROM ");
		sqlAseg.append("  exme_pacienteaseg ");
		sqlAseg.append("WHERE ");
		sqlAseg.append("  isactive = 'Y' AND ");
		sqlAseg.append("  EXME_Paciente_ID = ? ");
		sqlAseg.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));

		return DB.getSQLValue(null, sqlAseg.toString(), pacId);
	}
	
	/**
	 * Set Values Paciente
	 * @param pPacienteID
	 * @param esAsegurado
	 */
	public void setValues(final int pPacienteID, final boolean esAsegurado){
		setEXME_Paciente1_ID(pPacienteID);
		setEXME_Paciente_ID(pPacienteID);
		if (!esAsegurado) {
			setIsActive(false);
		}
	}

	@Override
	public String getCurp() {
		return getEXME_Paciente().getCurp();
	}
	
	@Override
	public String getLastname2() {
		return null;
	}

}
