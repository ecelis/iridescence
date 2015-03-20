package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEHistVacunaTemp extends X_EXME_Hist_Vacuna_Temp {
	private static final long	serialVersionUID	= 1L;

	public MEXMEHistVacunaTemp(Properties ctx, int EXME_Hist_Vacuna_Temp_ID, String trxName) {
		super(ctx, EXME_Hist_Vacuna_Temp_ID, trxName);
	}

	public MEXMEHistVacunaTemp(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private MEXMEVacuna vacuna = null;
	private MEXMEVacunaDet vacunaDet = null;
	
	public MEXMEVacuna getVacuna() {
		if( vacuna == null){
			vacuna = new MEXMEVacuna(getCtx(), getEXME_Vacuna_ID(), get_TrxName());
		}
		return vacuna;
	}

	public void setVacuna(MEXMEVacuna vacuna) {
		this.vacuna = vacuna;
	}

	public MEXMEVacunaDet getVacunaDet() {
		if(vacunaDet == null){
			vacunaDet = new MEXMEVacunaDet(getCtx(), getEXME_VacunaDet_ID(), get_TrxName());
		}
		return vacunaDet;
	}

	public void setVacunaDet(MEXMEVacunaDet vacunaDet) {
		this.vacunaDet = vacunaDet;
	}
}
