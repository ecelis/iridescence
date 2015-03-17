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

/** Generated Model for PHR_EjercicioPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_EjercicioPac extends PO implements I_PHR_EjercicioPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_EjercicioPac (Properties ctx, int PHR_EjercicioPac_ID, String trxName)
    {
      super (ctx, PHR_EjercicioPac_ID, trxName);
      /** if (PHR_EjercicioPac_ID == 0)
        {
			setDuracion (0);
			setEXME_Paciente_ID (0);
			setFechaIni (new Timestamp( System.currentTimeMillis() ));
			setNombre (null);
			setPHR_EjercicioPac_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_EjercicioPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_EjercicioPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Comment.
		@param Comentario Comment	  */
	public void setComentario (String Comentario)
	{
		set_Value (COLUMNNAME_Comentario, Comentario);
	}

	/** Get Comment.
		@return Comment	  */
	public String getComentario () 
	{
		return (String)get_Value(COLUMNNAME_Comentario);
	}

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (int Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Integer.valueOf(Duracion));
	}

	/** Get Duration.
		@return Duration
	  */
	public int getDuracion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duracion);
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
		if (FechaIni == null)
			throw new IllegalArgumentException ("FechaIni is mandatory.");
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** FrecEjerc AD_Reference_ID=1200444 */
	public static final int FRECEJERC_AD_Reference_ID=1200444;
	/** Day = D */
	public static final String FRECEJERC_Day = "D";
	/** Week = W */
	public static final String FRECEJERC_Week = "W";
	/** Month = M */
	public static final String FRECEJERC_Month = "M";
	/** Set Exercise Frequency.
		@param FrecEjerc 
		Exercise Frequency
	  */
	public void setFrecEjerc (String FrecEjerc)
	{

		if (FrecEjerc == null || FrecEjerc.equals("D") || FrecEjerc.equals("W") || FrecEjerc.equals("M")); else throw new IllegalArgumentException ("FrecEjerc Invalid value - " + FrecEjerc + " - Reference_ID=1200444 - D - W - M");		set_Value (COLUMNNAME_FrecEjerc, FrecEjerc);
	}

	/** Get Exercise Frequency.
		@return Exercise Frequency
	  */
	public String getFrecEjerc () 
	{
		return (String)get_Value(COLUMNNAME_FrecEjerc);
	}

	/** Set Itervalo.
		@param Itervalo 
		Itervalo
	  */
	public void setItervalo (int Itervalo)
	{
		set_Value (COLUMNNAME_Itervalo, Integer.valueOf(Itervalo));
	}

	/** Get Itervalo.
		@return Itervalo
	  */
	public int getItervalo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Itervalo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		if (Nombre == null)
			throw new IllegalArgumentException ("Nombre is mandatory.");
		set_Value (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_Value(COLUMNNAME_Nombre);
	}

	/** Set Patient Exercise.
		@param PHR_EjercicioPac_ID 
		Patient Exercise
	  */
	public void setPHR_EjercicioPac_ID (int PHR_EjercicioPac_ID)
	{
		if (PHR_EjercicioPac_ID < 1)
			 throw new IllegalArgumentException ("PHR_EjercicioPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_EjercicioPac_ID, Integer.valueOf(PHR_EjercicioPac_ID));
	}

	/** Get Patient Exercise.
		@return Patient Exercise
	  */
	public int getPHR_EjercicioPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_EjercicioPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}