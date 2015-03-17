/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_M_Substitute
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_M_Substitute 
{

    /** TableName=I_M_Substitute */
    public static final String Table_Name = "I_M_Substitute";

    /** AD_Table_ID=1200162 */
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

    /** Column name C_UOM_Value */
    public static final String COLUMNNAME_C_UOM_Value = "C_UOM_Value";

	/** Set C_UOM_Value	  */
	public void setC_UOM_Value (String C_UOM_Value);

	/** Get C_UOM_Value	  */
	public String getC_UOM_Value();

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

    /** Column name I_M_Substitute_ID */
    public static final String COLUMNNAME_I_M_Substitute_ID = "I_M_Substitute_ID";

	/** Set I_M_Substitute_ID	  */
	public void setI_M_Substitute_ID (int I_M_Substitute_ID);

	/** Get I_M_Substitute_ID	  */
	public int getI_M_Substitute_ID();

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

	public I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name Prod_Substitute_ID */
    public static final String COLUMNNAME_Prod_Substitute_ID = "Prod_Substitute_ID";

	/** Set Product Substitute.
	  * Product Substitute
	  */
	public void setProd_Substitute_ID (int Prod_Substitute_ID);

	/** Get Product Substitute.
	  * Product Substitute
	  */
	public int getProd_Substitute_ID();

    /** Column name Prod_Substitute_Value */
    public static final String COLUMNNAME_Prod_Substitute_Value = "Prod_Substitute_Value";

	/** Set Product Substitute Value	  */
	public void setProd_Substitute_Value (String Prod_Substitute_Value);

	/** Get Product Substitute Value	  */
	public String getProd_Substitute_Value();

    /** Column name QtyOrigin */
    public static final String COLUMNNAME_QtyOrigin = "QtyOrigin";

	/** Set Quantity Origin	  */
	public void setQtyOrigin (BigDecimal QtyOrigin);

	/** Get Quantity Origin	  */
	public BigDecimal getQtyOrigin();

    /** Column name QtyTarget */
    public static final String COLUMNNAME_QtyTarget = "QtyTarget";

	/** Set Quantity Target	  */
	public void setQtyTarget (BigDecimal QtyTarget);

	/** Get Quantity Target	  */
	public BigDecimal getQtyTarget();

    /** Column name UOM_Substitute_ID */
    public static final String COLUMNNAME_UOM_Substitute_ID = "UOM_Substitute_ID";

	/** Set UOM Substitute	  */
	public void setUOM_Substitute_ID (int UOM_Substitute_ID);

	/** Get UOM Substitute	  */
	public int getUOM_Substitute_ID();

    /** Column name UOM_Substitute_Value */
    public static final String COLUMNNAME_UOM_Substitute_Value = "UOM_Substitute_Value";

	/** Set UOM_Substitute_Value	  */
	public void setUOM_Substitute_Value (String UOM_Substitute_Value);

	/** Get UOM_Substitute_Value	  */
	public String getUOM_Substitute_Value();

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}
