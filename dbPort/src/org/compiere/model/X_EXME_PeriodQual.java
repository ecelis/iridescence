/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PeriodQual
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PeriodQual extends PO implements I_EXME_PeriodQual, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PeriodQual (Properties ctx, int EXME_PeriodQual_ID, String trxName)
    {
      super (ctx, EXME_PeriodQual_ID, trxName);
      /** if (EXME_PeriodQual_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_PeriodQual (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PeriodQual[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Period Qualifier.
		@param EXME_PeriodQual_ID Period Qualifier	  */
	public void setEXME_PeriodQual_ID (int EXME_PeriodQual_ID)
	{
		if (EXME_PeriodQual_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_PeriodQual_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_PeriodQual_ID, Integer.valueOf(EXME_PeriodQual_ID));
	}

	/** Get Period Qualifier.
		@return Period Qualifier	  */
	public int getEXME_PeriodQual_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PeriodQual_ID);
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