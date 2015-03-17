/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EstiloVidaPaciente
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EstiloVidaPaciente 
{

    /** TableName=EXME_EstiloVidaPaciente */
    public static final String Table_Name = "EXME_EstiloVidaPaciente";

    /** AD_Table_ID=1200868 */
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

    /** Column name EXME_ActPacienteDiag_ID */
    public static final String COLUMNNAME_EXME_ActPacienteDiag_ID = "EXME_ActPacienteDiag_ID";

	/** Set Patient's Diagnostic.
	  * Patient's Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID);

	/** Get Patient's Diagnostic.
	  * Patient's Diagnostic
	  */
	public int getEXME_ActPacienteDiag_ID();

	public I_EXME_ActPacienteDiag getEXME_ActPacienteDiag() throws RuntimeException;

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

    /** Column name EXME_EstiloVidaPaciente_ID */
    public static final String COLUMNNAME_EXME_EstiloVidaPaciente_ID = "EXME_EstiloVidaPaciente_ID";

	/** Set Patient's Life Style	  */
	public void setEXME_EstiloVidaPaciente_ID (int EXME_EstiloVidaPaciente_ID);

	/** Get Patient's Life Style	  */
	public int getEXME_EstiloVidaPaciente_ID();

    /** Column name EXME_EstiloVida_ID */
    public static final String COLUMNNAME_EXME_EstiloVida_ID = "EXME_EstiloVida_ID";

	/** Set Life Style	  */
	public void setEXME_EstiloVida_ID (int EXME_EstiloVida_ID);

	/** Get Life Style	  */
	public int getEXME_EstiloVida_ID();

	public I_EXME_EstiloVida getEXME_EstiloVida() throws RuntimeException;

    /** Column name EXME_MotivoCancel_ID */
    public static final String COLUMNNAME_EXME_MotivoCancel_ID = "EXME_MotivoCancel_ID";

	/** Set Cancel Reason.
	  * Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID);

	/** Get Cancel Reason.
	  * Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID();

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

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

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name MotivoCancelacion */
    public static final String COLUMNNAME_MotivoCancelacion = "MotivoCancelacion";

	/** Set Comment of the reason for suspension.
	  * Comment of the reason for suspension
	  */
	public void setMotivoCancelacion (String MotivoCancelacion);

	/** Get Comment of the reason for suspension.
	  * Comment of the reason for suspension
	  */
	public String getMotivoCancelacion();

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

    /** Column name Seguimiento */
    public static final String COLUMNNAME_Seguimiento = "Seguimiento";

	/** Set Monitoring	  */
	public void setSeguimiento (String Seguimiento);

	/** Get Monitoring	  */
	public String getSeguimiento();
}
