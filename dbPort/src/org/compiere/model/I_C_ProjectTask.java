/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_ProjectTask
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_ProjectTask 
{

    /** TableName=C_ProjectTask */
    public static final String Table_Name = "C_ProjectTask";

    /** AD_Table_ID=584 */
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

    /** Column name CommittedAmt */
    public static final String COLUMNNAME_CommittedAmt = "CommittedAmt";

	/** Set Committed Amount.
	  * The (legal) commitment amount
	  */
	public void setCommittedAmt (BigDecimal CommittedAmt);

	/** Get Committed Amount.
	  * The (legal) commitment amount
	  */
	public BigDecimal getCommittedAmt();

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

    /** Column name C_Task_ID */
    public static final String COLUMNNAME_C_Task_ID = "C_Task_ID";

	/** Set Standard Task.
	  * Standard Project Type Task
	  */
	public void setC_Task_ID (int C_Task_ID);

	/** Get Standard Task.
	  * Standard Project Type Task
	  */
	public int getC_Task_ID();

	public I_C_Task getC_Task() throws RuntimeException;

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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name PlannedAmt */
    public static final String COLUMNNAME_PlannedAmt = "PlannedAmt";

	/** Set Planned Amount.
	  * Planned amount for this project
	  */
	public void setPlannedAmt (BigDecimal PlannedAmt);

	/** Get Planned Amount.
	  * Planned amount for this project
	  */
	public BigDecimal getPlannedAmt();

    /** Column name ProjInvoiceRule */
    public static final String COLUMNNAME_ProjInvoiceRule = "ProjInvoiceRule";

	/** Set Invoice Rule.
	  * Invoice Rule for the project
	  */
	public void setProjInvoiceRule (String ProjInvoiceRule);

	/** Get Invoice Rule.
	  * Invoice Rule for the project
	  */
	public String getProjInvoiceRule();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

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