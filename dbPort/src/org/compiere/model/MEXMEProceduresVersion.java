/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.QuickSearchTables;

/**
 * @author vperez
 */
public class MEXMEProceduresVersion extends X_EXME_ProceduresVersion {
	
	public MEXMEProceduresVersion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public MEXMEProceduresVersion(Properties ctx, int EXME_DiagnosisTypeDet_ID, String trxName) {
		super(ctx, EXME_DiagnosisTypeDet_ID, trxName);
	}
	
	/**
	 * @Override
	 * @param newRecord
	 * @param success
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			QuickSearchTables.rebuildAll(X_EXME_ProcedureType.Table_Name + "@CQMPro", getCtx());
		}
		return success;
	}
}
