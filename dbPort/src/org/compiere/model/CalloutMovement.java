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
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Utilerias;



/**
 *	Inventory Movement Callouts
 *
 *  @author Jorg Janke
 *  @version $Id: CalloutMovement.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class CalloutMovement extends CalloutEngine
{
	/**
	 *  Product modified
	 * 		Set Attribute Set Instance
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_Product_ID = null;
		if(value instanceof BigDecimal) {
			M_Product_ID = Integer.valueOf(value.toString());
		} else if(value instanceof Integer) {
			M_Product_ID = (Integer)value;
		}
		if (M_Product_ID == null || M_Product_ID.intValue() == 0) {
			return "";
		}
		//	Set Attribute
		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
			&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0) {
			mTab.setValue("M_AttributeSetInstance_ID", Integer.valueOf(Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID")));
		} else {
			mTab.setValue("M_AttributeSetInstance_ID", null);
		}
		if(((BigDecimal)mTab.getValue("MovementQty")).compareTo(BigDecimal.ZERO)>0){
			return validateqty(mTab, ctx);
		}
		return "";
	}   //  product

	public String qtyToMove (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		String error = "";
		if(((BigDecimal)mTab.getValue("MovementQty")).compareTo(BigDecimal.ZERO)>0){
			error = validateqty(mTab, ctx);
		}
	return error;
	}

	public String validateqty(GridTab mTab, Properties ctx){
		final BigDecimal movementQty = (BigDecimal) mTab.getValue("MovementQty");
		final int locatorID = (Integer) mTab.getValue("M_locator_ID");
		int productID;
		if(mTab.getValue("M_Product_ID") == null){
			productID = 0;
		}else{
			productID = (Integer) mTab.getValue("M_Product_ID");
		}
		final MWarehouse warehouse = MWarehouse.getFromLocator(ctx, locatorID, null);
		final BigDecimal qtyAvalible = MStorage.getQtyAvailable(warehouse.getM_Warehouse_ID(), productID, 0, null);
		if(qtyAvalible != null && qtyAvalible.compareTo(movementQty)<0){
			//Menaje no hay suficientes existencias
			mTab.setValue("MovementQty",BigDecimal.ZERO);
			final MProduct prod = new MProduct(ctx, productID, null);
			return Utilerias.getLabel("error.encCtaPac.noExistenProd", prod.getName());
	}
		return"";
	}
}	//	CalloutMove
