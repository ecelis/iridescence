/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProgRecordatorio
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProgRecordatorio 
{

    /** TableName=EXME_ProgRecordatorio */
    public static final String Table_Name = "EXME_ProgRecordatorio";

    /** AD_Table_ID=1200843 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name EXME_ProgRecordatorio_ID */
    public static final String COLUMNNAME_EXME_ProgRecordatorio_ID = "EXME_ProgRecordatorio_ID";

	/** Set Reminder Programation	  */
	public void setEXME_ProgRecordatorio_ID (int EXME_ProgRecordatorio_ID);

	/** Get Reminder Programation	  */
	public int getEXME_ProgRecordatorio_ID();

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

    /** Column name Frequency */
    public static final String COLUMNNAME_Frequency = "Frequency";

	/** Set Frequency.
	  * Frequency of events
	  */
	public void setFrequency (int Frequency);

	/** Get Frequency.
	  * Frequency of events
	  */
	public int getFrequency();

    /** Column name IsCorreo */
    public static final String COLUMNNAME_IsCorreo = "IsCorreo";

	/** Set Is Postal Mail	  */
	public void setIsCorreo (boolean IsCorreo);

	/** Get Is Postal Mail	  */
	public boolean isCorreo();

    /** Column name IsEMail */
    public static final String COLUMNNAME_IsEMail = "IsEMail";

	/** Set Is EMail	  */
	public void setIsEMail (boolean IsEMail);

	/** Get Is EMail	  */
	public boolean isEMail();

    /** Column name IsManual */
    public static final String COLUMNNAME_IsManual = "IsManual";

	/** Set Manual.
	  * This is a manual process
	  */
	public void setIsManual (boolean IsManual);

	/** Get Manual.
	  * This is a manual process
	  */
	public boolean isManual();

    /** Column name IsSMS */
    public static final String COLUMNNAME_IsSMS = "IsSMS";

	/** Set Is SMS	  */
	public void setIsSMS (boolean IsSMS);

	/** Get Is SMS	  */
	public boolean isSMS();

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

    /** Column name TipoRecordatorio */
    public static final String COLUMNNAME_TipoRecordatorio = "TipoRecordatorio";

	/** Set Reminder Type	  */
	public void setTipoRecordatorio (String TipoRecordatorio);

	/** Get Reminder Type	  */
	public String getTipoRecordatorio();

    /** Column name ToMedico */
    public static final String COLUMNNAME_ToMedico = "ToMedico";

	/** Set To Doctor	  */
	public void setToMedico (boolean ToMedico);

	/** Get To Doctor	  */
	public boolean isToMedico();

    /** Column name Unidad */
    public static final String COLUMNNAME_Unidad = "Unidad";

	/** Set Unity	  */
	public void setUnidad (String Unidad);

	/** Get Unity	  */
	public String getUnidad();
}
