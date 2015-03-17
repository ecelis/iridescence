package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEFechaFact extends X_EXME_FechaFact {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEFechaFact.class);

	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param EXME_FechaFact_ID id
	 * @param trxName transaction
	 */
	public MEXMEFechaFact(Properties ctx, int EXME_FechaFact_ID, String trxName) {
		super(ctx, EXME_FechaFact_ID, trxName);

	}

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set
	 * @param trxName  transaction
	 */
	public MEXMEFechaFact(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * Obtener el registro mediante el id de la factura
	 * @param C_Invoice_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEFechaFact getFromInvoice (Properties ctx, int C_Invoice_ID, String trxName){
		
		MEXMEFechaFact retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(C_Invoice_ID<=0) {
			return retValue;
		}
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
		sql.append("SELECT * FROM EXME_FechaFact where C_Invoice_ID = ? AND isActive='Y'");
		
		pstmt = DB.prepareStatement(sql.toString(), trxName);
		pstmt.setInt(1, C_Invoice_ID);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			retValue = new MEXMEFechaFact(ctx, rs, trxName);
		}
		
		} catch (Exception e){
			s_log.log(Level.SEVERE, "MEXMEFechaFact.getFromInvoice - SQL : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return retValue;
	}

}
