/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BillingFilter
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_BillingFilter 
{

    /** TableName=EXME_BillingFilter */
    public static final String Table_Name = "EXME_BillingFilter";

    /** AD_Table_ID=1201303 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws RuntimeException;

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

    /** Column name AD_TableEngine_ID */
    public static final String COLUMNNAME_AD_TableEngine_ID = "AD_TableEngine_ID";

	/** Set Table Engine.
	  * Table where the engine search the records
	  */
	public void setAD_TableEngine_ID (int AD_TableEngine_ID);

	/** Get Table Engine.
	  * Table where the engine search the records
	  */
	public int getAD_TableEngine_ID();

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name Classname */
    public static final String COLUMNNAME_Classname = "Classname";

	/** Set Classname.
	  * Java Classname
	  */
	public void setClassname (String Classname);

	/** Get Classname.
	  * Java Classname
	  */
	public String getClassname();

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

    /** Column name DisplayColumn_ID */
    public static final String COLUMNNAME_DisplayColumn_ID = "DisplayColumn_ID";

	/** Set Display Column	  */
	public void setDisplayColumn_ID (int DisplayColumn_ID);

	/** Get Display Column	  */
	public int getDisplayColumn_ID();

    /** Column name EXME_BillingFilter_ID */
    public static final String COLUMNNAME_EXME_BillingFilter_ID = "EXME_BillingFilter_ID";

	/** Set Billing Rule Filter 	  */
	public void setEXME_BillingFilter_ID (int EXME_BillingFilter_ID);

	/** Get Billing Rule Filter 	  */
	public int getEXME_BillingFilter_ID();

    /** Column name FilterType */
    public static final String COLUMNNAME_FilterType = "FilterType";

	/** Set Filter Type.
	  * Filter Type for billing rules (rank or unique)
	  */
	public void setFilterType (String FilterType);

	/** Get Filter Type.
	  * Filter Type for billing rules (rank or unique)
	  */
	public String getFilterType();

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
