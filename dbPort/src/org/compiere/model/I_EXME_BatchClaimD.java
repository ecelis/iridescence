/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BatchClaimD
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_BatchClaimD 
{

    /** TableName=EXME_BatchClaimD */
    public static final String Table_Name = "EXME_BatchClaimD";

    /** AD_Table_ID=1201173 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name EXME_BatchClaimD_ID */
    public static final String COLUMNNAME_EXME_BatchClaimD_ID = "EXME_BatchClaimD_ID";

	/** Set EXME_BatchClaimD_ID	  */
	public void setEXME_BatchClaimD_ID (int EXME_BatchClaimD_ID);

	/** Get EXME_BatchClaimD_ID	  */
	public int getEXME_BatchClaimD_ID();

    /** Column name EXME_BatchClaimH_ID */
    public static final String COLUMNNAME_EXME_BatchClaimH_ID = "EXME_BatchClaimH_ID";

	/** Set EXME_BatchClaimH_ID	  */
	public void setEXME_BatchClaimH_ID (int EXME_BatchClaimH_ID);

	/** Get EXME_BatchClaimH_ID	  */
	public int getEXME_BatchClaimH_ID();

	public I_EXME_BatchClaimH getEXME_BatchClaimH() throws RuntimeException;

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Encounter Extension.
	  * Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Encounter Extension.
	  * Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID();

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();
}
