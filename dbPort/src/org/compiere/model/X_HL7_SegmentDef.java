/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_SegmentDef
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_HL7_SegmentDef extends PO implements I_HL7_SegmentDef, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_SegmentDef (Properties ctx, int HL7_SegmentDef_ID, String trxName)
    {
      super (ctx, HL7_SegmentDef_ID, trxName);
      /** if (HL7_SegmentDef_ID == 0)
        {
			setHL7_SegmentDef_ID (0);
			setSequence (0);
// @SQL=SELECT NVL(MAX(SEQUENCE),0)+1 AS DefaultValue FROM HL7_SegmentDef WHERE HL7_MessageDef_ID = @HL7_MessageDef_ID@
        } */
    }

    /** Load Constructor */
    public X_HL7_SegmentDef (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_SegmentDef[")
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

	public I_HL7_MessageDef getHL7_MessageDef() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_MessageDef.Table_Name);
        I_HL7_MessageDef result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_MessageDef)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_MessageDef_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Message Definition.
		@param HL7_MessageDef_ID Message Definition	  */
	public void setHL7_MessageDef_ID (int HL7_MessageDef_ID)
	{
		if (HL7_MessageDef_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HL7_MessageDef_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HL7_MessageDef_ID, Integer.valueOf(HL7_MessageDef_ID));
	}

	/** Get Message Definition.
		@return Message Definition	  */
	public int getHL7_MessageDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageDef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Segment Definition.
		@param HL7_SegmentDef_ID HL7 Segment Definition	  */
	public void setHL7_SegmentDef_ID (int HL7_SegmentDef_ID)
	{
		if (HL7_SegmentDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_SegmentDef_ID is mandatory.");
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

	/** Set Parent Segment Definition.
		@param HL7_SegmentDef2_ID 
		HL7 Parent Segment Definition when this segment is part of a group
	  */
	public void setHL7_SegmentDef2_ID (int HL7_SegmentDef2_ID)
	{
		if (HL7_SegmentDef2_ID < 1) 
			set_Value (COLUMNNAME_HL7_SegmentDef2_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_SegmentDef2_ID, Integer.valueOf(HL7_SegmentDef2_ID));
	}

	/** Get Parent Segment Definition.
		@return HL7 Parent Segment Definition when this segment is part of a group
	  */
	public int getHL7_SegmentDef2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_SegmentDef2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Segment getHL7_Segment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Segment.Table_Name);
        I_HL7_Segment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Segment)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Segment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Segment.
		@param HL7_Segment_ID 
		Segment of an HL7 Message
	  */
	public void setHL7_Segment_ID (int HL7_Segment_ID)
	{
		if (HL7_Segment_ID < 1) 
			set_Value (COLUMNNAME_HL7_Segment_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Segment_ID, Integer.valueOf(HL7_Segment_ID));
	}

	/** Get HL7 Segment.
		@return Segment of an HL7 Message
	  */
	public int getHL7_Segment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Segment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Group.
		@param IsGroup 
		Marks this segment as a group
	  */
	public void setIsGroup (boolean IsGroup)
	{
		set_Value (COLUMNNAME_IsGroup, Boolean.valueOf(IsGroup));
	}

	/** Get Is Group.
		@return Marks this segment as a group
	  */
	public boolean isGroup () 
	{
		Object oo = get_Value(COLUMNNAME_IsGroup);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Parent link column.
		@param IsParent 
		This column is a link to the parent table (e.g. header from lines) - incl. Association key columns
	  */
	public void setIsParent (boolean IsParent)
	{
		set_Value (COLUMNNAME_IsParent, Boolean.valueOf(IsParent));
	}

	/** Get Parent link column.
		@return This column is a link to the parent table (e.g. header from lines) - incl. Association key columns
	  */
	public boolean isParent () 
	{
		Object oo = get_Value(COLUMNNAME_IsParent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Order by clause.
		@param OrderByClause 
		Fully qualified ORDER BY clause
	  */
	public void setOrderByClause (String OrderByClause)
	{
		set_Value (COLUMNNAME_OrderByClause, OrderByClause);
	}

	/** Get Order by clause.
		@return Fully qualified ORDER BY clause
	  */
	public String getOrderByClause () 
	{
		return (String)get_Value(COLUMNNAME_OrderByClause);
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

	/** Set Query segment.
		@param SegmentSql 
		Query segment
	  */
	public void setSegmentSql (String SegmentSql)
	{
		set_Value (COLUMNNAME_SegmentSql, SegmentSql);
	}

	/** Get Query segment.
		@return Query segment
	  */
	public String getSegmentSql () 
	{
		return (String)get_Value(COLUMNNAME_SegmentSql);
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