/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrintedFormatHdr
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PrintedFormatHdr 
{

    /** TableName=EXME_PrintedFormatHdr */
    public static final String Table_Name = "EXME_PrintedFormatHdr";

    /** AD_Table_ID=1201335 */
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

    /** Column name EXME_FormSectionHeader_ID */
    public static final String COLUMNNAME_EXME_FormSectionHeader_ID = "EXME_FormSectionHeader_ID";

	/** Set Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID);

	/** Get Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID();

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name EXME_PrintedFormatHdr_ID */
    public static final String COLUMNNAME_EXME_PrintedFormatHdr_ID = "EXME_PrintedFormatHdr_ID";

	/** Set Printed Format Header	  */
	public void setEXME_PrintedFormatHdr_ID (int EXME_PrintedFormatHdr_ID);

	/** Get Printed Format Header	  */
	public int getEXME_PrintedFormatHdr_ID();

    /** Column name IsComplete */
    public static final String COLUMNNAME_IsComplete = "IsComplete";

	/** Set Complete.
	  * It is complete
	  */
	public void setIsComplete (boolean IsComplete);

	/** Get Complete.
	  * It is complete
	  */
	public boolean isComplete();
}
