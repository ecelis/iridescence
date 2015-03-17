/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_EnviosPago
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_EnviosPago 
{

    /** TableName=I_EXME_EnviosPago */
    public static final String Table_Name = "I_EXME_EnviosPago";

    /** AD_Table_ID=1200088 */
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

    /** Column name Clasificacion */
    public static final String COLUMNNAME_Clasificacion = "Clasificacion";

	/** Set Classification.
	  * Classification
	  */
	public void setClasificacion (String Clasificacion);

	/** Get Classification.
	  * Classification
	  */
	public String getClasificacion();

    /** Column name ConcPago */
    public static final String COLUMNNAME_ConcPago = "ConcPago";

	/** Set Payment Concept.
	  * Payment Concept
	  */
	public void setConcPago (String ConcPago);

	/** Get Payment Concept.
	  * Payment Concept
	  */
	public String getConcPago();

    /** Column name Debe */
    public static final String COLUMNNAME_Debe = "Debe";

	/** Set Debit.
	  * Debit
	  */
	public void setDebe (BigDecimal Debe);

	/** Get Debit.
	  * Debit
	  */
	public BigDecimal getDebe();

    /** Column name EXME_EnviosPago_ID */
    public static final String COLUMNNAME_EXME_EnviosPago_ID = "EXME_EnviosPago_ID";

	/** Set Shipping Payment.
	  * Shipping Payment
	  */
	public void setEXME_EnviosPago_ID (int EXME_EnviosPago_ID);

	/** Get Shipping Payment.
	  * Shipping Payment
	  */
	public int getEXME_EnviosPago_ID();

	public I_EXME_EnviosPago getEXME_EnviosPago() throws RuntimeException;

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

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

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

    /** Column name I_EXME_EnviosPago_ID */
    public static final String COLUMNNAME_I_EXME_EnviosPago_ID = "I_EXME_EnviosPago_ID";

	/** Set I_EXME_EnviosPago_ID	  */
	public void setI_EXME_EnviosPago_ID (int I_EXME_EnviosPago_ID);

	/** Get I_EXME_EnviosPago_ID	  */
	public int getI_EXME_EnviosPago_ID();

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

    /** Column name Paga */
    public static final String COLUMNNAME_Paga = "Paga";

	/** Set Pay.
	  * Pay
	  */
	public void setPaga (BigDecimal Paga);

	/** Get Pay.
	  * Pay
	  */
	public BigDecimal getPaga();

    /** Column name Pagado */
    public static final String COLUMNNAME_Pagado = "Pagado";

	/** Set Paid	  */
	public void setPagado (boolean Pagado);

	/** Get Paid	  */
	public boolean isPagado();

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

    /** Column name TS */
    public static final String COLUMNNAME_TS = "TS";

	/** Set Social Worker.
	  * Social Worker
	  */
	public void setTS (String TS);

	/** Get Social Worker.
	  * Social Worker
	  */
	public String getTS();
}
