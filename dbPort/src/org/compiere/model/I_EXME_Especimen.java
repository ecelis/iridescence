/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Especimen
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Especimen 
{

    /** TableName=EXME_Especimen */
    public static final String Table_Name = "EXME_Especimen";

    /** AD_Table_ID=1201081 */
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

    /** Column name AlternateCodingSystem */
    public static final String COLUMNNAME_AlternateCodingSystem = "AlternateCodingSystem";

	/** Set Alternate Coding System	  */
	public void setAlternateCodingSystem (String AlternateCodingSystem);

	/** Get Alternate Coding System	  */
	public String getAlternateCodingSystem();

    /** Column name AlternateCodingSystemVersion */
    public static final String COLUMNNAME_AlternateCodingSystemVersion = "AlternateCodingSystemVersion";

	/** Set Alternate Coding System Version	  */
	public void setAlternateCodingSystemVersion (String AlternateCodingSystemVersion);

	/** Get Alternate Coding System Version	  */
	public String getAlternateCodingSystemVersion();

    /** Column name AlternateIdentifier */
    public static final String COLUMNNAME_AlternateIdentifier = "AlternateIdentifier";

	/** Set Alternate Identifier	  */
	public void setAlternateIdentifier (String AlternateIdentifier);

	/** Get Alternate Identifier	  */
	public String getAlternateIdentifier();

    /** Column name AlternateValue */
    public static final String COLUMNNAME_AlternateValue = "AlternateValue";

	/** Set Alternate Value	  */
	public void setAlternateValue (String AlternateValue);

	/** Get Alternate Value	  */
	public String getAlternateValue();

    /** Column name CodingSystemVersion */
    public static final String COLUMNNAME_CodingSystemVersion = "CodingSystemVersion";

	/** Set Coding System Version	  */
	public void setCodingSystemVersion (String CodingSystemVersion);

	/** Get Coding System Version	  */
	public String getCodingSystemVersion();

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

    /** Column name EXME_Especimen_ID */
    public static final String COLUMNNAME_EXME_Especimen_ID = "EXME_Especimen_ID";

	/** Set Test Specimen.
	  * Test Specimen
	  */
	public void setEXME_Especimen_ID (int EXME_Especimen_ID);

	/** Get Test Specimen.
	  * Test Specimen
	  */
	public int getEXME_Especimen_ID();

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
