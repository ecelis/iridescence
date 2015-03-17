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
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trace;
import org.compiere.wf.MWorkflow;
import org.compiere.wf.MWorkflowAccess;
/**
 *	Role Model.
 *	Includes AD_User runtime info for Personal Access
 *	The class is final, so that you cannot overwrite the security rules.
 *
 *  @author Jorg Janke
 *  @version $Id: MRole.java,v 1.5 2006/08/09 16:38:47 jjanke Exp $
 */
public final class MRole extends X_AD_Role
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -576367531146447431L;
	// to save a little memory when concatenating separator
	private static final String DASH = " - ";
	// to obtain the table access level
	private static final String accessLevel = 
		"SELECT COUNT(*) FROM AD_Table WHERE AD_Table_ID = ? AND AccessLevel = '9'"; 
	
	
	/**
	 * 	Get Default (Client) Role
	 *	@return role
	 */
	public static MRole getDefault ()
	{
		//if (s_defaultRole == null && Ini.isClient())
			return getDefault (Env.getCtx(), false);
		//return s_defaultRole;
	}	//	getDefault

	/**
	 * 	Get/Set Default Role.
	 * 	@param ctx context
	 * 	@param reload if true forces load
	 *	@return role
	 *	@see org.compiere.util.Login#loadPreferences(KeyNamePair, KeyNamePair, java.sql.Timestamp, String)
	 */
	public static MRole getDefault (final Properties ctx, final boolean reload)
	{
		int AD_Role_ID = Env.getContextAsInt(ctx, "#AD_Role_ID");
		int AD_User_ID = Env.getContextAsInt(ctx, "#AD_User_ID");
//		if (!Ini.isClient())	//	none for Server
//			AD_User_ID = 0;
		MRole defaultRole = getDefaultRole(); 
		if (reload || defaultRole == null)
		{
			defaultRole = get (ctx, AD_Role_ID, AD_User_ID, reload);
			setDefaultRole(defaultRole);
		}
		else if (defaultRole.getAD_Role_ID() != AD_Role_ID
			|| defaultRole.getAD_User_ID() != AD_User_ID)
		{
			defaultRole = get (ctx, AD_Role_ID, AD_User_ID, reload);
			setDefaultRole(defaultRole);
		}
		return defaultRole;
	}	//	getDefault
	
	private static void setDefaultRole(MRole defaultRole) {
		Env.getCtx().remove(ROLE_KEY);
		Env.getCtx().put(ROLE_KEY, defaultRole);
	}

	private static MRole getDefaultRole() {
		return (MRole) Env.getCtx().get(ROLE_KEY);
	}


	/**
	 * 	Get Role for User
	 * 	@param ctx context
	 * 	@param AD_Role_ID role
	 * 	@param AD_User_ID user
	 * 	@param reload if true forces load
	 *	@return role
	 */
	public static MRole get (Properties ctx, int AD_Role_ID, int AD_User_ID, boolean reload)
	{
		s_log.info("AD_Role_ID=" + AD_Role_ID + ", AD_User_ID=" + AD_User_ID + ", reload=" + reload);
		String key = AD_Role_ID + "_" + AD_User_ID;
		MRole role = (MRole)s_roles.get (key);
		if (role == null || reload)
		{
			role = new MRole (ctx, AD_Role_ID, null);
			s_roles.put (key, role);
			if (AD_Role_ID == 0)
			{
				String trxName = null;
				role.load(trxName);			//	special Handling
			}
			role.setAD_User_ID(AD_User_ID);
			role.loadAccess(reload);
			s_log.info(role.toString());
		}
		return role;
	}	//	get

	/**
	 * 	Get Role (cached).
	 * 	Did not set user - so no access loaded
	 * 	@param ctx context
	 * 	@param AD_Role_ID role
	 *	@return role
	 */
	public static MRole get (Properties ctx, int AD_Role_ID)
	{
		// Merge ADempiere trunk_rev14653
		return get(ctx, AD_Role_ID, Env.getAD_User_ID(ctx), false); // metas-2009_0021_AP1_G94 - we need to use this method because we need to load/reload all accesses
		/* metas-2009_0021_AP1_G94
		String key = String.valueOf(AD_Role_ID);
		MRole role = (MRole)s_roles.get (key);
		String trxName = null;
		if (role == null)
		{
			role = new MRole (ctx, AD_Role_ID, trxName);
			s_roles.put (key, role);
			if (AD_Role_ID == 0)	//	System Role
			{
				role.load(trxName);	//	special Handling
			}
		}
		return role;
		/**/ // metas-2009_0021_AP1_G94
	}	//	get

	/**
	 * 	Get Roles Of Client
	 *	@param ctx context
	 *	@return roles of client
	 */
	public static MRole[] getOfClient (Properties ctx, String trxName)
	{
		String sql = "SELECT * FROM AD_Role WHERE AD_Client_ID=? order by name";
		ArrayList<MRole> list = new ArrayList<MRole> ();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MRole(ctx, rs, trxName));
			
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MRole[] retValue = new MRole[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfClient
	
	public static List<LabelValueBean> get(Properties ctx, String trxName) {
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		for (MRole role : getOfClient(ctx, trxName)) {
			list.add(new LabelValueBean(role.getName(), Integer.toString(role.getAD_Role_ID())));
		}
		return list;
	}
	
	/**
	 * Obtiene los roles a partir de una clausula where, utiliza método existente 
	 * getOf, creado para barrer los roles en los miniwizard de usuarios y acceso a perfiles.
	 * 
	 * @param ctx El contexto actual de la aplicación.
	 * @param saWhere La Clausula where a utilizar.
	 * @param trxName La transacción actual.
	 * @param iaAd_Client_Id el cliente logueado
	 * @param iaAd_Org_Id la organización del cliente.
	 * @return una Lista de objetos LabelValueBean.
	 * @author jcantu el 26 de Septiembre del 2012.
	 */
	public static List<LabelValueBean> get(Properties ctx, String saWhere, int iaAd_Client_Id, 
			int iaAd_Org_Id, String trxName) {
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		for (MRole role : getOf(ctx, saWhere, iaAd_Client_Id, iaAd_Org_Id, trxName)) {
			list.add(new LabelValueBean(role.getName(), Integer.toString(role.getAD_Role_ID())));
		}
		return list;
	}
	
	/**
	 * Obtiene los roles del cliente en particular recibido en el parámetro.
	 * 
	 * @param ctx el contexto de la aplicación.
	 * @param whereClause la clausula where a ejecutar como condición.
	 * @param iaAd_Client_Id el ad_client_id.
	 * @param iaAd_Org_Id la organizacion actual.
	 * @param saTrxName el nombre de la transacción actual.
	 * @return un arreglo de objetos de tipo MRole.
	 * @author jcantu.
	 */
	public static MRole[] getOf (Properties ctx, String whereClause, 
			int iaAd_Client_Id, int iaAd_Org_Id, String saTrxName) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM AD_Role ");
		
		if (whereClause != null && whereClause.length() > 0) {
			sql.append(" WHERE ").append(whereClause);
		}
		
		ArrayList<MRole> list = new ArrayList<MRole> ();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		try	{
			pstmt = DB.prepareStatement (sql.toString(), saTrxName);
			
			//Si el adclient es valido, setearlo
			if (iaAd_Client_Id > -1) {
				pstmt.setInt(1, iaAd_Client_Id);
			}
			
			//Si la Organizacion es valida, setearla
			if (iaAd_Org_Id > -1) {
				pstmt.setInt(2, iaAd_Org_Id);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next ()) {
				list.add (new MRole(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		MRole[] retValue = new MRole[list.size ()];
		list.toArray (retValue);
		return retValue;
	}

	/**
	 * 	Get Roles With where clause
	 *	@param ctx context
	 *	@param whereClause where clause
	 *	@return roles of client
	 */
	public static MRole[] getOf (Properties ctx, String whereClause) {
		//Se envía -1 al cliente y a la organizacion y null a la transacción para no afectar las 4 referencias que tenía originalmente.
		return getOf(ctx, whereClause, -1, -1, null);
	}	//	getOf

	/**	Default Role			*/
	private static MRole			s_defaultRole = null;
	/** Role/User Cache			*/
	private static CCache<String,MRole> s_roles = new CCache<String,MRole>("AD_Role", 5);
	/** Log						*/
	private static CLogger			s_log = CLogger.getCLogger(MRole.class);

	/**	Access SQL Read Write		*/
	public static final boolean		SQL_RW = true;
	/**	Access SQL Read Only		*/
	public static final boolean		SQL_RO = false;
	/**	Access SQL Fully Qualified	*/
	public static final boolean		SQL_FULLYQUALIFIED = true;
	/**	Access SQL Not Fully Qualified	*/
	public static final boolean		SQL_NOTQUALIFIED = false;

	/**	The AD_User_ID of the SuperUser				*/
	public static final int			SUPERUSER_USER_ID = 100;
	/**	The AD_User_ID of the System Administrator	*/
	public static final int			SYSTEM_USER_ID = 0;

	private static final String ROLE_KEY = "org.compiere.model.DefaultRole";
	

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Role_ID id
	 *	@param trxName transaction
	 */
	public MRole (Properties ctx, int AD_Role_ID, String trxName)
	{
		super (ctx, AD_Role_ID, trxName);
		//	ID=0 == System Administrator
		if (AD_Role_ID == 0)
		{
		//	setName (null);
			setIsCanExport (true);
			setIsCanReport (true);
			setIsManual (false);
			setIsPersonalAccess (false);
			setIsPersonalLock (false);
			setIsShowAcct (false);
			setIsAccessAllOrgs(false);
			setUserLevel (USERLEVEL_Organization);
			setPreferenceType(PREFERENCETYPE_Organization);
			setIsChangeLog(false);
			setOverwritePriceLimit(false);
			setIsUseUserOrgAccess(false);
			setMaxQueryRecords(0);
			setConfirmQueryRecords(0);
		}
	}	//	MRole

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRole(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRole

	/**
	 * 	Get Confirm Query Records
	 *	@return entered records or 500 (default)
	 */
	public int getConfirmQueryRecords ()
	{
		int no = super.getConfirmQueryRecords ();
		if (no == 0)
			return 500;
		return no;
	}	//	getConfirmQueryRecords

	/**
	 * 	Require Query
	 *	@param noRecords records
	 *	@return true if query required
	 */
	public boolean isQueryRequire (int noRecords)
	{
		if (noRecords < 2)
			return false;
		int max = getMaxQueryRecords();
		if (max > 0 && noRecords > max)
			return true;
		int qu = getConfirmQueryRecords();
		return (noRecords > qu);
	}	//	isQueryRequire

	/**
	 * 	Over max Query
	 *	@param noRecords records
	 *	@return true if over max query
	 */
	public boolean isQueryMax (int noRecords)
	{
		int max = getMaxQueryRecords();
		return max > 0 && noRecords > max;
	}	//	isQueryMax

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if it can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
	//	if (newRecord || is_ValueChanged("UserLevel"))
	//	{
			if (getAD_Client_ID() == 0)
				setUserLevel(USERLEVEL_System);
			else if (getUserLevel().equals(USERLEVEL_System))
			{
				log.saveError("AccessTableNoUpdate", Msg.getElement(getCtx(), "UserLevel"));
				return false;
			}
	//	}
			
			if (isForPatientAccess())
				{
					if(MRole.getOf(getCtx(), " isForPatientAccess = 'Y'").length > 0){
						log.saveError("role.patient.error", Msg.getElement(getCtx(), "isForPatientAccess"));
						return false;
					}
				}
			
		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
		{
			//	Add Role to SuperUser
			MUserRoles su = new MUserRoles(getCtx(), SUPERUSER_USER_ID, getAD_Role_ID(), get_TrxName());
			su.save();
			//	Add Role to User
			if (getCreatedBy() != SUPERUSER_USER_ID)
			{
				MUserRoles ur = new MUserRoles(getCtx(), getCreatedBy(), getAD_Role_ID(), get_TrxName());
				ur.save();
			}
			if (getCopyOf() > 0) {
				success = copyAccessRecords();
			}else{
				updateAccessRecords();
			}
		}
		//
		else if (is_ValueChanged("UserLevel"))
			updateAccessRecords();

		//	Default Role changed
		if (s_defaultRole != null
			&& s_defaultRole.get_ID() == get_ID())
			s_defaultRole = this;
		return success;
	}	//	afterSave
	
	/**
	 * 	Executed after Delete operation.
	 * 	@param success true if record deleted
	 *	@return true if delete is a success
	 *  Merge ADempiere trunk_rev14653
	 */
	protected boolean afterDelete (boolean success)
	{
		if(success) {
			deleteAccessRecords();
		}
		return success;
	} 	//	afterDelete
	
	public boolean copyAccessRecords() {
		boolean retValue = true;

//		List<MEXMEStrutsAccess> sAccess = MEXMEStrutsAccess.getAccesFromRole(getCtx(), getCopyOf(), get_TrxName());
//		for (MEXMEStrutsAccess ac : sAccess) {
//			MEXMEStrutsAccess newAccess = new MEXMEStrutsAccess(getCtx(), 0, get_TrxName());
//			PO.copyValues(ac, newAccess, getAD_Client_ID(), getAD_Org_ID());
//			newAccess.setAD_Role_ID(getAD_Role_ID());
//			newAccess.save(get_TrxName());
//		}

		List<MWindowAccess> wAccess = MWindowAccess.getAccesFromRole(getCtx(), getCopyOf(), get_TrxName());
		for (MWindowAccess ac : wAccess) {
			MWindowAccess newAccess = new MWindowAccess((MWindow) ac.getAD_Window(), getAD_Role_ID());
			newAccess.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			newAccess.save(get_TrxName());
		}

		List<MProcessAccess> pAccess = MProcessAccess.getAccesFromRole(getCtx(), getCopyOf(), get_TrxName());
		for (MProcessAccess ac : pAccess) {
			MProcessAccess newAccess = new MProcessAccess((MProcess) ac.getAD_Process(), getAD_Role_ID());
			newAccess.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			newAccess.save(get_TrxName());
		}

		List<MFormAccess> fAccess = MFormAccess.getAccesFromRole(getCtx(), getCopyOf(), get_TrxName());
		for (MFormAccess ac : fAccess) {
			MFormAccess newAccess = new MFormAccess((MForm) ac.getAD_Form(), getAD_Role_ID());
			newAccess.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			newAccess.save(get_TrxName());
		}

		List<MWorkflowAccess> wfAccess = MWorkflowAccess.getAccesFromRole(getCtx(), getCopyOf(), get_TrxName());
		for (MWorkflowAccess ac : wfAccess) {
			MWorkflowAccess newAccess = new MWorkflowAccess((MWorkflow) ac.getAD_Workflow(), getAD_Role_ID());
			newAccess.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			newAccess.save(get_TrxName());
		}

		return retValue;
	}

	/**
	 * 	Create Access Records
	 *	@return info
	 */
	public String updateAccessRecords ()
	{
		if (isManual())
			return "-";

		String roleClientOrgUser = getAD_Role_ID() + ","
			+ getAD_Client_ID() + "," + getAD_Org_ID() + ",'Y', SysDate,"
			+ getUpdatedBy() + ", SysDate," + getUpdatedBy()
			+ ",'Y' ";	//	IsReadWrite

		String rolePatientAccess = "";//TODO: Lama
		if(isForPatientAccess()){
			rolePatientAccess = " AND isForPatientAccess = 'Y' ";
		}
		
		String sqlWindow = "INSERT INTO AD_Window_Access "
			+ "(AD_Window_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT DISTINCT w.AD_Window_ID, " + roleClientOrgUser
			+ "FROM AD_Window w"
			+ " INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID)"
			+ " INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) "
			+ "WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt "	// only check first tab
				+ "WHERE xt.AD_Window_ID=w.AD_Window_ID)"
			+ "AND tt.AccessLevel IN ";

		String sqlProcess = "INSERT INTO AD_Process_Access "
			+ "(AD_Process_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT DISTINCT p.AD_Process_ID, " + roleClientOrgUser
			+ "FROM AD_Process p "
			+ "WHERE AccessLevel IN ";

		String sqlForm = "INSERT INTO AD_Form_Access "
			+ "(AD_Form_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT f.AD_Form_ID, " + roleClientOrgUser
			+ "FROM AD_Form f " 
			+ "WHERE AccessLevel IN ";

		String sqlWorkflow = "INSERT INTO AD_WorkFlow_Access "
			+ "(AD_WorkFlow_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT w.AD_WorkFlow_ID, " + roleClientOrgUser
			+ "FROM AD_WorkFlow w "
			+ "WHERE AccessLevel IN ";

		//expert : mrojas //TODO: quitar
//		String sqlStruts = "INSERT INTO EXME_Struts_Access "
//		    + "(EXME_Struts_ID, AD_Role_ID,"
//		    + " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
//		    + " SELECT s.EXME_Struts_ID, " + roleClientOrgUser
//		    + " FROM EXME_Struts s WHERE s.IsActive = 'Y'" 
//		    + "AND AccessLevel IN ";

		//expert : mrojas


		/**
		 *	Fill AD_xx_Access
		 *	---------------------------------------------------------------------------
		 *	SCO# Levels			S__ 100		4	System info
		 *						SCO	111		7	System shared info
		 *						SC_ 110		6	System/Client info
		 *						_CO	011		3	Client shared info
		 *						_C_	011		2	Client
		 *						__O	001		1	Organization info
		 *	Roles:
		 *		S		4,7,6
		 *		_CO		7,6,3,2,1
		 *		__O		3,1,7
		 */
		String roleAccessLevel = null;
		String roleAccessLevelWin = null;
		if (USERLEVEL_System.equals(getUserLevel()))
			roleAccessLevel = "('4','7','6')";
		else if (USERLEVEL_Client.equals(getUserLevel()))
			roleAccessLevel = "('7','6','3','2')";
		else if (USERLEVEL_ClientPlusOrganization.equals(getUserLevel()))
			roleAccessLevel = "('7','6','3','2','1')";
		else //	if (USERLEVEL_Organization.equals(getUserLevel()))
		{
			roleAccessLevel = "('3','1','7')";
			roleAccessLevelWin = roleAccessLevel + rolePatientAccess //FIXME
				+ " AND w.Name NOT LIKE '%(all)%'";
		}
		roleAccessLevel += rolePatientAccess; //FIXME
		if (roleAccessLevelWin == null)
			roleAccessLevelWin = roleAccessLevel;
		//
		String whereDel = " WHERE AD_Role_ID=" + getAD_Role_ID();
		//
		int winDel = DB.executeUpdate("DELETE AD_Window_Access" + whereDel, get_TrxName());
		int win = DB.executeUpdate(sqlWindow + roleAccessLevelWin, get_TrxName());
		int procDel = DB.executeUpdate("DELETE AD_Process_Access" + whereDel, get_TrxName());
		int proc = DB.executeUpdate(sqlProcess + roleAccessLevel, get_TrxName());
		int formDel = DB.executeUpdate("DELETE AD_Form_Access" + whereDel, get_TrxName());
		int form = DB.executeUpdate(sqlForm + roleAccessLevel, get_TrxName());
		int wfDel = DB.executeUpdate("DELETE AD_WorkFlow_Access" + whereDel, get_TrxName());
		int wf = DB.executeUpdate(sqlWorkflow + roleAccessLevel, get_TrxName());

		//expert : mrojas
//		int sDel = DB.executeUpdate("DELETE EXME_Struts_Access" + whereDel, get_TrxName());
//		int s = DB.executeUpdate(sqlStruts + roleAccessLevel, get_TrxName());
		//expert : mrojas

		log.fine("AD_Window_ID=" + winDel + "+" + win
			+ ", AD_Process_ID=" + procDel + "+" + proc
			+ ", AD_Form_ID=" + formDel + "+" + form
			+ ", AD_Workflow_ID=" + wfDel + "+" + wf
			//expert : mrojas
//			+ ", EXME_Struts_ID=" + sDel + "+" + s
			//expert : mrojas
		);

		loadAccess(true);
		return "@AD_Window_ID@ #" + win
			+ " -  @AD_Process_ID@ #" + proc
			+ " -  @AD_Form_ID@ #" + form
			+ " -  @AD_Workflow_ID@ #" + wf
			//expert : mrojas
//			+ " -  @EXME_Struts_ID@ #" + s
			//expert : mrojas
			;
	}	//	createAccessRecords

	
	/**
	 * Delete Access Records of the role after the role was (successfully) deleted.
	 * Merge ADempiere trunk_rev14653
	 */
	private void deleteAccessRecords() {
		String whereDel = " WHERE AD_Role_ID=" + getAD_Role_ID();
		//
		int winDel = DB.executeUpdate("DELETE FROM AD_Window_Access" + whereDel, get_TrxName());
		int procDel = DB.executeUpdate("DELETE FROM AD_Process_Access" + whereDel, get_TrxName());
		int formDel = DB.executeUpdate("DELETE FROM AD_Form_Access" + whereDel, get_TrxName());
		int wfDel = DB.executeUpdate("DELETE FROM AD_WorkFlow_Access" + whereDel, get_TrxName());
		int docactDel = DB.executeUpdate("DELETE FROM AD_Document_Action_Access" + whereDel, get_TrxName());
		

		log.fine("AD_Window_Access=" + winDel
			+ ", AD_Process_Access=" + procDel
			+ ", AD_Form_Access=" + formDel
			+ ", AD_Workflow_Access=" + wfDel
			+ ", AD_Document_Action_Access=" + docactDel);
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MRole[");
		sb.append(getAD_Role_ID()).append(',').append(getName())
			.append(",UserLevel=").append(getUserLevel())
			.append(',').append(getClientWhere(false))
			.append(',').append(getOrgWhere(false))
			.append(']');
		return sb.toString();
	}	//	toString

	/**
	 * 	Extended String Representation
	 *	@param ctx Properties
	 *	@return extended info
	 */
	public String toStringX (Properties ctx)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(Msg.translate(ctx, "AD_Role_ID")).append('=').append(getName())
			.append(DASH).append(Msg.translate(ctx, "IsCanExport")).append('=').append(isCanExport())
			.append(DASH).append(Msg.translate(ctx, "IsCanReport")).append('=').append(isCanReport())
			.append(Env.NL).append(Env.NL);
		//
		for (int i = 0; i < m_orgAccess.length; i++)
			sb.append(m_orgAccess[i].toString()).append(Env.NL);
		sb.append(Env.NL);
		//
		loadTableAccess(false);
		for (int i = 0; i < m_tableAccess.length; i++)
			sb.append(m_tableAccess[i].toStringX(ctx)).append(Env.NL);
		if (m_tableAccess.length > 0)
			sb.append(Env.NL);
		//
		loadColumnAccess(false);
		for (int i = 0; i < m_columnAccess.length; i++)
			sb.append(m_columnAccess[i].toStringX(ctx)).append(Env.NL);
		if (m_columnAccess.length > 0)
			sb.append(Env.NL);
		//
		loadRecordAccess(false);
		for (int i = 0; i < m_recordAccess.length; i++)
			sb.append(m_recordAccess[i].toStringX(ctx)).append(Env.NL);
		return sb.toString();
	}	//	toStringX



	/*************************************************************************
	 * 	Access Management
	 ************************************************************************/

	/** User 								*/
	private int						m_AD_User_ID = -1;

	/**	Positive List of Organizational Access		*/
	private OrgAccess[]				m_orgAccess = null;
	/** List of Table Access						*/
	private MTableAccess[]			m_tableAccess = null;
	/** List of Column Access				*/
	private MColumnAccess[]			m_columnAccess = null;
	/** List of Record Access				*/
	private MRecordAccess[]			m_recordAccess = null;
	/** List of Dependent Record Access		*/
	private MRecordAccess[]			m_recordDependentAccess = null;

	/**	Table Data Access Level	*/
	private HashMap<Integer,String>		m_tableAccessLevel = null;
	/**	Table Name				*/
	private HashMap<String,Integer>		m_tableName = null;

	/**	Window Access			*/
	private HashMap<Integer,Boolean>	m_windowAccess = null;
	/**	Process Access			*/
	private HashMap<Integer,Boolean>	m_processAccess = null;
	/**	Task Access				*/
	private HashMap<Integer,Boolean>	m_taskAccess = null;
	/**	Workflow Access			*/
	private HashMap<Integer,Boolean>	m_workflowAccess = null;
	/**	Form Access				*/
	private HashMap<Integer,Boolean>	m_formAccess = null;
	//Expert:Hassan
	/**	Acceso a rutas Struts
	 * @deprecated		*/
//	private HashMap<Integer,Boolean>	m_strutsAccess = null;
	//Expert:Hassan

	/**
	 * 	Set Logged in user
	 *	@param AD_User_ID user requesting info
	 */
	public void setAD_User_ID(int AD_User_ID)
	{
		m_AD_User_ID = AD_User_ID;
	}	//	setAD_User_ID

	/**
	 * 	Get Logged in user
	 *	@return AD_User_ID user requesting info
	 */
	public int getAD_User_ID()
	{
		return m_AD_User_ID;
	}	//	getAD_User_ID


	/**************************************************************************
	 * 	Load Access Info
	 * 	@param reload re-load from disk
	 */
	public void loadAccess (boolean reload)
	{
		loadOrgAccess(reload);
		loadTableAccess(reload);
		loadTableInfo(reload);
		loadColumnAccess(reload);
		loadRecordAccess(reload);
		if (reload)
		{
			m_windowAccess = null;
			m_processAccess = null;
			m_taskAccess = null;
			m_workflowAccess = null;
			m_formAccess = null;
		}
	}	//	loadAccess

	/**
	 * 	Load Org Access
	 *	@param reload reload
	 */
	private void loadOrgAccess (boolean reload)
	{
		if (!(reload || m_orgAccess == null))
			return;
		//
		ArrayList<OrgAccess> list = new ArrayList<OrgAccess>();

		if (isUseUserOrgAccess())
			loadOrgAccessUser(list);
		else
			loadOrgAccessRole(list);

		m_orgAccess = new OrgAccess[list.size()];
		list.toArray(m_orgAccess);
		log.fine("#" + m_orgAccess.length + (reload ? " - reload" : ""));
		if (Ini.isClient())
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < m_orgAccess.length; i++)
			{
				if (i > 0)
					sb.append(",");
				sb.append(m_orgAccess[i].AD_Org_ID);
			}
			Env.setContext(Env.getCtx(), "#User_Org", sb.toString());
		}
	}	//	loadOrgAccess

	/**
	 * 	Load Org Access User
	 *	@param list list
	 */
	private void loadOrgAccessUser(ArrayList<OrgAccess> list)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		String sql = "SELECT * FROM AD_User_OrgAccess "
			+ "WHERE AD_User_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MUserOrgAccess oa = new MUserOrgAccess(getCtx(), rs, get_TrxName());
				loadOrgAccessAdd (list, new OrgAccess(oa.getAD_Client_ID(), oa.getAD_Org_ID(), oa.isReadOnly()));
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}	//	loadOrgAccessRole

	/**
	 * 	Load Org Access Role
	 *	@param list list
	 */
	private void loadOrgAccessRole(ArrayList<OrgAccess> list)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		String sql = "SELECT * FROM AD_Role_OrgAccess "
			+ "WHERE AD_Role_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MRoleOrgAccess oa = new MRoleOrgAccess(getCtx(), rs, get_TrxName());
				loadOrgAccessAdd (list, new OrgAccess(oa.getAD_Client_ID(), oa.getAD_Org_ID(), oa.isReadOnly()));
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}	//	loadOrgAccessRole

	/**
	 * 	Load Org Access Add Tree to List
	 *	@param list list
	 *	@param oa org access
	 *	@see org.compiere.util.Login
	 */
	private void loadOrgAccessAdd (ArrayList<OrgAccess> list, OrgAccess oa)
	{
		if (list.contains(oa))
			return;
		list.add(oa);
		//	Do we look for trees?
		if (getAD_Tree_Org_ID() == 0)
			return;
		MOrg org = MOrg.get(getCtx(), oa.AD_Org_ID);
		if (!org.isSummary())
			return;
		//	Summary Org - Get Dependents
		MTree_Base tree = MTree_Base.get(getCtx(), getAD_Tree_Org_ID(), get_TrxName());
		String sql =  "SELECT AD_Client_ID, AD_Org_ID FROM AD_Org "
			+ "WHERE IsActive='Y' AND AD_Org_ID IN (SELECT Node_ID FROM "
			+ tree.getNodeTableName()
			+ " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive='Y')";
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, tree.getAD_Tree_ID());
			pstmt.setInt(2, org.getAD_Org_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				int AD_Client_ID = rs.getInt(1);
				int AD_Org_ID = rs.getInt(2);
				loadOrgAccessAdd (list, new OrgAccess(AD_Client_ID, AD_Org_ID, oa.readOnly));
			}
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}	//	loadOrgAccessAdd


	/**
	 * 	Load Table Access
	 *	@param reload reload
	 */
	private void loadTableAccess(boolean reload)
	{
		if (m_tableAccess != null && !reload)
			return;
		ArrayList<MTableAccess> list = new ArrayList<MTableAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		String sql = "SELECT * FROM AD_Table_Access "
			+ "WHERE AD_Role_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MTableAccess(getCtx(), rs, get_TrxName()));
			
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}

		m_tableAccess = new MTableAccess[list.size()];
		list.toArray(m_tableAccess);
		log.fine("#" + m_tableAccess.length);
	}	//	loadTableAccess

	/**
	 * 	Load Table Access and Name
	 *	@param reload reload
	 */
	private void loadTableInfo (boolean reload)
	{
		if (m_tableAccessLevel != null && m_tableName != null && !reload)
			return;
		m_tableAccessLevel = new HashMap<Integer,String>(300);
		m_tableName = new HashMap<String,Integer>(300);
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		String sql = "SELECT AD_Table_ID, AccessLevel, TableName "
			+ "FROM AD_Table WHERE IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Integer ii = new Integer(rs.getInt(1));
				m_tableAccessLevel.put(ii, rs.getString(2));
				m_tableName.put(rs.getString(3), ii);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		log.fine("#" + m_tableAccessLevel.size());
	}	//	loadTableAccessLevel


	/**
	 * 	Load Column Access
	 *	@param reload reload
	 */
	private void loadColumnAccess(boolean reload)
	{
		if (m_columnAccess != null && !reload)
			return;
		ArrayList<MColumnAccess> list = new ArrayList<MColumnAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		String sql = "SELECT * FROM AD_Column_Access "
			+ "WHERE AD_Role_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MColumnAccess(getCtx(), rs, get_TrxName()));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		m_columnAccess = new MColumnAccess[list.size()];
		list.toArray(m_columnAccess);
		log.fine("#" + m_columnAccess.length);
	}	//	loadColumnAccess

	/**
	 * 	Load Record Access
	 *	@param reload reload
	 */
	private void loadRecordAccess(boolean reload)
	{
		if (!(reload || m_recordAccess == null || m_recordDependentAccess == null))
			return;
		ArrayList<MRecordAccess> list = new ArrayList<MRecordAccess>();
		ArrayList<MRecordAccess> dependent = new ArrayList<MRecordAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Expert
		String sql = "SELECT * FROM AD_Record_Access "
			+ "WHERE AD_Role_ID=? AND IsActive='Y' ORDER BY AD_Table_ID";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MRecordAccess ra = new MRecordAccess(getCtx(), rs, get_TrxName());
				list.add(ra);
				if (ra.isDependentEntities())
					dependent.add(ra);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		m_recordAccess = new MRecordAccess[list.size()];
		list.toArray(m_recordAccess);
		m_recordDependentAccess = new MRecordAccess[dependent.size()];
		dependent.toArray(m_recordDependentAccess);
		log.fine("#" + m_recordAccess.length + " - Dependent #" + m_recordDependentAccess.length);
	}	//	loadRecordAccess


	/**************************************************************************
	 * 	Get Client Where Clause Value
	 * 	@param rw read write
	 * 	@return "AD_Client_ID=0" or "AD_Client_ID IN(0,1)"
	 */
	public String getClientWhere (boolean rw)
	{
		//	All Orgs - use Client of Role
		if (isAccessAllOrgs())
		{
			if (rw || getAD_Client_ID() == 0)
				return "AD_Client_ID=" + getAD_Client_ID();
			return "AD_Client_ID IN (0," + getAD_Client_ID() + ")";
		}

		//	Get Client from Org List
		loadOrgAccess (false);
		//	Unique Strings
		HashSet<String> set = new HashSet<String>();
		if (!rw)
			set.add("0");
		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
			set.add(String.valueOf(m_orgAccess[i].AD_Client_ID));
		//
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = set.iterator();
		boolean oneOnly = true;
		while (it.hasNext())
		{
			if (sb.length() > 0)
			{
				sb.append(",");
				oneOnly = false;
			}
			sb.append(it.next());
		}
		if (oneOnly)
		{
			if (sb.length() > 0)
				return "AD_Client_ID=" + sb.toString();
			else
			{
				log.log(Level.SEVERE, "No Access Org records");
				return "AD_Client_ID=-1";	//	No Access Record
			}
		}
		return "AD_Client_ID IN(" + sb.toString() + ")";
	}	//	getClientWhereValue

	/**
	 * 	Access to Client
	 *	@param AD_Client_ID client
	 *	@param rw read write access
	 *	@return true if access
	 */
	public boolean isClientAccess(int AD_Client_ID, boolean rw)
	{
		
		boolean isClientAccess = false;
		
		if (AD_Client_ID == 0 && !rw) {	//	can always read System
			isClientAccess = true;
		} else {
			loadOrgAccess(false);
			//	Positive List
			for (int i = 0; i < m_orgAccess.length; i++)
			{
				if (m_orgAccess[i].AD_Client_ID == AD_Client_ID)
				{
					if (!rw) {
						isClientAccess = true;
						break;
					}
					if (!m_orgAccess[i].readOnly) {	//	rw
						isClientAccess = true;
						break;
					}
				}
			}
		}
		return isClientAccess;
	}	//	isClientAccess

	/**
	 * 	Get Org Where Clause Value
	 * 	@param rw read write
	 * 	@return "AD_Org_ID=0" or "AD_Org_ID IN(0,1)" or null (if access all org)
	 */
	public String getOrgWhere (boolean rw)
	{
		if (isAccessAllOrgs())
			return null;
		loadOrgAccess(true);
		//	Unique Strings
		HashSet<String> set = new HashSet<String>();
		if (!rw)
			set.add("0");
		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
		{
			if (!rw)
				set.add(String.valueOf(m_orgAccess[i].AD_Org_ID));
			else if (!m_orgAccess[i].readOnly)	//	rw
				set.add(String.valueOf(m_orgAccess[i].AD_Org_ID));
		}
		//
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = set.iterator();
		boolean oneOnly = true;
		while (it.hasNext())
		{
			if (sb.length() > 0)
			{
				sb.append(",");
				oneOnly = false;
			}
			sb.append(it.next());
		}
		if (oneOnly)
		{
			if (sb.length() > 0)
				return "AD_Org_ID=" + sb.toString();
			else
			{
				log.log(Level.SEVERE, "No Access Org records");
				return "AD_Org_ID=-1";	//	No Access Record
			}
		}
		return "AD_Org_ID IN(" + sb.toString() + ")";
	}	//	getOrgWhereValue

	/**
	 * 	Access to Org
	 *	@param AD_Org_ID org
	 *	@param rw read write access
	 *	@return true if access
	 */
	public boolean isOrgAccess(int AD_Org_ID, boolean rw)
	{
		if (isAccessAllOrgs())
			return true;
		if (AD_Org_ID == 0 && !rw)		//	can always read common org
			return true;
		loadOrgAccess(false);

		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
		{
			if (m_orgAccess[i].AD_Org_ID == AD_Org_ID)
			{
				if (!rw)
					return true;
				if (!m_orgAccess[i].readOnly)	//	rw
					return true;
				return false;
			}
		}
		return false;
	}	//	isOrgAccess


	/**
	 * 	Can Report on table
	 *	@param AD_Table_ID table
	 *	@return true if access
	 */
	public boolean isCanReport (int AD_Table_ID)
	{
		if (!isCanReport())						//	Role Level block
		{
			log.warning ("Role denied");
			return false;
		}
		if (!isTableAccess(AD_Table_ID, true))	//	No R/O Access to Table
			return false;
		//
		boolean canReport = true;
		for (int i = 0; i < m_tableAccess.length; i++)
		{
			if (!MTableAccess.ACCESSTYPERULE_Reporting.equals(m_tableAccess[i].getAccessTypeRule()))
				continue;
			if (m_tableAccess[i].isExclude())		//	Exclude
			{
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					canReport = m_tableAccess[i].isCanReport();
					log.fine("Exclude " + AD_Table_ID + DASH + canReport);
					return canReport;
				}
			}
			else									//	Include
			{
				canReport = false;
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					canReport = m_tableAccess[i].isCanReport();
					log.fine("Include " + AD_Table_ID + DASH + canReport);
					return canReport;
				}
			}
		}	//	for all Table Access
		log.fine(AD_Table_ID + DASH + canReport);
		return canReport;
	}	//	isCanReport

	/**
	 * 	Can Export Table
	 *	@param AD_Table_ID
	 *	@return true if access
	 */
	public boolean isCanExport (int AD_Table_ID)
	{
		if (!isCanExport())						//	Role Level block
		{
			log.warning ("Role denied");
			return false;
		}
		if (!isTableAccess(AD_Table_ID, true))	//	No R/O Access to Table
			return false;
		if (!isCanReport (AD_Table_ID))			//	We cannot Export if we cannot report
			return false;
		//
		boolean canExport = true;
		for (int i = 0; i < m_tableAccess.length; i++)
		{
			if (!MTableAccess.ACCESSTYPERULE_Exporting.equals(m_tableAccess[i].getAccessTypeRule()))
				continue;
			if (m_tableAccess[i].isExclude())		//	Exclude
			{
				canExport = m_tableAccess[i].isCanExport();
				log.fine("Exclude " + AD_Table_ID + DASH + canExport);
				return canExport;
			}
			else									//	Include
			{
				canExport = false;
				canExport = m_tableAccess[i].isCanExport();
				log.fine("Include " + AD_Table_ID + DASH + canExport);
				return canExport;
			}
		}	//	for all Table Access
		log.fine(AD_Table_ID + DASH + canExport);
		return canExport;
	}	//	isCanExport

	/**
	 * 	Access to Table
	 *	@param AD_Table_ID table
	 *	@param ro check read only access otherwise read write access level
	 *	@return has RO/RW access to table
	 */
	public boolean isTableAccess (int AD_Table_ID, boolean ro)
	{
		if (!isTableAccessLevel (AD_Table_ID, ro))	//	Role Based Access
			return false;
		loadTableAccess(false);
		//
		boolean hasAccess = true;	//	assuming exclusive rule
		for (int i = 0; i < m_tableAccess.length; i++)
		{
			if (!MTableAccess.ACCESSTYPERULE_Accessing.equals(m_tableAccess[i].getAccessTypeRule()))
				continue;
			if (m_tableAccess[i].isExclude())		//	Exclude
			//	If you Exclude Access to a table and select Read Only,
			//	you can only read data (otherwise no access).
			{
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					if (ro)
						hasAccess = m_tableAccess[i].isReadOnly();
					else
						hasAccess = false;
					log.fine("Exclude AD_Table_ID=" + AD_Table_ID
						+ " (ro="  + ro + ",TableAccessRO=" + m_tableAccess[i].isReadOnly() + ") = " + hasAccess);
					return hasAccess;
				}
			}
			else								//	Include
			//	If you Include Access to a table and select Read Only,
			//	you can only read data (otherwise full access).
			{
				hasAccess = false;
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					if (!ro)	//	rw only if not r/o
						hasAccess = !m_tableAccess[i].isReadOnly();
					else
						hasAccess = true;
					log.fine("Include AD_Table_ID=" + AD_Table_ID
						+ " (ro="  + ro + ",TableAccessRO=" + m_tableAccess[i].isReadOnly() + ") = " + hasAccess);
					return hasAccess;
				}
			}
		}	//	for all Table Access
		if (!hasAccess)
			log.fine("AD_Table_ID=" + AD_Table_ID
				+ "(ro="  + ro + ") = " + hasAccess);
		return hasAccess;
	}	//	isTableAccess

	/**
	 * 	Access to Table based on Role User Level Table Access Level
	 *	@param AD_Table_ID table
	 *	@param ro check read only access otherwise read write access level
	 *	@return has RO/RW access to table
	 */
	public boolean isTableAccessLevel (int AD_Table_ID, boolean ro)
	{
		if (ro)				//	role can always read
			return true;
		//
		loadTableInfo(false);
		//	AccessLevel
		//		1 = Org - 2 = Client - 4 = System
		//		3 = Org+Client - 6 = Client+System - 7 = All
		String roleAccessLevel = (String)m_tableAccessLevel.get(new Integer(AD_Table_ID));
		if (roleAccessLevel == null)
		{
			log.fine("NO - No AccessLevel - AD_Table_ID=" + AD_Table_ID);
			return false;
		}
		//	Access to all User Levels
		if (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_All))
			return true;
		
		//INPROCESS : Client to System access level
		if(roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientToSystem))
			return true;
		
		//	User Level = SCO
		String userLevel = getUserLevel();
		//
		if (userLevel.charAt(0) == 'S'
			&& (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly)
				|| roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient)))
			return true;
		if (userLevel.charAt(1) == 'C'
			&& (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientOnly)
				|| roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient)))
			return true;
		if (userLevel.charAt(2) == 'O'
			&& (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_Organization)
				|| roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientPlusOrganization)))
			return true;
		log.fine("NO - AD_Table_ID=" + AD_Table_ID
			+ ", UserLevel=" + userLevel + ", AccessLevel=" + roleAccessLevel);
		return false;
	}	//	isTableAccessLevel


	/**
	 * 	Access to Column
	 *	@param AD_Table_ID table
	 *	@param AD_Column_ID column
	 *	@param ro read only
	 *	@return true if access
	 */
	public boolean isColumnAccess (int AD_Table_ID, int AD_Column_ID, boolean ro)
	{
		if (!isTableAccess(AD_Table_ID, ro))		//	No Access to Table
			return false;
		loadColumnAccess(false);

		boolean retValue = true;		//	assuming exclusive
		for (int i = 0; i < m_columnAccess.length; i++)
		{
			if (m_columnAccess[i].isExclude())		//	Exclude
			//	If you Exclude Access to a column and select Read Only,
			//	you can only read data (otherwise no access).
			{
				if (m_columnAccess[i].getAD_Table_ID() == AD_Table_ID
					&& m_columnAccess[i].getAD_Column_ID() == AD_Column_ID)
				{
					if (ro)		//	just R/O Access requested
						retValue = m_columnAccess[i].isReadOnly();
					else
						retValue = false;
					if (!retValue)
						log.fine("Exclude AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID
							+ " (ro="  + ro + ",ColumnAccessRO=" + m_columnAccess[i].isReadOnly() + ") = " + retValue);
					return retValue;
				}
			}
			else								//	Include
			//	If you Include Access to a column and select Read Only,
			//	you can only read data (otherwise full access).
			{
				if (m_columnAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					retValue = false;
					if (m_columnAccess[i].getAD_Column_ID() == AD_Column_ID)
					{
						if (!ro)	//	rw only if not r/o
							retValue = !m_columnAccess[i].isReadOnly();
						else
							retValue = true;
						if (!retValue)
							log.fine("Include AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID
								+ " (ro="  + ro + ",ColumnAccessRO=" + m_columnAccess[i].isReadOnly() + ") = " + retValue);
						return retValue;
					}
				}	//	same table
			}	//	include
		}	//	for all Table Access
		if (!retValue)
			log.fine("AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID
				+ " (ro="  + ro + ") = " + retValue);
		return retValue;
	}	//	isColumnAccess

	/**
	 *	Access to Record (no check of table)
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@param ro read only
	 *	@return boolean
	 */
	public boolean isRecordAccess (int AD_Table_ID, int Record_ID, boolean ro)
	{
	//	if (!isTableAccess(AD_Table_ID, ro))		//	No Access to Table
	//		return false;
		loadRecordAccess(false);
		boolean negativeList = true;
		for (int i = 0; i < m_recordAccess.length; i++)
		{
			MRecordAccess ra = m_recordAccess[i];
			if (ra.getAD_Table_ID() != AD_Table_ID)
				continue;

			if (ra.isExclude())		//	Exclude
			//	If you Exclude Access to a column and select Read Only,
			//	you can only read data (otherwise no access).
			{
				if (ra.getRecord_ID() == Record_ID)
				{
					if (ro)
						return ra.isReadOnly();
					else
						return false;
				}
			}
			else								//	Include
			//	If you Include Access to a column and select Read Only,
			//	you can only read data (otherwise full access).
			{
				negativeList = false;	//	has to be defined
				if (ra.getRecord_ID() == Record_ID)
				{
					if (!ro)
						return !ra.isReadOnly();
					else	//	ro
						return true;
				}
			}
		}	//	for all Table Access
		return negativeList;
	}	//	isRecordAccess

	/**
	 * 	Get Window Access
	 *	@param AD_Window_ID window
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getWindowAccess (int AD_Window_ID)
	{
		if (m_windowAccess == null)
		{
			m_windowAccess = new HashMap<Integer,Boolean>(100);
			String sql = "SELECT AD_Window_ID, IsReadWrite FROM AD_Window_Access WHERE AD_Role_ID=? AND IsActive='Y'";
			PreparedStatement pstmt = null;
			ResultSet rs = null; //Expert
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_windowAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
			log.fine("#" + m_windowAccess.size());
		}	//	reload
	//	log.fine("getWindowAccess - AD_Window_ID=" + AD_Window_ID + dash + retValue);
		return (Boolean)m_windowAccess.get(new Integer(AD_Window_ID));
	}	//	getWindowAccess

	/**
	 * 	Get Process Access
	 *	@param AD_Process_ID process
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getProcessAccess (int AD_Process_ID)
	{
		if (m_processAccess == null)
		{
			m_processAccess = new HashMap<Integer,Boolean>(50);
			String sql = "SELECT AD_Process_ID, IsReadWrite FROM AD_Process_Access WHERE AD_Role_ID=? AND IsActive='Y'";
			PreparedStatement pstmt = null;
			ResultSet rs = null; //Expert
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_processAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}	//	reload
		return (Boolean)m_processAccess.get(new Integer(AD_Process_ID));
	}	//	getProcessAccess

	/**
	 * 	Get Task Access
	 *	@param AD_Task_ID task
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getTaskAccess (int AD_Task_ID)
	{
		if (m_taskAccess == null)
		{
			m_taskAccess = new HashMap<Integer,Boolean>(10);
			String sql = "SELECT AD_Task_ID, IsReadWrite FROM AD_Task_Access "
				+ "WHERE AD_Role_ID=? AND IsActive='Y'";
			PreparedStatement pstmt = null;
            ResultSet rs = null; //Expert
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_taskAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}	//	reload
		return (Boolean)m_taskAccess.get(new Integer(AD_Task_ID));
	}	//	getTaskAccess

	/**
	 * 	Get Form Access
	 *	@param AD_Form_ID form
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getFormAccess (int AD_Form_ID)
	{
		if (m_formAccess == null)
		{
			m_formAccess = new HashMap<Integer,Boolean>(20);
			String sql = "SELECT AD_Form_ID, IsReadWrite FROM AD_Form_Access "
				+ "WHERE AD_Role_ID=? AND IsActive='Y'";
			PreparedStatement pstmt = null;
            ResultSet rs = null; //Expert
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_formAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
				
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}	//	reload
		Object obj = m_formAccess.get(new Integer(AD_Form_ID));
		return obj == null ? false : (Boolean)obj;
	}	//	getTaskAccess

	/**
	 * 	Get Workflow Access
	 *	@param AD_Workflow_ID workflow
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getWorkflowAccess (int AD_Workflow_ID)
	{
		if (m_workflowAccess == null)
		{
			m_workflowAccess = new HashMap<Integer,Boolean>(20);
			String sql = "SELECT AD_Workflow_ID, IsReadWrite FROM AD_Workflow_Access "
				+ "WHERE AD_Role_ID=? AND IsActive='Y'";
			PreparedStatement pstmt = null;
            ResultSet rs = null; //Expert
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_workflowAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
				
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}	//	reload
		return (Boolean)m_workflowAccess.get(new Integer(AD_Workflow_ID));
	}	//	getTaskAccess

	//Expert..
	/**
	 * 	Get Struts Access
	 *	@param AD_Workflow_ID workflow
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 * @deprecated
	 *
	public Boolean getStrutsAccess (int EXME_Struts_ID)
	{
		if (m_strutsAccess == null)
		{
			m_strutsAccess = new HashMap<Integer,Boolean>(20);
			String sql = "SELECT EXME_Struts_ID, IsReadWrite FROM EXME_Struts_Access WHERE AD_Role_ID=? AND IsActive='Y'";
			PreparedStatement pstmt = null;
            ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_strutsAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
				
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "getStrutsAccess", e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}	//	reload
		return (Boolean)m_strutsAccess.get(new Integer(EXME_Struts_ID));
	}	//	getTaskAccess
	//Expert..*/

	/*************************************************************************
	 *	Appends where clause to SQL statement for Table
	 *
	 *	@param SQL			existing SQL statement
	 *	@param TableNameIn	Table Name or list of table names AAA, BBB or AAA a, BBB b
	 *	@param fullyQualified	fullyQualified names
	 *	@param rw			if false, includes System Data
	 *	@return				updated SQL statement
	 */
	public String addAccessSQL (String SQL, String TableNameIn,
		boolean fullyQualified, boolean rw)
	{
		StringBuffer retSQL = new StringBuffer();
		
		//	Cut off last ORDER BY clause
		String orderBy = "";
		int posOrder = SQL.lastIndexOf(" GROUP BY ");
		if (posOrder != -1)
		{ //Raúl - Agregue el GROUP BY, este siempre es antes del ORDER BY
			orderBy = SQL.substring(posOrder);
			retSQL.append(SQL.substring(0, posOrder));
		} else {
			posOrder = SQL.lastIndexOf(" ORDER BY ");
			if(posOrder != -1) {
				orderBy = SQL.substring(posOrder);
				retSQL.append(SQL.substring(0, posOrder));
			} else {
				retSQL.append(SQL);
			}
		}
			

		//	Parse SQL
		AccessSqlParser asp = new AccessSqlParser(retSQL.toString());
		AccessSqlParser.TableInfo[] ti = asp.getTableInfo(asp.getMainSqlIndex());

		//  Do we have to add WHERE or AND
		if (asp.getMainSql().indexOf(" WHERE ") == -1)
			retSQL.append(" WHERE ");
		else
			retSQL.append(" AND ");

		//	Use First Table
		String tableName = "";
		if (ti.length > 0)
		{
			tableName = ti[0].getSynonym();
			if (tableName.length() == 0)
				tableName = ti[0].getTableName();
		}
		if (TableNameIn != null && !tableName.equals(TableNameIn))
		{
			String msg = "TableName not correctly parsed - TableNameIn="
				+ TableNameIn + DASH + asp;
			if (ti.length > 0)
				msg += " - #1 " + ti[0];
			msg += "\n = " + SQL;
			log.log(Level.SEVERE, msg);
			Trace.printStack();
			tableName = TableNameIn;
		}

		//	Client Access
		if (fullyQualified)
			retSQL.append(tableName).append(".");
		retSQL.append(getClientWhere(rw));

		//	Org Access
		if (!isAccessAllOrgs())
		{
			retSQL.append(" AND ");
			if (fullyQualified)
				retSQL.append(tableName).append(".");
			retSQL.append(getOrgWhere(rw));
		}

		//	** Data Access	**
		for (int i = 0; i < ti.length; i++)
		{
			String TableName = ti[i].getTableName();
			int AD_Table_ID = getAD_Table_ID (TableName);
			//	Data Table Access
			if (AD_Table_ID != 0 && !isTableAccess(AD_Table_ID, !rw))
			{
				retSQL.append(" AND 1=3");	//	prevent access at all
				log.fine("No access to AD_Table_ID=" + AD_Table_ID
					+ DASH + TableName + DASH + retSQL);
				break;	//	no need to check further
			}

			//	Data Column Access



			//	Data Record Access
			String keyColumnName = "";
			if (fullyQualified)
			{
				keyColumnName = ti[i].getSynonym();	//	table synonym
				if (keyColumnName.length() == 0)
					keyColumnName = TableName;
				keyColumnName += ".";
			}
			keyColumnName += TableName + "_ID";	//	derived from table

		//	log.fine("addAccessSQL - " + TableName + "(" + AD_Table_ID + ") " + keyColumnName);
			String recordWhere = getRecordWhere (AD_Table_ID, keyColumnName, rw);
			if (recordWhere.length() > 0)
			{
				retSQL.append(" AND ").append(recordWhere);
				log.finest("Record access - " + recordWhere);
			}
		}	//	for all table info

		//	Dependent Records (only for main SQL)
		String mainSql = asp.getMainSql();
		loadRecordAccess(false);
		int AD_Table_ID = 0;
		String whereColumnName = null;
		ArrayList<Integer> includes = new ArrayList<Integer>();
		ArrayList<Integer> excludes = new ArrayList<Integer>();
		for (int i = 0; i < m_recordDependentAccess.length; i++)
		{
			String columnName = m_recordDependentAccess[i].getKeyColumnName
				(asp.getTableInfo(asp.getMainSqlIndex()) );
			if (columnName == null)
				continue;	//	no key column
			int posColumn = mainSql.indexOf(columnName);
			if (posColumn == -1)
				continue;
			//	we found the column name - make sure it's a clumn name
			char charCheck = mainSql.charAt(posColumn-1);	//	before
			if (!(charCheck == ',' || charCheck == '.' || charCheck == ' ' || charCheck == '('))
				continue;
			charCheck = mainSql.charAt(posColumn+columnName.length());	//	after
			if (!(charCheck == ',' || charCheck == ' ' || charCheck == ')'))
				continue;

			if (AD_Table_ID != 0 && AD_Table_ID != m_recordDependentAccess[i].getAD_Table_ID())
				retSQL.append(getDependentAccess(whereColumnName, includes, excludes));

			AD_Table_ID = m_recordDependentAccess[i].getAD_Table_ID();
			//	*** we found the column in the main query
			if (m_recordDependentAccess[i].isExclude())
			{
				excludes.add(m_recordDependentAccess[i].getRecord_ID());
				log.fine("Exclude " + columnName + DASH + m_recordDependentAccess[i]);
			}
			else if (!rw || !m_recordDependentAccess[i].isReadOnly())
			{
				includes.add(m_recordDependentAccess[i].getRecord_ID());
				log.fine("Include " + columnName + DASH + m_recordDependentAccess[i]);
			}
			whereColumnName = getDependentRecordWhereColumn (mainSql, columnName);
		}	//	for all dependent records
		retSQL.append(getDependentAccess(whereColumnName, includes, excludes));
		//
		retSQL.append(orderBy);
		log.finest(retSQL.toString());
		return retSQL.toString();
	}	//	addAccessSQL

	/**
	 * 	Get Dependent Access
	 *	@param whereColumnName column
	 *	@param includes ids to include
	 *	@param excludes ids to exclude
	 *	@return where clause starting with AND or ""
	 */
	private String getDependentAccess(String whereColumnName,
		ArrayList<Integer> includes, ArrayList<Integer> excludes)
	{
		if (includes.size() == 0 && excludes.size() == 0)
			return "";
		if (includes.size() != 0 && excludes.size() != 0)
			log.warning("Mixing Include and Excluse rules - Will not return values");

		StringBuffer where = new StringBuffer(" AND ");
		if (includes.size() == 1)
			where.append(whereColumnName).append("=").append(includes.get(0));
		else if (includes.size() > 1)
		{
			where.append(whereColumnName).append(" IN (");
			for (int ii = 0; ii < includes.size(); ii++)
			{
				if (ii > 0)
					where.append(",");
				where.append(includes.get(ii));
			}
			where.append(")");
		}
		else if (excludes.size() == 1)
			where.append(whereColumnName).append("<>").append(excludes.get(0));
		else if (excludes.size() > 1)
		{
			where.append(whereColumnName).append(" NOT IN (");
			for (int ii = 0; ii < excludes.size(); ii++)
			{
				if (ii > 0)
					where.append(",");
				where.append(excludes.get(ii));
			}
			where.append(")");
		}
		log.finest(where.toString());
		return where.toString();
	}	//	getDependentAccess


	/**
	 * 	Get Dependent Record Where clause
	 *	@param mainSql sql to examine
	 *	@param columnName columnName
	 *	@return where clause column "x.columnName"
	 */
	private String getDependentRecordWhereColumn (String mainSql, String columnName)
	{
		String retValue = columnName;	//	if nothing else found
		int index = mainSql.indexOf(columnName);
		//	see if there are table synonym
		int offset = index - 1;
		char c = mainSql.charAt(offset);
		if (c == '.')
		{
			StringBuffer sb = new StringBuffer();
			while (c != ' ' && c != ',' && c != '(')	//	delimeter
			{
				sb.insert(0, c);
				c = mainSql.charAt(--offset);
			}
			sb.append(columnName);
			return sb.toString();
		}
		return retValue;
	}	//	getDependentRecordWhereColumn



	/**
	 *	UPADATE - Can I Update the record.
	 *  Access error info (AccessTableNoUpdate) is saved in the log
	 *
	 * @param AD_Client_ID comntext to derive client/org/user level
	 * @param AD_Org_ID number of the current window to retrieve context
	 * @param AD_Table_ID table
	 * @param Record_ID record id
	 * @param createError boolean
	 * @return true if you can update
	 * see org.compiere.model.MTable#dataSave(boolean)
	 **/
	public boolean canUpdate (int AD_Client_ID, int AD_Org_ID,
		int AD_Table_ID, int Record_ID, boolean createError)
	{
		String userLevel = getUserLevel();	//	Format 'SCO'

		if (userLevel.indexOf('S') != -1)	//	System cannot change anything
			return true;
				
		boolean	retValue = true;
		String whatMissing = "";
		
		//INPROCESS : Validamos el nivel de la tabla, si es Client to System, entonces puede actualizar
		boolean isClientToSystem = 
			(DB.getSQLValue(null, accessLevel, AD_Table_ID) > 0);
		
		if(isClientToSystem)
			return true;

		//	System == Client=0 & Org=0
		if (AD_Client_ID == 0 && AD_Org_ID == 0
			&& userLevel.charAt(0) != 'S')
		{
			retValue = false;
			whatMissing += "S";
		}

		//	Client == Client!=0 & Org=0
		else if (AD_Client_ID != 0 && AD_Org_ID == 0
			&& userLevel.charAt(1) != 'C')
		{
			if (userLevel.charAt(2) == 'O' && isOrgAccess(AD_Org_ID, true))
				;	//	Client+Org with access to *
			else
			{
				retValue = false;
				whatMissing += "C";
			}
		}

		//	Organization == Client!=0 & Org!=0
		else if (AD_Client_ID != 0 && AD_Org_ID != 0
			&& userLevel.charAt(2) != 'O')
		{
			retValue = false;
			whatMissing += "O";
		}

		// Client Access: Verify if the role has access to the given client - teo_sarca, BF [ 1982398 ]
		if (retValue) {
			retValue = isClientAccess(AD_Client_ID, true); // r/w access
		}
		
		// Org Access: Verify if the role has access to the given organization - teo_sarca, patch [ 1628050 ]
		if (retValue) {
			retValue = isOrgAccess(AD_Org_ID, true); // r/w access
			whatMissing="W";
		}
		
		//	Data Access
		if (retValue)
			retValue = isTableAccess(AD_Table_ID, false);

		if (retValue && Record_ID != 0)
			retValue = isRecordAccess(AD_Table_ID, Record_ID, false);

		if (!retValue && createError)
		{
			log.saveError("AccessTableNoUpdate",
				"AD_Client_ID=" + AD_Client_ID
				+ ", AD_Org_ID=" + AD_Org_ID + ", UserLevel=" + userLevel
				+ " => missing=" + whatMissing);
			log.warning (toString());
		}
		return retValue;
	}	//	canUpdate

	/**
	 *	VIEW - Can I view record in Table with given TableLevel.
	 *  <code>
	 *	TableLevel			S__ 100		4	System info
	 *						SCO	111		7	System shared info
	 *						SC_ 110		6	System/Client info
	 *						_CO	011		3	Client shared info
	 *						_C_	011		2	Client shared info
	 *						__O	001		1	Organization info
	 *  </code>
	 *
	 * 	@param ctx	context
	 *	@param TableLevel	AccessLevel
	 *	@return	true/false
	 *  Access error info (AccessTableNoUpdate, AccessTableNoView) is saved in the log
	 *  see org.compiere.model.MTabVO#loadTabDetails(MTabVO, ResultSet)
	 **/
	public boolean canView(Properties ctx, String TableLevel)
	{
		String userLevel = getUserLevel();	//	Format 'SCO'

		boolean retValue = true;

		//	7 - All
		if (X_AD_Table.ACCESSLEVEL_All.equals(TableLevel))
			retValue = true;

		//	4 - System data requires S
		else if (X_AD_Table.ACCESSLEVEL_SystemOnly.equals(TableLevel)
			&& userLevel.charAt(0) != 'S')
			retValue = false;

		//	2 - Client data requires C
		else if (X_AD_Table.ACCESSLEVEL_ClientOnly.equals(TableLevel)
			&& userLevel.charAt(1) != 'C')
			retValue = false;

		//	1 - Organization data requires O
		else if (X_AD_Table.ACCESSLEVEL_Organization.equals(TableLevel)
			&& userLevel.charAt(2) != 'O')
			retValue = false;

		//	3 - Client Shared requires C or O
		else if (X_AD_Table.ACCESSLEVEL_ClientPlusOrganization.equals(TableLevel)
			&& (!(userLevel.charAt(1) == 'C' || userLevel.charAt(2) == 'O')) )
				retValue = false;

		//	6 - System/Client requires S or C
		else if (X_AD_Table.ACCESSLEVEL_SystemPlusClient.equals(TableLevel)
			&& (!(userLevel.charAt(0) == 'S' || userLevel.charAt(1) == 'C')) )
			retValue = false;

		if (retValue)
			return retValue;

		//  Notification
		/**
		if (forInsert)
			log.saveError("AccessTableNoUpdate",
				"(Required=" + TableLevel + "("
				+ getTableLevelString(Env.getAD_Language(ctx), TableLevel)
				+ ") != UserLevel=" + userLevel);
		else
		**/
			log.saveError("AccessTableNoView",
				"Required=" + TableLevel + "("
				+ getTableLevelString(Env.getAD_Language(ctx), TableLevel)
				+ ") != UserLevel=" + userLevel);
		log.info (toString());
		return retValue;
	}	//	canView


	/**
	 *	Returns clear text String of TableLevel
	 *  @param AD_Language language
	 *  @param TableLevel level
	 *  @return info
	 */
	private String getTableLevelString (String AD_Language, String TableLevel)
	{
		String level = TableLevel + "??";
		if (TableLevel.equals("1"))
			level = "AccessOrg";
		else if (TableLevel.equals("2"))
			level = "AccessClient";
		else if (TableLevel.equals("3"))
			level = "AccessClientOrg";
		else if (TableLevel.equals("4"))
			level = "AccessSystem";
		else if (TableLevel.equals("6"))
			level = "AccessSystemClient";
		else if (TableLevel.equals("7"))
			level = "AccessShared";

		return Msg.getMsg(AD_Language, level);
	}	//	getTableLevelString

	/**
	 * 	Get Table ID from name
	 *	@param tableName table name
	 *	@return AD_Table_ID or 0
	 */
	private int getAD_Table_ID (String tableName)
	{
		loadTableInfo(false);
		Integer ii = (Integer)m_tableName.get(tableName);
		if (ii != null)
			return ii.intValue();
	//	log.log(Level.WARNING,"getAD_Table_ID - not found (" + tableName + ")");
		return 0;
	}	//	getAD_Table_ID

	/**
	 * 	Return Where clause for Record Access
	 *	@param AD_Table_ID table
	 *	@param keyColumnName (fully qualified) key column name
	 *	@param rw true if read write
	 *	@return where clause or ""
	 */
	private String getRecordWhere (int AD_Table_ID, String keyColumnName, boolean rw)
	{
		loadRecordAccess(false);
		//
		StringBuffer sbInclude = new StringBuffer();
		StringBuffer sbExclude = new StringBuffer();
		//	Role Access
		for (int i = 0; i < m_recordAccess.length; i++)
		{
			if (m_recordAccess[i].getAD_Table_ID() == AD_Table_ID)
			{
				//	NOT IN (x)
				if (m_recordAccess[i].isExclude())
				{
					if (sbExclude.length() == 0)
						sbExclude.append(keyColumnName)
							.append(" NOT IN (");
					else
						sbExclude.append(",");
					sbExclude.append(m_recordAccess[i].getRecord_ID());
				}
				//	IN (x)
				else if (!rw || !m_recordAccess[i].isReadOnly())	//	include
				{
					if (sbInclude.length() == 0)
						sbInclude.append(keyColumnName)
							.append(" IN (");
					else
						sbInclude.append(",");
					sbInclude.append(m_recordAccess[i].getRecord_ID());
				}
			}
		}	//	for all Table Access

		StringBuffer sb = new StringBuffer();
		if (sbExclude.length() > 0)
			sb.append(sbExclude).append(")");
		if (sbInclude.length() > 0)
		{
			if (sb.length() > 0)
				sb.append(" AND ");
			sb.append(sbInclude).append(")");
		}

		//	Don't ignore Privacy Access
		if (!isPersonalAccess())
		{
			String lockedIDs = MPrivateAccess.getLockedRecordWhere(AD_Table_ID, m_AD_User_ID);
			if (lockedIDs != null)
			{
				if (sb.length() > 0)
					sb.append(" AND ");
				sb.append(keyColumnName).append(lockedIDs);
			}
		}
		//
		return sb.toString();
	}	//	getRecordWhere

	
	 	
	
	/**
	 * 	Show (Value) Preference Menu
	 *	@return true if preference type is not None
	 */
	public boolean isShowPreference()
	{
		return !MRole.PREFERENCETYPE_None.equals(getPreferenceType());
	}	//	isShowPreference

	/**
	 * 	Org Access Summary
	 */
	class OrgAccess
	{
		/**
		 * 	Org Access constructor
		 *	@param ad_Client_ID client
		 *	@param ad_Org_ID org
		 *	@param readonly r/o
		 */
		public OrgAccess (int ad_Client_ID, int ad_Org_ID, boolean readonly)
		{
			this.AD_Client_ID = ad_Client_ID;
			this.AD_Org_ID = ad_Org_ID;
			this.readOnly = readonly;
		}
		/** Client				*/
		public int AD_Client_ID = 0;
		/** Organization		*/
		public int AD_Org_ID = 0;
		/** Read Only			*/
		public boolean readOnly = true;


		/**
		 * 	Equals
		 *	@param obj object to compare
		 *	@return true if equals
		 */
		public boolean equals (Object obj)
		{
			if (obj != null && obj instanceof OrgAccess)
			{
				OrgAccess comp = (OrgAccess)obj;
				return comp.AD_Client_ID == AD_Client_ID
					&& comp.AD_Org_ID == AD_Org_ID;
			}
			return false;
		}	//	equals


		/**
		 * 	Hash Code
		 *	@return hash Code
		 */
		public int hashCode ()
		{
			return (AD_Client_ID*7) + AD_Org_ID;
		}	//	hashCode

		/**
		 * 	Extended String Representation
		 *	@return extended info
		 */
		public String toString ()
		{
			String clientName = "System";
			if (AD_Client_ID != 0)
				clientName = MClient.get(getCtx(), AD_Client_ID).getName();
			String orgName = "*";
			if (AD_Org_ID != 0)
				orgName = MOrg.get(getCtx(), AD_Org_ID).getName();
			StringBuffer sb = new StringBuffer();
			sb.append(Msg.translate(getCtx(), "AD_Client_ID")).append("=")
				.append(clientName).append(DASH)
				.append(Msg.translate(getCtx(), "AD_Org_ID")).append("=")
				.append(orgName);
			if (readOnly)
				sb.append(" r/o");
			return sb.toString();
		}	//	toString

	}	//	OrgAccess

	/**
	 * Checks the access rights of the given role/client for the given document actions.
	 * @param clientId
	 * @param docTypeId
	 * @param options
	 * @param maxIndex
	 * @return number of valid actions in the String[] options
	 * @see metas-2009_0021_AP1_G94
	 * Merge ADempiere trunk_rev14653
	 */
	public int checkActionAccess(int clientId, int docTypeId, String[] options, int maxIndex)
	{
		if (maxIndex <= 0)
			return maxIndex;
		//
		final Vector<String> validOptions = new Vector<String>();
		final List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(docTypeId);
		//
		final StringBuffer sql_values = new StringBuffer();
		for (int i = 0; i < maxIndex; i++)
		{
			if (sql_values.length() > 0)
				sql_values.append(",");
			sql_values.append("?");
			params.add(options[i]);
		}
		//
		final String sql = "SELECT DISTINCT rl.Value FROM AD_Document_Action_Access a"
				+ " INNER JOIN AD_Ref_List rl ON (rl.AD_Reference_ID=135 and rl.AD_Ref_List_ID=a.AD_Ref_List_ID)"
				+ " WHERE a.IsActive='Y' AND a.AD_Client_ID=? AND a.C_DocType_ID=?" // #1,2
					+ " AND rl.Value IN ("+sql_values+")"
					+ " AND "+getIncludedRolesWhereClause("a.AD_Role_ID", params)
		;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String op = rs.getString(1);
				validOptions.add(op);
			}
			validOptions.toArray(options);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		int newMaxIndex = validOptions.size(); 
		return newMaxIndex;
	}
	
	/** List of included roles. Do not access directly */
	private List<MRole> m_includedRoles = null;
	
	/**
	 * 
	 * @param AD_Rol_ID
	 * @return
	 * @throws Exception
	 * @author Omar Torres Atonal
	 * @modified Lorena Lama
	 */
	public static List<LabelValueBean> getEstacionXRol(int AD_Rol_ID)
			throws Exception {
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuilder sql = new StringBuilder(
			   "select upper(est.value || ' - ' || est.name) as name,upper(est.value) as value \n" + 
			   "from ad_role rol  \n" +
			   "inner join EXME_RoleEstServ rle on(rle.ad_role_id=rol.ad_role_id)  \n" +   
			   "inner join EXME_Estserv est on(est.exme_estserv_id=rle.exme_estserv_id)  \n" +   
			   "where rle.isActive='Y' and est.isActive='Y' and rol.isActive='Y'  and rol.ad_role_id= ?" + //AD_Rol_ID +  
			   "\n order by ascii(trim(value)),value") ;

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Rol_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

//    /**
//     * Busca Inf de su Rol en base a una ruta
//     * @return
//     * @author hector perez
//     */
//    public static boolean estaEnRol(int rolID, String ruta, String trxName){
//        boolean estaEnRol = false;
//        
//        StringBuffer sql = new StringBuffer();
//        sql.append("select s.exme_struts_id, s.ruta ")
//            .append("from exme_struts s inner join exme_struts_access sa ")
//            .append("on (s.exme_struts_id = sa.exme_struts_id) ") 
//            .append("WHERE sa.ad_role_id = ")
//            .append(rolID)
//            .append("and s.ruta like '%" + ruta + "%' and sa.isactive='Y'");
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = DB.prepareStatement (sql.toString(), trxName);
//            rs = pstmt.executeQuery ();
//            if(rs.next()) {
//                estaEnRol = true;
//            }
//            
//        } catch (Exception e) {
//        	s_log.log(Level.SEVERE, "estaEnRol", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//        
//        return estaEnRol;
//    }

	public static MRole[] getAll(Properties ctx, MRole[] roles) {
		String sql = MEXMELookupInfo.addAccessLevelSQL(ctx,
				"SELECT * FROM AD_Role where isActive = 'Y' ", "AD_Role");
		sql += " order by name";

		ArrayList<MRole> list = new ArrayList<MRole>();
		List<MRole> seleccion = Arrays.asList(roles);
		PreparedStatement pstmt = null;
		ResultSet rs = null; // Expert
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MRole toAdd = new MRole(ctx, rs, null);
				if(roles != null)
					if(seleccion.contains(toAdd)){
						toAdd.setSeleccionado("true");
					}
				list.add(toAdd);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		MRole[] retValue = new MRole[list.size()];
		list.toArray(retValue);
		return retValue;
	}
    
	public static MRole[] getFromReport(Properties ctx, int EXME_Reporte_ID) {
		StringBuilder st = new StringBuilder("select * from AD_ROLE r ");
		st
				.append(
						"inner join EXME_Reporte_Access a on a.AD_Role_ID = r.AD_Role_ID ")
				.append(
						"inner join EXME_Reporte rep on rep.EXME_Reporte_ID = a.EXME_Reporte_ID ")
				.append("where rep.EXME_Reporte_ID = ? and r.isActive = 'Y' ");

		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
				.toString(), "AD_Role", "r"));
		st.append(" order by r.Name");

		ArrayList<MRole> list = new ArrayList<MRole>();
		PreparedStatement pstmt = null;
		ResultSet rs = null; // Expert
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, EXME_Reporte_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MRole(ctx, rs, null));
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		MRole[] retValue = new MRole[list.size()];
		list.toArray(retValue);
		return retValue;
	}
    
	
    @Override
	public int hashCode() {
    	int hash = 0;
        if(getAD_Role_ID() != 0){
        	hash = getAD_Role_ID();
        }
        return hash;
	}

	private String seleccionado = "false";

	public String getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}

    /**
     * Validate if the role has access to the form
     * @param ctx
     * @param className
     * @param trxName
     * @author Lorena Lama
     * @return
     */
    public static boolean validateFormRole(Properties ctx, String className, String trxName){
		int form_id = MForm.getForm_ID(className, trxName);
		if (form_id <= 0) {
			return false;
		}
		MRole role = getDefault();
		
		//Se agrego la condicion para evitar que tronara cuando
		//se entra por ingreso de paciente
		if(role!=null && role.getAD_Role_ID()>0){
			Boolean b = role.getFormAccess(form_id);
			if(b == null){
				return false;
			}else{
				return b;
			}
		}
		else{
			return false;
		}
	}
    
    /**
     * Gets role for patient access
     * @param ad_user_id
     * @return ad_role_id
     * @author Lorena Lama
     */
	public static int getPatientAccessRole(int ad_user_id) {
		StringBuffer sql = new StringBuffer("SELECT rol.AD_Role_ID FROM AD_Role rol "+
					" INNER JOIN AD_User_ROLES ur on ur.ad_ROLE_id = rol.ad_ROLE_id "+
					" WHERE rol.isForPatientAccess = 'Y' AND ur.AD_User_ID = ? ");
		return DB.getSQLValue(null, sql.toString(), ad_user_id);
	}

	/**
	 * Metodo para obtener id de rol
	 * @param ctx
	 * @param name
	 * @param trxName
	 * @return
	 */
	public static int getIdByNameOrg(Properties ctx, String name, String trxName){
		int resultado = 0;
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("select * from ad_role where upper(name) = ? ");
			sql.append("and ad_org_id = ?");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = rs.getInt(MRole.COLUMNNAME_AD_Role_ID);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "Closing rs and pstmt", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return resultado;
	}

	/**
	 * 
	 * @return unmodifiable list of included roles
	 * @see metas-2009_0021_AP1_G94
	 * Merge ADempiere trunk_rev14653
	 */
	public List<MRole> getIncludedRoles(boolean recursive) {
		if (!recursive) {
			List<MRole> list = this.m_includedRoles;
			if (list == null)
				list = new ArrayList<MRole>();
			return Collections.unmodifiableList(list);
		} else {
			List<MRole> list = new ArrayList<MRole>();
			if (m_includedRoles != null) {
				for (MRole role : m_includedRoles) {
					list.add(role);
					list.addAll(role.getIncludedRoles(true));
				}
			}
			return list;
		}
	}
	
	/**
	 * Get Role Where Clause.
	 * It will look something like myalias.AD_Role_ID IN (?, ?, ?).
	 * @param roleColumnSQL role columnname or role column SQL (e.g. myalias.AD_Role_ID) 
	 * @param params a list where the method will put SQL parameters.
	 * 				If null, this method will generate a not parametrized query 
	 * @return role SQL where clause
	 * Merge ADempiere trunk_rev14653
	 */
	public String getIncludedRolesWhereClause(String roleColumnSQL, List<Object> params) {
		StringBuffer whereClause = new StringBuffer();
		if (params != null) {
			whereClause.append("?");
			params.add(getAD_Role_ID());
		} else {
			whereClause.append(getAD_Role_ID());
		}
		//
		for (MRole role : getIncludedRoles(true)) {
			if (params != null) {
				whereClause.append(",?");
				params.add(role.getAD_Role_ID());
			} else {
				whereClause.append(",").append(role.getAD_Role_ID());
			}
		}
		//
		whereClause.insert(0, roleColumnSQL + " IN (").append(")");
		return whereClause.toString();
	}
	
	/**
	 * Valida si existe al menos un rol del tipo enviado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param roleType
	 *            Tipo de Role
	 * @param trxName
	 *            Transaccion
	 * @return
	 */
	public static int checkRoles(Properties ctx, String roleType, String trxName) {
		int retValue = -1;
		StringBuilder st = new StringBuilder("select AD_Role_ID from AD_Role ");
		st.append(" where isActive = 'Y' and roleType = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "AD_Role"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setString(1, roleType);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getInt(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {

			if (rs != null) {
				DB.close(rs);
			}
			if (pstmt != null) {
				DB.close(pstmt);
			}
		}
		return retValue;
	}
	
	/**
	 * Areas del Rol para configuradores
	 * 
	 * @param ctx
	 *            Contexto
	 * @param id
	 *            ID del rol
	 * @param trxName
	 *            Transaccion
	 * @return
	 */
	public static List<MEXMEArea> getAreas(Properties ctx, int id, String trxName) {
		List<MEXMEArea> lst = new ArrayList<MEXMEArea>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  EXME_Area_ID ");
		sql.append("FROM ");
		sql.append("  EXME_RoleArea ");
		sql.append("WHERE ");
		sql.append("  AD_Role_ID = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMEArea(ctx, rs.getInt(1), trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {

			if (rs != null) {
				DB.close(rs);
			}
			if (pstmt != null) {
				DB.close(pstmt);
			}
		}
		return lst;
	}
	
	public static String getOrgNameFromRole(Properties ctx, int adRole, String trxName) {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  org.name ");
		sql.append("FROM ");
		sql.append("  AD_Role role ");
		sql.append("  INNER JOIN AD_Org org ");
		sql.append("  ON (org.AD_Org_ID = role.AD_Org_ID) ");
		sql.append("WHERE ");
		sql.append("  role.AD_Role_ID = ? ");
		try {
			name = DB.getSQLValueString(trxName, sql.toString(), adRole);
		} catch (Exception ex) {
			s_log.log(Level.FINEST, null, ex);
		}
		return name;
	}
	
	public static int getIdRoleType(String roleType, int orgID, int clientID){
		int id=0;
		//select * from ad_role where roletype ='ADC' and ad_org_id=10001000 and ad_client_id = 10001000 and isactive ='Y'
			StringBuilder sql = new StringBuilder();
		sql.append(" select * from ad_role where roletype = ? ");
		sql.append(" and ad_org_id= ?");
		sql.append(" and ad_client_id = ?");
		//sql.append(" and isactive ='Y'");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, roleType);
			pstmt.setInt(2, orgID);
			pstmt.setInt(3, clientID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id= rs.getInt("AD_Role_ID");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {

			if (rs != null) {
				DB.close(rs);
			}
			if (pstmt != null) {
				DB.close(pstmt);
			}
		}
		return id ;
	}

	/**
	 * Obtiene todos los roles de acuerdo al cliente y organizacion especifica
	 * y elimina los perfiles a excepcion de el Admin y el user que son creados
	 * con organizacion 0.
	 * 
	 * @param ctx el contexto de la aplicacion.
	 * @param iaAd_Client_Id el id del cliente.
	 * @param iaAD_Org_ID el id de la organizacion.
	 * @param saTrxName la transaccion.
	 * @return true si el borrado fue exitoso, false si hubo error.
	 * @author Jesus Cantu.
	 */
	public static boolean cleanRoles(Properties ctx, int iaAd_Client_Id, 
			int iaAD_Org_ID, String saTrxName) {
		
		boolean blError = true;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("Select * from AD_Role where isActive = 'Y'");
		sql.append(" AND AD_Client_ID = ?");
		sql.append(" AND AD_Org_ID = ?");
			
		PreparedStatement pstmt = null;
		ResultSet rs = null; // Expert

		try {
			pstmt = DB.prepareStatement(sql.toString(), saTrxName);
			
			pstmt.setInt(1, iaAd_Client_Id);
			pstmt.setInt(2, iaAD_Org_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MRole toAdd = new MRole(ctx, rs.getInt("AD_Role_Id"), saTrxName);
				
				//Eliminar los roles por cada uno de los registros encontrados
				toAdd.deleteAccessRecords();
			}
		} catch (Exception e) {
			blError = false;
			s_log.log(Level.SEVERE, "MRole.cleanRoles - sql = " + sql.toString(), e);
		}
		finally	{
			DB.close(rs, pstmt);
		}

		return blError;
	}
	
	/**
	 * Obtiene el Perfil Admin del cliente  segun el nombre y AD_Client enviados.
	 * Metodo creado con la finalidad de relacionar todas las estaciones de servicio creadas
	 * en el wizard al perfil admin. 
	 * 
	 * @param ctx el contexto de la aplicacion.
	 * @param iaAd_Client_Id el id del cliente.
	 * @param saName el nombre del cliente. Si es nulo buscar con like a '%Admin'
 	 * @param saTrxName la transaccion.
	 * @return un objeto MRole con la información del perfil Admin.
	 * @author jcantu Jesus Cantu el 13 de Junio del 2012.
	 */
	public static MRole getPerfilAdmin(Properties ctx, int iaAD_Client_Id, 
			String saName, String saTrxName) {
		
		MRole objAdmin = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("Select * from AD_Role where isActive = 'Y'");
		sql.append(" AND AD_Client_ID = ?");
		
		if (saName != null) {
			sql.append(" AND upper (name) = ?");
		} else {
			sql.append(" AND upper (name) like upper('%Admin') ");	
		}
		
		
			
		PreparedStatement pstmt = null;
		ResultSet rs = null; 

		try {
			pstmt = DB.prepareStatement(sql.toString(), saTrxName);
			
			pstmt.setInt(1, iaAD_Client_Id);
			
			if (saName != null) {
				pstmt.setString(2, saName.toUpperCase());
			}
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				objAdmin = new MRole(ctx, rs.getInt("AD_Role_Id"), saTrxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MRole.getPerfilAdmin - sql = " + sql.toString(), e);
		}
		finally	{
			DB.close(rs, pstmt);
		}
		
		return objAdmin;
	}
	
}	//	MRole
