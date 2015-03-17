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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DynamicModel;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Order Line Model.
 * 	<code>
 * 			MOrderLine ol = new MOrderLine(m_order);
			ol.setM_Product_ID(wbl.getM_Product_ID());
			ol.setQtyOrdered(wbl.getQuantity());
			ol.setPrice();
			ol.setPriceActual(wbl.getPrice());
			ol.setTax();
			ol.save();

 *	</code>
 *  @author Jorg Janke
 *  @version $Id: MOrderLine.java,v 1.4 2006/08/11 02:26:22 mrojas Exp $
 */
public class MOrderLine extends X_C_OrderLine
{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -4470583839648791372L;
	/** define si la OC se esta comprando en automactico*/
	private boolean automatic = false;
	private int 			m_M_PriceList_ID = 0;
	//
	private boolean			m_IsSOTrx = true;
	//	Product Pricing
	private MProductPricing	m_productPrice = null;
	private MProductPrice   mproductPricePO = null; 
	
	/** Cached Precision		*/
	private Integer			m_precision = null;
	/**	Product					*/
	private MProduct 		m_product = null;
	/** Parent					*/
	private MOrder			m_parent = null;
	
	

	/**
	 * 	Get Order Unreserved Qty
	 *	@param ctx context
	 *	@param M_Warehouse_ID wh
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param excludeC_OrderLine_ID exclude C_OrderLine_ID
	 *	@return Unreserved Qty
	 */
	public static BigDecimal getNotReserved (Properties ctx, int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, int excludeC_OrderLine_ID)
	{
		BigDecimal retValue = Env.ZERO;
		String sql = "SELECT SUM(QtyOrdered-QtyDelivered-QtyReserved) "
			+ "FROM C_OrderLine ol"
			+ " INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) "
			+ "WHERE ol.M_Warehouse_ID=?"	//	#1
			+ " AND M_Product_ID=?"			//	#2
			+ " AND o.IsSOTrx='Y' AND o.DocStatus='DR'"
			+ " AND QtyOrdered-QtyDelivered-QtyReserved<>0"
			+ " AND ol.C_OrderLine_ID<>?";
		if (M_AttributeSetInstance_ID != 0)
			sql += " AND M_AttributeSetInstance_ID=?";
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, M_Warehouse_ID);
			pstmt.setInt (2, M_Product_ID);
			pstmt.setInt (3, excludeC_OrderLine_ID);
			if (M_AttributeSetInstance_ID != 0)
				pstmt.setInt (4, M_AttributeSetInstance_ID);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = rs.getBigDecimal(1);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
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
		if (retValue == null)
			s_log.fine("-");
		else
			s_log.fine(retValue.toString());
		return retValue;
	}	//	getNotReserved
	
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MOrderLine.class);
	
	/**************************************************************************
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_OrderLine_ID  order line to load
	 *  @param trxName trx name
	 */
	public MOrderLine (Properties ctx, int C_OrderLine_ID, String trxName)
	{
		super (ctx, C_OrderLine_ID, trxName);
		if (C_OrderLine_ID == 0)
		{
		//	setC_Order_ID (0);
		//	setLine (0);
		//	setM_Warehouse_ID (0);	// @M_Warehouse_ID@
		//	setC_BPartner_ID(0);
		//	setC_BPartner_Location_ID (0);	// @C_BPartner_Location_ID@
		//	setC_Currency_ID (0);	// @C_Currency_ID@
		//	setDateOrdered (new Timestamp(System.currentTimeMillis()));	// @DateOrdered@
			//
		//	setC_Tax_ID (0);
		//	setC_UOM_ID (0);
			//
			setFreightAmt (Env.ZERO);
			setLineNetAmt (Env.ZERO);
			//
			setPriceEntered(Env.ZERO);
			setPriceActual (Env.ZERO);
			setPriceLimit (Env.ZERO);
			setPriceList (Env.ZERO);
			//
			setM_AttributeSetInstance_ID(0);
			//
			setQtyEntered (Env.ZERO);
			setQtyOrdered (Env.ZERO);	// 1
			setQtyDelivered (Env.ZERO);
			setQtyInvoiced (Env.ZERO);
			setQtyReserved (Env.ZERO);
			//
			setIsDescription (false);	// N
			setProcessed (false);
			setLine (0);
		}
	}	//	MOrderLine
	
	/**
	 *  Parent Constructor.
	 		ol.setM_Product_ID(wbl.getM_Product_ID());
			ol.setQtyOrdered(wbl.getQuantity());
			ol.setPrice();
			ol.setPriceActual(wbl.getPrice());
			ol.setTax();
			ol.save();
	 *  @param  order parent order
	 */
	public MOrderLine (MOrder order)
	{
		this (order.getCtx(), 0, order.get_TrxName());
		if (order.get_ID() == 0)
			throw new IllegalArgumentException("Header not saved");
		setC_Order_ID (order.getC_Order_ID());	//	parent
		setOrder(order);
	}	//	MOrderLine

	public MProductPrice getProductPricePO(){
		if(mproductPricePO==null){
			mproductPricePO = MProductPrice.getProductPrice(
				getParent().getC_BPartner_ID()
				, 0
				, getM_Product_ID() 
				, false);
		}
		return mproductPricePO;
	}
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *  @param trxName transaction
	 */
	public MOrderLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MOrderLine

	/**
	 * 	Set Defaults from Order.
	 * 	Does not set Parent !!
	 * 	@param order order
	 */
	public void setOrder (MOrder order)
	{
		setClientOrg(order);
		setC_BPartner_ID(order.getC_BPartner_ID());
		setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
		setM_Warehouse_ID(order.getM_Warehouse_ID());
		setDateOrdered(order.getDateOrdered());
		setDatePromised(order.getDatePromised());
		setC_Currency_ID(order.getC_Currency_ID());
		//
		setHeaderInfo(order);	//	sets m_order
	}	//	setOrder

	/**
	 * 	Set Header Info
	 *	@param order order
	 */
	public void setHeaderInfo (MOrder order)
	{
		m_parent = order;
		m_precision = new Integer(order.getPrecision());
		m_M_PriceList_ID = order.getM_PriceList_ID();
		m_IsSOTrx = order.isSOTrx();
	}	//	setHeaderInfo
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MOrder getParent()
	{
		if (m_parent == null)
			m_parent = new MOrder(getCtx(), getC_Order_ID(), get_TrxName());
		return m_parent;
	}	//	getParent
	
	/**
	 * 	Set Price Entered/Actual.
	 * 	Use this Method if the Line UOM is the Product UOM 
	 *	@param PriceActual price
	 */
	public void setPrice (BigDecimal PriceActual)
	{
		setPriceEntered(PriceActual);
		setPriceActual (PriceActual);
	}	//	setPrice

	/**
	 * 	Set Price Actual.
	 * 	(actual price is not updateable)
	 *	@param PriceActual actual price
	 */
	public void setPriceActual (BigDecimal PriceActual)
	{
		if (PriceActual == null) 
			throw new IllegalArgumentException ("PriceActual is mandatory");
		set_ValueNoCheck("PriceActual", PriceActual);
	}	//	setPriceActual

	
	/**
	 *  Method to obtain the order lines of the current order
	 *  @param int c_order_id
	 *  @return Vector<DynamicModel>	 
	 */
	public static Vector<DynamicModel> getLinesByOrder(int c_order_id){
			
			Vector<DynamicModel> data=null;
								
			String sql = "select " +
					"col.c_orderline_id, " +
					"col.c_bpartner_id, " +
					"col.c_bpartner_location_id, " +
					"col.dateordered, " +
					"col.datepromised, " +
					"col.line, " +
					"col.m_product_id, " +
					"col.c_uom_id, " +
					"col.description, " +
					"col.qtyentered, " +
					"col.priceactual, " +
					"col.priceentered, " +
					"col.discount, " +
					"col.c_tax_id, " +
					"col.c_campaign_id, " +
					"col.linenetamt, " +
					"col.c_order_id, " +
					"col.ad_orgtrx_id, "+
			        "col.c_campaign_id, "+
			        "col.c_project_id, "+
			        "col.c_activity_id "+
					"from c_orderline col " +
					"where col.c_order_id = ? and col.isactive='Y'";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			try{
		
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, c_order_id);
				rs = pstmt.executeQuery();
				rsmd=rs.getMetaData();
			data = new Vector<DynamicModel>();
		
			while (rs.next()){
				
				DynamicModel dynamicModel=new DynamicModel();
								for(int i=1;i<=rsmd.getColumnCount();i++)
									dynamicModel.setValue(rsmd.getColumnName(i), rs.getString(i),rsmd.getColumnClassName(i));
								data.add(dynamicModel);
					}
				}catch(SQLException e){
						e.printStackTrace();
					}
				finally{
					try{
						if(pstmt!=null)
							pstmt.close();
						if(rs!=null)
							rs.close();
					}catch(SQLException e){
						s_log.log(Level.SEVERE, "getLinesbyOrder", e);
					}
				}
			
			return data;
		}
	
	/**
	 * 	Set Price for Product and PriceList.
	 * 	Use only if newly created.
	 * 	Uses standard price list of not set by order constructor
	 */
	public void setPrice()
	{
		if (getM_Product_ID() == 0)
			return;
		//if (m_M_PriceList_ID == 0)
			//throw new IllegalStateException("PriceList unknown!");
		setPrice (m_M_PriceList_ID);
	}	//	setPrice

	/**
	 * 	Set Price for Product and PriceList
	 * 	@param M_PriceList_ID price list
	 */
	public void setPrice (int M_PriceList_ID)
	{
		if (getM_Product_ID() == 0)
			return;
		//
		log.fine("M_PriceList_ID=" + M_PriceList_ID);

		if(M_PriceList_ID>0){
			getProductPricing (M_PriceList_ID);
			setPriceActual (m_productPrice.getPriceStd());
			setPriceList (m_productPrice.getPriceList());
			setPriceLimit (m_productPrice.getPriceLimit());
			//
			if (getQtyEntered().compareTo(getQtyOrdered()) == 0)
				setPriceEntered(getPriceActual());
			else
				setPriceEntered(getPriceActual().multiply(getQtyOrdered()
						.divide(getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP)));	//	recision

			//	Calculate Discount
			setDiscount(m_productPrice.getDiscount());
			//	Set UOM
			setC_UOM_ID(m_productPrice.getC_UOM_ID());
			if(getC_UOM_ID() == getC_UOMVolume_ID()){
				//si la unidad es la misma colocar las cantidades tmb en  campos de empaque
				setPriceActual_Vol(m_productPrice.getPriceStd());
				setPriceList_Vol(m_productPrice.getPriceList());
			}
		}
		else
		{
			setPriceActual (Env.ZERO);
			setPriceList (Env.ZERO);
			setPriceLimit (Env.ZERO);
			setPriceEntered(getPriceActual());
			setDiscount(Env.ZERO);
			setC_UOM_ID(getProduct().getC_UOM_ID());	
		}

	}	//	setPrice

	/**
	 * 	Get and calculate Product Pricing
	 *	@param M_PriceList_ID id
	 *	@return product pricing
	 */
	private MProductPricing getProductPricing (int M_PriceList_ID)
	{
		m_productPrice = new MProductPricing (getM_Product_ID(), 
			getC_BPartner_ID(), getQtyOrdered(), m_IsSOTrx);
		m_productPrice.setM_PriceList_ID(M_PriceList_ID);
		m_productPrice.setPriceDate(getDateOrdered());
		//
		m_productPrice.calculatePrice();
		return m_productPrice;
	}	//	getProductPrice
	
	/**
	 *	Set Tax
	 *	@return true if tax is set
	 */
	public boolean setTax()
	{
		int ii = Tax.get(getCtx(), getM_Product_ID(), getC_Charge_ID(), getDateOrdered(), getDateOrdered(),
			getAD_Org_ID(), getM_Warehouse_ID(),
			getC_BPartner_Location_ID(),		//	should be bill to
			getC_BPartner_Location_ID(), m_IsSOTrx);
		if (ii == 0)
		{
			log.log(Level.SEVERE, "No Tax found");
			return false;
		}
		setC_Tax_ID (ii);
		return true;
	}	//	setTax
	
	/**
	 * 	Calculate Extended Amt.
	 * 	May or may not include tax
	 */
	public void setLineNetAmt ()
	{
		if (getM_Product_ID() > 0) {
			BigDecimal bd;
			if (getC_UOM_ID() == getC_UOMVolume_ID()) {
				bd = getPriceActual().multiply(getQtyOrdered());

			} else {
				bd = getPriceActual_Vol().multiply(getQtyOrdered_Vol());

			}
			if (bd.scale() > getPrecision())
				bd = bd.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
			super.setLineNetAmt(bd);
		}
		// setLineNetAmt
	}
		
	
	/**
	 * 	Get Currency Precision from Currency
	 *	@return precision
	 */
	public int getPrecision()
	{
		if (m_precision != null)
			return m_precision.intValue();
		//
		if (getC_Currency_ID() == 0)
		{
			setOrder (getParent());
			if (m_precision != null)
				return m_precision.intValue();
		}
		if (getC_Currency_ID() != 0)
		{
			MCurrency cur = MCurrency.get(getCtx(), getC_Currency_ID());
			if (cur.get_ID() != 0)
			{
				m_precision = new Integer (cur.getStdPrecision());
				return m_precision.intValue();
			}
		}
		//	Fallback
		String sql = "SELECT c.StdPrecision "
			+ "FROM C_Currency c INNER JOIN C_Order x ON (x.C_Currency_ID=c.C_Currency_ID) "
			+ "WHERE x.C_Order_ID=?";
		int i = DB.getSQLValue(get_TrxName(), sql, getC_Order_ID());
		m_precision = new Integer(i);
		return m_precision.intValue();
	}	//	getPrecision
	
	/**
	 * 	Set Product
	 *	@param product product
	 */
	public void setProduct (MProduct product)
	{
		m_product = product;
		if (m_product != null)
		{
			setM_Product_ID(m_product.getM_Product_ID());
			setC_UOM_ID (m_product.getC_UOM_ID());
		}
		else
		{
			setM_Product_ID(0);
			set_ValueNoCheck ("C_UOM_ID", null);
		}
		setM_AttributeSetInstance_ID(0);
	}	//	setProduct

	
	/**
	 * 	Set M_Product_ID
	 *	@param M_Product_ID product
	 *	@param setUOM set also UOM
	 */
	public void setM_Product_ID (int M_Product_ID, boolean setUOM)
	{
		if (setUOM)
			setProduct(MProduct.get(getCtx(), M_Product_ID));
		else
			super.setM_Product_ID (M_Product_ID);
		setM_AttributeSetInstance_ID(0);
	}	//	setM_Product_ID
	
	/**
	 * 	Set Product and UOM
	 *	@param M_Product_ID product
	 *	@param C_UOM_ID uom
	 */
	public void setM_Product_ID (int M_Product_ID, int C_UOM_ID)
	{
		super.setM_Product_ID (M_Product_ID);
		if (C_UOM_ID != 0)
			super.setC_UOM_ID(C_UOM_ID);
		setM_AttributeSetInstance_ID(0);
	}	//	setM_Product_ID
	
	
	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product =  MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProduct
	
	/**
	 * 	Set M_AttributeSetInstance_ID
	 *	@param M_AttributeSetInstance_ID id
	 */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID == 0)		//	 0 is valid ID
			set_Value("M_AttributeSetInstance_ID", new Integer(0));
		else
			super.setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
	}	//	setM_AttributeSetInstance_ID
	
	/**
	 * 	Set Warehouse
	 *	@param M_Warehouse_ID warehouse
	 */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (getM_Warehouse_ID() > 0
			&& getM_Warehouse_ID() != M_Warehouse_ID
			&& !canChangeWarehouse())
			log.severe("Ignored - Already Delivered/Invoiced/Reserved");
		else
			super.setM_Warehouse_ID (M_Warehouse_ID);
	}	//	setM_Warehouse_ID
	
	/**
	 * 	Can Change Warehouse
	 *	@return true if warehouse can be changed
	 */
	public boolean canChangeWarehouse()
	{
		if (getQtyDelivered().signum() != 0)
		{
			log.saveError("Error", Msg.translate(getCtx(), "QtyDelivered") + "=" + getQtyDelivered());
			return false;
		}
		if (getQtyInvoiced().signum() != 0)
		{
			log.saveError("Error", Msg.translate(getCtx(), "QtyInvoiced") + "=" + getQtyInvoiced());
			return false;
		}
		if (getQtyReserved().signum() != 0)
		{
			log.saveError("Error", Msg.translate(getCtx(), "QtyReserved") + "=" + getQtyReserved());
			return false;
		}
		//	We can change
		return true;
	}	//	canChangeWarehouse
	
	/**
	 * 	Get C_Project_ID
	 *	@return project
	 */
	public int getC_Project_ID()
	{
		int ii = super.getC_Project_ID ();
		if (ii == 0)
			ii = getParent().getC_Project_ID();
		return ii;
	}	//	getC_Project_ID
	
	/**
	 * 	Get C_Activity_ID
	 *	@return Activity
	 */
	public int getC_Activity_ID()
	{
		int ii = 0;	//	super.getC_Activity_ID ();
		if (ii == 0)
			ii = getParent().getC_Activity_ID();
		return ii;
	}	//	getC_Activity_ID
	
	/**
	 * 	Get C_Campaign_ID
	 *	@return Campaign
	 */
	public int getC_Campaign_ID()
	{
		int ii = 0;	//	super.getC_Campaign_ID ();
		if (ii == 0)
			ii = getParent().getC_Campaign_ID();
		return ii;
	}	//	getC_Campaign_ID
	
	/**
	 * 	Get User2_ID
	 *	@return User2
	 */
	public int getUser1_ID ()
	{
		int ii = 0;	//	super.getUser1_ID ();
		if (ii == 0)
			ii = getParent().getUser1_ID();
		return ii;
	}	//	getUser1_ID

	/**
	 * 	Get User2_ID
	 *	@return User2
	 */
	public int getUser2_ID ()
	{
		int ii = 0;	//	super.getUser2_ID ();
		if (ii == 0)
			ii = getParent().getUser2_ID();
		return ii;
	}	//	getUser2_ID

	/**************************************************************************
	 * 	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MOrderLine[")
			.append(get_ID()).append(",Line=").append(getLine())
			.append(",Ordered=").append(getQtyOrdered())
			.append(",Delivered=").append(getQtyDelivered())
			.append(",Invoiced=").append(getQtyInvoiced())
			.append(",Reserved=").append(getQtyReserved())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription
	
	/**
	 * 	Get Description Text.
	 * 	For jsp access (vs. isDescription)
	 *	@return description
	 */
	public String getDescriptionText()
	{
		return super.getDescription();
	}	//	getDescriptionText
	
	/**
	 * 	Get Name
	 *	@return get the name of the line (from Product)
	 */
	public String getName()
	{
		getProduct();
		if (m_product != null)
			return m_product.getName();
		if (getC_Charge_ID() != 0)
		{
			MCharge charge = MCharge.get(getCtx(), getC_Charge_ID());
			return charge.getName();
		}
		return "";
	}	//	getName

	/**
	 * 	Set C_Charge_ID
	 *	@param C_Charge_ID charge
	 */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		super.setC_Charge_ID (C_Charge_ID);
		if (C_Charge_ID > 0)
			set_ValueNoCheck ("C_UOM_ID", null);
	}	//	setC_Charge_ID
	/**
	 *	Set Discount
	 */
	public void setDiscount()
	{
		BigDecimal list = getPriceList();
		//	No List Price
		if (Env.ZERO.compareTo(list) == 0)
			return;
		BigDecimal discount = list.subtract(getPriceActual())
			.multiply(new BigDecimal(100))
			.divide(list, getPrecision(), BigDecimal.ROUND_HALF_UP);
		setDiscount(discount);
	}	//	setDiscount

	/**
	 *	Is Tax Included in Amount
	 *	@return true if tax calculated
	 */
	public boolean isTaxIncluded()
	{
		if (m_M_PriceList_ID == 0)
		{
			m_M_PriceList_ID = DB.getSQLValue(get_TrxName(),
				"SELECT M_PriceList_ID FROM C_Order WHERE C_Order_ID=?",
				getC_Order_ID());
		}
		MPriceList pl = MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName());
		return pl.isTaxIncluded();
	}	//	isTaxIncluded

	
	/**
	 * 	Set Qty Entered/Ordered.
	 * 	Use this Method if the Line UOM is the Product UOM 
	 *	@param Qty QtyOrdered/Entered
	 */
	public void setQty (BigDecimal Qty)
	{
		super.setQtyEntered (Qty);
		super.setQtyOrdered (Qty);
	}	//	setQty

	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return true if it can be sabed
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Get Defaults from Parent
		if (getC_BPartner_ID() == 0 || getC_BPartner_Location_ID() == 0
			|| getM_Warehouse_ID() == 0 
			|| getC_Currency_ID() == 0)
			setOrder (getParent());
		if (m_M_PriceList_ID == 0)
			setHeaderInfo(getParent());

		
		//	R/O Check - Product/Warehouse Change
		if (!newRecord 
			&& (is_ValueChanged("M_Product_ID") || is_ValueChanged("M_Warehouse_ID"))) 
		{
			if (!canChangeWarehouse())
				return false;
		}	//	Product Changed
		
		//	Charge
		if (getC_Charge_ID() != 0 && getM_Product_ID() != 0)
				setM_Product_ID(0);
		//	No Product
		if (getM_Product_ID() == 0)
			setM_AttributeSetInstance_ID(0);
		//	Product
		else	//	Set/check Product Price
		{
			//TODO: Rev con gc si aplica de esta manera ya que se esta solicitando 
			// el precio desde la recepcion de material 
			//	Set Price if Actual = 0
			if (m_productPrice == null 
					&&  Env.ZERO.compareTo(getPriceActual()) == 0
					&&  Env.ZERO.compareTo(getPriceList()) == 0){
				setPrice();
			
				//	Check if on Price list
				if (m_productPrice == null)
					getProductPricing(m_M_PriceList_ID);
				if (!m_productPrice.isCalculated())
				{
					log.saveError("Error", Msg.getMsg(getCtx(), "ProductNotOnPriceList"));
					//return false;
				}
			}
		}

		//	UOM
		if (getC_UOM_ID() == 0 
			&& (getM_Product_ID() != 0 
				|| getPriceEntered().compareTo(Env.ZERO) != 0
				|| getC_Charge_ID() != 0))
		{
			int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
			if (C_UOM_ID > 0)
				setC_UOM_ID (C_UOM_ID);
		}
		
		//	Qty on instance ASI for SO
		if (m_IsSOTrx 
			&& getM_AttributeSetInstance_ID() != 0
			&& (newRecord || is_ValueChanged("M_Product_ID")
				|| is_ValueChanged("M_AttributeSetInstance_ID")
				|| is_ValueChanged("M_Warehouse_ID")))
		{
			MProduct product = getProduct();
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			boolean isInstance = M_AttributeSet_ID != 0;
			if (isInstance)
			{
				MAttributeSet mas = MAttributeSet.get(getCtx(), M_AttributeSet_ID);
				isInstance = mas.isInstanceAttribute();
			}
			//	Max
			if (isInstance)
			{
				MStorage[] storages = MStorage.getWarehouse(getCtx(), 
					getM_Warehouse_ID(), getM_Product_ID(), getM_AttributeSetInstance_ID(), 
					M_AttributeSet_ID, false, null, true, get_TrxName());
				BigDecimal qty = Env.ZERO;
				for (int i = 0; i < storages.length; i++)
				{
					if (storages[i].getM_AttributeSetInstance_ID() == getM_AttributeSetInstance_ID())
						qty = qty.add(storages[i].getQtyOnHand());
				}
				if (getQtyOrdered().compareTo(qty) > 0)
				{
					log.warning("Qty - Stock=" + qty + ", Ordered=" + getQtyOrdered());
					log.saveError("QtyInsufficient", "=" + qty); 
					return false;
				}
			}
		}
		
		//	FreightAmt Not used
		if (Env.ZERO.compareTo(getFreightAmt()) != 0)
			setFreightAmt(Env.ZERO);

		//	Set Tax
		if (getC_Tax_ID() == 0)
			setTax();

		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM C_OrderLine WHERE C_Order_ID=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getC_Order_ID());
			setLine (ii);
		}

		//	Calculations & Rounding
		setLineNetAmt();	//	extended Amount with or without tax
		if (getPriceActual().compareTo(BigDecimal.ZERO) > 0
			&& getDiscount().compareTo(BigDecimal.ZERO) == 0){
				setDiscount();
		}

		// Clase de producto para el post
		if (getEXME_ProductClassFact_ID()<=0 && getM_Product_ID()>0){
			setEXME_ProductClassFact_ID(MEXMEProductClassFact.get(getCtx(), getM_Product_ID(), get_TrxName()));
		}
		
		// Solo cuando es una transaccion de compra y no es un almacen de consigna
		/*if (!getParent().isConsigna() && !getParent().isSOTrx()) {
			MEXMEProductoOrg mexmeproductoorg = MEXMEProductoOrg.getProductoOrg(Env.getCtx(), getM_Product_ID(), getAD_Client_ID(), getAD_Org_ID(), null);
			if(mexmeproductoorg != null && mexmeproductoorg.isConsigna()){
				final Object param[]={getM_Product().getName()};
				log.saveError("Error", Utilerias.getAppMsg(getCtx(), "material.prod.consig", param));
				return false;
			}
			
		}*/
		
