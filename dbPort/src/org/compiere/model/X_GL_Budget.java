/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for GL_Budget
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_GL_Budget extends PO implements I_GL_Budget, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_GL_Budget (Properties ctx, int GL_Budget_ID, String trxName)
    {
      super (ctx, GL_Budget_ID, trxName);
      /** if (GL_Budget_ID == 0)
        {
			setEjercicio (0);
			setFechaFin (new Timestamp( System.currentTimeMillis() ));
			setFechaIni (new Timestamp( System.currentTimeMillis() ));
			setGL_Budget_ID (0);
			setIsPrimary (false);
			setName (null);
			setTipoPresup (null);
        } */
    }

    /** Load Constructor */
    public X_GL_Budget (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_GL_Budget[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Amplia_Reduce AD_Reference_ID=1200138 */
	public static final int AMPLIA_REDUCE_AD_Reference_ID=1200138;
	/** Expanded = A */
	public static final String AMPLIA_REDUCE_Expanded = "A";
	/** Not Applicable = O */
	public static final String AMPLIA_REDUCE_NotApplicable = "O";
	/** Reduced = R */
	public static final String AMPLIA_REDUCE_Reduced = "R";
	/** Set Enlarged / Reduced.
		@param Amplia_Reduce 
		Describe whether the budget has been expanded or reduced
	  */
	public void setAmplia_Reduce (String Amplia_Reduce)
	{

		if (Amplia_Reduce == null || Amplia_Reduce.equals("A") || Amplia_Reduce.equals("O") || Amplia_Reduce.equals("R")); else throw new IllegalArgumentException ("Amplia_Reduce Invalid value - " + Amplia_Reduce + " - Reference_ID=1200138 - A - O - R");		set_Value (COLUMNNAME_Amplia_Reduce, Amplia_Reduce);
	}

	/** Get Enlarged / Reduced.
		@return Describe whether the budget has been expanded or reduced
	  */
	public String getAmplia_Reduce () 
	{
		return (String)get_Value(COLUMNNAME_Amplia_Reduce);
	}

	/** BudgetStatus AD_Reference_ID=1200139 */
	public static final int BUDGETSTATUS_AD_Reference_ID=1200139;
	/** Authorized = A */
	public static final String BUDGETSTATUS_Authorized = "A";
	/** Draft  = B */
	public static final String BUDGETSTATUS_Draft = "B";
	/** Consolidated = C */
	public static final String BUDGETSTATUS_Consolidated = "C";
	/** Unconsolidated = N */
	public static final String BUDGETSTATUS_Unconsolidated = "N";
	/** Set Budget Status.
		@param BudgetStatus 
		Indicates the current status of this budget
	  */
	public void setBudgetStatus (String BudgetStatus)
	{

		if (BudgetStatus == null || BudgetStatus.equals("A") || BudgetStatus.equals("B") || BudgetStatus.equals("C") || BudgetStatus.equals("N")); else throw new IllegalArgumentException ("BudgetStatus Invalid value - " + BudgetStatus + " - Reference_ID=1200139 - A - B - C - N");		set_Value (COLUMNNAME_BudgetStatus, BudgetStatus);
	}

	/** Get Budget Status.
		@return Indicates the current status of this budget
	  */
	public String getBudgetStatus () 
	{
		return (String)get_Value(COLUMNNAME_BudgetStatus);
	}

	public I_C_Activity getC_Activity() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Activity.Table_Name);
        I_C_Activity result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Activity)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Activity_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Responsible Units.
		@param C_Activity_ID 
		Responsible Units
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Responsible Units.
		@return Responsible Units
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 1) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Target Document Type.
		@param C_DocTypeTarget_ID 
		Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID)
	{
		if (C_DocTypeTarget_ID < 1) 
			set_Value (COLUMNNAME_C_DocTypeTarget_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocTypeTarget_ID, Integer.valueOf(C_DocTypeTarget_ID));
	}

	/** Get Target Document Type.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeTarget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Year.
		@param Ejercicio 
		Year
	  */
	public void setEjercicio (int Ejercicio)
	{
		set_Value (COLUMNNAME_Ejercicio, Integer.valueOf(Ejercicio));
	}

	/** Get Year.
		@return Year
	  */
	public int getEjercicio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ejercicio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		if (FechaFin == null)
			throw new IllegalArgumentException ("FechaFin is mandatory.");
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		if (FechaIni == null)
			throw new IllegalArgumentException ("FechaIni is mandatory.");
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** Set Budget.
		@param GL_Budget_ID 
		General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID)
	{
		if (GL_Budget_ID < 1)
			 throw new IllegalArgumentException ("GL_Budget_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_Budget_ID, Integer.valueOf(GL_Budget_ID));
	}

	/** Get Budget.
		@return General Ledger Budget
	  */
	public int getGL_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Budget Reference.
		@param GL_BudgetRef_ID 
		Budget Reference
	  */
	public void setGL_BudgetRef_ID (int GL_BudgetRef_ID)
	{
		if (GL_BudgetRef_ID < 1) 
			set_Value (COLUMNNAME_GL_BudgetRef_ID, null);
		else 
			set_Value (COLUMNNAME_GL_BudgetRef_ID, Integer.valueOf(GL_BudgetRef_ID));
	}

	/** Get Budget Reference.
		@return Budget Reference
	  */
	public int getGL_BudgetRef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_BudgetRef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Primary.
		@param IsPrimary 
		Indicates if this is the primary budget
	  */
	public void setIsPrimary (boolean IsPrimary)
	{
		set_Value (COLUMNNAME_IsPrimary, Boolean.valueOf(IsPrimary));
	}

	/** Get Primary.
		@return Indicates if this is the primary budget
	  */
	public boolean isPrimary () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrimary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_Value (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Transfered.
		@param IsTransfered 
		Sets whether this transfer registration 
	  */
	public void setIsTransfered (boolean IsTransfered)
	{
		set_Value (COLUMNNAME_IsTransfered, Boolean.valueOf(IsTransfered));
	}

	/** Get Is Transfered.
		@return Sets whether this transfer registration 
	  */
	public boolean isTransfered () 
	{
		Object oo = get_Value(COLUMNNAME_IsTransfered);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Authorized .
		@param Pre_Autorizado 
		The amount authorized for the budget
	  */
	public void setPre_Autorizado (BigDecimal Pre_Autorizado)
	{
		set_Value (COLUMNNAME_Pre_Autorizado, Pre_Autorizado);
	}

	/** Get Authorized .
		@return The amount authorized for the budget
	  */
	public BigDecimal getPre_Autorizado () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pre_Autorizado);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Committed.
		@param Pre_Comprometido 
		Committed is the amount of the budget 
	  */
	public void setPre_Comprometido (BigDecimal Pre_Comprometido)
	{
		set_Value (COLUMNNAME_Pre_Comprometido, Pre_Comprometido);
	}

	/** Get Committed.
		@return Committed is the amount of the budget 
	  */
	public BigDecimal getPre_Comprometido () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pre_Comprometido);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Earned.
		@param Pre_Devengado 
		The amount of budget earned
	  */
	public void setPre_Devengado (BigDecimal Pre_Devengado)
	{
		set_Value (COLUMNNAME_Pre_Devengado, Pre_Devengado);
	}

	/** Get Earned.
		@return The amount of budget earned
	  */
	public BigDecimal getPre_Devengado () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pre_Devengado);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Exercised.
		@param Pre_Ejercido 
		The amount of the budget Exercised
	  */
	public void setPre_Ejercido (BigDecimal Pre_Ejercido)
	{
		set_Value (COLUMNNAME_Pre_Ejercido, Pre_Ejercido);
	}

	/** Get Exercised.
		@return The amount of the budget Exercised
	  */
	public BigDecimal getPre_Ejercido () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pre_Ejercido);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Requested.
		@param Pre_Solicitado 
		Requested is the amount of the budget
	  */
	public void setPre_Solicitado (BigDecimal Pre_Solicitado)
	{
		set_Value (COLUMNNAME_Pre_Solicitado, Pre_Solicitado);
	}

	/** Get Requested.
		@return Requested is the amount of the budget
	  */
	public BigDecimal getPre_Solicitado () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pre_Solicitado);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** TipoPresup AD_Reference_ID=1200137 */
	public static final int TIPOPRESUP_AD_Reference_ID=1200137;
	/** Optimistic = O */
	public static final String TIPOPRESUP_Optimistic = "O";
	/** Pessimistic = P */
	public static final String TIPOPRESUP_Pessimistic = "P";
	/** Realistic = R */
	public static final String TIPOPRESUP_Realistic = "R";
	/** Divers = V */
	public static final String TIPOPRESUP_Divers = "V";
	/** Set Budget Type.
		@param TipoPresup 
		Budget Type
	  */
	public void setTipoPresup (String TipoPresup)
	{
		if (TipoPresup == null) throw new IllegalArgumentException ("TipoPresup is mandatory");
		if (TipoPresup.equals("O") || TipoPresup.equals("P") || TipoPresup.equals("R") || TipoPresup.equals("V")); else throw new IllegalArgumentException ("TipoPresup Invalid value - " + TipoPresup + " - Reference_ID=1200137 - O - P - R - V");		set_Value (COLUMNNAME_TipoPresup, TipoPresup);
	}

	/** Get Budget Type.
		@return Budget Type
	  */
	public String getTipoPresup () 
	{
		return (String)get_Value(COLUMNNAME_TipoPresup);
	}
}