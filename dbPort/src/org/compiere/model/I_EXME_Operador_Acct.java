/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Operador_Acct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Operador_Acct 
{

    /** TableName=EXME_Operador_Acct */
    public static final String Table_Name = "EXME_Operador_Acct";

    /** AD_Table_ID=1000183 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

    /** Column name EXME_Operador_ID */
    public static final String COLUMNNAME_EXME_Operador_ID = "EXME_Operador_ID";

	/** Set Operator.
	  * Operator
	  */
	public void setEXME_Operador_ID (int EXME_Operador_ID);

	/** Get Operator.
	  * Operator
	  */
	public int getEXME_Operador_ID();

    /** Column name O_DeudDiv_Acct */
    public static final String COLUMNNAME_O_DeudDiv_Acct = "O_DeudDiv_Acct";

	/** Set Sundry debtors.
	  * Sundry debtors account for the Operator
	  */
	public void setO_DeudDiv_Acct (int O_DeudDiv_Acct);

	/** Get Sundry debtors.
	  * Sundry debtors account for the Operator
	  */
	public int getO_DeudDiv_Acct();
}
