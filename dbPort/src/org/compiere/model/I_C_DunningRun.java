/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_DunningRun
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_DunningRun 
{

    /** TableName=C_DunningRun */
    public static final String Table_Name = "C_DunningRun";

    /** AD_Table_ID=526 */
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

    /** Column name C_DunningLevel_ID */
    public static final String COLUMNNAME_C_DunningLevel_ID = "C_DunningLevel_ID";

	/** Set Dunning Level	  */
	public void setC_DunningLevel_ID (int C_DunningLevel_ID);

	/** Get Dunning Level	  */
	public int getC_DunningLevel_ID();

	public I_C_DunningLevel getC_DunningLevel() throws RuntimeException;

    /** Column name C_DunningRun_ID */
    public static final String COLUMNNAME_C_DunningRun_ID = "C_DunningRun_ID";

	/** Set Dunning Run.
	  * Dunning Run
	  */
	public void setC_DunningRun_ID (int C_DunningRun_ID);

	/** Get Dunning Run.
	  * Dunning Run
	  */
	public int getC_DunningRun_ID();

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

    /** Column name DunningDate */
    public static final String COLUMNNAME_DunningDate = "DunningDate";

	/** Set Dunning Date.
	  * Date of Dunning
	  */
	public void setDunningDate (Timestamp DunningDate);

	/** Get Dunning Date.
	  * Date of Dunning
	  */
	public Timestamp getDunningDate();

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

    /** Column name SendIt */
    public static final String COLUMNNAME_SendIt = "SendIt";

	/** Set Send	  */
	public void setSendIt (String SendIt);

	/** Get Send	  */
	public String getSendIt();
}