/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PA_BenchmarkData
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PA_BenchmarkData 
{

    /** TableName=PA_BenchmarkData */
    public static final String Table_Name = "PA_BenchmarkData";

    /** AD_Table_ID=834 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name BenchmarkDate */
    public static final String COLUMNNAME_BenchmarkDate = "BenchmarkDate";

	/** Set Benchmark Date.
	  * Benchmark Date
	  */
	public void setBenchmarkDate (Timestamp BenchmarkDate);

	/** Get Benchmark Date.
	  * Benchmark Date
	  */
	public Timestamp getBenchmarkDate();

    /** Column name BenchmarkValue */
    public static final String COLUMNNAME_BenchmarkValue = "BenchmarkValue";

	/** Set Value.
	  * Benchmark Value
	  */
	public void setBenchmarkValue (BigDecimal BenchmarkValue);

	/** Get Value.
	  * Benchmark Value
	  */
	public BigDecimal getBenchmarkValue();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PA_BenchmarkData_ID */
    public static final String COLUMNNAME_PA_BenchmarkData_ID = "PA_BenchmarkData_ID";

	/** Set Benchmark Data.
	  * Performance Benchmark Data Point
	  */
	public void setPA_BenchmarkData_ID (int PA_BenchmarkData_ID);

	/** Get Benchmark Data.
	  * Performance Benchmark Data Point
	  */
	public int getPA_BenchmarkData_ID();

    /** Column name PA_Benchmark_ID */
    public static final String COLUMNNAME_PA_Benchmark_ID = "PA_Benchmark_ID";

	/** Set Benchmark.
	  * Performance Benchmark
	  */
	public void setPA_Benchmark_ID (int PA_Benchmark_ID);

	/** Get Benchmark.
	  * Performance Benchmark
	  */
	public int getPA_Benchmark_ID();

	public I_PA_Benchmark getPA_Benchmark() throws RuntimeException;
}
