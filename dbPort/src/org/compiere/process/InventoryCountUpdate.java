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

import java.util.logging.Level;

import org.compiere.model.MInventory;
import org.compiere.util.CompiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**
 *	Update existing Inventory Count List with current Book value
 *	
 *  @author Jorg Janke
 *  @version $Id: InventoryCountUpdate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class InventoryCountUpdate extends SvrProcess
{
	/** Physical Inventory		*/
	private int		p_M_Inventory_ID = 0;
	/** Update to What			*/
	private boolean	p_InventoryCountSetZero = false;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("InventoryCountSet"))
				p_InventoryCountSetZero = "Z".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_M_Inventory_ID = getRecord_ID();
	}	//	prepare

	
	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception {
		log.info("M_Inventory_ID=" + p_M_Inventory_ID);
		MInventory inventory = new MInventory(getCtx(), p_M_Inventory_ID, get_TrxName());
		if (inventory.get_ID() == 0) {
			throw new CompiereSystemError("Not found: M_Inventory_ID=" + p_M_Inventory_ID);
		}

		// Set Count to Zero
		if (p_InventoryCountSetZero) {
			StringBuilder sql = new StringBuilder("UPDATE M_InventoryLine l ");
			sql.append(" SET QtyBook=0, QtyBook_Uom=0, QtyBook_Vol = 0 ");
			sql.append(" WHERE M_Inventory_ID = ? ");
			int no = DB.executeUpdate(sql.toString(), new Object[] { p_M_Inventory_ID }, null);
			log.info("Set Cont to Zero=" + no);
		} else {
			Trx trx = null;

			try {
				trx = Trx.get(Trx.createTrxName("invul"), true);
				
				if (MInventory.updateInventoryLines(getCtx(), p_M_Inventory_ID, trx.getTrxName())) {
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
		}

		return "@M_InventoryLine_ID@ - #";
	} // doIt
	
}	//	InventoryCountUpdate
