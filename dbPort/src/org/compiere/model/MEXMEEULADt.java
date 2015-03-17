/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * End User License Agreement Detail business model.
 * @author mrojas
 */
public class MEXMEEULADt extends X_EXME_EULA_Dt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static CLogger logger = CLogger.getCLogger(MEXMEEULA.class);
	
	/**
	 * @param ctx
	 * @param EXME_EULA_Dt_ID
	 * @param trxName
	 */
	public MEXMEEULADt(Properties ctx, int EXME_EULA_Dt_ID, String trxName) {
		super(ctx, EXME_EULA_Dt_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEULADt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/**
	 * Gets the current EULA version for the specified date. 
	 * @param ctx The application context
	 * @param EXME_EULA_ID The EULA to verify
	 * @param date The valid from date for the EULA
	 * @param trxName The transaction name
	 * @return The current EULA for the specified date.
	 */
	public static MEXMEEULADt getCurrentForDate(Properties ctx, int EXME_EULA_ID, 
			Timestamp date, String trxName) {
		MEXMEEULADt retVal = null;
		
		String sql = 
			"SELECT * FROM EXME_EULA_Dt WHERE EXME_EULA_ID = ? AND ValidFrom <= ? ORDER BY ValidFrom DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_EULA_ID);
			pstmt.setTimestamp(2, date);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retVal = new MEXMEEULADt(ctx, rs, trxName);
			}
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return retVal;
	}
	
	/**
	 * Gets the current EULA detail according to the environment data:
	 * <ul>
	 * 	<li>Client</li>
	 * 	<li>Organization</li>
	 * 	<li>User</li>
	 *	<li>Current date</li>
	 * </ul>
	 * @param ctx The application environment.
	 * @return The current EULA detail.
	 */
	public static MEXMEEULADt getCurrent(Properties ctx) {
		MEXMEEULADt retVal = null;
		
		// get the current organization EULA
		MOrgInfo orgInfo = MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx));
		
		// get the current EULA version
		Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		retVal = 
			MEXMEEULADt.getCurrentForDate(ctx, orgInfo.getEXME_EULA_ID(), date, null);
		
		
		return retVal;
	}
	
}
