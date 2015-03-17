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

/** Generated Model for EXME_Ensenanza_5
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Ensenanza_5 extends PO implements I_EXME_Ensenanza_5, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Ensenanza_5 (Properties ctx, int EXME_Ensenanza_5_ID, String trxName)
    {
      super (ctx, EXME_Ensenanza_5_ID, trxName);
      /** if (EXME_Ensenanza_5_ID == 0)
        {
			setCosto (false);
			setDescription (null);
			setEXME_Ensenanza_5_ID (0);
			setFecha_Curso (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setFecha_Registro (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Ensenanza_5 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Ensenanza_5[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cost.
		@param Costo 
		Cost
	  */
	public void setCosto (boolean Costo)
	{
		set_Value (COLUMNNAME_Costo, Boolean.valueOf(Costo));
	}

	/** Get Cost.
		@return Cost
	  */
	public boolean isCosto () 
	{
		Object oo = get_Value(COLUMNNAME_Costo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Cost of the Course.
		@param Costo_Curso 
		Cost of the Course
	  */
	public void setCosto_Curso (BigDecimal Costo_Curso)
	{
		set_Value (COLUMNNAME_Costo_Curso, Costo_Curso);
	}

	/** Get Cost of the Course.
		@return Cost of the Course
	  */
	public BigDecimal getCosto_Curso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Costo_Curso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Teaching Courses 5.
		@param EXME_Ensenanza_5_ID 
		Teaching Courses 5
	  */
	public void setEXME_Ensenanza_5_ID (int EXME_Ensenanza_5_ID)
	{
		if (EXME_Ensenanza_5_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ensenanza_5_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Ensenanza_5_ID, Integer.valueOf(EXME_Ensenanza_5_ID));
	}

	/** Get Teaching Courses 5.
		@return Teaching Courses 5
	  */
	public int getEXME_Ensenanza_5_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ensenanza_5_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Course Date.
		@param Fecha_Curso 
		Course Date
	  */
	public void setFecha_Curso (Timestamp Fecha_Curso)
	{
		if (Fecha_Curso == null)
			throw new IllegalArgumentException ("Fecha_Curso is mandatory.");
		set_Value (COLUMNNAME_Fecha_Curso, Fecha_Curso);
	}

	/** Get Course Date.
		@return Course Date
	  */
	public Timestamp getFecha_Curso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Curso);
	}

	/** Set Date of Record.
		@param Fecha_Registro 
		Date of Record
	  */
	public void setFecha_Registro (Timestamp Fecha_Registro)
	{
		if (Fecha_Registro == null)
			throw new IllegalArgumentException ("Fecha_Registro is mandatory.");
		set_Value (COLUMNNAME_Fecha_Registro, Fecha_Registro);
	}

	/** Get Date of Record.
		@return Date of Record
	  */
	public Timestamp getFecha_Registro () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Registro);
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

	/** Set Number of Students.
		@param Num_Alumnos 
		Number of Students
	  */
	public void setNum_Alumnos (int Num_Alumnos)
	{
		set_Value (COLUMNNAME_Num_Alumnos, Integer.valueOf(Num_Alumnos));
	}

	/** Get Number of Students.
		@return Number of Students
	  */
	public int getNum_Alumnos () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Num_Alumnos);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of Hours.
		@param Num_Horas 
		Number of Hours
	  */
	public void setNum_Horas (String Num_Horas)
	{
		set_Value (COLUMNNAME_Num_Horas, Num_Horas);
	}

	/** Get Number of Hours.
		@return Number of Hours
	  */
	public String getNum_Horas () 
	{
		return (String)get_Value(COLUMNNAME_Num_Horas);
	}
}