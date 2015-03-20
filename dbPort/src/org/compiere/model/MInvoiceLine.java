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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.bpm.BeanView;
import org.compiere.model.bpm.GetCost;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DynamicModel;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Invoice Line Model
 *
 * @author Jorg Janke
 * @version $Id: MInvoiceLine.java,v 1.1 2005/12/26 21:10:43 taniap Exp $
 */
public class MInvoiceLine extends X_C_InvoiceLine {

	/** serialVersionUID */
	private static final long serialVersionUID = 6083270790984098661L;
	/** objeto de factura */
	private MInvoice mInvoice = null;
	/** Objeto del producto */
	private MProduct mProduct = null;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MInvoiceLine.class);
	/** */
	private int m_M_PriceList_ID = 0;
	/** */
	private Timestamp m_DateInvoiced = null;
	/** */
	private int m_C_BPartner_ID = 0;
	/** */
	private int m_C_BPartner_Location_ID = 0;
	/** */
	private boolean m_IsSOTrx = true;
	/** */
	private boolean m_priceSet = false;
	/** */
	private MProduct m_product = null;
	/** Cached Name of the line */
	private String m_name = null;
	/** Cached Precision */
	private Integer m_precision = null;
	/** Product Pricing */
	private MProductPricing m_productPricing = null;
	/** Parent */
	private MInvoice m_parent = null;
	/** */
	public static int REDONDEO2 = 2;
