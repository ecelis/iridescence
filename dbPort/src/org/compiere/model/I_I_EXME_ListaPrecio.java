/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_ListaPrecio
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_I_EXME_ListaPrecio 
{

    /** TableName=I_EXME_ListaPrecio */
    public static final String Table_Name = "I_EXME_ListaPrecio";

    /** AD_Table_ID=1200303 */
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

    /** Column name C_UOM_Name */
    public static final String COLUMNNAME_C_UOM_Name = "C_UOM_Name";

	/** Set UOM Name	  */
	public void setC_UOM_Name (String C_UOM_Name);

	/** Get UOM Name	  */
	public String getC_UOM_Name();

    /** Column name C_UOMVolume_ID */
    public static final String COLUMNNAME_C_UOMVolume_ID = "C_UOMVolume_ID";

	/** Set Pack UOM.
	  * Unit of measure of volume or packing
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID);

	/** Get Pack UOM.
	  * Unit of measure of volume or packing
	  */
	public int getC_UOMVolume_ID();

    /** Column name Fecha_Carga */
    public static final String COLUMNNAME_Fecha_Carga = "Fecha_Carga";

	/** Set Load Date	  */
	public void setFecha_Carga (Timestamp Fecha_Carga);

	/** Get Load Date	  */
	public Timestamp getFecha_Carga();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_ListaPrecio_ID */
    public static final String COLUMNNAME_I_EXME_ListaPrecio_ID = "I_EXME_ListaPrecio_ID";

	/** Set Price List	  */
	public void setI_EXME_ListaPrecio_ID (int I_EXME_ListaPrecio_ID);

	/** Get Price List	  */
	public int getI_EXME_ListaPrecio_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_PriceList_Name */
    public static final String COLUMNNAME_M_PriceList_Name = "M_PriceList_Name";

	/** Set Name Price List.
	  * Name Price List
	  */
	public void setM_PriceList_Name (String M_PriceList_Name);

	/** Get Name Price List.
	  * Name Price List
	  */
	public String getM_PriceList_Name();

    /** Column name M_PriceList_Version_ID */
    public static final String COLUMNNAME_M_PriceList_Version_ID = "M_PriceList_Version_ID";

	/** Set Price List Version.
	  * Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID);

	/** Get Price List Version.
	  * Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID();

	public I_M_PriceList_Version getM_PriceList_Version() throws RuntimeException;

    /** Column name M_PriceList_Version_Name */
    public static final String COLUMNNAME_M_PriceList_Version_Name = "M_PriceList_Version_Name";

	/** Set Price List Version Name	  */
	public void setM_PriceList_Version_Name (String M_PriceList_Version_Name);

	/** Get Price List Version Name	  */
	public String getM_PriceList_Version_Name();

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

    /** Column name M_Product_Value */
    public static final String COLUMNNAME_M_Product_Value = "M_Product_Value";

	/** Set Product Code.
	  * product search Code
	  */
	public void setM_Product_Value (String M_Product_Value);

	/** Get Product Code.
	  * product search Code
	  */
	public String getM_Product_Value();

    /** Column name Precio */
    public static final String COLUMNNAME_Precio = "Precio";

	/** Set Price	  */
	public void setPrecio (BigDecimal Precio);

	/** Get Price	  */
	public BigDecimal getPrecio();

    /** Column name PriceLimit_Vol */
    public static final String COLUMNNAME_PriceLimit_Vol = "PriceLimit_Vol";

	/** Set Pack Limit Price.
	  * Limit Price for the Pack UOM
	  */
	public void setPriceLimit_Vol (BigDecimal PriceLimit_Vol);

	/** Get Pack Limit Price.
	  * Limit Price for the Pack UOM
	  */
	public BigDecimal getPriceLimit_Vol();

    /** Column name PriceList_Vol */
    public static final String COLUMNNAME_PriceList_Vol = "PriceList_Vol";

	/** Set Pack Price List.
	  * Price List for the Pack UOM
	  */
	public void setPriceList_Vol (BigDecimal PriceList_Vol);

	/** Get Pack Price List.
	  * Price List for the Pack UOM
	  */
	public BigDecimal getPriceList_Vol();

    /** Column name PriceStd_Vol */
    public static final String COLUMNNAME_PriceStd_Vol = "PriceStd_Vol";

	/** Set Pack Standard Price.
	  * Standard Price for the Pack UOM
	  */
	public void setPriceStd_Vol (BigDecimal PriceStd_Vol);

	/** Get Pack Standard Price.
	  * Standard Price for the Pack UOM
	  */
	public BigDecimal getPriceStd_Vol();

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

    /** Column name ProductDescription */
    public static final String COLUMNNAME_ProductDescription = "ProductDescription";

	/** Set Product Description.
	  * Product Description
	  */
	public void setProductDescription (String ProductDescription);

	/** Get Product Description.
	  * Product Description
	  */
	public String getProductDescription();

    /** Column name ProductValue */
    public static final String COLUMNNAME_ProductValue = "ProductValue";

	/** Set Product Key.
	  * Key of the Product
	  */
	public void setProductValue (String ProductValue);

	/** Get Product Key.
	  * Key of the Product
	  */
	public String getProductValue();
}
