package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** @deprecated Sera removida, no se utiliza en ecs*/
public class MEXMEConfigINER extends X_EXME_ConfigINER {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
//	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigINER.class);

	public MEXMEConfigINER(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	public MEXMEConfigINER(Properties ctx, int EXME_ConfigINER_ID, String trxName) {
//		super(ctx, EXME_ConfigINER_ID, trxName);
//	}
	
	/*public static MEXMEConfigINER get(Properties ctx, String trxName) {

		MEXMEConfigINER valueRetun = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_ConfigINER ")
			.append(" WHERE EXME_ConfigINER.IsActive = 'Y' ");
			
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ConfigINER"));
		
		sql.append(" ORDER BY EXME_ConfigINER.AD_Org_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			s_log.log(Level.FINEST, "SQL > " + sql);

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				valueRetun = new MEXMEConfigINER(ctx, rs, trxName);
			}

		} catch (Exception e) {
			
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return valueRetun;
	}*/
}