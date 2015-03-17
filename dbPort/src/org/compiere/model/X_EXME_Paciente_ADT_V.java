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

/** Generated Model for EXME_Paciente_ADT_V
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Paciente_ADT_V extends PO implements I_EXME_Paciente_ADT_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Paciente_ADT_V (Properties ctx, int EXME_Paciente_ADT_V_ID, String trxName) {
      super (ctx, EXME_Paciente_ADT_V_ID, trxName);
      /** if (EXME_Paciente_ADT_V_ID == 0) {
        } */
    }

    /** Load Constructor */
    public X_EXME_Paciente_ADT_V (Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel() {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx) {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString() { 
      StringBuffer sb = new StringBuffer ("X_EXME_Paciente_ADT_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/**
	 * Set Last Name.
	 *	@param Apellido1
	 *		Last Name
	*/
	public void setApellido1(String Apellido1) {
		set_Value(COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
	 *	@return Last Name	*/
	public String getApellido1() { 
		return (String) get_Value(COLUMNNAME_Apellido1);
	}

	/**
	 * Set Mother's Maiden Name.
	 *	@param Apellido2
	 *		Mother's Maiden Name
	*/
	public void setApellido2(String Apellido2) {
		set_Value(COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
	 *	@return Mother's Maiden Name	*/
	public String getApellido2() { 
		return (String) get_Value(COLUMNNAME_Apellido2);
	}

	/**
	 * Set Address .
	 *	@param C_Location_ID
	 *		Location or Address
	*/
	public void setC_Location_ID(int C_Location_ID) {
		if (C_Location_ID < 1) {
			set_Value(COLUMNNAME_C_Location_ID, null);
		} else {
			set_Value(COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
		}
	}

	/** Get Address .
	 *	@return Location or Address	*/
	public int getC_Location_ID() { 
		Integer ii = (Integer) get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/**
	 * Set Marital Status.
	 *	@param EdoCivil
	 *		Marital Status
	*/
	public void setEdoCivil(boolean EdoCivil) {
		set_Value (COLUMNNAME_EdoCivil, Boolean.valueOf(EdoCivil));
	}

	/** Get Marital Status.
	 *	@return Marital Status	*/
	public boolean isEdoCivil() { 
		Object oo = get_Value(COLUMNNAME_EdoCivil);
		if (oo != null) {
			if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/**
	 * Set EMail Address.
	 *	@param EMail
	 *		Electronic Mail Address
	*/
	public void setEMail(String EMail) {
		set_Value(COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
	 *	@return Electronic Mail Address	*/
	public String getEMail() { 
		return (String) get_Value(COLUMNNAME_EMail);
	}

	/**
	 * Set Patient Data View for ADT Messages.
	 *	@param EXME_Paciente_ADT_V_ID
	 *		Patient Data View for ADT Messages
	*/
	public void setEXME_Paciente_ADT_V_ID(int EXME_Paciente_ADT_V_ID) {
		if (EXME_Paciente_ADT_V_ID < 1) {
			set_ValueNoCheck(COLUMNNAME_EXME_Paciente_ADT_V_ID, null);
		} else {
			set_ValueNoCheck(COLUMNNAME_EXME_Paciente_ADT_V_ID, Integer.valueOf(EXME_Paciente_ADT_V_ID));
		}
	}

	/** Get Patient Data View for ADT Messages.
	 *	@return Patient Data View for ADT Messages	*/
	public int getEXME_Paciente_ADT_V_ID() { 
		Integer ii = (Integer) get_Value(COLUMNNAME_EXME_Paciente_ADT_V_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException {
		Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
		I_EXME_Paciente result = null;
		try {
			Constructor<?> constructor = null;
			constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
			result = (I_EXME_Paciente) constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
		} catch (Exception e) {
			log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
			log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Set Patient.
	 *	@param EXME_Paciente_ID
	 *		Patient
	*/
	public void setEXME_Paciente_ID(int EXME_Paciente_ID) {
		if (EXME_Paciente_ID < 1) {
			set_Value(COLUMNNAME_EXME_Paciente_ID, null);
		} else {
			set_Value(COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
		}
	}

	/** Get Patient.
	 *	@return Patient	*/
	public int getEXME_Paciente_ID() { 
		Integer ii = (Integer) get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/**
	 * Set Date of Death.
	 *	@param Fecha_Muerte
	 *		Date of Death
	*/
	public void setFecha_Muerte(Timestamp Fecha_Muerte) {
		set_Value(COLUMNNAME_Fecha_Muerte, Fecha_Muerte);
	}

	/** Get Date of Death.
	 *	@return Date of Death	*/
	public Timestamp getFecha_Muerte() { 
		return (Timestamp) get_Value(COLUMNNAME_Fecha_Muerte);
	}

	/**
	 * Set Birth Date.
	 *	@param FechaNac
	 *		Birth Date
	*/
	public void setFechaNac(Timestamp FechaNac) {
		set_Value(COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
	 *	@return Birth Date	*/
	public Timestamp getFechaNac() { 
		return (Timestamp) get_Value(COLUMNNAME_FechaNac);
	}

	/**
	 * Set Ethnic Group.
	 *	@param GpoEtnico
	 *		Ethnic Group
	*/
	public void setGpoEtnico(String GpoEtnico) {
		set_Value(COLUMNNAME_GpoEtnico, GpoEtnico);
	}

	/** Get Ethnic Group.
	 *	@return Ethnic Group	*/
	public String getGpoEtnico() { 
		return (String) get_Value(COLUMNNAME_GpoEtnico);
	}

	/**
	 * Set Is Death?.
	 *	@param IsMuerto
	 *		Is Death?
	*/
	public void setIsMuerto(String IsMuerto) {
		set_Value(COLUMNNAME_IsMuerto, IsMuerto);
	}

	/** Get Is Death?.
	 *	@return Is Death?	*/
	public String getIsMuerto() { 
		return (String) get_Value(COLUMNNAME_IsMuerto);
	}

	/**
	 * Set Native Language.
	 *	@param LenguaNativa
	 *		Native Language
	*/
	public void setLenguaNativa(String LenguaNativa) {
		set_Value(COLUMNNAME_LenguaNativa, LenguaNativa);
	}

	/** Get Native Language.
	 *	@return Native Language	*/
	public String getLenguaNativa() { 
		return (String) get_Value(COLUMNNAME_LenguaNativa);
	}

	/**
	 * Set Nationality.
	 *	@param Nacionalidad
	 *		Nationality
	*/
	public void setNacionalidad(String Nacionalidad) {
		set_Value(COLUMNNAME_Nacionalidad, Nacionalidad);
	}

	/** Get Nationality.
	 *	@return Nationality	*/
	public String getNacionalidad() { 
		return (String) get_Value(COLUMNNAME_Nacionalidad);
	}

	/**
	 * Set Name.
	 *	@param Nombre
	 *		Name of friend
	*/
	public void setNombre(String Nombre) {
		set_Value(COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
	 *	@return Name of friend	*/
	public String getNombre() { 
		return (String) get_Value(COLUMNNAME_Nombre);
	}

	/**
	 * Set Social Security Number.
	 *	@param NSS
	 *		Social Security Number
	*/
	public void setNSS(String NSS) {
		set_Value(COLUMNNAME_NSS, NSS);
	}

	/** Get Social Security Number.
	 *	@return Social Security Number	*/
	public String getNSS() { 
		return (String) get_Value(COLUMNNAME_NSS);
	}

	/**
	 * Set Race.
	 *	@param Raza
	 *		Race
	*/
	public void setRaza(String Raza) {
		set_Value(COLUMNNAME_Raza, Raza);
	}

	/** Get Race.
	 *	@return Race	*/
	public String getRaza() { 
		return (String) get_Value(COLUMNNAME_Raza);
	}

	/**
	 * Set Religion.
	 *	@param Religion
	 *		Religion
	*/
	public void setReligion(String Religion) {
		set_Value(COLUMNNAME_Religion, Religion);
	}

	/** Get Religion.
	 *	@return Religion	*/
	public String getReligion() { 
		return (String) get_Value(COLUMNNAME_Religion);
	}

	/**
	 * Set Sex.
	 *	@param Sexo
	 *		Sex
	*/
	public void setSexo(boolean Sexo) {
		set_Value (COLUMNNAME_Sexo, Boolean.valueOf(Sexo));
	}

	/** Get Sex.
	 *	@return Sex	*/
	public boolean isSexo() { 
		Object oo = get_Value(COLUMNNAME_Sexo);
		if (oo != null) {
			if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/**
	 * Set Home Phone.
	 *	@param TelParticular
	 *		Home Phone
	*/
	public void setTelParticular(String TelParticular) {
		set_Value(COLUMNNAME_TelParticular, TelParticular);
	}

	/** Get Home Phone.
	 *	@return Home Phone	*/
	public String getTelParticular() { 
		return (String) get_Value(COLUMNNAME_TelParticular);
	}
}