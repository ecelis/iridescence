/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MedicalResume
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MedicalResume 
{

    /** TableName=EXME_MedicalResume */
    public static final String Table_Name = "EXME_MedicalResume";

    /** AD_Table_ID=1200358 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name ClinicResumeOut */
    public static final String COLUMNNAME_ClinicResumeOut = "ClinicResumeOut";

	/** Set Clinic Resume Out	  */
	public void setClinicResumeOut (String ClinicResumeOut);

	/** Get Clinic Resume Out	  */
	public String getClinicResumeOut();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_ActPaciente_ID */
    public static final String COLUMNNAME_EXME_ActPaciente_ID = "EXME_ActPaciente_ID";

	/** Set Patient Activity.
	  * Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID);

	/** Get Patient Activity.
	  * Patient Activity
	  */
	public int getEXME_ActPaciente_ID();

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException;

    /** Column name EXME_MedicalResume_ID */
    public static final String COLUMNNAME_EXME_MedicalResume_ID = "EXME_MedicalResume_ID";

	/** Set Medical Resume	  */
	public void setEXME_MedicalResume_ID (int EXME_MedicalResume_ID);

	/** Get Medical Resume	  */
	public int getEXME_MedicalResume_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name Forecast */
    public static final String COLUMNNAME_Forecast = "Forecast";

	/** Set Forecast	  */
	public void setForecast (String Forecast);

	/** Get Forecast	  */
	public String getForecast();

    /** Column name MainTreatment */
    public static final String COLUMNNAME_MainTreatment = "MainTreatment";

	/** Set Main Treatment	  */
	public void setMainTreatment (String MainTreatment);

	/** Get Main Treatment	  */
	public String getMainTreatment();

    /** Column name MedicinePrescription */
    public static final String COLUMNNAME_MedicinePrescription = "MedicinePrescription";

	/** Set Medicine Prescription	  */
	public void setMedicinePrescription (String MedicinePrescription);

	/** Get Medicine Prescription	  */
	public String getMedicinePrescription();

    /** Column name OtherTreatment */
    public static final String COLUMNNAME_OtherTreatment = "OtherTreatment";

	/** Set Other Treatment	  */
	public void setOtherTreatment (String OtherTreatment);

	/** Get Other Treatment	  */
	public String getOtherTreatment();

    /** Column name Rehab */
    public static final String COLUMNNAME_Rehab = "Rehab";

	/** Set Rehab	  */
	public void setRehab (String Rehab);

	/** Get Rehab	  */
	public String getRehab();
}
