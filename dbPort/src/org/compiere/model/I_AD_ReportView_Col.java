/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_ReportView_Col
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_ReportView_Col 
{

    /** TableName=AD_ReportView_Col */
    public static final String Table_Name = "AD_ReportView_Col";

    /** AD_Table_ID=428 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name AD_ReportView_Col_ID */
    public static final String COLUMNNAME_AD_ReportView_Col_ID = "AD_ReportView_Col_ID";

	/** Set Report view Column	  */
	public void setAD_ReportView_Col_ID (int AD_ReportView_Col_ID);

	/** Get Report view Column	  */
	public int getAD_ReportView_Col_ID();

    /** Column name AD_ReportView_ID */
    public static final String COLUMNNAME_AD_ReportView_ID = "AD_ReportView_ID";

	/** Set Report View.
	  * View used to generate this report
	  */
	public void setAD_ReportView_ID (int AD_ReportView_ID);

	/** Get Report View.
	  * View used to generate this report
	  */
	public int getAD_ReportView_ID();

	public I_AD_ReportView getAD_ReportView() throws RuntimeException;

    /** Column name FunctionColumn */
    public static final String COLUMNNAME_FunctionColumn = "FunctionColumn";

	/** Set Function Column.
	  * Overwrite Column with Function 
	  */
	public void setFunctionColumn (String FunctionColumn);

	/** Get Function Column.
	  * Overwrite Column with Function 
	  */
	public String getFunctionColumn();

    /** Column name IsGroupFunction */
    public static final String COLUMNNAME_IsGroupFunction = "IsGroupFunction";

	/** Set SQL Group Function.
	  * This function will generate a Group By Clause
	  */
	public void setIsGroupFunction (boolean IsGroupFunction);

	/** Get SQL Group Function.
	  * This function will generate a Group By Clause
	  */
	public boolean isGroupFunction();
}
