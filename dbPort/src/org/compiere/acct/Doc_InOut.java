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

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClientInfo;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.model.X_AD_ClientInfo;
import org.compiere.model.X_M_Cost;
import org.compiere.model.X_M_InOut;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 *  Post Shipment/Receipt Documents.
 *  <pre>
 *  Table:              M_InOut (319)
 *  Document Types:     MMS, MMR
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_InOut.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_InOut extends Doc
{
	/** Generar asiento contable */
	boolean post = true;
	private String movementType = null;
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_InOut (MAcctSchema[] ass, ResultSet rs, String trxName){
		super (ass, MInOut.class, rs, null, trxName);
		movementType = null;
	}   //  DocInOut


	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{

		MInOut inout = (MInOut)getPO();

		setC_Currency_ID(NO_CURRENCY);
		setDateDoc (inout.getMovementDate());
		movementType = inout.getMovementType();

		// Si controla existencias se genera al asiento contable por organizacion del documento
		if(inout.getPost()==null){
			post = MEXMEMejoras.isControlaExistencias(inout.getAD_Client_ID(), inout.getAD_Org_ID(), null);

			// Evitar bloqueo
			final MInOut mInoutSub = new MInOut(getCtx(), inout.get_ID(), null);
			mInoutSub.set_TrxName(null);
			mInoutSub.setPost(post?"Y":"N");
			mInoutSub.save();

		} else {
			post = inout.getPost().equals("Y");
			p_PostMovement = inout.getPost().equals(Doc.STATUS_Unposted)?false:true;
		}

		//
		m_MatchRequirementR = X_AD_ClientInfo.MATCHREQUIREMENTR_None;
		//Alejandro. Transaccion de MMS, se asigna el isSOtrx.
		if(getDocumentType().equalsIgnoreCase(DOCTYPE_MatShipment)){
			inout.setIsSOTrx(true);
		}
		if (!inout.isSOTrx())
		{
			m_MatchRequirementR =
					MClientInfo.get (getCtx(), inout.getAD_Client_ID()).getMatchRequirementR();

			String mr = inout.getMatchRequirementR();

			if (mr == null) {
				inout.setMatchRequirementR(m_MatchRequirementR);
			} else {
				m_MatchRequirementR = mr;
			}
		}

		//	Contained Objects
		p_lines = loadLines(inout);
		log.fine("Lines=" + p_lines.length);

		if (!post || (m_matchProblem == null || m_matchProblem.length() == 0)) {
			return null;
		}

		return m_matchProblem.substring(1).trim();

	}   //  loadDocumentDetails

	/** Match Requirement		*/
	private String	m_MatchRequirementR = null;
	/** Match Problem Info	*/
	private String	m_matchProblem = "";

	/**
	 *	Load Invoice Line
	 *	@param inout shipment/receipt
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MInOut inout)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MInOutLine[] lines = inout.getLines(false);

		for (MInOutLine line: lines)//(int i = 0; i < lines.length; i++)
		{
//			MInOutLine line = lines[i];

			if (line.isDescription()
				|| line.getM_Product_ID() == 0
				|| line.getMovementQty().signum() == 0)
			{
				log.finer("Ignored: " + line);
				continue;
			}

			//	PO Matching
			if (m_MatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_PurchaseOrder)
				|| m_MatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_PurchaseOrderAndInvoice))
			{
				BigDecimal poDiff = line.getMatchPODifference();
				if (poDiff.signum() != 0)
					m_matchProblem += "; Line=" + line.getLine()
						+ " PO Match diff=" + poDiff;
				else if (!line.isMatchPOPosted())
					m_matchProblem += "; PO Match not posted for Line=" + line.getLine();
			}
			//	Inv Matching
			else if (m_MatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_Invoice)
				|| m_MatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_PurchaseOrderAndInvoice))
			{
				BigDecimal invDiff = line.getMatchInvDifference();
				if (invDiff.signum() != 0)
					m_matchProblem += "; Line=" + line.getLine()
						+ " PO Match diff=" + invDiff;
				else if (!line.isMatchInvPosted())
					m_matchProblem += "; Inv Match not posted for Line=" + line.getLine();
			}

			DocLine docLine = new DocLine (line, this);
			BigDecimal Qty = line.getMovementQty();
			docLine.setQty (Qty, getDocumentType().equals(DOCTYPE_MatShipment));    //  sets Trx and Storage Qty
			//
			log.fine(docLine.toString());
			list.add (docLine);
		}//finfor

		//	Return Array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	/**
	 *  Get Balance
	 *  @return Zero (always balanced)
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  MMS, MMR.
	 *  <pre>
	 *  Shipment
	 *      CoGS (RevOrg)   DR
	 *      Inventory               CR
	 *  Shipment of Project Issue
	 *      CoGS            DR
	 *      Project                 CR
	 *  Receipt
	 *      Inventory       DR
	 *      NotInvoicedReceipt      CR
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID (as.getC_Currency_ID());

		//  Line pointers
		FactLine dr = null;
		FactLine cr = null;

		//  *** Sales - Shipment
		if (getDocumentType().equals(DOCTYPE_MatShipment))
		{
			for (DocLine line: p_lines)//(int i = 0; i < p_lines.length; i++)
			{
//				DocLine line = p_lines[i];

				// Asientos solo si controla existencias o si es un servicio
				if(post || !line.getProduct().isStocked()){

					BigDecimal costs =
							line.getProductCosts(as, line.getAD_Org_ID(), false);

					if (costs == null || costs.signum() == 0){	//	zero costs OK

						MProduct product = line.getProduct();

						if (post && product.isStocked())
						{
							// Please configure the initial product cost of the tab "Purchases" for maintaining product.
							// Favor de configurar el costo inicial del producto en la pestaña "compras" del mantenimiento de producto.
							p_Error =
									Utilerias.getMsg(as.getCtx(), "error.costo.mayor0")
									+ ' ' + product.getName();
							// p_Error = "No Costs for " + line.getProduct().getName();
							log.log(Level.WARNING, p_Error);
							return null;
						}
						else	//	ignore service
							continue;
					}
					
					// Card 1444 El costo debe ser el de M_Transaction o si se cancela es el de la salida
					// si es servicio mantiene el costo obtenido con anterioridad
					if(line.getPOLine()!=null && line.getPOLine() instanceof MInOutLine){
						log.log(Level.INFO, "Existe una linea de referencia");
						MInOutLine mInOutLine = ((MInOutLine)line.getPOLine());
						if(mInOutLine.getProduct().isItem()){
							log.log(Level.INFO, "Es un producto");
							// Costo por unidad minima
							costs = mInOutLine.getTransactionalCost(as
									, X_M_InOut.MOVEMENTTYPE_CustomerReturns.equals(movementType.trim()));
							log.log(Level.INFO, "El Costo:"+String.valueOf(costs));
							costs = costs.multiply(line.getProductCost().getQty());
							log.log(Level.INFO, "Costo multiplicado por la cantidad:"+String.valueOf(costs));
						}
					}
					
					
					if(isSOTrx() && X_M_InOut.MOVEMENTTYPE_CustomerReturns.equals(movementType.trim())){
						if(costs.signum()>0)
							costs = costs.negate();
						log.log(Level.INFO, "El Costo negativo:"+String.valueOf(costs));
					}
					
					log.log(Level.INFO, "El Costo del asiento :"+String.valueOf(costs));
					//  CoGS            DR
					dr =
							fact.createLine(
									line,
									line.getAccount(ProductCost.ACCTTYPE_P_Cogs, as),
									as.getC_Currency_ID(),
									costs,
									null
							);

					if (dr == null)
					{
						p_Error = "FactLine DR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}

					dr.setM_Locator_ID(line.getM_Locator_ID());
					dr.setLocationFromLocator(line.getM_Locator_ID(), true);    //  from Loc
					dr.setLocationFromBPartner(getC_BPartner_Location_ID(), false);  //  to Loc
					dr.setAD_Org_ID(line.getOrder_Org_ID());		//	Revenue X-Org
					dr.setQty(line.getQty().negate());

					//  Inventory               CR
					cr =
							fact.createLine(
									line,
									line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
									as.getC_Currency_ID(),
									null,
									costs
							);

					if (cr == null)
					{
						p_Error = "FactLine CR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}

					cr.setM_Locator_ID(line.getM_Locator_ID());
					cr.setLocationFromLocator(line.getM_Locator_ID(), true);    // from Loc
					cr.setLocationFromBPartner(getC_BPartner_Location_ID(), false);  // to Loc

					//Comentado por el card 1444
//					if (line.getM_Product_ID() != 0)
//					{

						// Si es de venta, pero es una devolución la cantidad debe ser positiva
//						BigDecimal qtyPost = line.getQty();
//						if(isSOTrx()
//								&& X_C_DocType.DOCBASETYPE_MaterialDelivery.equals(MDocType.get(getCtx(), getC_DocType_ID()).getDocBaseType())
//								&& X_M_InOut.MOVEMENTTYPE_CustomerReturns.equals(movementType.trim())){
//							qtyPost = line.getQty().abs();
//						}

//						MCostDetail.createShipment(as
//								, line.getAD_Org_ID()
//								, line.getM_Product_ID()
//								, line.getM_AttributeSetInstance_ID()
//								, line.get_ID()
//								, 0
//								, costs
//								, qtyPost
//								, line.getDescription()
//								, true
//								, getTrxName());
//					}

				}
			}	//	for all lines
			updateProductInfo(as.getC_AcctSchema_ID());     //  only for SO!
		}	//	Shipment

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//  *** Purchasing - Receipt
		else if (getDocumentType().equals(DOCTYPE_MatReceipt))
		{
			for (int i = 0; i < p_lines.length; i++)
			{
				DocLine line = p_lines[i];

				if(post || !line.getProduct().isStocked()){

					MProduct product = line.getProduct();

					// non-zero costs, no necesario el costo
					BigDecimal costs =
							line.getProductCosts(as, line.getAD_Org_ID(), false, false);

					if (costs == null || costs.signum() == 0)
					{
						if (post && product.isStocked())
						{
							p_Error = Utilerias.getMsg(as.getCtx(), "error.costo.mayor0") +" " + product.getName();
							//p_Error = "Resubmit - No Costs for " + product.getName();
							log.log(Level.WARNING, p_Error);
							return null;
						}
						else	//	ignore service
							continue;
					}

					/* sea el precio de compra el que se utilice para generar el asiento contable,
					 afectando a la cuenta INVENTARIOS. OJO, este requerimiento solo aplica para productos
					 que se contabilicen usando el método de costo promedio (no para productos con costo estandar). */
					final BigDecimal cost_price = X_M_Cost.COSTINGMETHOD_AveragePO.equals(product.getCostingMethod(as))
							?line.getPriceActual ().multiply(line.getQty()):costs;//Card #1399 Posteo de Costo Promedio en Recepción/Devolución de Material
					
							
					//  Inventory/Asset			DR
					MAccount assets = line.getAccount(ProductCost.ACCTTYPE_P_Asset, as);

					if (product.isService()) {
						assets = line.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
					}

					dr = fact.createLine(line
							, assets
							, as.getC_Currency_ID()
							, cost_price
							, null);

					if (dr == null)
					{
						p_Error = "DR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}

					dr.setM_Locator_ID(line.getM_Locator_ID());
					dr.setLocationFromBPartner(getC_BPartner_Location_ID(), true);   // from Loc
					dr.setLocationFromLocator(line.getM_Locator_ID(), false);   // to Loc

					//  NotInvoicedReceipt				CR
					cr =
							fact.createLine(
									line,
									getAccount(Doc.ACCTTYPE_NotInvoicedReceipts, as),
									as.getC_Currency_ID(),
									null,
									cost_price
							);

					if (cr == null)
					{
						p_Error = "CR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}

					cr.setM_Locator_ID(line.getM_Locator_ID());
					cr.setLocationFromBPartner(getC_BPartner_Location_ID(), true);   //  from Loc
					cr.setLocationFromLocator(line.getM_Locator_ID(), false);   //  to Loc
					cr.setQty(line.getQty().negate());
				}// fin controla existencias

			}
		}	//	Receipt
		else
		{
			p_Error = "DocumentType unknown: " + getDocumentType();
			log.log(Level.SEVERE, p_Error);
			return null;
		}
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact


	/**
	 *  Update Sales Order Costing Product Info (old).
	 *  Purchase side handeled in Invoice Matching.
	 *  <br>
	 *  decrease average cumulatives
	 *  @param C_AcctSchema_ID accounting schema
	 *  @deprecated old costing
	 */
	private void updateProductInfo (int C_AcctSchema_ID)
	{
		log.fine("M_InOut_ID=" + get_ID());
		//	Old Model
		StringBuffer sql = new StringBuffer(
			"UPDATE M_Product_Costing pc "
			+ "SET (CostAverageCumQty, CostAverageCumAmt)="
			+ "(SELECT CostAverageCumQty - SUM(il.MovementQty),"
			+ " CostAverageCumAmt - SUM(il.MovementQty*CurrentCostPrice) "
			+ "FROM M_InOutLine il "
			+ "WHERE pc.M_Product_ID=il.M_Product_ID"
			+ " AND il.M_InOut_ID=").append(get_ID()).append(") ")
			.append("WHERE EXISTS (SELECT * "
			+ "FROM M_InOutLine il "
			+ "WHERE pc.M_Product_ID=il.M_Product_ID"
			+ " AND il.M_InOut_ID=").append(get_ID()).append(")");
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_Costing - Updated=" + no);
		//
	}   //  updateProductInfo

}   //  Doc_InOut
