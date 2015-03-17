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

/** Generated Model for HL7_Version
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Version extends PO implements I_HL7_Version, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Version (Properties ctx, int HL7_Version_ID, String trxName)
    {
      super (ctx, HL7_Version_ID, trxName);
      /** if (HL7_Version_ID == 0)
        {
			setHL7_Version_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_Version (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Version[")
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

	public I_HL7_MessagingStandard getHL7_MessagingStandard() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_MessagingStandard.Table_Name);
        I_HL7_MessagingStandard result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_MessagingStandard)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_MessagingStandard_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Messaging Standard.
		@param HL7_MessagingStandard_ID Messaging Standard	  */
	public void setHL7_MessagingStandard_ID (int HL7_MessagingStandard_ID)
	{
		if (HL7_MessagingStandard_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HL7_MessagingStandard_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_MessagingStandard_ID, Integer.valueOf(HL7_MessagingStandard_ID));
	}

	/** Get Messaging Standard.
		@return Messaging Standard	  */
	public int getHL7_MessagingStandard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessagingStandard_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHL7_MessagingStandard_ID()));
    }

	/** Set HL7 Version.
		@param HL7_Version_ID 
		HL7 Version number
	  */
	public void setHL7_Version_ID (int HL7_Version_ID)
	{
		if (HL7_Version_ID < 1)
			 throw new IllegalArgumentException ("HL7_Version_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Version_ID, Integer.valueOf(HL7_Version_ID));
	}

	/** Get HL7 Version.
		@return HL7 Version number
	  */
	public int getHL7_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Version_ID);
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