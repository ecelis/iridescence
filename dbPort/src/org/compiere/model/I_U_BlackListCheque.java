/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for U_BlackListCheque
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_U_BlackListCheque 
{

    /** TableName=U_BlackListCheque */
    public static final String Table_Name = "U_BlackListCheque";

    /** AD_Table_ID=1200808 */
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

    /** Column name BankName */
    public static final String COLUMNNAME_BankName = "BankName";

	/** Set Bank Name	  */
	public void setBankName (String BankName);

	/** Get Bank Name	  */
	public String getBankName();

    /** Column name ChequeNo */
    public static final String COLUMNNAME_ChequeNo = "ChequeNo";

	/** Set Cheque No	  */
	public void setChequeNo (String ChequeNo);

	/** Get Cheque No	  */
	public String getChequeNo();

    /** Column name U_BlackListCheque_ID */
    public static final String COLUMNNAME_U_BlackListCheque_ID = "U_BlackListCheque_ID";

	/** Set Black List Cheque	  */
	public void setU_BlackListCheque_ID (int U_BlackListCheque_ID);

	/** Get Black List Cheque	  */
	public int getU_BlackListCheque_ID();
}
