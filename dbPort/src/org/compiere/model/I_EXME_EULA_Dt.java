/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EULA_Dt
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_EULA_Dt 
{

    /** TableName=EXME_EULA_Dt */
    public static final String Table_Name = "EXME_EULA_Dt";

    /** AD_Table_ID=1201168 */
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

    /** Column name EXME_EULA_Dt_ID */
    public static final String COLUMNNAME_EXME_EULA_Dt_ID = "EXME_EULA_Dt_ID";

	/** Set EULA Detail.
	  * End User License Agreement Detail
	  */
	public void setEXME_EULA_Dt_ID (int EXME_EULA_Dt_ID);

	/** Get EULA Detail.
	  * End User License Agreement Detail
	  */
	public int getEXME_EULA_Dt_ID();

    /** Column name EXME_EULA_ID */
    public static final String COLUMNNAME_EXME_EULA_ID = "EXME_EULA_ID";

	/** Set End User Agreement License.
	  * The End User Agreement License
	  */
	public void setEXME_EULA_ID (int EXME_EULA_ID);

	/** Get End User Agreement License.
	  * The End User Agreement License
	  */
	public int getEXME_EULA_ID();

	public I_EXME_EULA getEXME_EULA() throws RuntimeException;

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

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();
}
