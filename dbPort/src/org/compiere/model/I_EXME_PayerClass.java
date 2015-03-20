/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PayerClass
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PayerClass 
{

    /** TableName=EXME_PayerClass */
    public static final String Table_Name = "EXME_PayerClass";

    /** AD_Table_ID=1201178 */
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

    /** Column name EXME_FinancialClass_ID */
    public static final String COLUMNNAME_EXME_FinancialClass_ID = "EXME_FinancialClass_ID";

	/** Set Financial Class Id	  */
	public void setEXME_FinancialClass_ID (int EXME_FinancialClass_ID);

	/** Get Financial Class Id	  */
	public int getEXME_FinancialClass_ID();

	public I_EXME_FinancialClass getEXME_FinancialClass() throws RuntimeException;

    /** Column name EXME_PayerClass_ID */
    public static final String COLUMNNAME_EXME_PayerClass_ID = "EXME_PayerClass_ID";

	/** Set Payer Class Id	  */
	public void setEXME_PayerClass_ID (int EXME_PayerClass_ID);

	/** Get Payer Class Id	  */
	public int getEXME_PayerClass_ID();

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

    /** Column name isMiscellaneous */
    public static final String COLUMNNAME_isMiscellaneous = "isMiscellaneous";

	/** Set Is Miscellaneous	  */
	public void setisMiscellaneous (boolean isMiscellaneous);

	/** Get Is Miscellaneous	  */
	public boolean isMiscellaneous();
}
