/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EquipoHab
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EquipoHab 
{

    /** TableName=EXME_EquipoHab */
    public static final String Table_Name = "EXME_EquipoHab";

    /** AD_Table_ID=1200337 */
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

    /** Column name Estatus_Equipo */
    public static final String COLUMNNAME_Estatus_Equipo = "Estatus_Equipo";

	/** Set Equipment Status	  */
	public void setEstatus_Equipo (String Estatus_Equipo);

	/** Get Equipment Status	  */
	public String getEstatus_Equipo();

    /** Column name EXME_EquipoHab_ID */
    public static final String COLUMNNAME_EXME_EquipoHab_ID = "EXME_EquipoHab_ID";

	/** Set Room Equipment	  */
	public void setEXME_EquipoHab_ID (int EXME_EquipoHab_ID);

	/** Get Room Equipment	  */
	public int getEXME_EquipoHab_ID();

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

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFecha_Fin();

    /** Column name Fecha_Ini */
    public static final String COLUMNNAME_Fecha_Ini = "Fecha_Ini";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFecha_Ini();
}
