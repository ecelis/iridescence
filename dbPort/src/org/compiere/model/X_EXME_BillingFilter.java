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

/** Generated Model for EXME_BillingFilter
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_BillingFilter extends PO implements I_EXME_BillingFilter, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BillingFilter (Properties ctx, int EXME_BillingFilter_ID, String trxName)
    {
      super (ctx, EXME_BillingFilter_ID, trxName);
      /** if (EXME_BillingFilter_ID == 0)
        {
			setAD_Column_ID (0);
			setAD_Table_ID (0);
			setEXME_BillingFilter_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_BillingFilter (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BillingFilter[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Column.Table_Name);
        I_AD_Column result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Column)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Column_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1)
			 throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Table Engine.
		@param AD_TableEngine_ID 
		Table where the engine search the records
	  */
	public void setAD_TableEngine_ID (int AD_TableEngine_ID)
	{
		if (AD_TableEngine_ID < 1) 
			set_Value (COLUMNNAME_AD_TableEngine_ID, null);
		else 
			set_Value (COLUMNNAME_AD_TableEngine_ID, Integer.valueOf(AD_TableEngine_ID));
	}

	/** Get Table Engine.
		@return Table where the engine search the records
	  */
	public int getAD_TableEngine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_TableEngine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
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

	/** Set Display Column.
		@param DisplayColumn_ID Display Column	  */
	public void setDisplayColumn_ID (int DisplayColumn_ID)
	{
		if (DisplayColumn_ID < 1) 
			set_Value (COLUMNNAME_DisplayColumn_ID, null);
		else 
			set_Value (COLUMNNAME_DisplayColumn_ID, Integer.valueOf(DisplayColumn_ID));
	}

	/** Get Display Column.
		@return Display Column	  */
	public int getDisplayColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DisplayColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Billing Rule Filter .
		@param EXME_BillingFilter_ID Billing Rule Filter 	  */
	public void setEXME_BillingFilter_ID (int EXME_BillingFilter_ID)
	{
		if (EXME_BillingFilter_ID < 1)
			 throw new IllegalArgumentException ("EXME_BillingFilter_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_BillingFilter_ID, Integer.valueOf(EXME_BillingFilter_ID));
	}

	/** Get Billing Rule Filter .
		@return Billing Rule Filter 	  */
	public int getEXME_BillingFilter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BillingFilter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FilterType AD_Reference_ID=1200625 */
	public static final int FILTERTYPE_AD_Reference_ID=1200625;
	/** R = R */
	public static final String FILTERTYPE_R = "R";
	/** U = U */
	public static final String FILTERTYPE_U = "U";
	/** Set Filter Type.
		@param FilterType 
		Filter Type for billing rules (rank or unique)
	  */
	public void setFilterType (String FilterType)
	{

		if (FilterType == null || FilterType.equals("R") || FilterType.equals("U")); else throw new IllegalArgumentException ("FilterType Invalid value - " + FilterType + " - Reference_ID=1200625 - R - U");		set_Value (COLUMNNAME_FilterType, FilterType);
	}

	/** Get Filter Type.
		@return Filter Type for billing rules (rank or unique)
	  */
	public String getFilterType () 
	{
		return (String)get_Value(COLUMNNAME_FilterType);
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