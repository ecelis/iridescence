/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_ComponentDef
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_ComponentDef extends PO implements I_HL7_ComponentDef, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_ComponentDef (Properties ctx, int HL7_ComponentDef_ID, String trxName)
    {
      super (ctx, HL7_ComponentDef_ID, trxName);
      /** if (HL7_ComponentDef_ID == 0)
        {
			setHL7_ComponentDef_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_ComponentDef (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_ComponentDef[")
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

	/** Set Has Subcomponents.
		@param HasSubcomponents Has Subcomponents	  */
	public void setHasSubcomponents (boolean HasSubcomponents)
	{
		set_Value (COLUMNNAME_HasSubcomponents, Boolean.valueOf(HasSubcomponents));
	}

	/** Get Has Subcomponents.
		@return Has Subcomponents	  */
	public boolean isHasSubcomponents () 
	{
		Object oo = get_Value(COLUMNNAME_HasSubcomponents);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	public I_HL7_Component getHL7_Component() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Component.Table_Name);
        I_HL7_Component result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Component)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Component_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Component.
		@param HL7_Component_ID HL7 Component	  */
	public void setHL7_Component_ID (int HL7_Component_ID)
	{
		if (HL7_Component_ID < 1) 
			set_Value (COLUMNNAME_HL7_Component_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Component_ID, Integer.valueOf(HL7_Component_ID));
	}

	/** Get HL7 Component.
		@return HL7 Component	  */
	public int getHL7_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_ValueNoCheck (COLUMNNAME_HL7_FieldDef_ID, null);
		else 
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

	public I_HL7_FieldDefRep getHL7_FieldDefRep() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_FieldDefRep.Table_Name);
        I_HL7_FieldDefRep result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_FieldDefRep)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_FieldDefRep_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Field Defenition repetition.
		@param HL7_FieldDefRep_ID Field Defenition repetition	  */
	public void setHL7_FieldDefRep_ID (int HL7_FieldDefRep_ID)
	{
		if (HL7_FieldDefRep_ID < 1) 
			set_Value (COLUMNNAME_HL7_FieldDefRep_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_FieldDefRep_ID, Integer.valueOf(HL7_FieldDefRep_ID));
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