/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Delivery
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Delivery 
{

    /** TableName=A_Asset_Delivery */
    public static final String Table_Name = "A_Asset_Delivery";

    /** AD_Table_ID=541 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_Delivery_ID */
    public static final String COLUMNNAME_A_Asset_Delivery_ID = "A_Asset_Delivery_ID";

	/** Set Asset Delivery.
	  * Delivery of Asset
	  */
	public void setA_Asset_Delivery_ID (int A_Asset_Delivery_ID);

	/** Get Asset Delivery.
	  * Delivery of Asset
	  */
	public int getA_Asset_Delivery_ID();

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public I_A_Asset getA_Asset() throws RuntimeException;

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

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name DeliveryConfirmation */
    public static final String COLUMNNAME_DeliveryConfirmation = "DeliveryConfirmation";

	/** Set Delivery Confirmation.
	  * EMail Delivery confirmation
	  */
	public void setDeliveryConfirmation (String DeliveryConfirmation);

	/** Get Delivery Confirmation.
	  * EMail Delivery confirmation
	  */
	public String getDeliveryConfirmation();

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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

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

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_EstServRef_ID */
    public static final String COLUMNNAME_EXME_EstServRef_ID = "EXME_EstServRef_ID";

	/** Set Reference Service Station.
	  * It establishes a relationship to the service station.
	  */
	public void setEXME_EstServRef_ID (int EXME_EstServRef_ID);

	/** Get Reference Service Station.
	  * It establishes a relationship to the service station.
	  */
	public int getEXME_EstServRef_ID();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name IsTransfered */
    public static final String COLUMNNAME_IsTransfered = "IsTransfered";

	/** Set Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public void setIsTransfered (String IsTransfered);

	/** Get Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public String getIsTransfered();

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

    /** Column name MessageID */
    public static final String COLUMNNAME_MessageID = "MessageID";

	/** Set Message ID.
	  * EMail Message ID
	  */
	public void setMessageID (String MessageID);

	/** Get Message ID.
	  * EMail Message ID
	  */
	public String getMessageID();

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

	public I_M_InOutLine getM_InOutLine() throws RuntimeException;

    /** Column name MovementDate */
    public static final String COLUMNNAME_MovementDate = "MovementDate";

	/** Set Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate);

	/** Get Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate();

    /** Column name M_ProductDownload_ID */
    public static final String COLUMNNAME_M_ProductDownload_ID = "M_ProductDownload_ID";

	/** Set Product Download.
	  * Product downloads
	  */
	public void setM_ProductDownload_ID (int M_ProductDownload_ID);

	/** Get Product Download.
	  * Product downloads
	  */
	public int getM_ProductDownload_ID();

	public I_M_ProductDownload getM_ProductDownload() throws RuntimeException;

    /** Column name Referrer */
    public static final String COLUMNNAME_Referrer = "Referrer";

	/** Set Referrer.
	  * Referring web address
	  */
	public void setReferrer (String Referrer);

	/** Get Referrer.
	  * Referring web address
	  */
	public String getReferrer();

    /** Column name Remote_Addr */
    public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";

	/** Set Remote Addr.
	  * Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr);

	/** Get Remote Addr.
	  * Remote Address
	  */
	public String getRemote_Addr();

    /** Column name Remote_Host */
    public static final String COLUMNNAME_Remote_Host = "Remote_Host";

	/** Set Remote Host.
	  * Remote host Info
	  */
	public void setRemote_Host (String Remote_Host);

	/** Get Remote Host.
	  * Remote host Info
	  */
	public String getRemote_Host();

    /** Column name SerNo */
    public static final String COLUMNNAME_SerNo = "SerNo";

	/** Set Serial No.
	  * Product Serial Number 
	  */
	public void setSerNo (String SerNo);

	/** Get Serial No.
	  * Product Serial Number 
	  */
	public String getSerNo();

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL();

    /** Column name VersionNo */
    public static final String COLUMNNAME_VersionNo = "VersionNo";

	/** Set Version No.
	  * Version Number
	  */
	public void setVersionNo (String VersionNo);

	/** Get Version No.
	  * Version Number
	  */
	public String getVersionNo();
}