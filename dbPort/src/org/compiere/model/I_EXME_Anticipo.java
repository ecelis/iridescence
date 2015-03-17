/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Anticipo
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Anticipo 
{

    /** TableName=EXME_Anticipo */
    public static final String Table_Name = "EXME_Anticipo";

    /** AD_Table_ID=1200351 */
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

    /** Column name Aplicado */
    public static final String COLUMNNAME_Aplicado = "Aplicado";

	/** Set Applied Amount.
	  * Applied Amount
	  */
	public void setAplicado (BigDecimal Aplicado);

	/** Get Applied Amount.
	  * Applied Amount
	  */
	public BigDecimal getAplicado();

    /** Column name EXME_Anticipo_ID */
    public static final String COLUMNNAME_EXME_Anticipo_ID = "EXME_Anticipo_ID";

	/** Set Prepayment	  */
	public void setEXME_Anticipo_ID (int EXME_Anticipo_ID);

	/** Get Prepayment	  */
	public int getEXME_Anticipo_ID();

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Patient Account Extension.
	  * Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Patient Account Extension.
	  * Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID();

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

    /** Column name Total */
    public static final String COLUMNNAME_Total = "Total";

	/** Set Total	  */
	public void setTotal (BigDecimal Total);

	/** Get Total	  */
	public BigDecimal getTotal();
}
