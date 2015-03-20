/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigINER
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigINER extends PO implements I_EXME_ConfigINER, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigINER (Properties ctx, int EXME_ConfigINER_ID, String trxName)
    {
      super (ctx, EXME_ConfigINER_ID, trxName);
      /** if (EXME_ConfigINER_ID == 0)
        {
			setEXME_ConfigINER_ID (0);
			setNotasMedicas (false);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigINER (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigINER[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Configuration INER.
		@param EXME_ConfigINER_ID Configuration INER	  */
	public void setEXME_ConfigINER_ID (int EXME_ConfigINER_ID)
	{
		if (EXME_ConfigINER_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigINER_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigINER_ID, Integer.valueOf(EXME_ConfigINER_ID));
	}

	/** Get Configuration INER.
		@return Configuration INER	  */
	public int getEXME_ConfigINER_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigINER_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Record.
		@param NotasMedicas Medical Record	  */
	public void setNotasMedicas (boolean NotasMedicas)
	{
		set_ValueNoCheck (COLUMNNAME_NotasMedicas, Boolean.valueOf(NotasMedicas));
	}

	/** Get Medical Record.
		@return Medical Record	  */
	public boolean isNotasMedicas () 
	{
		Object oo = get_Value(COLUMNNAME_NotasMedicas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}