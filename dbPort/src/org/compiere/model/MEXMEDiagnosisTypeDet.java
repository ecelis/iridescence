package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEDiagnosisTypeDet extends X_EXME_DiagnosisTypeDet{

	public MEXMEDiagnosisTypeDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	public MEXMEDiagnosisTypeDet(Properties ctx, int EXME_DiagnosisTypeDet_ID,
			String trxName) {
		super(ctx, EXME_DiagnosisTypeDet_ID, trxName);
		
	}

	/**
	 * Se guarda el value del diagnostico en la tabla Exme_diagnosistypedet
	 */
	  @Override
	    protected boolean beforeSave(boolean newRecord) {
	    	MDiagnostico diag = new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), null);
			setCode(diag.getValue());
			return true;
	    }
	

}
