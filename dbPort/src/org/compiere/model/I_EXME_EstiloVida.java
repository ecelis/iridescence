/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EstiloVida
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EstiloVida 
{

    /** TableName=EXME_EstiloVida */
    public static final String Table_Name = "EXME_EstiloVida";

    /** AD_Table_ID=1200867 */
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

    /** Column name CodSnomed */
    public static final String COLUMNNAME_CodSnomed = "CodSnomed";

	/** Set Snomed Code.
	  * Snomed Code
	  */
	public void setCodSnomed (String CodSnomed);

	/** Get Snomed Code.
	  * Snomed Code
	  */
	public String getCodSnomed();

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

    /** Column name EXME_EstiloVida_ID */
    public static final String COLUMNNAME_EXME_EstiloVida_ID = "EXME_EstiloVida_ID";

	/** Set Life Style	  */
	public void setEXME_EstiloVida_ID (int EXME_EstiloVida_ID);

	/** Get Life Style	  */
	public int getEXME_EstiloVida_ID();

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

    /** Column name TipoEstilo */
    public static final String COLUMNNAME_TipoEstilo = "TipoEstilo";

	/** Set Lifestyle Type	  */
	public void setTipoEstilo (String TipoEstilo);

	/** Get Lifestyle Type	  */
	public String getTipoEstilo();

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
