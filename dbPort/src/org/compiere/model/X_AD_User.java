/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_User
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_User extends PO implements I_AD_User, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_User (Properties ctx, int AD_User_ID, String trxName)
    {
      super (ctx, AD_User_ID, trxName);
      /** if (AD_User_ID == 0)
        {
			setAD_User_ID (0);
			setIsFullBPAccess (true);
// Y
			setIsInPayroll (false);
// N
			setIsNew (false);
			setName (null);
			setNotificationType (null);
// E
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_AD_User (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_User[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Beeper.
		@param Beeper Beeper	  */
	public void setBeeper (String Beeper)
	{
		set_Value (COLUMNNAME_Beeper, Beeper);
	}

	/** Get Beeper.
		@return Beeper	  */
	public String getBeeper () 
	{
		return (String)get_Value(COLUMNNAME_Beeper);
	}

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	/** Set Company Contact.
		@param C_BPartner_Contact_ID 
		Company Contact
	  */
	public void setC_BPartner_Contact_ID (int C_BPartner_Contact_ID)
	{
		if (C_BPartner_Contact_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Contact_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Contact_ID, Integer.valueOf(C_BPartner_Contact_ID));
	}

	/** Get Company Contact.
		@return Company Contact
	  */
	public int getC_BPartner_Contact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Contact_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner_Location.Table_Name);
        I_C_BPartner_Location result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner_Location)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_Location_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Company Location.
		@return Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Greeting getC_Greeting() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Greeting.Table_Name);
        I_C_Greeting result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Greeting)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Greeting_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Greeting.
		@param C_Greeting_ID 
		Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID)
	{
		if (C_Greeting_ID < 1) 
			set_Value (COLUMNNAME_C_Greeting_ID, null);
		else 
			set_Value (COLUMNNAME_C_Greeting_ID, Integer.valueOf(C_Greeting_ID));
	}

	/** Get Greeting.
		@return Greeting to print on correspondence
	  */
	public int getC_Greeting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Greeting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Job getC_Job() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Job.Table_Name);
        I_C_Job result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Job)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Job_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Position.
		@param C_Job_ID 
		Job Position
	  */
	public void setC_Job_ID (int C_Job_ID)
	{
		if (C_Job_ID < 1) 
			set_Value (COLUMNNAME_C_Job_ID, null);
		else 
			set_Value (COLUMNNAME_C_Job_ID, Integer.valueOf(C_Job_ID));
	}

	/** Get Position.
		@return Job Position
	  */
	public int getC_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** ConnectionProfile AD_Reference_ID=364 */
	public static final int CONNECTIONPROFILE_AD_Reference_ID=364;
	/** LAN = L */
	public static final String CONNECTIONPROFILE_LAN = "L";
	/** Terminal Server = T */
	public static final String CONNECTIONPROFILE_TerminalServer = "T";
	/** VPN = V */
	public static final String CONNECTIONPROFILE_VPN = "V";
	/** WAN = W */
	public static final String CONNECTIONPROFILE_WAN = "W";
	/** Set Connection Profile.
		@param ConnectionProfile 
		How a Java Client connects to the server(s)
	  */
	public void setConnectionProfile (String ConnectionProfile)
	{

		if (ConnectionProfile == null || ConnectionProfile.equals("L") || ConnectionProfile.equals("T") || ConnectionProfile.equals("V") || ConnectionProfile.equals("W")); else throw new IllegalArgumentException ("ConnectionProfile Invalid value - " + ConnectionProfile + " - Reference_ID=364 - L - T - V - W");		set_Value (COLUMNNAME_ConnectionProfile, ConnectionProfile);
	}

	/** Get Connection Profile.
		@return How a Java Client connects to the server(s)
	  */
	public String getConnectionProfile () 
	{
		return (String)get_Value(COLUMNNAME_ConnectionProfile);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set EMail User.
		@param EMailUser 
		User Name (ID) in the Mail System
	  */
	public void setEMailUser (String EMailUser)
	{
		set_Value (COLUMNNAME_EMailUser, EMailUser);
	}

	/** Get EMail User.
		@return User Name (ID) in the Mail System
	  */
	public String getEMailUser () 
	{
		return (String)get_Value(COLUMNNAME_EMailUser);
	}

	/** Set EMail User Password.
		@param EMailUserPW 
		Password of your email user id
	  */
	public void setEMailUserPW (String EMailUserPW)
	{
		set_ValueE (COLUMNNAME_EMailUserPW, EMailUserPW);
	}

	/** Get EMail User Password.
		@return Password of your email user id
	  */
	public String getEMailUserPW () 
	{
		return (String)get_ValueE(COLUMNNAME_EMailUserPW);
	}

	/** Set Verification Info.
		@param EMailVerify 
		Verification information of EMail Address
	  */
	public void setEMailVerify (String EMailVerify)
	{
		set_ValueNoCheck (COLUMNNAME_EMailVerify, EMailVerify);
	}

	/** Get Verification Info.
		@return Verification information of EMail Address
	  */
	public String getEMailVerify () 
	{
		return (String)get_Value(COLUMNNAME_EMailVerify);
	}

	/** Set EMail Verify.
		@param EMailVerifyDate 
		Date Email was verified
	  */
	public void setEMailVerifyDate (Timestamp EMailVerifyDate)
	{
		set_ValueNoCheck (COLUMNNAME_EMailVerifyDate, EMailVerifyDate);
	}

	/** Get EMail Verify.
		@return Date Email was verified
	  */
	public Timestamp getEMailVerifyDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EMailVerifyDate);
	}

	public I_EXME_Grado getEXME_Grado() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Grado.Table_Name);
        I_EXME_Grado result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Grado)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Grado_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Grade.
		@param EXME_Grado_ID 
		Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID)
	{
		if (EXME_Grado_ID < 1) 
			set_Value (COLUMNNAME_EXME_Grado_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Grado_ID, Integer.valueOf(EXME_Grado_ID));
	}

	/** Get Grade.
		@return Militar Grade
	  */
	public int getEXME_Grado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Grupo_Especialidad getEXME_Grupo_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Grupo_Especialidad.Table_Name);
        I_EXME_Grupo_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Grupo_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Grupo_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Speciality Group.
		@param EXME_Grupo_Especialidad_ID 
		Militar Speciality Group
	  */
	public void setEXME_Grupo_Especialidad_ID (int EXME_Grupo_Especialidad_ID)
	{
		if (EXME_Grupo_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Grupo_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Grupo_Especialidad_ID, Integer.valueOf(EXME_Grupo_Especialidad_ID));
	}

	/** Get Speciality Group.
		@return Militar Speciality Group
	  */
	public int getEXME_Grupo_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grupo_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fax.
		@param Fax 
		Facsimile number
	  */
	public void setFax (String Fax)
	{
		set_Value (COLUMNNAME_Fax, Fax);
	}

	/** Get Fax.
		@return Facsimile number
	  */
	public String getFax () 
	{
		return (String)get_Value(COLUMNNAME_Fax);
	}

	/** Set Date the password change is made.
		@param FechaCambiaPwd 
		Date the password change is made
	  */
	public void setFechaCambiaPwd (Timestamp FechaCambiaPwd)
	{
		set_Value (COLUMNNAME_FechaCambiaPwd, FechaCambiaPwd);
	}

	/** Get Date the password change is made.
		@return Date the password change is made
	  */
	public Timestamp getFechaCambiaPwd () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCambiaPwd);
	}

	/** Set Full BP Access.
		@param IsFullBPAccess 
		The user/concat has full access to Business Partner information and resources
	  */
	public void setIsFullBPAccess (boolean IsFullBPAccess)
	{
		set_Value (COLUMNNAME_IsFullBPAccess, Boolean.valueOf(IsFullBPAccess));
	}

	/** Get Full BP Access.
		@return The user/concat has full access to Business Partner information and resources
	  */
	public boolean isFullBPAccess () 
	{
		Object oo = get_Value(COLUMNNAME_IsFullBPAccess);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is In Payroll.
		@param IsInPayroll 
		Defined if any User Contact will be used for Calculate Payroll
	  */
	public void setIsInPayroll (boolean IsInPayroll)
	{
		set_Value (COLUMNNAME_IsInPayroll, Boolean.valueOf(IsInPayroll));
	}

	/** Get Is In Payroll.
		@return Defined if any User Contact will be used for Calculate Payroll
	  */
	public boolean isInPayroll () 
	{
		Object oo = get_Value(COLUMNNAME_IsInPayroll);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set New User.
		@param IsNew 
		New User
	  */
	public void setIsNew (boolean IsNew)
	{
		set_Value (COLUMNNAME_IsNew, Boolean.valueOf(IsNew));
	}

	/** Get New User.
		@return New User
	  */
	public boolean isNew () 
	{
		Object oo = get_Value(COLUMNNAME_IsNew);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Staff.
		@param IsStaff 
		Is ecaresoft staff
	  */
	public void setIsStaff (boolean IsStaff)
	{
		set_Value (COLUMNNAME_IsStaff, Boolean.valueOf(IsStaff));
	}

	/** Get Is Staff.
		@return Is ecaresoft staff
	  */
	public boolean isStaff () 
	{
		Object oo = get_Value(COLUMNNAME_IsStaff);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Last Contact.
		@param LastContact 
		Date this individual was last contacted
	  */
	public void setLastContact (Timestamp LastContact)
	{
		set_Value (COLUMNNAME_LastContact, LastContact);
	}

	/** Get Last Contact.
		@return Date this individual was last contacted
	  */
	public Timestamp getLastContact () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastContact);
	}

	/** Set Last Result.
		@param LastResult 
		Result of last contact
	  */
	public void setLastResult (String LastResult)
	{
		set_Value (COLUMNNAME_LastResult, LastResult);
	}

	/** Get Last Result.
		@return Result of last contact
	  */
	public String getLastResult () 
	{
		return (String)get_Value(COLUMNNAME_LastResult);
	}

	/** Set LDAP User Name.
		@param LDAPUser 
		User Name used for authorization via LDAP (directory) services
	  */
	public void setLDAPUser (String LDAPUser)
	{
		set_Value (COLUMNNAME_LDAPUser, LDAPUser);
	}

	/** Get LDAP User Name.
		@return User Name used for authorization via LDAP (directory) services
	  */
	public String getLDAPUser () 
	{
		return (String)get_Value(COLUMNNAME_LDAPUser);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** NotificationType AD_Reference_ID=344 */
	public static final int NOTIFICATIONTYPE_AD_Reference_ID=344;
	/** EMail = E */
	public static final String NOTIFICATIONTYPE_EMail = "E";
	/** Notice = N */
	public static final String NOTIFICATIONTYPE_Notice = "N";
	/** None = X */
	public static final String NOTIFICATIONTYPE_None = "X";
	/** EMail+Notice = B */
	public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
	/** Set Notification Type.
		@param NotificationType 
		Type of Notifications
	  */
	public void setNotificationType (String NotificationType)
	{
		if (NotificationType == null) throw new IllegalArgumentException ("NotificationType is mandatory");
		if (NotificationType.equals("E") || NotificationType.equals("N") || NotificationType.equals("X") || NotificationType.equals("B")); else throw new IllegalArgumentException ("NotificationType Invalid value - " + NotificationType + " - Reference_ID=344 - E - N - X - B");		set_Value (COLUMNNAME_NotificationType, NotificationType);
	}

	/** Get Notification Type.
		@return Type of Notifications
	  */
	public String getNotificationType () 
	{
		return (String)get_Value(COLUMNNAME_NotificationType);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set Temporary password.
		@param PasswordProv Temporary password	  */
	public void setPasswordProv (String PasswordProv)
	{
		set_Value (COLUMNNAME_PasswordProv, PasswordProv);
	}

	/** Get Temporary password.
		@return Temporary password	  */
	public String getPasswordProv () 
	{
		return (String)get_Value(COLUMNNAME_PasswordProv);
	}

	/** Set Main Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Main Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}

	/** Set Question.
		@param Pregunta 
		Question
	  */
	public void setPregunta (String Pregunta)
	{
		set_Value (COLUMNNAME_Pregunta, Pregunta);
	}

	/** Get Question.
		@return Question
	  */
	public String getPregunta () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Answer.
		@param Respuesta 
		Answer
	  */
	public void setRespuesta (String Respuesta)
	{
		set_ValueE (COLUMNNAME_Respuesta, Respuesta);
	}

	/** Get Answer.
		@return Answer
	  */
	public String getRespuesta () 
	{
		return (String)get_ValueE(COLUMNNAME_Respuesta);
	}

	/** SistemaMedicion AD_Reference_ID=1200380 */
	public static final int SISTEMAMEDICION_AD_Reference_ID=1200380;
	/** None = N */
	public static final String SISTEMAMEDICION_None = "N";
	/** Metric System = M */
	public static final String SISTEMAMEDICION_MetricSystem = "M";
	/** English System = E */
	public static final String SISTEMAMEDICION_EnglishSystem = "E";
	/** Set Systems of measurement.
		@param SistemaMedicion 
		Systems of measurement
	  */
	public void setSistemaMedicion (String SistemaMedicion)
	{

		if (SistemaMedicion == null || SistemaMedicion.equals("N") || SistemaMedicion.equals("M") || SistemaMedicion.equals("E")); else throw new IllegalArgumentException ("SistemaMedicion Invalid value - " + SistemaMedicion + " - Reference_ID=1200380 - N - M - E");		set_Value (COLUMNNAME_SistemaMedicion, SistemaMedicion);
	}

	/** Get Systems of measurement.
		@return Systems of measurement
	  */
	public String getSistemaMedicion () 
	{
		return (String)get_Value(COLUMNNAME_SistemaMedicion);
	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1) 
			set_Value (COLUMNNAME_Supervisor_ID, null);
		else 
			set_Value (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}

	/** Set UserPIN.
		@param UserPIN UserPIN	  */
	public void setUserPIN (String UserPIN)
	{
		set_Value (COLUMNNAME_UserPIN, UserPIN);
	}

	/** Get UserPIN.
		@return UserPIN	  */
	public String getUserPIN () 
	{
		return (String)get_Value(COLUMNNAME_UserPIN);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}