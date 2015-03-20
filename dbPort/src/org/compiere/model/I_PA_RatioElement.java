/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PA_RatioElement
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PA_RatioElement 
{

    /** TableName=PA_RatioElement */
    public static final String Table_Name = "PA_RatioElement";

    /** AD_Table_ID=836 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name Account_ID */
    public static final String COLUMNNAME_Account_ID = "Account_ID";

	/** Set Account.
	  * Account used
	  */
	public void setAccount_ID (int Account_ID);

	/** Get Account.
	  * Account used
	  */
	public int getAccount_ID();

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

    /** Column name ConstantValue */
    public static final String COLUMNNAME_ConstantValue = "ConstantValue";

	/** Set Constant Value.
	  * Constant value
	  */
	public void setConstantValue (BigDecimal ConstantValue);

	/** Get Constant Value.
	  * Constant value
	  */
	public BigDecimal getConstantValue();

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PA_MeasureCalc_ID */
    public static final String COLUMNNAME_PA_MeasureCalc_ID = "PA_MeasureCalc_ID";

	/** Set Measure Calculation.
	  * Calculation method for measuring performance
	  */
	public void setPA_MeasureCalc_ID (int PA_MeasureCalc_ID);

	/** Get Measure Calculation.
	  * Calculation method for measuring performance
	  */
	public int getPA_MeasureCalc_ID();

	public I_PA_MeasureCalc getPA_MeasureCalc() throws RuntimeException;

    /** Column name PA_RatioElement_ID */
    public static final String COLUMNNAME_PA_RatioElement_ID = "PA_RatioElement_ID";

	/** Set Ratio Element.
	  * Performance Ratio Element
	  */
	public void setPA_RatioElement_ID (int PA_RatioElement_ID);

	/** Get Ratio Element.
	  * Performance Ratio Element
	  */
	public int getPA_RatioElement_ID();

    /** Column name PA_Ratio_ID */
    public static final String COLUMNNAME_PA_Ratio_ID = "PA_Ratio_ID";

	/** Set Ratio.
	  * Performace Ratio
	  */
	public void setPA_Ratio_ID (int PA_Ratio_ID);

	/** Get Ratio.
	  * Performace Ratio
	  */
	public int getPA_Ratio_ID();

	public I_PA_Ratio getPA_Ratio() throws RuntimeException;

    /** Column name PA_RatioUsed_ID */
    public static final String COLUMNNAME_PA_RatioUsed_ID = "PA_RatioUsed_ID";

	/** Set Ratio Used.
	  * Performace Ratio Used
	  */
	public void setPA_RatioUsed_ID (int PA_RatioUsed_ID);

	/** Get Ratio Used.
	  * Performace Ratio Used
	  */
	public int getPA_RatioUsed_ID();

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set Posting Type.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get Posting Type.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

    /** Column name RatioElementType */
    public static final String COLUMNNAME_RatioElementType = "RatioElementType";

	/** Set Element Type.
	  * Ratio Element Type
	  */
	public void setRatioElementType (String RatioElementType);

	/** Get Element Type.
	  * Ratio Element Type
	  */
	public String getRatioElementType();

    /** Column name RatioOperand */
    public static final String COLUMNNAME_RatioOperand = "RatioOperand";

	/** Set Operand.
	  * Ratio Operand
	  */
	public void setRatioOperand (String RatioOperand);

	/** Get Operand.
	  * Ratio Operand
	  */
	public String getRatioOperand();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
