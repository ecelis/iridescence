/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_SegmentDef
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_HL7_SegmentDef 
{

    /** TableName=HL7_SegmentDef */
    public static final String Table_Name = "HL7_SegmentDef";

    /** AD_Table_ID=1200590 */
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

    /** Column name HL7_FieldDef_ID */
    public static final String COLUMNNAME_HL7_FieldDef_ID = "HL7_FieldDef_ID";

	/** Set HL7 Field Definition	  */
	public void setHL7_FieldDef_ID (int HL7_FieldDef_ID);

	/** Get HL7 Field Definition	  */
	public int getHL7_FieldDef_ID();

	public I_HL7_FieldDef getHL7_FieldDef() throws RuntimeException;

    /** Column name HL7_MessageDef_ID */
    public static final String COLUMNNAME_HL7_MessageDef_ID = "HL7_MessageDef_ID";

	/** Set Message Definition	  */
	public void setHL7_MessageDef_ID (int HL7_MessageDef_ID);

	/** Get Message Definition	  */
	public int getHL7_MessageDef_ID();

	public I_HL7_MessageDef getHL7_MessageDef() throws RuntimeException;

    /** Column name HL7_SegmentDef_ID */
    public static final String COLUMNNAME_HL7_SegmentDef_ID = "HL7_SegmentDef_ID";

	/** Set HL7 Segment Definition	  */
	public void setHL7_SegmentDef_ID (int HL7_SegmentDef_ID);

	/** Get HL7 Segment Definition	  */
	public int getHL7_SegmentDef_ID();

    /** Column name HL7_SegmentDef2_ID */
    public static final String COLUMNNAME_HL7_SegmentDef2_ID = "HL7_SegmentDef2_ID";

	/** Set Parent Segment Definition.
	  * HL7 Parent Segment Definition when this segment is part of a group
	  */
	public void setHL7_SegmentDef2_ID (int HL7_SegmentDef2_ID);

	/** Get Parent Segment Definition.
	  * HL7 Parent Segment Definition when this segment is part of a group
	  */
	public int getHL7_SegmentDef2_ID();

    /** Column name HL7_Segment_ID */
    public static final String COLUMNNAME_HL7_Segment_ID = "HL7_Segment_ID";

	/** Set HL7 Segment.
	  * Segment of an HL7 Message
	  */
	public void setHL7_Segment_ID (int HL7_Segment_ID);

	/** Get HL7 Segment.
	  * Segment of an HL7 Message
	  */
	public int getHL7_Segment_ID();

	public I_HL7_Segment getHL7_Segment() throws RuntimeException;

    /** Column name IsGroup */
    public static final String COLUMNNAME_IsGroup = "IsGroup";

	/** Set Is Group.
	  * Marks this segment as a group
	  */
	public void setIsGroup (boolean IsGroup);

	/** Get Is Group.
	  * Marks this segment as a group
	  */
	public boolean isGroup();

    /** Column name IsParent */
    public static final String COLUMNNAME_IsParent = "IsParent";

	/** Set Parent link column.
	  * This column is a link to the parent table (e.g. header from lines) - incl. Association key columns
	  */
	public void setIsParent (boolean IsParent);

	/** Get Parent link column.
	  * This column is a link to the parent table (e.g. header from lines) - incl. Association key columns
	  */
	public boolean isParent();

    /** Column name Optionality */
    public static final String COLUMNNAME_Optionality = "Optionality";

	/** Set Optionality	  */
	public void setOptionality (String Optionality);

	/** Get Optionality	  */
	public String getOptionality();

    /** Column name OrderByClause */
    public static final String COLUMNNAME_OrderByClause = "OrderByClause";

	/** Set Order by clause.
	  * Fully qualified ORDER BY clause
	  */
	public void setOrderByClause (String OrderByClause);

	/** Get Order by clause.
	  * Fully qualified ORDER BY clause
	  */
	public String getOrderByClause();

    /** Column name Repeated */
    public static final String COLUMNNAME_Repeated = "Repeated";

	/** Set Repeated	  */
	public void setRepeated (int Repeated);

	/** Get Repeated	  */
	public int getRepeated();

    /** Column name SegmentSql */
    public static final String COLUMNNAME_SegmentSql = "SegmentSql";

	/** Set Query segment.
	  * Query segment
	  */
	public void setSegmentSql (String SegmentSql);

	/** Get Query segment.
	  * Query segment
	  */
	public String getSegmentSql();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();
}
