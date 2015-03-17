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

/** Generated Model for EXME_Plazos_Clas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Plazos_Clas extends PO implements I_EXME_Plazos_Clas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Plazos_Clas (Properties ctx, int EXME_Plazos_Clas_ID, String trxName)
    {
      super (ctx, EXME_Plazos_Clas_ID, trxName);
      /** if (EXME_Plazos_Clas_ID == 0)
        {
			setEXME_Plazos_Clas_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Plazos_Clas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Plazos_Clas[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Final Debt.
		@param Adeudo_Fin 
		Final Debt
	  */
	public void setAdeudo_Fin (BigDecimal Adeudo_Fin)
	{
		set_Value (COLUMNNAME_Adeudo_Fin, Adeudo_Fin);
	}

	/** Get Final Debt.
		@return Final Debt
	  */
	public BigDecimal getAdeudo_Fin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Adeudo_Fin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Initial Debt.
		@param Adeudo_Ini 
		Initial Debt
	  */
	public void setAdeudo_Ini (BigDecimal Adeudo_Ini)
	{
		set_Value (COLUMNNAME_Adeudo_Ini, Adeudo_Ini);
	}

	/** Get Initial Debt.
		@return Initial Debt
	  */
	public BigDecimal getAdeudo_Ini () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Adeudo_Ini);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Days.
		@param Dias Days	  */
	public void setDias (int Dias)
	{
		set_Value (COLUMNNAME_Dias, Integer.valueOf(Dias));
	}

	/** Get Days.
		@return Days	  */
	public int getDias () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dias);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasCliente.Table_Name);
        I_EXME_ClasCliente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasCliente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasCliente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classification and Customer.
		@param EXME_ClasCliente_ID Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID)
	{
		if (EXME_ClasCliente_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClasCliente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClasCliente_ID, Integer.valueOf(EXME_ClasCliente_ID));
	}

	/** Get Classification and Customer.
		@return Classification and Customer	  */
	public int getEXME_ClasCliente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasCliente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Classified Deadlines.
		@param EXME_Plazos_Clas_ID 
		Classified Deadlines
	  */
	public void setEXME_Plazos_Clas_ID (int EXME_Plazos_Clas_ID)
	{
		if (EXME_Plazos_Clas_ID < 1)
			 throw new IllegalArgumentException ("EXME_Plazos_Clas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Plazos_Clas_ID, Integer.valueOf(EXME_Plazos_Clas_ID));
	}

	/** Get Classified Deadlines.
		@return Classified Deadlines
	  */
	public int getEXME_Plazos_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Plazos_Clas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Payments.
		@param Pagos 
		Payments
	  */
	public void setPagos (int Pagos)
	{
		set_Value (COLUMNNAME_Pagos, Integer.valueOf(Pagos));
	}

	/** Get Payments.
		@return Payments
	  */
	public int getPagos () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Pagos);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}