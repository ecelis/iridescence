/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Group_Diag
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Group_Diag 
{

    /** TableName=EXME_Group_Diag */
    public static final String Table_Name = "EXME_Group_Diag";

    /** AD_Table_ID=1201090 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_Group_Diag_ID */
    public static final String COLUMNNAME_EXME_Group_Diag_ID = "EXME_Group_Diag_ID";

	/** Set Medical diagnostic group	  */
	public void setEXME_Group_Diag_ID (int EXME_Group_Diag_ID);

	/** Get Medical diagnostic group	  */
	public int getEXME_Group_Diag_ID();

    /** Column name Stroke */
    public static final String COLUMNNAME_Stroke = "Stroke";

	/** Set Stroke	  */
	public void setStroke (String Stroke);

	/** Get Stroke	  */
	public String getStroke();
}
