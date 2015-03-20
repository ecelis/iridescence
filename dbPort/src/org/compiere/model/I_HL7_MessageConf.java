/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_MessageConf
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_HL7_MessageConf 
{

    /** TableName=HL7_MessageConf */
    public static final String Table_Name = "HL7_MessageConf";

    /** AD_Table_ID=1200602 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AcceptACKType */
    public static final String COLUMNNAME_AcceptACKType = "AcceptACKType";

	/** Set Accept Acknowledgment Type.
	  * This field identifies the conditions under which accept acknowledgments are required to be returned in response to this message
	  */
	public void setAcceptACKType (String AcceptACKType);

	/** Get Accept Acknowledgment Type.
	  * This field identifies the conditions under which accept acknowledgments are required to be returned in response to this message
	  */
	public String getAcceptACKType();

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (String AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public String getAccessLevel();

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

    /** Column name ApplicationACKType */
    public static final String COLUMNNAME_ApplicationACKType = "ApplicationACKType";

	/** Set Application Acknowledgment Type.
	  * This field contains the conditions under which application acknowledgments are required to be returned in response to this message
	  */
	public void setApplicationACKType (String ApplicationACKType);

	/** Get Application Acknowledgment Type.
	  * This field contains the conditions under which application acknowledgments are required to be returned in response to this message
	  */
	public String getApplicationACKType();

    /** Column name appOID */
    public static final String COLUMNNAME_appOID = "appOID";

	/** Set Application OID	  */
	public void setappOID (String appOID);

	/** Get Application OID	  */
	public String getappOID();

    /** Column name AppOIDType */
    public static final String COLUMNNAME_AppOIDType = "AppOIDType";

	/** Set Application OID Type	  */
	public void setAppOIDType (String AppOIDType);

	/** Get Application OID Type	  */
	public String getAppOIDType();

    /** Column name ChannelId */
    public static final String COLUMNNAME_ChannelId = "ChannelId";

	/** Set Channel	  */
	public void setChannelId (String ChannelId);

	/** Get Channel	  */
	public String getChannelId();

    /** Column name Classname */
    public static final String COLUMNNAME_Classname = "Classname";

	/** Set Classname.
	  * Java Classname
	  */
	public void setClassname (String Classname);

	/** Get Classname.
	  * Java Classname
	  */
	public String getClassname();

    /** Column name Component_Sep */
    public static final String COLUMNNAME_Component_Sep = "Component_Sep";

	/** Set Component Separator.
	  * Define a Component Separator characater for X12 messages
	  */
	public void setComponent_Sep (String Component_Sep);

	/** Get Component Separator.
	  * Define a Component Separator characater for X12 messages
	  */
	public String getComponent_Sep();

    /** Column name Comp_Sep */
    public static final String COLUMNNAME_Comp_Sep = "Comp_Sep";

	/** Set Comp_Sep	  */
	public void setComp_Sep (String Comp_Sep);

	/** Get Comp_Sep	  */
	public String getComp_Sep();

    /** Column name ConfType */
    public static final String COLUMNNAME_ConfType = "ConfType";

	/** Set Configuration Type	  */
	public void setConfType (String ConfType);

	/** Get Configuration Type	  */
	public String getConfType();

    /** Column name CreateInChannel */
    public static final String COLUMNNAME_CreateInChannel = "CreateInChannel";

	/** Set Create In Channel	  */
	public void setCreateInChannel (String CreateInChannel);

	/** Get Create In Channel	  */
	public String getCreateInChannel();

    /** Column name CreateOutChannel */
    public static final String COLUMNNAME_CreateOutChannel = "CreateOutChannel";

	/** Set Create Out Channel	  */
	public void setCreateOutChannel (String CreateOutChannel);

	/** Get Create Out Channel	  */
	public String getCreateOutChannel();

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

    /** Column name EXME_InterfaceSender_ID */
    public static final String COLUMNNAME_EXME_InterfaceSender_ID = "EXME_InterfaceSender_ID";

	/** Set Iterfase Sender.
	  * Iterfase Sender
	  */
	public void setEXME_InterfaceSender_ID (int EXME_InterfaceSender_ID);

	/** Get Iterfase Sender.
	  * Iterfase Sender
	  */
	public int getEXME_InterfaceSender_ID();

	public I_EXME_InterfaceSender getEXME_InterfaceSender() throws RuntimeException;

    /** Column name FacilityOID */
    public static final String COLUMNNAME_FacilityOID = "FacilityOID";

	/** Set Facility OID	  */
	public void setFacilityOID (String FacilityOID);

	/** Get Facility OID	  */
	public String getFacilityOID();

    /** Column name FacilityOIDType */
    public static final String COLUMNNAME_FacilityOIDType = "FacilityOIDType";

	/** Set Facility OID Type	  */
	public void setFacilityOIDType (String FacilityOIDType);

	/** Get Facility OID Type	  */
	public String getFacilityOIDType();

    /** Column name Field_Sep */
    public static final String COLUMNNAME_Field_Sep = "Field_Sep";

	/** Set Field Separator.
	  * Describe Field Separator character for X12 messages
	  */
	public void setField_Sep (String Field_Sep);

	/** Get Field Separator.
	  * Describe Field Separator character for X12 messages
	  */
	public String getField_Sep();

    /** Column name HL7_BPartner_ID */
    public static final String COLUMNNAME_HL7_BPartner_ID = "HL7_BPartner_ID";

	/** Set Messaging Partner.
	  * Identifies a Messaging Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID);

	/** Get Messaging Partner.
	  * Identifies a Messaging Partner
	  */
	public int getHL7_BPartner_ID();

	public I_HL7_BPartner getHL7_BPartner() throws RuntimeException;

    /** Column name HL7_MessageConf_ID */
    public static final String COLUMNNAME_HL7_MessageConf_ID = "HL7_MessageConf_ID";

	/** Set HL7 Message Configuration.
	  * Configuration of message and the process that generate them
	  */
	public void setHL7_MessageConf_ID (int HL7_MessageConf_ID);

	/** Get HL7 Message Configuration.
	  * Configuration of message and the process that generate them
	  */
	public int getHL7_MessageConf_ID();

    /** Column name HL7_MessageDef_ID */
    public static final String COLUMNNAME_HL7_MessageDef_ID = "HL7_MessageDef_ID";

	/** Set Message Definition	  */
	public void setHL7_MessageDef_ID (int HL7_MessageDef_ID);

	/** Get Message Definition	  */
	public int getHL7_MessageDef_ID();

	public I_HL7_MessageDef getHL7_MessageDef() throws RuntimeException;

    /** Column name HL7_Process_ID */
    public static final String COLUMNNAME_HL7_Process_ID = "HL7_Process_ID";

	/** Set Trigger Process.
	  * Process that generate HL7 Messages
	  */
	public void setHL7_Process_ID (int HL7_Process_ID);

	/** Get Trigger Process.
	  * Process that generate HL7 Messages
	  */
	public int getHL7_Process_ID();

	public I_HL7_Process getHL7_Process() throws RuntimeException;

    /** Column name IsResponse */
    public static final String COLUMNNAME_IsResponse = "IsResponse";

	/** Set Has a Message Response	  */
	public void setIsResponse (boolean IsResponse);

	/** Get Has a Message Response	  */
	public boolean isResponse();

    /** Column name MessageProfileID */
    public static final String COLUMNNAME_MessageProfileID = "MessageProfileID";

	/** Set Message Profile	  */
	public void setMessageProfileID (String MessageProfileID);

	/** Get Message Profile	  */
	public String getMessageProfileID();

    /** Column name MessageProfileOID */
    public static final String COLUMNNAME_MessageProfileOID = "MessageProfileOID";

	/** Set Message Profile OID	  */
	public void setMessageProfileOID (String MessageProfileOID);

	/** Get Message Profile OID	  */
	public String getMessageProfileOID();

    /** Column name MessageProfileOIDType */
    public static final String COLUMNNAME_MessageProfileOIDType = "MessageProfileOIDType";

	/** Set Message Profile OID Type	  */
	public void setMessageProfileOIDType (String MessageProfileOIDType);

	/** Get Message Profile OID Type	  */
	public String getMessageProfileOIDType();

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

    /** Column name ProcessingId */
    public static final String COLUMNNAME_ProcessingId = "ProcessingId";

	/** Set Processing ID.
	  * This field is used to decide whether to process the message as defined in HL7 Application (level 7) Processing rules
	  */
	public void setProcessingId (String ProcessingId);

	/** Get Processing ID.
	  * This field is used to decide whether to process the message as defined in HL7 Application (level 7) Processing rules
	  */
	public String getProcessingId();

    /** Column name Protocol */
    public static final String COLUMNNAME_Protocol = "Protocol";

	/** Set Protocol.
	  * Protocol
	  */
	public void setProtocol (String Protocol);

	/** Get Protocol.
	  * Protocol
	  */
	public String getProtocol();

    /** Column name ReceivingApplication */
    public static final String COLUMNNAME_ReceivingApplication = "ReceivingApplication";

	/** Set Receiving Application	  */
	public void setReceivingApplication (String ReceivingApplication);

	/** Get Receiving Application	  */
	public String getReceivingApplication();

    /** Column name ReceivingFacility */
    public static final String COLUMNNAME_ReceivingFacility = "ReceivingFacility";

	/** Set Receiving Facility.
	  * This field identifies the receiving application among multiple identical instances of the application
	  */
	public void setReceivingFacility (String ReceivingFacility);

	/** Get Receiving Facility.
	  * This field identifies the receiving application among multiple identical instances of the application
	  */
	public String getReceivingFacility();

    /** Column name Segment_Sep */
    public static final String COLUMNNAME_Segment_Sep = "Segment_Sep";

	/** Set Segment Separator.
	  * Describe Segment separator character for X12 Messages
	  */
	public void setSegment_Sep (String Segment_Sep);

	/** Get Segment Separator.
	  * Describe Segment separator character for X12 Messages
	  */
	public String getSegment_Sep();

    /** Column name Template */
    public static final String COLUMNNAME_Template = "Template";

	/** Set Template.
	  * Message Template
	  */
	public void setTemplate (String Template);

	/** Get Template.
	  * Message Template
	  */
	public String getTemplate();

    /** Column name TimeFormat */
    public static final String COLUMNNAME_TimeFormat = "TimeFormat";

	/** Set Time Format.
	  * Time Format for HL7 Message
	  */
	public void setTimeFormat (String TimeFormat);

	/** Get Time Format.
	  * Time Format for HL7 Message
	  */
	public String getTimeFormat();

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

    /** Column name WhereClause */
    public static final String COLUMNNAME_WhereClause = "WhereClause";

	/** Set Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause);

	/** Get Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public String getWhereClause();
}
