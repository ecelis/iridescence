/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_PaqBaseDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_PaqBaseDet 
{

    /** TableName=I_EXME_PaqBaseDet */
    public static final String Table_Name = "I_EXME_PaqBaseDet";

    /** AD_Table_ID=1200165 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_Currency_Value */
    public static final String COLUMNNAME_C_Currency_Value = "C_Currency_Value";

	/** Set C_Currency_Value	  */
	public void setC_Currency_Value (String C_Currency_Value);

	/** Get C_Currency_Value	  */
	public String getC_Currency_Value();

    /** Column name CostAverage */
    public static final String COLUMNNAME_CostAverage = "CostAverage";

	/** Set Average Cost.
	  * Weighted average costs
	  */
	public void setCostAverage (BigDecimal CostAverage);

	/** Get Average Cost.
	  * Weighted average costs
	  */
	public BigDecimal getCostAverage();

    /** Column name Costo */
    public static final String COLUMNNAME_Costo = "Costo";

	/** Set Cost.
	  * Cost
	  */
	public void setCosto (BigDecimal Costo);

	/** Get Cost.
	  * Cost
	  */
	public BigDecimal getCosto();

    /** Column name CostStandard */
    public static final String COLUMNNAME_CostStandard = "CostStandard";

	/** Set Standard Cost.
	  * Standard Costs
	  */
	public void setCostStandard (BigDecimal CostStandard);

	/** Get Standard Cost.
	  * Standard Costs
	  */
	public BigDecimal getCostStandard();

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name C_Tax_Value */
    public static final String COLUMNNAME_C_Tax_Value = "C_Tax_Value";

	/** Set C_Tax_Value	  */
	public void setC_Tax_Value (String C_Tax_Value);

	/** Get C_Tax_Value	  */
	public String getC_Tax_Value();

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

    /** Column name EXME_PaqBaseDet_ID */
    public static final String COLUMNNAME_EXME_PaqBaseDet_ID = "EXME_PaqBaseDet_ID";

	/** Set Detail of Base Package.
	  * Detail of Base Package
	  */
	public void setEXME_PaqBaseDet_ID (int EXME_PaqBaseDet_ID);

	/** Get Detail of Base Package.
	  * Detail of Base Package
	  */
	public int getEXME_PaqBaseDet_ID();

	public I_EXME_PaqBaseDet getEXME_PaqBaseDet() throws RuntimeException;

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException;

    /** Column name EXME_PaqBase_Version_Value */
    public static final String COLUMNNAME_EXME_PaqBase_Version_Value = "EXME_PaqBase_Version_Value";

	/** Set Base Package Version Value	  */
	public void setEXME_PaqBase_Version_Value (String EXME_PaqBase_Version_Value);

	/** Get Base Package Version Value	  */
	public String getEXME_PaqBase_Version_Value();

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

    /** Column name I_EXME_PaqBaseDet_ID */
    public static final String COLUMNNAME_I_EXME_PaqBaseDet_ID = "I_EXME_PaqBaseDet_ID";

	/** Set I_EXME_PaqBaseDet_ID	  */
	public void setI_EXME_PaqBaseDet_ID (int I_EXME_PaqBaseDet_ID);

	/** Get I_EXME_PaqBaseDet_ID	  */
	public int getI_EXME_PaqBaseDet_ID();

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

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

    /** Column name LineTotalAmt */
    public static final String COLUMNNAME_LineTotalAmt = "LineTotalAmt";

	/** Set Line Total.
	  * Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt);

	/** Get Line Total.
	  * Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt();

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

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name PriceLastPO */
    public static final String COLUMNNAME_PriceLastPO = "PriceLastPO";

	/** Set Last PO Price.
	  * Price of the last purchase order for the product
	  */
	public void setPriceLastPO (BigDecimal PriceLastPO);

	/** Get Last PO Price.
	  * Price of the last purchase order for the product
	  */
	public BigDecimal getPriceLastPO();

    /** Column name PriceLimit */
    public static final String COLUMNNAME_PriceLimit = "PriceLimit";

	/** Set Limit Price.
	  * Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit);

	/** Get Limit Price.
	  * Lowest price for a product
	  */
	public BigDecimal getPriceLimit();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

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

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();
}
