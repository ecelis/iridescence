/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_BPartner
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_BPartner 
{

    /** TableName=HL7_BPartner */
    public static final String Table_Name = "HL7_BPartner";

    /** AD_Table_ID=1200601 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name ConnectorType */
    public static final String COLUMNNAME_ConnectorType = "ConnectorType";

	/** Set Connector Type.
	  * Idenfities the connection type
	  */
	public void setConnectorType (String ConnectorType);

	/** Get Connector Type.
	  * Idenfities the connection type
	  */
	public String getConnectorType();

    /** Column name HL7_BPartner_ID */
    public static final String COLUMNNAME_HL7_BPartner_ID = "HL7_BPartner_ID";

	/** Set HL7 Business Partner.
	  * Identifies a Business Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID);

	/** Get HL7 Business Partner.
	  * Identifies a Business Partner
	  */
	public int getHL7_BPartner_ID();

    /** Column name InboundConnector */
    public static final String COLUMNNAME_InboundConnector = "InboundConnector";

	/** Set Inbound Connector	  */
	public void setInboundConnector (String InboundConnector);

	/** Get Inbound Connector	  */
	public String getInboundConnector();

    /** Column name ReceivingApplication */
    public static final String COLUMNNAME_ReceivingApplication = "ReceivingApplication";

	/** Set Receiving Application	  */
	public void setReceivingApplication (String ReceivingApplication);

	/** Get Receiving Application	  */
	public String getReceivingApplication();

    /** Column name SendsMessages */
    public static final String COLUMNNAME_SendsMessages = "SendsMessages";

	/** Set Sends Messages	  */
	public void setSendsMessages (boolean SendsMessages);

	/** Get Sends Messages	  */
	public boolean isSendsMessages();
}
