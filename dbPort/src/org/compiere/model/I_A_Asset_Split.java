/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Split
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Split 
{

    /** TableName=A_Asset_Split */
    public static final String Table_Name = "A_Asset_Split";

    /** AD_Table_ID=1200779 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Amount_Split */
    public static final String COLUMNNAME_A_Amount_Split = "A_Amount_Split";

	/** Set Amount Split	  */
	public void setA_Amount_Split (BigDecimal A_Amount_Split);

	/** Get Amount Split	  */
	public BigDecimal getA_Amount_Split();

    /** Column name A_Asset_Acct_ID */
    public static final String COLUMNNAME_A_Asset_Acct_ID = "A_Asset_Acct_ID";

	/** Set A_Asset_Acct_ID	  */
	public void setA_Asset_Acct_ID (int A_Asset_Acct_ID);

	/** Get A_Asset_Acct_ID	  */
	public int getA_Asset_Acct_ID();

    /** Column name A_Asset_Cost */
    public static final String COLUMNNAME_A_Asset_Cost = "A_Asset_Cost";

	/** Set Asset Cost	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost);

	/** Get Asset Cost	  */
	public BigDecimal getA_Asset_Cost();

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

    /** Column name A_Asset_ID_To */
    public static final String COLUMNNAME_A_Asset_ID_To = "A_Asset_ID_To";

	/** Set To Asset ID	  */
	public void setA_Asset_ID_To (int A_Asset_ID_To);

	/** Get To Asset ID	  */
	public int getA_Asset_ID_To();

    /** Column name A_Asset_Split_ID */
    public static final String COLUMNNAME_A_Asset_Split_ID = "A_Asset_Split_ID";

	/** Set Asset Split ID	  */
	public void setA_Asset_Split_ID (int A_Asset_Split_ID);

	/** Get Asset Split ID	  */
	public int getA_Asset_Split_ID();

    /** Column name A_Depreciation_Workfile_ID */
    public static final String COLUMNNAME_A_Depreciation_Workfile_ID = "A_Depreciation_Workfile_ID";

	/** Set A_Depreciation_Workfile_ID	  */
	public void setA_Depreciation_Workfile_ID (int A_Depreciation_Workfile_ID);

	/** Get A_Depreciation_Workfile_ID	  */
	public int getA_Depreciation_Workfile_ID();

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

    /** Column name A_Percent_Original */
    public static final String COLUMNNAME_A_Percent_Original = "A_Percent_Original";

	/** Set Percent Original	  */
	public void setA_Percent_Original (BigDecimal A_Percent_Original);

	/** Get Percent Original	  */
	public BigDecimal getA_Percent_Original();

    /** Column name A_Percent_Split */
    public static final String COLUMNNAME_A_Percent_Split = "A_Percent_Split";

	/** Set Percent Split	  */
	public void setA_Percent_Split (BigDecimal A_Percent_Split);

	/** Get Percent Split	  */
	public BigDecimal getA_Percent_Split();

    /** Column name A_QTY_Current */
    public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

	/** Set Quantity	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get Quantity	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_QTY_Split */
    public static final String COLUMNNAME_A_QTY_Split = "A_QTY_Split";

	/** Set Quantity Split	  */
	public void setA_QTY_Split (BigDecimal A_QTY_Split);

	/** Get Quantity Split	  */
	public BigDecimal getA_QTY_Split();

    /** Column name A_Split_Type */
    public static final String COLUMNNAME_A_Split_Type = "A_Split_Type";

	/** Set Split Type	  */
	public void setA_Split_Type (String A_Split_Type);

	/** Get Split Type	  */
	public String getA_Split_Type();

    /** Column name A_Transfer_Balance_IS */
    public static final String COLUMNNAME_A_Transfer_Balance_IS = "A_Transfer_Balance_IS";

	/** Set Transfer Balance IS	  */
	public void setA_Transfer_Balance_IS (boolean A_Transfer_Balance_IS);

	/** Get Transfer Balance IS	  */
	public boolean isA_Transfer_Balance_IS();

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
