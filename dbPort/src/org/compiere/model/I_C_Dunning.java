/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Dunning
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Dunning 
{

    /** TableName=C_Dunning */
    public static final String Table_Name = "C_Dunning";

    /** AD_Table_ID=301 */
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

    /** Column name C_Dunning_ID */
    public static final String COLUMNNAME_C_Dunning_ID = "C_Dunning_ID";

	/** Set Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public void setC_Dunning_ID (int C_Dunning_ID);

	/** Get Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public int getC_Dunning_ID();

    /** Column name CreateLevelsSequentially */
    public static final String COLUMNNAME_CreateLevelsSequentially = "CreateLevelsSequentially";

	/** Set Create levels sequentially.
	  * Create Dunning Letter by level sequentially
	  */
	public void setCreateLevelsSequentially (boolean CreateLevelsSequentially);

	/** Get Create levels sequentially.
	  * Create Dunning Letter by level sequentially
	  */
	public boolean isCreateLevelsSequentially();

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

    /** Column name SendDunningLetter */
    public static final String COLUMNNAME_SendDunningLetter = "SendDunningLetter";

	/** Set Send dunning letters.
	  * Indicates if dunning letters will be sent
	  */
	public void setSendDunningLetter (boolean SendDunningLetter);

	/** Get Send dunning letters.
	  * Indicates if dunning letters will be sent
	  */
	public boolean isSendDunningLetter();
}
