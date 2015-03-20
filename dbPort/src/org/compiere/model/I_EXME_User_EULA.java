/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_User_EULA
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_User_EULA 
{

    /** TableName=EXME_User_EULA */
    public static final String Table_Name = "EXME_User_EULA";

    /** AD_Table_ID=1201169 */
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

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name DateAccepted */
    public static final String COLUMNNAME_DateAccepted = "DateAccepted";

	/** Set Date Accepted.
	  * The date on which the user accepted the license.
	  */
	public void setDateAccepted (Timestamp DateAccepted);

	/** Get Date Accepted.
	  * The date on which the user accepted the license.
	  */
	public Timestamp getDateAccepted();

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

	public I_EXME_EULA_Dt getEXME_EULA_Dt() throws RuntimeException;
}
