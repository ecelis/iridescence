/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_PaqBase
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PaqBase extends PO implements I_EXME_PaqBase, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PaqBase (Properties ctx, int EXME_PaqBase_ID, String trxName)
    {
      super (ctx, EXME_PaqBase_ID, trxName);
      /** if (EXME_PaqBase_ID == 0)
        {
			setEXME_PaqBase_ID (0);
			setIsMiniPack (false);
			setIsUniversalComp (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PaqBase (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PaqBase[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Base Package.
		@param EXME_PaqBase_ID 
		Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID)
	{
		if (EXME_PaqBase_ID < 1)
			 throw new IllegalArgumentException ("EXME_PaqBase_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PaqBase_ID, Integer.valueOf(EXME_PaqBase_ID));
	}

	/** Get Base Package.
		@return Base Package
	  */
	public int getEXME_PaqBase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allows mini packages.
		@param IsMiniPack 
		Allows mini packages
	  */
	public void setIsMiniPack (boolean IsMiniPack)
	{
		set_Value (COLUMNNAME_IsMiniPack, Boolean.valueOf(IsMiniPack));
	}

	/** Get Allows mini packages.
		@return Allows mini packages
	  */
	public boolean isMiniPack () 
	{
		Object oo = get_Value(COLUMNNAME_IsMiniPack);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is universal component.
		@param IsUniversalComp Is universal component	  */
	public void setIsUniversalComp (boolean IsUniversalComp)
	{
		set_Value (COLUMNNAME_IsUniversalComp, Boolean.valueOf(IsUniversalComp));
	}

	/** Get Is universal component.
		@return Is universal component	  */
	public boolean isUniversalComp () 
	{
		Object oo = get_Value(COLUMNNAME_IsUniversalComp);
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