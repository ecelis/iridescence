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
 * @author odelarosa
 * 
 */
public class MEXMEPreferenceCard extends X_EXME_PreferenceCard {
	private static CLogger s_log = CLogger.getCLogger(MEXMEPreferenceCard.class);
	private static final long serialVersionUID = -7213520981797485820L;

	public static List<MEXMEPreferenceCard> get(Properties ctx, int medId, String name, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEPreferenceCard> lst = new ArrayList<MEXMEPreferenceCard>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_preferencecard ");
			sql.append("WHERE ");
			sql.append("  upper(name) LIKE ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			if (medId > 0) {
				sql.append(" AND exme_medico_id = ? ");
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, StringUtils.upperCase(StringUtils.defaultIfEmpty(name, "%")));
			if (medId > 0) {
				pstmt.setInt(2, medId);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEPreferenceCard(ctx, rs, null));
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
	 * @param EXME_PreferenceCard_ID
	 * @param trxName
	 */
	public MEXMEPreferenceCard(Properties ctx, int EXME_PreferenceCard_ID, String trxName) {
		super(ctx, EXME_PreferenceCard_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPreferenceCard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
