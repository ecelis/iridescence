/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InsulinaEstudios
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_InsulinaEstudios 
{

    /** TableName=EXME_InsulinaEstudios */
    public static final String Table_Name = "EXME_InsulinaEstudios";

    /** AD_Table_ID=1200592 */
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

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException;

    /** Column name EXME_EncounterForms_ID */
    public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
	  * Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID);

	/** Get Encounter Forms.
	  * Encounter Forms
	  */
	public int getEXME_EncounterForms_ID();

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException;

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name EXME_EstudiosDiabeticos_ID */
    public static final String COLUMNNAME_EXME_EstudiosDiabeticos_ID = "EXME_EstudiosDiabeticos_ID";

	/** Set Diabetic Studies	  */
	public void setEXME_EstudiosDiabeticos_ID (int EXME_EstudiosDiabeticos_ID);

	/** Get Diabetic Studies	  */
	public int getEXME_EstudiosDiabeticos_ID();

	public I_EXME_EstudiosDiabeticos getEXME_EstudiosDiabeticos() throws RuntimeException;

    /** Column name EXME_InsulinaEstudios_ID */
    public static final String COLUMNNAME_EXME_InsulinaEstudios_ID = "EXME_InsulinaEstudios_ID";

	/** Set Insulin Studies	  */
	public void setEXME_InsulinaEstudios_ID (int EXME_InsulinaEstudios_ID);

	/** Get Insulin Studies	  */
	public int getEXME_InsulinaEstudios_ID();

    /** Column name FechaAplica */
    public static final String COLUMNNAME_FechaAplica = "FechaAplica";

	/** Set Date of Application.
	  * Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica);

	/** Get Date of Application.
	  * Date of Application
	  */
	public Timestamp getFechaAplica();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
