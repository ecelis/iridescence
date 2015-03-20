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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Compiere;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * Product Cost Model
 * 
 * @author Jorg Janke
 * @version $Id: MCost.java,v 1.6 2006/07/30 00:51:02 jjanke Exp $
 */
public class MCost extends X_M_Cost {
	/** serialVersionUID */
	private static final long serialVersionUID = -1600364869788956542L;

	private final static CLogger logger = CLogger.getCLogger(MCost.class);
	private boolean isNewRecord = false;

	/**
	 * Retrieve/Calculate Current Cost Price
	 * 
	 * @param product
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            real asi
	 * @param as
	 *            accounting schema
	 * @param AD_Org_ID
	 *            real org
	 * @param costingMethod
	 *            AcctSchema.COSTINGMETHOD_*
	 * @param qty
	 *            qty
	 * @param C_OrderLine_ID
	 *            optional order line
	 * @param zeroCostsOK
	 *            zero/no costs are OK
	 * @param trxName
	 *            trx
	 * @return current cost price or null
	 */
	public static BigDecimal getCurrentCost(final MProduct product
			, int M_AttributeSetInstance_ID
			, final MAcctSchema as
			, int AD_Org_ID
			, String costingMethod
			, final BigDecimal qty
			, final int C_OrderLine_ID
			, final boolean zeroCostsOK
			, final boolean createUpdateCosts
			, final String trxName) {
//		String CostingLevel = as.getCostingLevel();
//
//		// Expert: Proyecto #102 Posteo,Costos y precios
//		// Si no encuentra el costo en cliente lo busca en system
//		final MProductCategoryAcct pca = MProductCategoryAcct.getAcct(product,
//				as);
//
//		if (pca == null) {
//			throw new IllegalStateException(Utilerias.getAppMsg(Env.getCtx(),
//					"msj.acctCategory", product.getM_Product_Category()
//							.getName(), as.getName()));
//		}
//
//		// Costing Level
//		if (pca.getCostingLevelDefault() != null) {
//			CostingLevel = pca.getCostingLevelDefault();
//		}
		
		final String CostingLevel = product.getCostingLevel(as);
		
		if (X_C_AcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
			AD_Org_ID = 0;
			M_AttributeSetInstance_ID = 0;
			
		} else if (X_C_AcctSchema.COSTINGLEVEL_Organization
				.equals(CostingLevel)) {
			M_AttributeSetInstance_ID = 0;
			
		} else if (X_C_AcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel)) {
			AD_Org_ID = 0;
		}
		// Costing Method
		if (costingMethod == null) {
			costingMethod = product.getCostingMethod(as);
			if (costingMethod == null) {
				throw new IllegalArgumentException("No Costing Method");
			}
		}

		// Create/Update Costs
		if(createUpdateCosts) {
			MCostDetail.processProduct(product, trxName);
		}

