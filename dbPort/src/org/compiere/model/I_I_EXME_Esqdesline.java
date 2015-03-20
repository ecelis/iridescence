/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Esqdesline
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Esqdesline 
{

    /** TableName=I_EXME_Esqdesline */
    public static final String Table_Name = "I_EXME_Esqdesline";

    /** AD_Table_ID=1200016 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Value */
    public static final String COLUMNNAME_C_BPartner_Value = "C_BPartner_Value";

	/** Set Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value);

	/** Get Business Partner Value	  */
	public String getC_BPartner_Value();

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getC_BP_Group() throws RuntimeException;

    /** Column name C_BP_Group_Value */
    public static final String COLUMNNAME_C_BP_Group_Value = "C_BP_Group_Value";

	/** Set Business Partner Group Value.
	  * Value of Business Partner Group
	  */
	public void setC_BP_Group_Value (String C_BP_Group_Value);

	/** Get Business Partner Group Value.
	  * Value of Business Partner Group
	  */
	public String getC_BP_Group_Value();

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

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Area.
	  * Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Area.
	  * Area
	  */
	public int getEXME_Area_ID();

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name EXME_Area_Value */
    public static final String COLUMNNAME_EXME_Area_Value = "EXME_Area_Value";

	/** Set EXME_Area_Value	  */
	public void setEXME_Area_Value (String EXME_Area_Value);

	/** Get EXME_Area_Value	  */
	public String getEXME_Area_Value();

    /** Column name EXME_EsqDesLine_ID */
    public static final String COLUMNNAME_EXME_EsqDesLine_ID = "EXME_EsqDesLine_ID";

	/** Set Price List Discount.
	  * Lines of discount schema
	  */
	public void setEXME_EsqDesLine_ID (int EXME_EsqDesLine_ID);

	/** Get Price List Discount.
	  * Lines of discount schema
	  */
	public int getEXME_EsqDesLine_ID();

	public I_EXME_EsqDesLine getEXME_EsqDesLine() throws RuntimeException;

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

    /** Column name I_EXME_Esqdesline_ID */
    public static final String COLUMNNAME_I_EXME_Esqdesline_ID = "I_EXME_Esqdesline_ID";

	/** Set I_EXME_Esqdesline_ID	  */
	public void setI_EXME_Esqdesline_ID (int I_EXME_Esqdesline_ID);

	/** Get I_EXME_Esqdesline_ID	  */
	public int getI_EXME_Esqdesline_ID();

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

    /** Column name List_AddAmt */
    public static final String COLUMNNAME_List_AddAmt = "List_AddAmt";

	/** Set List price Surcharge Amount.
	  * List Price Surcharge Amount
	  */
	public void setList_AddAmt (BigDecimal List_AddAmt);

	/** Get List price Surcharge Amount.
	  * List Price Surcharge Amount
	  */
	public BigDecimal getList_AddAmt();

    /** Column name List_Discount */
    public static final String COLUMNNAME_List_Discount = "List_Discount";

	/** Set List price Discount %.
	  * Discount from list price as a percentage
	  */
	public void setList_Discount (BigDecimal List_Discount);

	/** Get List price Discount %.
	  * Discount from list price as a percentage
	  */
	public BigDecimal getList_Discount();

    /** Column name Moneda */
    public static final String COLUMNNAME_Moneda = "Moneda";

	/** Set Coin	  */
	public void setMoneda (String Moneda);

	/** Get Coin	  */
	public String getMoneda();

    /** Column name Monto */
    public static final String COLUMNNAME_Monto = "Monto";

	/** Set Amount	  */
	public void setMonto (String Monto);

	/** Get Amount	  */
	public String getMonto();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_Category_Value */
    public static final String COLUMNNAME_M_Product_Category_Value = "M_Product_Category_Value";

	/** Set Product Category	  */
	public void setM_Product_Category_Value (String M_Product_Category_Value);

	/** Get Product Category	  */
	public String getM_Product_Category_Value();

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

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

    /** Column name Unidad_Operativa */
    public static final String COLUMNNAME_Unidad_Operativa = "Unidad_Operativa";

	/** Set Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa);

	/** Get Operational Unit	  */
	public String getUnidad_Operativa();

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
