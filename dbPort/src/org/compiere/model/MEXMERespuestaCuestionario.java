package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.officevisit.AReportParameter;
import com.ecaresoft.util.officevisit.OfficeVisitAnswer;

/**
 * @author odelarosa
 * 
 */
public class MEXMERespuestaCuestionario extends X_EXME_RespuestaCuestionario {
	private static CLogger s_log = CLogger.getCLogger(MEXMERespuestaCuestionario.class);
	private static final long serialVersionUID = 1127340904698891404L;

	public static void delete(Properties ctx, int tableId, int recordId, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario ");
		sql.append("WHERE ");
		sql.append("  ad_table_id = ? AND ");
		sql.append("  record_id = ?  ");

		if (cuestId != -1) {
			sql.append(" AND exme_cuestionario_id = ? ");
		}

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, recordId);
			if (cuestId != -1) {
				pstmt.setInt(3, cuestId);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}

	public static void deleteOS(Properties ctx, int tableId, int recordId, int osID, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario ");
		sql.append(" WHERE ");
		sql.append("  ad_table_id = ? AND ");
		sql.append("  record_id = ?  ");

		if (osID > 0) {
			sql.append(" AND EXME_OrderSet_ID = ? ");
		}else if(osID == 0){
			sql.append(" AND (EXME_OrderSet_ID is null or EXME_OrderSet_ID = 0) ");
		}else if(osID < 0){
			sql.append(" AND EXME_OrderSet_ID is not null and EXME_OrderSet_ID > 0 ");
		}

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, recordId);
			if (osID > 0) {
				pstmt.setInt(3, osID);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}
	
	public static void cleanOS(Properties ctx, int tableId, int recordId, int osID, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("Update ");		
		sql.append("  exme_respuestacuestionario ");
		sql.append("  set TEXTBINARY = null, EXME_PREGUNTA_LISTA_ID = null ");
		sql.append(" WHERE ");
		sql.append("  ad_table_id = ? AND ");
		sql.append("  record_id = ?  ");

		if (osID > 0) {
			sql.append(" AND EXME_OrderSet_ID = ? ");
		}else if(osID == 0){
			sql.append(" AND (EXME_OrderSet_ID is null or EXME_OrderSet_ID = 0) ");
		}else if(osID < 0){
			sql.append(" AND EXME_OrderSet_ID is not null and EXME_OrderSet_ID > 0 ");
		}

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, recordId);
			if (osID > 0) {
				pstmt.setInt(3, osID);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}
	
	/**
	 * @deprecated
	 * @param ctx
	 * @param tableId
	 * @param recordId
	 * @param cuestId
	 * @param multiple
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERespuestaCuestionario> get(Properties ctx, int tableId, int recordId, int cuestId, boolean multiple, String trxName) {
		return get(ctx, tableId, recordId, cuestId, multiple, trxName, true);
	}
	
	public static List<MEXMERespuestaCuestionario> getRespuestas(Properties ctx, int tableId, int recordId, int cuestId, int osID, boolean multiple, String trxName) {
		return getRespuestas(ctx, tableId, recordId, cuestId, osID, multiple, trxName, true);
	}
	
	/**
	 * Obtener respuestas de cuestionarios
	 * 
	 * @param ctx
	 *            Contexto de App
	 * @param tableId
	 *            Tabla relacionada
	 * @param recordId
	 *            Registro de la tabla relacionada
	 * @param cuestId
	 *            Id del cuestionario
	 * @param osID
	 *            Id del order set
	 * @param multiple
	 *            Traer o no respuestas de preguntas de opcion multiple
	 * @param trxName
	 *            Trx Name
	 * @param isByOrg
	 *            Traer respuestas con nivel de acceso o solo system
	 * @return Listado de respuestas del cuestionario
	 */
	public static List<MEXMERespuestaCuestionario> getRespuestas(Properties ctx, int tableId, int recordId, int cuestId, int osID, boolean multiple, String trxName, boolean isByOrg) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMERespuestaCuestionario> lst = new ArrayList<MEXMERespuestaCuestionario>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_respuestacuestionario rc ");
			sql.append("WHERE ");
			sql.append("  rc.ad_table_id = ? AND ");
			sql.append("  rc.record_id = ? AND ");
			sql.append("  rc.exme_cuestionario_id = ? AND ");
			sql.append("  rc.multiple = ? ");

			if (osID > 0)
				sql.append("  AND rc.EXME_OrderSet_ID = ").append(osID).append(" ");
			else if (osID < 0)
				sql.append("  AND (rc.EXME_OrderSet_ID is null or rc.EXME_OrderSet_ID = 0) ").append(osID).append(" ");

