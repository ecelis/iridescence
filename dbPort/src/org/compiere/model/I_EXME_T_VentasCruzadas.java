/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_VentasCruzadas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_VentasCruzadas 
{

    /** TableName=EXME_T_VentasCruzadas */
    public static final String Table_Name = "EXME_T_VentasCruzadas";

    /** AD_Table_ID=1200251 */
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

    /** Column name CentroCosto1Amt */
    public static final String COLUMNNAME_CentroCosto1Amt = "CentroCosto1Amt";

	/** Set Profit Center Amount 1.
	  * Profit Center Amount 1
	  */
	public void setCentroCosto1Amt (BigDecimal CentroCosto1Amt);

	/** Get Profit Center Amount 1.
	  * Profit Center Amount 1
	  */
	public BigDecimal getCentroCosto1Amt();

    /** Column name CentroCosto2Amt */
    public static final String COLUMNNAME_CentroCosto2Amt = "CentroCosto2Amt";

	/** Set Profit Center Amount 2.
	  * Profit Center Amount 2
	  */
	public void setCentroCosto2Amt (BigDecimal CentroCosto2Amt);

	/** Get Profit Center Amount 2.
	  * Profit Center Amount 2
	  */
	public BigDecimal getCentroCosto2Amt();

    /** Column name CentroCosto3Amt */
    public static final String COLUMNNAME_CentroCosto3Amt = "CentroCosto3Amt";

	/** Set Profit Center Amount 3	  */
	public void setCentroCosto3Amt (BigDecimal CentroCosto3Amt);

	/** Get Profit Center Amount 3	  */
	public BigDecimal getCentroCosto3Amt();

    /** Column name CentroCosto4Amt */
    public static final String COLUMNNAME_CentroCosto4Amt = "CentroCosto4Amt";

	/** Set Profit Center Amount 4	  */
	public void setCentroCosto4Amt (BigDecimal CentroCosto4Amt);

	/** Get Profit Center Amount 4	  */
	public BigDecimal getCentroCosto4Amt();

    /** Column name CentroCosto5Amt */
    public static final String COLUMNNAME_CentroCosto5Amt = "CentroCosto5Amt";

	/** Set Profit Center Amount 5	  */
	public void setCentroCosto5Amt (BigDecimal CentroCosto5Amt);

	/** Get Profit Center Amount 5	  */
	public BigDecimal getCentroCosto5Amt();

    /** Column name CentroCosto6Amt */
    public static final String COLUMNNAME_CentroCosto6Amt = "CentroCosto6Amt";

	/** Set Profit Center Amount 6	  */
	public void setCentroCosto6Amt (BigDecimal CentroCosto6Amt);

	/** Get Profit Center Amount 6	  */
	public BigDecimal getCentroCosto6Amt();

    /** Column name CentroCosto7Amt */
    public static final String COLUMNNAME_CentroCosto7Amt = "CentroCosto7Amt";

	/** Set Profit Center Amount 7	  */
	public void setCentroCosto7Amt (BigDecimal CentroCosto7Amt);

	/** Get Profit Center Amount 7	  */
	public BigDecimal getCentroCosto7Amt();

    /** Column name CentroCosto8Amt */
    public static final String COLUMNNAME_CentroCosto8Amt = "CentroCosto8Amt";

	/** Set Profit Center Amount 8	  */
	public void setCentroCosto8Amt (BigDecimal CentroCosto8Amt);

	/** Get Profit Center Amount 8	  */
	public BigDecimal getCentroCosto8Amt();

    /** Column name EXME_CentroCosto_ID */
    public static final String COLUMNNAME_EXME_CentroCosto_ID = "EXME_CentroCosto_ID";

	/** Set Cost Center	  */
	public void setEXME_CentroCosto_ID (int EXME_CentroCosto_ID);

	/** Get Cost Center	  */
	public int getEXME_CentroCosto_ID();

    /** Column name EXME_T_VentasCruzadas_ID */
    public static final String COLUMNNAME_EXME_T_VentasCruzadas_ID = "EXME_T_VentasCruzadas_ID";

	/** Set Cross-Selling	  */
	public void setEXME_T_VentasCruzadas_ID (int EXME_T_VentasCruzadas_ID);

	/** Get Cross-Selling	  */
	public int getEXME_T_VentasCruzadas_ID();
}
