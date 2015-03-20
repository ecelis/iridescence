/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProductoOrg
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ProductoOrg 
{

    /** TableName=EXME_ProductoOrg */
    public static final String Table_Name = "EXME_ProductoOrg";

    /** AD_Table_ID=1201258 */
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

    /** Column name CambiaPrecio */
    public static final String COLUMNNAME_CambiaPrecio = "CambiaPrecio";

	/** Set Change price	  */
	public void setCambiaPrecio (boolean CambiaPrecio);

	/** Get Change price	  */
	public boolean isCambiaPrecio();

    /** Column name Consigna */
    public static final String COLUMNNAME_Consigna = "Consigna";

	/** Set Is Consignment Warehosue	  */
	public void setConsigna (boolean Consigna);

	/** Get Is Consignment Warehosue	  */
	public boolean isConsigna();

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

    /** Column name EXME_Modifier1_ID */
    public static final String COLUMNNAME_EXME_Modifier1_ID = "EXME_Modifier1_ID";

	/** Set Modifier 1	  */
	public void setEXME_Modifier1_ID (int EXME_Modifier1_ID);

	/** Get Modifier 1	  */
	public int getEXME_Modifier1_ID();

    /** Column name EXME_Modifier2_ID */
    public static final String COLUMNNAME_EXME_Modifier2_ID = "EXME_Modifier2_ID";

	/** Set Modifier 2	  */
	public void setEXME_Modifier2_ID (int EXME_Modifier2_ID);

	/** Get Modifier 2	  */
	public int getEXME_Modifier2_ID();

    /** Column name EXME_Modifier3_ID */
    public static final String COLUMNNAME_EXME_Modifier3_ID = "EXME_Modifier3_ID";

	/** Set Modifier 3	  */
	public void setEXME_Modifier3_ID (int EXME_Modifier3_ID);

	/** Get Modifier 3	  */
	public int getEXME_Modifier3_ID();

    /** Column name EXME_Modifier4_ID */
    public static final String COLUMNNAME_EXME_Modifier4_ID = "EXME_Modifier4_ID";

	/** Set Modifier 4	  */
	public void setEXME_Modifier4_ID (int EXME_Modifier4_ID);

	/** Get Modifier 4	  */
	public int getEXME_Modifier4_ID();

    /** Column name EXME_ProductoOrg_ID */
    public static final String COLUMNNAME_EXME_ProductoOrg_ID = "EXME_ProductoOrg_ID";

	/** Set Producto Org	  */
	public void setEXME_ProductoOrg_ID (int EXME_ProductoOrg_ID);

	/** Get Producto Org	  */
	public int getEXME_ProductoOrg_ID();

    /** Column name EXME_RevenueCode_ID */
    public static final String COLUMNNAME_EXME_RevenueCode_ID = "EXME_RevenueCode_ID";

	/** Set Revenue Code	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID);

	/** Get Revenue Code	  */
	public int getEXME_RevenueCode_ID();

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException;

    /** Column name IsCoveredByInsurance */
    public static final String COLUMNNAME_IsCoveredByInsurance = "IsCoveredByInsurance";

	/** Set Is Covered By Insurance	  */
	public void setIsCoveredByInsurance (boolean IsCoveredByInsurance);

	/** Get Is Covered By Insurance	  */
	public boolean isCoveredByInsurance();

    /** Column name IsFormulary */
    public static final String COLUMNNAME_IsFormulary = "IsFormulary";

	/** Set In formulary.
	  * In formulary
	  */
	public void setIsFormulary (boolean IsFormulary);

	/** Get In formulary.
	  * In formulary
	  */
	public boolean isFormulary();

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

    /** Column name IsObsolete */
    public static final String COLUMNNAME_IsObsolete = "IsObsolete";

	/** Set Is Obsolete.
	  * The product is obsolete
	  */
	public void setIsObsolete (boolean IsObsolete);

	/** Get Is Obsolete.
	  * The product is obsolete
	  */
	public boolean isObsolete();

    /** Column name IsProfessional */
    public static final String COLUMNNAME_IsProfessional = "IsProfessional";

	/** Set Is Professional?	  */
	public void setIsProfessional (boolean IsProfessional);

	/** Get Is Professional?	  */
	public boolean isProfessional();

    /** Column name M_AttributeSet_ID */
    public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";

	/** Set Attribute Set.
	  * Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID);

	/** Get Attribute Set.
	  * Product Attribute Set
	  */
	public int getM_AttributeSet_ID();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

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

    /** Column name M_Product_Rel_ID */
    public static final String COLUMNNAME_M_Product_Rel_ID = "M_Product_Rel_ID";

	/** Set M_Product_Rel_ID	  */
	public void setM_Product_Rel_ID (int M_Product_Rel_ID);

	/** Get M_Product_Rel_ID	  */
	public int getM_Product_Rel_ID();

    /** Column name Unused */
    public static final String COLUMNNAME_Unused = "Unused";

	/** Set Unused	  */
	public void setUnused (boolean Unused);

	/** Get Unused	  */
	public boolean isUnused();
}
