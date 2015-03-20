/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EstudiosOBX
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EstudiosOBX 
{

    /** TableName=EXME_EstudiosOBX */
    public static final String Table_Name = "EXME_EstudiosOBX";

    /** AD_Table_ID=1200606 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AbnormalFlags */
    public static final String COLUMNNAME_AbnormalFlags = "AbnormalFlags";

	/** Set Abnormal Flags	  */
	public void setAbnormalFlags (String AbnormalFlags);

	/** Get Abnormal Flags	  */
	public String getAbnormalFlags();

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

    /** Column name AnalysisDate */
    public static final String COLUMNNAME_AnalysisDate = "AnalysisDate";

	/** Set Analysis Date	  */
	public void setAnalysisDate (Timestamp AnalysisDate);

	/** Get Analysis Date	  */
	public Timestamp getAnalysisDate();

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name CodeID */
    public static final String COLUMNNAME_CodeID = "CodeID";

	/** Set Code Identifier	  */
	public void setCodeID (int CodeID);

	/** Get Code Identifier	  */
	public int getCodeID();

    /** Column name CodeOBX */
    public static final String COLUMNNAME_CodeOBX = "CodeOBX";

	/** Set Observation Code	  */
	public void setCodeOBX (String CodeOBX);

	/** Get Observation Code	  */
	public String getCodeOBX();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

    /** Column name DateApproved */
    public static final String COLUMNNAME_DateApproved = "DateApproved";

	/** Set Date Approved	  */
	public void setDateApproved (Timestamp DateApproved);

	/** Get Date Approved	  */
	public Timestamp getDateApproved();

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indication's detail.
	  * Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indication's detail.
	  * Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID();

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException;

    /** Column name EXME_EstudiosOBX_ID */
    public static final String COLUMNNAME_EXME_EstudiosOBX_ID = "EXME_EstudiosOBX_ID";

	/** Set Test Results.
	  * OBX Laboratory Test Results
	  */
	public void setEXME_EstudiosOBX_ID (int EXME_EstudiosOBX_ID);

	/** Get Test Results.
	  * OBX Laboratory Test Results
	  */
	public int getEXME_EstudiosOBX_ID();

    /** Column name LabAddress */
    public static final String COLUMNNAME_LabAddress = "LabAddress";

	/** Set Lab address	  */
	public void setLabAddress (String LabAddress);

	/** Get Lab address	  */
	public String getLabAddress();

    /** Column name LabName */
    public static final String COLUMNNAME_LabName = "LabName";

	/** Set Lab Facility Name	  */
	public void setLabName (String LabName);

	/** Get Lab Facility Name	  */
	public String getLabName();

    /** Column name Observation */
    public static final String COLUMNNAME_Observation = "Observation";

	/** Set Observation.
	  * Optional short description of the record
	  */
	public void setObservation (String Observation);

	/** Get Observation.
	  * Optional short description of the record
	  */
	public String getObservation();

    /** Column name ObservationDate */
    public static final String COLUMNNAME_ObservationDate = "ObservationDate";

	/** Set Observation Date	  */
	public void setObservationDate (Timestamp ObservationDate);

	/** Get Observation Date	  */
	public Timestamp getObservationDate();

    /** Column name Parent_ID */
    public static final String COLUMNNAME_Parent_ID = "Parent_ID";

	/** Set Parent.
	  * Parent of Entity
	  */
	public void setParent_ID (int Parent_ID);

	/** Get Parent.
	  * Parent of Entity
	  */
	public int getParent_ID();

    /** Column name RangeReference */
    public static final String COLUMNNAME_RangeReference = "RangeReference";

	/** Set Range Reference	  */
	public void setRangeReference (String RangeReference);

	/** Get Range Reference	  */
	public String getRangeReference();

    /** Column name ResultStatus */
    public static final String COLUMNNAME_ResultStatus = "ResultStatus";

	/** Set Result Status	  */
	public void setResultStatus (String ResultStatus);

	/** Get Result Status	  */
	public String getResultStatus();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();

    /** Column name SystemCode */
    public static final String COLUMNNAME_SystemCode = "SystemCode";

	/** Set System Code	  */
	public void setSystemCode (String SystemCode);

	/** Get System Code	  */
	public String getSystemCode();

    /** Column name UOMValue */
    public static final String COLUMNNAME_UOMValue = "UOMValue";

	/** Set UOM Value	  */
	public void setUOMValue (String UOMValue);

	/** Get UOM Value	  */
	public String getUOMValue();
}
