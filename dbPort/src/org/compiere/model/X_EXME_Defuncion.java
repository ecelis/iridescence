/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Defuncion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Defuncion extends PO implements I_EXME_Defuncion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Defuncion (Properties ctx, int EXME_Defuncion_ID, String trxName)
    {
      super (ctx, EXME_Defuncion_ID, trxName);
      /** if (EXME_Defuncion_ID == 0)
        {
			setAvisoFamiliar (new Timestamp( System.currentTimeMillis() ));
			setAvisoTrabSoc (new Timestamp( System.currentTimeMillis() ));
			setEXME_Defuncion_ID (0);
			setEXME_Paciente_ID (0);
			setFechaHr (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Defuncion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Defuncion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Relatives Notice.
		@param AvisoFamiliar 
		Notice to patient relatives
	  */
	public void setAvisoFamiliar (Timestamp AvisoFamiliar)
	{
		if (AvisoFamiliar == null)
			throw new IllegalArgumentException ("AvisoFamiliar is mandatory.");
		set_Value (COLUMNNAME_AvisoFamiliar, AvisoFamiliar);
	}

	/** Get Relatives Notice.
		@return Notice to patient relatives
	  */
	public Timestamp getAvisoFamiliar () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AvisoFamiliar);
	}

	/** Set Social Work Notice.
		@param AvisoTrabSoc 
		Notice to Social work
	  */
	public void setAvisoTrabSoc (Timestamp AvisoTrabSoc)
	{
		if (AvisoTrabSoc == null)
			throw new IllegalArgumentException ("AvisoTrabSoc is mandatory.");
		set_Value (COLUMNNAME_AvisoTrabSoc, AvisoTrabSoc);
	}

	/** Get Social Work Notice.
		@return Notice to Social work
	  */
	public Timestamp getAvisoTrabSoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AvisoTrabSoc);
	}

	/** Set Address.
		@param Direccion Address	  */
	public void setDireccion (String Direccion)
	{
		set_Value (COLUMNNAME_Direccion, Direccion);
	}

	/** Get Address.
		@return Address	  */
	public String getDireccion () 
	{
		return (String)get_Value(COLUMNNAME_Direccion);
	}

	/** Set Deaths.
		@param EXME_Defuncion_ID 
		Deaths
	  */
	public void setEXME_Defuncion_ID (int EXME_Defuncion_ID)
	{
		if (EXME_Defuncion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Defuncion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Defuncion_ID, Integer.valueOf(EXME_Defuncion_ID));
	}

	/** Get Deaths.
		@return Deaths
	  */
	public int getEXME_Defuncion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Defuncion_ID);
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

	/** Set Hr and Date.
		@param FechaHr 
		Hr and Date
	  */
	public void setFechaHr (Timestamp FechaHr)
	{
		if (FechaHr == null)
			throw new IllegalArgumentException ("FechaHr is mandatory.");
		set_Value (COLUMNNAME_FechaHr, FechaHr);
	}

	/** Get Hr and Date.
		@return Hr and Date
	  */
	public Timestamp getFechaHr () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHr);
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

	/** Set Social Work Clinical.
		@param TSClinico 
		Social Work Clinical
	  */
	public void setTSClinico (String TSClinico)
	{
		set_Value (COLUMNNAME_TSClinico, TSClinico);
	}

	/** Get Social Work Clinical.
		@return Social Work Clinical
	  */
	public String getTSClinico () 
	{
		return (String)get_Value(COLUMNNAME_TSClinico);
	}
}