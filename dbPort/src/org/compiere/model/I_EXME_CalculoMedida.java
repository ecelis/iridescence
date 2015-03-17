/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CalculoMedida
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CalculoMedida 
{

    /** TableName=EXME_CalculoMedida */
    public static final String Table_Name = "EXME_CalculoMedida";

    /** AD_Table_ID=1201087 */
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

    /** Column name EXME_CalculoMedida_ID */
    public static final String COLUMNNAME_EXME_CalculoMedida_ID = "EXME_CalculoMedida_ID";

	/** Set Automated Measure Calculation	  */
	public void setEXME_CalculoMedida_ID (int EXME_CalculoMedida_ID);

	/** Get Automated Measure Calculation	  */
	public int getEXME_CalculoMedida_ID();

    /** Column name IsInpatient */
    public static final String COLUMNNAME_IsInpatient = "IsInpatient";

	/** Set Is Inpatient.
	  * Is Inpatient
	  */
	public void setIsInpatient (boolean IsInpatient);

	/** Get Is Inpatient.
	  * Is Inpatient
	  */
	public boolean isInpatient();
}
