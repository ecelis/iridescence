/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Depreciation_Table_Detail
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Depreciation_Table_Detail 
{

    /** TableName=A_Depreciation_Table_Detail */
    public static final String Table_Name = "A_Depreciation_Table_Detail";

    /** AD_Table_ID=1200770 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Depreciation_Rate */
    public static final String COLUMNNAME_A_Depreciation_Rate = "A_Depreciation_Rate";

	/** Set Rate	  */
	public void setA_Depreciation_Rate (BigDecimal A_Depreciation_Rate);

	/** Get Rate	  */
	public BigDecimal getA_Depreciation_Rate();

    /** Column name A_Depreciation_Table_Code */
    public static final String COLUMNNAME_A_Depreciation_Table_Code = "A_Depreciation_Table_Code";

	/** Set Depreciation Code	  */
	public void setA_Depreciation_Table_Code (String A_Depreciation_Table_Code);

	/** Get Depreciation Code	  */
	public String getA_Depreciation_Table_Code();

    /** Column name A_Depreciation_Table_Detail_ID */
    public static final String COLUMNNAME_A_Depreciation_Table_Detail_ID = "A_Depreciation_Table_Detail_ID";

	/** Set A_Depreciation_Table_Detail_ID	  */
	public void setA_Depreciation_Table_Detail_ID (int A_Depreciation_Table_Detail_ID);

	/** Get A_Depreciation_Table_Detail_ID	  */
	public int getA_Depreciation_Table_Detail_ID();

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

    /** Column name A_Period */
    public static final String COLUMNNAME_A_Period = "A_Period";

	/** Set Period/Yearly	  */
	public void setA_Period (int A_Period);

	/** Get Period/Yearly	  */
	public int getA_Period();

    /** Column name A_Table_Rate_Type */
    public static final String COLUMNNAME_A_Table_Rate_Type = "A_Table_Rate_Type";

	/** Set Type	  */
	public void setA_Table_Rate_Type (String A_Table_Rate_Type);

	/** Get Type	  */
	public String getA_Table_Rate_Type();

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
}
