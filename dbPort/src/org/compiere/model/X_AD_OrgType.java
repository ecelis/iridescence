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

/** Generated Model for AD_OrgType
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_OrgType extends PO implements I_AD_OrgType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_OrgType (Properties ctx, int AD_OrgType_ID, String trxName)
    {
      super (ctx, AD_OrgType_ID, trxName);
      /** if (AD_OrgType_ID == 0)
        {
			setAD_OrgType_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_OrgType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_OrgType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Organization Type.
		@param AD_OrgType_ID 
		Organization Type allows you to categorize your organizations
	  */
	public void setAD_OrgType_ID (int AD_OrgType_ID)
	{
		if (AD_OrgType_ID < 1)
			 throw new IllegalArgumentException ("AD_OrgType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_OrgType_ID, Integer.valueOf(AD_OrgType_ID));
	}

	/** Get Organization Type.
		@return Organization Type allows you to categorize your organizations
	  */
	public int getAD_OrgType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_PrintColor getAD_PrintColor() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintColor.Table_Name);
        I_AD_PrintColor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintColor)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintColor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Print Color.
		@param AD_PrintColor_ID 
		Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID)
	{
		if (AD_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintColor_ID, Integer.valueOf(AD_PrintColor_ID));
	}

	/** Get Print Color.
		@return Color used for printing and display
	  */
	public int getAD_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}