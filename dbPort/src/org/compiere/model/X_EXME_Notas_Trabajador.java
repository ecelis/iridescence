/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Notas_Trabajador
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Notas_Trabajador extends PO implements I_EXME_Notas_Trabajador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Notas_Trabajador (Properties ctx, int EXME_Notas_Trabajador_ID, String trxName)
    {
      super (ctx, EXME_Notas_Trabajador_ID, trxName);
      /** if (EXME_Notas_Trabajador_ID == 0)
        {
			setEXME_Notas_Trabajador_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Notas_Trabajador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Notas_Trabajador[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Area.
		@param EXME_Area_ID 
		Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1) 
			set_Value (COLUMNNAME_EXME_Area_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Area.
		@return Area
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
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

	/** Set Classification and Customer.
		@param EXME_ClasCliente_ID Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID)
	{
		throw new IllegalArgumentException ("EXME_ClasCliente_ID is virtual column");	}

	/** Get Classification and Customer.
		@return Classification and Customer	  */
	public int getEXME_ClasCliente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasCliente_ID);
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

	/** Set Notes of the Social Worker.
		@param EXME_Notas_Trabajador_ID 
		Notes of the Social Worker
	  */
	public void setEXME_Notas_Trabajador_ID (int EXME_Notas_Trabajador_ID)
	{
		if (EXME_Notas_Trabajador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Notas_Trabajador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Notas_Trabajador_ID, Integer.valueOf(EXME_Notas_Trabajador_ID));
	}

	/** Get Notes of the Social Worker.
		@return Notes of the Social Worker
	  */
	public int getEXME_Notas_Trabajador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Notas_Trabajador_ID);
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
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set Note Date.
		@param Fecha_Nota 
		Note Date
	  */
	public void setFecha_Nota (Timestamp Fecha_Nota)
	{
		set_ValueNoCheck (COLUMNNAME_Fecha_Nota, Fecha_Nota);
	}

	/** Get Note Date.
		@return Note Date
	  */
	public Timestamp getFecha_Nota () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Nota);
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

	/** Set Note.
		@param Nota 
		Note
	  */
	public void setNota (String Nota)
	{
		set_Value (COLUMNNAME_Nota, Nota);
	}

	/** Get Note.
		@return Note
	  */
	public String getNota () 
	{
		return (String)get_Value(COLUMNNAME_Nota);
	}

	/** Set Note2.
		@param Nota2 Note2	  */
	public void setNota2 (String Nota2)
	{
		set_Value (COLUMNNAME_Nota2, Nota2);
	}

	/** Get Note2.
		@return Note2	  */
	public String getNota2 () 
	{
		return (String)get_Value(COLUMNNAME_Nota2);
	}

	/** Set Nota3.
		@param Nota3 Nota3	  */
	public void setNota3 (String Nota3)
	{
		set_Value (COLUMNNAME_Nota3, Nota3);
	}

	/** Get Nota3.
		@return Nota3	  */
	public String getNota3 () 
	{
		return (String)get_Value(COLUMNNAME_Nota3);
	}

	/** TSSexo AD_Reference_ID=1000018 */
	public static final int TSSEXO_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String TSSEXO_Female = "F";
	/** Male = M */
	public static final String TSSEXO_Male = "M";
	/** Unassigned = U */
	public static final String TSSEXO_Unassigned = "U";
	/** Ambiguous = A */
	public static final String TSSEXO_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String TSSEXO_NotApplicable = "N";
	/** Other = O */
	public static final String TSSEXO_Other = "O";
	/** Set Sex.
		@param TSSexo Sex	  */
	public void setTSSexo (String TSSexo)
	{

		if (TSSexo == null || TSSexo.equals("F") || TSSexo.equals("M") || TSSexo.equals("U") || TSSexo.equals("A") || TSSexo.equals("N") || TSSexo.equals("O")); else throw new IllegalArgumentException ("TSSexo Invalid value - " + TSSexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_TSSexo, TSSexo);
	}

	/** Get Sex.
		@return Sex	  */
	public String getTSSexo () 
	{
		return (String)get_Value(COLUMNNAME_TSSexo);
	}
}