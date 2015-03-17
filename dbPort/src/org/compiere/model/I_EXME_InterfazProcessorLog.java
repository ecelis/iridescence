/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InterfazProcessorLog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_InterfazProcessorLog 
{

    /** TableName=EXME_InterfazProcessorLog */
    public static final String Table_Name = "EXME_InterfazProcessorLog";

    /** AD_Table_ID=1000187 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name BinaryData */
    public static final String COLUMNNAME_BinaryData = "BinaryData";

	/** Set BinaryData.
	  * Binary Data
	  */
	public void setBinaryData (byte[] BinaryData);

	/** Get BinaryData.
	  * Binary Data
	  */
	public byte[] getBinaryData();

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

    /** Column name EXME_InterfazProcessor_ID */
    public static final String COLUMNNAME_EXME_InterfazProcessor_ID = "EXME_InterfazProcessor_ID";

	/** Set Processor Interface	  */
	public void setEXME_InterfazProcessor_ID (int EXME_InterfazProcessor_ID);

	/** Get Processor Interface	  */
	public int getEXME_InterfazProcessor_ID();

	public I_EXME_InterfazProcessor getEXME_InterfazProcessor() throws RuntimeException;

    /** Column name EXME_InterfazProcessorLog_ID */
    public static final String COLUMNNAME_EXME_InterfazProcessorLog_ID = "EXME_InterfazProcessorLog_ID";

	/** Set Processor Interface Log.
	  * Processor Interface Log
	  */
	public void setEXME_InterfazProcessorLog_ID (int EXME_InterfazProcessorLog_ID);

	/** Get Processor Interface Log.
	  * Processor Interface Log
	  */
	public int getEXME_InterfazProcessorLog_ID();

    /** Column name IsError */
    public static final String COLUMNNAME_IsError = "IsError";

	/** Set Error.
	  * An Error occured in the execution
	  */
	public void setIsError (boolean IsError);

	/** Get Error.
	  * An Error occured in the execution
	  */
	public boolean isError();

    /** Column name Reference */
    public static final String COLUMNNAME_Reference = "Reference";

	/** Set Reference.
	  * Reference for this record
	  */
	public void setReference (String Reference);

	/** Get Reference.
	  * Reference for this record
	  */
	public String getReference();

    /** Column name Summary */
    public static final String COLUMNNAME_Summary = "Summary";

	/** Set Summary.
	  * Textual summary of this request
	  */
	public void setSummary (String Summary);

	/** Get Summary.
	  * Textual summary of this request
	  */
	public String getSummary();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();
}
