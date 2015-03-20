/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_Note
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Note extends PO implements I_HL7_Note, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Note (Properties ctx, int HL7_Note_ID, String trxName)
    {
      super (ctx, HL7_Note_ID, trxName);
      /** if (HL7_Note_ID == 0)
        {
			setAD_User_ID (0);
			setHL7_Note_ID (0);
			setIsGenerated (false);
			setProcess (null);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_HL7_Note (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Note[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Message getAD_Message() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Message.Table_Name);
        I_AD_Message result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Message)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Message_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Message.
		@param AD_Message_ID 
		System Message
	  */
	public void setAD_Message_ID (int AD_Message_ID)
	{
		if (AD_Message_ID < 1) 
			set_Value (COLUMNNAME_AD_Message_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Message_ID, Integer.valueOf(AD_Message_ID));
	}

	/** Get Message.
		@return System Message
	  */
	public int getAD_Message_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Message_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document.
		@param Document Document	  */
	public void setDocument (byte[] Document)
	{
		set_Value (COLUMNNAME_Document, Document);
	}

	/** Get Document.
		@return Document	  */
	public byte[] getDocument () 
	{
		return (byte[])get_Value(COLUMNNAME_Document);
	}

	/** Set HL7 Note.
		@param HL7_Note_ID HL7 Note	  */
	public void setHL7_Note_ID (int HL7_Note_ID)
	{
		if (HL7_Note_ID < 1)
			 throw new IllegalArgumentException ("HL7_Note_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Note_ID, Integer.valueOf(HL7_Note_ID));
	}

	/** Get HL7 Note.
		@return HL7 Note	  */
	public int getHL7_Note_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Note_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generated.
		@param IsGenerated 
		This Line is generated
	  */
	public void setIsGenerated (boolean IsGenerated)
	{
		set_Value (COLUMNNAME_IsGenerated, Boolean.valueOf(IsGenerated));
	}

	/** Get Generated.
		@return This Line is generated
	  */
	public boolean isGenerated () 
	{
		Object oo = get_Value(COLUMNNAME_IsGenerated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Process AD_Reference_ID=1200388 */
	public static final int PROCESS_AD_Reference_ID=1200388;
	/** Send Immunization Records update = VXU */
	public static final String PROCESS_SendImmunizationRecordsUpdate = "VXU";
	/** Query Immunization Records = VXQ */
	public static final String PROCESS_QueryImmunizationRecords = "VXQ";
	/** CDA Received Document = CRD */
	public static final String PROCESS_CDAReceivedDocument = "CRD";
	/** CDA Sended Document = CSD */
	public static final String PROCESS_CDASendedDocument = "CSD";
	/** Elegibility Response = 271 */
	public static final String PROCESS_ElegibilityResponse = "271";
	/** Claim Acknowledgment = 997 */
	public static final String PROCESS_ClaimAcknowledgment = "997";
	/** Continuity of Care Document = CCD */
	public static final String PROCESS_ContinuityOfCareDocument = "CCD";
	/** Continuity of Care Record = CCR */
	public static final String PROCESS_ContinuityOfCareRecord = "CCR";
	/** Set Process Name.
		@param Process Process Name	  */
	public void setProcess (String Process)
	{
		if (Process == null) throw new IllegalArgumentException ("Process is mandatory");
		if (Process.equals("VXU") || Process.equals("VXQ") || Process.equals("CRD") || Process.equals("CSD") || Process.equals("271") || Process.equals("997") || Process.equals("CCD") || Process.equals("CCR")); else throw new IllegalArgumentException ("Process Invalid value - " + Process + " - Reference_ID=1200388 - VXU - VXQ - CRD - CSD - 271 - 997 - CCD - CCR");		set_Value (COLUMNNAME_Process, Process);
	}

	/** Get Process Name.
		@return Process Name	  */
	public String getProcess () 
	{
		return (String)get_Value(COLUMNNAME_Process);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
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

	/** Status AD_Reference_ID=131 */
	public static final int STATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String STATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String STATUS_Completed = "CO";
	/** Approved = AP */
	public static final String STATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String STATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String STATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String STATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String STATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String STATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String STATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String STATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String STATUS_WaitingConfirmation = "WC";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("DR") || Status.equals("CO") || Status.equals("AP") || Status.equals("NA") || Status.equals("VO") || Status.equals("IN") || Status.equals("RE") || Status.equals("CL") || Status.equals("??") || Status.equals("IP") || Status.equals("WP") || Status.equals("WC")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}

	/** Set TextXML.
		@param TextXML TextXML	  */
	public void setTextXML (String TextXML)
	{
		set_Value (COLUMNNAME_TextXML, TextXML);
	}

	/** Get TextXML.
		@return TextXML	  */
	public String getTextXML () 
	{
		return (String)get_Value(COLUMNNAME_TextXML);
	}
}