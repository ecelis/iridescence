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

/** Generated Model for EXME_Frequency2
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Frequency2 extends PO implements I_EXME_Frequency2, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Frequency2 (Properties ctx, int EXME_Frequency2_ID, String trxName)
    {
      super (ctx, EXME_Frequency2_ID, trxName);
      /** if (EXME_Frequency2_ID == 0)
        {
			setEXME_Frequency1_ID (0);
			setEXME_Frequency2_ID (0);
			setName (null);
			setReschedule (false);
			setStartDateReq (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Frequency2 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Frequency2[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency1.Table_Name);
        I_EXME_Frequency1 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency1)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency1_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 1.
		@param EXME_Frequency1_ID 
		Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID)
	{
		if (EXME_Frequency1_ID < 1)
			 throw new IllegalArgumentException ("EXME_Frequency1_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Frequency1_ID, Integer.valueOf(EXME_Frequency1_ID));
	}

	/** Get Frequency 1.
		@return Frequency Header ID
	  */
	public int getEXME_Frequency1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Frequency 2.
		@param EXME_Frequency2_ID 
		Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID)
	{
		if (EXME_Frequency2_ID < 1)
			 throw new IllegalArgumentException ("EXME_Frequency2_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Frequency2_ID, Integer.valueOf(EXME_Frequency2_ID));
	}

	/** Get Frequency 2.
		@return Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency2_ID);
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

	/** Set Quantity.
		@param Quantity Quantity	  */
	public void setQuantity (int Quantity)
	{
		set_Value (COLUMNNAME_Quantity, Integer.valueOf(Quantity));
	}

	/** Get Quantity.
		@return Quantity	  */
	public int getQuantity () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Quantity);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reschedule.
		@param Reschedule Reschedule	  */
	public void setReschedule (boolean Reschedule)
	{
		set_Value (COLUMNNAME_Reschedule, Boolean.valueOf(Reschedule));
	}

	/** Get Reschedule.
		@return Reschedule	  */
	public boolean isReschedule () 
	{
		Object oo = get_Value(COLUMNNAME_Reschedule);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start Date/Time Required.
		@param StartDateReq Start Date/Time Required	  */
	public void setStartDateReq (boolean StartDateReq)
	{
		set_Value (COLUMNNAME_StartDateReq, Boolean.valueOf(StartDateReq));
	}

	/** Get Start Date/Time Required.
		@return Start Date/Time Required	  */
	public boolean isStartDateReq () 
	{
		Object oo = get_Value(COLUMNNAME_StartDateReq);
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