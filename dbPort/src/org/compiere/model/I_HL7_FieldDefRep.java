/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_FieldDefRep
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_FieldDefRep 
{

    /** TableName=HL7_FieldDefRep */
    public static final String Table_Name = "HL7_FieldDefRep";

    /** AD_Table_ID=1201047 */
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

    /** Column name HL7_FieldRep_ID */
    public static final String COLUMNNAME_HL7_FieldRep_ID = "HL7_FieldRep_ID";

	/** Set Field Repetition	  */
	public void setHL7_FieldRep_ID (int HL7_FieldRep_ID);

	/** Get Field Repetition	  */
	public int getHL7_FieldRep_ID();

	public I_HL7_FieldRep getHL7_FieldRep() throws RuntimeException;
}
