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

/** Generated Model for HL7_Group
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Group extends PO implements I_HL7_Group, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Group (Properties ctx, int HL7_Group_ID, String trxName)
    {
      super (ctx, HL7_Group_ID, trxName);
      /** if (HL7_Group_ID == 0)
        {
			setHL7_Group_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_Group (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Group[")
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

	/** Set HL7 Group.
		@param HL7_Group_ID 
		Group of Segments
	  */
	public void setHL7_Group_ID (int HL7_Group_ID)
	{
		if (HL7_Group_ID < 1)
			 throw new IllegalArgumentException ("HL7_Group_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Group_ID, Integer.valueOf(HL7_Group_ID));
	}

	/** Get HL7 Group.
		@return Group of Segments
	  */
	public int getHL7_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_SegmentDef getHL7_SegmentDef() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_SegmentDef.Table_Name);
        I_HL7_SegmentDef result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_SegmentDef)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_SegmentDef_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Segment Definition.
		@param HL7_SegmentDef_ID HL7 Segment Definition	  */
	public void setHL7_SegmentDef_ID (int HL7_SegmentDef_ID)
	{
		if (HL7_SegmentDef_ID < 1) 
			set_Value (COLUMNNAME_HL7_SegmentDef_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_SegmentDef_ID, Integer.valueOf(HL7_SegmentDef_ID));
	}

	/** Get HL7 Segment Definition.
		@return HL7 Segment Definition	  */
	public int getHL7_SegmentDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_SegmentDef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** StructureType AD_Reference_ID=1200360 */
	public static final int STRUCTURETYPE_AD_Reference_ID=1200360;
	/** Set Structure Type.
		@param StructureType 
		Type of gruop Struture -Array, Group, Segment
	  */
	public void setStructureType (String StructureType)
	{
		set_Value (COLUMNNAME_StructureType, StructureType);
	}

	/** Get Structure Type.
		@return Type of gruop Struture -Array, Group, Segment
	  */
	public String getStructureType () 
	{
		return (String)get_Value(COLUMNNAME_StructureType);
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