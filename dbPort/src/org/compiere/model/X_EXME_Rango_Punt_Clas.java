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

/** Generated Model for EXME_Rango_Punt_Clas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Rango_Punt_Clas extends PO implements I_EXME_Rango_Punt_Clas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Rango_Punt_Clas (Properties ctx, int EXME_Rango_Punt_Clas_ID, String trxName)
    {
      super (ctx, EXME_Rango_Punt_Clas_ID, trxName);
      /** if (EXME_Rango_Punt_Clas_ID == 0)
        {
			setEXME_Rango_Punt_Clas_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Rango_Punt_Clas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Rango_Punt_Clas[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Score Rank.
		@param EXME_Rango_Punt_Clas_ID 
		Rank of Classification and Score
	  */
	public void setEXME_Rango_Punt_Clas_ID (int EXME_Rango_Punt_Clas_ID)
	{
		if (EXME_Rango_Punt_Clas_ID < 1)
			 throw new IllegalArgumentException ("EXME_Rango_Punt_Clas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Rango_Punt_Clas_ID, Integer.valueOf(EXME_Rango_Punt_Clas_ID));
	}

	/** Get Score Rank.
		@return Rank of Classification and Score
	  */
	public int getEXME_Rango_Punt_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Rango_Punt_Clas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Score.
		@param Puntaje_Final 
		Final Score
	  */
	public void setPuntaje_Final (BigDecimal Puntaje_Final)
	{
		set_Value (COLUMNNAME_Puntaje_Final, Puntaje_Final);
	}

	/** Get Final Score.
		@return Final Score
	  */
	public BigDecimal getPuntaje_Final () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Puntaje_Final);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Original Score.
		@param Puntaje_Inicial 
		Original Score
	  */
	public void setPuntaje_Inicial (BigDecimal Puntaje_Inicial)
	{
		set_Value (COLUMNNAME_Puntaje_Inicial, Puntaje_Inicial);
	}

	/** Get Original Score.
		@return Original Score
	  */
	public BigDecimal getPuntaje_Inicial () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Puntaje_Inicial);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}