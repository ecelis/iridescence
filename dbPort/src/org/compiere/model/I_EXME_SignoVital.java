/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SignoVital
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SignoVital 
{

    /** TableName=EXME_SignoVital */
    public static final String Table_Name = "EXME_SignoVital";

    /** AD_Table_ID=1200103 */
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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name EXME_FormulaSigVital_ID */
    public static final String COLUMNNAME_EXME_FormulaSigVital_ID = "EXME_FormulaSigVital_ID";

	/** Set Vital Signs Formula	  */
	public void setEXME_FormulaSigVital_ID (int EXME_FormulaSigVital_ID);

	/** Get Vital Signs Formula	  */
	public int getEXME_FormulaSigVital_ID();

	public I_EXME_FormulaSigVital getEXME_FormulaSigVital() throws RuntimeException;

    /** Column name EXME_SignoVital_ID */
    public static final String COLUMNNAME_EXME_SignoVital_ID = "EXME_SignoVital_ID";

	/** Set Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID);

	/** Get Vital Sign	  */
	public int getEXME_SignoVital_ID();

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

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();

    /** Column name ValorMax */
    public static final String COLUMNNAME_ValorMax = "ValorMax";

	/** Set ValorMax	  */
	public void setValorMax (BigDecimal ValorMax);

	/** Get ValorMax	  */
	public BigDecimal getValorMax();

    /** Column name ValorMin */
    public static final String COLUMNNAME_ValorMin = "ValorMin";

	/** Set ValorMin	  */
	public void setValorMin (BigDecimal ValorMin);

	/** Get ValorMin	  */
	public BigDecimal getValorMin();

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

    /** Column name WindowType */
    public static final String COLUMNNAME_WindowType = "WindowType";

	/** Set Window Type.
	  * Type or classification of a Window
	  */
	public void setWindowType (String WindowType);

	/** Get Window Type.
	  * Type or classification of a Window
	  */
	public String getWindowType();
}
