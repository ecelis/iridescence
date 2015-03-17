/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Route
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Route extends PO implements I_EXME_Route, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Route (Properties ctx, int EXME_Route_ID, String trxName)
    {
      super (ctx, EXME_Route_ID, trxName);
      /** if (EXME_Route_ID == 0)
        {
			setEXME_Route_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Route (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Route[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Abbreviation.
		@param Abrev 
		Abbreviation
	  */
	public void setAbrev (String Abrev)
	{
		set_Value (COLUMNNAME_Abrev, Abrev);
	}

	/** Get Abbreviation.
		@return Abbreviation
	  */
	public String getAbrev () 
	{
		return (String)get_Value(COLUMNNAME_Abrev);
	}

	/** Set Description 1.
		@param Description1 
		Description 1
	  */
	public void setDescription1 (String Description1)
	{
		set_Value (COLUMNNAME_Description1, Description1);
	}

	/** Get Description 1.
		@return Description 1
	  */
	public String getDescription1 () 
	{
		return (String)get_Value(COLUMNNAME_Description1);
	}

	/** Set Description 2.
		@param Description2 Description 2	  */
	public void setDescription2 (String Description2)
	{
		set_Value (COLUMNNAME_Description2, Description2);
	}

	/** Get Description 2.
		@return Description 2	  */
	public String getDescription2 () 
	{
		return (String)get_Value(COLUMNNAME_Description2);
	}

	/** Set Description 3.
		@param Description3 
		Description 3
	  */
	public void setDescription3 (String Description3)
	{
		set_Value (COLUMNNAME_Description3, Description3);
	}

	/** Get Description 3.
		@return Description 3
	  */
	public String getDescription3 () 
	{
		return (String)get_Value(COLUMNNAME_Description3);
	}

	/** Set Description 4.
		@param Description4 
		Description 4
	  */
	public void setDescription4 (String Description4)
	{
		set_Value (COLUMNNAME_Description4, Description4);
	}

	/** Get Description 4.
		@return Description 4
	  */
	public String getDescription4 () 
	{
		return (String)get_Value(COLUMNNAME_Description4);
	}

	/** Set Description 5.
		@param Description5 
		Description 5
	  */
	public void setDescription5 (String Description5)
	{
		set_Value (COLUMNNAME_Description5, Description5);
	}

	/** Get Description 5.
		@return Description 5
	  */
	public String getDescription5 () 
	{
		return (String)get_Value(COLUMNNAME_Description5);
	}

	/** Set Description 6.
		@param Description6 
		Description 6
	  */
	public void setDescription6 (String Description6)
	{
		set_Value (COLUMNNAME_Description6, Description6);
	}

	/** Get Description 6.
		@return Description 6
	  */
	public String getDescription6 () 
	{
		return (String)get_Value(COLUMNNAME_Description6);
	}

	/** Set Route.
		@param EXME_Route_ID 
		Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID)
	{
		if (EXME_Route_ID < 1)
			 throw new IllegalArgumentException ("EXME_Route_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Route_ID, Integer.valueOf(EXME_Route_ID));
	}

	/** Get Route.
		@return Route
	  */
	public int getEXME_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Route_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RtID.
		@param RtID RtID	  */
	public void setRtID (int RtID)
	{
		set_Value (COLUMNNAME_RtID, Integer.valueOf(RtID));
	}

	/** Get RtID.
		@return RtID	  */
	public int getRtID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RtID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}