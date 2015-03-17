/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Habitacion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Habitacion 
{

    /** TableName=EXME_Habitacion */
    public static final String Table_Name = "EXME_Habitacion";

    /** AD_Table_ID=1000083 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (boolean Estatus);

	/** Get Status.
	  * Status
	  */
	public boolean isEstatus();

    /** Column name EXME_CDiario_ID */
    public static final String COLUMNNAME_EXME_CDiario_ID = "EXME_CDiario_ID";

	/** Set Daily Charges.
	  * Daily Charges
	  */
	public void setEXME_CDiario_ID (int EXME_CDiario_ID);

	/** Get Daily Charges.
	  * Daily Charges
	  */
	public int getEXME_CDiario_ID();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Unit.
	  * Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Unit.
	  * Service Unit
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_Habitacion_ID */
    public static final String COLUMNNAME_EXME_Habitacion_ID = "EXME_Habitacion_ID";

	/** Set Room.
	  * Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID);

	/** Get Room.
	  * Room
	  */
	public int getEXME_Habitacion_ID();

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

    /** Column name TipoLimpieza */
    public static final String COLUMNNAME_TipoLimpieza = "TipoLimpieza";

	/** Set CleanningType.
	  * Cleanning Typeto realize
	  */
	public void setTipoLimpieza (String TipoLimpieza);

	/** Get CleanningType.
	  * Cleanning Typeto realize
	  */
	public String getTipoLimpieza();

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
