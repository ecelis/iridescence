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

/** Generated Model for PHR_PacMedicamento
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacMedicamento extends PO implements I_PHR_PacMedicamento, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacMedicamento (Properties ctx, int PHR_PacMedicamento_ID, String trxName)
    {
      super (ctx, PHR_PacMedicamento_ID, trxName);
      /** if (PHR_PacMedicamento_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setName (null);
			setPeriodoIndefinido (false);
			setPHR_PacMedicamento_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacMedicamento (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacMedicamento[")
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

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ViasAdministracion.Table_Name);
        I_EXME_ViasAdministracion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ViasAdministracion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ViasAdministracion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
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
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
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
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
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

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set Period.
		@param Periodo 
		Period
	  */
	public void setPeriodo (BigDecimal Periodo)
	{
		set_Value (COLUMNNAME_Periodo, Periodo);
	}

	/** Get Period.
		@return Period
	  */
	public BigDecimal getPeriodo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Periodo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Indeterminate Period.
		@param PeriodoIndefinido Indeterminate Period	  */
	public void setPeriodoIndefinido (boolean PeriodoIndefinido)
	{
		set_Value (COLUMNNAME_PeriodoIndefinido, Boolean.valueOf(PeriodoIndefinido));
	}

	/** Get Indeterminate Period.
		@return Indeterminate Period	  */
	public boolean isPeriodoIndefinido () 
	{
		Object oo = get_Value(COLUMNNAME_PeriodoIndefinido);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_PHR_Evento getPHR_Evento() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_Evento.Table_Name);
        I_PHR_Evento result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_Evento)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_Evento_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Event.
		@param PHR_Evento_ID Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID)
	{
		if (PHR_Evento_ID < 1) 
			set_Value (COLUMNNAME_PHR_Evento_ID, null);
		else 
			set_Value (COLUMNNAME_PHR_Evento_ID, Integer.valueOf(PHR_Evento_ID));
	}

	/** Get Patient Event.
		@return Patient Event	  */
	public int getPHR_Evento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Evento_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Medication.
		@param PHR_PacMedicamento_ID Patient Medication	  */
	public void setPHR_PacMedicamento_ID (int PHR_PacMedicamento_ID)
	{
		if (PHR_PacMedicamento_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacMedicamento_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacMedicamento_ID, Integer.valueOf(PHR_PacMedicamento_ID));
	}

	/** Get Patient Medication.
		@return Patient Medication	  */
	public int getPHR_PacMedicamento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacMedicamento_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** UOMIntervalo AD_Reference_ID=1200387 */
	public static final int UOMINTERVALO_AD_Reference_ID=1200387;
	/** Minutes = MI */
	public static final String UOMINTERVALO_Minutes = "MI";
	/** Hours = HR */
	public static final String UOMINTERVALO_Hours = "HR";
	/** Days = DI */
	public static final String UOMINTERVALO_Days = "DI";
	/** Set Interval UOM.
		@param UOMIntervalo 
		Interval UOM
	  */
	public void setUOMIntervalo (String UOMIntervalo)
	{

		if (UOMIntervalo == null || UOMIntervalo.equals("MI") || UOMIntervalo.equals("HR") || UOMIntervalo.equals("DI")); else throw new IllegalArgumentException ("UOMIntervalo Invalid value - " + UOMIntervalo + " - Reference_ID=1200387 - MI - HR - DI");		set_Value (COLUMNNAME_UOMIntervalo, UOMIntervalo);
	}

	/** Get Interval UOM.
		@return Interval UOM
	  */
	public String getUOMIntervalo () 
	{
		return (String)get_Value(COLUMNNAME_UOMIntervalo);
	}

	/** UOMPeriodo AD_Reference_ID=1200386 */
	public static final int UOMPERIODO_AD_Reference_ID=1200386;
	/** Hours = HR */
	public static final String UOMPERIODO_Hours = "HR";
	/** Days = DI */
	public static final String UOMPERIODO_Days = "DI";
	/** Weeks = SE */
	public static final String UOMPERIODO_Weeks = "SE";
	/** Set Period UOM.
		@param UOMPeriodo Period UOM	  */
	public void setUOMPeriodo (String UOMPeriodo)
	{

		if (UOMPeriodo == null || UOMPeriodo.equals("HR") || UOMPeriodo.equals("DI") || UOMPeriodo.equals("SE")); else throw new IllegalArgumentException ("UOMPeriodo Invalid value - " + UOMPeriodo + " - Reference_ID=1200386 - HR - DI - SE");		set_Value (COLUMNNAME_UOMPeriodo, UOMPeriodo);
	}

	/** Get Period UOM.
		@return Period UOM	  */
	public String getUOMPeriodo () 
	{
		return (String)get_Value(COLUMNNAME_UOMPeriodo);
	}

	/** Set Route of Administration.
		@param Via 
		Route of Administration
	  */
	public void setVia (String Via)
	{
		set_Value (COLUMNNAME_Via, Via);
	}

	/** Get Route of Administration.
		@return Route of Administration
	  */
	public String getVia () 
	{
		return (String)get_Value(COLUMNNAME_Via);
	}
}