/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Impresora_Func
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Impresora_Func 
{

    /** TableName=EXME_Impresora_Func */
    public static final String Table_Name = "EXME_Impresora_Func";

    /** AD_Table_ID=1200298 */
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

    /** Column name EXME_Impresora_Func_ID */
    public static final String COLUMNNAME_EXME_Impresora_Func_ID = "EXME_Impresora_Func_ID";

	/** Set Printer Function	  */
	public void setEXME_Impresora_Func_ID (int EXME_Impresora_Func_ID);

	/** Get Printer Function	  */
	public int getEXME_Impresora_Func_ID();

    /** Column name EXME_Impresora_ID */
    public static final String COLUMNNAME_EXME_Impresora_ID = "EXME_Impresora_ID";

	/** Set printer.
	  * printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID);

	/** Get printer.
	  * printer
	  */
	public int getEXME_Impresora_ID();

	public I_EXME_Impresora getEXME_Impresora() throws RuntimeException;

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
