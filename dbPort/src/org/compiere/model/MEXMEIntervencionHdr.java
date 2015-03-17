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
public class MEXMEIntervencionHdr extends X_EXME_IntervencionHdr {

	private static final long serialVersionUID = 6202155647513141431L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEIntervencionHdr.class);

	public MEXMEIntervencionHdr(Properties ctx, int EXME_IntervencionHdr_ID, String trxName) {
		super(ctx, EXME_IntervencionHdr_ID, trxName);
	}

	public MEXMEIntervencionHdr(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMEIntervencionHdr get(Properties ctx, String name, String trxName) {
		StringBuilder st = new StringBuilder("select * from exme_intervencionhdr where value = ? ");
		MEXMEIntervencionHdr lstDiv = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lstDiv = new MEXMEIntervencionHdr(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiv;
	}

}
