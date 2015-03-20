/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteDiag
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ActPacienteDiag 
{

    /** TableName=EXME_ActPacienteDiag */
    public static final String Table_Name = "EXME_ActPacienteDiag";

    /** AD_Table_ID=1000087 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

    /** Load Meta Data */

    /** Column name Admitting */
    public static final String COLUMNNAME_Admitting = "Admitting";

	/** Set Admitting Diagnostic	  */
	public void setAdmitting (String Admitting);

	/** Get Admitting Diagnostic	  */
	public String getAdmitting();

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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name ColumnName */
    public static final String COLUMNNAME_ColumnName = "ColumnName";

	/** Set DB Column Name.
	  * Name of the column in the database
	  */
	public void setColumnName (String ColumnName);

	/** Get DB Column Name.
	  * Name of the column in the database
	  */
	public String getColumnName();

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

    /** Column name Diagnosis */
    public static final String COLUMNNAME_Diagnosis = "Diagnosis";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setDiagnosis (String Diagnosis);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public String getDiagnosis();

    /** Column name EncounterStatus */
    public static final String COLUMNNAME_EncounterStatus = "EncounterStatus";

	/** Set Encounter Status	  */
	public void setEncounterStatus (String EncounterStatus);

	/** Get Encounter Status	  */
	public String getEncounterStatus();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

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

    /** Column name EXME_ActPaciente_ID */
    public static final String COLUMNNAME_EXME_ActPaciente_ID = "EXME_ActPaciente_ID";

	/** Set Patient Activity.
	  * Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID);

	/** Get Patient Activity.
	  * Patient Activity
	  */
	public int getEXME_ActPaciente_ID();

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

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

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

    /** Column name Fecha_Diagnostico */
    public static final String COLUMNNAME_Fecha_Diagnostico = "Fecha_Diagnostico";

	/** Set Diagnostic Date.
	  * Diagnostic Date
	  */
	public void setFecha_Diagnostico (Timestamp Fecha_Diagnostico);

	/** Get Diagnostic Date.
	  * Diagnostic Date
	  */
	public Timestamp getFecha_Diagnostico();

    /** Column name Fecha_Estatus */
    public static final String COLUMNNAME_Fecha_Estatus = "Fecha_Estatus";

	/** Set Fecha_Estatus	  */
	public void setFecha_Estatus (Timestamp Fecha_Estatus);

	/** Get Fecha_Estatus	  */
	public Timestamp getFecha_Estatus();

    /** Column name Fecha_Final */
    public static final String COLUMNNAME_Fecha_Final = "Fecha_Final";

	/** Set Final Execution Date Transplantation.
	  * Final Execution Date Transplantation
	  */
	public void setFecha_Final (Timestamp Fecha_Final);

	/** Get Final Execution Date Transplantation.
	  * Final Execution Date Transplantation
	  */
	public Timestamp getFecha_Final();

    /** Column name ISCC */
    public static final String COLUMNNAME_ISCC = "ISCC";

	/** Set ISCC	  */
	public void setISCC (boolean ISCC);

	/** Get ISCC	  */
	public boolean isCC();

    /** Column name IsNosocomial */
    public static final String COLUMNNAME_IsNosocomial = "IsNosocomial";

	/** Set Is Nosocomial	  */
	public void setIsNosocomial (boolean IsNosocomial);

	/** Get Is Nosocomial	  */
	public boolean isNosocomial();

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

    /** Column name Padecimiento */
    public static final String COLUMNNAME_Padecimiento = "Padecimiento";

	/** Set Condition	  */
	public void setPadecimiento (boolean Padecimiento);

	/** Get Condition	  */
	public boolean isPadecimiento();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name SequenceDiag */
    public static final String COLUMNNAME_SequenceDiag = "SequenceDiag";

	/** Set Sequence of Diagnostic	  */
	public void setSequenceDiag (int SequenceDiag);

	/** Get Sequence of Diagnostic	  */
	public int getSequenceDiag();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();
}
