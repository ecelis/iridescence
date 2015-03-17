package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEBPGroup extends MBPGroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static CLogger s_log = CLogger.getCLogger (MEXMEBPGroup.class);
	
	public MEXMEBPGroup (Properties ctx, int C_BP_Group_ID, String trxName) {
		super(ctx, C_BP_Group_ID, trxName);
		
	}

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MEXMEBPGroup (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBPGroup
	
	/**
	 * 	Get MBPGroup from Business Partner
	 *	@param ctx context
	 *	@param C_BPartner_ID business partner id
	 *	@return MBPGroup
	 */
	public static MEXMEBPGroup getIDFromValue(Properties ctx, String value, String trxName) {
		MEXMEBPGroup retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM C_BP_Group g WHERE g.IsActive='Y' AND g.Value like ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_BP_Group", "g"));
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEBPGroup(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return retValue;
	} // getOfBPartner
	
	public static MEXMEBPGroup getDefaultBPGroup(Properties ctx, int AD_Client_ID) {
		StringBuilder st = new StringBuilder("select * from C_BP_Group where AD_Client_ID = ? and isDefault = 'Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEBPGroup bpg = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bpg = new MEXMEBPGroup(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return bpg;
	}
	
}
