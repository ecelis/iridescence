/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EquipoH
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EquipoH 
{

    /** TableName=EXME_EquipoH */
    public static final String Table_Name = "EXME_EquipoH";

    /** AD_Table_ID=1200339 */
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

    /** Column name EXME_ActPacienteIndH_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndH_ID = "EXME_ActPacienteIndH_ID";

	/** Set Patient's Indication.
	  * Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID);

	/** Get Patient's Indication.
	  * Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID();

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException;

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Service.
	  * Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Service.
	  * Service
	  */
	public int getEXME_Area_ID();

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_EquipoH_ID */
    public static final String COLUMNNAME_EXME_EquipoH_ID = "EXME_EquipoH_ID";

	/** Set Equipment History	  */
	public void setEXME_EquipoH_ID (int EXME_EquipoH_ID);

	/** Get Equipment History	  */
	public int getEXME_EquipoH_ID();

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

	public I_EXME_Equipo getEXME_Equipo() throws RuntimeException;

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

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException;

    /** Column name EXME_MotivoCita_ID */
    public static final String COLUMNNAME_EXME_MotivoCita_ID = "EXME_MotivoCita_ID";

	/** Set Motive of appointment.
	  * Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID);

	/** Get Motive of appointment.
	  * Motive of appointment
	  */
	public int getEXME_MotivoCita_ID();

	public I_EXME_MotivoCita getEXME_MotivoCita() throws RuntimeException;

    /** Column name EXME_ProgQuiro_ID */
    public static final String COLUMNNAME_EXME_ProgQuiro_ID = "EXME_ProgQuiro_ID";

	/** Set Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID);

	/** Get Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID();

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException;

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

	public I_EXME_Quirofano getEXME_Quirofano() throws RuntimeException;

    /** Column name Estatus_Equipo */
    public static final String COLUMNNAME_Estatus_Equipo = "Estatus_Equipo";

	/** Set Equipment Status	  */
	public void setEstatus_Equipo (String Estatus_Equipo);

	/** Get Equipment Status	  */
	public String getEstatus_Equipo();

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
}
