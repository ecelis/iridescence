/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ChequeCancelado
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ChequeCancelado 
{

    /** TableName=EXME_ChequeCancelado */
    public static final String Table_Name = "EXME_ChequeCancelado";

    /** AD_Table_ID=1200871 */
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

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_ChequeCancelado_ID */
    public static final String COLUMNNAME_EXME_ChequeCancelado_ID = "EXME_ChequeCancelado_ID";

	/** Set Cancelled check.
	  * Contains chqueques have been canceled
	  */
	public void setEXME_ChequeCancelado_ID (int EXME_ChequeCancelado_ID);

	/** Get Cancelled check.
	  * Contains chqueques have been canceled
	  */
	public int getEXME_ChequeCancelado_ID();

    /** Column name IsCancelled */
    public static final String COLUMNNAME_IsCancelled = "IsCancelled";

	/** Set Cancelled.
	  * The transaction was cancelled
	  */
	public void setIsCancelled (String IsCancelled);

	/** Get Cancelled.
	  * The transaction was cancelled
	  */
	public String getIsCancelled();
}
