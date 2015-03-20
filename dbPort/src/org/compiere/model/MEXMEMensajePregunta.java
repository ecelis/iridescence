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
public class MEXMEMensajePregunta extends X_EXME_MensajePregunta {

	private static CLogger log = CLogger.getCLogger(MEXMEMensajePregunta.class);
	private static final long serialVersionUID = -7514032912663777863L;

	/**
	 * Mensaje de pregunta
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param pregId
	 *            Pregunta a consultar
	 * @param trxName
	 *            Trx del movimiento
	 * @return Listado de mensajes de la pregunta
	 */
	public static List<MEXMEMensajePregunta> get(Properties ctx, int pregId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  Exme_Mensajepregunta ");
		sql.append("WHERE ");
		sql.append("  Exme_Pregunta_Id = ? ");

		List<MEXMEMensajePregunta> list = new ArrayList<MEXMEMensajePregunta>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pregId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEMensajePregunta(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * @param ctx
	 * @param EXME_MensajePregunta_ID
	 * @param trxName
	 */
	public MEXMEMensajePregunta(Properties ctx, int EXME_MensajePregunta_ID, String trxName) {
		super(ctx, EXME_MensajePregunta_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMensajePregunta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
