/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_HistRecordatorio
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_HistRecordatorio 
{

    /** TableName=EXME_HistRecordatorio */
    public static final String Table_Name = "EXME_HistRecordatorio";

    /** AD_Table_ID=1200844 */
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

    /** Column name EXME_HistRecordatorio_ID */
    public static final String COLUMNNAME_EXME_HistRecordatorio_ID = "EXME_HistRecordatorio_ID";

	/** Set Reminder History	  */
	public void setEXME_HistRecordatorio_ID (int EXME_HistRecordatorio_ID);

	/** Get Reminder History	  */
	public int getEXME_HistRecordatorio_ID();

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

    /** Column name EXME_ProgRecordatorio_ID */
    public static final String COLUMNNAME_EXME_ProgRecordatorio_ID = "EXME_ProgRecordatorio_ID";

	/** Set Reminder Programation	  */
	public void setEXME_ProgRecordatorio_ID (int EXME_ProgRecordatorio_ID);

	/** Get Reminder Programation	  */
	public int getEXME_ProgRecordatorio_ID();

	public I_EXME_ProgRecordatorio getEXME_ProgRecordatorio() throws RuntimeException;

    /** Column name FechaEnv */
    public static final String COLUMNNAME_FechaEnv = "FechaEnv";

	/** Set Sending Date	  */
	public void setFechaEnv (Timestamp FechaEnv);

	/** Get Sending Date	  */
	public Timestamp getFechaEnv();

    /** Column name FechaProg */
    public static final String COLUMNNAME_FechaProg = "FechaProg";

	/** Set Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg);

	/** Get Scheduled Date	  */
	public Timestamp getFechaProg();

    /** Column name IsEMail */
    public static final String COLUMNNAME_IsEMail = "IsEMail";

	/** Set Is EMail	  */
	public void setIsEMail (boolean IsEMail);

	/** Get Is EMail	  */
	public boolean isEMail();

    /** Column name IsSMS */
    public static final String COLUMNNAME_IsSMS = "IsSMS";

	/** Set Is SMS	  */
	public void setIsSMS (boolean IsSMS);

	/** Get Is SMS	  */
	public boolean isSMS();

    /** Column name ToMedico */
    public static final String COLUMNNAME_ToMedico = "ToMedico";

	/** Set To Doctor	  */
	public void setToMedico (boolean ToMedico);

	/** Get To Doctor	  */
	public boolean isToMedico();
}
