package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEReporteAccess extends X_EXME_Reporte_Access {
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEReporteAccess.class);

	public MEXMEReporteAccess(Properties ctx, int EXME_Reporte_Access_ID,
			String trxName) {
		super(ctx, EXME_Reporte_Access_ID, trxName);
	}

	public MEXMEReporteAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEReporteAccess> get(Properties ctx, String where,
			String trxName) {
		StringBuilder st = new StringBuilder(
				"SELECT * FROM EXME_Reporte_Access WHERE ");
		if (where != null) {
			st.append(where).append(" AND ");
		}
		st.append("isActive = 'Y' ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
				.toString(), "EXME_Reporte_Access"));

		List<MEXMEReporteAccess> lista = new ArrayList<MEXMEReporteAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null; // Expert
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMEReporteAccess(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null; // Expert
		}
		return lista;
	}
}
