/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;

/**
 *	Product PO Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MProductPO.java,v 1.3 2006/08/11 02:26:22 mrojas Exp $
 */
public class MProductPO extends X_M_Product_PO
{
	/** serialVersionUID */
	private static final long serialVersionUID = 737462845736801389L;

	/**
	 * 	Get current PO of Product
	 *	@param M_Product_ID product
	 *	@return PO - current vendor first
	 */
	public static MProductPO[] getOfProduct (final Properties ctx, final int M_Product_ID, final String trxName)
	{
//		final ArrayList<MProductPO> list = new ArrayList<MProductPO>();
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
//		.append(" SELECT * FROM M_Product_PO ")
//		.append(" WHERE AD_Client_ID = ? ")
//		.append(" AND M_Product_ID=? ")
//		.append(" AND IsActive='Y' ")
//		.append(" ORDER BY IsCurrentVendor DESC");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try
//		{
//			pstmt = DB.prepareStatement (sql.toString(), trxName);
//			pstmt.setInt (1, M_Product_ID);
//			pstmt.setInt (2, Env.getAD_Client_ID(ctx));
//			rs = pstmt.executeQuery ();
//			if (rs.next ()){
//				list.add(new MProductPO (ctx, rs, trxName));
//			}
////			rs.close ();
////			pstmt.close ();
////			pstmt = null;
////			rs=null;
//		} catch (SQLException ex) {
//			s_log.log(Level.SEVERE, sql.toString(), ex);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close ();
//		}
//		catch (SQLException ex1)
//		{
//		}
//		pstmt = null;
		//
//		final MProductPO[] retValue = new MProductPO[list.size()];
//		list.toArray(retValue);
//		return retValue;
		return MProductPO.getOfProduct ( ctx, M_Product_ID, Env.getAD_Client_ID(ctx), trxName);
	}	//	getOfProduct

	/**
	 * 	Get current PO of Product
	 *	@param M_Product_ID product
	 *	@return PO - current vendor first
	 *  Se agrego el parametro de cliente, en el entendido que esto servira 
	 *  para la transaccion inicial de un producto cuando aun no tiene 
	 *  un costo por no tener una recepcion de material
	 */
	public static MProductPO[] getOfProduct (final Properties ctx, final int M_Product_ID, final int AD_Client_ID, final String trxName)
	{
		final ArrayList<MProductPO> list = new ArrayList<MProductPO>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT *               ")
		.append(" FROM  M_Product_PO     ")
		.append(" WHERE IsActive='Y'     ")
		.append(" AND   AD_Client_ID = ? ")
		.append(" AND   M_Product_ID = ? ")
		.append(" ORDER BY IsCurrentVendor DESC");

		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt (1, AD_Client_ID);
			pstmt.setInt (2, M_Product_ID);
			rs = pstmt.executeQuery ();

			if (rs.next ()){
				list.add(new MProductPO (ctx, rs, trxName));
			}

		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, sql.toString(), ex);
		} finally {
			DB.close(rs,pstmt);
		}
		//
		final MProductPO[] retValue = new MProductPO[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfProduct

	/** Static Logger					*/
	private static CLogger s_log = CLogger.getCLogger(MProductPO.class);

	/**
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param ignored ignored
	 */
	public MProductPO (Properties ctx, int ignored, String trxName)
	{
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		else
		{
		//	setM_Product_ID (0);	// @M_Product_ID@
		//	setC_BPartner_ID (0);	// 0
		//	setVendorProductNo (null);	// @Value@
			setIsCurrentVendor (true);	// Y
		}
	}	//	MProduct_PO
	
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MProductPO(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductPO

	//expert : miguel rojas : para obtener la informacion relativa a un proveedor
	/**
	 * 
	 */
	public static MProductPO getOfProduct(Properties ctx, int M_Product_ID, int C_BPartner_ID) {
		 final MRole role = MRole.getDefault(ctx, false);
		final StringBuilder sql = new StringBuilder()
		.append(role.addAccessSQL(" SELECT * FROM M_Product_PO WHERE IsActive='Y' ", "M_Product_PO", false, false))
		.append(" AND AD_Client_ID  = ? ")
		.append(" AND M_Product_ID  = ? ")
		.append(" AND C_BPartner_ID = ? ")
		.append(" ORDER BY IsCurrentVendor DESC");
	    
		PreparedStatement pstmt = null;
		MProductPO productPO = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), null);
			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			pstmt.setInt (2, M_Product_ID);
			pstmt.setInt (3, C_BPartner_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ()){
				productPO = new MProductPO (ctx, rs, null);
			}
//			rs.close ();
//			pstmt.close ();
//			pstmt = null;
//			rs=null;
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, "getOfProduct", ex);
		} finally {
			DB.close(rs,pstmt);
		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close ();
//		}
//		catch (SQLException ex1)
//		{
//		}
//		pstmt = null;
//		//
//		
		
		return productPO;
	}
	//expert : miguel rojas : para obtener la informacion relativa a un proveedor
	
	/**
	 * Metodo para obtener el ultimo registro PO de un producto (ultimo precio de compra)
	 * @param ctx
	 * @param productID
	 * @param trxName
	 * @return
	 */
	public static MProductPO getLastPO(Properties ctx, int productID, String trxName) {
		
		MProductPO productPO = null;

		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT * FROM M_PRODUCT_PO ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND AD_Client_ID = ? ")//#1
		.append(" AND M_PRODUCT_ID = ? ")//#2
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" ORDER BY CREATED DESC ");
			
		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, Env.getAD_Client_ID(ctx));
			psmt.setInt(2, productID);

			rs = psmt.executeQuery();

			if (rs.next()) {
				productPO = new MProductPO(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return productPO;
	}
	
	
	
	/**
	 * Obtiene el listado de productPO para un producto
	 * @param ctx
	 * @param productID
	 * @param trxName
	 * @return
	 */
	public static List<MProductPO> getPOLst (Properties ctx
			, int productID, boolean onlyActive
			/*, boolean addAccessLvl*/, String trxName){
		
		final List<MProductPO> list = new ArrayList<MProductPO>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT * FROM M_Product_PO ")
		.append(" WHERE AD_Client_ID = ? ")
		.append(" AND M_Product_ID= ? ")
		.append(onlyActive?" AND IsActive='Y' ":"")
//		if(addAccessLvl){
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		}
		.append(" ORDER BY Updated DESC");
				
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			pstmt.setInt (2, productID);
			rs = pstmt.executeQuery ();
		
			while (rs.next ()){
				list.add(new MProductPO (ctx, rs, trxName));
			}
//		
//			rs.close ();
//			pstmt.close ();
			
		}
		catch (SQLException ex){
			s_log.log(Level.SEVERE, sql.toString(), ex);
		}finally{
			DB.close(rs, pstmt);
			pstmt = null;
			rs=null;
		}
		
		
		return list;
	}	//	getPOLst


}	//	MProductPO
