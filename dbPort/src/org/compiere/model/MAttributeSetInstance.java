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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;

/**
 *  Product Attribute Set Instance
 *
 *	@author Jorg Janke
 *	@version $Id: MAttributeSetInstance.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MAttributeSetInstance extends X_M_AttributeSetInstance
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	/**
	 * 	Get Attribute Set Instance from ID or Product
	 *	@param ctx context
	 * 	@param M_AttributeSetInstance_ID id or 0
	 * 	@param M_Product_ID required if id is 0
	 * 	@return Attribute Set Instance or null
	 */
	public static MAttributeSetInstance get (Properties ctx, 
		int M_AttributeSetInstance_ID, int M_Product_ID)
	{
		MAttributeSetInstance retValue = null;
		//	Load Instance if not 0
		if (M_AttributeSetInstance_ID != 0)
		{
			s_log.fine("From M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
			return new MAttributeSetInstance (ctx, M_AttributeSetInstance_ID, null);
		}
		//	Get new from Product
		s_log.fine("From M_Product_ID=" + M_Product_ID);
		if (M_Product_ID == 0)
			return null;
		String sql = "SELECT po.M_AttributeSet_ID, M_Product.M_AttributeSetInstance_ID "
			+ " FROM M_Product  "
			+ " INNER JOIN EXME_ProductoOrg po ON (M_Product.M_Product_ID=po.M_Product_ID AND po.IsActive='Y' AND po.AD_Org_ID = "+Env.getAD_Org_ID(Env.getCtx())+ ") "
			+ " WHERE M_Product.M_Product_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_Product_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				int M_AttributeSet_ID = rs.getInt(1);
			//	M_AttributeSetInstance_ID = rs.getInt(2);	//	needed ?
				//
				retValue = new MAttributeSetInstance (ctx, 0, M_AttributeSet_ID, null);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		//
		return retValue;
	}	//	get

	private static CLogger		s_log = CLogger.getCLogger (MAttributeSetInstance.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_AttributeSetInstance_ID id
	 *	@param trxName transaction
	 */
	public MAttributeSetInstance (Properties ctx, int M_AttributeSetInstance_ID, String trxName)
	{
		super (ctx, M_AttributeSetInstance_ID, trxName);
		if (M_AttributeSetInstance_ID == 0)
		{
		}
	}	//	MAttributeSetInstance

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MAttributeSetInstance (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAttributeSetInstance

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_AttributeSetInstance_ID id
	 * 	@param M_AttributeSet_ID attribute set
	 *	@param trxName transaction
	 */
	public MAttributeSetInstance (Properties ctx, int M_AttributeSetInstance_ID, 
		int M_AttributeSet_ID, String trxName)
	{
		this (ctx, M_AttributeSetInstance_ID, trxName);
		setM_AttributeSet_ID(M_AttributeSet_ID);
	}	//	MAttributeSetInstance

	/**	Attribute Set				*/
	private MAttributeSet 		m_mas = null;
	/**	Date Format					*/
	private DateFormat			m_dateFormat = DisplayType.getDateFormat(DisplayType.Date);

	/**
	 * 	Set Attribute Set
	 * 	@param mas attribute set
	 */
	public void setMAttributeSet (MAttributeSet mas)
	{
		m_mas = mas;
		setM_AttributeSet_ID(mas.getM_AttributeSet_ID());
	}	//	setAttributeSet

	/**
	 * 	Get Attribute Set
	 *	@return Attrbute Set or null
	 */
	public MAttributeSet getMAttributeSet()
	{
		if (m_mas == null && getM_AttributeSet_ID() != 0)
			m_mas = new MAttributeSet (getCtx(), getM_AttributeSet_ID(), get_TrxName());
		return m_mas;
	}	//	getMAttributeSet

	/**
	 * 	Set Description.
	 * 	- Product Values
	 * 	- Instance Values
	 * 	- SerNo	= #123
	 *  - Lot 	= \u00ab123\u00bb
	 *  - GuaranteeDate	= 10/25/2003
	 */
	public void setDescription()
	{
		//	Make sure we have a Attribute Set
		getMAttributeSet();
		if (m_mas == null)
		{
			setDescription ("");
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		//	Instance Attribute Values
		MAttribute[] attributes = m_mas.getMAttributes(true);
		for (int i = 0; i < attributes.length; i++)
		{
			MAttributeInstance mai = attributes[i].getMAttributeInstance(getM_AttributeSetInstance_ID());
			if (mai != null && mai.getValue() != null)
			{
				if (sb.length() > 0)
					sb.append("_");
				sb.append(mai.getValue());
			}
		}
		//	SerNo
		if (m_mas.isSerNo() && getSerNo() != null)
		{
			if (sb.length() > 0)
				sb.append("_");
			sb.append(m_mas.getSerNoCharStart()).append(getSerNo()).append(m_mas.getSerNoCharEnd());
		}
		//	Lot
		if (m_mas.isLot() && getLot() != null)
		{
			if (sb.length() > 0)
				sb.append("_");
			sb.append(m_mas.getLotCharStart()).append(getLot()).append(m_mas.getLotCharEnd());
		}
		//	GuaranteeDate
		if (m_mas.isGuaranteeDate() && getGuaranteeDate() != null)
		{
			if (sb.length() > 0)
				sb.append("_");
			sb.append (m_dateFormat.format(getGuaranteeDate()));
		}

		//	Product Attribute Values
		attributes = m_mas.getMAttributes(false);
		for (int i = 0; i < attributes.length; i++)
		{
			MAttributeInstance mai = attributes[i].getMAttributeInstance(getM_AttributeSetInstance_ID());
			if (mai != null && mai.getValue() != null)
			{
				if (sb.length() > 0)
					sb.append("_");
				sb.append(mai.getValue());
			}
		}
		setDescription (sb.toString());
	}	//	setDescription


	/**
	 * 	Get Guarantee Date
	 * 	@param getNew if true calculates/sets guarantee date
	 *	@return guarantee date or null if days = 0
	 */
	public Timestamp getGuaranteeDate(boolean getNew)
	{
		if (getNew)
		{
			int days = getMAttributeSet().getGuaranteeDays();
			if (days > 0)
			{
				Timestamp ts = TimeUtil.addDays(DB.getTimestampForOrg(getCtx()), days);
				setGuaranteeDate(ts);
			}
		}
		return getGuaranteeDate();
	}	//	getGuaranteeDate

	/**
	 * 	Get Lot No
	 * 	@param getNew if true create/set new lot
	 * 	@param M_Product_ID product used if new
	 *	@return lot
	 */
	public String getLot (boolean getNew, int M_Product_ID)
	{
		if (getNew)
			createLot(M_Product_ID);
		return getLot();
	}	//	getLot

	/**
	 * 	Create Lot
	 * 	@param M_Product_ID product used if new
	 *	@return lot info
	 */
	public KeyNamePair createLot (int M_Product_ID)
	{
		KeyNamePair retValue = null;
		int M_LotCtl_ID = getMAttributeSet().getM_LotCtl_ID();
		if (M_LotCtl_ID != 0)
		{
			MLotCtl ctl = new MLotCtl (getCtx(), M_LotCtl_ID, null);
			MLot lot = ctl.createLot(M_Product_ID);
			setM_Lot_ID (lot.getM_Lot_ID());
			setLot (lot.getName());
			retValue = new KeyNamePair (lot.getM_Lot_ID(), lot.getName());	
		}
		return retValue;
	}	//	createLot
	
	/**
	 * 	To to find lot and set Lot/ID
	 *	@param Lot lot
	 *	@param M_Product_ID product
	 */
	public void setLot (String Lot, int M_Product_ID)
	{
		//	Try to find it
		MLot mLot = MLot.getProductLot(getCtx(), M_Product_ID, Lot, get_TrxName());
		if (mLot != null)
			setM_Lot_ID(mLot.getM_Lot_ID());
		setLot (Lot);
	}	//	setLot

	/**
	 * 	Exclude Lot creation
	 *	@param AD_Column_ID column
	 *	@param isSOTrx SO
	 *	@return true if excluded
	 */
	public boolean isExcludeLot (int AD_Column_ID, boolean isSOTrx)
	{
		getMAttributeSet();
		if (m_mas != null)
			return m_mas.isExcludeLot (AD_Column_ID, isSOTrx);
		return false;
	}	//	isExcludeLot

	/**
	 *	Get Serial No
	 * 	@param getNew if true create/set new Ser No
	 *	@return Serial Number
	 */
	public String getSerNo (boolean getNew)
	{
		if (getNew)
		{
			int M_SerNoCtl_ID = getMAttributeSet().getM_SerNoCtl_ID();
			if (M_SerNoCtl_ID != 0)
			{
				MSerNoCtl ctl = new MSerNoCtl (getCtx(), M_SerNoCtl_ID, get_TrxName());
				setSerNo(ctl.createSerNo());
			}
		}
		return getSerNo();
	}	//	getSerNo

	/**
	 *	Exclude SerNo creation
	 *	@param AD_Column_ID column
	 *	@param isSOTrx SO
	 *	@return true if excluded
	 */
	public boolean isExcludeSerNo (int AD_Column_ID, boolean isSOTrx)
	{
		getMAttributeSet();
		if (m_mas != null)
			return m_mas.isExcludeSerNo (AD_Column_ID, isSOTrx);
		return false;
	}	//	isExcludeSerNo
	
	/**
	 * 
	 * @param ctx
	 * @param labelerID
	 * @param warehouseId
	 * @return
	 */
	public static List<MAttributeSetInstance> getLotes(Properties ctx, int warehouseId) {
		final List<MAttributeSetInstance> list = new ArrayList<MAttributeSetInstance>();
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		sql.append("select distinct(setinstance.m_attributesetinstance_id), setinstance.created from m_attributesetinstance setinstance ");
		sql.append("inner join m_storage stor ");
		sql.append("on (setinstance.m_attributesetinstance_id=stor.m_attributesetinstance_id) ");
		sql.append("inner join exme_labeler labeler ");
		sql.append("on(setinstance.exme_labeler_id = labeler.exme_labeler_id) ");
		sql.append("inner join m_locator loc ");
		sql.append("on(loc.m_locator_id = stor.m_locator_id) ");
		sql.append("where labeler.isvacuna ='Y' and loc.m_warehouse_id = ? order by setinstance.created desc ");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, warehouseId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MAttributeSetInstance lot = new MAttributeSetInstance(ctx, rs.getInt("m_attributesetinstance_id"), null);
				list.add(lot);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param attributesetID
	 * @param trxName
	 * @return
	 */
	public static List<MAttributeSetInstance> get(Properties ctx, int attributesetID, String trxName){

		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" M_Attributeset_ID = ?");
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setParameters(attributesetID)//
			.setOnlyActiveRecords(true)//
			.setOrderBy(" Updated desc")
			.addAccessLevelSQL(true)//
			.list();
		
	}
	
	/**
	 * Obtiene los lotes de un producto
	 * ordenados por vigencia en forma descendente
	 * @param ctx
	 * @param attributesetID
	 * @param trxName
	 * @return
	 */
	public static List<MAttributeSetInstance> getLot(Properties ctx, int productId, String trxName){
		return getLots(ctx, productId, -1, trxName);
	}
	/**
	 * Obtiene los lotes de un producto por almacen
	 * ordenados por vigencia en forma descendente
	 * 
	 * @param ctx Contexto
	 * @param productId Producto (obligatorio)
	 * @param warehouseId Almacen
	 * @param trxName
	 * @return
	 */
	public static List<MAttributeSetInstance> getLots(Properties ctx, int productId, int warehouseId, String trxName) {
		return getLots(ctx, productId, warehouseId > 0 ? new int[] { warehouseId } : null, trxName);
	}
	
	/**
	 * Obtiene los lotes de un producto por almacen
	 * ordenados por vigencia en forma descendente
	 * 
	 * @param ctx Contexto
	 * @param productId Producto (obligatorio)
	 * @param warehouseId Almacenes
	 * @param trxName
	 * @return
	 */
	public static List<MAttributeSetInstance> getLots(Properties ctx, 
		int productId, 
		int[] warehouseArray, 
		String trxName) {
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder joins = new StringBuilder();
		joins.append("INNER JOIN m_storage stor ");
		joins.append(" ON (M_AttributeSetInstance.m_attributesetinstance_id=stor.m_attributesetinstance_id) ");
		where.append(" stor.M_product_id=?");
		params.add(productId);
		if (warehouseArray != null && warehouseArray.length > 0 ) {//|| locatorId > 0) {
//			if (locatorId > 0) {
//				where.append(" AND stor.m_locator_id=? ");
//				params.add(locatorId);
//			} else if (warehouseId > 0) {
				joins.append("INNER JOIN m_locator l ON (stor.m_locator_id=l.m_locator_id) ");
				where.append(" AND l.M_Warehouse_ID IN (");
				for (int warehouseId : warehouseArray) {
					if (warehouseId > 0) {
						if (params.size() > 1) {
							where.append(",");
						}
						where.append("?");
						params.add(warehouseId);
					}
				}
				where.append(") ");
//			}
		}
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setParameters(params)//
			.setJoins(joins)//
			.setOnlyActiveRecords(true)//
			.setOrderBy(" GuaranteeDate DESC, Updated DESC")
			.addAccessLevelSQL(true)//
			.list();
	}
	
	/**
	 * Lotes de un producto en un localizador
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productId
	 *            Producto
	 * @param locatorId
	 *            Localizador
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de lotes
	 */
	public static List<MAttributeSetInstance> getLocatorLots(Properties ctx, int productId, int locatorId, String trxName) {
		final List<MAttributeSetInstance> list = new ArrayList<MAttributeSetInstance>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  att.* ");
		sql.append("FROM ");
		sql.append("  m_attributesetinstance att ");
		sql.append("  INNER JOIN m_storage s ");
		sql.append("  ON (att.m_product_id = s.m_product_id AND ");
		sql.append("  att.m_attributesetinstance_id = s.m_attributesetinstance_id) ");
		sql.append("WHERE ");
		sql.append("  att.m_product_id = ? AND ");
		sql.append("  s.m_locator_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "att"));
		sql.append(" order by att.GuaranteeDate DESC");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, locatorId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new MAttributeSetInstance(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/** @return cadena concatenando las columnas Lot y Description */
	public String getName() {
		final StringBuilder str = new StringBuilder();
		if(StringUtils.isNotBlank(getLot())) {
			str.append(getLot());
		}
		if(StringUtils.isNotBlank(getDescription())) {
			if(str.length()> 0) {
				str.append(" - ");
			}
			str.append(getDescription());
		}
		return str.toString();
	}
	
	/** @return cadena con la fecha de vigencia traducida */
	public String getGuaranteeDateStr() {
		final StringBuilder str = new StringBuilder();
		if(getGuaranteeDate() != null) {
			str.append(Utilerias.getMsg(getCtx(), "msj.validoHasta")).append(" ");
			str.append(Constantes.getSDFFechaCortaDMA(getCtx()).formatConvert(getGuaranteeDate()));
		}
		return str.toString();
	}

		/**
	 * Obtener el attributesetinstance dado un nombre (Lot), y/o un producto
	 * @param ctx
	 * @param lot string del lote
	 * @param productId id del producto 
	 * @param active activos o inactivos
	 * @param trxName
	 * @return
	 */
	public static int getAttributeSetInstance(Properties ctx, String lot
			, int productId, boolean active, String trxName){
		int mAttsetId = -1;

		final StringBuilder sql = new StringBuilder("SELECT m_attributesetinstance_id FROM m_attributesetinstance WHERE isactive = ? ");
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(active ? "Y" : "N");
		
		if (StringUtils.isNotBlank(lot)) {
			sql.append(" AND UPPER(Lot) = ? ");
			params.add(lot.toUpperCase());
		}
		
		if (productId > 0) {
			sql.append(" AND m_product_id = ? ");
			params.add(productId);
		}
		
		sql.append(" AND ad_org_id = ? ");
		params.add(Env.getAD_Org_ID(ctx));
		
		mAttsetId = DB.getSQLValue(trxName, sql.toString(), params);

		return mAttsetId;
	}

	@Override
	public String toString() {
		return getLot();
	}
	
	
	/**
	 * Lotes del producto previamente configurados
	 * @param ctx: Contexto
	 * @param productId: Producto
	 * @param trxName: Transacción
	 * @return
	 */
	public static List<KeyNamePair> getLotsOfProduct(Properties ctx, int productId, String trxName){
		final List<KeyNamePair> lstKeyNamePair = new ArrayList<KeyNamePair>();
		final List<MAttributeSetInstance> lst = new Query(ctx, Table_Name, " M_Product_ID = ?", trxName)//
			.setParameters(productId)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" GuaranteeDate ASC ")
			.list();
		
		for (MAttributeSetInstance bean: lst) {
			lstKeyNamePair.add(new KeyNamePair(bean.get_ID(), bean.getLot() ));
		}
		return lstKeyNamePair;
	}
}	//	MAttributeSetInstance
