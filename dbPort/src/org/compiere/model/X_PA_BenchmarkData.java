/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PA_BenchmarkData
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PA_BenchmarkData extends PO implements I_PA_BenchmarkData, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PA_BenchmarkData (Properties ctx, int PA_BenchmarkData_ID, String trxName)
    {
      super (ctx, PA_BenchmarkData_ID, trxName);
      /** if (PA_BenchmarkData_ID == 0)
        {
			setBenchmarkDate (new Timestamp( System.currentTimeMillis() ));
			setBenchmarkValue (Env.ZERO);
			setName (null);
			setPA_BenchmarkData_ID (0);
			setPA_Benchmark_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PA_BenchmarkData (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PA_BenchmarkData[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Benchmark Date.
		@param BenchmarkDate 
		Benchmark Date
	  */
	public void setBenchmarkDate (Timestamp BenchmarkDate)
	{
		if (BenchmarkDate == null)
			throw new IllegalArgumentException ("BenchmarkDate is mandatory.");
		set_Value (COLUMNNAME_BenchmarkDate, BenchmarkDate);
	}

	/** Get Benchmark Date.
		@return Benchmark Date
	  */
	public Timestamp getBenchmarkDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BenchmarkDate);
	}

	/** Set Value.
		@param BenchmarkValue 
		Benchmark Value
	  */
	public void setBenchmarkValue (BigDecimal BenchmarkValue)
	{
		if (BenchmarkValue == null)
			throw new IllegalArgumentException ("BenchmarkValue is mandatory.");
		set_Value (COLUMNNAME_BenchmarkValue, BenchmarkValue);
	}

	/** Get Value.
		@return Benchmark Value
	  */
	public BigDecimal getBenchmarkValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BenchmarkValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Benchmark Data.
		@param PA_BenchmarkData_ID 
		Performance Benchmark Data Point
	  */
	public void setPA_BenchmarkData_ID (int PA_BenchmarkData_ID)
	{
		if (PA_BenchmarkData_ID < 1)
			 throw new IllegalArgumentException ("PA_BenchmarkData_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_BenchmarkData_ID, Integer.valueOf(PA_BenchmarkData_ID));
	}

	/** Get Benchmark Data.
		@return Performance Benchmark Data Point
	  */
	public int getPA_BenchmarkData_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_BenchmarkData_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PA_Benchmark getPA_Benchmark() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PA_Benchmark.Table_Name);
        I_PA_Benchmark result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PA_Benchmark)constructor.newInstance(new Object[] {getCtx(), new Integer(getPA_Benchmark_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Benchmark.
		@param PA_Benchmark_ID 
		Performance Benchmark
	  */
	public void setPA_Benchmark_ID (int PA_Benchmark_ID)
	{
		if (PA_Benchmark_ID < 1)
			 throw new IllegalArgumentException ("PA_Benchmark_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_Benchmark_ID, Integer.valueOf(PA_Benchmark_ID));
	}

	/** Get Benchmark.
		@return Performance Benchmark
	  */
	public int getPA_Benchmark_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_Benchmark_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}