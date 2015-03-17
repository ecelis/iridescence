/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Reporte
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Reporte extends PO implements I_EXME_Reporte, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Reporte (Properties ctx, int EXME_Reporte_ID, String trxName)
    {
      super (ctx, EXME_Reporte_ID, trxName);
      /** if (EXME_Reporte_ID == 0)
        {
			setDescription (null);
			setEXME_Reporte_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Reporte (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Reporte[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Report.
		@param EXME_Reporte_ID Report	  */
	public void setEXME_Reporte_ID (int EXME_Reporte_ID)
	{
		if (EXME_Reporte_ID < 1)
			 throw new IllegalArgumentException ("EXME_Reporte_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Reporte_ID, Integer.valueOf(EXME_Reporte_ID));
	}

	/** Get Report.
		@return Report	  */
	public int getEXME_Reporte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reporte_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}