/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacPag
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CtaPacPag 
{

    /** TableName=EXME_CtaPacPag */
    public static final String Table_Name = "EXME_CtaPacPag";

    /** AD_Table_ID=1000171 */
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

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

    /** Column name Disponible */
    public static final String COLUMNNAME_Disponible = "Disponible";

	/** Set Available.
	  * Available
	  */
	public void setDisponible (BigDecimal Disponible);

	/** Get Available.
	  * Available
	  */
	public BigDecimal getDisponible();

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Encounter Extension.
	  * Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Encounter Extension.
	  * Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID();

    /** Column name EXME_CtaPacPag_ID */
    public static final String COLUMNNAME_EXME_CtaPacPag_ID = "EXME_CtaPacPag_ID";

	/** Set Encounter Payment.
	  * Indentifier of patient acct payment
	  */
	public void setEXME_CtaPacPag_ID (int EXME_CtaPacPag_ID);

	/** Get Encounter Payment.
	  * Indentifier of patient acct payment
	  */
	public int getEXME_CtaPacPag_ID();

    /** Column name IsPay */
    public static final String COLUMNNAME_IsPay = "IsPay";

	/** Set Pay.
	  * Registry is pay
	  */
	public void setIsPay (boolean IsPay);

	/** Get Pay.
	  * Registry is pay
	  */
	public boolean isPay();

    /** Column name SeDevolvio */
    public static final String COLUMNNAME_SeDevolvio = "SeDevolvio";

	/** Set Was Returned	  */
	public void setSeDevolvio (boolean SeDevolvio);

	/** Get Was Returned	  */
	public boolean isSeDevolvio();
}
