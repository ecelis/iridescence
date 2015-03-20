/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_MirthConfig
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_MirthConfig extends PO implements I_EXME_MirthConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MirthConfig (Properties ctx, int EXME_MirthConfig_ID, String trxName)
    {
      super (ctx, EXME_MirthConfig_ID, trxName);
      /** if (EXME_MirthConfig_ID == 0)
        {
			setEXME_MirthConfig_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MirthConfig (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_MirthConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Create Channel.
		@param CreateChannel Create Channel	  */
	public void setCreateChannel (String CreateChannel)
	{
		set_Value (COLUMNNAME_CreateChannel, CreateChannel);
	}

	/** Get Create Channel.
		@return Create Channel	  */
	public String getCreateChannel () 
	{
		return (String)get_Value(COLUMNNAME_CreateChannel);
	}

	/** Set Mirth Database Host.
		@param dbhost Mirth Database Host	  */
	public void setdbhost (String dbhost)
	{
		set_Value (COLUMNNAME_dbhost, dbhost);
	}

	/** Get Mirth Database Host.
		@return Mirth Database Host	  */
	public String getdbhost () 
	{
		return (String)get_Value(COLUMNNAME_dbhost);
	}

	/** Set Mirth Database Name.
		@param dbname Mirth Database Name	  */
	public void setdbname (String dbname)
	{
		set_Value (COLUMNNAME_dbname, dbname);
	}

	/** Get Mirth Database Name.
		@return Mirth Database Name	  */
	public String getdbname () 
	{
		return (String)get_Value(COLUMNNAME_dbname);
	}

	/** Set Mirth Database Port.
		@param dbport Mirth Database Port	  */
	public void setdbport (BigDecimal dbport)
	{
		set_Value (COLUMNNAME_dbport, dbport);
	}

	/** Get Mirth Database Port.
		@return Mirth Database Port	  */
	public BigDecimal getdbport () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_dbport);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Service Endpoint URI.
		@param EndpointURI 
		Service Endpoint URI
	  */
	public void setEndpointURI (String EndpointURI)
	{
		set_Value (COLUMNNAME_EndpointURI, EndpointURI);
	}

	/** Get Service Endpoint URI.
		@return Service Endpoint URI
	  */
	public String getEndpointURI () 
	{
		return (String)get_Value(COLUMNNAME_EndpointURI);
	}

	/** Set Mirth Configuration.
		@param EXME_MirthConfig_ID Mirth Configuration	  */
	public void setEXME_MirthConfig_ID (int EXME_MirthConfig_ID)
	{
		if (EXME_MirthConfig_ID < 1)
			 throw new IllegalArgumentException ("EXME_MirthConfig_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MirthConfig_ID, Integer.valueOf(EXME_MirthConfig_ID));
	}

	/** Get Mirth Configuration.
		@return Mirth Configuration	  */
	public int getEXME_MirthConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MirthConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** GetMethods AD_Reference_ID=1200432 */
	public static final int GETMETHODS_AD_Reference_ID=1200432;
	/** Get Methods = N */
	public static final String GETMETHODS_GetMethods = "N";
	/** Set Get Methods.
		@param GetMethods 
		Get Soap Methods 
	  */
	public void setGetMethods (String GetMethods)
	{

		if (GetMethods == null || GetMethods.equals("N")); else throw new IllegalArgumentException ("GetMethods Invalid value - " + GetMethods + " - Reference_ID=1200432 - N");		set_Value (COLUMNNAME_GetMethods, GetMethods);
	}

	/** Get Get Methods.
		@return Get Soap Methods 
	  */
	public String getGetMethods () 
	{
		return (String)get_Value(COLUMNNAME_GetMethods);
	}

	/** Set Method.
		@param Method 
		Soap Method
	  */
	public void setMethod (String Method)
	{
		set_Value (COLUMNNAME_Method, Method);
	}

	/** Get Method.
		@return Soap Method
	  */
	public String getMethod () 
	{
		return (String)get_Value(COLUMNNAME_Method);
	}

	/** Set Mirth Host.
		@param MirthHost Mirth Host	  */
	public void setMirthHost (String MirthHost)
	{
		set_Value (COLUMNNAME_MirthHost, MirthHost);
	}

	/** Get Mirth Host.
		@return Mirth Host	  */
	public String getMirthHost () 
	{
		return (String)get_Value(COLUMNNAME_MirthHost);
	}

	/** Set Mirth Password.
		@param MirthPassword Mirth Password	  */
	public void setMirthPassword (String MirthPassword)
	{
		set_Value (COLUMNNAME_MirthPassword, MirthPassword);
	}

	/** Get Mirth Password.
		@return Mirth Password	  */
	public String getMirthPassword () 
	{
		return (String)get_Value(COLUMNNAME_MirthPassword);
	}

	/** Set Mirth Port.
		@param MirthPort Mirth Port	  */
	public void setMirthPort (String MirthPort)
	{
		set_Value (COLUMNNAME_MirthPort, MirthPort);
	}

	/** Get Mirth Port.
		@return Mirth Port	  */
	public String getMirthPort () 
	{
		return (String)get_Value(COLUMNNAME_MirthPort);
	}

	/** Set Mirth User.
		@param MirthUser Mirth User	  */
	public void setMirthUser (String MirthUser)
	{
		set_Value (COLUMNNAME_MirthUser, MirthUser);
	}

	/** Get Mirth User.
		@return Mirth User	  */
	public String getMirthUser () 
	{
		return (String)get_Value(COLUMNNAME_MirthUser);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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

	/** Set Soap Action URI.
		@param SoapActionURI Soap Action URI	  */
	public void setSoapActionURI (String SoapActionURI)
	{
		set_Value (COLUMNNAME_SoapActionURI, SoapActionURI);
	}

	/** Get Soap Action URI.
		@return Soap Action URI	  */
	public String getSoapActionURI () 
	{
		return (String)get_Value(COLUMNNAME_SoapActionURI);
	}

	/** Set Soap Envelope.
		@param SoapEnvelope Soap Envelope	  */
	public void setSoapEnvelope (String SoapEnvelope)
	{
		set_Value (COLUMNNAME_SoapEnvelope, SoapEnvelope);
	}

	/** Get Soap Envelope.
		@return Soap Envelope	  */
	public String getSoapEnvelope () 
	{
		return (String)get_Value(COLUMNNAME_SoapEnvelope);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set WSDL Path.
		@param WSDLPath WSDL Path	  */
	public void setWSDLPath (String WSDLPath)
	{
		set_Value (COLUMNNAME_WSDLPath, WSDLPath);
	}

	/** Get WSDL Path.
		@return WSDL Path	  */
	public String getWSDLPath () 
	{
		return (String)get_Value(COLUMNNAME_WSDLPath);
	}
}