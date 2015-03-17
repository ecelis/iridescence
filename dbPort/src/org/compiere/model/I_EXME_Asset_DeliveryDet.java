/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Asset_DeliveryDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Asset_DeliveryDet 
{

    /** TableName=EXME_Asset_DeliveryDet */
    public static final String Table_Name = "EXME_Asset_DeliveryDet";

    /** AD_Table_ID=1200466 */
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

	public I_A_Asset_Delivery getA_Asset_Delivery() throws RuntimeException;

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

    /** Column name EXME_Asset_DeliveryDet_ID */
    public static final String COLUMNNAME_EXME_Asset_DeliveryDet_ID = "EXME_Asset_DeliveryDet_ID";

	/** Set Delivery Detail.
	  * Identifier of the detail of delivery
	  */
	public void setEXME_Asset_DeliveryDet_ID (int EXME_Asset_DeliveryDet_ID);

	/** Get Delivery Detail.
	  * Identifier of the detail of delivery
	  */
	public int getEXME_Asset_DeliveryDet_ID();

    /** Column name IsTransfered */
    public static final String COLUMNNAME_IsTransfered = "IsTransfered";

	/** Set Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public void setIsTransfered (boolean IsTransfered);

	/** Get Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public boolean isTransfered();

    /** Column name NumeroControl */
    public static final String COLUMNNAME_NumeroControl = "NumeroControl";

	/** Set Control Number.
	  * Contains the number of active contro
	  */
	public void setNumeroControl (String NumeroControl);

	/** Get Control Number.
	  * Contains the number of active contro
	  */
	public String getNumeroControl();
}
