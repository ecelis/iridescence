/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_TablasPaciente
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TablasPaciente extends PO implements I_EXME_TablasPaciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TablasPaciente (Properties ctx, int EXME_TablasPaciente_ID, String trxName)
    {
      super (ctx, EXME_TablasPaciente_ID, trxName);
      /** if (EXME_TablasPaciente_ID == 0)
        {
			setAD_Table_ID (0);
			setEXME_TablasPaciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_TablasPaciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TablasPaciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Tables.
		@param EXME_TablasPaciente_ID Patient Tables	  */
	public void setEXME_TablasPaciente_ID (int EXME_TablasPaciente_ID)
	{
		if (EXME_TablasPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TablasPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TablasPaciente_ID, Integer.valueOf(EXME_TablasPaciente_ID));
	}

	/** Get Patient Tables.
		@return Patient Tables	  */
	public int getEXME_TablasPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TablasPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}