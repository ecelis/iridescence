/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PayerClass
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PayerClass extends PO implements I_EXME_PayerClass, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PayerClass (Properties ctx, int EXME_PayerClass_ID, String trxName)
    {
      super (ctx, EXME_PayerClass_ID, trxName);
      /** if (EXME_PayerClass_ID == 0)
        {
			setEXME_PayerClass_ID (0);
			setName (null);
			setValue (null);
			setisMiscellaneous (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_PayerClass (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PayerClass[")
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

	public I_EXME_FinancialClass getEXME_FinancialClass() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FinancialClass.Table_Name);
        I_EXME_FinancialClass result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FinancialClass)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FinancialClass_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Financial Class Id.
		@param EXME_FinancialClass_ID Financial Class Id	  */
	public void setEXME_FinancialClass_ID (int EXME_FinancialClass_ID)
	{
		if (EXME_FinancialClass_ID < 1) 
			set_Value (COLUMNNAME_EXME_FinancialClass_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FinancialClass_ID, Integer.valueOf(EXME_FinancialClass_ID));
	}

	/** Get Financial Class Id.
		@return Financial Class Id	  */
	public int getEXME_FinancialClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FinancialClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payer Class Id.
		@param EXME_PayerClass_ID Payer Class Id	  */
	public void setEXME_PayerClass_ID (int EXME_PayerClass_ID)
	{
		if (EXME_PayerClass_ID < 1)
			 throw new IllegalArgumentException ("EXME_PayerClass_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PayerClass_ID, Integer.valueOf(EXME_PayerClass_ID));
	}

	/** Get Payer Class Id.
		@return Payer Class Id	  */
	public int getEXME_PayerClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PayerClass_ID);
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

	/** Set Is Miscellaneous.
		@param isMiscellaneous Is Miscellaneous	  */
	public void setisMiscellaneous (boolean isMiscellaneous)
	{
		set_Value (COLUMNNAME_isMiscellaneous, Boolean.valueOf(isMiscellaneous));
	}

	/** Get Is Miscellaneous.
		@return Is Miscellaneous	  */
	public boolean isMiscellaneous () 
	{
		Object oo = get_Value(COLUMNNAME_isMiscellaneous);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}