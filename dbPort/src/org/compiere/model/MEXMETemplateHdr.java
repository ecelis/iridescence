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
 * @author odelarosa
 * 
 */
public class MEXMETemplateHdr extends X_EXME_Template_Hdr {

	private static final long serialVersionUID = -1929438470661677067L;
	private static CLogger s_log = CLogger.getCLogger(MEXMETemplateHdr.class);

	/**
	 * @param ctx
	 * @param EXME_Template_Hdr_ID
	 * @param trxName
	 */
	public MEXMETemplateHdr(Properties ctx, int EXME_Template_Hdr_ID, String trxName) {
		super(ctx, EXME_Template_Hdr_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETemplateHdr(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Listado de plantillas con el texto indicado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param text
	 *            Texto a buscar
	 * @param trxName
	 *            Trx Name
	 * @return Listado
	 */
	public static List<MEXMETemplateHdr> get(Properties ctx, String text, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_template_hdr ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' ");

		sql.append("and (");

		sql.append("upper(value) like ? ");
		sql.append(" OR ");
		sql.append("upper(name) like ? ");

		sql.append(")");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append(" order by value");

		List<MEXMETemplateHdr> list = new ArrayList<MEXMETemplateHdr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "%" + StringUtils.defaultString(text).toUpperCase() + "%");
			pstmt.setString(2, "%" + StringUtils.defaultString(text).toUpperCase() + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new MEXMETemplateHdr(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static List<Integer> getFormIds(Properties ctx, int id, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (resp.exme_cuestionario_id), ");
		sql.append("  cuest.name ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario resp ");
		sql.append("  INNER JOIN exme_cuestionario cuest ");
		sql.append("  ON resp.exme_cuestionario_id = cuest.exme_cuestionario_id ");
		sql.append("WHERE ");
		sql.append("  resp.ad_table_id = ? AND ");
		sql.append("  resp.record_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXMERespuestaCuestionario.Table_Name, "resp"));
		sql.append("ORDER BY ");
		sql.append("  cuest.name ");

		List<Integer> list = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Table_ID);
			pstmt.setInt(2, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

}
