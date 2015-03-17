/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.Compiere;
import org.compiere.db.CConnection;
import org.compiere.model.MCountry;
import org.compiere.model.MEXMEConfigEC;
import org.compiere.model.MEXMEConfigFE;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMEI18N;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEUser;
import org.compiere.model.MLocation;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MRole;
import org.compiere.model.MSession;
import org.compiere.model.MSystem;
import org.compiere.model.MTree_Base;
import org.compiere.model.ModelValidationEngine;


/**
 *	Login Manager
 *
 *  @author Jorg Janke
 *  @version $Id: Login.java,v 1.11 2006/08/23 01:35:36 mrojas Exp $
 */
public class Login
{
	/**
	 *  Test Init - Set Environment for tests
	 *	@param isClient client session
	 *	@return Context
	 */
	public static Properties initTest (boolean isClient)
	{
	//	logger.entering("Env", "initTest");
		Compiere.startupEnvironment(true);
		//  Test Context
		Properties ctx = Env.getCtx();
		Login login = new Login(ctx);
		KeyNamePair[] roles = login.getRoles(CConnection.get(),
			"System", "System", true);
		//  load role
		if (roles != null && roles.length > 0)
		{
			KeyNamePair[] clients = login.getClients (roles[0]);
			//  load client
			if (clients != null && clients.length > 0)
			{
				KeyNamePair[] orgs = login.getOrgs(clients[0]);
				//  load org
				if (orgs != null && orgs.length > 0)
				{
					//KeyNamePair[] whs = login.getWarehouses(orgs[0], null); //expert: miguel rojas
					//
					login.loadPreferences(orgs[0], null, null, null, null); //expert: miguel rojas
				}
			}
		}
		//
		Env.setContext(ctx, "#Date", "2000-01-01");
	//	logger.exiting("Env", "initTest");
		return ctx;
	}   //  testInit

	/**
	 *  Java Version Test
	 *  @param isClient client connection
	 *  @return true if Java Version is OK
	 */
	public static boolean isJavaOK (boolean isClient)
	{
		// Java System version check
		String jVersion = System.getProperty("java.version");
		if (jVersion.startsWith("1.6")) {
			return true;
		}
		// Warning
		boolean ok = false;
		// if (jVersion.startsWith("1.4")
		// || jVersion.startsWith("1.5.1")) // later/earlier release
		// ok = true;

		// Error Message
		StringBuffer msg = new StringBuffer(50);
		msg.append(System.getProperty("java.vm.name")).append(" - ").append(jVersion);
		if (ok) {
			msg.append("(untested)");
		}
		msg.append("  <>  1.6");
		//
		if (isClient) {
			JOptionPane.showMessageDialog(null, msg.toString(),
				org.compiere.Compiere.getName() + " - Java Version Check",
				ok ? JOptionPane.WARNING_MESSAGE : JOptionPane.ERROR_MESSAGE);
		} else {
			log.severe(msg.toString());
		}
		return ok;
	}   //  isJavaOK


	/**************************************************************************
	 * 	Login
	 * 	@param ctx context
	 */
	public Login (Properties ctx)
	{
		if (ctx == null) {
			throw new IllegalArgumentException("Context missing");
		}
		this.ctx = ctx;
	}	//	Login

	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(Login.class);
	/** Context				*/
	private Properties 		ctx = null;
	/** Connection Profile	*/
	private String			connProfile = null;
	/** Permite saber si el usuario tiene un rol asignado o no */
	private boolean sinRol = false;
	/** CTX_AD_ROLE_ID */
	private static final String CTX_AD_ROLE_ID = "#AD_Role_ID";
	/** CTX_AD_USER_ID */
	private static final String CTX_AD_USER_ID = "#AD_User_ID";

