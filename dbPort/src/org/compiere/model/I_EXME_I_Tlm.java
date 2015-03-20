/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_I_Tlm
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_I_Tlm 
{

    /** TableName=EXME_I_Tlm */
    public static final String Table_Name = "EXME_I_Tlm";

    /** AD_Table_ID=1200349 */
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

    /** Column name EXME_I_TLM_ID */
    public static final String COLUMNNAME_EXME_I_TLM_ID = "EXME_I_TLM_ID";

	/** Set EXME_I_TLM_ID.
	  * eCareSoft Interface Output Table
	  */
	public void setEXME_I_TLM_ID (int EXME_I_TLM_ID);

	/** Get EXME_I_TLM_ID.
	  * eCareSoft Interface Output Table
	  */
	public int getEXME_I_TLM_ID();

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName (String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();

    /** Column name Interfase */
    public static final String COLUMNNAME_Interfase = "Interfase";

	/** Set Interface	  */
	public void setInterfase (String Interfase);

	/** Get Interface	  */
	public String getInterfase();

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();

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

    /** Column name ProcessingDate */
    public static final String COLUMNNAME_ProcessingDate = "ProcessingDate";

	/** Set Processing date	  */
	public void setProcessingDate (Timestamp ProcessingDate);

	/** Get Processing date	  */
	public Timestamp getProcessingDate();
}
