/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ASP_Field
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_ASP_Field 
{

    /** TableName=ASP_Field */
    public static final String Table_Name = "ASP_Field";

    /** AD_Table_ID=1200685 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Field_ID */
    public static final String COLUMNNAME_AD_Field_ID = "AD_Field_ID";

	/** Set Field.
	  * Field on a database table
	  */
	public void setAD_Field_ID (int AD_Field_ID);

	/** Get Field.
	  * Field on a database table
	  */
	public int getAD_Field_ID();

	public I_AD_Field getAD_Field() throws RuntimeException;

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

    /** Column name ASP_Field_ID */
    public static final String COLUMNNAME_ASP_Field_ID = "ASP_Field_ID";

	/** Set ASP Field	  */
	public void setASP_Field_ID (int ASP_Field_ID);

	/** Get ASP Field	  */
	public int getASP_Field_ID();

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

	public I_ASP_Tab getASP_Tab() throws RuntimeException;
}
