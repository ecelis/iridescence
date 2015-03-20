/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_SubcomponentDef
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_SubcomponentDef extends PO implements I_HL7_SubcomponentDef, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_SubcomponentDef (Properties ctx, int HL7_SubcomponentDef_ID, String trxName)
    {
      super (ctx, HL7_SubcomponentDef_ID, trxName);
      /** if (HL7_SubcomponentDef_ID == 0)
        {
			setHL7_ComponentDef_ID (0);
			setHL7_SubcomponentDef_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_SubcomponentDef (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_SubcomponentDef[")
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

	public I_HL7_ComponentDef getHL7_ComponentDef() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_ComponentDef.Table_Name);
        I_HL7_ComponentDef result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_ComponentDef)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_ComponentDef_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Component Definition.
		@param HL7_ComponentDef_ID HL7 Component Definition	  */
	public void setHL7_ComponentDef_ID (int HL7_ComponentDef_ID)
	{
		if (HL7_ComponentDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_ComponentDef_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_ComponentDef_ID, Integer.valueOf(HL7_ComponentDef_ID));
	}

	/** Get HL7 Component Definition.
		@return HL7 Component Definition	  */
	public int getHL7_ComponentDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_ComponentDef_ID);
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

	/** Set HL7 Subcomponent Definition.
		@param HL7_SubcomponentDef_ID HL7 Subcomponent Definition	  */
	public void setHL7_SubcomponentDef_ID (int HL7_SubcomponentDef_ID)
	{
		if (HL7_SubcomponentDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_SubcomponentDef_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_SubcomponentDef_ID, Integer.valueOf(HL7_SubcomponentDef_ID));
	}

	/** Get HL7 Subcomponent Definition.
		@return HL7 Subcomponent Definition	  */
	public int getHL7_SubcomponentDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_SubcomponentDef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Subcomponent getHL7_Subcomponent() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Subcomponent.Table_Name);
        I_HL7_Subcomponent result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Subcomponent)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Subcomponent_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Subcomponent.
		@param HL7_Subcomponent_ID HL7 Subcomponent	  */
	public void setHL7_Subcomponent_ID (int HL7_Subcomponent_ID)
	{
		if (HL7_Subcomponent_ID < 1) 
			set_Value (COLUMNNAME_HL7_Subcomponent_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Subcomponent_ID, Integer.valueOf(HL7_Subcomponent_ID));
	}

	/** Get HL7 Subcomponent.
		@return HL7 Subcomponent	  */
	public int getHL7_Subcomponent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Subcomponent_ID);
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