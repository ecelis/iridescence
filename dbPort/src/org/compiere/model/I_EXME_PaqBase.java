/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PaqBase
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PaqBase 
{

    /** TableName=EXME_PaqBase */
    public static final String Table_Name = "EXME_PaqBase";

    /** AD_Table_ID=1000091 */
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

    /** Column name EXME_PaqBase_ID */
    public static final String COLUMNNAME_EXME_PaqBase_ID = "EXME_PaqBase_ID";

	/** Set Base Package.
	  * Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID);

	/** Get Base Package.
	  * Base Package
	  */
	public int getEXME_PaqBase_ID();

    /** Column name IsMiniPack */
    public static final String COLUMNNAME_IsMiniPack = "IsMiniPack";

	/** Set Allows mini packages.
	  * Allows mini packages
	  */
	public void setIsMiniPack (boolean IsMiniPack);

	/** Get Allows mini packages.
	  * Allows mini packages
	  */
	public boolean isMiniPack();

    /** Column name IsUniversalComp */
    public static final String COLUMNNAME_IsUniversalComp = "IsUniversalComp";

	/** Set Is universal component	  */
	public void setIsUniversalComp (boolean IsUniversalComp);

	/** Get Is universal component	  */
	public boolean isUniversalComp();

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
