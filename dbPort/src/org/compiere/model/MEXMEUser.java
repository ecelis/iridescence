package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.bpm.BeanView;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;

/**
 * Modelo para Usuarios (extiende de MUser)
 * 
 * <b>Fecha:</b> 20/Marzo/2006
 * <p>
 * <b>Modificado: </b> $Author: taniap $
 * <p>
 * <b>En :</b> $Date: 2006/09/05 23:18:55 $
 * <p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.3 $
 */
public class MEXMEUser extends MUser {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEUser.class);

	/**************************************************************************
	 * Default Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_User_ID
	 *            id
	 */
	public MEXMEUser(Properties ctx, int AD_User_ID, String trxName) {
		super(ctx, AD_User_ID, trxName); // 0 is also System
		if (AD_User_ID == 0) {
			setIsFullBPAccess(true);
			setNotificationType(NOTIFICATIONTYPE_EMail);
		}
	} // MUser

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            current row of result set to be loaded
	 */
	public MEXMEUser(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MUser

	/**
	 * Get Sales Rep User (admin)
	 * 
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            id
	 * @return array of users
	 */
	public static int getSalesRepAdmin(Properties ctx) {
		int user = 0;
		String sql = "SELECT u.AD_User_ID FROM C_BPartner bp "
				+ "INNER JOIN AD_User u ON (u.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE bp.IsSalesRep = 'Y' AND bp.AD_Client_ID = ? AND "
				+ "bp.ISDEFAULT = 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();
			if (rs.next())
				user = rs.getInt("AD_User_ID");
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return user;
	} // getSalesRepAdmin

	/**
	 * Get Sales Rep User (admin)
	 * 
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            id
	 * @return array of users
	 */
	public static int getPerfil(Properties ctx) {
		int user = 0;
		String sql = "SELECT u.AD_User_ID FROM C_BPartner bp "
				+ "INNER JOIN AD_User u ON (u.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE bp.IsSalesRep = 'Y' AND bp.AD_Client_ID = ? AND "
				+ "UPPER(bp.Value) LIKE '%ADMIN%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();
			if (rs.next())
				user = rs.getInt("AD_User_ID");
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return user;
	} // getSalesRepAdmin

	/**
	 * Buscamos las caracteristicas del usuario
	 * 
	 * @param ctx
	 *            contexto
	 * @return
	 */
	public static boolean getReferences(Properties ctx) {

		boolean resultado = false;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT usuario.AD_User_ID ");
			sql.append(" , COALESCE(asis.EXME_Asistente_ID,-1)  AS EXME_Asistente_ID ");
			sql.append(" , COALESCE(morg.EXME_Medico_ID,-1)     AS EXME_Medico_ID ");
			sql.append(" , COALESCE(enfe.EXME_Enfermeria_ID,-1) AS EXME_Enfermeria_ID ");
			sql.append(" , COALESCE(phar.EXME_Pharmacist_ID,-1) AS EXME_Pharmacist_ID ");
			sql.append(" , phar.type                            AS EXME_Pharmacist_Type ");
			sql.append(" , COALESCE(msus.Substitute_ID,-1)      AS Substitute_ID ");
			sql.append(" FROM AD_User usuario ");
			sql.append(" LEFT JOIN EXME_Asistente   asis ON usuario.AD_User_ID  = asis.AD_User_ID     AND asis.IsActive = 'Y' ");
			sql.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Asistente.Table_Name, "asis")));
			sql.append(" LEFT JOIN EXME_Medico_Org  morg ON usuario.AD_User_ID  = morg.AD_User_ID     AND morg.IsActive = 'Y' ");
			sql.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Medico_Org.Table_Name, "morg")));
			sql.append(" LEFT JOIN EXME_Enfermeria  enfe ON usuario.AD_User_ID  = enfe.AD_User_ID     AND enfe.IsActive = 'Y' ");
			sql.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Enfermeria.Table_Name, "enfe")));
			sql.append(" LEFT JOIN EXME_Pharmacist  phar ON usuario.AD_User_ID  = phar.AD_User_ID     AND phar.IsActive = 'Y' ");
			sql.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Pharmacist.Table_Name, "phar")));
			sql.append(" LEFT JOIN EXME_Medico      medi ON usuario.AD_User_ID  = medi.AD_User_ID     AND medi.IsActive = 'Y' ");
			sql.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Medico.Table_Name, "medi")));
			sql.append(" LEFT JOIN EXME_Medico_Sust msus ON medi.EXME_Medico_ID = msus.Substitute_ID  AND msus.IsActive = 'Y' ");
			sql.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Medico_Sust.Table_Name, "msus")));
			sql.append(" WHERE usuario.AD_User_ID = ? ");
			

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_User_ID(ctx));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Env.setContext(ctx, "#EXME_Asistente_ID", rs.getInt("EXME_Asistente_ID"));
				Env.setContext(ctx, "#EXME_Medico_ID", rs.getInt("EXME_Medico_ID"));
				Env.setContext(ctx, "#EXME_Enfermeria_ID", rs.getInt("EXME_Enfermeria_ID"));
				Env.setContext(ctx, "#EXME_Pharmacist_ID", rs.getInt("EXME_Pharmacist_ID"));
				Env.setContext(ctx, "#Substitute_ID", rs.getInt("Substitute_ID"));
				Env.setContext(ctx, "#EXME_Pharmacist_Type", StringUtils.stripToEmpty(rs.getString("EXME_Pharmacist_Type")));
			}
			else {
				Env.setContext(ctx, "#EXME_Asistente_ID", -1);
				Env.setContext(ctx, "#EXME_Medico_ID", -1);
				Env.setContext(ctx, "#EXME_Enfermeria_ID", -1);
				Env.setContext(ctx, "#EXME_Pharmacist_ID", -1);
				Env.setContext(ctx, "#Substitute_ID", -1);
				Env.setContext(ctx, "#EXME_Pharmacist_Type", "");
			}

			s_log.fine("Asistente: " + Env.getEXME_Asistente_ID(ctx));
			s_log.fine("Medico: " + Env.getEXME_Medico_ID(ctx));
			s_log.fine("Enfermera: " + Env.getEXME_Enfermeria_ID(ctx));
			s_log.fine("EXME_Pharmacist_ID: " + Env.getEXME_Pharmacist_ID(ctx));
			s_log.fine("Sustituto: " + Env.getSubstitute_ID(ctx));

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			resultado = false;
		} finally {
			DB.close(rs, pstmt);
		}

		return resultado;
	}
	
	public static boolean sinRol(String appUser, boolean authenticated, String appPwd){
		boolean sinRol = false;
		List<BeanView>  bean = new ArrayList<BeanView>() ;
		StringBuffer sql = new StringBuffer("SELECT u.AD_User_ID, NVL(r.AD_Role_ID,0), r.Name,")
		.append(" u.ConnectionProfile ")
		.append(" FROM AD_User u")
		.append(" LEFT JOIN AD_User_Roles ur ON (u.AD_User_ID=ur.AD_User_ID AND ur.IsActive='Y')")
		.append(" LEFT JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID AND r.IsActive='Y' AND r.isTemplate = 'N') ")
		.append(" WHERE COALESCE(u.LDAPUser,u.Name)=?")		//	#1
		.append(" AND u.IsActive='Y'")
		.append(" AND EXISTS (SELECT * FROM AD_Client c WHERE u.AD_Client_ID=c.AD_Client_ID AND c.IsActive='Y')");
		if (!authenticated && appPwd != null) {
			sql.append(" AND (u.Password=? OR u.Password=?)"); // #2/3
		}
		sql.append(" ORDER BY r.AD_Org_ID, r.Name");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, appUser.toUpperCase());
			if (!authenticated && appPwd != null) {
				pstmt.setString(2, appPwd);
				pstmt.setString(3, SecureEngine.encrypt(appPwd));
			}
			rs = pstmt.executeQuery();

			while (rs.next()){
				bean.add(new BeanView(String.valueOf(rs.getInt(1)),
						String.valueOf(rs.getInt(2)),
						rs.getString(3),
						rs.getString(4)));

			} 

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		if(bean.size()>0 && (bean.get(0).getCadena2() == null || bean.get(0).getCadena2().equals("0"))){
			sinRol  = true;
		}
		
		return sinRol;
	}
}
