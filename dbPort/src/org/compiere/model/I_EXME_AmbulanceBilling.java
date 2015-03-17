/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AmbulanceBilling
 *  @author Generated Class 
 *  @version Release 1.2.0
 */
public interface I_EXME_AmbulanceBilling 
{

    /** TableName=EXME_AmbulanceBilling */
    public static final String Table_Name = "EXME_AmbulanceBilling";

    /** AD_Table_ID=1201211 */
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

    /** Column name Destination */
    public static final String COLUMNNAME_Destination = "Destination";

	/** Set Destination	  */
	public void setDestination (String Destination);

	/** Get Destination	  */
	public String getDestination();

    /** Column name EXME_AmbulanceBilling_ID */
    public static final String COLUMNNAME_EXME_AmbulanceBilling_ID = "EXME_AmbulanceBilling_ID";

	/** Set Ambulance Billing	  */
	public void setEXME_AmbulanceBilling_ID (int EXME_AmbulanceBilling_ID);

	/** Get Ambulance Billing	  */
	public int getEXME_AmbulanceBilling_ID();

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

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Diagnostico2_ID */
    public static final String COLUMNNAME_EXME_Diagnostico2_ID = "EXME_Diagnostico2_ID";

	/** Set Second Diagnostic.
	  * Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID);

	/** Get Second Diagnostic.
	  * Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID();

    /** Column name EXME_Diagnostico3_ID */
    public static final String COLUMNNAME_EXME_Diagnostico3_ID = "EXME_Diagnostico3_ID";

	/** Set Third Diagnostic.
	  * Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID);

	/** Get Third Diagnostic.
	  * Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID();

    /** Column name EXME_Diagnostico4_ID */
    public static final String COLUMNNAME_EXME_Diagnostico4_ID = "EXME_Diagnostico4_ID";

	/** Set Fourth Diagnosis	  */
	public void setEXME_Diagnostico4_ID (int EXME_Diagnostico4_ID);

	/** Get Fourth Diagnosis	  */
	public int getEXME_Diagnostico4_ID();

    /** Column name Land */
    public static final String COLUMNNAME_Land = "Land";

	/** Set Land	  */
	public void setLand (boolean Land);

	/** Get Land	  */
	public boolean isLand();

    /** Column name MedicalCondition */
    public static final String COLUMNNAME_MedicalCondition = "MedicalCondition";

	/** Set Medical Condition Validation	  */
	public void setMedicalCondition (String MedicalCondition);

	/** Get Medical Condition Validation	  */
	public String getMedicalCondition();

    /** Column name Origin */
    public static final String COLUMNNAME_Origin = "Origin";

	/** Set Origin	  */
	public void setOrigin (String Origin);

	/** Get Origin	  */
	public String getOrigin();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();
}
