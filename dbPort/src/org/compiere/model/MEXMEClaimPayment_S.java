package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author lherrera
 *
 */
public class MEXMEClaimPayment_S extends X_EXME_ClaimPayment_S {
	private static final long serialVersionUID = -477935633751942861L;
	private static CLogger log = CLogger.getCLogger(MEXMEClaimPayment_S.class);

	/**
	 * @param ctx
	 * @param EXME_ClaimPayment_S_ID
	 * @param trxName
	 */
	public MEXMEClaimPayment_S(Properties ctx, int EXME_ClaimPayment_S_ID,
			String trxName) {
		super(ctx, EXME_ClaimPayment_S_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEClaimPayment_S(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEClaimPayment_S> getAll(Properties ctx, String trxName) {
		ArrayList<MEXMEClaimPayment_S> ret = new ArrayList<MEXMEClaimPayment_S>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT * FROM  EXME_ClaimPayment_S WHERE IsActive = 'Y'")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
			.append(" ORDER BY PaymentDate DESC ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ret.add(new MEXMEClaimPayment_S(ctx, rs, null));
			}

		} catch (Exception e) {
			ret = null;
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return ret;
	}

}
