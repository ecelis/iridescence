/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Ocupacion_Clas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Ocupacion_Clas 
{

    /** TableName=I_EXME_Ocupacion_Clas */
    public static final String Table_Name = "I_EXME_Ocupacion_Clas";

    /** AD_Table_ID=1200068 */
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

    /** Column name EXME_Ocupacion_Clas_ID */
    public static final String COLUMNNAME_EXME_Ocupacion_Clas_ID = "EXME_Ocupacion_Clas_ID";

	/** Set Classified Ocupation.
	  * Classified Ocupation
	  */
	public void setEXME_Ocupacion_Clas_ID (int EXME_Ocupacion_Clas_ID);

	/** Get Classified Ocupation.
	  * Classified Ocupation
	  */
	public int getEXME_Ocupacion_Clas_ID();

	public I_EXME_Ocupacion_Clas getEXME_Ocupacion_Clas() throws RuntimeException;

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Ocupacion_Clas_ID */
    public static final String COLUMNNAME_I_EXME_Ocupacion_Clas_ID = "I_EXME_Ocupacion_Clas_ID";

	/** Set Occupation Class	  */
	public void setI_EXME_Ocupacion_Clas_ID (int I_EXME_Ocupacion_Clas_ID);

	/** Get Occupation Class	  */
	public int getI_EXME_Ocupacion_Clas_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Puntaje */
    public static final String COLUMNNAME_Puntaje = "Puntaje";

	/** Set Score.
	  * Score
	  */
	public void setPuntaje (BigDecimal Puntaje);

	/** Get Score.
	  * Score
	  */
	public BigDecimal getPuntaje();

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
