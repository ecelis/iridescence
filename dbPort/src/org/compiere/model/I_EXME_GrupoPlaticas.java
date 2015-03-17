/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GrupoPlaticas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_GrupoPlaticas 
{

    /** TableName=EXME_GrupoPlaticas */
    public static final String Table_Name = "EXME_GrupoPlaticas";

    /** AD_Table_ID=1200649 */
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

    /** Column name Asistentes */
    public static final String COLUMNNAME_Asistentes = "Asistentes";

	/** Set Assistants	  */
	public void setAsistentes (int Asistentes);

	/** Get Assistants	  */
	public int getAsistentes();

    /** Column name Conferencista */
    public static final String COLUMNNAME_Conferencista = "Conferencista";

	/** Set Lecturer.
	  * Name of lecturer who teaches the conversation 
	  */
	public void setConferencista (String Conferencista);

	/** Get Lecturer.
	  * Name of lecturer who teaches the conversation 
	  */
	public String getConferencista();

    /** Column name EXME_GrupoPlaticas_ID */
    public static final String COLUMNNAME_EXME_GrupoPlaticas_ID = "EXME_GrupoPlaticas_ID";

	/** Set Chat Group	  */
	public void setEXME_GrupoPlaticas_ID (int EXME_GrupoPlaticas_ID);

	/** Get Chat Group	  */
	public int getEXME_GrupoPlaticas_ID();

    /** Column name EXME_MO_Platicas_ID */
    public static final String COLUMNNAME_EXME_MO_Platicas_ID = "EXME_MO_Platicas_ID";

	/** Set Talk.
	  * Talk  by Speciality
	  */
	public void setEXME_MO_Platicas_ID (int EXME_MO_Platicas_ID);

	/** Get Talk.
	  * Talk  by Speciality
	  */
	public int getEXME_MO_Platicas_ID();

	public I_EXME_MO_Platicas getEXME_MO_Platicas() throws RuntimeException;

    /** Column name FechaHr */
    public static final String COLUMNNAME_FechaHr = "FechaHr";

	/** Set Hr and Date.
	  * Hr and Date
	  */
	public void setFechaHr (Timestamp FechaHr);

	/** Get Hr and Date.
	  * Hr and Date
	  */
	public Timestamp getFechaHr();

    /** Column name PlaticaImpartida */
    public static final String COLUMNNAME_PlaticaImpartida = "PlaticaImpartida";

	/** Set Chat Group Distributed	  */
	public void setPlaticaImpartida (boolean PlaticaImpartida);

	/** Get Chat Group Distributed	  */
	public boolean isPlaticaImpartida();

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
