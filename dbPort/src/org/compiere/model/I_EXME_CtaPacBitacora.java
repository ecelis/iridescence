/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacBitacora
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CtaPacBitacora 
{

    /** TableName=EXME_CtaPacBitacora */
    public static final String Table_Name = "EXME_CtaPacBitacora";

    /** AD_Table_ID=1200284 */
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

    /** Column name EXME_CtaPacBitacora_ID */
    public static final String COLUMNNAME_EXME_CtaPacBitacora_ID = "EXME_CtaPacBitacora_ID";

	/** Set Patient Account Log.
	  * Description of the Patient Account Log
	  */
	public void setEXME_CtaPacBitacora_ID (int EXME_CtaPacBitacora_ID);

	/** Get Patient Account Log.
	  * Description of the Patient Account Log
	  */
	public int getEXME_CtaPacBitacora_ID();

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

    /** Column name EXME_PaqBase_Version_New_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_New_ID = "EXME_PaqBase_Version_New_ID";

	/** Set PackBase New Version	  */
	public void setEXME_PaqBase_Version_New_ID (int EXME_PaqBase_Version_New_ID);

	/** Get PackBase New Version	  */
	public int getEXME_PaqBase_Version_New_ID();

    /** Column name EXME_PaqBase_Version_Old_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_Old_ID = "EXME_PaqBase_Version_Old_ID";

	/** Set PackBase Old Version	  */
	public void setEXME_PaqBase_Version_Old_ID (int EXME_PaqBase_Version_Old_ID);

	/** Get PackBase Old Version	  */
	public int getEXME_PaqBase_Version_Old_ID();

    /** Column name Opcion */
    public static final String COLUMNNAME_Opcion = "Opcion";

	/** Set Option.
	  * Description of options
	  */
	public void setOpcion (String Opcion);

	/** Get Option.
	  * Description of options
	  */
	public String getOpcion();
}
