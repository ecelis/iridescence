/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Gestoria
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Gestoria extends PO implements I_EXME_Gestoria, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Gestoria (Properties ctx, int EXME_Gestoria_ID, String trxName)
    {
      super (ctx, EXME_Gestoria_ID, trxName);
      /** if (EXME_Gestoria_ID == 0)
        {
			setEXME_Gestoria_ID (0);
			setEXME_Institucion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Gestoria (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Gestoria[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Special Study.
		@param Estudio_Esp 
		Special Study
	  */
	public void setEstudio_Esp (String Estudio_Esp)
	{
		set_Value (COLUMNNAME_Estudio_Esp, Estudio_Esp);
	}

	/** Get Special Study.
		@return Special Study
	  */
	public String getEstudio_Esp () 
	{
		return (String)get_Value(COLUMNNAME_Estudio_Esp);
	}

	/** Set Agency.
		@param EXME_Gestoria_ID 
		Agency
	  */
	public void setEXME_Gestoria_ID (int EXME_Gestoria_ID)
	{
		if (EXME_Gestoria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Gestoria_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Gestoria_ID, Integer.valueOf(EXME_Gestoria_ID));
	}

	/** Get Agency.
		@return Agency
	  */
	public int getEXME_Gestoria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Gestoria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical File.
		@param EXME_Hist_Exp_ID 
		Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID)
	{
		if (EXME_Hist_Exp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, Integer.valueOf(EXME_Hist_Exp_ID));
	}

	/** Get Medical File.
		@return Medical File
	  */
	public int getEXME_Hist_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Exp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Institution.
		@param EXME_Institucion_ID 
		Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Institucion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Institution.
		@return Institution
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
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

	/** Set Appointment Date.
		@param Fecha_Cita 
		Appointment Date
	  */
	public void setFecha_Cita (Timestamp Fecha_Cita)
	{
		set_Value (COLUMNNAME_Fecha_Cita, Fecha_Cita);
	}

	/** Get Appointment Date.
		@return Appointment Date
	  */
	public Timestamp getFecha_Cita () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Cita);
	}

	/** Set Delivery Date (social work).
		@param Fecha_Entr 
		Delivery Date (social work)
	  */
	public void setFecha_Entr (Timestamp Fecha_Entr)
	{
		set_Value (COLUMNNAME_Fecha_Entr, Fecha_Entr);
	}

	/** Get Delivery Date (social work).
		@return Delivery Date (social work)
	  */
	public Timestamp getFecha_Entr () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Entr);
	}

	/** Set Office Date.
		@param Fecha_Oficio 
		Office Date
	  */
	public void setFecha_Oficio (Timestamp Fecha_Oficio)
	{
		set_Value (COLUMNNAME_Fecha_Oficio, Fecha_Oficio);
	}

	/** Get Office Date.
		@return Office Date
	  */
	public Timestamp getFecha_Oficio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Oficio);
	}

	/** Set Inter. Consulta.
		@param InterCons 
		Inter. Consulta
	  */
	public void setInterCons (String InterCons)
	{
		set_Value (COLUMNNAME_InterCons, InterCons);
	}

	/** Get Inter. Consulta.
		@return Inter. Consulta
	  */
	public String getInterCons () 
	{
		return (String)get_Value(COLUMNNAME_InterCons);
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_Value (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
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

	/** Set Transfer.
		@param Traslado 
		Transfer
	  */
	public void setTraslado (String Traslado)
	{
		set_Value (COLUMNNAME_Traslado, Traslado);
	}

	/** Get Transfer.
		@return Transfer
	  */
	public String getTraslado () 
	{
		return (String)get_Value(COLUMNNAME_Traslado);
	}

	/** TSCama AD_Reference_ID=1000049 */
	public static final int TSCAMA_AD_Reference_ID=1000049;
	/** Set Social Work Bed.
		@param TSCama Social Work Bed	  */
	public void setTSCama (String TSCama)
	{
		set_Value (COLUMNNAME_TSCama, TSCama);
	}

	/** Get Social Work Bed.
		@return Social Work Bed	  */
	public String getTSCama () 
	{
		return (String)get_Value(COLUMNNAME_TSCama);
	}

	/** Set SW Institution.
		@param TSInstitucion SW Institution	  */
	public void setTSInstitucion (String TSInstitucion)
	{
		set_Value (COLUMNNAME_TSInstitucion, TSInstitucion);
	}

	/** Get SW Institution.
		@return SW Institution	  */
	public String getTSInstitucion () 
	{
		return (String)get_Value(COLUMNNAME_TSInstitucion);
	}
}