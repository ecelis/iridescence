/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AudListaPrecio
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_AudListaPrecio 
{

    /** TableName=EXME_AudListaPrecio */
    public static final String Table_Name = "EXME_AudListaPrecio";

    /** AD_Table_ID=1200304 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name EXME_AudListaPrecio_ID */
    public static final String COLUMNNAME_EXME_AudListaPrecio_ID = "EXME_AudListaPrecio_ID";

	/** Set Audit Price List	  */
	public void setEXME_AudListaPrecio_ID (int EXME_AudListaPrecio_ID);

	/** Get Audit Price List	  */
	public int getEXME_AudListaPrecio_ID();

    /** Column name EXME_ConfigEC_ID */
    public static final String COLUMNNAME_EXME_ConfigEC_ID = "EXME_ConfigEC_ID";

	/** Set EMR Configuration.
	  * EMR Configuration
	  */
	public void setEXME_ConfigEC_ID (int EXME_ConfigEC_ID);

	/** Get EMR Configuration.
	  * EMR Configuration
	  */
	public int getEXME_ConfigEC_ID();

	public I_EXME_ConfigEC getEXME_ConfigEC() throws RuntimeException;

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

    /** Column name PrecioAnterior */
    public static final String COLUMNNAME_PrecioAnterior = "PrecioAnterior";

	/** Set Old Price	  */
	public void setPrecioAnterior (BigDecimal PrecioAnterior);

	/** Get Old Price	  */
	public BigDecimal getPrecioAnterior();

    /** Column name PrecioNuevo */
    public static final String COLUMNNAME_PrecioNuevo = "PrecioNuevo";

	/** Set New Price	  */
	public void setPrecioNuevo (BigDecimal PrecioNuevo);

	/** Get New Price	  */
	public BigDecimal getPrecioNuevo();
}
