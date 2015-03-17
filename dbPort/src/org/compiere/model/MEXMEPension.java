package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPension extends X_EXME_Pension{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2150564614622305512L;
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEPension.class);
	
	/**
	 * @param ctx
	 * @param C_CashLine_ID
	 * @param trxName
	 */
	public MEXMEPension(Properties ctx, int EXME_Pension_ID, String trxName) {
		super(ctx, EXME_Pension_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPension(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Pensiones
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEPension getUltimoReg(Properties ctx, int EXME_Paciente_ID, 
			String whereClause, String trxName){
		
		MEXMEPension pension = null;

		String sql = " SELECT * FROM EXME_Pension WHERE EXME_Paciente_ID = ? ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Pension");

		if (whereClause != null)
			sql += whereClause;

		sql += " ORDER BY CREATED DESC ";

		s_log.log(Level.FINEST, "SQL> " + sql + " ID > " + EXME_Paciente_ID);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1,EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pension = new MEXMEPension(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getUltimoReg", e);
		} finally {
			DB.close(rs, pstmt);
			
		}
		return pension;
	} 
	
	/**
	 * Pensiones
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEPension get(Properties ctx, int EXME_Paciente_ID, 
			int EXME_CtaPac_ID, String trxName){
		
		MEXMEPension pension = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM EXME_Pension ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND EXME_Paciente_ID = ? ")
		.append(" AND EXME_CtaPac_ID = ? ");
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Pension"));
		
		s_log.log(Level.FINEST,"SQL> " + sql.toString() + " ID > " + EXME_Paciente_ID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,EXME_Paciente_ID);
			pstmt.setInt(2,EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pension = new MEXMEPension(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}
		return pension;
	} 
	
	
	/**
	 * Pensiones
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEPension get(Properties ctx, int EXME_Paciente_ID,String trxName){
		
		MEXMEPension pension = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM EXME_Pension ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND EXME_Paciente_ID = ? ")
		.append(" AND EXME_CtaPac_ID IS NULL  ");
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Pension"));
		
		s_log.log(Level.FINEST,"SQL> " + sql.toString() + " ID > " + EXME_Paciente_ID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pension = new MEXMEPension(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}
		return pension;
	} 
}
