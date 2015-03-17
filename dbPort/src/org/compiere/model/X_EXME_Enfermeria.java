/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Enfermeria
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Enfermeria extends PO implements I_EXME_Enfermeria, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Enfermeria (Properties ctx, int EXME_Enfermeria_ID, String trxName)
    {
      super (ctx, EXME_Enfermeria_ID, trxName);
      /** if (EXME_Enfermeria_ID == 0)
        {
			setAD_User_ID (0);
			setApellido1 (null);
			setEXME_Enfermeria_ID (0);
			setFechaNac (new Timestamp( System.currentTimeMillis() ));
			setName (null);
			setSexo (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Enfermeria (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Enfermeria[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		set_Value (COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
		@return Mother's Maiden Name
	  */
	public String getApellido2 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2);
	}

	/** Set Cellular Phone.
		@param Celular 
		Cellular Phone
	  */
	public void setCelular (String Celular)
	{
		set_Value (COLUMNNAME_Celular, Celular);
	}

	/** Get Cellular Phone.
		@return Cellular Phone
	  */
	public String getCelular () 
	{
		return (String)get_Value(COLUMNNAME_Celular);
	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set National Identification Number.
		@param Curp 
		National Identification Number
	  */
	public void setCurp (String Curp)
	{
		set_Value (COLUMNNAME_Curp, Curp);
	}

	/** Get National Identification Number.
		@return National Identification Number
	  */
	public String getCurp () 
	{
		return (String)get_Value(COLUMNNAME_Curp);
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

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Enfermeria_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shift.
		@param EXME_Turnos_ID 
		Shift
	  */
	public void setEXME_Turnos_ID (int EXME_Turnos_ID)
	{
		if (EXME_Turnos_ID < 1) 
			set_Value (COLUMNNAME_EXME_Turnos_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Turnos_ID, Integer.valueOf(EXME_Turnos_ID));
	}

	/** Get Shift.
		@return Shift
	  */
	public int getEXME_Turnos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Turnos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Drop Date.
		@param FechaBaja 
		Drop Date
	  */
	public void setFechaBaja (Timestamp FechaBaja)
	{
		set_Value (COLUMNNAME_FechaBaja, FechaBaja);
	}

	/** Get Drop Date.
		@return Drop Date
	  */
	public Timestamp getFechaBaja () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaBaja);
	}

	/** Set Admission Date.
		@param FechaIngreso 
		Admission Date
	  */
	public void setFechaIngreso (Timestamp FechaIngreso)
	{
		set_Value (COLUMNNAME_FechaIngreso, FechaIngreso);
	}

	/** Get Admission Date.
		@return Admission Date
	  */
	public Timestamp getFechaIngreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIngreso);
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

	/** Set Nurse Name.
		@param Nombre_Enf Nurse Name	  */
	public void setNombre_Enf (String Nombre_Enf)
	{
		set_Value (COLUMNNAME_Nombre_Enf, Nombre_Enf);
	}

	/** Get Nurse Name.
		@return Nurse Name	  */
	public String getNombre_Enf () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Enf);
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

	/** Set Taxpayer Identification Number.
		@param Rfc 
		Taxpayer Identification Number
	  */
	public void setRfc (String Rfc)
	{
		set_Value (COLUMNNAME_Rfc, Rfc);
	}

	/** Get Taxpayer Identification Number.
		@return Taxpayer Identification Number
	  */
	public String getRfc () 
	{
		return (String)get_Value(COLUMNNAME_Rfc);
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