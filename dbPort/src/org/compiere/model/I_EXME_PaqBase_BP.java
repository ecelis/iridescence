/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PaqBase_BP
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PaqBase_BP 
{

    /** TableName=EXME_PaqBase_BP */
    public static final String Table_Name = "EXME_PaqBase_BP";

    /** AD_Table_ID=1200139 */
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

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name EXME_PaqBase_BP_ID */
    public static final String COLUMNNAME_EXME_PaqBase_BP_ID = "EXME_PaqBase_BP_ID";

	/** Set Business Partner - Base Package.
	  * Business Partner - Base Package
	  */
	public void setEXME_PaqBase_BP_ID (int EXME_PaqBase_BP_ID);

	/** Get Business Partner - Base Package.
	  * Business Partner - Base Package
	  */
	public int getEXME_PaqBase_BP_ID();

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException;
}
