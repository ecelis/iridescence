/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Remuneration
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Remuneration 
{

    /** TableName=C_Remuneration */
    public static final String Table_Name = "C_Remuneration";

    /** AD_Table_ID=792 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name C_Remuneration_ID */
    public static final String COLUMNNAME_C_Remuneration_ID = "C_Remuneration_ID";

	/** Set Remuneration.
	  * Wage or Salary
	  */
	public void setC_Remuneration_ID (int C_Remuneration_ID);

	/** Get Remuneration.
	  * Wage or Salary
	  */
	public int getC_Remuneration_ID();

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

    /** Column name GrossRAmt */
    public static final String COLUMNNAME_GrossRAmt = "GrossRAmt";

	/** Set Gross Amount.
	  * Gross Remuneration Amount
	  */
	public void setGrossRAmt (BigDecimal GrossRAmt);

	/** Get Gross Amount.
	  * Gross Remuneration Amount
	  */
	public BigDecimal getGrossRAmt();

    /** Column name GrossRCost */
    public static final String COLUMNNAME_GrossRCost = "GrossRCost";

	/** Set Gross Cost.
	  * Gross Remuneration Costs
	  */
	public void setGrossRCost (BigDecimal GrossRCost);

	/** Get Gross Cost.
	  * Gross Remuneration Costs
	  */
	public BigDecimal getGrossRCost();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name OvertimeAmt */
    public static final String COLUMNNAME_OvertimeAmt = "OvertimeAmt";

	/** Set Overtime Amount.
	  * Hourly Overtime Rate
	  */
	public void setOvertimeAmt (BigDecimal OvertimeAmt);

	/** Get Overtime Amount.
	  * Hourly Overtime Rate
	  */
	public BigDecimal getOvertimeAmt();

    /** Column name OvertimeCost */
    public static final String COLUMNNAME_OvertimeCost = "OvertimeCost";

	/** Set Overtime Cost.
	  * Hourly Overtime Cost
	  */
	public void setOvertimeCost (BigDecimal OvertimeCost);

	/** Get Overtime Cost.
	  * Hourly Overtime Cost
	  */
	public BigDecimal getOvertimeCost();

    /** Column name RemunerationType */
    public static final String COLUMNNAME_RemunerationType = "RemunerationType";

	/** Set Remuneration Type.
	  * Type of Remuneration
	  */
	public void setRemunerationType (String RemunerationType);

	/** Get Remuneration Type.
	  * Type of Remuneration
	  */
	public String getRemunerationType();

    /** Column name StandardHours */
    public static final String COLUMNNAME_StandardHours = "StandardHours";

	/** Set Standard Hours.
	  * Standard Work Hours based on Remuneration Type
	  */
	public void setStandardHours (int StandardHours);

	/** Get Standard Hours.
	  * Standard Work Hours based on Remuneration Type
	  */
	public int getStandardHours();
}
