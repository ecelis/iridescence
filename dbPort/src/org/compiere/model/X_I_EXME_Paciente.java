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

/** Generated Model for I_EXME_Paciente
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_I_EXME_Paciente extends PO implements I_I_EXME_Paciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Paciente (Properties ctx, int I_EXME_Paciente_ID, String trxName)
    {
      super (ctx, I_EXME_Paciente_ID, trxName);
      /** if (I_EXME_Paciente_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Paciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Paciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1 of Registration.
		@param Adress1Reg 
		Address 1 of Registrarion
	  */
	public void setAdress1Reg (String Adress1Reg)
	{
		set_Value (COLUMNNAME_Adress1Reg, Adress1Reg);
	}

	/** Get Address 1 of Registration.
		@return Address 1 of Registrarion
	  */
	public String getAdress1Reg () 
	{
		return (String)get_Value(COLUMNNAME_Adress1Reg);
	}

	/** Set Address 2 of Registration.
		@param Adress2Reg 
		Address 2 of Registration
	  */
	public void setAdress2Reg (String Adress2Reg)
	{
		set_Value (COLUMNNAME_Adress2Reg, Adress2Reg);
	}

	/** Get Address 2 of Registration.
		@return Address 2 of Registration
	  */
	public String getAdress2Reg () 
	{
		return (String)get_Value(COLUMNNAME_Adress2Reg);
	}

	/** Set Antiquity.
		@param Antiguedad_Fam 
		Antiquity of the relative's actual job
	  */
	public void setAntiguedad_Fam (int Antiguedad_Fam)
	{
		set_Value (COLUMNNAME_Antiguedad_Fam, Integer.valueOf(Antiguedad_Fam));
	}

	/** Get Antiquity.
		@return Antiquity of the relative's actual job
	  */
	public int getAntiguedad_Fam () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Antiguedad_Fam);
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

	/** Set Last Name.
		@param Apellido1_Fam 
		Relative's Last Name
	  */
	public void setApellido1_Fam (String Apellido1_Fam)
	{
		set_Value (COLUMNNAME_Apellido1_Fam, Apellido1_Fam);
	}

	/** Get Last Name.
		@return Relative's Last Name
	  */
	public String getApellido1_Fam () 
	{
		return (String)get_Value(COLUMNNAME_Apellido1_Fam);
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

	/** Set Mother's maiden Name.
		@param Apellido2_Fam 
		Mother's maiden Name
	  */
	public void setApellido2_Fam (String Apellido2_Fam)
	{
		set_Value (COLUMNNAME_Apellido2_Fam, Apellido2_Fam);
	}

	/** Get Mother's maiden Name.
		@return Mother's maiden Name
	  */
	public String getApellido2_Fam () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2_Fam);
	}

	/** Set Married Name.
		@param Apellido3 
		Married Name
	  */
	public void setApellido3 (String Apellido3)
	{
		set_Value (COLUMNNAME_Apellido3, Apellido3);
	}

	/** Get Married Name.
		@return Married Name
	  */
	public String getApellido3 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido3);
	}

	/** Set Weapon.
		@param Arma Weapon	  */
	public void setArma (String Arma)
	{
		set_Value (COLUMNNAME_Arma, Arma);
	}

	/** Get Weapon.
		@return Weapon	  */
	public String getArma () 
	{
		return (String)get_Value(COLUMNNAME_Arma);
	}

	/** Set Street and Number.
		@param CalleNumero Street and Number	  */
	public void setCalleNumero (String CalleNumero)
	{
		set_Value (COLUMNNAME_CalleNumero, CalleNumero);
	}

	/** Get Street and Number.
		@return Street and Number	  */
	public String getCalleNumero () 
	{
		return (String)get_Value(COLUMNNAME_CalleNumero);
	}

	/** Set Birth Street and Number.
		@param CalleNumero_Nac Birth Street and Number	  */
	public void setCalleNumero_Nac (String CalleNumero_Nac)
	{
		set_Value (COLUMNNAME_CalleNumero_Nac, CalleNumero_Nac);
	}

	/** Get Birth Street and Number.
		@return Birth Street and Number	  */
	public String getCalleNumero_Nac () 
	{
		return (String)get_Value(COLUMNNAME_CalleNumero_Nac);
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner_Location.Table_Name);
        I_C_BPartner_Location result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner_Location)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_Location_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Business Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Business Partner Location Value.
		@param C_BPartner_Location_Value 
		Business Partner Location Value
	  */
	public void setC_BPartner_Location_Value (String C_BPartner_Location_Value)
	{
		set_Value (COLUMNNAME_C_BPartner_Location_Value, C_BPartner_Location_Value);
	}

	/** Get Business Partner Location Value.
		@return Business Partner Location Value
	  */
	public String getC_BPartner_Location_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_BPartner_Location_Value);
	}

	/** Set Insurance.
		@param C_BPartner_Seg_ID 
		Business Partner Insurance
	  */
	public void setC_BPartner_Seg_ID (int C_BPartner_Seg_ID)
	{
		if (C_BPartner_Seg_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Seg_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Seg_ID, Integer.valueOf(C_BPartner_Seg_ID));
	}

	/** Get Insurance.
		@return Business Partner Insurance
	  */
	public int getC_BPartner_Seg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Seg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Business Partner Insurance Value.
		@param C_BPartner_Seg_Value Business Partner Insurance Value	  */
	public void setC_BPartner_Seg_Value (String C_BPartner_Seg_Value)
	{
		set_Value (COLUMNNAME_C_BPartner_Seg_Value, C_BPartner_Seg_Value);
	}

	/** Get Business Partner Insurance Value.
		@return Business Partner Insurance Value	  */
	public String getC_BPartner_Seg_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_BPartner_Seg_Value);
	}

	/** Set Business Partner Value.
		@param C_BPartner_Value Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value)
	{
		set_Value (COLUMNNAME_C_BPartner_Value, C_BPartner_Value);
	}

	/** Get Business Partner Value.
		@return Business Partner Value	  */
	public String getC_BPartner_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_BPartner_Value);
	}

	/** Set Country of Registration.
		@param C_Country_IDReg 
		Country of Registration
	  */
	public void setC_Country_IDReg (int C_Country_IDReg)
	{
		set_Value (COLUMNNAME_C_Country_IDReg, Integer.valueOf(C_Country_IDReg));
	}

	/** Get Country of Registration.
		@return Country of Registration
	  */
	public int getC_Country_IDReg () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_IDReg);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Country of responsible person.
		@param C_Country_PersResp_ID 
		Country of responsible person
	  */
	public void setC_Country_PersResp_ID (int C_Country_PersResp_ID)
	{
		if (C_Country_PersResp_ID < 1) 
			set_Value (COLUMNNAME_C_Country_PersResp_ID, null);
		else 
			set_Value (COLUMNNAME_C_Country_PersResp_ID, Integer.valueOf(C_Country_PersResp_ID));
	}

	/** Get Country of responsible person.
		@return Country of responsible person
	  */
	public int getC_Country_PersResp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_PersResp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CityReg.
		@param CityReg 
		City of registration
	  */
	public void setCityReg (String CityReg)
	{
		set_Value (COLUMNNAME_CityReg, CityReg);
	}

	/** Get CityReg.
		@return City of registration
	  */
	public String getCityReg () 
	{
		return (String)get_Value(COLUMNNAME_CityReg);
	}

	/** Set City.
		@param Ciudad 
		description of a city
	  */
	public void setCiudad (String Ciudad)
	{
		set_Value (COLUMNNAME_Ciudad, Ciudad);
	}

	/** Get City.
		@return description of a city
	  */
	public String getCiudad () 
	{
		return (String)get_Value(COLUMNNAME_Ciudad);
	}

	/** Set Birth City.
		@param Ciudad_Nac Birth City	  */
	public void setCiudad_Nac (String Ciudad_Nac)
	{
		set_Value (COLUMNNAME_Ciudad_Nac, Ciudad_Nac);
	}

	/** Get Birth City.
		@return Birth City	  */
	public String getCiudad_Nac () 
	{
		return (String)get_Value(COLUMNNAME_Ciudad_Nac);
	}

	/** Set City of Responsible person.
		@param CiudadPersResp 
		City of Responsible person
	  */
	public void setCiudadPersResp (String CiudadPersResp)
	{
		set_Value (COLUMNNAME_CiudadPersResp, CiudadPersResp);
	}

	/** Get City of Responsible person.
		@return City of Responsible person
	  */
	public String getCiudadPersResp () 
	{
		return (String)get_Value(COLUMNNAME_CiudadPersResp);
	}

	/** Set Address .
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

	/** Set Birth Place.
		@param C_Location_Nac_ID 
		Birth Place
	  */
	public void setC_Location_Nac_ID (int C_Location_Nac_ID)
	{
		if (C_Location_Nac_ID < 1) 
			set_Value (COLUMNNAME_C_Location_Nac_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_Nac_ID, Integer.valueOf(C_Location_Nac_ID));
	}

	/** Get Birth Place.
		@return Birth Place
	  */
	public int getC_Location_Nac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_Nac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Registered Address.
		@param C_LocationReg_ID Registered Address	  */
	public void setC_LocationReg_ID (int C_LocationReg_ID)
	{
		if (C_LocationReg_ID < 1) 
			set_Value (COLUMNNAME_C_LocationReg_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationReg_ID, Integer.valueOf(C_LocationReg_ID));
	}

	/** Get Registered Address.
		@return Registered Address	  */
	public int getC_LocationReg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationReg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suburb / District.
		@param Colonia 
		Suburb / District
	  */
	public void setColonia (String Colonia)
	{
		set_Value (COLUMNNAME_Colonia, Colonia);
	}

	/** Get Suburb / District.
		@return Suburb / District
	  */
	public String getColonia () 
	{
		return (String)get_Value(COLUMNNAME_Colonia);
	}

	/** Set Birth Colony.
		@param Colonia_Nac Birth Colony	  */
	public void setColonia_Nac (String Colonia_Nac)
	{
		set_Value (COLUMNNAME_Colonia_Nac, Colonia_Nac);
	}

	/** Get Birth Colony.
		@return Birth Colony	  */
	public String getColonia_Nac () 
	{
		return (String)get_Value(COLUMNNAME_Colonia_Nac);
	}

	/** Set Address Colony of Responsible Person.
		@param ColoniaPersResp 
		Address of responsible person
	  */
	public void setColoniaPersResp (String ColoniaPersResp)
	{
		set_Value (COLUMNNAME_ColoniaPersResp, ColoniaPersResp);
	}

	/** Get Address Colony of Responsible Person.
		@return Address of responsible person
	  */
	public String getColoniaPersResp () 
	{
		return (String)get_Value(COLUMNNAME_ColoniaPersResp);
	}

	/** Set Country of Registration.
		@param CountryReg 
		Country of Registration
	  */
	public void setCountryReg (String CountryReg)
	{
		set_Value (COLUMNNAME_CountryReg, CountryReg);
	}

	/** Get Country of Registration.
		@return Country of Registration
	  */
	public String getCountryReg () 
	{
		return (String)get_Value(COLUMNNAME_CountryReg);
	}

	/** Set Zip Code.
		@param CP 
		Zip Code
	  */
	public void setCP (String CP)
	{
		set_Value (COLUMNNAME_CP, CP);
	}

	/** Get Zip Code.
		@return Zip Code
	  */
	public String getCP () 
	{
		return (String)get_Value(COLUMNNAME_CP);
	}

	/** Set Birth Postal Code.
		@param CP_Nac Birth Postal Code	  */
	public void setCP_Nac (String CP_Nac)
	{
		set_Value (COLUMNNAME_CP_Nac, CP_Nac);
	}

	/** Get Birth Postal Code.
		@return Birth Postal Code	  */
	public String getCP_Nac () 
	{
		return (String)get_Value(COLUMNNAME_CP_Nac);
	}

	/** Set Zip code Of Responsible Person.
		@param CpPersResp 
		Postal code of responsible person
	  */
	public void setCpPersResp (String CpPersResp)
	{
		set_Value (COLUMNNAME_CpPersResp, CpPersResp);
	}

	/** Get Zip code Of Responsible Person.
		@return Postal code of responsible person
	  */
	public String getCpPersResp () 
	{
		return (String)get_Value(COLUMNNAME_CpPersResp);
	}

	/** Set Region of Registration.
		@param C_Region_IDReg 
		Region of Registration
	  */
	public void setC_Region_IDReg (int C_Region_IDReg)
	{
		set_Value (COLUMNNAME_C_Region_IDReg, Integer.valueOf(C_Region_IDReg));
	}

	/** Get Region of Registration.
		@return Region of Registration
	  */
	public int getC_Region_IDReg () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_IDReg);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Region of responsible person.
		@param C_Region_PersResp_ID 
		Region of responsible person
	  */
	public void setC_Region_PersResp_ID (int C_Region_PersResp_ID)
	{
		if (C_Region_PersResp_ID < 1) 
			set_Value (COLUMNNAME_C_Region_PersResp_ID, null);
		else 
			set_Value (COLUMNNAME_C_Region_PersResp_ID, Integer.valueOf(C_Region_PersResp_ID));
	}

	/** Get Region of responsible person.
		@return Region of responsible person
	  */
	public int getC_Region_PersResp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_PersResp_ID);
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

	/** Set Degree.
		@param Degree Degree	  */
	public void setDegree (String Degree)
	{
		set_Value (COLUMNNAME_Degree, Degree);
	}

	/** Get Degree.
		@return Degree	  */
	public String getDegree () 
	{
		return (String)get_Value(COLUMNNAME_Degree);
	}

	/** Set Right Holder.
		@param Derechohabiente 
		Right Holder
	  */
	public void setDerechohabiente (boolean Derechohabiente)
	{
		set_Value (COLUMNNAME_Derechohabiente, Boolean.valueOf(Derechohabiente));
	}

	/** Get Right Holder.
		@return Right Holder
	  */
	public boolean isDerechohabiente () 
	{
		Object oo = get_Value(COLUMNNAME_Derechohabiente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Right Holder of Other Institution.
		@param DerechohabienteOtro 
		Righr holder of other institution
	  */
	public void setDerechohabienteOtro (boolean DerechohabienteOtro)
	{
		set_Value (COLUMNNAME_DerechohabienteOtro, Boolean.valueOf(DerechohabienteOtro));
	}

	/** Get Right Holder of Other Institution.
		@return Righr holder of other institution
	  */
	public boolean isDerechohabienteOtro () 
	{
		Object oo = get_Value(COLUMNNAME_DerechohabienteOtro);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Diagnostico_Egreso_Descr.
		@param Diagnostico_Egreso_Descr Diagnostico_Egreso_Descr	  */
	public void setDiagnostico_Egreso_Descr (String Diagnostico_Egreso_Descr)
	{
		set_Value (COLUMNNAME_Diagnostico_Egreso_Descr, Diagnostico_Egreso_Descr);
	}

	/** Get Diagnostico_Egreso_Descr.
		@return Diagnostico_Egreso_Descr	  */
	public String getDiagnostico_Egreso_Descr () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico_Egreso_Descr);
	}

	/** Set Diagnostico_Ingreso_Descr.
		@param Diagnostico_Ingreso_Descr Diagnostico_Ingreso_Descr	  */
	public void setDiagnostico_Ingreso_Descr (String Diagnostico_Ingreso_Descr)
	{
		set_Value (COLUMNNAME_Diagnostico_Ingreso_Descr, Diagnostico_Ingreso_Descr);
	}

	/** Get Diagnostico_Ingreso_Descr.
		@return Diagnostico_Ingreso_Descr	  */
	public String getDiagnostico_Ingreso_Descr () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico_Ingreso_Descr);
	}

	/** Set Address.
		@param DirFamiliar 
		Address of relative
	  */
	public void setDirFamiliar (String DirFamiliar)
	{
		set_Value (COLUMNNAME_DirFamiliar, DirFamiliar);
	}

	/** Get Address.
		@return Address of relative
	  */
	public String getDirFamiliar () 
	{
		return (String)get_Value(COLUMNNAME_DirFamiliar);
	}

	/** Set Address of Responsible Person.
		@param DirPersResp 
		Address of responsible person
	  */
	public void setDirPersResp (String DirPersResp)
	{
		set_Value (COLUMNNAME_DirPersResp, DirPersResp);
	}

	/** Get Address of Responsible Person.
		@return Address of responsible person
	  */
	public String getDirPersResp () 
	{
		return (String)get_Value(COLUMNNAME_DirPersResp);
	}

	/** Set Work Address.
		@param DirTrabPersResp 
		Work address of responsible person
	  */
	public void setDirTrabPersResp (String DirTrabPersResp)
	{
		set_Value (COLUMNNAME_DirTrabPersResp, DirTrabPersResp);
	}

	/** Get Work Address.
		@return Work address of responsible person
	  */
	public String getDirTrabPersResp () 
	{
		return (String)get_Value(COLUMNNAME_DirTrabPersResp);
	}

	/** Set AgreementDocument.
		@param DocumentoConvenio AgreementDocument	  */
	public void setDocumentoConvenio (String DocumentoConvenio)
	{
		set_Value (COLUMNNAME_DocumentoConvenio, DocumentoConvenio);
	}

	/** Get AgreementDocument.
		@return AgreementDocument	  */
	public String getDocumentoConvenio () 
	{
		return (String)get_Value(COLUMNNAME_DocumentoConvenio);
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

	/** Set Insured.
		@param EsAsegurado 
		the patient is insured
	  */
	public void setEsAsegurado (boolean EsAsegurado)
	{
		set_Value (COLUMNNAME_EsAsegurado, Boolean.valueOf(EsAsegurado));
	}

	/** Get Insured.
		@return the patient is insured
	  */
	public boolean isEsAsegurado () 
	{
		Object oo = get_Value(COLUMNNAME_EsAsegurado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Value Schooling.
		@param Escolaridad_Value Value Schooling	  */
	public void setEscolaridad_Value (String Escolaridad_Value)
	{
		set_Value (COLUMNNAME_Escolaridad_Value, Escolaridad_Value);
	}

	/** Get Value Schooling.
		@return Value Schooling	  */
	public String getEscolaridad_Value () 
	{
		return (String)get_Value(COLUMNNAME_Escolaridad_Value);
	}

	/** Set Value Schooling TS.
		@param Especialidad_TS_Value Value Schooling TS	  */
	public void setEspecialidad_TS_Value (String Especialidad_TS_Value)
	{
		set_Value (COLUMNNAME_Especialidad_TS_Value, Especialidad_TS_Value);
	}

	/** Get Value Schooling TS.
		@return Value Schooling TS	  */
	public String getEspecialidad_TS_Value () 
	{
		return (String)get_Value(COLUMNNAME_Especialidad_TS_Value);
	}

	/** Set Is Title Holder.
		@param EsTitular 
		The right holder is the title holder
	  */
	public void setEsTitular (boolean EsTitular)
	{
		set_Value (COLUMNNAME_EsTitular, Boolean.valueOf(EsTitular));
	}

	/** Get Is Title Holder.
		@return The right holder is the title holder
	  */
	public boolean isEsTitular () 
	{
		Object oo = get_Value(COLUMNNAME_EsTitular);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Medical Record.
		@param EXME_ArchClinico_ID 
		Medical Record
	  */
	public void setEXME_ArchClinico_ID (int EXME_ArchClinico_ID)
	{
		if (EXME_ArchClinico_ID < 1) 
			set_Value (COLUMNNAME_EXME_ArchClinico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ArchClinico_ID, Integer.valueOf(EXME_ArchClinico_ID));
	}

	/** Get Medical Record.
		@return Medical Record
	  */
	public int getEXME_ArchClinico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ArchClinico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Clinical File Value.
		@param EXME_ArchClinico_Value Clinical File Value	  */
	public void setEXME_ArchClinico_Value (String EXME_ArchClinico_Value)
	{
		set_Value (COLUMNNAME_EXME_ArchClinico_Value, EXME_ArchClinico_Value);
	}

	/** Get Clinical File Value.
		@return Clinical File Value	  */
	public String getEXME_ArchClinico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_ArchClinico_Value);
	}

	public I_EXME_Arma getEXME_Arma() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Arma.Table_Name);
        I_EXME_Arma result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Arma)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Arma_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Weapon.
		@param EXME_Arma_ID 
		Militar Weapon
	  */
	public void setEXME_Arma_ID (int EXME_Arma_ID)
	{
		if (EXME_Arma_ID < 1) 
			set_Value (COLUMNNAME_EXME_Arma_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Arma_ID, Integer.valueOf(EXME_Arma_ID));
	}

	/** Get Weapon.
		@return Militar Weapon
	  */
	public int getEXME_Arma_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Arma_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Zone.
		@param EXME_Delegacion_Paciente_ID Patient Zone	  */
	public void setEXME_Delegacion_Paciente_ID (int EXME_Delegacion_Paciente_ID)
	{
		if (EXME_Delegacion_Paciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_Paciente_ID, Integer.valueOf(EXME_Delegacion_Paciente_ID));
	}

	/** Get Patient Zone.
		@return Patient Zone	  */
	public int getEXME_Delegacion_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Escolaridad getEXME_Escolaridad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Escolaridad.Table_Name);
        I_EXME_Escolaridad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Escolaridad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Escolaridad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Schooling.
		@param EXME_Escolaridad_ID 
		Schooling
	  */
	public void setEXME_Escolaridad_ID (int EXME_Escolaridad_ID)
	{
		if (EXME_Escolaridad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Escolaridad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Escolaridad_ID, Integer.valueOf(EXME_Escolaridad_ID));
	}

	/** Get Schooling.
		@return Schooling
	  */
	public int getEXME_Escolaridad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Escolaridad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especialidad_TS getEXME_Especialidad_TS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad_TS.Table_Name);
        I_EXME_Especialidad_TS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad_TS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_TS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Social Work Specialty.
		@param EXME_Especialidad_TS_ID 
		Social Work Specialty
	  */
	public void setEXME_Especialidad_TS_ID (int EXME_Especialidad_TS_ID)
	{
		if (EXME_Especialidad_TS_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_TS_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_TS_ID, Integer.valueOf(EXME_Especialidad_TS_ID));
	}

	/** Get Social Work Specialty.
		@return Social Work Specialty
	  */
	public int getEXME_Especialidad_TS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_TS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Grado getEXME_Grado() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Grado.Table_Name);
        I_EXME_Grado result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Grado)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Grado_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Grade.
		@param EXME_Grado_ID 
		Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID)
	{
		if (EXME_Grado_ID < 1) 
			set_Value (COLUMNNAME_EXME_Grado_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Grado_ID, Integer.valueOf(EXME_Grado_ID));
	}

	/** Get Grade.
		@return Militar Grade
	  */
	public int getEXME_Grado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Grupo_Especialidad getEXME_Grupo_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Grupo_Especialidad.Table_Name);
        I_EXME_Grupo_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Grupo_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Grupo_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Speciality Group.
		@param EXME_Grupo_Especialidad_ID 
		Militar Speciality Group
	  */
	public void setEXME_Grupo_Especialidad_ID (int EXME_Grupo_Especialidad_ID)
	{
		if (EXME_Grupo_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Grupo_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Grupo_Especialidad_ID, Integer.valueOf(EXME_Grupo_Especialidad_ID));
	}

	/** Get Speciality Group.
		@return Militar Speciality Group
	  */
	public int getEXME_Grupo_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grupo_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Institucion.Table_Name);
        I_EXME_Institucion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Institucion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Institucion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Institution.
		@param EXME_Institucion_ID 
		Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Institucion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Institution.
		@return Institution
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Nacionalidad getEXME_Nacionalidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Nacionalidad.Table_Name);
        I_EXME_Nacionalidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Nacionalidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Nacionalidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Nationality.
		@param EXME_Nacionalidad_ID 
		Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID)
	{
		if (EXME_Nacionalidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Nacionalidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Nacionalidad_ID, Integer.valueOf(EXME_Nacionalidad_ID));
	}

	/** Get Nationality.
		@return Nationality
	  */
	public int getEXME_Nacionalidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Nacionalidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ocupation.
		@param EXME_Ocupacion_Fam_ID 
		Relative Ocupation
	  */
	public void setEXME_Ocupacion_Fam_ID (int EXME_Ocupacion_Fam_ID)
	{
		if (EXME_Ocupacion_Fam_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ocupacion_Fam_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ocupacion_Fam_ID, Integer.valueOf(EXME_Ocupacion_Fam_ID));
	}

	/** Get Ocupation.
		@return Relative Ocupation
	  */
	public int getEXME_Ocupacion_Fam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_Fam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value Occupation.
		@param EXME_Ocupacion_Fam_Value Value Occupation	  */
	public void setEXME_Ocupacion_Fam_Value (String EXME_Ocupacion_Fam_Value)
	{
		set_Value (COLUMNNAME_EXME_Ocupacion_Fam_Value, EXME_Ocupacion_Fam_Value);
	}

	/** Get Value Occupation.
		@return Value Occupation	  */
	public String getEXME_Ocupacion_Fam_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Ocupacion_Fam_Value);
	}

	public I_EXME_Ocupacion getEXME_Ocupacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ocupacion.Table_Name);
        I_EXME_Ocupacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ocupacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ocupacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Ocupation.
		@param EXME_Ocupacion_ID 
		Ocupation
	  */
	public void setEXME_Ocupacion_ID (int EXME_Ocupacion_ID)
	{
		if (EXME_Ocupacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ocupacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ocupacion_ID, Integer.valueOf(EXME_Ocupacion_ID));
	}

	/** Get Ocupation.
		@return Ocupation
	  */
	public int getEXME_Ocupacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value Occupation.
		@param EXME_Ocupacion_Value Value Occupation	  */
	public void setEXME_Ocupacion_Value (String EXME_Ocupacion_Value)
	{
		set_Value (COLUMNNAME_EXME_Ocupacion_Value, EXME_Ocupacion_Value);
	}

	/** Get Value Occupation.
		@return Value Occupation	  */
	public String getEXME_Ocupacion_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Ocupacion_Value);
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	public I_EXME_Parentesco getEXME_Parentesco() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Parentesco.Table_Name);
        I_EXME_Parentesco result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Parentesco)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Parentesco_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Kinship.
		@param EXME_Parentesco_ID 
		Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID)
	{
		if (EXME_Parentesco_ID < 1) 
			set_Value (COLUMNNAME_EXME_Parentesco_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Parentesco_ID, Integer.valueOf(EXME_Parentesco_ID));
	}

	/** Get Kinship.
		@return Kinship
	  */
	public int getEXME_Parentesco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Kinship_Value.
		@param EXME_Parentesco_Value Kinship_Value	  */
	public void setEXME_Parentesco_Value (String EXME_Parentesco_Value)
	{
		set_Value (COLUMNNAME_EXME_Parentesco_Value, EXME_Parentesco_Value);
	}

	/** Get Kinship_Value.
		@return Kinship_Value	  */
	public String getEXME_Parentesco_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Parentesco_Value);
	}

	public I_EXME_PatientClass getEXME_PatientClass() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PatientClass.Table_Name);
        I_EXME_PatientClass result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PatientClass)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PatientClass_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Classification.
		@param EXME_PatientClass_ID Patient Classification	  */
	public void setEXME_PatientClass_ID (int EXME_PatientClass_ID)
	{
		if (EXME_PatientClass_ID < 1) 
			set_Value (COLUMNNAME_EXME_PatientClass_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PatientClass_ID, Integer.valueOf(EXME_PatientClass_ID));
	}

	/** Get Patient Classification.
		@return Patient Classification	  */
	public int getEXME_PatientClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Class Value.
		@param EXME_PatientClass_Value 
		Patient Class Value
	  */
	public void setEXME_PatientClass_Value (String EXME_PatientClass_Value)
	{
		set_Value (COLUMNNAME_EXME_PatientClass_Value, EXME_PatientClass_Value);
	}

	/** Get Patient Class Value.
		@return Patient Class Value
	  */
	public String getEXME_PatientClass_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_PatientClass_Value);
	}

	public I_EXME_Referencia getEXME_Referencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Referencia.Table_Name);
        I_EXME_Referencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Referencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Referencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Reference.
		@param EXME_Referencia_ID 
		Reference to which the patient belongs.
	  */
	public void setEXME_Referencia_ID (int EXME_Referencia_ID)
	{
		if (EXME_Referencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Referencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Referencia_ID, Integer.valueOf(EXME_Referencia_ID));
	}

	/** Get Patient Reference.
		@return Reference to which the patient belongs.
	  */
	public int getEXME_Referencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Referencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Referencia_Int getEXME_Referencia_Int() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Referencia_Int.Table_Name);
        I_EXME_Referencia_Int result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Referencia_Int)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Referencia_Int_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Internal Reference.
		@param EXME_Referencia_Int_ID 
		Patient's Internal Reference
	  */
	public void setEXME_Referencia_Int_ID (int EXME_Referencia_Int_ID)
	{
		if (EXME_Referencia_Int_ID < 1) 
			set_Value (COLUMNNAME_EXME_Referencia_Int_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Referencia_Int_ID, Integer.valueOf(EXME_Referencia_Int_ID));
	}

	/** Get Internal Reference.
		@return Patient's Internal Reference
	  */
	public int getEXME_Referencia_Int_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Referencia_Int_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Religion getEXME_Religion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Religion.Table_Name);
        I_EXME_Religion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Religion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Religion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Religion.
		@param EXME_Religion_ID 
		Religion
	  */
	public void setEXME_Religion_ID (int EXME_Religion_ID)
	{
		if (EXME_Religion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Religion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Religion_ID, Integer.valueOf(EXME_Religion_ID));
	}

	/** Get Religion.
		@return Religion
	  */
	public int getEXME_Religion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Religion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value Religion.
		@param EXME_Religion_Value Value Religion	  */
	public void setEXME_Religion_Value (String EXME_Religion_Value)
	{
		set_Value (COLUMNNAME_EXME_Religion_Value, EXME_Religion_Value);
	}

	/** Get Value Religion.
		@return Value Religion	  */
	public String getEXME_Religion_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Religion_Value);
	}

	/** Set Town Council Name.
		@param EXME_TownCouncil_Name 
		Town Council Name
	  */
	public void setEXME_TownCouncil_Name (String EXME_TownCouncil_Name)
	{
		set_Value (COLUMNNAME_EXME_TownCouncil_Name, EXME_TownCouncil_Name);
	}

	/** Get Town Council Name.
		@return Town Council Name
	  */
	public String getEXME_TownCouncil_Name () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TownCouncil_Name);
	}

	public I_EXME_Unidad getEXME_Unidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Unidad.Table_Name);
        I_EXME_Unidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Unidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Unidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Unit.
		@param EXME_Unidad_ID 
		Militar Unit
	  */
	public void setEXME_Unidad_ID (int EXME_Unidad_ID)
	{
		if (EXME_Unidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Unidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Unidad_ID, Integer.valueOf(EXME_Unidad_ID));
	}

	/** Get Unit.
		@return Militar Unit
	  */
	public int getEXME_Unidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Unidad_ID);
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

	/** Set Registration Date.
		@param FechaRegistro 
		Registration Date
	  */
	public void setFechaRegistro (Timestamp FechaRegistro)
	{
		set_Value (COLUMNNAME_FechaRegistro, FechaRegistro);
	}

	/** Get Registration Date.
		@return Registration Date
	  */
	public Timestamp getFechaRegistro () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRegistro);
	}

	/** Set Termination Date.
		@param FechaVencimiento Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento)
	{
		set_Value (COLUMNNAME_FechaVencimiento, FechaVencimiento);
	}

	/** Get Termination Date.
		@return Termination Date	  */
	public Timestamp getFechaVencimiento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVencimiento);
	}

	/** Set Vali Date.
		@param FechaVigencia 
		Valid Date
	  */
	public void setFechaVigencia (Timestamp FechaVigencia)
	{
		set_Value (COLUMNNAME_FechaVigencia, FechaVigencia);
	}

	/** Get Vali Date.
		@return Valid Date
	  */
	public Timestamp getFechaVigencia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVigencia);
	}

	/** Set GroupEsp.
		@param GroupEsp GroupEsp	  */
	public void setGroupEsp (String GroupEsp)
	{
		set_Value (COLUMNNAME_GroupEsp, GroupEsp);
	}

	/** Get GroupEsp.
		@return GroupEsp	  */
	public String getGroupEsp () 
	{
		return (String)get_Value(COLUMNNAME_GroupEsp);
	}

	/** Set Birth Hour.
		@param HoraNac 
		Birth Hour
	  */
	public void setHoraNac (String HoraNac)
	{
		set_Value (COLUMNNAME_HoraNac, HoraNac);
	}

	/** Get Birth Hour.
		@return Birth Hour
	  */
	public String getHoraNac () 
	{
		return (String)get_Value(COLUMNNAME_HoraNac);
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

	/** Set Patient.
		@param I_EXME_Paciente_ID Patient	  */
	public void setI_EXME_Paciente_ID (int I_EXME_Paciente_ID)
	{
		if (I_EXME_Paciente_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Paciente_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Paciente_ID, Integer.valueOf(I_EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient	  */
	public int getI_EXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Paciente_ID);
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

	/** Set Image.
		@param Imagen 
		Name of stored image
	  */
	public void setImagen (String Imagen)
	{
		set_Value (COLUMNNAME_Imagen, Imagen);
	}

	/** Get Image.
		@return Name of stored image
	  */
	public String getImagen () 
	{
		return (String)get_Value(COLUMNNAME_Imagen);
	}

	/** Set Social Security Number.
		@param Imss 
		Social Security Number
	  */
	public void setImss (String Imss)
	{
		set_Value (COLUMNNAME_Imss, Imss);
	}

	/** Get Social Security Number.
		@return Social Security Number
	  */
	public String getImss () 
	{
		return (String)get_Value(COLUMNNAME_Imss);
	}

	/** Set Institucion_Names.
		@param Institucion_Names Institucion_Names	  */
	public void setInstitucion_Names (String Institucion_Names)
	{
		set_Value (COLUMNNAME_Institucion_Names, Institucion_Names);
	}

	/** Get Institucion_Names.
		@return Institucion_Names	  */
	public String getInstitucion_Names () 
	{
		return (String)get_Value(COLUMNNAME_Institucion_Names);
	}

	/** Set Value Institution.
		@param Institucion_Value Value Institution	  */
	public void setInstitucion_Value (String Institucion_Value)
	{
		set_Value (COLUMNNAME_Institucion_Value, Institucion_Value);
	}

	/** Get Value Institution.
		@return Value Institution	  */
	public String getInstitucion_Value () 
	{
		return (String)get_Value(COLUMNNAME_Institucion_Value);
	}

	/** Set Credit Limit Of Claimholders.
		@param LimCredDerechoh Credit Limit Of Claimholders	  */
	public void setLimCredDerechoh (BigDecimal LimCredDerechoh)
	{
		set_Value (COLUMNNAME_LimCredDerechoh, LimCredDerechoh);
	}

	/** Get Credit Limit Of Claimholders.
		@return Credit Limit Of Claimholders	  */
	public BigDecimal getLimCredDerechoh () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LimCredDerechoh);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Credit Limit.
		@param LimiteCredito 
		Credit Limit
	  */
	public void setLimiteCredito (BigDecimal LimiteCredito)
	{
		set_Value (COLUMNNAME_LimiteCredito, LimiteCredito);
	}

	/** Get Credit Limit.
		@return Credit Limit
	  */
	public BigDecimal getLimiteCredito () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LimiteCredito);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Nacionality Value.
		@param Nacionalidad_Value Nacionality Value	  */
	public void setNacionalidad_Value (String Nacionalidad_Value)
	{
		set_Value (COLUMNNAME_Nacionalidad_Value, Nacionalidad_Value);
	}

	/** Get Nacionality Value.
		@return Nacionality Value	  */
	public String getNacionalidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_Nacionalidad_Value);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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

	/** Set Authorizarion No..
		@param NoAutorizacion 
		Authorization Number
	  */
	public void setNoAutorizacion (String NoAutorizacion)
	{
		set_Value (COLUMNNAME_NoAutorizacion, NoAutorizacion);
	}

	/** Get Authorizarion No..
		@return Authorization Number
	  */
	public String getNoAutorizacion () 
	{
		return (String)get_Value(COLUMNNAME_NoAutorizacion);
	}

	/** Set Name.
		@param Nombre_Fam 
		Relative Name
	  */
	public void setNombre_Fam (String Nombre_Fam)
	{
		set_Value (COLUMNNAME_Nombre_Fam, Nombre_Fam);
	}

	/** Get Name.
		@return Relative Name
	  */
	public String getNombre_Fam () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Fam);
	}

	/** Set Name.
		@param NombreFamiliar 
		Relative Name
	  */
	public void setNombreFamiliar (String NombreFamiliar)
	{
		set_Value (COLUMNNAME_NombreFamiliar, NombreFamiliar);
	}

	/** Get Name.
		@return Relative Name
	  */
	public String getNombreFamiliar () 
	{
		return (String)get_Value(COLUMNNAME_NombreFamiliar);
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

	/** Set Sinister No..
		@param NoSiniestro 
		Sinister Number
	  */
	public void setNoSiniestro (String NoSiniestro)
	{
		set_Value (COLUMNNAME_NoSiniestro, NoSiniestro);
	}

	/** Get Sinister No..
		@return Sinister Number
	  */
	public String getNoSiniestro () 
	{
		return (String)get_Value(COLUMNNAME_NoSiniestro);
	}

	/** Set Record Number of Foreign.
		@param NumExtReg 
		Record Number of Foreign
	  */
	public void setNumExtReg (String NumExtReg)
	{
		set_Value (COLUMNNAME_NumExtReg, NumExtReg);
	}

	/** Get Record Number of Foreign.
		@return Record Number of Foreign
	  */
	public String getNumExtReg () 
	{
		return (String)get_Value(COLUMNNAME_NumExtReg);
	}

	/** Set Number interior of Registration.
		@param NumInReg 
		Number interior of Registration
	  */
	public void setNumInReg (String NumInReg)
	{
		set_Value (COLUMNNAME_NumInReg, NumInReg);
	}

	/** Get Number interior of Registration.
		@return Number interior of Registration
	  */
	public String getNumInReg () 
	{
		return (String)get_Value(COLUMNNAME_NumInReg);
	}

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set Another Institution.
		@param Otra_Inst Another Institution	  */
	public void setOtra_Inst (String Otra_Inst)
	{
		set_Value (COLUMNNAME_Otra_Inst, Otra_Inst);
	}

	/** Get Another Institution.
		@return Another Institution	  */
	public String getOtra_Inst () 
	{
		return (String)get_Value(COLUMNNAME_Otra_Inst);
	}

	/** Set Pais.
		@param Pais Pais	  */
	public void setPais (String Pais)
	{
		set_Value (COLUMNNAME_Pais, Pais);
	}

	/** Get Pais.
		@return Pais	  */
	public String getPais () 
	{
		return (String)get_Value(COLUMNNAME_Pais);
	}

	/** Set Pais_Nac.
		@param Pais_Nac Pais_Nac	  */
	public void setPais_Nac (String Pais_Nac)
	{
		set_Value (COLUMNNAME_Pais_Nac, Pais_Nac);
	}

	/** Get Pais_Nac.
		@return Pais_Nac	  */
	public String getPais_Nac () 
	{
		return (String)get_Value(COLUMNNAME_Pais_Nac);
	}

	/** Set Pais_PersResp.
		@param Pais_PersResp Pais_PersResp	  */
	public void setPais_PersResp (String Pais_PersResp)
	{
		set_Value (COLUMNNAME_Pais_PersResp, Pais_PersResp);
	}

	/** Get Pais_PersResp.
		@return Pais_PersResp	  */
	public String getPais_PersResp () 
	{
		return (String)get_Value(COLUMNNAME_Pais_PersResp);
	}

	/** Set Private.
		@param Particular 
		Private
	  */
	public void setParticular (boolean Particular)
	{
		set_Value (COLUMNNAME_Particular, Boolean.valueOf(Particular));
	}

	/** Get Private.
		@return Private
	  */
	public boolean isParticular () 
	{
		Object oo = get_Value(COLUMNNAME_Particular);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Population.
		@param PoblacionAbierta 
		Open Population
	  */
	public void setPoblacionAbierta (boolean PoblacionAbierta)
	{
		set_Value (COLUMNNAME_PoblacionAbierta, Boolean.valueOf(PoblacionAbierta));
	}

	/** Get Open Population.
		@return Open Population
	  */
	public boolean isPoblacionAbierta () 
	{
		Object oo = get_Value(COLUMNNAME_PoblacionAbierta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Insurance Policy.
		@param Poliza 
		Insurance Policy
	  */
	public void setPoliza (String Poliza)
	{
		set_Value (COLUMNNAME_Poliza, Poliza);
	}

	/** Get Insurance Policy.
		@return Insurance Policy
	  */
	public String getPoliza () 
	{
		return (String)get_Value(COLUMNNAME_Poliza);
	}

	/** Set Postal Code Registration.
		@param PostalReg 
		Postal Code Registration
	  */
	public void setPostalReg (String PostalReg)
	{
		set_Value (COLUMNNAME_PostalReg, PostalReg);
	}

	/** Get Postal Code Registration.
		@return Postal Code Registration
	  */
	public String getPostalReg () 
	{
		return (String)get_Value(COLUMNNAME_PostalReg);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
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

	/** Set Job.
		@param Puesto_Fam 
		Relative Job
	  */
	public void setPuesto_Fam (String Puesto_Fam)
	{
		set_Value (COLUMNNAME_Puesto_Fam, Puesto_Fam);
	}

	/** Get Job.
		@return Relative Job
	  */
	public String getPuesto_Fam () 
	{
		return (String)get_Value(COLUMNNAME_Puesto_Fam);
	}

	/** Set Internal Reference Type.
		@param Referencia_Int_Value Internal Reference Type	  */
	public void setReferencia_Int_Value (String Referencia_Int_Value)
	{
		set_Value (COLUMNNAME_Referencia_Int_Value, Referencia_Int_Value);
	}

	/** Get Internal Reference Type.
		@return Internal Reference Type	  */
	public String getReferencia_Int_Value () 
	{
		return (String)get_Value(COLUMNNAME_Referencia_Int_Value);
	}

	/** Set Reference Value.
		@param Referencia_Value Reference Value	  */
	public void setReferencia_Value (String Referencia_Value)
	{
		set_Value (COLUMNNAME_Referencia_Value, Referencia_Value);
	}

	/** Get Reference Value.
		@return Reference Value	  */
	public String getReferencia_Value () 
	{
		return (String)get_Value(COLUMNNAME_Referencia_Value);
	}

	/** Set Region.
		@param Region Region	  */
	public void setRegion (String Region)
	{
		set_Value (COLUMNNAME_Region, Region);
	}

	/** Get Region.
		@return Region	  */
	public String getRegion () 
	{
		return (String)get_Value(COLUMNNAME_Region);
	}

	/** Set Birthplace.
		@param Region_Nac Birthplace	  */
	public void setRegion_Nac (String Region_Nac)
	{
		set_Value (COLUMNNAME_Region_Nac, Region_Nac);
	}

	/** Get Birthplace.
		@return Birthplace	  */
	public String getRegion_Nac () 
	{
		return (String)get_Value(COLUMNNAME_Region_Nac);
	}

	/** Set Responsible Person Region.
		@param Region_PersResp Responsible Person Region	  */
	public void setRegion_PersResp (String Region_PersResp)
	{
		set_Value (COLUMNNAME_Region_PersResp, Region_PersResp);
	}

	/** Get Responsible Person Region.
		@return Responsible Person Region	  */
	public String getRegion_PersResp () 
	{
		return (String)get_Value(COLUMNNAME_Region_PersResp);
	}

	/** Set RegionReg.
		@param RegionReg 
		Region of Registration
	  */
	public void setRegionReg (String RegionReg)
	{
		set_Value (COLUMNNAME_RegionReg, RegionReg);
	}

	/** Get RegionReg.
		@return Region of Registration
	  */
	public String getRegionReg () 
	{
		return (String)get_Value(COLUMNNAME_RegionReg);
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

	/** Set Relative's RFC.
		@param RFC_Fam 
		Relative's RFC
	  */
	public void setRFC_Fam (String RFC_Fam)
	{
		set_Value (COLUMNNAME_RFC_Fam, RFC_Fam);
	}

	/** Get Relative's RFC.
		@return Relative's RFC
	  */
	public String getRFC_Fam () 
	{
		return (String)get_Value(COLUMNNAME_RFC_Fam);
	}

	/** Set Medicaid.
		@param SeguroPopular 
		Medicaid number
	  */
	public void setSeguroPopular (String SeguroPopular)
	{
		set_Value (COLUMNNAME_SeguroPopular, SeguroPopular);
	}

	/** Get Medicaid.
		@return Medicaid number
	  */
	public String getSeguroPopular () 
	{
		return (String)get_Value(COLUMNNAME_SeguroPopular);
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

	/** Set Relative's Phone.
		@param TelFamiliar 
		Relative's Phone
	  */
	public void setTelFamiliar (String TelFamiliar)
	{
		set_Value (COLUMNNAME_TelFamiliar, TelFamiliar);
	}

	/** Get Relative's Phone.
		@return Relative's Phone
	  */
	public String getTelFamiliar () 
	{
		return (String)get_Value(COLUMNNAME_TelFamiliar);
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

	/** Set Relative's home phone.
		@param TelParticular_Fam 
		Relative's home phone
	  */
	public void setTelParticular_Fam (String TelParticular_Fam)
	{
		set_Value (COLUMNNAME_TelParticular_Fam, TelParticular_Fam);
	}

	/** Get Relative's home phone.
		@return Relative's home phone
	  */
	public String getTelParticular_Fam () 
	{
		return (String)get_Value(COLUMNNAME_TelParticular_Fam);
	}

	/** Set Title Holder.
		@param Titular 
		Title Holder
	  */
	public void setTitular (String Titular)
	{
		set_Value (COLUMNNAME_Titular, Titular);
	}

	/** Get Title Holder.
		@return Title Holder
	  */
	public String getTitular () 
	{
		return (String)get_Value(COLUMNNAME_Titular);
	}

	/** Set Title Holder.
		@param Titular_ID 
		Title Holder
	  */
	public void setTitular_ID (int Titular_ID)
	{
		if (Titular_ID < 1) 
			set_Value (COLUMNNAME_Titular_ID, null);
		else 
			set_Value (COLUMNNAME_Titular_ID, Integer.valueOf(Titular_ID));
	}

	/** Get Title Holder.
		@return Title Holder
	  */
	public int getTitular_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Titular_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Titular Value.
		@param Titular_Value Titular Value	  */
	public void setTitular_Value (String Titular_Value)
	{
		set_Value (COLUMNNAME_Titular_Value, Titular_Value);
	}

	/** Get Titular Value.
		@return Titular Value	  */
	public String getTitular_Value () 
	{
		return (String)get_Value(COLUMNNAME_Titular_Value);
	}

	/** Set Unity.
		@param Unidad Unity	  */
	public void setUnidad (String Unidad)
	{
		set_Value (COLUMNNAME_Unidad, Unidad);
	}

	/** Get Unity.
		@return Unity	  */
	public String getUnidad () 
	{
		return (String)get_Value(COLUMNNAME_Unidad);
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