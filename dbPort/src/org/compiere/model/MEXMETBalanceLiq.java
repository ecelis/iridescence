package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMETBalanceLiq extends X_EXME_T_BalanceLiq{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMETBalanceLiq.class);
	
	public MEXMETBalanceLiq(Properties ctx, int EXME_T_BalanceLiq_ID,
			String trxName) {
		super(ctx, EXME_T_BalanceLiq_ID, trxName);
	}
	
	public MEXMETBalanceLiq(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/**
	 * Regresa el MEXMETBalanceLiq dependiendo de la sesi�n del usuario
	 * 
	 * @param ctx Properties 
	 * @param trxName String
	 * @return ArrayList<MEXMETBalanceLiq> 
	 * @throws 
	 */
	public static ArrayList<MEXMETBalanceLiq> getTBalance(Properties ctx, int adSesionID, String trxName) {

		ArrayList<MEXMETBalanceLiq> obj = new ArrayList<MEXMETBalanceLiq>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    
		sql.append(" SELECT *" );
		sql.append(" FROM EXME_T_BalanceLiq ");
		sql.append(" WHERE ISACTIVE = 'Y' AND AD_SESSION_ID = ? ");
  
		sql = new StringBuilder (MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_BalanceLiq"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, adSesionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETBalanceLiq balance= new MEXMETBalanceLiq(ctx, rs, null);
				obj.add(balance);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return obj;
	}

	

}
