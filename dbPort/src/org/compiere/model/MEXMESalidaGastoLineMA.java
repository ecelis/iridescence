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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 *	Inventory Material Allocation
 *	
 *  @author Jorg Janke
 *  @version $Id: MEXMESalidaGastoLineMA.java,v 1.3 2006/07/30 00:51:04 jjanke Exp $
 */
public class MEXMESalidaGastoLineMA extends X_EXME_SalidaGastoLineMA
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	/**
	 * 	Get Material Allocations for Line
	 *	@param ctx context
	 *	@param EXME_SalidaGastoLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MEXMESalidaGastoLineMA[] get (Properties ctx, int EXME_SalidaGastoLine_ID, String trxName)
	{
		ArrayList<MEXMESalidaGastoLineMA> list = new ArrayList<MEXMESalidaGastoLineMA>();
		String sql = "SELECT * FROM EXME_SalidaGastoLineMA WHERE EXME_SalidaGastoLine_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, EXME_SalidaGastoLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MEXMESalidaGastoLineMA (ctx, rs, trxName));
			
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			pstmt = null;
		}
		
		MEXMESalidaGastoLineMA[] retValue = new MEXMESalidaGastoLineMA[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**
	 * 	Delete all Material Allocation for Inventory
	 *	@param EXME_SalidaGasto_ID inventory
	 *	@param trxName transaction
	 *	@return number of rows deleted or -1 for error
	 */
	public static int deleteInventoryMA (int EXME_SalidaGasto_ID, String trxName)
	{
		String sql = "DELETE FROM EXME_SalidaGastoLineMA ma WHERE EXISTS "
			+ "(SELECT * FROM EXME_SalidaGastoLine l WHERE l.EXME_SalidaGastoLine_ID=ma.EXME_SalidaGastoLine_ID"
			+ " AND EXME_SalidaGasto_ID=" + EXME_SalidaGasto_ID + ")";
		return DB.executeUpdate(sql, trxName);
	}	//	deleteInventoryMA
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MEXMESalidaGastoLineMA.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param EXME_SalidaGastoLineMA_ID ignored
	 *	@param trxName trx
	 */
	public MEXMESalidaGastoLineMA (Properties ctx, int EXME_SalidaGastoLineMA_ID, String trxName)
	{
		super (ctx, EXME_SalidaGastoLineMA_ID, trxName);
		if (EXME_SalidaGastoLineMA_ID != 0)
			throw new IllegalArgumentException("Multi-Key");
	}	//	MEXMESalidaGastoLineMA

	/**
	 * 	Load Cosntructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MEXMESalidaGastoLineMA (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MEXMESalidaGastoLineMA
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param MovementQty qty
	 */
	public MEXMESalidaGastoLineMA (MEXMESalidaGastoLine parent, int M_AttributeSetInstance_ID, BigDecimal MovementQty)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setEXME_SalidaGastoLine_ID(parent.getEXME_SalidaGastoLine_ID());
		//
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setMovementQty(MovementQty);
	}	//	MEXMESalidaGastoLineMA
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MEXMESalidaGastoLineMA[");
		sb.append("EXME_SalidaGastoLine_ID=").append(getEXME_SalidaGastoLine_ID())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append(", Qty=").append(getMovementQty())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	MEXMESalidaGastoLineMA
