/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InvoiceRel
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_InvoiceRel 
{

    /** TableName=EXME_InvoiceRel */
    public static final String Table_Name = "EXME_InvoiceRel";

    /** AD_Table_ID=1201399 */
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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name C_Conversion_Rate_ID */
    public static final String COLUMNNAME_C_Conversion_Rate_ID = "C_Conversion_Rate_ID";

	/** Set Conversion Rate.
	  * Rate used for converting currencies
	  */
	public void setC_Conversion_Rate_ID (int C_Conversion_Rate_ID);

	/** Get Conversion Rate.
	  * Rate used for converting currencies
	  */
	public int getC_Conversion_Rate_ID();

	public I_C_Conversion_Rate getC_Conversion_Rate() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name EXME_InvoiceRel_ID */
    public static final String COLUMNNAME_EXME_InvoiceRel_ID = "EXME_InvoiceRel_ID";

	/** Set Relation Invoice - Notes.
	  * Relation Invoice - Notes
	  */
	public void setEXME_InvoiceRel_ID (int EXME_InvoiceRel_ID);

	/** Get Relation Invoice - Notes.
	  * Relation Invoice - Notes
	  */
	public int getEXME_InvoiceRel_ID();

    /** Column name Ref_Conversion_Rate_ID */
    public static final String COLUMNNAME_Ref_Conversion_Rate_ID = "Ref_Conversion_Rate_ID";

	/** Set Ref_Conversion_Rate	  */
	public void setRef_Conversion_Rate_ID (int Ref_Conversion_Rate_ID);

	/** Get Ref_Conversion_Rate	  */
	public int getRef_Conversion_Rate_ID();

    /** Column name Ref_Invoice_ID */
    public static final String COLUMNNAME_Ref_Invoice_ID = "Ref_Invoice_ID";

	/** Set Referenced Invoice	  */
	public void setRef_Invoice_ID (int Ref_Invoice_ID);

	/** Get Referenced Invoice	  */
	public int getRef_Invoice_ID();

    /** Column name TrxType */
    public static final String COLUMNNAME_TrxType = "TrxType";

	/** Set Transaction Type.
	  * Type of  transaction 
	  */
	public void setTrxType (String TrxType);

	/** Get Transaction Type.
	  * Type of  transaction 
	  */
	public String getTrxType();
}
