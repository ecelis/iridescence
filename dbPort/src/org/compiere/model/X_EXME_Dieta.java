/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Dieta
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Dieta extends PO implements I_EXME_Dieta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Dieta (Properties ctx, int EXME_Dieta_ID, String trxName)
    {
      super (ctx, EXME_Dieta_ID, trxName);
      /** if (EXME_Dieta_ID == 0)
        {
			setEXME_Dieta_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Dieta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Dieta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diet.
		@param EXME_Dieta_ID Diet	  */
	public void setEXME_Dieta_ID (int EXME_Dieta_ID)
	{
		if (EXME_Dieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Dieta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Dieta_ID, Integer.valueOf(EXME_Dieta_ID));
	}

	/** Get Diet.
		@return Diet	  */
	public int getEXME_Dieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sub diet.
		@param IsSubDiet Sub diet	  */
	public void setIsSubDiet (boolean IsSubDiet)
	{
		set_Value (COLUMNNAME_IsSubDiet, Boolean.valueOf(IsSubDiet));
	}

	/** Get Sub diet.
		@return Sub diet	  */
	public boolean isSubDiet () 
	{
		Object oo = get_Value(COLUMNNAME_IsSubDiet);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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