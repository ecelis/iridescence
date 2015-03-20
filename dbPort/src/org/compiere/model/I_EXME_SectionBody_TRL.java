/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SectionBody_TRL
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SectionBody_TRL 
{

    /** TableName=EXME_SectionBody_TRL */
    public static final String Table_Name = "EXME_SectionBody_TRL";

    /** AD_Table_ID=1200897 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

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

    /** Column name EXME_SectionBody_ID */
    public static final String COLUMNNAME_EXME_SectionBody_ID = "EXME_SectionBody_ID";

	/** Set Section Body	  */
	public void setEXME_SectionBody_ID (int EXME_SectionBody_ID);

	/** Get Section Body	  */
	public int getEXME_SectionBody_ID();

	public I_EXME_SectionBody getEXME_SectionBody() throws RuntimeException;

    /** Column name IsTranslated */
    public static final String COLUMNNAME_IsTranslated = "IsTranslated";

	/** Set Translated.
	  * This column is translated
	  */
	public void setIsTranslated (boolean IsTranslated);

	/** Get Translated.
	  * This column is translated
	  */
	public boolean isTranslated();

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