//	/** base gravable de factura aseguradora */
//	private List<MInvoiceTax> hMbasesGravAseg = null;
//	/** base gravable de factura aseguradora */
//	private BigDecimal baseGravAseg = null;
	/** extensiones */
	private int extensionFact = 0;
	/** objeto de impuesto */
	private MTax tax = null;
	/** total de impuesto */
	private BigDecimal totalTax = Env.ZERO;
	/** tipo de linea como en cargos a cuenta paciente */
	private String tipoLinea = null;
	/** tipo de linea como en cargos a cuenta paciente */
	private String subTipoLinea = null;

	/**
	 * Load Constructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set record
	 */
	public MInvoiceLine(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	} // MInvoiceLine

	/**************************************************************************
	 * Invoice Line Constructor
	 *
	 * @param ctx
	 *            context
	 * @param C_InvoiceLine_ID
	 *            invoice line or 0
	 * @param trxName
	 *            transaction name
	 */
	public MInvoiceLine(final Properties ctx, final int C_InvoiceLine_ID, final String trxName) {
		super(ctx, C_InvoiceLine_ID, trxName);
		if (C_InvoiceLine_ID == 0) {
			setIsDescription(false);
			setIsPrinted(true);
			setLineNetAmt(Env.ZERO);
			setPriceEntered(Env.ZERO);
			setPriceActual(Env.ZERO);
			setPriceLimit(Env.ZERO);
			setPriceList(Env.ZERO);
			setM_AttributeSetInstance_ID(0);
			setTaxAmt(Env.ZERO);
			//
			setQtyEntered(Env.ZERO);
			setQtyInvoiced(Env.ZERO);
		}
	} // MInvoiceLine

	/**
	 * Parent Constructor
	 *
	 * @param invoice
	 *            parent
	 */
	public MInvoiceLine(final MInvoice invoice) {
		this(invoice.getCtx(), 0, invoice.get_TrxName());
		if (invoice.get_ID() == 0){
			throw new IllegalArgumentException("Header not saved");
		}
		setClientOrg(invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
		setC_Invoice_ID(invoice.getC_Invoice_ID());
		setInvoice(invoice);
	} // MInvoiceLine

	/**
	 * Get Invoice Line referencing InOut Line
	 *
	 * @param sLine
	 *            shipment line
	 * @return (first) invoice line
	 */
	public static MInvoiceLine getOfInOutLine(final MInOutLine sLine) {
		if (sLine == null) {
			return null;
		}
		MInvoiceLine retValue = null;
		final String sql = "SELECT * FROM C_InvoiceLine WHERE M_InOutLine_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, sLine.get_TrxName());
			pstmt.setInt(1, sLine.getM_InOutLine_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MInvoiceLine(sLine.getCtx(), rs,
						sLine.get_TrxName());
				if (rs.next()) {
					s_log.warning("More than one C_InvoiceLine of " + sLine);
				}
			}
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	} // getOfInOutLine

	/**
	 * Traduccion de Callout utilizado en MInvoiceLine
	 *
	 * @param value
	 *            mProductID
	 * @return DynamicModel modelo dinamico con diferentes valores necesarios
	 *         para\n obtener C_Charge_ID, PriceList, PriceLimit, PriceActual,
	 *         PriceEntered\n C_UOM_ID, EnforcePriceLimit, DiscountSchema.
	 */
	public DynamicModel getSelectedInvoiceProduct(final Object value) {
		DynamicModel dynamicModel = null;

		try {
			final Integer mProductID = (Integer) value;
			if (mProductID == null || mProductID.intValue() == 0) {
				return null;
			}
			dynamicModel = new DynamicModel();
			dynamicModel.setValue("C_Charge_ID", null);
			/***** Price Calculation see also qty ****/

			final boolean isSOTrx = (getCtx().get("IsSOTrx") == null ? false
					: getCtx().get("IsSOTrx").equals("Y") ? true : false);
			final Integer cBPartnerID = m_parent.getC_BPartner_ID();
			final BigDecimal qty = getQtyInvoiced();
			final MProductPricing pp = new MProductPricing(mProductID, cBPartnerID,
					qty, isSOTrx);
			final Integer mPriceListID = m_parent.getM_PriceList_ID();
			pp.setM_PriceList_ID(mPriceListID);
			final MPriceList priceList = new MPriceList(getCtx(), mPriceListID, null);
			final MPriceListVersion mPriceList = new MPriceListVersion(priceList);
			final Integer mPriceListVersionID = mPriceList
					.getM_PriceList_Version_ID();
			pp.setM_PriceList_Version_ID(mPriceListVersionID);
			final Timestamp date = m_parent.getDateInvoiced();
			pp.setPriceDate(date);
			dynamicModel.setValue("PriceList",
					String.valueOf(pp.getPriceList()));
			dynamicModel.setValue("PriceLimit",
					String.valueOf(pp.getPriceLimit()));
			dynamicModel.setValue("PriceActual",
					String.valueOf(pp.getPriceStd()));
			dynamicModel.setValue("PriceEntered",
					String.valueOf(pp.getPriceStd()));
			dynamicModel.setValue("C_Currency_ID",
					String.valueOf(pp.getC_Currency_ID()));
			dynamicModel.setValue("C_UOM_ID", String.valueOf(pp.getC_UOM_ID()));
			getCtx().put("EnforcePriceLimit",
					pp.isEnforcePriceLimit() ? "Y" : "N");
			getCtx().put("DiscountSchema", pp.isDiscountSchema() ? "Y" : "N");

			// Tax
			// check parent location
			final Integer shipCBParentLocationID = m_parent
					.getC_BPartner_Location_ID();
			if (shipCBParentLocationID == 0) {
				// AMT
				;
			}
			final Integer billCBPartnerLocationID = shipCBParentLocationID;

			// Dates
			final Timestamp billDate = m_parent.getDateInvoiced();
			final Timestamp shipDate = billDate;
			final Integer adOrgID = getAD_Org_ID();
			final Integer mWareHouseID = getM_Warehouse_ID();
			final Integer cTaxID = Tax.get(
					getCtx(),
					mProductID,
					(dynamicModel.getValue("C_Charge_ID") == null ? 0 : Integer
							.parseInt(dynamicModel.getValue("C_Charge_ID"))),
					billDate, shipDate, adOrgID, mWareHouseID,
					billCBPartnerLocationID, shipCBParentLocationID, isSOTrx);

			dynamicModel.setValue("C_Tax_ID", String.valueOf(cTaxID));

			// AMT

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, "MInvoiceLine.getSelectedInvoiceProduct", e);
			// e.printStackTrace();
		}
		return dynamicModel;
	}

	/**
	 * Set Defaults from Order. Called also from copy lines from invoice Does
	 * not set Parent !!
	 *
	 * @param invoice
	 *            invoice
	 */
	public void setInvoice(final MInvoice invoice) {
		m_parent = invoice;
		m_M_PriceList_ID = invoice.getM_PriceList_ID();
		m_DateInvoiced = invoice.getDateInvoiced();
		m_C_BPartner_ID = invoice.getC_BPartner_ID();
		m_C_BPartner_Location_ID = invoice.getC_BPartner_Location_ID();
		m_IsSOTrx = invoice.isSOTrx();
		m_precision = new Integer(invoice.getPrecision());
	} // setOrder

	/**
	 * Get Parent
	 *
	 * @return parent
	 */
	public MInvoice getParent() {
		if (m_parent == null) {
			m_parent = new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
		}
		return m_parent;
	} // getParent

	/**
	 * Set values from Order Line. Does not set quantity!
	 *
	 * @param oLine
	 *            line
	 */
	public void setOrderLine(final MOrderLine oLine) {
		setC_OrderLine_ID(oLine.getC_OrderLine_ID());
		//
		setLine(oLine.getLine());
		setIsDescription(oLine.isDescription());
		setDescription(oLine.getDescription());
		//
		setC_Charge_ID(oLine.getC_Charge_ID());
		//
		setM_Product_ID(oLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());
		setS_ResourceAssignment_ID(oLine.getS_ResourceAssignment_ID());
		setC_UOM_ID(oLine.getC_UOM_ID());
		//
		setPriceEntered(oLine.getPriceEntered());
		setPriceActual(oLine.getPriceActual());
		setPriceLimit(oLine.getPriceLimit());
		setPriceList(oLine.getPriceList());
		//

		// cambios para unidad de medida
		setPriceEntered_Vol(oLine.getPriceEntered_Vol());
		setPriceActual_Vol(oLine.getPriceActual_Vol());
		setPriceList_Vol(oLine.getPriceList_Vol());
		//
		if(oLine.getC_Tax_ID()==0){
			int taxId = 0;
			if(oLine.getM_Product_ID()>0){
				final MTax mTax = oLine.getProduct().getTax();
				taxId = mTax.getC_Tax_ID();
			}
			if(oLine.getC_Charge_ID()>0){
				final MTax mTax = oLine.getImpuestoCargo();
				taxId = mTax.getC_Tax_ID();
			}
			if(taxId>0){
				setC_Tax_ID(taxId);
			}
		} else {
			setC_Tax_ID(oLine.getC_Tax_ID());
		}
		setLineNetAmt(oLine.getLineNetAmt());

		// FIXME Total de linea; Linea + Impuesto
		final BigDecimal txAmt = oLine.getC_Tax().getRate();
		if (txAmt == null) {
			setLineTotalAmt(oLine.getLineNetAmt());
		} else {
			setLineTotalAmt(oLine.getLineNetAmt().add(
					oLine.getLineNetAmt().multiply(
							txAmt.divide(new BigDecimal(100)))));
			oLine.getLineNetAmt().multiply(txAmt.divide(new BigDecimal(100)));
		}
		// setC_Tax_ID(oLine.getC_Tax_ID());
		// setLineNetAmt(oLine.getLineNetAmt());

	} // setOrderLine

	/**
	 * Set values from Shipment Line. Does not set quantity!
	 *
	 * @param sLine
	 *            ship line
	 */
	public void setShipLine(final MInOutLine sLine) {
		setM_InOutLine_ID(sLine.getM_InOutLine_ID());
		setC_OrderLine_ID(sLine.getC_OrderLine_ID());

		//
		setLine(sLine.getLine());
		setIsDescription(sLine.isDescription());
		setDescription(sLine.getDescription());
		//
		setM_Product_ID(sLine.getM_Product_ID());
		setC_UOM_ID(sLine.getC_UOM_ID());

		// rsolorzano
		// cambios para unidad de medida
		setC_UOMVolume_ID(sLine.getC_UOMVolume_ID());

		setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
		// setS_ResourceAssignment_ID(sLine.getS_ResourceAssignment_ID());
		setC_Charge_ID(sLine.getC_Charge_ID());
		//
		final int C_OrderLine_ID = sLine.getC_OrderLine_ID();
		if (C_OrderLine_ID != 0) {
			final MOrderLine oLine = new MOrderLine(getCtx(), C_OrderLine_ID,
					get_TrxName());
			setS_ResourceAssignment_ID(oLine.getS_ResourceAssignment_ID());
			//
			setPriceEntered(oLine.getPriceEntered());
			setPriceActual(oLine.getPriceActual());
			setPriceLimit(oLine.getPriceLimit());
			setPriceList(oLine.getPriceList());

			// rsolorzano
			// cambios para unidad de medida
			setPriceEntered_Vol(oLine.getPriceEntered_Vol());
			setPriceActual_Vol(oLine.getPriceActual_Vol());
			setPriceList_Vol(oLine.getPriceList_Vol());
			//
			setC_Tax_ID(oLine.getC_Tax_ID());
//			setLineNetAmt(oLine.getLineNetAmt());
			setLineNetAmt();
			// FIXME Total de linea; Linea + Impuesto
			final BigDecimal txAmt = oLine.getC_Tax().getRate();
			if (txAmt == null) {
//				setLineTotalAmt(oLine.getLineNetAmt());
				setLineTotalAmt();
			} else {
//				setLineTotalAmt(oLine.getLineNetAmt().add(
//						oLine.getLineNetAmt().multiply(
//								txAmt.divide(new BigDecimal(100)))));
				setLineTotalAmt();
				oLine.getLineNetAmt().multiply(
						txAmt.divide(new BigDecimal(100)));
			}

		} else {
			setPrice();
			setTax();
		}
	} // setOrderLine

	/**
	 * Add to Description
	 *
	 * @param description
	 *            text
	 */
	public void addDescription(final String description) {
		final String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	} // addDescription

	/**
	 * Set M_AttributeSetInstance_ID
	 *
	 * @param M_AttributeSetInstance_ID
	 *            id
	 */
	@Override
	public void setM_AttributeSetInstance_ID(final int M_AttributeSetInstance_ID) {
		if (M_AttributeSetInstance_ID == 0) {
			set_Value("M_AttributeSetInstance_ID", new Integer(0));
		} else {
			super.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		}
	} // setM_AttributeSetInstance_ID

	/**************************************************************************
	 * Set Price for Product and PriceList. Uses standard SO price list of not
	 * set by invoice constructor
	 */
	public void setPrice() {
		if (getM_Product_ID() == 0 || isDescription()) {
			return;
		}
		if (m_M_PriceList_ID == 0 || m_C_BPartner_ID == 0) {
			setInvoice(getParent());
		}
		if (m_M_PriceList_ID == 0 || m_C_BPartner_ID == 0) {
			// throw new IllegalStateException("setPrice - PriceList unknown!");
			// rsolorzano
			// cambios para unidad de medida
			throw new IllegalStateException("Invoice No: "
					+ getParent().getDocumentNo()
					+ ": setPrice - PriceList unknown!");
		}
		setPrice(m_M_PriceList_ID, m_C_BPartner_ID);
	} // setPrice

	/**
	 * Set Price for Product and PriceList
	 *
	 * @param M_PriceList_ID
	 *            price list
	 * @param C_BPartner_ID
	 *            business partner
	 */
	public void setPrice(final int M_PriceList_ID, final int C_BPartner_ID) {
		if (getM_Product_ID() == 0 || isDescription()) {
			return;
		}
		//
		log.fine("M_PriceList_ID=" + M_PriceList_ID);
		m_productPricing = new MProductPricing(getM_Product_ID(),
				C_BPartner_ID, getQtyInvoiced(), m_IsSOTrx);
		m_productPricing.setM_PriceList_ID(M_PriceList_ID);
		m_productPricing.setPriceDate(m_DateInvoiced);
		//
		setPriceActual(m_productPricing.getPriceStd());
		setPriceList(m_productPricing.getPriceList());
		setPriceLimit(m_productPricing.getPriceLimit());

		// setear los precios de las unidades del volumen en la linea de factura
		final MProductPrice mPrice = MProductPrice.getProductPrice(C_BPartner_ID,
				m_productPricing.getM_PriceList_ID(), getM_Product_ID(),
				getmInvoice().isSOTrx());
		if(mPrice!=null){
			setPriceActual_Vol(mPrice.getPriceStd_Vol());
			setPriceList_Vol(mPrice.getPriceList_Vol());
			setPriceEntered_Vol(mPrice.getPriceStd_Vol());
		}
		//
		if (getQtyEntered().compareTo(getQtyInvoiced()) == 0){
			setPriceEntered(getPriceActual());
		}else{
			setPriceEntered(getPriceActual().multiply(
					getQtyInvoiced().divide(getQtyEntered(),
							BigDecimal.ROUND_HALF_UP))); // no precision
		}
		//
		if (getC_UOM_ID() == 0){
			setC_UOM_ID(m_productPricing.getC_UOM_ID());
		}
		//
		m_priceSet = true;
	} // setPrice

	/**
	 * Set Price Entered/Actual. Use this Method if the Line UOM is the Product
	 * UOM
	 *
	 * @param PriceActual
	 *            price
	 */
	public void setPrice(final BigDecimal PriceActual) {
		setPriceEntered(PriceActual);
		setPriceActual(PriceActual);
	} // setPrice

	/**
	 * Set Price Actual. (actual price is not updateable)
	 *
	 * @param PriceActual
	 *            actual price
	 */
	@Override
	public void setPriceActual(final BigDecimal PriceActual) {
		if (PriceActual == null) {
			throw new IllegalArgumentException("PriceActual is mandatory");
		}
		// set_ValueNoCheck("PriceActual", PriceActual); //Expert: twry para
		// redondeo
		set_ValueNoCheck("PriceActual",
				PriceActual.setScale(REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP)); // Expert
																			// twry
																			// para
																			// redondeo
	} // setPriceActual

	/**
	 * Impuesto por producto
	 */
	public void setTax(final MTax tax) {
		this.tax = tax;
	}

