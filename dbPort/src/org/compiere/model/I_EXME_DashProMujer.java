/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DashProMujer
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_DashProMujer 
{

    /** TableName=EXME_DashProMujer */
    public static final String Table_Name = "EXME_DashProMujer";

    /** AD_Table_ID=1201569 */
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

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name Apellido1 */
    public static final String COLUMNNAME_Apellido1 = "Apellido1";

	/** Set Last Name.
	  * Last Name
	  */
	public void setApellido1 (String Apellido1);

	/** Get Last Name.
	  * Last Name
	  */
	public String getApellido1();

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public I_C_Country getC_Country() throws RuntimeException;

    /** Column name Curp */
    public static final String COLUMNNAME_Curp = "Curp";

	/** Set National Identification Number.
	  * National Identification Number
	  */
	public void setCurp (String Curp);

	/** Get National Identification Number.
	  * National Identification Number
	  */
	public String getCurp();

    /** Column name EXME_DashProMujer_ID */
    public static final String COLUMNNAME_EXME_DashProMujer_ID = "EXME_DashProMujer_ID";

	/** Set Dashboard for ProMujer Transactional Messages	  */
	public void setEXME_DashProMujer_ID (int EXME_DashProMujer_ID);

	/** Get Dashboard for ProMujer Transactional Messages	  */
	public int getEXME_DashProMujer_ID();

    /** Column name Process */
    public static final String COLUMNNAME_Process = "Process";

	/** Set Process Name	  */
	public void setProcess (String Process);

	/** Get Process Name	  */
	public String getProcess();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name StatusCode */
    public static final String COLUMNNAME_StatusCode = "StatusCode";

	/** Set Status Code	  */
	public void setStatusCode (String StatusCode);

	/** Get Status Code	  */
	public String getStatusCode();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();

    /** Column name TransactionCode */
    public static final String COLUMNNAME_TransactionCode = "TransactionCode";

	/** Set Transaction Code.
	  * The transaction code represents the search definition
	  */
	public void setTransactionCode (String TransactionCode);

	/** Get Transaction Code.
	  * The transaction code represents the search definition
	  */
	public String getTransactionCode();
}
