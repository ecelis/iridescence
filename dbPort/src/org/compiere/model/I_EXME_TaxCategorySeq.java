/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TaxCategorySeq
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_TaxCategorySeq 
{

    /** TableName=EXME_TaxCategorySeq */
    public static final String Table_Name = "EXME_TaxCategorySeq";

    /** AD_Table_ID=1201573 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name Base_Calculation */
    public static final String COLUMNNAME_Base_Calculation = "Base_Calculation";

	/** Set Base_Calculation	  */
	public void setBase_Calculation (int Base_Calculation);

	/** Get Base_Calculation	  */
	public int getBase_Calculation();

    /** Column name Buy_Sell */
    public static final String COLUMNNAME_Buy_Sell = "Buy_Sell";

	/** Set Buy_Sell	  */
	public void setBuy_Sell (String Buy_Sell);

	/** Get Buy_Sell	  */
	public String getBuy_Sell();

    /** Column name C_TaxCategory_ID */
    public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";

	/** Set Tax Category.
	  * Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID);

	/** Get Tax Category.
	  * Tax Category
	  */
	public int getC_TaxCategory_ID();

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException;

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name EXME_TaxCategorySeq_ID */
    public static final String COLUMNNAME_EXME_TaxCategorySeq_ID = "EXME_TaxCategorySeq_ID";

	/** Set Tax sequence	  */
	public void setEXME_TaxCategorySeq_ID (int EXME_TaxCategorySeq_ID);

	/** Get Tax sequence	  */
	public int getEXME_TaxCategorySeq_ID();

    /** Column name Ref_TaxCategorySeq_ID */
    public static final String COLUMNNAME_Ref_TaxCategorySeq_ID = "Ref_TaxCategorySeq_ID";

	/** Set Base of calculation	  */
	public void setRef_TaxCategorySeq_ID (int Ref_TaxCategorySeq_ID);

	/** Get Base of calculation	  */
	public int getRef_TaxCategorySeq_ID();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();

    /** Column name SOPOType */
    public static final String COLUMNNAME_SOPOType = "SOPOType";

	/** Set SO/PO Type.
	  * Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public void setSOPOType (String SOPOType);

	/** Get SO/PO Type.
	  * Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public String getSOPOType();
}
