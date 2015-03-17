/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Product_Cte
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_Product_Cte 
{

    /** TableName=M_Product_Cte */
    public static final String Table_Name = "M_Product_Cte";

    /** AD_Table_ID=1201112 */
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

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public I_C_Charge getC_Charge() throws RuntimeException;

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

    /** Column name EXME_ConceptoFac_ID */
    public static final String COLUMNNAME_EXME_ConceptoFac_ID = "EXME_ConceptoFac_ID";

	/** Set Invoice Concept	  */
	public void setEXME_ConceptoFac_ID (int EXME_ConceptoFac_ID);

	/** Get Invoice Concept	  */
	public int getEXME_ConceptoFac_ID();

	public I_EXME_ConceptoFac getEXME_ConceptoFac() throws RuntimeException;

    /** Column name EXME_FactorPre_ID */
    public static final String COLUMNNAME_EXME_FactorPre_ID = "EXME_FactorPre_ID";

	/** Set Price Factor.
	  * Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID);

	/** Get Price Factor.
	  * Sales Price Factor
	  */
	public int getEXME_FactorPre_ID();

	public I_EXME_FactorPre getEXME_FactorPre() throws RuntimeException;

    /** Column name IsLot */
    public static final String COLUMNNAME_IsLot = "IsLot";

	/** Set Lot.
	  * The product instances have a Lot Number
	  */
	public void setIsLot (boolean IsLot);

	/** Get Lot.
	  * The product instances have a Lot Number
	  */
	public boolean isLot();

    /** Column name M_Product_Cte_ID */
    public static final String COLUMNNAME_M_Product_Cte_ID = "M_Product_Cte_ID";

	/** Set Client Product.
	  * Client Product
	  */
	public void setM_Product_Cte_ID (int M_Product_Cte_ID);

	/** Get Client Product.
	  * Client Product
	  */
	public int getM_Product_Cte_ID();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;
}
