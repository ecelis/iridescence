/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_AccessListBPGroup
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_CM_AccessListBPGroup 
{

    /** TableName=CM_AccessListBPGroup */
    public static final String Table_Name = "CM_AccessListBPGroup";

    /** AD_Table_ID=886 */
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

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getC_BP_Group() throws RuntimeException;

    /** Column name CM_AccessProfile_ID */
    public static final String COLUMNNAME_CM_AccessProfile_ID = "CM_AccessProfile_ID";

	/** Set Web Access Profile.
	  * Web Access Profile
	  */
	public void setCM_AccessProfile_ID (int CM_AccessProfile_ID);

	/** Get Web Access Profile.
	  * Web Access Profile
	  */
	public int getCM_AccessProfile_ID();

	public I_CM_AccessProfile getCM_AccessProfile() throws RuntimeException;
}
