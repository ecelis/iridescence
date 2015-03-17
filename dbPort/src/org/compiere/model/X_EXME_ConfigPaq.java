/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigPaq
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigPaq extends PO implements I_EXME_ConfigPaq, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigPaq (Properties ctx, int EXME_ConfigPaq_ID, String trxName)
    {
      super (ctx, EXME_ConfigPaq_ID, trxName);
      /** if (EXME_ConfigPaq_ID == 0)
        {
			setActualizaPrecios (false);
			setAutoVersion (false);
			setEditPrecios (false);
			setEXME_ConfigPaq_ID (0);
			setName (null);
			setPorcDesc (0);
			setUsaSustituto (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigPaq (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigPaq[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Update Prices.
		@param ActualizaPrecios 
		Update Prices from the Price List
	  */
	public void setActualizaPrecios (boolean ActualizaPrecios)
	{
		set_Value (COLUMNNAME_ActualizaPrecios, Boolean.valueOf(ActualizaPrecios));
	}

	/** Get Update Prices.
		@return Update Prices from the Price List
	  */
	public boolean isActualizaPrecios () 
	{
		Object oo = get_Value(COLUMNNAME_ActualizaPrecios);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set AutoVersion.
		@param AutoVersion 
		Create a new version of each package when creating a new version of price list
	  */
	public void setAutoVersion (boolean AutoVersion)
	{
		set_Value (COLUMNNAME_AutoVersion, Boolean.valueOf(AutoVersion));
	}

	/** Get AutoVersion.
		@return Create a new version of each package when creating a new version of price list
	  */
	public boolean isAutoVersion () 
	{
		Object oo = get_Value(COLUMNNAME_AutoVersion);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set EditPrices.
		@param EditPrecios 
		Indicates if permits to edit prices of packages
	  */
	public void setEditPrecios (boolean EditPrecios)
	{
		set_Value (COLUMNNAME_EditPrecios, Boolean.valueOf(EditPrecios));
	}

	/** Get EditPrices.
		@return Indicates if permits to edit prices of packages
	  */
	public boolean isEditPrecios () 
	{
		Object oo = get_Value(COLUMNNAME_EditPrecios);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Package Configuration.
		@param EXME_ConfigPaq_ID 
		Package Configuration
	  */
	public void setEXME_ConfigPaq_ID (int EXME_ConfigPaq_ID)
	{
		if (EXME_ConfigPaq_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigPaq_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigPaq_ID, Integer.valueOf(EXME_ConfigPaq_ID));
	}

	/** Get Package Configuration.
		@return Package Configuration
	  */
	public int getEXME_ConfigPaq_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigPaq_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set PercDisc.
		@param PorcDesc 
		Percentage of Discount that automatically applies when creating a new version of some package
	  */
	public void setPorcDesc (int PorcDesc)
	{
		set_Value (COLUMNNAME_PorcDesc, Integer.valueOf(PorcDesc));
	}

	/** Get PercDisc.
		@return Percentage of Discount that automatically applies when creating a new version of some package
	  */
	public int getPorcDesc () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PorcDesc);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Use Substitute.
		@param UsaSustituto 
		Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public void setUsaSustituto (boolean UsaSustituto)
	{
		set_Value (COLUMNNAME_UsaSustituto, Boolean.valueOf(UsaSustituto));
	}

	/** Get Use Substitute.
		@return Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public boolean isUsaSustituto () 
	{
		Object oo = get_Value(COLUMNNAME_UsaSustituto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}