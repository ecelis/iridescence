/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for PHR_RssAdmin
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_RssAdmin extends PO implements I_PHR_RssAdmin, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_RssAdmin (Properties ctx, int PHR_RssAdmin_ID, String trxName)
    {
      super (ctx, PHR_RssAdmin_ID, trxName);
      /** if (PHR_RssAdmin_ID == 0)
        {
			setPHR_RssAdmin_ID (0);
			setRss (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_RssAdmin (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_RssAdmin[")
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

	/** Set Primary key.
		@param PHR_RssAdmin_ID 
		Primary key
	  */
	public void setPHR_RssAdmin_ID (int PHR_RssAdmin_ID)
	{
		if (PHR_RssAdmin_ID < 1)
			 throw new IllegalArgumentException ("PHR_RssAdmin_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_RssAdmin_ID, Integer.valueOf(PHR_RssAdmin_ID));
	}

	/** Get Primary key.
		@return Primary key
	  */
	public int getPHR_RssAdmin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_RssAdmin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Articles Address.
		@param Rss 
		Articles Address
	  */
	public void setRss (String Rss)
	{
		if (Rss == null)
			throw new IllegalArgumentException ("Rss is mandatory.");
		set_Value (COLUMNNAME_Rss, Rss);
	}

	/** Get Articles Address.
		@return Articles Address
	  */
	public String getRss () 
	{
		return (String)get_Value(COLUMNNAME_Rss);
	}
}