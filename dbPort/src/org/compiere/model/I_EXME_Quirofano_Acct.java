/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Quirofano_Acct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Quirofano_Acct 
{

    /** TableName=EXME_Quirofano_Acct */
    public static final String Table_Name = "EXME_Quirofano_Acct";

    /** AD_Table_ID=1000031 */
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

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name EXME_Quirofano_ID */
    public static final String COLUMNNAME_EXME_Quirofano_ID = "EXME_Quirofano_ID";

	/** Set Surgery Room.
	  * Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID);

	/** Get Surgery Room.
	  * Surgey Room
	  */
	public int getEXME_Quirofano_ID();

	public I_EXME_Quirofano getEXME_Quirofano() throws RuntimeException;

    /** Column name Q_LimpReal_Acct */
    public static final String COLUMNNAME_Q_LimpReal_Acct = "Q_LimpReal_Acct";

	/** Set Cleaning Account.
	  * Cleaning Account
	  */
	public void setQ_LimpReal_Acct (int Q_LimpReal_Acct);

	/** Get Cleaning Account.
	  * Cleaning Account
	  */
	public int getQ_LimpReal_Acct();

    /** Column name Q_PrepReal_Acct */
    public static final String COLUMNNAME_Q_PrepReal_Acct = "Q_PrepReal_Acct";

	/** Set Preparation Account.
	  * Preparation account
	  */
	public void setQ_PrepReal_Acct (int Q_PrepReal_Acct);

	/** Get Preparation Account.
	  * Preparation account
	  */
	public int getQ_PrepReal_Acct();

    /** Column name Q_UsoReal_Acct */
    public static final String COLUMNNAME_Q_UsoReal_Acct = "Q_UsoReal_Acct";

	/** Set Use Account.
	  * use Account
	  */
	public void setQ_UsoReal_Acct (int Q_UsoReal_Acct);

	/** Get Use Account.
	  * use Account
	  */
	public int getQ_UsoReal_Acct();
}
