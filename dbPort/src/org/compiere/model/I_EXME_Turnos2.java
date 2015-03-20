/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Turnos2
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Turnos2 
{

    /** TableName=EXME_Turnos2 */
    public static final String Table_Name = "EXME_Turnos2";

    /** AD_Table_ID=1200342 */
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

    /** Column name Dom_Entrada */
    public static final String COLUMNNAME_Dom_Entrada = "Dom_Entrada";

	/** Set Sunday`s Check-In	  */
	public void setDom_Entrada (Timestamp Dom_Entrada);

	/** Get Sunday`s Check-In	  */
	public Timestamp getDom_Entrada();

    /** Column name Dom_Salida */
    public static final String COLUMNNAME_Dom_Salida = "Dom_Salida";

	/** Set Sunday`s Check-Out	  */
	public void setDom_Salida (Timestamp Dom_Salida);

	/** Get Sunday`s Check-Out	  */
	public Timestamp getDom_Salida();

    /** Column name EXME_Turnos2_ID */
    public static final String COLUMNNAME_EXME_Turnos2_ID = "EXME_Turnos2_ID";

	/** Set Warehouse Shifts 2	  */
	public void setEXME_Turnos2_ID (int EXME_Turnos2_ID);

	/** Get Warehouse Shifts 2	  */
	public int getEXME_Turnos2_ID();

    /** Column name Jue_Entrada */
    public static final String COLUMNNAME_Jue_Entrada = "Jue_Entrada";

	/** Set Thursday´s Check-In	  */
	public void setJue_Entrada (Timestamp Jue_Entrada);

	/** Get Thursday´s Check-In	  */
	public Timestamp getJue_Entrada();

    /** Column name Jue_Salida */
    public static final String COLUMNNAME_Jue_Salida = "Jue_Salida";

	/** Set Thursday´s Check-Out	  */
	public void setJue_Salida (Timestamp Jue_Salida);

	/** Get Thursday´s Check-Out	  */
	public Timestamp getJue_Salida();

    /** Column name Lun_Entrada */
    public static final String COLUMNNAME_Lun_Entrada = "Lun_Entrada";

	/** Set Monday's Check-In	  */
	public void setLun_Entrada (Timestamp Lun_Entrada);

	/** Get Monday's Check-In	  */
	public Timestamp getLun_Entrada();

    /** Column name Lun_Salida */
    public static final String COLUMNNAME_Lun_Salida = "Lun_Salida";

	/** Set Monday´s Check-Out	  */
	public void setLun_Salida (Timestamp Lun_Salida);

	/** Get Monday´s Check-Out	  */
	public Timestamp getLun_Salida();

    /** Column name Mar_Entrada */
    public static final String COLUMNNAME_Mar_Entrada = "Mar_Entrada";

	/** Set Tuesday's Ceck-In	  */
	public void setMar_Entrada (Timestamp Mar_Entrada);

	/** Get Tuesday's Ceck-In	  */
	public Timestamp getMar_Entrada();

    /** Column name Mar_Salida */
    public static final String COLUMNNAME_Mar_Salida = "Mar_Salida";

	/** Set Tuesday's Check-Out	  */
	public void setMar_Salida (Timestamp Mar_Salida);

	/** Get Tuesday's Check-Out	  */
	public Timestamp getMar_Salida();

    /** Column name Mie_Entrada */
    public static final String COLUMNNAME_Mie_Entrada = "Mie_Entrada";

	/** Set Wednesday's Check-In	  */
	public void setMie_Entrada (Timestamp Mie_Entrada);

	/** Get Wednesday's Check-In	  */
	public Timestamp getMie_Entrada();

    /** Column name Mie_Salida */
    public static final String COLUMNNAME_Mie_Salida = "Mie_Salida";

	/** Set Wednesday's Check-Out	  */
	public void setMie_Salida (Timestamp Mie_Salida);

	/** Get Wednesday's Check-Out	  */
	public Timestamp getMie_Salida();

    /** Column name Sab_Entrada */
    public static final String COLUMNNAME_Sab_Entrada = "Sab_Entrada";

	/** Set Saturday's Check-In	  */
	public void setSab_Entrada (Timestamp Sab_Entrada);

	/** Get Saturday's Check-In	  */
	public Timestamp getSab_Entrada();

    /** Column name Sab_Salida */
    public static final String COLUMNNAME_Sab_Salida = "Sab_Salida";

	/** Set Saturday's Check-Out	  */
	public void setSab_Salida (Timestamp Sab_Salida);

	/** Get Saturday's Check-Out	  */
	public Timestamp getSab_Salida();

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

    /** Column name Vie_Entrada */
    public static final String COLUMNNAME_Vie_Entrada = "Vie_Entrada";

	/** Set Friday's Check-In	  */
	public void setVie_Entrada (Timestamp Vie_Entrada);

	/** Get Friday's Check-In	  */
	public Timestamp getVie_Entrada();

    /** Column name Vie_Salida */
    public static final String COLUMNNAME_Vie_Salida = "Vie_Salida";

	/** Set Friday's Check-Out	  */
	public void setVie_Salida (Timestamp Vie_Salida);

	/** Get Friday's Check-Out	  */
	public Timestamp getVie_Salida();
}
