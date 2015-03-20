/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ASP_Tab
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_ASP_Tab 
{

    /** TableName=ASP_Tab */
    public static final String Table_Name = "ASP_Tab";

    /** AD_Table_ID=1200684 */
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

    /** Column name AD_Tab_ID */
    public static final String COLUMNNAME_AD_Tab_ID = "AD_Tab_ID";

	/** Set Tab.
	  * Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID);

	/** Get Tab.
	  * Tab within a Window
	  */
	public int getAD_Tab_ID();

	public I_AD_Tab getAD_Tab() throws RuntimeException;

    /** Column name AllFields */
    public static final String COLUMNNAME_AllFields = "AllFields";

	/** Set AllFields	  */
	public void setAllFields (boolean AllFields);

	/** Get AllFields	  */
	public boolean isAllFields();

    /** Column name ASP_Status */
    public static final String COLUMNNAME_ASP_Status = "ASP_Status";

	/** Set ASP Status	  */
	public void setASP_Status (String ASP_Status);

	/** Get ASP Status	  */
	public String getASP_Status();

    /** Column name ASP_Tab_ID */
    public static final String COLUMNNAME_ASP_Tab_ID = "ASP_Tab_ID";

	/** Set ASP Tab	  */
	public void setASP_Tab_ID (int ASP_Tab_ID);

	/** Get ASP Tab	  */
	public int getASP_Tab_ID();

    /** Column name ASP_Window_ID */
    public static final String COLUMNNAME_ASP_Window_ID = "ASP_Window_ID";

	/** Set ASP Window	  */
	public void setASP_Window_ID (int ASP_Window_ID);

	/** Get ASP Window	  */
	public int getASP_Window_ID();

	public I_ASP_Window getASP_Window() throws RuntimeException;

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
