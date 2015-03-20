/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ConfigEncoder
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigEncoder extends PO implements I_EXME_ConfigEncoder, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigEncoder (Properties ctx, int EXME_ConfigEncoder_ID, String trxName)
    {
      super (ctx, EXME_ConfigEncoder_ID, trxName);
      /** if (EXME_ConfigEncoder_ID == 0)
        {
			setClientID (null);
			setClientSecret (null);
			setEXME_ConfigEncoder_ID (0);
			setGrantType (null);
			setHost (null);
			setName (null);
			setPort (Env.ZERO);
			setScheme (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigEncoder (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigEncoder[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ID Client.
		@param ClientID 
		ID Client
	  */
	public void setClientID (String ClientID)
	{
		if (ClientID == null)
			throw new IllegalArgumentException ("ClientID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_ClientID, ClientID);
	}

	/** Get ID Client.
		@return ID Client
	  */
	public String getClientID () 
	{
		return (String)get_Value(COLUMNNAME_ClientID);
	}

	/** Set Secret key.
		@param ClientSecret 
		Secret key
	  */
	public void setClientSecret (String ClientSecret)
	{
		if (ClientSecret == null)
			throw new IllegalArgumentException ("ClientSecret is mandatory.");
		set_ValueNoCheck (COLUMNNAME_ClientSecret, ClientSecret);
	}

	/** Get Secret key.
		@return Secret key
	  */
	public String getClientSecret () 
	{
		return (String)get_Value(COLUMNNAME_ClientSecret);
	}

	/** Set Encoder configuration.
		@param EXME_ConfigEncoder_ID 
		Encoder configuration
	  */
	public void setEXME_ConfigEncoder_ID (int EXME_ConfigEncoder_ID)
	{
		if (EXME_ConfigEncoder_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEncoder_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigEncoder_ID, Integer.valueOf(EXME_ConfigEncoder_ID));
	}

	/** Get Encoder configuration.
		@return Encoder configuration
	  */
	public int getEXME_ConfigEncoder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEncoder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Expires.
		@param Expires 
		Expires
	  */
	public void setExpires (BigDecimal Expires)
	{
		set_Value (COLUMNNAME_Expires, Expires);
	}

	/** Get Expires.
		@return Expires
	  */
	public BigDecimal getExpires () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Expires);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Grant type.
		@param GrantType 
		Grant type
	  */
	public void setGrantType (String GrantType)
	{
		if (GrantType == null)
			throw new IllegalArgumentException ("GrantType is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GrantType, GrantType);
	}

	/** Get Grant type.
		@return Grant type
	  */
	public String getGrantType () 
	{
		return (String)get_Value(COLUMNNAME_GrantType);
	}

	/** Set Host.
		@param Host Host	  */
	public void setHost (String Host)
	{
		if (Host == null)
			throw new IllegalArgumentException ("Host is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Host, Host);
	}

	/** Get Host.
		@return Host	  */
	public String getHost () 
	{
		return (String)get_Value(COLUMNNAME_Host);
	}

	/** Set Last update.
		@param LastUpdate 
		Last update
	  */
	public void setLastUpdate (Timestamp LastUpdate)
	{
		set_ValueNoCheck (COLUMNNAME_LastUpdate, LastUpdate);
	}

	/** Get Last update.
		@return Last update
	  */
	public Timestamp getLastUpdate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastUpdate);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Name, Name);
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

	/** Set Port.
		@param Port Port	  */
	public void setPort (BigDecimal Port)
	{
		if (Port == null)
			throw new IllegalArgumentException ("Port is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Port, Port);
	}

	/** Get Port.
		@return Port	  */
	public BigDecimal getPort () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Port);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Scheme.
		@param Scheme 
		Scheme
	  */
	public void setScheme (String Scheme)
	{
		if (Scheme == null)
			throw new IllegalArgumentException ("Scheme is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Scheme, Scheme);
	}

	/** Get Scheme.
		@return Scheme
	  */
	public String getScheme () 
	{
		return (String)get_Value(COLUMNNAME_Scheme);
	}

	/** Set Timeout (MS).
		@param TimeoutMS 
		Timeout (MS)
	  */
	public void setTimeoutMS (int TimeoutMS)
	{
		set_ValueNoCheck (COLUMNNAME_TimeoutMS, Integer.valueOf(TimeoutMS));
	}

	/** Get Timeout (MS).
		@return Timeout (MS)
	  */
	public int getTimeoutMS () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TimeoutMS);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Token.
		@param Token 
		Token
	  */
	public void setToken (String Token)
	{
		set_ValueNoCheck (COLUMNNAME_Token, Token);
	}

	/** Get Token.
		@return Token
	  */
	public String getToken () 
	{
		return (String)get_Value(COLUMNNAME_Token);
	}

	/** Set TokenType.
		@param TokenType 
		Wiki Token Type
	  */
	public void setTokenType (String TokenType)
	{
		set_Value (COLUMNNAME_TokenType, TokenType);
	}

	/** Get TokenType.
		@return Wiki Token Type
	  */
	public String getTokenType () 
	{
		return (String)get_Value(COLUMNNAME_TokenType);
	}

	/** Set Url detail.
		@param UrlDetail 
		Url detail
	  */
	public void setUrlDetail (String UrlDetail)
	{
		set_ValueNoCheck (COLUMNNAME_UrlDetail, UrlDetail);
	}

	/** Get Url detail.
		@return Url detail
	  */
	public String getUrlDetail () 
	{
		return (String)get_Value(COLUMNNAME_UrlDetail);
	}

	/** Set Url search.
		@param UrlSearch 
		Url search
	  */
	public void setUrlSearch (String UrlSearch)
	{
		set_ValueNoCheck (COLUMNNAME_UrlSearch, UrlSearch);
	}

	/** Get Url search.
		@return Url search
	  */
	public String getUrlSearch () 
	{
		return (String)get_Value(COLUMNNAME_UrlSearch);
	}

	/** Set Url Server 1.
		@param UrlServer1 
		Url Server 1
	  */
	public void setUrlServer1 (String UrlServer1)
	{
		set_ValueNoCheck (COLUMNNAME_UrlServer1, UrlServer1);
	}

	/** Get Url Server 1.
		@return Url Server 1
	  */
	public String getUrlServer1 () 
	{
		return (String)get_Value(COLUMNNAME_UrlServer1);
	}

	/** Set Url Server 2.
		@param UrlServer2 
		Url Server 2
	  */
	public void setUrlServer2 (String UrlServer2)
	{
		set_ValueNoCheck (COLUMNNAME_UrlServer2, UrlServer2);
	}

	/** Get Url Server 2.
		@return Url Server 2
	  */
	public String getUrlServer2 () 
	{
		return (String)get_Value(COLUMNNAME_UrlServer2);
	}

	/** Set Url server 3.
		@param UrlServer3 
		Url server 3
	  */
	public void setUrlServer3 (String UrlServer3)
	{
		set_ValueNoCheck (COLUMNNAME_UrlServer3, UrlServer3);
	}

	/** Get Url server 3.
		@return Url server 3
	  */
	public String getUrlServer3 () 
	{
		return (String)get_Value(COLUMNNAME_UrlServer3);
	}

	/** Set Url server 4.
		@param UrlServer4 
		Url server 4
	  */
	public void setUrlServer4 (String UrlServer4)
	{
		set_ValueNoCheck (COLUMNNAME_UrlServer4, UrlServer4);
	}

	/** Get Url server 4.
		@return Url server 4
	  */
	public String getUrlServer4 () 
	{
		return (String)get_Value(COLUMNNAME_UrlServer4);
	}
}