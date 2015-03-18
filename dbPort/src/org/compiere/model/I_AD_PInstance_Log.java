/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_PInstance_Log
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_PInstance_Log 
{

    /** TableName=AD_PInstance_Log */
    public static final String Table_Name = "AD_PInstance_Log";

    /** AD_Table_ID=578 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

	public I_AD_PInstance getAD_PInstance() throws RuntimeException;

    /** Column name Log_ID */
    public static final String COLUMNNAME_Log_ID = "Log_ID";

	/** Set Log	  */
	public void setLog_ID (int Log_ID);

	/** Get Log	  */
	public int getLog_ID();

    /** Column name P_Date */
    public static final String COLUMNNAME_P_Date = "P_Date";

	/** Set Process Date.
	  * Process Parameter
	  */
	public void setP_Date (Timestamp P_Date);

	/** Get Process Date.
	  * Process Parameter
	  */
	public Timestamp getP_Date();

    /** Column name P_ID */
    public static final String COLUMNNAME_P_ID = "P_ID";

	/** Set Process ID	  */
	public void setP_ID (int P_ID);

	/** Get Process ID	  */
	public int getP_ID();

    /** Column name P_Msg */
    public static final String COLUMNNAME_P_Msg = "P_Msg";

	/** Set Process Message	  */
	public void setP_Msg (String P_Msg);

	/** Get Process Message	  */
	public String getP_Msg();

    /** Column name P_Number */
    public static final String COLUMNNAME_P_Number = "P_Number";

	/** Set Process Number.
	  * Process Parameter
	  */
	public void setP_Number (BigDecimal P_Number);

	/** Get Process Number.
	  * Process Parameter
	  */
	public BigDecimal getP_Number();
}