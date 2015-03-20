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

/** Generated Model for I_EXME_Medico
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Medico extends PO implements I_I_EXME_Medico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Medico (Properties ctx, int I_EXME_Medico_ID, String trxName)
    {
      super (ctx, I_EXME_Medico_ID, trxName);
      /** if (I_EXME_Medico_ID == 0)
        {
			setI_EXME_Medico_ID (0);
			setI_IsImported (false);
// N
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Medico (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Medico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_Value (COLUMNNAME_Address1, Address1);
	}

	/** Get Address 1.
		@return Address line 1 for this location
	  */
	public String getAddress1 () 
	{
		return (String)get_Value(COLUMNNAME_Address1);
	}

	/** Set Address 1 of Doctor's office .
		@param Address1Cons 
		Address 1 of Doctor's office
	  */
	public void setAddress1Cons (String Address1Cons)
	{
		set_Value (COLUMNNAME_Address1Cons, Address1Cons);
	}

	/** Get Address 1 of Doctor's office .
		@return Address 1 of Doctor's office
	  */
	public String getAddress1Cons () 
	{
		return (String)get_Value(COLUMNNAME_Address1Cons);
	}

	/** Set Address 2.
		@param Address2 
		Address line 2 for this location
	  */
	public void setAddress2 (String Address2)
	{
		set_Value (COLUMNNAME_Address2, Address2);
	}

	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
	}

	/** Set Address 2 of Doctor's office.
		@param Address2Cons 
		Address 2 of Doctor's office
	  */
	public void setAddress2Cons (String Address2Cons)
	{
		set_Value (COLUMNNAME_Address2Cons, Address2Cons);
	}

	/** Get Address 2 of Doctor's office.
		@return Address 2 of Doctor's office
	  */
	public String getAddress2Cons () 
	{
		return (String)get_Value(COLUMNNAME_Address2Cons);
	}

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
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

	/** Set Doctor's office Country.
		@param C_Country_Cons_ID 
		Doctor's office Country
	  */
	public void setC_Country_Cons_ID (int C_Country_Cons_ID)
	{
		if (C_Country_Cons_ID < 1) 
			set_Value (COLUMNNAME_C_Country_Cons_ID, null);
		else 
			set_Value (COLUMNNAME_C_Country_Cons_ID, Integer.valueOf(C_Country_Cons_ID));
	}

	/** Get Doctor's office Country.
		@return Doctor's office Country
	  */
	public int getC_Country_Cons_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_Cons_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Country getC_Country() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Country.Table_Name);
        I_C_Country result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Country)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Country_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1) 
			set_Value (COLUMNNAME_C_Country_ID, null);
		else 
			set_Value (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Professional Identity Number.
		@param CedProfesional 
		Professional Identity Number
	  */
	public void setCedProfesional (String CedProfesional)
	{
		set_Value (COLUMNNAME_CedProfesional, CedProfesional);
	}

	/** Get Professional Identity Number.
		@return Professional Identity Number
	  */
	public String getCedProfesional () 
	{
		return (String)get_Value(COLUMNNAME_CedProfesional);
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

	/** Set Medical Center Key.
		@param CentroMedico_Value 
		Medical Center Search Key
	  */
	public void setCentroMedico_Value (String CentroMedico_Value)
	{
		set_Value (COLUMNNAME_CentroMedico_Value, CentroMedico_Value);
	}

	/** Get Medical Center Key.
		@return Medical Center Search Key
	  */
	public String getCentroMedico_Value () 
	{
		return (String)get_Value(COLUMNNAME_CentroMedico_Value);
	}

	/** Set City.
		@param City 
		Identifies a City
	  */
	public void setCity (String City)
	{
		set_Value (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Set Doctor's Office City.
		@param CityCons 
		Doctors' office city
	  */
	public void setCityCons (String CityCons)
	{
		set_Value (COLUMNNAME_CityCons, CityCons);
	}

	/** Get Doctor's Office City.
		@return Doctors' office city
	  */
	public String getCityCons () 
	{
		return (String)get_Value(COLUMNNAME_CityCons);
	}

	/** Set Doctor`s office address.
		@param C_Location_Cons_ID 
		Doctor's office address
	  */
	public void setC_Location_Cons_ID (int C_Location_Cons_ID)
	{
		if (C_Location_Cons_ID < 1) 
			set_Value (COLUMNNAME_C_Location_Cons_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_Cons_ID, Integer.valueOf(C_Location_Cons_ID));
	}

	/** Get Doctor`s office address.
		@return Doctor's office address
	  */
	public int getC_Location_Cons_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_Cons_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Health Code.
		@param CodSanidad 
		Health Code
	  */
	public void setCodSanidad (String CodSanidad)
	{
		set_Value (COLUMNNAME_CodSanidad, CodSanidad);
	}

	/** Get Health Code.
		@return Health Code
	  */
	public String getCodSanidad () 
	{
		return (String)get_Value(COLUMNNAME_CodSanidad);
	}

	/** Set Contact Name.
		@param ContactName 
		Business Partner Contact Name
	  */
	public void setContactName (String ContactName)
	{
		set_Value (COLUMNNAME_ContactName, ContactName);
	}

	/** Get Contact Name.
		@return Business Partner Contact Name
	  */
	public String getContactName () 
	{
		return (String)get_Value(COLUMNNAME_ContactName);
	}

	/** Set ISO Country Code.
		@param CountryCode 
		Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode)
	{
		set_Value (COLUMNNAME_CountryCode, CountryCode);
	}

	/** Get ISO Country Code.
		@return Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode () 
	{
		return (String)get_Value(COLUMNNAME_CountryCode);
	}

	/** Set Doctor's Office Country Code.
		@param CountryCodeCons 
		Two capital letters to see the country code ISO according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.htm
	  */
	public void setCountryCodeCons (String CountryCodeCons)
	{
		set_Value (COLUMNNAME_CountryCodeCons, CountryCodeCons);
	}

	/** Get Doctor's Office Country Code.
		@return Two capital letters to see the country code ISO according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.htm
	  */
	public String getCountryCodeCons () 
	{
		return (String)get_Value(COLUMNNAME_CountryCodeCons);
	}

	/** Set State/Region of Doctor's office.
		@param C_Region_Cons_ID 
		State/Region of Doctor's office
	  */
	public void setC_Region_Cons_ID (int C_Region_Cons_ID)
	{
		if (C_Region_Cons_ID < 1) 
			set_Value (COLUMNNAME_C_Region_Cons_ID, null);
		else 
			set_Value (COLUMNNAME_C_Region_Cons_ID, Integer.valueOf(C_Region_Cons_ID));
	}

	/** Get State/Region of Doctor's office.
		@return State/Region of Doctor's office
	  */
	public int getC_Region_Cons_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_Cons_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Region getC_Region() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Region.Table_Name);
        I_C_Region result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Region)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Region_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Region.
		@param C_Region_ID 
		Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID)
	{
		if (C_Region_ID < 1) 
			set_Value (COLUMNNAME_C_Region_ID, null);
		else 
			set_Value (COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
	}

	/** Get Region.
		@return Identifies a geographical Region
	  */
	public int getC_Region_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_ID);
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

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EdoCivil AD_Reference_ID=1000000 */
	public static final int EDOCIVIL_AD_Reference_ID=1000000;
	/** Married = C */
	public static final String EDOCIVIL_Married = "C";
	/** Single = S */
	public static final String EDOCIVIL_Single = "S";
	/** Divorced = D */
	public static final String EDOCIVIL_Divorced = "D";
	/** Widower = V */
	public static final String EDOCIVIL_Widower = "V";
	/** Free Union = U */
	public static final String EDOCIVIL_FreeUnion = "U";
	/** Other = O */
	public static final String EDOCIVIL_Other = "O";
	/** Set Marital Status.
		@param EdoCivil 
		Marital Status
	  */
	public void setEdoCivil (String EdoCivil)
	{

		if (EdoCivil == null || EdoCivil.equals("C") || EdoCivil.equals("S") || EdoCivil.equals("D") || EdoCivil.equals("V") || EdoCivil.equals("U") || EdoCivil.equals("O")); else throw new IllegalArgumentException ("EdoCivil Invalid value - " + EdoCivil + " - Reference_ID=1000000 - C - S - D - V - U - O");		set_Value (COLUMNNAME_EdoCivil, EdoCivil);
	}

	/** Get Marital Status.
		@return Marital Status
	  */
	public String getEdoCivil () 
	{
		return (String)get_Value(COLUMNNAME_EdoCivil);
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

	/** Set Is internal.
		@param EsInterno 
		is Internal
	  */
	public void setEsInterno (boolean EsInterno)
	{
		set_Value (COLUMNNAME_EsInterno, Boolean.valueOf(EsInterno));
	}

	/** Get Is internal.
		@return is Internal
	  */
	public boolean isEsInterno () 
	{
		Object oo = get_Value(COLUMNNAME_EsInterno);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Certified by the Board.
		@param EstaCertifConsejo 
		Certified
	  */
	public void setEstaCertifConsejo (boolean EstaCertifConsejo)
	{
		set_Value (COLUMNNAME_EstaCertifConsejo, Boolean.valueOf(EstaCertifConsejo));
	}

	/** Get Is Certified by the Board.
		@return Certified
	  */
	public boolean isEstaCertifConsejo () 
	{
		Object oo = get_Value(COLUMNNAME_EstaCertifConsejo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Recertified.
		@param EstaRecertificado 
		Recertified
	  */
	public void setEstaRecertificado (boolean EstaRecertificado)
	{
		set_Value (COLUMNNAME_EstaRecertificado, Boolean.valueOf(EstaRecertificado));
	}

	/** Get Is Recertified.
		@return Recertified
	  */
	public boolean isEstaRecertificado () 
	{
		Object oo = get_Value(COLUMNNAME_EstaRecertificado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Suspended.
		@param EstaSuspendido 
		Suspended
	  */
	public void setEstaSuspendido (boolean EstaSuspendido)
	{
		set_Value (COLUMNNAME_EstaSuspendido, Boolean.valueOf(EstaSuspendido));
	}

	/** Get Suspended.
		@return Suspended
	  */
	public boolean isEstaSuspendido () 
	{
		Object oo = get_Value(COLUMNNAME_EstaSuspendido);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Medical Center.
		@param EXME_CentroMedico_ID 
		medical Center
	  */
	public void setEXME_CentroMedico_ID (int EXME_CentroMedico_ID)
	{
		if (EXME_CentroMedico_ID < 1) 
			set_Value (COLUMNNAME_EXME_CentroMedico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CentroMedico_ID, Integer.valueOf(EXME_CentroMedico_ID));
	}

	/** Get Medical Center.
		@return medical Center
	  */
	public int getEXME_CentroMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CentroMedico_ID);
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

	/** Set Type of Doctor.
		@param EXME_TipoMedico_ID 
		Type of Doctor
	  */
	public void setEXME_TipoMedico_ID (int EXME_TipoMedico_ID)
	{
		if (EXME_TipoMedico_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoMedico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoMedico_ID, Integer.valueOf(EXME_TipoMedico_ID));
	}

	/** Get Type of Doctor.
		@return Type of Doctor
	  */
	public int getEXME_TipoMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type of Doctor Code.
		@param EXME_TipoMedico_Value 
		Type of Doctor search code
	  */
	public void setEXME_TipoMedico_Value (String EXME_TipoMedico_Value)
	{
		set_Value (COLUMNNAME_EXME_TipoMedico_Value, EXME_TipoMedico_Value);
	}

	/** Get Type of Doctor Code.
		@return Type of Doctor search code
	  */
	public String getEXME_TipoMedico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TipoMedico_Value);
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

	/** Set University.
		@param EXME_Universidad_ID 
		University
	  */
	public void setEXME_Universidad_ID (int EXME_Universidad_ID)
	{
		if (EXME_Universidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Universidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Universidad_ID, Integer.valueOf(EXME_Universidad_ID));
	}

	/** Get University.
		@return University
	  */
	public int getEXME_Universidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Universidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set University Code.
		@param EXME_Universidad_Value 
		University Search Code
	  */
	public void setEXME_Universidad_Value (String EXME_Universidad_Value)
	{
		set_Value (COLUMNNAME_EXME_Universidad_Value, EXME_Universidad_Value);
	}

	/** Get University Code.
		@return University Search Code
	  */
	public String getEXME_Universidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Universidad_Value);
	}

	/** Set Cerificate Date.
		@param FechaCertifConsejo 
		Cerificate Date
	  */
	public void setFechaCertifConsejo (Timestamp FechaCertifConsejo)
	{
		set_Value (COLUMNNAME_FechaCertifConsejo, FechaCertifConsejo);
	}

	/** Get Cerificate Date.
		@return Cerificate Date
	  */
	public Timestamp getFechaCertifConsejo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCertifConsejo);
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
		set_Value (COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
		@return Birth Date
	  */
	public Timestamp getFechaNac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaNac);
	}

	/** Set Title Date.
		@param FechaTitulo 
		Title Date
	  */
	public void setFechaTitulo (Timestamp FechaTitulo)
	{
		set_Value (COLUMNNAME_FechaTitulo, FechaTitulo);
	}

	/** Get Title Date.
		@return Title Date
	  */
	public Timestamp getFechaTitulo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaTitulo);
	}

	/** Set Certif Expire Date.
		@param FechaVencimCertif 
		Cerificate Expire Date
	  */
	public void setFechaVencimCertif (Timestamp FechaVencimCertif)
	{
		set_Value (COLUMNNAME_FechaVencimCertif, FechaVencimCertif);
	}

	/** Get Certif Expire Date.
		@return Cerificate Expire Date
	  */
	public Timestamp getFechaVencimCertif () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVencimCertif);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported Doctor.
		@param I_EXME_Medico_ID 
		Imported Doctor
	  */
	public void setI_EXME_Medico_ID (int I_EXME_Medico_ID)
	{
		if (I_EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Medico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Medico_ID, Integer.valueOf(I_EXME_Medico_ID));
	}

	/** Get Imported Doctor.
		@return Imported Doctor
	  */
	public int getI_EXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
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
	public void setIntervaloConsulta (int IntervaloConsulta)
	{
		set_Value (COLUMNNAME_IntervaloConsulta, Integer.valueOf(IntervaloConsulta));
	}

	/** Get Consult Interval.
		@return Consult Interval	  */
	public int getIntervaloConsulta () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntervaloConsulta);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Code.
		@param M_Product_Value 
		product search Code
	  */
	public void setM_Product_Value (String M_Product_Value)
	{
		set_Value (COLUMNNAME_M_Product_Value, M_Product_Value);
	}

	/** Get Product Code.
		@return product search Code
	  */
	public String getM_Product_Value () 
	{
		return (String)get_Value(COLUMNNAME_M_Product_Value);
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

	/** Set Doctor's Office No..
		@param NoConsultorio 
		Doctor's office No.
	  */
	public void setNoConsultorio (String NoConsultorio)
	{
		set_Value (COLUMNNAME_NoConsultorio, NoConsultorio);
	}

	/** Get Doctor's Office No..
		@return Doctor's office No.
	  */
	public String getNoConsultorio () 
	{
		return (String)get_Value(COLUMNNAME_NoConsultorio);
	}

	/** Set ZIP.
		@param Postal 
		Postal code
	  */
	public void setPostal (String Postal)
	{
		set_Value (COLUMNNAME_Postal, Postal);
	}

	/** Get ZIP.
		@return Postal code
	  */
	public String getPostal () 
	{
		return (String)get_Value(COLUMNNAME_Postal);
	}

	/** Set Doctor's Office Postal Code.
		@param PostalCons 
		Doctor's office Postal Code
	  */
	public void setPostalCons (String PostalCons)
	{
		set_Value (COLUMNNAME_PostalCons, PostalCons);
	}

	/** Get Doctor's Office Postal Code.
		@return Doctor's office Postal Code
	  */
	public String getPostalCons () 
	{
		return (String)get_Value(COLUMNNAME_PostalCons);
	}

	/** Set Price by Consulting.
		@param Precio_Consulta 
		Price by consulting
	  */
	public void setPrecio_Consulta (BigDecimal Precio_Consulta)
	{
		set_Value (COLUMNNAME_Precio_Consulta, Precio_Consulta);
	}

	/** Get Price by Consulting.
		@return Price by consulting
	  */
	public BigDecimal getPrecio_Consulta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Precio_Consulta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Radio.
		@param Radio Radio	  */
	public void setRadio (String Radio)
	{
		set_Value (COLUMNNAME_Radio, Radio);
	}

	/** Get Radio.
		@return Radio	  */
	public String getRadio () 
	{
		return (String)get_Value(COLUMNNAME_Radio);
	}

	/** Set Region.
		@param RegionName 
		Name of the Region
	  */
	public void setRegionName (String RegionName)
	{
		set_Value (COLUMNNAME_RegionName, RegionName);
	}

	/** Get Region.
		@return Name of the Region
	  */
	public String getRegionName () 
	{
		return (String)get_Value(COLUMNNAME_RegionName);
	}

	/** Set Doctor's Office Region.
		@param RegionNameCons 
		Name of Region
	  */
	public void setRegionNameCons (String RegionNameCons)
	{
		set_Value (COLUMNNAME_RegionNameCons, RegionNameCons);
	}

	/** Get Doctor's Office Region.
		@return Name of Region
	  */
	public String getRegionNameCons () 
	{
		return (String)get_Value(COLUMNNAME_RegionNameCons);
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

		if (Sexo == null || Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
	}

	/** Set Doctor's Office Telephone.
		@param TelConsultorio 
		doctor's office telephone
	  */
	public void setTelConsultorio (String TelConsultorio)
	{
		set_Value (COLUMNNAME_TelConsultorio, TelConsultorio);
	}

	/** Get Doctor's Office Telephone.
		@return doctor's office telephone
	  */
	public String getTelConsultorio () 
	{
		return (String)get_Value(COLUMNNAME_TelConsultorio);
	}

	/** Set Private Telephone.
		@param TelParticular 
		Private telephone
	  */
	public void setTelParticular (String TelParticular)
	{
		set_Value (COLUMNNAME_TelParticular, TelParticular);
	}

	/** Get Private Telephone.
		@return Private telephone
	  */
	public String getTelParticular () 
	{
		return (String)get_Value(COLUMNNAME_TelParticular);
	}

	/** Set Incentive.
		@param TieneIncentivo 
		Has incentive
	  */
	public void setTieneIncentivo (boolean TieneIncentivo)
	{
		set_Value (COLUMNNAME_TieneIncentivo, Boolean.valueOf(TieneIncentivo));
	}

	/** Get Incentive.
		@return Has incentive
	  */
	public boolean isTieneIncentivo () 
	{
		Object oo = get_Value(COLUMNNAME_TieneIncentivo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Shift Code.
		@param Turnos_Value 
		Shift search code
	  */
	public void setTurnos_Value (String Turnos_Value)
	{
		set_Value (COLUMNNAME_Turnos_Value, Turnos_Value);
	}

	/** Get Shift Code.
		@return Shift search code
	  */
	public String getTurnos_Value () 
	{
		return (String)get_Value(COLUMNNAME_Turnos_Value);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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