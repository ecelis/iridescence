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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;

/**
 *	Warehouse Locator Object
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MLocator.java,v 1.1 2006/09/08 02:41:32 taniap Exp $
 */
public class MLocator extends X_M_Locator
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	/**
	 * 	Get oldest Default Locator of warehouse with locator
	 *	@param ctx context
	 *	@param M_Locator_ID locator
	 *	@return locator or null
	 */
	public static MLocator getDefault (Properties ctx, int M_Locator_ID)
	{
		String trxName = null;
		MLocator retValue = null;
		String sql = "SELECT * FROM M_Locator l "
			+ "WHERE IsDefault='Y'"
			+ " AND EXISTS (SELECT * FROM M_Locator lx "
				+ "WHERE l.M_Warehouse_ID=lx.M_Warehouse_ID AND lx.M_Locator_ID=?) "
			+ "ORDER BY Created";
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_Locator_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				retValue = new MLocator (ctx, rs, trxName);
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		
		return retValue;
	}	//	getDefault
	
	
	/**
	 * 	Get the Locator with the combination or create new one
	 *	@param ctx Context
	 *	@param M_Warehouse_ID warehouse
	 *	@param Value value
	 *	@param X x
	 *	@param Y y
	 *	@param Z z
	 * 	@return locator
	 */
	 public static MLocator get (Properties ctx, int M_Warehouse_ID, String Value,
		 String X, String Y, String Z)
	 {
		MLocator retValue = null;
		String sql = "SELECT * FROM M_Locator WHERE M_Warehouse_ID=? AND X=? AND Y=? AND Z=?";
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_Warehouse_ID);
			pstmt.setString(2, X);
			pstmt.setString(3, Y);
			pstmt.setString(4, Z);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MLocator (ctx, rs, null);
			
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, "get", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		if (retValue == null)
		{
			MWarehouse wh = MWarehouse.get (ctx, M_Warehouse_ID);
			retValue = new MLocator (wh, Value);
			retValue.setXYZ(X, Y, Z);
			retValue.save();
		}
		return retValue;
	 }	//	get

	/**
	 * 	Get Locator from Cache
	 *	@param ctx context
	 *	@param M_Locator_ID id
	 *	@return MLocator
	 */
	public static MLocator get (Properties ctx, int M_Locator_ID)
	{
		if (s_cache == null)
			s_cache	= new CCache<Integer,MLocator>("M_Locator", 20);
		Integer key = new Integer (M_Locator_ID);
		MLocator retValue = (MLocator) s_cache.get (key);
		if (retValue != null 
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MLocator (ctx, M_Locator_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MLocator> s_cache; 
	 
	/**	Logger						*/
	private static CLogger		s_log = CLogger.getCLogger (MLocator.class);
	
	
	/**************************************************************************
	 * 	Standard Locator Constructor
	 *	@param ctx Context
	 *	@param M_Locator_ID id
	 *	@param trxName transaction
	 */
	public MLocator (Properties ctx, int M_Locator_ID, String trxName)
	{
		super (ctx, M_Locator_ID, trxName);
		if (M_Locator_ID == 0)
		{
		//	setM_Locator_ID (0);		//	PK
		//	setM_Warehouse_ID (0);		//	Parent
			setIsDefault (false);
			setPriorityNo (50);
		//	setValue (null);
		//	setX (null);
		//	setY (null);
		//	setZ (null);
		}
	}	//	MLocator

	/**
	 * 	New Locator Constructor with XYZ=000
	 *	@param warehouse parent
	 *	@param Value value
	 */
	public MLocator (MWarehouse warehouse, String Value)
	{
		this (warehouse.getCtx(), 0, warehouse.get_TrxName());
		setClientOrg(warehouse);
		setM_Warehouse_ID (warehouse.getM_Warehouse_ID());		//	Parent
		setValue (Value);
		setXYZ("0","0","0");
	}	//	MLocator

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MLocator (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MLocator

	/**
	 *	Get String Representation
	 * 	@return Value
	 */
	public String toString()
	{
		return getValue();
	}	//	getValue

	/**
	 * 	Set Location
	 *	@param X x
	 *	@param Y y
	 *	@param Z z
	 */
	public void setXYZ (String X, String Y, String Z)
	{
		setX (X);
		setY (Y);
		setZ (Z);
	}	//	setXYZ
	
	
	/**
	 * 	Get Warehouse Name
	 * 	@return name
	 */
	public String getWarehouseName()
	{
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		if (wh.get_ID() == 0)
			return "<" + getM_Warehouse_ID() + ">";
		return wh.getName();
	}	//	getWarehouseName

	/**
	 * 	Can Locator Store Product
	 *	@param M_Product_ID id
	 *	@return true if can be stored
	 */
	public boolean isCanStoreProduct (int M_Product_ID)
	{
		//	Default Locator
		if (M_Product_ID == 0 || isDefault())
			return true;
		
		int count = 0;
//		PreparedStatement pstmt = null;
		//	Already Stored
		String sql = "SELECT COUNT(*) FROM M_Storage s WHERE s.M_Locator_ID=? AND s.M_Product_ID=?";
//        ResultSet rs = null;//Expert
		try
		{
//			pstmt = DB.prepareStatement (sql, null);
//			pstmt.setInt (1, getM_Locator_ID());
//			pstmt.setInt (2, M_Product_ID);
//			rs = pstmt.executeQuery ();
//			if (rs.next ()) {
//				count = rs.getInt(1);
			count = DB.getSQLValue(null, sql, getM_Locator_ID(), M_Product_ID);
//			}
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
//		}finally {//Expert
//           DB.close(rs, pstmt);
		}
		//	Default Product Locator
		if (count == 0)
		{
			sql = "SELECT COUNT(*) FROM M_Product s WHERE s.M_Locator_ID=? AND s.M_Product_ID=?";
			try
			{
//				pstmt = DB.prepareStatement (sql, null);
//				pstmt.setInt (1, getM_Locator_ID());
//				pstmt.setInt (2, M_Product_ID);
//				rs = pstmt.executeQuery ();
//				if (rs.next ()){
//					count = rs.getInt(1);
					count = DB.getSQLValue(null, sql, getM_Locator_ID(), M_Product_ID);
//				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
//			}finally {//Expert
//				DB.close(rs, pstmt);
			}
		}
		return count != 0;
	}	//	isCanStoreProduct
	
	
	/**
	*  Obtenemos el localizador de default para el almacen seleccionado
	*
	*  @param warehouseId Almacen
	*  @return M_Locator_ID
	*/
	public static int getLocatorID(Properties ctx, int warehouseId, String trxName) throws MedsysException {
		//armamos la cadena sql para encontrar el localizador
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M_Locator_ID FROM M_Locator ");
		sql.append(" WHERE M_Warehouse_ID = ? AND IsActive = 'Y' ");
		sql.append(" ORDER BY IsDefault DESC, PriorityNo DESC, M_Locator_ID ");
		
		int locatorId = DB.getSQLValue(trxName, sql.toString(), warehouseId);
		if(locatorId < 0) {
			throw new MedsysException ("error.noLocatorID");
		}
		return locatorId;
	}
	
	/**
	 * Obtiene de la base de datos el almacen relacionado
	 * @param ctx
	 * @param locatorId
	 * @param trxName
	 * @return
	 */
	public static MWarehouse getWarehouse(Properties ctx, int locatorId, String trxName) {
		MWarehouse warehouse = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  w.* ");
		sql.append("FROM ");
		sql.append("  m_locator l ");
		sql.append("  INNER JOIN m_warehouse w ");
		sql.append("  ON l.m_warehouse_id = w.m_warehouse_id ");
		sql.append("WHERE ");
		sql.append("  l.m_locator_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, locatorId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				warehouse = new MWarehouse(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return warehouse;
	}
	
	/**
     *  beforeSave method created by EXPERT Sistemas to set a Locator by default
     *  in case of any new or modification record, only if no default Locator is
     *  selected. 
     *  @see org.compiere.model.PO#beforeSave(boolean)
     *  @param newRecord
     *  @author Jesus Cantu 13/Nov/2007
     *  @return true
     */
    protected boolean beforeSave (boolean newRecord) {
    	//System.out.println("before Save method Chuy");
    	
    	// Verify if this warehouse contains a locator by default.
    	// if Not, sets the one that is being modified or inserted as default.
    	if (!existLocatorDefault()) {
    		//System.out.println("Setting true");
    		this.setIsDefault(true);
    	}
    	
    	return true;
    }
    
    /**
     * Brings true if any default locator is found.
     * 
     * @return true if a default locator was found.
     */
    private boolean existLocatorDefault () {
    	boolean blResult = false;
    	StringBuilder sql = new StringBuilder(50);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	//MLocator objlMLoc = null;
    	
		Trx m_trx = null;
//		String trx = null;
		
    	sql.append("select * from M_Locator where ");
    	sql.append("M_Warehouse_ID = ");
    	sql.append(getM_Warehouse_ID());
    	sql.append(" AND M_Locator_ID <> ");
    	sql.append(getM_Locator_ID());
    	sql.append(" AND isDefault = 'Y'");
    	
    	try {
    		
    		m_trx 	= Trx.get(Trx.createTrxName("MLocatorTrx"), true);
//    		trx 	= m_trx.getTrxName();
    		
            pstmt 	= DB.prepareStatement(sql.toString(),m_trx.getTrxName());
            rs 		= pstmt.executeQuery();
            
            while (rs.next()) {
            	blResult = true;
            	//objlMLoc =  new MLocator(getCtx(),rs,get_TrxName());
            	if (isDefault()) {
            		//objlMLoc =  new MLocator(getCtx(), rs.getInt("M_Locator_ID"), trx);
            		//System.out.println("Entering is default");
            		// Deschecar el que esta checado.
            		//objlMLoc.setIsDefault(false);
            		//objlMLoc.setX("CHICO");
            		//objlMLoc.setXYZ("X", "Y", "Z");
            		updateDefault(rs.getString("M_Locator_ID"), m_trx.getTrxName());
            		//System.out.println("ID a Salvar = " + rs.getString("value"));
            		/*if (objlMLoc.save()) {
            			System.out.println("Salvado");
            		} else {
            			System.out.println("Mamadas de COMPIERE");
            		}*/
            		
            		//System.out.println("************************************");
            	}
            }
           
            s_log.log(Level.INFO, "existLocatorDefault - MLocator - sql = " + sql.toString());
        } catch (Exception e) {
        	Trx.rollback(m_trx);
            s_log.log(Level.SEVERE, "existLocatorDefault - MLocator", e);
        } finally {
        	DB.close(rs, pstmt);
            Trx.close(m_trx);
        }
    	
    	return blResult;
    	
    }
    
    private void updateDefault(String saLocId, String saTrxName){
    	StringBuilder sql = new StringBuilder(50);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	sql.append("update M_Locator set IsDefault='N' where M_Locator_ID = ");
    	sql.append(saLocId);
    	
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), saTrxName);
    		rs = pstmt.executeQuery();
    	} catch (Exception exc) {
    		exc.printStackTrace();
    	} finally {
    		DB.close(rs, pstmt);
    		rs=null;
    		pstmt=null;
    	}
    	
    }
    
	//TODO Documentar Metodo
//    public Vector<OptionItem> getLocalizador(int m_wh_id) {
//
//		String sql = "select m_locator_id, value from m_locator where m_warehouse_id = ? and isactive='Y'";
//
//		Vector<OptionItem> localizador = null;
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			pstmt.setInt(1, m_wh_id);
//			rs = pstmt.executeQuery();
//			localizador = new Vector<OptionItem>();
//
//			while (rs.next()) {
//				localizador
//						.add(new OptionItem(rs.getString(1), rs.getString(2)));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return localizador;
//	}
    
    
 public static MLocator getMLocatorByValue(Properties ctx, String value){
        
       
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("	SELECT * FROM M_Locator WHERE isActive = 'Y' AND Value = ?	");
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Locator"));
		
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        MLocator locator = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				locator = new MLocator(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
		}

		return locator;
        
    }
 
	/**
	 * Obtiene los localizadores de un almacen por producto y/o lote
	 * 
	 * @param ctx Contexto
	 * @param warehouseId almacen (obligatorio)
	 * @param productId Producto
	 * @param attributeSetInstanceId Lote
	 * @param trxName
	 * @return
	 */
	public static List<MLocator> getLocators(Properties ctx, int warehouseId, int productId, int attributeSetInstanceId, String trxName) {

		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder joins = new StringBuilder();
		where.append("  M_Locator.M_Warehouse_ID=? ");
		params.add(warehouseId);
		if (productId > 0 || attributeSetInstanceId >= 0) {
			joins.append("INNER JOIN m_storage stor ");
			joins.append(" ON (M_Locator.M_Locator_ID=stor.M_Locator_ID) ");
			if (productId > 0) {
				where.append(" AND stor.M_product_id = ?");
				params.add(productId);
			}
			if (attributeSetInstanceId >= 0) {
				where.append(" AND stor.M_AttributeSetInstance_id = ?");
				params.add(attributeSetInstanceId);
			}
		}
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setParameters(params)//
			.setOnlyActiveRecords(true)//
			.setJoins(joins)//
			.setOrderBy(" IsDefault DESC, PriorityNo DESC, M_Locator_ID")//
			.addAccessLevelSQL(true)//
			.list();
	}
	
	/** @return la llave de busqueda o los valores X, Y y Z */
	public String getName() {
		final StringBuilder str = new StringBuilder();
		if (StringUtils.isNotBlank(getValue())) {
			str.append(getValue());
		} else {
			if (StringUtils.isNotBlank(getX())) {
				str.append("X: ");
				str.append(getX());
			}
			if (StringUtils.isNotBlank(getY())) {
				str.append(" Y: ");
				str.append(getY());
			}
			if (StringUtils.isNotBlank(getZ())) {
				str.append(" Z: ");
				str.append(getZ());
			}
		}
		return str.toString();
	}


	 /**
 	 * Obtener los localizadores de un almacen
 	 * @return
 	 */
	public static ArrayList<KeyNamePair> getLocatorsFromStorage(int wareHId, String TrxName) {
		ArrayList<KeyNamePair> lst = new ArrayList<KeyNamePair>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT l.m_locator_id, l.value FROM m_locator l  ");
		sql.append(" INNER JOIN m_warehouse w ON l.m_warehouse_id = w.m_warehouse_id ");
		sql.append(" LEFT JOIN m_storage s ON l.m_locator_id = s.m_locator_id ");
		sql.append(" WHERE w.m_warehouse_id = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), sql.toString(), Table_Name, "l"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, wareHId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}
	
	private MWarehouse warehouse;

	public MWarehouse getWarehouse() {
		if(warehouse == null) {
			warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		}
		return warehouse;
	}
}	//	MLocator
