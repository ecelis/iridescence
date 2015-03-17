/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_BPartnerFTP
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_BPartnerFTP extends PO implements I_HL7_BPartnerFTP, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartnerFTP (Properties ctx, int HL7_BPartnerFTP_ID, String trxName)
    {
      super (ctx, HL7_BPartnerFTP_ID, trxName);
      /** if (HL7_BPartnerFTP_ID == 0)
        {
			setFtpDirectory (null);
			setFtpHost (null);
			setHL7_BPartnerFTP_ID (0);
			setHL7_BPartner_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartnerFTP (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_BPartnerFTP[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Anonymous AD_Reference_ID=1200251 */
	public static final int ANONYMOUS_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String ANONYMOUS_NO = "0";
	/** Yes = 1 */
	public static final String ANONYMOUS_Yes = "1";
	/** Set Anonymous.
		@param Anonymous 
		Anonymous User
	  */
	public void setAnonymous (String Anonymous)
	{

		if (Anonymous == null || Anonymous.equals("0") || Anonymous.equals("1")); else throw new IllegalArgumentException ("Anonymous Invalid value - " + Anonymous + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_Anonymous, Anonymous);
	}

	/** Get Anonymous.
		@return Anonymous User
	  */
	public String getAnonymous () 
	{
		return (String)get_Value(COLUMNNAME_Anonymous);
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

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** FileType AD_Reference_ID=1200219 */
	public static final int FILETYPE_AD_Reference_ID=1200219;
	/** Binary = 1 */
	public static final String FILETYPE_Binary = "1";
	/** Ascii = 0 */
	public static final String FILETYPE_Ascii = "0";
	/** Set File Type.
		@param FileType File Type	  */
	public void setFileType (String FileType)
	{

		if (FileType == null || FileType.equals("1") || FileType.equals("0")); else throw new IllegalArgumentException ("FileType Invalid value - " + FileType + " - Reference_ID=1200219 - 1 - 0");		set_Value (COLUMNNAME_FileType, FileType);
	}

	/** Get File Type.
		@return File Type	  */
	public String getFileType () 
	{
		return (String)get_Value(COLUMNNAME_FileType);
	}

	/** Set FTP Directory /.
		@param FtpDirectory 
		ftp directory path
	  */
	public void setFtpDirectory (String FtpDirectory)
	{
		if (FtpDirectory == null)
			throw new IllegalArgumentException ("FtpDirectory is mandatory.");
		set_Value (COLUMNNAME_FtpDirectory, FtpDirectory);
	}

	/** Get FTP Directory /.
		@return ftp directory path
	  */
	public String getFtpDirectory () 
	{
		return (String)get_Value(COLUMNNAME_FtpDirectory);
	}

	/** Set FTP Host ftp://.
		@param FtpHost 
		IP, Address or URL of FTP server
	  */
	public void setFtpHost (String FtpHost)
	{
		if (FtpHost == null)
			throw new IllegalArgumentException ("FtpHost is mandatory.");
		set_Value (COLUMNNAME_FtpHost, FtpHost);
	}

	/** Get FTP Host ftp://.
		@return IP, Address or URL of FTP server
	  */
	public String getFtpHost () 
	{
		return (String)get_Value(COLUMNNAME_FtpHost);
	}

	/** Set BPartner FTP configuration.
		@param HL7_BPartnerFTP_ID BPartner FTP configuration	  */
	public void setHL7_BPartnerFTP_ID (int HL7_BPartnerFTP_ID)
	{
		if (HL7_BPartnerFTP_ID < 1)
			 throw new IllegalArgumentException ("HL7_BPartnerFTP_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_BPartnerFTP_ID, Integer.valueOf(HL7_BPartnerFTP_ID));
	}

	/** Get BPartner FTP configuration.
		@return BPartner FTP configuration	  */
	public int getHL7_BPartnerFTP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartnerFTP_ID);
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

	/** PassiveMode AD_Reference_ID=1200251 */
	public static final int PASSIVEMODE_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String PASSIVEMODE_NO = "0";
	/** Yes = 1 */
	public static final String PASSIVEMODE_Yes = "1";
	/** Set Passive Mode.
		@param PassiveMode Passive Mode	  */
	public void setPassiveMode (String PassiveMode)
	{

		if (PassiveMode == null || PassiveMode.equals("0") || PassiveMode.equals("1")); else throw new IllegalArgumentException ("PassiveMode Invalid value - " + PassiveMode + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_PassiveMode, PassiveMode);
	}

	/** Get Passive Mode.
		@return Passive Mode	  */
	public String getPassiveMode () 
	{
		return (String)get_Value(COLUMNNAME_PassiveMode);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
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

	/** Set Test Write.
		@param TestWrite 
		Test the configuration
	  */
	public void setTestWrite (String TestWrite)
	{
		set_Value (COLUMNNAME_TestWrite, TestWrite);
	}

	/** Get Test Write.
		@return Test the configuration
	  */
	public String getTestWrite () 
	{
		return (String)get_Value(COLUMNNAME_TestWrite);
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}

	/** ValidateConnection AD_Reference_ID=1200251 */
	public static final int VALIDATECONNECTION_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String VALIDATECONNECTION_NO = "0";
	/** Yes = 1 */
	public static final String VALIDATECONNECTION_Yes = "1";
	/** Set Validate Connection.
		@param ValidateConnection Validate Connection	  */
	public void setValidateConnection (String ValidateConnection)
	{

		if (ValidateConnection == null || ValidateConnection.equals("0") || ValidateConnection.equals("1")); else throw new IllegalArgumentException ("ValidateConnection Invalid value - " + ValidateConnection + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_ValidateConnection, ValidateConnection);
	}

	/** Get Validate Connection.
		@return Validate Connection	  */
	public String getValidateConnection () 
	{
		return (String)get_Value(COLUMNNAME_ValidateConnection);
	}
}