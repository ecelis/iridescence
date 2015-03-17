/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Asistente
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Asistente 
{

    /** TableName=EXME_Asistente */
    public static final String Table_Name = "EXME_Asistente";

    /** AD_Table_ID=1000056 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (boolean AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public boolean isAccessLevel();

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

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name ConfirmImaging */
    public static final String COLUMNNAME_ConfirmImaging = "ConfirmImaging";

	/** Set Confirm imaging orders.
	  * Check to enable the assistant to confirm imaging order
	  */
	public void setConfirmImaging (boolean ConfirmImaging);

	/** Get Confirm imaging orders.
	  * Check to enable the assistant to confirm imaging order
	  */
	public boolean isConfirmImaging();

    /** Column name ConfirmLabOrders */
    public static final String COLUMNNAME_ConfirmLabOrders = "ConfirmLabOrders";

	/** Set Confirm Lab Orders.
	  * Check to enable assistant to confirm lab orders
	  */
	public void setConfirmLabOrders (boolean ConfirmLabOrders);

	/** Get Confirm Lab Orders.
	  * Check to enable assistant to confirm lab orders
	  */
	public boolean isConfirmLabOrders();

    /** Column name ConfirmReferrals */
    public static final String COLUMNNAME_ConfirmReferrals = "ConfirmReferrals";

	/** Set Confirm Referrals.
	  * Check to enable assistant to confirm medical referrals
	  */
	public void setConfirmReferrals (boolean ConfirmReferrals);

	/** Get Confirm Referrals.
	  * Check to enable assistant to confirm medical referrals
	  */
	public boolean isConfirmReferrals();

    /** Column name CreatePrescription */
    public static final String COLUMNNAME_CreatePrescription = "CreatePrescription";

	/** Set Create Prescription.
	  * Check to enable assistant to create prescriptions
	  */
	public void setCreatePrescription (boolean CreatePrescription);

	/** Get Create Prescription.
	  * Check to enable assistant to create prescriptions
	  */
	public boolean isCreatePrescription();

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

    /** Column name EXME_Asistente_ID */
    public static final String COLUMNNAME_EXME_Asistente_ID = "EXME_Asistente_ID";

	/** Set Assistant.
	  * Assistant
	  */
	public void setEXME_Asistente_ID (int EXME_Asistente_ID);

	/** Get Assistant.
	  * Assistant
	  */
	public int getEXME_Asistente_ID();

    /** Column name EXME_CentroMedico_ID */
    public static final String COLUMNNAME_EXME_CentroMedico_ID = "EXME_CentroMedico_ID";

	/** Set Medical Center.
	  * medical Center
	  */
	public void setEXME_CentroMedico_ID (int EXME_CentroMedico_ID);

	/** Get Medical Center.
	  * medical Center
	  */
	public int getEXME_CentroMedico_ID();

	public I_EXME_CentroMedico getEXME_CentroMedico() throws RuntimeException;

    /** Column name FinishAppointment */
    public static final String COLUMNNAME_FinishAppointment = "FinishAppointment";

	/** Set Finish Appointment	  */
	public void setFinishAppointment (boolean FinishAppointment);

	/** Get Finish Appointment	  */
	public boolean isFinishAppointment();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name StartAppointment */
    public static final String COLUMNNAME_StartAppointment = "StartAppointment";

	/** Set Start Office Visit	  */
	public void setStartAppointment (boolean StartAppointment);

	/** Get Start Office Visit	  */
	public boolean isStartAppointment();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name ViewAllergies */
    public static final String COLUMNNAME_ViewAllergies = "ViewAllergies";

	/** Set Can review allergies.
	  * Can review allergies
	  */
	public void setViewAllergies (boolean ViewAllergies);

	/** Get Can review allergies.
	  * Can review allergies
	  */
	public boolean isViewAllergies();

    /** Column name ViewHistory */
    public static final String COLUMNNAME_ViewHistory = "ViewHistory";

	/** Set Can review history	  */
	public void setViewHistory (boolean ViewHistory);

	/** Get Can review history	  */
	public boolean isViewHistory();

    /** Column name ViewHMedication */
    public static final String COLUMNNAME_ViewHMedication = "ViewHMedication";

	/** Set Can review home medication.
	  * Can review home medication
	  */
	public void setViewHMedication (boolean ViewHMedication);

	/** Get Can review home medication.
	  * Can review home medication
	  */
	public boolean isViewHMedication();

    /** Column name ViewInstructions */
    public static final String COLUMNNAME_ViewInstructions = "ViewInstructions";

	/** Set View Instructions.
	  * View Instructions
	  */
	public void setViewInstructions (boolean ViewInstructions);

	/** Get View Instructions.
	  * View Instructions
	  */
	public boolean isViewInstructions();

    /** Column name ViewNextAppt */
    public static final String COLUMNNAME_ViewNextAppt = "ViewNextAppt";

	/** Set View Next Appt.
	  * View Next Appt
	  */
	public void setViewNextAppt (boolean ViewNextAppt);

	/** Get View Next Appt.
	  * View Next Appt
	  */
	public boolean isViewNextAppt();

    /** Column name ViewService */
    public static final String COLUMNNAME_ViewService = "ViewService";

	/** Set View Service.
	  * View Service
	  */
	public void setViewService (boolean ViewService);

	/** Get View Service.
	  * View Service
	  */
	public boolean isViewService();

    /** Column name ViewVitals */
    public static final String COLUMNNAME_ViewVitals = "ViewVitals";

	/** Set Can review vitals.
	  * Can review vitals
	  */
	public void setViewVitals (boolean ViewVitals);

	/** Get Can review vitals.
	  * Can review vitals
	  */
	public boolean isViewVitals();
}
