/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Depreciation_Forecast
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Depreciation_Forecast 
{

    /** TableName=A_Depreciation_Forecast */
    public static final String Table_Name = "A_Depreciation_Forecast";

    /** AD_Table_ID=1200775 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Depreciation_Forecast_ID */
    public static final String COLUMNNAME_A_Depreciation_Forecast_ID = "A_Depreciation_Forecast_ID";

	/** Set Depreciation Forecast ID	  */
	public void setA_Depreciation_Forecast_ID (int A_Depreciation_Forecast_ID);

	/** Get Depreciation Forecast ID	  */
	public int getA_Depreciation_Forecast_ID();

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

    /** Column name A_End_Asset_ID */
    public static final String COLUMNNAME_A_End_Asset_ID = "A_End_Asset_ID";

	/** Set End Asset ID	  */
	public void setA_End_Asset_ID (int A_End_Asset_ID);

	/** Get End Asset ID	  */
	public int getA_End_Asset_ID();

    /** Column name A_Start_Asset_ID */
    public static final String COLUMNNAME_A_Start_Asset_ID = "A_Start_Asset_ID";

	/** Set Start Asset ID	  */
	public void setA_Start_Asset_ID (int A_Start_Asset_ID);

	/** Get Start Asset ID	  */
	public int getA_Start_Asset_ID();

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
