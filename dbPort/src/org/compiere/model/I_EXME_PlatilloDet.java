/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PlatilloDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PlatilloDet 
{

    /** TableName=EXME_PlatilloDet */
    public static final String Table_Name = "EXME_PlatilloDet";

    /** AD_Table_ID=1200595 */
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

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

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

    /** Column name EXME_PlatilloDet_ID */
    public static final String COLUMNNAME_EXME_PlatilloDet_ID = "EXME_PlatilloDet_ID";

	/** Set Saucer Detail	  */
	public void setEXME_PlatilloDet_ID (int EXME_PlatilloDet_ID);

	/** Get Saucer Detail	  */
	public int getEXME_PlatilloDet_ID();

    /** Column name EXME_PlatilloHdr_ID */
    public static final String COLUMNNAME_EXME_PlatilloHdr_ID = "EXME_PlatilloHdr_ID";

	/** Set Saucer	  */
	public void setEXME_PlatilloHdr_ID (int EXME_PlatilloHdr_ID);

	/** Get Saucer	  */
	public int getEXME_PlatilloHdr_ID();

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
