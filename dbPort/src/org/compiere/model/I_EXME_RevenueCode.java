/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RevenueCode
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_RevenueCode 
{

    /** TableName=EXME_RevenueCode */
    public static final String Table_Name = "EXME_RevenueCode";

    /** AD_Table_ID=1201122 */
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

    /** Column name EXME_RevenueCode_ID */
    public static final String COLUMNNAME_EXME_RevenueCode_ID = "EXME_RevenueCode_ID";

	/** Set Revenue Code ID	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID);

	/** Get Revenue Code ID	  */
	public int getEXME_RevenueCode_ID();

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

    /** Column name RV_Category */
    public static final String COLUMNNAME_RV_Category = "RV_Category";

	/** Set Rev. Code Category	  */
	public void setRV_Category (String RV_Category);

	/** Get Rev. Code Category	  */
	public String getRV_Category();

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
