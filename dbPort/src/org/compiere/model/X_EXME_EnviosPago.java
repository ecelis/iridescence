/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_EnviosPago
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnviosPago extends PO implements I_EXME_EnviosPago, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnviosPago (Properties ctx, int EXME_EnviosPago_ID, String trxName)
    {
      super (ctx, EXME_EnviosPago_ID, trxName);
      /** if (EXME_EnviosPago_ID == 0)
        {
			setEXME_EnviosPago_ID (0);
			setEXME_Hist_Exp_ID (0);
			setEXME_Paciente_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_EnviosPago (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnviosPago[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Payment Concept.
		@param ConcPago 
		Payment Concept
	  */
	public void setConcPago (String ConcPago)
	{
		set_Value (COLUMNNAME_ConcPago, ConcPago);
	}

	/** Get Payment Concept.
		@return Payment Concept
	  */
	public String getConcPago () 
	{
		return (String)get_Value(COLUMNNAME_ConcPago);
	}

	/** Set Debit.
		@param Debe 
		Debit
	  */
	public void setDebe (BigDecimal Debe)
	{
		set_Value (COLUMNNAME_Debe, Debe);
	}

	/** Get Debit.
		@return Debit
	  */
	public BigDecimal getDebe () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Debe);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Shipping Payment.
		@param EXME_EnviosPago_ID 
		Shipping Payment
	  */
	public void setEXME_EnviosPago_ID (int EXME_EnviosPago_ID)
	{
		if (EXME_EnviosPago_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnviosPago_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnviosPago_ID, Integer.valueOf(EXME_EnviosPago_ID));
	}

	/** Get Shipping Payment.
		@return Shipping Payment
	  */
	public int getEXME_EnviosPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnviosPago_ID);
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
			 throw new IllegalArgumentException ("EXME_Hist_Exp_ID is mandatory.");
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

	/** Set Pay.
		@param Paga 
		Pay
	  */
	public void setPaga (BigDecimal Paga)
	{
		set_Value (COLUMNNAME_Paga, Paga);
	}

	/** Get Pay.
		@return Pay
	  */
	public BigDecimal getPaga () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Paga);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid.
		@param Pagado Paid	  */
	public void setPagado (boolean Pagado)
	{
		set_Value (COLUMNNAME_Pagado, Boolean.valueOf(Pagado));
	}

	/** Get Paid.
		@return Paid	  */
	public boolean isPagado () 
	{
		Object oo = get_Value(COLUMNNAME_Pagado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Social Worker.
		@param TS 
		Social Worker
	  */
	public void setTS (String TS)
	{
		set_Value (COLUMNNAME_TS, TS);
	}

	/** Get Social Worker.
		@return Social Worker
	  */
	public String getTS () 
	{
		return (String)get_Value(COLUMNNAME_TS);
	}

	/** Set Social Work Clasification.
		@param TSClasificacion Social Work Clasification	  */
	public void setTSClasificacion (String TSClasificacion)
	{
		set_Value (COLUMNNAME_TSClasificacion, TSClasificacion);
	}

	/** Get Social Work Clasification.
		@return Social Work Clasification	  */
	public String getTSClasificacion () 
	{
		return (String)get_Value(COLUMNNAME_TSClasificacion);
	}
}