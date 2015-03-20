/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CorteCajaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CorteCajaDet 
{

    /** TableName=EXME_CorteCajaDet */
    public static final String Table_Name = "EXME_CorteCajaDet";

    /** AD_Table_ID=1000152 */
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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws RuntimeException;

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

    /** Column name EXME_CorteCajaDet_ID */
    public static final String COLUMNNAME_EXME_CorteCajaDet_ID = "EXME_CorteCajaDet_ID";

	/** Set Detail Cash Balance.
	  * Identifier of cash balance detail
	  */
	public void setEXME_CorteCajaDet_ID (int EXME_CorteCajaDet_ID);

	/** Get Detail Cash Balance.
	  * Identifier of cash balance detail
	  */
	public int getEXME_CorteCajaDet_ID();

    /** Column name EXME_CorteCaja_ID */
    public static final String COLUMNNAME_EXME_CorteCaja_ID = "EXME_CorteCaja_ID";

	/** Set Cash Balance.
	  * Identifier of Cash Balance
	  */
	public void setEXME_CorteCaja_ID (int EXME_CorteCaja_ID);

	/** Get Cash Balance.
	  * Identifier of Cash Balance
	  */
	public int getEXME_CorteCaja_ID();

    /** Column name EXME_FormaPago_ID */
    public static final String COLUMNNAME_EXME_FormaPago_ID = "EXME_FormaPago_ID";

	/** Set Payment Form.
	  * Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID);

	/** Get Payment Form.
	  * Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();
}
