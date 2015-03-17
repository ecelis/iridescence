/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProductStrength
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProductStrength extends PO implements I_EXME_ProductStrength, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductStrength (Properties ctx, int EXME_ProductStrength_ID, String trxName)
    {
      super (ctx, EXME_ProductStrength_ID, trxName);
      /** if (EXME_ProductStrength_ID == 0)
        {
			setEXME_ProductStrength_ID (0);
			setSTRENGTH_DESCRIPTION (null);
			setSTRENGTH_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductStrength (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductStrength[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Product Strength ID.
		@param EXME_ProductStrength_ID Product Strength ID	  */
	public void setEXME_ProductStrength_ID (int EXME_ProductStrength_ID)
	{
		if (EXME_ProductStrength_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductStrength_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductStrength_ID, Integer.valueOf(EXME_ProductStrength_ID));
	}

	/** Get Product Strength ID.
		@return Product Strength ID	  */
	public int getEXME_ProductStrength_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductStrength_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set STRENGTH_DESCRIPTION.
		@param STRENGTH_DESCRIPTION STRENGTH_DESCRIPTION	  */
	public void setSTRENGTH_DESCRIPTION (String STRENGTH_DESCRIPTION)
	{
		if (STRENGTH_DESCRIPTION == null)
			throw new IllegalArgumentException ("STRENGTH_DESCRIPTION is mandatory.");
		set_Value (COLUMNNAME_STRENGTH_DESCRIPTION, STRENGTH_DESCRIPTION);
	}

	/** Get STRENGTH_DESCRIPTION.
		@return STRENGTH_DESCRIPTION	  */
	public String getSTRENGTH_DESCRIPTION () 
	{
		return (String)get_Value(COLUMNNAME_STRENGTH_DESCRIPTION);
	}

	/** Set STRENGTH_ID.
		@param STRENGTH_ID STRENGTH_ID	  */
	public void setSTRENGTH_ID (int STRENGTH_ID)
	{
		if (STRENGTH_ID < 1)
			 throw new IllegalArgumentException ("STRENGTH_ID is mandatory.");
		set_Value (COLUMNNAME_STRENGTH_ID, Integer.valueOf(STRENGTH_ID));
	}

	/** Get STRENGTH_ID.
		@return STRENGTH_ID	  */
	public int getSTRENGTH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_STRENGTH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}