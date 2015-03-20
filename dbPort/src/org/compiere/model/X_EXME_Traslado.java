/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Traslado
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Traslado extends PO implements I_EXME_Traslado, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Traslado (Properties ctx, int EXME_Traslado_ID, String trxName)
    {
      super (ctx, EXME_Traslado_ID, trxName);
      /** if (EXME_Traslado_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_Traslado_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setUser_T_S (0);
// @#AD_User_ID@
        } */
    }

    /** Load Constructor */
    public X_EXME_Traslado (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Traslado[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Consecutive.
		@param Consecutivo 
		Consecutive
	  */
	public void setConsecutivo (int Consecutivo)
	{
		set_ValueNoCheck (COLUMNNAME_Consecutivo, Integer.valueOf(Consecutivo));
	}

	/** Get Consecutive.
		@return Consecutive
	  */
	public int getConsecutivo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Consecutivo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnostic.
		@param Diagnostico 
		Diagnostic
	  */
	public void setDiagnostico (String Diagnostico)
	{
		set_Value (COLUMNNAME_Diagnostico, Diagnostico);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getDiagnostico () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico);
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

	/** Set Transfer.
		@param EXME_Traslado_ID 
		Transfer
	  */
	public void setEXME_Traslado_ID (int EXME_Traslado_ID)
	{
		if (EXME_Traslado_ID < 1)
			 throw new IllegalArgumentException ("EXME_Traslado_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Traslado_ID, Integer.valueOf(EXME_Traslado_ID));
	}

	/** Get Transfer.
		@return Transfer
	  */
	public int getEXME_Traslado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Traslado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
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

	/** Set Transfer Place.
		@param Lugar_Tras 
		Transfer Place
	  */
	public void setLugar_Tras (String Lugar_Tras)
	{
		set_Value (COLUMNNAME_Lugar_Tras, Lugar_Tras);
	}

	/** Get Transfer Place.
		@return Transfer Place
	  */
	public String getLugar_Tras () 
	{
		return (String)get_Value(COLUMNNAME_Lugar_Tras);
	}

	/** Set Medical Manager.
		@param Medico_Resp 
		Medical Manager
	  */
	public void setMedico_Resp (String Medico_Resp)
	{
		set_Value (COLUMNNAME_Medico_Resp, Medico_Resp);
	}

	/** Get Medical Manager.
		@return Medical Manager
	  */
	public String getMedico_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Medico_Resp);
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

	/** Set User T.S..
		@param User_T_S 
		User T.S.
	  */
	public void setUser_T_S (int User_T_S)
	{
		set_ValueNoCheck (COLUMNNAME_User_T_S, Integer.valueOf(User_T_S));
	}

	/** Get User T.S..
		@return User T.S.
	  */
	public int getUser_T_S () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User_T_S);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}