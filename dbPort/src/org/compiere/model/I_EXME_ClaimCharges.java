/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimCharges
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimCharges 
{

    /** TableName=EXME_ClaimCharges */
    public static final String Table_Name = "EXME_ClaimCharges";

    /** AD_Table_ID=1201238 */
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

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

    /** Column name DateDelivered */
    public static final String COLUMNNAME_DateDelivered = "DateDelivered";

	/** Set Date Delivered.
	  * Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered);

	/** Get Date Delivered.
	  * Date when the product was delivered
	  */
	public Timestamp getDateDelivered();

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

    /** Column name EXME_ClaimCharges_ID */
    public static final String COLUMNNAME_EXME_ClaimCharges_ID = "EXME_ClaimCharges_ID";

	/** Set EXME_ClaimCharges_ID	  */
	public void setEXME_ClaimCharges_ID (int EXME_ClaimCharges_ID);

	/** Get EXME_ClaimCharges_ID	  */
	public int getEXME_ClaimCharges_ID();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name ListDetails */
    public static final String COLUMNNAME_ListDetails = "ListDetails";

	/** Set List Details.
	  * List document details
	  */
	public void setListDetails (String ListDetails);

	/** Get List Details.
	  * List document details
	  */
	public String getListDetails();

    /** Column name Non_Covered_Charges */
    public static final String COLUMNNAME_Non_Covered_Charges = "Non_Covered_Charges";

	/** Set Non_Covered_Charges	  */
	public void setNon_Covered_Charges (BigDecimal Non_Covered_Charges);

	/** Get Non_Covered_Charges	  */
	public BigDecimal getNon_Covered_Charges();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name RevenueCode */
    public static final String COLUMNNAME_RevenueCode = "RevenueCode";

	/** Set Revenue Code.
	  * Revenue Code
	  */
	public void setRevenueCode (String RevenueCode);

	/** Get Revenue Code.
	  * Revenue Code
	  */
	public String getRevenueCode();
}
