package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.WebEnv;


public class MEXMEMovementLineB extends X_EXME_MovementLineB {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEMovementLineB.class);

	public MEXMEMovementLineB (Properties ctx, int EXME_MovementLineB_ID, String trxName)
	{
		super (ctx, EXME_MovementLineB_ID, trxName);
	}

	/** Load Constructor 
	@param ctx context
	@param rs result set 
	@param trxName transaction
	 */
	public MEXMEMovementLineB (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}

	/**
	 * Obtenemos el registro a partir de la linea del pedido
	 * @param ctx
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static MEXMEMovementLineB getObjFromMov(
			Properties ctx, int movementLine, String trxName)
			throws Exception {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//value = value.replaceAll("\\*", "%");

		sql.append(" SELECT EXME_MovementLineB.* ")
		.append(" FROM EXME_MovementLineB ")
		.append(" WHERE EXME_MovementLineB.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_MovementLineB"))
		.append(" AND EXME_MovementLineB.M_MovementLine_ID = ? ");

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE,"SQL : " + sql.toString() + "  value: " + movementLine);
		}

		MEXMEMovementLineB movimiento = null;
		try {
		    pstmt = DB.prepareStatement(sql.toString(), null);
		    pstmt.setInt(1, movementLine);

		    rs = pstmt.executeQuery();

		    //La primera coincidencia
			if (rs.next()) {
				movimiento = new MEXMEMovementLineB(ctx, rs, null);
			}
			
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    		throw new Exception(e.getMessage());
    	} finally {
    		DB.close(rs, pstmt);
    			pstmt = null;
    			rs = null;
    	}
    	
		return movimiento;
	}
}
