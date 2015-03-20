/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EvalPiel
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EvalPiel 
{

    /** TableName=EXME_EvalPiel */
    public static final String Table_Name = "EXME_EvalPiel";

    /** AD_Table_ID=1200498 */
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

    /** Column name Act_Motriz */
    public static final String COLUMNNAME_Act_Motriz = "Act_Motriz";

	/** Set Motor Activity	  */
	public void setAct_Motriz (String Act_Motriz);

	/** Get Motor Activity	  */
	public String getAct_Motriz();

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

    /** Column name EXME_EdoConciencia_ID */
    public static final String COLUMNNAME_EXME_EdoConciencia_ID = "EXME_EdoConciencia_ID";

	/** Set Conscience	  */
	public void setEXME_EdoConciencia_ID (int EXME_EdoConciencia_ID);

	/** Get Conscience	  */
	public int getEXME_EdoConciencia_ID();

	public I_EXME_EdoConciencia getEXME_EdoConciencia() throws RuntimeException;

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

    /** Column name EXME_EvalPiel_ID */
    public static final String COLUMNNAME_EXME_EvalPiel_ID = "EXME_EvalPiel_ID";

	/** Set Skin Evaluation	  */
	public void setEXME_EvalPiel_ID (int EXME_EvalPiel_ID);

	/** Get Skin Evaluation	  */
	public int getEXME_EvalPiel_ID();

    /** Column name EdoNutricion */
    public static final String COLUMNNAME_EdoNutricion = "EdoNutricion";

	/** Set Nutricional Level	  */
	public void setEdoNutricion (String EdoNutricion);

	/** Get Nutricional Level	  */
	public String getEdoNutricion();

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

    /** Column name Incontinencia */
    public static final String COLUMNNAME_Incontinencia = "Incontinencia";

	/** Set Incontinence	  */
	public void setIncontinencia (String Incontinencia);

	/** Get Incontinence	  */
	public String getIncontinencia();

    /** Column name Movilidad */
    public static final String COLUMNNAME_Movilidad = "Movilidad";

	/** Set Mobility	  */
	public void setMovilidad (String Movilidad);

	/** Get Mobility	  */
	public String getMovilidad();
}
