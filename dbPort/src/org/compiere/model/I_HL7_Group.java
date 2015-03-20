/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_Group
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_Group 
{

    /** TableName=HL7_Group */
    public static final String Table_Name = "HL7_Group";

    /** AD_Table_ID=1200833 */
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

    /** Column name HL7_Group_ID */
    public static final String COLUMNNAME_HL7_Group_ID = "HL7_Group_ID";

	/** Set HL7 Group.
	  * Group of Segments
	  */
	public void setHL7_Group_ID (int HL7_Group_ID);

	/** Get HL7 Group.
	  * Group of Segments
	  */
	public int getHL7_Group_ID();

    /** Column name HL7_SegmentDef_ID */
    public static final String COLUMNNAME_HL7_SegmentDef_ID = "HL7_SegmentDef_ID";

	/** Set HL7 Segment Definition	  */
	public void setHL7_SegmentDef_ID (int HL7_SegmentDef_ID);

	/** Get HL7 Segment Definition	  */
	public int getHL7_SegmentDef_ID();

	public I_HL7_SegmentDef getHL7_SegmentDef() throws RuntimeException;

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();

    /** Column name StructureType */
    public static final String COLUMNNAME_StructureType = "StructureType";

	/** Set Structure Type.
	  * Type of gruop Struture -Array, Group, Segment
	  */
	public void setStructureType (String StructureType);

	/** Get Structure Type.
	  * Type of gruop Struture -Array, Group, Segment
	  */
	public String getStructureType();

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
