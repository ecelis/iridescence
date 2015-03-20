/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PreUni
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PreUni extends PO implements I_EXME_PreUni, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreUni (Properties ctx, int EXME_PreUni_ID, String trxName)
    {
      super (ctx, EXME_PreUni_ID, trxName);
      /** if (EXME_PreUni_ID == 0)
        {
			setAD_OrgTrx_ID (0);
			setBudgetStatus (null);
			setEjercicio (Env.ZERO);
			setEXME_Partida_ID (0);
			setEXME_PreUni_ID (0);
			setIsApproved (false);
			setPre_Solicitado (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreUni (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreUni[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1)
			 throw new IllegalArgumentException ("AD_OrgTrx_ID is mandatory.");
		set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		if (BudgetStatus == null) throw new IllegalArgumentException ("BudgetStatus is mandatory");
		if (BudgetStatus.equals("A") || BudgetStatus.equals("B") || BudgetStatus.equals("C") || BudgetStatus.equals("N")); else throw new IllegalArgumentException ("BudgetStatus Invalid value - " + BudgetStatus + " - Reference_ID=1200139 - A - B - C - N");		set_Value (COLUMNNAME_BudgetStatus, BudgetStatus);
	}

	/** Get Budget Status.
		@return Indicates the current status of this budget
	  */
	public String getBudgetStatus () 
	{
		return (String)get_Value(COLUMNNAME_BudgetStatus);
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
	public void setEjercicio (BigDecimal Ejercicio)
	{
		if (Ejercicio == null)
			throw new IllegalArgumentException ("Ejercicio is mandatory.");
		set_Value (COLUMNNAME_Ejercicio, Ejercicio);
	}

	/** Get Year.
		@return Year
	  */
	public BigDecimal getEjercicio () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ejercicio);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_Partida getEXME_Partida() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Partida.Table_Name);
        I_EXME_Partida result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Partida)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Partida_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget Item.
		@param EXME_Partida_ID 
		Budget Item
	  */
	public void setEXME_Partida_ID (int EXME_Partida_ID)
	{
		if (EXME_Partida_ID < 1)
			 throw new IllegalArgumentException ("EXME_Partida_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Partida_ID, Integer.valueOf(EXME_Partida_ID));
	}

	/** Get Budget Item.
		@return Budget Item
	  */
	public int getEXME_Partida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Partida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Budget unit.
		@param EXME_PreUni_ID 
		Is the identifier of each budget request by administrative unit
	  */
	public void setEXME_PreUni_ID (int EXME_PreUni_ID)
	{
		if (EXME_PreUni_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreUni_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreUni_ID, Integer.valueOf(EXME_PreUni_ID));
	}

	/** Get Budget unit.
		@return Is the identifier of each budget request by administrative unit
	  */
	public int getEXME_PreUni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreUni_ID);
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

	/** Set Is Distributed.
		@param IsDistributed 
		Describes whether the item is distributed proportionally 
	  */
	public void setIsDistributed (boolean IsDistributed)
	{
		set_Value (COLUMNNAME_IsDistributed, Boolean.valueOf(IsDistributed));
	}

	/** Get Is Distributed.
		@return Describes whether the item is distributed proportionally 
	  */
	public boolean isDistributed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDistributed);
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
		if (Pre_Solicitado == null)
			throw new IllegalArgumentException ("Pre_Solicitado is mandatory.");
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

		if (TipoPresup == null || TipoPresup.equals("O") || TipoPresup.equals("P") || TipoPresup.equals("R") || TipoPresup.equals("V")); else throw new IllegalArgumentException ("TipoPresup Invalid value - " + TipoPresup + " - Reference_ID=1200137 - O - P - R - V");		set_Value (COLUMNNAME_TipoPresup, TipoPresup);
	}

	/** Get Budget Type.
		@return Budget Type
	  */
	public String getTipoPresup () 
	{
		return (String)get_Value(COLUMNNAME_TipoPresup);
	}
}