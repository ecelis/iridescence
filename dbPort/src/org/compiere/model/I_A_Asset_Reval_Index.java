/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Reval_Index
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Reval_Index 
{

    /** TableName=A_Asset_Reval_Index */
    public static final String Table_Name = "A_Asset_Reval_Index";

    /** AD_Table_ID=1200777 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_Reval_Index_ID */
    public static final String COLUMNNAME_A_Asset_Reval_Index_ID = "A_Asset_Reval_Index_ID";

	/** Set Asset Reval Index ID	  */
	public void setA_Asset_Reval_Index_ID (int A_Asset_Reval_Index_ID);

	/** Get Asset Reval Index ID	  */
	public int getA_Asset_Reval_Index_ID();

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

    /** Column name A_Effective_Date */
    public static final String COLUMNNAME_A_Effective_Date = "A_Effective_Date";

	/** Set Effective Date	  */
	public void setA_Effective_Date (Timestamp A_Effective_Date);

	/** Get Effective Date	  */
	public Timestamp getA_Effective_Date();

    /** Column name A_Reval_Code */
    public static final String COLUMNNAME_A_Reval_Code = "A_Reval_Code";

	/** Set Reval Code	  */
	public void setA_Reval_Code (String A_Reval_Code);

	/** Get Reval Code	  */
	public String getA_Reval_Code();

    /** Column name A_Reval_Multiplier */
    public static final String COLUMNNAME_A_Reval_Multiplier = "A_Reval_Multiplier";

	/** Set A Reval Multiplier	  */
	public void setA_Reval_Multiplier (String A_Reval_Multiplier);

	/** Get A Reval Multiplier	  */
	public String getA_Reval_Multiplier();

    /** Column name A_Reval_Rate */
    public static final String COLUMNNAME_A_Reval_Rate = "A_Reval_Rate";

	/** Set Reval Rate	  */
	public void setA_Reval_Rate (BigDecimal A_Reval_Rate);

	/** Get Reval Rate	  */
	public BigDecimal getA_Reval_Rate();
}
