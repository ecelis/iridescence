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

public class MEXMEInsuranceType extends X_EXME_InsuranceType {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8741109787231602064L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEInsuranceType.class);

	public MEXMEInsuranceType(Properties ctx, int EXME_InsuranceType_ID,
			String trxName) {
		super(ctx, EXME_InsuranceType_ID, trxName);
	}

	/**
	 * Método que nos trae <strong>Nombre </strong> y <strong>
	 * EXME_InsuranceType_ID </strong> de todas los tipos de aseguradoras
	 * existentes para poder desplegarlos en un combo
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getAll(Properties ctx, String trxName) {

		List<LabelValueBean> lstInsuranceType = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT NAME, EXME_InsuranceType_ID ").append(
				" FROM EXME_InsuranceType WHERE isActive = 'Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), Table_Name));
		sql.append(" ORDER BY NAME");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString(1), String
						.valueOf(rs.getLong(2)));
				lstInsuranceType.add(lvb);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getAll", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstInsuranceType;
	}

}
