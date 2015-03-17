package org.compiere.process;

import org.compiere.model.MInOut;
import org.compiere.model.bpm.Inventory;

/**
 * Actualiza el inventario
 * a partir de EXME_InOutStop
 * @author mvrodriguez
 * @deprecated
 */
public class UpdateInventory extends SvrProcess {

	private int id = 0;
	
	@Override
	protected void prepare() { 
		id = getProcessInfo().getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		MInOut in = new MInOut(getCtx(), id, get_TrxName());
//		Inventory.updateInventory(getCtx(), in.getLines(), get_TrxName());
		return "@M_InOutLine_ID@ - #" + in.getLines().length;
	}
}
