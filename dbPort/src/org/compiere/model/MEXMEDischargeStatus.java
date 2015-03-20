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
import org.compiere.util.KeyNamePair;

public class MEXMEDischargeStatus extends X_EXME_DischargeStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger		slog = CLogger.getCLogger (MEXMEDischargeStatus.class);

	
	public MEXMEDischargeStatus(final Properties ctx, final int EXME_DischargeStatus_ID, final String trxName) {
		super(ctx, EXME_DischargeStatus_ID, trxName);
	}

	public MEXMEDischargeStatus(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/** @deprecated use {@link KeyNamePair} instead	{@link #getList(Properties)} */
	public static ArrayList<LabelValueBean> getLstDischargeCodes (final Properties ctx) throws Exception{
		final ArrayList<LabelValueBean> lstCodes = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT Name, EXME_DischargeStatus_ID ") 
		.append(" FROM EXME_DischargeStatus ")
		.append(" WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_DischargeStatus"))
		.append(" ORDER BY Value ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean code = new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2)));
				lstCodes.add(code);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLstDischargeCodes", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return lstCodes;
	}
	
	/**
	 * Discharge status list
	 * @param ctx
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getList(final Properties ctx){
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT EXME_DischargeStatus_ID, Name ");
		sql.append(" FROM EXME_DischargeStatus ");
		sql.append(" WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Value ");
		return Query.getListKN(sql.toString(), null);
	}
	
	public static boolean isExpiredfromID(final Properties ctx, final int dischargeStatusID) {
		if (dischargeStatusID > 0) {
			final MEXMEDischargeStatus status = new Query(ctx, Table_Name, " EXME_DischargeStatus_ID=?", null)//
				.setParameters(dischargeStatusID)//
				.first();
			return status != null && TYPE_Expired.equals(status.getType());
		}
		return false;
	}
	
	/**
	 * Discharge status list
	 * @param ctx
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getListValName(final Properties ctx){
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT value, Name ");
		sql.append(" FROM EXME_DischargeStatus ");
		sql.append(" WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Value ");
		return Query.getListKN(sql.toString(), null);
	}
}
