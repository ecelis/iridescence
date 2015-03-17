/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Cripto
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Cripto 
{

    /** TableName=EXME_Cripto */
    public static final String Table_Name = "EXME_Cripto";

    /** AD_Table_ID=1200486 */
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

    /** Column name EXME_Cripto_ID */
    public static final String COLUMNNAME_EXME_Cripto_ID = "EXME_Cripto_ID";

	/** Set EXME_Cripto_ID.
	  * EXME_Cripto_ID
	  */
	public void setEXME_Cripto_ID (int EXME_Cripto_ID);

	/** Get EXME_Cripto_ID.
	  * EXME_Cripto_ID
	  */
	public int getEXME_Cripto_ID();

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

    /** Column name StringIVBRaw */
    public static final String COLUMNNAME_StringIVBRaw = "StringIVBRaw";

	/** Set StringIVBRaw.
	  * StringIVBRaw
	  */
	public void setStringIVBRaw (String StringIVBRaw);

	/** Get StringIVBRaw.
	  * StringIVBRaw
	  */
	public String getStringIVBRaw();

    /** Column name StringIVB64 */
    public static final String COLUMNNAME_StringIVB64 = "StringIVB64";

	/** Set StringIVB64.
	  * StringIVB64
	  */
	public void setStringIVB64 (String StringIVB64);

	/** Get StringIVB64.
	  * StringIVB64
	  */
	public String getStringIVB64();

    /** Column name StringKeyB64 */
    public static final String COLUMNNAME_StringKeyB64 = "StringKeyB64";

	/** Set StringKeyB64.
	  * StringKeyB64
	  */
	public void setStringKeyB64 (String StringKeyB64);

	/** Get StringKeyB64.
	  * StringKeyB64
	  */
	public String getStringKeyB64();

    /** Column name StringKeyRaw */
    public static final String COLUMNNAME_StringKeyRaw = "StringKeyRaw";

	/** Set StringKeyRaw.
	  * StringKeyRaw
	  */
	public void setStringKeyRaw (String StringKeyRaw);

	/** Get StringKeyRaw.
	  * StringKeyRaw
	  */
	public String getStringKeyRaw();

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
