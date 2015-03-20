package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMENotification extends X_EXME_Notification {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6275236181181996924L;

	private static CLogger s_log = CLogger.getCLogger(MEXMENotification.class);

	public MEXMENotification(Properties ctx, int EXME_Notification_ID, String trxName) {
		super(ctx, EXME_Notification_ID, trxName);
	}

	public MEXMENotification(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMENotification> getList() {
		List<MEXMENotification> list = new ArrayList<MEXMENotification>();

		String sql = "SELECT * FROM EXME_NOTIFICATION ORDER BY fechavencimiento DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMENotification(Env.getCtx(), rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}

		return list;
	}

	/**
	 * Notificaciones del usuario, se muestran si:</br>
	 * <ul>
	 * <li>Si el mensaje es por cliente y el usuario tiene acceso al cliente</li>
	 * <li>Si el mensaje es por organización y el usuario tiene acceso a la organización</li>
	 * <li>Si el mensaje es por usuario y es el mismo que el usuario
	 * <li>Si el mensaje no tiene tabla
	 * </ul>
	 * 
	 * @param user
	 * @return
	 */
	public static List<MEXMENotification> getUserList(MUser user) {
		List<MEXMENotification> list = new ArrayList<MEXMENotification>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EXME_NOTIFICATION WHERE fechavencimiento > ?");
		sql.append(" ORDER BY fechavencimiento DESC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setTimestamp(1, Env.getCurrentDate());
			rs = pstmt.executeQuery();
			MEXMENotification not = null;

			while (rs.next()) {

				not = new MEXMENotification(Env.getCtx(), rs, null);
				if (not.getAD_Table_ID() > 0) {
					if (not.getAD_Table_ID() == MClient.Table_ID) {
						List<MUserOrgAccess> lista = Arrays.asList(MUserOrgAccess.getOfUser(Env.getCtx(), user.getAD_User_ID()));

						for (MUserOrgAccess acces : lista) {
							MOrg org = new MOrg(Env.getCtx(), acces.getAD_Org_ID(), null);
							if (not.getRecord_ID() == org.getAD_Client_ID()) {
								if (!list.contains(not)) {
									list.add(not);
								}
							}
						}

					}

					if (not.getAD_Table_ID() == MOrg.Table_ID) {
						List<MUserOrgAccess> lista = Arrays.asList(MUserOrgAccess.getOfUser(Env.getCtx(), user.getAD_User_ID()));
						for (MUserOrgAccess acces : lista) {
							if (not.getRecord_ID() == acces.getAD_Org_ID()) {
								if (!list.contains(not)) {
									list.add(not);
								}
							}
						}
					}

					if (not.getAD_Table_ID() == MRole.Table_ID) {
						List<MUserRoles> rolList = Arrays.asList(MUserRoles.getOfUser(Env.getCtx(), user.getAD_User_ID()));
						for (MUserRoles rol : rolList) {
							if (not.getRecord_ID() == rol.getAD_Role_ID()) {
								if (!list.contains(not)) {
									list.add(not);
								}
							}
						}

					}

					if (not.getAD_Table_ID() == MUser.Table_ID) {
						if (not.getRecord_ID() == user.getAD_User_ID()) {
							if (!list.contains(not)) {
								list.add(not);
							}
						}
					}
				} else {
					if (!list.contains(not)) {
						list.add(not);
					}
				}

			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

}
