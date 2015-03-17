/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_UOM
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_UOM 
{

    /** TableName=C_UOM */
    public static final String Table_Name = "C_UOM";

    /** AD_Table_ID=146 */
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

    /** Column name CostingPrecision */
    public static final String COLUMNNAME_CostingPrecision = "CostingPrecision";

	/** Set Costing Precision.
	  * Rounding used costing calculations
	  */
	public void setCostingPrecision (int CostingPrecision);

	/** Get Costing Precision.
	  * Rounding used costing calculations
	  */
	public int getCostingPrecision();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

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

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

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

    /** Column name SistemaMedicion */
    public static final String COLUMNNAME_SistemaMedicion = "SistemaMedicion";

	/** Set Systems of measurement.
	  * Systems of measurement
	  */
	public void setSistemaMedicion (String SistemaMedicion);

	/** Get Systems of measurement.
	  * Systems of measurement
	  */
	public String getSistemaMedicion();

    /** Column name StdPrecision */
    public static final String COLUMNNAME_StdPrecision = "StdPrecision";

	/** Set Standard Precision.
	  * Rule for rounding  calculated amounts
	  */
	public void setStdPrecision (int StdPrecision);

	/** Get Standard Precision.
	  * Rule for rounding  calculated amounts
	  */
	public int getStdPrecision();

    /** Column name UOMSymbol */
    public static final String COLUMNNAME_UOMSymbol = "UOMSymbol";

	/** Set Symbol.
	  * Symbol for a Unit of Measure
	  */
	public void setUOMSymbol (String UOMSymbol);

	/** Get Symbol.
	  * Symbol for a Unit of Measure
	  */
	public String getUOMSymbol();

    /** Column name UOMType */
    public static final String COLUMNNAME_UOMType = "UOMType";

	/** Set UOM Type	  */
	public void setUOMType (String UOMType);

	/** Get UOM Type	  */
	public String getUOMType();

    /** Column name X12DE355 */
    public static final String COLUMNNAME_X12DE355 = "X12DE355";

	/** Set UOM Code.
	  * UOM EDI X12 Code
	  */
	public void setX12DE355 (String X12DE355);

	/** Get UOM Code.
	  * UOM EDI X12 Code
	  */
	public String getX12DE355();
}
