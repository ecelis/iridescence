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

/** Generated Model for HL7_MessageDef
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_MessageDef extends PO implements I_HL7_MessageDef, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_MessageDef (Properties ctx, int HL7_MessageDef_ID, String trxName)
    {
      super (ctx, HL7_MessageDef_ID, trxName);
      /** if (HL7_MessageDef_ID == 0)
        {
			setHL7_MessageDef_ID (0);
			setHL7_Message_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_MessageDef (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_MessageDef[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Copy Message Definition.
		@param CopyMessageDef Copy Message Definition	  */
	public void setCopyMessageDef (String CopyMessageDef)
	{
		set_Value (COLUMNNAME_CopyMessageDef, CopyMessageDef);
	}

	/** Get Copy Message Definition.
		@return Copy Message Definition	  */
	public String getCopyMessageDef () 
	{
		return (String)get_Value(COLUMNNAME_CopyMessageDef);
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

	public I_HL7_GenerationTable getHL7_GenerationTable() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_GenerationTable.Table_Name);
        I_HL7_GenerationTable result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_GenerationTable)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_GenerationTable_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Message Generation Table.
		@param HL7_GenerationTable_ID Message Generation Table	  */
	public void setHL7_GenerationTable_ID (int HL7_GenerationTable_ID)
	{
		if (HL7_GenerationTable_ID < 1) 
			set_Value (COLUMNNAME_HL7_GenerationTable_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_GenerationTable_ID, Integer.valueOf(HL7_GenerationTable_ID));
	}

	/** Get Message Generation Table.
		@return Message Generation Table	  */
	public int getHL7_GenerationTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_GenerationTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Message Definition.
		@param HL7_MessageDef_ID HL7 Message Definition	  */
	public void setHL7_MessageDef_ID (int HL7_MessageDef_ID)
	{
		if (HL7_MessageDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_MessageDef_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_MessageDef_ID, Integer.valueOf(HL7_MessageDef_ID));
	}

	/** Get HL7 Message Definition.
		@return HL7 Message Definition	  */
	public int getHL7_MessageDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageDef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Message getHL7_Message() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Message.Table_Name);
        I_HL7_Message result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Message)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Message_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Message.
		@param HL7_Message_ID HL7 Message	  */
	public void setHL7_Message_ID (int HL7_Message_ID)
	{
		if (HL7_Message_ID < 1)
			 throw new IllegalArgumentException ("HL7_Message_ID is mandatory.");
		set_Value (COLUMNNAME_HL7_Message_ID, Integer.valueOf(HL7_Message_ID));
	}

	/** Get HL7 Message.
		@return HL7 Message	  */
	public int getHL7_Message_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Message_ID);
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
}