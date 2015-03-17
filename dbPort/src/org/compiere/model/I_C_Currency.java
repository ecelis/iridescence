/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Currency
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Currency 
{

    /** TableName=C_Currency */
    public static final String Table_Name = "C_Currency";

    /** AD_Table_ID=141 */
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

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

    /** Column name CostingPrecision */
    public static final String COLUMNNAME_CostingPrecision = "CostingPrecision";

	/** Set Costing Precision.
	  * Rounding used costing calculations
	  */
	public void setCostingPrecision (int CostingPrecision);

	/** Get Costing Precision.
	  * Rounding used costing calculations
	  */
	public int getCostingPrecision();

    /** Column name CurSymbol */
    public static final String COLUMNNAME_CurSymbol = "CurSymbol";

	/** Set CurrSymbol.
	  * Symbol of the currency (opt used for printing only)
	  */
	public void setCurSymbol (String CurSymbol);

	/** Get CurrSymbol.
	  * Symbol of the currency (opt used for printing only)
	  */
	public String getCurSymbol();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EMUEntryDate */
    public static final String COLUMNNAME_EMUEntryDate = "EMUEntryDate";

	/** Set EMU Entry Date.
	  * Date when the currency joined / will join the EMU
	  */
	public void setEMUEntryDate (Timestamp EMUEntryDate);

	/** Get EMU Entry Date.
	  * Date when the currency joined / will join the EMU
	  */
	public Timestamp getEMUEntryDate();

    /** Column name EMURate */
    public static final String COLUMNNAME_EMURate = "EMURate";

	/** Set EMU Rate.
	  * Official rate to the Euro
	  */
	public void setEMURate (BigDecimal EMURate);

	/** Get EMU Rate.
	  * Official rate to the Euro
	  */
	public BigDecimal getEMURate();

    /** Column name IsEMUMember */
    public static final String COLUMNNAME_IsEMUMember = "IsEMUMember";

	/** Set EMU Member.
	  * This currency is member if the European Monetary Union
	  */
	public void setIsEMUMember (boolean IsEMUMember);

	/** Get EMU Member.
	  * This currency is member if the European Monetary Union
	  */
	public boolean isEMUMember();

    /** Column name IsEuro */
    public static final String COLUMNNAME_IsEuro = "IsEuro";

	/** Set The Euro Currency.
	  * This currency is the Euro
	  */
	public void setIsEuro (boolean IsEuro);

	/** Get The Euro Currency.
	  * This currency is the Euro
	  */
	public boolean isEuro();

    /** Column name ISO_Code */
    public static final String COLUMNNAME_ISO_Code = "ISO_Code";

	/** Set ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public void setISO_Code (String ISO_Code);

	/** Get ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public String getISO_Code();

    /** Column name RoundOffFactor */
    public static final String COLUMNNAME_RoundOffFactor = "RoundOffFactor";

	/** Set Round Off Factor.
	  * Used to Round Off Payment Amount
	  */
	public void setRoundOffFactor (BigDecimal RoundOffFactor);

	/** Get Round Off Factor.
	  * Used to Round Off Payment Amount
	  */
	public BigDecimal getRoundOffFactor();

    /** Column name StdPrecision */
    public static final String COLUMNNAME_StdPrecision = "StdPrecision";

	/** Set Standard Precision.
	  * Rule for rounding  calculated amounts
	  */
	public void setStdPrecision (int StdPrecision);

	/** Get Standard Precision.
	  * Rule for rounding  calculated amounts
	  */
	public int getStdPrecision();
}
