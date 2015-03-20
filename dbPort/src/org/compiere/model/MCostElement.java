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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.OptionItem;
import org.compiere.util.ValueNamePair;

/**
 * 	Cost Element Model
 *  @author Jorg Janke
 *  @version $Id: MCostElement.java,v 1.2 2006/07/30 00:58:04 jjanke Exp $
 */
public class MCostElement extends X_M_CostElement
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6915211862956433439L;


	/**
	 * 	Get Material Cost Element or create it
	 *	@param po parent
	 *	@param CostingMethod method
	 *	@return cost element
	 */
	public static MCostElement getMaterialCostElement (PO po, String CostingMethod)
	{
		if (CostingMethod == null || CostingMethod.length() == 0){
			s_log.severe("No CostingMethod");
			return null;
		}
		
		//
		final String whereClause = " AD_Client_ID=? AND CostingMethod=? AND CostElementType=?";
		final List<MCostElement> list = new Query(po.getCtx(), I_M_CostElement.Table_Name, whereClause, po.get_TrxName())
			.setParameters(po.getAD_Client_ID(), CostingMethod, COSTELEMENTTYPE_Material)
			.setOrderBy(I_M_CostElement.COLUMNNAME_AD_Org_ID)
			.list();
		
		MCostElement retValue = null;
		if (list.size() > 0){
			retValue = list.get(0);
		}
		if (list.size() > 1){
			s_log.info("More then one Material Cost Element for CostingMethod=" + CostingMethod);
		}
		
//		//
//		MCostElement retValue = null;
//		String sql = "SELECT * FROM M_CostElement WHERE AD_Client_ID=? AND CostingMethod=? ORDER BY AD_Org_ID";
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement (sql, po.get_TrxName());
//			pstmt.setInt (1, po.getAD_Client_ID());
//			pstmt.setString(2, CostingMethod);
//			ResultSet rs = pstmt.executeQuery ();
//			if (rs.next ())
//				retValue = new MCostElement (po.getCtx(), rs, po.get_TrxName());
//			if (rs.next())
//				s_log.warning("More then one Material Cost Element for CostingMethod=" + CostingMethod);
//			rs.close ();
//			pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			s_log.log (Level.SEVERE, sql, e);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			pstmt = null;
//		}
		if (retValue != null)
			return retValue;
		
		//	Create New
		retValue = new MCostElement (po.getCtx(), 0, po.get_TrxName());
		retValue.setClientOrg(po.getAD_Client_ID(), 0);
		String name = MRefList.getListName(po.getCtx(), COSTINGMETHOD_AD_Reference_ID, CostingMethod);
		if (name == null || name.length() == 0){
			name = CostingMethod;
		}
		retValue.setName(name);
		retValue.setCostElementType(COSTELEMENTTYPE_Material);
		retValue.setCostingMethod(CostingMethod);
		retValue.save(); //TODO twry retValue.saveEx();
		//
		return retValue;
	}	//	getMaterialCostElement

	/**
	 * 	Get first Material Cost Element
	 *	@param ctx context
	 *	@param CostingMethod costing method
	 *	@return Cost Element or null
	 */
	public static MCostElement getMaterialCostElement(final Properties ctx, final String CostingMethod)
	{
//		MCostElement retValue = null;
//		String sql = "SELECT * FROM M_CostElement WHERE AD_Client_ID=? AND CostingMethod=? ORDER BY AD_Org_ID";
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement (sql, null);
//			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
//			pstmt.setString(2, CostingMethod);
//			ResultSet rs = pstmt.executeQuery ();
//			if (rs.next ())
//				retValue = new MCostElement (ctx, rs, null);
//			if (rs.next())
//				s_log.info("More then one Material Cost Element for CostingMethod=" + CostingMethod);
//			rs.close ();
//			pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			s_log.log (Level.SEVERE, sql, e);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			pstmt = null;
//		}
//		return retValue;
		
		final String whereClause = "AD_Client_ID=? AND CostingMethod=? AND CostElementType=?";
		final List<MCostElement> list = new Query(ctx, I_M_CostElement.Table_Name, whereClause, null)
										.setParameters(Env.getAD_Client_ID(ctx),CostingMethod,COSTELEMENTTYPE_Material)
										.setOrderBy(I_M_CostElement.COLUMNNAME_AD_Org_ID)
										.list();
		MCostElement retValue = null;
		if (list.size() > 0){
			retValue = list.get(0);
		}
		if (list.size() > 1){
			s_log.info("More then one Material Cost Element for CostingMethod=" + CostingMethod);
		}
		return retValue;
	}	//	getMaterialCostElement
	
	
	/**
	 * 	Get active Material Cost Element for client 
	 *	@param po parent
	 *	@return cost element array
	 */
	public static List<MCostElement> getCostElementsWithCostingMethods (final PO po)
	{
		final String whereClause = " AD_Client_ID=? AND CostingMethod IS NOT NULL";
		return new Query(po.getCtx(), I_M_CostElement.Table_Name, whereClause, po.get_TrxName())
		.setParameters(po.getAD_Client_ID())
		.setOnlyActiveRecords(true)
		.list();
	}	//	getCostElementCostingMethod	
	
	/**
	 * 	Get active Material Cost Element for client 
	 *	@param po parent
	 *	@return cost element array
	 */
	public static MCostElement[] getCostingMethods (PO po)
	{
//		ArrayList<MCostElement> list = new ArrayList<MCostElement>();
//		String sql = "SELECT * FROM M_CostElement "
//			+ "WHERE AD_Client_ID=?"
//			+ " AND IsActive='Y' AND CostElementType='M' AND CostingMethod IS NOT NULL";
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement (sql, po.get_TrxName());
//			pstmt.setInt (1, po.getAD_Client_ID());
//			ResultSet rs = pstmt.executeQuery ();
//			while (rs.next ())
//				list.add(new MCostElement (po.getCtx(), rs, po.get_TrxName()));
//			rs.close ();
//			pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			s_log.log (Level.SEVERE, sql, e);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			pstmt = null;
//		}
//		//
//		MCostElement[] retValue = new MCostElement[list.size ()];
//		list.toArray (retValue);
//		return retValue;
		
		final String whereClause ="AD_Client_ID=? AND CostElementType=? AND CostingMethod IS NOT NULL";
		final List<MCostElement> list = new Query(po.getCtx(), I_M_CostElement.Table_Name, whereClause, po.get_TrxName())
										.setParameters(po.getAD_Client_ID(),COSTELEMENTTYPE_Material)
										.setOnlyActiveRecords(true)
										.list();
		//
		final MCostElement[] retValue = new MCostElement[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getMaterialCostElement

	/**
	 * 	Get active non Material Cost Element for client 
	 *	@param po parent
	 *	@return cost element array
	 */
	public static MCostElement[] getNonCostingMethods (PO po)
	{
		final String whereClause = "AD_Client_ID=? AND CostingMethod IS NULL";
		List<MCostElement>list = new Query(po.getCtx(),I_M_CostElement.Table_Name, whereClause, po.get_TrxName())
		.setParameters(po.getAD_Client_ID())
		.setOnlyActiveRecords(true)
		.list(); 
		//
		MCostElement[] retValue = new MCostElement[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getMaterialCostElement
	
	public static MCostElement get (Properties ctx, int M_CostElement_ID){
		return get(ctx, M_CostElement_ID, null);
	}
	/**
	 * 	Get Cost Element from Cache
	 *	@param ctx context
	 *	@param M_CostElement_ID id
	 *	@return Cost Element
	 */
	public static MCostElement get (Properties ctx, int M_CostElement_ID, String trxName)
	{
		Integer key = new Integer (M_CostElement_ID);
		MCostElement retValue = (MCostElement) s_cache.get (key);
		if (retValue != null 
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MCostElement (ctx, M_CostElement_ID, trxName);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**
	 * Get All Cost Elements for current AD_Client_ID
	 * @param ctx context
	 * @param trxName transaction
	 * @return array cost elements
	 */
	public static MCostElement[] getElements (Properties ctx, String trxName)
	{
		int AD_Org_ID = 0; // Org is always ZERO - see beforeSave
		
		final String whereClause = " AD_Client_ID = ? AND AD_Org_ID=? ";
		List<MCostElement> list = new Query(ctx, Table_Name, whereClause, trxName)
					.setParameters(Env.getAD_Client_ID(ctx),AD_Org_ID)
					.list();
		MCostElement[] retValue = new MCostElement[list.size()];
		list.toArray(retValue);
		return retValue;	
	}
	
	/**
	 * Get All Cost Elements for current AD_Client_ID
	 * @param ctx context
	 * @param trxName transaction
	 * @return array cost elements
	 **/
	public static List<MCostElement> getByCostingMethod (Properties ctx, String CostingMethod)
	{		
		final String whereClause = "AD_Client_ID = ? AND CostingMethod=?";
		return new Query(ctx, Table_Name, whereClause, null)
					.setOnlyActiveRecords(true)
					.setParameters(Env.getAD_Client_ID(ctx),CostingMethod)
					.list();	
	}	
	
	/**	Cache						*/
	private static CCache<Integer,MCostElement>	s_cache	= new CCache<Integer,MCostElement>("M_CostElement", 20);
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MCostElement.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_CostElement_ID id
	 *	@param trxName trx
	 */
	public MCostElement (Properties ctx, int M_CostElement_ID, String trxName)
	{
		super (ctx, M_CostElement_ID, trxName);
		if (M_CostElement_ID == 0)
		{
		//	setName (null);
			setCostElementType (COSTELEMENTTYPE_Material);
			setIsCalculated (false);
		}
	}	//	MCostElement

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCostElement (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCostElement
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Check Unique Costing Method
		if (COSTELEMENTTYPE_Material.equals(getCostElementType())
				|| COSTELEMENTTYPE_OutsideProcessing.equals(getCostElementType())
			&& (newRecord || is_ValueChanged(COLUMNNAME_CostingMethod)))
		{
			String sql = "SELECT  COALESCE(MAX(M_CostElement_ID),0) FROM M_CostElement "
				+ "WHERE AD_Client_ID=? AND CostingMethod=? AND CostElementType=?";
			int id = DB.getSQLValue(get_TrxName(), sql, getAD_Client_ID(), getCostingMethod() , getCostElementType());
			if (id > 0 && id != get_ID())
			{
				log.saveError("AlreadyExists", Msg.getElement(getCtx(), "CostingMethod"));
				return false;
			}
		}

		//	Maintain Calclated
		if (COSTELEMENTTYPE_Material.equals(getCostElementType()))
		{
			String cm = getCostingMethod();
			if (cm == null || cm.length() == 0
				|| COSTINGMETHOD_StandardCosting.equals(cm))
				setIsCalculated(false);
			else
				setIsCalculated(true);
		}
		else
		{
			if (isCalculated())
				setIsCalculated(false);
			if (getCostingMethod() != null)
				setCostingMethod(null);
		}
		
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		return true;
	}	//	beforeSave
	
	/**
	 * 	Before Delete
	 *	@return true if can be deleted
	 */
	protected boolean beforeDelete ()
	{
		String cm = getCostingMethod();
		if (cm == null
			|| !COSTELEMENTTYPE_Material.equals(getCostElementType()))
			return true;
		
		//	Costing Methods on AS level
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		for (int i = 0; i < ass.length; i++)
		{
			if (ass[i].getCostingMethod().equals(getCostingMethod()))
			{
				log.saveError("CannotDeleteUsed", Msg.getElement(getCtx(), "C_AcctSchema_ID") 
					+ " - " + ass[i].getName());
				return false;
			}
		}
		
		//	Costing Methods on PC level
//		String sql = "SELECT M_Product_Category_ID FROM M_Product_Category_Acct WHERE AD_Client_ID=? AND CostingMethod=?";
//		int M_Product_Category_ID = 0;
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement (sql, null);
//			pstmt.setInt (1, getAD_Client_ID());
//			pstmt.setString (2, getCostingMethod());
//			ResultSet rs = pstmt.executeQuery ();
//			if (rs.next ())
//			{
//				M_Product_Category_ID = rs.getInt(1);
//			}
//			rs.close ();
//			pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			log.log (Level.SEVERE, sql, e);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close ();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			pstmt = null;
//		}
		
		// Costing Methods on PC level
		int M_Product_Category_ID = 0;
		final String whereClause =" AD_Client_ID=? AND CostingMethod=?";
		final MProductCategoryAcct retValue = new Query(getCtx(), I_M_Product_Category_Acct.Table_Name, whereClause, null)
												.setParameters(getAD_Client_ID(), getCostingMethod())
												.first();
		if (retValue != null){
			M_Product_Category_ID = retValue.getM_Product_Category_ID();
		}
			
		if (M_Product_Category_ID != 0)
		{
			log.saveError("CannotDeleteUsed", Msg.getElement(getCtx(), "M_Product_Category_ID") 
				+ " (ID=" + M_Product_Category_ID + ")");
			return false;
		}
		return true;
	}	//	beforeDelete
	
	/**
	 * 	Is this a Costing Method
	 *	@return true if not Material cost or no costing method.
	 */
	public boolean isCostingMethod()
	{
		return COSTELEMENTTYPE_Material.equals(getCostElementType())
			&& getCostingMethod() != null;
	}	//	isCostingMethod
	
	/**
	 * 	Is Avg Invoice Costing Method
	 *	@return true if AverageInvoice
	 */
	public boolean isAverageInvoice()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_AverageInvoice)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isAverageInvoice
	
	/**
	 * 	Is Avg PO Costing Method
	 *	@return true if AveragePO
	 */
	public boolean isAveragePO()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_AveragePO)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isAveragePO
	/**
	 * 	Is FiFo Costing Method
	 *	@return true if Fifo
	 */
	public boolean isFifo()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_Fifo)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isFifo
	/**
	 * 	Is Last Invoice Costing Method
	 *	@return true if LastInvoice
	 */
	public boolean isLastInvoice()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_LastInvoice)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isLastInvoice
	/**
	 * 	Is Last PO Costing Method
	 *	@return true if LastPOPrice
	 */
	public boolean isLastPOPrice()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_LastPOPrice)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isLastPOPrice
	/**
	 * 	Is LiFo Costing Method
	 *	@return true if Lifo
	 */
	public boolean isLifo()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_Lifo)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isLiFo
	/**
	 * 	Is Std Costing Method
	 *	@return true if StandardCosting
	 */
	public boolean isStandardCosting()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_StandardCosting)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isStandardCosting
	/**
	 * 	Is User Costing Method
	 *	@return true if User Defined
	 */
	public boolean isUserDefined()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_UserDefined)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isAveragePO
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MCostElement[");
		sb.append (get_ID ())
			.append ("-").append (getName())
			.append(",Type=").append(getCostElementType())
			.append(",Method=").append(getCostingMethod())
			.append ("]");
		return sb.toString ();
	} //	toString
	
