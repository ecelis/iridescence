package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEGroupDiag extends X_EXME_Group_Diag {

	private static final long serialVersionUID = 1L;

	private static CLogger log = CLogger.getCLogger(MEXMEGroupDiag.class);
	
	public MEXMEGroupDiag(Properties ctx, int EXME_Group_Diag_ID, String trxName) {
		super(ctx, EXME_Group_Diag_ID, trxName);
	}
	
	public MEXMEGroupDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	/**
	 * Listado de Stroke por tipo
	 * 
	 * @param ctx
	 *            Contexto
	 * @param stroke
	 *            Tipo de Stroke
	 * @param trxName
	 *            Trx
	 * @return
	 */
	public static List<Integer> getStroke(Properties ctx, String stroke, String trxName) {
		StringBuilder st = new StringBuilder("SELECT EXME_Diagnostico_ID FROM EXME_Group_Diag ");
		st.append(" where stroke = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_Group_Diag"));
		List<Integer> lista = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setString(1, stroke);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lista.add(rs.getInt(1));
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
}
