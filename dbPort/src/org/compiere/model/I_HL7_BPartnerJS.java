/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_BPartnerJS
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_HL7_BPartnerJS 
{

    /** TableName=HL7_BPartnerJS */
    public static final String Table_Name = "HL7_BPartnerJS";

    /** AD_Table_ID=1201018 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name HL7_BPartnerJS_ID */
    public static final String COLUMNNAME_HL7_BPartnerJS_ID = "HL7_BPartnerJS_ID";

	/** Set Connector HL7 Javascript	  */
	public void setHL7_BPartnerJS_ID (int HL7_BPartnerJS_ID);

	/** Get Connector HL7 Javascript	  */
	public int getHL7_BPartnerJS_ID();

    /** Column name IsInbound */
    public static final String COLUMNNAME_IsInbound = "IsInbound";

	/** Set Is Inbound	  */
	public void setIsInbound (boolean IsInbound);

	/** Get Is Inbound	  */
	public boolean isInbound();

    /** Column name Javascript */
    public static final String COLUMNNAME_Javascript = "Javascript";

	/** Set Javascript.
	  * Javascript code used to send the message
	  */
	public void setJavascript (String Javascript);

	/** Get Javascript.
	  * Javascript code used to send the message
	  */
	public String getJavascript();
}
