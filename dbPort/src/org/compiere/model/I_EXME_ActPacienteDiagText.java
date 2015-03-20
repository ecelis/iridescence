/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteDiagText
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ActPacienteDiagText 
{

    /** TableName=EXME_ActPacienteDiagText */
    public static final String Table_Name = "EXME_ActPacienteDiagText";

    /** AD_Table_ID=1201135 */
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

    /** Column name Diagnostico */
    public static final String COLUMNNAME_Diagnostico = "Diagnostico";

	/** Set Diagnostic.
	  * Diagnostic
	  */
	public void setDiagnostico (String Diagnostico);

	/** Get Diagnostic.
	  * Diagnostic
	  */
	public String getDiagnostico();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (boolean Estatus);

	/** Get Status.
	  * Status
	  */
	public boolean isEstatus();

    /** Column name EXME_ACTPACIENTEDIAGTEXT_ID */
    public static final String COLUMNNAME_EXME_ACTPACIENTEDIAGTEXT_ID = "EXME_ACTPACIENTEDIAGTEXT_ID";

	/** Set EXME_ACTPACIENTEDIAGTEXT_ID	  */
	public void setEXME_ACTPACIENTEDIAGTEXT_ID (int EXME_ACTPACIENTEDIAGTEXT_ID);

	/** Get EXME_ACTPACIENTEDIAGTEXT_ID	  */
	public int getEXME_ACTPACIENTEDIAGTEXT_ID();

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

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

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

    /** Column name SEQUENCEDIAG */
    public static final String COLUMNNAME_SEQUENCEDIAG = "SEQUENCEDIAG";

	/** Set SEQUENCEDIAG	  */
	public void setSEQUENCEDIAG (int SEQUENCEDIAG);

	/** Get SEQUENCEDIAG	  */
	public int getSEQUENCEDIAG();
}
