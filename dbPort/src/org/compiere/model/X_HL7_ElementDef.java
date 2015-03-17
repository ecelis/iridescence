/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_ElementDef
 *  @author Generated Class 
 *  @version Release 5.5 - $Id$ */
public class X_HL7_ElementDef extends PO implements I_HL7_ElementDef, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_ElementDef (Properties ctx, int HL7_ElementDef_ID, String trxName)
    {
      super (ctx, HL7_ElementDef_ID, trxName);
      /** if (HL7_ElementDef_ID == 0)
        {
			setHL7_ElementDef_ID (0);
			setSequence (0);
// @SQL=SELECT NVL(MAX(SEQUENCE),0)+1 AS DefaultValue FROM HL7_ElementDef WHERE HL7_SegmentDef_ID = @HL7_SegmentDef_ID@
        } */
    }

    /** Load Constructor */
    public X_HL7_ElementDef (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_HL7_ElementDef[")
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

	/** Set HL7 Element Definition.
		@param HL7_ElementDef_ID HL7 Element Definition	  */
	public void setHL7_ElementDef_ID (int HL7_ElementDef_ID)
	{
		if (HL7_ElementDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_ElementDef_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_ElementDef_ID, Integer.valueOf(HL7_ElementDef_ID));
	}

	/** Get HL7 Element Definition.
		@return HL7 Element Definition	  */
	public int getHL7_ElementDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_ElementDef_ID);
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
			set_Value (COLUMNNAME_HL7_Field_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Field_ID, Integer.valueOf(HL7_Field_ID));
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

	public I_HL7_LinkedTable getHL7_LinkedTable() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_LinkedTable.Table_Name);
        I_HL7_LinkedTable result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_LinkedTable)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_LinkedTable_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Linked Tables HL7.
		@param HL7_LinkedTable_ID Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID)
	{
		if (HL7_LinkedTable_ID < 1) 
			set_Value (COLUMNNAME_HL7_LinkedTable_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_LinkedTable_ID, Integer.valueOf(HL7_LinkedTable_ID));
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
			set_ValueNoCheck (COLUMNNAME_HL7_SegmentDef_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_SegmentDef_ID, Integer.valueOf(HL7_SegmentDef_ID));
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

	/** Optionality AD_Reference_ID=1200207 */
	public static final int OPTIONALITY_AD_Reference_ID=1200207;
	/** Required = R */
	public static final String OPTIONALITY_Required = "R";
	/** Optional = O */
	public static final String OPTIONALITY_Optional = "O";
	/** Conditional = C */
	public static final String OPTIONALITY_Conditional = "C";
	/** Not used whit this Event = X */
	public static final String OPTIONALITY_NotUsedWhitThisEvent = "X";
	/** Backward Compatibility = B */
	public static final String OPTIONALITY_BackwardCompatibility = "B";
	/** Withdrawn = W */
	public static final String OPTIONALITY_Withdrawn = "W";
	/** Set Optionality.
		@param Optionality Optionality	  */
	public void setOptionality (String Optionality)
	{

		if (Optionality == null || Optionality.equals("R") || Optionality.equals("O") || Optionality.equals("C") || Optionality.equals("X") || Optionality.equals("B") || Optionality.equals("W")); else throw new IllegalArgumentException ("Optionality Invalid value - " + Optionality + " - Reference_ID=1200207 - R - O - C - X - B - W");		set_Value (COLUMNNAME_Optionality, Optionality);
	}

	/** Get Optionality.
		@return Optionality	  */
	public String getOptionality () 
	{
		return (String)get_Value(COLUMNNAME_Optionality);
	}

	/** Set Repeated.
		@param Repeated Repeated	  */
	public void setRepeated (int Repeated)
	{
		set_Value (COLUMNNAME_Repeated, Integer.valueOf(Repeated));
	}

	/** Get Repeated.
		@return Repeated	  */
	public int getRepeated () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Repeated);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}