			if (isByOrg) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "rc"));
			} else {
				sql.append(" AND rc.ad_client_id = 0 ");
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, recordId);
			pstmt.setInt(3, cuestId);
			pstmt.setString(4, DB.TO_STRING(multiple));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMERespuestaCuestionario(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	public static List<MEXMERespuestaCuestionario> getRespuesta(Properties ctx, int adTableID, int recordID, int exmeCuestionarioID, boolean isMultiple, String trxName, boolean isByOrg, int exmePreguntaID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMERespuestaCuestionario> lst = new ArrayList<MEXMERespuestaCuestionario>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_respuestacuestionario rc ");
			sql.append("WHERE ");
			sql.append("  rc.ad_table_id = ? AND ");
			sql.append("  rc.record_id = ? AND ");
			sql.append("  rc.exme_cuestionario_id = ? AND ");
			sql.append("  rc.multiple = ? AND");
			sql.append("  rc.exme_pregunta_ID = ? ");

			if (isByOrg) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "rc"));
			} else {
				sql.append(" AND rc.ad_client_id = 0 ");
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, adTableID);
			pstmt.setInt(2, recordID);
			pstmt.setInt(3, exmeCuestionarioID);
			pstmt.setString(4, DB.TO_STRING(isMultiple));
			pstmt.setInt(5, exmePreguntaID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMERespuestaCuestionario(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}
	/**
	 * @deprecated
	 * @param ctx
	 * @param tableId
	 * @param recordId
	 * @param cuestId
	 * @param multiple
	 * @param trxName
	 * @param isByOrg
	 * @return
	 */
	public static List<MEXMERespuestaCuestionario> get(Properties ctx, int tableId, int recordId, int cuestId, boolean multiple, String trxName, boolean isByOrg) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMERespuestaCuestionario> lst = new ArrayList<MEXMERespuestaCuestionario>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_respuestacuestionario rc ");
			sql.append("  INNER JOIN exme_cuestionariodt dt ");
			sql.append("  ON dt.exme_cuestionario_id = rc.exme_cuestionario_id AND ");
			sql.append("  dt.exme_pregunta_id = rc.exme_pregunta_id ");
			sql.append("WHERE ");
			sql.append("  rc.ad_table_id = ? AND ");
			sql.append("  rc.record_id = ? AND ");
			sql.append("  rc.exme_cuestionario_id = ? AND ");
			sql.append("  rc.multiple = ? ");

			if (isByOrg) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "rc"));
			} else {
				sql.append(" AND rc.ad_client_id = 0 ");
			}

			sql.append(" ORDER BY ");
			sql.append("  dt.secuencia ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, recordId);
			pstmt.setInt(3, cuestId);
			pstmt.setString(4, DB.TO_STRING(multiple));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMERespuestaCuestionario(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * @param ctx
	 * @param EXME_RespuestaCuestionario_ID
	 * @param trxName
	 */
	public MEXMERespuestaCuestionario(Properties ctx, int EXME_RespuestaCuestionario_ID, String trxName) {
		super(ctx, EXME_RespuestaCuestionario_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERespuestaCuestionario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public final static SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * Listado para reporte de cuestionarios Office Vist
	 * 
	 * @param ctx
	 *            Contexto
	 * @param date
	 *            Fecha inicial (Cita)
	 * @param date2
	 *            Fecha final (Cita)
	 * @param reportParameters
	 *            Parámetros de reporte
	 * @param orgId
	 *            Organización id o -1
	 * @param age
	 *            Edad del paciente
	 * @param age2
	 *            Edad del paciente
	 * @param sex
	 *            Sexo del paciente
	 * @param trxName
	 *            Trx
	 * @return Listado que cumple con los parámetros
	 */
	public static List<OfficeVisitAnswer> getOfficeVisitReport(Properties ctx, Timestamp date, Timestamp date2, //
			List<AReportParameter> reportParameters, int orgId, int age, int age2, String sex, String trxName) {

		List<OfficeVisitAnswer> list = new ArrayList<OfficeVisitAnswer>();

		List<Integer> tableIds = new ArrayList<Integer>();
		tableIds.add(X_EXME_CitaMedica.Table_ID);
		tableIds.add(X_EXME_PrintedFormatDet.Table_ID);
		tableIds.add(X_EXME_CtaCuest.Table_ID);

		for (int tableId : tableIds) {
			for (AReportParameter reportParameter : reportParameters) {
				List<Object> params = new ArrayList<Object>();

				StringBuilder sql = new StringBuilder();
				sql.append("SELECT ");
				sql.append("  res.textbinary, pac.nombre_pac, med.nombre_med as med, ");

				if (X_EXME_CtaCuest.Table_ID == tableId) {
					sql.append("  cc.created as cita, '' as terUser, ");
				} else {
					sql.append("  cit.fechaHrCita as cita, ter.name as terUser, ");
				}

				sql.append("   pre.tipodato, pac.sexo, cta.documentno as ctaPacNo, pac.FechaNac, exp.documentno as exp ");
				sql.append("FROM ");
				sql.append("  exme_respuestacuestionario res ");
				sql.append("  INNER JOIN exme_pregunta pre ");
				sql.append("  ON res.exme_pregunta_id = pre.exme_pregunta_id ");

				if (X_EXME_CitaMedica.Table_ID == tableId) {
					sql.append("  INNER JOIN exme_citamedica cit ");
					sql.append("  ON res.record_Id = cit.exme_citamedica_id ");

					sql.append("  LEFT JOIN ad_user ter ");
					sql.append("  ON cit.TerminadoPor = ter.ad_user_id ");

					sql.append("  INNER JOIN EXME_Medico med ");
					sql.append("  ON cit.EXME_Medico_ID = med.EXME_Medico_id ");
				} else if (X_EXME_PrintedFormatDet.Table_ID == tableId) {
					sql.append("  INNER JOIN EXME_PrintedFormatDet pf ");
					sql.append("  ON res.record_Id = pf.EXME_PrintedFormatDet_ID ");
					sql.append("  INNER JOIN exme_citamedica cit ");
					sql.append("  ON pf.exme_citamedica_Id = cit.exme_citamedica_id ");

					sql.append("  LEFT JOIN ad_user ter ");
					sql.append("  ON cit.TerminadoPor = ter.ad_user_id ");

					sql.append("  INNER JOIN EXME_Medico med ");
					sql.append("  ON cit.EXME_Medico_ID = med.EXME_Medico_id ");
				} else if (X_EXME_CtaCuest.Table_ID == tableId) {
					sql.append("  INNER JOIN EXME_CtaCuest cc ");
					sql.append("  ON res.record_Id = cc.EXME_CtaCuest_ID ");
					// Se ve sin sentido, pero sirve
					sql.append("  INNER JOIN exme_ctapac cit ");
					sql.append("  ON cc.exme_ctapac_Id = cit.exme_ctapac_id ");

					sql.append("  INNER JOIN EXME_Medico med ");
					sql.append("  ON cit.EXME_Medico_ID = med.EXME_Medico_id ");
				}

				sql.append("  INNER JOIN exme_paciente pac ");
				sql.append("  ON cit.exme_paciente_id = pac.exme_paciente_id ");
				sql.append("  INNER JOIN exme_hist_exp exp ");
				sql.append("  ON (pac.exme_paciente_id = exp.exme_paciente_id and ");
				sql.append("  exp.ad_org_id = cit.ad_org_id) ");
				sql.append("  LEFT JOIN exme_ctapac cta ");
				sql.append("  ON cit.exme_ctapac_id = cta.exme_ctapac_id ");
				sql.append("WHERE ");
				sql.append("  res.ad_table_id = ?  ");

				if (X_EXME_CtaCuest.Table_ID != tableId) {
					sql.append("  AND cit.fechaHrCita between ? and ?  ");
				} else {
					sql.append("  AND cc.created between ? and ?  ");
				}

				sql.append("  AND res.ad_client_id = ? ");

				params.add(tableId);
				params.add(date);
				params.add(date2);
				params.add(Env.getAD_Client_ID(ctx));

				if (orgId > 0) {
					sql.append("   AND res.ad_org_id = ? ");
					params.add(orgId);
				}

				if (age > -1 && age2 > -1) {
					if (X_EXME_CtaCuest.Table_ID == tableId) {
						sql.append(" AND DATE_PART('year', cc.created) - DATE_PART('year', pac.fechanac) ");
					} else {
						sql.append(" AND DATE_PART('year', cit.fechaHrCita) - DATE_PART('year', pac.fechanac) ");
					}
					sql.append(" between ? and ?");

					params.add(age);
					params.add(age2);
				}

				if (StringUtils.isNotBlank(sex)) {
					sql.append(" AND pac.sexo = ? ");
					params.add(sex);
				}

				sql.append(reportParameter.getSql());
				params.addAll(reportParameter.getParams());

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(sql.toString(), trxName);
					DB.setParameters(pstmt, params);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						OfficeVisitAnswer answer = new OfficeVisitAnswer();
						answer.setMrn(rs.getString("exp"));
						answer.setPacName(SecureEngine.decrypt(rs.getString("nombre_pac")));
						answer.setQuestion(reportParameter.getName());
						answer.setSex(MRefList.getListName(ctx, X_EXME_Paciente.SEXO_AD_Reference_ID, rs.getString("sexo")));
						answer.setCtaPacNo(rs.getString("ctaPacNo"));
						answer.setDob(rs.getTimestamp("FechaNac"));
						answer.setDoctor(rs.getString("med"));
						answer.setLastDoctor(rs.getString("terUser"));

						String str = rs.getString("textbinary");
						String tipoDato = StringUtils.trim(rs.getString("tipoDato"));

						if (X_EXME_Pregunta.TIPODATO_Logial.equals(tipoDato)) {
							str = Utilerias.getAppMsg(ctx, "Y".equals(str) ? "imag.checked" : "msj.reglaCuestionario.negativo");
						} else if (X_EXME_Pregunta.TIPODATO_Date.equals(tipoDato)) {
							str = Constantes.getShortDate(ctx).format(SDF.parse(str));
						} else if (X_EXME_Pregunta.TIPODATO_MultiOptions.equals(tipoDato)) {
							str = MEXMEPreguntaLista.getPrintText(Integer.valueOf(str));
						}

						answer.setAnsware(str);
						answer.setAppointment(rs.getTimestamp("cita"));

						list.add(answer);
					}

				} catch (Exception e) {
					s_log.log(Level.SEVERE, null, e);
				} finally {
					DB.close(rs, pstmt);
				}
			}
		}

		return list;
	}

}
