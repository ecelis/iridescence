/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Bank
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_Bank 
{

    /** TableName=C_Bank */
    public static final String Table_Name = "C_Bank";

    /** AD_Table_ID=296 */
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

    /** Column name C_Bank_ID */
    public static final String COLUMNNAME_C_Bank_ID = "C_Bank_ID";

	/** Set Bank.
	  * Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID);

	/** Get Bank.
	  * Bank
	  */
	public int getC_Bank_ID();

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name IsNational */
    public static final String COLUMNNAME_IsNational = "IsNational";

	/** Set National.
	  * National
	  */
	public void setIsNational (boolean IsNational);

	/** Get National.
	  * National
	  */
	public boolean isNational();

    /** Column name IsOwnBank */
    public static final String COLUMNNAME_IsOwnBank = "IsOwnBank";

	/** Set Own Bank.
	  * Bank for this Organization
	  */
	public void setIsOwnBank (boolean IsOwnBank);

	/** Get Own Bank.
	  * Bank for this Organization
	  */
	public boolean isOwnBank();

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

    /** Column name RoutingNo */
    public static final String COLUMNNAME_RoutingNo = "RoutingNo";

	/** Set Routing No.
	  * Bank Routing Number
	  */
	public void setRoutingNo (String RoutingNo);

	/** Get Routing No.
	  * Bank Routing Number
	  */
	public String getRoutingNo();

    /** Column name SwiftCode */
    public static final String COLUMNNAME_SwiftCode = "SwiftCode";

	/** Set Swift code.
	  * Swift Code or BIC
	  */
	public void setSwiftCode (String SwiftCode);

	/** Get Swift code.
	  * Swift Code or BIC
	  */
	public String getSwiftCode();
}
