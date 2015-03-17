/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Defuncion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Defuncion 
{

    /** TableName=I_EXME_Defuncion */
    public static final String Table_Name = "I_EXME_Defuncion";

    /** AD_Table_ID=1200060 */
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

    /** Column name AvisoFamiliar */
    public static final String COLUMNNAME_AvisoFamiliar = "AvisoFamiliar";

	/** Set Relatives Notice.
	  * Notice to patient relatives
	  */
	public void setAvisoFamiliar (Timestamp AvisoFamiliar);

	/** Get Relatives Notice.
	  * Notice to patient relatives
	  */
	public Timestamp getAvisoFamiliar();

    /** Column name AvisoTrabSoc */
    public static final String COLUMNNAME_AvisoTrabSoc = "AvisoTrabSoc";

	/** Set Social Work Notice.
	  * Notice to Social work
	  */
	public void setAvisoTrabSoc (Timestamp AvisoTrabSoc);

	/** Get Social Work Notice.
	  * Notice to Social work
	  */
	public Timestamp getAvisoTrabSoc();

    /** Column name Direccion */
    public static final String COLUMNNAME_Direccion = "Direccion";

	/** Set Address	  */
	public void setDireccion (String Direccion);

	/** Get Address	  */
	public String getDireccion();

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

    /** Column name EXME_Defuncion_ID */
    public static final String COLUMNNAME_EXME_Defuncion_ID = "EXME_Defuncion_ID";

	/** Set Deaths.
	  * Deaths
	  */
	public void setEXME_Defuncion_ID (int EXME_Defuncion_ID);

	/** Get Deaths.
	  * Deaths
	  */
	public int getEXME_Defuncion_ID();

	public I_EXME_Defuncion getEXME_Defuncion() throws RuntimeException;

    /** Column name EXME_Habitacion_ID */
    public static final String COLUMNNAME_EXME_Habitacion_ID = "EXME_Habitacion_ID";

	/** Set Room.
	  * Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID);

	/** Get Room.
	  * Room
	  */
	public int getEXME_Habitacion_ID();

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException;

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

    /** Column name FechaHr */
    public static final String COLUMNNAME_FechaHr = "FechaHr";

	/** Set Hr and Date.
	  * Hr and Date
	  */
	public void setFechaHr (Timestamp FechaHr);

	/** Get Hr and Date.
	  * Hr and Date
	  */
	public Timestamp getFechaHr();

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

    /** Column name I_EXME_Defuncion_ID */
    public static final String COLUMNNAME_I_EXME_Defuncion_ID = "I_EXME_Defuncion_ID";

	/** Set Decease	  */
	public void setI_EXME_Defuncion_ID (int I_EXME_Defuncion_ID);

	/** Get Decease	  */
	public int getI_EXME_Defuncion_ID();

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

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

    /** Column name Observacion */
    public static final String COLUMNNAME_Observacion = "Observacion";

	/** Set Observation.
	  * Observation
	  */
	public void setObservacion (String Observacion);

	/** Get Observation.
	  * Observation
	  */
	public String getObservacion();

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

    /** Column name TSCama */
    public static final String COLUMNNAME_TSCama = "TSCama";

	/** Set Social Work Bed	  */
	public void setTSCama (String TSCama);

	/** Get Social Work Bed	  */
	public String getTSCama();

    /** Column name TSClinico */
    public static final String COLUMNNAME_TSClinico = "TSClinico";

	/** Set Social Work Clinical.
	  * Social Work Clinical
	  */
	public void setTSClinico (String TSClinico);

	/** Get Social Work Clinical.
	  * Social Work Clinical
	  */
	public String getTSClinico();
}
