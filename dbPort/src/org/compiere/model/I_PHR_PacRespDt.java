/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_PacRespDt
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_PacRespDt 
{

    /** TableName=PHR_PacRespDt */
    public static final String Table_Name = "PHR_PacRespDt";

    /** AD_Table_ID=1201017 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name ImagenBinary */
    public static final String COLUMNNAME_ImagenBinary = "ImagenBinary";

	/** Set Binary Image	  */
	public void setImagenBinary (byte[] ImagenBinary);

	/** Get Binary Image	  */
	public byte[] getImagenBinary();

    /** Column name PHR_PacCuest_ID */
    public static final String COLUMNNAME_PHR_PacCuest_ID = "PHR_PacCuest_ID";

	/** Set Patient Questionaire	  */
	public void setPHR_PacCuest_ID (int PHR_PacCuest_ID);

	/** Get Patient Questionaire	  */
	public int getPHR_PacCuest_ID();

	public I_PHR_PacCuest getPHR_PacCuest() throws RuntimeException;

    /** Column name PHR_PacRespDt_ID */
    public static final String COLUMNNAME_PHR_PacRespDt_ID = "PHR_PacRespDt_ID";

	/** Set Detail of Response Patient	  */
	public void setPHR_PacRespDt_ID (int PHR_PacRespDt_ID);

	/** Get Detail of Response Patient	  */
	public int getPHR_PacRespDt_ID();

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

    /** Column name Respuesta */
    public static final String COLUMNNAME_Respuesta = "Respuesta";

	/** Set Answer.
	  * Answer
	  */
	public void setRespuesta (String Respuesta);

	/** Get Answer.
	  * Answer
	  */
	public String getRespuesta();

    /** Column name RutaImagen */
    public static final String COLUMNNAME_RutaImagen = "RutaImagen";

	/** Set Image Route.
	  * Image Route
	  */
	public void setRutaImagen (String RutaImagen);

	/** Get Image Route.
	  * Image Route
	  */
	public String getRutaImagen();

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (int Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public int getSecuencia();

    /** Column name TextBinary */
    public static final String COLUMNNAME_TextBinary = "TextBinary";

	/** Set Binary Text	  */
	public void setTextBinary (String TextBinary);

	/** Get Binary Text	  */
	public String getTextBinary();
}
