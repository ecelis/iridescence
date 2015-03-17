/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_MedicoEsp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_MedicoEsp 
{

    /** TableName=I_EXME_MedicoEsp */
    public static final String Table_Name = "I_EXME_MedicoEsp";

    /** AD_Table_ID=1000158 */
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

    /** Column name EXME_MedicoEsp_ID */
    public static final String COLUMNNAME_EXME_MedicoEsp_ID = "EXME_MedicoEsp_ID";

	/** Set Specialty Doctor.
	  * Specialty Doctor
	  */
	public void setEXME_MedicoEsp_ID (int EXME_MedicoEsp_ID);

	/** Get Specialty Doctor.
	  * Specialty Doctor
	  */
	public int getEXME_MedicoEsp_ID();

	public I_EXME_MedicoEsp getEXME_MedicoEsp() throws RuntimeException;

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

    /** Column name EXME_Medico_Sust_ID */
    public static final String COLUMNNAME_EXME_Medico_Sust_ID = "EXME_Medico_Sust_ID";

	/** Set Substitute Doctor.
	  * Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID);

	/** Get Substitute Doctor.
	  * Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID();

	public I_EXME_Medico_Sust getEXME_Medico_Sust() throws RuntimeException;

    /** Column name HaceGuardia */
    public static final String COLUMNNAME_HaceGuardia = "HaceGuardia";

	/** Set Guards	  */
	public void setHaceGuardia (boolean HaceGuardia);

	/** Get Guards	  */
	public boolean isHaceGuardia();

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

    /** Column name I_EXME_MedicoEsp_ID */
    public static final String COLUMNNAME_I_EXME_MedicoEsp_ID = "I_EXME_MedicoEsp_ID";

	/** Set Imported Specialty-Doctor.
	  * Imorted Specialty Doctor
	  */
	public void setI_EXME_MedicoEsp_ID (int I_EXME_MedicoEsp_ID);

	/** Get Imported Specialty-Doctor.
	  * Imorted Specialty Doctor
	  */
	public int getI_EXME_MedicoEsp_ID();

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

    /** Column name MedicoSust_Value */
    public static final String COLUMNNAME_MedicoSust_Value = "MedicoSust_Value";

	/** Set Substituted Doctor Code.
	  * Substituted Doctor search code
	  */
	public void setMedicoSust_Value (String MedicoSust_Value);

	/** Get Substituted Doctor Code.
	  * Substituted Doctor search code
	  */
	public String getMedicoSust_Value();

    /** Column name Medico_Value */
    public static final String COLUMNNAME_Medico_Value = "Medico_Value";

	/** Set Doctor Code.
	  * Doctor search code
	  */
	public void setMedico_Value (String Medico_Value);

	/** Get Doctor Code.
	  * Doctor search code
	  */
	public String getMedico_Value();

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
}
