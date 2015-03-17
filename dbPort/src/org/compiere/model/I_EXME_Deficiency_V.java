/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Deficiency_V
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Deficiency_V 
{

    /** TableName=EXME_Deficiency_V */
    public static final String Table_Name = "EXME_Deficiency_V";

    /** AD_Table_ID=1201387 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name AssignedTo */
    public static final String COLUMNNAME_AssignedTo = "AssignedTo";

	/** Set Assigned To	  */
	public void setAssignedTo (String AssignedTo);

	/** Get Assigned To	  */
	public String getAssignedTo();

    /** Column name DaysDeficient */
    public static final String COLUMNNAME_DaysDeficient = "DaysDeficient";

	/** Set Days Deficient	  */
	public void setDaysDeficient (String DaysDeficient);

	/** Get Days Deficient	  */
	public String getDaysDeficient();

    /** Column name DefGroup */
    public static final String COLUMNNAME_DefGroup = "DefGroup";

	/** Set DefGroup	  */
	public void setDefGroup (String DefGroup);

	/** Get DefGroup	  */
	public String getDefGroup();

    /** Column name Deficiency */
    public static final String COLUMNNAME_Deficiency = "Deficiency";

	/** Set Deficiency	  */
	public void setDeficiency (String Deficiency);

	/** Get Deficiency	  */
	public String getDeficiency();

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

    /** Column name EXME_Deficiency_V_ID */
    public static final String COLUMNNAME_EXME_Deficiency_V_ID = "EXME_Deficiency_V_ID";

	/** Set EXME_Deficiency_V_ID	  */
	public void setEXME_Deficiency_V_ID (int EXME_Deficiency_V_ID);

	/** Get EXME_Deficiency_V_ID	  */
	public int getEXME_Deficiency_V_ID();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

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

    /** Column name PatientName */
    public static final String COLUMNNAME_PatientName = "PatientName";

	/** Set PatientName	  */
	public void setPatientName (String PatientName);

	/** Get PatientName	  */
	public String getPatientName();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();
}
