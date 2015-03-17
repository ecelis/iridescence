/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FormaPago
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_FormaPago 
{

    /** TableName=EXME_FormaPago */
    public static final String Table_Name = "EXME_FormaPago";

    /** AD_Table_ID=1000030 */
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

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EsDevol */
    public static final String COLUMNNAME_EsDevol = "EsDevol";

	/** Set IsReturn.
	  * Indicates if the form of payment is (isn`t) return
	  */
	public void setEsDevol (boolean EsDevol);

	/** Get IsReturn.
	  * Indicates if the form of payment is (isn`t) return
	  */
	public boolean isEsDevol();

    /** Column name EXME_FormaPago_ID */
    public static final String COLUMNNAME_EXME_FormaPago_ID = "EXME_FormaPago_ID";

	/** Set Payment Form.
	  * Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID);

	/** Get Payment Form.
	  * Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name PosteaCaja */
    public static final String COLUMNNAME_PosteaCaja = "PosteaCaja";

	/** Set Post in Cash book.
	  * Indicates if post in cash book
	  */
	public void setPosteaCaja (boolean PosteaCaja);

	/** Get Post in Cash book.
	  * Indicates if post in cash book
	  */
	public boolean isPosteaCaja();

    /** Column name Ref_FormaPago_ID */
    public static final String COLUMNNAME_Ref_FormaPago_ID = "Ref_FormaPago_ID";

	/** Set Ref. Payment Form.
	  * if payment form is a return, this field says to which payment form is associated this return.
	  */
	public void setRef_FormaPago_ID (int Ref_FormaPago_ID);

	/** Get Ref. Payment Form.
	  * if payment form is a return, this field says to which payment form is associated this return.
	  */
	public int getRef_FormaPago_ID();

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
