/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Plantilla
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Plantilla 
{

    /** TableName=EXME_Plantilla */
    public static final String Table_Name = "EXME_Plantilla";

    /** AD_Table_ID=1201023 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name Category */
    public static final String COLUMNNAME_Category = "Category";

	/** Set Category	  */
	public void setCategory (String Category);

	/** Get Category	  */
	public String getCategory();

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

    /** Column name EXME_ClasificacionPlantilla_ID */
    public static final String COLUMNNAME_EXME_ClasificacionPlantilla_ID = "EXME_ClasificacionPlantilla_ID";

	/** Set Classification Template	  */
	public void setEXME_ClasificacionPlantilla_ID (int EXME_ClasificacionPlantilla_ID);

	/** Get Classification Template	  */
	public int getEXME_ClasificacionPlantilla_ID();

	public I_EXME_ClasificacionPlantilla getEXME_ClasificacionPlantilla() throws RuntimeException;

    /** Column name EXME_Cuestionario_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ID = "EXME_Cuestionario_ID";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public int getEXME_Cuestionario_ID();

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException;

    /** Column name EXME_Plantilla_ID */
    public static final String COLUMNNAME_EXME_Plantilla_ID = "EXME_Plantilla_ID";

	/** Set Template	  */
	public void setEXME_Plantilla_ID (int EXME_Plantilla_ID);

	/** Get Template	  */
	public int getEXME_Plantilla_ID();

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

    /** Column name Plantilla */
    public static final String COLUMNNAME_Plantilla = "Plantilla";

	/** Set Template	  */
	public void setPlantilla (byte[] Plantilla);

	/** Get Template	  */
	public byte[] getPlantilla();

    /** Column name Query */
    public static final String COLUMNNAME_Query = "Query";

	/** Set Query.
	  * SQL
	  */
	public void setQuery (String Query);

	/** Get Query.
	  * SQL
	  */
	public String getQuery();
}
