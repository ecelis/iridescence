/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_AltasVolunt
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_AltasVolunt 
{

    /** TableName=I_EXME_AltasVolunt */
    public static final String Table_Name = "I_EXME_AltasVolunt";

    /** AD_Table_ID=1200058 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name CamaAlta */
    public static final String COLUMNNAME_CamaAlta = "CamaAlta";

	/** Set Discharge Bed	  */
	public void setCamaAlta (String CamaAlta);

	/** Get Discharge Bed	  */
	public String getCamaAlta();

    /** Column name CodigoMed */
    public static final String COLUMNNAME_CodigoMed = "CodigoMed";

	/** Set Doctor Code	  */
	public void setCodigoMed (String CodigoMed);

	/** Get Doctor Code	  */
	public String getCodigoMed();

    /** Column name Consecutivo */
    public static final String COLUMNNAME_Consecutivo = "Consecutivo";

	/** Set Consecutive.
	  * Consecutive
	  */
	public void setConsecutivo (int Consecutivo);

	/** Get Consecutive.
	  * Consecutive
	  */
	public int getConsecutivo();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name EXME_AltasVolunt_ID */
    public static final String COLUMNNAME_EXME_AltasVolunt_ID = "EXME_AltasVolunt_ID";

	/** Set Voluntary Discharges.
	  * Voluntary Discharges from Hospital
	  */
	public void setEXME_AltasVolunt_ID (int EXME_AltasVolunt_ID);

	/** Get Voluntary Discharges.
	  * Voluntary Discharges from Hospital
	  */
	public int getEXME_AltasVolunt_ID();

	public I_EXME_AltasVolunt getEXME_AltasVolunt() throws RuntimeException;

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

	public I_EXME_Cama getEXME_Cama() throws RuntimeException;

    /** Column name EXME_Cama_Value */
    public static final String COLUMNNAME_EXME_Cama_Value = "EXME_Cama_Value";

	/** Set Bed Value	  */
	public void setEXME_Cama_Value (String EXME_Cama_Value);

	/** Get Bed Value	  */
	public String getEXME_Cama_Value();

    /** Column name EXME_Habitacion_Value */
    public static final String COLUMNNAME_EXME_Habitacion_Value = "EXME_Habitacion_Value";

	/** Set EXME_Habitacion_Value.
	  * Room
	  */
	public void setEXME_Habitacion_Value (String EXME_Habitacion_Value);

	/** Get EXME_Habitacion_Value.
	  * Room
	  */
	public String getEXME_Habitacion_Value();

    /** Column name EXME_Hist_Exp_ID */
    public static final String COLUMNNAME_EXME_Hist_Exp_ID = "EXME_Hist_Exp_ID";

	/** Set Medical File.
	  * Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID);

	/** Get Medical File.
	  * Medical File
	  */
	public int getEXME_Hist_Exp_ID();

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException;

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

    /** Column name EXME_Paciente_Value */
    public static final String COLUMNNAME_EXME_Paciente_Value = "EXME_Paciente_Value";

	/** Set Patient History Number.
	  * Patient History Number
	  */
	public void setEXME_Paciente_Value (String EXME_Paciente_Value);

	/** Get Patient History Number.
	  * Patient History Number
	  */
	public String getEXME_Paciente_Value();

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setExpediente (String Expediente);

	/** Get Medical Record.
	  * Medical Record
	  */
	public String getExpediente();

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

    /** Column name I_EXME_AltasVolunt_ID */
    public static final String COLUMNNAME_I_EXME_AltasVolunt_ID = "I_EXME_AltasVolunt_ID";

	/** Set Voluntary Discharge	  */
	public void setI_EXME_AltasVolunt_ID (int I_EXME_AltasVolunt_ID);

	/** Get Voluntary Discharge	  */
	public int getI_EXME_AltasVolunt_ID();

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

    /** Column name MedicoResp */
    public static final String COLUMNNAME_MedicoResp = "MedicoResp";

	/** Set Medical Manager.
	  * Medical Manager
	  */
	public void setMedicoResp (String MedicoResp);

	/** Get Medical Manager.
	  * Medical Manager
	  */
	public String getMedicoResp();

    /** Column name Motivo */
    public static final String COLUMNNAME_Motivo = "Motivo";

	/** Set Motive.
	  * Motive / Reason
	  */
	public void setMotivo (String Motivo);

	/** Get Motive.
	  * Motive / Reason
	  */
	public String getMotivo();

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
