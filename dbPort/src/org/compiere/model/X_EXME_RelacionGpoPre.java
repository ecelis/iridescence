/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_RelacionGpoPre
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RelacionGpoPre extends PO implements I_EXME_RelacionGpoPre, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RelacionGpoPre (Properties ctx, int EXME_RelacionGpoPre_ID, String trxName)
    {
      super (ctx, EXME_RelacionGpoPre_ID, trxName);
      /** if (EXME_RelacionGpoPre_ID == 0)
        {
			setEXME_RelacionGpoPre_ID (0);
			setLineNo (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RelacionGpoPre (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RelacionGpoPre[")
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

	/** Set Price Group Relation.
		@param EXME_RelacionGpoPre_ID 
		Price Group Relation
	  */
	public void setEXME_RelacionGpoPre_ID (int EXME_RelacionGpoPre_ID)
	{
		if (EXME_RelacionGpoPre_ID < 1)
			 throw new IllegalArgumentException ("EXME_RelacionGpoPre_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RelacionGpoPre_ID, Integer.valueOf(EXME_RelacionGpoPre_ID));
	}

	/** Get Price Group Relation.
		@return Price Group Relation
	  */
	public int getEXME_RelacionGpoPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RelacionGpoPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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