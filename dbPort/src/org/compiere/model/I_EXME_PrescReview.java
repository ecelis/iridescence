/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrescReview
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_PrescReview 
{

    /** TableName=EXME_PrescReview */
    public static final String Table_Name = "EXME_PrescReview";

    /** AD_Table_ID=1201165 */
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

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Pharmacist_ID */
    public static final String COLUMNNAME_EXME_Pharmacist_ID = "EXME_Pharmacist_ID";

	/** Set Pharmacist	  */
	public void setEXME_Pharmacist_ID (int EXME_Pharmacist_ID);

	/** Get Pharmacist	  */
	public int getEXME_Pharmacist_ID();

	public I_EXME_Pharmacist getEXME_Pharmacist() throws RuntimeException;

    /** Column name EXME_PlanMed_ID */
    public static final String COLUMNNAME_EXME_PlanMed_ID = "EXME_PlanMed_ID";

	/** Set Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID);

	/** Get Medical Plan	  */
	public int getEXME_PlanMed_ID();

	public I_EXME_PlanMed getEXME_PlanMed() throws RuntimeException;

    /** Column name EXME_PrescReview_ID */
    public static final String COLUMNNAME_EXME_PrescReview_ID = "EXME_PrescReview_ID";

	/** Set Review of Prescription ID	  */
	public void setEXME_PrescReview_ID (int EXME_PrescReview_ID);

	/** Get Review of Prescription ID	  */
	public int getEXME_PrescReview_ID();

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
}
