/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Payments_V
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Payments_V 
{

    /** TableName=EXME_Payments_V */
    public static final String Table_Name = "EXME_Payments_V";

    /** AD_Table_ID=1201302 */
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

    /** Column name BatchNo */
    public static final String COLUMNNAME_BatchNo = "BatchNo";

	/** Set BatchNo	  */
	public void setBatchNo (String BatchNo);

	/** Get BatchNo	  */
	public String getBatchNo();

    /** Column name EXME_Payments_V_ID */
    public static final String COLUMNNAME_EXME_Payments_V_ID = "EXME_Payments_V_ID";

	/** Set Payments View Id	  */
	public void setEXME_Payments_V_ID (int EXME_Payments_V_ID);

	/** Get Payments View Id	  */
	public int getEXME_Payments_V_ID();

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

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

    /** Column name Tot_Encounters */
    public static final String COLUMNNAME_Tot_Encounters = "Tot_Encounters";

	/** Set Tot_Encounters	  */
	public void setTot_Encounters (BigDecimal Tot_Encounters);

	/** Get Tot_Encounters	  */
	public BigDecimal getTot_Encounters();

    /** Column name TOT_Payment */
    public static final String COLUMNNAME_TOT_Payment = "TOT_Payment";

	/** Set TOT_Payment	  */
	public void setTOT_Payment (BigDecimal TOT_Payment);

	/** Get TOT_Payment	  */
	public BigDecimal getTOT_Payment();
}
