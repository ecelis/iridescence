/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ASP_Process_Para
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_ASP_Process_Para 
{

    /** TableName=ASP_Process_Para */
    public static final String Table_Name = "ASP_Process_Para";

    /** AD_Table_ID=1200687 */
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

    /** Column name AD_Process_Para_ID */
    public static final String COLUMNNAME_AD_Process_Para_ID = "AD_Process_Para_ID";

	/** Set Process Parameter.
	  * Process Parameter
	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID);

	/** Get Process Parameter.
	  * Process Parameter
	  */
	public int getAD_Process_Para_ID();

	public I_AD_Process_Para getAD_Process_Para() throws RuntimeException;

    /** Column name ASP_Process_ID */
    public static final String COLUMNNAME_ASP_Process_ID = "ASP_Process_ID";

	/** Set ASP Process	  */
	public void setASP_Process_ID (int ASP_Process_ID);

	/** Get ASP Process	  */
	public int getASP_Process_ID();

	public I_ASP_Process getASP_Process() throws RuntimeException;

    /** Column name ASP_Process_Para_ID */
    public static final String COLUMNNAME_ASP_Process_Para_ID = "ASP_Process_Para_ID";

	/** Set ASP Process Parameter	  */
	public void setASP_Process_Para_ID (int ASP_Process_Para_ID);

	/** Get ASP Process Parameter	  */
	public int getASP_Process_Para_ID();

    /** Column name ASP_Status */
    public static final String COLUMNNAME_ASP_Status = "ASP_Status";

	/** Set ASP Status	  */
	public void setASP_Status (String ASP_Status);

	/** Get ASP Status	  */
	public String getASP_Status();
}
