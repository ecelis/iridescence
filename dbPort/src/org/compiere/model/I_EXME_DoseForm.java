/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DoseForm
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_DoseForm 
{

    /** TableName=EXME_DoseForm */
    public static final String Table_Name = "EXME_DoseForm";

    /** AD_Table_ID=1201059 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name Abrev */
    public static final String COLUMNNAME_Abrev = "Abrev";

	/** Set Abbreviation.
	  * Abbreviation
	  */
	public void setAbrev (String Abrev);

	/** Get Abbreviation.
	  * Abbreviation
	  */
	public String getAbrev();

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

    /** Column name DfID */
    public static final String COLUMNNAME_DfID = "DfID";

	/** Set DfID	  */
	public void setDfID (int DfID);

	/** Get DfID	  */
	public int getDfID();

    /** Column name EXME_DoseForm_ID */
    public static final String COLUMNNAME_EXME_DoseForm_ID = "EXME_DoseForm_ID";

	/** Set DoseForm.
	  * DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID);

	/** Get DoseForm.
	  * DoseForm
	  */
	public int getEXME_DoseForm_ID();

    /** Column name NCITCode */
    public static final String COLUMNNAME_NCITCode = "NCITCode";

	/** Set NCITCode.
	  * National Cancer Institute thesaurus Code
	  */
	public void setNCITCode (String NCITCode);

	/** Get NCITCode.
	  * National Cancer Institute thesaurus Code
	  */
	public String getNCITCode();
}
