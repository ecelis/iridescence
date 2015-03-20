/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SaldoPaquete
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_SaldoPaquete 
{

    /** TableName=EXME_SaldoPaquete */
    public static final String Table_Name = "EXME_SaldoPaquete";

    /** AD_Table_ID=1201392 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

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

    /** Column name EXME_SaldoPaquete_ID */
    public static final String COLUMNNAME_EXME_SaldoPaquete_ID = "EXME_SaldoPaquete_ID";

	/** Set Package Balance	  */
	public void setEXME_SaldoPaquete_ID (int EXME_SaldoPaquete_ID);

	/** Get Package Balance	  */
	public int getEXME_SaldoPaquete_ID();

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

    /** Column name RemainingAmt */
    public static final String COLUMNNAME_RemainingAmt = "RemainingAmt";

	/** Set Remaining Amt.
	  * Remaining Amount
	  */
	public void setRemainingAmt (BigDecimal RemainingAmt);

	/** Get Remaining Amt.
	  * Remaining Amount
	  */
	public BigDecimal getRemainingAmt();

    /** Column name Sustituto_ID */
    public static final String COLUMNNAME_Sustituto_ID = "Sustituto_ID";

	/** Set Substitute	  */
	public void setSustituto_ID (int Sustituto_ID);

	/** Get Substitute	  */
	public int getSustituto_ID();

    /** Column name UOM_Substitute_ID */
    public static final String COLUMNNAME_UOM_Substitute_ID = "UOM_Substitute_ID";

	/** Set UOM Substitute	  */
	public void setUOM_Substitute_ID (int UOM_Substitute_ID);

	/** Get UOM Substitute	  */
	public int getUOM_Substitute_ID();
}
