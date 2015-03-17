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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMatchPO;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 *  Post MatchPO Documents.
 *  <pre>
 *  Table:              C_MatchPO (473)
 *  Document Types:     MXP
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_MatchPO.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_MatchPO extends Doc
{
	/** Generar asiento contable */
	boolean post = true;
	
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	protected Doc_MatchPO (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MMatchPO.class, rs, DOCTYPE_MatMatchPO, trxName);
	}   //  Doc_MatchPO

	private int         m_C_OrderLine_ID = 0;
	private MOrderLine	m_oLine = null;
	//
	private int         m_M_InOutLine_ID = 0;
	@SuppressWarnings("unused")
	private int         m_C_InvoiceLine_ID = 0;
	private ProductCost m_pc;
	private int			m_M_AttributeSetInstance_ID = 0;
	private MMatchPO    m_MatchPO = null; 
	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		setC_Currency_ID (Doc.NO_CURRENCY);
		final MMatchPO matchPO = (MMatchPO)getPO();
		setDateDoc(matchPO.getDateTrx());
		
		// Si controla existencias se genera al asiento contable por organizacion del documento
		if(matchPO.getPost()==null){
			post = MEXMEMejoras.isControlaExistencias(matchPO.getAD_Client_ID(), matchPO.getAD_Org_ID(), null);
			
			final MMatchPO matchPOSub = new MMatchPO(getCtx(), matchPO.get_ID(), null);// sin transacción
			matchPOSub.set_TrxName(null);
			matchPOSub.setPost(post?"Y":"N");
			matchPOSub.save();// sin transacción evita bloqueos
			
		} else {
			post = matchPO.getPost().equals("Y");
			p_PostMovement = matchPO.getPost().equals(Doc.STATUS_Unposted)?false:true;
		}
		
		//
		m_M_AttributeSetInstance_ID = matchPO.getM_AttributeSetInstance_ID();
		setQty (matchPO.getQty());
		//
		m_C_OrderLine_ID = matchPO.getC_OrderLine_ID();
		m_oLine = new MOrderLine (getCtx(), m_C_OrderLine_ID, getTrxName());
		//
		m_M_InOutLine_ID = matchPO.getM_InOutLine_ID();
		m_C_InvoiceLine_ID = matchPO.getC_InvoiceLine_ID();
		//
		m_pc = new ProductCost (getCtx(), getM_Product_ID(), m_M_AttributeSetInstance_ID, getTrxName());
		m_pc.setQty(getQty());
		
		// No se contabiliza si es consigna
		final MInOutLine mLine = new MInOutLine(getCtx(),m_M_InOutLine_ID, getTrxName());
		if(mLine.getParent().isSetpoint()){
			post = false;
		}
		
		m_MatchPO = matchPO;
		return null;
	}   //  loadDocumentDetails

	
	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line and tax amounts from total - no rounding
	 *  @return Zero - always balanced
	 */
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}   //  getBalance

	
	/**
	 *  Create Facts (the accounting logic) for
	 *  MXP.
	 *  <pre>
	 *      Product PPV     <difference>
	 *      PPV_Offset                  <difference>
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (final MAcctSchema as)
	{
		final ArrayList<Fact> facts = new ArrayList<Fact>();
		//
		if (getM_Product_ID() == 0		//  Nothing to do if no Product
			|| getQty().signum() == 0
			|| m_M_InOutLine_ID == 0)	//  No posting if not matched to Shipment
		{
			log.fine("No Product/Qty - M_Product_ID=" + getM_Product_ID()
				+ ",Qty=" + getQty());
			return facts;
		}
		
		// Asientos solo si controla existencias o si es un servicio
		final MProduct mProduct = new MProduct(as.getCtx(), getM_Product_ID(), null);
		if(post || !mProduct.isStocked()){

			//  create Fact Header
			final Fact fact = new Fact(this, as, Fact.POST_Actual);
			setC_Currency_ID(as.getC_Currency_ID());

			//	Purchase Order Line
			BigDecimal poCost = m_MatchPO.getCost(as, m_oLine);
//			BigDecimal poCost = m_oLine.getPriceCost();
//			if (poCost == null || poCost.signum() == 0) {
//				poCost = m_oLine.getPriceActual();
//			}
//			poCost = poCost.multiply(getQty());			//	Delivered so far
//			
//			//	Different currency
//			if (m_oLine.getC_Currency_ID() != as.getC_Currency_ID())
//			{
//				final MOrder order = m_oLine.getParent();
//				final BigDecimal rate = MConversionRate.getRate(
//						order.getC_Currency_ID()
//						, as.getC_Currency_ID()
//						, order.getDateAcct()
//						, order.getC_ConversionType_ID()
//						, m_oLine.getAD_Client_ID()
//						, m_oLine.getAD_Org_ID());
//				if (rate == null) {
//					log.severe("Purchase Order not convertible - " + as.getName());
//					p_Error = Utilerias.getLabel("error.caja.conversionRate", as.getName());
//					return null;
//				}
//				poCost = poCost.multiply(rate);
//				if (poCost.scale() > as.getCostingPrecision()) {
//					poCost = poCost.setScale(as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
//				}
//			}

			if (poCost == null) {
				log.severe("Purchase Order not convertible - " + as.getName());
				p_Error = "Purchase Order not convertible - " + as.getName();//Utilerias.getLabel("error.caja.conversionRate", as.getName());
				return null;
			}
			
			//	Create PO Cost Detail Record firs 
			// Se comentaron las líneas para que se ejecute en el completeit de minout
			/*MCostDetail.createOrder(as
					, m_oLine.getAD_Org_ID()
					, getM_Product_ID()
					, m_M_AttributeSetInstance_ID
					, m_C_OrderLine_ID
					, 0 		//	no cost element
					, poCost
					, getQty()		//	Delivered
					, m_oLine.getDescription()
					, getTrxName());*/

			//	Current Costs
