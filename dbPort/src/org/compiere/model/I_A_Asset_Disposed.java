/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Disposed
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Disposed 
{

    /** TableName=A_Asset_Disposed */
    public static final String Table_Name = "A_Asset_Disposed";

    /** AD_Table_ID=1200784 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_Disposed_ID */
    public static final String COLUMNNAME_A_Asset_Disposed_ID = "A_Asset_Disposed_ID";

	/** Set A_Asset_Disposed_ID	  */
	public void setA_Asset_Disposed_ID (int A_Asset_Disposed_ID);

	/** Get A_Asset_Disposed_ID	  */
	public int getA_Asset_Disposed_ID();

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

    /** Column name A_Asset_Trade_ID */
    public static final String COLUMNNAME_A_Asset_Trade_ID = "A_Asset_Trade_ID";

	/** Set A_Asset_Trade_ID	  */
	public void setA_Asset_Trade_ID (int A_Asset_Trade_ID);

	/** Get A_Asset_Trade_ID	  */
	public int getA_Asset_Trade_ID();

    /** Column name A_Disposed_Date */
    public static final String COLUMNNAME_A_Disposed_Date = "A_Disposed_Date";

	/** Set Disposed Date	  */
	public void setA_Disposed_Date (Timestamp A_Disposed_Date);

	/** Get Disposed Date	  */
	public Timestamp getA_Disposed_Date();

    /** Column name A_Disposed_Method */
    public static final String COLUMNNAME_A_Disposed_Method = "A_Disposed_Method";

	/** Set Disposal Method	  */
	public void setA_Disposed_Method (String A_Disposed_Method);

	/** Get Disposal Method	  */
	public String getA_Disposed_Method();

    /** Column name A_Disposed_Reason */
    public static final String COLUMNNAME_A_Disposed_Reason = "A_Disposed_Reason";

	/** Set Disposed Reason Code	  */
	public void setA_Disposed_Reason (String A_Disposed_Reason);

	/** Get Disposed Reason Code	  */
	public String getA_Disposed_Reason();

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

    /** Column name A_Proceeds */
    public static final String COLUMNNAME_A_Proceeds = "A_Proceeds";

	/** Set A_Proceeds	  */
	public void setA_Proceeds (BigDecimal A_Proceeds);

	/** Get A_Proceeds	  */
	public BigDecimal getA_Proceeds();

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

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

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