/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_ProductDownload
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_ProductDownload 
{

    /** TableName=M_ProductDownload */
    public static final String Table_Name = "M_ProductDownload";

    /** AD_Table_ID=777 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name DownloadURL */
    public static final String COLUMNNAME_DownloadURL = "DownloadURL";

	/** Set Download URL.
	  * URL of the Download files
	  */
	public void setDownloadURL (String DownloadURL);

	/** Get Download URL.
	  * URL of the Download files
	  */
	public String getDownloadURL();

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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

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
}
