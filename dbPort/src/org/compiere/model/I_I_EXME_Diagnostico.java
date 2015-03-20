/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Diagnostico
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Diagnostico 
{

    /** TableName=I_EXME_Diagnostico */
    public static final String Table_Name = "I_EXME_Diagnostico";

    /** AD_Table_ID=1000150 */
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

    /** Column name CodGrd */
    public static final String COLUMNNAME_CodGrd = "CodGrd";

	/** Set Code GRD.
	  * Code GRD
	  */
	public void setCodGrd (String CodGrd);

	/** Get Code GRD.
	  * Code GRD
	  */
	public String getCodGrd();

    /** Column name CodInegi */
    public static final String COLUMNNAME_CodInegi = "CodInegi";

	/** Set Code INEGI.
	  * Code INEGI
	  */
	public void setCodInegi (String CodInegi);

	/** Get Code INEGI.
	  * Code INEGI
	  */
	public String getCodInegi();

    /** Column name CodOms */
    public static final String COLUMNNAME_CodOms = "CodOms";

	/** Set World Organization Health Code.
	  * World Organization Health Code
	  */
	public void setCodOms (String CodOms);

	/** Get World Organization Health Code.
	  * World Organization Health Code
	  */
	public String getCodOms();

    /** Column name CodSnomed */
    public static final String COLUMNNAME_CodSnomed = "CodSnomed";

	/** Set Snomed Code.
	  * Snomed Code
	  */
	public void setCodSnomed (String CodSnomed);

	/** Get Snomed Code.
	  * Snomed Code
	  */
	public String getCodSnomed();

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

    /** Column name EXME_DiagnosticoHdr_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoHdr_ID = "EXME_DiagnosticoHdr_ID";

	/** Set ICD.
	  * International Classification of Diseases
	  */
	public void setEXME_DiagnosticoHdr_ID (int EXME_DiagnosticoHdr_ID);

	/** Get ICD.
	  * International Classification of Diseases
	  */
	public int getEXME_DiagnosticoHdr_ID();

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

    /** Column name I_EXME_Diagnostico_ID */
    public static final String COLUMNNAME_I_EXME_Diagnostico_ID = "I_EXME_Diagnostico_ID";

	/** Set Imported Diagnostic.
	  * Imported Diagnostic
	  */
	public void setI_EXME_Diagnostico_ID (int I_EXME_Diagnostico_ID);

	/** Get Imported Diagnostic.
	  * Imported Diagnostic
	  */
	public int getI_EXME_Diagnostico_ID();

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

    /** Column name ValueHdr */
    public static final String COLUMNNAME_ValueHdr = "ValueHdr";

	/** Set Header Value	  */
	public void setValueHdr (String ValueHdr);

	/** Get Header Value	  */
	public String getValueHdr();
}