//	/**
//	 * Obj tax
//	 * 
//	 * @return
//	 */
//	public MTax getTax() {
//		if (tax == null) {
//			if (getC_Tax_ID() > 0) {
//				tax = new MTax(getCtx(), getC_Tax_ID(), get_TrxName());
//			} else {
//				if (getM_Product_ID() > 0) {
//					try {
//						tax = MEXMETax.getImpuestoProducto(getCtx(),
//								getM_Product_ID(), get_TrxName());
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//
//				if (tax == null) {
//					try {
//						tax = new MTax(getCtx(), MEXMETax.getExemptTaxID(
//								getCtx(), get_TrxName()), get_TrxName());
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		return tax;
//	}

	/**
	 * Set Tax - requires Warehouse
	 *
	 * @return true if found
	 */
	public boolean setTax() {
		if (isDescription()) {
			return true;
		}
		//
		final int M_Warehouse_ID = Env.getContextAsInt(getCtx(), "#M_Warehouse_ID");
		//
		final int C_Tax_ID = Tax.get(getCtx(), getM_Product_ID(), getC_Charge_ID(),
				m_DateInvoiced, m_DateInvoiced, getAD_Org_ID(), M_Warehouse_ID,
				m_C_BPartner_Location_ID, // should be bill to
				m_C_BPartner_Location_ID, m_IsSOTrx);
		if (C_Tax_ID == 0) {
			log.log(Level.SEVERE, "No Tax found");
			return false;
		}
		setC_Tax_ID(C_Tax_ID);
		// if (m_IsSOTrx)
		// {
		// }
		return true;
	} // setTax

	/**
	 * Calculare Tax Amt. Assumes Line Net is calculated
	 */
	public void setTaxAmt() {
		BigDecimal TaxAmt = Env.ZERO;
		if (getC_Tax_ID() != 0) {
			// setLineNetAmt();
			final MTax tax = new MTax(getCtx(), getC_Tax_ID(), get_TrxName());
			TaxAmt = tax.calculateTax(getLineNetAmt(), isTaxIncluded(),
					getPrecision());
		}
		super.setTaxAmt(TaxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
	} // setTaxAmt

	/**
	 * Calculate Extended Amt. May or may not include tax
	 */
	public void setLineNetAmt() {
		// Calculations & Rounding
		BigDecimal net = Env.ZERO;
		
		// Si ya existe la referencia a su encabezado (FACTURA/NOTA)
		if(getC_Invoice_ID()>0){
			
			// Si la transacción es de venta utiliza las unidades minimas
			if(getmInvoice().isSOTrx()) {
				net = getPriceActual().multiply(getQtyInvoiced());
				
			} else {
				
				// Si la transacción es de compra utiliza las unidades minimas y de volument
				if (getC_UOM_ID() == getC_UOMVolume_ID()) {
					net = getPriceActual().multiply(getQtyInvoiced());
				}else{
					net = getPriceActual_Vol().multiply(getQtyInvoiced_Vol());
				}
			}
			
			
		} else {
			net = getPriceActual().multiply(getQtyInvoiced());
		}
		
		if (net.scale() > getPrecision()) {
			net = net.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
		}
		super.setLineNetAmt(net);
	} // setLineNetAmt

	/**
	 * Set Qty Invoiced/Entered.
	 *
	 * @param Qty
	 *            Invoiced/Ordered
	 */
	public void setQty(final int Qty) {
		setQty(new BigDecimal(Qty));
	} // setQtyInvoiced

	/**
	 * Set Qty Invoiced
	 *
	 * @param Qty
	 *            Invoiced/Entered
	 */
	public void setQty(final BigDecimal Qty) {
		setQtyEntered(Qty);
		setQtyInvoiced(Qty);
	} // setQtyInvoiced

	/**
	 * Set Product
	 *
	 * @param product
	 *            product
	 */
	public void setProduct(final MProduct product) {
		m_product = product;
		if (m_product != null) {
			setM_Product_ID(m_product.getM_Product_ID());
			setC_UOM_ID(m_product.getC_UOM_ID());
		} else {
			setM_Product_ID(0);
			setC_UOM_ID(0);
		}
		setM_AttributeSetInstance_ID(0);
	} // setProduct

	/**
	 * Set M_Product_ID
	 *
	 * @param M_Product_ID
	 *            product
	 */
	public void setM_Product_ID(final int M_Product_ID, final boolean setUOM) {
		if (setUOM) {
			setProduct(MProduct.get(getCtx(), M_Product_ID));
		} else {
			super.setM_Product_ID(M_Product_ID);
		}
		setM_AttributeSetInstance_ID(0);
	} // setM_Product_ID

	/**
	 * Set Product and UOM
	 *
	 * @param M_Product_ID
	 *            product
	 * @param C_UOM_ID
	 *            uom
	 */
	public void setM_Product_ID(final int M_Product_ID, final int C_UOM_ID) {
		super.setM_Product_ID(M_Product_ID);
		super.setC_UOM_ID(C_UOM_ID);
		setM_AttributeSetInstance_ID(0);
	} // setM_Product_ID

	/**
	 * String Representation
	 *
	 * @return info
	 */
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("MInvoiceLine[").append(get_ID())
				.append(",").append(getLine()).append(",QtyInvoiced=")
				.append(getQtyInvoiced()).append(",LineNetAmt=")
				.append(getLineNetAmt()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Get (Product/Charge) Name
	 *
	 * @return name
	 */
	public String getName() {
		if (m_name == null) {
			final String sql = "SELECT COALESCE (p.Name, c.Name) "
					+ "FROM C_InvoiceLine il"
					+ " LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)"
					+ " LEFT OUTER JOIN C_Charge C ON (il.C_Charge_ID=c.C_Charge_ID) "
					+ "WHERE C_InvoiceLine_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getC_InvoiceLine_ID());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					m_name = rs.getString(1);
				}
				// rs.close();
				// pstmt.close();
				// pstmt = null;
				// rs=null;
				if (m_name == null) {
					m_name = "??";
				}
			} catch (final Exception e) {
				log.log(Level.SEVERE, "getName", e);
			} finally {
				DB.close(rs, pstmt);
			}
		}
		return m_name;
	} // getName

	/**
	 * Set Temporary (cached) Name
	 *
	 * @param tempName
	 *            Cached Name
	 */
	public void setName(final String tempName) {
		m_name = tempName;
	} // setName

	/**
	 * Get Description Text. For jsp access (vs. isDescription)
	 *
	 * @return description
	 */
	public String getDescriptionText() {
		return super.getDescription();
	} // getDescriptionText

	/**
	 * Get Currency Precision
	 *
	 * @return precision
	 */
	public int getPrecision() {
		if (m_precision != null) {
			return m_precision.intValue();
		}

		final String sql = "SELECT c.StdPrecision "
				+ "FROM C_Currency c INNER JOIN C_Invoice x ON (x.C_Currency_ID=c.C_Currency_ID) "
				+ "WHERE x.C_Invoice_ID=?";
		int i = DB.getSQLValue(get_TrxName(), sql, getC_Invoice_ID());
		if (i < 0) {
			log.warning("getPrecision = " + i + " - set to 2");
			i = 2;
		}
		m_precision = new Integer(i);
		return m_precision.intValue();
	} // getPrecision

	/**
	 * Is Tax Included in Amount
	 */
	public boolean isTaxIncluded() {
		if (m_M_PriceList_ID == 0) {
			m_M_PriceList_ID = DB
					.getSQLValue(
							get_TrxName(),
							"SELECT M_PriceList_ID FROM C_Invoice WHERE C_Invoice_ID=?",
							getC_Invoice_ID());
		}
		final MPriceList pl = MPriceList.get(getCtx(), m_M_PriceList_ID,
				get_TrxName());
		return pl.isTaxIncluded();
	} // isTaxIncluded

	/**
	 * Method to obtain the invoice lines of the current order
	 *
	 * @param int c_invoice_id
	 * @return Vector<DynamicModel>
	 */
	public static Vector<DynamicModel> getLinesByInvoice(final int c_invoice_id) {

		Vector<DynamicModel> data = null;

		final String sql = "select c_invoiceline_id, " +
				"c_invoice_id, " +
				"m_product_id, " +
				"qtyinvoiced, " +
				"pricelist, " +
				"priceactual, " +
				"pricelimit, " +
				"linenetamt, " +
				"c_uom_id, " +
				"c_tax_id, " +
				"taxamt, " +
				"linetotalamt, " +
				"qtyentered, " +
				"priceentered, " +
				"m_warehouse_id, " +
				"ad_orgtrx_id " +
				"from c_invoiceline " +
				"where isactive='Y' and c_invoice_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {

			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, c_invoice_id);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			data = new Vector<DynamicModel>();

			while (rs.next()) {

				final DynamicModel dynamicModel = new DynamicModel();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i),
							rs.getString(i), rsmd.getColumnClassName(i));
				}
				data.add(dynamicModel);
			}
		} catch (final SQLException e) {
			// e.printStackTrace();
			s_log.log(Level.SEVERE, "getLinesByInvoice : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return data;
	}

//	/**
//	 * getByCtaPacDet
//	 *
//	 * @param ctx
//	 * @param ctapacDet
//	 * @param pacienteId
//	 * @param cbpartnerId
//	 * @return
//	 */
//	public static MInvoiceLine getByCtaPacDet(Properties ctx, int ctapacDet,
//			int pacienteId, int cbpartnerId) {
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append("SELECT L.C_INVOICELINE_ID ")
//				.append("FROM   C_INVOICE C INNER JOIN C_INVOICELINE L ON L.C_INVOICE_ID = C.C_INVOICE_ID ")
//				.append("WHERE  C.EXME_PACIENTE_ID = ? ")
//				.append("AND    C.C_BPARTNER_ID = ? ")
//				.append("AND    L.EXME_CTAPACDET_ID = ? ");
//
//		MInvoiceLine line = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, pacienteId);
//			pstmt.setInt(2, cbpartnerId);
//			pstmt.setInt(3, ctapacDet);
//
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				line = new MInvoiceLine(ctx, rs.getInt("C_InvoiceLine_ID"),
//						null);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getByCtaPacDet : " + sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return line;
//	}

	/**************************************************************************
	 * Before Save
	 *
	 * @param newRecord
	 * @return true if save
	 */
	@Override
	protected boolean beforeSave(final boolean newRecord) {
		
		log.fine("New=" + newRecord);
		if(getC_Invoice_ID()>0){
			setInvoice(new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName()));
		}

		// Charge
		if (getC_Charge_ID() != 0) {
			if (getM_Product_ID() != 0) {
				setM_Product_ID(0);
			}
		} else // Set Product Price
		{
			if (!m_priceSet
					&& Env.ZERO.compareTo(getPriceActual()) == 0
					&& Env.ZERO.compareTo(getPriceList()) == 0
					&& !m_IsSOTrx ) {
				setPrice();
			}
		}

		// Set Tax
		if (getC_Tax_ID() == 0){
			setTax();
		}

		// Get Line No
		if (getLine() == 0) {
			final String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM C_InvoiceLine WHERE C_Invoice_ID=?";
			final int ii = DB.getSQLValue(get_TrxName(), sql, getC_Invoice_ID());
			setLine(ii);
		}
		// UOM
		if (getC_UOM_ID() == 0) {
			final int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
			if (C_UOM_ID > 0){
				setC_UOM_ID(C_UOM_ID);
			}
		}
		
		// Calculations & Rounding
		// if(getPriceActual().compareTo(Env.ZERO)>=0) //Twry Que no Recalcule
		// para el posteo
		if (getLineNetAmt().compareTo(Env.ZERO) == 0 && getC_Charge_ID() <= 0){ // Twry
																				// Que
																				// no
																				// Recalcule
																				// para
																				// el
																				// posteo
			setLineNetAmt();
		}

		if (!m_IsSOTrx // AP Inv Tax Amt
				&& getTaxAmt().compareTo(Env.ZERO) == 0){//FIXME no se calcula el impuesto al modificar cantidades
			setTaxAmt();
			setLineTotalAmt();
			
		}

		// Clase de producto para el post
		if (getEXME_ProductClassFact_ID()<=0 && getM_Product_ID()>0){
			setEXME_ProductClassFact_ID(MEXMEProductClassFact.get(getCtx(), getM_Product_ID(), get_TrxName()));
		}
		//
		return true;
	} // beforeSave

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return saved
	 */
	@Override
	protected boolean afterSave(final boolean newRecord, boolean success) {
		if (!success){
			return success;
		}

		// Factura proveedor
		if(!m_IsSOTrx){

			// Si no es nuevo y cambia el impuesto
			if (!newRecord && is_ValueChanged("C_Tax_ID")) {
				// Recalculate Tax for old Tax
				final MInvoiceTax tax = MInvoiceTax.get(this, getPrecision(), true,
						get_TrxName()); // old Tax
				if (tax != null) {
					if (!tax.calculateTaxFromLines()) {
						return false;
					}
					if (!tax.save(get_TrxName())) {
						return true;
					}
				}

				success = updateHeaderTax();

			} else if (newRecord
					// Si es un nuevo registro o cambia alguna de los siguientes campos
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_LineNetAmt)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_PriceActual)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_PriceActual_Vol)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_PriceEntered)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_PriceEntered_Vol)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_PriceList)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_PriceList_Vol)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_QtyInvoiced)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_QtyInvoiced_Vol)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_QtyEntered)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_QtyEntered_Vol)
					|| is_ValueChanged(I_C_InvoiceLine.COLUMNNAME_TaxAmt)
					) {
				success = updateHeaderTax();
			}
		}
		return success;
	} // afterSave

	/**
	 * After Delete
	 *
	 * @param success
	 *            success
	 * @return deleted
	 */
	@Override
	protected boolean afterDelete(boolean success) {
		if (!success){
			return success;
		}

		if(!m_IsSOTrx){
			success = updateHeaderTax();
		}
		return success;
	} // afterDelete

	/**
	 * Update Tax & Header
	 */
	private boolean updateHeaderTax() {
		// Recalculate Tax for this Tax
		final MInvoiceTax tax = MInvoiceTax.get(this, getPrecision(), false,
				get_TrxName()); // current Tax
		if (tax != null) {
			if (!tax.calculateTaxFromLines()){
				return false;
			}
			if (!tax.save(get_TrxName())){
				return false;
			}
		}

		int no = 0;
		// Update Invoice Header//
		String sql = "UPDATE C_Invoice i"
				+ " SET TotalLines="
				+ "(SELECT COALESCE(SUM(LineNetAmt),0) FROM C_InvoiceLine il WHERE i.C_Invoice_ID=il.C_Invoice_ID) "
				+ "WHERE C_Invoice_ID=" + getC_Invoice_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1){
			log.warning("(1) #" + no);
		}

		if (isTaxIncluded()){
			sql = "UPDATE C_Invoice i " + " SET GrandTotal=TotalLines "
					+ "WHERE C_Invoice_ID=" + getC_Invoice_ID();
		}else{

			sql = "UPDATE C_Invoice i "
					+ " SET GrandTotal=TotalLines+"
					+ "(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID) "
					+ "WHERE C_Invoice_ID=" + getC_Invoice_ID();
		}
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1){
			log.warning("(2) #" + no);
		}

		m_parent = null;
		return no == 1;
	} // updateHeaderTax

	/**************************************************************************
	 * Allocate Landed Costs
	 *
	 * @return error message or ""
	 */
	public String allocateLandedCosts() {
		if (isProcessed()) {
			return "Processed";
		}
		final MLandedCost[] lcs = MLandedCost.getLandedCosts(this);
		if (lcs.length == 0) {
			return "";
		}
		final String sql = "DELETE C_LandedCostAllocation WHERE C_InvoiceLine_ID="
				+ getC_InvoiceLine_ID();
		final int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0) {
			log.info("Deleted #" + no);
		}

		int inserted = 0;
		// *** Single Criteria ***
		if (lcs.length == 1) {
			final MLandedCost lc = lcs[0];
			if (lc.getM_InOut_ID() != 0) {
				// Create List
				final ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
				final MInOut ship = new MInOut(getCtx(), lc.getM_InOut_ID(),
						get_TrxName());
				final MInOutLine[] lines = ship.getLines();
				for (final MInOutLine line : lines) {
					if (line.isDescription()
							|| line.getM_Product_ID() == 0) {
						continue;
					}
					if (lc.getM_Product_ID() == 0
							|| lc.getM_Product_ID() == line
									.getM_Product_ID()) {
						list.add(line);
					}
				}
				if (list.size() == 0) {
					return "No Matching Lines (with Product) in Shipment";
				}
				// Calculate total & base
				BigDecimal total = Env.ZERO;
				for (int i = 0; i < list.size(); i++) {
					final MInOutLine iol = list.get(i);
					total = total.add(iol.getBase(lc
							.getLandedCostDistribution()));
				}
				if (total.signum() == 0) {
					return "Total of Base values is 0 - "
							+ lc.getLandedCostDistribution();
				}
				// Create Allocations
				for (int i = 0; i < list.size(); i++) {
					final MInOutLine iol = list.get(i);
					final MLandedCostAllocation lca = new MLandedCostAllocation(this,
							lc.getM_CostElement_ID());
					lca.setM_Product_ID(iol.getM_Product_ID());
					lca.setM_AttributeSetInstance_ID(iol
							.getM_AttributeSetInstance_ID());
					final BigDecimal base = iol.getBase(lc
							.getLandedCostDistribution());
					lca.setBase(base);
					if (base.signum() != 0) {
						double result = getLineNetAmt().multiply(base)
								.doubleValue();
						result /= total.doubleValue();
						lca.setAmt(result, getPrecision());
					}
					if (!lca.save()) {
						return "Cannot save line Allocation = " + lca;
					}
					inserted++;
				}
				log.info("Inserted " + inserted);
				allocateLandedCostRounding();
				return "";
			}
			// Single Line
			else if (lc.getM_InOutLine_ID() != 0) {
				final MInOutLine iol = new MInOutLine(getCtx(),
						lc.getM_InOutLine_ID(), get_TrxName());
				if (iol.isDescription() || iol.getM_Product_ID() == 0) {
					return "Invalid Receipt Line - " + iol;
				}
				final MLandedCostAllocation lca = new MLandedCostAllocation(this,
						lc.getM_CostElement_ID());
				lca.setM_Product_ID(iol.getM_Product_ID());
				lca.setM_AttributeSetInstance_ID(iol
						.getM_AttributeSetInstance_ID());
				lca.setAmt(getLineNetAmt());
				if (lca.save()) {
					return "";
				}
				return "Cannot save single line Allocation = " + lc;
			}
			// Single Product
			else if (lc.getM_Product_ID() != 0) {
				final MLandedCostAllocation lca = new MLandedCostAllocation(this,
						lc.getM_CostElement_ID());
				lca.setM_Product_ID(lc.getM_Product_ID()); // No ASI
				lca.setAmt(getLineNetAmt());
				if (lca.save()) {
					return "";
				}
				return "Cannot save Product Allocation = " + lc;
			} else {
				return "No Reference for " + lc;
			}
		}

		// *** Multiple Criteria ***
		final String LandedCostDistribution = lcs[0].getLandedCostDistribution();
		final int M_CostElement_ID = lcs[0].getM_CostElement_ID();
		for (final MLandedCost lc2 : lcs) {
			final MLandedCost lc = lc2;
			if (!LandedCostDistribution.equals(lc.getLandedCostDistribution())) {
				return "Multiple Landed Cost Rules must have consistent Landed Cost Distribution";
			}
			if (lc.getM_Product_ID() != 0 && lc.getM_InOut_ID() == 0
					&& lc.getM_InOutLine_ID() == 0) {
				return "Multiple Landed Cost Rules cannot directly allocate to a Product";
			}
			if (M_CostElement_ID != lc.getM_CostElement_ID()) {
				return "Multiple Landed Cost Rules cannot different Cost Elements";
			}
		}
		// Create List
		final ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		for (final MLandedCost lc : lcs) {
			if (lc.getM_InOut_ID() != 0 && lc.getM_InOutLine_ID() == 0) // entire
																		// receipt
			{
				final MInOut ship = new MInOut(getCtx(), lc.getM_InOut_ID(),
						get_TrxName());
				final MInOutLine[] lines = ship.getLines();
				for (final MInOutLine line : lines) {
					if (line.isDescription() // decription or no product
							|| line.getM_Product_ID() == 0) {
						continue;
					}
					if (lc.getM_Product_ID() == 0 // no restriction or product
													// match
							|| lc.getM_Product_ID() == line
									.getM_Product_ID()) {
						list.add(line);
					}
				}
			} else if (lc.getM_InOutLine_ID() != 0) // receipt line
			{
				final MInOutLine iol = new MInOutLine(getCtx(),
						lc.getM_InOutLine_ID(), get_TrxName());
				if (!iol.isDescription() && iol.getM_Product_ID() != 0) {
					list.add(iol);
				}
			}
		}
		if (list.size() == 0) {
			return "No Matching Lines (with Product)";
		}
		// Calculate total & base
		BigDecimal total = Env.ZERO;
		for (int i = 0; i < list.size(); i++) {
			final MInOutLine iol = list.get(i);
			total = total.add(iol.getBase(LandedCostDistribution));
		}
		if (total.signum() == 0) {
			return "Total of Base values is 0 - " + LandedCostDistribution;
		}
		// Create Allocations
		for (int i = 0; i < list.size(); i++) {
			final MInOutLine iol = list.get(i);
			final MLandedCostAllocation lca = new MLandedCostAllocation(this,
					lcs[0].getM_CostElement_ID());
			lca.setM_Product_ID(iol.getM_Product_ID());
			lca.setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
			final BigDecimal base = iol.getBase(LandedCostDistribution);
			lca.setBase(base);
			if (base.signum() != 0) {
				double result = getLineNetAmt().multiply(base).doubleValue();
				result /= total.doubleValue();
				lca.setAmt(result, getPrecision());
			}
			if (!lca.save()) {
				return "Cannot save line Allocation = " + lca;
			}
			inserted++;
		}

		log.info("Inserted " + inserted);
		allocateLandedCostRounding();
		return "";
	} // allocate Costs

	/**
	 * Allocate Landed Cost - Enforce Rounding
	 */
	private void allocateLandedCostRounding() {
		final MLandedCostAllocation[] allocations = MLandedCostAllocation
				.getOfInvoiceLine(getCtx(), getC_InvoiceLine_ID(),
						get_TrxName());
		MLandedCostAllocation largestAmtAllocation = null;
		BigDecimal allocationAmt = Env.ZERO;
		for (final MLandedCostAllocation allocation : allocations) {
			if (largestAmtAllocation == null
					|| allocation.getAmt().compareTo(
							largestAmtAllocation.getAmt()) > 0) {
				largestAmtAllocation = allocation;
			}
			allocationAmt = allocationAmt.add(allocation.getAmt());
		}
		final BigDecimal difference = getLineNetAmt().subtract(allocationAmt);
		if (difference.signum() != 0) {
			largestAmtAllocation.setAmt(largestAmtAllocation.getAmt().add(
					difference));
			largestAmtAllocation.save();
			log.config("Difference=" + difference
					+ ", C_LandedCostAllocation_ID="
					+ largestAmtAllocation.getC_LandedCostAllocation_ID()
					+ ", Amt" + largestAmtAllocation.getAmt());
		}
	} // allocateLandedCostRounding

	public MCtaPacDet getCtaPacDet() {
		return new MCtaPacDet(getCtx(), getEXME_CtaPacDet_ID(), get_TrxName());
	}

