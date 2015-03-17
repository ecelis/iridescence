package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MCapturaPagos {
	
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MCapturaPagos.class);

	/**
	 * Anticipos
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static BigDecimal getAnticipos(Properties ctx, int EXME_CtaPac_ID, String trxName ) 
	throws SQLException {
		return MCapturaPagos.getCashLine(ctx, 
				" AND C_Payment_ID IS NOT NULL AND EXME_CtaPac_ID IS NOT NULL AND EXME_CtaPac_ID = " + EXME_CtaPac_ID,
				trxName);
	}
	
	/**
	 * Pagos Directos
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static BigDecimal getPagosDirectos(Properties ctx, int EXME_CtaPac_ID, String trxName ) 
	throws SQLException {
		
		String where = " AND C_Payment_ID IS NULL "
			+ " AND EXME_CtaPac_ID IS NULL " 
			+ " AND C_Invoice_ID IS NOT NULL "
			+ " AND C_Invoice_ID IN ( "
			+ "         SELECT  C_Invoice_ID FROM  EXME_CtaPacExt WHERE EXME_CtaPac_ID = "+ EXME_CtaPac_ID
			+ "         AND C_Invoice_ID IS NOT NULL ) ";



		return MCapturaPagos.getCashLine(ctx, where, trxName);
	}
	
	/**
	 * Obtenemos el monto de los pagos de los anticipos.
	 * @return
	 * @throws SQLException 
	 */
	public static BigDecimal getCashLine(Properties ctx, String whereClause, 
			String trxName) throws SQLException {

		BigDecimal total = Env.ZERO;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NVL(SUM(NVL(Amount,0)),0) AS MONTO FROM C_CashLine WHERE  IsActive = 'Y' ");
		
		if (whereClause != null)

			sql.append(whereClause);
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"C_CashLine"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getBigDecimal("MONTO");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return total;
		
	}

}
