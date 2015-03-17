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
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * 	Product Category Account Model
 *  @author Jorg Janke
 *  @version $Id: MProductCategoryAcct.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MProductCategoryAcct extends X_M_Product_Category_Acct
{
	/** serialVersionUID */
	private static final long serialVersionUID = -1785907366589029107L;

	/**
	 * 	Get Category Acct
	 *	@param ctx context
	 *	@param M_Product_Category_ID category
	 *	@param C_AcctSchema_ID acct schema
	 *	@param trxName trx
	 *	@return category acct
	 */
	public static MProductCategoryAcct get (Properties ctx, 
		int M_Product_Category_ID, int C_AcctSchema_ID, String trxName)
	{
		MProductCategoryAcct retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
			.append(" SELECT * FROM  M_Product_Category_Acct ")
			.append(" WHERE M_Product_Category_ID = ? ")
			.append(" AND C_AcctSchema_ID = ? ");
		
		try {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt (1, M_Product_Category_ID);
			pstmt.setInt (2, C_AcctSchema_ID);
			
			rs = pstmt.executeQuery ();
			if (rs.next ()){
				retValue = new MProductCategoryAcct (ctx, rs, trxName);
			}
		} catch (Exception e){
			s_log.log (Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
		}
		
		if(retValue==null){
			s_log.log (Level.SEVERE, "Sin cuentas contables de la categoria: id_"+M_Product_Category_ID
					+" y del esquema: id_"+C_AcctSchema_ID);
		}
		return retValue;
	}	//	get
	
//	/**
//	 * 	Get Category Acct
//	 *	@param ctx context
//	 *	@param M_Product_Category_ID category
//	 *	@param C_AcctSchema_ID acct schema
//	 *	@param trxName trx
//	 *	@return category acct
//	 *  Expert: #Post,Cost And Price
//	 */
//	private static MProductCategoryAcct getAcct (Properties ctx, 
//		int M_Product_Category_ID, int C_AcctSchema_ID, String trxName)
//	{
//		MProductCategoryAcct retValue = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			sql.append(" SELECT * FROM  M_Product_Category_Acct ")
//			.append(" WHERE M_Product_Category_ID = ? ")//#1
//			.append(" AND C_AcctSchema_ID IN (?) ")//#2
//			.append(" ORDER BY AD_Client_ID DESC ");
//		try
//		{
//			pstmt = DB.prepareStatement (sql.toString(), trxName);
//			pstmt.setInt (1, M_Product_Category_ID);
//			pstmt.setInt (2, C_AcctSchema_ID);
////			pstmt.setInt (3, Env.getContextAsInt(ctx, "$C_DefaultAcctSchema_ID"));// ya no será necesario buscar a nivel de system GC 18/03/2014
//			
//			rs = pstmt.executeQuery ();
//			if (rs.next ())
//				retValue = new MProductCategoryAcct (ctx, rs, trxName);
//		}
//		catch (Exception e)
//		{
//			s_log.log (Level.SEVERE, sql.toString(), e);
//		}
//		finally
//		{
//			DB.close(rs, pstmt);
//		}
//		
//		return retValue;
//	}	//	get
	
//	/**
//	 * Get Category Acct
//	 * Si el registro de la categoria de producto existe a nivel de cliente
//	 * 	obtenemos el metodo y nivel de costo,
//	 * 	pero si alguno de estos datos o ambos no estan configurados
//	 * 	los buscamos a nivel de esquema contable del cliente
//	 * Si no existe a nivel de cliente
//	 * 	obtenemos el metodo y nivel de costo a nivel de system,
//	 * 	pero si alguno de estos datos o ambos no estan configurados
//	 * 	los buscamos a nivel de esquema contable de system
//	 * @param product Objeto del producto
//	 * @param as Esquema contable del cliente
//	 * @return category acct (System o cliente)
//	 * Expert: #Post,Cost And Price
//	 */
//	public static MProductCategoryAcct getAcct(MProduct product, MAcctSchema as){
//		
//		// Esquema contable de System
//		MAcctSchema asSystem = MAcctSchema.get(product.getCtx(), 
//				Env.getContextAsInt(product.getCtx(), "$C_DefaultAcctSchema_ID"), null);
//		
//		// Busca el registro contable de la categoria para el cliente o system
//		MProductCategoryAcct pca = MProductCategoryAcct.getAcct(product.getCtx(), 
//			product.getM_Product_Category_ID(), as.getC_AcctSchema_ID(), product.get_TrxName());
//		
//		/*
//		 * CostingLevel
//		 */
//		// Si existe el registro
//		if(pca!=null){
//			// busca el nivel de costo
//			pca.setCostingLevelDefault(pca.getCostingLevel());
//			
//			// Si no lo encuentra busca a nivel del esquemas contables
//			if (pca.getCostingLevelDefault() == null) {
//				// A nivel de cliente
//				if(pca.getAD_Client_ID()>0){
//					pca.setCostingLevelDefault(as.getCostingLevel());
//				} 
//				else
//				{
//					// A nivel de system
//					pca.setCostingLevelDefault(asSystem.getCostingLevel());
//				}
//			}
//			
//			/*
//			 * CostingMethod
//			 */
//			// busca el metodo de costeo
//			pca.setCostingMethodDefault(pca.getCostingMethod());
//			
//			// Si no lo encuentra busca a nivel del esquemas contables
//			if (pca.getCostingMethodDefault() == null) {
//				// A nivel de cliente
//				if(pca.getAD_Client_ID()>0){
//					pca.setCostingMethodDefault(as.getCostingMethod());
//				} 
//				else
//				{
//					// A nivel de system
//					pca.setCostingMethodDefault(asSystem.getCostingMethod());
//				}
//			}
//			
//		}// Fin si pca!=null
//		
//		return pca;
//	}
//	
//	/**
//	 * Nivel de costo del cliente o de system
//	 * Expert: #Post,Cost And Price
//	 */
//	private String costingLevelDefault=null; 
//	
//	public String getCostingLevelDefault() {
//		return this.costingLevelDefault;
//	}
//
//	public void setCostingLevelDefault(String costingLevelDefault) {
//		this.costingLevelDefault = costingLevelDefault;
//	}
//
//	/**
//	 * Metodo de costeo del cliente o de System
//	 * Expert: #Post,Cost And Price
//	 */
//	private String costingMethodDefault = null;
//	
//	public String getCostingMethodDefault() {
//		return this.costingMethodDefault;
//	}
//
//	public void setCostingMethodDefault(String costingMethodDefault) {
//		this.costingMethodDefault = costingMethodDefault;
//	}
	
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MProductCategoryAcct.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param ignored ignored 
	 *	@param trxName
	 */
	public MProductCategoryAcct (final Properties ctx, final int ignored, final String trxName)
	{
		super (ctx, ignored, trxName);
		if (ignored != 0){
			throw new IllegalArgumentException("Multi-Key");
		}
	}	//	MProductCategoryAcct

	/**
	 * 	Load Cosntructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MProductCategoryAcct (final Properties ctx, final ResultSet rs, final String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MProductCategoryAcct

	/**
	 * 	Check Costing Setup
	 */
	public void checkCosting()
	{
		//	Create Cost Elements
		if (getCostingMethod() != null && getCostingMethod().length() > 0){
			MCostElement.getMaterialCostElement(this, getCostingMethod());
		}
	}	//	checkCosting

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (final boolean newRecord, final boolean success)
	{
		checkCosting();
		return success;
	}	//	afterSave
	
}	//	MProductCategoryAcct
