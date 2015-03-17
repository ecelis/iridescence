/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MenuDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MenuDet 
{

    /** TableName=EXME_MenuDet */
    public static final String Table_Name = "EXME_MenuDet";

    /** AD_Table_ID=1200596 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_MenuDet_ID */
    public static final String COLUMNNAME_EXME_MenuDet_ID = "EXME_MenuDet_ID";

	/** Set Menu Detail	  */
	public void setEXME_MenuDet_ID (int EXME_MenuDet_ID);

	/** Get Menu Detail	  */
	public int getEXME_MenuDet_ID();

    /** Column name EXME_MenuHdr_ID */
    public static final String COLUMNNAME_EXME_MenuHdr_ID = "EXME_MenuHdr_ID";

	/** Set Menu	  */
	public void setEXME_MenuHdr_ID (int EXME_MenuHdr_ID);

	/** Get Menu	  */
	public int getEXME_MenuHdr_ID();

    /** Column name EXME_PlatilloHdr_ID */
    public static final String COLUMNNAME_EXME_PlatilloHdr_ID = "EXME_PlatilloHdr_ID";

	/** Set Saucer	  */
	public void setEXME_PlatilloHdr_ID (int EXME_PlatilloHdr_ID);

	/** Get Saucer	  */
	public int getEXME_PlatilloHdr_ID();

	public I_EXME_PlatilloHdr getEXME_PlatilloHdr() throws RuntimeException;

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
