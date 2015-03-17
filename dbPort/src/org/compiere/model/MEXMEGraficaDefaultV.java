package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEGraficaDefaultV extends X_EXME_GraficaDefaultV {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6424940606802208756L;

	public MEXMEGraficaDefaultV(Properties ctx, int EXME_GraficaDefaultV_ID, String trxName) {
		super(ctx, EXME_GraficaDefaultV_ID, trxName);
	}

	public MEXMEGraficaDefaultV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static CLogger  s_log = CLogger.getCLogger (MEXMEGraficaDefaultV.class);
	
	public static List<MEXMEGraficaDefaultV> getGraphicDefault(Properties ctx, int exmeGraficaId, String trxName) {
		List<MEXMEGraficaDefaultV> lista = new ArrayList<MEXMEGraficaDefaultV>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = new StringBuilder();
			sql.append("SELECT  * FROM EXME_GraficaDefaultV ")
			.append(" WHERE IsActive = 'Y' ")
			.append(" AND EXME_Grafica_ID = ?");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

			sql.append(" ORDER BY Value");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmeGraficaId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MEXMEGraficaDefaultV(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}

}
