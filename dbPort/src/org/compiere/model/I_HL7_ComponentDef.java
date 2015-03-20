/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_ComponentDef
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_ComponentDef 
{

    /** TableName=HL7_ComponentDef */
    public static final String Table_Name = "HL7_ComponentDef";

    /** AD_Table_ID=1200830 */
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

    /** Column name HasSubcomponents */
    public static final String COLUMNNAME_HasSubcomponents = "HasSubcomponents";

	/** Set Has Subcomponents	  */
	public void setHasSubcomponents (boolean HasSubcomponents);

	/** Get Has Subcomponents	  */
	public boolean isHasSubcomponents();

    /** Column name HL7_ComponentDef_ID */
    public static final String COLUMNNAME_HL7_ComponentDef_ID = "HL7_ComponentDef_ID";

	/** Set HL7 Component Definition	  */
	public void setHL7_ComponentDef_ID (int HL7_ComponentDef_ID);

	/** Get HL7 Component Definition	  */
	public int getHL7_ComponentDef_ID();

    /** Column name HL7_Component_ID */
    public static final String COLUMNNAME_HL7_Component_ID = "HL7_Component_ID";

	/** Set HL7 Component	  */
	public void setHL7_Component_ID (int HL7_Component_ID);

	/** Get HL7 Component	  */
	public int getHL7_Component_ID();

	public I_HL7_Component getHL7_Component() throws RuntimeException;

    /** Column name HL7_FieldDef_ID */
    public static final String COLUMNNAME_HL7_FieldDef_ID = "HL7_FieldDef_ID";

	/** Set HL7 Field Definition	  */
	public void setHL7_FieldDef_ID (int HL7_FieldDef_ID);

	/** Get HL7 Field Definition	  */
	public int getHL7_FieldDef_ID();

	public I_HL7_FieldDef getHL7_FieldDef() throws RuntimeException;

    /** Column name HL7_FieldDefRep_ID */
    public static final String COLUMNNAME_HL7_FieldDefRep_ID = "HL7_FieldDefRep_ID";

	/** Set Field Defenition repetition	  */
	public void setHL7_FieldDefRep_ID (int HL7_FieldDefRep_ID);

	/** Get Field Defenition repetition	  */
	public int getHL7_FieldDefRep_ID();

	public I_HL7_FieldDefRep getHL7_FieldDefRep() throws RuntimeException;

    /** Column name HL7_LinkedTable_ID */
    public static final String COLUMNNAME_HL7_LinkedTable_ID = "HL7_LinkedTable_ID";

	/** Set Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID);

	/** Get Linked Tables HL7	  */
	public int getHL7_LinkedTable_ID();

    /** Column name Optionality */
    public static final String COLUMNNAME_Optionality = "Optionality";

	/** Set Optionality	  */
	public void setOptionality (String Optionality);

	/** Get Optionality	  */
	public String getOptionality();

    /** Column name Repeated */
    public static final String COLUMNNAME_Repeated = "Repeated";

	/** Set Repeated	  */
	public void setRepeated (int Repeated);

	/** Get Repeated	  */
	public int getRepeated();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();
}
