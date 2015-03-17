package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Detalle de formato impreso
 * 
 * @author odelarosa
 * 
 */
public class MEXMEPrintedFormatDet extends X_EXME_PrintedFormatDet {

	private static CLogger s_log = CLogger.getCLogger(MEXMEPrintedFormatDet.class);
	private static final long serialVersionUID = -5475423953946137682L;

	/**
	 * Busca los cuestionarios relacionados a una forma especial
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @param name
	 *            Nombre del cuestionario
	 * @param trxName
	 *            Trx
	 * @return Listado de eventos
	 */
	public static List<MEXMECuestionario.QEvent> getQuestionnaires(Properties ctx, int pacId, String name, String trxName) {
		List<MEXMECuestionario.QEvent> list = new ArrayList<MEXMECuestionario.QEvent>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  (SELECT DISTINCT ");
		sql.append("      ON (cu.exme_cuestionario_id, ");
		sql.append("      pf.exme_printedformatdet_id) cu.exme_cuestionario_id, ");
		sql.append("      pf.exme_printedformatdet_id, ");
		sql.append("      cu.name ");
		sql.append("      AS cuest, ");
		sql.append("      rc.created, ");
		sql.append("      u.ad_user_id, ");
		sql.append("      u.name ");
		sql.append("      AS userName ");
		sql.append("    FROM ");
		sql.append("      EXME_PrintedFormatDet pf ");
		sql.append("      INNER JOIN exme_citamedica cm ");
		sql.append("      ON pf.exme_citamedica_id = cm.exme_citamedica_id ");
		sql.append("      INNER JOIN exme_respuestacuestionario rc ");
		sql.append("      ON (rc.ad_table_id = ? AND ");
		sql.append("      rc.record_id = pf.exme_printedformatdet_id) ");
		sql.append("      INNER JOIN exme_cuestionario cu ");
		sql.append("      ON rc.exme_cuestionario_id = cu.exme_cuestionario_id ");
		sql.append("      INNER JOIN ad_user u ");
		sql.append("      ON rc.createdby = u.ad_user_id ");
		sql.append("    WHERE ");
		sql.append("      cm.exme_paciente_id = ? AND ");
		sql.append("      (upper(cu.name) LIKE ? OR ");
		sql.append("      upper(cu.value) LIKE ?) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "pf"));
		sql.append("  ) test ");
		sql.append(" ORDER BY ");
		sql.append("  created desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Table_ID);
			pstmt.setInt(2, pacId);
			pstmt.setString(3, "%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");
			pstmt.setString(4, "%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECuestionario.QEvent event = new MEXMECuestionario.QEvent();
				event.setTableId(Table_ID);
				event.setDate(rs.getTimestamp("created"));
				event.setId(rs.getInt("exme_cuestionario_id"));
				event.setName(rs.getString("cuest"));
				event.setRecordId(rs.getInt("exme_printedformatdet_id"));
				event.setUserId(rs.getInt("ad_user_id"));
				event.setUserName(rs.getString("userName"));
				list.add(event);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Busca dentro de las preguntas, relacionadas a una forma especial
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @param searchString
	 *            Texto a buscar
	 * @param trxName
	 *            Trx
	 * @return Listado de eventos
	 */
	public static List<MEXMECuestionario.QEvent> searchIntoQuestionnaires(Properties ctx, int pacId, String searchString, String trxName) {
		List<MEXMECuestionario.QEvent> lst = new ArrayList<MEXMECuestionario.QEvent>();

		searchString = StringUtils.join(StringUtils.split(searchString, Constantes.SPACE), " | ");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  u.description ");
		sql.append("  AS user, ");
		sql.append("  rc.created, ");
		sql.append("  pf.EXME_PrintedFormatDet_ID, ");
		sql.append("  cu.EXME_Cuestionario_id, ");
		sql.append("  ts_headline(rc.textbinary, ");
		sql.append("  to_tsquery(?)) ");
		sql.append("  AS hl, ");
		sql.append("  cu.name, ");
		sql.append("  u.ad_user_id ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario rc ");
		sql.append("  INNER JOIN EXME_PrintedFormatDet pf ");
		sql.append("  ON (rc.record_id = pf.EXME_PrintedFormatDet_id) ");
		sql.append("  INNER JOIN exme_citamedica cm ");
		sql.append("  ON (pf.exme_citamedica_id = cm.exme_citamedica_id) ");
		sql.append("  INNER JOIN EXME_cuestionario cu ");
		sql.append("  ON rc.exme_cuestionario_id = cu.exme_cuestionario_id ");
		sql.append("  INNER JOIN AD_User u ");
		sql.append("  ON rc.createdby = u.ad_user_id ");
		sql.append("WHERE ");
		sql.append("  rc.ad_table_id = ? AND cm.exme_paciente_id = ? AND ");
		sql.append("  to_tsvector(rc.textbinary) @@ to_tsquery(?) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "pf"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, searchString);
			pstmt.setInt(2, Table_ID);
			pstmt.setInt(3, pacId);
			pstmt.setString(4, searchString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECuestionario.QEvent event = new MEXMECuestionario.QEvent();
				event.setDate(rs.getTimestamp("created"));
				event.setHeadLine(rs.getString("hl"));
				event.setId(rs.getInt("EXME_Cuestionario_id"));
				event.setName(rs.getString("name"));
				event.setRecordId(rs.getInt("EXME_PrintedFormatDet_ID"));
				event.setTableId(Table_ID);
				event.setUserName(rs.getString("user"));
				event.setUserId(rs.getInt("ad_user_id"));

				lst.add(event);
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
	 * @param EXME_PrintedFormatDet_ID
	 * @param trxName
	 */
	public MEXMEPrintedFormatDet(Properties ctx, int EXME_PrintedFormatDet_ID, String trxName) {
		super(ctx, EXME_PrintedFormatDet_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPrintedFormatDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