	/**
	 *	(Test) Client Login.
	 *  <p>
	 *  - Get Connection
	 *  - Compare User info
	 *  <p>
	 *  Sets Context with login info
	 * @param cc connection
	 * @param appUser user
	 * @param appPwd password
	 * @param force ignore password
	 * @return  Array of Role KeyNamePair or null if error
	 * The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	protected KeyNamePair[] getRoles (CConnection cc,
		String appUser, String appPwd, boolean force)
	{
		// Establish connection
		DB.setDBTarget(cc);
		Env.setContext(ctx, "#Host", cc.getAppsHost());
		Env.setContext(ctx, "#Database", cc.getDbName());

		KeyNamePair[] retValue = null;
		if (DB.getConnectionRO() == null) {
			log.saveError("NoDatabase", "");
		} else if (appPwd != null) {
			retValue = getRoles(appUser, appPwd, force);
		}
		//
		return retValue;
	}   //  getRoles

	/**
	 *  (Web) Client Login.
	 *  <p>
	 *  Compare User Info
	 *  <p>
	 *  Sets Conext with login info
	 *  @param app_user Principal
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	public KeyNamePair[] getRoles (Principal app_user)
	{
		KeyNamePair[] retValue = null;
		if (app_user != null) {
			// login w/o password as previously authorized
			retValue = getRoles(app_user.getName().toUpperCase(), null, false);
		}
		return retValue;
	}   //  getRoles

	/**
	 *  Client Login.
	 *  <p>
	 *  Compare User Info
	 *  <p>
	 *  Sets Conext with login info
	 *  @param app_user user id
	 *  @param app_pwd password
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	public KeyNamePair[] getRoles (String app_user, String app_pwd)
	{
		return getRoles (app_user, app_pwd, false);
	}   //  login

	/**
	 *  Actual DB login procedure.
	 *  @param appUser user
	 *  @param appPwd password
	 *  @param force ignore password
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	private KeyNamePair[] getRoles (String appUser, String appPwd, boolean force)
	{
		log.info("User=" + appUser);
		// long start = System.currentTimeMillis();
		if (appUser == null) {
			log.warning("No Apps User");
			return null;
		}

		// Authentication
		boolean authenticated = false;
		MSystem system = MSystem.get(ctx);
		if (system.isLDAP()) {
			authenticated = system.isLDAP(appUser, appPwd);
//			if (authenticated) {
//				appPwd = null;
//			}
			// if not authenticated, use AD_User as backup
		} else if (appPwd == null || appPwd.length() == 0) {
			log.warning("No Apps Password");
			return null;
		}

		KeyNamePair[] retValue ;
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		//
		StringBuffer sql = new StringBuffer("SELECT u.AD_User_ID, r.AD_Role_ID, r.Name \n")
			.append("\t, u.ConnectionProfile, r.UserType \n")
			.append("\t, COALESCE(u.Description,u.name) as uname \n")
			.append("\t, IsStaff \n")
			.append("FROM AD_User u \n")
			.append("\tINNER JOIN AD_User_Roles ur ON (u.AD_User_ID=ur.AD_User_ID AND ur.IsActive='Y') \n")
			.append("\tINNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID AND r.IsActive='Y' AND r.isTemplate = 'N') \n")
			.append("WHERE COALESCE(u.LDAPUser,u.Name)=?")		//	#1
			.append("\tAND u.IsActive='Y'")
			.append("\tAND EXISTS (SELECT * FROM AD_Client c WHERE u.AD_Client_ID=c.AD_Client_ID AND c.IsActive='Y') \n");
		if (!authenticated && appPwd != null) {
			sql.append("\tAND (u.Password=? OR u.Password=?) \n"); // #2/3
		}
		sql.append("\tAND r.isTemplate = 'N' \n");
		sql.append(" ORDER BY r.AD_Org_ID, r.Name");
		PreparedStatement pstmt = null;
		ResultSet rs = null; // Expert

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, appUser.toUpperCase());
			if (!authenticated && appPwd != null) {
				pstmt.setString(2, appPwd);
				pstmt.setString(3, SecureEngine.encrypt(appPwd));
			}
			// execute a query
			rs = pstmt.executeQuery();

			if (!rs.next()) { // no record found
				if (force) {
					Env.setContext(ctx, "#AD_User_Name", "System");
					Env.setContext(ctx, CTX_AD_USER_ID, "0");
					Env.setContext(ctx, "#AD_User_Description", "System Forced Login");
					Env.setContext(ctx, "#User_Level", "S  "); // Format 'SCO'
					Env.setContext(ctx, "#User_Client", "0"); // Format c1, c2, ...
					Env.setContext(ctx, "#User_Org", "0"); // Format o1, o2, ...
					Env.setContext(ctx, "#UserType", "US"); // Format o1, o2, ... //Expert: Asignacion Precios
					retValue = new KeyNamePair[] { new KeyNamePair(0, "System Administrator") };
					return retValue;
				} else {
					sinRol  = MEXMEUser.sinRol(appUser, authenticated, appPwd);
					log.saveError("UserPwdError", appUser, false);
					return null;
				}
			}
			Env.setContext(ctx, "#AD_User_Name", appUser);
			Env.setContext(ctx, "#AD_User_FullName", rs.getString("uname"));
			Env.setContext(ctx, CTX_AD_USER_ID, rs.getInt(1));
			Env.setContext(ctx, "#SalesRep_ID", rs.getInt(1));
			Env.setContext(ctx, "#UserType", rs.getString(5)); //Expert: Asignacion Precios
			Env.setContext(ctx, "#IsStaff" , rs.getString(7));
			//
			Ini.setProperty(Ini.P_UID, appUser);
			if (Ini.isPropertyBool(Ini.P_STORE_PWD)) {
				Ini.setProperty(Ini.P_PWD, appPwd);
			}
			connProfile = rs.getString(4); // User Based
			if (connProfile != null) {
				CConnection cc = CConnection.get();
				if (!cc.getConnectionProfile().equals(connProfile)) {
					cc.setConnectionProfile(connProfile);
					Ini.setProperty(Ini.P_CONNECTION, cc.toStringLong());
					Ini.saveProperties(false);
				}
			}

			do // read all roles
			{
				int AD_Role_ID = rs.getInt(2);
				if (AD_Role_ID == 0) {
					Env.setContext(ctx, "#SysAdmin", "Y");
				}
				KeyNamePair p = new KeyNamePair(AD_Role_ID, rs.getString(3));
				list.add(p);
			} while (rs.next());

			 // Caracteristicas del usuario
//            MEXMEUser.getReferences(ctx);//Expert..

		} catch (SQLException ex) {
			log.log(Level.SEVERE, sql.toString(), ex);
			log.saveError("DBLogin", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		//setMedsysContext();
		//
		// Expert..
		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
		log.fine("User=" + appUser + " - roles #" + retValue.length);

		return retValue;
	} // getRoles

	@SuppressWarnings("unused")
	private void setMedsysContext() {
		StringBuffer sql = new StringBuffer();
		try {
			int adUserID = Env.getContextAsInt(ctx, CTX_AD_USER_ID);
			StringBuffer whereClause = new StringBuffer("WHERE AD_User_ID = ? AND IsActive = 'Y'");

			// if user is medical assistant
			sql = new StringBuffer("SELECT EXME_Asistente_ID FROM EXME_Asistente ").append(whereClause);
			setContext("#EXME_Asistente_ID", sql.toString(), adUserID);

			// if user is physician
			sql = new StringBuffer("SELECT EXME_Medico_ID FROM EXME_Medico_Org ").append(whereClause);
			// lhernandez. el usuario se busca en EXME_Medico_Org. 23/09/2010
			setContext("#EXME_Medico_ID", sql.toString(), adUserID);

			// if user is physician substitute
			sql = new StringBuffer("SELECT ms.Substitute_ID FROM EXME_Medico_Sust ms ").append(
					" INNER JOIN EXME_Medico m ON ( ms.Substitute_ID = m.EXME_Medico_ID ").append(
					" AND m.AD_User_ID = ? ) WHERE ms.IsActive = 'Y'");
			setContext("#Substitute_ID", sql.toString(), adUserID);

			// if user is nurse
			sql = new StringBuffer("SELECT EXME_Enfermeria_ID FROM EXME_Enfermeria ").append(whereClause);
			setContext("#EXME_Enfermeria_ID", sql.toString(), adUserID);

			// if user is pharmacist
			sql = new StringBuffer("SELECT EXME_Pharmacist_ID FROM EXME_Pharmacist ").append(whereClause);
			setContext("#EXME_Pharmacist_ID", sql.toString(), adUserID);
			//
		} catch (Exception ex) {
			log.log(Level.SEVERE, sql.toString(), ex);
			log.saveError("DBLogin", ex);
		}
	}

	private void setContext(String key, String sql, Object... params) {
		Env.setContext(ctx, key, DB.getSQLValue(null, sql, params));
		log.fine(key + ": " + Env.getContextAsInt(ctx, key));
	}

	/**************************************************************************
	 *  Load Clients.
	 *  <p>
	 *  Sets Role info in context and loads its clients
	 *  @param  role    role information
	 *  @return list of valid client KeyNodePairs or null if in error
	 */
	public KeyNamePair[] getClients (KeyNamePair role)
	{
		if (role == null) {
			throw new IllegalArgumentException("Role missing");
		}

	//	s_log.fine("loadClients - Role: " + role.toStringX());

		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		String sql = "SELECT DISTINCT r.UserLevel, r.ConnectionProfile, "	//	1/2
			+ " c.AD_Client_ID,c.Name "						//	3/4
			+ "FROM AD_Role r"
			+ " INNER JOIN AD_Client c ON (r.AD_Client_ID=c.AD_Client_ID) "
			+ "WHERE r.AD_Role_ID=?"		//	#1
			+ " AND r.IsActive='Y' AND c.IsActive='Y'";

		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		//	get Role details
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, role.getKey());
			rs = pstmt.executeQuery();

