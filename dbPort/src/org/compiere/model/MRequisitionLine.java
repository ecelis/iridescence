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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
/**
 *	Requisition Line Model
 *
 *  @author Jorg Janke
 *  @version $Id: MRequisitionLine.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class MRequisitionLine extends X_M_RequisitionLine{
	/**
	 *
	 */
	private static final long serialVersionUID = -8890392904883601542L;
	private static CLogger log = CLogger.getCLogger(MRequisitionLine.class);
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_RequisitionLine_ID id
	 *	@param trxName transaction
	 */
	public MRequisitionLine (final Properties ctx, final int M_RequisitionLine_ID, final String trxName)
	{
		super (ctx, M_RequisitionLine_ID, trxName);
		if (M_RequisitionLine_ID == 0)
		{
		//	setM_Requisition_ID (0);
			setLine (0);	// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_RequisitionLine WHERE M_Requisition_ID=@M_Requisition_ID@
			setLineNetAmt (Env.ZERO);
			setPriceActual (Env.ZERO);
			setQty (Env.ONE);	// 1
		}

	}	//	MRequisitionLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRequisitionLine (final Properties ctx, final ResultSet rs, final String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRequisitionLine

	/**
	 * 	Parent Constructor
	 *	@param req requisition
	 */
	public MRequisitionLine (final MRequisition req)
	{
		this (req.getCtx(), 0, req.get_TrxName());
		setClientOrg(req);
		setM_Requisition_ID(req.getM_Requisition_ID());
		//m_M_PriceList_ID = req.getM_PriceList_ID();
		m_M_PriceList_ID = 0;
		m_parent = req;
	}	//	MRequisitionLine

	/** Parent					*/
	private MRequisition	m_parent = null;

	/**	PriceList				*/
	private int 	m_M_PriceList_ID = 0;
	/** Temp BPartner			*/
//	private int		m_C_BPartner_ID = 0;

	/**
	 * @return Returns the c_BPartner_ID.
	 */
//	public int getC_BPartner_ID ()
//	{
//		return m_C_BPartner_ID;
//	}
	/**
	 * @param partner_ID The c_BPartner_ID to set.
	 */
//	public void setC_BPartner_ID (int partner_ID)
//	{
//		m_C_BPartner_ID = partner_ID;
//	}

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MRequisition getParent()
	{
		if (m_parent == null) {
			m_parent = new MRequisition (getCtx(), getM_Requisition_ID(), get_TrxName());
		}
		return m_parent;
	}	//	getParent

	/**
	 * 	Set Price
	 */
	public void setPrice()
	{
		if (getC_Charge_ID() != 0)
		{
			final MCharge charge = MCharge.get(getCtx(), getC_Charge_ID());
			setPriceActual(charge.getChargeAmt());
		}
		if (getM_Product_ID() == 0) {
			return;
		}
		if (m_M_PriceList_ID == 0) {
			m_M_PriceList_ID = getParent().getM_PriceList_ID();
		}
		if (m_M_PriceList_ID == 0)
		{
			log.log(Level.SEVERE, "PriceList unknown!");
		//	return;
		}
		if(m_M_PriceList_ID>0) {
			setPrice (m_M_PriceList_ID);
		}
	}	//	setPrice

	/**
	 * 	Set Price for Product and PriceList
	 * 	@param M_PriceList_ID price list
	 */
	public void setPrice (final int M_PriceList_ID)
	{
		if (getM_Product_ID() == 0) {
			return;
		}
		//
		log.fine("M_PriceList_ID=" + M_PriceList_ID);
//		final boolean isSOTrx = false;
		final MProductPricing pp = new MProductPricing (getM_Product_ID(),
			getC_BPartner_ID(), getQty(), false);
		pp.setM_PriceList_ID(M_PriceList_ID);
	//	pp.setPriceDate(getDateOrdered());
		//
		setPriceActual (pp.getPriceStd());
	}	//	setPrice

	/**
	 * 	Calculate Line Net Amt
	 */
	public void setLineNetAmt ()
	{
		final BigDecimal lineNetAmt = getQty().multiply(getPriceActual());
		super.setLineNetAmt (lineNetAmt);
	}	//	setLineNetAmt


	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	@Override
	protected boolean beforeSave (final boolean newRecord){

		boolean retValue = true;

		//rsolorzano 05/09/2012 validacion conversion UDM
		if(getM_Product().getC_UOM_ID()!= getM_Product().getC_UOMVolume_ID()){

			final MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), getM_Product_ID(), getM_Product().getC_UOMVolume_ID(),null);
			if(rates==null){
				log.saveError(Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion"), getM_Product().getName());
				retValue = false;
			}
		}

		if (getLine() == 0){
			final String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM M_RequisitionLine WHERE M_Requisition_ID=?";
			final int ii = DB.getSQLValue (get_TrxName(), sql, getM_Requisition_ID());
			setLine (ii);
		}
		//	Product & ASI - Charge
		if (getM_Product_ID() != 0 && getC_Charge_ID() != 0){
			setC_Charge_ID(0);
		}
		if (getM_AttributeSetInstance_ID() != 0 && getC_Charge_ID() != 0){
			setM_AttributeSetInstance_ID(0);
		}
		//
		if (getPriceActual().compareTo(Env.ZERO) == 0){
			setPrice();
		}

		setLineNetAmt();

		// Clase de producto para el post
		if (getEXME_ProductClassFact_ID()<=0 && getM_Product_ID()>0){
			setEXME_ProductClassFact_ID(MEXMEProductClassFact.get(getCtx(), getM_Product_ID(), get_TrxName()));
		}

		if(getM_Product()!=null || getC_Charge()!=null){
			retValue = multiplyChargeProdLine();
		}
		
		//card 1335 permitir productos en consigna
