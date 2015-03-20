package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEAsistente extends X_EXME_Asistente {

	private static final long serialVersionUID = -2583986463870612773L;

	public MEXMEAsistente(Properties ctx, int EXME_Asistente_ID, String trxName) {
		super(ctx, EXME_Asistente_ID, trxName);
	}

	public MEXMEAsistente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public boolean isViewAfterFinished(){
		return isViewAllergies() || isViewHistory() || isViewHMedication() || isViewInstructions()
				|| isViewNextAppt() || isViewService() || isViewVitals()
				|| isCreatePrescription() || isConfirmReferrals();
	}

//	public boolean isCompleteAppt() {
//		return (isConfirmImaging() || isConfirmLabOrders() || isConfirmReferrals() || isCreatePrescription());
//	}

	public boolean isHasDoctor() {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select md.EXME_Medico_ID from exme_medico md ")
				.append("inner join exme_medicoasist asmed on asmed.exme_medico_id = md.exme_medico_id ")
				.append("inner join exme_medico_org mo on md.exme_medico_id = mo.exme_medico_id ")
//				.append("inner join exme_asistente asis on asis.exme_asistente_id = asmed.exme_asistente_id ").
				.append("where asmed.exme_asistente_id = ? AND md.isactive = 'Y'")
				.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedicoOrg.Table_Name, "mo"))
				; 
		log.log(Level.FINEST, sql.toString());
		return DB.getSQLValue(null, sql.toString(), getEXME_Asistente_ID()) > 0;
	}
}
