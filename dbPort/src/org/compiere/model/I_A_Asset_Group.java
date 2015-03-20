/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Group
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Group 
{

    /** TableName=A_Asset_Group */
    public static final String Table_Name = "A_Asset_Group";

    /** AD_Table_ID=542 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_Group_ID */
    public static final String COLUMNNAME_A_Asset_Group_ID = "A_Asset_Group_ID";

	/** Set Asset Group.
	  * Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID);

	/** Get Asset Group.
	  * Group of Assets
	  */
	public int getA_Asset_Group_ID();

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

    /** Column name EXME_Clase_ID */
    public static final String COLUMNNAME_EXME_Clase_ID = "EXME_Clase_ID";

	/** Set Class.
	  * Class identifier
	  */
	public void setEXME_Clase_ID (int EXME_Clase_ID);

	/** Get Class.
	  * Class identifier
	  */
	public int getEXME_Clase_ID();

	public I_EXME_Clase getEXME_Clase() throws RuntimeException;

    /** Column name EXME_Cuenta_ID */
    public static final String COLUMNNAME_EXME_Cuenta_ID = "EXME_Cuenta_ID";

	/** Set Account.
	  * Account identifier
	  */
	public void setEXME_Cuenta_ID (int EXME_Cuenta_ID);

	/** Get Account.
	  * Account identifier
	  */
	public int getEXME_Cuenta_ID();

	public I_EXME_Cuenta getEXME_Cuenta() throws RuntimeException;

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

    /** Column name IsCreateAsActive */
    public static final String COLUMNNAME_IsCreateAsActive = "IsCreateAsActive";

	/** Set Create As Active.
	  * Create Asset and activate it
	  */
	public void setIsCreateAsActive (boolean IsCreateAsActive);

	/** Get Create As Active.
	  * Create Asset and activate it
	  */
	public boolean isCreateAsActive();

    /** Column name IsDepreciated */
    public static final String COLUMNNAME_IsDepreciated = "IsDepreciated";

	/** Set Depreciate.
	  * The asset will be depreciated
	  */
	public void setIsDepreciated (boolean IsDepreciated);

	/** Get Depreciate.
	  * The asset will be depreciated
	  */
	public boolean isDepreciated();

    /** Column name IsOneAssetPerUOM */
    public static final String COLUMNNAME_IsOneAssetPerUOM = "IsOneAssetPerUOM";

	/** Set One Asset Per UOM.
	  * Create one asset per UOM
	  */
	public void setIsOneAssetPerUOM (boolean IsOneAssetPerUOM);

	/** Get One Asset Per UOM.
	  * Create one asset per UOM
	  */
	public boolean isOneAssetPerUOM();

    /** Column name IsOwned */
    public static final String COLUMNNAME_IsOwned = "IsOwned";

	/** Set Owned.
	  * The asset is owned by the organization
	  */
	public void setIsOwned (boolean IsOwned);

	/** Get Owned.
	  * The asset is owned by the organization
	  */
	public boolean isOwned();

    /** Column name IsTrackIssues */
    public static final String COLUMNNAME_IsTrackIssues = "IsTrackIssues";

	/** Set Track Issues.
	  * Enable tracking issues for this asset
	  */
	public void setIsTrackIssues (boolean IsTrackIssues);

	/** Get Track Issues.
	  * Enable tracking issues for this asset
	  */
	public boolean isTrackIssues();

    /** Column name MetodoDepre */
    public static final String COLUMNNAME_MetodoDepre = "MetodoDepre";

	/** Set Depreciation Method	  */
	public void setMetodoDepre (String MetodoDepre);

	/** Get Depreciation Method	  */
	public String getMetodoDepre();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
