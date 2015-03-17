package org.compiere.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.ecaresoft.api.Generic;

/**
 * Nuevo logueo
 * 
 * @author odelarosa
 *         Modificado por Lorena Lama, Feb 2015 (multi organizacion Card #1638 )
 */
public class NewLogin {

	private static CLogger	s_log		= CLogger.getCLogger(NewLogin.class);
	private int				adUserId;
	private boolean			useUserOrg	= false;

	/**
	 * Constructor default
	 * 
	 * @param adUserId Usuario
	 */
	public NewLogin(final int adUserId) {
		this.adUserId = adUserId;
		setUserOrg();
	}

	public int getAdUserId() {
		return adUserId;
	}

	/** @return Clientes del Usuario */
	public List<Generic> getClients() {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT DISTINCT c.ad_client_id, c.name ");
		if (useUserOrg) {
			// Acceso por usuario - organizacion
			sql.append("FROM ad_user_orgaccess ur ");
			sql.append("INNER JOIN ad_client c ON ur.ad_client_id=c.ad_client_id ");
		} else {
			// Usuario - Perfil | Perfil - Organizacion | Cliente - Organizacion
			sql.append("FROM ad_user_roles ur ");
			sql.append("INNER JOIN ad_role_orgaccess ro ");
			sql.append("      ON ur.ad_role_id=ro.ad_role_id AND ro.isactive='Y' ");
			sql.append("INNER JOIN ad_org o ");
			sql.append("      ON ro.ad_org_id=o.ad_org_id AND o.isactive='Y' ");
			sql.append("INNER JOIN ad_client c ");
			sql.append("      ON o.ad_client_id=c.ad_client_id ");
		}
		sql.append("WHERE ur.ad_user_id=?  ");
		sql.append(" AND ur.isactive='Y' ");
		sql.append(" AND c.isactive='Y' ");
		sql.append("ORDER BY c.name ");

		return getList(sql, adUserId);
	}

	/**
	 * @param adRoleId Rol
	 * @param adOrgId Organización
	 * @return Estaciones de Servicio del Rol
	 */
	public List<Generic> getEstServ(final int adRoleId, final int adOrgId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.exme_estserv_id, e.name ");
		sql.append("FROM exme_estserv e ");
		sql.append("INNER JOIN exme_roleestserv re ");
		sql.append("        ON e.exme_estserv_id=re.exme_estserv_id ");
		sql.append("WHERE re.ad_role_id=?  ");
		sql.append("  AND re.isActive='Y'");
		sql.append("  AND re.ad_org_id=? ");
		sql.append("  AND e.isactive='Y' ");
		sql.append("ORDER BY e.name ");

		return getList(sql, adRoleId, adOrgId);
	}

