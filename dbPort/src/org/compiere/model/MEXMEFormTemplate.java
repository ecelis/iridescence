package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Plantillas de Cuestionarios <br/>
 * Almacena un set de respuestas
 * 
 * @author odelarosa
 * 
 */
public class MEXMEFormTemplate extends X_EXME_FormTemplate {
	private static CLogger s_log = CLogger.getCLogger(MEXMEFormTemplate.class);
	private static final long serialVersionUID = 1466971252510079516L;

	/**
	 * 
	 * @param ctx
	 * @param questId
	 * @param name
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEFormTemplate> get(Properties ctx, int questId, String name, String trxName) {
		List<MEXMEFormTemplate> lst = new ArrayList<MEXMEFormTemplate>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_formtemplate ");
		sql.append("WHERE ");
		sql.append("  (upper(name) LIKE ? OR ");
		sql.append("  upper(value) LIKE ?) ");
		sql.append(" AND exme_cuestionario_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" order by name");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			name = "%" + StringUtils.defaultIfEmpty(name, "").toUpperCase() + "%";
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			pstmt.setInt(3, questId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMEFormTemplate(ctx, rs, trxName));
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
	 * @param EXME_FormTemplate_ID
	 * @param trxName
	 */
	public MEXMEFormTemplate(Properties ctx, int EXME_FormTemplate_ID, String trxName) {
		super(ctx, EXME_FormTemplate_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFormTemplate(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}
