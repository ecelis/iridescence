/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigDer
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigDer 
{

    /** TableName=EXME_ConfigDer */
    public static final String Table_Name = "EXME_ConfigDer";

    /** AD_Table_ID=1000135 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name Derechohabiente */
    public static final String COLUMNNAME_Derechohabiente = "Derechohabiente";

	/** Set Right Holder.
	  * Right Holder
	  */
	public void setDerechohabiente (boolean Derechohabiente);

	/** Get Right Holder.
	  * Right Holder
	  */
	public boolean isDerechohabiente();

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

    /** Column name EXME_ConfigDer_ID */
    public static final String COLUMNNAME_EXME_ConfigDer_ID = "EXME_ConfigDer_ID";

	/** Set Key Configuration.
	  * Key Configuration
	  */
	public void setEXME_ConfigDer_ID (int EXME_ConfigDer_ID);

	/** Get Key Configuration.
	  * Key Configuration
	  */
	public int getEXME_ConfigDer_ID();

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

    /** Column name PermiteAltaD */
    public static final String COLUMNNAME_PermiteAltaD = "PermiteAltaD";

	/** Set Posting of Economic Dependants.
	  * Permit posting of economic dependants
	  */
	public void setPermiteAltaD (boolean PermiteAltaD);

	/** Get Posting of Economic Dependants.
	  * Permit posting of economic dependants
	  */
	public boolean isPermiteAltaD();

    /** Column name PermiteAltaT */
    public static final String COLUMNNAME_PermiteAltaT = "PermiteAltaT";

	/** Set Posting of Title Holders.
	  * Permir Posting of Title Holders?
	  */
	public void setPermiteAltaT (boolean PermiteAltaT);

	/** Get Posting of Title Holders.
	  * Permir Posting of Title Holders?
	  */
	public boolean isPermiteAltaT();

    /** Column name PermiteRefer */
    public static final String COLUMNNAME_PermiteRefer = "PermiteRefer";

	/** Set References/Counter references.
	  * Permit to make references/Counter references
	  */
	public void setPermiteRefer (boolean PermiteRefer);

	/** Get References/Counter references.
	  * Permit to make references/Counter references
	  */
	public boolean isPermiteRefer();
}
