/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_BPartnerEmail
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_BPartnerEmail extends PO implements I_HL7_BPartnerEmail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartnerEmail (Properties ctx, int HL7_BPartnerEmail_ID, String trxName)
    {
      super (ctx, HL7_BPartnerEmail_ID, trxName);
      /** if (HL7_BPartnerEmail_ID == 0)
        {
			setHL7_BPartnerEmail_ID (0);
			setHL7_BPartner_ID (0);
			setMailFrom (null);
			setMailTo (null);
			setSMTPHost (null);
			setSMTPPort (0);
// 25
			setUserName (null);
			setUserPassword (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartnerEmail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_BPartnerEmail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** ContentType AD_Reference_ID=1200246 */
	public static final int CONTENTTYPE_AD_Reference_ID=1200246;
	/** text/html = text/html */
	public static final String CONTENTTYPE_TextHtml = "text/html";
	/** text/plain = text/plain */
	public static final String CONTENTTYPE_TextPlain = "text/plain";
	/** Set Content Type.
		@param ContentType Content Type	  */
	public void setContentType (String ContentType)
	{

		if (ContentType == null || ContentType.equals("text/html") || ContentType.equals("text/plain")); else throw new IllegalArgumentException ("ContentType Invalid value - " + ContentType + " - Reference_ID=1200246 - text/html - text/plain");		set_Value (COLUMNNAME_ContentType, ContentType);
	}

	/** Get Content Type.
		@return Content Type	  */
	public String getContentType () 
	{
		return (String)get_Value(COLUMNNAME_ContentType);
	}

	/** Set HL7 Business Partner Email.
		@param HL7_BPartnerEmail_ID 
		HL7 Business Partner Email configuration
	  */
	public void setHL7_BPartnerEmail_ID (int HL7_BPartnerEmail_ID)
	{
		if (HL7_BPartnerEmail_ID < 1)
			 throw new IllegalArgumentException ("HL7_BPartnerEmail_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_BPartnerEmail_ID, Integer.valueOf(HL7_BPartnerEmail_ID));
	}

	/** Get HL7 Business Partner Email.
		@return HL7 Business Partner Email configuration
	  */
	public int getHL7_BPartnerEmail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartnerEmail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("HL7_BPartner_ID is mandatory.");
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

	/** Set Mail Body.
		@param MailBody 
		Mail Body
	  */
	public void setMailBody (String MailBody)
	{
		set_Value (COLUMNNAME_MailBody, MailBody);
	}

	/** Get Mail Body.
		@return Mail Body
	  */
	public String getMailBody () 
	{
		return (String)get_Value(COLUMNNAME_MailBody);
	}

	/** Set Mail From.
		@param MailFrom Mail From	  */
	public void setMailFrom (String MailFrom)
	{
		if (MailFrom == null)
			throw new IllegalArgumentException ("MailFrom is mandatory.");
		set_Value (COLUMNNAME_MailFrom, MailFrom);
	}

	/** Get Mail From.
		@return Mail From	  */
	public String getMailFrom () 
	{
		return (String)get_Value(COLUMNNAME_MailFrom);
	}

	/** Set Mail To.
		@param MailTo Mail To	  */
	public void setMailTo (String MailTo)
	{
		if (MailTo == null)
			throw new IllegalArgumentException ("MailTo is mandatory.");
		set_Value (COLUMNNAME_MailTo, MailTo);
	}

	/** Get Mail To.
		@return Mail To	  */
	public String getMailTo () 
	{
		return (String)get_Value(COLUMNNAME_MailTo);
	}

	/** SecureConnection AD_Reference_ID=1200245 */
	public static final int SECURECONNECTION_AD_Reference_ID=1200245;
	/** None = none */
	public static final String SECURECONNECTION_None = "none";
	/** TLS = tls */
	public static final String SECURECONNECTION_TLS = "tls";
	/** SSL = ssl */
	public static final String SECURECONNECTION_SSL = "ssl";
	/** Set Secure Connection.
		@param SecureConnection Secure Connection	  */
	public void setSecureConnection (String SecureConnection)
	{

		if (SecureConnection == null || SecureConnection.equals("none") || SecureConnection.equals("tls") || SecureConnection.equals("ssl")); else throw new IllegalArgumentException ("SecureConnection Invalid value - " + SecureConnection + " - Reference_ID=1200245 - none - tls - ssl");		set_Value (COLUMNNAME_SecureConnection, SecureConnection);
	}

	/** Get Secure Connection.
		@return Secure Connection	  */
	public String getSecureConnection () 
	{
		return (String)get_Value(COLUMNNAME_SecureConnection);
	}

	/** Set SMTP Server Host.
		@param SMTPHost 
		Hostname of Mail Server for SMTP and IMAP
	  */
	public void setSMTPHost (String SMTPHost)
	{
		if (SMTPHost == null)
			throw new IllegalArgumentException ("SMTPHost is mandatory.");
		set_Value (COLUMNNAME_SMTPHost, SMTPHost);
	}

	/** Get SMTP Server Host.
		@return Hostname of Mail Server for SMTP and IMAP
	  */
	public String getSMTPHost () 
	{
		return (String)get_Value(COLUMNNAME_SMTPHost);
	}

	/** Set SMTP Server Port.
		@param SMTPPort 
		Simple Mail Transfer Protocol Server Port
	  */
	public void setSMTPPort (int SMTPPort)
	{
		set_Value (COLUMNNAME_SMTPPort, Integer.valueOf(SMTPPort));
	}

	/** Get SMTP Server Port.
		@return Simple Mail Transfer Protocol Server Port
	  */
	public int getSMTPPort () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SMTPPort);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Subject.
		@param Subject 
		Email Message Subject
	  */
	public void setSubject (String Subject)
	{
		set_Value (COLUMNNAME_Subject, Subject);
	}

	/** Get Subject.
		@return Email Message Subject
	  */
	public String getSubject () 
	{
		return (String)get_Value(COLUMNNAME_Subject);
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		if (UserName == null)
			throw new IllegalArgumentException ("UserName is mandatory.");
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}

	/** Set User Password.
		@param UserPassword User Password	  */
	public void setUserPassword (String UserPassword)
	{
		if (UserPassword == null)
			throw new IllegalArgumentException ("UserPassword is mandatory.");
		set_Value (COLUMNNAME_UserPassword, UserPassword);
	}

	/** Get User Password.
		@return User Password	  */
	public String getUserPassword () 
	{
		return (String)get_Value(COLUMNNAME_UserPassword);
	}
}