/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Metricas_Fumador
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Metricas_Fumador extends PO implements I_EXME_Metricas_Fumador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Metricas_Fumador (Properties ctx, int EXME_Metricas_Fumador_ID, String trxName)
    {
      super (ctx, EXME_Metricas_Fumador_ID, trxName);
      /** if (EXME_Metricas_Fumador_ID == 0)
        {
			setEXME_EstiloVidaPaciente_ID (0);
			setEXME_Metricas_Fumador_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Metricas_Fumador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Metricas_Fumador[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_EstiloVidaPaciente getEXME_EstiloVidaPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstiloVidaPaciente.Table_Name);
        I_EXME_EstiloVidaPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstiloVidaPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstiloVidaPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Life Style.
		@param EXME_EstiloVidaPaciente_ID Patient's Life Style	  */
	public void setEXME_EstiloVidaPaciente_ID (int EXME_EstiloVidaPaciente_ID)
	{
		if (EXME_EstiloVidaPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstiloVidaPaciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstiloVidaPaciente_ID, Integer.valueOf(EXME_EstiloVidaPaciente_ID));
	}

	/** Get Patient's Life Style.
		@return Patient's Life Style	  */
	public int getEXME_EstiloVidaPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstiloVidaPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Smoker metrics.
		@param EXME_Metricas_Fumador_ID 
		Smoker metrics
	  */
	public void setEXME_Metricas_Fumador_ID (int EXME_Metricas_Fumador_ID)
	{
		if (EXME_Metricas_Fumador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Metricas_Fumador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Metricas_Fumador_ID, Integer.valueOf(EXME_Metricas_Fumador_ID));
	}

	/** Get Smoker metrics.
		@return Smoker metrics
	  */
	public int getEXME_Metricas_Fumador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Metricas_Fumador_ID);
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
}