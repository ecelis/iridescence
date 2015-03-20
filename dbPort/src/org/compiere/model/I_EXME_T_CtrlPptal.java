/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_CtrlPptal
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_CtrlPptal 
{

    /** TableName=EXME_T_CtrlPptal */
    public static final String Table_Name = "EXME_T_CtrlPptal";

    /** AD_Table_ID=1200029 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public I_C_ElementValue getC_ElementValue() throws RuntimeException;

    /** Column name Comp_Acum */
    public static final String COLUMNNAME_Comp_Acum = "Comp_Acum";

	/** Set Commitment (Accrued).
	  * Commitment (Accrued)
	  */
	public void setComp_Acum (BigDecimal Comp_Acum);

	/** Get Commitment (Accrued).
	  * Commitment (Accrued)
	  */
	public BigDecimal getComp_Acum();

    /** Column name Comp_Per */
    public static final String COLUMNNAME_Comp_Per = "Comp_Per";

	/** Set Commitment (Period).
	  * Commitment (Period)
	  */
	public void setComp_Per (BigDecimal Comp_Per);

	/** Get Commitment (Period).
	  * Commitment (Period)
	  */
	public BigDecimal getComp_Per();

    /** Column name Ejer_Acum */
    public static final String COLUMNNAME_Ejer_Acum = "Ejer_Acum";

	/** Set Done (Accumulated).
	  * Done (Accumulated)
	  */
	public void setEjer_Acum (BigDecimal Ejer_Acum);

	/** Get Done (Accumulated).
	  * Done (Accumulated)
	  */
	public BigDecimal getEjer_Acum();

    /** Column name Ejer_Per */
    public static final String COLUMNNAME_Ejer_Per = "Ejer_Per";

	/** Set Done (Period).
	  * Done (Period)
	  */
	public void setEjer_Per (BigDecimal Ejer_Per);

	/** Get Done (Period).
	  * Done (Period)
	  */
	public BigDecimal getEjer_Per();

    /** Column name EXME_T_CtrlPptal_ID */
    public static final String COLUMNNAME_EXME_T_CtrlPptal_ID = "EXME_T_CtrlPptal_ID";

	/** Set Report of Budgetary Control.
	  * Report of Budgetary Control
	  */
	public void setEXME_T_CtrlPptal_ID (int EXME_T_CtrlPptal_ID);

	/** Get Report of Budgetary Control.
	  * Report of Budgetary Control
	  */
	public int getEXME_T_CtrlPptal_ID();

    /** Column name Ppto_Acum */
    public static final String COLUMNNAME_Ppto_Acum = "Ppto_Acum";

	/** Set Budget (Accumulated).
	  * Budget (Accumulated)
	  */
	public void setPpto_Acum (BigDecimal Ppto_Acum);

	/** Get Budget (Accumulated).
	  * Budget (Accumulated)
	  */
	public BigDecimal getPpto_Acum();

    /** Column name Ppto_Per */
    public static final String COLUMNNAME_Ppto_Per = "Ppto_Per";

	/** Set Budget (Period).
	  * Budget (Period)
	  */
	public void setPpto_Per (BigDecimal Ppto_Per);

	/** Get Budget (Period).
	  * Budget (Period)
	  */
	public BigDecimal getPpto_Per();

    /** Column name Sesion */
    public static final String COLUMNNAME_Sesion = "Sesion";

	/** Set Session.
	  * Session
	  */
	public void setSesion (String Sesion);

	/** Get Session.
	  * Session
	  */
	public String getSesion();

    /** Column name XComp_Acum */
    public static final String COLUMNNAME_XComp_Acum = "XComp_Acum";

	/** Set To Compromise (Accumulated).
	  * To Compromise (Accumulated)
	  */
	public void setXComp_Acum (BigDecimal XComp_Acum);

	/** Get To Compromise (Accumulated).
	  * To Compromise (Accumulated)
	  */
	public BigDecimal getXComp_Acum();

    /** Column name XComp_Per */
    public static final String COLUMNNAME_XComp_Per = "XComp_Per";

	/** Set To Compromise (Period).
	  * To Compromise (Period)
	  */
	public void setXComp_Per (BigDecimal XComp_Per);

	/** Get To Compromise (Period).
	  * To Compromise (Period)
	  */
	public BigDecimal getXComp_Per();

    /** Column name XEjer_Acum */
    public static final String COLUMNNAME_XEjer_Acum = "XEjer_Acum";

	/** Set To Be Spend (Accumulated).
	  * To Be Spend (Accumulated)
	  */
	public void setXEjer_Acum (BigDecimal XEjer_Acum);

	/** Get To Be Spend (Accumulated).
	  * To Be Spend (Accumulated)
	  */
	public BigDecimal getXEjer_Acum();

    /** Column name XEjer_Per */
    public static final String COLUMNNAME_XEjer_Per = "XEjer_Per";

	/** Set To Spend (Period).
	  * To Spend (Period)
	  */
	public void setXEjer_Per (BigDecimal XEjer_Per);

	/** Get To Spend (Period).
	  * To Spend (Period)
	  */
	public BigDecimal getXEjer_Per();
}