			if (!rs.next())
			{
				DB.close(rs, pstmt);
				log.log(Level.SEVERE, "No Clients for Role: " + role.toStringX());
				return null;
			}

			//  Role Info
			Env.setContext(ctx, CTX_AD_ROLE_ID, role.getKey());
			Env.setContext(ctx, "#AD_Role_Name", role.getName());
			Ini.setProperty(Ini.P_ROLE, role.getName());
			//	User Level
			Env.setContext(ctx, "#User_Level", rs.getString(1));  	//	Format 'SCO'

			//Guarda el Rol en el contexto y verifica si esta marcada la seguridad en el mtto de prfil para dicho rol.
			MRole r = new MRole(ctx, role.getKey(), null);
            Env.setContext(ctx, "#IsNeedSecurity", r.isNeedSecurity());

			//	ConnectionProfile
			CConnection cc = CConnection.get();
			if (connProfile == null)			//	No User Based
			{
				connProfile = rs.getString(2);	//	Role Based
				if (connProfile != null
					&& !cc.getConnectionProfile().equals(connProfile))
				{
					cc.setConnectionProfile(connProfile);
					Ini.setProperty(Ini.P_CONNECTION, cc.toStringLong());
					Ini.saveProperties(false);
				}
			}

			//  load Clients
			do
			{
				int AD_Client_ID = rs.getInt(3);
				String Name = rs.getString(4);
				KeyNamePair p = new KeyNamePair(AD_Client_ID, Name);
				list.add(p);
			}
			while (rs.next());

			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("Role: " + role.toStringX() + " - clients #" + retValue.length);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql, ex);
			retValue = null;
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return retValue;
	}   //  getClients

	/**
	 *  Load Organizations.
	 *  <p>
	 *  Sets Client info in context and loads its organization, the role has access to
	 *  @param  client    client information
	 *  @return list of valid Org KeyNodePairs or null if in error
	 */
	public KeyNamePair[] getOrgs (KeyNamePair client)
	{
		if (client == null) {
			throw new IllegalArgumentException("Client missing");
		}
		if (Env.getContext(ctx,CTX_AD_ROLE_ID).length() == 0) {	//	could be number 0
			throw new UnsupportedOperationException("Missing Context #AD_Role_ID");
		}

		int AD_Role_ID = Env.getContextAsInt(ctx,CTX_AD_ROLE_ID);
		int AD_User_ID = Env.getContextAsInt(ctx, CTX_AD_USER_ID);
	//	s_log.fine("Client: " + client.toStringX() + ", AD_Role_ID=" + AD_Role_ID);

		//	get Client details for role
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		//
		String sql = "SELECT o.AD_Org_ID,o.Name,o.IsSummary "	//	1..3
			+ "FROM AD_Role r, AD_Client c"
			+ " INNER JOIN AD_Org o ON (c.AD_Client_ID=o.AD_Client_ID OR o.AD_Org_ID=0) "
			+ " LEFT JOIN AD_OrgInfo oi ON(c.AD_Client_ID=oi.AD_Client_ID AND o.AD_Org_ID=oi.AD_Org_ID) " //expert : miguel rojas : no sera posible logearse a una organizacion que tenga org padre
			+ "WHERE r.AD_Role_ID=?" 	//	#1
			+ " AND c.AD_Client_ID=?"	//	#2
			+ " AND o.IsActive='Y'"
			+ " AND oi.Parent_Org_ID IS NULL"  //expert : miguel rojas
			+ " AND (r.IsAccessAllOrgs='Y' "
				+ "OR (r.IsUseUserOrgAccess='N' AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_Role_OrgAccess ra "
					+ "WHERE ra.AD_Role_ID=r.AD_Role_ID AND ra.IsActive='Y')) "
				+ "OR (r.IsUseUserOrgAccess='Y' AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_User_OrgAccess ua "
					+ "WHERE ua.AD_User_ID=? AND ua.IsActive='Y'))"		//	#3
				+ ") "
			+ "ORDER BY o.Name";
		//
		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		MRole role = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Role_ID);
			pstmt.setInt(2, client.getKey());
			pstmt.setInt(3, AD_User_ID);
			rs = pstmt.executeQuery();
			//  load Orgs
			while (rs.next())
			{
				int AD_Org_ID = rs.getInt(1);
				String Name = rs.getString(2);
				boolean summary = "Y".equals(rs.getString(3));
				if (summary)
				{
					if (role == null) {
						role = MRole.get(ctx, AD_Role_ID);
					}
					getOrgsAddSummary (list, AD_Org_ID, Name, role);
				}
				else
				{
					KeyNamePair p = new KeyNamePair(AD_Org_ID, Name);
					if (!list.contains(p)) {
						list.add(p);
					}
				}
			}
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("Client: " + client.toStringX()
				+ ", AD_Role_ID=" + AD_Role_ID
				+ ", AD_User_ID=" + AD_User_ID
				+ " - orgs #" + retValue.length);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql, ex);
			retValue = null;
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		//	No Orgs
		if (retValue == null || retValue.length == 0)
		{
			log.log(Level.WARNING, "No Org for Client: " + client.toStringX()
				+ ", AD_Role_ID=" + AD_Role_ID
				+ ", AD_User_ID=" + AD_User_ID);
			return null;
		}

		//  Client Info
		Env.setContext(ctx, "#AD_Client_ID", client.getKey());
		Env.setContext(ctx, "#AD_Client_Name", client.getName());
		Ini.setProperty(Ini.P_CLIENT, client.getName());
		return retValue;
	}   //  getOrgs

	/**
	 * 	Get Orgs - Add Summary Org
	 *	@param list list
	 *	@param Summary_Org_ID summary org
	 *	@param Summary_Name name
	 *	@param role role
	 *	@see org.compiere.model.MRole#loadOrgAccessAdd
	 */
	private void getOrgsAddSummary (List<KeyNamePair> list, int Summary_Org_ID,
		String Summary_Name, MRole role)
	{
		if (role == null)
		{
			log.warning("Summary Org=" + Summary_Name + "(" + Summary_Org_ID + ") - No Role");
			return;
		}
		//	Do we look for trees?
		if (role.getAD_Tree_Org_ID() == 0)
		{
			log.config("Summary Org=" + Summary_Name + "(" + Summary_Org_ID + ") - No Org Tree: " + role);
			return;
		}
		//	Summary Org - Get Dependents
		MTree_Base tree = MTree_Base.get(ctx, role.getAD_Tree_Org_ID(), null);
		String sql =  "SELECT AD_Client_ID, AD_Org_ID, Name, IsSummary FROM AD_Org "
			+ "WHERE IsActive='Y' AND AD_Org_ID IN (SELECT Node_ID FROM "
			+ tree.getNodeTableName()
			+ " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive='Y') "
			+ "ORDER BY Name";
		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, tree.getAD_Tree_ID());
			pstmt.setInt (2, Summary_Org_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				//int AD_Client_ID = rs.getInt(1);
				int AD_Org_ID = rs.getInt(2);
				String Name = rs.getString(3);
				boolean summary = "Y".equals(rs.getString(4));
				//
				if (summary) {
					getOrgsAddSummary(list, AD_Org_ID, Name, role);
				} else {
					KeyNamePair p = new KeyNamePair(AD_Org_ID, Name);
					if (!list.contains(p)) {
						list.add(p);
					}
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
	}	//	getOrgAddSummary


	/**
	 *  Load Warehouses
	 * @param org organization
	 * @return Array of Warehouse Info
	 */
	public KeyNamePair[] getWarehouses (KeyNamePair org, KeyNamePair estServ)//expert
	{
		if (org == null) {
			throw new IllegalArgumentException("Org missing");
		}

	//	s_log.info("loadWarehouses - Org: " + org.toStringX());

		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		//Expert.. miguel rojas
		String sql = null;

		if(estServ == null) {
			sql = "SELECT M_Warehouse_ID, Name FROM M_Warehouse "
				+ "WHERE AD_Org_ID=? AND IsActive='Y' "
				+ "ORDER BY Name";
		} else {
			sql = "SELECT w.M_Warehouse_ID, w.Name "
			      + "FROM M_Warehouse w, EXME_EstServAlm ew "
			      + "WHERE ew.AD_Org_ID = ? "
			      + "AND ew.EXME_EstServ_ID = ? "
			      + "AND ew.M_Warehouse_ID = w.M_Warehouse_ID "
			      + "AND ew.IsActive = 'Y' "
			      + "ORDER BY IsDefault DESC, w.Name ASC";
		}
		//Expert: miguel rojas

		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, org.getKey());
			//expert: miguel rojas
			if(estServ != null) {
				pstmt.setInt(2, estServ.getKey());
			}
			//expert: miguel rojas
			rs = pstmt.executeQuery();

			if (!rs.next())
			{
				DB.close(rs, pstmt);
				log.info("No Warehouses for Org: " + org.toStringX());
				return null;
			}

			//  load Warehousess
			do
			{
				int AD_Warehouse_ID = rs.getInt(1);
				String Name = rs.getString(2);
				KeyNamePair p = new KeyNamePair(AD_Warehouse_ID, Name);
				list.add(p);
			}
			while (rs.next());

			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("Org: " + org.toStringX()
				+ " - warehouses #" + retValue.length);
		}
		catch (SQLException ex) {
			log.log(Level.SEVERE, "getWarehouses", ex);
			retValue = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}   //  getWarehouses

	/**
	 * 	Validate Login
	 *	@param org log-in org
	 *	@return error message
	 */
	public String validateLogin (KeyNamePair org)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		int AD_Org_ID = org.getKey();
		int AD_Role_ID = Env.getAD_Role_ID(ctx);
		int AD_User_ID = Env.getAD_User_ID(ctx);
		String error = ModelValidationEngine.get().loginComplete(AD_Client_ID, AD_Org_ID, AD_Role_ID, AD_User_ID);
		if (error != null && error.length() > 0)
		{
			log.severe("Refused: " + error);
			return error;
		}
		return null;
	}	//	validateLogin

	/**
	 *	Load Preferences into Context for selected client.
	 *  <p>
	 *  Sets Org info in context and loads relevant field from
	 *	- AD_Client/Info,
	 *  - C_AcctSchema,
	 *  - C_AcctSchema_Elements
	 *	- AD_Preference
	 *  <p>
	 *  Assumes that the context is set for #AD_Client_ID, #AD_User_ID, #AD_Role_ID
	 *
	 *  @param  org    org information
	 *  @param  warehouse   optional warehouse information
	 *  @param  timestamp   optional date
	 *  @param  printerName optional printer info
	 *  @return AD_Message of error (NoValidAcctInfo) or ""
	 */
	public String loadPreferences (KeyNamePair org,
		KeyNamePair estServ, KeyNamePair warehouse,
		java.sql.Timestamp timestamp, String printerName)//expert
	{
		log.info("Org: " + org.toStringX());

		if (ctx == null || org == null) {
			throw new IllegalArgumentException("Required parameter missing");
		}
		if (Env.getContext(ctx, "#AD_Client_ID").length() == 0) {
			throw new UnsupportedOperationException("Missing Context #AD_Client_ID");
		}
		if (Env.getContext(ctx, CTX_AD_USER_ID).length() == 0) {
			throw new UnsupportedOperationException("Missing Context #AD_User_ID");
		}
		if (Env.getContext(ctx, CTX_AD_ROLE_ID).length() == 0) {
			throw new UnsupportedOperationException("Missing Context #AD_Role_ID");
		}
		//  Org Info - assumes that it is valid
		Env.setContext(ctx, "#AD_Org_ID", org.getKey());
		Env.setContext(ctx, "#AD_Org_Name", org.getName());
		Ini.setProperty(Ini.P_ORG, org.getName());

		if (estServ == null) {
			Env.setContext(ctx, "#EXME_EstServ_ID", -1);
			Env.setContext(ctx, "#EXME_EstServ_Name", "");

		} else {

			Env.setContext(ctx, "#EXME_EstServ_ID", estServ.getKey());
			MEXMEEstServ es = new MEXMEEstServ(ctx, Env.getContextAsInt(ctx, "#EXME_EstServ_ID"), null);
			if (es != null && es.getEXME_EstServ_ID() != 0) {
				Env.setContext(ctx, "#TipoArea", es.getTipoArea());
				Env.setContext(ctx, "#AD_OrgTrx_ID", es.getAD_OrgTrx_ID());
				setCtxConfig();// Lama
			}
			Env.setContext(ctx, "#EXME_EstServ_Name", estServ.getName().equals("") ? es.getName() : estServ.getName());

		}

		loadSecurity();
		final int countryID ;
		// current organization info
		if (Env.getAD_Org_ID(ctx) > 0) {
			MOrgInfo orgInfo = MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx));
			Env.setContext(ctx, Env.TIMEZONE, orgInfo.getTimeZone());
			
			// Country
			if (orgInfo.getC_Location_ID() > 0) {
				countryID = new MLocation(ctx, orgInfo.getC_Location_ID(), null).getC_Country_ID();
			} else {
				countryID=  MCountry.getDefault(ctx).getC_Country_ID();
			}
		} else {
			Env.setContext(ctx, Env.TIMEZONE, Env.getSystemTimezone());
			// Country
			countryID = MCountry.getDefault(ctx).getC_Country_ID();
		}
		// Lama: pais y codigo
		Env.setContext(ctx, "#C_Country_ID",countryID);
		Env.setContext(ctx, "#C_Country_Code",DB.getSQLValueString(null, "SELECT CountryCode FROM C_Country WHERE C_Country_ID=?", countryID));

		//  Warehouse Info
		if (warehouse == null) {
			Env.setContext(ctx, "#M_Warehouse_ID", -1);
			Ini.setProperty(Ini.P_WAREHOUSE, "");
		} else {
			Env.setContext(ctx, "#M_Warehouse_ID", warehouse.getKey());
			Env.setContext(ctx, "#M_Warehouse_Name", warehouse.getName());
			Ini.setProperty(Ini.P_WAREHOUSE, warehouse.getName());
		}

		//	Date (default today)
		long today = DB.getTimestampForOrg(ctx).getTime();
		if (timestamp != null){
			today = timestamp.getTime();
		}
		Env.setContext(ctx, "#Date", new java.sql.Timestamp(today));

		//	Optional Printer
