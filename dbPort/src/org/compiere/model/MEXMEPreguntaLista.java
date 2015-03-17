package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMEPreguntaLista extends X_EXME_Pregunta_Lista {

	private static CLogger s_log = CLogger.getCLogger(MEXMEPreguntaLista.class);
	private static final long serialVersionUID = 6903849452004621154L;

	public static List<MEXMEPreguntaLista> get(Properties ctx, int preguntaId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_pregunta_lista ");
		sql.append("WHERE ");
		sql.append("  exme_pregunta_id = ? AND ");
		sql.append("  isactive = 'Y' ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPreguntaLista.Table_Name));
		sql.append(" order by seqNo ");
		List<MEXMEPreguntaLista> list = new ArrayList<MEXMEPreguntaLista>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, preguntaId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEPreguntaLista(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static String getPrintText(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  name ");
		sql.append("FROM ");
		sql.append("  exme_pregunta_lista ");
		sql.append(" where exme_pregunta_lista_id = ?");
		return DB.getSQLValueString(null, sql.toString(), id);
	}

	/**
	 * @param ctx
	 * @param EXME_Pregunta_Lista_ID
	 * @param trxName
	 */
	public MEXMEPreguntaLista(Properties ctx, int EXME_Pregunta_Lista_ID, String trxName) {
		super(ctx, EXME_Pregunta_Lista_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPreguntaLista(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
