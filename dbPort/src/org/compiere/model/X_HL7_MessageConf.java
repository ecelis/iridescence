/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for HL7_MessageConf
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_HL7_MessageConf extends PO implements I_HL7_MessageConf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_MessageConf (Properties ctx, int HL7_MessageConf_ID, String trxName)
    {
      super (ctx, HL7_MessageConf_ID, trxName);
      /** if (HL7_MessageConf_ID == 0)
        {
			setHL7_MessageConf_ID (0);
			setHL7_MessageDef_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_MessageConf (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_HL7_MessageConf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AcceptACKType AD_Reference_ID=1200353 */
	public static final int ACCEPTACKTYPE_AD_Reference_ID=1200353;
	/** Always = AL */
	public static final String ACCEPTACKTYPE_Always = "AL";
	/** Error/Reject conditions only = ER */
	public static final String ACCEPTACKTYPE_ErrorRejectConditionsOnly = "ER";
	/** Never = NE */
	public static final String ACCEPTACKTYPE_Never = "NE";
	/** Successful completion only = SU */
	public static final String ACCEPTACKTYPE_SuccessfulCompletionOnly = "SU";
	/** Set Accept Acknowledgment Type.
		@param AcceptACKType 
		This field identifies the conditions under which accept acknowledgments are required to be returned in response to this message
	  */
	public void setAcceptACKType (String AcceptACKType)
	{

		if (AcceptACKType == null || AcceptACKType.equals("AL") || AcceptACKType.equals("ER") || AcceptACKType.equals("NE") || AcceptACKType.equals("SU")); else throw new IllegalArgumentException ("AcceptACKType Invalid value - " + AcceptACKType + " - Reference_ID=1200353 - AL - ER - NE - SU");		set_Value (COLUMNNAME_AcceptACKType, AcceptACKType);
	}

	/** Get Accept Acknowledgment Type.
		@return This field identifies the conditions under which accept acknowledgments are required to be returned in response to this message
	  */
	public String getAcceptACKType () 
	{
		return (String)get_Value(COLUMNNAME_AcceptACKType);
	}

	/** AccessLevel AD_Reference_ID=5 */
	public static final int ACCESSLEVEL_AD_Reference_ID=5;
	/** Organization = 1 */
	public static final String ACCESSLEVEL_Organization = "1";
	/** Client+Organization = 3 */
	public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
	/** System only = 4 */
	public static final String ACCESSLEVEL_SystemOnly = "4";
	/** All = 7 */
	public static final String ACCESSLEVEL_All = "7";
	/** System+Client = 6 */
	public static final String ACCESSLEVEL_SystemPlusClient = "6";
	/** Client only = 2 */
	public static final String ACCESSLEVEL_ClientOnly = "2";
	/** Client to System = 9 */
	public static final String ACCESSLEVEL_ClientToSystem = "9";
	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (String AccessLevel)
	{

		if (AccessLevel == null || AccessLevel.equals("1") || AccessLevel.equals("3") || AccessLevel.equals("4") || AccessLevel.equals("7") || AccessLevel.equals("6") || AccessLevel.equals("2") || AccessLevel.equals("9")); else throw new IllegalArgumentException ("AccessLevel Invalid value - " + AccessLevel + " - Reference_ID=5 - 1 - 3 - 4 - 7 - 6 - 2 - 9");		set_Value (COLUMNNAME_AccessLevel, AccessLevel);
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public String getAccessLevel () 
	{
		return (String)get_Value(COLUMNNAME_AccessLevel);
	}

	/** ApplicationACKType AD_Reference_ID=1200353 */
	public static final int APPLICATIONACKTYPE_AD_Reference_ID=1200353;
	/** Always = AL */
	public static final String APPLICATIONACKTYPE_Always = "AL";
	/** Error/Reject conditions only = ER */
	public static final String APPLICATIONACKTYPE_ErrorRejectConditionsOnly = "ER";
	/** Never = NE */
	public static final String APPLICATIONACKTYPE_Never = "NE";
	/** Successful completion only = SU */
	public static final String APPLICATIONACKTYPE_SuccessfulCompletionOnly = "SU";
	/** Set Application Acknowledgment Type.
		@param ApplicationACKType 
		This field contains the conditions under which application acknowledgments are required to be returned in response to this message
	  */
	public void setApplicationACKType (String ApplicationACKType)
	{

		if (ApplicationACKType == null || ApplicationACKType.equals("AL") || ApplicationACKType.equals("ER") || ApplicationACKType.equals("NE") || ApplicationACKType.equals("SU")); else throw new IllegalArgumentException ("ApplicationACKType Invalid value - " + ApplicationACKType + " - Reference_ID=1200353 - AL - ER - NE - SU");		set_Value (COLUMNNAME_ApplicationACKType, ApplicationACKType);
	}

	/** Get Application Acknowledgment Type.
		@return This field contains the conditions under which application acknowledgments are required to be returned in response to this message
	  */
	public String getApplicationACKType () 
	{
		return (String)get_Value(COLUMNNAME_ApplicationACKType);
	}

	/** Set Application OID.
		@param appOID Application OID	  */
	public void setappOID (String appOID)
	{
		set_Value (COLUMNNAME_appOID, appOID);
	}

	/** Get Application OID.
		@return Application OID	  */
	public String getappOID () 
	{
		return (String)get_Value(COLUMNNAME_appOID);
	}

	/** AppOIDType AD_Reference_ID=1200467 */
	public static final int APPOIDTYPE_AD_Reference_ID=1200467;
	/** HL7 = HL7 */
	public static final String APPOIDTYPE_HL7 = "HL7";
	/** ISO = ISO */
	public static final String APPOIDTYPE_ISO = "ISO";
	/** Set Application OID Type.
		@param AppOIDType Application OID Type	  */
	public void setAppOIDType (String AppOIDType)
	{

		if (AppOIDType == null || AppOIDType.equals("HL7") || AppOIDType.equals("ISO")); else throw new IllegalArgumentException ("AppOIDType Invalid value - " + AppOIDType + " - Reference_ID=1200467 - HL7 - ISO");		set_Value (COLUMNNAME_AppOIDType, AppOIDType);
	}

	/** Get Application OID Type.
		@return Application OID Type	  */
	public String getAppOIDType () 
	{
		return (String)get_Value(COLUMNNAME_AppOIDType);
	}

	/** Set Channel.
		@param ChannelId Channel	  */
	public void setChannelId (String ChannelId)
	{
		set_Value (COLUMNNAME_ChannelId, ChannelId);
	}

	/** Get Channel.
		@return Channel	  */
	public String getChannelId () 
	{
		return (String)get_Value(COLUMNNAME_ChannelId);
	}

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
	}

	/** Set Component Separator.
		@param Component_Sep 
		Define a Component Separator characater for X12 messages
	  */
	public void setComponent_Sep (String Component_Sep)
	{
		set_Value (COLUMNNAME_Component_Sep, Component_Sep);
	}

	/** Get Component Separator.
		@return Define a Component Separator characater for X12 messages
	  */
	public String getComponent_Sep () 
	{
		return (String)get_Value(COLUMNNAME_Component_Sep);
	}

	/** Set Comp_Sep.
		@param Comp_Sep Comp_Sep	  */
	public void setComp_Sep (String Comp_Sep)
	{
		set_Value (COLUMNNAME_Comp_Sep, Comp_Sep);
	}

	/** Get Comp_Sep.
		@return Comp_Sep	  */
	public String getComp_Sep () 
	{
		return (String)get_Value(COLUMNNAME_Comp_Sep);
	}

	/** ConfType AD_Reference_ID=1200425 */
	public static final int CONFTYPE_AD_Reference_ID=1200425;
	/** Surveillance Report = W */
	public static final String CONFTYPE_SurveillanceReport = "W";
	/** Clinical Record CDA = C */
	public static final String CONFTYPE_ClinicalRecordCDA = "C";
	/** Clinical Record CCR = R */
	public static final String CONFTYPE_ClinicalRecordCCR = "R";
	/** Elegibility = E */
	public static final String CONFTYPE_Elegibility = "E";
	/** Reportable Lab Results = L */
	public static final String CONFTYPE_ReportableLabResults = "L";
	/** Inmunization Messaging = I */
	public static final String CONFTYPE_InmunizationMessaging = "I";
	/** RX Script = S */
	public static final String CONFTYPE_RXScript = "S";
	/** LabDAQ = Q */
	public static final String CONFTYPE_LabDAQ = "Q";
	/** Professional Claim = P */
	public static final String CONFTYPE_ProfessionalClaim = "P";
	/** Institutional Claim = T */
	public static final String CONFTYPE_InstitutionalClaim = "T";
	/** PIStatement = A */
	public static final String CONFTYPE_PIStatement = "A";
	/** Both Claim Types = B */
	public static final String CONFTYPE_BothClaimTypes = "B";
	/** Inpatient Claim = N */
	public static final String CONFTYPE_InpatientClaim = "N";
	/** Outpatient Institutional Claim = O */
	public static final String CONFTYPE_OutpatientInstitutionalClaim = "O";
	/** Outpatient Professional Claim = F */
	public static final String CONFTYPE_OutpatientProfessionalClaim = "F";
	/** Radiology Messaging = D */
	public static final String CONFTYPE_RadiologyMessaging = "D";
	/** Patient Administrative ADT = M */
	public static final String CONFTYPE_PatientAdministrativeADT = "M";
	/** Set Configuration Type.
		@param ConfType Configuration Type	  */
	public void setConfType (String ConfType)
	{

		if (ConfType == null || ConfType.equals("W") || ConfType.equals("C") || ConfType.equals("R") || ConfType.equals("E") || ConfType.equals("L") || ConfType.equals("I") || ConfType.equals("S") || ConfType.equals("Q") || ConfType.equals("P") || ConfType.equals("T") || ConfType.equals("A") || ConfType.equals("B") || ConfType.equals("N") || ConfType.equals("O") || ConfType.equals("F") || ConfType.equals("D")); else throw new IllegalArgumentException ("ConfType Invalid value - " + ConfType + " - Reference_ID=1200425 - W - C - R - E - L - I - S - Q - P - T - A - B - N - O - F - D");		set_Value (COLUMNNAME_ConfType, ConfType);
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public String getConfType () 
	{
		return (String)get_Value(COLUMNNAME_ConfType);
	}

	/** CreateInChannel AD_Reference_ID=1200447 */
	public static final int CREATEINCHANNEL_AD_Reference_ID=1200447;
	/** Create In Channel = N */
	public static final String CREATEINCHANNEL_CreateInChannel = "N";
	/** Recreate In Channel = Y */
	public static final String CREATEINCHANNEL_RecreateInChannel = "Y";
	/** Set Create In Channel.
		@param CreateInChannel Create In Channel	  */
	public void setCreateInChannel (String CreateInChannel)
	{

		if (CreateInChannel == null || CreateInChannel.equals("N") || CreateInChannel.equals("Y")); else throw new IllegalArgumentException ("CreateInChannel Invalid value - " + CreateInChannel + " - Reference_ID=1200447 - N - Y");		set_Value (COLUMNNAME_CreateInChannel, CreateInChannel);
	}

	/** Get Create In Channel.
		@return Create In Channel	  */
	public String getCreateInChannel () 
	{
		return (String)get_Value(COLUMNNAME_CreateInChannel);
	}

	/** CreateOutChannel AD_Reference_ID=1200445 */
	public static final int CREATEOUTCHANNEL_AD_Reference_ID=1200445;
	/** Create Out Channel = N */
	public static final String CREATEOUTCHANNEL_CreateOutChannel = "N";
	/** Recreate Out Channel = Y */
	public static final String CREATEOUTCHANNEL_RecreateOutChannel = "Y";
	/** Set Create Out Channel.
		@param CreateOutChannel Create Out Channel	  */
	public void setCreateOutChannel (String CreateOutChannel)
	{

		if (CreateOutChannel == null || CreateOutChannel.equals("N") || CreateOutChannel.equals("Y")); else throw new IllegalArgumentException ("CreateOutChannel Invalid value - " + CreateOutChannel + " - Reference_ID=1200445 - N - Y");		set_Value (COLUMNNAME_CreateOutChannel, CreateOutChannel);
	}

	/** Get Create Out Channel.
		@return Create Out Channel	  */
	public String getCreateOutChannel () 
	{
		return (String)get_Value(COLUMNNAME_CreateOutChannel);
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

	public I_EXME_InterfaceSender getEXME_InterfaceSender() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_InterfaceSender.Table_Name);
        I_EXME_InterfaceSender result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_InterfaceSender)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_InterfaceSender_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Iterfase Sender.
		@param EXME_InterfaceSender_ID 
		Iterfase Sender
	  */
	public void setEXME_InterfaceSender_ID (int EXME_InterfaceSender_ID)
	{
		if (EXME_InterfaceSender_ID < 1) 
			set_Value (COLUMNNAME_EXME_InterfaceSender_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_InterfaceSender_ID, Integer.valueOf(EXME_InterfaceSender_ID));
	}

	/** Get Iterfase Sender.
		@return Iterfase Sender
	  */
	public int getEXME_InterfaceSender_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InterfaceSender_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Facility OID.
		@param FacilityOID Facility OID	  */
	public void setFacilityOID (String FacilityOID)
	{
		set_Value (COLUMNNAME_FacilityOID, FacilityOID);
	}

	/** Get Facility OID.
		@return Facility OID	  */
	public String getFacilityOID () 
	{
		return (String)get_Value(COLUMNNAME_FacilityOID);
	}

	/** FacilityOIDType AD_Reference_ID=1200467 */
	public static final int FACILITYOIDTYPE_AD_Reference_ID=1200467;
	/** HL7 = HL7 */
	public static final String FACILITYOIDTYPE_HL7 = "HL7";
	/** ISO = ISO */
	public static final String FACILITYOIDTYPE_ISO = "ISO";
	/** Set Facility OID Type.
		@param FacilityOIDType Facility OID Type	  */
	public void setFacilityOIDType (String FacilityOIDType)
	{

		if (FacilityOIDType == null || FacilityOIDType.equals("HL7") || FacilityOIDType.equals("ISO")); else throw new IllegalArgumentException ("FacilityOIDType Invalid value - " + FacilityOIDType + " - Reference_ID=1200467 - HL7 - ISO");		set_Value (COLUMNNAME_FacilityOIDType, FacilityOIDType);
	}

	/** Get Facility OID Type.
		@return Facility OID Type	  */
	public String getFacilityOIDType () 
	{
		return (String)get_Value(COLUMNNAME_FacilityOIDType);
	}

	/** Set Field Separator.
		@param Field_Sep 
		Describe Field Separator character for X12 messages
	  */
	public void setField_Sep (String Field_Sep)
	{
		set_Value (COLUMNNAME_Field_Sep, Field_Sep);
	}

	/** Get Field Separator.
		@return Describe Field Separator character for X12 messages
	  */
	public String getField_Sep () 
	{
		return (String)get_Value(COLUMNNAME_Field_Sep);
	}

	public I_HL7_BPartner getHL7_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_BPartner.Table_Name);
        I_HL7_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Messaging Partner.
		@param HL7_BPartner_ID 
		Identifies a Messaging Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID)
	{
		if (HL7_BPartner_ID < 1) 
			set_Value (COLUMNNAME_HL7_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_BPartner_ID, Integer.valueOf(HL7_BPartner_ID));
	}

	/** Get Messaging Partner.
		@return Identifies a Messaging Partner
	  */
	public int getHL7_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Message Configuration.
		@param HL7_MessageConf_ID 
		Configuration of message and the process that generate them
	  */
	public void setHL7_MessageConf_ID (int HL7_MessageConf_ID)
	{
		if (HL7_MessageConf_ID < 1)
			 throw new IllegalArgumentException ("HL7_MessageConf_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_MessageConf_ID, Integer.valueOf(HL7_MessageConf_ID));
	}

	/** Get HL7 Message Configuration.
		@return Configuration of message and the process that generate them
	  */
	public int getHL7_MessageConf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageConf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_MessageDef getHL7_MessageDef() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_MessageDef.Table_Name);
        I_HL7_MessageDef result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_MessageDef)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_MessageDef_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Message Definition.
		@param HL7_MessageDef_ID Message Definition	  */
	public void setHL7_MessageDef_ID (int HL7_MessageDef_ID)
	{
		if (HL7_MessageDef_ID < 1)
			 throw new IllegalArgumentException ("HL7_MessageDef_ID is mandatory.");
		set_Value (COLUMNNAME_HL7_MessageDef_ID, Integer.valueOf(HL7_MessageDef_ID));
	}

	/** Get Message Definition.
		@return Message Definition	  */
	public int getHL7_MessageDef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageDef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Process getHL7_Process() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Process.Table_Name);
        I_HL7_Process result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Process)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Process_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Trigger Process.
		@param HL7_Process_ID 
		Process that generate HL7 Messages
	  */
	public void setHL7_Process_ID (int HL7_Process_ID)
	{
		if (HL7_Process_ID < 1) 
			set_Value (COLUMNNAME_HL7_Process_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Process_ID, Integer.valueOf(HL7_Process_ID));
	}

	/** Get Trigger Process.
		@return Process that generate HL7 Messages
	  */
	public int getHL7_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Has a Message Response.
		@param IsResponse Has a Message Response	  */
	public void setIsResponse (boolean IsResponse)
	{
		set_Value (COLUMNNAME_IsResponse, Boolean.valueOf(IsResponse));
	}

	/** Get Has a Message Response.
		@return Has a Message Response	  */
	public boolean isResponse () 
	{
		Object oo = get_Value(COLUMNNAME_IsResponse);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message Profile.
		@param MessageProfileID Message Profile	  */
	public void setMessageProfileID (String MessageProfileID)
	{
		set_Value (COLUMNNAME_MessageProfileID, MessageProfileID);
	}

	/** Get Message Profile.
		@return Message Profile	  */
	public String getMessageProfileID () 
	{
		return (String)get_Value(COLUMNNAME_MessageProfileID);
	}

	/** Set Message Profile OID.
		@param MessageProfileOID Message Profile OID	  */
	public void setMessageProfileOID (String MessageProfileOID)
	{
		set_Value (COLUMNNAME_MessageProfileOID, MessageProfileOID);
	}

	/** Get Message Profile OID.
		@return Message Profile OID	  */
	public String getMessageProfileOID () 
	{
		return (String)get_Value(COLUMNNAME_MessageProfileOID);
	}

	/** MessageProfileOIDType AD_Reference_ID=1200467 */
	public static final int MESSAGEPROFILEOIDTYPE_AD_Reference_ID=1200467;
	/** HL7 = HL7 */
	public static final String MESSAGEPROFILEOIDTYPE_HL7 = "HL7";
	/** ISO = ISO */
	public static final String MESSAGEPROFILEOIDTYPE_ISO = "ISO";
	/** Set Message Profile OID Type.
		@param MessageProfileOIDType Message Profile OID Type	  */
	public void setMessageProfileOIDType (String MessageProfileOIDType)
	{

		if (MessageProfileOIDType == null || MessageProfileOIDType.equals("HL7") || MessageProfileOIDType.equals("ISO")); else throw new IllegalArgumentException ("MessageProfileOIDType Invalid value - " + MessageProfileOIDType + " - Reference_ID=1200467 - HL7 - ISO");		set_Value (COLUMNNAME_MessageProfileOIDType, MessageProfileOIDType);
	}

	/** Get Message Profile OID Type.
		@return Message Profile OID Type	  */
	public String getMessageProfileOIDType () 
	{
		return (String)get_Value(COLUMNNAME_MessageProfileOIDType);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_ValueNoCheck (COLUMNNAME_Name, Name);
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

	/** ProcessingId AD_Reference_ID=1200370 */
	public static final int PROCESSINGID_AD_Reference_ID=1200370;
	/** P = P */
	public static final String PROCESSINGID_P = "P";
	/** Set Processing ID.
		@param ProcessingId 
		This field is used to decide whether to process the message as defined in HL7 Application (level 7) Processing rules
	  */
	public void setProcessingId (String ProcessingId)
	{

		if (ProcessingId == null || ProcessingId.equals("P")); else throw new IllegalArgumentException ("ProcessingId Invalid value - " + ProcessingId + " - Reference_ID=1200370 - P");		set_Value (COLUMNNAME_ProcessingId, ProcessingId);
	}

	/** Get Processing ID.
		@return This field is used to decide whether to process the message as defined in HL7 Application (level 7) Processing rules
	  */
	public String getProcessingId () 
	{
		return (String)get_Value(COLUMNNAME_ProcessingId);
	}

	/** Protocol AD_Reference_ID=1200429 */
	public static final int PROTOCOL_AD_Reference_ID=1200429;
	/** HL7 Version 2 = HL7V2 */
	public static final String PROTOCOL_HL7Version2 = "HL7V2";
	/** ASC X12 = X12 */
	public static final String PROTOCOL_ASCX12 = "X12";
	/** XML Extensible Markup Language = XML */
	public static final String PROTOCOL_XMLExtensibleMarkupLanguage = "XML";
	/** HL7 Version 3 = HL7V3 */
	public static final String PROTOCOL_HL7Version3 = "HL7V3";
	/** EDI Electronic data interchange = EDI */
	public static final String PROTOCOL_EDIElectronicDataInterchange = "EDI";
	/** Delimited Text = DELIM */
	public static final String PROTOCOL_DelimitedText = "DELIM";
	/** Continuity of Care Record (CCR) = CCR */
	public static final String PROTOCOL_ContinuityOfCareRecordCCR = "CCR";
	/** Clinical Document Architecture (CDA) = CDA */
	public static final String PROTOCOL_ClinicalDocumentArchitectureCDA = "CDA";
	/** Set Protocol.
		@param Protocol 
		Protocol
	  */
	public void setProtocol (String Protocol)
	{

		if (Protocol == null || Protocol.equals("HL7V2") || Protocol.equals("X12") || Protocol.equals("XML") || Protocol.equals("HL7V3") || Protocol.equals("EDI") || Protocol.equals("DELIM") || Protocol.equals("CCR") || Protocol.equals("CDA")); else throw new IllegalArgumentException ("Protocol Invalid value - " + Protocol + " - Reference_ID=1200429 - HL7V2 - X12 - XML - HL7V3 - EDI - DELIM - CCR - CDA");		set_Value (COLUMNNAME_Protocol, Protocol);
	}

	/** Get Protocol.
		@return Protocol
	  */
	public String getProtocol () 
	{
		return (String)get_Value(COLUMNNAME_Protocol);
	}

	/** Set Receiving Application.
		@param ReceivingApplication Receiving Application	  */
	public void setReceivingApplication (String ReceivingApplication)
	{
		set_Value (COLUMNNAME_ReceivingApplication, ReceivingApplication);
	}

	/** Get Receiving Application.
		@return Receiving Application	  */
	public String getReceivingApplication () 
	{
		return (String)get_Value(COLUMNNAME_ReceivingApplication);
	}

	/** Set Receiving Facility.
		@param ReceivingFacility 
		This field identifies the receiving application among multiple identical instances of the application
	  */
	public void setReceivingFacility (String ReceivingFacility)
	{
		set_Value (COLUMNNAME_ReceivingFacility, ReceivingFacility);
	}

	/** Get Receiving Facility.
		@return This field identifies the receiving application among multiple identical instances of the application
	  */
	public String getReceivingFacility () 
	{
		return (String)get_Value(COLUMNNAME_ReceivingFacility);
	}

	/** Set Segment Separator.
		@param Segment_Sep 
		Describe Segment separator character for X12 Messages
	  */
	public void setSegment_Sep (String Segment_Sep)
	{
		set_Value (COLUMNNAME_Segment_Sep, Segment_Sep);
	}

	/** Get Segment Separator.
		@return Describe Segment separator character for X12 Messages
	  */
	public String getSegment_Sep () 
	{
		return (String)get_Value(COLUMNNAME_Segment_Sep);
	}

	/** Set Template.
		@param Template 
		Message Template
	  */
	public void setTemplate (String Template)
	{
		set_Value (COLUMNNAME_Template, Template);
	}

	/** Get Template.
		@return Message Template
	  */
	public String getTemplate () 
	{
		return (String)get_Value(COLUMNNAME_Template);
	}

	/** TimeFormat AD_Reference_ID=1200253 */
	public static final int TIMEFORMAT_AD_Reference_ID=1200253;
	/** 24 Hour = HH24:MI:SS */
	public static final String TIMEFORMAT_24Hour = "HH24:MI:SS";
	/** 12 Hour = HH12:MI:SS */
	public static final String TIMEFORMAT_12Hour = "HH12:MI:SS";
	/** HL7 DTM = YYYYMMDD */
	public static final String TIMEFORMAT_HL7DTM = "YYYYMMDD";
	/** Set Time Format.
		@param TimeFormat 
		Time Format for HL7 Message
	  */
	public void setTimeFormat (String TimeFormat)
	{

		if (TimeFormat == null || TimeFormat.equals("HH24:MI:SS") || TimeFormat.equals("HH12:MI:SS") || TimeFormat.equals("YYYYMMDD")); else throw new IllegalArgumentException ("TimeFormat Invalid value - " + TimeFormat + " - Reference_ID=1200253 - HH24:MI:SS - HH12:MI:SS - YYYYMMDD");		set_Value (COLUMNNAME_TimeFormat, TimeFormat);
	}

	/** Get Time Format.
		@return Time Format for HL7 Message
	  */
	public String getTimeFormat () 
	{
		return (String)get_Value(COLUMNNAME_TimeFormat);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_ValueNoCheck (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}