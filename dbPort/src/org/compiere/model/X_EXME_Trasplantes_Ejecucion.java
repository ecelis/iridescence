/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Trasplantes_Ejecucion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Trasplantes_Ejecucion extends PO implements I_EXME_Trasplantes_Ejecucion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Trasplantes_Ejecucion (Properties ctx, int EXME_Trasplantes_Ejecucion_ID, String trxName)
    {
      super (ctx, EXME_Trasplantes_Ejecucion_ID, trxName);
      /** if (EXME_Trasplantes_Ejecucion_ID == 0)
        {
			setEXME_MedicoCirujano_ID (0);
			setEXME_MedicoTratante_ID (0);
			setEXME_Organos_Tejidos_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Trasplantes_Ejecucion_ID (0);
			setFechaRegListaEspera (new Timestamp( System.currentTimeMillis() ));
			setFechaTrasplante (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Trasplantes_Ejecucion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Trasplantes_Ejecucion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Notes.
		@param Comentarios Notes	  */
	public void setComentarios (String Comentarios)
	{
		set_Value (COLUMNNAME_Comentarios, Comentarios);
	}

	/** Get Notes.
		@return Notes	  */
	public String getComentarios () 
	{
		return (String)get_Value(COLUMNNAME_Comentarios);
	}

	/** Set Successful.
		@param Exitoso 
		Successful
	  */
	public void setExitoso (boolean Exitoso)
	{
		set_Value (COLUMNNAME_Exitoso, Boolean.valueOf(Exitoso));
	}

	/** Get Successful.
		@return Successful
	  */
	public boolean isExitoso () 
	{
		Object oo = get_Value(COLUMNNAME_Exitoso);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Medical Surgeon.
		@param EXME_MedicoCirujano_ID 
		Medical in charge of Surgery
	  */
	public void setEXME_MedicoCirujano_ID (int EXME_MedicoCirujano_ID)
	{
		if (EXME_MedicoCirujano_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedicoCirujano_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MedicoCirujano_ID, Integer.valueOf(EXME_MedicoCirujano_ID));
	}

	/** Get Medical Surgeon.
		@return Medical in charge of Surgery
	  */
	public int getEXME_MedicoCirujano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoCirujano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Handler.
		@param EXME_MedicoTratante_ID 
		Medical Patient processor
	  */
	public void setEXME_MedicoTratante_ID (int EXME_MedicoTratante_ID)
	{
		if (EXME_MedicoTratante_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedicoTratante_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MedicoTratante_ID, Integer.valueOf(EXME_MedicoTratante_ID));
	}

	/** Get Medical Handler.
		@return Medical Patient processor
	  */
	public int getEXME_MedicoTratante_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoTratante_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Transplantation.
		@param EXME_Trasplantes_Ejecucion_ID 
		Implementation of Transplantation
	  */
	public void setEXME_Trasplantes_Ejecucion_ID (int EXME_Trasplantes_Ejecucion_ID)
	{
		if (EXME_Trasplantes_Ejecucion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Trasplantes_Ejecucion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Trasplantes_Ejecucion_ID, Integer.valueOf(EXME_Trasplantes_Ejecucion_ID));
	}

	/** Get Transplantation.
		@return Implementation of Transplantation
	  */
	public int getEXME_Trasplantes_Ejecucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Trasplantes_Ejecucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Waiting List Registration.
		@param FechaRegListaEspera 
		Registration Date Waiting List
	  */
	public void setFechaRegListaEspera (Timestamp FechaRegListaEspera)
	{
		if (FechaRegListaEspera == null)
			throw new IllegalArgumentException ("FechaRegListaEspera is mandatory.");
		set_Value (COLUMNNAME_FechaRegListaEspera, FechaRegListaEspera);
	}

	/** Get Waiting List Registration.
		@return Registration Date Waiting List
	  */
	public Timestamp getFechaRegListaEspera () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRegListaEspera);
	}

	/** Set Transplant Date.
		@param FechaTrasplante 
		Transplant Date
	  */
	public void setFechaTrasplante (Timestamp FechaTrasplante)
	{
		if (FechaTrasplante == null)
			throw new IllegalArgumentException ("FechaTrasplante is mandatory.");
		set_Value (COLUMNNAME_FechaTrasplante, FechaTrasplante);
	}

	/** Get Transplant Date.
		@return Transplant Date
	  */
	public Timestamp getFechaTrasplante () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaTrasplante);
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