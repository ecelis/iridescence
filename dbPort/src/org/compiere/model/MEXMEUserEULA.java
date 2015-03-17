/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author mrojas
 *
 */
public class MEXMEUserEULA extends X_EXME_User_EULA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static CLogger logger = CLogger.getCLogger(MEXMEUserEULA.class);

	/**
	 * @param ctx
	 * @param EXME_User_EULA_ID
	 * @param trxName
	 */
	public MEXMEUserEULA(Properties ctx, int EXME_User_EULA_ID, String trxName) {
		// ignore
		super(ctx, 0, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEUserEULA(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/**
	 * Check if the user has accepted the current EULA version for the organization.
	 * @param ctx The application context.
	 * @return If the user has accepted or not the EULA.
	 */
	public static boolean hasAccepted(Properties ctx) {
		boolean retVal = false;
		if (!MUser.isPatient(ctx, Env.getAD_User_ID(ctx))) {
			String sql = 
				"SELECT * FROM EXME_User_EULA WHERE AD_Client_ID = ? AND AD_Org_ID = ? AND AD_User_ID = ? AND EXME_EULA_Dt_ID = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			MEXMEEULADt currEULA = MEXMEEULADt.getCurrent(ctx);
			
			// do we have a EULA current version?
			if(currEULA == null) {
				throw new MedsysException(Msg.getMsg(ctx, "NoCurrentEULA"));
			} else {
				// Check if the user has already accepted
				try {
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, Env.getAD_Client_ID(ctx));
					pstmt.setInt(2, Env.getAD_Org_ID(ctx));
					pstmt.setInt(3, Env.getAD_User_ID(ctx));
					pstmt.setInt(4, currEULA.getEXME_EULA_Dt_ID());
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						retVal = true;
					}
					
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "", e);
				} finally {
					DB.close(rs, pstmt);
				}
			}
		} else {
			retVal = Boolean.TRUE;
		}
		return retVal;
	}
	
	/**
	 * Saves the user EULA acceptance. 
	 * @param ctx The application environment.
	 * @param trxName The transaction name.
	 * @return True if the acceptance is saved, false otherwise.
	 */
	public static boolean saveUserAccept(MEXMEEULA eula){
		boolean retVal = false;
		
		MEXMEUserEULA acceptance = 
			new MEXMEUserEULA(eula.getCtx(), 0, eula.get_TrxName());
		acceptance.setClientOrg(
				Env.getAD_Client_ID(eula.getCtx()), 
				Env.getAD_Org_ID(eula.getCtx())
		);
		acceptance.setAD_User_ID(Env.getAD_User_ID(eula.getCtx()));
		acceptance.setDateAccepted(DB.getTimestampForOrg(Env.getCtx()));
		acceptance.setEXME_EULA_Dt_ID(eula.getCurrent().getEXME_EULA_Dt_ID());
		
		retVal = acceptance.save();
		
		return retVal;
	}

}
