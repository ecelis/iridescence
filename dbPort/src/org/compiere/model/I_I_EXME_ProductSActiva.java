/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_ProductSActiva
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_ProductSActiva 
{

    /** TableName=I_EXME_ProductSActiva */
    public static final String Table_Name = "I_EXME_ProductSActiva";

    /** AD_Table_ID=1000167 */
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

    /** Column name EXME_SActiva_ID */
    public static final String COLUMNNAME_EXME_SActiva_ID = "EXME_SActiva_ID";

	/** Set Active Substance.
	  * Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID);

	/** Get Active Substance.
	  * Active Substance
	  */
	public int getEXME_SActiva_ID();

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException;

    /** Column name EXME_SActiva_Value */
    public static final String COLUMNNAME_EXME_SActiva_Value = "EXME_SActiva_Value";

	/** Set Active Substance Code.
	  * Active Substance search Code
	  */
	public void setEXME_SActiva_Value (String EXME_SActiva_Value);

	/** Get Active Substance Code.
	  * Active Substance search Code
	  */
	public String getEXME_SActiva_Value();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_ProductSActiva_ID */
    public static final String COLUMNNAME_I_EXME_ProductSActiva_ID = "I_EXME_ProductSActiva_ID";

	/** Set Product - Imported Active Substance.
	  * Product -Imported Active Substance
	  */
	public void setI_EXME_ProductSActiva_ID (int I_EXME_ProductSActiva_ID);

	/** Get Product - Imported Active Substance.
	  * Product -Imported Active Substance
	  */
	public int getI_EXME_ProductSActiva_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

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

    /** Column name M_Product_Value */
    public static final String COLUMNNAME_M_Product_Value = "M_Product_Value";

	/** Set Product Code.
	  * product search Code
	  */
	public void setM_Product_Value (String M_Product_Value);

	/** Get Product Code.
	  * product search Code
	  */
	public String getM_Product_Value();

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
}
