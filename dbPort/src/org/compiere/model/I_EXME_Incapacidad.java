/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Incapacidad
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Incapacidad 
{

    /** TableName=EXME_Incapacidad */
    public static final String Table_Name = "EXME_Incapacidad";

    /** AD_Table_ID=1200526 */
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

    /** Column name EXME_Articulo_Ley_ID */
    public static final String COLUMNNAME_EXME_Articulo_Ley_ID = "EXME_Articulo_Ley_ID";

	/** Set Rule Article	  */
	public void setEXME_Articulo_Ley_ID (int EXME_Articulo_Ley_ID);

	/** Get Rule Article	  */
	public int getEXME_Articulo_Ley_ID();

	public I_EXME_Articulo_Ley getEXME_Articulo_Ley() throws RuntimeException;

    /** Column name EXME_Fraccion_Ley_ID */
    public static final String COLUMNNAME_EXME_Fraccion_Ley_ID = "EXME_Fraccion_Ley_ID";

	/** Set Law Fraction	  */
	public void setEXME_Fraccion_Ley_ID (int EXME_Fraccion_Ley_ID);

	/** Get Law Fraction	  */
	public int getEXME_Fraccion_Ley_ID();

	public I_EXME_Fraccion_Ley getEXME_Fraccion_Ley() throws RuntimeException;

    /** Column name EXME_Incapacidad_ID */
    public static final String COLUMNNAME_EXME_Incapacidad_ID = "EXME_Incapacidad_ID";

	/** Set Disability	  */
	public void setEXME_Incapacidad_ID (int EXME_Incapacidad_ID);

	/** Get Disability	  */
	public int getEXME_Incapacidad_ID();

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

    /** Column name Padecimiento */
    public static final String COLUMNNAME_Padecimiento = "Padecimiento";

	/** Set Condition	  */
	public void setPadecimiento (String Padecimiento);

	/** Get Condition	  */
	public String getPadecimiento();

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
