/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigRecordatorio
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigRecordatorio 
{

    /** TableName=EXME_ConfigRecordatorio */
    public static final String Table_Name = "EXME_ConfigRecordatorio";

    /** AD_Table_ID=1200842 */
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

    /** Column name EXME_ConfigRecordatorio_ID */
    public static final String COLUMNNAME_EXME_ConfigRecordatorio_ID = "EXME_ConfigRecordatorio_ID";

	/** Set Reminders Configuration	  */
	public void setEXME_ConfigRecordatorio_ID (int EXME_ConfigRecordatorio_ID);

	/** Get Reminders Configuration	  */
	public int getEXME_ConfigRecordatorio_ID();

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

    /** Column name isPhone */
    public static final String COLUMNNAME_isPhone = "isPhone";

	/** Set isPhone.
	  * isPhone
	  */
	public void setisPhone (boolean isPhone);

	/** Get isPhone.
	  * isPhone
	  */
	public boolean isPhone();

    /** Column name IsSMS */
    public static final String COLUMNNAME_IsSMS = "IsSMS";

	/** Set Is SMS	  */
	public void setIsSMS (boolean IsSMS);

	/** Get Is SMS	  */
	public boolean isSMS();
}
