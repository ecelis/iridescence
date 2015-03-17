/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Parentesco
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Parentesco 
{

    /** TableName=EXME_Parentesco */
    public static final String Table_Name = "EXME_Parentesco";

    /** AD_Table_ID=1000027 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_Parentesco_ID */
    public static final String COLUMNNAME_EXME_Parentesco_ID = "EXME_Parentesco_ID";

	/** Set Kinship.
	  * Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID);

	/** Get Kinship.
	  * Kinship
	  */
	public int getEXME_Parentesco_ID();

    /** Column name IsCloseRelative */
    public static final String COLUMNNAME_IsCloseRelative = "IsCloseRelative";

	/** Set Is close relative	  */
	public void setIsCloseRelative (boolean IsCloseRelative);

	/** Get Is close relative	  */
	public boolean isCloseRelative();

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

    /** Column name Type2 */
    public static final String COLUMNNAME_Type2 = "Type2";

	/** Set Type.
	  * Type
	  */
	public void setType2 (boolean Type2);

	/** Get Type.
	  * Type
	  */
	public boolean isType2();

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

    /** Column name Value_270 */
    public static final String COLUMNNAME_Value_270 = "Value_270";

	/** Set Value 270	  */
	public void setValue_270 (String Value_270);

	/** Get Value 270	  */
	public String getValue_270();

    /** Column name Value_837 */
    public static final String COLUMNNAME_Value_837 = "Value_837";

	/** Set Value 837	  */
	public void setValue_837 (String Value_837);

	/** Get Value 837	  */
	public String getValue_837();
}
