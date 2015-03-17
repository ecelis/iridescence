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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 *	Requisition Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutRequisition.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class CalloutRequisition extends CalloutEngine{
	
	/**
	 *	Requisition Line - Product.
	 *		- PriceStd
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		if (isCalloutActive()){
			return "";
		}
		
		setCalloutActive(true);
		
		Integer M_Product_ID = null;
		
		if(value instanceof BigDecimal){
			M_Product_ID = Integer.valueOf(value.toString());
		} else if(value instanceof Integer){
			M_Product_ID = (Integer)value;
		}
			
		if (M_Product_ID == null || M_Product_ID.intValue() == 0){
			setCalloutActive(false);
			return "";	
		}
			
		// se limpian los datos de cantidad
		mTab.setValue("Qty_Vol", BigDecimal.ONE);
		mTab.setValue("Qty", BigDecimal.ONE);
		
		final int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, WindowNo, "C_BPartner_ID");
		
		final BigDecimal Qty = (BigDecimal)mTab.getValue("Qty");
		
		final boolean isSOTrx = false;
		final MProductPricing pp = new MProductPricing (M_Product_ID.intValue(), C_BPartner_ID, Qty, isSOTrx);
		//
		final int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID");
		pp.setM_PriceList_ID(M_PriceList_ID);
		final int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		final Timestamp orderDate = (Timestamp)mTab.getValue("DateRequired");
		pp.setPriceDate(orderDate);
		//		
		mTab.setValue("PriceActual", pp.getPriceStd());
		Env.setContext(ctx, WindowNo, "EnforcePriceLimit", pp.isEnforcePriceLimit() ? "Y" : "N");	//	not used
		Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema() ? "Y" : "N");
		
		//rsolorzano 05-sep-2012
		//cambios unidades de medida
		final MProduct product = MProduct.get(ctx, M_Product_ID.intValue());
		mTab.setValue("C_UOM_ID", Integer.valueOf(product.getC_UOM_ID()));
		mTab.setValue("C_UOMVOLUME_ID", Integer.valueOf(product.getC_UOMVolume_ID()));
		
		BigDecimal QtyValue = null;
		
		if(mTab.getValue("Qty") instanceof BigDecimal && value !=null){
			QtyValue = (BigDecimal) mTab.getValue("Qty");
		}else{
			QtyValue = BigDecimal.ZERO;
			mTab.setValue("Qty", QtyValue);
			mTab.setValue("Qty_Vol", Qty);
			setCalloutActive(false);
			return "";
			
		}
		
		//si las dos presentaciones son diferentes, verificamos si tiene conversiones
		if(product.getC_UOM_ID() != product.getC_UOMVolume_ID()){
			
			final MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),null);
					
			if(rates==null){
				log.saveError(Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion"), product.getName());
				final String msg = Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion");
				//mField.setValue(null, true);
				mTab.setValue("Qty_Vol", BigDecimal.ONE);
				mTab.setValue("Qty", BigDecimal.ONE);
				setCalloutActive(false);
				return msg;
			}


			final BigDecimal QtyTmp = MEXMEUOMConversion.convertProductFrom(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), Qty, null);
			mTab.setValue("Qty", QtyTmp);
			
		}


		setCalloutActive(false);
		return "";
	}	//	product
	
	/**
	 *	Order Line - Amount.
	 *		- called from Qty, PriceActual
	 *		- calculates LineNetAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String amt (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null){
			return "";
		}
		setCalloutActive(true);

		//	Qty changed - recalc price
		if (mField.getColumnName().equals("Qty") 
			&& "Y".equals(Env.getContext(ctx, WindowNo, "DiscountSchema"))){
			final int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, WindowNo, "M_Product_ID");
			final int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, WindowNo, "C_BPartner_ID");
			final BigDecimal Qty = (BigDecimal)value;
			boolean isSOTrx = false;
			MProductPricing pp = new MProductPricing (M_Product_ID, 
				C_BPartner_ID, Qty, isSOTrx);
			//
			final int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID");
			pp.setM_PriceList_ID(M_PriceList_ID);
			final int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
			pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
			Timestamp orderDate = (Timestamp)mTab.getValue("DateInvoiced");
			pp.setPriceDate(orderDate);
			//
			mTab.setValue("PriceActual", pp.getPriceStd());
		}

		int StdPrecision = Env.getContextAsInt(ctx, WindowNo, "StdPrecision");
		BigDecimal Qty = (BigDecimal)mTab.getValue("Qty");
		BigDecimal PriceActual = (BigDecimal)mTab.getValue("PriceActual");
		if(PriceActual==null){
			PriceActual = Env.ZERO;
			mTab.setValue("PriceActual", Env.ZERO);
		}
		
		//	get values
		log.fine("amt - Qty=" + Qty + ", Price=" + PriceActual + ", Precision=" + StdPrecision);

		//	Multiply
		BigDecimal LineNetAmt = Qty.multiply(PriceActual);
		if (LineNetAmt.scale() > StdPrecision){
			LineNetAmt = LineNetAmt.setScale(StdPrecision, BigDecimal.ROUND_HALF_UP);
		}
		mTab.setValue("LineNetAmt", LineNetAmt);
		log.info("amt - LineNetAmt=" + LineNetAmt);
		//
		setCalloutActive(false);
		return "";
	}	//	amt
	
	
	/**
	 * calcula el campo qtyVol en base a la cantidad capturada.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String qty(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		String msg = "";
		
		if (isCalloutActive()){
			return msg;
		}
		
		setCalloutActive(true);
			
		BigDecimal Qty = null;
		Integer M_Product_ID = 0;
		
		if(value instanceof BigDecimal){
			Qty = (BigDecimal) value;
		}else{
			Qty = BigDecimal.ZERO;
			mField.setValue(Qty, true);
			mTab.setValue("Qty_Vol", Qty);
			setCalloutActive(false);
			return msg;
		}
			
		if(mTab.getValue("M_Product_ID") instanceof BigDecimal){
			M_Product_ID = Integer.valueOf(mTab.getValue("M_Product_ID").toString());
		}else if(mTab.getValue("M_Product_ID") instanceof Integer){
			M_Product_ID = (Integer) mTab.getValue("M_Product_ID");
		}
			
		MProduct product = MProduct.get(ctx, M_Product_ID);
		//si las dos presentaciones son iguales, los dos campos llevaran el mismo valor
		//si las dos presentacions son diferentes, verificamos si tiene conversiones
		if(product.getC_UOM_ID() == product.getC_UOMVolume_ID()){
		
			mTab.setValue("Qty_Vol", Qty);
			setCalloutActive(false);
			return msg;
		
		}else{
			
			MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),null);
			
			if(rates==null){
				log.saveError(Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion"), product.getName());
				msg = Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion");
				mField.setValue(mField.getOldValue(), true);
				setCalloutActive(false); 
				return msg;
			}
			
			BigDecimal QtyTmp = MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), Qty, null);
			mTab.setValue("Qty_Vol", QtyTmp);
			
			setCalloutActive(false);
			return msg;
		}

	}	


	/**
	 * calcula el campo qty en base a la cantidad  de volumen capturada.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String qtyVol (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
	
		String msg = "";
		
		if (isCalloutActive()){
			return msg;
		}
		
		setCalloutActive(true);
			
		BigDecimal QtyVol = null;
		Integer M_Product_ID = 0;
		
		if(value instanceof BigDecimal){
			QtyVol = (BigDecimal) value;
		}else{
			QtyVol = BigDecimal.ZERO;
			mField.setValue(QtyVol, true);
			mTab.setValue("Qty", QtyVol);
			setCalloutActive(false);
			return msg;
		}
			
		if(mTab.getValue("M_Product_ID") instanceof BigDecimal){
			M_Product_ID = Integer.valueOf(mTab.getValue("M_Product_ID").toString());
		}else if(mTab.getValue("M_Product_ID") instanceof Integer){
			M_Product_ID = (Integer) mTab.getValue("M_Product_ID");
		}
			
		MProduct product = MProduct.get(ctx, M_Product_ID);
		//si las dos presentaciones son iguales, los dos campos llevaran el mismo valor
		//si las dos presentacions son diferentes, verificamos si tiene conversiones
		if(product.getC_UOM_ID() == product.getC_UOMVolume_ID()){
		
			mTab.setValue("Qty", QtyVol);
			setCalloutActive(false);
			return msg;
		
		}else{
			
			MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),null);
			
			if(rates==null){
				log.saveError(Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion"), product.getName());
				msg = Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion");
				mField.setValue(mField.getOldValue(), true);
				setCalloutActive(false);
				return msg;
			}
			
			BigDecimal QtyTmp = MEXMEUOMConversion.convertProductFrom (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), QtyVol, null);
			mTab.setValue("Qty", QtyTmp);
			
			setCalloutActive(false);
			return msg;
		}
	}	

	
	

	
}	//	CalloutRequisition
