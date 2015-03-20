/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for HL7_BPartnerLLP
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_BPartnerLLP extends PO implements I_HL7_BPartnerLLP, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartnerLLP (Properties ctx, int HL7_BPartnerLLP_ID, String trxName)
    {
      super (ctx, HL7_BPartnerLLP_ID, trxName);
      /** if (HL7_BPartnerLLP_ID == 0)
        {
			setACKTimeout (Env.ZERO);
// 5000
			setBufferSize (Env.ZERO);
// 65536
			setCharEncoding (null);
// hex
			setEndOfMessage (null);
// 0x1C
			setLLPAddress (null);
			setLLPPort (Env.ZERO);
// 6660
			setMaxRetryCount (Env.ZERO);
// 2
			setReconnectMillisecs (0);
// 10000
			setRecordSeparator (null);
// 0x0D
			setSegmentEnd (null);
// 0x0D
			setSendTimeout (Env.ZERO);
// 5000
			setStartOfMessage (null);
// 0x0B
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartnerLLP (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HL7_BPartnerLLP[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** ACKResponse AD_Reference_ID=1200251 */
	public static final int ACKRESPONSE_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String ACKRESPONSE_NO = "0";
	/** Yes = 1 */
	public static final String ACKRESPONSE_Yes = "1";
	/** Set Porcess ACK Response.
		@param ACKResponse Porcess ACK Response	  */
	public void setACKResponse (String ACKResponse)
	{

		if (ACKResponse == null || ACKResponse.equals("0") || ACKResponse.equals("1")); else throw new IllegalArgumentException ("ACKResponse Invalid value - " + ACKResponse + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_ACKResponse, ACKResponse);
	}

	/** Get Porcess ACK Response.
		@return Porcess ACK Response	  */
	public String getACKResponse () 
	{
		return (String)get_Value(COLUMNNAME_ACKResponse);
	}

	/** Set ACK Timeout.
		@param ACKTimeout ACK Timeout	  */
	public void setACKTimeout (BigDecimal ACKTimeout)
	{
		if (ACKTimeout == null)
			throw new IllegalArgumentException ("ACKTimeout is mandatory.");
		set_Value (COLUMNNAME_ACKTimeout, ACKTimeout);
	}

	/** Get ACK Timeout.
		@return ACK Timeout	  */
	public BigDecimal getACKTimeout () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ACKTimeout);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Buffer Size.
		@param BufferSize Buffer Size	  */
	public void setBufferSize (BigDecimal BufferSize)
	{
		if (BufferSize == null)
			throw new IllegalArgumentException ("BufferSize is mandatory.");
		set_Value (COLUMNNAME_BufferSize, BufferSize);
	}

	/** Get Buffer Size.
		@return Buffer Size	  */
	public BigDecimal getBufferSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BufferSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** CharEncoding AD_Reference_ID=1200252 */
	public static final int CHARENCODING_AD_Reference_ID=1200252;
	/** Hex = hex */
	public static final String CHARENCODING_Hex = "hex";
	/** ASCII = ascii */
	public static final String CHARENCODING_ASCII = "ascii";
	/** Set LLP Frame Encoding.
		@param CharEncoding LLP Frame Encoding	  */
	public void setCharEncoding (String CharEncoding)
	{
		if (CharEncoding == null) throw new IllegalArgumentException ("CharEncoding is mandatory");
		if (CharEncoding.equals("hex") || CharEncoding.equals("ascii")); else throw new IllegalArgumentException ("CharEncoding Invalid value - " + CharEncoding + " - Reference_ID=1200252 - hex - ascii");		set_Value (COLUMNNAME_CharEncoding, CharEncoding);
	}

	/** Get LLP Frame Encoding.
		@return LLP Frame Encoding	  */
	public String getCharEncoding () 
	{
		return (String)get_Value(COLUMNNAME_CharEncoding);
	}

	/** Set Encoding.
		@param Encoding 
		Messaage encoding
	  */
	public void setEncoding (String Encoding)
	{
		set_Value (COLUMNNAME_Encoding, Encoding);
	}

	/** Get Encoding.
		@return Messaage encoding
	  */
	public String getEncoding () 
	{
		return (String)get_Value(COLUMNNAME_Encoding);
	}

	/** Set End Of Message Character.
		@param EndOfMessage End Of Message Character	  */
	public void setEndOfMessage (String EndOfMessage)
	{
		if (EndOfMessage == null)
			throw new IllegalArgumentException ("EndOfMessage is mandatory.");
		set_Value (COLUMNNAME_EndOfMessage, EndOfMessage);
	}

	/** Get End Of Message Character.
		@return End Of Message Character	  */
	public String getEndOfMessage () 
	{
		return (String)get_Value(COLUMNNAME_EndOfMessage);
	}

	public I_HL7_BPartner getHL7_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_BPartner.Table_Name);
        I_HL7_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Business Partner.
		@param HL7_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID)
	{
		if (HL7_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HL7_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_BPartner_ID, Integer.valueOf(HL7_BPartner_ID));
	}

	/** Get HL7 Business Partner.
		@return Identifies a Business Partner
	  */
	public int getHL7_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Business Partner LLP.
		@param HL7_BPartnerLLP_ID HL7 Business Partner LLP	  */
	public void setHL7_BPartnerLLP_ID (int HL7_BPartnerLLP_ID)
	{
		if (HL7_BPartnerLLP_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HL7_BPartnerLLP_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_BPartnerLLP_ID, Integer.valueOf(HL7_BPartnerLLP_ID));
	}

	/** Get HL7 Business Partner LLP.
		@return HL7 Business Partner LLP	  */
	public int getHL7_BPartnerLLP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartnerLLP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Inbound.
		@param IsInbound Is Inbound	  */
	public void setIsInbound (boolean IsInbound)
	{
		set_Value (COLUMNNAME_IsInbound, Boolean.valueOf(IsInbound));
	}

	/** Get Is Inbound.
		@return Is Inbound	  */
	public boolean isInbound () 
	{
		Object oo = get_Value(COLUMNNAME_IsInbound);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** KeepConnectionOpen AD_Reference_ID=1200251 */
	public static final int KEEPCONNECTIONOPEN_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String KEEPCONNECTIONOPEN_NO = "0";
	/** Yes = 1 */
	public static final String KEEPCONNECTIONOPEN_Yes = "1";
	/** Set Keep Connection Open.
		@param KeepConnectionOpen Keep Connection Open	  */
	public void setKeepConnectionOpen (String KeepConnectionOpen)
	{

		if (KeepConnectionOpen == null || KeepConnectionOpen.equals("0") || KeepConnectionOpen.equals("1")); else throw new IllegalArgumentException ("KeepConnectionOpen Invalid value - " + KeepConnectionOpen + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_KeepConnectionOpen, KeepConnectionOpen);
	}

	/** Get Keep Connection Open.
		@return Keep Connection Open	  */
	public String getKeepConnectionOpen () 
	{
		return (String)get_Value(COLUMNNAME_KeepConnectionOpen);
	}

	/** Set LLP Host Address.
		@param LLPAddress LLP Host Address	  */
	public void setLLPAddress (String LLPAddress)
	{
		if (LLPAddress == null)
			throw new IllegalArgumentException ("LLPAddress is mandatory.");
		set_Value (COLUMNNAME_LLPAddress, LLPAddress);
	}

	/** Get LLP Host Address.
		@return LLP Host Address	  */
	public String getLLPAddress () 
	{
		return (String)get_Value(COLUMNNAME_LLPAddress);
	}

	/** Set LLP Port.
		@param LLPPort LLP Port	  */
	public void setLLPPort (BigDecimal LLPPort)
	{
		if (LLPPort == null)
			throw new IllegalArgumentException ("LLPPort is mandatory.");
		set_Value (COLUMNNAME_LLPPort, LLPPort);
	}

	/** Get LLP Port.
		@return LLP Port	  */
	public BigDecimal getLLPPort () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LLPPort);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Maximun Retry Count.
		@param MaxRetryCount Maximun Retry Count	  */
	public void setMaxRetryCount (BigDecimal MaxRetryCount)
	{
		if (MaxRetryCount == null)
			throw new IllegalArgumentException ("MaxRetryCount is mandatory.");
		set_Value (COLUMNNAME_MaxRetryCount, MaxRetryCount);
	}

	/** Get Maximun Retry Count.
		@return Maximun Retry Count	  */
	public BigDecimal getMaxRetryCount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxRetryCount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reconnect Intervals (ms).
		@param ReconnectMillisecs Reconnect Intervals (ms)	  */
	public void setReconnectMillisecs (int ReconnectMillisecs)
	{
		set_Value (COLUMNNAME_ReconnectMillisecs, Integer.valueOf(ReconnectMillisecs));
	}

	/** Get Reconnect Intervals (ms).
		@return Reconnect Intervals (ms)	  */
	public int getReconnectMillisecs () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReconnectMillisecs);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Record Separator Char.
		@param RecordSeparator Record Separator Char	  */
	public void setRecordSeparator (String RecordSeparator)
	{
		if (RecordSeparator == null)
			throw new IllegalArgumentException ("RecordSeparator is mandatory.");
		set_Value (COLUMNNAME_RecordSeparator, RecordSeparator);
	}

	/** Get Record Separator Char.
		@return Record Separator Char	  */
	public String getRecordSeparator () 
	{
		return (String)get_Value(COLUMNNAME_RecordSeparator);
	}

	/** Set End of Segment Char.
		@param SegmentEnd End of Segment Char	  */
	public void setSegmentEnd (String SegmentEnd)
	{
		if (SegmentEnd == null)
			throw new IllegalArgumentException ("SegmentEnd is mandatory.");
		set_Value (COLUMNNAME_SegmentEnd, SegmentEnd);
	}

	/** Get End of Segment Char.
		@return End of Segment Char	  */
	public String getSegmentEnd () 
	{
		return (String)get_Value(COLUMNNAME_SegmentEnd);
	}

	/** Set Send Timeout (ms).
		@param SendTimeout Send Timeout (ms)	  */
	public void setSendTimeout (BigDecimal SendTimeout)
	{
		if (SendTimeout == null)
			throw new IllegalArgumentException ("SendTimeout is mandatory.");
		set_Value (COLUMNNAME_SendTimeout, SendTimeout);
	}

	/** Get Send Timeout (ms).
		@return Send Timeout (ms)	  */
	public BigDecimal getSendTimeout () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SendTimeout);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Start of Message Char.
		@param StartOfMessage Start of Message Char	  */
	public void setStartOfMessage (String StartOfMessage)
	{
		if (StartOfMessage == null)
			throw new IllegalArgumentException ("StartOfMessage is mandatory.");
		set_Value (COLUMNNAME_StartOfMessage, StartOfMessage);
	}

	/** Get Start of Message Char.
		@return Start of Message Char	  */
	public String getStartOfMessage () 
	{
		return (String)get_Value(COLUMNNAME_StartOfMessage);
	}

	/** Set Template.
		@param Template 
		Message Template
	  */
	public void setTemplate (String Template)
	{
		set_Value (COLUMNNAME_Template, Template);
	}

	/** Get Template.
		@return Message Template
	  */
	public String getTemplate () 
	{
		return (String)get_Value(COLUMNNAME_Template);
	}
}