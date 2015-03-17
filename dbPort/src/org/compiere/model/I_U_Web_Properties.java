/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for U_Web_Properties
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_U_Web_Properties 
{

    /** TableName=U_Web_Properties */
    public static final String Table_Name = "U_Web_Properties";

    /** AD_Table_ID=1200809 */
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

    /** Column name U_Key */
    public static final String COLUMNNAME_U_Key = "U_Key";

	/** Set Key	  */
	public void setU_Key (String U_Key);

	/** Get Key	  */
	public String getU_Key();

    /** Column name U_Value */
    public static final String COLUMNNAME_U_Value = "U_Value";

	/** Set Value	  */
	public void setU_Value (String U_Value);

	/** Get Value	  */
	public String getU_Value();

    /** Column name U_Web_Properties_ID */
    public static final String COLUMNNAME_U_Web_Properties_ID = "U_Web_Properties_ID";

	/** Set Web Properties	  */
	public void setU_Web_Properties_ID (int U_Web_Properties_ID);

	/** Get Web Properties	  */
	public int getU_Web_Properties_ID();
}
