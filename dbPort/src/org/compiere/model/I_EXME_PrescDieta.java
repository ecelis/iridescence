/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrescDieta
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PrescDieta 
{

    /** TableName=EXME_PrescDieta */
    public static final String Table_Name = "EXME_PrescDieta";

    /** AD_Table_ID=1200484 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name ADAT */
    public static final String COLUMNNAME_ADAT = "ADAT";

	/** Set ADAT.
	  * Advance Diet As Tolerated
	  */
	public void setADAT (boolean ADAT);

	/** Get ADAT.
	  * Advance Diet As Tolerated
	  */
	public boolean isADAT();

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

    /** Column name Authenticated */
    public static final String COLUMNNAME_Authenticated = "Authenticated";

	/** Set Authenticated	  */
	public void setAuthenticated (boolean Authenticated);

	/** Get Authenticated	  */
	public boolean isAuthenticated();

    /** Column name AuthenticatedBy */
    public static final String COLUMNNAME_AuthenticatedBy = "AuthenticatedBy";

	/** Set Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy);

	/** Get Authenticated By	  */
	public int getAuthenticatedBy();

    /** Column name Authenticated_Date */
    public static final String COLUMNNAME_Authenticated_Date = "Authenticated_Date";

	/** Set Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date);

	/** Get Authentication Date	  */
	public Timestamp getAuthenticated_Date();

    /** Column name CanceledBy */
    public static final String COLUMNNAME_CanceledBy = "CanceledBy";

	/** Set Canceled By.
	  * Canceled By
	  */
	public void setCanceledBy (int CanceledBy);

	/** Get Canceled By.
	  * Canceled By
	  */
	public int getCanceledBy();

    /** Column name DiscontinuedDate */
    public static final String COLUMNNAME_DiscontinuedDate = "DiscontinuedDate";

	/** Set Discontinued Date	  */
	public void setDiscontinuedDate (Timestamp DiscontinuedDate);

	/** Get Discontinued Date	  */
	public Timestamp getDiscontinuedDate();

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

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_PrescDieta_ID */
    public static final String COLUMNNAME_EXME_PrescDieta_ID = "EXME_PrescDieta_ID";

	/** Set Diet Prescription.
	  * Diet Prescription
	  */
	public void setEXME_PrescDieta_ID (int EXME_PrescDieta_ID);

	/** Get Diet Prescription.
	  * Diet Prescription
	  */
	public int getEXME_PrescDieta_ID();

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaInicio */
    public static final String COLUMNNAME_FechaInicio = "FechaInicio";

	/** Set Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio);

	/** Get Beginning Date	  */
	public Timestamp getFechaInicio();

    /** Column name MotivoCancel */
    public static final String COLUMNNAME_MotivoCancel = "MotivoCancel";

	/** Set Cancel Reason.
	  * Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel);

	/** Get Cancel Reason.
	  * Cancel Reason
	  */
	public String getMotivoCancel();

    /** Column name NotedBy */
    public static final String COLUMNNAME_NotedBy = "NotedBy";

	/** Set Noted By	  */
	public void setNotedBy (int NotedBy);

	/** Get Noted By	  */
	public int getNotedBy();

    /** Column name NotedDate */
    public static final String COLUMNNAME_NotedDate = "NotedDate";

	/** Set Noted Date	  */
	public void setNotedDate (Timestamp NotedDate);

	/** Get Noted Date	  */
	public Timestamp getNotedDate();

    /** Column name Observacion */
    public static final String COLUMNNAME_Observacion = "Observacion";

	/** Set Observation.
	  * Observation
	  */
	public void setObservacion (String Observacion);

	/** Get Observation.
	  * Observation
	  */
	public String getObservacion();

    /** Column name OrderType */
    public static final String COLUMNNAME_OrderType = "OrderType";

	/** Set OrderType	  */
	public void setOrderType (String OrderType);

	/** Get OrderType	  */
	public String getOrderType();

    /** Column name ReadBack */
    public static final String COLUMNNAME_ReadBack = "ReadBack";

	/** Set Read Back.
	  * Read Back
	  */
	public void setReadBack (String ReadBack);

	/** Get Read Back.
	  * Read Back
	  */
	public String getReadBack();

    /** Column name Vigente */
    public static final String COLUMNNAME_Vigente = "Vigente";

	/** Set Valid	  */
	public void setVigente (boolean Vigente);

	/** Get Valid	  */
	public boolean isVigente();
}
