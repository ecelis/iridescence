/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_Component
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Component extends PO implements I_HL7_Component, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Component (Properties ctx, int HL7_Component_ID, String trxName)
    {
      super (ctx, HL7_Component_ID, trxName);
      /** if (HL7_Component_ID == 0)
        {
			setHL7_Component_ID (0);
			setHL7_Field_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_Component (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Component[")
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

	/** Set Default Logic.
		@param DefaultValue 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue (String DefaultValue)
	{
		set_Value (COLUMNNAME_DefaultValue, DefaultValue);
	}

	/** Get Default Logic.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue);
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

	/** Set Has Subcomponents.
		@param HasSubcomponents Has Subcomponents	  */
	public void setHasSubcomponents (boolean HasSubcomponents)
	{
		set_Value (COLUMNNAME_HasSubcomponents, Boolean.valueOf(HasSubcomponents));
	}

	/** Get Has Subcomponents.
		@return Has Subcomponents	  */
	public boolean isHasSubcomponents () 
	{
		Object oo = get_Value(COLUMNNAME_HasSubcomponents);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set HL7 Component.
		@param HL7_Component_ID HL7 Component	  */
	public void setHL7_Component_ID (int HL7_Component_ID)
	{
		if (HL7_Component_ID < 1)
			 throw new IllegalArgumentException ("HL7_Component_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Component_ID, Integer.valueOf(HL7_Component_ID));
	}

	/** Get HL7 Component.
		@return HL7 Component	  */
	public int getHL7_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Field getHL7_Field() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Field.Table_Name);
        I_HL7_Field result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Field)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Field_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Field.
		@param HL7_Field_ID 
		Field of an HL7 Segment
	  */
	public void setHL7_Field_ID (int HL7_Field_ID)
	{
		if (HL7_Field_ID < 1)
			 throw new IllegalArgumentException ("HL7_Field_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Field_ID, Integer.valueOf(HL7_Field_ID));
	}

	/** Get HL7 Field.
		@return Field of an HL7 Segment
	  */
	public int getHL7_Field_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Field_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_FieldRep getHL7_FieldRep() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_FieldRep.Table_Name);
        I_HL7_FieldRep result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_FieldRep)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_FieldRep_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Field Repetition.
		@param HL7_FieldRep_ID Field Repetition	  */
	public void setHL7_FieldRep_ID (int HL7_FieldRep_ID)
	{
		if (HL7_FieldRep_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HL7_FieldRep_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_FieldRep_ID, Integer.valueOf(HL7_FieldRep_ID));
	}

	/** Get Field Repetition.
		@return Field Repetition	  */
	public int getHL7_FieldRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_FieldRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Date.
		@param IsDate 
		Marks the field as a Date to get it formated as such
	  */
	public void setIsDate (boolean IsDate)
	{
		set_Value (COLUMNNAME_IsDate, Boolean.valueOf(IsDate));
	}

	/** Get Is Date.
		@return Marks the field as a Date to get it formated as such
	  */
	public boolean isDate () 
	{
		Object oo = get_Value(COLUMNNAME_IsDate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Verified.
		@param IsVerified 
		The BOM configuration has been verified
	  */
	public void setIsVerified (String IsVerified)
	{
		set_Value (COLUMNNAME_IsVerified, IsVerified);
	}

	/** Get Verified.
		@return The BOM configuration has been verified
	  */
	public String getIsVerified () 
	{
		return (String)get_Value(COLUMNNAME_IsVerified);
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