/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Paciente_Cta_V
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Paciente_Cta_V 
{

    /** TableName=EXME_Paciente_Cta_V */
    public static final String Table_Name = "EXME_Paciente_Cta_V";

    /** AD_Table_ID=1201263 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AdmitDate */
    public static final String COLUMNNAME_AdmitDate = "AdmitDate";

	/** Set Admit Date.
	  * Admit Date
	  */
	public void setAdmitDate (Timestamp AdmitDate);

	/** Get Admit Date.
	  * Admit Date
	  */
	public Timestamp getAdmitDate();

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

    /** Column name Apellido1 */
    public static final String COLUMNNAME_Apellido1 = "Apellido1";

	/** Set Last Name.
	  * Last Name
	  */
	public void setApellido1 (String Apellido1);

	/** Get Last Name.
	  * Last Name
	  */
	public String getApellido1();

    /** Column name C_BPartnerPac_ID */
    public static final String COLUMNNAME_C_BPartnerPac_ID = "C_BPartnerPac_ID";

	/** Set Company of the Patient	  */
	public void setC_BPartnerPac_ID (int C_BPartnerPac_ID);

	/** Get Company of the Patient	  */
	public int getC_BPartnerPac_ID();

    /** Column name CBPIns_ID */
    public static final String COLUMNNAME_CBPIns_ID = "CBPIns_ID";

	/** Set Insurance ID (INS)	  */
	public void setCBPIns_ID (int CBPIns_ID);

	/** Get Insurance ID (INS)	  */
	public int getCBPIns_ID();

    /** Column name CBPIns_Name */
    public static final String COLUMNNAME_CBPIns_Name = "CBPIns_Name";

	/** Set Insurance Name (INS)	  */
	public void setCBPIns_Name (String CBPIns_Name);

	/** Get Insurance Name (INS)	  */
	public String getCBPIns_Name();

    /** Column name CBPIns_PayerID */
    public static final String COLUMNNAME_CBPIns_PayerID = "CBPIns_PayerID";

	/** Set Payer ID (INS)	  */
	public void setCBPIns_PayerID (String CBPIns_PayerID);

	/** Get Payer ID (INS)	  */
	public String getCBPIns_PayerID();

    /** Column name CBPIns_Value */
    public static final String COLUMNNAME_CBPIns_Value = "CBPIns_Value";

	/** Set Insurance Value (INS)	  */
	public void setCBPIns_Value (String CBPIns_Value);

	/** Get Insurance Value (INS)	  */
	public String getCBPIns_Value();

    /** Column name CBPProf_ID */
    public static final String COLUMNNAME_CBPProf_ID = "CBPProf_ID";

	/** Set Insurance ID (PROF)	  */
	public void setCBPProf_ID (int CBPProf_ID);

	/** Get Insurance ID (PROF)	  */
	public int getCBPProf_ID();

    /** Column name CBPProf_Name */
    public static final String COLUMNNAME_CBPProf_Name = "CBPProf_Name";

	/** Set Insurance Name (PROF)	  */
	public void setCBPProf_Name (String CBPProf_Name);

	/** Get Insurance Name (PROF)	  */
	public String getCBPProf_Name();

    /** Column name CBPProf_PayerID */
    public static final String COLUMNNAME_CBPProf_PayerID = "CBPProf_PayerID";

	/** Set Payer ID (PROF)	  */
	public void setCBPProf_PayerID (String CBPProf_PayerID);

	/** Get Payer ID (PROF)	  */
	public String getCBPProf_PayerID();

    /** Column name CBPProf_Value */
    public static final String COLUMNNAME_CBPProf_Value = "CBPProf_Value";

	/** Set Insurance Value (PROF)	  */
	public void setCBPProf_Value (String CBPProf_Value);

	/** Get Insurance Value (PROF)	  */
	public String getCBPProf_Value();

    /** Column name ChargeStatus */
    public static final String COLUMNNAME_ChargeStatus = "ChargeStatus";

	/** Set Charge Status	  */
	public void setChargeStatus (boolean ChargeStatus);

	/** Get Charge Status	  */
	public boolean isChargeStatus();

    /** Column name CPCIns_Name */
    public static final String COLUMNNAME_CPCIns_Name = "CPCIns_Name";

	/** Set PayerClass Name (INS)	  */
	public void setCPCIns_Name (String CPCIns_Name);

	/** Get PayerClass Name (INS)	  */
	public String getCPCIns_Name();

    /** Column name CPCIns_Value */
    public static final String COLUMNNAME_CPCIns_Value = "CPCIns_Value";

	/** Set PayerClass Value (INS)	  */
	public void setCPCIns_Value (String CPCIns_Value);

	/** Get PayerClass Value (INS)	  */
	public String getCPCIns_Value();

    /** Column name CPCProf_Name */
    public static final String COLUMNNAME_CPCProf_Name = "CPCProf_Name";

	/** Set PayerClass Name (PROF)	  */
	public void setCPCProf_Name (String CPCProf_Name);

	/** Get PayerClass Name (PROF)	  */
	public String getCPCProf_Name();

    /** Column name CPCProf_Value */
    public static final String COLUMNNAME_CPCProf_Value = "CPCProf_Value";

	/** Set PayerClass Value (PROF)	  */
	public void setCPCProf_Value (String CPCProf_Value);

	/** Get PayerClass Value (PROF)	  */
	public String getCPCProf_Value();

    /** Column name CtaPacDoc */
    public static final String COLUMNNAME_CtaPacDoc = "CtaPacDoc";

	/** Set CtaPacDoc	  */
	public void setCtaPacDoc (String CtaPacDoc);

	/** Get CtaPacDoc	  */
	public String getCtaPacDoc();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EncounterStatus */
    public static final String COLUMNNAME_EncounterStatus = "EncounterStatus";

	/** Set Encounter Status	  */
	public void setEncounterStatus (String EncounterStatus);

	/** Get Encounter Status	  */
	public String getEncounterStatus();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Paciente_Cta_V_ID */
    public static final String COLUMNNAME_EXME_Paciente_Cta_V_ID = "EXME_Paciente_Cta_V_ID";

	/** Set EXME_Paciente_Cta_V_ID	  */
	public void setEXME_Paciente_Cta_V_ID (int EXME_Paciente_Cta_V_ID);

	/** Get EXME_Paciente_Cta_V_ID	  */
	public int getEXME_Paciente_Cta_V_ID();

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

    /** Column name FechaAlta */
    public static final String COLUMNNAME_FechaAlta = "FechaAlta";

	/** Set Discharge Date.
	  * Discharge Date
	  */
	public void setFechaAlta (Timestamp FechaAlta);

	/** Get Discharge Date.
	  * Discharge Date
	  */
	public Timestamp getFechaAlta();

    /** Column name FechaNac */
    public static final String COLUMNNAME_FechaNac = "FechaNac";

	/** Set Birth Date.
	  * Birth Date
	  */
	public void setFechaNac (String FechaNac);

	/** Get Birth Date.
	  * Birth Date
	  */
	public String getFechaNac();

    /** Column name Guar_Name */
    public static final String COLUMNNAME_Guar_Name = "Guar_Name";

	/** Set Guarantor Name	  */
	public void setGuar_Name (String Guar_Name);

	/** Get Guarantor Name	  */
	public String getGuar_Name();

    /** Column name Historia */
    public static final String COLUMNNAME_Historia = "Historia";

	/** Set Unique Patient Identification.
	  * Unique Patient Identification
	  */
	public void setHistoria (String Historia);

	/** Get Unique Patient Identification.
	  * Unique Patient Identification
	  */
	public String getHistoria();

    /** Column name InstitutionalStatus */
    public static final String COLUMNNAME_InstitutionalStatus = "InstitutionalStatus";

	/** Set Institutional Status.
	  * Institutional Status
	  */
	public void setInstitutionalStatus (String InstitutionalStatus);

	/** Get Institutional Status.
	  * Institutional Status
	  */
	public String getInstitutionalStatus();

    /** Column name InstitutionalStatus_Name */
    public static final String COLUMNNAME_InstitutionalStatus_Name = "InstitutionalStatus_Name";

	/** Set Institutional Status Name	  */
	public void setInstitutionalStatus_Name (String InstitutionalStatus_Name);

	/** Get Institutional Status Name	  */
	public String getInstitutionalStatus_Name();

    /** Column name InstitutionalStep */
    public static final String COLUMNNAME_InstitutionalStep = "InstitutionalStep";

	/** Set Institutional Step.
	  * Insurance that has the liability for institutional charges
	  */
	public void setInstitutionalStep (String InstitutionalStep);

	/** Get Institutional Step.
	  * Insurance that has the liability for institutional charges
	  */
	public String getInstitutionalStep();

    /** Column name InstitutionalStep_Name */
    public static final String COLUMNNAME_InstitutionalStep_Name = "InstitutionalStep_Name";

	/** Set Institutional Step Name	  */
	public void setInstitutionalStep_Name (String InstitutionalStep_Name);

	/** Get Institutional Step Name	  */
	public String getInstitutionalStep_Name();

    /** Column name Last_Name */
    public static final String COLUMNNAME_Last_Name = "Last_Name";

	/** Set Last_Name	  */
	public void setLast_Name (String Last_Name);

	/** Get Last_Name	  */
	public String getLast_Name();

    /** Column name MRN */
    public static final String COLUMNNAME_MRN = "MRN";

	/** Set Medical Record Number.
	  * Medical Record Number
	  */
	public void setMRN (String MRN);

	/** Get Medical Record Number.
	  * Medical Record Number
	  */
	public String getMRN();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name Nombre */
    public static final String COLUMNNAME_Nombre = "Nombre";

	/** Set Name.
	  * Name of friend
	  */
	public void setNombre (String Nombre);

	/** Get Name.
	  * Name of friend
	  */
	public String getNombre();

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

    /** Column name NSS */
    public static final String COLUMNNAME_NSS = "NSS";

	/** Set Social Security Number	  */
	public void setNSS (String NSS);

	/** Get Social Security Number	  */
	public String getNSS();

    /** Column name PatientType */
    public static final String COLUMNNAME_PatientType = "PatientType";

	/** Set PatientType	  */
	public void setPatientType (String PatientType);

	/** Get PatientType	  */
	public String getPatientType();

    /** Column name ProfessionalStatus */
    public static final String COLUMNNAME_ProfessionalStatus = "ProfessionalStatus";

	/** Set Professional Status.
	  * Professional Status
	  */
	public void setProfessionalStatus (String ProfessionalStatus);

	/** Get Professional Status.
	  * Professional Status
	  */
	public String getProfessionalStatus();

    /** Column name ProfessionalStatus_Name */
    public static final String COLUMNNAME_ProfessionalStatus_Name = "ProfessionalStatus_Name";

	/** Set Professional Status Name	  */
	public void setProfessionalStatus_Name (String ProfessionalStatus_Name);

	/** Get Professional Status Name	  */
	public String getProfessionalStatus_Name();

    /** Column name ProfessionalStep */
    public static final String COLUMNNAME_ProfessionalStep = "ProfessionalStep";

	/** Set Professional Step.
	  * Insurance that has the liability for professional charges
	  */
	public void setProfessionalStep (String ProfessionalStep);

	/** Get Professional Step.
	  * Insurance that has the liability for professional charges
	  */
	public String getProfessionalStep();

    /** Column name ProfessionalStep_Name */
    public static final String COLUMNNAME_ProfessionalStep_Name = "ProfessionalStep_Name";

	/** Set Professional Step Name	  */
	public void setProfessionalStep_Name (String ProfessionalStep_Name);

	/** Get Professional Step Name	  */
	public String getProfessionalStep_Name();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (boolean Sexo);

	/** Get Sex.
	  * Sex
	  */
	public boolean isSexo();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name SuffixNSS */
    public static final String COLUMNNAME_SuffixNSS = "SuffixNSS";

	/** Set SSN	  */
	public void setSuffixNSS (String SuffixNSS);

	/** Get SSN	  */
	public String getSuffixNSS();

    /** Column name TelParticular */
    public static final String COLUMNNAME_TelParticular = "TelParticular";

	/** Set Home Phone.
	  * Home Phone
	  */
	public void setTelParticular (String TelParticular);

	/** Get Home Phone.
	  * Home Phone
	  */
	public String getTelParticular();
}