//	public BigDecimal getPriceActual() {
//		return super.getPriceActual().setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP);
//	}
//
//	public void setPriceEntered(BigDecimal PriceEntered) {
//		super.setPriceEntered(PriceEntered.setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP));
//	}
//
//	public BigDecimal getPriceEntered() {
//		return super.getPriceEntered().setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP);
//	}
//
//	public void setPriceLimit(BigDecimal PriceLimit) {
//		super.setPriceLimit(PriceLimit.setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP));
//	}
//
//	public BigDecimal getPriceLimit() {
//		return super.getPriceLimit().setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP);
//	}
//
//	public void setPriceList(BigDecimal PriceList) {
//		super.setPriceList(PriceList.setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP));
//	}
//
//	public BigDecimal getPriceList() {
//		return super.getPriceList().setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP);
//	}

//	public void setLineNetAmt(final BigDecimal LineNetAmt) {
//		super.setLineNetAmt(LineNetAmt.setScale(REDONDEO2,
//				BigDecimal.ROUND_HALF_UP));
//	}

	@Override
	public BigDecimal getLineNetAmt() {
		if (super.getLineNetAmt().scale() > getPrecision()){
			return super.getLineNetAmt().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP);
		}
		return super.getLineNetAmt();
	}

//	public void setLineTotalAmt(BigDecimal LineTotalAmt) {
//		if (LineTotalAmt.scale() > getPrecision()){
//			super.setLineTotalAmt(LineTotalAmt.setScale(getPrecision(),BigDecimal.ROUND_HALF_UP));
//		} else {
//			super.setLineTotalAmt(LineTotalAmt);
//		}
//	}

	@Override
	public BigDecimal getLineTotalAmt() {
		if (super.getLineTotalAmt().scale() > getPrecision()){
			return super.getLineTotalAmt().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP);
		} else {
			return super.getLineTotalAmt();
		}
	}

	/**
	 * Set Tax Amount.
	 *
	 * @param TaxAmt
	 *            Tax Amount for a document
	 */
	@Override
	public void setTaxAmt(final BigDecimal TaxAmt) {
		if(TaxAmt.scale() > getPrecision()){
			super.setTaxAmt(TaxAmt.setScale(REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP));
		}else{
			super.setTaxAmt(TaxAmt);
		}
	}

	/** redondeo para los calculos */
	private final int REDONDEO6_Calculos = 6;

