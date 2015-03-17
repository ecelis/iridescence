/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author lama
 * 
 */
public class MBPartnerCte extends X_C_BPartner_Cte {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MBPartnerCte.class);

	/**
	 * @param ctx
	 * @param C_BPartner_Cte_ID
	 * @param trxName
	 */
	public MBPartnerCte(Properties ctx, int C_BPartner_Cte_ID, String trxName) {
		super(ctx, C_BPartner_Cte_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPartnerCte(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param C_BPartner_ID
	 * @param get_TrxName
	 * @return
	 */
	public static MBPartnerCte get(Properties ctx, int C_BPartner_ID, String trxName) {
		MBPartnerCte retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT * FROM C_BPartner_Cte ");
			sql.append(" WHERE C_BPartner_ID = ? ");
			sql.append(" AND IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_BPartner_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MBPartnerCte(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MBPPartnerCte.get - sql = " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

}
