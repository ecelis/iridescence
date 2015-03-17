/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoHabitacion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_TipoHabitacion 
{

    /** TableName=EXME_TipoHabitacion */
    public static final String Table_Name = "EXME_TipoHabitacion";

    /** AD_Table_ID=1000029 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name ChargesDaily */
    public static final String COLUMNNAME_ChargesDaily = "ChargesDaily";

	/** Set Charges Daily.
	  * Charges Daily
	  */
	public void setChargesDaily (boolean ChargesDaily);

	/** Get Charges Daily.
	  * Charges Daily
	  */
	public boolean isChargesDaily();

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

    /** Column name EXME_TipoHabitacion_ID */
    public static final String COLUMNNAME_EXME_TipoHabitacion_ID = "EXME_TipoHabitacion_ID";

	/** Set Type of Room.
	  * Type of Room
	  */
	public void setEXME_TipoHabitacion_ID (int EXME_TipoHabitacion_ID);

	/** Get Type of Room.
	  * Type of Room
	  */
	public int getEXME_TipoHabitacion_ID();

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