//	public Vector<OptionItem> getCostElement() {
//
//		Vector<OptionItem> costElement = null;
//
//		String sql = "select m_costelement_id, name from m_costelement where costelementtype='M'";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			
//			rs = pstmt.executeQuery();
//			costElement = new Vector<OptionItem>();
//
//			while (rs.next()) {
//				costElement.add(new OptionItem(rs.getString(1), rs.getString(2)));
//
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "getLines", e);
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//				pstmt = null;
//				if (rs != null)
//					rs.close();
//				rs = null;
//			} catch (Exception e) {
//				log.log(Level.SEVERE, "getLines", e);
//			}
//		}
//		return costElement;
//	}
	
	/**
	 * Obtiene la Lista de los Elementos de Costo
	 * @param active Estatus de los registros a consultar (Y/N), en caso de que se deseen todos los registros mandar null
	 * @return List<LabelValueBean>
	 */
	public static List<ValueNamePair> getCostElementCbo(Properties ctx, String active, String where, boolean client) {
		List<ValueNamePair> lst = new ArrayList<ValueNamePair>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT M_COSTELEMENT.M_COSTELEMENT_ID, M_COSTELEMENT.NAME ");
		sql.append("  FROM M_COSTELEMENT ");
		
		if(active != null) {			
			sql.append(" WHERE M_COSTELEMENT.ISACTIVE = ? ");
			if(StringUtils.isNotBlank(where)){
				sql.append(" AND ");
				sql.append(where);
			}
		}else if(StringUtils.isNotBlank(where)){
			sql.append(" WHERE ");
				sql.append(where);
		}
		//Evitar que aparescan las opciones de nivel system
		if(client){
			sql.append(" and ad_client_id in ( ");
			sql.append(Env.getAD_Client_ID(ctx));
			sql.append(" ) ");
		}else{
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		}
		//sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_CostElement"));
		
		sql.append(" ORDER BY M_COSTELEMENT.NAME ");
		
		pstmt = null;
		rs = null;

		try {
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			if(active != null) {
				
				pstmt.setString(1, active);
				
			} 
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ValueNamePair partner = new ValueNamePair(rs.getString(1), String.valueOf(rs.getString(2)));
				lst.add(partner);
				
			}
               
		} catch (Exception e) {
			
    		sql = null;  
    		
    		try {
    			
    			if (pstmt != null) {
    				
    				pstmt.close ();    				
    				
    			}
    				
    			if (rs != null) {
    				
    				rs.close ();
    				
    			}
    				
    		} catch (Exception ex) {
    			
    			ex.getMessage();
    			
    		}
    		
    		pstmt = null;
    		rs = null;
    		
    	}finally {
    		
    		try {
    			
    			if (pstmt != null) {
    				
    				pstmt.close();
    				
    			}
    			
    			if (rs != null) {
    				
    				rs.close();
    				
    			}
    			
    			pstmt = null;
    			rs = null;
    			
    		} catch (Exception e) {
    			
    			 e.printStackTrace();
    			pstmt = null;
    			rs = null;
    			
    		}
    		
    	}
    	
		return lst;
	}

	public static  MCostElement getStandardCosting(final Properties ctx) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from m_costelement where ad_client_id = ? ");
		sql.append(" and costingmethod = ? ");
		sql.append(" and costelementtype = ? ");
		sql.append(" and isactive = 'Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MCostElement elm = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			pstmt.setString (2, COSTINGMETHOD_StandardCosting);
			pstmt.setString (3, COSTELEMENTTYPE_Material);
			rs = pstmt.executeQuery();
			

			if (rs.next()) {
				elm = new MCostElement(ctx,rs,null);

			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return elm;
	}

}	//	MCostElement
