/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigEncoder
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigEncoder 
{

    /** TableName=EXME_ConfigEncoder */
    public static final String Table_Name = "EXME_ConfigEncoder";

    /** AD_Table_ID=1201292 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name ClientID */
    public static final String COLUMNNAME_ClientID = "ClientID";

	/** Set ID Client.
	  * ID Client
	  */
	public void setClientID (String ClientID);

	/** Get ID Client.
	  * ID Client
	  */
	public String getClientID();

    /** Column name ClientSecret */
    public static final String COLUMNNAME_ClientSecret = "ClientSecret";

	/** Set Secret key.
	  * Secret key
	  */
	public void setClientSecret (String ClientSecret);

	/** Get Secret key.
	  * Secret key
	  */
	public String getClientSecret();

    /** Column name EXME_ConfigEncoder_ID */
    public static final String COLUMNNAME_EXME_ConfigEncoder_ID = "EXME_ConfigEncoder_ID";

	/** Set Encoder configuration.
	  * Encoder configuration
	  */
	public void setEXME_ConfigEncoder_ID (int EXME_ConfigEncoder_ID);

	/** Get Encoder configuration.
	  * Encoder configuration
	  */
	public int getEXME_ConfigEncoder_ID();

    /** Column name Expires */
    public static final String COLUMNNAME_Expires = "Expires";

	/** Set Expires.
	  * Expires
	  */
	public void setExpires (BigDecimal Expires);

	/** Get Expires.
	  * Expires
	  */
	public BigDecimal getExpires();

    /** Column name GrantType */
    public static final String COLUMNNAME_GrantType = "GrantType";

	/** Set Grant type.
	  * Grant type
	  */
	public void setGrantType (String GrantType);

	/** Get Grant type.
	  * Grant type
	  */
	public String getGrantType();

    /** Column name Host */
    public static final String COLUMNNAME_Host = "Host";

	/** Set Host	  */
	public void setHost (String Host);

	/** Get Host	  */
	public String getHost();

    /** Column name LastUpdate */
    public static final String COLUMNNAME_LastUpdate = "LastUpdate";

	/** Set Last update.
	  * Last update
	  */
	public void setLastUpdate (Timestamp LastUpdate);

	/** Get Last update.
	  * Last update
	  */
	public Timestamp getLastUpdate();

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

    /** Column name Port */
    public static final String COLUMNNAME_Port = "Port";

	/** Set Port	  */
	public void setPort (BigDecimal Port);

	/** Get Port	  */
	public BigDecimal getPort();

    /** Column name Scheme */
    public static final String COLUMNNAME_Scheme = "Scheme";

	/** Set Scheme.
	  * Scheme
	  */
	public void setScheme (String Scheme);

	/** Get Scheme.
	  * Scheme
	  */
	public String getScheme();

    /** Column name TimeoutMS */
    public static final String COLUMNNAME_TimeoutMS = "TimeoutMS";

	/** Set Timeout (MS).
	  * Timeout (MS)
	  */
	public void setTimeoutMS (int TimeoutMS);

	/** Get Timeout (MS).
	  * Timeout (MS)
	  */
	public int getTimeoutMS();

    /** Column name Token */
    public static final String COLUMNNAME_Token = "Token";

	/** Set Token.
	  * Token
	  */
	public void setToken (String Token);

	/** Get Token.
	  * Token
	  */
	public String getToken();

    /** Column name TokenType */
    public static final String COLUMNNAME_TokenType = "TokenType";

	/** Set TokenType.
	  * Wiki Token Type
	  */
	public void setTokenType (String TokenType);

	/** Get TokenType.
	  * Wiki Token Type
	  */
	public String getTokenType();

    /** Column name UrlDetail */
    public static final String COLUMNNAME_UrlDetail = "UrlDetail";

	/** Set Url detail.
	  * Url detail
	  */
	public void setUrlDetail (String UrlDetail);

	/** Get Url detail.
	  * Url detail
	  */
	public String getUrlDetail();

    /** Column name UrlSearch */
    public static final String COLUMNNAME_UrlSearch = "UrlSearch";

	/** Set Url search.
	  * Url search
	  */
	public void setUrlSearch (String UrlSearch);

	/** Get Url search.
	  * Url search
	  */
	public String getUrlSearch();

    /** Column name UrlServer1 */
    public static final String COLUMNNAME_UrlServer1 = "UrlServer1";

	/** Set Url Server 1.
	  * Url Server 1
	  */
	public void setUrlServer1 (String UrlServer1);

	/** Get Url Server 1.
	  * Url Server 1
	  */
	public String getUrlServer1();

    /** Column name UrlServer2 */
    public static final String COLUMNNAME_UrlServer2 = "UrlServer2";

	/** Set Url Server 2.
	  * Url Server 2
	  */
	public void setUrlServer2 (String UrlServer2);

	/** Get Url Server 2.
	  * Url Server 2
	  */
	public String getUrlServer2();

    /** Column name UrlServer3 */
    public static final String COLUMNNAME_UrlServer3 = "UrlServer3";

	/** Set Url server 3.
	  * Url server 3
	  */
	public void setUrlServer3 (String UrlServer3);

	/** Get Url server 3.
	  * Url server 3
	  */
	public String getUrlServer3();

    /** Column name UrlServer4 */
    public static final String COLUMNNAME_UrlServer4 = "UrlServer4";

	/** Set Url server 4.
	  * Url server 4
	  */
	public void setUrlServer4 (String UrlServer4);

	/** Get Url server 4.
	  * Url server 4
	  */
	public String getUrlServer4();
}
