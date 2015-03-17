/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_FieldDef
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_FieldDef extends PO implements I_HL7_FieldDef, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_FieldDef (Properties ctx, int HL7_FieldDef_ID, String trxName)
    {
      super (ctx, HL7_FieldDef_ID, trxName);
      /** if (HL7_FieldDef_ID == 0)
        {
			setHL7_FieldDef_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_FieldDef (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_FieldDef[")
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

	/** Set Has Components.
		@param HasComponents Has Components	  */
	public void setHasComponents (boolean HasComponents)
	{
		set_Value (COLUMNNAME_HasComponents, Boolean.valueOf(HasComponents));
	}

	/** Get Has Components.
		@return Has Components	  */
	public boolean isHasComponents () 
	{
		Object oo = get_Value(COLUMNNAME_HasComponents);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_HL7_Evaluation getHL7_Evaluation() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Evaluation.Table_Name);
        I_HL7_Evaluation result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Evaluation)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Evaluation_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Evaluation.
		@param HL7_Evaluation_ID HL7 Evaluation	  */
	public void setHL7_Evaluation_ID (int HL7_Evaluation_ID)
	{
		if (HL7_Evaluation_ID < 1) 
			set_Value (COLUMNNAME_HL7_Evaluation_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Evaluation_ID, Integer.valueOf(HL7_Evaluation_ID));
	}

	/** Get HL7 Evaluation.
		@return HL7 Evaluation	  */
	public int getHL7_Evaluation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Evaluation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Field Definition.
		@param HL7_FieldDef_ID HL7 Field Definition	  */
	public void setHL7_FieldDef_ID (int HL7_FieldDef_ID)
	{
		if (HL7_FieldDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_FieldDef_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_FieldDef_ID, Integer.valueOf(HL7_FieldDef_ID));
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

	/** Set Increment Type.
		@param IncrementType Increment Type	  */
	public void setIncrementType (boolean IncrementType)
	{
		set_Value (COLUMNNAME_IncrementType, Boolean.valueOf(IncrementType));
	}

	/** Get Increment Type.
		@return Increment Type	  */
	public boolean isIncrementType () 
	{
		Object oo = get_Value(COLUMNNAME_IncrementType);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Identifier.
		@param IsIdentifier 
		This column is part of the record identifier
	  */
	public void setIsIdentifier (boolean IsIdentifier)
	{
		set_Value (COLUMNNAME_IsIdentifier, Boolean.valueOf(IsIdentifier));
	}

	/** Get Identifier.
		@return This column is part of the record identifier
	  */
	public boolean isIdentifier () 
	{
		Object oo = get_Value(COLUMNNAME_IsIdentifier);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Repeated.
		@param IsRepeated Is Repeated	  */
	public void setIsRepeated (boolean IsRepeated)
	{
		set_Value (COLUMNNAME_IsRepeated, Boolean.valueOf(IsRepeated));
	}

	/** Get Is Repeated.
		@return Is Repeated	  */
	public boolean isRepeated () 
	{
		Object oo = get_Value(COLUMNNAME_IsRepeated);
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