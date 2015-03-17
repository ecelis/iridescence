/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_FieldRep
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_FieldRep 
{

    /** TableName=HL7_FieldRep */
    public static final String Table_Name = "HL7_FieldRep";

    /** AD_Table_ID=1201034 */
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

    /** Column name HL7_FieldRep_ID */
    public static final String COLUMNNAME_HL7_FieldRep_ID = "HL7_FieldRep_ID";

	/** Set Field Repetition	  */
	public void setHL7_FieldRep_ID (int HL7_FieldRep_ID);

	/** Get Field Repetition	  */
	public int getHL7_FieldRep_ID();

    /** Column name Occurrence */
    public static final String COLUMNNAME_Occurrence = "Occurrence";

	/** Set Occurrence	  */
	public void setOccurrence (BigDecimal Occurrence);

	/** Get Occurrence	  */
	public BigDecimal getOccurrence();
}
