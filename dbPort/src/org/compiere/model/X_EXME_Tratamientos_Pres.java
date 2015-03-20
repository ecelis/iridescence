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

/** Generated Model for EXME_Tratamientos_Pres
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Tratamientos_Pres extends PO implements I_EXME_Tratamientos_Pres, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos_Pres (Properties ctx, int EXME_Tratamientos_Pres_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_Pres_ID, trxName);
      /** if (EXME_Tratamientos_Pres_ID == 0)
        {
			setEXME_Tratamientos_Detalle_ID (0);
			setEXME_Tratamientos_Pres_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos_Pres (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos_Pres[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Dose.
		@param Dosis Dose	  */
	public void setDosis (String Dosis)
	{
		set_Value (COLUMNNAME_Dosis, Dosis);
	}

	/** Get Dose.
		@return Dose	  */
	public String getDosis () 
	{
		return (String)get_Value(COLUMNNAME_Dosis);
	}

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (BigDecimal Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Duracion);
	}

	/** Get Duration.
		@return Duration
	  */
	public BigDecimal getDuracion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Duracion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Detalle.Table_Name);
        I_EXME_Tratamientos_Detalle result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Detalle)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Detalle_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments Detail.
		@param EXME_Tratamientos_Detalle_ID Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID)
	{
		if (EXME_Tratamientos_Detalle_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Detalle_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Tratamientos_Detalle_ID, Integer.valueOf(EXME_Tratamientos_Detalle_ID));
	}

	/** Get Treatments Detail.
		@return Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Detalle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Prescription treatment.
		@param EXME_Tratamientos_Pres_ID Prescription treatment	  */
	public void setEXME_Tratamientos_Pres_ID (int EXME_Tratamientos_Pres_ID)
	{
		if (EXME_Tratamientos_Pres_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Pres_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_Pres_ID, Integer.valueOf(EXME_Tratamientos_Pres_ID));
	}

	/** Get Prescription treatment.
		@return Prescription treatment	  */
	public int getEXME_Tratamientos_Pres_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Pres_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (BigDecimal Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Intervalo);
	}

	/** Get Interval.
		@return Interval
	  */
	public BigDecimal getIntervalo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Intervalo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Observation.
		@param Observacion 
		Observation
	  */
	public void setObservacion (String Observacion)
	{
		set_Value (COLUMNNAME_Observacion, Observacion);
	}

	/** Get Observation.
		@return Observation
	  */
	public String getObservacion () 
	{
		return (String)get_Value(COLUMNNAME_Observacion);
	}

	/** Set Session Number.
		@param SessionNo 
		Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo)
	{
		set_Value (COLUMNNAME_SessionNo, Integer.valueOf(SessionNo));
	}

	/** Get Session Number.
		@return Session Number of a treatment
	  */
	public int getSessionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SessionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Duration UOM.
		@param UOMDuracion Duration UOM	  */
	public void setUOMDuracion (String UOMDuracion)
	{
		set_Value (COLUMNNAME_UOMDuracion, UOMDuracion);
	}

	/** Get Duration UOM.
		@return Duration UOM	  */
	public String getUOMDuracion () 
	{
		return (String)get_Value(COLUMNNAME_UOMDuracion);
	}

	/** Set Interval UOM.
		@param UOMIntervalo 
		Interval UOM
	  */
	public void setUOMIntervalo (String UOMIntervalo)
	{
		set_Value (COLUMNNAME_UOMIntervalo, UOMIntervalo);
	}

	/** Get Interval UOM.
		@return Interval UOM
	  */
	public String getUOMIntervalo () 
	{
		return (String)get_Value(COLUMNNAME_UOMIntervalo);
	}
}