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
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

public class MEXMEGraficaDet extends X_EXME_GraficaDet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1881992238125962774L;

	public MEXMEGraficaDet(Properties ctx, int EXME_GraficaDet_ID, String trxName) {
		super(ctx, EXME_GraficaDet_ID, trxName);
	}
	
	public MEXMEGraficaDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static CLogger s_log = CLogger.getCLogger (MEXMEGraficaDet.class);
	
	public static List<MEXMEGraficaDet> getGraphicDet(Properties ctx, int EXME_Grafica_ID, String trxName) {
		List<MEXMEGraficaDet> lista = new ArrayList<MEXMEGraficaDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = new StringBuilder();
			sql.append("SELECT  * FROM EXME_GraficaDet ")
			.append(" WHERE IsActive = 'Y' AND EXME_Grafica_ID = ?");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY Secuencia");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Grafica_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MEXMEGraficaDet(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	public static MEXMEGraficaDet getGraphicByAxis(Properties ctx, int exmeGraficaID, boolean xAxis, String trxName) {
		MEXMEGraficaDet obj = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = new StringBuilder();
			sql.append("SELECT  * FROM EXME_GraficaDet ")
			.append(" WHERE IsActive = 'Y' AND EXME_Grafica_ID = ?");
			if (xAxis) {
				sql.append("AND IsXAxis = 'Y'");
			} else {
				sql.append("AND IsYAxis = 'Y'");
			}			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmeGraficaID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				obj = new MEXMEGraficaDet(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return obj;
	}
	
	protected boolean beforeSave() {
		
		if (isXAxis() && isYAxis()) {
			log.saveError(" ", Utilerias.getMessage(Env.getCtx(), null, "msg.ConfigRecordatorio.EMail"));
			return false;
		}
		List<MEXMEGraficaDet> lst = MEXMEGraficaDet.getGraphicDet(getCtx(), getEXME_Grafica_ID(), null);
		for (MEXMEGraficaDet obj: lst) {
			if (isXAxis() && obj.isXAxis()) {
				log.saveError(" ", Utilerias.getMessage(Env.getCtx(), null, "msg.ConfigRecordatorio.EMail"));
				return false;
			} else if (isYAxis() && obj.isYAxis()) {
				log.saveError(" ", Utilerias.getMessage(Env.getCtx(), null, "msg.ConfigRecordatorio.EMail"));
				return false;
			}
		}
		return true;
	}
}
