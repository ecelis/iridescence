/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_Dashboard
 *  @author Generated Class 
 *  @version Release @VERSION@ - $Id$ */
public class X_HL7_Dashboard extends PO implements I_HL7_Dashboard, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Dashboard (Properties ctx, int HL7_Dashboard_ID, String trxName)
    {
      super (ctx, HL7_Dashboard_ID, trxName);
      /** if (HL7_Dashboard_ID == 0)
        {
			setHL7_Dashboard_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_Dashboard (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_HL7_Dashboard[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** GenerateMessage AD_Reference_ID=1200430 */
	public static final int GENERATEMESSAGE_AD_Reference_ID=1200430;
	/** Regenerate Message = N */
	public static final String GENERATEMESSAGE_RegenerateMessage = "N";
	/** Set Generate Message.
		@param GenerateMessage Generate Message	  */
	public void setGenerateMessage (String GenerateMessage)
	{

		if (GenerateMessage == null || GenerateMessage.equals("N")); else throw new IllegalArgumentException ("GenerateMessage Invalid value - " + GenerateMessage + " - Reference_ID=1200430 - N");		set_Value (COLUMNNAME_GenerateMessage, GenerateMessage);
	}

	/** Get Generate Message.
		@return Generate Message	  */
	public String getGenerateMessage () 
	{
		return (String)get_Value(COLUMNNAME_GenerateMessage);
	}

	/** Set HL7 Dashboard.
		@param HL7_Dashboard_ID HL7 Dashboard	  */
	public void setHL7_Dashboard_ID (int HL7_Dashboard_ID)
	{
		if (HL7_Dashboard_ID < 1)
			 throw new IllegalArgumentException ("HL7_Dashboard_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Dashboard_ID, Integer.valueOf(HL7_Dashboard_ID));
	}

	/** Get HL7 Dashboard.
		@return HL7 Dashboard	  */
	public int getHL7_Dashboard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Dashboard_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_MessageConf getHL7_MessageConf() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_MessageConf.Table_Name);
        I_HL7_MessageConf result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_MessageConf)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_MessageConf_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Message Configuration.
		@param HL7_MessageConf_ID 
		Configuration of message and the process that generate them
	  */
	public void setHL7_MessageConf_ID (int HL7_MessageConf_ID)
	{
		if (HL7_MessageConf_ID < 1) 
			set_Value (COLUMNNAME_HL7_MessageConf_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_MessageConf_ID, Integer.valueOf(HL7_MessageConf_ID));
	}

	/** Get HL7 Message Configuration.
		@return Configuration of message and the process that generate them
	  */
	public int getHL7_MessageConf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageConf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Message.
		@param Message 
		EMail Message
	  */
	public void setMessage (String Message)
	{
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage () 
	{
		return (String)get_Value(COLUMNNAME_Message);
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Record_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SendMessage AD_Reference_ID=1200431 */
	public static final int SENDMESSAGE_AD_Reference_ID=1200431;
	/** Resend Message = N */
	public static final String SENDMESSAGE_ResendMessage = "N";
	/** Set Send Message.
		@param SendMessage 
		Send Message
	  */
	public void setSendMessage (String SendMessage)
	{

		if (SendMessage == null || SendMessage.equals("N")); else throw new IllegalArgumentException ("SendMessage Invalid value - " + SendMessage + " - Reference_ID=1200431 - N");		set_Value (COLUMNNAME_SendMessage, SendMessage);
	}

	/** Get Send Message.
		@return Send Message
	  */
	public String getSendMessage () 
	{
		return (String)get_Value(COLUMNNAME_SendMessage);
	}

	/** Status AD_Reference_ID=1200419 */
	public static final int STATUS_AD_Reference_ID=1200419;
	/** Not  Sent = NS */
	public static final String STATUS_NotSent = "NS";
	/** Generated with Errors = GE */
	public static final String STATUS_GeneratedWithErrors = "GE";
	/** Successfully Sent = SS */
	public static final String STATUS_SuccessfullySent = "SS";
	/** Sent, waiting for Aknowledgment = SW */
	public static final String STATUS_SentWaitingForAknowledgment = "SW";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("NS") || Status.equals("GE") || Status.equals("SS") || Status.equals("SW")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200419 - NS - GE - SS - SW");		set_ValueNoCheck (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}