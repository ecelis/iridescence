package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.QuickSearchTables;

/**
 * @author odelarosa
 * 
 */
public class MEXMESociosFavoritos extends X_EXME_SociosFavoritos {

	private static CLogger s_log = CLogger.getCLogger(MEXMESociosFavoritos.class);
	private static final long serialVersionUID = -5377680870441105301L;

	public static List<MEXMESociosFavoritos> get(Properties ctx, String trxName) {
		List<MEXMESociosFavoritos> lst = new ArrayList<MEXMESociosFavoritos>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_SOCIOSFAVORITOS ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " where ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMESociosFavoritos(ctx, rs, null));
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
	 * @param EXME_SociosFavoritos_ID
	 * @param trxName
	 */
	public MEXMESociosFavoritos(Properties ctx, int EXME_SociosFavoritos_ID, String trxName) {
		super(ctx, EXME_SociosFavoritos_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESociosFavoritos(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		QuickSearchTables.checkTables(MBPartner.class, MBPartner.Table_Name, getC_BPartner_ID(), get_TrxName(), getCtx());
		return super.afterSave(newRecord, success);
	}

	@Override
	protected boolean afterDelete(boolean success) {
		QuickSearchTables.checkTables(MBPartner.class, MBPartner.Table_Name, getC_BPartner_ID(), get_TrxName(), getCtx());
		return super.afterDelete(success);
	}

}
