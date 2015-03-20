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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.util.CompiereUserError;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	Create PO from Requisition 
 *	
 *	
 *  @author Jorg Janke
 *  @version $Id: RequisitionPOCreate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class RequisitionPOCreate extends SvrProcess
{
	/** Org					*/
	private int			p_AD_Org_ID = 0;
	/** Warehouse			*/
	private int			p_M_Warehouse_ID = 0;
	/**	Doc Date From		*/
	private Timestamp	p_DateDoc_From;
	/**	Doc Date To			*/
	private Timestamp	p_DateDoc_To;
	/**	Doc Date From		*/
	private Timestamp	p_DateRequired_From;
	/**	Doc Date To			*/
	private Timestamp	p_DateRequired_To;
	/** Priority			*/
	private String		p_PriorityRule = null;
	/** User				*/
	private int			p_AD_User_ID = 0;
	/** Product				*/
	private int			p_M_Product_ID = 0;
	/** Product	Category	*/
	private int			p_M_Product_Category_ID = 0;
	/** BPartner Group	*/
	private int			p_C_BP_Group_ID = 0;
	/** Requisition			*/
	private int 		p_M_Requisition_ID = 0;

	/** Consolidate			*/
//	private boolean		p_ConsolidateDocument = false;

	/** Order				*/
	private MOrder		m_order = null;
	/** Order Line			*/
	private MOrderLine	m_orderLine = null;
	/** Orders Cache : (C_BPartner_ID, DateRequired, M_PriceList_ID) -> MOrder */
	private Map<MultiKey, MOrder> m_cacheOrders = new HashMap<MultiKey, MOrder>();
	
	private Timestamp p_DateRequired;
	
	private MRequisition req = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null) { 
				;
			} else if ("AD_Org_ID".equals(name)) {
				p_AD_Org_ID = para[i].getParameterAsInt();
			} else if ("M_Warehouse_ID".equals(name)) {
				p_M_Warehouse_ID = para[i].getParameterAsInt();
			} else if ("DateDoc".equals(name)) {
				p_DateDoc_From = (Timestamp)para[i].getParameter();
				p_DateDoc_To = (Timestamp)para[i].getParameter_To();
			} else if ("DateRequired".equals(name)) {
				p_DateRequired_From = (Timestamp)para[i].getParameter();
				p_DateRequired_To = (Timestamp)para[i].getParameter_To();
			} else if ("PriorityRule".equals(name)) {
				p_PriorityRule = (String)para[i].getParameter();
			} else if ("AD_User_ID".equals(name)) {
				p_AD_User_ID = para[i].getParameterAsInt();
			} else if ("M_Product_ID".equals(name)) {
				p_M_Product_ID = para[i].getParameterAsInt();
			} else if ("M_Product_Category_ID".equals(name)) {
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			} else if ("C_BP_Group_ID".equals(name)) {
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			} else if ("M_Requisition_ID".equals(name)) {
				p_M_Requisition_ID = para[i].getParameterAsInt();
//			} else if ("ConsolidateDocument".equals(name)) {
//				p_ConsolidateDocument = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}	//	prepare
	
	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		
		//	Specific
		if (p_M_Requisition_ID == 0) {
			createAllReqs();
		} else {
			createSingleReq();
		}
		
		
		return "";
	}	//	doit
	
	private int 		m_M_Requisition_ID = 0;
	private int 		m_M_Product_ID = 0;
	private int			m_M_AttributeSetInstance_ID = 0;
	/** BPartner				*/
	private MBPartner	m_bpartner = null;
	
	
	
	/**
	 * Creates purchase order(s) for a specific requisition.
	 * @throws Exception
	 */
	private void createSingleReq() throws Exception {
		log.info("M_Requisition_ID=" + p_M_Requisition_ID);
		req = new MRequisition(getCtx(), p_M_Requisition_ID, get_TrxName());
		p_DateRequired = req.getDateRequired();
		
		if (!MRequisition.DOCSTATUS_Completed.equals(req.getDocStatus())) {
			throw new CompiereUserError("@DocStatus@ = " + req.getDocStatus());
		}
		
		MRequisitionLine[] lines = req.getLines(false);
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].getC_OrderLine_ID() == 0) {
				process (lines[i]);
			}
		}
		
		closeOrder();
	}
	
	/**
	 * Creates purchase order(s) for all the pending requisitions.
	 * @throws Exception
	 */
	private void createAllReqs() throws Exception {
		p_DateRequired = new Timestamp(System.currentTimeMillis());

		//	
		log.info("AD_Org_ID=" + p_AD_Org_ID
				+ ", M_Warehouse_ID=" + p_M_Warehouse_ID
				+ ", DateDoc=" + p_DateDoc_From + "/" + p_DateDoc_To
				+ ", DateRequired=" + p_DateRequired_From + "/" + p_DateRequired_To
				+ ", PriorityRule=" + p_PriorityRule
				+ ", AD_User_ID=" + p_AD_User_ID
				+ ", M_Product_ID=" + p_M_Product_ID);
//				+ ", ConsolidateDocument" + p_ConsolidateDocument);

		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		whereClause.append("C_OrderLine_ID IS NULL AND M_Product_ID IS NOT NULL");
		if (p_AD_Org_ID > 0) {
			whereClause.append(" AND AD_Org_ID=?");
			params.add(p_AD_Org_ID);
		}
		
		if (p_M_Product_ID > 0) {
			whereClause.append(" AND M_Product_ID=?");
			params.add(p_M_Product_ID);
		} else if (p_M_Product_Category_ID > 0) {
			whereClause.append(" AND EXISTS (SELECT 1 FROM M_Product p WHERE M_RequisitionLine.M_Product_ID=p.M_Product_ID")
			.append(" AND p.M_Product_Category_ID=?)");
			params.add(p_M_Product_Category_ID);
		}

		if (p_C_BP_Group_ID > 0) {
			whereClause.append(" AND (")
			.append("M_RequisitionLine.C_BPartner_ID IS NULL")
			.append(" OR EXISTS (SELECT 1 FROM C_BPartner bp WHERE M_RequisitionLine.C_BPartner_ID=bp.C_BPartner_ID AND bp.C_BP_Group_ID=?)")
			.append(')');
			params.add(p_C_BP_Group_ID);
		}

		//
		//	Requisition Header
		whereClause.append(
				" AND EXISTS (SELECT 1 FROM M_Requisition r WHERE M_RequisitionLine.M_Requisition_ID=r.M_Requisition_ID AND r.DocStatus=?"
		);
		
		params.add(MRequisition.DOCSTATUS_Completed);
		if (p_M_Warehouse_ID > 0) {
			whereClause.append(" AND r.M_Warehouse_ID=?");
			params.add(p_M_Warehouse_ID);
		}
		
		if (p_DateDoc_From != null) {
			whereClause.append(" AND r.DateDoc >= ?");
			params.add(p_DateDoc_From);
		}
		
		if (p_DateDoc_To != null) {
			whereClause.append(" AND r.DateDoc <= ?");
			params.add(p_DateDoc_To);
		}
		
		if (p_DateRequired_From != null) {
			whereClause.append(" AND r.DateRequired >= ?");
			params.add(p_DateRequired_From);
		}
		
		if (p_DateRequired_To != null) {
			whereClause.append(" AND r.DateRequired <= ?");
			params.add(p_DateRequired_To);
		}
		
		if (p_PriorityRule != null) {
			whereClause.append(" AND r.PriorityRule >= ?");
			params.add(p_PriorityRule);
		}
		
		if (p_AD_User_ID > 0) {
			whereClause.append(" AND r.AD_User_ID=?");
			params.add(p_AD_User_ID);
		}
		
		whereClause.append(')'); // End Requisition Header
		//
		// ORDER BY clause
		StringBuffer orderClause = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
//		if (!p_ConsolidateDocument) {
//			orderClause.append("M_Requisition_ID, ");
//		}
		
		orderClause.append("C_BPartner_ID, (SELECT DateRequired FROM M_Requisition r WHERE M_RequisitionLine.M_Requisition_ID=r.M_Requisition_ID),");
		orderClause.append("M_Product_ID, C_Charge_ID, M_AttributeSetInstance_ID");

		POResultSet<MRequisitionLine> rs = new Query(getCtx(), MRequisitionLine.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(orderClause.toString())
		.setClient_ID()
		.scroll();
		
		try {
			while (rs.hasNext()) {
				process(rs.next());
			}
		} finally {
			DB.close(rs);
		}
		
		closeOrder();
	}
	
	
	/**
	 * 	Process Line
	 *	@param rLine request line
	 * 	@throws Exception
	 */
	private void process (MRequisitionLine rLine) 
			throws Exception {
		if (rLine.getM_Product_ID() == 0 && rLine.getC_Charge_ID() == 0) {
			log.warning("Ignored Line" + rLine.getLine() 
				+ " " + rLine.getDescription()
				+ " - " + rLine.getLineNetAmt());
		} else {
//			if (!p_ConsolidateDocument && rLine.getM_Requisition_ID() != m_M_Requisition_ID) {
//				closeOrder();
//			}
			
			if (m_orderLine == null
				|| rLine.getM_Product_ID() != m_M_Product_ID
				|| rLine.getM_AttributeSetInstance_ID() != m_M_AttributeSetInstance_ID
				|| rLine.getC_Charge_ID() != 0		//	single line per charge
				|| m_order == null
				|| m_order.getDatePromised().compareTo(p_DateRequired) != 0
				) {
				newLine(rLine);
				// No Order Line was produced (vendor was not valid/allowed) => SKIP
				if (m_orderLine == null) {
					return;
				}
			}

			//	Update Order Line
			m_orderLine.setQty(m_orderLine.getQtyOrdered().add(rLine.getQty()));
			
			//rsolorzano
			//cambios para unidades de medida
			m_orderLine.setQtyOrdered_Vol(rLine.getQty_Vol());
			m_orderLine.setQtyEntered_Vol(rLine.getQty_Vol());
			m_orderLine.setQtyDelivered_Vol(BigDecimal.ZERO);
			m_orderLine.setQtyInvoiced_Vol(BigDecimal.ZERO);
			
			m_orderLine.setC_UOMVolume_ID(rLine.getC_UOMVolume_ID());

			
			boolean lisSOTrx = false;
			if( m_orderLine.getC_Order_ID()>0){
				MOrder morder = new MOrder(m_orderLine.getCtx(), m_orderLine.getC_Order_ID(), m_orderLine.get_TrxName());
				lisSOTrx = morder.isSOTrx();
			}
			
			//se obtiene el product price
			MProductPrice mPrice =  
					MProductPrice.getProductPrice(0,m_order.getM_PriceList_ID(), rLine.getM_Product_ID(), lisSOTrx);

			if(mPrice == null){
				m_orderLine.setPriceList_Vol(BigDecimal.ZERO);
				m_orderLine.setPriceEntered_Vol(BigDecimal.ZERO);
				m_orderLine.setPriceActual_Vol(BigDecimal.ZERO);
				m_orderLine.setPriceList(BigDecimal.ZERO);
				m_orderLine.setPriceEntered(BigDecimal.ZERO);
				m_orderLine.setPriceActual(BigDecimal.ZERO);			
			} else {
				//Si el producto existe en una lista de precios
				m_orderLine.setPriceList_Vol(mPrice.getPriceList_Vol());
				m_orderLine.setPriceEntered_Vol(mPrice.getPriceList_Vol());
				m_orderLine.setPriceActual_Vol(mPrice.getPriceList_Vol());
				m_orderLine.setPriceList(mPrice.getPriceList());
				m_orderLine.setPriceEntered(mPrice.getPriceList());
				m_orderLine.setPriceActual(mPrice.getPriceList());
			}
			
			
			//	Update Requisition Line
			rLine.setC_OrderLine_ID(m_orderLine.getC_OrderLine_ID());
			rLine.saveEx();
		}
		
		
	}	//	process
	
	/**
	 * 	Create new Order
	 *	@param rLine request line
	 *	@param C_BPartner_ID b.partner
	 * 	@throws Exception
	 */
	private void newOrder(MRequisitionLine rLine, int C_BPartner_ID) 
			throws Exception {
		if (m_order != null) {
			closeOrder();
		}
		
		//	BPartner
		if (m_bpartner == null || C_BPartner_ID != m_bpartner.get_ID()) {
			m_bpartner = MBPartner.get(getCtx(), C_BPartner_ID);
		}
		
		
		//	Order
		Timestamp DateRequired = p_DateRequired;
		int M_PriceList_ID =  m_bpartner==null?0:m_bpartner.getPO_PriceList_ID();
		MultiKey key = new MultiKey(C_BPartner_ID, DateRequired, M_PriceList_ID);
		m_order = m_cacheOrders.get(key);
		
		if (m_order == null) {
			
			//get the document type
			MDocType dt = null;
			MDocType[] dts = 
				MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_PurchaseOrder);
			
			for(int i = 0; i < dts.length; i++) {
				if(dts[i].getAD_Org_ID() == Env.getAD_Org_ID(getCtx()) && !dts[i].isSOTrx()) {
					dt = dts[i];
					break;
				}
			}
			//MDocType[10001109-Purchase Order,DocNoSequence_ID=10001608]
			if(dt == null) {
				throw new Exception("Can not find Document type for Purchase Order."); //FIXME : hard coded message
			} else {
				m_order = new MOrder(getCtx(), 0, get_TrxName());
				m_order.setDatePromised(DateRequired);
				m_order.setIsSOTrx(false);
				m_order.setC_DocType_ID(dt.getC_DocType_ID());
				m_order.setC_DocTypeTarget_ID(dt.getC_DocType_ID());
				m_order.setBPartner(m_bpartner);
				m_order.setM_PriceList_ID(M_PriceList_ID);
				//	default po document type
//				if (!p_ConsolidateDocument)
//				{
					m_order.setDescription(
							Msg.getElement(getCtx(), "M_Requisition_ID")
							+ ": " + rLine.getParent().getDocumentNo()
					);
					//Card 955 Imprimir las Órdenes de Compra en un rango de fechas 
					m_order.setM_Requisition_ID(rLine.getM_Requisition_ID());
					
//				}
				
				//	Prepare Save
				m_order.saveEx();
				// Put to cache
				m_cacheOrders.put(key, m_order);
			}
		}
		m_M_Requisition_ID = rLine.getM_Requisition_ID();
	}	//	newOrder

	/**
	 * 	Close Order
	 * 	@throws Exception
	 */
	private void closeOrder() throws Exception {
		if (m_orderLine != null) {
			m_orderLine.saveEx();
		}
		
		if (m_order != null) {
			m_order.load(get_TrxName());
			//se limpia el log
			//this.getProcessInfo().setLogList(new ArrayList<ProcessInfoLog>());
			addLog(0, null, m_order.getGrandTotal(), m_order.getDocumentNo());
		}
		
		m_order = null;
		m_orderLine = null;
	}	//	closeOrder

	
	/**
	 * 	New Order Line (different Product)
	 *	@param rLine request line
	 * 	@throws Exception
	 */
	private void newLine(final MRequisitionLine rLine) throws Exception
	{
		if (m_orderLine != null) {
			m_orderLine.saveEx();
		}
		
		m_orderLine = null;
		MProduct product = MProduct.get(getCtx(), rLine.getM_Product_ID());

		//	Get Business Partner
		int C_BPartner_ID = rLine.getC_BPartner_ID();
		
		if (C_BPartner_ID == 0) {
			if (rLine.getC_Charge_ID() == 0) {
				// Find Strategic Vendor for Product
				// TODO: refactor
				MProductPO[] ppos = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), null);
				for (int i = 0; i < ppos.length; i++) {
					if (ppos[i].isCurrentVendor() && ppos[i].getC_BPartner_ID() != 0) {
						C_BPartner_ID = ppos[i].getC_BPartner_ID();
						break;
					}
				}
				
				if (C_BPartner_ID == 0 && ppos.length > 0) {
					C_BPartner_ID = ppos[0].getC_BPartner_ID();
				}
				
//				if (C_BPartner_ID == 0) {
//					//throw new CompiereUserError("No Vendor for  product " + product.getName());
//					// El socio no es necesario hasta completar la orden, pensando en que no se utiliza la orden de venta
//				}
			} else {
				MCharge charge = MCharge.get(getCtx(), rLine.getC_Charge_ID());
				C_BPartner_ID = charge.getC_BPartner_ID();
//				if (C_BPartner_ID == 0) {
//					//throw new CompiereUserError("No Vendor for Charge " + charge.getName());
//					// El socio no es necesario hasta completar la orden, pensando en que no se utiliza la orden de venta
//				}
			}
		}
		
		if(C_BPartner_ID>0 && !isGenerateForVendor(C_BPartner_ID)) {
				log.info("Skip for partner "+C_BPartner_ID);
				return;
		}

		//	New Order - Different Vendor
		if (m_order == null 
			|| m_order.getC_BPartner_ID() != C_BPartner_ID
//			|| m_order.getDatePromised().compareTo(p_DateRequired) != 0
			) {
			newOrder(rLine, C_BPartner_ID);
		}
		
		//	No Order Line
		m_orderLine = new MOrderLine(m_order);
		m_orderLine.setDatePromised(p_DateRequired);
		
		if (product == null) {
			m_orderLine.setC_Charge_ID(rLine.getC_Charge_ID());
			m_orderLine.setPriceActual(rLine.getPriceActual());
		} else {
			m_orderLine.setProduct(product);
			m_orderLine.setM_AttributeSetInstance_ID(rLine.getM_AttributeSetInstance_ID());
		}
		
		m_orderLine.setAD_Org_ID(rLine.getAD_Org_ID());
		
		//	Prepare Save
		m_M_Product_ID = rLine.getM_Product_ID();
		m_M_AttributeSetInstance_ID = rLine.getM_AttributeSetInstance_ID();
		m_orderLine.saveEx();
	}	//	newLine

	/**
	 * Do we need to generate Purchase Orders for given Vendor 
	 * @param C_BPartner_ID
	 * @return true if it's allowed
	 */
	private boolean isGenerateForVendor(final int C_BPartner_ID)
	{
		
		boolean retVal = false;
		
		// No filter group was set => generate for all vendors
		if (p_C_BP_Group_ID <= 0) {
			retVal = true;
		} else  if (m_excludedVendors.contains(C_BPartner_ID)) {
			retVal = false;
		} else {
			//
			retVal = 
					new Query(
							getCtx(), 
							MBPartner.Table_Name, 
							"C_BPartner_ID=? AND C_BP_Group_ID=?", 
							get_TrxName()
					)
					.setParameters(new Object[]{C_BPartner_ID, p_C_BP_Group_ID})
					.match();
			
			if (!retVal) {
				m_excludedVendors.add(C_BPartner_ID);
			}
		}
		
		return retVal;
	}
	
	private final List<Integer> m_excludedVendors = new ArrayList<Integer>();
	
}	//	RequisitionPOCreate
