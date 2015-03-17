/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Cta_Payer
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Cta_Payer 
{

    /** TableName=EXME_Cta_Payer */
    public static final String Table_Name = "EXME_Cta_Payer";

    /** AD_Table_ID=1201313 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Encounter */
    public static final String COLUMNNAME_Encounter = "Encounter";

	/** Set Document No.
	  * Document No
	  */
	public void setEncounter (String Encounter);

	/** Get Document No.
	  * Document No
	  */
	public String getEncounter();

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

    /** Column name EXME_Cta_Payer_ID */
    public static final String COLUMNNAME_EXME_Cta_Payer_ID = "EXME_Cta_Payer_ID";

	/** Set Encounter and Payer View ID.
	  * Encounter and Payer View ID
	  */
	public void setEXME_Cta_Payer_ID (int EXME_Cta_Payer_ID);

	/** Get Encounter and Payer View ID.
	  * Encounter and Payer View ID
	  */
	public int getEXME_Cta_Payer_ID();

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

    /** Column name SupportBilling */
    public static final String COLUMNNAME_SupportBilling = "SupportBilling";

	/** Set Support Billing	  */
	public void setSupportBilling (String SupportBilling);

	/** Get Support Billing	  */
	public String getSupportBilling();
}
