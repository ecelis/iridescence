/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_MedicoEsp
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_MedicoEsp extends PO implements I_EXME_MedicoEsp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MedicoEsp (Properties ctx, int EXME_MedicoEsp_ID, String trxName)
    {
      super (ctx, EXME_MedicoEsp_ID, trxName);
      /** if (EXME_MedicoEsp_ID == 0)
        {
			setEXME_Especialidad_ID (0);
			setEXME_MedicoEsp_ID (0);
			setEXME_Medico_ID (0);
			setHaceGuardia (false);
// N
			setIsDefault (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_MedicoEsp (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_MedicoEsp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty Doctor.
		@param EXME_MedicoEsp_ID 
		Specialty Doctor
	  */
	public void setEXME_MedicoEsp_ID (int EXME_MedicoEsp_ID)
	{
		if (EXME_MedicoEsp_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedicoEsp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MedicoEsp_ID, Integer.valueOf(EXME_MedicoEsp_ID));
	}

	/** Get Specialty Doctor.
		@return Specialty Doctor
	  */
	public int getEXME_MedicoEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoEsp_ID);
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
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
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

	/** Set Substitute Doctor.
		@param EXME_Medico_Sust_ID 
		Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID)
	{
		if (EXME_Medico_Sust_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_Sust_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_Sust_ID, Integer.valueOf(EXME_Medico_Sust_ID));
	}

	/** Get Substitute Doctor.
		@return Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Sust_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Guards.
		@param HaceGuardia Guards	  */
	public void setHaceGuardia (boolean HaceGuardia)
	{
		set_Value (COLUMNNAME_HaceGuardia, Boolean.valueOf(HaceGuardia));
	}

	/** Get Guards.
		@return Guards	  */
	public boolean isHaceGuardia () 
	{
		Object oo = get_Value(COLUMNNAME_HaceGuardia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Consult Interval.
		@param IntervaloConsulta Consult Interval	  */
	public void setIntervaloConsulta (BigDecimal IntervaloConsulta)
	{
		set_Value (COLUMNNAME_IntervaloConsulta, IntervaloConsulta);
	}

	/** Get Consult Interval.
		@return Consult Interval	  */
	public BigDecimal getIntervaloConsulta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IntervaloConsulta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Waiting Time.
		@param TiempoEspera 
		Waiting Time (in months)
	  */
	public void setTiempoEspera (BigDecimal TiempoEspera)
	{
		set_Value (COLUMNNAME_TiempoEspera, TiempoEspera);
	}

	/** Get Waiting Time.
		@return Waiting Time (in months)
	  */
	public BigDecimal getTiempoEspera () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TiempoEspera);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}