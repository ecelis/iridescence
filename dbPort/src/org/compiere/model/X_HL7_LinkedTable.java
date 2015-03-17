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

/** Generated Model for HL7_LinkedTable
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_LinkedTable extends PO implements I_HL7_LinkedTable, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_LinkedTable (Properties ctx, int HL7_LinkedTable_ID, String trxName)
    {
      super (ctx, HL7_LinkedTable_ID, trxName);
      /** if (HL7_LinkedTable_ID == 0)
        {
			setHL7_LinkedTable_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_LinkedTable (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_LinkedTable[")
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
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
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

	/** Set Linked Column.
		@param AD_Column_Rel_ID Linked Column	  */
	public void setAD_Column_Rel_ID (int AD_Column_Rel_ID)
	{
		if (AD_Column_Rel_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_Rel_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_Rel_ID, Integer.valueOf(AD_Column_Rel_ID));
	}

	/** Get Linked Column.
		@return Linked Column	  */
	public int getAD_Column_Rel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_Rel_ID);
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
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
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

	/** Set Linked Table.
		@param AD_Table_Rel_ID Linked Table	  */
	public void setAD_Table_Rel_ID (int AD_Table_Rel_ID)
	{
		if (AD_Table_Rel_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_Rel_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_Rel_ID, Integer.valueOf(AD_Table_Rel_ID));
	}

	/** Get Linked Table.
		@return Linked Table	  */
	public int getAD_Table_Rel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_Rel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Linked Tables HL7.
		@param HL7_LinkedTable_ID Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID)
	{
		if (HL7_LinkedTable_ID < 1)
			 throw new IllegalArgumentException ("HL7_LinkedTable_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_LinkedTable_ID, Integer.valueOf(HL7_LinkedTable_ID));
	}

	/** Get Linked Tables HL7.
		@return Linked Tables HL7	  */
	public int getHL7_LinkedTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_LinkedTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Linked Table Origin.
		@param HL7_LinkedTable2_ID Linked Table Origin	  */
	public void setHL7_LinkedTable2_ID (int HL7_LinkedTable2_ID)
	{
		if (HL7_LinkedTable2_ID < 1) 
			set_Value (COLUMNNAME_HL7_LinkedTable2_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_LinkedTable2_ID, Integer.valueOf(HL7_LinkedTable2_ID));
	}

	/** Get Linked Table Origin.
		@return Linked Table Origin	  */
	public int getHL7_LinkedTable2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_LinkedTable2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHL7_LinkedTable2_ID()));
    }

	/** Set Is Inner Join.
		@param IsInner Is Inner Join	  */
	public void setIsInner (boolean IsInner)
	{
		set_Value (COLUMNNAME_IsInner, Boolean.valueOf(IsInner));
	}

	/** Get Is Inner Join.
		@return Is Inner Join	  */
	public boolean isInner () 
	{
		Object oo = get_Value(COLUMNNAME_IsInner);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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