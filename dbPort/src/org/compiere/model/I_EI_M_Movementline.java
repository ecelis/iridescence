/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EI_M_Movementline
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EI_M_Movementline 
{

    /** TableName=EI_M_Movementline */
    public static final String Table_Name = "EI_M_Movementline";

    /** AD_Table_ID=1200241 */
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

    /** Column name EI_M_Movementline_ID */
    public static final String COLUMNNAME_EI_M_Movementline_ID = "EI_M_Movementline_ID";

	/** Set Movement Line.
	  * Movement Line
	  */
	public void setEI_M_Movementline_ID (int EI_M_Movementline_ID);

	/** Get Movement Line.
	  * Movement Line
	  */
	public int getEI_M_Movementline_ID();

    /** Column name ErrorDescription */
    public static final String COLUMNNAME_ErrorDescription = "ErrorDescription";

	/** Set ErrorDescription.
	  * ErrorDescription
	  */
	public void setErrorDescription (String ErrorDescription);

	/** Get ErrorDescription.
	  * ErrorDescription
	  */
	public String getErrorDescription();

    /** Column name ErrorStatus */
    public static final String COLUMNNAME_ErrorStatus = "ErrorStatus";

	/** Set ErrorStatus.
	  * ErrorStatus
	  */
	public void setErrorStatus (boolean ErrorStatus);

	/** Get ErrorStatus.
	  * ErrorStatus
	  */
	public boolean isErrorStatus();
}
