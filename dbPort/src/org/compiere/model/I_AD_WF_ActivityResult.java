/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_WF_ActivityResult
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_WF_ActivityResult 
{

    /** TableName=AD_WF_ActivityResult */
    public static final String Table_Name = "AD_WF_ActivityResult";

    /** AD_Table_ID=650 */
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

    /** Column name AD_WF_Activity_ID */
    public static final String COLUMNNAME_AD_WF_Activity_ID = "AD_WF_Activity_ID";

	/** Set Workflow Activity.
	  * Workflow Activity
	  */
	public void setAD_WF_Activity_ID (int AD_WF_Activity_ID);

	/** Get Workflow Activity.
	  * Workflow Activity
	  */
	public int getAD_WF_Activity_ID();

	public I_AD_WF_Activity getAD_WF_Activity() throws RuntimeException;

    /** Column name AD_WF_ActivityResult_ID */
    public static final String COLUMNNAME_AD_WF_ActivityResult_ID = "AD_WF_ActivityResult_ID";

	/** Set Workflow Activity Result.
	  * Result of the Workflow Process Activity
	  */
	public void setAD_WF_ActivityResult_ID (int AD_WF_ActivityResult_ID);

	/** Get Workflow Activity Result.
	  * Result of the Workflow Process Activity
	  */
	public int getAD_WF_ActivityResult_ID();

    /** Column name AttributeName */
    public static final String COLUMNNAME_AttributeName = "AttributeName";

	/** Set Attribute Name.
	  * Name of the Attribute
	  */
	public void setAttributeName (String AttributeName);

	/** Get Attribute Name.
	  * Name of the Attribute
	  */
	public String getAttributeName();

    /** Column name AttributeValue */
    public static final String COLUMNNAME_AttributeValue = "AttributeValue";

	/** Set Attribute Value.
	  * Value of the Attribute
	  */
	public void setAttributeValue (String AttributeValue);

	/** Get Attribute Value.
	  * Value of the Attribute
	  */
	public String getAttributeValue();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();
}
