/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_SaldoCtaPacDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_SaldoCtaPacDet 
{

    /** TableName=EXME_T_SaldoCtaPacDet */
    public static final String Table_Name = "EXME_T_SaldoCtaPacDet";

    /** AD_Table_ID=1200550 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name EXME_T_SaldoCtaPacDet_ID */
    public static final String COLUMNNAME_EXME_T_SaldoCtaPacDet_ID = "EXME_T_SaldoCtaPacDet_ID";

	/** Set Temporal Balance Details of the Patient's Account.
	  * Temporal Balance Details of the Patient's Account
	  */
	public void setEXME_T_SaldoCtaPacDet_ID (int EXME_T_SaldoCtaPacDet_ID);

	/** Get Temporal Balance Details of the Patient's Account.
	  * Temporal Balance Details of the Patient's Account
	  */
	public int getEXME_T_SaldoCtaPacDet_ID();

    /** Column name EXME_T_SaldoCtaPac_ID */
    public static final String COLUMNNAME_EXME_T_SaldoCtaPac_ID = "EXME_T_SaldoCtaPac_ID";

	/** Set Patient Account Balance.
	  * The Balance of the Patient Account
	  */
	public void setEXME_T_SaldoCtaPac_ID (int EXME_T_SaldoCtaPac_ID);

	/** Get Patient Account Balance.
	  * The Balance of the Patient Account
	  */
	public int getEXME_T_SaldoCtaPac_ID();

	public I_EXME_T_SaldoCtaPac getEXME_T_SaldoCtaPac() throws RuntimeException;

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

    /** Column name Saldo */
    public static final String COLUMNNAME_Saldo = "Saldo";

	/** Set Balance.
	  * Balance of Patient Account
	  */
	public void setSaldo (BigDecimal Saldo);

	/** Get Balance.
	  * Balance of Patient Account
	  */
	public BigDecimal getSaldo();
}
