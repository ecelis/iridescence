/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AutopsiaLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_AutopsiaLine 
{

    /** TableName=EXME_AutopsiaLine */
    public static final String Table_Name = "EXME_AutopsiaLine";

    /** AD_Table_ID=1200317 */
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

    /** Column name EXME_Autopsia_ID */
    public static final String COLUMNNAME_EXME_Autopsia_ID = "EXME_Autopsia_ID";

	/** Set Autopsy.
	  * Autopsy
	  */
	public void setEXME_Autopsia_ID (int EXME_Autopsia_ID);

	/** Get Autopsy.
	  * Autopsy
	  */
	public int getEXME_Autopsia_ID();

	public I_EXME_Autopsia getEXME_Autopsia() throws RuntimeException;

    /** Column name EXME_AutopsiaLine_ID */
    public static final String COLUMNNAME_EXME_AutopsiaLine_ID = "EXME_AutopsiaLine_ID";

	/** Set Autopsy Line.
	  * Autopsy Line
	  */
	public void setEXME_AutopsiaLine_ID (int EXME_AutopsiaLine_ID);

	/** Get Autopsy Line.
	  * Autopsy Line
	  */
	public int getEXME_AutopsiaLine_ID();

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

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

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();
}
