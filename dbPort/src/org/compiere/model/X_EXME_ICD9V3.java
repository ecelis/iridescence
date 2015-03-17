/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ICD9V3
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_EXME_ICD9V3 extends PO implements I_EXME_ICD9V3, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ICD9V3 (Properties ctx, int EXME_ICD9V3_ID, String trxName)
    {
      super (ctx, EXME_ICD9V3_ID, trxName);
      /** if (EXME_ICD9V3_ID == 0)
        {
			setEXME_ICD9V3_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ICD9V3 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ICD9V3[")
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

	/** Set ICD 9 Vol. 3.
		@param EXME_ICD9V3_ID 
		ICD 9 Vol. 3
	  */
	public void setEXME_ICD9V3_ID (int EXME_ICD9V3_ID)
	{
		if (EXME_ICD9V3_ID < 1)
			 throw new IllegalArgumentException ("EXME_ICD9V3_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ICD9V3_ID, Integer.valueOf(EXME_ICD9V3_ID));
	}

	/** Get ICD 9 Vol. 3.
		@return ICD 9 Vol. 3
	  */
	public int getEXME_ICD9V3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ICD9V3_ID);
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

	/** Set Valid from.
		@param Valid_From Valid from	  */
	public void setValid_From (Timestamp Valid_From)
	{
		set_Value (COLUMNNAME_Valid_From, Valid_From);
	}

	/** Get Valid from.
		@return Valid from	  */
	public Timestamp getValid_From () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Valid_From);
	}

	/** Set Valid to.
		@param Valid_To Valid to	  */
	public void setValid_To (Timestamp Valid_To)
	{
		set_Value (COLUMNNAME_Valid_To, Valid_To);
	}

	/** Get Valid to.
		@return Valid to	  */
	public Timestamp getValid_To () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Valid_To);
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