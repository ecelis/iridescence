/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_BPartnerSFTP
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_BPartnerSFTP extends PO implements I_HL7_BPartnerSFTP, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartnerSFTP (Properties ctx, int HL7_BPartnerSFTP_ID, String trxName)
    {
      super (ctx, HL7_BPartnerSFTP_ID, trxName);
      /** if (HL7_BPartnerSFTP_ID == 0)
        {
			setDirectory (null);
			setFileName (null);
// ${UUID}.hl7
			setHL7_BPartner_ID (0);
			setHL7_BPartnerSFTP_ID (0);
			setPassword (null);
			setSFTPHost (null);
			setUserName (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartnerSFTP (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_BPartnerSFTP[")
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

		if (AppendToFile == null || AppendToFile.equals("0") || AppendToFile.equals("1")); else throw new IllegalArgumentException ("AppendToFile Invalid value - " + AppendToFile + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_AppendToFile, AppendToFile);
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

	/** Set Filter Name Pattern.
		@param Filter 
		Filter Name Pattern
	  */
	public void setFilter (String Filter)
	{
		set_Value (COLUMNNAME_Filter, Filter);
	}

	/** Get Filter Name Pattern.
		@return Filter Name Pattern
	  */
	public String getFilter () 
	{
		return (String)get_Value(COLUMNNAME_Filter);
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

	/** Set HL7 Business Partner SFTP.
		@param HL7_BPartnerSFTP_ID HL7 Business Partner SFTP	  */
	public void setHL7_BPartnerSFTP_ID (int HL7_BPartnerSFTP_ID)
	{
		if (HL7_BPartnerSFTP_ID < 1)
			 throw new IllegalArgumentException ("HL7_BPartnerSFTP_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_BPartnerSFTP_ID, Integer.valueOf(HL7_BPartnerSFTP_ID));
	}

	/** Get HL7 Business Partner SFTP.
		@return HL7 Business Partner SFTP	  */
	public int getHL7_BPartnerSFTP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartnerSFTP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** IsCheckAge AD_Reference_ID=1200251 */
	public static final int ISCHECKAGE_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String ISCHECKAGE_NO = "0";
	/** Yes = 1 */
	public static final String ISCHECKAGE_Yes = "1";
	/** Set Check File Age.
		@param IsCheckAge Check File Age	  */
	public void setIsCheckAge (String IsCheckAge)
	{

		if (IsCheckAge == null || IsCheckAge.equals("0") || IsCheckAge.equals("1")); else throw new IllegalArgumentException ("IsCheckAge Invalid value - " + IsCheckAge + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_IsCheckAge, IsCheckAge);
	}

	/** Get Check File Age.
		@return Check File Age	  */
	public String getIsCheckAge () 
	{
		return (String)get_Value(COLUMNNAME_IsCheckAge);
	}

	/** IsDeleteAfterRead AD_Reference_ID=1200251 */
	public static final int ISDELETEAFTERREAD_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String ISDELETEAFTERREAD_NO = "0";
	/** Yes = 1 */
	public static final String ISDELETEAFTERREAD_Yes = "1";
	/** Set Delete File After Read.
		@param IsDeleteAfterRead Delete File After Read	  */
	public void setIsDeleteAfterRead (String IsDeleteAfterRead)
	{

		if (IsDeleteAfterRead == null || IsDeleteAfterRead.equals("0") || IsDeleteAfterRead.equals("1")); else throw new IllegalArgumentException ("IsDeleteAfterRead Invalid value - " + IsDeleteAfterRead + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_IsDeleteAfterRead, IsDeleteAfterRead);
	}

	/** Get Delete File After Read.
		@return Delete File After Read	  */
	public String getIsDeleteAfterRead () 
	{
		return (String)get_Value(COLUMNNAME_IsDeleteAfterRead);
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

	/** Set On Error Move to.
		@param MoveErrorToFile On Error Move to	  */
	public void setMoveErrorToFile (String MoveErrorToFile)
	{
		set_Value (COLUMNNAME_MoveErrorToFile, MoveErrorToFile);
	}

	/** Get On Error Move to.
		@return On Error Move to	  */
	public String getMoveErrorToFile () 
	{
		return (String)get_Value(COLUMNNAME_MoveErrorToFile);
	}

	/** Set Move to directory.
		@param MoveToDir Move to directory	  */
	public void setMoveToDir (String MoveToDir)
	{
		set_Value (COLUMNNAME_MoveToDir, MoveToDir);
	}

	/** Get Move to directory.
		@return Move to directory	  */
	public String getMoveToDir () 
	{
		return (String)get_Value(COLUMNNAME_MoveToDir);
	}

	/** Set Move File to.
		@param MoveToFile Move File to	  */
	public void setMoveToFile (String MoveToFile)
	{
		set_Value (COLUMNNAME_MoveToFile, MoveToFile);
	}

	/** Get Move File to.
		@return Move File to	  */
	public String getMoveToFile () 
	{
		return (String)get_Value(COLUMNNAME_MoveToFile);
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

	/** Set Polling Frequency.
		@param PollingFreq Polling Frequency	  */
	public void setPollingFreq (int PollingFreq)
	{
		set_Value (COLUMNNAME_PollingFreq, Integer.valueOf(PollingFreq));
	}

	/** Get Polling Frequency.
		@return Polling Frequency	  */
	public int getPollingFreq () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PollingFreq);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PollingType AD_Reference_ID=1200435 */
	public static final int POLLINGTYPE_AD_Reference_ID=1200435;
	/** Interval = interval */
	public static final String POLLINGTYPE_Interval = "interval";
	/** Time = time */
	public static final String POLLINGTYPE_Time = "time";
	/** Set Polling Type.
		@param PollingType Polling Type	  */
	public void setPollingType (String PollingType)
	{

		if (PollingType == null || PollingType.equals("interval") || PollingType.equals("time")); else throw new IllegalArgumentException ("PollingType Invalid value - " + PollingType + " - Reference_ID=1200435 - interval - time");		set_Value (COLUMNNAME_PollingType, PollingType);
	}

	/** Get Polling Type.
		@return Polling Type	  */
	public String getPollingType () 
	{
		return (String)get_Value(COLUMNNAME_PollingType);
	}

	/** Set SFTP Host.
		@param SFTPHost SFTP Host	  */
	public void setSFTPHost (String SFTPHost)
	{
		if (SFTPHost == null)
			throw new IllegalArgumentException ("SFTPHost is mandatory.");
		set_Value (COLUMNNAME_SFTPHost, SFTPHost);
	}

	/** Get SFTP Host.
		@return SFTP Host	  */
	public String getSFTPHost () 
	{
		return (String)get_Value(COLUMNNAME_SFTPHost);
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