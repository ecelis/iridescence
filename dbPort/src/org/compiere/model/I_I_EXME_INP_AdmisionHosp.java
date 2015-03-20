/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_INP_AdmisionHosp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_INP_AdmisionHosp 
{

    /** TableName=I_EXME_INP_AdmisionHosp */
    public static final String Table_Name = "I_EXME_INP_AdmisionHosp";

    /** AD_Table_ID=1200129 */
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

    /** Column name Apellido1 */
    public static final String COLUMNNAME_Apellido1 = "Apellido1";

	/** Set Last Name.
	  * Last Name
	  */
	public void setApellido1 (String Apellido1);

	/** Get Last Name.
	  * Last Name
	  */
	public String getApellido1();

    /** Column name Apellido2 */
    public static final String COLUMNNAME_Apellido2 = "Apellido2";

	/** Set Mother's Maiden Name.
	  * Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2);

	/** Get Mother's Maiden Name.
	  * Mother's Maiden Name
	  */
	public String getApellido2();

    /** Column name Clasificacion_Value */
    public static final String COLUMNNAME_Clasificacion_Value = "Clasificacion_Value";

	/** Set Classification_Value	  */
	public void setClasificacion_Value (String Clasificacion_Value);

	/** Get Classification_Value	  */
	public String getClasificacion_Value();

    /** Column name Condi */
    public static final String COLUMNNAME_Condi = "Condi";

	/** Set Condi	  */
	public void setCondi (String Condi);

	/** Get Condi	  */
	public String getCondi();

    /** Column name EXME_ClasCliente_ID */
    public static final String COLUMNNAME_EXME_ClasCliente_ID = "EXME_ClasCliente_ID";

	/** Set Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID);

	/** Get Classification and Customer	  */
	public int getEXME_ClasCliente_ID();

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException;

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

    /** Column name EXME_INP_AdmisionHosp_ID */
    public static final String COLUMNNAME_EXME_INP_AdmisionHosp_ID = "EXME_INP_AdmisionHosp_ID";

	/** Set EXME_INP_AdmisionHosp_ID	  */
	public void setEXME_INP_AdmisionHosp_ID (int EXME_INP_AdmisionHosp_ID);

	/** Get EXME_INP_AdmisionHosp_ID	  */
	public int getEXME_INP_AdmisionHosp_ID();

	public I_EXME_INP_AdmisionHosp getEXME_INP_AdmisionHosp() throws RuntimeException;

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

    /** Column name Expediente_Value */
    public static final String COLUMNNAME_Expediente_Value = "Expediente_Value";

	/** Set Medical Record_Value	  */
	public void setExpediente_Value (String Expediente_Value);

	/** Get Medical Record_Value	  */
	public String getExpediente_Value();

    /** Column name Fecha_Apertura */
    public static final String COLUMNNAME_Fecha_Apertura = "Fecha_Apertura";

	/** Set Fecha_Apertura	  */
	public void setFecha_Apertura (Timestamp Fecha_Apertura);

	/** Get Fecha_Apertura	  */
	public Timestamp getFecha_Apertura();

    /** Column name Fecha_Defuncion */
    public static final String COLUMNNAME_Fecha_Defuncion = "Fecha_Defuncion";

	/** Set Fecha_Defuncion	  */
	public void setFecha_Defuncion (Timestamp Fecha_Defuncion);

	/** Get Fecha_Defuncion	  */
	public Timestamp getFecha_Defuncion();

    /** Column name Fecha_GuiaVerde */
    public static final String COLUMNNAME_Fecha_GuiaVerde = "Fecha_GuiaVerde";

	/** Set Fecha_GuiaVerde	  */
	public void setFecha_GuiaVerde (Timestamp Fecha_GuiaVerde);

	/** Get Fecha_GuiaVerde	  */
	public Timestamp getFecha_GuiaVerde();

    /** Column name Fecha_Microfilmacion */
    public static final String COLUMNNAME_Fecha_Microfilmacion = "Fecha_Microfilmacion";

	/** Set Fecha_Microfilmacion	  */
	public void setFecha_Microfilmacion (Timestamp Fecha_Microfilmacion);

	/** Get Fecha_Microfilmacion	  */
	public Timestamp getFecha_Microfilmacion();

    /** Column name FechaNac */
    public static final String COLUMNNAME_FechaNac = "FechaNac";

	/** Set Birth Date.
	  * Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac);

	/** Get Birth Date.
	  * Birth Date
	  */
	public Timestamp getFechaNac();

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

    /** Column name I_EXME_INP_AdmisionHosp_ID */
    public static final String COLUMNNAME_I_EXME_INP_AdmisionHosp_ID = "I_EXME_INP_AdmisionHosp_ID";

	/** Set I_EXME_INP_AdmisionHosp_ID	  */
	public void setI_EXME_INP_AdmisionHosp_ID (int I_EXME_INP_AdmisionHosp_ID);

	/** Get I_EXME_INP_AdmisionHosp_ID	  */
	public int getI_EXME_INP_AdmisionHosp_ID();

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

    /** Column name Microrrollo */
    public static final String COLUMNNAME_Microrrollo = "Microrrollo";

	/** Set Microrrollo	  */
	public void setMicrorrollo (String Microrrollo);

	/** Get Microrrollo	  */
	public String getMicrorrollo();

    /** Column name Nombre */
    public static final String COLUMNNAME_Nombre = "Nombre";

	/** Set Name.
	  * Name of friend
	  */
	public void setNombre (String Nombre);

	/** Get Name.
	  * Name of friend
	  */
	public String getNombre();

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

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (boolean Sexo);

	/** Get Sex.
	  * Sex
	  */
	public boolean isSexo();
}
