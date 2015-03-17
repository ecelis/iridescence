/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_PeriodControl
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_PeriodControl 
{

    /** TableName=C_PeriodControl */
    public static final String Table_Name = "C_PeriodControl";

    /** AD_Table_ID=229 */
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

    /** Column name C_PeriodControl_ID */
    public static final String COLUMNNAME_C_PeriodControl_ID = "C_PeriodControl_ID";

	/** Set Period Control	  */
	public void setC_PeriodControl_ID (int C_PeriodControl_ID);

	/** Get Period Control	  */
	public int getC_PeriodControl_ID();

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public I_C_Period getC_Period() throws RuntimeException;

    /** Column name DocBaseType */
    public static final String COLUMNNAME_DocBaseType = "DocBaseType";

	/** Set Document BaseType.
	  * Logical type of document
	  */
	public void setDocBaseType (String DocBaseType);

	/** Get Document BaseType.
	  * Logical type of document
	  */
	public String getDocBaseType();

    /** Column name PeriodAction */
    public static final String COLUMNNAME_PeriodAction = "PeriodAction";

	/** Set Period Action.
	  * Action taken for this period
	  */
	public void setPeriodAction (String PeriodAction);

	/** Get Period Action.
	  * Action taken for this period
	  */
	public String getPeriodAction();

    /** Column name PeriodStatus */
    public static final String COLUMNNAME_PeriodStatus = "PeriodStatus";

	/** Set Period Status.
	  * Current state of this period
	  */
	public void setPeriodStatus (String PeriodStatus);

	/** Get Period Status.
	  * Current state of this period
	  */
	public String getPeriodStatus();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
