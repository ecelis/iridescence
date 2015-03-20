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

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Tax Category Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MTaxCategory.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class MTaxCategory extends X_C_TaxCategory
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MTaxCategory.class);

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_TaxCategory_ID id
	 *	@param trxName trx
	 */
	public MTaxCategory (Properties ctx, int C_TaxCategory_ID, String trxName)
	{
		super (ctx, C_TaxCategory_ID, trxName);
		if (C_TaxCategory_ID == 0)
		{
		//	setName (null);
			setIsDefault (false);
		}
	}	//	MTaxCategory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs resukt set
	 *	@param trxName trx
	 */
	public MTaxCategory (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MTaxCategory
	
	/**
	 * 
	 * @param saTrxName el nombre de la transaccion actual.
	 * @param iaAd_Client_id el id del cliente.
	 * @param iaAD_Org_id el id de la Organizacion.
	 * @param saName el name a buscar como condicion.
	 * @return un entero que representa el C_TaxCategory_ID encontrado.
	 * @author jcantu Creado el 20 de Junio del 2012.
	 */
	public static int getTaxCategoryId(String saTrxName, 
			int iaAd_Client_id, int iaAD_Org_id, String saName) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		sql.append("Select C_TaxCategory_ID from C_TaxCategory where AD_Client_id = ?"); 
		sql.append(" and AD_Org_ID = ? And upper(name) = upper('");
		sql.append(saName);
		sql.append("') And isactive='Y' ");
		return DB.getSQLValue(saTrxName, sql.toString(), iaAd_Client_id, iaAD_Org_id);
		
	}
	
	/**
	 * Return tax category as Array for client
	 * @param ctx
	 * @param trxname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static MTaxCategory[] getTaxCategory(Properties ctx, String trxname) {

		MTaxCategory[] retVal = loadAllTaxCategory(ctx);

		Arrays.sort(retVal, new MTaxCategory(ctx, 0, trxname));

		return retVal;
	} // getTaxCategory

	/**
	 * 	Load tax category.
	 *
	 *	@param ctx context
	 */
	private static MTaxCategory[] loadAllTaxCategory (Properties ctx)
	{
		String sql = "SELECT * FROM C_TaxCategory WHERE IsActive='Y' and ad_client_id = " + Env.getAD_Client_ID(ctx);
		Statement stmt = DB.createStatement();
		ResultSet rs = null;

		ArrayList<MTaxCategory> category = new ArrayList<MTaxCategory>();

		try
		{
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MTaxCategory c = new MTaxCategory (ctx, rs, null);
				category.add(c);
				//	Country code of Client Language
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, stmt);
		}

		MTaxCategory[] arrCat = new MTaxCategory[category.size()];
		category.toArray(arrCat);

		return arrCat;

	}	//	loadAllTaxCategory

	public static List<MTaxCategory> getTaxCategoryLst(String whereClause, List<Object> params, String trxName) {
		return new Query(Env.getCtx(), Table_Name, whereClause, trxName)
			.addAccessLevelSQL(true)
			.setParameters(params).list();
	}
}	//	MTaxCategory
