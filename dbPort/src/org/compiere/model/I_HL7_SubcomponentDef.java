/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_SubcomponentDef
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_SubcomponentDef 
{

    /** TableName=HL7_SubcomponentDef */
    public static final String Table_Name = "HL7_SubcomponentDef";

    /** AD_Table_ID=1200831 */
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

    /** Column name HL7_ComponentDef_ID */
    public static final String COLUMNNAME_HL7_ComponentDef_ID = "HL7_ComponentDef_ID";

	/** Set HL7 Component Definition	  */
	public void setHL7_ComponentDef_ID (int HL7_ComponentDef_ID);

	/** Get HL7 Component Definition	  */
	public int getHL7_ComponentDef_ID();

	public I_HL7_ComponentDef getHL7_ComponentDef() throws RuntimeException;

    /** Column name HL7_LinkedTable_ID */
    public static final String COLUMNNAME_HL7_LinkedTable_ID = "HL7_LinkedTable_ID";

	/** Set Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID);

	/** Get Linked Tables HL7	  */
	public int getHL7_LinkedTable_ID();

    /** Column name HL7_SubcomponentDef_ID */
    public static final String COLUMNNAME_HL7_SubcomponentDef_ID = "HL7_SubcomponentDef_ID";

	/** Set HL7 Subcomponent Definition	  */
	public void setHL7_SubcomponentDef_ID (int HL7_SubcomponentDef_ID);

	/** Get HL7 Subcomponent Definition	  */
	public int getHL7_SubcomponentDef_ID();

    /** Column name HL7_Subcomponent_ID */
    public static final String COLUMNNAME_HL7_Subcomponent_ID = "HL7_Subcomponent_ID";

	/** Set HL7 Subcomponent	  */
	public void setHL7_Subcomponent_ID (int HL7_Subcomponent_ID);

	/** Get HL7 Subcomponent	  */
	public int getHL7_Subcomponent_ID();

	public I_HL7_Subcomponent getHL7_Subcomponent() throws RuntimeException;

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
