/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_AccessContainer
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_CM_AccessContainer 
{

    /** TableName=CM_AccessContainer */
    public static final String Table_Name = "CM_AccessContainer";

    /** AD_Table_ID=888 */
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

    /** Column name CM_Container_ID */
    public static final String COLUMNNAME_CM_Container_ID = "CM_Container_ID";

	/** Set Web Container.
	  * Web Container contains content like images, text etc.
	  */
	public void setCM_Container_ID (int CM_Container_ID);

	/** Get Web Container.
	  * Web Container contains content like images, text etc.
	  */
	public int getCM_Container_ID();

	public I_CM_Container getCM_Container() throws RuntimeException;
}
