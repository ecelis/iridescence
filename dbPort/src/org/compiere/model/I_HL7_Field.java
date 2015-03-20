/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_Field
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_Field 
{

    /** TableName=HL7_Field */
    public static final String Table_Name = "HL7_Field";

    /** AD_Table_ID=1200585 */
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

    /** Column name DefaultValue */
    public static final String COLUMNNAME_DefaultValue = "DefaultValue";

	/** Set Default Logic.
	  * Default value hierarchy, separated by ;

	  */
	public void setDefaultValue (String DefaultValue);

	/** Get Default Logic.
	  * Default value hierarchy, separated by ;

	  */
	public String getDefaultValue();

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

    /** Column name HasComponents */
    public static final String COLUMNNAME_HasComponents = "HasComponents";

	/** Set Has Components	  */
	public void setHasComponents (boolean HasComponents);

	/** Get Has Components	  */
	public boolean isHasComponents();

    /** Column name HL7_Field_ID */
    public static final String COLUMNNAME_HL7_Field_ID = "HL7_Field_ID";

	/** Set HL7 Field.
	  * Field of an HL7 Segment
	  */
	public void setHL7_Field_ID (int HL7_Field_ID);

	/** Get HL7 Field.
	  * Field of an HL7 Segment
	  */
	public int getHL7_Field_ID();

    /** Column name HL7_Version_ID */
    public static final String COLUMNNAME_HL7_Version_ID = "HL7_Version_ID";

	/** Set HL7 Version.
	  * HL7 Version number
	  */
	public void setHL7_Version_ID (int HL7_Version_ID);

	/** Get HL7 Version.
	  * HL7 Version number
	  */
	public int getHL7_Version_ID();

	public I_HL7_Version getHL7_Version() throws RuntimeException;

    /** Column name IsDate */
    public static final String COLUMNNAME_IsDate = "IsDate";

	/** Set Is Date.
	  * Marks the field as a Date to get it formated as such
	  */
	public void setIsDate (boolean IsDate);

	/** Get Is Date.
	  * Marks the field as a Date to get it formated as such
	  */
	public boolean isDate();

    /** Column name IsIncremental */
    public static final String COLUMNNAME_IsIncremental = "IsIncremental";

	/** Set Is Incremental	  */
	public void setIsIncremental (boolean IsIncremental);

	/** Get Is Incremental	  */
	public boolean isIncremental();

    /** Column name IsRepeated */
    public static final String COLUMNNAME_IsRepeated = "IsRepeated";

	/** Set Is Repeated	  */
	public void setIsRepeated (boolean IsRepeated);

	/** Get Is Repeated	  */
	public boolean isRepeated();

    /** Column name IsVerified */
    public static final String COLUMNNAME_IsVerified = "IsVerified";

	/** Set Verified.
	  * The BOM configuration has been verified
	  */
	public void setIsVerified (String IsVerified);

	/** Get Verified.
	  * The BOM configuration has been verified
	  */
	public String getIsVerified();

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
