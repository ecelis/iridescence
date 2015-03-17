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
import java.util.logging.Level;

import org.compiere.model.MInventory;
import org.compiere.model.MProduct;
import org.compiere.util.CompiereSystemError;
import org.compiere.util.Trx;

/**
 * Create Inventory Count List with current Book value
 * 
 * @author Jorg Janke
 * @version $Id: InventoryCountCreate.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class InventoryCountCreate extends SvrProcess {

	/** Physical Inventory Parameter */
	private int p_M_Inventory_ID = 0;
	/** Physical Inventory */
	private MInventory m_inventory = null;
	/** Locator Parameter */
	private int p_M_Locator_ID = 0;
	/** Locator Parameter */
	private String p_LocatorValue = null;
	/** Product Parameter */
	private String p_ProductValue = null;
	/** Product Category Parameter */
	private int p_M_Product_Category_ID = 0;
	/** Qty Range Parameter */
	private String p_QtyRange = null;
	/** Update to What */
	private boolean p_InventoryCountSetZero = false;
	/** Delete Parameter */
	private boolean p_DeleteOld = false;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Locator_ID"))
				p_M_Locator_ID = para[i].getParameterAsInt();
			else if (name.equals("LocatorValue"))
				p_LocatorValue = (String) para[i].getParameter();
			else if (name.equals("ProductValue")) {
				if (para[i].getParameter() instanceof BigDecimal) {
					MProduct product = new MProduct(getCtx(), ((BigDecimal) para[i].getParameter()).intValue(), null);
					p_ProductValue = product.getValue();
				} else {
					p_ProductValue = (String) para[i].getParameter();
				}
			} else if (name.equals("M_Product_Category_ID"))
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			else if (name.equals("QtyRange"))
				p_QtyRange = (String) para[i].getParameter();
			else if (name.equals("InventoryCountSet"))
				p_InventoryCountSetZero = "Z".equals(para[i].getParameter());
			else if (name.equals("DeleteOld"))
				p_DeleteOld = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_M_Inventory_ID = getRecord_ID();
	} // prepare

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */
	@Override
	protected String doIt() throws Exception {
		log.info("M_Inventory_ID=" + p_M_Inventory_ID + ", M_Locator_ID=" + p_M_Locator_ID + ", LocatorValue=" + p_LocatorValue + ", ProductValue=" + p_ProductValue + ", M_Product_Category_ID=" + p_M_Product_Category_ID + ", QtyRange=" + p_QtyRange + ", DeleteOld=" + p_DeleteOld);

		m_inventory = new MInventory(getCtx(), p_M_Inventory_ID, get_TrxName());

		if (m_inventory.get_ID() == 0) {
			throw new CompiereSystemError("Not found: M_Inventory_ID=" + p_M_Inventory_ID);
		}

		if (m_inventory.isProcessed()) {
			throw new CompiereSystemError("@M_Inventory_ID@ @Processed@");
		}

		Trx trx = null;

		try {
			trx = Trx.get(Trx.createTrxName("invcl"), true);

			int count = MInventory.createInventoryLines(getCtx(), p_M_Inventory_ID,//
					p_M_Locator_ID, p_LocatorValue, -1, p_ProductValue, p_M_Product_Category_ID, //
					p_QtyRange, p_InventoryCountSetZero, p_DeleteOld, trx.getTrxName());

			if (count > 0) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
			Trx.rollback(trx);
		} finally {
			Trx.close(trx);
		}

		return "@M_InventoryLine_ID@ - #";
	} // doIt

} // InventoryCountCreate
