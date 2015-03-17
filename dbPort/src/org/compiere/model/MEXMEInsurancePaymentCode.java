/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author twry
 *
 */
public class MEXMEInsurancePaymentCode extends X_EXME_InsurancePaymentCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8957784369892836388L;
	/** Log */
	private static CLogger s_log = CLogger.getCLogger(MEXMEInsurancePaymentCode.class);
	/**
	 * @param ctx
	 * @param EXME_InsurancePaymentCode_ID
	 * @param trxName
	 */
	public MEXMEInsurancePaymentCode(Properties ctx,
			int EXME_InsurancePaymentCode_ID, String trxName) {
		super(ctx, EXME_InsurancePaymentCode_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEInsurancePaymentCode(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param C_BPartner_ID
	 * @return
	 */
	public static List<LabelValueBean> getLst(Properties ctx, int C_BPartner_ID) {
		List<LabelValueBean> valid = new ArrayList<LabelValueBean>();

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder()
		.append("SELECT Name, EXME_InsurancePaymentCode_ID ")
		.append(" FROM  EXME_InsurancePaymentCode ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND C_BPArtner_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_InsurancePaymentCode"));

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, C_BPartner_ID);
			rs = psmt.executeQuery();

			while (rs.next()) {
				valid.add(new LabelValueBean(rs.getString(1),String.valueOf(rs.getInt(2))));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return valid;
	}
	
}
