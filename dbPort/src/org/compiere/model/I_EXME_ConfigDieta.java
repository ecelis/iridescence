/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigDieta
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigDieta 
{

    /** TableName=EXME_ConfigDieta */
    public static final String Table_Name = "EXME_ConfigDieta";

    /** AD_Table_ID=1200432 */
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

    /** Column name EXME_ConfigDieta_ID */
    public static final String COLUMNNAME_EXME_ConfigDieta_ID = "EXME_ConfigDieta_ID";

	/** Set Diet Configuration	  */
	public void setEXME_ConfigDieta_ID (int EXME_ConfigDieta_ID);

	/** Get Diet Configuration	  */
	public int getEXME_ConfigDieta_ID();

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

    /** Column name Tipo */
    public static final String COLUMNNAME_Tipo = "Tipo";

	/** Set Type	  */
	public void setTipo (String Tipo);

	/** Get Type	  */
	public String getTipo();

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
