/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tratamientos_Paq
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tratamientos_Paq 
{

    /** TableName=EXME_Tratamientos_Paq */
    public static final String Table_Name = "EXME_Tratamientos_Paq";

    /** AD_Table_ID=1201107 */
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

    /** Column name EXME_PaqBase_ID */
    public static final String COLUMNNAME_EXME_PaqBase_ID = "EXME_PaqBase_ID";

	/** Set Base Package.
	  * Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID);

	/** Get Base Package.
	  * Base Package
	  */
	public int getEXME_PaqBase_ID();

	public I_EXME_PaqBase getEXME_PaqBase() throws RuntimeException;

    /** Column name EXME_Tratamientos_Detalle_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Detalle_ID = "EXME_Tratamientos_Detalle_ID";

	/** Set Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID);

	/** Get Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID();

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException;

    /** Column name EXME_Tratamientos_Paq_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Paq_ID = "EXME_Tratamientos_Paq_ID";

	/** Set Treatment Packages	  */
	public void setEXME_Tratamientos_Paq_ID (int EXME_Tratamientos_Paq_ID);

	/** Get Treatment Packages	  */
	public int getEXME_Tratamientos_Paq_ID();

    /** Column name SessionNo */
    public static final String COLUMNNAME_SessionNo = "SessionNo";

	/** Set Session Number.
	  * Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo);

	/** Get Session Number.
	  * Session Number of a treatment
	  */
	public int getSessionNo();
}
