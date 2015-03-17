/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_CyclePhase
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_CyclePhase 
{

    /** TableName=C_CyclePhase */
    public static final String Table_Name = "C_CyclePhase";

    /** AD_Table_ID=433 */
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

    /** Column name C_CycleStep_ID */
    public static final String COLUMNNAME_C_CycleStep_ID = "C_CycleStep_ID";

	/** Set Cycle Step.
	  * The step for this Cycle
	  */
	public void setC_CycleStep_ID (int C_CycleStep_ID);

	/** Get Cycle Step.
	  * The step for this Cycle
	  */
	public int getC_CycleStep_ID();

	public I_C_CycleStep getC_CycleStep() throws RuntimeException;

    /** Column name C_Phase_ID */
    public static final String COLUMNNAME_C_Phase_ID = "C_Phase_ID";

	/** Set Standard Phase.
	  * Standard Phase of the Project Type
	  */
	public void setC_Phase_ID (int C_Phase_ID);

	/** Get Standard Phase.
	  * Standard Phase of the Project Type
	  */
	public int getC_Phase_ID();

	public I_C_Phase getC_Phase() throws RuntimeException;
}
