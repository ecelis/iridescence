/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GL_BudgetControl
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_GL_BudgetControl 
{

    /** TableName=GL_BudgetControl */
    public static final String Table_Name = "GL_BudgetControl";

    /** AD_Table_ID=822 */
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

    /** Column name BudgetControlScope */
    public static final String COLUMNNAME_BudgetControlScope = "BudgetControlScope";

	/** Set Control Scope.
	  * Scope of the Budget Control
	  */
	public void setBudgetControlScope (String BudgetControlScope);

	/** Get Control Scope.
	  * Scope of the Budget Control
	  */
	public String getBudgetControlScope();

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

    /** Column name CommitmentType */
    public static final String COLUMNNAME_CommitmentType = "CommitmentType";

	/** Set Commitment Type.
	  * Create Commitment and/or Reservations for Budget Control
	  */
	public void setCommitmentType (String CommitmentType);

	/** Get Commitment Type.
	  * Create Commitment and/or Reservations for Budget Control
	  */
	public String getCommitmentType();

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

    /** Column name GL_BudgetControl_ID */
    public static final String COLUMNNAME_GL_BudgetControl_ID = "GL_BudgetControl_ID";

	/** Set Budget Control.
	  * Budget Control
	  */
	public void setGL_BudgetControl_ID (int GL_BudgetControl_ID);

	/** Get Budget Control.
	  * Budget Control
	  */
	public int getGL_BudgetControl_ID();

    /** Column name GL_Budget_ID */
    public static final String COLUMNNAME_GL_Budget_ID = "GL_Budget_ID";

	/** Set Budget.
	  * General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID);

	/** Get Budget.
	  * General Ledger Budget
	  */
	public int getGL_Budget_ID();

	public I_GL_Budget getGL_Budget() throws RuntimeException;

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

    /** Column name IsBeforeApproval */
    public static final String COLUMNNAME_IsBeforeApproval = "IsBeforeApproval";

	/** Set Before Approval.
	  * The Check is before the (manual) approval
	  */
	public void setIsBeforeApproval (boolean IsBeforeApproval);

	/** Get Before Approval.
	  * The Check is before the (manual) approval
	  */
	public boolean isBeforeApproval();

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

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}
