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

/** Generated Model for PHR_PacSignoVitalDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacSignoVitalDet extends PO implements I_PHR_PacSignoVitalDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacSignoVitalDet (Properties ctx, int PHR_PacSignoVitalDet_ID, String trxName)
    {
      super (ctx, PHR_PacSignoVitalDet_ID, trxName);
      /** if (PHR_PacSignoVitalDet_ID == 0)
        {
			setEXME_SignoVital_ID (0);
			setPHR_PacSignoVitalDet_ID (0);
			setPHR_PacSignoVital_ID (0);
			setValor (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacSignoVitalDet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacSignoVitalDet[")
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

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SignoVital.Table_Name);
        I_EXME_SignoVital result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SignoVital)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SignoVital_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vital Sign.
		@param EXME_SignoVital_ID Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID)
	{
		if (EXME_SignoVital_ID < 1)
			 throw new IllegalArgumentException ("EXME_SignoVital_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SignoVital_ID, Integer.valueOf(EXME_SignoVital_ID));
	}

	/** Get Vital Sign.
		@return Vital Sign	  */
	public int getEXME_SignoVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SignoVital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Detail of patient's vital signs.
		@param PHR_PacSignoVitalDet_ID Detail of patient's vital signs	  */
	public void setPHR_PacSignoVitalDet_ID (int PHR_PacSignoVitalDet_ID)
	{
		if (PHR_PacSignoVitalDet_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacSignoVitalDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacSignoVitalDet_ID, Integer.valueOf(PHR_PacSignoVitalDet_ID));
	}

	/** Get Detail of patient's vital signs.
		@return Detail of patient's vital signs	  */
	public int getPHR_PacSignoVitalDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacSignoVitalDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PHR_PacSignoVital getPHR_PacSignoVital() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_PacSignoVital.Table_Name);
        I_PHR_PacSignoVital result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_PacSignoVital)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_PacSignoVital_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Vital Signs.
		@param PHR_PacSignoVital_ID Patient's Vital Signs	  */
	public void setPHR_PacSignoVital_ID (int PHR_PacSignoVital_ID)
	{
		if (PHR_PacSignoVital_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacSignoVital_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_PacSignoVital_ID, Integer.valueOf(PHR_PacSignoVital_ID));
	}

	/** Get Patient's Vital Signs.
		@return Patient's Vital Signs	  */
	public int getPHR_PacSignoVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacSignoVital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value.
		@param Valor Value	  */
	public void setValor (BigDecimal Valor)
	{
		if (Valor == null)
			throw new IllegalArgumentException ("Valor is mandatory.");
		set_Value (COLUMNNAME_Valor, Valor);
	}

	/** Get Value.
		@return Value	  */
	public BigDecimal getValor () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Valor);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}