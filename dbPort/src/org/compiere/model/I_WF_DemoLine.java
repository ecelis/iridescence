/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for WF_DemoLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_WF_DemoLine 
{

    /** TableName=WF_DemoLine */
    public static final String Table_Name = "WF_DemoLine";

    /** AD_Table_ID=1201150 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name WF_DemoLine_ID */
    public static final String COLUMNNAME_WF_DemoLine_ID = "WF_DemoLine_ID";

	/** Set WF_DemoLine_ID	  */
	public void setWF_DemoLine_ID (int WF_DemoLine_ID);

	/** Get WF_DemoLine_ID	  */
	public int getWF_DemoLine_ID();

    /** Column name WF_Demo_ID */
    public static final String COLUMNNAME_WF_Demo_ID = "WF_Demo_ID";

	/** Set WF_Demo_ID	  */
	public void setWF_Demo_ID (int WF_Demo_ID);

	/** Get WF_Demo_ID	  */
	public int getWF_Demo_ID();

	public I_WF_Demo getWF_Demo() throws RuntimeException;
}
