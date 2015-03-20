/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MotivoAut
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MotivoAut 
{

    /** TableName=EXME_MotivoAut */
    public static final String Table_Name = "EXME_MotivoAut";

    /** AD_Table_ID=1000144 */
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

    /** Column name EXME_MotivoAut_ID */
    public static final String COLUMNNAME_EXME_MotivoAut_ID = "EXME_MotivoAut_ID";

	/** Set Motive Authorization Personnel.
	  * Personnel that authorizes motive of loan request from the physical file
	  */
	public void setEXME_MotivoAut_ID (int EXME_MotivoAut_ID);

	/** Get Motive Authorization Personnel.
	  * Personnel that authorizes motive of loan request from the physical file
	  */
	public int getEXME_MotivoAut_ID();

    /** Column name EXME_MotivoSol_ID */
    public static final String COLUMNNAME_EXME_MotivoSol_ID = "EXME_MotivoSol_ID";

	/** Set Motive of Request.
	  * Motive of loan request from the physical file
	  */
	public void setEXME_MotivoSol_ID (String EXME_MotivoSol_ID);

	/** Get Motive of Request.
	  * Motive of loan request from the physical file
	  */
	public String getEXME_MotivoSol_ID();
}
