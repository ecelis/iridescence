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
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              M_Movement (323)
 *  Document Types:     MMM
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Movement.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_Movement extends Doc
{
	/** Generar asiento contable */
	boolean post = true;

	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_Movement (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MMovement.class, rs, DOCTYPE_MatMovement, trxName);
	}   //  Doc_Movement

	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		MMovement move = (MMovement)getPO();
		setC_Currency_ID(NO_CURRENCY);
		setDateDoc (move.getDateReceived()==null?move.getMovementDate():move.getDateReceived());//Por cambio de mes Card #1236
		setDateAcct(move.getDateReceived()==null?move.getMovementDate():move.getDateReceived());//Por cambio de mes Card #1236
		
		// Si controla existencias se genera al asiento contable por organizacion del documento
		if(move.getPost()==null){
			post = MEXMEMejoras.isControlaExistencias(move.getAD_Client_ID(), move.getAD_Org_ID(), null);
			
			final MMovement moveSub = new MMovement(getCtx(), move.get_ID(), null);
			moveSub.set_TrxName(null);
			moveSub.setPost(post?"Y":"N");
			moveSub.save();
			
		} else {
			post = move.getPost().equals("Y");
			p_PostMovement = move.getPost().equals(Doc.STATUS_Unposted)?false:true;
		}
		
		//	Contained Objects
		p_lines = loadLines(move);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load Invoice Line
	 *	@param move move
	 *  @return document lines (DocLine_Material)
	 */
	private DocLine[] loadLines(final MMovement move){
		
		final ArrayList<DocLine> list = new ArrayList<DocLine>();
		final MMovementLine[] lines = move.getLines(false);
		for (int i = 0; i < lines.length; i++){
			final MMovementLine line = lines[i];
			
			if(line.getMovementQty().compareTo(Env.ZERO)!=0){
				final DocLine docLine = new DocLine (line, this);
				docLine.setQty(line.getMovementQty(), false);
				log.fine(docLine.toString());
				list.add (docLine);
			}
		}
		
		//Expert : Lama .- OrgOrx del header
		OrgTrx = move.getAD_OrgTrx_ID(); //recibe
		
		//	Return Array
		final DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	/**
	 *  Get Balance
	 *  @return balance (ZERO) - always balanced
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance

	private int OrgTrx = 0; //Expert : Lama

	/**
	 *  Create Facts (the accounting logic) for
	 *  MMM.
	 *  <pre>
	 *  Movement
	 *      Inventory       DR      CR
	 *      InventoryTo     DR      CR
	 *  </pre>
	 *  @param as account schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID(as.getC_Currency_ID());

		//  Line pointers
		FactLine dr = null;
		FactLine cr = null;

		//
		for (int i = 0; i < p_lines.length; i++)
		{
			DocLine line = p_lines[i];
			
			// Asientos solo si controla existencias o si es un servicio
			if(p_PostMovement || !line.getProduct().isStocked()){
				
				BigDecimal costs = line.getProductCosts(as, line.getAD_Org_ID(), false);
				if (costs == null || costs.signum() == 0)	//	zero costs OK
				{
					MProduct product = line.getProduct();
					if (post && product.isStocked())
					{
						//					Please configure the initial product cost of the tab "Purchases" for maintaining product.
						//					Favor de configurar el costo inicial del producto en la pestaña "compras" del mantenimiento de producto.
						p_Error = Utilerias.getMsg(as.getCtx(), "error.costo.mayor0") +" " + product.getName();
						//					p_Error = "No Costs for " + line.getProduct().getName();
						log.log(Level.WARNING, p_Error);
						return null;
					}
					else	//	ignore service
						continue;
				} else {
					costs = ((MMovementLine)line.getPOLine()).getCostTransaction(as
							, line.getProductCost().getQty()
							, costs
							, getTrxName());
				
				}
				//			if (costs == null || costs.signum() == 0)
				//			{
				//				p_Error = Utilerias.getMsg(as.getCtx(), "error.costo.mayor0") +" " + line.getProduct().getName();
				////				p_Error = "No Costs for " + line.getProduct().getName();
				//				return null;
				//			}


				//  ** Inventory       DR      CR
				dr = fact.createLine(line,
						line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
						as.getC_Currency_ID(), costs.negate());		//	from (-) CR
				if (dr == null)
					continue;
				dr.setM_Locator_ID(line.getM_Locator_ID());
				dr.setQty(line.getQty().negate());	//	outgoing

				//  ** InventoryTo     DR      CR
				cr = fact.createLine(line,
						line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
						as.getC_Currency_ID(), costs);			//	to (+) DR
				if (cr == null)
					continue;
				cr.setM_Locator_ID(line.getM_LocatorTo_ID());
				cr.setQty(line.getQty());
				cr.setAD_OrgTrx_ID(OrgTrx);	//Expert : Lama  .- OrgTrx que recibe (+)

				//	Only for between-org movements
				if (dr.getAD_Org_ID() != cr.getAD_Org_ID())
				{
//					String costingLevel = as.getCostingLevel();
//
//					// Expert: #Post,Cost And Price
//					MProductCategoryAcct pca = MProductCategoryAcct.getAcct(
//							line.getProduct(), as);
//
//					if (pca.getCostingLevelDefault() != null)
//						costingLevel = pca.getCostingLevelDefault();
					if (!MAcctSchema.COSTINGLEVEL_Organization.equals(line.getProduct().getCostingLevel(as)))
						continue;
					//
					String description = line.getDescription();
					if (description == null)
						description = "";
					//	Cost Detail From
					MCostDetail.createMovement(as, dr.getAD_Org_ID(), 	//	locator org
							line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
							line.get_ID(), 0,
							costs.negate(), line.getQty().negate(), true,
							description + "(|->)", getTrxName());
					//	Cost Detail To
					MCostDetail.createMovement(as, cr.getAD_Org_ID(),	//	locator org 
							line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
							line.get_ID(), 0,
							costs, line.getQty(), false,
							description + "(|<-)", getTrxName());
				}
			}
		} // fin for

		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact
}   //  Doc_Movement
