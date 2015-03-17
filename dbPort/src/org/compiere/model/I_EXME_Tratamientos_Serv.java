/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tratamientos_Serv
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tratamientos_Serv 
{

    /** TableName=EXME_Tratamientos_Serv */
    public static final String Table_Name = "EXME_Tratamientos_Serv";

    /** AD_Table_ID=1201103 */
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

    /** Column name Almacen_Surt_ID */
    public static final String COLUMNNAME_Almacen_Surt_ID = "Almacen_Surt_ID";

	/** Set Warehouse that Applies_ID	  */
	public void setAlmacen_Surt_ID (int Almacen_Surt_ID);

	/** Get Warehouse that Applies_ID	  */
	public int getAlmacen_Surt_ID();

    /** Column name EXME_Tratamientos_Detalle_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Detalle_ID = "EXME_Tratamientos_Detalle_ID";

	/** Set Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID);

	/** Get Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID();

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException;

    /** Column name EXME_Tratamientos_Serv_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Serv_ID = "EXME_Tratamientos_Serv_ID";

	/** Set Treatment Services	  */
	public void setEXME_Tratamientos_Serv_ID (int EXME_Tratamientos_Serv_ID);

	/** Get Treatment Services	  */
	public int getEXME_Tratamientos_Serv_ID();

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

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

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
}
