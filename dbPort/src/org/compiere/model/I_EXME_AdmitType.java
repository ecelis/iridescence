/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AdmitType
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_AdmitType 
{

    /** TableName=EXME_AdmitType */
    public static final String Table_Name = "EXME_AdmitType";

    /** AD_Table_ID=1201175 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_AdmitType_ID */
    public static final String COLUMNNAME_EXME_AdmitType_ID = "EXME_AdmitType_ID";

	/** Set Admit Type.
	  * Admit Type ot the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID);

	/** Get Admit Type.
	  * Admit Type ot the Patient account
	  */
	public int getEXME_AdmitType_ID();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsNewBorn */
    public static final String COLUMNNAME_IsNewBorn = "IsNewBorn";

	/** Set Is New Born.
	  * Is New Born
	  */
	public void setIsNewBorn (boolean IsNewBorn);

	/** Get Is New Born.
	  * Is New Born
	  */
	public boolean isNewBorn();

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
