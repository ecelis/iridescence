/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GL_Budget
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_GL_Budget 
{

    /** TableName=GL_Budget */
    public static final String Table_Name = "GL_Budget";

    /** AD_Table_ID=271 */
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

    /** Column name Amplia_Reduce */
    public static final String COLUMNNAME_Amplia_Reduce = "Amplia_Reduce";

	/** Set Enlarged / Reduced.
	  * Describe whether the budget has been expanded or reduced
	  */
	public void setAmplia_Reduce (String Amplia_Reduce);

	/** Get Enlarged / Reduced.
	  * Describe whether the budget has been expanded or reduced
	  */
	public String getAmplia_Reduce();

    /** Column name BudgetStatus */
    public static final String COLUMNNAME_BudgetStatus = "BudgetStatus";

	/** Set Budget Status.
	  * Indicates the current status of this budget
	  */
	public void setBudgetStatus (String BudgetStatus);

	/** Get Budget Status.
	  * Indicates the current status of this budget
	  */
	public String getBudgetStatus();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Responsible Units.
	  * Responsible Units
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Responsible Units.
	  * Responsible Units
	  */
	public int getC_Activity_ID();

	public I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_DocTypeTarget_ID */
    public static final String COLUMNNAME_C_DocTypeTarget_ID = "C_DocTypeTarget_ID";

	/** Set Target Document Type.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID);

	/** Get Target Document Type.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID();

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name Ejercicio */
    public static final String COLUMNNAME_Ejercicio = "Ejercicio";

	/** Set Year.
	  * Year
	  */
	public void setEjercicio (int Ejercicio);

	/** Get Year.
	  * Year
	  */
	public int getEjercicio();

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

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

    /** Column name GL_BudgetRef_ID */
    public static final String COLUMNNAME_GL_BudgetRef_ID = "GL_BudgetRef_ID";

	/** Set Budget Reference.
	  * Budget Reference
	  */
	public void setGL_BudgetRef_ID (int GL_BudgetRef_ID);

	/** Get Budget Reference.
	  * Budget Reference
	  */
	public int getGL_BudgetRef_ID();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsPrimary */
    public static final String COLUMNNAME_IsPrimary = "IsPrimary";

	/** Set Primary.
	  * Indicates if this is the primary budget
	  */
	public void setIsPrimary (boolean IsPrimary);

	/** Get Primary.
	  * Indicates if this is the primary budget
	  */
	public boolean isPrimary();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name IsTransfered */
    public static final String COLUMNNAME_IsTransfered = "IsTransfered";

	/** Set Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public void setIsTransfered (boolean IsTransfered);

	/** Get Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public boolean isTransfered();

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

    /** Column name Pre_Autorizado */
    public static final String COLUMNNAME_Pre_Autorizado = "Pre_Autorizado";

	/** Set Authorized .
	  * The amount authorized for the budget
	  */
	public void setPre_Autorizado (BigDecimal Pre_Autorizado);

	/** Get Authorized .
	  * The amount authorized for the budget
	  */
	public BigDecimal getPre_Autorizado();

    /** Column name Pre_Comprometido */
    public static final String COLUMNNAME_Pre_Comprometido = "Pre_Comprometido";

	/** Set Committed.
	  * Committed is the amount of the budget 
	  */
	public void setPre_Comprometido (BigDecimal Pre_Comprometido);

	/** Get Committed.
	  * Committed is the amount of the budget 
	  */
	public BigDecimal getPre_Comprometido();

    /** Column name Pre_Devengado */
    public static final String COLUMNNAME_Pre_Devengado = "Pre_Devengado";

	/** Set Earned.
	  * The amount of budget earned
	  */
	public void setPre_Devengado (BigDecimal Pre_Devengado);

	/** Get Earned.
	  * The amount of budget earned
	  */
	public BigDecimal getPre_Devengado();

    /** Column name Pre_Ejercido */
    public static final String COLUMNNAME_Pre_Ejercido = "Pre_Ejercido";

	/** Set Exercised.
	  * The amount of the budget Exercised
	  */
	public void setPre_Ejercido (BigDecimal Pre_Ejercido);

	/** Get Exercised.
	  * The amount of the budget Exercised
	  */
	public BigDecimal getPre_Ejercido();

    /** Column name Pre_Solicitado */
    public static final String COLUMNNAME_Pre_Solicitado = "Pre_Solicitado";

	/** Set Requested.
	  * Requested is the amount of the budget
	  */
	public void setPre_Solicitado (BigDecimal Pre_Solicitado);

	/** Get Requested.
	  * Requested is the amount of the budget
	  */
	public BigDecimal getPre_Solicitado();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name TipoPresup */
    public static final String COLUMNNAME_TipoPresup = "TipoPresup";

	/** Set Budget Type.
	  * Budget Type
	  */
	public void setTipoPresup (String TipoPresup);

	/** Get Budget Type.
	  * Budget Type
	  */
	public String getTipoPresup();
}
