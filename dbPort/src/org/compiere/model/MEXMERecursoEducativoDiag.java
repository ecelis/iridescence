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
public class MEXMERecursoEducativoDiag extends X_EXME_RecursoEducativoDiag {

	private static final long serialVersionUID = 8156542110846992712L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERecursoEducativoDiag.class);

	/**
	 * @param ctx
	 * @param EXME_RecursoEducativoDiag_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativoDiag(Properties ctx, int EXME_RecursoEducativoDiag_ID, String trxName) {
		super(ctx, EXME_RecursoEducativoDiag_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativoDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene los recursos educativos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoDiag> getFromRecursoEducativo(final Properties ctx, int recursoEducativoID, final String trxName) {
		List<MEXMERecursoEducativoDiag> lista = new ArrayList<MEXMERecursoEducativoDiag>();
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

//	public static List<MEXMERecursoEducativoDiag> getFromRecursoEducativo(Properties ctx, int recursoEducativoID, String trxName) {
//		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoDiag WHERE EXME_RecursoEducativo_ID = ? ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_RecursoEducativoDiag"));
//		st.append(" order by created");
//		List<MEXMERecursoEducativoDiag> lista = new ArrayList<MEXMERecursoEducativoDiag>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), trxName);
//			pstmt.setInt(1, recursoEducativoID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMERecursoEducativoDiag(ctx, rs, trxName));
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
