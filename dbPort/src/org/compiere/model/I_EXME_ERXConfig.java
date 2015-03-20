/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ERXConfig
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ERXConfig 
{

    /** TableName=EXME_ERXConfig */
    public static final String Table_Name = "EXME_ERXConfig";

    /** AD_Table_ID=1201222 */
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

    /** Column name DataProvider */
    public static final String COLUMNNAME_DataProvider = "DataProvider";

	/** Set Data provider ID.
	  * Data provider ID
	  */
	public void setDataProvider (String DataProvider);

	/** Get Data provider ID.
	  * Data provider ID
	  */
	public String getDataProvider();

    /** Column name DirectoryURL */
    public static final String COLUMNNAME_DirectoryURL = "DirectoryURL";

	/** Set Directory URL.
	  * Directory URL
	  */
	public void setDirectoryURL (String DirectoryURL);

	/** Get Directory URL.
	  * Directory URL
	  */
	public String getDirectoryURL();

    /** Column name DirectoryUser */
    public static final String COLUMNNAME_DirectoryUser = "DirectoryUser";

	/** Set Directory user.
	  * Directory user
	  */
	public void setDirectoryUser (String DirectoryUser);

	/** Get Directory user.
	  * Directory user
	  */
	public String getDirectoryUser();

    /** Column name DirectoryUserPass */
    public static final String COLUMNNAME_DirectoryUserPass = "DirectoryUserPass";

	/** Set Directory user password.
	  * Directory user password
	  */
	public void setDirectoryUserPass (String DirectoryUserPass);

	/** Get Directory user password.
	  * Directory user password
	  */
	public String getDirectoryUserPass();

    /** Column name DownloadURL */
    public static final String COLUMNNAME_DownloadURL = "DownloadURL";

	/** Set Download URL.
	  * URL of the Download files
	  */
	public void setDownloadURL (String DownloadURL);

	/** Get Download URL.
	  * URL of the Download files
	  */
	public String getDownloadURL();

    /** Column name EXME_ERXConfig_ID */
    public static final String COLUMNNAME_EXME_ERXConfig_ID = "EXME_ERXConfig_ID";

	/** Set Eprescribing Configuration.
	  * Eprescribing Configuration
	  */
	public void setEXME_ERXConfig_ID (int EXME_ERXConfig_ID);

	/** Get Eprescribing Configuration.
	  * Eprescribing Configuration
	  */
	public int getEXME_ERXConfig_ID();

    /** Column name IsInProduction */
    public static final String COLUMNNAME_IsInProduction = "IsInProduction";

	/** Set In Production.
	  * The system is in production
	  */
	public void setIsInProduction (boolean IsInProduction);

	/** Get In Production.
	  * The system is in production
	  */
	public boolean isInProduction();

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

    /** Column name PartnerAccount */
    public static final String COLUMNNAME_PartnerAccount = "PartnerAccount";

	/** Set Partner Account	  */
	public void setPartnerAccount (String PartnerAccount);

	/** Get Partner Account	  */
	public String getPartnerAccount();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name PortalID */
    public static final String COLUMNNAME_PortalID = "PortalID";

	/** Set Portal ID.
	  * Portal Identifier
	  */
	public void setPortalID (String PortalID);

	/** Get Portal ID.
	  * Portal Identifier
	  */
	public String getPortalID();

    /** Column name ProxyAddress */
    public static final String COLUMNNAME_ProxyAddress = "ProxyAddress";

	/** Set Proxy address.
	  *  Address of your proxy server
	  */
	public void setProxyAddress (String ProxyAddress);

	/** Get Proxy address.
	  *  Address of your proxy server
	  */
	public String getProxyAddress();

    /** Column name ProxyPort */
    public static final String COLUMNNAME_ProxyPort = "ProxyPort";

	/** Set Proxy port.
	  * Port of your proxy server
	  */
	public void setProxyPort (String ProxyPort);

	/** Get Proxy port.
	  * Port of your proxy server
	  */
	public String getProxyPort();

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL();

    /** Column name Usuario */
    public static final String COLUMNNAME_Usuario = "Usuario";

	/** Set User.
	  * User
	  */
	public void setUsuario (String Usuario);

	/** Get User.
	  * User
	  */
	public String getUsuario();

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
