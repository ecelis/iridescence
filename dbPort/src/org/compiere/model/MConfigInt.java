package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MConfigInt extends X_EXME_ConfigInt {

	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MConfigInt.class);
	
	/**
	 * @param ctx
	 * @param ID
	 * @param trx
	 */
	public MConfigInt(Properties ctx, int EXME_ConfigInt_ID, String trxName) {
		super(ctx, EXME_ConfigInt_ID, trxName);
	}
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trx
	 */
	public MConfigInt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

    public static MConfigInt get(Properties ctx, String trxName) {

		if (ctx == null) {
			return null;
		}

		MConfigInt retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_ConfigInt.* ") 
        	.append("FROM EXME_ConfigInt WHERE EXME_ConfigInt.IsActive='Y' ");
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_ConfigInt.AD_Org_ID DESC ");
         
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MConfigInt(ctx, rs, trxName);
			}			
			if (trxName != null) {
				retValue.set_TrxName(trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}
}
