package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class MEXMEConfigSeguridad extends X_EXME_ConfigSeguridad {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigSeguridad.class);

	public MEXMEConfigSeguridad(Properties ctx, int EXME_ConfigSeguridad_ID, String trxName) {
		super(ctx, EXME_ConfigSeguridad_ID, trxName);
	}

	public MEXMEConfigSeguridad(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMEConfigSeguridad get(Properties ctx, String trxName) {

		String sql = "SELECT * FROM EXME_ConfigSeguridad WHERE IsActive = 'Y' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEConfigSeguridad config = null;

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ConfigSeguridad");
		pstmt = DB.prepareStatement(sql, trxName);

		try {
			rs = pstmt.executeQuery();

			if (rs.next()) {
				config = new MEXMEConfigSeguridad(ctx, rs, trxName);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "While loading right holder ....", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return config;
	}

	public static MEXMEConfigSeguridad getByClient(Properties ctx, int AD_Client_ID, String trxName) {
		String sql = "SELECT * FROM EXME_ConfigSeguridad WHERE IsActive = 'Y' and AD_Client_ID = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEConfigSeguridad config = null;

		//sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ConfigSeguridad");

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				config = new MEXMEConfigSeguridad(ctx, rs, trxName);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "While loading right holder ....", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return config;

	}
	
	/**
	 * Revisa si requiere o no cambio de password por fecha
	 * 
	 * @param ctx
	 * @param AD_Client_ID
	 * @param user
	 * @param trxName
	 * @return
	 */
	public static boolean needsToChangePassword(Properties ctx, int AD_Client_ID, MUser user, String trxName) {

		if (user.getAD_User_ID() == 100 || user.getAD_User_ID() == 0) {
			return false;
		} else {
			if (user.isNew()) {
				return true;
			} else {

				MEXMEConfigSeguridad config = getByClient(ctx, AD_Client_ID, trxName);

				int days = 0;

				if (config != null) {
					days = config.getDiasCambiaPwd();
				} else {
					return false;
				}

				if (days == 0) {
					return false;
				}

				if (user.getFechaCambiaPwd() == null) {
					return false;
				}

				DateTime now = new DateTime(DB.getDateForOrg(ctx));
				DateTime date = new DateTime(user.getFechaCambiaPwd());

				Days d = Days.daysBetween(date, now);

				if (d.getDays() >= days) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
