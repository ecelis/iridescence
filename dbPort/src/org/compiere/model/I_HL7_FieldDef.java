/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_FieldDef
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_FieldDef 
{

    /** TableName=HL7_FieldDef */
    public static final String Table_Name = "HL7_FieldDef";

    /** AD_Table_ID=1200829 */
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

    /** Column name HasComponents */
    public static final String COLUMNNAME_HasComponents = "HasComponents";

	/** Set Has Components	  */
	public void setHasComponents (boolean HasComponents);

	/** Get Has Components	  */
	public boolean isHasComponents();

    /** Column name HL7_Evaluation_ID */
    public static final String COLUMNNAME_HL7_Evaluation_ID = "HL7_Evaluation_ID";

	/** Set HL7 Evaluation	  */
	public void setHL7_Evaluation_ID (int HL7_Evaluation_ID);

	/** Get HL7 Evaluation	  */
	public int getHL7_Evaluation_ID();

	public I_HL7_Evaluation getHL7_Evaluation() throws RuntimeException;

    /** Column name HL7_FieldDef_ID */
    public static final String COLUMNNAME_HL7_FieldDef_ID = "HL7_FieldDef_ID";

	/** Set HL7 Field Definition	  */
	public void setHL7_FieldDef_ID (int HL7_FieldDef_ID);

	/** Get HL7 Field Definition	  */
	public int getHL7_FieldDef_ID();

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

	public I_HL7_Field getHL7_Field() throws RuntimeException;

    /** Column name HL7_LinkedTable_ID */
    public static final String COLUMNNAME_HL7_LinkedTable_ID = "HL7_LinkedTable_ID";

	/** Set Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID);

	/** Get Linked Tables HL7	  */
	public int getHL7_LinkedTable_ID();

    /** Column name HL7_SegmentDef_ID */
    public static final String COLUMNNAME_HL7_SegmentDef_ID = "HL7_SegmentDef_ID";

	/** Set HL7 Segment Definition	  */
	public void setHL7_SegmentDef_ID (int HL7_SegmentDef_ID);

	/** Get HL7 Segment Definition	  */
	public int getHL7_SegmentDef_ID();

	public I_HL7_SegmentDef getHL7_SegmentDef() throws RuntimeException;

    /** Column name IncrementType */
    public static final String COLUMNNAME_IncrementType = "IncrementType";

	/** Set Increment Type	  */
	public void setIncrementType (boolean IncrementType);

	/** Get Increment Type	  */
	public boolean isIncrementType();

    /** Column name IsIdentifier */
    public static final String COLUMNNAME_IsIdentifier = "IsIdentifier";

	/** Set Identifier.
	  * This column is part of the record identifier
	  */
	public void setIsIdentifier (boolean IsIdentifier);

	/** Get Identifier.
	  * This column is part of the record identifier
	  */
	public boolean isIdentifier();

    /** Column name IsRepeated */
    public static final String COLUMNNAME_IsRepeated = "IsRepeated";

	/** Set Is Repeated	  */
	public void setIsRepeated (boolean IsRepeated);

	/** Get Is Repeated	  */
	public boolean isRepeated();

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
