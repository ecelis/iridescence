/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BillingRuleDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_BillingRuleDet 
{

    /** TableName=EXME_BillingRuleDet */
    public static final String Table_Name = "EXME_BillingRuleDet";

    /** AD_Table_ID=1201304 */
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

    /** Column name ComparisonValue */
    public static final String COLUMNNAME_ComparisonValue = "ComparisonValue";

	/** Set Comparison Value	  */
	public void setComparisonValue (String ComparisonValue);

	/** Get Comparison Value	  */
	public String getComparisonValue();

    /** Column name EXME_BillingFilter_ID */
    public static final String COLUMNNAME_EXME_BillingFilter_ID = "EXME_BillingFilter_ID";

	/** Set Billing Rule Filter 	  */
	public void setEXME_BillingFilter_ID (int EXME_BillingFilter_ID);

	/** Get Billing Rule Filter 	  */
	public int getEXME_BillingFilter_ID();

	public I_EXME_BillingFilter getEXME_BillingFilter() throws RuntimeException;

    /** Column name EXME_BillingRuleDet_ID */
    public static final String COLUMNNAME_EXME_BillingRuleDet_ID = "EXME_BillingRuleDet_ID";

	/** Set Billing Rule Detail	  */
	public void setEXME_BillingRuleDet_ID (int EXME_BillingRuleDet_ID);

	/** Get Billing Rule Detail	  */
	public int getEXME_BillingRuleDet_ID();

    /** Column name EXME_BillingRule_ID */
    public static final String COLUMNNAME_EXME_BillingRule_ID = "EXME_BillingRule_ID";

	/** Set Billing Rule.
	  * Billing Rule
	  */
	public void setEXME_BillingRule_ID (int EXME_BillingRule_ID);

	/** Get Billing Rule.
	  * Billing Rule
	  */
	public int getEXME_BillingRule_ID();

	public I_EXME_BillingRule getEXME_BillingRule() throws RuntimeException;

    /** Column name IEOperator */
    public static final String COLUMNNAME_IEOperator = "IEOperator";

	/** Set I/E Operator.
	  * Include / Exclude Operator
	  */
	public void setIEOperator (String IEOperator);

	/** Get I/E Operator.
	  * Include / Exclude Operator
	  */
	public String getIEOperator();

    /** Column name IEValue */
    public static final String COLUMNNAME_IEValue = "IEValue";

	/** Set I/E Value.
	  * Include / Exclude Value
	  */
	public void setIEValue (String IEValue);

	/** Get I/E Value.
	  * Include / Exclude Value
	  */
	public String getIEValue();

    /** Column name Operation */
    public static final String COLUMNNAME_Operation = "Operation";

	/** Set Operation.
	  * Compare Operation
	  */
	public void setOperation (String Operation);

	/** Get Operation.
	  * Compare Operation
	  */
	public String getOperation();

    /** Column name ParameterValue */
    public static final String COLUMNNAME_ParameterValue = "ParameterValue";

	/** Set Parameter Value	  */
	public void setParameterValue (String ParameterValue);

	/** Get Parameter Value	  */
	public String getParameterValue();

    /** Column name ValorFin */
    public static final String COLUMNNAME_ValorFin = "ValorFin";

	/** Set Final Value	  */
	public void setValorFin (String ValorFin);

	/** Get Final Value	  */
	public String getValorFin();

    /** Column name ValorIni */
    public static final String COLUMNNAME_ValorIni = "ValorIni";

	/** Set Initial Value	  */
	public void setValorIni (String ValorIni);

	/** Get Initial Value	  */
	public String getValorIni();

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
