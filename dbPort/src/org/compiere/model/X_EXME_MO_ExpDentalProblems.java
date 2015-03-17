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

/** Generated Model for EXME_MO_ExpDentalProblems
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_ExpDentalProblems extends PO implements I_EXME_MO_ExpDentalProblems, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_ExpDentalProblems (Properties ctx, int EXME_MO_ExpDentalProblems_ID, String trxName)
    {
      super (ctx, EXME_MO_ExpDentalProblems_ID, trxName);
      /** if (EXME_MO_ExpDentalProblems_ID == 0)
        {
			setEXME_MO_ExpDentalProblems_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_ExpDentalProblems (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_ExpDentalProblems[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Dental Face.
		@param Cara Dental Face	  */
	public void setCara (BigDecimal Cara)
	{
		set_Value (COLUMNNAME_Cara, Cara);
	}

	/** Get Dental Face.
		@return Dental Face	  */
	public BigDecimal getCara () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cara);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_EXME_MO_DentalProblems getEXME_MO_DentalProblems() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_DentalProblems.Table_Name);
        I_EXME_MO_DentalProblems result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_DentalProblems)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_DentalProblems_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dental Problem.
		@param EXME_MO_DentalProblems_ID Dental Problem	  */
	public void setEXME_MO_DentalProblems_ID (int EXME_MO_DentalProblems_ID)
	{
		if (EXME_MO_DentalProblems_ID < 1) 
			set_Value (COLUMNNAME_EXME_MO_DentalProblems_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MO_DentalProblems_ID, Integer.valueOf(EXME_MO_DentalProblems_ID));
	}

	/** Get Dental Problem.
		@return Dental Problem	  */
	public int getEXME_MO_DentalProblems_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_DentalProblems_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exp Dental Problem.
		@param EXME_MO_ExpDentalProblems_ID Exp Dental Problem	  */
	public void setEXME_MO_ExpDentalProblems_ID (int EXME_MO_ExpDentalProblems_ID)
	{
		if (EXME_MO_ExpDentalProblems_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_ExpDentalProblems_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_ExpDentalProblems_ID, Integer.valueOf(EXME_MO_ExpDentalProblems_ID));
	}

	/** Get Exp Dental Problem.
		@return Exp Dental Problem	  */
	public int getEXME_MO_ExpDentalProblems_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_ExpDentalProblems_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MO_Expediente getEXME_MO_Expediente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_Expediente.Table_Name);
        I_EXME_MO_Expediente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_Expediente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_Expediente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set MO Record.
		@param EXME_MO_Expediente_ID MO Record	  */
	public void setEXME_MO_Expediente_ID (int EXME_MO_Expediente_ID)
	{
		if (EXME_MO_Expediente_ID < 1) 
			set_Value (COLUMNNAME_EXME_MO_Expediente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MO_Expediente_ID, Integer.valueOf(EXME_MO_Expediente_ID));
	}

	/** Get MO Record.
		@return MO Record	  */
	public int getEXME_MO_Expediente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Expediente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FromTable.
		@param FromTable FromTable	  */
	public void setFromTable (boolean FromTable)
	{
		set_Value (COLUMNNAME_FromTable, Boolean.valueOf(FromTable));
	}

	/** Get FromTable.
		@return FromTable	  */
	public boolean isFromTable () 
	{
		Object oo = get_Value(COLUMNNAME_FromTable);
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

	/** Set Value.
		@param Valor Value	  */
	public void setValor (String Valor)
	{
		set_Value (COLUMNNAME_Valor, Valor);
	}

	/** Get Value.
		@return Value	  */
	public String getValor () 
	{
		return (String)get_Value(COLUMNNAME_Valor);
	}

	/** Set Vestibular.
		@param Vestibular Vestibular	  */
	public void setVestibular (boolean Vestibular)
	{
		set_Value (COLUMNNAME_Vestibular, Boolean.valueOf(Vestibular));
	}

	/** Get Vestibular.
		@return Vestibular	  */
	public boolean isVestibular () 
	{
		Object oo = get_Value(COLUMNNAME_Vestibular);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}