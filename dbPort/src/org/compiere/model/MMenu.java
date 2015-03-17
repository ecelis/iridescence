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
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Menu Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MMenu.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MMenu extends X_AD_Menu
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Get menues with where clause
	 * @param ctx context
	 * @param whereClause where clause w/o the actual WHERE
	 * @return MMenu
	 * @deprecated
	 */
	public static MMenu[] get (Properties ctx, String whereClause)
	{
		return get(ctx, whereClause, null);
	}
	
	/**
	 * Get menues with where clause
	 * @param ctx context
	 * @param whereClause where clause w/o the actual WHERE
	 * @param trxName transaction
	 * @return MMenu
	 */
	public static MMenu[] get (Properties ctx, String whereClause, String trxName)
	{
		String sql = "SELECT * FROM AD_Menu";
		if (whereClause != null && whereClause.length() > 0)
			sql += " WHERE " + whereClause;
		ArrayList<MMenu> list = new ArrayList<MMenu>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMenu (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMenu[] retValue = new MMenu[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MMenu.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Menu_ID id
	 *	@param trxName transaction
	 */
	public MMenu (Properties ctx, int AD_Menu_ID, String trxName)
	{
		super (ctx, AD_Menu_ID, trxName);
		if (AD_Menu_ID == 0)
		{
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsReadOnly (false);	// N
			setIsSOTrx (false);
			setIsSummary (false);
		//	setName (null);
		}
	}	//	MMenu

	/**
	 * 	Load Contrusctor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMenu (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMenu

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Reset info
		if (isSummary() && getAction() != null)
			setAction(null);
		String action = getAction();
		if (action == null)
			action = "";
		//	Clean up references
		if (getAD_Window_ID() != 0 && !action.equals(ACTION_Window))
			setAD_Window_ID(0);
		if (getAD_Form_ID() != 0 && !action.equals(ACTION_Form))
			setAD_Form_ID(0);
		if (getAD_Workflow_ID() != 0 && !action.equals(ACTION_WorkFlow))
			setAD_Workflow_ID(0);
		if (getAD_Workbench_ID() != 0 && !action.equals(ACTION_Workbench))
			setAD_Workbench_ID(0);
		if (getAD_Task_ID() != 0 && !action.equals(ACTION_Task))
			setAD_Task_ID(0);
		if (getAD_Process_ID() != 0 
			&& !(action.equals(ACTION_Process) || action.equals(ACTION_Report)))
			setAD_Process_ID(0);
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
		if (newRecord)
			insert_Tree(MTree_Base.TREETYPE_Menu);
		return success;
	}	//	afterSave

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			delete_Tree(MTree_Base.TREETYPE_Menu);
		return success;
	}	//	afterDelete
	
	/**
	 *  FR [ 1966326 ]
	 * 	get Menu ID
	 *	@param String Menu Name
	 *	@return int retValue
	 */
	public static int getMenu_ID(String menuName, boolean isSummary) {
		String sql = "SELECT AD_Menu_ID FROM AD_Menu WHERE Name = ? AND IsSummary = ? ";
		return DB.getSQLValue(null, sql, menuName, DB.TO_STRING(isSummary));
	}
	
	
	/**
	 *  get translated menu title
	 *  @author Lorena Lama
	 * @return String retValue
	 */
	public String getTitle() {
		return get_Translation(COLUMNNAME_Name);
	}
	
	/**
	 * Nombre del menú
	 * @param className
	 * @param trxname
	 * @return Primero de la lista
	 */
	public static String getTitle(final Properties ctx, final String className) {

		boolean isBase = Env.isBaseLanguage(Env.getAD_Language(ctx), X_AD_Menu.Table_Name);
		String retValue = className;
		if(isBase){
			final StringBuilder sql = new StringBuilder("SELECT menu.Name ")
			.append(" FROM AD_Menu menu")
			.append(" INNER JOIN AD_Form     fom ON  menu.AD_Form_ID = fom.AD_Form_ID ")
			.append("                            AND fom.isActive='Y'  ")
			.append("                            AND fom.className=?   ")//'com.ecaresoft.web.forms.movements.WReturnProductTray'
			.append(" WHERE menu.isActive='Y'");
			retValue =  DB.getSQLValueString(null, sql.toString(), className);
		} else {

			final StringBuilder sql = new StringBuilder("SELECT trl.Name ")
			.append(" FROM AD_Menu menu")
			.append(" INNER JOIN AD_Menu_Trl trl ON  trl.AD_Menu_ID  = menu.AD_Menu_ID")
			.append("                            AND trl.isActive='Y'  ")
			.append("                            AND trl.AD_Language=? ")
			.append(" INNER JOIN AD_Form     fom ON  menu.AD_Form_ID = fom.AD_Form_ID ")
			.append("                            AND fom.isActive='Y'  ")
			.append("                            AND fom.className=?   ")//'com.ecaresoft.web.forms.movements.WReturnProductTray'
			.append(" WHERE menu.isActive='Y' ");
			retValue =  DB.getSQLValueString(null, sql.toString(), Env.getAD_Language(ctx), className);
		}
		return retValue;
	} // get
}	//	MMenu
