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

/** Generated Model for EXME_PreUniPe
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PreUniPe extends PO implements I_EXME_PreUniPe, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreUniPe (Properties ctx, int EXME_PreUniPe_ID, String trxName)
    {
      super (ctx, EXME_PreUniPe_ID, trxName);
      /** if (EXME_PreUniPe_ID == 0)
        {
			setEXME_PreUni_ID (0);
			setEXME_PreUniPe_ID (0);
			setPeriodo (0);
			setPre_Solicitado (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreUniPe (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreUniPe[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_PreUni getEXME_PreUni() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PreUni.Table_Name);
        I_EXME_PreUni result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PreUni)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PreUni_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget unit.
		@param EXME_PreUni_ID 
		Is the identifier of each budget request by administrative unit
	  */
	public void setEXME_PreUni_ID (int EXME_PreUni_ID)
	{
		if (EXME_PreUni_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreUni_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PreUni_ID, Integer.valueOf(EXME_PreUni_ID));
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

	/** Set Budget Unit detail.
		@param EXME_PreUniPe_ID 
		Is the identifier of each detail of the budget request by administrative unit
	  */
	public void setEXME_PreUniPe_ID (int EXME_PreUniPe_ID)
	{
		if (EXME_PreUniPe_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreUniPe_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreUniPe_ID, Integer.valueOf(EXME_PreUniPe_ID));
	}

	/** Get Budget Unit detail.
		@return Is the identifier of each detail of the budget request by administrative unit
	  */
	public int getEXME_PreUniPe_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreUniPe_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Period.
		@param Periodo 
		Period
	  */
	public void setPeriodo (int Periodo)
	{
		set_Value (COLUMNNAME_Periodo, Integer.valueOf(Periodo));
	}

	/** Get Period.
		@return Period
	  */
	public int getPeriodo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Periodo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}