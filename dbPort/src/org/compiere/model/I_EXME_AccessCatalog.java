/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AccessCatalog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_AccessCatalog 
{

    /** TableName=EXME_AccessCatalog */
    public static final String Table_Name = "EXME_AccessCatalog";

    /** AD_Table_ID=1200300 */
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

    /** Column name EXME_AccessCatalog_ID */
    public static final String COLUMNNAME_EXME_AccessCatalog_ID = "EXME_AccessCatalog_ID";

	/** Set Access Catalog.
	  * Access Catalog
	  */
	public void setEXME_AccessCatalog_ID (int EXME_AccessCatalog_ID);

	/** Get Access Catalog.
	  * Access Catalog
	  */
	public int getEXME_AccessCatalog_ID();

    /** Column name MultiAccess */
    public static final String COLUMNNAME_MultiAccess = "MultiAccess";

	/** Set Multi Access.
	  * Multi Access
	  */
	public void setMultiAccess (int MultiAccess);

	/** Get Multi Access.
	  * Multi Access
	  */
	public int getMultiAccess();

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

    /** Column name OriginalAccess */
    public static final String COLUMNNAME_OriginalAccess = "OriginalAccess";

	/** Set OriginalAccess	  */
	public void setOriginalAccess (int OriginalAccess);

	/** Get OriginalAccess	  */
	public int getOriginalAccess();

    /** Column name UniqueAccess */
    public static final String COLUMNNAME_UniqueAccess = "UniqueAccess";

	/** Set UniqueAccess.
	  * UniqueAccess
	  */
	public void setUniqueAccess (int UniqueAccess);

	/** Get UniqueAccess.
	  * UniqueAccess
	  */
	public int getUniqueAccess();

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
