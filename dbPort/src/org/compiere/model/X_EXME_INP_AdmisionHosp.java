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

/** Generated Model for EXME_INP_AdmisionHosp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_INP_AdmisionHosp extends PO implements I_EXME_INP_AdmisionHosp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_INP_AdmisionHosp (Properties ctx, int EXME_INP_AdmisionHosp_ID, String trxName)
    {
      super (ctx, EXME_INP_AdmisionHosp_ID, trxName);
      /** if (EXME_INP_AdmisionHosp_ID == 0)
        {
			setApellido1 (null);
			setApellido2 (null);
			setEXME_ClasCliente_ID (0);
			setEXME_Hist_Exp_ID (0);
			setEXME_INP_AdmisionHosp_ID (0);
			setFecha_Apertura (new Timestamp( System.currentTimeMillis() ));
			setFechaNac (new Timestamp( System.currentTimeMillis() ));
			setNombre (null);
			setSexo (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_INP_AdmisionHosp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_INP_AdmisionHosp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Last Name.
		@param Apellido1 
		Last Name
	  */
	public void setApellido1 (String Apellido1)
	{
		if (Apellido1 == null)
			throw new IllegalArgumentException ("Apellido1 is mandatory.");
		set_Value (COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
		@return Last Name
	  */
	public String getApellido1 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido1);
	}

	/** Set Mother's Maiden Name.
		@param Apellido2 
		Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2)
	{
		if (Apellido2 == null)
			throw new IllegalArgumentException ("Apellido2 is mandatory.");
		set_Value (COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
		@return Mother's Maiden Name
	  */
	public String getApellido2 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2);
	}

	/** Set Condi.
		@param Condi Condi	  */
	public void setCondi (String Condi)
	{
		set_Value (COLUMNNAME_Condi, Condi);
	}

	/** Get Condi.
		@return Condi	  */
	public String getCondi () 
	{
		return (String)get_Value(COLUMNNAME_Condi);
	}

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasCliente.Table_Name);
        I_EXME_ClasCliente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasCliente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasCliente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classification and Customer.
		@param EXME_ClasCliente_ID Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID)
	{
		if (EXME_ClasCliente_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClasCliente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ClasCliente_ID, Integer.valueOf(EXME_ClasCliente_ID));
	}

	/** Get Classification and Customer.
		@return Classification and Customer	  */
	public int getEXME_ClasCliente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasCliente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hist_Exp.Table_Name);
        I_EXME_Hist_Exp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hist_Exp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hist_Exp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set EXME_INP_AdmisionHosp_ID.
		@param EXME_INP_AdmisionHosp_ID EXME_INP_AdmisionHosp_ID	  */
	public void setEXME_INP_AdmisionHosp_ID (int EXME_INP_AdmisionHosp_ID)
	{
		if (EXME_INP_AdmisionHosp_ID < 1)
			 throw new IllegalArgumentException ("EXME_INP_AdmisionHosp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_INP_AdmisionHosp_ID, Integer.valueOf(EXME_INP_AdmisionHosp_ID));
	}

	/** Get EXME_INP_AdmisionHosp_ID.
		@return EXME_INP_AdmisionHosp_ID	  */
	public int getEXME_INP_AdmisionHosp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_INP_AdmisionHosp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fecha_Apertura.
		@param Fecha_Apertura Fecha_Apertura	  */
	public void setFecha_Apertura (Timestamp Fecha_Apertura)
	{
		if (Fecha_Apertura == null)
			throw new IllegalArgumentException ("Fecha_Apertura is mandatory.");
		set_Value (COLUMNNAME_Fecha_Apertura, Fecha_Apertura);
	}

	/** Get Fecha_Apertura.
		@return Fecha_Apertura	  */
	public Timestamp getFecha_Apertura () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Apertura);
	}

	/** Set Fecha_Defuncion.
		@param Fecha_Defuncion Fecha_Defuncion	  */
	public void setFecha_Defuncion (Timestamp Fecha_Defuncion)
	{
		set_Value (COLUMNNAME_Fecha_Defuncion, Fecha_Defuncion);
	}

	/** Get Fecha_Defuncion.
		@return Fecha_Defuncion	  */
	public Timestamp getFecha_Defuncion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Defuncion);
	}

	/** Set Fecha_GuiaVerde.
		@param Fecha_GuiaVerde Fecha_GuiaVerde	  */
	public void setFecha_GuiaVerde (Timestamp Fecha_GuiaVerde)
	{
		set_Value (COLUMNNAME_Fecha_GuiaVerde, Fecha_GuiaVerde);
	}

	/** Get Fecha_GuiaVerde.
		@return Fecha_GuiaVerde	  */
	public Timestamp getFecha_GuiaVerde () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_GuiaVerde);
	}

	/** Set Fecha_Microfilmacion.
		@param Fecha_Microfilmacion Fecha_Microfilmacion	  */
	public void setFecha_Microfilmacion (Timestamp Fecha_Microfilmacion)
	{
		set_Value (COLUMNNAME_Fecha_Microfilmacion, Fecha_Microfilmacion);
	}

	/** Get Fecha_Microfilmacion.
		@return Fecha_Microfilmacion	  */
	public Timestamp getFecha_Microfilmacion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Microfilmacion);
	}

	/** Set Birth Date.
		@param FechaNac 
		Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac)
	{
		if (FechaNac == null)
			throw new IllegalArgumentException ("FechaNac is mandatory.");
		set_Value (COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
		@return Birth Date
	  */
	public Timestamp getFechaNac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaNac);
	}

	/** Set Microrrollo.
		@param Microrrollo Microrrollo	  */
	public void setMicrorrollo (String Microrrollo)
	{
		set_Value (COLUMNNAME_Microrrollo, Microrrollo);
	}

	/** Get Microrrollo.
		@return Microrrollo	  */
	public String getMicrorrollo () 
	{
		return (String)get_Value(COLUMNNAME_Microrrollo);
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		if (Nombre == null)
			throw new IllegalArgumentException ("Nombre is mandatory.");
		set_Value (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_Value(COLUMNNAME_Nombre);
	}

	/** Sexo AD_Reference_ID=1000018 */
	public static final int SEXO_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String SEXO_Female = "F";
	/** Male = M */
	public static final String SEXO_Male = "M";
	/** Unassigned = U */
	public static final String SEXO_Unassigned = "U";
	/** Ambiguous = A */
	public static final String SEXO_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String SEXO_NotApplicable = "N";
	/** Other = O */
	public static final String SEXO_Other = "O";
	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (String Sexo)
	{
		if (Sexo == null) throw new IllegalArgumentException ("Sexo is mandatory");
		if (Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
	}
}