package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMERolesConfigurador extends X_EXME_RolesConfigurador {

	private static final long serialVersionUID = 3730605053793140071L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERolesConfigurador.class);

	public MEXMERolesConfigurador(Properties ctx, int EXME_RolesConfigurador_ID, String trxName) {
		super(ctx, EXME_RolesConfigurador_ID, trxName);
	}

	public MEXMERolesConfigurador(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Crea los roles del configurador
	 * 
	 * @param ctx
	 * @param localCtx
	 * @param client
	 * @param org
	 * @param areaType
	 * @param trxName
	 * @return
	 */
	public static boolean createRolesConfigurador(Properties ctx, Properties localCtx, 
			MClient client, MOrg org, String areaType, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select r.* from exme_rolesconfigurador rc ");
		sql.append("	inner join ad_role r on r.roletype = rc.roletype and rc.ad_client_id = 0");
		sql.append("	where rc.tipoarea = ? and r.ad_client_id = 0 and rc.isActive = 'Y' and r.isActive = 'Y' and r.isTemplate='Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, areaType);

			rs = pstmt.executeQuery();
			MRole role = null;
			MRole newrole = null;
			HashMap<String, MRole> roles = new HashMap<String, MRole>();
			while (rs.next()) {
				role = new MRole(ctx, rs, trxName);
				newrole = new MRole(ctx, 0, trxName);
				copyValues(role, newrole, client.getAD_Client_ID(), org.getAD_Org_ID());
				newrole.setUserLevel(X_AD_Role.USERLEVEL_Client);
				String roleType = newrole.getRoleType();
				newrole.setIsTemplate(false);
				newrole.setCopyOf(role.getAD_Role_ID());
				newrole.setName(MSetup.abc(new MOrg(localCtx, Env.getAD_Org_ID(localCtx), trxName).getName()) + " " + newrole.getName());
				if (!newrole.save(trxName)) {
					return false;
				} else {
					roles.put(roleType, newrole);
				}
				localCtx.put("#ROLES_Created", roles);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "MEXMERolesConfigurador.createRolesConfigurador", e);
			return false;
		} finally {
			DB.close(rs, pstmt);

		}

		return true;
	}

	/**
	 * Roles del Configurador
	 * 
	 * @param ctx
	 *            Contexto
	 * @param areaType
	 *            Tipo de Area
	 * @return Roles del Configurador
	 */
	public static ArrayList<LabelValueBean> getRolesConfigurador(Properties ctx, String areaType) {
		ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select r.roletype from exme_rolesconfigurador rc ");
		sql.append("	inner join ad_role r on r.roletype = rc.roletype and rc.ad_client_id = 0");
		sql.append("	where rc.isActive = 'Y' and rc.tipoarea = ? and r.ad_client_id = 0 and r.isTemplate = 'Y'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, areaType);

			rs = pstmt.executeQuery();
			LabelValueBean item;
			while (rs.next()) {
				String id = rs.getString(1);
				String valor = MRefList.getListName(ctx, X_EXME_RolesConfigurador.ROLETYPE_AD_Reference_ID, id);
				item = new LabelValueBean(valor, id);
				ret.add(item);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "MEXMERolesConfigurador.createRolesConfigurador", e);
		} finally {
			DB.close(rs, pstmt);

		}

		return ret;
	}
	
	/**
	 * Roles Type By Area
	 * 
	 * @param ctx
	 *            Context
	 * @param areaType
	 *            Tipo de area
	 * @return
	 */
	public static List<String> getRolesTypesByArea(Properties ctx, String areaType) {
		List<String> ret = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select rc.roletype from exme_rolesconfigurador rc ");
		sql.append("	where rc.isActive = 'Y' and rc.tipoarea = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, areaType);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString(1));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);

		}

		return ret;
	}
	
	/**
	 * Roles By Type
	 * 
	 * @param ctx
	 *            Contexto
	 * @param type
	 *            Tipo
	 * @param trxName
	 *            Transaccion
	 * @return Listado de roles por tipo de ocnfigurador
	 */
	public static List<MRole> getRolesByType(Properties ctx, String type, String trxName) {
		List<MRole> ret = new ArrayList<MRole>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  AD_Role ");
		sql.append("WHERE ");
		sql.append("  RoleType = ? AND ");
		sql.append("  isTemplate = 'Y' AND ");
		sql.append("  AD_Client_ID = 0 AND ");
		sql.append("  AD_Org_ID = 0 and isActive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, type);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ret.add(new MRole(ctx, rs, null));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);

		}

		return ret;
	}

}
