/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CorteCaja
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CorteCaja 
{

    /** TableName=EXME_CorteCaja */
    public static final String Table_Name = "EXME_CorteCaja";

    /** AD_Table_ID=1000151 */
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

    /** Column name CashierAmount */
    public static final String COLUMNNAME_CashierAmount = "CashierAmount";

	/** Set Cashier amount.
	  * Cashier amount
	  */
	public void setCashierAmount (BigDecimal CashierAmount);

	/** Get Cashier amount.
	  * Cashier amount
	  */
	public BigDecimal getCashierAmount();

    /** Column name C_Cash_ID */
    public static final String COLUMNNAME_C_Cash_ID = "C_Cash_ID";

	/** Set Cash Journal.
	  * Cash Journal
	  */
	public void setC_Cash_ID (int C_Cash_ID);

	/** Get Cash Journal.
	  * Cash Journal
	  */
	public int getC_Cash_ID();

	public I_C_Cash getC_Cash() throws RuntimeException;

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

    /** Column name StatementDate */
    public static final String COLUMNNAME_StatementDate = "StatementDate";

	/** Set Statement date.
	  * Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate);

	/** Get Statement date.
	  * Date of the statement
	  */
	public Timestamp getStatementDate();
}
