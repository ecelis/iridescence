/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Notas_Trabajador
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Notas_Trabajador 
{

    /** TableName=I_EXME_Notas_Trabajador */
    public static final String Table_Name = "I_EXME_Notas_Trabajador";

    /** AD_Table_ID=1200063 */
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

    /** Column name EXME_Notas_Trabajador_ID */
    public static final String COLUMNNAME_EXME_Notas_Trabajador_ID = "EXME_Notas_Trabajador_ID";

	/** Set Notes of the Social Worker.
	  * Notes of the Social Worker
	  */
	public void setEXME_Notas_Trabajador_ID (int EXME_Notas_Trabajador_ID);

	/** Get Notes of the Social Worker.
	  * Notes of the Social Worker
	  */
	public int getEXME_Notas_Trabajador_ID();

	public I_EXME_Notas_Trabajador getEXME_Notas_Trabajador() throws RuntimeException;

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

    /** Column name Fecha_Nota */
    public static final String COLUMNNAME_Fecha_Nota = "Fecha_Nota";

	/** Set Note Date.
	  * Note Date
	  */
	public void setFecha_Nota (Timestamp Fecha_Nota);

	/** Get Note Date.
	  * Note Date
	  */
	public Timestamp getFecha_Nota();

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

    /** Column name I_EXME_Notas_Trabajador_ID */
    public static final String COLUMNNAME_I_EXME_Notas_Trabajador_ID = "I_EXME_Notas_Trabajador_ID";

	/** Set Social Worker Notes	  */
	public void setI_EXME_Notas_Trabajador_ID (int I_EXME_Notas_Trabajador_ID);

	/** Get Social Worker Notes	  */
	public int getI_EXME_Notas_Trabajador_ID();

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

    /** Column name Nota */
    public static final String COLUMNNAME_Nota = "Nota";

	/** Set Note.
	  * Note
	  */
	public void setNota (String Nota);

	/** Get Note.
	  * Note
	  */
	public String getNota();

    /** Column name Nota2 */
    public static final String COLUMNNAME_Nota2 = "Nota2";

	/** Set Note2	  */
	public void setNota2 (String Nota2);

	/** Get Note2	  */
	public String getNota2();

    /** Column name Nota3 */
    public static final String COLUMNNAME_Nota3 = "Nota3";

	/** Set Nota3	  */
	public void setNota3 (String Nota3);

	/** Get Nota3	  */
	public String getNota3();

    /** Column name NumHist */
    public static final String COLUMNNAME_NumHist = "NumHist";

	/** Set History Number	  */
	public void setNumHist (String NumHist);

	/** Get History Number	  */
	public String getNumHist();

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
