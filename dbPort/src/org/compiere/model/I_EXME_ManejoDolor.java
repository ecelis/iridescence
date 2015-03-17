/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ManejoDolor
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ManejoDolor 
{

    /** TableName=EXME_ManejoDolor */
    public static final String Table_Name = "EXME_ManejoDolor";

    /** AD_Table_ID=1200512 */
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

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (String Duracion);

	/** Get Duration.
	  * Duration
	  */
	public String getDuracion();

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

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException;

    /** Column name EXME_EncounterForms_ID */
    public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
	  * Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID);

	/** Get Encounter Forms.
	  * Encounter Forms
	  */
	public int getEXME_EncounterForms_ID();

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException;

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name EXME_ManejoDolor_ID */
    public static final String COLUMNNAME_EXME_ManejoDolor_ID = "EXME_ManejoDolor_ID";

	/** Set Paint Control	  */
	public void setEXME_ManejoDolor_ID (int EXME_ManejoDolor_ID);

	/** Get Paint Control	  */
	public int getEXME_ManejoDolor_ID();

    /** Column name EXME_ParteCorporal_ID */
    public static final String COLUMNNAME_EXME_ParteCorporal_ID = "EXME_ParteCorporal_ID";

	/** Set Body part	  */
	public void setEXME_ParteCorporal_ID (int EXME_ParteCorporal_ID);

	/** Get Body part	  */
	public int getEXME_ParteCorporal_ID();

	public I_EXME_ParteCorporal getEXME_ParteCorporal() throws RuntimeException;

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name Intensidad */
    public static final String COLUMNNAME_Intensidad = "Intensidad";

	/** Set Intensity	  */
	public void setIntensidad (String Intensidad);

	/** Get Intensity	  */
	public String getIntensidad();

    /** Column name IntensidadGrafica */
    public static final String COLUMNNAME_IntensidadGrafica = "IntensidadGrafica";

	/** Set Intensity Graph	  */
	public void setIntensidadGrafica (String IntensidadGrafica);

	/** Get Intensity Graph	  */
	public String getIntensidadGrafica();

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

    /** Column name Tipo_Dolor */
    public static final String COLUMNNAME_Tipo_Dolor = "Tipo_Dolor";

	/** Set Pain Type	  */
	public void setTipo_Dolor (String Tipo_Dolor);

	/** Get Pain Type	  */
	public String getTipo_Dolor();
}
