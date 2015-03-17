/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DH_BalanceServices
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_DH_BalanceServices 
{

    /** TableName=DH_BalanceServices */
    public static final String Table_Name = "DH_BalanceServices";

    /** AD_Table_ID=1201565 */
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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (int Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public int getAmount();

    /** Column name DH_BalanceServices_ID */
    public static final String COLUMNNAME_DH_BalanceServices_ID = "DH_BalanceServices_ID";

	/** Set Patient services / packages balance	  */
	public void setDH_BalanceServices_ID (int DH_BalanceServices_ID);

	/** Get Patient services / packages balance	  */
	public int getDH_BalanceServices_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

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
	public void setRemainingAmt (int RemainingAmt);

	/** Get Remaining Amt.
	  * Remaining Amount
	  */
	public int getRemainingAmt();
}
