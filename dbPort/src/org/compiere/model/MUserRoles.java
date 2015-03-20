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

import java.sql.*;
import java.util.*;

import java.util.logging.*;
import org.compiere.util.*;

/**
 *	User Roles Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MUserRoles.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MUserRoles extends X_AD_User_Roles
{
	/**
	 * 	Get User Roles Of Role
	 *	@param ctx context
	 *	@param AD_Role_ID role
	 *	@return array of user roles
	 */
	public static MUserRoles[] getOfRole (Properties ctx, int AD_Role_ID)
	{
		String sql = "SELECT * FROM AD_User_Roles WHERE AD_Role_ID=?";
		ArrayList<MUserRoles> list = new ArrayList<MUserRoles>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Role_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ()) {
				list.add (new MUserRoles (ctx, rs, null));
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "getOfRole", e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		MUserRoles[] retValue = new MUserRoles[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfRole

	/**
	 * 	Get User Roles Of User
	 *	@param ctx context
	 *	@param AD_User_ID role
	 *	@return array of user roles
	 */
	public static MUserRoles[] getOfUser (Properties ctx, int AD_User_ID)
	{
		String sql = "SELECT * FROM AD_User_Roles WHERE AD_User_ID=?";
		ArrayList<MUserRoles> list = new ArrayList<MUserRoles>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_User_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ()) {
				list.add (new MUserRoles (ctx, rs, null));
			}
			
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "getOfUser", e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		MUserRoles[] retValue = new MUserRoles[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfUser
	
	public static MUserRoles getOfUserRole(Properties ctx, int AD_User_ID, int AD_Role_ID,String trxName) {
		String sql = "SELECT * FROM AD_User_Roles WHERE AD_User_ID=? and AD_Role_ID=?";
		MUserRoles userRole = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_User_ID);
			pstmt.setInt(2, AD_Role_ID);
			rs = pstmt.executeQuery();
			if (rs.next()){
				userRole = new MUserRoles(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getOfUser", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return userRole;
	}

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MUserRoles.class);

	
	/**************************************************************************
	 * 	Persistence Constructor
	 *	@param ctx context
	 *	@param ignored invalid
	 *	@param trxName transaction
	 */
	public MUserRoles (Properties ctx, int ignored, String trxName)
	{
		super (ctx, ignored, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
	}	//	MUserRoles

	/**
	 * 	Load constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MUserRoles (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MUserRoles

	/**
	 * 	Full Constructor
	 *	@param ctx context
	 *	@param AD_User_ID user
	 *	@param AD_Role_ID role
	 *	@param trxName transaction
	 */
	public MUserRoles (Properties ctx, int AD_User_ID, int AD_Role_ID, String trxName)
	{
		this (ctx, 0, trxName);
		setAD_User_ID(AD_User_ID);
		setAD_Role_ID(AD_Role_ID);
	}	//	MUserRoles
	
	/** 
	 * 	Set User/Contact.
	 *	User within the system - Internal or Business Partner Contact
	 *	@param AD_User_ID user 
	 */
	public void setAD_User_ID (int AD_User_ID)
	{
		set_ValueNoCheck ("AD_User_ID", new Integer(AD_User_ID));
	}	//	setAD_User_ID
	
	/** 
	 * 	Set Role.
	 * 	Responsibility Role
	 * 	@param AD_Role_ID role 
	 **/
	public void setAD_Role_ID (int AD_Role_ID)
	{
		set_ValueNoCheck ("AD_Role_ID", new Integer(AD_Role_ID));
	}	//	setAD_Role_ID

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getAD_User_ID();
		result = prime * result + getAD_Role_ID();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MUserRoles other = (MUserRoles) obj;
		if (getAD_User_ID() != other.getAD_User_ID())
			return false;
		if (getAD_Role_ID() != other.getAD_Role_ID())
			return false;
		return true;
	}
	
}	//	MUserRoles
