/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Pregunta
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Pregunta 
{

    /** TableName=I_EXME_Pregunta */
    public static final String Table_Name = "I_EXME_Pregunta";

    /** AD_Table_ID=1200407 */
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

    /** Column name Especialidad_Value */
    public static final String COLUMNNAME_Especialidad_Value = "Especialidad_Value";

	/** Set Specialty Value.
	  * Specialty search value
	  */
	public void setEspecialidad_Value (String Especialidad_Value);

	/** Get Specialty Value.
	  * Specialty search value
	  */
	public String getEspecialidad_Value();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

    /** Column name EXME_Pregunta_ID */
    public static final String COLUMNNAME_EXME_Pregunta_ID = "EXME_Pregunta_ID";

	/** Set Question.
	  * Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID);

	/** Get Question.
	  * Question
	  */
	public int getEXME_Pregunta_ID();

	public I_EXME_Pregunta getEXME_Pregunta() throws RuntimeException;

    /** Column name EXME_Pregunta_Lista_ID */
    public static final String COLUMNNAME_EXME_Pregunta_Lista_ID = "EXME_Pregunta_Lista_ID";

	/** Set Fixed Answer.
	  * Fixed answer for the question in the clinic questionnaire
	  */
	public void setEXME_Pregunta_Lista_ID (int EXME_Pregunta_Lista_ID);

	/** Get Fixed Answer.
	  * Fixed answer for the question in the clinic questionnaire
	  */
	public int getEXME_Pregunta_Lista_ID();

	public I_EXME_Pregunta_Lista getEXME_Pregunta_Lista() throws RuntimeException;

    /** Column name EXME_TipoPregunta_ID */
    public static final String COLUMNNAME_EXME_TipoPregunta_ID = "EXME_TipoPregunta_ID";

	/** Set Type of Question.
	  * Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID);

	/** Get Type of Question.
	  * Type of Question
	  */
	public int getEXME_TipoPregunta_ID();

	public I_EXME_TipoPregunta getEXME_TipoPregunta() throws RuntimeException;

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

    /** Column name I_EXME_Pregunta_ID */
    public static final String COLUMNNAME_I_EXME_Pregunta_ID = "I_EXME_Pregunta_ID";

	/** Set Questionnaires import interface.
	  * Questionnaires import interface
	  */
	public void setI_EXME_Pregunta_ID (int I_EXME_Pregunta_ID);

	/** Get Questionnaires import interface.
	  * Questionnaires import interface
	  */
	public int getI_EXME_Pregunta_ID();

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

    /** Column name Pregunta_Description */
    public static final String COLUMNNAME_Pregunta_Description = "Pregunta_Description";

	/** Set Question Description.
	  * Question Description
	  */
	public void setPregunta_Description (String Pregunta_Description);

	/** Get Question Description.
	  * Question Description
	  */
	public String getPregunta_Description();

    /** Column name Pregunta_Lista_Description */
    public static final String COLUMNNAME_Pregunta_Lista_Description = "Pregunta_Lista_Description";

	/** Set Question's Fixed Answer Description.
	  * Question's Fixed Answer Description
	  */
	public void setPregunta_Lista_Description (String Pregunta_Lista_Description);

	/** Get Question's Fixed Answer Description.
	  * Question's Fixed Answer Description
	  */
	public String getPregunta_Lista_Description();

    /** Column name Pregunta_Lista_Name */
    public static final String COLUMNNAME_Pregunta_Lista_Name = "Pregunta_Lista_Name";

	/** Set Question's Fixed Answer Name.
	  * Question's Fixed Answer Name
	  */
	public void setPregunta_Lista_Name (String Pregunta_Lista_Name);

	/** Get Question's Fixed Answer Name.
	  * Question's Fixed Answer Name
	  */
	public String getPregunta_Lista_Name();

    /** Column name Pregunta_Lista_Value */
    public static final String COLUMNNAME_Pregunta_Lista_Value = "Pregunta_Lista_Value";

	/** Set Question's Fixed Answer Value.
	  * Question's Fixed Answer Value
	  */
	public void setPregunta_Lista_Value (String Pregunta_Lista_Value);

	/** Get Question's Fixed Answer Value.
	  * Question's Fixed Answer Value
	  */
	public String getPregunta_Lista_Value();

    /** Column name Pregunta_Name */
    public static final String COLUMNNAME_Pregunta_Name = "Pregunta_Name";

	/** Set Question's Name.
	  * Question's Name
	  */
	public void setPregunta_Name (String Pregunta_Name);

	/** Get Question's Name.
	  * Question's Name
	  */
	public String getPregunta_Name();

    /** Column name Pregunta_TipoDato */
    public static final String COLUMNNAME_Pregunta_TipoDato = "Pregunta_TipoDato";

	/** Set Question's Data Type.
	  * Question's Data Type
	  */
	public void setPregunta_TipoDato (String Pregunta_TipoDato);

	/** Get Question's Data Type.
	  * Question's Data Type
	  */
	public String getPregunta_TipoDato();

    /** Column name Pregunta_Value */
    public static final String COLUMNNAME_Pregunta_Value = "Pregunta_Value";

	/** Set Question's Value.
	  * Question's Value
	  */
	public void setPregunta_Value (String Pregunta_Value);

	/** Get Question's Value.
	  * Question's Value
	  */
	public String getPregunta_Value();

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

    /** Column name TipoPregunta_Description */
    public static final String COLUMNNAME_TipoPregunta_Description = "TipoPregunta_Description";

	/** Set Question Type's Description.
	  * Question Type's Description
	  */
	public void setTipoPregunta_Description (String TipoPregunta_Description);

	/** Get Question Type's Description.
	  * Question Type's Description
	  */
	public String getTipoPregunta_Description();

    /** Column name TipoPregunta_Name */
    public static final String COLUMNNAME_TipoPregunta_Name = "TipoPregunta_Name";

	/** Set Question Type's Name.
	  * Question Type's Name
	  */
	public void setTipoPregunta_Name (String TipoPregunta_Name);

	/** Get Question Type's Name.
	  * Question Type's Name
	  */
	public String getTipoPregunta_Name();

    /** Column name TipoPregunta_Value */
    public static final String COLUMNNAME_TipoPregunta_Value = "TipoPregunta_Value";

	/** Set Question Type's Value.
	  * Question Type's Value
	  */
	public void setTipoPregunta_Value (String TipoPregunta_Value);

	/** Get Question Type's Value.
	  * Question Type's Value
	  */
	public String getTipoPregunta_Value();
}
