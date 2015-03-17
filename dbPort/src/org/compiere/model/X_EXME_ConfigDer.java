/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigDer
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigDer extends PO implements I_EXME_ConfigDer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigDer (Properties ctx, int EXME_ConfigDer_ID, String trxName)
    {
      super (ctx, EXME_ConfigDer_ID, trxName);
      /** if (EXME_ConfigDer_ID == 0)
        {
			setDerechohabiente (false);
			setEXME_ConfigDer_ID (0);
			setName (null);
			setPermiteAltaD (false);
			setPermiteAltaT (false);
			setPermiteRefer (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigDer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigDer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Right Holder.
		@param Derechohabiente 
		Right Holder
	  */
	public void setDerechohabiente (boolean Derechohabiente)
	{
		set_Value (COLUMNNAME_Derechohabiente, Boolean.valueOf(Derechohabiente));
	}

	/** Get Right Holder.
		@return Right Holder
	  */
	public boolean isDerechohabiente () 
	{
		Object oo = get_Value(COLUMNNAME_Derechohabiente);
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

	/** Set Key Configuration.
		@param EXME_ConfigDer_ID 
		Key Configuration
	  */
	public void setEXME_ConfigDer_ID (int EXME_ConfigDer_ID)
	{
		if (EXME_ConfigDer_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDer_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigDer_ID, Integer.valueOf(EXME_ConfigDer_ID));
	}

	/** Get Key Configuration.
		@return Key Configuration
	  */
	public int getEXME_ConfigDer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDer_ID);
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

	/** Set Posting of Economic Dependants.
		@param PermiteAltaD 
		Permit posting of economic dependants
	  */
	public void setPermiteAltaD (boolean PermiteAltaD)
	{
		set_Value (COLUMNNAME_PermiteAltaD, Boolean.valueOf(PermiteAltaD));
	}

	/** Get Posting of Economic Dependants.
		@return Permit posting of economic dependants
	  */
	public boolean isPermiteAltaD () 
	{
		Object oo = get_Value(COLUMNNAME_PermiteAltaD);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Posting of Title Holders.
		@param PermiteAltaT 
		Permir Posting of Title Holders?
	  */
	public void setPermiteAltaT (boolean PermiteAltaT)
	{
		set_Value (COLUMNNAME_PermiteAltaT, Boolean.valueOf(PermiteAltaT));
	}

	/** Get Posting of Title Holders.
		@return Permir Posting of Title Holders?
	  */
	public boolean isPermiteAltaT () 
	{
		Object oo = get_Value(COLUMNNAME_PermiteAltaT);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set References/Counter references.
		@param PermiteRefer 
		Permit to make references/Counter references
	  */
	public void setPermiteRefer (boolean PermiteRefer)
	{
		set_Value (COLUMNNAME_PermiteRefer, Boolean.valueOf(PermiteRefer));
	}

	/** Get References/Counter references.
		@return Permit to make references/Counter references
	  */
	public boolean isPermiteRefer () 
	{
		Object oo = get_Value(COLUMNNAME_PermiteRefer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}