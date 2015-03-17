/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Paciente_Expediente_V
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Paciente_Expediente_V extends PO implements I_EXME_Paciente_Expediente_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Paciente_Expediente_V (Properties ctx, int EXME_Paciente_Expediente_V_ID, String trxName)
    {
      super (ctx, EXME_Paciente_Expediente_V_ID, trxName);
      /** if (EXME_Paciente_Expediente_V_ID == 0)
        {
			setApellido1 (null);
			setEXME_Paciente_Expediente_V_ID (0);
			setFechaNac (new Timestamp( System.currentTimeMillis() ));
			setHistoria (null);
			setMRN (null);
			setNombre (null);
			setSexo (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Paciente_Expediente_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Paciente_Expediente_V[")
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

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set MRN_Patient_ID.
		@param EXME_Paciente_Expediente_V_ID MRN_Patient_ID	  */
	public void setEXME_Paciente_Expediente_V_ID (int EXME_Paciente_Expediente_V_ID)
	{
		if (EXME_Paciente_Expediente_V_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_Expediente_V_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_Expediente_V_ID, Integer.valueOf(EXME_Paciente_Expediente_V_ID));
	}

	/** Get MRN_Patient_ID.
		@return MRN_Patient_ID	  */
	public int getEXME_Paciente_Expediente_V_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_Expediente_V_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Unique Patient Identification.
		@param Historia 
		Unique Patient Identification
	  */
	public void setHistoria (String Historia)
	{
		if (Historia == null)
			throw new IllegalArgumentException ("Historia is mandatory.");
		set_Value (COLUMNNAME_Historia, Historia);
	}

	/** Get Unique Patient Identification.
		@return Unique Patient Identification
	  */
	public String getHistoria () 
	{
		return (String)get_Value(COLUMNNAME_Historia);
	}

	/** Set Medical Record Number.
		@param MRN 
		Medical Record Number
	  */
	public void setMRN (String MRN)
	{
		if (MRN == null)
			throw new IllegalArgumentException ("MRN is mandatory.");
		set_Value (COLUMNNAME_MRN, MRN);
	}

	/** Get Medical Record Number.
		@return Medical Record Number
	  */
	public String getMRN () 
	{
		return (String)get_Value(COLUMNNAME_MRN);
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

	/** Set Social Security Number.
		@param NSS Social Security Number	  */
	public void setNSS (String NSS)
	{
		set_Value (COLUMNNAME_NSS, NSS);
	}

	/** Get Social Security Number.
		@return Social Security Number	  */
	public String getNSS () 
	{
		return (String)get_Value(COLUMNNAME_NSS);
	}

	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (boolean Sexo)
	{
		set_Value (COLUMNNAME_Sexo, Boolean.valueOf(Sexo));
	}

	/** Get Sex.
		@return Sex
	  */
	public boolean isSexo () 
	{
		Object oo = get_Value(COLUMNNAME_Sexo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SSN.
		@param SuffixNSS SSN	  */
	public void setSuffixNSS (String SuffixNSS)
	{
		set_Value (COLUMNNAME_SuffixNSS, SuffixNSS);
	}

	/** Get SSN.
		@return SSN	  */
	public String getSuffixNSS () 
	{
		return (String)get_Value(COLUMNNAME_SuffixNSS);
	}

	/** Set Home Phone.
		@param TelParticular 
		Home Phone
	  */
	public void setTelParticular (String TelParticular)
	{
		set_Value (COLUMNNAME_TelParticular, TelParticular);
	}

	/** Get Home Phone.
		@return Home Phone
	  */
	public String getTelParticular () 
	{
		return (String)get_Value(COLUMNNAME_TelParticular);
	}
}