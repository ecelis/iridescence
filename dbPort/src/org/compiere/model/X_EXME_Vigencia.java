/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Vigencia
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Vigencia extends PO implements I_EXME_Vigencia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Vigencia (Properties ctx, int EXME_Vigencia_ID, String trxName)
    {
      super (ctx, EXME_Vigencia_ID, trxName);
      /** if (EXME_Vigencia_ID == 0)
        {
			setEXME_Vigencia_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Vigencia (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Vigencia[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Years of Vigency.
		@param Anios_Vigencia 
		Years of Vigency
	  */
	public void setAnios_Vigencia (int Anios_Vigencia)
	{
		set_Value (COLUMNNAME_Anios_Vigencia, Integer.valueOf(Anios_Vigencia));
	}

	/** Get Years of Vigency.
		@return Years of Vigency
	  */
	public int getAnios_Vigencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Anios_Vigencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validity.
		@param EXME_Vigencia_ID 
		Duration in Years for the Study of a Patient
	  */
	public void setEXME_Vigencia_ID (int EXME_Vigencia_ID)
	{
		if (EXME_Vigencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vigencia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Vigencia_ID, Integer.valueOf(EXME_Vigencia_ID));
	}

	/** Get Validity.
		@return Duration in Years for the Study of a Patient
	  */
	public int getEXME_Vigencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vigencia_ID);
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