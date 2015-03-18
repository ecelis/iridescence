/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Transfer
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Transfer 
{

    /** TableName=A_Asset_Transfer */
    public static final String Table_Name = "A_Asset_Transfer";

    /** AD_Table_ID=1200785 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Accumdepreciation_Acct */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct = "A_Accumdepreciation_Acct";

	/** Set Accumulated Depreciation	  */
	public void setA_Accumdepreciation_Acct (int A_Accumdepreciation_Acct);

	/** Get Accumulated Depreciation	  */
	public int getA_Accumdepreciation_Acct();

    /** Column name A_Accumdepreciation_Acct_New */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct_New = "A_Accumdepreciation_Acct_New";

	/** Set New Accum Depreciation Acct	  */
	public void setA_Accumdepreciation_Acct_New (int A_Accumdepreciation_Acct_New);

	/** Get New Accum Depreciation Acct	  */
	public int getA_Accumdepreciation_Acct_New();

    /** Column name A_Accumdepreciation_Acct_Str */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct_Str = "A_Accumdepreciation_Acct_Str";

	/** Set Old Accum Depreciation Acct	  */
	public void setA_Accumdepreciation_Acct_Str (String A_Accumdepreciation_Acct_Str);

	/** Get Old Accum Depreciation Acct	  */
	public String getA_Accumdepreciation_Acct_Str();

    /** Column name A_Asset_Acct */
    public static final String COLUMNNAME_A_Asset_Acct = "A_Asset_Acct";

	/** Set Asset Cost Account	  */
	public void setA_Asset_Acct (int A_Asset_Acct);

	/** Get Asset Cost Account	  */
	public int getA_Asset_Acct();

    /** Column name A_Asset_Acct_ID */
    public static final String COLUMNNAME_A_Asset_Acct_ID = "A_Asset_Acct_ID";

	/** Set A_Asset_Acct_ID	  */
	public void setA_Asset_Acct_ID (int A_Asset_Acct_ID);

	/** Get A_Asset_Acct_ID	  */
	public int getA_Asset_Acct_ID();

    /** Column name A_Asset_Acct_New */
    public static final String COLUMNNAME_A_Asset_Acct_New = "A_Asset_Acct_New";

	/** Set New Asset Cost Acct	  */
	public void setA_Asset_Acct_New (int A_Asset_Acct_New);

	/** Get New Asset Cost Acct	  */
	public int getA_Asset_Acct_New();

    /** Column name A_Asset_Acct_Str */
    public static final String COLUMNNAME_A_Asset_Acct_Str = "A_Asset_Acct_Str";

	/** Set Old Asset Cost Acct	  */
	public void setA_Asset_Acct_Str (String A_Asset_Acct_Str);

	/** Get Old Asset Cost Acct	  */
	public String getA_Asset_Acct_Str();

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_Asset_Transfer_ID */
    public static final String COLUMNNAME_A_Asset_Transfer_ID = "A_Asset_Transfer_ID";

	/** Set A_Asset_Transfer_ID	  */
	public void setA_Asset_Transfer_ID (int A_Asset_Transfer_ID);

	/** Get A_Asset_Transfer_ID	  */
	public int getA_Asset_Transfer_ID();

    /** Column name A_Depreciation_Acct */
    public static final String COLUMNNAME_A_Depreciation_Acct = "A_Depreciation_Acct";

	/** Set Depreciation Expense Account	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct);

	/** Get Depreciation Expense Account	  */
	public int getA_Depreciation_Acct();

    /** Column name A_Depreciation_Acct_New */
    public static final String COLUMNNAME_A_Depreciation_Acct_New = "A_Depreciation_Acct_New";

	/** Set New Depreciation Exp Acct	  */
	public void setA_Depreciation_Acct_New (int A_Depreciation_Acct_New);

	/** Get New Depreciation Exp Acct	  */
	public int getA_Depreciation_Acct_New();

    /** Column name A_Depreciation_Acct_Str */
    public static final String COLUMNNAME_A_Depreciation_Acct_Str = "A_Depreciation_Acct_Str";

	/** Set Old Depreciation Exp Acct	  */
	public void setA_Depreciation_Acct_Str (String A_Depreciation_Acct_Str);

	/** Get Old Depreciation Exp Acct	  */
	public String getA_Depreciation_Acct_Str();

    /** Column name A_Disposal_Loss */
    public static final String COLUMNNAME_A_Disposal_Loss = "A_Disposal_Loss";

	/** Set Loss on Disposal	  */
	public void setA_Disposal_Loss (int A_Disposal_Loss);

	/** Get Loss on Disposal	  */
	public int getA_Disposal_Loss();

    /** Column name A_Disposal_Loss_New */
    public static final String COLUMNNAME_A_Disposal_Loss_New = "A_Disposal_Loss_New";

	/** Set New Disposal Loss	  */
	public void setA_Disposal_Loss_New (int A_Disposal_Loss_New);

	/** Get New Disposal Loss	  */
	public int getA_Disposal_Loss_New();

    /** Column name A_Disposal_Loss_Str */
    public static final String COLUMNNAME_A_Disposal_Loss_Str = "A_Disposal_Loss_Str";

	/** Set Old Disposal Loss	  */
	public void setA_Disposal_Loss_Str (String A_Disposal_Loss_Str);

	/** Get Old Disposal Loss	  */
	public String getA_Disposal_Loss_Str();

    /** Column name A_Disposal_Revenue */
    public static final String COLUMNNAME_A_Disposal_Revenue = "A_Disposal_Revenue";

	/** Set Disposal Revenue	  */
	public void setA_Disposal_Revenue (int A_Disposal_Revenue);

	/** Get Disposal Revenue	  */
	public int getA_Disposal_Revenue();

    /** Column name A_Disposal_Revenue_New */
    public static final String COLUMNNAME_A_Disposal_Revenue_New = "A_Disposal_Revenue_New";

	/** Set New Disposal Revenue	  */
	public void setA_Disposal_Revenue_New (int A_Disposal_Revenue_New);

	/** Get New Disposal Revenue	  */
	public int getA_Disposal_Revenue_New();

    /** Column name A_Disposal_Revenue_Str */
    public static final String COLUMNNAME_A_Disposal_Revenue_Str = "A_Disposal_Revenue_Str";

	/** Set Old Disposal Revenue	  */
	public void setA_Disposal_Revenue_Str (String A_Disposal_Revenue_Str);

	/** Get Old Disposal Revenue	  */
	public String getA_Disposal_Revenue_Str();

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

    /** Column name A_Period_End */
    public static final String COLUMNNAME_A_Period_End = "A_Period_End";

	/** Set Period End	  */
	public void setA_Period_End (int A_Period_End);

	/** Get Period End	  */
	public int getA_Period_End();

    /** Column name A_Period_Start */
    public static final String COLUMNNAME_A_Period_Start = "A_Period_Start";

	/** Set Period Start	  */
	public void setA_Period_Start (int A_Period_Start);

	/** Get Period Start	  */
	public int getA_Period_Start();

    /** Column name A_Split_Percent */
    public static final String COLUMNNAME_A_Split_Percent = "A_Split_Percent";

	/** Set Split Percentage	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent);

	/** Get Split Percentage	  */
	public BigDecimal getA_Split_Percent();

    /** Column name A_Transfer_Balance */
    public static final String COLUMNNAME_A_Transfer_Balance = "A_Transfer_Balance";

	/** Set Transfer Balance Sheet	  */
	public void setA_Transfer_Balance (boolean A_Transfer_Balance);

	/** Get Transfer Balance Sheet	  */
	public boolean isA_Transfer_Balance();

    /** Column name A_Transfer_Balance_IS */
    public static final String COLUMNNAME_A_Transfer_Balance_IS = "A_Transfer_Balance_IS";

	/** Set Transfer Balance IS	  */
	public void setA_Transfer_Balance_IS (boolean A_Transfer_Balance_IS);

	/** Get Transfer Balance IS	  */
	public boolean isA_Transfer_Balance_IS();

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set Posting Type.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get Posting Type.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}