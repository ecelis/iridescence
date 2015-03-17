/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TablasPaciente
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TablasPaciente 
{

    /** TableName=EXME_TablasPaciente */
    public static final String Table_Name = "EXME_TablasPaciente";

    /** AD_Table_ID=1200856 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

    /** Column name EXME_TablasPaciente_ID */
    public static final String COLUMNNAME_EXME_TablasPaciente_ID = "EXME_TablasPaciente_ID";

	/** Set Patient Tables	  */
	public void setEXME_TablasPaciente_ID (int EXME_TablasPaciente_ID);

	/** Get Patient Tables	  */
	public int getEXME_TablasPaciente_ID();
}
