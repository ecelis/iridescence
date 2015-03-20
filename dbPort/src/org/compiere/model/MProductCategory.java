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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Product Category Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MProductCategory.java,v 1.5 2006/08/15 21:52:05 taniap Exp $
 */
public class MProductCategory extends X_M_Product_Category
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5871189650845239107L;


	/**
	 * 	Get from Cache
	 *	@param ctx context
	 *	@param M_Product_Category_ID id
	 *	@return category
	 */
	public static MProductCategory get (Properties ctx, int M_Product_Category_ID)
	{
		Integer ii = new Integer (M_Product_Category_ID);
		MProductCategory pc = (MProductCategory)s_cache.get(ii);
		if (pc == null)
			pc = new MProductCategory (ctx, M_Product_Category_ID, null);
		return pc;
	}	//	get
	
	/**
	 * 	Is Product in Category
	 *	@param M_Product_Category_ID category
	 *	@param M_Product_ID product
	 *	@return true if product has category
	 */
	public static boolean isCategory (int M_Product_Category_ID, int M_Product_ID)
	{
		if (M_Product_ID == 0 || M_Product_Category_ID == 0)
			return false;
		//	Look up
		Integer product = new Integer (M_Product_ID);
		Integer category = (Integer)s_products.get(product);
		if (category != null)
			return category.intValue() == M_Product_Category_ID;
		
		String sql = "SELECT M_Product_Category_ID FROM M_Product WHERE M_Product_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, M_Product_ID);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				category = new Integer(rs.getInt(1));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		if (category != null)
		{
			//	TODO: LRU logic  
			s_products.put(product, category);
			//
			s_log.fine("M_Product_ID=" + M_Product_ID + "(" + category
				+ ") in M_Product_Category_ID=" + M_Product_Category_ID
				+ " - " + (category.intValue() == M_Product_Category_ID));
			return category.intValue() == M_Product_Category_ID;
		}
		s_log.log(Level.SEVERE, "Not found M_Product_ID=" + M_Product_ID);
		return false;
	}	//	isCategory
	
	/**	Categopry Cache				*/
	private static CCache<Integer,MProductCategory>	s_cache = new CCache<Integer,MProductCategory>("M_Product_Category", 20);
	/**	Product Cache				*/
	private static CCache<Integer,Integer> s_products = new CCache<Integer,Integer>("M_Product", 100);
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MProductCategory.class);

	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param M_Product_Category_ID id
	 */
	public MProductCategory (Properties ctx, int M_Product_Category_ID, String trxName)
	{
		super(ctx, M_Product_Category_ID, trxName);
		if (M_Product_Category_ID == 0)
		{
		//	setName (null);
		//	setValue (null);
			setMMPolicy (MMPOLICY_FiFo);	// F
			setPlannedMargin (Env.ZERO);
			setIsDefault (false);
			setIsSelfService (true);	// Y
		}
	}	//	MProductCategory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MProductCategory(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductCategory

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord & success)
			insert_Accounting("M_Product_Category_Acct", "C_AcctSchema_Default", null);

		return success;
	}	//	afterSave

	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("M_Product_Category_Acct"); 
	}	//	beforeDelete
	
	/**
	 * 	FiFo Material Movement Policy
	 *	@return true if FiFo
	 */
	public boolean isFiFo()
	{
		return MMPOLICY_FiFo.equals(getMMPolicy());
	}	//	isFiFo
	
	//expert : miguel rojas : para importar
	/**
	 * 	Import Constructor
	 *	@param impP import
	 */
	public MProductCategory (X_I_Product_Category impP)
	{
		this (impP.getCtx(), 0, impP.get_TrxName());
		setClientOrg(impP);
		setUpdatedBy(impP.getUpdatedBy());
		//
		setValue(impP.getValue());
		setName(impP.getName());
		
	}	//	MProductCategory
	
	/**
	 * 	Before Save
	 *	@param newRecord
	 *	@return true
	 *
	protected boolean beforeSave (boolean newRecord)
	{
		//EXPERT: twry
		/*if(newRecord) {
			if(getC_Charge_DesGlob_ID()==0 || getC_Charge_ID()==0){
				String sql = " SELECT C_Charge_ID FROM EXME_ConfigPre WHERE AD_Client_ID = ? AND AD_Org_ID IN (0,?) ";
				int ii = DB.getSQLValue (get_TrxName(), sql, getAD_Client_ID(), getAD_Org_ID());
				
				//EXPERT: Lama
				if(ii>0){
					setC_Charge_DesGlob_ID(ii);
					setC_Charge_ID(ii);
				} else {
					sql = " SELECT c.C_Charge_ID FROM C_Charge c "
						+ " INNER JOIN C_TaxCategory t ON t.C_TaxCategory_ID = c.C_TaxCategory_ID"
						+ " WHERE t.isDefault = 'Y' AND c.AD_Client_ID = ? ";
					ii = DB.getSQLValue (get_TrxName(), sql, getAD_Client_ID());
					setC_Charge_DesGlob_ID(ii);
					setC_Charge_ID(ii);
				}
				//FIN Lama
			}
		    
		}*/
		//FIN twry
	/*	return true;
	}	//	beforeSave
	*/
	//expert : miguel rojas : para importar
	
	
	public BigDecimal amount = Env.ZERO;


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	public boolean insertAccounting(final int AD_Client_ID) {
		return insert_Accounting_Client(
				"M_Product_Category_Acct", 
				"C_AcctSchema_Default", 
				null, AD_Client_ID
		);
	}
	
	public boolean insertAccounting() {
		return insert_Accounting_Client(
				"M_Product_Category_Acct", 
				"C_AcctSchema_Default", 
				null
		);
	}
	
	/**
	 * Metodo para cambiar el costingmethod en las categorias de producto de Servicios
	 * 
	 * @param costingMethod el Metodo de Costeo.
	 * @param categoryId el Product Category Id.
	 * @param trxName El Nombre de Transaccion.
	 * @return true si el update fue correcto, de lo contrario false.
	 * @author jcantup el 22 Noviembre del 2013.
	 */
	public boolean updateAccountingCostMethod(String costingMethod, int categoryId, String trxName) {
		int no =
				DB.executeUpdate(
						"Update M_Product_Category_Acct set costingmethod = ?, costinglevel='C' where m_product_category_id = ?",
							new Object[]{costingMethod, categoryId},
							trxName
					);
		
		
		return no == 1;
		
	}

	public static MProductCategory get(Properties ctx, String value, String trxName) {
		StringBuilder st = new StringBuilder("select * from M_Product_Category where value = ? ");
		MProductCategory category = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				category = new MProductCategory(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return category;
	}
	
	/**
	 * Obtiene la Lista de Categorias de Productos
	 * @param active Estatus de los registros a consultar (Y/N), en caso de que se deseen todos los registros mandar null
	 * @return List<LabelValueBean>
	 * @author mvrodriguez
	 */
	public static List<LabelValueBean> getCategoryListCbo(Properties ctx, String active) {
		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT M_PRODUCT_CATEGORY.M_PRODUCT_CATEGORY_ID, M_PRODUCT_CATEGORY.NAME ");
		sql.append("  FROM M_PRODUCT_CATEGORY ");
		
		if (active != null) {
			sql.append(" WHERE M_PRODUCT_CATEGORY.ISACTIVE = ? ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY M_PRODUCT_CATEGORY.NAME ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (active != null) {
				pstmt.setString(1, active);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean partner = new LabelValueBean(rs.getString("NAME"), String.valueOf(rs.getString("M_PRODUCT_CATEGORY_ID")));
				lst.add(partner);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;

	}
	
	/**
     * Categorias existentes
     * @param ctx
     * @param trxName
     * @return Lista de tipo {@link LabelValueBean} 
     * @deprecated use {@link MEXMEProductCategory#getLstCategories(Properties, String)} ya no se debe usar LabelValueBean
     */
    public static List<LabelValueBean> getCategorias(Properties ctx, String trxName){
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		sql.append(" SELECT C.M_Product_Category_ID, C.Name ")
		.append(" FROM M_Product_Category C ")
		.append(" WHERE C.IsActive='Y' ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "C"));
		sql.append(" ORDER BY Name ");
        
		try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);  
        	rs = pstmt.executeQuery();        	
        	lista.add(new LabelValueBean(" ","0"));
        	while (rs.next()){
        		lista.add(new LabelValueBean(rs.getString(2), String.valueOf(rs.getInt(1))));
        	}

        } catch (Exception e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        	lista = null;
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
    
    /**
     * Categorias existentes
     * @param ctx
     * @param trxName
     * @return Lista de tipo {@link LabelValueBean} 
     */
    public static List<LabelValueBean> getCategoryDrugs(Properties ctx, String trxName){
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		sql.append(" SELECT p.M_Product_Category_ID, pC.Name ");
		sql.append(" FROM M_Product p ");
		sql.append(" INNER JOIN M_Product_Category pC ON p.M_Product_Category_ID = pC.M_Product_Category_ID ");
		sql.append(" Where p.ProductClass = 'DG'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product_Category", "pC"));
		sql.append(" group by p.M_Product_Category_ID, pC.Name ");
		sql.append(" ORDER BY pc.Name ");
        
		try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);  
        	rs = pstmt.executeQuery();        	
        	lista.add(new LabelValueBean(" ","0"));
        	while (rs.next()){
        		lista.add(new LabelValueBean(rs.getString(2), String.valueOf(rs.getInt(1))));
        	}

        } catch (Exception e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        	lista = null;
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
    
    
    /**
	 * Regresa el listado de categorias de producto activas, filtradas por nivel de acceso
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MProductCategory> getLstCategories(Properties ctx, String trxName){
		
		List<MProductCategory> lista = new ArrayList<MProductCategory>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		sql.append(" SELECT *  FROM M_Product_Category C WHERE C.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "C"));
		sql.append(" ORDER BY Name ");
        
		try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);  
        	rs = pstmt.executeQuery();        	
        	while (rs.next()){
        		lista.add(new MEXMEProductCategory(ctx, rs, null));
        	}

        } catch (Exception e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        	lista = null;
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
}	//	MProductCategory
