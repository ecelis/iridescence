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
 * @author twry
 *
 */
public class MEXMEEncoderOrg extends X_EXME_EncoderOrg {
	
	private static CLogger      slog = CLogger.getCLogger (MEXMEEncoderOrg.class);
	
	/**
	 * @param ctx
	 * @param EXME_EncoderOrg_ID
	 * @param trxName
	 */
	public MEXMEEncoderOrg(final Properties ctx, final int encoderOrgID,
			String trxName) {
		super(ctx, encoderOrgID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEncoderOrg(final Properties ctx, final ResultSet rSet, final String trxName) {
		super(ctx, rSet, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEEncoderOrg get(final Properties ctx, final String trxName) {
		MEXMEEncoderOrg retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		sql.append(" SELECT * FROM EXME_EncoderOrg WHERE IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));
		sql.append(" ORDER BY AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMEEncoderOrg(ctx, rs, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get - closing", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;
	}

}
