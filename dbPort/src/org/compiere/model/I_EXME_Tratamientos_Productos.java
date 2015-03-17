/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tratamientos_Productos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tratamientos_Productos 
{

    /** TableName=EXME_Tratamientos_Productos */
    public static final String Table_Name = "EXME_Tratamientos_Productos";

    /** AD_Table_ID=1200375 */
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

    /** Column name EXME_Tratamientos_Detalle_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Detalle_ID = "EXME_Tratamientos_Detalle_ID";

	/** Set Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID);

	/** Get Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID();

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException;

    /** Column name EXME_Tratamientos_Productos_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Productos_ID = "EXME_Tratamientos_Productos_ID";

	/** Set Treatments Products	  */
	public void setEXME_Tratamientos_Productos_ID (int EXME_Tratamientos_Productos_ID);

	/** Get Treatments Products	  */
	public int getEXME_Tratamientos_Productos_ID();

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

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name SessionNo */
    public static final String COLUMNNAME_SessionNo = "SessionNo";

	/** Set Session Number.
	  * Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo);

	/** Get Session Number.
	  * Session Number of a treatment
	  */
	public int getSessionNo();

    /** Column name TipoDato */
    public static final String COLUMNNAME_TipoDato = "TipoDato";

	/** Set Data Type.
	  * Data Type
	  */
	public void setTipoDato (String TipoDato);

	/** Get Data Type.
	  * Data Type
	  */
	public String getTipoDato();
}