//		if (printerName == null){
//			printerName = "";
//		}
		Env.setContext(ctx, "#Printer", printerName == null ? "" : printerName);
		Ini.setProperty(Ini.P_PRINTER, printerName == null ? "" : printerName);

		//	Load Role Info
		MRole.getDefault(ctx, true);

		//	Other
		Env.setAutoCommit(ctx, Ini.isPropertyBool(Ini.P_A_COMMIT));
		Env.setAutoNew(ctx, Ini.isPropertyBool(Ini.P_A_NEW));
		if (MRole.getDefault(ctx, false).isShowAcct()) {
			Env.setContext(ctx, "#ShowAcct", Ini.getProperty(Ini.P_SHOW_ACCT));
		} else {
			Env.setContext(ctx, "#ShowAcct", "N");
		}
		Env.setContext(ctx, "#ShowTrl", Ini.getProperty(Ini.P_SHOW_TRL));
		Env.setContext(ctx, "#ShowAdvanced", Ini.getProperty(Ini.P_SHOW_ADVANCED));

		String retValue = "";
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		//int AD_Org_ID =  org.getKey();
		//int AD_User_ID =  Env.getContextAsInt(m_ctx, ctx_AD_User_ID);
		int AD_Role_ID =  Env.getContextAsInt(ctx, CTX_AD_ROLE_ID);

		//	Other Settings
		Env.setContext(ctx, "#YYYY", "Y");
		Env.setContext(ctx, "#StdPrecision", 2);

		//	AccountSchema Info (System)
		//  Expert: Proyecto #102 Post,Cost And Price
		loadDefaultAcctSchema ();

		//  AccountSchema Info (first)
		StringBuilder sql = new StringBuilder(" SELECT * ")
		.append(" FROM C_AcctSchema a, AD_ClientInfo c ")
		.append(" WHERE a.C_AcctSchema_ID = c.C_AcctSchema1_ID ")
		.append(" AND c.AD_Client_ID=? ");
		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		try
		{
			int C_AcctSchema_ID = 0;
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Accounting Info
				C_AcctSchema_ID = rs.getInt("C_AcctSchema_ID");
				Env.setContext(ctx, "$C_AcctSchema_ID", C_AcctSchema_ID);
				Env.setContext(ctx, "$C_Currency_ID", rs.getInt("C_Currency_ID"));
				Env.setContext(ctx, "$HasAlias", rs.getString("HasAlias"));

			} else {
				// No Warning for System
				if (AD_Role_ID != 0) {
					retValue = "NoValidAcctInfo";
				}
			}
			DB.close(rs,pstmt);

			//	Accounting Elements
			sql = new StringBuilder("SELECT ElementType ")
				.append("FROM C_AcctSchema_Element ")
				.append("WHERE C_AcctSchema_ID=?")
				.append(" AND IsActive='Y'");
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_AcctSchema_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Env.setContext(ctx, "$Element_" + rs.getString("ElementType"), "Y");
			}
			DB.close(rs,pstmt);

			//	This reads all relevant window neutral defaults
			//	overwriting superseeded ones.  Window specific is read in Mainain
			sql = new StringBuilder("SELECT Attribute, Value, AD_Window_ID ")
			.append("FROM AD_Preference ")
			.append("WHERE AD_Client_ID IN (0, @#AD_Client_ID@)")
			.append(" AND AD_Org_ID IN (0, @#AD_Org_ID@)")
			.append(" AND (AD_User_ID IS NULL OR AD_User_ID=0 OR AD_User_ID=@#AD_User_ID@)")
			.append(" AND IsActive='Y' ")
			.append( "ORDER BY Attribute, AD_Client_ID, AD_User_ID DESC, AD_Org_ID");
				//	the last one overwrites - System - Client - User - Org - Window
			sql = new StringBuilder(Env.parseContext(ctx, 0, sql.toString(), false));
			if (sql.length() == 0) {
				log.log(Level.SEVERE, "loadPreferences - Missing Environment");
			} else {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int AD_Window_ID = rs.getInt(3);
					String at = "";
					if (rs.wasNull()) {
						at = "P|" + rs.getString(1);
					} else {
						at = "P" + AD_Window_ID + "|" + rs.getString(1);
					}
					String va = rs.getString(2);
					Env.setContext(ctx, at, va);
				}
				DB.close(rs,pstmt);
			}

			//	Default Values
			log.info("Default Values ...");
			sql = new StringBuilder("SELECT t.TableName, c.ColumnName ")
			.append("FROM AD_Column c ")
			.append(" INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) ")
			.append("WHERE c.IsKey='Y' AND t.IsActive='Y'")
			.append(" AND EXISTS (SELECT * FROM AD_Column cc ")
			.append(" WHERE ColumnName = 'IsDefault' AND t.AD_Table_ID=cc.AD_Table_ID AND cc.IsActive='Y')");
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				loadDefault(rs.getString(1), rs.getString(2));
			}
			DB.close(rs,pstmt);

		} catch (SQLException e) {
			log.log(Level.SEVERE, "loadPreferences", e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		Ini.saveProperties(Ini.isClient());

		// application
		Env.setContext(
				ctx,
				"#ECSURL",
				new StringBuilder("http://").append(CConnection.get().getAppsHost()).append(":")
						.append(Utilerias.getPropertiesFromEnv("COMPIERE_WEB_PORT")).append("/eCareSoftWeb").toString());
		setConfigCountry();
		// Roles por cliente/organizacion
		MEXMEUser.getReferences(ctx);//Expert..
		return retValue;
	}	//	loadPreferences

	/**
	 * Carga las configuraciones del pais que esta en el contexto al contexto.
	 */
	private void setConfigCountry() {
		MEXMEI18N conf = MEXMEI18N.getFromCountry(ctx, Env.getC_Country_ID(ctx), null);
		if (conf != null) {
			Env.setContext(ctx, "#isSurtidoInterno",conf.isSurtidoInterno() ? "Y" : "N");
		}
	}

	/**
	 * Configuraciones para Ingreso de Pacientes
	 * @author Lorena Lama
	 * Created 23/03/2010
	 */
	private void setCtxConfig() {
		org.compiere.model.MEXMEConfigEspecial.setCtx(ctx);
		org.compiere.model.MEXMEConfigDer.setCtx(ctx);
		MEXMEConfigEC.setCtx(ctx);
		MEXMEConfigPre.setCtx(ctx);
		MEXMEConfigFE.setCtx(ctx);
		MEXMEMejoras.setCtx(ctx);
	}

	private void loadSecurity(){
		//validacion de seguridad para usuarios
		SimpleDateFormat formato = Constantes.sdfFecha(ctx);
		String cond = null;
		String or = " OR CreatedBy = " + Env.getAD_User_ID(ctx) + " and TO_CHAR(Created, '"+ formato.toPattern() +"') = "
					+ DB.TO_STRING(formato.format(DB.getTimestampForOrg(ctx)));

		if (Env.getAD_User_ID(ctx) == 0 || Env.getAD_User_ID(ctx) == 100) {
			String and = " OR AD_USER_ID IN (0,100) ";
			cond = and + or;
		} else {
			cond = or;
		}

		Env.setContext(ctx, "#ShowSystem", cond);

		if(Env.getAD_User_ID(ctx) <= 100) {
			Env.setContext(ctx, "#ShowSuperUser", "Y");
		}

		if("N".equals(Env.getContext(ctx, "#IsStaff"))) {
			Env.setContext(ctx, "#ShowStaff", "AND AD_User.IsStaff = 'N'");
		} else {
			Env.setContext(ctx, "#ShowStaff", "AND AD_User.IsStaff in ('N', 'Y')");
		}

		//fin gderreza
	}

	/**
	 *	Load Default Value for Table into Context.
	 *  @param TableName table name
	 *  @param ColumnName column name
	 */
	private void loadDefault (String TableName, String ColumnName)
	{
		if (TableName.startsWith("AD_Window")
			|| TableName.startsWith("AD_PrintFormat")
			|| TableName.startsWith("AD_Workflow") ) {
			return;
		}
		String value = null;
		//
		String sql = "SELECT " + ColumnName + " FROM " + TableName	//	most specific first
			+ " WHERE IsDefault='Y' AND IsActive='Y' ORDER BY AD_Client_ID DESC, AD_Org_ID DESC";
		sql = MRole.getDefault(ctx, false).addAccessSQL(sql,
			TableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				value = rs.getString(1);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, TableName + " (" + sql + ")", e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}

		//	Set Context Value
		if (value != null && value.length() != 0)
		{
			if ("C_DocType".equals(TableName)) {
				Env.setContext(ctx, "#C_DocTypeTarget_ID", value);
			}else{
				if (("M_PriceList_ID".equals(ColumnName) && Env.getM_PriceList_ID(ctx) < 0) ||
						!("M_PriceList_ID".equals(ColumnName)))	{
					Env.setContext(ctx, "#" + ColumnName, value);
				}
			}
		}
	}	//	loadDefault



	//expert: miguel angel rojas
	/**
	 *  Cargar extaciones de servicio
	 *
	 * @param ctx context
	 * @param roler perfil
	 * @return un arreglo con las estaciones de servicio relativas al rol
	 */
	public KeyNamePair[] getEstServ(KeyNamePair org, KeyNamePair role)
	{
		if (ctx == null || role == null) {
			throw new IllegalArgumentException("DB.loadWarehouses - required parameter missing");
		}

	//	s_log.info("loadWarehouses - Org: " + org.toStringX());

		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		String sql = "SELECT e.EXME_EstServ_ID, e.Name FROM EXME_EstServ e, EXME_RoleEstServ r "
			+ "WHERE r.AD_Role_ID = ? AND r.IsActive='Y' "
			+ "AND r.AD_Org_ID = ? "
			+ "AND e.AD_Org_ID = ? "
			+ "AND r.EXME_EstServ_ID = e.EXME_EstServ_ID "
			+ "AND e.IsActive = 'Y' "
			+ "ORDER BY Name";
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, role.getKey());
			pstmt.setInt(2, org.getKey());
			pstmt.setInt(3, org.getKey());
			rs = pstmt.executeQuery();

			if (!rs.next())
			{
				DB.close(rs, pstmt);
				log.log(Level.WARNING, "loadEstServ - No existen estaciones de servicio para el prefil: " + role.toStringX());
				return null;
			}

			//  load Warehousess
			do
			{
				int EXME_EstServ_ID = rs.getInt(1);
				String Name = rs.getString(2);
				KeyNamePair p = new KeyNamePair(EXME_EstServ_ID, Name);
				list.add(p);
			}
			while (rs.next());


			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, "loadEstServ", (Exception)ex);
			retValue = null;
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return retValue;
	}   //  loadEstServ
	//expert: miguel angel rojas

	//expert: miguel angel rojas
	/**
	 * Devolvemos el Area de Ingreso relacionado a una estacion de servicio
	 * en particular
	 *
	 * @param estServID Estacion de Servicio
	 *
	 * @return String Tipo de Area de la EstServID
	 *
	 * @throws Exception en caso de ocurrir un error al procesar la
	 * consulta.
	 */
	public String getTipoAreaEstServ(long estServID) throws Exception {
		/*String tipoArea = null;
		String sql = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			sql = "SELECT tipoarea " +
			      "FROM EXME_EstServ es " +
			      "WHERE es.isActive = 'Y' AND es.EXME_EstServ_ID = ? ";

			//System.out.println("SQL: " + sql.toString());
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setLong(1, estServID);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				tipoArea = rs.getString("tipoarea");
			} else {
				throw new Exception("No existe registro con la Estacion de Servicio " + estServID);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		return tipoArea;
		*/
		return MEXMEEstServ.getTipoAreaEstServ(ctx,estServID);
	}

	/**
	 * Si la sesion expir&oacute;n debemos disminuir el
	 * n&uacute;mero de sesiones activas y marcarla como
	 * procesada en la bit&aacute;cora.
	 *
	 * @param sesionId La sesi&oacute;n que est&aacute;
	 * expirando.
	 */
	public void expiroSesion(String sesionId) {
	    try {
            //el modo de salida es timeout, tenemos que actualizar las sesiones en bd
            //int adClientId = Env.getAD_Client_ID(m_ctx);
//            int adUserId   = Env.getAD_User_ID(m_ctx);

            //recuperamos la sesion
            MSession sesion = MSession.get(ctx, false);

            if(sesion != null) {
                sesion.logout();
            }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//expert: miguel angel rojas


	/**
	 * 	Batch Login using Ini values
	 * 	<code>
		Compiere.startup(true);
		Ini.setProperty(Ini.P_UID,"SuperUser");
		Ini.setProperty(Ini.P_PWD,"System");
		Ini.setProperty(Ini.P_ROLE,"GardenAdmin");
		Ini.setProperty(Ini.P_CLIENT, "Garden World");
		Ini.setProperty(Ini.P_ORG,"HQ");
		Ini.setProperty(Ini.P_WAREHOUSE,"HQ");
		Ini.setProperty(Ini.P_LANGUAGE,"English");
		Ini.setProperty(Ini.P_PRINTER,"MyPrinter");
		Login login = new Login(Env.getCtx());
		login.batchLogin();
	 * 	</code>
	 * 	@param loginDate optional login date
	 * 	@return true if logged in using Ini values
	 */
	public boolean batchLogin(java.sql.Timestamp loginDate)
	{
		//	User Login
		String uid = Ini.getProperty(Ini.P_UID);
		String pwd = Ini.getProperty(Ini.P_PWD);
		KeyNamePair[] roles = getRoles (uid, pwd);
		if (roles == null || roles.length == 0)
		{
			log.severe("User/Password invalid: " + uid);
			return false;
		}
		log.info("User: " + uid);

		//	Role
		String role = Ini.getProperty(Ini.P_ROLE);
		KeyNamePair rolePP = null;
		for (int i = 0; i < roles.length; i++)
		{
			KeyNamePair pair = roles[i];
			if (pair.getName().trim().equalsIgnoreCase(role.trim()))
			{
				rolePP = pair;
				break;
			}
		}
		if (rolePP == null)
		{
			log.severe("Role invalid: " + role);
			for (int i = 0; i < roles.length; i++) {
				log.info("Option: " + roles[i]);
			}
			return false;
		}
		log.info("Role: " + role);

		//	Clients
		String client = Ini.getProperty(Ini.P_CLIENT);
		KeyNamePair[] clients = getClients(rolePP);
		if (clients == null || clients.length == 0)
		{
			log.severe("No Clients for Role: " + role);
			return false;
		}
		KeyNamePair clientPP = null;
		for (int i = 0; i < clients.length; i++)
		{
			KeyNamePair pair = clients[i];
			if (pair.getName().equalsIgnoreCase(client))
			{
				clientPP = pair;
				break;
			}
		}
		if (clientPP == null)
		{
			log.severe("Client invalid: " + client);
			for (int i = 0; i < clients.length; i++) {
				log.info("Option: " + clients[i]);
			}
			return false;
		}

		//	Organization
		String org = Ini.getProperty(Ini.P_ORG);
		KeyNamePair[] orgs = getOrgs(clientPP);
		if (orgs == null || orgs.length == 0)
		{
			log.severe("No Orgs for Client: " + client);
			return false;
		}
		KeyNamePair orgPP = null;
		for (int i = 0; i < orgs.length; i++)
		{
			KeyNamePair pair = orgs[i];
			if (pair.getName().equalsIgnoreCase(org))
			{
				orgPP = pair;
				break;
			}
		}
		if (orgPP == null)
		{
			log.severe("Org invalid: " + org);
			for (int i = 0; i < orgs.length; i++) {
				log.info("Option: " + orgs[i]);
			}
			return false;
		}
		String error = validateLogin(orgPP);
		if (error != null && error.length() > 0) {
			return false;
		}


		// service station
		KeyNamePair ssPP = null;
		String ss = Ini.getProperty("#EXME_EstServ_ID");
		KeyNamePair[] stations = getEstServ(orgPP, rolePP);
		if(ss == null || stations == null || stations.length == 0) {
			stations = new KeyNamePair[0];

			log.severe("No service stations for org : " + org);
		} else {

			for (int i = 0; i < stations.length; i++) {
				KeyNamePair pair = stations[i];
				if (pair.getName().equalsIgnoreCase(ss)) {
					ssPP = pair;
					break;
				}
			}

			if (ssPP == null) {
				log.severe("Service station invalid: " + ss);
				for (int i = 0; i < stations.length; i++) {
					log.info("Option: " + stations[i]);
				}
			}
		}

		//	Warehouse
		String wh = Ini.getProperty(Ini.P_WAREHOUSE);
		KeyNamePair[] whs = getWarehouses(orgPP, ssPP); // expert : miguel rojas
		if (whs == null || whs.length == 0)
		{
			whs = new KeyNamePair[0];

			log.severe("No Warehouses for Org: " + org);
			//return false;
		}
		KeyNamePair whPP = null;
		for (int i = 0; i < whs.length; i++)
		{
			KeyNamePair pair = whs[i];
			if (pair.getName().equalsIgnoreCase(wh))
			{
				whPP = pair;
				break;
			}
		}
		if (whPP == null)
		{
			log.severe("Warehouse invalid: " + wh);
			for (int i = 0; i < whs.length; i++) {
				log.info("Option: " + whs[i]);
			}
			//return false;
		}

		//	Language
		String langName = Ini.getProperty(Ini.P_LANGUAGE);
		Language language = Language.getLanguage(langName);
		Language.setLoginLanguage(language);
		Env.verifyLanguage (ctx, language);
		Env.setContext(ctx, Env.LANGUAGE, language.getAD_Language());
		Locale loc = language.getLocale();
		Locale.setDefault(loc);
		Msg.getMsg(ctx, "0");

		//	Preferences
		String printerName = Ini.getProperty(Ini.P_PRINTER);
		// if (loginDate == null) {
		// loginDate = new java.sql.Timestamp(System.currentTimeMillis());
		// }
		loadPreferences(orgPP, ssPP, whPP, //
				loginDate == null ? new java.sql.Timestamp(System.currentTimeMillis()) : loginDate, //
				printerName);
		//
		log.info("complete");
		return true;
	}	//	batchLogin

	/**
	 * 	Batch Login with system date
	 *	@return true if logged in
	 */
	public boolean batchLogin()
	{
		return batchLogin(new java.sql.Timestamp (System.currentTimeMillis()));
	}	//	batchLogin

	/**
	 * 	Get SSO Principal
	 *	@return principal
	 */
	public Principal getPrincipal()
	{
		return null;
	}	//	getPrincipal


	/**
	 * Default AcctSchema
	 * Expert: #Post,Cost And Price
	 */
	private void loadDefaultAcctSchema ()
	{
		//
		StringBuilder sql = new StringBuilder(" SELECT * ")
		.append(" FROM C_AcctSchema a, AD_ClientInfo c ")
		.append(" WHERE a.C_AcctSchema_ID = c.C_AcctSchema1_ID ")
		.append(" AND c.AD_Client_ID = 0 ");
		PreparedStatement pstmt = null;
        ResultSet rs = null; //Expert
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Accounting Info
				Env.setContext(ctx, "$C_DefaultAcctSchema_ID", rs.getInt("C_AcctSchema_ID"));
			} else {
				Env.setContext(ctx, "$C_DefaultAcctSchema_ID", 0);
			}

		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}	//	loadDefault

	public boolean isSinRol() {
		return sinRol;
	}

	public void setSinRol(boolean sinRol) {
		this.sinRol = sinRol;
	}
}	//	Login
