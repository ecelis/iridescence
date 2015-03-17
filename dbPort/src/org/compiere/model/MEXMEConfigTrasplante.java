/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** 
 * @author LLama
 * @deprecated Sera removida, no se utiliza en ecs
 */
public class MEXMEConfigTrasplante extends X_EXME_ConfigTrasplante {
	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
//	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigTrasplante.class);

	/**
	 * @param ctx
	 * @param EXME_ConfigTrasplante_ID
	 * @param trxName
	 */
	public MEXMEConfigTrasplante(Properties ctx, int EXME_ConfigTrasplante_ID, String trxName) {
		super(ctx, EXME_ConfigTrasplante_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigTrasplante(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 *
	public static MEXMEConfigTrasplante get(Properties ctx, String trxName) {
		MEXMEConfigTrasplante valueRetun = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM EXME_ConfigTrasplante ");
		sql.append(" WHERE EXME_ConfigTrasplante.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_ConfigTrasplante.AD_Org_ID DESC");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				valueRetun = new MEXMEConfigTrasplante(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return valueRetun;
	}*/
}