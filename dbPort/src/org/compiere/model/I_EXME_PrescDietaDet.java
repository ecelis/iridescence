/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrescDietaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PrescDietaDet 
{

    /** TableName=EXME_PrescDietaDet */
    public static final String Table_Name = "EXME_PrescDietaDet";

    /** AD_Table_ID=1200483 */
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

    /** Column name EXME_Dieta_ID */
    public static final String COLUMNNAME_EXME_Dieta_ID = "EXME_Dieta_ID";

	/** Set Diet	  */
	public void setEXME_Dieta_ID (int EXME_Dieta_ID);

	/** Get Diet	  */
	public int getEXME_Dieta_ID();

	public I_EXME_Dieta getEXME_Dieta() throws RuntimeException;

    /** Column name EXME_PrescDietaDet_ID */
    public static final String COLUMNNAME_EXME_PrescDietaDet_ID = "EXME_PrescDietaDet_ID";

	/** Set Dietetics Prescription Details.
	  * Dietetics Prescription Details
	  */
	public void setEXME_PrescDietaDet_ID (int EXME_PrescDietaDet_ID);

	/** Get Dietetics Prescription Details.
	  * Dietetics Prescription Details
	  */
	public int getEXME_PrescDietaDet_ID();

    /** Column name EXME_PrescDieta_ID */
    public static final String COLUMNNAME_EXME_PrescDieta_ID = "EXME_PrescDieta_ID";

	/** Set Dietetics Prescription.
	  * Dietetics Prescription
	  */
	public void setEXME_PrescDieta_ID (int EXME_PrescDieta_ID);

	/** Get Dietetics Prescription.
	  * Dietetics Prescription
	  */
	public int getEXME_PrescDieta_ID();

	public I_EXME_PrescDieta getEXME_PrescDieta() throws RuntimeException;

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();
}
