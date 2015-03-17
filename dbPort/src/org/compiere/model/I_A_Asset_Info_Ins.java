/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Info_Ins
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Info_Ins 
{

    /** TableName=A_Asset_Info_Ins */
    public static final String Table_Name = "A_Asset_Info_Ins";

    /** AD_Table_ID=1200792 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_Asset_Info_Ins_ID */
    public static final String COLUMNNAME_A_Asset_Info_Ins_ID = "A_Asset_Info_Ins_ID";

	/** Set A_Asset_Info_Ins_ID	  */
	public void setA_Asset_Info_Ins_ID (int A_Asset_Info_Ins_ID);

	/** Get A_Asset_Info_Ins_ID	  */
	public int getA_Asset_Info_Ins_ID();

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

    /** Column name A_Ins_Premium */
    public static final String COLUMNNAME_A_Ins_Premium = "A_Ins_Premium";

	/** Set Insurance Premium	  */
	public void setA_Ins_Premium (BigDecimal A_Ins_Premium);

	/** Get Insurance Premium	  */
	public BigDecimal getA_Ins_Premium();

    /** Column name A_Insurance_Co */
    public static final String COLUMNNAME_A_Insurance_Co = "A_Insurance_Co";

	/** Set Insurance Company	  */
	public void setA_Insurance_Co (String A_Insurance_Co);

	/** Get Insurance Company	  */
	public String getA_Insurance_Co();

    /** Column name A_Ins_Value */
    public static final String COLUMNNAME_A_Ins_Value = "A_Ins_Value";

	/** Set Insured Value	  */
	public void setA_Ins_Value (BigDecimal A_Ins_Value);

	/** Get Insured Value	  */
	public BigDecimal getA_Ins_Value();

    /** Column name A_Policy_No */
    public static final String COLUMNNAME_A_Policy_No = "A_Policy_No";

	/** Set Policy Number	  */
	public void setA_Policy_No (String A_Policy_No);

	/** Get Policy Number	  */
	public String getA_Policy_No();

    /** Column name A_Renewal_Date */
    public static final String COLUMNNAME_A_Renewal_Date = "A_Renewal_Date";

	/** Set Policy Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date);

	/** Get Policy Renewal Date	  */
	public Timestamp getA_Renewal_Date();

    /** Column name A_Replace_Cost */
    public static final String COLUMNNAME_A_Replace_Cost = "A_Replace_Cost";

	/** Set Replacement Costs	  */
	public void setA_Replace_Cost (BigDecimal A_Replace_Cost);

	/** Get Replacement Costs	  */
	public BigDecimal getA_Replace_Cost();

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Text	  */
	public void setText (String Text);

	/** Get Text	  */
	public String getText();
}
