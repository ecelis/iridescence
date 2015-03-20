/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_AcctSchema_GL
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_AcctSchema_GL 
{

    /** TableName=C_AcctSchema_GL */
    public static final String Table_Name = "C_AcctSchema_GL";

    /** AD_Table_ID=266 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name Adefas_Acct */
    public static final String COLUMNNAME_Adefas_Acct = "Adefas_Acct";

	/** Set ADEFAS	  */
	public void setAdefas_Acct (int Adefas_Acct);

	/** Get ADEFAS	  */
	public int getAdefas_Acct();

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

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name CommitmentOffset_Acct */
    public static final String COLUMNNAME_CommitmentOffset_Acct = "CommitmentOffset_Acct";

	/** Set Commitment Offset.
	  * Budgetary Commitment Offset Account
	  */
	public void setCommitmentOffset_Acct (int CommitmentOffset_Acct);

	/** Get Commitment Offset.
	  * Budgetary Commitment Offset Account
	  */
	public int getCommitmentOffset_Acct();

    /** Column name CommitmentOffsetSales_Acct */
    public static final String COLUMNNAME_CommitmentOffsetSales_Acct = "CommitmentOffsetSales_Acct";

	/** Set Commitment Offset Sales.
	  * Budgetary Commitment Offset Account for Sales
	  */
	public void setCommitmentOffsetSales_Acct (int CommitmentOffsetSales_Acct);

	/** Get Commitment Offset Sales.
	  * Budgetary Commitment Offset Account for Sales
	  */
	public int getCommitmentOffsetSales_Acct();

    /** Column name CurrencyBalancing_Acct */
    public static final String COLUMNNAME_CurrencyBalancing_Acct = "CurrencyBalancing_Acct";

	/** Set Currency Balancing Acct.
	  * Account used when a currency is out of balance
	  */
	public void setCurrencyBalancing_Acct (int CurrencyBalancing_Acct);

	/** Get Currency Balancing Acct.
	  * Account used when a currency is out of balance
	  */
	public int getCurrencyBalancing_Acct();

    /** Column name IncomeSummary_Acct */
    public static final String COLUMNNAME_IncomeSummary_Acct = "IncomeSummary_Acct";

	/** Set Income Summary Acct.
	  * Income Summary Account 
	  */
	public void setIncomeSummary_Acct (int IncomeSummary_Acct);

	/** Get Income Summary Acct.
	  * Income Summary Account 
	  */
	public int getIncomeSummary_Acct();

    /** Column name IntercompanyDueFrom_Acct */
    public static final String COLUMNNAME_IntercompanyDueFrom_Acct = "IntercompanyDueFrom_Acct";

	/** Set Intercompany Due From Acct.
	  * Intercompany Due From / Receivables Account
	  */
	public void setIntercompanyDueFrom_Acct (int IntercompanyDueFrom_Acct);

	/** Get Intercompany Due From Acct.
	  * Intercompany Due From / Receivables Account
	  */
	public int getIntercompanyDueFrom_Acct();

    /** Column name IntercompanyDueTo_Acct */
    public static final String COLUMNNAME_IntercompanyDueTo_Acct = "IntercompanyDueTo_Acct";

	/** Set Intercompany Due To Acct.
	  * Intercompany Due To / Payable Account
	  */
	public void setIntercompanyDueTo_Acct (int IntercompanyDueTo_Acct);

	/** Get Intercompany Due To Acct.
	  * Intercompany Due To / Payable Account
	  */
	public int getIntercompanyDueTo_Acct();

    /** Column name LeyIngDeve_Acct */
    public static final String COLUMNNAME_LeyIngDeve_Acct = "LeyIngDeve_Acct";

	/** Set Accrued Revenue Act	  */
	public void setLeyIngDeve_Acct (int LeyIngDeve_Acct);

	/** Get Accrued Revenue Act	  */
	public int getLeyIngDeve_Acct();

    /** Column name LeyIngEsti_Acct */
    public static final String COLUMNNAME_LeyIngEsti_Acct = "LeyIngEsti_Acct";

	/** Set Estimated Revenue Act	  */
	public void setLeyIngEsti_Acct (int LeyIngEsti_Acct);

	/** Get Estimated Revenue Act	  */
	public int getLeyIngEsti_Acct();

    /** Column name LeyIngReca_Acct */
    public static final String COLUMNNAME_LeyIngReca_Acct = "LeyIngReca_Acct";

	/** Set Collected Revenue Act	  */
	public void setLeyIngReca_Acct (int LeyIngReca_Acct);

	/** Get Collected Revenue Act	  */
	public int getLeyIngReca_Acct();

    /** Column name LeyIngxEjec_Acct */
    public static final String COLUMNNAME_LeyIngxEjec_Acct = "LeyIngxEjec_Acct";

	/** Set Run Income Act	  */
	public void setLeyIngxEjec_Acct (int LeyIngxEjec_Acct);

	/** Get Run Income Act	  */
	public int getLeyIngxEjec_Acct();

    /** Column name ModPresEgrAprob_Acct */
    public static final String COLUMNNAME_ModPresEgrAprob_Acct = "ModPresEgrAprob_Acct";

	/** Set Modifications to Approved Expenditures Budget	  */
	public void setModPresEgrAprob_Acct (int ModPresEgrAprob_Acct);

	/** Get Modifications to Approved Expenditures Budget	  */
	public int getModPresEgrAprob_Acct();

    /** Column name PPVOffset_Acct */
    public static final String COLUMNNAME_PPVOffset_Acct = "PPVOffset_Acct";

	/** Set PPV Offset.
	  * Purchase Price Variance Offset Account
	  */
	public void setPPVOffset_Acct (int PPVOffset_Acct);

	/** Get PPV Offset.
	  * Purchase Price Variance Offset Account
	  */
	public int getPPVOffset_Acct();

    /** Column name PresEgrAprob_Acct */
    public static final String COLUMNNAME_PresEgrAprob_Acct = "PresEgrAprob_Acct";

	/** Set Approved Expenditure Budget	  */
	public void setPresEgrAprob_Acct (int PresEgrAprob_Acct);

	/** Get Approved Expenditure Budget	  */
	public int getPresEgrAprob_Acct();

    /** Column name PresEgrComp_Acct */
    public static final String COLUMNNAME_PresEgrComp_Acct = "PresEgrComp_Acct";

	/** Set Committed Expenditure Budget	  */
	public void setPresEgrComp_Acct (int PresEgrComp_Acct);

	/** Get Committed Expenditure Budget	  */
	public int getPresEgrComp_Acct();

    /** Column name PresEgrDeve_Acct */
    public static final String COLUMNNAME_PresEgrDeve_Acct = "PresEgrDeve_Acct";

	/** Set Accrued Expenditure Budget	  */
	public void setPresEgrDeve_Acct (int PresEgrDeve_Acct);

	/** Get Accrued Expenditure Budget	  */
	public int getPresEgrDeve_Acct();

    /** Column name PresEgrEjer_Acct */
    public static final String COLUMNNAME_PresEgrEjer_Acct = "PresEgrEjer_Acct";

	/** Set Exercised Expenditure Budget	  */
	public void setPresEgrEjer_Acct (int PresEgrEjer_Acct);

	/** Get Exercised Expenditure Budget	  */
	public int getPresEgrEjer_Acct();

    /** Column name PresEgrPaga_Acct */
    public static final String COLUMNNAME_PresEgrPaga_Acct = "PresEgrPaga_Acct";

	/** Set Paid Expenditure Budget	  */
	public void setPresEgrPaga_Acct (int PresEgrPaga_Acct);

	/** Get Paid Expenditure Budget	  */
	public int getPresEgrPaga_Acct();

    /** Column name PresEgrxEjer_Acct */
    public static final String COLUMNNAME_PresEgrxEjer_Acct = "PresEgrxEjer_Acct";

	/** Set Expenditure Budget by Exercise	  */
	public void setPresEgrxEjer_Acct (int PresEgrxEjer_Acct);

	/** Get Expenditure Budget by Exercise	  */
	public int getPresEgrxEjer_Acct();

    /** Column name RetainedEarning_Acct */
    public static final String COLUMNNAME_RetainedEarning_Acct = "RetainedEarning_Acct";

	/** Set Retained Earning Acct	  */
	public void setRetainedEarning_Acct (int RetainedEarning_Acct);

	/** Get Retained Earning Acct	  */
	public int getRetainedEarning_Acct();

    /** Column name SuperFina_Acct */
    public static final String COLUMNNAME_SuperFina_Acct = "SuperFina_Acct";

	/** Set Financial Surplus	  */
	public void setSuperFina_Acct (int SuperFina_Acct);

	/** Get Financial Surplus	  */
	public int getSuperFina_Acct();

    /** Column name SuspenseBalancing_Acct */
    public static final String COLUMNNAME_SuspenseBalancing_Acct = "SuspenseBalancing_Acct";

	/** Set Suspense Balancing Acct	  */
	public void setSuspenseBalancing_Acct (int SuspenseBalancing_Acct);

	/** Get Suspense Balancing Acct	  */
	public int getSuspenseBalancing_Acct();

    /** Column name SuspenseError_Acct */
    public static final String COLUMNNAME_SuspenseError_Acct = "SuspenseError_Acct";

	/** Set Suspense Error Acct	  */
	public void setSuspenseError_Acct (int SuspenseError_Acct);

	/** Get Suspense Error Acct	  */
	public int getSuspenseError_Acct();

    /** Column name UseCurrencyBalancing */
    public static final String COLUMNNAME_UseCurrencyBalancing = "UseCurrencyBalancing";

	/** Set Use Currency Balancing	  */
	public void setUseCurrencyBalancing (boolean UseCurrencyBalancing);

	/** Get Use Currency Balancing	  */
	public boolean isUseCurrencyBalancing();

    /** Column name UseSuspenseBalancing */
    public static final String COLUMNNAME_UseSuspenseBalancing = "UseSuspenseBalancing";

	/** Set Use Suspense Balancing	  */
	public void setUseSuspenseBalancing (boolean UseSuspenseBalancing);

	/** Get Use Suspense Balancing	  */
	public boolean isUseSuspenseBalancing();

    /** Column name UseSuspenseError */
    public static final String COLUMNNAME_UseSuspenseError = "UseSuspenseError";

	/** Set Use Suspense Error	  */
	public void setUseSuspenseError (boolean UseSuspenseError);

	/** Get Use Suspense Error	  */
	public boolean isUseSuspenseError();
}
