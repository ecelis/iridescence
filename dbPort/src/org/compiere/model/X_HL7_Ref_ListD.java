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

/** Generated Model for HL7_Ref_ListD
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Ref_ListD extends PO implements I_HL7_Ref_ListD, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Ref_ListD (Properties ctx, int HL7_Ref_ListD_ID, String trxName)
    {
      super (ctx, HL7_Ref_ListD_ID, trxName);
      /** if (HL7_Ref_ListD_ID == 0)
        {
			setAD_Ref_List_ID (0);
			setHL7_Ref_ListD_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_Ref_ListD (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Ref_ListD[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Ref_List getAD_Ref_List() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Ref_List.Table_Name);
        I_AD_Ref_List result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Ref_List)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Ref_List_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reference List.
		@param AD_Ref_List_ID 
		Reference List based on Table
	  */
	public void setAD_Ref_List_ID (int AD_Ref_List_ID)
	{
		if (AD_Ref_List_ID < 1)
			 throw new IllegalArgumentException ("AD_Ref_List_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Ref_List_ID, Integer.valueOf(AD_Ref_List_ID));
	}

	/** Get Reference List.
		@return Reference List based on Table
	  */
	public int getAD_Ref_List_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Ref_List_ID);
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

	/** Set HL7 Reference List Detail.
		@param HL7_Ref_ListD_ID HL7 Reference List Detail	  */
	public void setHL7_Ref_ListD_ID (int HL7_Ref_ListD_ID)
	{
		if (HL7_Ref_ListD_ID < 1)
			 throw new IllegalArgumentException ("HL7_Ref_ListD_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Ref_ListD_ID, Integer.valueOf(HL7_Ref_ListD_ID));
	}

	/** Get HL7 Reference List Detail.
		@return HL7 Reference List Detail	  */
	public int getHL7_Ref_ListD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Ref_ListD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Version getHL7_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Version.Table_Name);
        I_HL7_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Version.
		@param HL7_Version_ID 
		HL7 Version number
	  */
	public void setHL7_Version_ID (int HL7_Version_ID)
	{
		if (HL7_Version_ID < 1) 
			set_Value (COLUMNNAME_HL7_Version_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Version_ID, Integer.valueOf(HL7_Version_ID));
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