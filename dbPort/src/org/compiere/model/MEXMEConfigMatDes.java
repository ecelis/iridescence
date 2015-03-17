package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEConfigMatDes extends X_EXME_ConfigMatDes{
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MEXMEConfigMatDes.class);
	
	public MEXMEConfigMatDes(Properties ctx, int EXME_ConfigMatDes_ID, String trxName) {
		super(ctx, EXME_ConfigMatDes_ID, trxName);
	}

	public MEXMEConfigMatDes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEConfigMatDes get(Properties ctx, String trxName){
		MEXMEConfigMatDes retValue = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT * FROM EXME_ConfigMatDes WHERE IsActive = 'Y' ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_ConfigMatDes"));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue = new MEXMEConfigMatDes(ctx, rs, trxName);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return retValue;
	}

}
