/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_SearchDefinition
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_SearchDefinition 
{

    /** TableName=AD_SearchDefinition */
    public static final String Table_Name = "AD_SearchDefinition";

    /** AD_Table_ID=1200666 */
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

    /** Column name AD_SearchDefinition_ID */
    public static final String COLUMNNAME_AD_SearchDefinition_ID = "AD_SearchDefinition_ID";

	/** Set AD_SearchDefinition_ID	  */
	public void setAD_SearchDefinition_ID (int AD_SearchDefinition_ID);

	/** Get AD_SearchDefinition_ID	  */
	public int getAD_SearchDefinition_ID();

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

    /** Column name AD_Window_ID */
    public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";

	/** Set Window.
	  * Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID);

	/** Get Window.
	  * Data entry or display window
	  */
	public int getAD_Window_ID();

    /** Column name DataType */
    public static final String COLUMNNAME_DataType = "DataType";

	/** Set Data Type.
	  * Type of data
	  */
	public void setDataType (String DataType);

	/** Get Data Type.
	  * Type of data
	  */
	public String getDataType();

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

    /** Column name PO_Window_ID */
    public static final String COLUMNNAME_PO_Window_ID = "PO_Window_ID";

	/** Set PO Window.
	  * Purchase Order Window
	  */
	public void setPO_Window_ID (int PO_Window_ID);

	/** Get PO Window.
	  * Purchase Order Window
	  */
	public int getPO_Window_ID();

    /** Column name Query */
    public static final String COLUMNNAME_Query = "Query";

	/** Set Query.
	  * SQL
	  */
	public void setQuery (String Query);

	/** Get Query.
	  * SQL
	  */
	public String getQuery();

    /** Column name SearchType */
    public static final String COLUMNNAME_SearchType = "SearchType";

	/** Set Search Type.
	  * Which kind of search is used (Query or Table)
	  */
	public void setSearchType (String SearchType);

	/** Get Search Type.
	  * Which kind of search is used (Query or Table)
	  */
	public String getSearchType();

    /** Column name TransactionCode */
    public static final String COLUMNNAME_TransactionCode = "TransactionCode";

	/** Set Transaction Code.
	  * The transaction code represents the search definition
	  */
	public void setTransactionCode (String TransactionCode);

	/** Get Transaction Code.
	  * The transaction code represents the search definition
	  */
	public String getTransactionCode();
}
