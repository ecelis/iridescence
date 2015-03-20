/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DiagnosticoHdr_TRL
 *  @author Generated Class 
 *  @version Release 5.5
 */
public interface I_EXME_DiagnosticoHdr_TRL 
{

    /** TableName=EXME_DiagnosticoHdr_TRL */
    public static final String Table_Name = "EXME_DiagnosticoHdr_TRL";

    /** AD_Table_ID=1200874 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name EXME_DiagnosticoHdr_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoHdr_ID = "EXME_DiagnosticoHdr_ID";

	/** Set ICD.
	  * International Classification of Diseases
	  */
	public void setEXME_DiagnosticoHdr_ID (int EXME_DiagnosticoHdr_ID);

	/** Get ICD.
	  * International Classification of Diseases
	  */
	public int getEXME_DiagnosticoHdr_ID();

	public I_EXME_DiagnosticoHdr getEXME_DiagnosticoHdr() throws RuntimeException;

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
