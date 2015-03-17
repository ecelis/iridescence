package org.compiere.model;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;

public class MEXMETrasplantesEspera extends X_EXME_Trasplantes_Espera {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3875752983432455946L;

	public MEXMETrasplantesEspera(Properties ctx,
			int EXME_Trasplantes_Espera_ID, String trxName) {
		super(ctx, EXME_Trasplantes_Espera_ID, trxName);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		String oldEstatus = null;

		try {
			oldEstatus = (String) get_ValueOld("Estatus");
		} catch (NullPointerException e) {
			return true;
		}

		if (!oldEstatus.equals(getEstatus()))
			setFecha_Estatus(DB.getTimestampForOrg(getCtx()));
		return true;
	}

}
