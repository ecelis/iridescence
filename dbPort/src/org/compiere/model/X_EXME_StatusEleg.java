/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_StatusEleg
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_StatusEleg extends PO implements I_EXME_StatusEleg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_StatusEleg (Properties ctx, int EXME_StatusEleg_ID, String trxName)
    {
      super (ctx, EXME_StatusEleg_ID, trxName);
      /** if (EXME_StatusEleg_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_StatusEleg (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_StatusEleg[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Elegibility Status.
		@param EXME_StatusEleg_ID Elegibility Status	  */
	public void setEXME_StatusEleg_ID (int EXME_StatusEleg_ID)
	{
		if (EXME_StatusEleg_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_StatusEleg_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_StatusEleg_ID, Integer.valueOf(EXME_StatusEleg_ID));
	}

	/** Get Elegibility Status.
		@return Elegibility Status	  */
	public int getEXME_StatusEleg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_StatusEleg_ID);
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