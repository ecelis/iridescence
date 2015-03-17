package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEObservacion extends X_EXME_Observacion {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEObservacion.class);

	public MEXMEObservacion(Properties ctx, int EXME_Observacion_ID,
			String trxName) {
		super(ctx, EXME_Observacion_ID, trxName);
	}

	public MEXMEObservacion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMEObservacion get(Properties ctx, int AD_Table_ID,
			int Record_ID) {
		MEXMEObservacion retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM EXME_Observacion WHERE AD_Table_ID=? AND Record_ID=? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_Observacion"));
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Table_ID);
			pstmt.setInt(2, Record_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEObservacion(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}
		return retValue;
	}

}
