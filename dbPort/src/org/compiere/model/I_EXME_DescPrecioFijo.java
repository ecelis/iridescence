/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DescPrecioFijo
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_DescPrecioFijo 
{

    /** TableName=EXME_DescPrecioFijo */
    public static final String Table_Name = "EXME_DescPrecioFijo";

    /** AD_Table_ID=1200446 */
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

    /** Column name AplicaCoaseDeduc */
    public static final String COLUMNNAME_AplicaCoaseDeduc = "AplicaCoaseDeduc";

	/** Set Discount on Deductible and Coinsurance.
	  * Apply discount on deductible and coinsurance
	  */
	public void setAplicaCoaseDeduc (boolean AplicaCoaseDeduc);

	/** Get Discount on Deductible and Coinsurance.
	  * Apply discount on deductible and coinsurance
	  */
	public boolean isAplicaCoaseDeduc();

    /** Column name AplicaConvenio */
    public static final String COLUMNNAME_AplicaConvenio = "AplicaConvenio";

	/** Set Applies Agreement.
	  * Apply discount for Agreement
	  */
	public void setAplicaConvenio (boolean AplicaConvenio);

	/** Get Applies Agreement.
	  * Apply discount for Agreement
	  */
	public boolean isAplicaConvenio();

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

	public I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name DescFijo */
    public static final String COLUMNNAME_DescFijo = "DescFijo";

	/** Set Fixed Discount.
	  * Fixed Discount
	  */
	public void setDescFijo (BigDecimal DescFijo);

	/** Get Fixed Discount.
	  * Fixed Discount
	  */
	public BigDecimal getDescFijo();

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

    /** Column name EXME_DescPrecioFijo_ID */
    public static final String COLUMNNAME_EXME_DescPrecioFijo_ID = "EXME_DescPrecioFijo_ID";

	/** Set Discount for a fixed billing Price.
	  * Discount for a fixed billing Price
	  */
	public void setEXME_DescPrecioFijo_ID (int EXME_DescPrecioFijo_ID);

	/** Get Discount for a fixed billing Price.
	  * Discount for a fixed billing Price
	  */
	public int getEXME_DescPrecioFijo_ID();

    /** Column name ExtensionNo */
    public static final String COLUMNNAME_ExtensionNo = "ExtensionNo";

	/** Set Extension Number.
	  * Extension Number
	  */
	public void setExtensionNo (int ExtensionNo);

	/** Get Extension Number.
	  * Extension Number
	  */
	public int getExtensionNo();

    /** Column name FactValorReal */
    public static final String COLUMNNAME_FactValorReal = "FactValorReal";

	/** Set FactValorReal.
	  * Invoice value of the account
	  */
	public void setFactValorReal (boolean FactValorReal);

	/** Get FactValorReal.
	  * Invoice value of the account
	  */
	public boolean isFactValorReal();

    /** Column name PrecioFijo */
    public static final String COLUMNNAME_PrecioFijo = "PrecioFijo";

	/** Set Fixed Price.
	  * Fixed Price
	  */
	public void setPrecioFijo (BigDecimal PrecioFijo);

	/** Get Fixed Price.
	  * Fixed Price
	  */
	public BigDecimal getPrecioFijo();

    /** Column name TopeMaxCta */
    public static final String COLUMNNAME_TopeMaxCta = "TopeMaxCta";

	/** Set Maximum total of the Account.
	  * Maximum total value of the account
	  */
	public void setTopeMaxCta (BigDecimal TopeMaxCta);

	/** Get Maximum total of the Account.
	  * Maximum total value of the account
	  */
	public BigDecimal getTopeMaxCta();

    /** Column name TopeMaxDesc */
    public static final String COLUMNNAME_TopeMaxDesc = "TopeMaxDesc";

	/** Set Upper limit for application of discount.
	  * Upper limit for application of discount
	  */
	public void setTopeMaxDesc (BigDecimal TopeMaxDesc);

	/** Get Upper limit for application of discount.
	  * Upper limit for application of discount
	  */
	public BigDecimal getTopeMaxDesc();

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
