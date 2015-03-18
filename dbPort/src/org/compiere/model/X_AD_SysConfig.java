/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_SysConfig
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_SysConfig extends PO implements I_AD_SysConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_SysConfig (Properties ctx, int AD_SysConfig_ID, String trxName)
    {
      super (ctx, AD_SysConfig_ID, trxName);
      /** if (AD_SysConfig_ID == 0)
        {
			setAD_SysConfig_ID (0);
			setEntityType (null);
// U
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_SysConfig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_SysConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set System Configurator.
		@param AD_SysConfig_ID System Configurator	  */
	public void setAD_SysConfig_ID (int AD_SysConfig_ID)
	{
		if (AD_SysConfig_ID < 1)
			 throw new IllegalArgumentException ("AD_SysConfig_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_SysConfig_ID, Integer.valueOf(AD_SysConfig_ID));
	}

	/** Get System Configurator.
		@return System Configurator	  */
	public int getAD_SysConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_SysConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ConfigurationLevel AD_Reference_ID=1200257 */
	public static final int CONFIGURATIONLEVEL_AD_Reference_ID=1200257;
	/** System = S */
	public static final String CONFIGURATIONLEVEL_System = "S";
	/** Client = C */
	public static final String CONFIGURATIONLEVEL_Client = "C";
	/** Organization = O */
	public static final String CONFIGURATIONLEVEL_Organization = "O";
	/** Set Configuration Level.
		@param ConfigurationLevel 
		Configuration Level for this parameter
	  */
	public void setConfigurationLevel (String ConfigurationLevel)
	{

		if (ConfigurationLevel == null || ConfigurationLevel.equals("S") || ConfigurationLevel.equals("C") || ConfigurationLevel.equals("O")); else throw new IllegalArgumentException ("ConfigurationLevel Invalid value - " + ConfigurationLevel + " - Reference_ID=1200257 - S - C - O");		set_Value (COLUMNNAME_ConfigurationLevel, ConfigurationLevel);
	}

	/** Get Configuration Level.
		@return Configuration Level for this parameter
	  */
	public String getConfigurationLevel () 
	{
		return (String)get_Value(COLUMNNAME_ConfigurationLevel);
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

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{
		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
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