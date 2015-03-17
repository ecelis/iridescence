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

/** Generated Model for EXME_INPCDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_INPCDet extends PO implements I_EXME_INPCDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_INPCDet (Properties ctx, int EXME_INPCDet_ID, String trxName)
    {
      super (ctx, EXME_INPCDet_ID, trxName);
      /** if (EXME_INPCDet_ID == 0)
        {
			setC_Period_ID (0);
			setEXME_INPCDet_ID (0);
			setEXME_INPC_ID (0);
			setSecuencia (0);
			setTasa (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_INPCDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_INPCDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Period getC_Period() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Period.Table_Name);
        I_C_Period result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Period)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Period_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1)
			 throw new IllegalArgumentException ("C_Period_ID is mandatory.");
		set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Detail INPC.
		@param EXME_INPCDet_ID Detail INPC	  */
	public void setEXME_INPCDet_ID (int EXME_INPCDet_ID)
	{
		if (EXME_INPCDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_INPCDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_INPCDet_ID, Integer.valueOf(EXME_INPCDet_ID));
	}

	/** Get Detail INPC.
		@return Detail INPC	  */
	public int getEXME_INPCDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_INPCDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_INPC getEXME_INPC() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_INPC.Table_Name);
        I_EXME_INPC result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_INPC)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_INPC_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set INPC.
		@param EXME_INPC_ID INPC	  */
	public void setEXME_INPC_ID (int EXME_INPC_ID)
	{
		if (EXME_INPC_ID < 1)
			 throw new IllegalArgumentException ("EXME_INPC_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_INPC_ID, Integer.valueOf(EXME_INPC_ID));
	}

	/** Get INPC.
		@return INPC	  */
	public int getEXME_INPC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_INPC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (int Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Integer.valueOf(Secuencia));
	}

	/** Get Sequence.
		@return Sequence
	  */
	public int getSecuencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secuencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rate.
		@param Tasa Rate	  */
	public void setTasa (BigDecimal Tasa)
	{
		if (Tasa == null)
			throw new IllegalArgumentException ("Tasa is mandatory.");
		set_Value (COLUMNNAME_Tasa, Tasa);
	}

	/** Get Rate.
		@return Rate	  */
	public BigDecimal getTasa () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Tasa);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}