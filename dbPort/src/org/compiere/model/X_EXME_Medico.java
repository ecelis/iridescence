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

/** Generated Model for EXME_Medico
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Medico extends PO implements I_EXME_Medico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Medico (Properties ctx, int EXME_Medico_ID, String trxName)
    {
      super (ctx, EXME_Medico_ID, trxName);
      /** if (EXME_Medico_ID == 0)
        {
			setApellido1 (null);
			setCedProfesional (null);
			setC_Location_ID (0);
			setEsInterno (false);
			setEstaCertifConsejo (true);
// Y
			setEstaSuspendido (false);
			setEXME_Medico_ID (0);
			setEXME_TipoMedico_ID (0);
			setModificaEnFactura (false);
			setName (null);
			setSexo (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Medico (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Medico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact .
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

	/** Get User/Contact .
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

	/** Set Professional Identity Number.
		@param CedProfesional 
		Professional Identity Number
	  */
	public void setCedProfesional (String CedProfesional)
	{
		if (CedProfesional == null)
			throw new IllegalArgumentException ("CedProfesional is mandatory.");
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

	/** Set Family location.
		@param C_Location_Fam_ID 
		Family location
	  */
	public void setC_Location_Fam_ID (int C_Location_Fam_ID)
	{
		if (C_Location_Fam_ID < 1) 
			set_Value (COLUMNNAME_C_Location_Fam_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_Fam_ID, Integer.valueOf(C_Location_Fam_ID));
	}

	/** Get Family location.
		@return Family location
	  */
	public int getC_Location_Fam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_Fam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
		set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
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

	/** Set Contact.
		@param Contact Contact	  */
	public void setContact (String Contact)
	{
		set_Value (COLUMNNAME_Contact, Contact);
	}

	/** Get Contact.
		@return Contact	  */
	public String getContact () 
	{
		return (String)get_Value(COLUMNNAME_Contact);
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

	/** Set DEA Number.
		@param DEANumber DEA Number	  */
	public void setDEANumber (String DEANumber)
	{
		set_Value (COLUMNNAME_DEANumber, DEANumber);
	}

	/** Get DEA Number.
		@return DEA Number	  */
	public String getDEANumber () 
	{
		return (String)get_Value(COLUMNNAME_DEANumber);
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
	/** Separated = P */
	public static final String EDOCIVIL_Separated = "P";
	/** Set Marital Status.
		@param EdoCivil 
		Marital Status
	  */
	public void setEdoCivil (String EdoCivil)
	{

		if (EdoCivil == null || EdoCivil.equals("C") || EdoCivil.equals("S") || EdoCivil.equals("D") || EdoCivil.equals("V") || EdoCivil.equals("U") || EdoCivil.equals("O") || EdoCivil.equals("P")); else throw new IllegalArgumentException ("EdoCivil Invalid value - " + EdoCivil + " - Reference_ID=1000000 - C - S - D - V - U - O - P");		set_Value (COLUMNNAME_EdoCivil, EdoCivil);
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

	public I_EXME_AMASpecialty getEXME_AMASpecialty() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AMASpecialty.Table_Name);
        I_EXME_AMASpecialty result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AMASpecialty)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AMASpecialty_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set AMA Speciality.
		@param EXME_AMASpecialty_ID AMA Speciality	  */
	public void setEXME_AMASpecialty_ID (int EXME_AMASpecialty_ID)
	{
		if (EXME_AMASpecialty_ID < 1) 
			set_Value (COLUMNNAME_EXME_AMASpecialty_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AMASpecialty_ID, Integer.valueOf(EXME_AMASpecialty_ID));
	}

	/** Get AMA Speciality.
		@return AMA Speciality	  */
	public int getEXME_AMASpecialty_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AMASpecialty_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
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

	public I_EXME_Medico_Sust getEXME_Medico_Sust() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico_Sust.Table_Name);
        I_EXME_Medico_Sust result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico_Sust)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_Sust_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Motive of discharge.
		@param EXME_MotivoBaja_ID 
		Motive of discharge
	  */
	public void setEXME_MotivoBaja_ID (int EXME_MotivoBaja_ID)
	{
		if (EXME_MotivoBaja_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoBaja_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoBaja_ID, Integer.valueOf(EXME_MotivoBaja_ID));
	}

	/** Get Motive of discharge.
		@return Motive of discharge
	  */
	public int getEXME_MotivoBaja_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoBaja_ID);
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
			 throw new IllegalArgumentException ("EXME_TipoMedico_ID is mandatory.");
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

	/** Set Fax Number.
		@param FaxNumber 
		Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public void setFaxNumber (String FaxNumber)
	{
		set_Value (COLUMNNAME_FaxNumber, FaxNumber);
	}

	/** Get Fax Number.
		@return Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public String getFaxNumber () 
	{
		return (String)get_Value(COLUMNNAME_FaxNumber);
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

	/** ItemClass AD_Reference_ID=1200115 */
	public static final int ITEMCLASS_AD_Reference_ID=1200115;
	/** Misc_Controlled_1 = 1 */
	public static final String ITEMCLASS_Misc_Controlled_1 = "1";
	/** Schedule_II = 2 */
	public static final String ITEMCLASS_Schedule_II = "2";
	/** Schedule_III = 3 */
	public static final String ITEMCLASS_Schedule_III = "3";
	/** Schedule_IV = 4 */
	public static final String ITEMCLASS_Schedule_IV = "4";
	/** Schedule_V = 5 */
	public static final String ITEMCLASS_Schedule_V = "5";
	/** Misc_Controlled_2 = C */
	public static final String ITEMCLASS_Misc_Controlled_2 = "C";
	/** Uncontrolled_1 = U */
	public static final String ITEMCLASS_Uncontrolled_1 = "U";
	/** Uncontrolled_2 = M */
	public static final String ITEMCLASS_Uncontrolled_2 = "M";
	/** Psychotropic = 7 */
	public static final String ITEMCLASS_Psychotropic = "7";
	/** Set Item Class.
		@param ItemClass 
		Item Class
	  */
	public void setItemClass (String ItemClass)
	{

		if (ItemClass == null || ItemClass.equals("1") || ItemClass.equals("2") || ItemClass.equals("3") || ItemClass.equals("4") || ItemClass.equals("5") || ItemClass.equals("C") || ItemClass.equals("U") || ItemClass.equals("M") || ItemClass.equals("7")); else throw new IllegalArgumentException ("ItemClass Invalid value - " + ItemClass + " - Reference_ID=1200115 - 1 - 2 - 3 - 4 - 5 - C - U - M - 7");		set_Value (COLUMNNAME_ItemClass, ItemClass);
	}

	/** Get Item Class.
		@return Item Class
	  */
	public String getItemClass () 
	{
		return (String)get_Value(COLUMNNAME_ItemClass);
	}

	/** Set Max Appointments per Days.
		@param MaxCitas Max Appointments per Days	  */
	public void setMaxCitas (BigDecimal MaxCitas)
	{
		set_Value (COLUMNNAME_MaxCitas, MaxCitas);
	}

	/** Get Max Appointments per Days.
		@return Max Appointments per Days	  */
	public BigDecimal getMaxCitas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxCitas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Medicaid Number.
		@param MedicaidNo Medicaid Number	  */
	public void setMedicaidNo (String MedicaidNo)
	{
		set_Value (COLUMNNAME_MedicaidNo, MedicaidNo);
	}

	/** Get Medicaid Number.
		@return Medicaid Number	  */
	public String getMedicaidNo () 
	{
		return (String)get_Value(COLUMNNAME_MedicaidNo);
	}

	/** Set Medicare Number.
		@param MedicareNo Medicare Number	  */
	public void setMedicareNo (String MedicareNo)
	{
		set_Value (COLUMNNAME_MedicareNo, MedicareNo);
	}

	/** Get Medicare Number.
		@return Medicare Number	  */
	public String getMedicareNo () 
	{
		return (String)get_Value(COLUMNNAME_MedicareNo);
	}

	/** Set Medical Message.
		@param MensajeMedico 
		Medical Message
	  */
	public void setMensajeMedico (String MensajeMedico)
	{
		set_Value (COLUMNNAME_MensajeMedico, MensajeMedico);
	}

	/** Get Medical Message.
		@return Medical Message
	  */
	public String getMensajeMedico () 
	{
		return (String)get_Value(COLUMNNAME_MensajeMedico);
	}

	/** Set Modify In Invoice.
		@param ModificaEnFactura Modify In Invoice	  */
	public void setModificaEnFactura (boolean ModificaEnFactura)
	{
		set_Value (COLUMNNAME_ModificaEnFactura, Boolean.valueOf(ModificaEnFactura));
	}

	/** Get Modify In Invoice.
		@return Modify In Invoice	  */
	public boolean isModificaEnFactura () 
	{
		Object oo = get_Value(COLUMNNAME_ModificaEnFactura);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Medical Name.
		@param Nombre_Med 
		Medical complete name
	  */
	public void setNombre_Med (String Nombre_Med)
	{
		set_Value (COLUMNNAME_Nombre_Med, Nombre_Med);
	}

	/** Get Medical Name.
		@return Medical complete name
	  */
	public String getNombre_Med () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Med);
	}

	/** Set Telephone extension.
		@param PhoneExt 
		Telephone extension
	  */
	public void setPhoneExt (int PhoneExt)
	{
		set_Value (COLUMNNAME_PhoneExt, Integer.valueOf(PhoneExt));
	}

	/** Get Telephone extension.
		@return Telephone extension
	  */
	public int getPhoneExt () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PhoneExt);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Pixels - Minute Scale.
		@param ScaleMin 
		Pixels - Minute Scale for Daily Medical Agenda
	  */
	public void setScaleMin (int ScaleMin)
	{
		set_Value (COLUMNNAME_ScaleMin, Integer.valueOf(ScaleMin));
	}

	/** Get Pixels - Minute Scale.
		@return Pixels - Minute Scale for Daily Medical Agenda
	  */
	public int getScaleMin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ScaleMin);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Level.
		@param ServiceLevel Service Level	  */
	public void setServiceLevel (String ServiceLevel)
	{
		set_Value (COLUMNNAME_ServiceLevel, ServiceLevel);
	}

	/** Get Service Level.
		@return Service Level	  */
	public String getServiceLevel () 
	{
		return (String)get_Value(COLUMNNAME_ServiceLevel);
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

	/** Set State License Number.
		@param StateLicenseNumber State License Number	  */
	public void setStateLicenseNumber (String StateLicenseNumber)
	{
		set_Value (COLUMNNAME_StateLicenseNumber, StateLicenseNumber);
	}

	/** Get State License Number.
		@return State License Number	  */
	public String getStateLicenseNumber () 
	{
		return (String)get_Value(COLUMNNAME_StateLicenseNumber);
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