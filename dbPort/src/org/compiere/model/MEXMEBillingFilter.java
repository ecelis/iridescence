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
 * 
 * @author rsolorzano
 * 
 */
public class MEXMEBillingFilter extends X_EXME_BillingFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6678834199253919424L;

	private static CLogger log = CLogger.getCLogger(MEXMEBillingFilter.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_BillingFilter_ID
	 * @param trxName
	 */
	public MEXMEBillingFilter(Properties ctx, int EXME_BillingFilter_ID, String trxName) {
		super(ctx, EXME_BillingFilter_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEBillingFilter(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get all the filters for billing queue rules
	 * @param ctx
	 * @return
	 */
	public static List<MEXMEBillingFilter> getAll(Properties ctx) {

		final List<MEXMEBillingFilter> retValue = new ArrayList<MEXMEBillingFilter>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_BILLINGFILTER WHERE ISACTIVE = 'Y' ");
		sql.append("ORDER BY NAME");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMEBillingFilter(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getList", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

}
