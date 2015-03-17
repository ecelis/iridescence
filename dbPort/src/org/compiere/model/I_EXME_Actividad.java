/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Actividad
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Actividad 
{

    /** TableName=EXME_Actividad */
    public static final String Table_Name = "EXME_Actividad";

    /** AD_Table_ID=1000019 */
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

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name EXME_Actividad_ID */
    public static final String COLUMNNAME_EXME_Actividad_ID = "EXME_Actividad_ID";

	/** Set Activity	  */
	public void setEXME_Actividad_ID (int EXME_Actividad_ID);

	/** Get Activity	  */
	public int getEXME_Actividad_ID();

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

    /** Column name EXME_TipoActividad_ID */
    public static final String COLUMNNAME_EXME_TipoActividad_ID = "EXME_TipoActividad_ID";

	/** Set Activity Type	  */
	public void setEXME_TipoActividad_ID (int EXME_TipoActividad_ID);

	/** Get Activity Type	  */
	public int getEXME_TipoActividad_ID();

	public I_EXME_TipoActividad getEXME_TipoActividad() throws RuntimeException;

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name UsaSustituto */
    public static final String COLUMNNAME_UsaSustituto = "UsaSustituto";

	/** Set Use Substitute.
	  * Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public void setUsaSustituto (boolean UsaSustituto);

	/** Get Use Substitute.
	  * Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public boolean isUsaSustituto();

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
