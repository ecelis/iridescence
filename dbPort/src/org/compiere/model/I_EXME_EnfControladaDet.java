/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EnfControladaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EnfControladaDet 
{

    /** TableName=EXME_EnfControladaDet */
    public static final String Table_Name = "EXME_EnfControladaDet";

    /** AD_Table_ID=1200494 */
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

    /** Column name EXME_EnfControladaDet_ID */
    public static final String COLUMNNAME_EXME_EnfControladaDet_ID = "EXME_EnfControladaDet_ID";

	/** Set Controlled Illness Detail.
	  * Controlled Illness Detail
	  */
	public void setEXME_EnfControladaDet_ID (int EXME_EnfControladaDet_ID);

	/** Get Controlled Illness Detail.
	  * Controlled Illness Detail
	  */
	public int getEXME_EnfControladaDet_ID();

    /** Column name EXME_EnfControlada_ID */
    public static final String COLUMNNAME_EXME_EnfControlada_ID = "EXME_EnfControlada_ID";

	/** Set Controlled Illness.
	  * Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID);

	/** Get Controlled Illness.
	  * Controlled Illness
	  */
	public int getEXME_EnfControlada_ID();
}
