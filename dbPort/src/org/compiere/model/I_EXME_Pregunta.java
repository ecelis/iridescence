/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Pregunta
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Pregunta 
{

    /** TableName=EXME_Pregunta */
    public static final String Table_Name = "EXME_Pregunta";

    /** AD_Table_ID=1000020 */
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

    /** Column name EsCore */
    public static final String COLUMNNAME_EsCore = "EsCore";

	/** Set Public Domain.
	  * Public Domain
	  */
	public void setEsCore (boolean EsCore);

	/** Get Public Domain.
	  * Public Domain
	  */
	public boolean isEsCore();

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

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

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

    /** Column name FileContent */
    public static final String COLUMNNAME_FileContent = "FileContent";

	/** Set File Content	  */
	public void setFileContent (byte[] FileContent);

	/** Get File Content	  */
	public byte[] getFileContent();

    /** Column name HideLabel */
    public static final String COLUMNNAME_HideLabel = "HideLabel";

	/** Set Hide Label	  */
	public void setHideLabel (boolean HideLabel);

	/** Get Hide Label	  */
	public boolean isHideLabel();

    /** Column name Mensaje */
    public static final String COLUMNNAME_Mensaje = "Mensaje";

	/** Set Message	  */
	public void setMensaje (String Mensaje);

	/** Get Message	  */
	public String getMensaje();

    /** Column name Mensaje_Valor1 */
    public static final String COLUMNNAME_Mensaje_Valor1 = "Mensaje_Valor1";

	/** Set Message First Value	  */
	public void setMensaje_Valor1 (BigDecimal Mensaje_Valor1);

	/** Get Message First Value	  */
	public BigDecimal getMensaje_Valor1();

    /** Column name Mensaje_Valor2 */
    public static final String COLUMNNAME_Mensaje_Valor2 = "Mensaje_Valor2";

	/** Set Message Second Value	  */
	public void setMensaje_Valor2 (BigDecimal Mensaje_Valor2);

	/** Get Message Second Value	  */
	public BigDecimal getMensaje_Valor2();

    /** Column name Multiple */
    public static final String COLUMNNAME_Multiple = "Multiple";

	/** Set Multiple	  */
	public void setMultiple (boolean Multiple);

	/** Get Multiple	  */
	public boolean isMultiple();

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

    /** Column name NColumns */
    public static final String COLUMNNAME_NColumns = "NColumns";

	/** Set Columns	  */
	public void setNColumns (int NColumns);

	/** Get Columns	  */
	public int getNColumns();

    /** Column name NRows */
    public static final String COLUMNNAME_NRows = "NRows";

	/** Set Rows	  */
	public void setNRows (int NRows);

	/** Get Rows	  */
	public int getNRows();

    /** Column name Obligatoria */
    public static final String COLUMNNAME_Obligatoria = "Obligatoria";

	/** Set Mandatory.
	  * Mandatory
	  */
	public void setObligatoria (boolean Obligatoria);

	/** Get Mandatory.
	  * Mandatory
	  */
	public boolean isObligatoria();

    /** Column name PageSize */
    public static final String COLUMNNAME_PageSize = "PageSize";

	/** Set Page Size	  */
	public void setPageSize (int PageSize);

	/** Get Page Size	  */
	public int getPageSize();

    /** Column name PrintName */
    public static final String COLUMNNAME_PrintName = "PrintName";

	/** Set Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName);

	/** Get Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public String getPrintName();

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

    /** Column name TipoDato */
    public static final String COLUMNNAME_TipoDato = "TipoDato";

	/** Set Data Type.
	  * Data Type
	  */
	public void setTipoDato (String TipoDato);

	/** Get Data Type.
	  * Data Type
	  */
	public String getTipoDato();

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

    /** Column name X */
    public static final String COLUMNNAME_X = "X";

	/** Set Aisle (X).
	  * X dimension, e.g., Aisle
	  */
	public void setX (int X);

	/** Get Aisle (X).
	  * X dimension, e.g., Aisle
	  */
	public int getX();

    /** Column name Y */
    public static final String COLUMNNAME_Y = "Y";

	/** Set Bin (Y).
	  * Y dimension, e.g., Bin
	  */
	public void setY (int Y);

	/** Get Bin (Y).
	  * Y dimension, e.g., Bin
	  */
	public int getY();
}
