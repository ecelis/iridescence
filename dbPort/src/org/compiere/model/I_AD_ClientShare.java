/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_ClientShare
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_ClientShare 
{

    /** TableName=AD_ClientShare */
    public static final String Table_Name = "AD_ClientShare";

    /** AD_Table_ID=827 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name AD_ClientShare_ID */
    public static final String COLUMNNAME_AD_ClientShare_ID = "AD_ClientShare_ID";

	/** Set Client Share.
	  * Force (not) sharing of client/org entities
	  */
	public void setAD_ClientShare_ID (int AD_ClientShare_ID);

	/** Get Client Share.
	  * Force (not) sharing of client/org entities
	  */
	public int getAD_ClientShare_ID();

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

    /** Column name ShareType */
    public static final String COLUMNNAME_ShareType = "ShareType";

	/** Set Share Type.
	  * Type of sharing
	  */
	public void setShareType (String ShareType);

	/** Get Share Type.
	  * Type of sharing
	  */
	public String getShareType();
}
