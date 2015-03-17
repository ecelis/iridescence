/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Quirofano
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Quirofano 
{

    /** TableName=EXME_Quirofano */
    public static final String Table_Name = "EXME_Quirofano";

    /** AD_Table_ID=1000052 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Color_ID */
    public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";

	/** Set System Color.
	  * Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID);

	/** Get System Color.
	  * Color for backgrounds or indicators
	  */
	public int getAD_Color_ID();

	public I_AD_Color getAD_Color() throws RuntimeException;

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

    /** Column name Disponible */
    public static final String COLUMNNAME_Disponible = "Disponible";

	/** Set Available.
	  * Available
	  */
	public void setDisponible (boolean Disponible);

	/** Get Available.
	  * Available
	  */
	public boolean isDisponible();

    /** Column name DispQuirFin */
    public static final String COLUMNNAME_DispQuirFin = "DispQuirFin";

	/** Set Available To.
	  * Available To
	  */
	public void setDispQuirFin (Timestamp DispQuirFin);

	/** Get Available To.
	  * Available To
	  */
	public Timestamp getDispQuirFin();

    /** Column name DispQuirIni */
    public static final String COLUMNNAME_DispQuirIni = "DispQuirIni";

	/** Set Available Since.
	  * Available Since
	  */
	public void setDispQuirIni (Timestamp DispQuirIni);

	/** Get Available Since.
	  * Available Since
	  */
	public Timestamp getDispQuirIni();

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_Quirofano_ID */
    public static final String COLUMNNAME_EXME_Quirofano_ID = "EXME_Quirofano_ID";

	/** Set Surgery Room.
	  * Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID);

	/** Get Surgery Room.
	  * Surgey Room
	  */
	public int getEXME_Quirofano_ID();

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (int Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public int getIntervalo();

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

    /** Column name RespQuirofano */
    public static final String COLUMNNAME_RespQuirofano = "RespQuirofano";

	/** Set Responsible.
	  * Responsible of surgery room
	  */
	public void setRespQuirofano (int RespQuirofano);

	/** Get Responsible.
	  * Responsible of surgery room
	  */
	public int getRespQuirofano();

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
