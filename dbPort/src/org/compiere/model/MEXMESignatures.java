package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMESignatures extends X_EXME_Signatures {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMESignatures.class);

	public MEXMESignatures(Properties ctx, int EXME_Signatures_ID,
			String trxName) {
		super(ctx, EXME_Signatures_ID, trxName);
	}

	public MEXMESignatures(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMESignatures> getSigs(Properties ctx, int medicoId,
			int productID, String trxName) {
		ArrayList<MEXMESignatures> sigs = new ArrayList<MEXMESignatures>();

		String sql = "SELECT * FROM EXME_SIGNATURES WHERE ISACTIVE = 'Y' AND EXME_MEDICO_ID = ? AND M_PRODUCT_ID = ?";
		
		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
		ResultSet rs = null;
		
		try {
			pstmt.setInt(1, medicoId);
			pstmt.setInt(2, productID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sigs.add(new MEXMESignatures(ctx,rs,trxName));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return sigs;
	}

}
