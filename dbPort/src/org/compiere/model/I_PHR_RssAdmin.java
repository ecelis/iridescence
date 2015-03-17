/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_RssAdmin
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_RssAdmin 
{

    /** TableName=PHR_RssAdmin */
    public static final String Table_Name = "PHR_RssAdmin";

    /** AD_Table_ID=1201019 */
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

    /** Column name PHR_RssAdmin_ID */
    public static final String COLUMNNAME_PHR_RssAdmin_ID = "PHR_RssAdmin_ID";

	/** Set Primary key.
	  * Primary key
	  */
	public void setPHR_RssAdmin_ID (int PHR_RssAdmin_ID);

	/** Get Primary key.
	  * Primary key
	  */
	public int getPHR_RssAdmin_ID();

    /** Column name Rss */
    public static final String COLUMNNAME_Rss = "Rss";

	/** Set Articles Address.
	  * Articles Address
	  */
	public void setRss (String Rss);

	/** Get Articles Address.
	  * Articles Address
	  */
	public String getRss();
}
