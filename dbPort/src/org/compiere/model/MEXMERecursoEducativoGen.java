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
public class MEXMERecursoEducativoGen extends X_EXME_RecursoEducativoGen {

	private static final long serialVersionUID = -8900169278962119295L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERecursoEducativoGen.class);

	/**
	 * Recursos educativos de producto generico
	 * 
	 * @param ctx
	 *            Contexto
	 * @param recursoEducativoID
	 *            Recurso Educativo
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<MEXMERecursoEducativoGen> getFromRecursoEducativo(Properties ctx, int recursoEducativoID, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoGen WHERE EXME_RecursoEducativo_ID = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), MEXMERecursoEducativoGen.Table_Name));
		st.append(" order by created");
		List<MEXMERecursoEducativoGen> lista = new ArrayList<MEXMERecursoEducativoGen>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, recursoEducativoID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMERecursoEducativoGen(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * @param ctx
	 * @param EXME_RecursoEducativoGen_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativoGen(Properties ctx, int EXME_RecursoEducativoGen_ID, String trxName) {
		super(ctx, EXME_RecursoEducativoGen_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativoGen(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
