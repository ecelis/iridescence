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

/** Generated Model for EXME_HistConsulta
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_HistConsulta extends PO implements I_EXME_HistConsulta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_HistConsulta (Properties ctx, int EXME_HistConsulta_ID, String trxName)
    {
      super (ctx, EXME_HistConsulta_ID, trxName);
      /** if (EXME_HistConsulta_ID == 0)
        {
			setEXME_CitaMedica_ID (0);
			setEXME_HistConsulta_ID (0);
			setFechaCita (new Timestamp( System.currentTimeMillis() ));
			setNombreMedico (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_HistConsulta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_HistConsulta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cerrada.
		@param Cerrada Cerrada	  */
	public void setCerrada (boolean Cerrada)
	{
		set_Value (COLUMNNAME_Cerrada, Boolean.valueOf(Cerrada));
	}

	/** Get Cerrada.
		@return Cerrada	  */
	public boolean isCerrada () 
	{
		Object oo = get_Value(COLUMNNAME_Cerrada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description.
		@param Descripcion Description	  */
	public void setDescripcion (String Descripcion)
	{
		set_Value (COLUMNNAME_Descripcion, Descripcion);
	}

	/** Get Description.
		@return Description	  */
	public String getDescripcion () 
	{
		return (String)get_Value(COLUMNNAME_Descripcion);
	}

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1)
			 throw new IllegalArgumentException ("EXME_CitaMedica_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_HistConsulta_ID.
		@param EXME_HistConsulta_ID EXME_HistConsulta_ID	  */
	public void setEXME_HistConsulta_ID (int EXME_HistConsulta_ID)
	{
		if (EXME_HistConsulta_ID < 1)
			 throw new IllegalArgumentException ("EXME_HistConsulta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_HistConsulta_ID, Integer.valueOf(EXME_HistConsulta_ID));
	}

	/** Get EXME_HistConsulta_ID.
		@return EXME_HistConsulta_ID	  */
	public int getEXME_HistConsulta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HistConsulta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FechaCita.
		@param FechaCita FechaCita	  */
	public void setFechaCita (Timestamp FechaCita)
	{
		if (FechaCita == null)
			throw new IllegalArgumentException ("FechaCita is mandatory.");
		set_Value (COLUMNNAME_FechaCita, FechaCita);
	}

	/** Get FechaCita.
		@return FechaCita	  */
	public Timestamp getFechaCita () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCita);
	}

	/** Set NombreMedico.
		@param NombreMedico NombreMedico	  */
	public void setNombreMedico (String NombreMedico)
	{
		if (NombreMedico == null)
			throw new IllegalArgumentException ("NombreMedico is mandatory.");
		set_Value (COLUMNNAME_NombreMedico, NombreMedico);
	}

	/** Get NombreMedico.
		@return NombreMedico	  */
	public String getNombreMedico () 
	{
		return (String)get_Value(COLUMNNAME_NombreMedico);
	}
}