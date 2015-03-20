/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigPedAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigPedAut extends PO implements I_EXME_ConfigPedAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigPedAut (Properties ctx, int EXME_ConfigPedAut_ID, String trxName)
    {
      super (ctx, EXME_ConfigPedAut_ID, trxName);
      /** if (EXME_ConfigPedAut_ID == 0)
        {
			setEXME_ConfigPedAut_ID (0);
			setEXME_TipoProd_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigPedAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigPedAut[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Automatic Product Request Configuration.
		@param EXME_ConfigPedAut_ID 
		Automatic Product Request Configuration
	  */
	public void setEXME_ConfigPedAut_ID (int EXME_ConfigPedAut_ID)
	{
		if (EXME_ConfigPedAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigPedAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigPedAut_ID, Integer.valueOf(EXME_ConfigPedAut_ID));
	}

	/** Get Automatic Product Request Configuration.
		@return Automatic Product Request Configuration
	  */
	public int getEXME_ConfigPedAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigPedAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Subtype.
		@param EXME_TipoProd_ID 
		Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID)
	{
		if (EXME_TipoProd_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProd_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProd_ID, Integer.valueOf(EXME_TipoProd_ID));
	}

	/** Get Product Subtype.
		@return Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}