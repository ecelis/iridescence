/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_User
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_AD_User 
{

    /** TableName=AD_User */
    public static final String Table_Name = "AD_User";

    /** AD_Table_ID=114 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

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

    /** Column name Beeper */
    public static final String COLUMNNAME_Beeper = "Beeper";

	/** Set Beeper	  */
	public void setBeeper (String Beeper);

	/** Get Beeper	  */
	public String getBeeper();

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name C_BPartner_Contact_ID */
    public static final String COLUMNNAME_C_BPartner_Contact_ID = "C_BPartner_Contact_ID";

	/** Set Company Contact.
	  * Company Contact
	  */
	public void setC_BPartner_Contact_ID (int C_BPartner_Contact_ID);

	/** Get Company Contact.
	  * Company Contact
	  */
	public int getC_BPartner_Contact_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Greeting_ID */
    public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";

	/** Set Greeting.
	  * Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID);

	/** Get Greeting.
	  * Greeting to print on correspondence
	  */
	public int getC_Greeting_ID();

	public I_C_Greeting getC_Greeting() throws RuntimeException;

    /** Column name C_Job_ID */
    public static final String COLUMNNAME_C_Job_ID = "C_Job_ID";

	/** Set Position.
	  * Job Position
	  */
	public void setC_Job_ID (int C_Job_ID);

	/** Get Position.
	  * Job Position
	  */
	public int getC_Job_ID();

	public I_C_Job getC_Job() throws RuntimeException;

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name ConnectionProfile */
    public static final String COLUMNNAME_ConnectionProfile = "ConnectionProfile";

	/** Set Connection Profile.
	  * How a Java Client connects to the server(s)
	  */
	public void setConnectionProfile (String ConnectionProfile);

	/** Get Connection Profile.
	  * How a Java Client connects to the server(s)
	  */
	public String getConnectionProfile();

    /** Column name DateTo */
    public static final String COLUMNNAME_DateTo = "DateTo";

	/** Set Date To.
	  * End date of a date range
	  */
	public void setDateTo (Timestamp DateTo);

	/** Get Date To.
	  * End date of a date range
	  */
	public Timestamp getDateTo();

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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EMailUser */
    public static final String COLUMNNAME_EMailUser = "EMailUser";

	/** Set EMail User.
	  * User Name (ID) in the Mail System
	  */
	public void setEMailUser (String EMailUser);

	/** Get EMail User.
	  * User Name (ID) in the Mail System
	  */
	public String getEMailUser();

    /** Column name EMailUserPW */
    public static final String COLUMNNAME_EMailUserPW = "EMailUserPW";

	/** Set EMail User Password.
	  * Password of your email user id
	  */
	public void setEMailUserPW (String EMailUserPW);

	/** Get EMail User Password.
	  * Password of your email user id
	  */
	public String getEMailUserPW();

    /** Column name EMailVerify */
    public static final String COLUMNNAME_EMailVerify = "EMailVerify";

	/** Set Verification Info.
	  * Verification information of EMail Address
	  */
	public void setEMailVerify (String EMailVerify);

	/** Get Verification Info.
	  * Verification information of EMail Address
	  */
	public String getEMailVerify();

    /** Column name EMailVerifyDate */
    public static final String COLUMNNAME_EMailVerifyDate = "EMailVerifyDate";

	/** Set EMail Verify.
	  * Date Email was verified
	  */
	public void setEMailVerifyDate (Timestamp EMailVerifyDate);

	/** Get EMail Verify.
	  * Date Email was verified
	  */
	public Timestamp getEMailVerifyDate();

    /** Column name EXME_Grado_ID */
    public static final String COLUMNNAME_EXME_Grado_ID = "EXME_Grado_ID";

	/** Set Grade.
	  * Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID);

	/** Get Grade.
	  * Militar Grade
	  */
	public int getEXME_Grado_ID();

	public I_EXME_Grado getEXME_Grado() throws RuntimeException;

    /** Column name EXME_Grupo_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Grupo_Especialidad_ID = "EXME_Grupo_Especialidad_ID";

	/** Set Speciality Group.
	  * Militar Speciality Group
	  */
	public void setEXME_Grupo_Especialidad_ID (int EXME_Grupo_Especialidad_ID);

	/** Get Speciality Group.
	  * Militar Speciality Group
	  */
	public int getEXME_Grupo_Especialidad_ID();

	public I_EXME_Grupo_Especialidad getEXME_Grupo_Especialidad() throws RuntimeException;

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name FechaCambiaPwd */
    public static final String COLUMNNAME_FechaCambiaPwd = "FechaCambiaPwd";

	/** Set Date the password change is made.
	  * Date the password change is made
	  */
	public void setFechaCambiaPwd (Timestamp FechaCambiaPwd);

	/** Get Date the password change is made.
	  * Date the password change is made
	  */
	public Timestamp getFechaCambiaPwd();

    /** Column name IsFullBPAccess */
    public static final String COLUMNNAME_IsFullBPAccess = "IsFullBPAccess";

	/** Set Full BP Access.
	  * The user/concat has full access to Business Partner information and resources
	  */
	public void setIsFullBPAccess (boolean IsFullBPAccess);

	/** Get Full BP Access.
	  * The user/concat has full access to Business Partner information and resources
	  */
	public boolean isFullBPAccess();

    /** Column name IsInPayroll */
    public static final String COLUMNNAME_IsInPayroll = "IsInPayroll";

	/** Set Is In Payroll.
	  * Defined if any User Contact will be used for Calculate Payroll
	  */
	public void setIsInPayroll (boolean IsInPayroll);

	/** Get Is In Payroll.
	  * Defined if any User Contact will be used for Calculate Payroll
	  */
	public boolean isInPayroll();

    /** Column name IsNew */
    public static final String COLUMNNAME_IsNew = "IsNew";

	/** Set New User.
	  * New User
	  */
	public void setIsNew (boolean IsNew);

	/** Get New User.
	  * New User
	  */
	public boolean isNew();

    /** Column name IsStaff */
    public static final String COLUMNNAME_IsStaff = "IsStaff";

	/** Set Is Staff.
	  * Is ecaresoft staff
	  */
	public void setIsStaff (boolean IsStaff);

	/** Get Is Staff.
	  * Is ecaresoft staff
	  */
	public boolean isStaff();

    /** Column name LastContact */
    public static final String COLUMNNAME_LastContact = "LastContact";

	/** Set Last Contact.
	  * Date this individual was last contacted
	  */
	public void setLastContact (Timestamp LastContact);

	/** Get Last Contact.
	  * Date this individual was last contacted
	  */
	public Timestamp getLastContact();

    /** Column name LastResult */
    public static final String COLUMNNAME_LastResult = "LastResult";

	/** Set Last Result.
	  * Result of last contact
	  */
	public void setLastResult (String LastResult);

	/** Get Last Result.
	  * Result of last contact
	  */
	public String getLastResult();

    /** Column name LDAPUser */
    public static final String COLUMNNAME_LDAPUser = "LDAPUser";

	/** Set LDAP User Name.
	  * User Name used for authorization via LDAP (directory) services
	  */
	public void setLDAPUser (String LDAPUser);

	/** Get LDAP User Name.
	  * User Name used for authorization via LDAP (directory) services
	  */
	public String getLDAPUser();

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

    /** Column name NotificationType */
    public static final String COLUMNNAME_NotificationType = "NotificationType";

	/** Set Notification Type.
	  * Type of Notifications
	  */
	public void setNotificationType (String NotificationType);

	/** Get Notification Type.
	  * Type of Notifications
	  */
	public String getNotificationType();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name PasswordProv */
    public static final String COLUMNNAME_PasswordProv = "PasswordProv";

	/** Set Temporary password	  */
	public void setPasswordProv (String PasswordProv);

	/** Get Temporary password	  */
	public String getPasswordProv();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Main Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Main Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name Pregunta */
    public static final String COLUMNNAME_Pregunta = "Pregunta";

	/** Set Question.
	  * Question
	  */
	public void setPregunta (String Pregunta);

	/** Get Question.
	  * Question
	  */
	public String getPregunta();

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

    /** Column name Respuesta */
    public static final String COLUMNNAME_Respuesta = "Respuesta";

	/** Set Answer.
	  * Answer
	  */
	public void setRespuesta (String Respuesta);

	/** Get Answer.
	  * Answer
	  */
	public String getRespuesta();

    /** Column name SistemaMedicion */
    public static final String COLUMNNAME_SistemaMedicion = "SistemaMedicion";

	/** Set Systems of measurement.
	  * Systems of measurement
	  */
	public void setSistemaMedicion (String SistemaMedicion);

	/** Get Systems of measurement.
	  * Systems of measurement
	  */
	public String getSistemaMedicion();

    /** Column name Supervisor_ID */
    public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

	/** Set Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID);

	/** Get Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

    /** Column name UserPIN */
    public static final String COLUMNNAME_UserPIN = "UserPIN";

	/** Set UserPIN	  */
	public void setUserPIN (String UserPIN);

	/** Get UserPIN	  */
	public String getUserPIN();

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
}
