/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Client
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_Client extends PO implements I_AD_Client, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Client (Properties ctx, int AD_Client_ID, String trxName)
    {
      super (ctx, AD_Client_ID, trxName);
      /** if (AD_Client_ID == 0)
        {
			setAutoArchive (null);
// N
			setIsCostImmediate (false);
// N
			setIsMultiLingualDocument (false);
			setIsPostImmediate (false);
// N
			setIsServerEMail (false);
			setIsSmtpAuthorization (false);
// N
			setIsUseASP (false);
// N
			setIsUseBetaFunctions (true);
// Y
			setMMPolicy (null);
// F
			setName (null);
			setSSLConnection (false);
			setStoreArchiveOnFileSystem (false);
			setStoreAttachmentsOnFileSystem (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Client (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Client[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AD_Language AD_Reference_ID=327 */
	public static final int AD_LANGUAGE_AD_Reference_ID=327;
	/** Set Language.
		@param AD_Language 
		Language for this entity
	  */
	public void setAD_Language (String AD_Language)
	{
		set_Value (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language () 
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
	}

	public I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_ReplicationStrategy.Table_Name);
        I_AD_ReplicationStrategy result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_ReplicationStrategy)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_ReplicationStrategy_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Replication Strategy.
		@param AD_ReplicationStrategy_ID 
		Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
	{
		if (AD_ReplicationStrategy_ID < 1) 
			set_Value (COLUMNNAME_AD_ReplicationStrategy_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ReplicationStrategy_ID, Integer.valueOf(AD_ReplicationStrategy_ID));
	}

	/** Get Replication Strategy.
		@return Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AutoArchive AD_Reference_ID=334 */
	public static final int AUTOARCHIVE_AD_Reference_ID=334;
	/** None = N */
	public static final String AUTOARCHIVE_None = "N";
	/** All (Reports, Documents) = 1 */
	public static final String AUTOARCHIVE_AllReportsDocuments = "1";
	/** Documents = 2 */
	public static final String AUTOARCHIVE_Documents = "2";
	/** External Documents = 3 */
	public static final String AUTOARCHIVE_ExternalDocuments = "3";
	/** Set Auto Archive.
		@param AutoArchive 
		Enable and level of automatic Archive of documents
	  */
	public void setAutoArchive (String AutoArchive)
	{
		if (AutoArchive == null) throw new IllegalArgumentException ("AutoArchive is mandatory");
		if (AutoArchive.equals("N") || AutoArchive.equals("1") || AutoArchive.equals("2") || AutoArchive.equals("3")); else throw new IllegalArgumentException ("AutoArchive Invalid value - " + AutoArchive + " - Reference_ID=334 - N - 1 - 2 - 3");		set_Value (COLUMNNAME_AutoArchive, AutoArchive);
	}

	/** Get Auto Archive.
		@return Enable and level of automatic Archive of documents
	  */
	public String getAutoArchive () 
	{
		return (String)get_Value(COLUMNNAME_AutoArchive);
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

	/** Set Document Directory.
		@param DocumentDir 
		Directory for documents from the application server
	  */
	public void setDocumentDir (String DocumentDir)
	{
		set_Value (COLUMNNAME_DocumentDir, DocumentDir);
	}

	/** Get Document Directory.
		@return Directory for documents from the application server
	  */
	public String getDocumentDir () 
	{
		return (String)get_Value(COLUMNNAME_DocumentDir);
	}

	/** Set EMail Test.
		@param EMailTest 
		Test EMail
	  */
	public void setEMailTest (String EMailTest)
	{
		set_Value (COLUMNNAME_EMailTest, EMailTest);
	}

	/** Get EMail Test.
		@return Test EMail
	  */
	public String getEMailTest () 
	{
		return (String)get_Value(COLUMNNAME_EMailTest);
	}

	/** Set Image.
		@param Image Image	  */
	public void setImage (String Image)
	{
		set_Value (COLUMNNAME_Image, Image);
	}

	/** Get Image.
		@return Image	  */
	public String getImage () 
	{
		return (String)get_Value(COLUMNNAME_Image);
	}

	/** Set Cost Immediately.
		@param IsCostImmediate 
		Update Costs immediately for testing
	  */
	public void setIsCostImmediate (boolean IsCostImmediate)
	{
		set_Value (COLUMNNAME_IsCostImmediate, Boolean.valueOf(IsCostImmediate));
	}

	/** Get Cost Immediately.
		@return Update Costs immediately for testing
	  */
	public boolean isCostImmediate () 
	{
		Object oo = get_Value(COLUMNNAME_IsCostImmediate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Multi Lingual Documents.
		@param IsMultiLingualDocument 
		Documents are Multi Lingual
	  */
	public void setIsMultiLingualDocument (boolean IsMultiLingualDocument)
	{
		set_Value (COLUMNNAME_IsMultiLingualDocument, Boolean.valueOf(IsMultiLingualDocument));
	}

	/** Get Multi Lingual Documents.
		@return Documents are Multi Lingual
	  */
	public boolean isMultiLingualDocument () 
	{
		Object oo = get_Value(COLUMNNAME_IsMultiLingualDocument);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post Immediately.
		@param IsPostImmediate 
		Post the accounting immediately for testing
	  */
	public void setIsPostImmediate (boolean IsPostImmediate)
	{
		set_Value (COLUMNNAME_IsPostImmediate, Boolean.valueOf(IsPostImmediate));
	}

	/** Get Post Immediately.
		@return Post the accounting immediately for testing
	  */
	public boolean isPostImmediate () 
	{
		Object oo = get_Value(COLUMNNAME_IsPostImmediate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Server EMail.
		@param IsServerEMail 
		Send EMail from Server
	  */
	public void setIsServerEMail (boolean IsServerEMail)
	{
		set_Value (COLUMNNAME_IsServerEMail, Boolean.valueOf(IsServerEMail));
	}

	/** Get Server EMail.
		@return Send EMail from Server
	  */
	public boolean isServerEMail () 
	{
		Object oo = get_Value(COLUMNNAME_IsServerEMail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SMTP Authentication.
		@param IsSmtpAuthorization 
		Your mail server requires Authentication
	  */
	public void setIsSmtpAuthorization (boolean IsSmtpAuthorization)
	{
		set_Value (COLUMNNAME_IsSmtpAuthorization, Boolean.valueOf(IsSmtpAuthorization));
	}

	/** Get SMTP Authentication.
		@return Your mail server requires Authentication
	  */
	public boolean isSmtpAuthorization () 
	{
		Object oo = get_Value(COLUMNNAME_IsSmtpAuthorization);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsUseASP.
		@param IsUseASP IsUseASP	  */
	public void setIsUseASP (boolean IsUseASP)
	{
		set_Value (COLUMNNAME_IsUseASP, Boolean.valueOf(IsUseASP));
	}

	/** Get IsUseASP.
		@return IsUseASP	  */
	public boolean isUseASP () 
	{
		Object oo = get_Value(COLUMNNAME_IsUseASP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Use Beta Functions.
		@param IsUseBetaFunctions 
		Enable the use of Beta Functionality
	  */
	public void setIsUseBetaFunctions (boolean IsUseBetaFunctions)
	{
		set_Value (COLUMNNAME_IsUseBetaFunctions, Boolean.valueOf(IsUseBetaFunctions));
	}

	/** Get Use Beta Functions.
		@return Enable the use of Beta Functionality
	  */
	public boolean isUseBetaFunctions () 
	{
		Object oo = get_Value(COLUMNNAME_IsUseBetaFunctions);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set LDAPQUERY.
		@param LDAPQUERY LDAPQUERY	  */
	public void setLDAPQUERY (String LDAPQUERY)
	{
		set_Value (COLUMNNAME_LDAPQUERY, LDAPQUERY);
	}

	/** Get LDAPQUERY.
		@return LDAPQUERY	  */
	public String getLDAPQUERY () 
	{
		return (String)get_Value(COLUMNNAME_LDAPQUERY);
	}

	/** MMPolicy AD_Reference_ID=335 */
	public static final int MMPOLICY_AD_Reference_ID=335;
	/** LiFo = L */
	public static final String MMPOLICY_LiFo = "L";
	/** FiFo = F */
	public static final String MMPOLICY_FiFo = "F";
	/** Set Material Policy.
		@param MMPolicy 
		Material Movement Policy
	  */
	public void setMMPolicy (String MMPolicy)
	{
		if (MMPolicy == null) throw new IllegalArgumentException ("MMPolicy is mandatory");
		if (MMPolicy.equals("L") || MMPolicy.equals("F")); else throw new IllegalArgumentException ("MMPolicy Invalid value - " + MMPolicy + " - Reference_ID=335 - L - F");		set_Value (COLUMNNAME_MMPolicy, MMPolicy);
	}

	/** Get Material Policy.
		@return Material Movement Policy
	  */
	public String getMMPolicy () 
	{
		return (String)get_Value(COLUMNNAME_MMPolicy);
	}

	/** Set Model Validation Classes.
		@param ModelValidationClasses 
		List of data model validation classes separated by ;
	  */
	public void setModelValidationClasses (String ModelValidationClasses)
	{
		set_Value (COLUMNNAME_ModelValidationClasses, ModelValidationClasses);
	}

	/** Get Model Validation Classes.
		@return List of data model validation classes separated by ;
	  */
	public String getModelValidationClasses () 
	{
		return (String)get_Value(COLUMNNAME_ModelValidationClasses);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Request EMail.
		@param RequestEMail 
		EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public void setRequestEMail (String RequestEMail)
	{
		set_Value (COLUMNNAME_RequestEMail, RequestEMail);
	}

	/** Get Request EMail.
		@return EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public String getRequestEMail () 
	{
		return (String)get_Value(COLUMNNAME_RequestEMail);
	}

	/** Set Request Folder.
		@param RequestFolder 
		EMail folder to process incoming emails; if empty INBOX is used
	  */
	public void setRequestFolder (String RequestFolder)
	{
		set_Value (COLUMNNAME_RequestFolder, RequestFolder);
	}

	/** Get Request Folder.
		@return EMail folder to process incoming emails; if empty INBOX is used
	  */
	public String getRequestFolder () 
	{
		return (String)get_Value(COLUMNNAME_RequestFolder);
	}

	/** Set Request User.
		@param RequestUser 
		User Name (ID) of the email owner
	  */
	public void setRequestUser (String RequestUser)
	{
		set_Value (COLUMNNAME_RequestUser, RequestUser);
	}

	/** Get Request User.
		@return User Name (ID) of the email owner
	  */
	public String getRequestUser () 
	{
		return (String)get_Value(COLUMNNAME_RequestUser);
	}

	/** Set Request User Password.
		@param RequestUserPW 
		Password of the user name (ID) for mail processing
	  */
	public void setRequestUserPW (String RequestUserPW)
	{
		set_Value (COLUMNNAME_RequestUserPW, RequestUserPW);
	}

	/** Get Request User Password.
		@return Password of the user name (ID) for mail processing
	  */
	public String getRequestUserPW () 
	{
		return (String)get_Value(COLUMNNAME_RequestUserPW);
	}

	/** Set Short Name.
		@param ShortName Short Name	  */
	public void setShortName (String ShortName)
	{
		set_Value (COLUMNNAME_ShortName, ShortName);
	}

	/** Get Short Name.
		@return Short Name	  */
	public String getShortName () 
	{
		return (String)get_Value(COLUMNNAME_ShortName);
	}

	/** Set SMTP Server Host.
		@param SMTPHost 
		Hostname of Mail Server for SMTP and IMAP
	  */
	public void setSMTPHost (String SMTPHost)
	{
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

	/** Set SSL Connection.
		@param SSLConnection SSL Connection	  */
	public void setSSLConnection (boolean SSLConnection)
	{
		set_Value (COLUMNNAME_SSLConnection, Boolean.valueOf(SSLConnection));
	}

	/** Get SSL Connection.
		@return SSL Connection	  */
	public boolean isSSLConnection () 
	{
		Object oo = get_Value(COLUMNNAME_SSLConnection);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SSL Port.
		@param SSLPort SSL Port	  */
	public void setSSLPort (int SSLPort)
	{
		set_Value (COLUMNNAME_SSLPort, Integer.valueOf(SSLPort));
	}

	/** Get SSL Port.
		@return SSL Port	  */
	public int getSSLPort () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SSLPort);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Store Archive On File System.
		@param StoreArchiveOnFileSystem Store Archive On File System	  */
	public void setStoreArchiveOnFileSystem (boolean StoreArchiveOnFileSystem)
	{
		set_Value (COLUMNNAME_StoreArchiveOnFileSystem, Boolean.valueOf(StoreArchiveOnFileSystem));
	}

	/** Get Store Archive On File System.
		@return Store Archive On File System	  */
	public boolean isStoreArchiveOnFileSystem () 
	{
		Object oo = get_Value(COLUMNNAME_StoreArchiveOnFileSystem);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Store Attachments On File System.
		@param StoreAttachmentsOnFileSystem Store Attachments On File System	  */
	public void setStoreAttachmentsOnFileSystem (boolean StoreAttachmentsOnFileSystem)
	{
		set_Value (COLUMNNAME_StoreAttachmentsOnFileSystem, Boolean.valueOf(StoreAttachmentsOnFileSystem));
	}

	/** Get Store Attachments On File System.
		@return Store Attachments On File System	  */
	public boolean isStoreAttachmentsOnFileSystem () 
	{
		Object oo = get_Value(COLUMNNAME_StoreAttachmentsOnFileSystem);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Time Zone.
		@param TimeZone Time Zone	  */
	public void setTimeZone (String TimeZone)
	{
		set_Value (COLUMNNAME_TimeZone, TimeZone);
	}

	/** Get Time Zone.
		@return Time Zone	  */
	public String getTimeZone () 
	{
		return (String)get_Value(COLUMNNAME_TimeZone);
	}

	/** TipoCliente AD_Reference_ID=1200421 */
	public static final int TIPOCLIENTE_AD_Reference_ID=1200421;
	/** PHYSICIAN OFFICE  = P */
	public static final String TIPOCLIENTE_PHYSICIANOFFICE = "P";
	/** OUTPATIENT CARE CENTER  = O */
	public static final String TIPOCLIENTE_OUTPATIENTCARECENTER = "O";
	/** AMBULATORY SURGERY CENTER  = A */
	public static final String TIPOCLIENTE_AMBULATORYSURGERYCENTER = "A";
	/** HOSPITAL CENTER = C */
	public static final String TIPOCLIENTE_HOSPITALCENTER = "C";
	/** Set Client Type.
		@param TipoCliente Client Type	  */
	public void setTipoCliente (String TipoCliente)
	{

		if (TipoCliente == null || TipoCliente.equals("P") || TipoCliente.equals("O") || TipoCliente.equals("A") || TipoCliente.equals("C")); else throw new IllegalArgumentException ("TipoCliente Invalid value - " + TipoCliente + " - Reference_ID=1200421 - P - O - A - C");		set_Value (COLUMNNAME_TipoCliente, TipoCliente);
	}

	/** Get Client Type.
		@return Client Type	  */
	public String getTipoCliente () 
	{
		return (String)get_Value(COLUMNNAME_TipoCliente);
	}

	/** Set Unix Archive Path.
		@param UnixArchivePath Unix Archive Path	  */
	public void setUnixArchivePath (String UnixArchivePath)
	{
		set_Value (COLUMNNAME_UnixArchivePath, UnixArchivePath);
	}

	/** Get Unix Archive Path.
		@return Unix Archive Path	  */
	public String getUnixArchivePath () 
	{
		return (String)get_Value(COLUMNNAME_UnixArchivePath);
	}

	/** Set Unix Attachment Path.
		@param UnixAttachmentPath Unix Attachment Path	  */
	public void setUnixAttachmentPath (String UnixAttachmentPath)
	{
		set_Value (COLUMNNAME_UnixAttachmentPath, UnixAttachmentPath);
	}

	/** Get Unix Attachment Path.
		@return Unix Attachment Path	  */
	public String getUnixAttachmentPath () 
	{
		return (String)get_Value(COLUMNNAME_UnixAttachmentPath);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Windows Archive Path.
		@param WindowsArchivePath Windows Archive Path	  */
	public void setWindowsArchivePath (String WindowsArchivePath)
	{
		set_Value (COLUMNNAME_WindowsArchivePath, WindowsArchivePath);
	}

	/** Get Windows Archive Path.
		@return Windows Archive Path	  */
	public String getWindowsArchivePath () 
	{
		return (String)get_Value(COLUMNNAME_WindowsArchivePath);
	}

	/** Set Windows Attachment Path.
		@param WindowsAttachmentPath Windows Attachment Path	  */
	public void setWindowsAttachmentPath (String WindowsAttachmentPath)
	{
		set_Value (COLUMNNAME_WindowsAttachmentPath, WindowsAttachmentPath);
	}

	/** Get Windows Attachment Path.
		@return Windows Attachment Path	  */
	public String getWindowsAttachmentPath () 
	{
		return (String)get_Value(COLUMNNAME_WindowsAttachmentPath);
	}
}