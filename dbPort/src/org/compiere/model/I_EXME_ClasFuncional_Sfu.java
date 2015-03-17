/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClasFuncional_Sfu
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClasFuncional_Sfu 
{

    /** TableName=EXME_ClasFuncional_Sfu */
    public static final String Table_Name = "EXME_ClasFuncional_Sfu";

    /** AD_Table_ID=1201364 */
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

    /** Column name EXME_ClasFuncional_Fun_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_Fun_ID = "EXME_ClasFuncional_Fun_ID";

	/** Set Function	  */
	public void setEXME_ClasFuncional_Fun_ID (int EXME_ClasFuncional_Fun_ID);

	/** Get Function	  */
	public int getEXME_ClasFuncional_Fun_ID();

    /** Column name EXME_ClasFuncional_Sfu_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_Sfu_ID = "EXME_ClasFuncional_Sfu_ID";

	/** Set Subfunction	  */
	public void setEXME_ClasFuncional_Sfu_ID (int EXME_ClasFuncional_Sfu_ID);

	/** Get Subfunction	  */
	public int getEXME_ClasFuncional_Sfu_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
