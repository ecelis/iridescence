/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Charge
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_Charge 
{

    /** TableName=C_Charge */
    public static final String Table_Name = "C_Charge";

    /** AD_Table_ID=313 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AcctType */
    public static final String COLUMNNAME_AcctType = "AcctType";

	/** Set Revenue/Expense	  */
	public void setAcctType (String AcctType);

	/** Get Revenue/Expense	  */
	public String getAcctType();

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

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

    /** Column name C_ChargeType_ID */
    public static final String COLUMNNAME_C_ChargeType_ID = "C_ChargeType_ID";

	/** Set Charge Type	  */
	public void setC_ChargeType_ID (int C_ChargeType_ID);

	/** Get Charge Type	  */
	public int getC_ChargeType_ID();

	public I_C_ChargeType getC_ChargeType() throws RuntimeException;

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

    /** Column name ChargeAmt */
    public static final String COLUMNNAME_ChargeAmt = "ChargeAmt";

	/** Set Charge amount.
	  * Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt);

	/** Get Charge amount.
	  * Charge Amount
	  */
	public BigDecimal getChargeAmt();

    /** Column name C_TaxCategory_ID */
    public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";

	/** Set Tax Category.
	  * Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID);

	/** Get Tax Category.
	  * Tax Category
	  */
	public int getC_TaxCategory_ID();

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException;

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

    /** Column name Excepcion */
    public static final String COLUMNNAME_Excepcion = "Excepcion";

	/** Set Exception.
	  * Exception
	  */
	public void setExcepcion (String Excepcion);

	/** Get Exception.
	  * Exception
	  */
	public String getExcepcion();

    /** Column name InvoiceRequired */
    public static final String COLUMNNAME_InvoiceRequired = "InvoiceRequired";

	/** Set Requires invoice?	  */
	public void setInvoiceRequired (boolean InvoiceRequired);

	/** Get Requires invoice?	  */
	public boolean isInvoiceRequired();

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

    /** Column name IsSameCurrency */
    public static final String COLUMNNAME_IsSameCurrency = "IsSameCurrency";

	/** Set Same Currency	  */
	public void setIsSameCurrency (boolean IsSameCurrency);

	/** Get Same Currency	  */
	public boolean isSameCurrency();

    /** Column name IsSameTax */
    public static final String COLUMNNAME_IsSameTax = "IsSameTax";

	/** Set Same Tax.
	  * Use the same tax as the main transaction
	  */
	public void setIsSameTax (boolean IsSameTax);

	/** Get Same Tax.
	  * Use the same tax as the main transaction
	  */
	public boolean isSameTax();

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

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

    /** Column name NextInvoice */
    public static final String COLUMNNAME_NextInvoice = "NextInvoice";

	/** Set Next invoice	  */
	public void setNextInvoice (boolean NextInvoice);

	/** Get Next invoice	  */
	public boolean isNextInvoice();

    /** Column name PercentageApplied */
    public static final String COLUMNNAME_PercentageApplied = "PercentageApplied";

	/** Set % to be applied	  */
	public void setPercentageApplied (BigDecimal PercentageApplied);

	/** Get % to be applied	  */
	public BigDecimal getPercentageApplied();

    /** Column name PriceType */
    public static final String COLUMNNAME_PriceType = "PriceType";

	/** Set Price type	  */
	public void setPriceType (String PriceType);

	/** Get Price type	  */
	public String getPriceType();

    /** Column name PrintInvoiceDescription */
    public static final String COLUMNNAME_PrintInvoiceDescription = "PrintInvoiceDescription";

	/** Set Print invoice description	  */
	public void setPrintInvoiceDescription (String PrintInvoiceDescription);

	/** Get Print invoice description	  */
	public String getPrintInvoiceDescription();

    /** Column name ProductClass */
    public static final String COLUMNNAME_ProductClass = "ProductClass";

	/** Set Product Class	  */
	public void setProductClass (String ProductClass);

	/** Get Product Class	  */
	public String getProductClass();

    /** Column name SameTaxCategory */
    public static final String COLUMNNAME_SameTaxCategory = "SameTaxCategory";

	/** Set Same tax category	  */
	public void setSameTaxCategory (boolean SameTaxCategory);

	/** Get Same tax category	  */
	public boolean isSameTaxCategory();

    /** Column name Sign */
    public static final String COLUMNNAME_Sign = "Sign";

	/** Set Sign	  */
	public void setSign (boolean Sign);

	/** Get Sign	  */
	public boolean isSign();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
