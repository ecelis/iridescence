/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigPaq
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigPaq 
{

    /** TableName=EXME_ConfigPaq */
    public static final String Table_Name = "EXME_ConfigPaq";

    /** AD_Table_ID=1000133 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name ActualizaPrecios */
    public static final String COLUMNNAME_ActualizaPrecios = "ActualizaPrecios";

	/** Set Update Prices.
	  * Update Prices from the Price List
	  */
	public void setActualizaPrecios (boolean ActualizaPrecios);

	/** Get Update Prices.
	  * Update Prices from the Price List
	  */
	public boolean isActualizaPrecios();

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

    /** Column name AutoVersion */
    public static final String COLUMNNAME_AutoVersion = "AutoVersion";

	/** Set AutoVersion.
	  * Create a new version of each package when creating a new version of price list
	  */
	public void setAutoVersion (boolean AutoVersion);

	/** Get AutoVersion.
	  * Create a new version of each package when creating a new version of price list
	  */
	public boolean isAutoVersion();

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

    /** Column name EditPrecios */
    public static final String COLUMNNAME_EditPrecios = "EditPrecios";

	/** Set EditPrices.
	  * Indicates if permits to edit prices of packages
	  */
	public void setEditPrecios (boolean EditPrecios);

	/** Get EditPrices.
	  * Indicates if permits to edit prices of packages
	  */
	public boolean isEditPrecios();

    /** Column name EXME_ConfigPaq_ID */
    public static final String COLUMNNAME_EXME_ConfigPaq_ID = "EXME_ConfigPaq_ID";

	/** Set Package Configuration.
	  * Package Configuration
	  */
	public void setEXME_ConfigPaq_ID (int EXME_ConfigPaq_ID);

	/** Get Package Configuration.
	  * Package Configuration
	  */
	public int getEXME_ConfigPaq_ID();

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

    /** Column name PorcDesc */
    public static final String COLUMNNAME_PorcDesc = "PorcDesc";

	/** Set PercDisc.
	  * Percentage of Discount that automatically applies when creating a new version of some package
	  */
	public void setPorcDesc (int PorcDesc);

	/** Get PercDisc.
	  * Percentage of Discount that automatically applies when creating a new version of some package
	  */
	public int getPorcDesc();

    /** Column name UsaSustituto */
    public static final String COLUMNNAME_UsaSustituto = "UsaSustituto";

	/** Set Use Substitute.
	  * Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public void setUsaSustituto (boolean UsaSustituto);

	/** Get Use Substitute.
	  * Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public boolean isUsaSustituto();
}
