/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Asset_Loan
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Asset_Loan 
{

    /** TableName=EXME_Asset_Loan */
    public static final String Table_Name = "EXME_Asset_Loan";

    /** AD_Table_ID=1200854 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name CaptureDate */
    public static final String COLUMNNAME_CaptureDate = "CaptureDate";

	/** Set Capture Date.
	  * Is set to capture date of the loan application
	  */
	public void setCaptureDate (Timestamp CaptureDate);

	/** Get Capture Date.
	  * Is set to capture date of the loan application
	  */
	public Timestamp getCaptureDate();

    /** Column name EXME_Asset_Loan_ID */
    public static final String COLUMNNAME_EXME_Asset_Loan_ID = "EXME_Asset_Loan_ID";

	/** Set Asset Loan.
	  * Loan applications are recorded bibliographic material and periodicals library
	  */
	public void setEXME_Asset_Loan_ID (int EXME_Asset_Loan_ID);

	/** Get Asset Loan.
	  * Loan applications are recorded bibliographic material and periodicals library
	  */
	public int getEXME_Asset_Loan_ID();

    /** Column name IsClosed */
    public static final String COLUMNNAME_IsClosed = "IsClosed";

	/** Set Closed Status.
	  * The status is closed
	  */
	public void setIsClosed (boolean IsClosed);

	/** Get Closed Status.
	  * The status is closed
	  */
	public boolean isClosed();

    /** Column name LoanDate */
    public static final String COLUMNNAME_LoanDate = "LoanDate";

	/** Set Loan Date.
	  * Establishing a loan application date
	  */
	public void setLoanDate (Timestamp LoanDate);

	/** Get Loan Date.
	  * Establishing a loan application date
	  */
	public Timestamp getLoanDate();
}
