/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ERXConfig
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ERXConfig extends PO implements I_EXME_ERXConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ERXConfig (Properties ctx, int EXME_ERXConfig_ID, String trxName)
    {
      super (ctx, EXME_ERXConfig_ID, trxName);
      /** if (EXME_ERXConfig_ID == 0)
        {
			setDirectoryURL (null);
			setDirectoryUser (null);
			setDirectoryUserPass (null);
			setDownloadURL (null);
			setEXME_ERXConfig_ID (0);
			setName (null);
			setPartnerAccount (null);
			setPassword (null);
			setPortalID (null);
			setURL (null);
			setUsuario (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ERXConfig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ERXConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Data provider ID.
		@param DataProvider 
		Data provider ID
	  */
	public void setDataProvider (String DataProvider)
	{
		set_Value (COLUMNNAME_DataProvider, DataProvider);
	}

	/** Get Data provider ID.
		@return Data provider ID
	  */
	public String getDataProvider () 
	{
		return (String)get_Value(COLUMNNAME_DataProvider);
	}

	/** Set Directory URL.
		@param DirectoryURL 
		Directory URL
	  */
	public void setDirectoryURL (String DirectoryURL)
	{
		if (DirectoryURL == null)
			throw new IllegalArgumentException ("DirectoryURL is mandatory.");
		set_Value (COLUMNNAME_DirectoryURL, DirectoryURL);
	}

	/** Get Directory URL.
		@return Directory URL
	  */
	public String getDirectoryURL () 
	{
		return (String)get_Value(COLUMNNAME_DirectoryURL);
	}

	/** Set Directory user.
		@param DirectoryUser 
		Directory user
	  */
	public void setDirectoryUser (String DirectoryUser)
	{
		if (DirectoryUser == null)
			throw new IllegalArgumentException ("DirectoryUser is mandatory.");
		set_Value (COLUMNNAME_DirectoryUser, DirectoryUser);
	}

	/** Get Directory user.
		@return Directory user
	  */
	public String getDirectoryUser () 
	{
		return (String)get_Value(COLUMNNAME_DirectoryUser);
	}

	/** Set Directory user password.
		@param DirectoryUserPass 
		Directory user password
	  */
	public void setDirectoryUserPass (String DirectoryUserPass)
	{
		if (DirectoryUserPass == null)
			throw new IllegalArgumentException ("DirectoryUserPass is mandatory.");
		set_Value (COLUMNNAME_DirectoryUserPass, DirectoryUserPass);
	}

	/** Get Directory user password.
		@return Directory user password
	  */
	public String getDirectoryUserPass () 
	{
		return (String)get_Value(COLUMNNAME_DirectoryUserPass);
	}

	/** Set Download URL.
		@param DownloadURL 
		URL of the Download files
	  */
	public void setDownloadURL (String DownloadURL)
	{
		if (DownloadURL == null)
			throw new IllegalArgumentException ("DownloadURL is mandatory.");
		set_Value (COLUMNNAME_DownloadURL, DownloadURL);
	}

	/** Get Download URL.
		@return URL of the Download files
	  */
	public String getDownloadURL () 
	{
		return (String)get_Value(COLUMNNAME_DownloadURL);
	}

	/** Set Eprescribing Configuration.
		@param EXME_ERXConfig_ID 
		Eprescribing Configuration
	  */
	public void setEXME_ERXConfig_ID (int EXME_ERXConfig_ID)
	{
		if (EXME_ERXConfig_ID < 1)
			 throw new IllegalArgumentException ("EXME_ERXConfig_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ERXConfig_ID, Integer.valueOf(EXME_ERXConfig_ID));
	}

	/** Get Eprescribing Configuration.
		@return Eprescribing Configuration
	  */
	public int getEXME_ERXConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ERXConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set In Production.
		@param IsInProduction 
		The system is in production
	  */
	public void setIsInProduction (boolean IsInProduction)
	{
		set_Value (COLUMNNAME_IsInProduction, Boolean.valueOf(IsInProduction));
	}

	/** Get In Production.
		@return The system is in production
	  */
	public boolean isInProduction () 
	{
		Object oo = get_Value(COLUMNNAME_IsInProduction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Partner Account.
		@param PartnerAccount Partner Account	  */
	public void setPartnerAccount (String PartnerAccount)
	{
		if (PartnerAccount == null)
			throw new IllegalArgumentException ("PartnerAccount is mandatory.");
		set_Value (COLUMNNAME_PartnerAccount, PartnerAccount);
	}

	/** Get Partner Account.
		@return Partner Account	  */
	public String getPartnerAccount () 
	{
		return (String)get_Value(COLUMNNAME_PartnerAccount);
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

	/** Set Portal ID.
		@param PortalID 
		Portal Identifier
	  */
	public void setPortalID (String PortalID)
	{
		if (PortalID == null)
			throw new IllegalArgumentException ("PortalID is mandatory.");
		set_Value (COLUMNNAME_PortalID, PortalID);
	}

	/** Get Portal ID.
		@return Portal Identifier
	  */
	public String getPortalID () 
	{
		return (String)get_Value(COLUMNNAME_PortalID);
	}

	/** Set Proxy address.
		@param ProxyAddress 
		 Address of your proxy server
	  */
	public void setProxyAddress (String ProxyAddress)
	{
		set_Value (COLUMNNAME_ProxyAddress, ProxyAddress);
	}

	/** Get Proxy address.
		@return  Address of your proxy server
	  */
	public String getProxyAddress () 
	{
		return (String)get_Value(COLUMNNAME_ProxyAddress);
	}

	/** Set Proxy port.
		@param ProxyPort 
		Port of your proxy server
	  */
	public void setProxyPort (String ProxyPort)
	{
		set_Value (COLUMNNAME_ProxyPort, ProxyPort);
	}

	/** Get Proxy port.
		@return Port of your proxy server
	  */
	public String getProxyPort () 
	{
		return (String)get_Value(COLUMNNAME_ProxyPort);
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		if (URL == null)
			throw new IllegalArgumentException ("URL is mandatory.");
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}

	/** Set User.
		@param Usuario 
		User
	  */
	public void setUsuario (String Usuario)
	{
		if (Usuario == null)
			throw new IllegalArgumentException ("Usuario is mandatory.");
		set_Value (COLUMNNAME_Usuario, Usuario);
	}

	/** Get User.
		@return User
	  */
	public String getUsuario () 
	{
		return (String)get_Value(COLUMNNAME_Usuario);
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
}