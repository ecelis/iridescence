/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Dispense
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Dispense 
{

    /** TableName=EXME_Dispense */
    public static final String Table_Name = "EXME_Dispense";

    /** AD_Table_ID=1201379 */
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

    /** Column name DeliveredQty */
    public static final String COLUMNNAME_DeliveredQty = "DeliveredQty";

	/** Set Delivered qty	  */
	public void setDeliveredQty (BigDecimal DeliveredQty);

	/** Get Delivered qty	  */
	public BigDecimal getDeliveredQty();

    /** Column name EXME_Dispense_ID */
    public static final String COLUMNNAME_EXME_Dispense_ID = "EXME_Dispense_ID";

	/** Set Dispense	  */
	public void setEXME_Dispense_ID (int EXME_Dispense_ID);

	/** Get Dispense	  */
	public int getEXME_Dispense_ID();

    /** Column name EXME_Pharmacist_ID */
    public static final String COLUMNNAME_EXME_Pharmacist_ID = "EXME_Pharmacist_ID";

	/** Set Pharmacist	  */
	public void setEXME_Pharmacist_ID (int EXME_Pharmacist_ID);

	/** Get Pharmacist	  */
	public int getEXME_Pharmacist_ID();

	public I_EXME_Pharmacist getEXME_Pharmacist() throws RuntimeException;

    /** Column name EXME_PrescRXDet_ID */
    public static final String COLUMNNAME_EXME_PrescRXDet_ID = "EXME_PrescRXDet_ID";

	/** Set RXNorm Prescription Detail.
	  * RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID);

	/** Get RXNorm Prescription Detail.
	  * RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID();

	public I_EXME_PrescRXDet getEXME_PrescRXDet() throws RuntimeException;

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

    /** Column name UndeliveredQty */
    public static final String COLUMNNAME_UndeliveredQty = "UndeliveredQty";

	/** Set Undelivered Qty	  */
	public void setUndeliveredQty (BigDecimal UndeliveredQty);

	/** Get Undelivered Qty	  */
	public BigDecimal getUndeliveredQty();
}
