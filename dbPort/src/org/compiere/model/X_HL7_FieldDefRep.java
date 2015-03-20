/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_FieldDefRep
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_FieldDefRep extends PO implements I_HL7_FieldDefRep, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_FieldDefRep (Properties ctx, int HL7_FieldDefRep_ID, String trxName)
    {
      super (ctx, HL7_FieldDefRep_ID, trxName);
      /** if (HL7_FieldDefRep_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_HL7_FieldDefRep (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_FieldDefRep[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_HL7_FieldDef getHL7_FieldDef() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_FieldDef.Table_Name);
        I_HL7_FieldDef result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_FieldDef)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_FieldDef_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Field Definition.
		@param HL7_FieldDef_ID HL7 Field Definition	  */
	public void setHL7_FieldDef_ID (int HL7_FieldDef_ID)
	{
		if (HL7_FieldDef_ID < 1) 
			set_Value (COLUMNNAME_HL7_FieldDef_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_FieldDef_ID, Integer.valueOf(HL7_FieldDef_ID));
	}

	/** Get HL7 Field Definition.
		@return HL7 Field Definition	  */
	public int getHL7_FieldDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_FieldDef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Field Defenition repetition.
		@param HL7_FieldDefRep_ID Field Defenition repetition	  */
	public void setHL7_FieldDefRep_ID (int HL7_FieldDefRep_ID)
	{
		if (HL7_FieldDefRep_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HL7_FieldDefRep_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_FieldDefRep_ID, Integer.valueOf(HL7_FieldDefRep_ID));
	}

	/** Get Field Defenition repetition.
		@return Field Defenition repetition	  */
	public int getHL7_FieldDefRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_FieldDefRep_ID);
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
			set_Value (COLUMNNAME_HL7_FieldRep_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_FieldRep_ID, Integer.valueOf(HL7_FieldRep_ID));
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
}