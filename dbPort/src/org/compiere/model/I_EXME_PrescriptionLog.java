/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrescriptionLog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PrescriptionLog 
{

    /** TableName=EXME_PrescriptionLog */
    public static final String Table_Name = "EXME_PrescriptionLog";

    /** AD_Table_ID=1201091 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name Comentario */
    public static final String COLUMNNAME_Comentario = "Comentario";

	/** Set Comment	  */
	public void setComentario (String Comentario);

	/** Get Comment	  */
	public String getComentario();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (boolean Estatus);

	/** Get Status.
	  * Status
	  */
	public boolean isEstatus();

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

    /** Column name EXME_PrescriptionLog_ID */
    public static final String COLUMNNAME_EXME_PrescriptionLog_ID = "EXME_PrescriptionLog_ID";

	/** Set Patient Account Without Medication.
	  * Patient Account Without Medication
	  */
	public void setEXME_PrescriptionLog_ID (int EXME_PrescriptionLog_ID);

	/** Get Patient Account Without Medication.
	  * Patient Account Without Medication
	  */
	public int getEXME_PrescriptionLog_ID();
}
