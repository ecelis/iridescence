/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for I_EXME_Budget
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Budget extends PO implements I_I_EXME_Budget, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Budget (Properties ctx, int I_EXME_Budget_ID, String trxName)
    {
      super (ctx, I_EXME_Budget_ID, trxName);
      /** if (I_EXME_Budget_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Budget (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Budget[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Import budget.
		@param I_EXME_Budget_ID 
		Allows the importation of Budgets
	  */
	public void setI_EXME_Budget_ID (int I_EXME_Budget_ID)
	{
		if (I_EXME_Budget_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Budget_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Budget_ID, Integer.valueOf(I_EXME_Budget_ID));
	}

	/** Get Import budget.
		@return Allows the importation of Budgets
	  */
	public int getI_EXME_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Organization.
		@param Organizacion 
		Specify the Organization
	  */
	public void setOrganizacion (String Organizacion)
	{
		set_Value (COLUMNNAME_Organizacion, Organizacion);
	}

	/** Get Organization.
		@return Specify the Organization
	  */
	public String getOrganizacion () 
	{
		return (String)get_Value(COLUMNNAME_Organizacion);
	}

	/** Set Item.
		@param Partida 
		Specify the budget item
	  */
	public void setPartida (String Partida)
	{
		set_Value (COLUMNNAME_Partida, Partida);
	}

	/** Get Item.
		@return Specify the budget item
	  */
	public String getPartida () 
	{
		return (String)get_Value(COLUMNNAME_Partida);
	}

	/** Set Period.
		@param Periodo 
		Period
	  */
	public void setPeriodo (String Periodo)
	{
		set_Value (COLUMNNAME_Periodo, Periodo);
	}

	/** Get Period.
		@return Period
	  */
	public String getPeriodo () 
	{
		return (String)get_Value(COLUMNNAME_Periodo);
	}

	/** Set Authorized .
		@param Pre_Autorizado 
		The amount authorized for the budget
	  */
	public void setPre_Autorizado (String Pre_Autorizado)
	{
		set_Value (COLUMNNAME_Pre_Autorizado, Pre_Autorizado);
	}

	/** Get Authorized .
		@return The amount authorized for the budget
	  */
	public String getPre_Autorizado () 
	{
		return (String)get_Value(COLUMNNAME_Pre_Autorizado);
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
}