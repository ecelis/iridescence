/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PeriodDoc
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PeriodDoc 
{

    /** TableName=EXME_PeriodDoc */
    public static final String Table_Name = "EXME_PeriodDoc";

    /** AD_Table_ID=1201560 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name CurrentNext */
    public static final String COLUMNNAME_CurrentNext = "CurrentNext";

	/** Set Current Next.
	  * The next number to be used
	  */
	public void setCurrentNext (int CurrentNext);

	/** Get Current Next.
	  * The next number to be used
	  */
	public int getCurrentNext();

    /** Column name CurrentNextBudget */
    public static final String COLUMNNAME_CurrentNextBudget = "CurrentNextBudget";

	/** Set Current Next (Budget)	  */
	public void setCurrentNextBudget (int CurrentNextBudget);

	/** Get Current Next (Budget)	  */
	public int getCurrentNextBudget();

    /** Column name EXME_PeriodDoc_ID */
    public static final String COLUMNNAME_EXME_PeriodDoc_ID = "EXME_PeriodDoc_ID";

	/** Set Period Documents	  */
	public void setEXME_PeriodDoc_ID (int EXME_PeriodDoc_ID);

	/** Get Period Documents	  */
	public int getEXME_PeriodDoc_ID();

    /** Column name PolicyType */
    public static final String COLUMNNAME_PolicyType = "PolicyType";

	/** Set Policy Type	  */
	public void setPolicyType (String PolicyType);

	/** Get Policy Type	  */
	public String getPolicyType();
}
