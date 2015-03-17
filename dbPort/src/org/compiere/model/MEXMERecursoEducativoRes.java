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
public class MEXMERecursoEducativoRes extends X_EXME_RecursoEducativoRes {

	private static final long serialVersionUID = 3911480080155947306L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERecursoEducativoRes.class);

	/**
	 * @param ctx
	 * @param EXME_RecursoEducativoRes_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativoRes(Properties ctx, int EXME_RecursoEducativoRes_ID, String trxName) {
		super(ctx, EXME_RecursoEducativoRes_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativoRes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene los recursos educativos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoRes> getFromRecursoEducativo(final Properties ctx, int recursoEducativoID, final String trxName) {
		List<MEXMERecursoEducativoRes> lista = new ArrayList<MEXMERecursoEducativoRes>();
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

//	public static List<MEXMERecursoEducativoRes> getFromRecursoEducativo(Properties ctx, int recursoEducativoID, String trxName) {
//		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoRes WHERE EXME_RecursoEducativo_ID = ? ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_RecursoEducativoRes"));
//		st.append(" order by created");
//		List<MEXMERecursoEducativoRes> lista = new ArrayList<MEXMERecursoEducativoRes>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), trxName);
//			pstmt.setInt(1, recursoEducativoID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMERecursoEducativoRes(ctx, rs, trxName));
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
