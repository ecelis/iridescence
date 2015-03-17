/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_LinkedTableD
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_LinkedTableD 
{

    /** TableName=HL7_LinkedTableD */
    public static final String Table_Name = "HL7_LinkedTableD";

    /** AD_Table_ID=1200893 */
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

    /** Column name ComparisonOperator */
    public static final String COLUMNNAME_ComparisonOperator = "ComparisonOperator";

	/** Set Comparison Operator	  */
	public void setComparisonOperator (String ComparisonOperator);

	/** Get Comparison Operator	  */
	public String getComparisonOperator();

    /** Column name ComparisonValue */
    public static final String COLUMNNAME_ComparisonValue = "ComparisonValue";

	/** Set Comparison Value	  */
	public void setComparisonValue (String ComparisonValue);

	/** Get Comparison Value	  */
	public String getComparisonValue();

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

    /** Column name HL7_LinkedTableD_ID */
    public static final String COLUMNNAME_HL7_LinkedTableD_ID = "HL7_LinkedTableD_ID";

	/** Set Linked Table Detail	  */
	public void setHL7_LinkedTableD_ID (int HL7_LinkedTableD_ID);

	/** Get Linked Table Detail	  */
	public int getHL7_LinkedTableD_ID();

    /** Column name HL7_LinkedTable_ID */
    public static final String COLUMNNAME_HL7_LinkedTable_ID = "HL7_LinkedTable_ID";

	/** Set Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID);

	/** Get Linked Tables HL7	  */
	public int getHL7_LinkedTable_ID();

	public I_HL7_LinkedTable getHL7_LinkedTable() throws RuntimeException;

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
