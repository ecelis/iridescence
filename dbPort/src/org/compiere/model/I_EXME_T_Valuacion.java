/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_Valuacion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_Valuacion 
{

    /** TableName=EXME_T_Valuacion */
    public static final String Table_Name = "EXME_T_Valuacion";

    /** AD_Table_ID=1200308 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name Almacen */
    public static final String COLUMNNAME_Almacen = "Almacen";

	/** Set Warehouse	  */
	public void setAlmacen (String Almacen);

	/** Get Warehouse	  */
	public String getAlmacen();

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

    /** Column name EXME_T_Valuacion_ID */
    public static final String COLUMNNAME_EXME_T_Valuacion_ID = "EXME_T_Valuacion_ID";

	/** Set Valuation	  */
	public void setEXME_T_Valuacion_ID (int EXME_T_Valuacion_ID);

	/** Get Valuation	  */
	public int getEXME_T_Valuacion_ID();

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

    /** Column name Precio */
    public static final String COLUMNNAME_Precio = "Precio";

	/** Set Price	  */
	public void setPrecio (BigDecimal Precio);

	/** Get Price	  */
	public BigDecimal getPrecio();

    /** Column name Total */
    public static final String COLUMNNAME_Total = "Total";

	/** Set Total	  */
	public void setTotal (BigDecimal Total);

	/** Get Total	  */
	public BigDecimal getTotal();

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
