/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for IX_EXME_CtaPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_IX_EXME_CtaPac 
{

    /** TableName=IX_EXME_CtaPac */
    public static final String Table_Name = "IX_EXME_CtaPac";

    /** AD_Table_ID=1200257 */
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

    /** Column name IX_EXME_CtaPac_ID */
    public static final String COLUMNNAME_IX_EXME_CtaPac_ID = "IX_EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setIX_EXME_CtaPac_ID (int IX_EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getIX_EXME_CtaPac_ID();

    /** Column name NombreArchivoInterfase */
    public static final String COLUMNNAME_NombreArchivoInterfase = "NombreArchivoInterfase";

	/** Set Interface File Name	  */
	public void setNombreArchivoInterfase (String NombreArchivoInterfase);

	/** Get Interface File Name	  */
	public String getNombreArchivoInterfase();
}
