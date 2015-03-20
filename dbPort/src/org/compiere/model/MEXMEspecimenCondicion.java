/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author raul 28/11/2010
 *
 */
public class MEXMEspecimenCondicion extends X_EXME_EspecimenCondicion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEspecimenCondicion.class);

	/**
	 * @param ctx
	 * @param EXME_EspecimenCondicion_ID
	 * @param trxName
	 */
	public MEXMEspecimenCondicion(Properties ctx,
			int EXME_EspecimenCondicion_ID, String trxName) {
		super(ctx, EXME_EspecimenCondicion_ID, trxName);
	}
	
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEspecimenCondicion(Properties ctx, ResultSet rs, String trxName) {
		super( ctx,  rs, trxName);
	}


	public static MEXMEspecimenCondicion getCondicion(Properties ctx,
			String value, String trxName){
		MEXMEspecimenCondicion condicion = null;
		
		StringBuilder st = new StringBuilder("select * from EXME_EspecimenCondicion where value = ? ");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				condicion = new MEXMEspecimenCondicion(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return condicion;
	}

}
