/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.QuickSearchTables;

/**
 * View Product - Intervention
 * Card #618
 * @author lama
 * 
 */
public class MEXMEProductInterv extends X_EXME_ProductInterv {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param EXME_ProductInterv_ID
	 * @param trxName
	 */
	public MEXMEProductInterv(Properties ctx, int EXME_ProductInterv_ID, String trxName) {
		super(ctx, EXME_ProductInterv_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProductInterv(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public int getM_Product_ID() {
		return super.getEXME_ProductInterv_ID();
	}

	private String	observations;

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public MProgQuiroDet newProgQuiroDet(final String trxName) {
		final MProgQuiroDet progDet = new MProgQuiroDet(getCtx(), 0, trxName);
		progDet.setDescription(getObservations());
		progDet.setEXME_Intervencion_ID(getEXME_Intervencion_ID());
		progDet.setM_Product_ID(getM_Product_ID());
		return progDet;
	}

	public static void checkTables(Properties ctx) {
		QuickSearchTables.rebuildAll(MEXMEProductInterv.Table_Name + "@System*", ctx);
		QuickSearchTables.rebuildAll(MEXMEProductInterv.Table_Name + "@ClientOrg*", ctx);
	}

	/**
	 * Updates EXME_ProductInterv (view) search file index when newRecordOrDelete is true
	 * or any of the following columns changes:
	 * IsActive
	 * EXME_Intervencion_ID
	 * Name
	 * ProductClass
	 * 
	 * @param prod Product model
	 * @param newRecordOrDelete True if a new record or record was deleted
	 */
	public static void checkTable(final MProduct prod, boolean newRecordOrDelete) {
		if (prod != null) {
			// si requiere
			if (newRecordOrDelete //
				|| prod.is_ValueChanged(MProduct.COLUMN_IsActive) //
				|| prod.is_ValueChanged(MProduct.COLUMNNAME_EXME_Intervencion_ID) || prod.is_ValueChanged(MProduct.COLUMNNAME_Name) //
				|| prod.is_ValueChanged(MProduct.COLUMNNAME_ProductClass)) {
				if (prod.getAD_Client_ID() == 0) {
					QuickSearchTables.rebuildAll(MEXMEProductInterv.Table_Name + "@System*", prod.getCtx());
				} else {
					QuickSearchTables.rebuildAll(MEXMEProductInterv.Table_Name + "@ClientOrg*", prod.getCtx());
				}
				QuickSearchTables.checkTables(MEXMEProductInterv.class, MEXMEProductInterv.Table_Name, prod.getM_Product_ID(), prod.get_TrxName(),
					prod.getCtx());
			}
		}
	}
}