		return getCurrentCost(product
				, M_AttributeSetInstance_ID
				, as
				, AD_Org_ID
				, as.getM_CostType_ID()
				, costingMethod
				, qty
				, C_OrderLine_ID
				, zeroCostsOK
				//, costsStd
				, trxName);
	} // getCurrentCost

	
	/**
	 * Get Current Cost Price for Costing Level
	 * 
	 * @param product
	 *            product
	 * @param M_ASI_ID
	 *            costing level asi
	 * @param Org_ID
	 *            costing level org
	 * @param M_CostType_ID
	 *            cost type
	 * @param as
	 *            AcctSchema
	 * @param costingMethod
	 *            method
	 * @param qty
	 *            quantity
	 * @param C_OrderLine_ID
	 *            optional order line
	 * @param zeroCostsOK
	 *            zero/no costs are OK
	 * @param trxName
	 *            trx
	 * @return cost price or null
	 */
	private static BigDecimal getCurrentCost(final MProduct product,
			final int M_ASI_ID, final MAcctSchema as
			, final int Org_ID,
			final int M_CostType_ID, final String costingMethod,
			final BigDecimal qty, final int C_OrderLine_ID,
			final boolean zeroCostsOK, /*final boolean codstsStd,*/
			final String trxName) {
		
//		BigDecimal currentCostPrice = null;
		String costElementType = null;
		BigDecimal percent = null;
		//
		BigDecimal materialCostEach = Env.ZERO;
		BigDecimal otherCostEach = Env.ZERO;
		BigDecimal percentage = Env.ZERO;
		int count = 0;
		//
		final StringBuilder sql = new  StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT COALESCE(SUM(c.CurrentCostPrice),0), ce.CostElementType, ce.CostingMethod ")// 1..3
				.append("  , c.Percent, c.M_CostElement_ID  ") // 4..5
				.append("  , COALESCE(SUM(c.CurrentCostPriceLL),0) ") // 6
				.append(" FROM M_Cost c ")
				.append("  LEFT OUTER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID)  ")
				.append(" WHERE c.AD_Client_ID = ?   ")// #1
				.append("  AND c.AD_Org_ID = ?       ") // #2
				.append("  AND c.M_Product_ID = ?    ") // #3
				.append("  AND (c.M_AttributeSetInstance_ID=? OR c.M_AttributeSetInstance_ID=0) ") // #4
				.append("  AND c.M_CostType_ID = ?   ")//#5
				.append("  AND c.C_AcctSchema_ID = ? ") // #6
				.append("  AND (ce.CostingMethod IS NULL OR ce.CostingMethod=?)  ") // #7
				.append(" GROUP BY ce.CostElementType, ce.CostingMethod, c.Percent, c.M_CostElement_ID ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, as.getAD_Client_ID());
			pstmt.setInt(2, Org_ID);
			pstmt.setInt(3, product.getM_Product_ID());
			pstmt.setInt(4, M_ASI_ID);// setAttributeSetInstance
			pstmt.setInt(5, M_CostType_ID);
			pstmt.setInt(6, as.getC_AcctSchema_ID());
			pstmt.setString(7, costingMethod);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				BigDecimal currentCostPrice = rs.getBigDecimal(1);
				BigDecimal currentCostPriceLL = rs.getBigDecimal(6);
				
				costElementType = rs.getString(2);
				final String cm = rs.getString(3);
				percent = rs.getBigDecimal(4);
				//
				s_log.finest("CurrentCostPrice=" + currentCostPrice
						+ ", CostElementType=" + costElementType
						+ ", CostingMethod=" + cm + ", Percent=" + percent);
				//
				if (currentCostPrice.signum() != 0 || currentCostPriceLL.signum() != 0){
					if (cm != null) {
						materialCostEach = materialCostEach.add(currentCostPrice).add(currentCostPriceLL);
					} else {
						otherCostEach = otherCostEach.add(currentCostPrice).add(currentCostPriceLL);
					}
				}
				if (percent != null && percent.signum() != 0) {
					percentage = percentage.add(percent);
				}
				count++;
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		if (count > 1) {
			s_log.finest("MaterialCost=" + materialCostEach + ", OtherCosts="
					+ otherCostEach + ", Percentage=" + percentage);
		}

		//	Seed Initial Costs
		if (materialCostEach.signum() == 0){		//	no costs
			if (zeroCostsOK){
				return Env.ZERO;
			}
			
			materialCostEach = getSeedCosts(product, M_ASI_ID,
				as, Org_ID, costingMethod, C_OrderLine_ID);
		}
		
		if (materialCostEach == null){
			return null;
		}

		// Material Costs
		BigDecimal materialCost = materialCostEach.multiply(qty);
		// Standard costs - just Material Costs
		if (X_M_CostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod)) {
			s_log.finer("MaterialCosts = " + materialCost);
			return materialCost;
		}
		
		if (X_M_CostElement.COSTINGMETHOD_Fifo.equals(costingMethod)
				|| X_M_CostElement.COSTINGMETHOD_Lifo.equals(costingMethod)) {
			final MCostElement ce = MCostElement.getMaterialCostElement(as,
					costingMethod);
			materialCost = MCostQueue.getCosts(product, M_ASI_ID, as, Org_ID,
					ce, qty, trxName);
		}

		// Other Costs
		final BigDecimal otherCost = otherCostEach.multiply(qty);

		// Costs
		BigDecimal costs = otherCost.add(materialCost);
		if (costs.signum() == 0) {
			return null;
		}

		s_log.finer("Sum Costs = " + costs);
		final int precision = as.getCostingPrecision();
		if (percentage.signum() == 0) // no percentages
		{
			if (costs.scale() > precision) {
				costs = costs.setScale(precision, BigDecimal.ROUND_HALF_UP);
			}
			return costs;
		}
		
		//
		BigDecimal percentCost = costs.multiply(percentage);
		percentCost = percentCost.divide(Env.ONEHUNDRED, precision,BigDecimal.ROUND_HALF_UP);
		costs = costs.add(percentCost);
		if (costs.scale() > precision) {
			costs = costs.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		
		s_log.finer("Sum Costs = " + costs + " (Add=" + percentCost + ")");
		return costs;
	} // getCurrentCost

	/**
	 * Get Seed Costs
	 * 
	 * @param product
	 *            product
	 * @param M_ASI_ID
	 *            costing level asi
	 * @param as
	 *            accounting schema
	 * @param Org_ID
	 *            costing level org
	 * @param costingMethod
	 *            costing method
	 * @param C_OrderLine_ID
	 *            optional order line
	 * @return price or null
	 */
	public static BigDecimal getSeedCosts(final MProduct product,
			final int M_ASI_ID, final MAcctSchema as, final int Org_ID,
			final String costingMethod, final int C_OrderLine_ID) {
		BigDecimal retValue = null;
		// Direct Data
		if (X_M_CostElement.COSTINGMETHOD_AverageInvoice.equals(costingMethod)) {
			retValue = calculateAverageInv(product, M_ASI_ID, as, Org_ID);
			
		} else if (X_M_CostElement.COSTINGMETHOD_AveragePO.equals(costingMethod)) {
			retValue = calculateAveragePO(product, M_ASI_ID, as, Org_ID);
			
		} else if (X_M_CostElement.COSTINGMETHOD_Fifo.equals(costingMethod)) {
			retValue = calculateFiFo(product, M_ASI_ID, as, Org_ID);
			
		} else if (X_M_CostElement.COSTINGMETHOD_Lifo.equals(costingMethod)) {
			retValue = calculateLiFo(product, M_ASI_ID, as, Org_ID);
			
		} else if (X_M_CostElement.COSTINGMETHOD_LastInvoice.equals(costingMethod)) {
			retValue = getLastInvoicePrice(product, M_ASI_ID, Org_ID,
					as.getC_Currency_ID());
			
		} else if (X_M_CostElement.COSTINGMETHOD_LastPOPrice.equals(costingMethod)) {
			if (C_OrderLine_ID != 0) {
				retValue = getPOPrice(product, C_OrderLine_ID,
						as.getC_Currency_ID());
			}
			
			if (retValue == null || retValue.signum() == 0) {
				retValue = getLastPOPrice(product, M_ASI_ID, Org_ID,
						as.getC_Currency_ID());
			}
			
		} else if (X_M_CostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod)) {
			// migrate old costs
			final MProductCosting pc = MProductCosting.get(product.getCtx(),
					product.getM_Product_ID(), as.getC_AcctSchema_ID(), null);
			if (pc != null) {
				retValue = pc.getCurrentCostPrice();
			}
			
		} else if (X_M_CostElement.COSTINGMETHOD_UserDefined.equals(costingMethod)) {
			;
		} else {
			throw new IllegalArgumentException("Unknown Costing Method = "
					+ costingMethod);
		}

		if (retValue != null && retValue.signum() != 0) {
			s_log.fine(product.getName() + ", CostingMethod=" + costingMethod
					+ " - " + retValue);
			return retValue;
		}

		// Look for exact Order Line
		if (C_OrderLine_ID != 0) {
			retValue = getPOPrice(product, C_OrderLine_ID,as.getC_Currency_ID());
			if (retValue != null && retValue.signum() != 0) {
				s_log.fine(product.getName() + ", PO - " + retValue);
				return retValue;
			}
		}

		// Look for Standard Costs first
		if (!X_M_CostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod)) {
			final MCostElement ce = MCostElement.getMaterialCostElement(as
					,X_M_CostElement.COSTINGMETHOD_StandardCosting);
			
			final MCost cost = get(product, M_ASI_ID, as, Org_ID,ce.getM_CostElement_ID(), null);
			if (cost != null && cost.getCurrentCostPrice().signum() != 0) {
				s_log.fine(product.getName() + ", Standard - " + retValue);
				return cost.getCurrentCostPrice();
			}
		}

		// We do not have a price
		// PO first
		if (X_M_CostElement.COSTINGMETHOD_AveragePO.equals(costingMethod)
				|| X_M_CostElement.COSTINGMETHOD_LastPOPrice.equals(costingMethod)
				|| X_M_CostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod)) {
			// try Last PO
			retValue = getLastPOPrice(product, M_ASI_ID, Org_ID,as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0)) {
				retValue = getLastPOPrice(product, M_ASI_ID, 0,
						as.getC_Currency_ID());
			}
			
			if (retValue != null && retValue.signum() != 0) {
				s_log.fine(product.getName() + ", LastPO = " + retValue);
				return retValue;
			}
			
		} else { // Inv first
			// try last Inv
			retValue = getLastInvoicePrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0)) {
				retValue = getLastInvoicePrice(product, M_ASI_ID, 0,
						as.getC_Currency_ID());
			}
			
			if (retValue != null && retValue.signum() != 0) {
				s_log.fine(product.getName() + ", LastInv = " + retValue);
				return retValue;
			}
		}

		// Still Nothing
		// Inv second
		if (X_M_CostElement.COSTINGMETHOD_AveragePO.equals(costingMethod)
				|| X_M_CostElement.COSTINGMETHOD_LastPOPrice.equals(costingMethod)
				|| X_M_CostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod)) {
			// try last Inv
			retValue = getLastInvoicePrice(product, M_ASI_ID, Org_ID,as.getC_Currency_ID());
			
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0)) {
				retValue = getLastInvoicePrice(product, M_ASI_ID, 0,as.getC_Currency_ID());
			}
			
			if (retValue != null && retValue.signum() != 0) {
				s_log.fine(product.getName() + ", LastInv = " + retValue);
				return retValue;
			}
			
		} else { // PO second
			// try Last PO
			retValue = getLastPOPrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0)) {
				retValue = getLastPOPrice(product, M_ASI_ID, 0,
						as.getC_Currency_ID());
			}
			
			if (retValue != null && retValue.signum() != 0) {
				s_log.fine(product.getName() + ", LastPO = " + retValue);
				return retValue;
			}
		}

		// Still nothing try ProductPO
		final MProductPO[] pos = MProductPO.getOfProduct(product.getCtx(),
				product.getM_Product_ID(), null);
		for (final MProductPO po : pos) {
			BigDecimal price = po.getPricePO();
			if (price == null || price.signum() == 0) {
				price = po.getPriceList();
			}
			if (price != null && price.signum() != 0) {
				price = MConversionRate.convert(product.getCtx(), price,
						po.getC_Currency_ID(), as.getC_Currency_ID(),
						as.getAD_Client_ID(), Org_ID);

				// Version adempiere que no tenemos
				if (price != null && price.signum() != 0)
				{
					if (po.getC_UOM_ID() != product.getC_UOM_ID())
					{
						price = MUOMConversion.convertProductTo (Env.getCtx(),
								product.getM_Product_ID(),
								po.getC_UOM_ID(), price);
					}
				}

				if (price != null && price.signum() != 0) {
					retValue = price;
					s_log.fine(product.getName() + ", Product_PO = " + retValue);
					return retValue;
				}
			}
		}

		// Still nothing try Purchase Price List
		// ....

		s_log.fine(product.getName() + " = " + retValue);
		return retValue;
	} // getSeedCosts

	/**
	 * Still nothing try ProductPO
	 * 
	 * @param product
	 *            Producto
	 * @param as
	 *            Esquema contable
	 * @param Org_ID
	 *            Organizacion
	 * @return Costo
	 */
	public static BigDecimal costProductPO(final MProduct product,
			final MAcctSchema as, final int Org_ID) {
		BigDecimal retValue = null;
		// Still nothing try ProductPO
		final MProductPO[] pos = MProductPO.getOfProduct(product.getCtx(),
				product.getM_Product_ID(), as.getAD_Client_ID(), null);
		
		for (final MProductPO po : pos) {
			BigDecimal price = po.getPricePO();
			if (price == null || price.signum() == 0) {
				price = po.getPriceList();
			}
			
			if (price != null && price.signum() != 0) {
				price = MConversionRate.convert(product.getCtx(), price,
						po.getC_Currency_ID(), as.getC_Currency_ID(),
						as.getAD_Client_ID(), Org_ID);
				if (price != null && price.signum() != 0) {
					retValue = price;
					s_log.fine(product.getName() + ", Product_PO = " + retValue);
					return retValue;
				}
			}
		}
		return retValue;
	}

	/**
	 * Get Last Invoice Price in currency
	 * 
	 * @param product
	 *            product
	 * @param M_ASI_ID
	 *            attribute set instance
	 * @param AD_Org_ID
	 *            org
	 * @param C_Currency_ID
	 *            accounting currency
	 * @return last invoice price in currency
	 */
	public static BigDecimal getLastInvoicePrice(final MProduct product,
			final int M_ASI_ID, final int AD_Org_ID, final int C_Currency_ID) {
		BigDecimal retValue = null;
		
		final StringBuilder sql = new  StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT currencyConvert(il.PriceActual, i.C_Currency_ID, ?, i.DateAcct, i.C_ConversionType_ID, il.AD_Client_ID, il.AD_Org_ID) ")//#1
				.append(" FROM C_InvoiceLine il ")
				.append("  INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) ")
				.append(" WHERE i.AD_Client_ID = ? ")//#2
				.append("  AND il.M_Product_ID=?")//#3
				.append("  AND i.IsSOTrx='N' ");
		if (AD_Org_ID != 0) {
			sql.append(" AND il.AD_Org_ID=?");//#4
			
		} else if (M_ASI_ID != 0) {
			sql.append(" AND il.M_AttributeSetInstance_ID=? ");//#4
		}
		
		sql.append(" ORDER BY i.DateInvoiced DESC, il.Line DESC ");
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), product.get_TrxName());
			pstmt.setInt(1, C_Currency_ID);
			pstmt.setInt(2, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(3, product.getM_Product_ID());
			
			if (AD_Org_ID != 0) {
				pstmt.setInt(4, AD_Org_ID);
				
			} else if (M_ASI_ID != 0) {
				pstmt.setInt(4, M_ASI_ID);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getBigDecimal(1);
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
		}

		if (retValue != null) {
			s_log.finer(product.getName() + " = " + retValue);
			return retValue;
		}
		return null;
	} // getLastInvoicePrice

	/**
	 * Get Last PO Price in currency
	 * 
	 * @param product
	 *            product
	 * @param M_ASI_ID
	 *            attribute set instance
	 * @param AD_Org_ID
	 *            org
	 * @param C_Currency_ID
	 *            accounting currency
	 * @return last PO price in currency or null
	 */
	public static BigDecimal getLastPOPrice(final MProduct product,
			final int M_ASI_ID, final int AD_Org_ID, final int C_Currency_ID) {
		BigDecimal retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT currencyConvert(ol.PriceCost, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID) ")
				.append(" , currencyConvert(ol.PriceActual, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID) ")
				// ,ol.PriceCost,ol.PriceActual, ol.QtyOrdered, o.DateOrdered,
				// ol.Line
				.append(" FROM C_OrderLine ol      ")
				.append(" INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) ")
				.append(" WHERE ol.AD_Client_ID = ? ")//#3
				.append(" AND ol.M_Product_ID = ?   ")//#4
				.append(" AND o.IsSOTrx='N'         ");
		if (AD_Org_ID != 0) {
			sql.append(" AND ol.AD_Org_ID=? ");//#5
			
		} else if (M_ASI_ID != 0) {
			sql.append(" AND t.M_AttributeSetInstance_ID=? ");//#6
		}
		sql.append(" ORDER BY o.DateOrdered DESC, ol.Line DESC ");

		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), product.get_TrxName());
			pstmt.setInt(1, C_Currency_ID);
			pstmt.setInt(2, C_Currency_ID);
			pstmt.setInt(3, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(4, product.getM_Product_ID());
			if (AD_Org_ID != 0) {
				pstmt.setInt(5, AD_Org_ID);
				
			} else if (M_ASI_ID != 0) {
				pstmt.setInt(5, M_ASI_ID);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getBigDecimal(1);
				if (retValue == null || retValue.signum() == 0) {
					retValue = rs.getBigDecimal(2);
				}
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		if (retValue != null) {
			s_log.finer(product.getName() + " = " + retValue);
			return retValue;
		}
		return null;
	} // getLastPOPrice

	/**
	 * Get PO Price in currency
	 * 
	 * @param product
	 *            product
	 * @param C_OrderLine_ID
	 *            order line
	 * @param C_Currency_ID
	 *            accounting currency
	 * @return last PO price in currency or null
	 */
	public static BigDecimal getPOPrice(final MProduct product,
			final int C_OrderLine_ID, final int C_Currency_ID) {
		
		BigDecimal retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT currencyConvert(ol.PriceCost, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID),")
		.append("  currencyConvert(ol.PriceActual, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID) ")
		// ,ol.PriceCost,ol.PriceActual, ol.QtyOrdered, o.DateOrdered,ol.Line
		.append(" FROM C_OrderLine ol ")
		.append("  INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) ")
		.append(" WHERE ol.AD_Client_ID =?  ")//#3
		.append(" AND   ol.C_OrderLine_ID=? ")//#4
		.append(" AND   o.IsSOTrx='N' ");
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), product.get_TrxName());
			pstmt.setInt(1, C_Currency_ID);
			pstmt.setInt(2, C_Currency_ID);
			pstmt.setInt(3, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(4, C_OrderLine_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getBigDecimal(1);
				if (retValue == null || retValue.signum() == 0) {
					retValue = rs.getBigDecimal(2);
				}
			}
			
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		if (retValue != null) {
			s_log.finer(product.getName() + " = " + retValue);
			return retValue;
		}
		return null;
	} // getPOPrice

	/**************************************************************************
	 * Create costing for client. Handles Transaction if not in a transaction
	 * 
	 * @param client
	 *            client
	 */
	public static boolean create(final MClient client) {
		final MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(
				client.getCtx(), client.getAD_Client_ID());
		final String trxName = client.get_TrxName();
		String trxNameUsed = trxName;
		Trx trx = null;
		if (trxName == null) {
			trxNameUsed = Trx.createTrxName("Cost");
			trx = Trx.get(trxNameUsed, true);
		}
		boolean success = false;

		// For all Products
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT * ")
				.append(" FROM M_Product p ")
				.append(" WHERE AD_Client_ID = ? ")
				.append(" AND EXISTS ( SELECT * FROM M_CostDetail cd ")
				.append("              WHERE p.M_Product_ID = cd.M_Product_ID ")
				.append("              AND cd.AD_Client_ID = ? ")
				.append("              AND cd.Processed='N' ) ");
		// 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxNameUsed);
			pstmt.setInt(1, client.getAD_Client_ID());
			pstmt.setInt(2, client.getAD_Client_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MProduct product = new MProduct(client.getCtx(), rs,
						trxNameUsed);
				for (final MAcctSchema as : ass) {
					final BigDecimal cost = getCurrentCost(product, 0, as, 0,
							null, Env.ONE, 0, false, true, trxNameUsed); // create
																			// non-zero
																			// costs
					s_log.info(product.getName() + " = " + cost);
				}
			}

			if (success && trx != null) {
				trx.commit();
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			success = false;
		} finally {
			DB.close(rs, pstmt);
			Trx.close(trx, true);
		}
		return success;
	} // create

	/**
	 * Create standard Costing records for Product
	 * 
	 * @param product
	 *            product
	 */
	public static boolean createCost(final MProduct product,
			final BigDecimal amount) {
		return create(product, amount);
	}

	// public static void createCost (MProduct product){
	// create (product, Env.ONE);
	// }

	// protected static void create (MProduct product){
	// create (product, Env.ONE);
	// }

	protected static boolean create(final MProduct product, final BigDecimal amount) {
		s_log.config(product.getName());
		boolean successTest = false;
		// Cost Elements
		final List <MCostElement> ces = MCostElement.getCostElementsWithCostingMethods(product); 

		MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(product.getCtx(),
				Env.getAD_Client_ID(product.getCtx()), product.get_TrxName());
		MOrg[] orgs = null;

		final int M_ASI_ID = 0; // No Attribute
		for (MAcctSchema as : mass) {
			String cl = product.getCostingLevel(as);

			// Create Std Costing
			if (X_C_AcctSchema.COSTINGLEVEL_Client.equals(cl)) {

				for(MCostElement ce : ces) {

					final MCost cost = MCost.get(product, M_ASI_ID, as, 0,
							ce.getM_CostElement_ID(), null);
					if (cost.is_new()) {
						//														// precios
						if (cost.save()) {
							s_log.config("Std.Cost for " + product.getName()
									+ " - " + as.getName());
						} else {
							s_log.warning("Not created: Std.Cost for "
									+ product.getName() + " - " + as.getName());
						}
					}
				}
			} else if (X_C_AcctSchema.COSTINGLEVEL_Organization.equals(cl)) {
				if (orgs == null) {
					orgs = MOrg.getOfClient(product);
				}
				for (final MOrg org : orgs) {
					for(MCostElement ce : ces){
						final MCost cost = MCost.get(product, M_ASI_ID, as,
								org.getAD_Org_ID(), ce.getM_CostElement_ID(),
								null); 
						if (cost.is_new()) {
							// Crear el costo con costo 1
							cost.setCurrentCostPrice(amount); // Expert: Proyecto
							// #102
							// Posteo,costos y
							// precios
							if (cost.save()) {
								s_log.config("Std.Cost for " + product.getName()
										+ " - " + org.getName() + " - "
										+ as.getName());
							} else {
								s_log.warning("Not created: Std.Cost for "
										+ product.getName() + " - "
										+ org.getName() + " - " + as.getName());
							}
						}
					} // for all orgs
				}
			} else {
				s_log.warning("Not created: Std.Cost for " + product.getName()
						+ " - Costing Level on Batch/Lot");
				successTest = false;
			}
		} // accounting schema loop
		return successTest;
	} // create

	/**************************************************************************
	 * Calculate Average Invoice from Trx
	 * 
	 * @param product
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            optional asi
	 * @param as
	 *            acct schema
	 * @param AD_Org_ID
	 *            optonal org
	 * @return average costs or null
	 */
	public static BigDecimal calculateAverageInv(final MProduct product,
			final int M_AttributeSetInstance_ID, final MAcctSchema as,
			final int AD_Org_ID) {
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT t.MovementQty, mi.Qty, il.QtyInvoiced, il.PriceActual, ")
		.append("  i.C_Currency_ID, i.DateAcct, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID, t.M_Transaction_ID ")
		.append(" FROM M_Transaction t ")
		.append("  INNER JOIN M_MatchInv mi ON (t.M_InOutLine_ID=mi.M_InOutLine_ID) ")
		.append("  INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID) ")
		.append("  INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) ")
		.append(" WHERE t.AD_Client_ID=? ")//#1
		.append("  AND  t.M_Product_ID=? ");//#2
		if (AD_Org_ID != 0) {
			sql.append(" AND t.AD_Org_ID=? ");
		} else if (M_AttributeSetInstance_ID != 0) {
			sql.append(" AND t.M_AttributeSetInstance_ID=? ");
		}
		sql.append(" ORDER BY t.M_Transaction_ID");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal newStockQty = Env.ZERO;
		//
		BigDecimal newAverageAmt = Env.ZERO;
		final int oldTransaction_ID = 0;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(2, product.getM_Product_ID());
			if (AD_Org_ID != 0) {
				pstmt.setInt(3, AD_Org_ID);
			} else if (M_AttributeSetInstance_ID != 0) {
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final BigDecimal oldStockQty = newStockQty;
				final BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(10);
				if (M_Transaction_ID != oldTransaction_ID) {
					newStockQty = oldStockQty.add(movementQty);
				}
				M_Transaction_ID = oldTransaction_ID;
				//
				final BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null) {
					s_log.finer("Movement=" + movementQty + ", StockQty="
							+ newStockQty);
					continue;
				}
				// Assumption: everything is matched
				final BigDecimal price = rs.getBigDecimal(4);
				final int C_Currency_ID = rs.getInt(5);
				final Timestamp DateAcct = rs.getTimestamp(6);
				final int C_ConversionType_ID = rs.getInt(7);
				final int Client_ID = rs.getInt(8);
				final int Org_ID = rs.getInt(9);
				final BigDecimal cost = MConversionRate.convert(
						product.getCtx(), price, C_Currency_ID,
						as.getC_Currency_ID(), DateAcct, C_ConversionType_ID,
						Client_ID, Org_ID);
				//
				final BigDecimal oldAverageAmt = newAverageAmt;
				final BigDecimal averageCurrent = oldStockQty
						.multiply(oldAverageAmt);
				final BigDecimal averageIncrease = matchQty.multiply(cost);
				BigDecimal newAmt = averageCurrent.add(averageIncrease);
				newAmt = newAmt.setScale(as.getCostingPrecision(),
						BigDecimal.ROUND_HALF_UP);

				if (newStockQty.compareTo(Env.ZERO) != 0) {
					newAverageAmt = newAmt.divide(newStockQty,
							as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				}
				s_log.finer("Movement=" + movementQty + ", StockQty="
						+ newStockQty + ", Match=" + matchQty + ", Cost="
						+ cost + ", NewAvg=" + newAverageAmt);
			}
			// rs.close ();
			// pstmt.close ();
			// pstmt = null;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		if (newAverageAmt != null & newAverageAmt.signum() != 0) {
			s_log.finer(product.getName() + " = " + newAverageAmt);
			return newAverageAmt;
		}
		return null;
	} // calculateAverageInv

	/**
	 * Calculate Average PO
	 * 
	 * @param product
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            asi
	 * @param as
	 *            acct schema
	 * @param AD_Org_ID
	 *            org
	 * @return costs or null
	 */
	public static BigDecimal calculateAveragePO(final MProduct product,
			final int M_AttributeSetInstance_ID, final MAcctSchema as,
			final int AD_Org_ID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append("SELECT t.MovementQty, mp.Qty, ol.QtyOrdered, ol.PriceCost, ol.PriceActual,") // 1..5
				.append("  o.C_Currency_ID, o.DateAcct, o.C_ConversionType_ID, ") // 6..8
				.append("  o.AD_Client_ID, o.AD_Org_ID, t.M_Transaction_ID ") // 9..11
				.append(" FROM M_Transaction t ")
				.append("  INNER JOIN M_MatchPO mp ON (t.M_InOutLine_ID=mp.M_InOutLine_ID) ")
				.append("  INNER JOIN C_OrderLine ol ON (mp.C_OrderLine_ID=ol.C_OrderLine_ID) ")
				.append("  INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) ")
				.append(" WHERE t.AD_Client_ID=? ")
				.append(" AND   t.M_Product_ID=? ");
		if (AD_Org_ID != 0) {
			sql.append(" AND t.AD_Org_ID=? ");
		} else if (M_AttributeSetInstance_ID != 0) {
			sql.append(" AND t.M_AttributeSetInstance_ID=? ");
		}
		sql.append(" ORDER BY t.M_Transaction_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal newStockQty = Env.ZERO;
		//
		BigDecimal newAverageAmt = Env.ZERO;
		final int oldTransaction_ID = 0;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(2, product.getM_Product_ID());
			if (AD_Org_ID != 0) {
				pstmt.setInt(3, AD_Org_ID);
			} else if (M_AttributeSetInstance_ID != 0) {
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final BigDecimal oldStockQty = newStockQty;// 0 //50
				final BigDecimal movementQty = rs.getBigDecimal(1);// 50.00//250.00
				int M_Transaction_ID = rs.getInt(11);
				if (M_Transaction_ID != oldTransaction_ID) {
					newStockQty = oldStockQty.add(movementQty);// 50.00
				}
				M_Transaction_ID = oldTransaction_ID;
				//
				final BigDecimal matchQty = rs.getBigDecimal(2);// 50.00//250.00
				if (matchQty == null) {
					s_log.finer("Movement=" + movementQty + ", StockQty="
							+ newStockQty);
					continue;
				}
				// Assumption: everything is matched
				BigDecimal price = rs.getBigDecimal(4);
				if (price == null || price.signum() == 0) {
					price = rs.getBigDecimal(5);// 0.1000//0.4000 // Actual
				}
				final int C_Currency_ID = rs.getInt(6);
				final Timestamp DateAcct = rs.getTimestamp(7);
				final int C_ConversionType_ID = rs.getInt(8);
				final int Client_ID = rs.getInt(9);
				final int Org_ID = rs.getInt(10);
				final BigDecimal cost = MConversionRate.convert(
						product.getCtx(), price, C_Currency_ID,
						as.getC_Currency_ID(), DateAcct, C_ConversionType_ID,
						Client_ID, Org_ID);// 0.1000//0.4000
				//
				final BigDecimal oldAverageAmt = newAverageAmt;// 0//0.1000
				final BigDecimal averageCurrent = oldStockQty
						.multiply(oldAverageAmt);// 0//5.000000
				
				if (cost == null) {
					s_log.finer("Movement=" + movementQty + ", StockQty="
							+ newStockQty + " Cost " + cost);
					continue;
				}
				
				final BigDecimal averageIncrease = matchQty.multiply(cost);// 50.00*0.1
																			// =
																			// 5.000000//250.00*0.4000=100.000000
				BigDecimal newAmt = averageCurrent.add(averageIncrease);// 0+5.
																		// =
																		// 5.000000//5.000000+100.000000=105.000000
				newAmt = newAmt.setScale(as.getCostingPrecision(),
						BigDecimal.ROUND_HALF_UP);
				newAverageAmt = newAmt.divide(newStockQty,
						as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);// 5.0000/50
																			// =
																			// 0.1000
				s_log.finer("Movement=" + movementQty
						+ ", StockQty="
						+ newStockQty // 105/300=0.3500
						+ ", Match=" + matchQty + ", Cost=" + cost
						+ ", NewAvg=" + newAverageAmt);
			}
			// rs.close ();
			// pstmt.close ();
			// pstmt = null;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		if (newAverageAmt != null & newAverageAmt.signum() != 0) {
			s_log.finer(product.getName() + " = " + newAverageAmt);
			return newAverageAmt;
		}
		return null;
	} // calculateAveragePO

	/**
	 * Calculate FiFo Cost
	 * 
	 * @param product
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            asi
	 * @param as
	 *            acct schema
	 * @param AD_Org_ID
	 *            org
	 * @return costs or null
	 */
	public static BigDecimal calculateFiFo(final MProduct product,
			final int M_AttributeSetInstance_ID, final MAcctSchema as,
			final int AD_Org_ID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT t.MovementQty, mi.Qty, il.QtyInvoiced, il.PriceActual, ")
				.append("  i.C_Currency_ID, i.DateAcct, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID, t.M_Transaction_ID ")
				.append(" FROM M_Transaction t ")
				.append("  INNER JOIN M_MatchInv mi ON (t.M_InOutLine_ID=mi.M_InOutLine_ID) ")
				.append("  INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID) ")
				.append("  INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) ")
				.append(" WHERE t.AD_Client_ID=? ")//#1
				.append(" AND   t.M_Product_ID=? ");//#2
		if (AD_Org_ID != 0) {
			sql.append(" AND t.AD_Org_ID=? ");//#3
		} else if (M_AttributeSetInstance_ID != 0) {
			sql.append(" AND t.M_AttributeSetInstance_ID=? ");//#3
		}
		sql.append(" ORDER BY t.M_Transaction_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		final int oldTransaction_ID = 0;
		final ArrayList<QtyCost> fifo = new ArrayList<QtyCost>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(2, product.getM_Product_ID());
			if (AD_Org_ID != 0) {
				pstmt.setInt(3, AD_Org_ID);
			} else if (M_AttributeSetInstance_ID != 0) {
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(10);
				if (M_Transaction_ID == oldTransaction_ID) {
					continue; // assuming same price for receipt
				}
				M_Transaction_ID = oldTransaction_ID;
				//
				final BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null) // out (negative)
				{
					if (!fifo.isEmpty()) {
						QtyCost pp = fifo.get(0);
						pp.Qty = pp.Qty.add(movementQty);
						BigDecimal remainder = pp.Qty;
						if (remainder.signum() == 0) {
							fifo.remove(0);
						} else {
							while (remainder.signum() != 0) {
								if (fifo.size() == 1) // Last
								{
									pp.Cost = Env.ZERO;
									remainder = Env.ZERO;
								} else {
									fifo.remove(0);
									pp = fifo.get(0);
									pp.Qty = pp.Qty.add(movementQty);
									remainder = pp.Qty;
								}
							}
						}
					} else {
						final QtyCost pp = new QtyCost(movementQty, Env.ZERO);
						fifo.add(pp);
					}
					s_log.finer("Movement=" + movementQty + ", Size="
							+ fifo.size());
					continue;
				}
				// Assumption: everything is matched
				final BigDecimal price = rs.getBigDecimal(4);
				final int C_Currency_ID = rs.getInt(5);
				final Timestamp DateAcct = rs.getTimestamp(6);
				final int C_ConversionType_ID = rs.getInt(7);
				final int Client_ID = rs.getInt(8);
				final int Org_ID = rs.getInt(9);
				final BigDecimal cost = MConversionRate.convert(
						product.getCtx(), price, C_Currency_ID,
						as.getC_Currency_ID(), DateAcct, C_ConversionType_ID,
						Client_ID, Org_ID);

				// Add Stock
				boolean used = false;
				if (fifo.size() == 1) {
					final QtyCost pp = fifo.get(0);
					if (pp.Qty.signum() < 0) {
						pp.Qty = pp.Qty.add(movementQty);
						if (pp.Qty.signum() == 0) {
							fifo.remove(0);
						} else {
							pp.Cost = cost;
						}
						used = true;
					}

				}
				if (!used) {
					final QtyCost pp = new QtyCost(movementQty, cost);
					fifo.add(pp);
				}
				s_log.finer("Movement=" + movementQty + ", Size=" + fifo.size());
			}
			// rs.close ();
			// pstmt.close ();
			// pstmt = null;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		if (fifo.isEmpty()) {
			return null;
		}

		final QtyCost pp = fifo.get(0);
		s_log.finer(product.getName() + " = " + pp.Cost);
		return pp.Cost;
	} // calculateFiFo

	/**
	 * Calculate LiFo costs
	 * 
	 * @param product
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            asi
	 * @param as
	 *            acct schema
	 * @param AD_Org_ID
	 *            org
	 * @return costs or null
	 */
	public static BigDecimal calculateLiFo(final MProduct product,
			final int M_AttributeSetInstance_ID, final MAcctSchema as,
			final int AD_Org_ID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT t.MovementQty, mi.Qty, il.QtyInvoiced, il.PriceActual,")
		.append("  i.C_Currency_ID, i.DateAcct, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID, t.M_Transaction_ID ")
		.append(" FROM M_Transaction t ")
		.append("  INNER JOIN M_MatchInv mi ON (t.M_InOutLine_ID=mi.M_InOutLine_ID) ")
		.append("  INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID) ")
		.append("  INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) ")
		.append(" WHERE t.AD_Client_ID=? ")//#1
		.append(" AND   t.M_Product_ID=? "); //#2
		if (AD_Org_ID != 0) {
			sql.append(" AND t.AD_Org_ID=? ");//#3
		} else if (M_AttributeSetInstance_ID != 0) {
			sql.append(" AND t.M_AttributeSetInstance_ID=? ");//#3
		}
		// Starting point?
		sql.append(" ORDER BY t.M_Transaction_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		final int oldTransaction_ID = 0;
		final ArrayList<QtyCost> lifo = new ArrayList<QtyCost>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(product.getCtx()));
			pstmt.setInt(2, product.getM_Product_ID());
			if (AD_Org_ID != 0) {
				pstmt.setInt(3, AD_Org_ID);
			} else if (M_AttributeSetInstance_ID != 0) {
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(10);
				if (M_Transaction_ID == oldTransaction_ID) {
					continue; // assuming same price for receipt
				}
				M_Transaction_ID = oldTransaction_ID;
				//
				final BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null) // out (negative)
				{
					if (!lifo.isEmpty()) {
						QtyCost pp = lifo.get(lifo.size() - 1);
						pp.Qty = pp.Qty.add(movementQty);
						BigDecimal remainder = pp.Qty;
						if (remainder.signum() == 0) {
							lifo.remove(lifo.size() - 1);
						} else {
							while (remainder.signum() != 0) {
								if (lifo.size() == 1) // Last
								{
									pp.Cost = Env.ZERO;
									remainder = Env.ZERO;
								} else {
									lifo.remove(lifo.size() - 1);
									pp = lifo.get(lifo.size() - 1);
									pp.Qty = pp.Qty.add(movementQty);
									remainder = pp.Qty;
								}
							}
						}
					} else {
						final QtyCost pp = new QtyCost(movementQty, Env.ZERO);
						lifo.add(pp);
					}
					s_log.finer("Movement=" + movementQty + ", Size="
							+ lifo.size());
					continue;
				}
				// Assumption: everything is matched
				final BigDecimal price = rs.getBigDecimal(4);
				final int C_Currency_ID = rs.getInt(5);
				final Timestamp DateAcct = rs.getTimestamp(6);
				final int C_ConversionType_ID = rs.getInt(7);
				final int Client_ID = rs.getInt(8);
				final int Org_ID = rs.getInt(9);
				final BigDecimal cost = MConversionRate.convert(
						product.getCtx(), price, C_Currency_ID,
						as.getC_Currency_ID(), DateAcct, C_ConversionType_ID,
						Client_ID, Org_ID);
				//
				final QtyCost pp = new QtyCost(movementQty, cost);
				lifo.add(pp);
				s_log.finer("Movement=" + movementQty + ", Size=" + lifo.size());
			}
			// rs.close ();
			// pstmt.close ();
			// pstmt = null;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		if (lifo.isEmpty()) {
			return null;
		}

		final QtyCost pp = lifo.get(lifo.size() - 1);
		s_log.finer(product.getName() + " = " + pp.Cost);
		return pp.Cost;
	} // calculateLiFo

	/**************************************************************************
	 * MCost Qty-Cost Pair
	 */
	static class QtyCost {
		/**
		 * Constructor
		 * 
		 * @param qty
		 *            qty
		 * @param cost
		 *            cost
		 */
		public QtyCost(final BigDecimal qty, final BigDecimal cost) {
			Qty = qty;
			Cost = cost;
		}

		/** Qty */
		public BigDecimal Qty = null;
		/** Cost */
		public BigDecimal Cost = null;

		/**
		 * String Representation
		 * 
		 * @return info
		 */
		@Override
		public String toString() {
			final StringBuffer sb = new StringBuffer("Qty=").append(Qty)
					.append(",Cost=").append(Cost);
			return sb.toString();
		} // toString
	} // QtyCost

	/**
	 * Get/Create Cost Record. CostingLevel is not validated
	 * 
	 * @param product
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            costing level asi
	 * @param as
	 *            accounting schema
	 * @param AD_Org_ID
	 *            costing level org
	 * @param M_CostElement_ID
	 *            element
	 * @return cost price or null
	 */
	public static MCost get(final MProduct product,
			final int M_AttributeSetInstance_ID, final MAcctSchema as,
			final int AD_Org_ID, final int M_CostElement_ID,
			final String trxName) {
		
		// FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final StringBuilder whereClause = new StringBuilder(" AD_Client_ID=? ")
				.append("  AND AD_Org_ID=?")
				.append("  AND M_Product_ID = ? ")
				.append("  AND M_AttributeSetInstance_ID = ? ")
				.append("  AND M_CostType_ID = ? ")
				.append("  AND C_AcctSchema_ID = ? ")
				.append("  AND M_CostElement_ID = ? ");
		
		MCost cost = new Query(product.getCtx(), I_M_Cost.Table_Name, whereClause.toString(),trxName)
			.setParameters(as.getAD_Client_ID()
					, AD_Org_ID
					, product.getM_Product_ID()
					, M_AttributeSetInstance_ID
					, as.getM_CostType_ID()
					, as.getC_AcctSchema_ID()
					, M_CostElement_ID)
			.firstOnly();
		// FR: [ 2214883 ] - end -
		// New
		if (cost == null) {
			s_log.finer(" No se localizo el costo por lo que se crea para el producto: "+ product.getValue());
			cost = new MCost(product, M_AttributeSetInstance_ID, as, AD_Org_ID,
					M_CostElement_ID);
		}
		return cost;
	} // get

	/**
	 * Get Costs
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Client_ID
	 *            client
	 * @param AD_Org_ID
	 *            org
	 * @param M_Product_ID
	 *            product
	 * @param M_CostType_ID
	 *            cost type
	 * @param C_AcctSchema_ID
	 *            as
	 * @param M_CostElement_ID
	 *            cost element
	 * @param M_AttributeSetInstance_ID
	 *            asi
	 * @return cost or null
	 */
	public static MCost get(final Properties ctx, final int AD_Client_ID,
			final int AD_Org_ID, final int M_Product_ID,
			final int M_CostType_ID, final int C_AcctSchema_ID,
			final int M_CostElement_ID, final int M_AttributeSetInstance_ID) {
		return get(ctx, AD_Client_ID, AD_Org_ID, M_Product_ID, M_CostType_ID,
				C_AcctSchema_ID, M_CostElement_ID, M_AttributeSetInstance_ID,
				null, true);
	} // get

	public static MCost get(final Properties ctx, final int AD_Client_ID,
			final int AD_Org_ID, final int M_Product_ID,
			final int M_CostType_ID, final int C_AcctSchema_ID,
			final int M_CostElement_ID, final int M_AttributeSetInstance_ID,
			final String trxName, final boolean isActive) {

		final StringBuilder whereClause = new StringBuilder()
		.append(" AD_Client_ID=? ")
		.append(" AND AD_Org_ID = ?")
		.append(" AND M_Product_ID =? ")
		.append(" AND M_CostType_ID =? ")
		.append(" AND C_AcctSchema_ID =? ")
		.append(" AND M_CostElement_ID =? ")
		.append(" AND M_AttributeSetInstance_ID =? ");
		final Object[] params = new Object[] { AD_Client_ID, AD_Org_ID,
				M_Product_ID, M_CostType_ID, C_AcctSchema_ID, M_CostElement_ID,
				M_AttributeSetInstance_ID };
		MCost mcost = new Query(ctx, Table_Name, whereClause.toString(), trxName)
				.setOnlyActiveRecords(isActive).setParameters(params).firstOnly();
		return mcost;
	}

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MCost.class);

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param ignored
	 *            multi-key
	 * @param trxName
	 *            trx
	 */
	public MCost(final Properties ctx, final int ignored, final String trxName) {
		super(ctx, ignored, trxName);
		if (ignored == 0) {
			// setC_AcctSchema_ID (0);
			// setM_CostElement_ID (0);
			// setM_CostType_ID (0);
			// setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
			//
			setCurrentCostPrice(Env.ZERO);
			setFutureCostPrice(Env.ZERO);
			setCurrentQty(Env.ZERO);
			setCumulatedAmt(Env.ZERO);
			setCumulatedQty(Env.ZERO);
		} else {
			throw new IllegalArgumentException("Multi-Key");
		}
	} // MCost

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            trx
	 */
	public MCost(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
		m_manual = false;
	} // MCost

	/**
	 * Parent Constructor
	 * 
	 * @param product
	 *            Product
	 * @param M_AttributeSetInstance_ID
	 *            asi
	 * @param as
	 *            Acct Schema
	 * @param AD_Org_ID
	 *            org
	 * @param M_CostElement_ID
	 *            cost element
	 */
	public MCost(final MProduct product, final int M_AttributeSetInstance_ID,
			final MAcctSchema as, final int AD_Org_ID,
			final int M_CostElement_ID) {
		this(product.getCtx(), 0, product.get_TrxName());
		// setClientOrg(product.getAD_Client_ID(), AD_Org_ID);
		setClientOrg(as.getAD_Client_ID(), AD_Org_ID);// Cambio por nivel de
														// acceso del producto
		setC_AcctSchema_ID(as.getC_AcctSchema_ID());
		setM_CostType_ID(as.getM_CostType_ID());
		setM_Product_ID(product.getM_Product_ID());
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setM_CostElement_ID(M_CostElement_ID);
		//
		m_manual = false;
		isNewRecord = true;
	} // MCost

	/** Data is entered Manually */
	private boolean m_manual = true;

	/**
	 * Add Cumulative Amt/Qty and Current Qty
	 * 
	 * @param amt
	 *            amt
	 * @param qty
	 *            qty
	 */
	public void add(final BigDecimal amt, final BigDecimal qty) {
		setCumulatedAmt(getCumulatedAmt().add(amt));
		setCumulatedQty(getCumulatedQty().add(qty));
		setCurrentQty(getCurrentQty().add(qty));
	} // add

	/**
	 * Add Amt/Qty and calculate weighted average. ((OldAvg*OldQty)+(Price*Qty))
	 * / (OldQty+Qty)
	 * @param amtStd Costo estandar
	 * @param amt total amt (price * qty)
	 * @param qty qty
	 * @param isSOTrxMMR transacción que afecta el costo promedio 
     * @param qtyOnHand Cantidad a la mano 
	 */
	public void setWeightedAverage(final BigDecimal amtStd, final BigDecimal amt, final BigDecimal qty, final boolean isSOTrxMMR, final BigDecimal qtyOnHand) {
		log.warning(" MCost.WeightedAverage Product: " + getM_Product_ID());
		
		final BigDecimal newSum = amt; // is total already
		final BigDecimal oldSum = amtStd == null
				? getCurrentCostPrice().multiply(qtyOnHand==null?getCurrentQty():qtyOnHand)
				: amtStd.multiply(getCurrentQty());
		

		final BigDecimal sumAmt = oldSum.add(newSum);
		final BigDecimal sumQty = getCurrentQty().add(qty);
		
		// SOLO Cuando se haga una Recepcion de material del proveedor y Devolucion a proveedor y es costo promedio 
		if (sumQty.signum() != 0 && isSOTrxMMR) {//-1 (negative), 0 (zero), or 1 (positive)
			final BigDecimal cost = sumAmt.divide(sumQty, getPrecision(),BigDecimal.ROUND_HALF_UP);
			log.warning(" oldSum :" + oldSum + " newSum :" + newSum + " Cost :" + cost);
			
			if(cost.signum() < 0) {
				setCurrentCostPrice(Env.ZERO);
				// Como puede ser que el costo o la cantidad nueva sea mayor a lo que se tiene calculado
				// posiblemente el costo puede ser negativo, y como eso no seria correcto
				// se pondra en cero GC.
			} else {
				setCurrentCostPrice(cost);
			}
		}
		//
		setCumulatedAmt(getCumulatedAmt().add(amt));
		setCumulatedQty(getCumulatedQty().add(qty));
		setCurrentQty(getCurrentQty().add(qty));
	} // setWeightedAverage

	/**
	 * Get Costing Precision
	 * 
	 * @return precision (6)
	 */
	private int getPrecision() {
		final MAcctSchema as = MAcctSchema.get(getCtx(), getC_AcctSchema_ID());
		if (as != null) {
			return as.getCostingPrecision();
		}
		return 6;
	} // gerPrecision

	/**
	 * Set Current Cost Price
	 * 
	 * @param currentCostPrice
	 *            if null set to 0
	 */
	@Override
	public void setCurrentCostPrice(final BigDecimal currentCostPrice) {
		if (currentCostPrice != null) {
			super.setCurrentCostPrice(currentCostPrice);
		} else {
			super.setCurrentCostPrice(Env.ZERO);
		}
	} // setCurrentCostPrice

	/**
	 * Get History Average (Amt/Qty)
	 * 
	 * @return average if amt/aty <> 0 otherwise null
	 */
	public BigDecimal getHistoryAverage() {
		BigDecimal retValue = null;
		if (getCumulatedQty().signum() != 0 && getCumulatedAmt().signum() != 0) {
			retValue = getCumulatedAmt().divide(getCumulatedQty(),
					getPrecision(), BigDecimal.ROUND_HALF_UP);
		}
		return retValue;
	} // getHistoryAverage

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer(100);
		sb.append("MCost[AD_Client_ID=").append(getAD_Client_ID());

		if (getAD_Org_ID() != 0) {
			sb.append(",AD_Org_ID=").append(getAD_Org_ID());
		}
		sb.append(",M_Product_ID=").append(getM_Product_ID());
		if (getM_AttributeSetInstance_ID() != 0) {
			sb.append(",AD_ASI_ID=").append(getM_AttributeSetInstance_ID());
		}
		// sb.append (",C_AcctSchema_ID=").append (getC_AcctSchema_ID());
		// sb.append (",M_CostType_ID=").append (getM_CostType_ID());
		sb.append(",M_CostElement_ID=").append(getM_CostElement_ID());
		//
		sb.append(", CurrentCost=").append(getCurrentCostPrice())
				.append(", C.Amt=").append(getCumulatedAmt()).append(",C.Qty=")
				.append(getCumulatedQty()).append(']');
		return sb.toString();
	} // toString

	/**
	 * Get Cost Element
	 * 
	 * @return cost element
	 */
	public MCostElement getCostElement() {
		final int M_CostElement_ID = getM_CostElement_ID();
		if (M_CostElement_ID == 0) {
			return null;
		}
		return MCostElement.get(getCtx(), M_CostElement_ID, get_TrxName());
	} // getCostElement

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true if can be saved
	 */
	@Override
	protected boolean beforeSave(final boolean newRecord) {
		final MCostElement ce = getCostElement();
		// Check if data entry makes sense
		if (m_manual) {
			final MAcctSchema as = new MAcctSchema(getCtx(),
					getC_AcctSchema_ID(), null);
			
			final MProduct product = MProduct.get(getCtx(), getM_Product_ID());
			final String CostingLevel = product.getCostingLevel(as);
			

//			// Expert: Proyecto #102 Posteo,Costos y precios
//			// Si no encuentra el costo en cliente lo busca en system
//			final MProductCategoryAcct pca = MProductCategoryAcct.getAcct(
//					product, as);
//
//			if (pca.getCostingLevelDefault() != null) {
//				CostingLevel = pca.getCostingLevelDefault();
//			}
			if (X_C_AcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
				if (getAD_Org_ID() != 0 || getM_AttributeSetInstance_ID() != 0) {
					log.saveError("CostingLevelClient", "");
					return false;
				}
				
			} else if (X_C_AcctSchema.COSTINGLEVEL_BatchLot
					.equals(CostingLevel)) {
				if (getM_AttributeSetInstance_ID() == 0 && ce.isCostingMethod()) {
					log.saveError("FillMandatory", Msg.getElement(getCtx(),
							"M_AttributeSetInstance_ID"));
					return false;
				}
				if (getAD_Org_ID() != 0) {
					setAD_Org_ID(0);
				}
			}
		}

		// Cannot enter calculated
		if (m_manual && ce != null && ce.isCalculated()) {
			log.saveError("Error", Msg.getElement(getCtx(), "IsCalculated"));
			return false;
		}
		// Percentage
		if (ce != null
				&& (ce.isCalculated() || X_M_CostElement.COSTELEMENTTYPE_Material
						.equals(ce.getCostElementType()) && getPercent() != 0)) {
			setPercent(0);
		}

		if (getPercent() != 0) {
			if (getCurrentCostPrice().signum() != 0) {
				setCurrentCostPrice(Env.ZERO);
			}
			if (getFutureCostPrice().signum() != 0) {
				setFutureCostPrice(Env.ZERO);
			}
			if (getCumulatedAmt().signum() != 0) {
				setCumulatedAmt(Env.ZERO);
			}
			if (getCumulatedQty().signum() != 0) {
				setCumulatedQty(Env.ZERO);
			}
		}
		return true;
	} // beforeSave

	/**
	 * Before Delete
	 * 
	 * @return true
	 */
	@Override
	protected boolean beforeDelete() {
		return true;
	} // beforeDelete

	/**
	 * Generate costs for all active products for the provided accounting schema
	 * 
	 * @param as
	 *            The accounting schema
	 * @return A string with the error messages.
	 */
	public static String generateForAS(final MAcctSchema as) {

		logger.info("Generating for products ...");

		final StringBuffer retVal = new StringBuffer(100);

		final Properties ctx = as.getCtx();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT p.M_Product_ID, p.Value ")
				.append(" FROM M_Product p ")
				.append(" WHERE p.M_Product_ID NOT IN ( ")
				.append(" 		SELECT M_Product_ID FROM M_Cost ")
				.append(" 		WHERE AD_Client_ID = ? ")
				.append(" 	) ") 
				.append(" AND p.IsActive = 'Y' ")
				.append(" AND p.AD_Client_ID = 0 ");

				final StringBuilder sqlInsert = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append("INSERT INTO M_Cost ")
				.append(" (AD_CLIENT_ID, AD_ORG_ID, M_PRODUCT_ID, M_COSTTYPE_ID, C_ACCTSCHEMA_ID, ")
				.append(" M_COSTELEMENT_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY, CURRENTCOSTPRICE, ")
				.append(" M_ATTRIBUTESETINSTANCE_ID, CURRENTQTY ) ")
				.append(" VALUES (?,0,?,?,?, ").append(" ?,'Y',SYSDATE,0,SYSDATE,0,?, 0, 0)");

		PreparedStatement pstmt = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));

			logger.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			final MCostElement ce = MCostElement.getMaterialCostElement(as,
					as.getCostingMethod());

			psInsert = DB.prepareStatement(sqlInsert.toString(), as.get_TrxName());
			int no = 0;

			while (rs.next()) {

				psInsert.setInt(1, as.getAD_Client_ID());
				psInsert.setInt(2, rs.getInt(1));
				psInsert.setInt(3, as.getM_CostType_ID());
				psInsert.setInt(4, as.getC_AcctSchema_ID());
				psInsert.setInt(5, ce.getM_CostElement_ID());
				psInsert.setBigDecimal(6, Env.ONE);

				no = psInsert.executeUpdate();

				if (no == 0) {
					logger.warning("Can not create costs for "
							+ rs.getString(2) + " - " + CLogger.retrieveError());

					retVal.append("Can not create costs for " + rs.getString(2)
							+ " - " + CLogger.retrieveError());
				}
			}

		} catch (final SQLException e) {
			logger.log(Level.SEVERE, "Inserting cost data for products", e);

			retVal.append('\n').append(e.getMessage());

		} finally {
			DB.close(rs, pstmt);
			DB.close(null, psInsert);
		}

		return retVal.toString();
	}

	/**
	 * Test
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(final String[] args) {
		/**
		 * DELETE M_Cost c WHERE EXISTS (SELECT * FROM M_CostElement ce WHERE
		 * c.M_CostElement_ID=ce.M_CostElement_ID AND ce.IsCalculated='Y') /
		 * UPDATE M_Cost SET CumulatedAmt=0, CumulatedQty=0 / UPDATE
		 * M_CostDetail SET Processed='N' WHERE Processed='Y' / COMMIT /
		 **/

		Compiere.startup(true);
		final MClient client = MClient.get(Env.getCtx(), 11); // GardenWorld
		create(client);

	} // main

	/**
	 * Get Current Cost Price for Costing Level
	 * 
	 * @param product
	 *            product
	 * @param M_ASI_ID
	 *            costing level asi
	 * @param Org_ID
	 *            costing level org
	 * @param M_CostType_ID
	 *            cost type
	 * @param as
	 *            AcctSchema
	 * @param costingMethod
	 *            method
	 * @param qty
	 *            quantity
	 * @param C_OrderLine_ID
	 *            optional order line
	 * @param zeroCostsOK
	 *            zero/no costs are OK
	 * @param trxName
	 *            trx
	 * @return cost price or null
	 */
	public static BigDecimal materialCostEachStd(final MProduct product,
			final int M_ASI_ID, final MAcctSchema as, final int Org_ID,
			final int M_CostType_ID, final String costingMethod,
			final BigDecimal qty, final int C_OrderLine_ID,
			final boolean zeroCostsOK, final String trxName) {

		s_log.finer("MaterialCosts Std = "
				+ X_M_CostElement.COSTINGMETHOD_StandardCosting);

		BigDecimal currentCostPrice = null;
		String costElementType = null;
		BigDecimal percent = null;
		//
		BigDecimal materialCostEach = Env.ZERO;
		BigDecimal otherCostEach = Env.ZERO;
		BigDecimal percentage = Env.ZERO;
		int count = 0;
		//
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT SUM(c.CurrentCostPrice), ce.CostElementType, ce.CostingMethod, ")
				.append("  c.Percent, c.M_CostElement_ID ") // 4..5
				.append(" FROM M_Cost c ")
				.append("  LEFT OUTER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID) ")
				.append(" WHERE c.AD_Client_ID=? AND c.AD_Org_ID=? ") // #1/2
				.append("  AND c.M_Product_ID=? ") // #3
				.append("  AND (c.M_AttributeSetInstance_ID=? OR c.M_AttributeSetInstance_ID=0) ") // #4
				.append("  AND c.M_CostType_ID=? AND c.C_AcctSchema_ID=? ") // #5/6
				.append("  AND (ce.CostingMethod IS NULL OR ce.CostingMethod=?) ") // #7
				.append(" GROUP BY ce.CostElementType, ce.CostingMethod, c.Percent, c.M_CostElement_ID ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, as.getAD_Client_ID());
			pstmt.setInt(2, Org_ID);
			pstmt.setInt(3, product.getM_Product_ID());
			pstmt.setInt(4, M_ASI_ID);
			pstmt.setInt(5, M_CostType_ID);
			pstmt.setInt(6, as.getC_AcctSchema_ID());
			pstmt.setString(7, costingMethod);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				currentCostPrice = rs.getBigDecimal(1);
				costElementType = rs.getString(2);
				final String cm = rs.getString(3);
				percent = rs.getBigDecimal(4);
				// M_CostElement_ID = rs.getInt(5);
				s_log.finest("CurrentCostPrice=" + currentCostPrice
						+ ", CostElementType=" + costElementType
						+ ", CostingMethod=" + cm + ", Percent=" + percent);
				//
				if (currentCostPrice != null && currentCostPrice.signum() != 0) {
					if (cm != null) {
						materialCostEach = materialCostEach
								.add(currentCostPrice);
					} else {
						otherCostEach = otherCostEach.add(currentCostPrice);
					}
				}
				if (percent != null && percent.signum() != 0) {
					percentage = percentage.add(percent);
				}
				count++;
			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		if (count > 1) {
			s_log.finest("MaterialCost=" + materialCostEach + ", OtherCosts="
					+ otherCostEach + ", Percentage=" + percentage);
		}

		// Seed Initial Costs
		if (materialCostEach.signum() == 0) // no costs
		{
			if (zeroCostsOK) {
				return Env.ZERO;
			}
		}

		// if (materialCostEach == null)
		// return null;

		s_log.finer("MaterialCosts = " + materialCostEach);
		return materialCostEach;
	} // getCurrentCost

	
	/** Test */
	public static BigDecimal getCurrentCostTest(final MProduct product,
			final int M_ASI_ID, final MAcctSchema as
			, final int Org_ID,
			final int M_CostType_ID, final String costingMethod,
			final BigDecimal qty, final int C_OrderLine_ID,
			final boolean zeroCostsOK,
			final String trxName) {
		return 	MCost.getCurrentCost(product
				, M_ASI_ID
				, as
				, Org_ID
				, M_CostType_ID
				, costingMethod
				, qty
				, C_OrderLine_ID
				, zeroCostsOK
				, trxName);
		
	}
	
	public boolean isNewRecord() {
		return isNewRecord;
	}


	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
} // MCost
