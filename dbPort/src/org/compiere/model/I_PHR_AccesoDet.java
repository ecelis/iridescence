/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_AccesoDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_AccesoDet 
{

    /** TableName=PHR_AccesoDet */
    public static final String Table_Name = "PHR_AccesoDet";

    /** AD_Table_ID=1200974 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (String AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public String getAccessLevel();

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

    /** Column name PHR_AccesoDet_ID */
    public static final String COLUMNNAME_PHR_AccesoDet_ID = "PHR_AccesoDet_ID";

	/** Set Access Detail to PHR	  */
	public void setPHR_AccesoDet_ID (int PHR_AccesoDet_ID);

	/** Get Access Detail to PHR	  */
	public int getPHR_AccesoDet_ID();

    /** Column name PHR_Acceso_ID */
    public static final String COLUMNNAME_PHR_Acceso_ID = "PHR_Acceso_ID";

	/** Set Access to PHR	  */
	public void setPHR_Acceso_ID (int PHR_Acceso_ID);

	/** Get Access to PHR	  */
	public int getPHR_Acceso_ID();

	public I_PHR_Acceso getPHR_Acceso() throws RuntimeException;
}
