package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

/**
 * Encabezado de formato impreso
 * 
 * @author odelarosa
 * 
 */
public class MEXMEPrintedFormatHdr extends X_EXME_PrintedFormatHdr {
	private static CLogger s_log = CLogger.getCLogger(MEXMEPrintedFormatHdr.class);
	private static final long serialVersionUID = 4703352625971563293L;

	/**
	 * Listado de formatos impresos
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param pacId
	 *            Paciente
	 * @param date1
	 *            Fecha inicial
	 * @param date2
	 *            Fecha final
	 * @param trxName
	 *            Trx Name
	 * @return Listado que coincida con las fechas dadas
	 */
	public static List<MEXMEPrintedFormatHdr> get(Properties ctx, int pacId, Timestamp date1, Timestamp date2, String trxName) {
		List<MEXMEPrintedFormatHdr> lst = new ArrayList<MEXMEPrintedFormatHdr>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_printedformathdr ");
		sql.append("WHERE ");
		sql.append(" EXME_Paciente_ID = ? AND ");
		sql.append("  created BETWEEN ");
		sql.append("  ? AND ");
		sql.append("  ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append(" ORDER BY created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pacId);
			pstmt.setTimestamp(2, TimeUtil.getInitialRangeT(ctx, date1));
			pstmt.setTimestamp(3, TimeUtil.getFinalRangeT(ctx, date2));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMEPrintedFormatHdr(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Obtener el evento previo en caso de existir, <7br> siempre y cuando no este finalizado
	 * 
	 * @param ctx
	 * @param pacId
	 * @param hdrId
	 * @param trxName
	 * @return
	 */
	public static MEXMEPrintedFormatHdr getExisting(Properties ctx, int pacId, int hdrId, String trxName) {
		MEXMEPrintedFormatHdr hdr = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_printedformathdr ");
		sql.append("WHERE ");
		sql.append("  exme_paciente_id = ? AND ");
		sql.append("  exme_formsectionheader_id = ? AND ");
		sql.append("  isComplete = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacId);
			pstmt.setInt(2, hdrId);
			pstmt.setString(3, "N");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hdr = new MEXMEPrintedFormatHdr(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return hdr;
	}

	private List<MEXMEPrintedFormatDet> detail = null;

	/**
	 * @param ctx
	 * @param EXME_PrintedFormatHdr_ID
	 * @param trxName
	 */
	public MEXMEPrintedFormatHdr(Properties ctx, int EXME_PrintedFormatHdr_ID, String trxName) {
		super(ctx, EXME_PrintedFormatHdr_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPrintedFormatHdr(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtener listado de secciones
	 * 
	 * @return
	 */
	public List<MEXMEPrintedFormatDet> getDetail() {
		if (detail == null) {
			detail = new ArrayList<MEXMEPrintedFormatDet>();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_printedformatdet ");
			sql.append("WHERE ");
			sql.append("  exme_printedformathdr_id = ? AND ");
			sql.append("  isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), Constantes.SPACE, MEXMEPrintedFormatDet.Table_Name));
			sql.append(" ORDER BY SeqNo");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, getEXME_PrintedFormatHdr_ID());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					detail.add(new MEXMEPrintedFormatDet(getCtx(), rs, null));
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, null, e);
			} finally {
				DB.close(rs, pstmt);
			}
		}
		return detail;
	}

}
