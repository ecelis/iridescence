/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigEsterilizacion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigEsterilizacion 
{

    /** TableName=EXME_ConfigEsterilizacion */
    public static final String Table_Name = "EXME_ConfigEsterilizacion";

    /** AD_Table_ID=1201242 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
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

    /** Column name EXME_ConfigEsterilizacion_ID */
    public static final String COLUMNNAME_EXME_ConfigEsterilizacion_ID = "EXME_ConfigEsterilizacion_ID";

	/** Set EXME_ConfigEsterilizacion_ID	  */
	public void setEXME_ConfigEsterilizacion_ID (int EXME_ConfigEsterilizacion_ID);

	/** Get EXME_ConfigEsterilizacion_ID	  */
	public int getEXME_ConfigEsterilizacion_ID();

    /** Column name EXME_TipoProd_ID */
    public static final String COLUMNNAME_EXME_TipoProd_ID = "EXME_TipoProd_ID";

	/** Set Product Subtype.
	  * Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID);

	/** Get Product Subtype.
	  * Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID();

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException;

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

    /** Column name ProcedureType */
    public static final String COLUMNNAME_ProcedureType = "ProcedureType";

	/** Set Procedure Type.
	  * Procedure Type
	  */
	public void setProcedureType (String ProcedureType);

	/** Get Procedure Type.
	  * Procedure Type
	  */
	public String getProcedureType();

    /** Column name ProductClass */
    public static final String COLUMNNAME_ProductClass = "ProductClass";

	/** Set Product Class	  */
	public void setProductClass (String ProductClass);

	/** Get Product Class	  */
	public String getProductClass();

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
