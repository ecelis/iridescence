package org.compiere.model;

import java.util.Properties;

import org.compiere.util.Utilerias;

public class CalloutPharmacist extends CalloutEngine {
	
	public String email(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		if (Utilerias.validateMail(String.valueOf(value))) {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_EMail, String.valueOf(value));
		} else {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_EMail, "");
		}
		setCalloutActive(false);

		return "";
	}
	
	public String emailWork(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		if (Utilerias.validateMail(String.valueOf(value))) {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_EMailWork, String.valueOf(value));
		} else {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_EMailWork, "");
		}
		setCalloutActive(false);

		return "";
	}
	
	public String telefono(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		if (Utilerias.validateTel(ctx,String.valueOf(value))) {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_TelParticular, String.valueOf(value));
		} else {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_TelParticular, "");
		}
		setCalloutActive(false);

		return "";
	}
	
	public String telefonoWork(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		if (Utilerias.validateTel(ctx,String.valueOf(value))) {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_TelTrabajo, String.valueOf(value));
		} else {
			mTab.setValue(X_EXME_Pharmacist.COLUMNNAME_TelTrabajo, "");
		}
		setCalloutActive(false);

		return "";
	}
}
