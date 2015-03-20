/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RangoSV
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_RangoSV 
{

    /** TableName=EXME_RangoSV */
    public static final String Table_Name = "EXME_RangoSV";

    /** AD_Table_ID=1201042 */
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

    /** Column name EdadFin */
    public static final String COLUMNNAME_EdadFin = "EdadFin";

	/** Set To Age	  */
	public void setEdadFin (BigDecimal EdadFin);

	/** Get To Age	  */
	public BigDecimal getEdadFin();

    /** Column name EdadIni */
    public static final String COLUMNNAME_EdadIni = "EdadIni";

	/** Set Age Initial	  */
	public void setEdadIni (BigDecimal EdadIni);

	/** Get Age Initial	  */
	public BigDecimal getEdadIni();

    /** Column name EXME_RangoSV_ID */
    public static final String COLUMNNAME_EXME_RangoSV_ID = "EXME_RangoSV_ID";

	/** Set Vital signs ranges	  */
	public void setEXME_RangoSV_ID (int EXME_RangoSV_ID);

	/** Get Vital signs ranges	  */
	public int getEXME_RangoSV_ID();

    /** Column name EXME_SignoVital_ID */
    public static final String COLUMNNAME_EXME_SignoVital_ID = "EXME_SignoVital_ID";

	/** Set Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID);

	/** Get Vital Sign	  */
	public int getEXME_SignoVital_ID();

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException;

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

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

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (String Sexo);

	/** Get Sex.
	  * Sex
	  */
	public String getSexo();

    /** Column name ValorFin */
    public static final String COLUMNNAME_ValorFin = "ValorFin";

	/** Set Final Value	  */
	public void setValorFin (BigDecimal ValorFin);

	/** Get Final Value	  */
	public BigDecimal getValorFin();

    /** Column name ValorIni */
    public static final String COLUMNNAME_ValorIni = "ValorIni";

	/** Set Initial Value	  */
	public void setValorIni (BigDecimal ValorIni);

	/** Get Initial Value	  */
	public BigDecimal getValorIni();

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
}
