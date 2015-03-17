/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Asset_LoanDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Asset_LoanDet 
{

    /** TableName=EXME_Asset_LoanDet */
    public static final String Table_Name = "EXME_Asset_LoanDet";

    /** AD_Table_ID=1200855 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public I_A_Asset getA_Asset() throws RuntimeException;

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

    /** Column name AstrayDate */
    public static final String COLUMNNAME_AstrayDate = "AstrayDate";

	/** Set Astray Date.
	  * Set the date on which the borrowed material was lost
	  */
	public void setAstrayDate (Timestamp AstrayDate);

	/** Get Astray Date.
	  * Set the date on which the borrowed material was lost
	  */
	public Timestamp getAstrayDate();

    /** Column name CommentaryLoan */
    public static final String COLUMNNAME_CommentaryLoan = "CommentaryLoan";

	/** Set Commentary Loan.
	  * It provides a commentary regarding the loan
	  */
	public void setCommentaryLoan (String CommentaryLoan);

	/** Get Commentary Loan.
	  * It provides a commentary regarding the loan
	  */
	public String getCommentaryLoan();

    /** Column name CommentaryReturned */
    public static final String COLUMNNAME_CommentaryReturned = "CommentaryReturned";

	/** Set Commentary Returned.
	  * It provides a commentary to make the return for
	  */
	public void setCommentaryReturned (String CommentaryReturned);

	/** Get Commentary Returned.
	  * It provides a commentary to make the return for
	  */
	public String getCommentaryReturned();

    /** Column name EffectiveDate */
    public static final String COLUMNNAME_EffectiveDate = "EffectiveDate";

	/** Set Effective Date.
	  * It establishes the effective date of the loan
	  */
	public void setEffectiveDate (Timestamp EffectiveDate);

	/** Get Effective Date.
	  * It establishes the effective date of the loan
	  */
	public Timestamp getEffectiveDate();

    /** Column name EXME_Asset_LoanDet_ID */
    public static final String COLUMNNAME_EXME_Asset_LoanDet_ID = "EXME_Asset_LoanDet_ID";

	/** Set Asset Loan detail.
	  * Contain details of the request for material made
	  */
	public void setEXME_Asset_LoanDet_ID (int EXME_Asset_LoanDet_ID);

	/** Get Asset Loan detail.
	  * Contain details of the request for material made
	  */
	public int getEXME_Asset_LoanDet_ID();

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

	public I_EXME_Asset_Loan getEXME_Asset_Loan() throws RuntimeException;

    /** Column name IsReturned */
    public static final String COLUMNNAME_IsReturned = "IsReturned";

	/** Set Returned	  */
	public void setIsReturned (boolean IsReturned);

	/** Get Returned	  */
	public boolean isReturned();

    /** Column name IsStrayed */
    public static final String COLUMNNAME_IsStrayed = "IsStrayed";

	/** Set Strayed.
	  * Defines whether the material reported missing
	  */
	public void setIsStrayed (boolean IsStrayed);

	/** Get Strayed.
	  * Defines whether the material reported missing
	  */
	public boolean isStrayed();

    /** Column name ReturnDate */
    public static final String COLUMNNAME_ReturnDate = "ReturnDate";

	/** Set Return Date.
	  * It sets a return date when material is returned
	  */
	public void setReturnDate (Timestamp ReturnDate);

	/** Get Return Date.
	  * It sets a return date when material is returned
	  */
	public Timestamp getReturnDate();
}
