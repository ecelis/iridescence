/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author LLama
 *
 */
public class MEXMECornea extends X_EXME_Cornea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger		s_log = CLogger.getCLogger (MEXMECornea.class);

	/**
	 * @param ctx
	 * @param EXME_Cornea_ID
	 * @param trxName
	 */
	public MEXMECornea(Properties ctx, int EXME_Cornea_ID, String trxName) {
		super(ctx, EXME_Cornea_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECornea(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Revisa si ya existe un determinado Numero de cornea
	 * @param ctx
	 * @param numCornea
	 * @param trxName
	 * @return
	 */
	public static boolean existeCornea(Properties ctx, String numCornea, int corneaID, String trxName) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean retValue = false;

		try {
			sql.append("SELECT NumCornea FROM EXME_Cornea WHERE numCornea = ? AND isActive = 'Y' ")
			.append(" AND exme_cornea_id <> ? ")	
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECornea.Table_Name));
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, numCornea);
			pstmt.setInt(2,corneaID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = true;
			}			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;
	}

}
