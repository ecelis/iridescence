/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for R_IssueStatus
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_R_IssueStatus 
{

    /** TableName=R_IssueStatus */
    public static final String Table_Name = "R_IssueStatus";

    /** AD_Table_ID=838 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name R_IssueStatus_ID */
    public static final String COLUMNNAME_R_IssueStatus_ID = "R_IssueStatus_ID";

	/** Set Issue Status.
	  * Status of an Issue
	  */
	public void setR_IssueStatus_ID (int R_IssueStatus_ID);

	/** Get Issue Status.
	  * Status of an Issue
	  */
	public int getR_IssueStatus_ID();
}
