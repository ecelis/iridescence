/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;

/**
 * @author odelarosa
 * 
 */
public class MEXMERecursoEducativoProd extends X_EXME_RecursoEducativoProd {

	private static final long serialVersionUID = -2755630486731915047L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERecursoEducativoProd.class);

	/**
	 * @param ctx
	 * @param EXME_RecursoEducativoProd_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativoProd(Properties ctx, int EXME_RecursoEducativoProd_ID, String trxName) {
		super(ctx, EXME_RecursoEducativoProd_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativoProd(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene los recursos educativos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoProd> getFromRecursoEducativo(final Properties ctx, int recursoEducativoID, final String trxName) {
		List<MEXMERecursoEducativoProd> lista = new ArrayList<MEXMERecursoEducativoProd>();
		try {
			lista = new Query(ctx, Table_Name, " EXME_RecursoEducativo_ID=? ", trxName)//
				.addAccessLevelSQL(true)//
				.setParameters(recursoEducativoID)//
				.setOrderBy(" Created ")//
				.list();
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}

//	public static List<MEXMERecursoEducativoProd> getFromRecursoEducativo(Properties ctx, int recursoEducativoID, String trxName) {
//		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoProd WHERE EXME_RecursoEducativo_ID = ? ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_RecursoEducativoProd"));
//		st.append(" order by created");
//		List<MEXMERecursoEducativoProd> lista = new ArrayList<MEXMERecursoEducativoProd>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), trxName);
//			pstmt.setInt(1, recursoEducativoID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMERecursoEducativoProd(ctx, rs, trxName));
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

}
