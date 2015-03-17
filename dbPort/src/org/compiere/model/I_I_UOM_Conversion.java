/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_UOM_Conversion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_UOM_Conversion 
{

    /** TableName=I_UOM_Conversion */
    public static final String Table_Name = "I_UOM_Conversion";

    /** AD_Table_ID=1000155 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name C_UOM_Conversion_ID */
    public static final String COLUMNNAME_C_UOM_Conversion_ID = "C_UOM_Conversion_ID";

	/** Set UOM Conversion.
	  * Unit of Measure Conversion
	  */
	public void setC_UOM_Conversion_ID (int C_UOM_Conversion_ID);

	/** Get UOM Conversion.
	  * Unit of Measure Conversion
	  */
	public int getC_UOM_Conversion_ID();

	public I_C_UOM_Conversion getC_UOM_Conversion() throws RuntimeException;

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

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name C_UOM_To_ID */
    public static final String COLUMNNAME_C_UOM_To_ID = "C_UOM_To_ID";

	/** Set UoM To.
	  * Target or destination Unit of Measure
	  */
	public void setC_UOM_To_ID (int C_UOM_To_ID);

	/** Get UoM To.
	  * Target or destination Unit of Measure
	  */
	public int getC_UOM_To_ID();

    /** Column name DivideRate */
    public static final String COLUMNNAME_DivideRate = "DivideRate";

	/** Set Divide Rate.
	  * To convert Source number to Target number, the Source is divided
	  */
	public void setDivideRate (BigDecimal DivideRate);

	/** Get Divide Rate.
	  * To convert Source number to Target number, the Source is divided
	  */
	public BigDecimal getDivideRate();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_UOM_Conversion_ID */
    public static final String COLUMNNAME_I_UOM_Conversion_ID = "I_UOM_Conversion_ID";

	/** Set Imported Conversion of Unit of Measure.
	  * Imported Conversion of unit of Measure
	  */
	public void setI_UOM_Conversion_ID (int I_UOM_Conversion_ID);

	/** Get Imported Conversion of Unit of Measure.
	  * Imported Conversion of unit of Measure
	  */
	public int getI_UOM_Conversion_ID();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

    /** Column name M_Product_Value */
    public static final String COLUMNNAME_M_Product_Value = "M_Product_Value";

	/** Set Product Code.
	  * product search Code
	  */
	public void setM_Product_Value (String M_Product_Value);

	/** Get Product Code.
	  * product search Code
	  */
	public String getM_Product_Value();

    /** Column name MultiplyRate */
    public static final String COLUMNNAME_MultiplyRate = "MultiplyRate";

	/** Set Multiply Rate.
	  * Rate to multiple the source by to calculate the target.
	  */
	public void setMultiplyRate (BigDecimal MultiplyRate);

	/** Get Multiply Rate.
	  * Rate to multiple the source by to calculate the target.
	  */
	public BigDecimal getMultiplyRate();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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

    /** Column name X12DE355To */
    public static final String COLUMNNAME_X12DE355To = "X12DE355To";

	/** Set Destin EDI code.
	  * Destin EDI of UoM code
	  */
	public void setX12DE355To (String X12DE355To);

	/** Get Destin EDI code.
	  * Destin EDI of UoM code
	  */
	public String getX12DE355To();
}
