/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ParametrosWebH
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ParametrosWebH extends PO implements I_EXME_ParametrosWebH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ParametrosWebH (Properties ctx, int EXME_ParametrosWebH_ID, String trxName)
    {
      super (ctx, EXME_ParametrosWebH_ID, trxName);
      /** if (EXME_ParametrosWebH_ID == 0)
        {
			setEXME_ParametrosWebH_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ParametrosWebH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ParametrosWebH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Access Link Configuration Parameter.
		@param EXME_ParametrosWebH_ID 
		Access Link Configuration Parameter
	  */
	public void setEXME_ParametrosWebH_ID (int EXME_ParametrosWebH_ID)
	{
		if (EXME_ParametrosWebH_ID < 1)
			 throw new IllegalArgumentException ("EXME_ParametrosWebH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ParametrosWebH_ID, Integer.valueOf(EXME_ParametrosWebH_ID));
	}

	/** Get Access Link Configuration Parameter.
		@return Access Link Configuration Parameter
	  */
	public int getEXME_ParametrosWebH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParametrosWebH_ID);
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

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
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
}