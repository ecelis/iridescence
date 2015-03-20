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
import java.util.logging.Level;

import org.compiere.util.*;

/**
 * 	Cost Type Model
 *  @author Jorg Janke
 *  @version $Id: MCostType.java,v 1.2 2006/07/30 00:58:38 jjanke Exp $
 */
public class MCostType extends X_M_CostType
{
	
	private static CLogger slog = CLogger.getCLogger(MCostType.class);
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_CostType_ID id
	 *	@param trxName trx
	 */
	public MCostType (Properties ctx, int M_CostType_ID, String trxName)
	{
		super (ctx, M_CostType_ID, trxName);
	}	//	MCostType

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCostType (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCostType
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MCostType[");
		sb.append (get_ID()).append ("-").append (getName ()).append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		return true;
	}	//	beforeSave

	/**
	 * 	Before Delete
	 *	@return true if it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		for (int i = 0; i < ass.length; i++)
		{
			if (ass[i].getM_CostType_ID() == getM_CostType_ID())
			{
				log.saveError("CannotDelete", Msg.getElement(getCtx(), "C_AcctSchema_ID")
					+ " - " + ass[i].getName());
				return false;
			}
		}
		return true;
	}	//	beforeDelete
	
	   /**
     *  Get/Create Cost Record.
     *  CostingLevel is not validated
     *  @param product product
     *  @param M_AttributeSetInstance_ID costing level asi
     *  @param as accounting schema
     *  @param AD_Org_ID costing level org
     *  @param M_CostElement_ID element
     *  @return cost price or null
     */
    public static List<ValueNamePair> getCostTypes(Properties ctx, boolean clientOnly) {
    	List<ValueNamePair> lista = new ArrayList<ValueNamePair>();
		StringBuilder sql = new StringBuilder("SELECT * FROM M_CostType WHERE IsActive = 'Y' ");

		if(clientOnly){
			sql.append(" and ad_client_id in( ");
			sql.append(Env.getAD_Client_ID(ctx));
			sql.append(" ) ");
		}else{
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()){
				MCostType cost = new MCostType(ctx, rs, null);
				lista.add(new ValueNamePair(String.valueOf(cost.getM_CostType_ID()), cost.getName()));
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

        return lista;
    }   //  get   
	
}	//	MCostType
