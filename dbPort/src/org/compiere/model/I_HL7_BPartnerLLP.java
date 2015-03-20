/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_BPartnerLLP
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_BPartnerLLP 
{

    /** TableName=HL7_BPartnerLLP */
    public static final String Table_Name = "HL7_BPartnerLLP";

    /** AD_Table_ID=1200650 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name ACKResponse */
    public static final String COLUMNNAME_ACKResponse = "ACKResponse";

	/** Set Porcess ACK Response	  */
	public void setACKResponse (String ACKResponse);

	/** Get Porcess ACK Response	  */
	public String getACKResponse();

    /** Column name ACKTimeout */
    public static final String COLUMNNAME_ACKTimeout = "ACKTimeout";

	/** Set ACK Timeout	  */
	public void setACKTimeout (BigDecimal ACKTimeout);

	/** Get ACK Timeout	  */
	public BigDecimal getACKTimeout();

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

    /** Column name BufferSize */
    public static final String COLUMNNAME_BufferSize = "BufferSize";

	/** Set Buffer Size	  */
	public void setBufferSize (BigDecimal BufferSize);

	/** Get Buffer Size	  */
	public BigDecimal getBufferSize();

    /** Column name CharEncoding */
    public static final String COLUMNNAME_CharEncoding = "CharEncoding";

	/** Set LLP Frame Encoding	  */
	public void setCharEncoding (String CharEncoding);

	/** Get LLP Frame Encoding	  */
	public String getCharEncoding();

    /** Column name Encoding */
    public static final String COLUMNNAME_Encoding = "Encoding";

	/** Set Encoding.
	  * Messaage encoding
	  */
	public void setEncoding (String Encoding);

	/** Get Encoding.
	  * Messaage encoding
	  */
	public String getEncoding();

    /** Column name EndOfMessage */
    public static final String COLUMNNAME_EndOfMessage = "EndOfMessage";

	/** Set End Of Message Character	  */
	public void setEndOfMessage (String EndOfMessage);

	/** Get End Of Message Character	  */
	public String getEndOfMessage();

    /** Column name HL7_BPartner_ID */
    public static final String COLUMNNAME_HL7_BPartner_ID = "HL7_BPartner_ID";

	/** Set HL7 Business Partner.
	  * Identifies a Business Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID);

	/** Get HL7 Business Partner.
	  * Identifies a Business Partner
	  */
	public int getHL7_BPartner_ID();

	public I_HL7_BPartner getHL7_BPartner() throws RuntimeException;

    /** Column name HL7_BPartnerLLP_ID */
    public static final String COLUMNNAME_HL7_BPartnerLLP_ID = "HL7_BPartnerLLP_ID";

	/** Set HL7 Business Partner LLP	  */
	public void setHL7_BPartnerLLP_ID (int HL7_BPartnerLLP_ID);

	/** Get HL7 Business Partner LLP	  */
	public int getHL7_BPartnerLLP_ID();

    /** Column name IsInbound */
    public static final String COLUMNNAME_IsInbound = "IsInbound";

	/** Set Is Inbound	  */
	public void setIsInbound (boolean IsInbound);

	/** Get Is Inbound	  */
	public boolean isInbound();

    /** Column name KeepConnectionOpen */
    public static final String COLUMNNAME_KeepConnectionOpen = "KeepConnectionOpen";

	/** Set Keep Connection Open	  */
	public void setKeepConnectionOpen (String KeepConnectionOpen);

	/** Get Keep Connection Open	  */
	public String getKeepConnectionOpen();

    /** Column name LLPAddress */
    public static final String COLUMNNAME_LLPAddress = "LLPAddress";

	/** Set LLP Host Address	  */
	public void setLLPAddress (String LLPAddress);

	/** Get LLP Host Address	  */
	public String getLLPAddress();

    /** Column name LLPPort */
    public static final String COLUMNNAME_LLPPort = "LLPPort";

	/** Set LLP Port	  */
	public void setLLPPort (BigDecimal LLPPort);

	/** Get LLP Port	  */
	public BigDecimal getLLPPort();

    /** Column name MaxRetryCount */
    public static final String COLUMNNAME_MaxRetryCount = "MaxRetryCount";

	/** Set Maximun Retry Count	  */
	public void setMaxRetryCount (BigDecimal MaxRetryCount);

	/** Get Maximun Retry Count	  */
	public BigDecimal getMaxRetryCount();

    /** Column name ReconnectMillisecs */
    public static final String COLUMNNAME_ReconnectMillisecs = "ReconnectMillisecs";

	/** Set Reconnect Intervals (ms)	  */
	public void setReconnectMillisecs (int ReconnectMillisecs);

	/** Get Reconnect Intervals (ms)	  */
	public int getReconnectMillisecs();

    /** Column name RecordSeparator */
    public static final String COLUMNNAME_RecordSeparator = "RecordSeparator";

	/** Set Record Separator Char	  */
	public void setRecordSeparator (String RecordSeparator);

	/** Get Record Separator Char	  */
	public String getRecordSeparator();

    /** Column name SegmentEnd */
    public static final String COLUMNNAME_SegmentEnd = "SegmentEnd";

	/** Set End of Segment Char	  */
	public void setSegmentEnd (String SegmentEnd);

	/** Get End of Segment Char	  */
	public String getSegmentEnd();

    /** Column name SendTimeout */
    public static final String COLUMNNAME_SendTimeout = "SendTimeout";

	/** Set Send Timeout (ms)	  */
	public void setSendTimeout (BigDecimal SendTimeout);

	/** Get Send Timeout (ms)	  */
	public BigDecimal getSendTimeout();

    /** Column name StartOfMessage */
    public static final String COLUMNNAME_StartOfMessage = "StartOfMessage";

	/** Set Start of Message Char	  */
	public void setStartOfMessage (String StartOfMessage);

	/** Get Start of Message Char	  */
	public String getStartOfMessage();

    /** Column name Template */
    public static final String COLUMNNAME_Template = "Template";

	/** Set Template.
	  * Message Template
	  */
	public void setTemplate (String Template);

	/** Get Template.
	  * Message Template
	  */
	public String getTemplate();
}