//		MEXMEProductoOrg mexmeproductoorg = MEXMEProductoOrg.getProductoOrg(Env.getCtx(), getM_Product_ID(),getAD_Client_ID(), getAD_Org_ID(), null);
//		if(mexmeproductoorg != null && mexmeproductoorg.isConsigna()){
//			final Object param[]={getM_Product().getName()};
//			log.saveError(null,Utilerias.getAppMsg(getCtx(),"material.prod.consig", param));
//			retValue = false;
//		}
		
		return retValue;
	}	//	beforeSave

	/**
	 * 	After Save.
	 * 	Update Total on Header
	 *	@param newRecord if new record
	 *	@param success save was success
	 *	@return true if saved
	 */
	@Override
	protected boolean afterSave (final boolean newRecord, final boolean success)
	{
		if (!success) {
			return success;
		}
		return updateHeader();
	}	//	afterSave


	/**
	 * 	After Delete
	 *	@param success
	 *	@return true/false
	 */
	@Override
	protected boolean afterDelete (final boolean success)
	{
		if (!success) {
			return success;
		}
		return updateHeader();
	}	//	afterDelete

	/**
	 * 	Update Header
	 *	@return header updated
	 */
	private boolean updateHeader()
	{
		log.fine("");
		final String sql = "UPDATE M_Requisition r"
			+ " SET TotalLines="
				+ "(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl "
				+ "WHERE r.M_Requisition_ID=rl.M_Requisition_ID) "
			+ "WHERE M_Requisition_ID=" + getM_Requisition_ID();
		final int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1) {
			log.log(Level.SEVERE, "Header update #" + no);
		}
		m_parent = null;
		return no == 1;
	}	//	updateHeader

	/**
	 * Busca un producto o un cargo de una requisicion
	 * @param requisitionid
	 * @param productId
	 * @param chargeId
	 * @param categoryId
	 * @return
	 */
	public static List<MRequisitionLine> searchLines(final int requisitionid, final int productId, final int chargeId, final int categoryId) {

		final StringBuilder whereClause = new StringBuilder();
		final StringBuilder join = new StringBuilder();
		join.append(" INNER JOIN M_Product ON (M_RequisitionLine.M_Product_ID = M_Product.M_Product_ID) ");

		Query query;

		if(productId>0 && categoryId>0){
			whereClause.append(" ( M_Requisition_ID = ? AND M_RequisitionLine.M_Product_ID=? AND M_Product.M_Product_Category_ID=? ) ");
			query = new Query(Env.getCtx(), Table_Name, whereClause.toString(), null)
			.setJoins(join)
			.addAccessLevelSQL(true)
			.setParameters(requisitionid, productId, categoryId);
		}else if(productId>0){
			whereClause.append(" M_Requisition_ID = ? AND M_RequisitionLine.M_Product_ID=? ");
			query = new Query(Env.getCtx(), Table_Name, whereClause.toString(), null)
			.addAccessLevelSQL(true)
			.setParameters(requisitionid, productId);
		}else if(chargeId>0 && categoryId>0){
			whereClause.append(" ( M_Requisition_ID = ? AND M_RequisitionLine.C_Charge_ID=? AND M_Product.M_Product_Category_ID=? ) ");
			query = new Query(Env.getCtx(), Table_Name, whereClause.toString(), null)
			.setJoins(join)
			.addAccessLevelSQL(true)
			.setParameters(requisitionid, chargeId, categoryId);
		}else if(chargeId>0){
			whereClause.append(" M_Requisition_ID = ? AND M_RequisitionLine.C_Charge_ID=? ");
			query = new Query(Env.getCtx(), Table_Name, whereClause.toString(), null)
			.addAccessLevelSQL(true)
			.setParameters(requisitionid, chargeId);
		}else{
			whereClause.append(" M_Requisition_ID = ? AND M_Product.M_Product_Category_ID=? ");
			query = new Query(Env.getCtx(), Table_Name, whereClause.toString(), null)
			.addAccessLevelSQL(true)
			.setJoins(join)
			.setParameters(requisitionid, categoryId);
		}
		return query.list();
	}
	
	/**
	 * Regresa si existe el cargo/producto en la requisicion
	 * @return
	 */
	public boolean multiplyChargeProdLine(){
		boolean retValue= true;
		final Object param[]=new Object[1];
		if((getProductsLine(true).contains(getM_Product_ID()) && is_new()) && (retValue && param[0]==null)){//2 grupo siempre verdadero (PMD)
			param[0]=getM_Product().getName();
		}else if(getProductsLine(false).contains(getC_Charge_ID()) && is_new()){
			param[0]=getC_Charge().getName();
		}
		if(param[0]!=null){
			log.saveError("Error", Utilerias.getAppMsg(getCtx(), "error.citas.existeMed", param));
			retValue = false;
		}
		return retValue;
	}

	/**
	 * Llena la lista de con id  de productos/cargos de la requisicion
	 */
	public List<Integer> getProductsLine(final boolean isProduct){
		final List<Integer> prodLst = new ArrayList<Integer>();
		String string;
		if(isProduct){
			string = "SELECT m_product_id FROM m_requisitionline WHERE isactive='Y' AND m_requisition_id=? AND m_product_id IS NOT NULL ";
		}else{
			string = "SELECT c_charge_id FROM m_requisitionline WHERE isactive='Y' AND m_requisition_id=? AND c_charge_id is NOT NULL ";
			}
		final StringBuilder sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), string, this.get_TableName()));
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getM_Requisition_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(isProduct){
					prodLst.add(rs.getInt("m_product_id"));
				}else{
					prodLst.add(rs.getInt("c_charge_id"));
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, "getProds", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return prodLst;
	}
	
	public static List<MRequisitionLine> getLines(Properties ctx, String sqlAND, String trxName){
		
		List<MRequisitionLine> list = new ArrayList<MRequisitionLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT line.* FROM  M_Requisitionline line ")
		.append(" INNER JOIN M_Requisition req on req.M_Requisition_id = line.M_Requisition_id ") 
		.append(" AND line.C_ORDERLINE_ID is null AND line.M_PRODUCT_ID is not null ")
		.append(" WHERE req.IsMostrado = 'Y' AND req.DocStatus = 'CO' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MRequisitionLine.Table_Name, "line"));
		
		if (StringUtils.isNotEmpty(sqlAND)) {
			sql.append(sqlAND);
		}
		sql.append(" ORDER BY line.c_bpartner_id, Created DESC ");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MRequisitionLine(ctx, rs, trxName));
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, "getProds", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Obtener las lineas sin provedor
	 * @param requisitionid
	 * @return
	 */
	public static int getLinesWithOutProvider(final int requisitionid) {
		String sql = "SELECT COUNT(m_requisitionline_id) FROM m_requisitionline WHERE m_requisition_id = ? AND c_bpartner_id is null";
		return DB.getSQLValue(null, sql, requisitionid);
	}
	
	public MProduct product;
	
	public MProduct getProduct() {
		if (product == null && getM_Product_ID() > 0) {
			product = new MProduct(getCtx(), getM_Product_ID(), null);
		}
		
		return product;
	}

	/**
	 * Metodo para obtener la Requisicion 
	 * apartir de una Orden de compra 
	 * @param ctx
	 * @param orderID
	 * @param trxName
	 * @return
	 **/
	public static List<MRequisitionLine> getFromPO(final Properties ctx, int orderID, final String trxName){
		List<MRequisitionLine> retValue = new ArrayList<MRequisitionLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM M_REQUISITIONLINE mLine ")
		.append(" INNER JOIN c_orderline oLine on mLine.C_OrderLine_ID = oLine.C_OrderLine_ID ")
		.append(" WHERE oLine.C_Order_ID = ?  ")	
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "mLine"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, orderID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MRequisitionLine(ctx, rs, trxName));
			}

		} catch (final SQLException e) {
			log.log(Level.SEVERE, "getReadyPO", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

}	//	MRequisitionLine
