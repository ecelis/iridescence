/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_StopPolicy
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_StopPolicy 
{

    /** TableName=EXME_StopPolicy */
    public static final String Table_Name = "EXME_StopPolicy";

    /** AD_Table_ID=1201126 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name EXME_ProductFam_ID */
    public static final String COLUMNNAME_EXME_ProductFam_ID = "EXME_ProductFam_ID";

	/** Set Family Products.
	  * Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID);

	/** Get Family Products.
	  * Family Products
	  */
	public int getEXME_ProductFam_ID();

	public I_EXME_ProductFam getEXME_ProductFam() throws RuntimeException;

    /** Column name EXME_StopPolicy_ID */
    public static final String COLUMNNAME_EXME_StopPolicy_ID = "EXME_StopPolicy_ID";

	/** Set Stop Policy ID	  */
	public void setEXME_StopPolicy_ID (int EXME_StopPolicy_ID);

	/** Get Stop Policy ID	  */
	public int getEXME_StopPolicy_ID();

    /** Column name MaxAutoDuration */
    public static final String COLUMNNAME_MaxAutoDuration = "MaxAutoDuration";

	/** Set Max Automatic Duration	  */
	public void setMaxAutoDuration (int MaxAutoDuration);

	/** Get Max Automatic Duration	  */
	public int getMaxAutoDuration();
}
