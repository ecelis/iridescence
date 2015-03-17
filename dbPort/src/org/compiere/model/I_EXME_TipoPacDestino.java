/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoPacDestino
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_TipoPacDestino 
{

    /** TableName=EXME_TipoPacDestino */
    public static final String Table_Name = "EXME_TipoPacDestino";

    /** AD_Table_ID=1201181 */
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

    /** Column name AlertMessage */
    public static final String COLUMNNAME_AlertMessage = "AlertMessage";

	/** Set Alert Message.
	  * Alert Message
	  */
	public void setAlertMessage (String AlertMessage);

	/** Get Alert Message.
	  * Alert Message
	  */
	public String getAlertMessage();

    /** Column name ChangeDate */
    public static final String COLUMNNAME_ChangeDate = "ChangeDate";

	/** Set Change Date	  */
	public void setChangeDate (boolean ChangeDate);

	/** Get Change Date	  */
	public boolean isChangeDate();

    /** Column name EXME_AdmitSource_ID */
    public static final String COLUMNNAME_EXME_AdmitSource_ID = "EXME_AdmitSource_ID";

	/** Set Admit Source.
	  * Admit Source of the patient account
	  */
	public void setEXME_AdmitSource_ID (int EXME_AdmitSource_ID);

	/** Get Admit Source.
	  * Admit Source of the patient account
	  */
	public int getEXME_AdmitSource_ID();

	public I_EXME_AdmitSource getEXME_AdmitSource() throws RuntimeException;

    /** Column name EXME_AdmitType_ID */
    public static final String COLUMNNAME_EXME_AdmitType_ID = "EXME_AdmitType_ID";

	/** Set Admit Type.
	  * Admit Type of the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID);

	/** Get Admit Type.
	  * Admit Type of the Patient account
	  */
	public int getEXME_AdmitType_ID();

	public I_EXME_AdmitType getEXME_AdmitType() throws RuntimeException;

    /** Column name EXME_TipoPacDestino_ID */
    public static final String COLUMNNAME_EXME_TipoPacDestino_ID = "EXME_TipoPacDestino_ID";

	/** Set To Patient Type	  */
	public void setEXME_TipoPacDestino_ID (int EXME_TipoPacDestino_ID);

	/** Get To Patient Type	  */
	public int getEXME_TipoPacDestino_ID();

    /** Column name EXME_TipoPaciente_ID */
    public static final String COLUMNNAME_EXME_TipoPaciente_ID = "EXME_TipoPaciente_ID";

	/** Set Type of Patient.
	  * Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID);

	/** Get Type of Patient.
	  * Type of Patient
	  */
	public int getEXME_TipoPaciente_ID();

    /** Column name EXME_TipoPacienteTo_ID */
    public static final String COLUMNNAME_EXME_TipoPacienteTo_ID = "EXME_TipoPacienteTo_ID";

	/** Set To Patient Type	  */
	public void setEXME_TipoPacienteTo_ID (int EXME_TipoPacienteTo_ID);

	/** Get To Patient Type	  */
	public int getEXME_TipoPacienteTo_ID();

    /** Column name RequireAuthentication */
    public static final String COLUMNNAME_RequireAuthentication = "RequireAuthentication";

	/** Set Require Authentication.
	  * Indicates that this patient type change will require authentication
	  */
	public void setRequireAuthentication (boolean RequireAuthentication);

	/** Get Require Authentication.
	  * Indicates that this patient type change will require authentication
	  */
	public boolean isRequireAuthentication();
}
