/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Sobreprecio
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Sobreprecio 
{

    /** TableName=EXME_Sobreprecio */
    public static final String Table_Name = "EXME_Sobreprecio";

    /** AD_Table_ID=1000124 */
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

    /** Column name EXME_Sobreprecio_ID */
    public static final String COLUMNNAME_EXME_Sobreprecio_ID = "EXME_Sobreprecio_ID";

	/** Set Overprice.
	  * Overprice
	  */
	public void setEXME_Sobreprecio_ID (int EXME_Sobreprecio_ID);

	/** Get Overprice.
	  * Overprice
	  */
	public int getEXME_Sobreprecio_ID();

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

    /** Column name M_WarehouseSur_ID */
    public static final String COLUMNNAME_M_WarehouseSur_ID = "M_WarehouseSur_ID";

	/** Set Completed Warehouse.
	  * Completed warehouse
	  */
	public void setM_WarehouseSur_ID (int M_WarehouseSur_ID);

	/** Get Completed Warehouse.
	  * Completed warehouse
	  */
	public int getM_WarehouseSur_ID();

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

    /** Column name Porcentaje */
    public static final String COLUMNNAME_Porcentaje = "Porcentaje";

	/** Set Percentage.
	  * percentage
	  */
	public void setPorcentaje (int Porcentaje);

	/** Get Percentage.
	  * percentage
	  */
	public int getPorcentaje();

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
