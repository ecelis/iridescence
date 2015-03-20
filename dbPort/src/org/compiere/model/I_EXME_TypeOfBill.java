/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TypeOfBill
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_TypeOfBill 
{

    /** TableName=EXME_TypeOfBill */
    public static final String Table_Name = "EXME_TypeOfBill";

    /** AD_Table_ID=1201123 */
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

    /** Column name BillType */
    public static final String COLUMNNAME_BillType = "BillType";

	/** Set Type of Bill.
	  * Type of Bill
	  */
	public void setBillType (String BillType);

	/** Get Type of Bill.
	  * Type of Bill
	  */
	public String getBillType();

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (int Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public int getCode();

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

    /** Column name EXME_TypeOfBill_ID */
    public static final String COLUMNNAME_EXME_TypeOfBill_ID = "EXME_TypeOfBill_ID";

	/** Set Type Of Bill.
	  * Type Of Bill
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID);

	/** Get Type Of Bill.
	  * Type Of Bill
	  */
	public int getEXME_TypeOfBill_ID();

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
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();

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
