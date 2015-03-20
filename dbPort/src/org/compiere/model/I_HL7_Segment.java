/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_Segment
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_Segment 
{

    /** TableName=HL7_Segment */
    public static final String Table_Name = "HL7_Segment";

    /** AD_Table_ID=1200584 */
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