	/**
	 * @param sql Sentencia SQL
	 * @param params listado de parametros
	 * @return Lista de valores genericos
	 */
	private List<Generic> getList(final StringBuilder sql, Object... params) {
		final List<Generic> list = new ArrayList<Generic>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Generic(rs.getString(2), rs.getString(1)));
			}
			s_log.config(sql.toString());
			s_log.config(params.toString());
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * @param adClientId Cliente
	 * @return Organizaciones del Usuario
	 */
	public List<Generic> getOrgs(final int adClientId) {
		final List<Object> params = new ArrayList<>();
		final StringBuilder sql = new StringBuilder();

		// subtabla para organizaciones padre
		sql.append("WITH parent_org AS ( ");
		sql.append(" SELECT o.*  \n");
		sql.append(" FROM ad_org o  \n");
		sql.append(" INNER JOIN ad_orginfo i ON o.ad_org_id=i.ad_org_id ");
		sql.append("                        AND i.parent_org_id IS NULL \n");
		sql.append(" WHERE o.isactive='Y' ");
		sql.append(")  ");
		sql.append("SELECT DISTINCT o.ad_org_id, o.name ");
		// si tiene registro en ad_user_roles
		if (useUserOrg) {
			sql.append("FROM ( \n");
			// Permisos para todas las organizaciones AD_Org_ID=*
			sql.append(" SELECT o.ad_org_id, o.name \n");
			sql.append(" FROM parent_org o  \n");
			sql.append(" INNER JOIN ad_user_orgaccess uoa ");
			sql.append("         ON (o.ad_client_id=uoa.ad_client_id ");
			sql.append("             AND uoa.ad_org_id=0 ");
			sql.append("             AND uoa.isactive='Y' ");
			sql.append("             AND uoa.ad_user_id=?)  \n");
			sql.append(" WHERE o.ad_client_id=?  \n");
			params.add(adUserId);
			params.add(adClientId);
			sql.append("UNION \n");
			// Permisos para todas las organizaciones especificas
			sql.append(" SELECT o.ad_org_id, o.name \n");
			sql.append(" FROM parent_org o \n");
			sql.append(" INNER JOIN  ad_user_orgaccess uoa ");
			sql.append("         ON (o.ad_org_id=uoa.ad_org_id  ");
			sql.append("       AND uoa.isactive='Y' ");
			sql.append("       AND uoa.ad_user_id=?) ");
			sql.append(" WHERE o.ad_client_id=?  ");
			params.add(adUserId);
			params.add(adClientId);
			sql.append(") AS o  \n");
		} else {
			sql.append("FROM  ad_user_roles ur ");
			sql.append("INNER JOIN ad_role_orgaccess ro ");
			sql.append("       ON ur.ad_role_id=ro.ad_role_id ");
			sql.append("INNER JOIN parent_org o ");
			sql.append("       ON ro.ad_org_id=o.ad_org_id ");
			sql.append("WHERE ur.ad_user_id=? ");
			sql.append("  AND ur.isactive='Y' ");
			sql.append("  AND o.ad_client_id=?  ");
			params.add(adUserId);
			params.add(adClientId);
		}
		sql.append("ORDER BY o.name ");
		return getList(sql, params.toArray());
	}

	/**
	 * @param adClientId Cliente
	 * @param adOrgId Organizacion
	 * @return Roles del Usuario
	 */
	public List<Generic> getRoles(final int adClientId, final int adOrgId) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT r.ad_role_id, r.name ");
		if (useUserOrg) {
			sql.append("FROM ad_user_roles ur ");
			sql.append("INNER JOIN ad_user_orgaccess uo ");
			sql.append("      ON ur.ad_user_id=uo.ad_user_id ");
			sql.append("INNER JOIN ad_role r ");
			sql.append("      ON ur.ad_role_id=r.ad_role_id ");
			sql.append("WHERE ur.ad_user_id=?  ");
			sql.append(" AND ur.ad_org_id IN (0,?)  ");
			sql.append(" AND ur.isactive='Y'  ");
			sql.append("AND uo.isactive='Y' ");
		} else {
			sql.append("FROM ad_user_roles ur ");
			sql.append("INNER JOIN ad_role_orgaccess ro ");
			sql.append("      ON ur.ad_role_id=ro.ad_role_id ");
			sql.append("INNER JOIN ad_role r ");
			sql.append("      ON ro.ad_role_id=r.ad_role_id ");
			sql.append("WHERE ur.ad_user_id=?  ");
			sql.append("  AND ro.ad_org_id=?  ");
			sql.append("  AND ur.isactive='Y'  ");
		}
		sql.append(" AND r.ad_client_id=?  ");
		sql.append(" AND r.isactive='Y'  ");
		sql.append(" AND r.istemplate='N'  ");
		sql.append("ORDER BY r.name ");

		return getList(sql, adUserId, adOrgId, adClientId);
	}

	public void setAdUserId(final int adUserId) {
		this.adUserId = adUserId;
	}

	/** Inicializamos si es por user org o no */
	private void setUserOrg() {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) ");
		sql.append("FROM ad_user_orgaccess ");
		sql.append("WHERE ad_user_id=?  ");
		sql.append("  AND isactive='Y' ");
		final int tot = DB.getSQLValue(null, sql.toString(), adUserId);
		if (tot > 0) {
			useUserOrg = true;
		}
	}
}
