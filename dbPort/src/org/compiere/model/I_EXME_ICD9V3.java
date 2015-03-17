/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ICD9V3
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_ICD9V3 
{

    /** TableName=EXME_ICD9V3 */
    public static final String Table_Name = "EXME_ICD9V3";

    /** AD_Table_ID=1201212 */
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

    /** Column name EXME_ICD9V3_ID */
    public static final String COLUMNNAME_EXME_ICD9V3_ID = "EXME_ICD9V3_ID";

	/** Set ICD 9 Vol. 3.
	  * ICD 9 Vol. 3
	  */
	public void setEXME_ICD9V3_ID (int EXME_ICD9V3_ID);

	/** Get ICD 9 Vol. 3.
	  * ICD 9 Vol. 3
	  */
	public int getEXME_ICD9V3_ID();

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

    /** Column name Valid_From */
    public static final String COLUMNNAME_Valid_From = "Valid_From";

	/** Set Valid from	  */
	public void setValid_From (Timestamp Valid_From);

	/** Get Valid from	  */
	public Timestamp getValid_From();

    /** Column name Valid_To */
    public static final String COLUMNNAME_Valid_To = "Valid_To";

	/** Set Valid to	  */
	public void setValid_To (Timestamp Valid_To);

	/** Get Valid to	  */
	public Timestamp getValid_To();

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
