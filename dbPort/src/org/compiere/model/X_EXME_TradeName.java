/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_TradeName
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_TradeName extends PO implements I_EXME_TradeName, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TradeName (Properties ctx, int EXME_TradeName_ID, String trxName)
    {
      super (ctx, EXME_TradeName_ID, trxName);
      /** if (EXME_TradeName_ID == 0)
        {
			setEXME_TradeName_ID (0);
			setName (null);
			setTrade_Name_ID (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TradeName (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TradeName[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trade Name.
		@param EXME_TradeName_ID 
		Trade Name
	  */
	public void setEXME_TradeName_ID (int EXME_TradeName_ID)
	{
		if (EXME_TradeName_ID < 1)
			 throw new IllegalArgumentException ("EXME_TradeName_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TradeName_ID, Integer.valueOf(EXME_TradeName_ID));
	}

	/** Get Trade Name.
		@return Trade Name
	  */
	public int getEXME_TradeName_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TradeName_ID);
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

	/** Set Trade name/key.
		@param Trade_Name_ID Trade name/key	  */
	public void setTrade_Name_ID (String Trade_Name_ID)
	{
		if (Trade_Name_ID == null)
			throw new IllegalArgumentException ("Trade_Name_ID is mandatory.");
		set_Value (COLUMNNAME_Trade_Name_ID, Trade_Name_ID);
	}

	/** Get Trade name/key.
		@return Trade name/key	  */
	public String getTrade_Name_ID () 
	{
		return (String)get_Value(COLUMNNAME_Trade_Name_ID);
	}
}