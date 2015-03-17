/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_Subcomponent
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_Subcomponent 
{

    /** TableName=HL7_Subcomponent */
    public static final String Table_Name = "HL7_Subcomponent";

    /** AD_Table_ID=1200587 */
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

    /** Column name HL7_Component_ID */
    public static final String COLUMNNAME_HL7_Component_ID = "HL7_Component_ID";

	/** Set HL7 Component	  */
	public void setHL7_Component_ID (int HL7_Component_ID);

	/** Get HL7 Component	  */
	public int getHL7_Component_ID();

	public I_HL7_Component getHL7_Component() throws RuntimeException;

    /** Column name HL7_Subcomponent_ID */
    public static final String COLUMNNAME_HL7_Subcomponent_ID = "HL7_Subcomponent_ID";

	/** Set HL7 Subcomponent	  */
	public void setHL7_Subcomponent_ID (int HL7_Subcomponent_ID);

	/** Get HL7 Subcomponent	  */
	public int getHL7_Subcomponent_ID();

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