//	/**
//	 * Calculo de impuesto
//	 */
//	private void setTaxAmt(final boolean taxIncluded) {
//		try {
//			if (getTax() != null && getTax().getC_Tax_ID() > 0) {
//				setTaxAmt(getTax().calculateTax(getLineNetAmt(), taxIncluded,
//						REDONDEO6_Calculos));//
//			} else {
//				setTaxAmt(Env.ZERO);
//			}
//		} catch (Exception e) {
//			super.setTaxAmt(Env.ZERO);
//		}
//	}

	/**
	 * Get Tax Amount.
	 *
	 * @return Tax Amount for a document
	 */
	@Override
	public BigDecimal getTaxAmt() {
		return super.getTaxAmt(); // .setScale(REDONDEO6,
									// BigDecimal.ROUND_HALF_UP);
	}

	public MUOM getUom() {
		return new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
	}

	public MInvoice getmInvoice() {
		if (mInvoice == null && getC_Invoice_ID() > 0) {
			mInvoice = new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
		}
		return mInvoice;
	}

	public void setmInvoice(final MInvoice mInvoice) {
		this.mInvoice = mInvoice;
	}

	/**
	 * Obtenemos le producto.
	 *
	 * @return
	 */
	public MProduct getProduct() {
		if (mProduct == null || mProduct.getM_Product_ID() == 0) {
			mProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return mProduct;
	}

//	public void setLineTotalAmt() {
//		getLineNetAmt().add(getTaxAmt());
//	}

	/**
	 * Calculatar Line Total Amt. Esto se realiza sumando la cantidad en
	 * LineNetAmt + TaxAmt
	 *
	 * @return
	 */
	public void setLineTotalAmt() {
		// Rmontemayor--> Se hace la suma de las columnas
		BigDecimal total = getLineNetAmt().add(getTaxAmt());
		// Se revisa si la precision de punto decimal es la correcta, si no, la
		// arregla.
		if (total.scale() > getPrecision()) {
			total = total.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
		}
		super.setLineTotalAmt(total);
	}


	public int getExtensionFact() {
		return extensionFact;
	}

	public void setExtensionFact(final int extensionFact) {
		this.extensionFact = extensionFact;
	}

	/**
	 * Por aqui es posible obtene
	 *
	 * @return
	 */
	public MInOutLine getInOutLine() {
		return new MInOutLine(getCtx(), getM_InOutLine_ID(), get_TrxName());
	}

	/**
	 * Por aqui es posible obtener e
	 *
	 * @return
	 */
	public MProduct getProducto() {
		return new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
	}

	/**
	 * Por aqui es posible ob
	 *
	 * @return
	 */
	public MCharge getCharge() {
		return new MCharge(getCtx(), getC_Charge_ID(), get_TrxName());
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(final BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public String getTipoLinea() {
		return tipoLinea;
	}

	public void setTipoLinea(final String tipoLinea) {
		this.tipoLinea = tipoLinea;
	}

	public String getSubTipoLinea() {
		return subTipoLinea;
	}

	public void setSubTipoLinea(final String subTipoLinea) {
		this.subTipoLinea = subTipoLinea;
	}

//	/**
//	 * Metodo usado para los casos en que se crean las lineas de facturas por
//	 * los decuentos en convenio
//	 */
//	public void copyPrice() {
//
//		if (getCtx() != null) {
//
//			setPriceActual(getPriceList());
//			setPriceEntered(getPriceList());
//			setPriceLimit(getPriceList());
//			setQtyEntered(Env.ONE);
//			setQtyInvoiced(getQtyEntered());
//
//			setLineNetAmt(Env.ZERO);
//
//			// impuesto excento
//			int taxExcento_ID = 0;
//			try {
//				taxExcento_ID = MEXMETax
//						.getExemptTaxID(getCtx(), get_TrxName());// TTPR Tasa de
//																	// impuesto
//																	// con
//																	// vigencia
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			setC_Tax_ID(taxExcento_ID);
//			setTaxAmt(Env.ZERO);
//			setDiscountTaxAmt(Env.ZERO);
//			setLineTotalAmt(Env.ZERO);
//		}
//	}

//	/**
//	 * Impuesto por producto
//	 */
//	public void setC_Tax_ID() {
//		this.setC_Tax_ID(getTax().getC_Tax_ID());
//	}

//	/**
//	 * Precios de la linea de factura
//	 * Facturación directa
//	 * @param precio
//	 * @param extension
//	 * @param idProd
//	 * @param idChargeCargo
//	 *            del descuento global
//	 */
//	public void setPricesExt(final BigDecimal precio, final int extension,
//			final int idProd, final int idCharge, final boolean reloadTax,
//			final BigDecimal taxAmt) {
//		
//		setC_Charge_ID(idCharge);// Cargo del descuento global
//		setM_Product_ID(idProd);// CCCMD
//
//		setPriceList(precio);
//		setPriceActual(precio);
//		setPriceLimit(precio);
//
//		setLineNetAmt();
//		setExtensionFact(extension);
//
//		if (idProd > 0 && reloadTax) {
//			// Se calcula el monto del impuesto YA INCLUIDO en el importe
//			setTaxAmt(true);
//			// Se asigna el importe a las columnas sin impuesto
//			setPrices(getPriceList().subtract(getTaxAmt()));
//		} else if (idCharge > 0) {
//			// impuesto en cero
//			try {
//				setC_Tax_ID(MEXMETax.getImpuestoCargo(getCtx(),
//						idCharge, get_TrxName()).getC_Tax_ID());
//			} catch (Exception e) {
//				s_log.log(Level.SEVERE, "No existe tasa de impuesto exenta", e);
//			}
//
//			setTaxAmt(taxAmt == null ? Env.ZERO : taxAmt);
//			// Se asigna el importe a las columnas con impuesto
//			setPrices(getPriceList());
//
//		} else {
//			// impuesto en cero
//			try {
//				setC_Tax_ID(MEXMETax.getExemptTaxID(getCtx(),
//						get_TrxName()));
//			} catch (Exception e) {
//				s_log.log(Level.SEVERE, "No existe tasa de impuesto exenta", e);
//			}
//
//			setTaxAmt(Env.ZERO);
//			// Se asigna el importe a las columnas con impuesto
//			setPrices(getPriceList());
//		}
//
//		// si la extension es cero (paciente), el tipo de linea es cargo
//		if (extension == 0) {
//			setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
//		}
//	}

	/**
	 * precio
	 * Facturación directa
	 * @param precio
	 */
	public void setPrices(final BigDecimal precio) {
		setPriceActual(precio);
		setPriceLimit(precio);
		setPriceList(precio);
		setLineNetAmt();
		setLineTotalAmt();
	}

//	/**
//	 * Valores por defecto para la linea de la factura
//	 * Facturación directa
//	 * @param ctx
//	 * @param iline
//	 * @return
//	 */
//	public void valoresPorDefecto(final Properties ctx) {
//		if (getM_Product_ID() > 0) {
//			setC_UOM_ID(getProducto().getC_UOM_ID());
//			setC_Tax_ID(getProducto().getTax().getC_Tax_ID());
//		} else {
//			setC_Tax_ID(getCharge().getMTax().getC_Tax_ID());
//		}
//
//		setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(ctx)); // Estacion de logueo
//		setQtyEntered(Env.ONE);
//		setQtyInvoiced(Env.ONE);
//		setDescription("Cargo Add");
//		//setDescriptionServ("Cargo Add"); Se utiliza para la descripcion de cargo (C_Charge_Id)
//		setProcessed(false);
//
//		setPriceEntered(getPriceList());
//		setLineNetAmt();
//		setTaxAmt();
//		setLineTotalAmt();
//		setLine(0);
//	}

//	/**
//	 * Copago, Coaseguro, Coaseguro Medico y Deducible
//	 * Facturación directa
//	 * @param confID
//	 * @param precio
//	 * @param extension
//	 * @param reloadTax
//	 */
//	public void setCCCMD(final int confID, final BigDecimal precio,
//			final int extension) {
//		setM_Product_ID(confID);// Producto del copago
//		valoresPorDefecto(getCtx());
//		setPricesExt(precio, extension, confID, 0, extension == 0, null);
//	}

//	/**
//	 * Descuento
//	 * Facturación directa
//	 * @param confID
//	 * @param precio
//	 * @param extension
//	 * @param reloadTax
//	 */
//	public void setCreateDiscount(final int confID, final BigDecimal precio,
//			final int extension, final BigDecimal taxAmt) {
//		setC_Charge_ID(confID);// Cargo del descuento global
//		valoresPorDefecto(getCtx());
//		setPricesExt(precio, extension, 0, confID, true, taxAmt);
//	}

	/**
	 * Copiar los datos del cargos de la cuenta
	 * a las lineas de la factura
	 * @param cargos
	 */
	public void setValuesCtaPacDet(final MCtaPacDet cargos) {
		PO.copyValues(cargos, this);

		setQtyEntered(cargos.getQtyOrdered());
		setQtyInvoiced(cargos.getQtyDelivered());
		setC_UOM_ID(cargos.getInvoice_UOM_ID()==0?cargos.getC_UOM_ID():cargos.getInvoice_UOM_ID());
		//setDescriptionServ(cargos.getDescription()); Se utiliza para la descripcion de cargo (C_Charge_Id)
		setIsPrinted(true);
		setProcessed(false);
		setExtensionFact(cargos.getEXME_CtaPacExt_ID());

		// siempre sera el precio de Lista
		setPriceEntered(cargos.getPriceActual().scale() > getPrecision()
				? cargos.getPriceActual().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP)
						:cargos.getPriceActual());

		setPriceList(cargos.getPriceListInv().scale() > getPrecision()
				? cargos.getPriceListInv().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP)
						:cargos.getPriceListInv());

		setPriceLimit(cargos.getPriceLimitInv().scale() > getPrecision()
				? cargos.getPriceLimitInv().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP)
						:cargos.getPriceLimitInv());

		setPriceActual(cargos.getPriceActualInv().scale() > getPrecision()
				? cargos.getPriceActualInv().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP)
						:cargos.getPriceActualInv());

		setDiscount(cargos.getDiscountAmt().scale() > getPrecision()
				?cargos.getDiscountAmt().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP)
						:cargos.getDiscountAmt());

		// Objeto de impuesto
		setTax(new MTax(getCtx(), getC_Tax_ID(), null));
		// Cantidad por precio con descuento
		setLineNetAmt(cargos.getLineNetAmt());
		// Cantidad por precio con descuento mas impuesto
		setLineTotalAmt();
		setAD_OrgTrx_ID(cargos.getAD_OrgTrx_ID());
		setM_InOutLine_ID(cargos.getM_InOutLine_ID());
		setEXME_CtaPacDet_ID(cargos.getEXME_CtaPacDet_ID());
	}

	public static List<BeanView> getLinesDetail(final Properties ctx, final String whereClause, final List<Object> params, final String trxname){
		final List<BeanView> ret = new ArrayList<BeanView>();
		try{
			 final MTax[] allTaxs = MTax.getAll(ctx);
			 BeanView aux = null;
             for(final MTax tax : allTaxs){
            	 aux = new BeanView();
            	 aux.setCadena1(MInvoice.getLabelNRs(ctx, whereClause, params, tax.getC_Tax_ID(), trxname));// DESCRIPCION
            	 aux.setInteger1(tax.getC_Tax_ID());// TAX id
            	 final BigDecimal[] arrTotals = MInvoice.getTotals(ctx, whereClause, params, tax.getC_Tax_ID(), trxname);
            	 aux.setDcimal(arrTotals[0].setScale(2, BigDecimal.ROUND_HALF_UP));// MONTO
            	 aux.setDcimal2(arrTotals[1].setScale(2, BigDecimal.ROUND_HALF_UP));// IMPUESTO

            	 ret.add(aux);
              }

		}catch(final Exception e){
			s_log.log(Level.SEVERE, e.getMessage());
		}finally{

		}
		return ret;
	}

	/**
	 * Crear lineas a partir de un C_Charge_ID
	 * @param bean donde:
	 * 				cadena1  = DESCRIPCION
	 * 				integer1 = TAX id
	 * 				dcimal   = MONTO
	 * 				dcimal2  = IMPUESTO
	 */
	public void createLineCharge(final BeanView bean){
		final MTax mTax = new MTax(getCtx(), bean.getInteger1(), null);

		if(mTax==null || mTax.getC_Tax_ID()<1){
			throw new MedsysException(
					Utilerias.getLabel("error.factura.sinLineas"));
		}

		final MCharge mCharge = MCharge.getChargeTax(getCtx(),mTax.getC_TaxCategory_ID(),null);
		//
		if(mCharge==null || mCharge.getC_Charge_ID()<1){
			throw new MedsysException(
					Utilerias.getLabel("error.factura.sinLineas"));
		} else {
			setQtyEntered(Env.ONE);
			setQtyInvoiced(Env.ONE);
			setProcessed(false);
			setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
			//
			setC_Charge_ID(mCharge.getC_Charge_ID());
			setDescription(bean.getCadena1());
			//
			setDiscount(Env.ZERO);
			setC_Tax_ID(bean.getInteger1());//setC_Tax_ID(getCharge().getTax().getC_Tax_ID());
			setTax(mTax);
			//
			setTaxAmt(bean.getDcimal2());// IMPUESTO
			setPriceEntered(bean.getDcimal());// MONTO
			setPrices(bean.getDcimal());// MONTO
		}
	}


	/**
	 * Prorrateo de impuestos
	 * @param isInsurance
	 * @param lsttaxs
	 * @param totalLines L
	 * @param totalTaxs
	 * @param trxName
	 * @return
	 */
	public BigDecimal taxBreakdown(final boolean isInsurance, final List<MInvoiceTax> lsttaxs, final BigDecimal totalLines, final BigDecimal totalTaxs, final String trxName) {
		BigDecimal totalTaxAmt = Env.ZERO;

		if(totalLines.compareTo(Env.ZERO)==0 || totalTaxs.compareTo(Env.ZERO)==0){
			return totalTaxAmt;
		}

		for (final MInvoiceTax mInvTax   : lsttaxs) {
			// 16($300) --  (linea x 400 / total)
			final BigDecimal base = (mInvTax.getTaxBaseAmt().multiply(getLineNetAmt())).divide(totalLines, 4, BigDecimal.ROUND_HALF_UP);
			// negativo si es aseguradora positivo si es particular
			final BigDecimal imps = (mInvTax.getTaxAmt().multiply(getTaxAmt())).divide(totalTaxs, 4, BigDecimal.ROUND_HALF_UP);

			final MInvoiceTax mInvTaxBD = MInvoiceTax.getInvoiceTax(getCtx(), mInvTax.getC_Tax_ID(), getC_Invoice_ID(), trxName);
			if(mInvTaxBD!=null){
				mInvTaxBD.setTaxBaseAmt(mInvTaxBD.getTaxBaseAmt().add(base));
				mInvTaxBD.setTaxAmt(mInvTaxBD.getTaxAmt().add(imps));
				if(mInvTaxBD.save(trxName)){
					totalTaxAmt = totalTaxAmt.add(imps);
				} else {
					// Envio de error
					throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
				}
			}
		}
		return totalTaxAmt;
	}
	public boolean isCCCmD(){
		return getM_Product_ID() > 0 && getProduct() != null && !getProduct().isProduct();
	}
	
	
	public void setCost(){
		final GetCost costos = new GetCost(getCtx(), getM_Product_ID());
		costos.setCosts();
		setCostAverage(costos.getCostAverage()==null?Env.ZERO:costos.getCostAverage());
		setCostStandard(costos.getCostStandard()==null?Env.ZERO:costos.getCostStandard());
		setPriceLastPO(costos.getPriceLastPO()==null?Env.ZERO:costos.getPriceLastPO());
	}

	/**
	 * Copiar los datos de una linea de recepcion a una linea de factura
	 * @param inOutLine
	 */
	public void setMaterialReceiptValues(final MInOutLine inOutLine) {
		setM_Product_ID(inOutLine.getM_Product_ID());
		setC_Charge_ID(inOutLine.getC_Charge_ID());
		setC_UOM_ID(inOutLine.getC_UOM_ID());
		setC_UOMVolume_ID(inOutLine.getC_UOMVolume_ID());
		setQty(inOutLine.getQtyEntered());
		setQtyEntered(inOutLine.getQtyEntered());
		setQtyEntered_Vol(inOutLine.getQtyEntered_Vol());
		setQtyInvoiced_Vol(inOutLine.getQtyEntered_Vol());
		setPriceActual(inOutLine.getPriceActual());
		setPriceActual_Vol(inOutLine.getPriceActual_Vol());
		setPriceEntered(inOutLine.getPriceActual());
		setPriceEntered_Vol(inOutLine.getPriceActual_Vol());
		setPriceList(inOutLine.getPriceList());
		setPriceList_Vol(inOutLine.getPriceList_Vol());
		setAD_OrgTrx_ID(inOutLine.getAD_OrgTrx_ID());
		setC_Tax_ID(inOutLine.getC_Tax_ID());
		setDiscount(inOutLine.getDiscount());
		final int C_OrderLine_ID = inOutLine.getC_OrderLine_ID();
		if (C_OrderLine_ID != 0) {
			final MOrderLine oLine = new MOrderLine(getCtx(), C_OrderLine_ID, get_TrxName());
			setPriceLimit(oLine.getPriceLimit());
		}
		setLineNetAmt();
		setTaxAmt();
		setLineTotalAmt();
		
	}
	
	/**
	 * Copiar los datos de una linea de recepcion a una linea de factura
	 * @param orderLine
	 */
	public void setPurchaseOrderValues(final MOrderLine orderLine) {
		setM_Product_ID(orderLine.getM_Product_ID());
		setC_Charge_ID(orderLine.getC_Charge_ID());
		setC_UOM_ID(orderLine.getC_UOM_ID());
		setC_UOMVolume_ID(orderLine.getC_UOMVolume_ID());
		setQty(orderLine.getQtyEntered());
		setQtyEntered(orderLine.getQtyEntered());
		setQtyEntered_Vol(orderLine.getQtyEntered_Vol());
		setQtyInvoiced_Vol(orderLine.getQtyEntered_Vol());
		setPriceActual(orderLine.getPriceActual());
		setPriceActual_Vol(orderLine.getPriceActual_Vol());
		setPriceEntered(orderLine.getPriceActual());
		setPriceEntered_Vol(orderLine.getPriceActual_Vol());
		setPriceList(orderLine.getPriceList());
		setPriceList_Vol(orderLine.getPriceList_Vol());
		setPriceLimit(orderLine.getPriceLimit());
		setAD_OrgTrx_ID(orderLine.getAD_OrgTrx_ID());
		setC_Tax_ID(orderLine.getC_Tax_ID());
		setDiscount(orderLine.getDiscount());
		setLineNetAmt();
		setTaxAmt();
		setLineTotalAmt();

	}
	
	private String lineValue = null;
	private String lineName = null;
	private String udmName = null;
	private BigDecimal discountQty = null;

	public String getLineValue() {
		if (lineValue == null) {
			if (getM_Product_ID() > 0) {
				lineValue = getM_Product().getValue();
			} else {
				lineValue = getC_Charge().getValue();
			}
		}
		return lineValue;
	}

	public String getLineName() {
		if (lineName == null) {
			if (getM_Product_ID() > 0) {
				lineName = getM_Product().getName();
			} else {
				lineName = getC_Charge().getName();
			}
		}
		return lineName;
	}

	public String getUdmName() {
		if (udmName == null) {
			final MUOM uom = new MUOM(Env.getCtx(), getC_UOMVolume_ID(), null);
			if (uom != null && uom.getC_UOM_ID() <= 0) {
				udmName = "";
			} else {
				udmName = uom.getName();
			}
		}
		return udmName;
	}
	
	public BigDecimal getDiscountQty() {
		if (discountQty == null){
			if (getPriceList_Vol().compareTo(BigDecimal.ZERO) == 0) {
				discountQty = getPriceEntered_Vol().multiply(getDiscount().divide(new BigDecimal(100)));
			}else{
				discountQty = getPriceList_Vol().subtract(getPriceActual_Vol());
			}
		}
		return discountQty;
	}

} // MInvoiceLine