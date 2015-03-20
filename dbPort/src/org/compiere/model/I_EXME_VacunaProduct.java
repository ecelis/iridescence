/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_VacunaProduct
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_VacunaProduct 
{

    /** TableName=EXME_VacunaProduct */
    public static final String Table_Name = "EXME_VacunaProduct";

    /** AD_Table_ID=1201085 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - All 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name EXME_Vacuna_ID */
    public static final String COLUMNNAME_EXME_Vacuna_ID = "EXME_Vacuna_ID";

	/** Set Vaccine.
	  * Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID);

	/** Get Vaccine.
	  * Vaccine
	  */
	public int getEXME_Vacuna_ID();

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException;

    /** Column name EXME_VacunaProduct_ID */
    public static final String COLUMNNAME_EXME_VacunaProduct_ID = "EXME_VacunaProduct_ID";

	/** Set Vaccine-Product Relationship.
	  * Describes the Vaccine-Product relationship
	  */
	public void setEXME_VacunaProduct_ID (int EXME_VacunaProduct_ID);

	/** Get Vaccine-Product Relationship.
	  * Describes the Vaccine-Product relationship
	  */
	public int getEXME_VacunaProduct_ID();

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
}
