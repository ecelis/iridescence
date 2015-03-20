/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.BalanceServicesH;
import org.compiere.model.bean.BalanceServicesH.BalanceService;
import org.compiere.model.bpm.GetPrice;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Saldo de servicios de paciente (pro mujer)
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class MDHBalanceServices extends X_DH_BalanceServices {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		sLog				= CLogger.getCLogger(MDHBalanceServices.class);

	/**
	 * @param ctx
	 * @param DH_BalanceServices_ID
	 * @param trxName
	 */
	public MDHBalanceServices(final Properties ctx, final int DH_BalanceServices_ID, final String trxName) {
		super(ctx, DH_BalanceServices_ID, trxName);
		if (is_new()) {
			setAmount(1);
			setRemainingAmt(1);
		}
	}

	/**
	 * @param from
	 * @param productId
	 */
	public MDHBalanceServices(final MDHPatientServices from, final int productId) {
		this(from.getCtx(), 0, from.get_TrxName());
		setEXME_Paciente_ID(from.getEXME_Paciente_ID());
		setEXME_PaqBase_Version_ID(from.getEXME_PaqBase_Version_ID());
		if (productId > 0) {
			setM_Product_ID(productId);
		} else {
			setM_Product_ID(from.getM_Product_ID());
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDHBalanceServices(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Servicios a los que se tiene derecho, uno o mas pacientes
	 * 
	 * @param ctx Conexto
	 * @param remaining true: solo los disponibles false: todos los servicios
	 * @param patientIds Id de pacientes a buscar su saldo
	 * @param trxName nombre de la transaccion
	 * @return
	 * @throws SQLException
	 */
	public static BalanceServicesH getLines(final Properties ctx, final int patientId, final boolean remaining, final String trxName)
		throws SQLException {
		// Ids de los pacientes a buscar su saldo (en caso de que no sea cliente)
		final List<Integer> patientIds = MDHPatientInfo.getLines(ctx, patientId, trxName);

		final BalanceServicesH retVale = new BalanceServicesH();
		final List<Object> params = new ArrayList<>();

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT l.DH_BalanceServices_id, ");
		sql.append("        l.EXME_Paciente_ID, ");
		sql.append("        l.m_Product_ID, ");
		sql.append("        l.EXME_PaqBase_Version_ID, ");
		sql.append("       pb.IsUniversalComp as univComp, ");
		sql.append("        p.name as prodName, ");
		sql.append("        l.RemainingAmt ");
		sql.append(" FROM DH_BalanceServices l \n");
		sql.append(" INNER JOIN M_Product p ON (l.M_Product_ID=p.M_Product_ID) \n");
		sql.append(" LEFT JOIN EXME_PaqBase_Version pv ON (l.EXME_PaqBase_Version_ID=pv.EXME_PaqBase_Version_ID) \n");
		sql.append(" LEFT JOIN EXME_PaqBase pb ON (pv.EXME_PaqBase_ID=pb.EXME_PaqBase_ID) \n");
		sql.append(" LEFT JOIN EXME_PaqBaseDet pd ON (pv.EXME_PaqBase_Version_ID=pd.EXME_PaqBase_Version_ID) \n");

		sql.append(" WHERE l.isActive='Y' \n");
		if (remaining) {
			sql.append("   AND l.RemainingAmt>0 \n");
		}
		if (patientIds.isEmpty()) {
			sql.append(" AND l.EXME_Paciente_ID=? \n");
			params.add(patientId);
		} else {
			StringBuilder sql2 = new StringBuilder();
			sql2.append(" IN (");
			for (int i = 0; i < patientIds.size(); i++) {
				if (i > 0) {
					sql2.append(",");
				}
				sql2.append("?");
			}
			sql2.append(") \n");
			params.addAll(patientIds);
			sql.append(" AND l.EXME_Paciente_ID").append(sql2).append(" AND \n");
			sql.append("(CASE WHEN l.EXME_PaqBase_Version_ID > 0 THEN \n");
			sql.append("    CASE WHEN pd.Tipo='").append(MEXMEPaqBaseDet.TIPO_Nontransferable).append("' THEN 0 \n");
			sql.append("         WHEN pd.Tipo='").append(MEXMEPaqBaseDet.TIPO_Transferable).append("' THEN 1 \n");
			sql.append("         WHEN pd.Tipo='").append(MEXMEPaqBaseDet.TIPO_Transferable_To_Family).append("'  \n");
			sql.append("         THEN (SELECT count(*) FROM EXME_PacienteRel r ");
			sql.append("               INNER JOIN EXME_Parentesco p ON (r.EXME_Parentesco_ID=r.EXME_Parentesco_ID \n");
			sql.append("                                            AND p.isCloseRelative='Y') ");
			sql.append("               WHERE (r.EXME_Paciente2_ID=? AND r.EXME_Paciente1_ID=l.EXME_Paciente_ID) \n");
			sql.append("                 OR  (r.EXME_Paciente1_ID=? AND r.EXME_Paciente2_ID=l.EXME_Paciente_ID)) \n");
			params.add(patientId);
			params.add(patientId);
			sql.append("     ELSE 0 END \n");
			sql.append(" ELSE 1 END) > 0 \n");
		}
		sql.append(" AND EXISTS (SELECT ps.* FROM DH_PatientServices ps WHERE ps.EXME_Paciente_ID=l.EXME_Paciente_ID AND IsActive='Y') \n");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MProduct.Table_Name, "p"));
		sql.append(" ORDER BY p.name ");
		
		try (PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);) {
			DB.setParameters(pstmt, params);
			final ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				final BalanceService bean = new BalanceService();
				bean.setPatientId(rs.getInt(COLUMNNAME_EXME_Paciente_ID));
				bean.setPaqBaseVerId(rs.getInt(COLUMNNAME_EXME_PaqBase_Version_ID));
				bean.setProductId(rs.getInt(COLUMNNAME_M_Product_ID));
				bean.setProdName(rs.getString("ProdName"));

				if (bean.getPaqBaseVerId() > 0) {
					// bean.setUnlimited(rs.getInt(COLUMNNAME_RemainingAmt) == 999);
					if ("Y".equals(rs.getString("univComp"))) {
						bean.setUniversalComp(true);
						retVale.addUniversalComp(bean);
					} else {
						retVale.addPackage(bean);
					}
				} else {
					retVale.addService(bean);
				}
			}

		} catch (final SQLException e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
			throw e;
		}

		return retVale;
	}

	/**
	 * Cresa el saldo de los servicios, a partir de la asignacion de un nuevo servicio / paquete
	 * 
	 * @param serv Asignacion servicio / paquete
	 * @param trxName nombre de la transaccion
	 * @return
	 */
	public static boolean createBalance(final MDHPatientServices serv) {
		boolean retValue = true;

		// si es un paquete
		if (serv.getEXME_PaqBase_Version_ID() > 0 && serv.getQtyEntered() > 0) {
			final List<MEXMEPaqBaseDet> list = MEXMEPaqBaseDet.getDetalleDeVersion(serv.getCtx(), serv.getEXME_PaqBase_Version_ID(), true, null);
			paqdet: for (final MEXMEPaqBaseDet det : list) {
				// ilimitado
				if (det.getCantidad().intValue() == 999) {
					retValue = saveNewBalanceServices(serv, det.getM_Product_ID(), 999);
					if (!retValue) {
						break paqdet;
					}
				} else {
					// separa en 1 el saldo
					int total = det.getCantidad().intValue() * serv.getQtyEntered();
					for (int i = 0; i < total; i++) {
						retValue = saveNewBalanceServices(serv, det.getM_Product_ID(), 1);
						if (!retValue) {
							break paqdet;
						}
					}
				}
			}
		} else if (serv.getM_Product_ID() > 0 && serv.getQtyEntered() > 0) {
			// si solo es un servicio, separa en 1 el saldo
			for (int i = 0; i < serv.getQtyEntered(); i++) { // FIXME
				retValue = saveNewBalanceServices(serv, 0, 1);
				if (!retValue) {
					break;
				}
			}

		} else {
			retValue = false;
		}
		return retValue;
	}

	/**
	 * 
	 * @param from
	 * @param productId
	 * @param qty
	 */
	private static boolean saveNewBalanceServices(final MDHPatientServices from, final int productId, final int qty) {
		final MDHBalanceServices balance = new MDHBalanceServices(from, productId);
		balance.setRemainingAmt(qty);
		return balance.save();
	}

	/**
	 * Actualizacion retroactiva de edicion de paquetes
	 * 
	 * @param det Linea de paquete editada / agregada
	 * @param differenceQty cantidad positiva (si originalmente eran 1 y cambio a 2 se requiere la diferencia: 1), si ahora es ilimitado entonces 999
	 * @param trxName
	 * @return
	 */
	public static boolean addServiceToAllPatient(final MEXMEPaqBaseDet det, final int differenceQty, final String trxName) {
		boolean retValue = false;
		if (differenceQty <= 0) {
			if (MDHPatientServices.isPackVersionUsed(det.getCtx(), det.getEXME_PaqBase_Version_ID(), trxName)) {
				sLog.saveError(null, Utilerias.getMsg(det.getCtx(), "error.qtyNegative"));
			} else {
				return true;
			}
		} else if (det.getEXME_PaqBase_Version_ID() > 0 && det.getM_Product_ID() > 0) {
			// pacientes con la version de paquete
			final List<Integer> patients = new Query(det.getCtx(), I_EXME_Paciente.Table_Name, "", trxName)//
				.addAccessLevelSQL(true)//
				.setJoins(new StringBuilder().append(" INNER JOIN DH_PatientServices ps ")//
					.append(" ON (EXME_Paciente.EXME_Paciente_ID=ps.EXME_Paciente_ID ")//
					.append(" AND ps.EXME_PaqBase_Version_ID=?) "))//
				.setOnlyActiveRecords(true)//
				.setParameters(det.getEXME_PaqBase_Version_ID())//
				.listIds();

			for (final Integer patientId : patients) {
				for (int i = 0; i < differenceQty; i++) {
					final MDHBalanceServices balance = new MDHBalanceServices(det.getCtx(), 0, trxName);
					balance.setEXME_Paciente_ID(patientId);
					balance.setEXME_PaqBase_Version_ID(det.getEXME_PaqBase_Version_ID());
					balance.setM_Product_ID(det.getM_Product_ID());
					if (differenceQty == 999) {
						balance.setRemainingAmt(999);// si es ilimitado
					}
					if (!balance.save()) {
						return retValue;
					}
					if (balance.getRemainingAmt() == 999) {
						break;
					}
				}
			}
			retValue = true;
		} else {
			sLog.saveError(null, Utilerias.getMsg(det.getCtx(), "msj.error.productoRequerido"));
		}
		return retValue;
	}

	/**
	 * Convierte los servicios seleccionados (temporales) en registros de actividad paciente.
	 * 
	 * @param appt cita medica
	 * @param header sol servicios
	 * @param lines lineas temporales de servicios
	 * @param trxName nombre de la transaccion
	 * @return listado de errores
	 */
	public static ErrorList createLines(final MEXMECitaMedica appt, final MEXMEActPacienteIndH header, final List<BalanceService> lines,
		final String trxName) {
		final ErrorList errors = new ErrorList();
		if (lines.isEmpty()) {
			return errors;
		}
		int i = 0;
		final int areaId = new MEXMEEstServ(appt.getCtx(), appt.getEXME_EstServ_ID(), trxName).getEXME_Area_ID();
		final String tipoArea = Env.getTipoArea(appt.getCtx());
		final int warehouseId = Env.getM_Warehouse_ID(appt.getCtx());
		for (final BalanceService line : lines) {

			final MEXMEActPacienteInd ind = new MEXMEActPacienteInd(appt.getCtx(), line.getActIndId(), trxName);
			if (ind.is_new()) {
				ind.setActPacienteIndH(header);
				ind.setEXME_ActPacienteIndH_ID(header.get_ID());
				ind.setActPacienteID(header.getEXME_ActPaciente_ID());
				ind.setM_Warehouse_ID(warehouseId);
				ind.setM_Product_ID(line.getProductId());
				ind.setEstatus(X_EXME_ActPacienteInd.ESTATUS_PendingService);
				ind.setDocStatus(DocAction.STATUS_WaitingConfirmation);
				ind.setIsTodayService(true);
				ind.setNameServ(line.getProdName());
				ind.setLine(++i * 10);// secuencia
				ind.setTipoArea(tipoArea);
				ind.setEXME_Area_ID(areaId);
				if (ind.save()) {
					if (updateBalance(appt.getCtx(), line, true, trxName)) {
						final MEXMECitaMedicaDet det;
						if (line.getApptTempId() > 0) {
							det = new MEXMECitaMedicaDet(appt.getCtx(), line.getApptTempId(), trxName);
						} else {
							det = MEXMECitaMedicaDet.createTodayService(appt, line, trxName);
						}
						det.setEXME_ActPacienteInd_ID(ind.getEXME_ActPacienteInd_ID());
						if (!det.save()) {
							errors.add(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger());
						}
					}
				} else {
					errors.add(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger());
				}
			}
		}
		return errors;
	}

	/**
	 * Actualliza el saldo para una linea.
	 * Excluye aquellos que son ilimitados.
	 * 
	 * @param ctx contexto de la aplicacion
	 * @param line linea con la información del producto, version, paciente
	 * @param remove true: actualiza la cantidad disponible a 0, false: actualiza la cantidad disponible a 1
	 * @param trxName
	 * @return
	 */
	private static boolean updateBalance(final Properties ctx, final BalanceService line, boolean remove, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("     EXME_Paciente_ID=? ");
		sql.append(" AND COALESCE(EXME_PaqBase_Version_ID,0)=?");
		sql.append(" AND M_Product_ID=? ");
		sql.append(" AND RemainingAmt!=999 ");
		if (remove) {
			sql.append(" AND RemainingAmt>? ");
		} else {
			sql.append(" AND RemainingAmt<=? ");
		}
		// final List<MDHBalanceServices> lines
		final MDHBalanceServices balance = new Query(ctx, Table_Name, sql.toString(), trxName)//
			.addAccessLevelSQL(true)//
			.setOnlyActiveRecords(true)//
			.setParameters(line.getPatientId(), line.getPaqBaseVerId(), line.getProductId(), 0)//
			// .list();
			.first();

		// for (final MDHBalanceServices balance : lines) {
		if (balance != null) {
			balance.setRemainingAmt(remove ? 0 : 1);
			if (!balance.save()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Copia una version de paquete a otra organizacion que se requiera
	 * es requerido qeu el producto exista en las demas organizaciones con la
	 * misma llave de búsqueda
	 * Si se envía 0 como organizacion , asume que es a todas las organizaciones de ese cliente
	 * distintas a la organizacion de la version
	 * 
	 * @param version Version de paquete (requerido)
	 * @param orgId Organizacion destino (requerido, positivo)
	 * @return
	 */
	public static ErrorList copyPackVersionToOrgs(final MEXMEPaqBaseVersion version, int orgToId) {
		final ErrorList errors = new ErrorList();
		Trx trx = null;
		try {
			if (version != null) {
				// revisa que tenfa detalle
				List<MEXMEPaqBaseDet> details = version.getLstDetalle();
				if (details.isEmpty()) {
					errors.add(Error.CONFIGURATION_ERROR, "El paquete no cuenta con detalle");
					return errors;
				}
				// revisa las organizaciones a las cuales copiar
				Properties ctx = version.getCtx();
				List<KeyNamePair> list = new ArrayList<>();
				if (orgToId == 0) {
					list = MOrg.getParentOrg(version.getCtx(), MTable.ACCESSLEVEL_ClientOnly, Env.getAD_Client_ID(ctx), null);
				} else if (orgToId > 0 && orgToId != Env.getAD_Org_ID(ctx)) {
					list.add(new KeyNamePair(orgToId, MOrg.getName(ctx, orgToId)));
				}
				list.remove(new KeyNamePair(Env.getAD_Org_ID(ctx), Env.getAD_Org_Name(ctx)));

				final StringBuilder prodVersion = new StringBuilder();

				if (!list.isEmpty()) {
					// se crea la transaccion solo si hay algo que copiar
					trx = Trx.get(Trx.createTrxName("CopyPack"), true);

					for (KeyNamePair pair : list) {
						int orgId = pair.getKey();
						// revisar que exista un producto en la org destino
						int productId = MProduct.getIdFromValue(version.getProduct().getValue(), orgId, true);
						if (productId <= 0) {// FIXME etiquetas
							prodVersion.append("No existe el producto : ").append(version.getProduct().getValue());
							prodVersion.append(" en la organización: ").append(MOrg.getName(ctx, orgId)).append(".<br>");
							continue;
						}
						// revisar si ya existe la version de ese paquete
						int packVersionId =
							DB.getSQLValue(null, "SELECT EXME_PaqBase_Version_ID FROM EXME_PaqBase_Version WHERE M_Product_ID=? AND Name=? ",
								productId, version.getName());

						if (packVersionId > 0) {// FIXME etiquetas
							if (orgToId > 0) { // solo si no eligio Todas
								errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "lista.precios.version.name") + " en la organización: "
									+ MOrg.getName(ctx, orgId) + ".<br>");
							}
							continue;
						}
						// IMPORTANTE crea un conexto local con la organizacion a la que se hara la copia
						Properties localCtx = new Properties();
						localCtx.putAll(ctx);
						Env.setContext(localCtx, "#AD_Org_ID", orgId);
						// Copia de version
						final MEXMEPaqBaseVersion newVersion = new MEXMEPaqBaseVersion(localCtx, 0, trx.getTrxName());
						copyValues(version, newVersion);
						newVersion.setAD_Org_ID(orgId);
						newVersion.setM_Product_ID(productId);
						// impuesto y precios de la org destino
						MTax taxV = newVersion.getProduct().getTax();
						newVersion.setC_Tax_ID(taxV.getC_Tax_ID());
						newVersion.setTaxAmt(taxV.getAmount());

						newVersion.setBaseAmt(BigDecimal.ZERO);
						newVersion.setDiscount(BigDecimal.ZERO);

						final MPriceList priceList = MEXMEConfigPre.getPriceList(localCtx, orgId);
						if (priceList == null) {
							errors.add(Error.CONFIGURATION_ERROR, "No se encontro una lista de precios en la configuracion de precios");
							continue;
						} else {
							newVersion.setM_PriceList_ID(priceList.getM_PriceList_ID());
						}

						if (!newVersion.save()) {
							errors.add(Error.EXCEPTION_ERROR, new MedsysException().getLocalizedMessage());
							continue;
						}

						//
						final StringBuilder prodDet = new StringBuilder();
						for (MEXMEPaqBaseDet detail : details) {
							// revisar que exista un producto en la org destino
							int productDetId = MProduct.getIdFromValue(detail.getProduct().getValue(), orgId, true);
							if (productDetId <= 0) {
								if (prodDet.length() > 0) {
									prodDet.append(",");
								}
								prodDet.append(detail.getProduct().getValue());
								continue;
							}

							// copia del detalle, utiliza el contexto local IMPORTANTE
							final MEXMEPaqBaseDet newDetail = new MEXMEPaqBaseDet(localCtx, 0, trx.getTrxName());
							copyValues(detail, newDetail);
							newDetail.setEXME_PaqBase_Version_ID(newVersion.getEXME_PaqBase_Version_ID());
							newDetail.setAD_Org_ID(orgId);
							newDetail.setM_Product_ID(productDetId);
							// impuesto y precios de la org destino
							MTax taxDet = newVersion.getProduct().getTax();
							newDetail.setC_Tax_ID(taxDet.getC_Tax_ID());
							newDetail.setTaxAmt(taxDet.getAmount());

							MPrecios precios = GetPrice.getPriceVta(localCtx, productDetId, 0,//
								newDetail.getProduct().getC_UOM_ID(), Env.getCurrentDate(), Env.getTipoArea(Env.getCtx()));
							if (precios == null) {
								newDetail.setPriceList(BigDecimal.ZERO);
								newDetail.setCosto(BigDecimal.ZERO);
								newDetail.setPriceActual(BigDecimal.ZERO);
								newDetail.setPriceLimit(BigDecimal.ZERO);
							} else {
								newDetail.setPriceList(precios.getPriceList());
								newDetail.setCosto(precios.getCosto());
								newDetail.setPriceActual(precios.getPriceStd());
								newDetail.setPriceLimit(precios.getPriceLimit());
							}

							newDetail.setC_Currency_ID(priceList.getC_Currency_ID());
							newDetail.setLineNetAmt(newDetail.getCantidad().multiply(newDetail.getPriceActual()));
							newDetail.setTaxAmt(taxDet.calculateTax(newDetail.getLineNetAmt(), priceList.isTaxIncluded(), priceList
								.getPricePrecision().intValue()));
							newDetail.setLineTotalAmt(newDetail.getLineNetAmt().add(newDetail.getTaxAmt()));

							if (!newDetail.save()) {
								errors.add(Error.EXCEPTION_ERROR, new MedsysException().getLocalizedMessage());
								continue;
							}
						}

						// resume en un 1 solo mensage todos los productos que no se encontraron de una sola version
						if (prodDet.length() > 0) {
							prodVersion.append("<br> No existen los siguientes productos en la organización:  ");
							prodVersion.append(MOrg.getName(ctx, orgId)).append(": <br>");
							prodVersion.append(prodDet);
							prodVersion.append("<br>");
						}
					}

					// Resumen de todos los errores
					if (prodVersion.length() > 0) {
						errors.add(Error.VALIDATION_ERROR, prodVersion.toString());
					}

					if (errors.isEmpty()) {
						Trx.commit(trx, true);
					} else {
						Trx.rollback(trx, true);
					}
				}
			}
		} catch (Exception e) {
			Trx.rollback(trx, true);
			errors.add(Error.EXCEPTION_ERROR, StringUtils.isBlank(e.getLocalizedMessage()) ? e.toString() : e.getLocalizedMessage());
		} finally {
			Trx.close(trx, true);
		}
		return errors;
	}
}
