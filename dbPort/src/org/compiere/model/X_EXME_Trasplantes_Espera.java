/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Trasplantes_Espera
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Trasplantes_Espera extends PO implements I_EXME_Trasplantes_Espera, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Trasplantes_Espera (Properties ctx, int EXME_Trasplantes_Espera_ID, String trxName)
    {
      super (ctx, EXME_Trasplantes_Espera_ID, trxName);
      /** if (EXME_Trasplantes_Espera_ID == 0)
        {
			setEstatus (null);
			setEXME_Organos_Tejidos_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Prioridad_ID (0);
			setEXME_Trasplantes_Espera_ID (0);
			setPeso (null);
			setTalla (null);
			setTipoSangre (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Trasplantes_Espera (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Trasplantes_Espera[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Joined.
		@param Antiguedad_Registro 
		Joined
	  */
	public void setAntiguedad_Registro (String Antiguedad_Registro)
	{
		throw new IllegalArgumentException ("Antiguedad_Registro is virtual column");	}

	/** Get Joined.
		@return Joined
	  */
	public String getAntiguedad_Registro () 
	{
		return (String)get_Value(COLUMNNAME_Antiguedad_Registro);
	}

	/** Set Criteria.
		@param Criterios 
		Criteria
	  */
	public void setCriterios (String Criterios)
	{
		set_Value (COLUMNNAME_Criterios, Criterios);
	}

	/** Get Criteria.
		@return Criteria
	  */
	public String getCriterios () 
	{
		return (String)get_Value(COLUMNNAME_Criterios);
	}

	/** Estatus AD_Reference_ID=1200395 */
	public static final int ESTATUS_AD_Reference_ID=1200395;
	/** Active = ta */
	public static final String ESTATUS_Active = "ta";
	/** Inactive = ti */
	public static final String ESTATUS_Inactive = "ti";
	/** Transplanted = tt */
	public static final String ESTATUS_Transplanted = "tt";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("ta") || Estatus.equals("ti") || Estatus.equals("tt")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200395 - ta - ti - tt");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Organos_Tejidos getEXME_Organos_Tejidos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Organos_Tejidos.Table_Name);
        I_EXME_Organos_Tejidos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Organos_Tejidos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Organos_Tejidos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Organs/Tissues .
		@param EXME_Organos_Tejidos_ID 
		ID de table organs and tissues
	  */
	public void setEXME_Organos_Tejidos_ID (int EXME_Organos_Tejidos_ID)
	{
		if (EXME_Organos_Tejidos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Organos_Tejidos_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Organos_Tejidos_ID, Integer.valueOf(EXME_Organos_Tejidos_ID));
	}

	/** Get Organs/Tissues .
		@return ID de table organs and tissues
	  */
	public int getEXME_Organos_Tejidos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Organos_Tejidos_ID);
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

	public I_EXME_Prioridad getEXME_Prioridad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Prioridad.Table_Name);
        I_EXME_Prioridad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Prioridad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Prioridad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Priority.
		@param EXME_Prioridad_ID 
		Indicates if this request is of a high, medium or low priority
	  */
	public void setEXME_Prioridad_ID (int EXME_Prioridad_ID)
	{
		if (EXME_Prioridad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Prioridad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Prioridad_ID, Integer.valueOf(EXME_Prioridad_ID));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority
	  */
	public int getEXME_Prioridad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Prioridad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transplant Waiting.
		@param EXME_Trasplantes_Espera_ID 
		Transplant Waiting
	  */
	public void setEXME_Trasplantes_Espera_ID (int EXME_Trasplantes_Espera_ID)
	{
		if (EXME_Trasplantes_Espera_ID < 1)
			 throw new IllegalArgumentException ("EXME_Trasplantes_Espera_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Trasplantes_Espera_ID, Integer.valueOf(EXME_Trasplantes_Espera_ID));
	}

	/** Get Transplant Waiting.
		@return Transplant Waiting
	  */
	public int getEXME_Trasplantes_Espera_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Trasplantes_Espera_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fecha_Estatus.
		@param Fecha_Estatus Fecha_Estatus	  */
	public void setFecha_Estatus (Timestamp Fecha_Estatus)
	{
		set_ValueNoCheck (COLUMNNAME_Fecha_Estatus, Fecha_Estatus);
	}

	/** Get Fecha_Estatus.
		@return Fecha_Estatus	  */
	public Timestamp getFecha_Estatus () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Estatus);
	}

	/** Set Date of Record.
		@param Fecha_Registro 
		Date of Record
	  */
	public void setFecha_Registro (Timestamp Fecha_Registro)
	{
		set_Value (COLUMNNAME_Fecha_Registro, Fecha_Registro);
	}

	/** Get Date of Record.
		@return Date of Record
	  */
	public Timestamp getFecha_Registro () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Registro);
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

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (String Peso)
	{
		if (Peso == null)
			throw new IllegalArgumentException ("Peso is mandatory.");
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public String getPeso () 
	{
		return (String)get_Value(COLUMNNAME_Peso);
	}

	/** Set Position / Rating.
		@param Posicion_Calificacion 
		Position / Rating
	  */
	public void setPosicion_Calificacion (String Posicion_Calificacion)
	{
		set_Value (COLUMNNAME_Posicion_Calificacion, Posicion_Calificacion);
	}

	/** Get Position / Rating.
		@return Position / Rating
	  */
	public String getPosicion_Calificacion () 
	{
		return (String)get_Value(COLUMNNAME_Posicion_Calificacion);
	}

	/** Set Height.
		@param Talla 
		Height
	  */
	public void setTalla (String Talla)
	{
		if (Talla == null)
			throw new IllegalArgumentException ("Talla is mandatory.");
		set_Value (COLUMNNAME_Talla, Talla);
	}

	/** Get Height.
		@return Height
	  */
	public String getTalla () 
	{
		return (String)get_Value(COLUMNNAME_Talla);
	}

	/** Set Bloody Type.
		@param TipoSangre Bloody Type	  */
	public void setTipoSangre (String TipoSangre)
	{
		if (TipoSangre == null)
			throw new IllegalArgumentException ("TipoSangre is mandatory.");
		set_Value (COLUMNNAME_TipoSangre, TipoSangre);
	}

	/** Get Bloody Type.
		@return Bloody Type	  */
	public String getTipoSangre () 
	{
		return (String)get_Value(COLUMNNAME_TipoSangre);
	}

	/** Set Valuation.
		@param Valoracion Valuation	  */
	public void setValoracion (String Valoracion)
	{
		set_Value (COLUMNNAME_Valoracion, Valoracion);
	}

	/** Get Valuation.
		@return Valuation	  */
	public String getValoracion () 
	{
		return (String)get_Value(COLUMNNAME_Valoracion);
	}
}