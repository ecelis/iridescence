/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ValCorporal
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ValCorporal 
{

    /** TableName=EXME_ValCorporal */
    public static final String Table_Name = "EXME_ValCorporal";

    /** AD_Table_ID=1200504 */
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

    /** Column name Deshidratacion */
    public static final String COLUMNNAME_Deshidratacion = "Deshidratacion";

	/** Set Dehydration	  */
	public void setDeshidratacion (String Deshidratacion);

	/** Get Dehydration	  */
	public String getDeshidratacion();

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

    /** Column name EXME_ValCorporal_ID */
    public static final String COLUMNNAME_EXME_ValCorporal_ID = "EXME_ValCorporal_ID";

	/** Set Body Valuation	  */
	public void setEXME_ValCorporal_ID (int EXME_ValCorporal_ID);

	/** Get Body Valuation	  */
	public int getEXME_ValCorporal_ID();

    /** Column name Edema */
    public static final String COLUMNNAME_Edema = "Edema";

	/** Set Edema.
	  * Edema
	  */
	public void setEdema (String Edema);

	/** Get Edema.
	  * Edema
	  */
	public String getEdema();

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

    /** Column name Humedad */
    public static final String COLUMNNAME_Humedad = "Humedad";

	/** Set Humidity	  */
	public void setHumedad (String Humedad);

	/** Get Humidity	  */
	public String getHumedad();

    /** Column name Llen_Capilar */
    public static final String COLUMNNAME_Llen_Capilar = "Llen_Capilar";

	/** Set Capillary refill time	  */
	public void setLlen_Capilar (String Llen_Capilar);

	/** Get Capillary refill time	  */
	public String getLlen_Capilar();

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
}
