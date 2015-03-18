/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for W_Click
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_W_Click 
{

    /** TableName=W_Click */
    public static final String Table_Name = "W_Click";

    /** AD_Table_ID=550 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AcceptLanguage */
    public static final String COLUMNNAME_AcceptLanguage = "AcceptLanguage";

	/** Set Accept Language.
	  * Language accepted based on browser information
	  */
	public void setAcceptLanguage (String AcceptLanguage);

	/** Get Accept Language.
	  * Language accepted based on browser information
	  */
	public String getAcceptLanguage();

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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

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

    /** Column name Referrer */
    public static final String COLUMNNAME_Referrer = "Referrer";

	/** Set Referrer.
	  * Referring web address
	  */
	public void setReferrer (String Referrer);

	/** Get Referrer.
	  * Referring web address
	  */
	public String getReferrer();

    /** Column name Remote_Addr */
    public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";

	/** Set Remote Addr.
	  * Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr);

	/** Get Remote Addr.
	  * Remote Address
	  */
	public String getRemote_Addr();

    /** Column name Remote_Host */
    public static final String COLUMNNAME_Remote_Host = "Remote_Host";

	/** Set Remote Host.
	  * Remote host Info
	  */
	public void setRemote_Host (String Remote_Host);

	/** Get Remote Host.
	  * Remote host Info
	  */
	public String getRemote_Host();

    /** Column name TargetURL */
    public static final String COLUMNNAME_TargetURL = "TargetURL";

	/** Set Target URL.
	  * URL for the Target
	  */
	public void setTargetURL (String TargetURL);

	/** Get Target URL.
	  * URL for the Target
	  */
	public String getTargetURL();

    /** Column name UserAgent */
    public static final String COLUMNNAME_UserAgent = "UserAgent";

	/** Set User Agent.
	  * Browser Used
	  */
	public void setUserAgent (String UserAgent);

	/** Get User Agent.
	  * Browser Used
	  */
	public String getUserAgent();

    /** Column name W_ClickCount_ID */
    public static final String COLUMNNAME_W_ClickCount_ID = "W_ClickCount_ID";

	/** Set Click Count.
	  * Web Click Management
	  */
	public void setW_ClickCount_ID (int W_ClickCount_ID);

	/** Get Click Count.
	  * Web Click Management
	  */
	public int getW_ClickCount_ID();

	public I_W_ClickCount getW_ClickCount() throws RuntimeException;

    /** Column name W_Click_ID */
    public static final String COLUMNNAME_W_Click_ID = "W_Click_ID";

	/** Set Web Click.
	  * Individual Web Click
	  */
	public void setW_Click_ID (int W_Click_ID);

	/** Get Web Click.
	  * Individual Web Click
	  */
	public int getW_Click_ID();
}