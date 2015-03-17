/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Equipo
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Equipo 
{

    /** TableName=EXME_Equipo */
    public static final String Table_Name = "EXME_Equipo";

    /** AD_Table_ID=1000044 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public I_A_Asset getA_Asset() throws RuntimeException;

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

    /** Column name CantTotal */
    public static final String COLUMNNAME_CantTotal = "CantTotal";

	/** Set Total Quantity.
	  * Total Quantity
	  */
	public void setCantTotal (int CantTotal);

	/** Get Total Quantity.
	  * Total Quantity
	  */
	public int getCantTotal();

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

    /** Column name Disponible */
    public static final String COLUMNNAME_Disponible = "Disponible";

	/** Set Available.
	  * Available
	  */
	public void setDisponible (int Disponible);

	/** Get Available.
	  * Available
	  */
	public int getDisponible();

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Area.
	  * Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Area.
	  * Area
	  */
	public int getEXME_Area_ID();

    /** Column name EXME_Equipo_ID */
    public static final String COLUMNNAME_EXME_Equipo_ID = "EXME_Equipo_ID";

	/** Set Equipment.
	  * Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID);

	/** Get Equipment.
	  * Equipment
	  */
	public int getEXME_Equipo_ID();

    /** Column name Fijo */
    public static final String COLUMNNAME_Fijo = "Fijo";

	/** Set Fixed.
	  * Fixed
	  */
	public void setFijo (boolean Fijo);

	/** Get Fixed.
	  * Fixed
	  */
	public boolean isFijo();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

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

    /** Column name Todos */
    public static final String COLUMNNAME_Todos = "Todos";

	/** Set Generate Equipment for Surgery Room.
	  * Generate equipment for surgery room
	  */
	public void setTodos (boolean Todos);

	/** Get Generate Equipment for Surgery Room.
	  * Generate equipment for surgery room
	  */
	public boolean isTodos();

    /** Column name Ubicacion */
    public static final String COLUMNNAME_Ubicacion = "Ubicacion";

	/** Set Location of Element.
	  * Location of Element
	  */
	public void setUbicacion (String Ubicacion);

	/** Get Location of Element.
	  * Location of Element
	  */
	public String getUbicacion();

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
