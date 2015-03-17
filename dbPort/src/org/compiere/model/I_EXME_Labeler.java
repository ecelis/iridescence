/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Labeler
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Labeler 
{

    /** TableName=EXME_Labeler */
    public static final String Table_Name = "EXME_Labeler";

    /** AD_Table_ID=1201061 */
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

    /** Column name EXME_Labeler_ID */
    public static final String COLUMNNAME_EXME_Labeler_ID = "EXME_Labeler_ID";

	/** Set Labeler.
	  * Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID);

	/** Get Labeler.
	  * Labeler
	  */
	public int getEXME_Labeler_ID();

    /** Column name IsVacuna */
    public static final String COLUMNNAME_IsVacuna = "IsVacuna";

	/** Set Is Vaccine	  */
	public void setIsVacuna (boolean IsVacuna);

	/** Get Is Vaccine	  */
	public boolean isVacuna();

    /** Column name LabelerID */
    public static final String COLUMNNAME_LabelerID = "LabelerID";

	/** Set Labeler	  */
	public void setLabelerID (String LabelerID);

	/** Get Labeler	  */
	public String getLabelerID();

    /** Column name Mfgname */
    public static final String COLUMNNAME_Mfgname = "Mfgname";

	/** Set Name.
	  * Name
	  */
	public void setMfgname (String Mfgname);

	/** Get Name.
	  * Name
	  */
	public String getMfgname();

    /** Column name MVXCode */
    public static final String COLUMNNAME_MVXCode = "MVXCode";

	/** Set Code MVX	  */
	public void setMVXCode (String MVXCode);

	/** Get Code MVX	  */
	public String getMVXCode();

    /** Column name Ndcmfgcode */
    public static final String COLUMNNAME_Ndcmfgcode = "Ndcmfgcode";

	/** Set Code.
	  * Code
	  */
	public void setNdcmfgcode (String Ndcmfgcode);

	/** Get Code.
	  * Code
	  */
	public String getNdcmfgcode();
}
