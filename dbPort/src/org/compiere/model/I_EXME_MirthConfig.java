/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MirthConfig
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_MirthConfig 
{

    /** TableName=EXME_MirthConfig */
    public static final String Table_Name = "EXME_MirthConfig";

    /** AD_Table_ID=1200638 */
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

    /** Column name CreateChannel */
    public static final String COLUMNNAME_CreateChannel = "CreateChannel";

	/** Set Create Channel	  */
	public void setCreateChannel (String CreateChannel);

	/** Get Create Channel	  */
	public String getCreateChannel();

    /** Column name dbhost */
    public static final String COLUMNNAME_dbhost = "dbhost";

	/** Set Mirth Database Host	  */
	public void setdbhost (String dbhost);

	/** Get Mirth Database Host	  */
	public String getdbhost();

    /** Column name dbname */
    public static final String COLUMNNAME_dbname = "dbname";

	/** Set Mirth Database Name	  */
	public void setdbname (String dbname);

	/** Get Mirth Database Name	  */
	public String getdbname();

    /** Column name dbport */
    public static final String COLUMNNAME_dbport = "dbport";

	/** Set Mirth Database Port	  */
	public void setdbport (BigDecimal dbport);

	/** Get Mirth Database Port	  */
	public BigDecimal getdbport();

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

    /** Column name EndpointURI */
    public static final String COLUMNNAME_EndpointURI = "EndpointURI";

	/** Set Service Endpoint URI.
	  * Service Endpoint URI
	  */
	public void setEndpointURI (String EndpointURI);

	/** Get Service Endpoint URI.
	  * Service Endpoint URI
	  */
	public String getEndpointURI();

    /** Column name EXME_MirthConfig_ID */
    public static final String COLUMNNAME_EXME_MirthConfig_ID = "EXME_MirthConfig_ID";

	/** Set Mirth Configuration	  */
	public void setEXME_MirthConfig_ID (int EXME_MirthConfig_ID);

	/** Get Mirth Configuration	  */
	public int getEXME_MirthConfig_ID();

    /** Column name GetMethods */
    public static final String COLUMNNAME_GetMethods = "GetMethods";

	/** Set Get Methods.
	  * Get Soap Methods 
	  */
	public void setGetMethods (String GetMethods);

	/** Get Get Methods.
	  * Get Soap Methods 
	  */
	public String getGetMethods();

    /** Column name Method */
    public static final String COLUMNNAME_Method = "Method";

	/** Set Method.
	  * Soap Method
	  */
	public void setMethod (String Method);

	/** Get Method.
	  * Soap Method
	  */
	public String getMethod();

    /** Column name MirthHost */
    public static final String COLUMNNAME_MirthHost = "MirthHost";

	/** Set Mirth Host	  */
	public void setMirthHost (String MirthHost);

	/** Get Mirth Host	  */
	public String getMirthHost();

    /** Column name MirthPassword */
    public static final String COLUMNNAME_MirthPassword = "MirthPassword";

	/** Set Mirth Password	  */
	public void setMirthPassword (String MirthPassword);

	/** Get Mirth Password	  */
	public String getMirthPassword();

    /** Column name MirthPort */
    public static final String COLUMNNAME_MirthPort = "MirthPort";

	/** Set Mirth Port	  */
	public void setMirthPort (String MirthPort);

	/** Get Mirth Port	  */
	public String getMirthPort();

    /** Column name MirthUser */
    public static final String COLUMNNAME_MirthUser = "MirthUser";

	/** Set Mirth User	  */
	public void setMirthUser (String MirthUser);

	/** Get Mirth User	  */
	public String getMirthUser();

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

    /** Column name SoapActionURI */
    public static final String COLUMNNAME_SoapActionURI = "SoapActionURI";

	/** Set Soap Action URI	  */
	public void setSoapActionURI (String SoapActionURI);

	/** Get Soap Action URI	  */
	public String getSoapActionURI();

    /** Column name SoapEnvelope */
    public static final String COLUMNNAME_SoapEnvelope = "SoapEnvelope";

	/** Set Soap Envelope	  */
	public void setSoapEnvelope (String SoapEnvelope);

	/** Get Soap Envelope	  */
	public String getSoapEnvelope();

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

    /** Column name WSDLPath */
    public static final String COLUMNNAME_WSDLPath = "WSDLPath";

	/** Set WSDL Path	  */
	public void setWSDLPath (String WSDLPath);

	/** Get WSDL Path	  */
	public String getWSDLPath();
}
