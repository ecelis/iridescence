/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Donador
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Donador extends PO implements I_EXME_Donador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Donador (Properties ctx, int EXME_Donador_ID, String trxName)
    {
      super (ctx, EXME_Donador_ID, trxName);
      /** if (EXME_Donador_ID == 0)
        {
			setApellido1 (null);
			setApellido2 (null);
			setCaso (null);
			setCurp (null);
			setEdad (Env.ZERO);
			setEXME_CausaMuerte_ID (0);
			setEXME_Donador_ID (0);
			setEXME_Organos_Tejidos_ID (0);
			setFecha_Extraccion (new Timestamp( System.currentTimeMillis() ));
			setFechaMuerte (new Timestamp( System.currentTimeMillis() ));
			setFecha_Trasplante (new Timestamp( System.currentTimeMillis() ));
			setIsHospital (false);
			setName (null);
			setSexo (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Donador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Donador[")
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

	/** Set Caso.
		@param Caso 
		Donor Initials
	  */
	public void setCaso (String Caso)
	{
		if (Caso == null)
			throw new IllegalArgumentException ("Caso is mandatory.");
		set_Value (COLUMNNAME_Caso, Caso);
	}

	/** Get Caso.
		@return Donor Initials
	  */
	public String getCaso () 
	{
		return (String)get_Value(COLUMNNAME_Caso);
	}

	/** Set National Identification Number.
		@param Curp 
		National Identification Number
	  */
	public void setCurp (String Curp)
	{
		if (Curp == null)
			throw new IllegalArgumentException ("Curp is mandatory.");
		set_Value (COLUMNNAME_Curp, Curp);
	}

	/** Get National Identification Number.
		@return National Identification Number
	  */
	public String getCurp () 
	{
		return (String)get_Value(COLUMNNAME_Curp);
	}

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (BigDecimal Edad)
	{
		if (Edad == null)
			throw new IllegalArgumentException ("Edad is mandatory.");
		set_Value (COLUMNNAME_Edad, Edad);
	}

	/** Get Age.
		@return Age
	  */
	public BigDecimal getEdad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Edad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_CausaMuerte getEXME_CausaMuerte() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CausaMuerte.Table_Name);
        I_EXME_CausaMuerte result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CausaMuerte)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CausaMuerte_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Death Cause.
		@param EXME_CausaMuerte_ID 
		Death Cause
	  */
	public void setEXME_CausaMuerte_ID (int EXME_CausaMuerte_ID)
	{
		if (EXME_CausaMuerte_ID < 1)
			 throw new IllegalArgumentException ("EXME_CausaMuerte_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CausaMuerte_ID, Integer.valueOf(EXME_CausaMuerte_ID));
	}

	/** Get Death Cause.
		@return Death Cause
	  */
	public int getEXME_CausaMuerte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CausaMuerte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Donor.
		@param EXME_Donador_ID Donor	  */
	public void setEXME_Donador_ID (int EXME_Donador_ID)
	{
		if (EXME_Donador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Donador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Donador_ID, Integer.valueOf(EXME_Donador_ID));
	}

	/** Get Donor.
		@return Donor	  */
	public int getEXME_Donador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Donador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_EXME_Organos_Tejidos getEXME_Organos_Tejidos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Organos_Tejidos.Table_Name);
        I_EXME_Organos_Tejidos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Organos_Tejidos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Organos_Tejidos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Date Extraction.
		@param Fecha_Extraccion 
		Date of organ harvesting
	  */
	public void setFecha_Extraccion (Timestamp Fecha_Extraccion)
	{
		if (Fecha_Extraccion == null)
			throw new IllegalArgumentException ("Fecha_Extraccion is mandatory.");
		set_Value (COLUMNNAME_Fecha_Extraccion, Fecha_Extraccion);
	}

	/** Get Date Extraction.
		@return Date of organ harvesting
	  */
	public Timestamp getFecha_Extraccion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Extraccion);
	}

	/** Set Death's Date.
		@param FechaMuerte 
		Death's Date
	  */
	public void setFechaMuerte (Timestamp FechaMuerte)
	{
		if (FechaMuerte == null)
			throw new IllegalArgumentException ("FechaMuerte is mandatory.");
		set_Value (COLUMNNAME_FechaMuerte, FechaMuerte);
	}

	/** Get Death's Date.
		@return Death's Date
	  */
	public Timestamp getFechaMuerte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaMuerte);
	}

	/** Set Transplant Date.
		@param Fecha_Trasplante 
		Transplant Date
	  */
	public void setFecha_Trasplante (Timestamp Fecha_Trasplante)
	{
		if (Fecha_Trasplante == null)
			throw new IllegalArgumentException ("Fecha_Trasplante is mandatory.");
		set_Value (COLUMNNAME_Fecha_Trasplante, Fecha_Trasplante);
	}

	/** Get Transplant Date.
		@return Transplant Date
	  */
	public Timestamp getFecha_Trasplante () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Trasplante);
	}

	/** Set Source Hospital.
		@param Hospital_Origen 
		Source Hospital
	  */
	public void setHospital_Origen (String Hospital_Origen)
	{
		set_Value (COLUMNNAME_Hospital_Origen, Hospital_Origen);
	}

	/** Get Source Hospital.
		@return Source Hospital
	  */
	public String getHospital_Origen () 
	{
		return (String)get_Value(COLUMNNAME_Hospital_Origen);
	}

	/** Set IsHospital.
		@param IsHospital 
		Determines whether the physician is the hospital
	  */
	public void setIsHospital (boolean IsHospital)
	{
		set_Value (COLUMNNAME_IsHospital, Boolean.valueOf(IsHospital));
	}

	/** Get IsHospital.
		@return Determines whether the physician is the hospital
	  */
	public boolean isHospital () 
	{
		Object oo = get_Value(COLUMNNAME_IsHospital);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set External Medical.
		@param Medico_Externo 
		External Medical
	  */
	public void setMedico_Externo (String Medico_Externo)
	{
		set_Value (COLUMNNAME_Medico_Externo, Medico_Externo);
	}

	/** Get External Medical.
		@return External Medical
	  */
	public String getMedico_Externo () 
	{
		return (String)get_Value(COLUMNNAME_Medico_Externo);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Middle Name.
		@param Nombre2 
		Middle name
	  */
	public void setNombre2 (String Nombre2)
	{
		set_Value (COLUMNNAME_Nombre2, Nombre2);
	}

	/** Get Middle Name.
		@return Middle name
	  */
	public String getNombre2 () 
	{
		return (String)get_Value(COLUMNNAME_Nombre2);
	}

	/** Set Provanance.
		@param Procedencia 
		Sets from which the active 
	  */
	public void setProcedencia (String Procedencia)
	{
		set_Value (COLUMNNAME_Procedencia, Procedencia);
	}

	/** Get Provanance.
		@return Sets from which the active 
	  */
	public String getProcedencia () 
	{
		return (String)get_Value(COLUMNNAME_Procedencia);
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

	/** Set Valuation.
		@param Valoracion Valuation	  */
	public void setValoracion (String Valoracion)
	{
		set_Value (COLUMNNAME_Valoracion, Valoracion);
	}

	/** Get Valuation.
		@return Valuation	  */
	public String getValoracion () 
	{
		return (String)get_Value(COLUMNNAME_Valoracion);
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