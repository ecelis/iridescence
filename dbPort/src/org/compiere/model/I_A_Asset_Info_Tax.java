/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Info_Tax
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Info_Tax 
{

    /** TableName=A_Asset_Info_Tax */
    public static final String Table_Name = "A_Asset_Info_Tax";

    /** AD_Table_ID=1200788 */
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

    /** Column name A_Asset_Info_Tax_ID */
    public static final String COLUMNNAME_A_Asset_Info_Tax_ID = "A_Asset_Info_Tax_ID";

	/** Set A_Asset_Info_Tax_ID	  */
	public void setA_Asset_Info_Tax_ID (int A_Asset_Info_Tax_ID);

	/** Get A_Asset_Info_Tax_ID	  */
	public int getA_Asset_Info_Tax_ID();

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

    /** Column name A_Finance_Meth */
    public static final String COLUMNNAME_A_Finance_Meth = "A_Finance_Meth";

	/** Set Finance Method	  */
	public void setA_Finance_Meth (String A_Finance_Meth);

	/** Get Finance Method	  */
	public String getA_Finance_Meth();

    /** Column name A_Investment_CR */
    public static final String COLUMNNAME_A_Investment_CR = "A_Investment_CR";

	/** Set Investment Credit	  */
	public void setA_Investment_CR (int A_Investment_CR);

	/** Get Investment Credit	  */
	public int getA_Investment_CR();

    /** Column name A_New_Used */
    public static final String COLUMNNAME_A_New_Used = "A_New_Used";

	/** Set Purchased New?	  */
	public void setA_New_Used (boolean A_New_Used);

	/** Get Purchased New?	  */
	public boolean isA_New_Used();

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

    /** Column name A_Tax_Entity */
    public static final String COLUMNNAME_A_Tax_Entity = "A_Tax_Entity";

	/** Set Tax Entity	  */
	public void setA_Tax_Entity (String A_Tax_Entity);

	/** Get Tax Entity	  */
	public String getA_Tax_Entity();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();
}
