/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_IntervencionLoinc
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_IntervencionLoinc 
{

    /** TableName=EXME_IntervencionLoinc */
    public static final String Table_Name = "EXME_IntervencionLoinc";

    /** AD_Table_ID=1201333 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_IntervencionLoinc_ID */
    public static final String COLUMNNAME_EXME_IntervencionLoinc_ID = "EXME_IntervencionLoinc_ID";

	/** Set CPT - Loinc	  */
	public void setEXME_IntervencionLoinc_ID (int EXME_IntervencionLoinc_ID);

	/** Get CPT - Loinc	  */
	public int getEXME_IntervencionLoinc_ID();

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

	public I_EXME_Loinc getEXME_Loinc() throws RuntimeException;
}
