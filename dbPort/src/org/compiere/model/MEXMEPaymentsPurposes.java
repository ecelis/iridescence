package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPaymentsPurposes extends X_EXME_PaymentPurposes{
	private static final long serialVersionUID = -3064142172834639930L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEPaymentsPurposes.class);

	public MEXMEPaymentsPurposes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEPaymentsPurposes(Properties ctx, int EXME_PaymentPurposes_ID, String trxName) {
		super(ctx, EXME_PaymentPurposes_ID, trxName);
	}
	
	/**
	 * Devuelve la siguiente cita medica no ejecutada del tratamiento
	 * 
	 * @param ctx
	 * @param exmeTratamientoId
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getAll(Properties ctx, String trxName) {
		ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			sql.append(" SELECT value, name FROM ")
			.append(" EXME_PaymentPurposes ")			
			.append(" WHERE EXME_PaymentPurposes.isactive = 'Y' ")			
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaymentPurposes"));

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ret.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return ret;
	}
}
