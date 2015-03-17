package org.compiere.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * 
 * @author rsolorzano
 *
 */
public class MEXMEBillingRuleDet extends X_EXME_BillingRuleDet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7943491099144799632L;
	private static CLogger log = CLogger.getCLogger(MEXMEBillingRuleDet.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_BillingRuleDet_ID
	 * @param trxName
	 */
	public MEXMEBillingRuleDet(Properties ctx, int EXME_BillingRuleDet_ID, String trxName) {
		super(ctx, EXME_BillingRuleDet_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEBillingRuleDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Get the rules detail for a billing queue rule header
	 * @param ctx
	 * @param ruleID
	 * @return
	 */
	public static List<MEXMEBillingRuleDet> getAllByID(Properties ctx, int ruleID) {

		final List<MEXMEBillingRuleDet> retValue = new ArrayList<MEXMEBillingRuleDet>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_BILLINGRULEDET WHERE ISACTIVE = 'Y' ");
		sql.append("AND EXME_BILLINGRULE_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ruleID);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				retValue.add(new MEXMEBillingRuleDet(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getList", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	

}
