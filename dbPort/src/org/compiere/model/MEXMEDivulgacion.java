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
import org.compiere.util.vo.DisclosuresVO;

/**
 * 
 * @author Omar de la Rosa
 *
 */
public class MEXMEDivulgacion extends X_EXME_Divulgacion {

	private static final long serialVersionUID = -1892537026411050766L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEDivulgacion.class);

	public MEXMEDivulgacion(Properties ctx, int EXME_Divulgacion_ID, String trxName) {
		super(ctx, EXME_Divulgacion_ID, trxName);
	}

	public MEXMEDivulgacion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Listado de Divulgaciones en un periodo de tiempo
	 * @param ctx Contexto
	 * @param fechaIni Fecha Inicial
	 * @param fechaFin Fecha Final
	 * @return Listado de divulgaciones
	 */
	public static List<DisclosuresVO> getDisclosures(Properties ctx, String fechaIni, String fechaFin) {
		StringBuilder st = new StringBuilder("select i.exme_divulgacion_id as id, u.name, i.created, i.description, p.value from EXME_Divulgacion i ");
		st.append("inner join ad_user u on u.ad_user_id = i.createdby ");
		st.append("inner join exme_paciente p on p.exme_paciente_id = i.exme_paciente_id ");
		st.append("where i.isactive = 'Y' ");
		st.append("and i.created >= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st.append("and i.created <= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_Divulgacion", "i"));
		st.append(" order by i.created desc");
		List<DisclosuresVO> lstDiv = new ArrayList<DisclosuresVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, fechaIni);
			pstmt.setString(2, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DisclosuresVO vo = new DisclosuresVO();
				vo.setFecha(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("id")));
				vo.setValue(rs.getString("value"));
				vo.setDescription(rs.getString("description"));
				vo.setUserName(rs.getString("name"));
				lstDiv.add(vo);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiv;
	}

}
