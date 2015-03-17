/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Emergencia_Usuario
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Emergencia_Usuario 
{

    /** TableName=EXME_Emergencia_Usuario */
    public static final String Table_Name = "EXME_Emergencia_Usuario";

    /** AD_Table_ID=1200849 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name EXME_Emergencia_Usuario_ID */
    public static final String COLUMNNAME_EXME_Emergencia_Usuario_ID = "EXME_Emergencia_Usuario_ID";

	/** Set Emergency User	  */
	public void setEXME_Emergencia_Usuario_ID (int EXME_Emergencia_Usuario_ID);

	/** Get Emergency User	  */
	public int getEXME_Emergencia_Usuario_ID();
}
