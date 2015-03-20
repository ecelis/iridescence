/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for HL7_FieldRep
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_FieldRep extends PO implements I_HL7_FieldRep, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_FieldRep (Properties ctx, int HL7_FieldRep_ID, String trxName)
    {
      super (ctx, HL7_FieldRep_ID, trxName);
      /** if (HL7_FieldRep_ID == 0)
        {
			setHL7_FieldRep_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_FieldRep (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_FieldRep[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_ValueNoCheck (COLUMNNAME_HL7_Field_ID, null);
		else 
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

	/** Set Field Repetition.
		@param HL7_FieldRep_ID Field Repetition	  */
	public void setHL7_FieldRep_ID (int HL7_FieldRep_ID)
	{
		if (HL7_FieldRep_ID < 1)
			 throw new IllegalArgumentException ("HL7_FieldRep_ID is mandatory.");
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

	/** Set Occurrence.
		@param Occurrence Occurrence	  */
	public void setOccurrence (BigDecimal Occurrence)
	{
		set_Value (COLUMNNAME_Occurrence, Occurrence);
	}

	/** Get Occurrence.
		@return Occurrence	  */
	public BigDecimal getOccurrence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Occurrence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}