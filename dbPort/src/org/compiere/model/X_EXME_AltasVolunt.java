/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_AltasVolunt
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AltasVolunt extends PO implements I_EXME_AltasVolunt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AltasVolunt (Properties ctx, int EXME_AltasVolunt_ID, String trxName)
    {
      super (ctx, EXME_AltasVolunt_ID, trxName);
      /** if (EXME_AltasVolunt_ID == 0)
        {
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setEXME_AltasVolunt_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AltasVolunt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AltasVolunt[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Discharge Bed.
		@param CamaAlta Discharge Bed	  */
	public void setCamaAlta (String CamaAlta)
	{
		set_Value (COLUMNNAME_CamaAlta, CamaAlta);
	}

	/** Get Discharge Bed.
		@return Discharge Bed	  */
	public String getCamaAlta () 
	{
		return (String)get_Value(COLUMNNAME_CamaAlta);
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

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Voluntary Discharges.
		@param EXME_AltasVolunt_ID 
		Voluntary Discharges from Hospital
	  */
	public void setEXME_AltasVolunt_ID (int EXME_AltasVolunt_ID)
	{
		if (EXME_AltasVolunt_ID < 1)
			 throw new IllegalArgumentException ("EXME_AltasVolunt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AltasVolunt_ID, Integer.valueOf(EXME_AltasVolunt_ID));
	}

	/** Get Voluntary Discharges.
		@return Voluntary Discharges from Hospital
	  */
	public int getEXME_AltasVolunt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AltasVolunt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
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

	/** Set Medical Manager.
		@param MedicoResp 
		Medical Manager
	  */
	public void setMedicoResp (String MedicoResp)
	{
		set_Value (COLUMNNAME_MedicoResp, MedicoResp);
	}

	/** Get Medical Manager.
		@return Medical Manager
	  */
	public String getMedicoResp () 
	{
		return (String)get_Value(COLUMNNAME_MedicoResp);
	}

	/** Set Motive.
		@param Motivo 
		Motive / Reason
	  */
	public void setMotivo (String Motivo)
	{
		set_Value (COLUMNNAME_Motivo, Motivo);
	}

	/** Get Motive.
		@return Motive / Reason
	  */
	public String getMotivo () 
	{
		return (String)get_Value(COLUMNNAME_Motivo);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}