//			String costingMethod = as.getCostingMethod();
//			final MProduct product = MProduct.get(getCtx(), getM_Product_ID());

			// Expert: #Post,Cost And Price
//			MProductCategoryAcct pca = MProductCategoryAcct.getAcct(product, as);
//			if (pca.getCostingMethodDefault() != null)
//				costingMethod = pca.getCostingMethodDefault();

			final BigDecimal costs = m_pc.getProductCosts(as
					, getAD_Org_ID()
					, mProduct.getCostingMethod(as)
					, m_C_OrderLine_ID
					, false //	non-zero costs
					, false);	// Evitar que calcule el costo nuevamente

			//	No Costs yet - no PPV
			if (costs == null || costs.signum() == 0)
			{
				p_Error = "Resubmit - No Costs for " + mProduct.getName();
				log.log(Level.SEVERE, p_Error);
				return null;
			}

			//	Difference
			BigDecimal difference = poCost.subtract(costs);
			//	Nothing to post
			if (difference.signum() == 0)
			{
				log.log(Level.FINE, "No Cost Difference for M_Product_ID=" + getM_Product_ID());
				facts.add(fact);
				return facts;
			}

			//  Product PPV
			FactLine cr = fact.createLine(null,
					m_pc.getAccount(ProductCost.ACCTTYPE_P_PPV, as),
					as.getC_Currency_ID(), difference);
			if (cr != null)
			{
				cr.setQty(getQty());
				cr.setC_BPartner_ID(m_oLine.getC_BPartner_ID());
				cr.setC_Activity_ID(m_oLine.getC_Activity_ID());
				cr.setC_Campaign_ID(m_oLine.getC_Campaign_ID());
				cr.setC_Project_ID(m_oLine.getC_Project_ID());
				cr.setC_UOM_ID(m_oLine.getC_UOM_ID());
				cr.setUser1_ID(m_oLine.getUser1_ID());
				cr.setUser2_ID(m_oLine.getUser2_ID());
			}

			//  PPV Offset
			FactLine dr = fact.createLine(null,
					getAccount(Doc.ACCTTYPE_PPVOffset, as),
					as.getC_Currency_ID(), difference.negate());
			if (dr != null)
			{
				dr.setQty(getQty().negate());
				dr.setC_BPartner_ID(m_oLine.getC_BPartner_ID());
				dr.setC_Activity_ID(m_oLine.getC_Activity_ID());
				dr.setC_Campaign_ID(m_oLine.getC_Campaign_ID());
				dr.setC_Project_ID(m_oLine.getC_Project_ID());
				dr.setC_UOM_ID(m_oLine.getC_UOM_ID());
				dr.setUser1_ID(m_oLine.getUser1_ID());
				dr.setUser2_ID(m_oLine.getUser2_ID());
			}

			//
			facts.add(fact);
		}
		return facts;
	}   //  createFact

	/**
	 *  Update Product Info (old).
	 *  - Costing (CostStandardPOQty, CostStandardPOAmt)
	 *  @param C_AcctSchema_ID accounting schema
	 *  @deprecated old costing
	 */
	@SuppressWarnings("unused")
	private void updateProductInfo (int C_AcctSchema_ID)
	{
		log.fine("M_MatchPO_ID=" + get_ID());

		//  update Product Costing
		//  requires existence of currency conversion !!
		StringBuffer sql = new StringBuffer (
			"UPDATE M_Product_Costing pc "
			+ "SET (CostStandardPOQty,CostStandardPOAmt) = "
			+ "(SELECT CostStandardPOQty + m.Qty,"
			+ " CostStandardPOAmt + currencyConvert(ol.PriceActual,ol.C_Currency_ID,a.C_Currency_ID,ol.DateOrdered,null,ol.AD_Client_ID,ol.AD_Org_ID)*m.Qty "
			+ "FROM M_MatchPO m, C_OrderLine ol, C_AcctSchema a "
			+ "WHERE m.C_OrderLine_ID=ol.C_OrderLine_ID"
			+ " AND pc.M_Product_ID=ol.M_Product_ID"
			+ " AND pc.C_AcctSchema_ID=a.C_AcctSchema_ID"
			+ "AND m.M_MatchPO_ID=").append(get_ID()).append(") ")
			.append("WHERE pc.C_AcctSchema_ID=").append(C_AcctSchema_ID)
			.append(" AND pc.M_Product_ID=").append(getM_Product_ID());
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_Costing - Updated=" + no);
	}   //  updateProductInfo

}   //  Doc_MatchPO
