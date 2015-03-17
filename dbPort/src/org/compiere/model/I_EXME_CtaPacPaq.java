/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacPaq
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CtaPacPaq 
{

    /** TableName=EXME_CtaPacPaq */
    public static final String Table_Name = "EXME_CtaPacPaq";

    /** AD_Table_ID=1200071 */
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

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Patient Account Extension.
	  * Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Patient Account Extension.
	  * Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID();

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_CtaPacPaq_ID */
    public static final String COLUMNNAME_EXME_CtaPacPaq_ID = "EXME_CtaPacPaq_ID";

	/** Set Patient Account Package	  */
	public void setEXME_CtaPacPaq_ID (int EXME_CtaPacPaq_ID);

	/** Get Patient Account Package	  */
	public int getEXME_CtaPacPaq_ID();

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
