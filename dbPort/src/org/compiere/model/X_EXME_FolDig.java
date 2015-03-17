/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_FolDig
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FolDig extends PO implements I_EXME_FolDig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FolDig (Properties ctx, int EXME_FolDig_ID, String trxName)
    {
      super (ctx, EXME_FolDig_ID, trxName);
      /** if (EXME_FolDig_ID == 0)
        {
			setEXME_FolDig_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FolDig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FolDig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assign Client_ID.
		@param AssignClient_ID 
		AssignClientID
	  */
	public void setAssignClient_ID (int AssignClient_ID)
	{
		if (AssignClient_ID < 1) 
			set_Value (COLUMNNAME_AssignClient_ID, null);
		else 
			set_Value (COLUMNNAME_AssignClient_ID, Integer.valueOf(AssignClient_ID));
	}

	/** Get Assign Client_ID.
		@return AssignClientID
	  */
	public int getAssignClient_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AssignClient_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Assigned.
		@param Assigned Assigned	  */
	public void setAssigned (int Assigned)
	{
		set_Value (COLUMNNAME_Assigned, Integer.valueOf(Assigned));
	}

	/** Get Assigned.
		@return Assigned	  */
	public int getAssigned () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Assigned);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Available.
		@param Available 
		Available
	  */
	public void setAvailable (int Available)
	{
		set_Value (COLUMNNAME_Available, Integer.valueOf(Available));
	}

	/** Get Available.
		@return Available
	  */
	public int getAvailable () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Available);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Assign.
		@param DateAssign 
		DateAssign
	  */
	public void setDateAssign (Timestamp DateAssign)
	{
		set_Value (COLUMNNAME_DateAssign, DateAssign);
	}

	/** Get Date Assign.
		@return DateAssign
	  */
	public Timestamp getDateAssign () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAssign);
	}

	/** Set Date Sale.
		@param DateSale 
		Date Sale
	  */
	public void setDateSale (Timestamp DateSale)
	{
		set_Value (COLUMNNAME_DateSale, DateSale);
	}

	/** Get Date Sale.
		@return Date Sale
	  */
	public Timestamp getDateSale () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateSale);
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

	/** Set Folios Assigned ID.
		@param EXME_FolDig_ID 
		Folios Assigned ID
	  */
	public void setEXME_FolDig_ID (int EXME_FolDig_ID)
	{
		if (EXME_FolDig_ID < 1)
			 throw new IllegalArgumentException ("EXME_FolDig_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FolDig_ID, Integer.valueOf(EXME_FolDig_ID));
	}

	/** Get Folios Assigned ID.
		@return Folios Assigned ID
	  */
	public int getEXME_FolDig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FolDig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Consumption.
		@param isConsumo Consumption	  */
	public void setisConsumo (boolean isConsumo)
	{
		set_Value (COLUMNNAME_isConsumo, Boolean.valueOf(isConsumo));
	}

	/** Get Consumption.
		@return Consumption	  */
	public boolean isConsumo () 
	{
		Object oo = get_Value(COLUMNNAME_isConsumo);
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