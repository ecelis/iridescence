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

/** Generated Model for HL7_LinkedTableD
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_LinkedTableD extends PO implements I_HL7_LinkedTableD, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_LinkedTableD (Properties ctx, int HL7_LinkedTableD_ID, String trxName)
    {
      super (ctx, HL7_LinkedTableD_ID, trxName);
      /** if (HL7_LinkedTableD_ID == 0)
        {
			setAD_Column_ID (0);
			setComparisonOperator (null);
			setComparisonValue (null);
			setHL7_LinkedTableD_ID (0);
			setHL7_LinkedTable_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_LinkedTableD (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_LinkedTableD[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Column.Table_Name);
        I_AD_Column result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Column)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Column_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1)
			 throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_Column_ID()));
    }

	/** ComparisonOperator AD_Reference_ID=1200406 */
	public static final int COMPARISONOPERATOR_AD_Reference_ID=1200406;
	/** < = < */
	public static final String COMPARISONOPERATOR_Le = "<";
	/** = = = */
	public static final String COMPARISONOPERATOR_Eq = "=";
	/** > = > */
	public static final String COMPARISONOPERATOR_Gt = ">";
	/** >= = >= */
	public static final String COMPARISONOPERATOR_GtEq = ">=";
	/** <= = <= */
	public static final String COMPARISONOPERATOR_LeEq = "<=";
	/** <> = <> */
	public static final String COMPARISONOPERATOR_ = "<>";
	/** Set Comparison Operator.
		@param ComparisonOperator Comparison Operator	  */
	public void setComparisonOperator (String ComparisonOperator)
	{
		if (ComparisonOperator == null) throw new IllegalArgumentException ("ComparisonOperator is mandatory");
		if (ComparisonOperator.equals("<") || ComparisonOperator.equals("=") || ComparisonOperator.equals(">") || ComparisonOperator.equals(">=") || ComparisonOperator.equals("<=") || ComparisonOperator.equals("<>")); else throw new IllegalArgumentException ("ComparisonOperator Invalid value - " + ComparisonOperator + " - Reference_ID=1200406 - < - = - > - >= - <= - <>");		set_Value (COLUMNNAME_ComparisonOperator, ComparisonOperator);
	}

	/** Get Comparison Operator.
		@return Comparison Operator	  */
	public String getComparisonOperator () 
	{
		return (String)get_Value(COLUMNNAME_ComparisonOperator);
	}

	/** Set Comparison Value.
		@param ComparisonValue Comparison Value	  */
	public void setComparisonValue (String ComparisonValue)
	{
		if (ComparisonValue == null)
			throw new IllegalArgumentException ("ComparisonValue is mandatory.");
		set_Value (COLUMNNAME_ComparisonValue, ComparisonValue);
	}

	/** Get Comparison Value.
		@return Comparison Value	  */
	public String getComparisonValue () 
	{
		return (String)get_Value(COLUMNNAME_ComparisonValue);
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

	/** Set Linked Table Detail.
		@param HL7_LinkedTableD_ID Linked Table Detail	  */
	public void setHL7_LinkedTableD_ID (int HL7_LinkedTableD_ID)
	{
		if (HL7_LinkedTableD_ID < 1)
			 throw new IllegalArgumentException ("HL7_LinkedTableD_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_LinkedTableD_ID, Integer.valueOf(HL7_LinkedTableD_ID));
	}

	/** Get Linked Table Detail.
		@return Linked Table Detail	  */
	public int getHL7_LinkedTableD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_LinkedTableD_ID);
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
			 throw new IllegalArgumentException ("HL7_LinkedTable_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_LinkedTable_ID, Integer.valueOf(HL7_LinkedTable_ID));
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
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