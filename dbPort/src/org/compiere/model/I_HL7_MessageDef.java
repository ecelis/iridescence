/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_MessageDef
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_MessageDef 
{

    /** TableName=HL7_MessageDef */
    public static final String Table_Name = "HL7_MessageDef";

    /** AD_Table_ID=1200588 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name CopyMessageDef */
    public static final String COLUMNNAME_CopyMessageDef = "CopyMessageDef";

	/** Set Copy Message Definition	  */
	public void setCopyMessageDef (String CopyMessageDef);

	/** Get Copy Message Definition	  */
	public String getCopyMessageDef();

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

    /** Column name HL7_GenerationTable_ID */
    public static final String COLUMNNAME_HL7_GenerationTable_ID = "HL7_GenerationTable_ID";

	/** Set Message Generation Table	  */
	public void setHL7_GenerationTable_ID (int HL7_GenerationTable_ID);

	/** Get Message Generation Table	  */
	public int getHL7_GenerationTable_ID();

	public I_HL7_GenerationTable getHL7_GenerationTable() throws RuntimeException;

    /** Column name HL7_MessageDef_ID */
    public static final String COLUMNNAME_HL7_MessageDef_ID = "HL7_MessageDef_ID";

	/** Set HL7 Message Definition	  */
	public void setHL7_MessageDef_ID (int HL7_MessageDef_ID);

	/** Get HL7 Message Definition	  */
	public int getHL7_MessageDef_ID();

    /** Column name HL7_Message_ID */
    public static final String COLUMNNAME_HL7_Message_ID = "HL7_Message_ID";

	/** Set HL7 Message	  */
	public void setHL7_Message_ID (int HL7_Message_ID);

	/** Get HL7 Message	  */
	public int getHL7_Message_ID();

	public I_HL7_Message getHL7_Message() throws RuntimeException;

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
