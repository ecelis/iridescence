package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.vo.PrintedReportVO;

/**
 * 
 * @author Omar de la Rosa
 *
 */
public class MEXMEReporteImpreso extends X_EXME_ReporteImpreso {

	private static final long serialVersionUID = 6309037306704217713L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEReporteImpreso.class);

	public MEXMEReporteImpreso(Properties ctx, int EXME_ReporteImpreso_ID, String trxName) {
		super(ctx, EXME_ReporteImpreso_ID, trxName);
	}

	public MEXMEReporteImpreso(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Lista de reportes impresos en un periodo de tiempo
	 * @param ctx Contexto
	 * @param fechaIni Fecha Inicial
	 * @param fechaFin Fecha Final
	 * @return Listado de reportes
	 */
	public static List<PrintedReportVO> getPrintedReports(Properties ctx, String fechaIni, String fechaFin) {
		StringBuilder st = new StringBuilder("select i.exme_reporteimpreso_id as id, u.name, i.created, i.name as rep, i.sql, i.params from EXME_ReporteImpreso i ");
		st.append("inner join ad_user u on u.ad_user_id = i.createdby ");
		st.append("where i.isactive = 'Y' ");
		st.append("and i.created >= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st.append("and i.created <= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_ReporteImpreso", "i"));
		st.append(" order by i.created desc");
		List<PrintedReportVO> printedReportVO = new ArrayList<PrintedReportVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, fechaIni);
			pstmt.setString(2, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PrintedReportVO vo = new PrintedReportVO();
				vo.setFecha(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("id")));
				vo.setName(rs.getString("rep"));
				vo.setParam(rs.getString("params"));
				vo.setSql(rs.getString("sql"));
				vo.setUserName(rs.getString("name"));
				printedReportVO.add(vo);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return printedReportVO;
	}

}