//		if (newRecord || is_ValueChanged(X_C_OrderLine.COLUMNNAME_M_Product_ID)
//				|| is_ValueChanged(X_C_OrderLine.COLUMNNAME_M_AttributeSetInstance_ID)
//				|| is_ValueChanged(X_C_OrderLine.COLUMNNAME_C_UOM_ID)){
//			if(!automatic) {
//				log.saveError(null, Utilerias.getAppMsg(getCtx(), "msj.error.movSimilarOrder"));
//				return false;
//			}
//		}
		
		return true;
	}	//	beforeSave
	
	public boolean isAutomatic() {
		return automatic;
	}

	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}

	/**
	 * Validar que no tenga registro similar
	 * 
	 * @return Registro o -1 si no tiene
	 */
	public static int getSimilarRecord(List<Object> params, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c_orderline_id ");
		sql.append("FROM ");
		sql.append("  c_orderline ");
		sql.append("WHERE ");
		sql.append("  c_order_id = ? AND ");
		sql.append("  c_orderline_id != ? AND ");
		//sql.append("  c_uomvolume_id = ? AND ");
		sql.append("  m_product_id = ? AND ");
		sql.append("  M_AttributeSetInstance_ID = ? AND ");
		sql.append("  isactive = 'Y' ");

		int id = DB.getSQLValue(trxName, sql.toString(), params);

		return id;
	}

	/**
	 * Validar que un encabezado MOrder no podra tener lineas similares
	 * 
	 * @return
	 */
	/*public boolean hasSimilarRecords() {
		return getSimilarRecord(get_TrxName()) > 0;
	}*/
	
	/**
	 * Before Delete
	 * 
	 * @return true if it can be deleted
	 */
	protected boolean beforeDelete() {
		// R/O Check - Something delivered. etc.
		if (Env.ZERO.compareTo(getQtyDelivered()) != 0) {
			log.saveError("DeleteError", Msg.translate(getCtx(), "QtyDelivered") + "=" + getQtyDelivered());
			return false;
		}
		if (Env.ZERO.compareTo(getQtyInvoiced()) != 0) {
			log.saveError("DeleteError", Msg.translate(getCtx(), "QtyInvoiced") + "=" + getQtyInvoiced());
			return false;
		}
		if (Env.ZERO.compareTo(getQtyReserved()) != 0) {
			// For PO should be On Order
			log.saveError("DeleteError", Msg.translate(getCtx(), "QtyReserved") + "=" + getQtyReserved());
			return false;
		}

		return true;
	} // beforeDelete
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		
		//expert : miguel rojas : actualizamos la informacion de compras
		//#08309 no precio producto si esta en borrador
		if(!m_IsSOTrx && getM_Product_ID() > 0 && !"DR".equals(getC_Order().getDocStatus())) {
		    //recuperamos la informacion de compras
		    MProductPO po = MProductPO.getOfProduct(getCtx(), getM_Product_ID(), getC_BPartner_ID());
		    if(po != null) {
		        //actualizamos la informacion, si es que cambio
		    	// RQM #5921 precio de lista que este configurado, si no se tiene poner cero. 
		    	MProductPrice prdo = MProductPrice.getProductPrice(getC_BPartner_ID(), 0, getM_Product_ID(), false);
		    	if(prdo == null) {
		    		po.setPriceList(BigDecimal.ZERO);
		    	} else {
		    		po.setPriceList(prdo.getPriceList());
		    	}
//		    	if(po.getPriceList() != getPriceList()) {
//		            po.setPriceList(getPriceList());
//		        }
		        if(po.getPricePO() != getPriceEntered()) {
		            po.setPricePO(getPriceActual());
		        }
		        // RQM #5921 cantidad que se registro en la orden de compra
		        po.setOrder_Min(getQtyEntered());
				if (getC_UOMVolume_ID() > 0 && getC_UOMVolume_ID() != getC_UOM_ID()) {
					po.setOrder_Pack(getQtyEntered_Vol());
				} else {
					po.setOrder_Pack(BigDecimal.ZERO);
				}
		        
		        po.save();
		    }//si no se encontro un MProductPO, hay que crear un nuevo registro, expert: eruiz
		    else{
		    	
		    	if(getC_BPartner_ID()>0 && Env.ZERO.compareTo(getPriceList())<0 && Env.ZERO.compareTo(getPriceActual())<0){
		    		po = new MProductPO(getCtx(), 0, null);
		    		MProduct prod = new MProduct(getCtx(), getM_Product_ID(), null);

		    		po.setM_Product_ID(getM_Product_ID());
		    		po.setC_BPartner_ID(getC_BPartner_ID());
//		    		po.setPriceList(getPriceList());
		    		// RQM #5921 precio de lista que este configurado, si no se tiene poner cero. 
			    	MProductPrice prdo = MProductPrice.getProductPrice(getC_BPartner_ID(), 0, getM_Product_ID(), false);
			    	if(prdo == null) {
			    		po.setPriceList(BigDecimal.ZERO);
			    	} else {
			    		po.setPriceList(prdo.getPriceList());
			    	}
		    		po.setPricePO(getPriceActual());
		    		po.setPriceLastPO(getPriceActual());
		    		po.setIsCurrentVendor(true);
		    		po.setVendorProductNo(prod.getValue());
		    		po.setC_UOM_ID(getC_UOM_ID());
		    		po.setC_Currency_ID(getC_Currency_ID());

		    		// RQM #5921 cantidad que se registro en la orden de compra
			        po.setOrder_Min(getQtyEntered());
					if (getC_UOMVolume_ID() > 0 && getC_UOMVolume_ID() != getC_UOM_ID()) {
						po.setOrder_Pack(getQtyEntered_Vol());
					} else {
						po.setOrder_Pack(BigDecimal.ZERO);
					}
		    		
		    		if(!po.save()){
		    			return false;
		    		}
		    	}
		    }//fin sino
		}
		//expert : miguel rojas : actualizamos la informacion de compras
		
		if (!newRecord && is_ValueChanged("C_Tax_ID"))
		{
			//	Recalculate Tax for old Tax
			MOrderTax tax = MOrderTax.get (this, getPrecision(), 
				true, get_TrxName());	//	old Tax
			if (tax != null)
			{
				if (!tax.calculateTaxFromLines())
					return false;
				if (!tax.save(get_TrxName()))
					return false;
			}
		}
		//expert, eruiz: actualizamos la informacion de costos
		if (getM_Product_ID() > 0){ // Ealvarez Ticket 04972  se valida Producto
		
		try{
			MProductCosting costing = MProductCosting.get(getCtx(),getM_Product_ID(),
				Integer.parseInt(getCtx().getProperty("$C_AcctSchema_ID")),get_TrxName());
			if(costing != null){
				costing.setPriceList(getPriceList());
				costing.setPriceActual(getPriceActual());
			}//Si no se encontro un MProductCosting, es necesario crear un nuevo registro: eruiz
			else{
				costing = new MProductCosting(getCtx(),0, get_TrxName());
				
				//Asignamos llaves foraneas
				costing.setC_AcctSchema_ID(Integer.parseInt(getCtx().getProperty("$C_AcctSchema_ID")));
				costing.setM_Product_ID(getM_Product_ID());
				
				//Asignamos los precios a los datos correspondientes a la actualizaci�n
				costing.setPriceList(getPriceList());
				costing.setPriceActual(getPriceActual());
				
				//Asignamos los costos que se llenan comunmente
				costing.setCurrentCostPrice(getPriceEntered());
				costing.setFutureCostPrice(getPriceEntered());
				costing.setCostStandard(getPriceEntered());
				costing.setCostAverage(getPriceEntered());
				
				//Como los campos son obligatorios, la otras cantidades se llenaran con 0
				costing.setTotalInvQty(new BigDecimal(0));
				costing.setTotalInvAmt(new BigDecimal(0));
				costing.setPriceLastPO(new BigDecimal(0));
				costing.setPriceLastInv(new BigDecimal(0));
				costing.setCostStandardCumAmt(new BigDecimal(0));
				costing.setCostStandardCumQty(new BigDecimal(0));
				costing.setCostStandardPOAmt(new BigDecimal(0));
				costing.setCostStandardPOQty(new BigDecimal(0));
				costing.setCostAverageCumAmt(new BigDecimal(0));
				costing.setCostAverageCumQty(new BigDecimal(0));
			}
		
			if(!costing.save())
				return false;
		}catch(Exception e){
			return false;
		}
	}
			
		return updateHeaderTax();
	}	//	afterSave

	/**
	 * 	After Delete
	 *	@param success success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		if (getS_ResourceAssignment_ID() != 0)
		{
			MResourceAssignment ra = new MResourceAssignment(getCtx(), getS_ResourceAssignment_ID(), get_TrxName());
			ra.delete(true);
		}
		
		return updateHeaderTax();
	}	//	afterDelete
	
	/**
	 *	Update Tax & Header
	 *	@return true if header updated
	 */
	private boolean updateHeaderTax()
	{
		//	Recalculate Tax for this Tax
		MOrderTax tax = MOrderTax.get (this, getPrecision(), 
			false, get_TrxName());	//	current Tax
		if (!tax.calculateTaxFromLines())
			return false;
		if (!tax.save(get_TrxName()))
			return false;
		
		//	Update Order Header
		String sql = "UPDATE C_Order i"
			+ " SET TotalLines="
				+ "(SELECT COALESCE(SUM(LineNetAmt),0) FROM C_OrderLine il WHERE i.C_Order_ID=il.C_Order_ID) "
			+ "WHERE C_Order_ID=" + getC_Order_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("(1) #" + no);

		if (isTaxIncluded())
			sql = "UPDATE C_Order i "
				+ " SET GrandTotal=TotalLines "
				+ "WHERE C_Order_ID=" + getC_Order_ID();
		else
			sql = "UPDATE C_Order i "
				+ " SET GrandTotal=TotalLines+"
					+ "(SELECT COALESCE(SUM(TaxAmt),0) FROM C_OrderTax it WHERE i.C_Order_ID=it.C_Order_ID) "
					+ "WHERE C_Order_ID=" + getC_Order_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("(2) #" + no);
		m_parent = null;
		return no == 1;
	}	//	updateHeaderTax
	
	/**
	 *	Get Line by Id
	 *	@return Vector 
	 */
	 public static Vector<DynamicModel> getLineByID(int orderline_id){
			
			Vector<DynamicModel> data_lines=null;
								
			String sql = "select * from c_orderline where c_orderline.isactive='Y' and c_orderline_id = ? ";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			try{
		
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, orderline_id);
				rs = pstmt.executeQuery();
				rsmd=rs.getMetaData();
				data_lines = new Vector<DynamicModel>();
		
			while (rs.next()){
				
				DynamicModel dynamicModel=new DynamicModel();
								for(int i=1;i<=rsmd.getColumnCount();i++)
									dynamicModel.setValue(rsmd.getColumnName(i), rs.getString(i),rsmd.getColumnClassName(i));
									data_lines.add(dynamicModel);
					}
				}catch(SQLException e){
						e.printStackTrace();
					}
				finally{
					try{
						if(pstmt!=null)
							pstmt.close();
						if(rs!=null)
							rs.close();
					}catch(SQLException e){
						//log.log(Level.SEVERE, "getLineByID", e);
						s_log.log (Level.SEVERE, sql, e);
					}
				}
			
			return data_lines;
		}

	public boolean isConverted() {
		boolean retValue = true;
		if (getQtyDelivered_Vol().compareTo(BigDecimal.ZERO) == 0 &&
			getQtyEntered_Vol().compareTo(BigDecimal.ZERO) == 0 &&
			getQtyEntered_Vol().compareTo(BigDecimal.ZERO) == 0	&&
			getQtyInvoiced_Vol().compareTo(BigDecimal.ZERO) == 0 &&
			getQtyOrdered_Vol().compareTo(BigDecimal.ZERO) == 0) {
			retValue = false;
		}
		return retValue;
	}
	
	
	public MTax getImpuestoCargo() {
		return MEXMETax.getImpuestoCargo(getCtx(), getC_Charge_ID(),
				get_TrxName());
	}
	
	private String ocPrdValue = "";

	public String getOcPrdValue() {
		if(getM_Product_ID() > 0){
			ocPrdValue = getM_Product().getValue();	
		}else if(getC_Charge_ID() > 0){
			ocPrdValue = getC_Charge().getValue();	
		}	
		return ocPrdValue;
	}

	public void setOcPrdValue(String ocPrdValue) {
		this.ocPrdValue = ocPrdValue;
	}
	
	private String ocProdName = StringUtils.EMPTY;
	
	
	public String getOcProdName() {
		if(getM_Product_ID() > 0){
			ocProdName = getM_Product().getName();	
		}else if(getC_Charge_ID() > 0){
			ocProdName = getC_Charge().getName();	
		}
		return ocProdName;
	}

	public void setOcProdName(String ocProdName) {
		this.ocProdName = ocProdName;
	}
	
	private String qty = "";
	
	public String getQty() {
		if(getC_UOM_ID() == getC_UOMVolume_ID()){
			qty = getQtyEntered().toString();
			
		}else{
			qty = getQtyEntered_Vol().toString();
		}
		
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	private String uom = "";
	
	MUOM unidad = null;
	
	public String getUom() {
		if(getC_UOM_ID() == getC_UOMVolume_ID()){
			unidad = new MUOM(Env.getCtx(), getC_UOM_ID(), null);
		}else{
			unidad = new MUOM(Env.getCtx(), getC_UOMVolume_ID(), null);
		}
		uom = unidad.toString();
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	private String price = "";

	public String getPrice() {
		price = getPriceList_Vol().toString();
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	private String discountPO = "";
	
	private BigDecimal discount = getPriceList_Vol().multiply(getDiscount().divide(new BigDecimal(100))); 
	
	public String getDiscountPO() {
		if(getM_Product_ID() > 0){
			discountPO = discount.toString();
		}else{
			discountPO = discount.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		}
		return discountPO;
	}

	public void setDiscountPO(String discountPO) {
		this.discountPO = discountPO;
	}
	
	private BigDecimal impuesto = (getPriceList_Vol().subtract(discount)).multiply((getC_Tax().getRate().divide(new BigDecimal(100))));
	
	private String tax = "";

	public String getTax() {
		if(getM_Product_ID()>0){
			tax = ((impuesto.multiply(getQtyOrdered_Vol()).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
		}else{
			tax = (impuesto.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		}
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}
	
	private String total = "";

	public String getTotal() {
		if(getM_Product_ID()>0)
			total = (((((getPriceList_Vol().subtract(discount)).add(impuesto)).multiply(getQtyOrdered_Vol())).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
		else{
			total = (((getPriceList_Vol().subtract(discount)).add(impuesto).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
		}
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	/** Obtener las lineas que no estan siendo utilizadas por una factura */
	public static List<MOrderLine> getLinesNotIninvoice(int cOrderId){
		final String where = " isBeingUsedInvoice = ? and c_order_id = ? ";
		return new Query(Env.getCtx(), Table_Name, where, null)
				.setOnlyActiveRecords(true)
				.setParameters("N", cOrderId)
				.addAccessLevelSQL(true)
				.list();
	}
	
	
}	//	MOrderLine
