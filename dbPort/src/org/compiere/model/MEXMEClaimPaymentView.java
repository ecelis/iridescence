package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEClaimPaymentView extends X_EXME_ClaimPayment_View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8108893546657537103L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEClaimPaymentView.class);

	public MEXMEClaimPaymentView(Properties ctx, int EXME_ClaimPayment_View_ID, String trxName) {
		super(ctx, EXME_ClaimPayment_View_ID, trxName);
	}
	
	public MEXMEClaimPaymentView(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get a list of logs by HL7 Dashboard ID
	 * @param ctx
	 * @param HL7_Dashboard_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMEClaimPaymentView> getAll(Properties ctx, String trxName, String whereClause, Object... parameters) {
		ArrayList<MEXMEClaimPaymentView> list = new ArrayList<MEXMEClaimPaymentView>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_CLAIMPAYMENT_VIEW WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(whereClause == null ? "" : whereClause);

		sql.append(" ORDER BY EXME_ClaimPayment_View.EXME_ClaimPayment_View_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, parameters);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEClaimPaymentView reg = new MEXMEClaimPaymentView(ctx, rs, trxName);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getAll: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
}
