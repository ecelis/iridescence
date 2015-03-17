package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEConfigEncoder extends X_EXME_ConfigEncoder {
	
	private static CLogger      slog = CLogger.getCLogger (MEXMEConfigEncoder.class);
	
	public MEXMEConfigEncoder(final Properties ctx, final int configEncodID,
			final String trxName) {
		super(ctx, configEncodID, trxName);
	}

	public MEXMEConfigEncoder(final Properties ctx, final ResultSet rSet, final String trxName) {
		super(ctx, rSet, trxName);
	}

	public static MEXMEConfigEncoder get(final Properties ctx, final String trxName) {
		MEXMEConfigEncoder retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		sql.append(" SELECT * FROM EXME_ConfigEncoder WHERE IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));
		sql.append(" ORDER BY AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMEConfigEncoder(ctx, rs, trxName);
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
