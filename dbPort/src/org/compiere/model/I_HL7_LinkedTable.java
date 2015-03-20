/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_LinkedTable
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_LinkedTable 
{

    /** TableName=HL7_LinkedTable */
    public static final String Table_Name = "HL7_LinkedTable";

    /** AD_Table_ID=1200818 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws RuntimeException;

    /** Column name AD_Column_Rel_ID */
    public static final String COLUMNNAME_AD_Column_Rel_ID = "AD_Column_Rel_ID";

	/** Set Linked Column	  */
	public void setAD_Column_Rel_ID (int AD_Column_Rel_ID);

	/** Get Linked Column	  */
	public int getAD_Column_Rel_ID();

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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name AD_Table_Rel_ID */
    public static final String COLUMNNAME_AD_Table_Rel_ID = "AD_Table_Rel_ID";

	/** Set Linked Table	  */
	public void setAD_Table_Rel_ID (int AD_Table_Rel_ID);

	/** Get Linked Table	  */
	public int getAD_Table_Rel_ID();

    /** Column name HL7_LinkedTable_ID */
    public static final String COLUMNNAME_HL7_LinkedTable_ID = "HL7_LinkedTable_ID";

	/** Set Linked Tables HL7	  */
	public void setHL7_LinkedTable_ID (int HL7_LinkedTable_ID);

	/** Get Linked Tables HL7	  */
	public int getHL7_LinkedTable_ID();

    /** Column name HL7_LinkedTable2_ID */
    public static final String COLUMNNAME_HL7_LinkedTable2_ID = "HL7_LinkedTable2_ID";

	/** Set Linked Table Origin	  */
	public void setHL7_LinkedTable2_ID (int HL7_LinkedTable2_ID);

	/** Get Linked Table Origin	  */
	public int getHL7_LinkedTable2_ID();

    /** Column name IsInner */
    public static final String COLUMNNAME_IsInner = "IsInner";

	/** Set Is Inner Join	  */
	public void setIsInner (boolean IsInner);

	/** Get Is Inner Join	  */
	public boolean isInner();

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
