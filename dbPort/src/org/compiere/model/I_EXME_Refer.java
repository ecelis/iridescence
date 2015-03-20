/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Refer
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Refer 
{

    /** TableName=EXME_Refer */
    public static final String Table_Name = "EXME_Refer";

    /** AD_Table_ID=1000137 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_OrgEnv_ID */
    public static final String COLUMNNAME_AD_OrgEnv_ID = "AD_OrgEnv_ID";

	/** Set Sending Organization	  */
	public void setAD_OrgEnv_ID (int AD_OrgEnv_ID);

	/** Get Sending Organization	  */
	public int getAD_OrgEnv_ID();

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

    /** Column name AD_OrgRec_ID */
    public static final String COLUMNNAME_AD_OrgRec_ID = "AD_OrgRec_ID";

	/** Set Receiving Organization.
	  * Receiving Organization for Referenced Patient
	  */
	public void setAD_OrgRec_ID (int AD_OrgRec_ID);

	/** Get Receiving Organization.
	  * Receiving Organization for Referenced Patient
	  */
	public int getAD_OrgRec_ID();

    /** Column name AprovadoEnv */
    public static final String COLUMNNAME_AprovadoEnv = "AprovadoEnv";

	/** Set Approved for shipping.
	  * Approved for shipping
	  */
	public void setAprovadoEnv (boolean AprovadoEnv);

	/** Get Approved for shipping.
	  * Approved for shipping
	  */
	public boolean isAprovadoEnv();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_DocTypeTarget_ID */
    public static final String COLUMNNAME_C_DocTypeTarget_ID = "C_DocTypeTarget_ID";

	/** Set Target Document Type.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID);

	/** Get Target Document Type.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

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

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException;

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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

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

    /** Column name EXME_Refer_ID */
    public static final String COLUMNNAME_EXME_Refer_ID = "EXME_Refer_ID";

	/** Set Patient's External Admission	  */
	public void setEXME_Refer_ID (int EXME_Refer_ID);

	/** Get Patient's External Admission	  */
	public int getEXME_Refer_ID();

    /** Column name EXME_ReferMot_ID */
    public static final String COLUMNNAME_EXME_ReferMot_ID = "EXME_ReferMot_ID";

	/** Set Reference Motive.
	  * Reference Motive
	  */
	public void setEXME_ReferMot_ID (int EXME_ReferMot_ID);

	/** Get Reference Motive.
	  * Reference Motive
	  */
	public int getEXME_ReferMot_ID();

	public I_EXME_ReferMot getEXME_ReferMot() throws RuntimeException;

    /** Column name FechaCancel */
    public static final String COLUMNNAME_FechaCancel = "FechaCancel";

	/** Set Cancel date.
	  * Cancel date
	  */
	public void setFechaCancel (Timestamp FechaCancel);

	/** Get Cancel date.
	  * Cancel date
	  */
	public Timestamp getFechaCancel();

    /** Column name FechaEnv */
    public static final String COLUMNNAME_FechaEnv = "FechaEnv";

	/** Set Sending Date	  */
	public void setFechaEnv (Timestamp FechaEnv);

	/** Get Sending Date	  */
	public Timestamp getFechaEnv();

    /** Column name FechaRec */
    public static final String COLUMNNAME_FechaRec = "FechaRec";

	/** Set Reception Date.
	  * Contains the date on which it carries out waste collection
	  */
	public void setFechaRec (Timestamp FechaRec);

	/** Get Reception Date.
	  * Contains the date on which it carries out waste collection
	  */
	public Timestamp getFechaRec();

    /** Column name Motivo */
    public static final String COLUMNNAME_Motivo = "Motivo";

	/** Set Motive.
	  * Motive / Reason
	  */
	public void setMotivo (String Motivo);

	/** Get Motive.
	  * Motive / Reason
	  */
	public String getMotivo();

    /** Column name PersonaEnv */
    public static final String COLUMNNAME_PersonaEnv = "PersonaEnv";

	/** Set Sending Responsible	  */
	public void setPersonaEnv (String PersonaEnv);

	/** Get Sending Responsible	  */
	public String getPersonaEnv();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Ref_EXME_Refer_ID */
    public static final String COLUMNNAME_Ref_EXME_Refer_ID = "Ref_EXME_Refer_ID";

	/** Set Ref_EXME_Refer_ID	  */
	public void setRef_EXME_Refer_ID (int Ref_EXME_Refer_ID);

	/** Get Ref_EXME_Refer_ID	  */
	public int getRef_EXME_Refer_ID();

    /** Column name TipoAreaEnv */
    public static final String COLUMNNAME_TipoAreaEnv = "TipoAreaEnv";

	/** Set Sending Area Type	  */
	public void setTipoAreaEnv (String TipoAreaEnv);

	/** Get Sending Area Type	  */
	public String getTipoAreaEnv();

    /** Column name TipoAreaRec */
    public static final String COLUMNNAME_TipoAreaRec = "TipoAreaRec";

	/** Set Receiving Area.
	  * Type of receiving area
	  */
	public void setTipoAreaRec (String TipoAreaRec);

	/** Get Receiving Area.
	  * Type of receiving area
	  */
	public String getTipoAreaRec();
}
