/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_BPartnerSMB
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_BPartnerSMB extends PO implements I_HL7_BPartnerSMB, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartnerSMB (Properties ctx, int HL7_BPartnerSMB_ID, String trxName)
    {
      super (ctx, HL7_BPartnerSMB_ID, trxName);
      /** if (HL7_BPartnerSMB_ID == 0)
        {
			setAppendToFile (null);
// 0
			setDirectory (null);
			setEncoding (null);
// DEFAULT_ENCODING
			setFileName (null);
// ${UUID}.hl7
			setHL7_BPartner_ID (0);
			setHL7_BPartnerSMB_ID (0);
			setPassword (null);
			setSMBHost (null);
			setTemplate (null);
// ${message.encodedData}
			setUserName (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartnerSMB (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_BPartnerSMB[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AppendToFile AD_Reference_ID=1200251 */
	public static final int APPENDTOFILE_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String APPENDTOFILE_NO = "0";
	/** Yes = 1 */
	public static final String APPENDTOFILE_Yes = "1";
	/** Set Append To File.
		@param AppendToFile Append To File	  */
	public void setAppendToFile (String AppendToFile)
	{
		if (AppendToFile == null) throw new IllegalArgumentException ("AppendToFile is mandatory");
		if (AppendToFile.equals("0") || AppendToFile.equals("1")); else throw new IllegalArgumentException ("AppendToFile Invalid value - " + AppendToFile + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_AppendToFile, AppendToFile);
	}

	/** Get Append To File.
		@return Append To File	  */
	public String getAppendToFile () 
	{
		return (String)get_Value(COLUMNNAME_AppendToFile);
	}

	/** Set Directory.
		@param Directory Directory	  */
	public void setDirectory (String Directory)
	{
		if (Directory == null)
			throw new IllegalArgumentException ("Directory is mandatory.");
		set_Value (COLUMNNAME_Directory, Directory);
	}

	/** Get Directory.
		@return Directory	  */
	public String getDirectory () 
	{
		return (String)get_Value(COLUMNNAME_Directory);
	}

	/** Set Encoding.
		@param Encoding 
		Messaage encoding
	  */
	public void setEncoding (String Encoding)
	{
		if (Encoding == null)
			throw new IllegalArgumentException ("Encoding is mandatory.");
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
		if (FileName == null)
			throw new IllegalArgumentException ("FileName is mandatory.");
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

	/** Set HL7 Busniess Partner SMB.
		@param HL7_BPartnerSMB_ID HL7 Busniess Partner SMB	  */
	public void setHL7_BPartnerSMB_ID (int HL7_BPartnerSMB_ID)
	{
		if (HL7_BPartnerSMB_ID < 1)
			 throw new IllegalArgumentException ("HL7_BPartnerSMB_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_BPartnerSMB_ID, Integer.valueOf(HL7_BPartnerSMB_ID));
	}

	/** Get HL7 Busniess Partner SMB.
		@return HL7 Busniess Partner SMB	  */
	public int getHL7_BPartnerSMB_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartnerSMB_ID);
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

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		if (Password == null)
			throw new IllegalArgumentException ("Password is mandatory.");
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set SMB Host.
		@param SMBHost SMB Host	  */
	public void setSMBHost (String SMBHost)
	{
		if (SMBHost == null)
			throw new IllegalArgumentException ("SMBHost is mandatory.");
		set_Value (COLUMNNAME_SMBHost, SMBHost);
	}

	/** Get SMB Host.
		@return SMB Host	  */
	public String getSMBHost () 
	{
		return (String)get_Value(COLUMNNAME_SMBHost);
	}

	/** Set Template.
		@param Template 
		Message Template
	  */
	public void setTemplate (String Template)
	{
		if (Template == null)
			throw new IllegalArgumentException ("Template is mandatory.");
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
}