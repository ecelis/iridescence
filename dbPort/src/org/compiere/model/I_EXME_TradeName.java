/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TradeName
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_TradeName 
{

    /** TableName=EXME_TradeName */
    public static final String Table_Name = "EXME_TradeName";

    /** AD_Table_ID=1201166 */
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

    /** Column name EXME_TradeName_ID */
    public static final String COLUMNNAME_EXME_TradeName_ID = "EXME_TradeName_ID";

	/** Set Trade Name.
	  * Trade Name
	  */
	public void setEXME_TradeName_ID (int EXME_TradeName_ID);

	/** Get Trade Name.
	  * Trade Name
	  */
	public int getEXME_TradeName_ID();

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

    /** Column name Trade_Name_ID */
    public static final String COLUMNNAME_Trade_Name_ID = "Trade_Name_ID";

	/** Set Trade name/key	  */
	public void setTrade_Name_ID (String Trade_Name_ID);

	/** Get Trade name/key	  */
	public String getTrade_Name_ID();
}
