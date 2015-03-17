/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Info_Lic
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Info_Lic 
{

    /** TableName=A_Asset_Info_Lic */
    public static final String Table_Name = "A_Asset_Info_Lic";

    /** AD_Table_ID=1200791 */
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

    /** Column name A_Asset_Info_Lic_ID */
    public static final String COLUMNNAME_A_Asset_Info_Lic_ID = "A_Asset_Info_Lic_ID";

	/** Set A Asset Info Lic ID	  */
	public void setA_Asset_Info_Lic_ID (int A_Asset_Info_Lic_ID);

	/** Get A Asset Info Lic ID	  */
	public int getA_Asset_Info_Lic_ID();

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

    /** Column name A_Issuing_Agency */
    public static final String COLUMNNAME_A_Issuing_Agency = "A_Issuing_Agency";

	/** Set Issuing Agency	  */
	public void setA_Issuing_Agency (String A_Issuing_Agency);

	/** Get Issuing Agency	  */
	public String getA_Issuing_Agency();

    /** Column name A_License_Fee */
    public static final String COLUMNNAME_A_License_Fee = "A_License_Fee";

	/** Set License Fee	  */
	public void setA_License_Fee (BigDecimal A_License_Fee);

	/** Get License Fee	  */
	public BigDecimal getA_License_Fee();

    /** Column name A_License_No */
    public static final String COLUMNNAME_A_License_No = "A_License_No";

	/** Set License No	  */
	public void setA_License_No (String A_License_No);

	/** Get License No	  */
	public String getA_License_No();

    /** Column name A_Renewal_Date */
    public static final String COLUMNNAME_A_Renewal_Date = "A_Renewal_Date";

	/** Set Policy Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date);

	/** Get Policy Renewal Date	  */
	public Timestamp getA_Renewal_Date();

    /** Column name A_State */
    public static final String COLUMNNAME_A_State = "A_State";

	/** Set Account State.
	  * State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State);

	/** Get Account State.
	  * State of the Credit Card or Account holder
	  */
	public String getA_State();

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Text	  */
	public void setText (String Text);

	/** Get Text	  */
	public String getText();
}
