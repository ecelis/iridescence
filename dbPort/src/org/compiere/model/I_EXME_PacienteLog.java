/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteLog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacienteLog 
{

    /** TableName=EXME_PacienteLog */
    public static final String Table_Name = "EXME_PacienteLog";

    /** AD_Table_ID=1200311 */
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

    /** Column name EXME_PacienteLog_ID */
    public static final String COLUMNNAME_EXME_PacienteLog_ID = "EXME_PacienteLog_ID";

	/** Set Patient Log.
	  * Patient Log
	  */
	public void setEXME_PacienteLog_ID (int EXME_PacienteLog_ID);

	/** Get Patient Log.
	  * Patient Log
	  */
	public int getEXME_PacienteLog_ID();

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

    /** Column name NewName */
    public static final String COLUMNNAME_NewName = "NewName";

	/** Set New Name	  */
	public void setNewName (String NewName);

	/** Get New Name	  */
	public String getNewName();

    /** Column name OldName */
    public static final String COLUMNNAME_OldName = "OldName";

	/** Set Old Name	  */
	public void setOldName (String OldName);

	/** Get Old Name	  */
	public String getOldName();
}
