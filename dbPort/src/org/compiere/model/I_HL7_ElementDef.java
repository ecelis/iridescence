/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_ElementDef
 *  @author Generated Class 
 *  @version Release 5.5
 */
public interface I_HL7_ElementDef 
{

    /** TableName=HL7_ElementDef */
    public static final String Table_Name = "HL7_ElementDef";

    /** AD_Table_ID=1200591 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name HL7_ElementDef_ID */
    public static final String COLUMNNAME_HL7_ElementDef_ID = "HL7_ElementDef_ID";

	/** Set HL7 Element Definition	  */
	public void setHL7_ElementDef_ID (int HL7_ElementDef_ID);

	/** Get HL7 Element Definition	  */
	public int getHL7_ElementDef_ID();

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

	public I_HL7_LinkedTable getHL7_LinkedTable() throws RuntimeException;

    /** Column name HL7_SegmentDef_ID */
    public static final String COLUMNNAME_HL7_SegmentDef_ID = "HL7_SegmentDef_ID";

	/** Set HL7 Segment Definition	  */
	public void setHL7_SegmentDef_ID (int HL7_SegmentDef_ID);

	/** Get HL7 Segment Definition	  */
	public int getHL7_SegmentDef_ID();

	public I_HL7_SegmentDef getHL7_SegmentDef() throws RuntimeException;

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
