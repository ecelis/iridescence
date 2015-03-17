/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Attribute_Value
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Attribute_Value 
{

    /** TableName=AD_Attribute_Value */
    public static final String Table_Name = "AD_Attribute_Value";

    /** AD_Table_ID=406 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Attribute_ID */
    public static final String COLUMNNAME_AD_Attribute_ID = "AD_Attribute_ID";

	/** Set System Attribute	  */
	public void setAD_Attribute_ID (int AD_Attribute_ID);

	/** Get System Attribute	  */
	public int getAD_Attribute_ID();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name V_Date */
    public static final String COLUMNNAME_V_Date = "V_Date";

	/** Set Date	  */
	public void setV_Date (Timestamp V_Date);

	/** Get Date	  */
	public Timestamp getV_Date();

    /** Column name V_Number */
    public static final String COLUMNNAME_V_Number = "V_Number";

	/** Set Number Value	  */
	public void setV_Number (String V_Number);

	/** Get Number Value	  */
	public String getV_Number();

    /** Column name V_String */
    public static final String COLUMNNAME_V_String = "V_String";

	/** Set Alphanumeric value	  */
	public void setV_String (String V_String);

	/** Get Alphanumeric value	  */
	public String getV_String();
}
