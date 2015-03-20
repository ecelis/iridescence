package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 
 * @author odelarosa
 * 
 */
public class MEXMEDiagnosticoHdr extends X_EXME_DiagnosticoHdr {

	private static final long serialVersionUID = 403755337337284145L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEDiagnosticoHdr.class);

	public MEXMEDiagnosticoHdr(Properties ctx, int EXME_DiagnosticoHdr_ID, String trxName) {
		super(ctx, EXME_DiagnosticoHdr_ID, trxName);
	}

	public MEXMEDiagnosticoHdr(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene Diagnostico por Value
	 * @param ctx
	 * @param value
	 * @return
	 */
	public static MEXMEDiagnosticoHdr get(Properties ctx, String value) {
		StringBuilder st = new StringBuilder("select * from exme_diagnosticohdr where value = ? ");
		MEXMEDiagnosticoHdr diag = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				diag = new MEXMEDiagnosticoHdr(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return diag;
	}

}
