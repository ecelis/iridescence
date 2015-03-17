/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_ChargeType_DocType
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_ChargeType_DocType 
{

    /** TableName=C_ChargeType_DocType */
    public static final String Table_Name = "C_ChargeType_DocType";

    /** AD_Table_ID=1200816 */
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

    /** Column name C_ChargeType_ID */
    public static final String COLUMNNAME_C_ChargeType_ID = "C_ChargeType_ID";

	/** Set Charge Type	  */
	public void setC_ChargeType_ID (int C_ChargeType_ID);

	/** Get Charge Type	  */
	public int getC_ChargeType_ID();

	public I_C_ChargeType getC_ChargeType() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name IsAllowNegative */
    public static final String COLUMNNAME_IsAllowNegative = "IsAllowNegative";

	/** Set Allow Negative	  */
	public void setIsAllowNegative (boolean IsAllowNegative);

	/** Get Allow Negative	  */
	public boolean isAllowNegative();

    /** Column name IsAllowPositive */
    public static final String COLUMNNAME_IsAllowPositive = "IsAllowPositive";

	/** Set Allow Positive	  */
	public void setIsAllowPositive (boolean IsAllowPositive);

	/** Get Allow Positive	  */
	public boolean isAllowPositive();
}
