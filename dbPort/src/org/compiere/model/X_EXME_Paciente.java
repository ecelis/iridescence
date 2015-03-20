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

/** Generated Model for EXME_Paciente
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Paciente extends PO implements I_EXME_Paciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Paciente (Properties ctx, int EXME_Paciente_ID, String trxName)
    {
      super (ctx, EXME_Paciente_ID, trxName);
      /** if (EXME_Paciente_ID == 0)
        {
			setAD_Language (null);
			setApellido1 (null);
			setC_BPartner_ID (0);
			setC_Location_ID (0);
			setcopyKin (false);
			setCopyLocation (false);
			setcopyResponsible (false);
			setDerechohabiente (false);
// N
			setEdoCivil (null);
			setEsAsegurado (false);
// N
			setEXME_Paciente_ID (0);
			setFamiliarComCode (null);
			setFechaNac (new Timestamp( System.currentTimeMillis() ));
			setIsChangeLog (null);
			setIsDonador (false);
			setIsFactEspec (false);
// N
			setIsPension (false);
			setIsPrinted (null);
			setIsReceptor (false);
			setIsRefer (false);
			setName (null);
			setParticularComCode (null);
			setPrescActivas (false);
			setSexo (null);
			setValue (null);
			setWork1ComCode (null);
			setWork2ComCode (null);
			setWork3ComCode (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Paciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Paciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AD_Language AD_Reference_ID=1200541 */
	public static final int AD_LANGUAGE_AD_Reference_ID=1200541;
	/** Set Language.
		@param AD_Language 
		Language for this entity
	  */
	public void setAD_Language (String AD_Language)
	{
		set_Value (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language () 
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
	}

	/** Set AD_Org_Elig_ID.
		@param AD_Org_Elig_ID AD_Org_Elig_ID	  */
	public void setAD_Org_Elig_ID (int AD_Org_Elig_ID)
	{
		if (AD_Org_Elig_ID < 1) 
			set_Value (COLUMNNAME_AD_Org_Elig_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Org_Elig_ID, Integer.valueOf(AD_Org_Elig_ID));
	}

	/** Get AD_Org_Elig_ID.
		@return AD_Org_Elig_ID	  */
	public int getAD_Org_Elig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Org_Elig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Antiquity.
		@param Antiguedad_Fam 
		Antiquity of the relative's actual job
	  */
	public void setAntiguedad_Fam (BigDecimal Antiguedad_Fam)
	{
		set_Value (COLUMNNAME_Antiguedad_Fam, Antiguedad_Fam);
	}

	/** Get Antiquity.
		@return Antiquity of the relative's actual job
	  */
	public BigDecimal getAntiguedad_Fam () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Antiguedad_Fam);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last Name.
		@param Apellido1 
		Last Name
	  */
	public void setApellido1 (String Apellido1)
	{
		if (Apellido1 == null)
			throw new IllegalArgumentException ("Apellido1 is mandatory.");
		set_ValueE (COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
		@return Last Name
	  */
	public String getApellido1 () 
	{
		return (String)get_ValueE(COLUMNNAME_Apellido1);
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
		set_ValueE (COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
		@return Mother's Maiden Name
	  */
	public String getApellido2 () 
	{
		return (String)get_ValueE(COLUMNNAME_Apellido2);
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

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Company Location.
		@return Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company of the Patient.
		@param C_BPartnerPac_ID Company of the Patient	  */
	public void setC_BPartnerPac_ID (int C_BPartnerPac_ID)
	{
		if (C_BPartnerPac_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerPac_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerPac_ID, Integer.valueOf(C_BPartnerPac_ID));
	}

	/** Get Company of the Patient.
		@return Company of the Patient	  */
	public int getC_BPartnerPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Birth Country.
		@param C_Country_Nac_ID 
		Birth Country
	  */
	public void setC_Country_Nac_ID (int C_Country_Nac_ID)
	{
		if (C_Country_Nac_ID < 1) 
			set_Value (COLUMNNAME_C_Country_Nac_ID, null);
		else 
			set_Value (COLUMNNAME_C_Country_Nac_ID, Integer.valueOf(C_Country_Nac_ID));
	}

	/** Get Birth Country.
		@return Birth Country
	  */
	public int getC_Country_Nac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_Nac_ID);
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

	/** Set Relative address.
		@param C_LocationFam_ID Relative address	  */
	public void setC_LocationFam_ID (int C_LocationFam_ID)
	{
		if (C_LocationFam_ID < 1) 
			set_Value (COLUMNNAME_C_LocationFam_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationFam_ID, Integer.valueOf(C_LocationFam_ID));
	}

	/** Get Relative address.
		@return Relative address	  */
	public int getC_LocationFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationFam_ID);
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

	/** Set Mailing Address.
		@param C_Location_Mail_ID Mailing Address	  */
	public void setC_Location_Mail_ID (int C_Location_Mail_ID)
	{
		if (C_Location_Mail_ID < 1) 
			set_Value (COLUMNNAME_C_Location_Mail_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_Mail_ID, Integer.valueOf(C_Location_Mail_ID));
	}

	/** Get Mailing Address.
		@return Mailing Address	  */
	public int getC_Location_Mail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_Mail_ID);
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

	/** Set Address of responsible person.
		@param C_LocationPerResp_ID 
		Address of responsible person
	  */
	public void setC_LocationPerResp_ID (int C_LocationPerResp_ID)
	{
		if (C_LocationPerResp_ID < 1) 
			set_Value (COLUMNNAME_C_LocationPerResp_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationPerResp_ID, Integer.valueOf(C_LocationPerResp_ID));
	}

	/** Get Address of responsible person.
		@return Address of responsible person
	  */
	public int getC_LocationPerResp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationPerResp_ID);
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

	/** Set Copy.
		@param copyKin 
		Copy Kin Information
	  */
	public void setcopyKin (boolean copyKin)
	{
		set_Value (COLUMNNAME_copyKin, Boolean.valueOf(copyKin));
	}

	/** Get Copy.
		@return Copy Kin Information
	  */
	public boolean iscopyKin () 
	{
		Object oo = get_Value(COLUMNNAME_copyKin);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Copy Location.
		@param CopyLocation 
		Copy the location
	  */
	public void setCopyLocation (boolean CopyLocation)
	{
		set_Value (COLUMNNAME_CopyLocation, Boolean.valueOf(CopyLocation));
	}

	/** Get Copy Location.
		@return Copy the location
	  */
	public boolean isCopyLocation () 
	{
		Object oo = get_Value(COLUMNNAME_CopyLocation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Copy mailing address.
		@param CopyMail 
		Copy mailing address
	  */
	public void setCopyMail (boolean CopyMail)
	{
		set_Value (COLUMNNAME_CopyMail, Boolean.valueOf(CopyMail));
	}

	/** Get Copy mailing address.
		@return Copy mailing address
	  */
	public boolean isCopyMail () 
	{
		Object oo = get_Value(COLUMNNAME_CopyMail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Copy.
		@param copyResponsible 
		Copy Responsible Information
	  */
	public void setcopyResponsible (boolean copyResponsible)
	{
		set_Value (COLUMNNAME_copyResponsible, Boolean.valueOf(copyResponsible));
	}

	/** Get Copy.
		@return Copy Responsible Information
	  */
	public boolean iscopyResponsible () 
	{
		Object oo = get_Value(COLUMNNAME_copyResponsible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Create Beneficiary.
		@param CreateBeneficiary Create Beneficiary	  */
	public void setCreateBeneficiary (String CreateBeneficiary)
	{
		set_Value (COLUMNNAME_CreateBeneficiary, CreateBeneficiary);
	}

	/** Get Create Beneficiary.
		@return Create Beneficiary	  */
	public String getCreateBeneficiary () 
	{
		return (String)get_Value(COLUMNNAME_CreateBeneficiary);
	}

	/** Set View Report.
		@param CreateReport 
		View Report of patient data
	  */
	public void setCreateReport (String CreateReport)
	{
		set_Value (COLUMNNAME_CreateReport, CreateReport);
	}

	/** Get View Report.
		@return View Report of patient data
	  */
	public String getCreateReport () 
	{
		return (String)get_Value(COLUMNNAME_CreateReport);
	}

	/** Set Driver License Region.
		@param C_RegionDriverLic_ID 
		Driver License Region
	  */
	public void setC_RegionDriverLic_ID (int C_RegionDriverLic_ID)
	{
		if (C_RegionDriverLic_ID < 1) 
			set_Value (COLUMNNAME_C_RegionDriverLic_ID, null);
		else 
			set_Value (COLUMNNAME_C_RegionDriverLic_ID, Integer.valueOf(C_RegionDriverLic_ID));
	}

	/** Get Driver License Region.
		@return Driver License Region
	  */
	public int getC_RegionDriverLic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_RegionDriverLic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Birth State.
		@param C_Region_Nac_ID 
		Birth State
	  */
	public void setC_Region_Nac_ID (int C_Region_Nac_ID)
	{
		if (C_Region_Nac_ID < 1) 
			set_Value (COLUMNNAME_C_Region_Nac_ID, null);
		else 
			set_Value (COLUMNNAME_C_Region_Nac_ID, Integer.valueOf(C_Region_Nac_ID));
	}

	/** Get Birth State.
		@return Birth State
	  */
	public int getC_Region_Nac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_Nac_ID);
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

	/** Set Recognized Diagnosis.
		@param DiagConocido 
		Recognized Diagnosis
	  */
	public void setDiagConocido (boolean DiagConocido)
	{
		set_Value (COLUMNNAME_DiagConocido, Boolean.valueOf(DiagConocido));
	}

	/** Get Recognized Diagnosis.
		@return Recognized Diagnosis
	  */
	public boolean isDiagConocido () 
	{
		Object oo = get_Value(COLUMNNAME_DiagConocido);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Company Address.
		@param Direccion_Lab_Pac 
		Company Address
	  */
	public void setDireccion_Lab_Pac (String Direccion_Lab_Pac)
	{
		set_Value (COLUMNNAME_Direccion_Lab_Pac, Direccion_Lab_Pac);
	}

	/** Get Company Address.
		@return Company Address
	  */
	public String getDireccion_Lab_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Direccion_Lab_Pac);
	}

	/** Set Birth Address.
		@param DireccionNac Birth Address	  */
	public void setDireccionNac (String DireccionNac)
	{
		throw new IllegalArgumentException ("DireccionNac is virtual column");	}

	/** Get Birth Address.
		@return Birth Address	  */
	public String getDireccionNac () 
	{
		return (String)get_Value(COLUMNNAME_DireccionNac);
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

	/** Set Drug Eligibility Application.
		@param DrugEligibilityApplication Drug Eligibility Application	  */
	public void setDrugEligibilityApplication (String DrugEligibilityApplication)
	{
		set_Value (COLUMNNAME_DrugEligibilityApplication, DrugEligibilityApplication);
	}

	/** Get Drug Eligibility Application.
		@return Drug Eligibility Application	  */
	public String getDrugEligibilityApplication () 
	{
		return (String)get_Value(COLUMNNAME_DrugEligibilityApplication);
	}

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (String Edad)
	{
		throw new IllegalArgumentException ("Edad is virtual column");	}

	/** Get Age.
		@return Age
	  */
	public String getEdad () 
	{
		return (String)get_Value(COLUMNNAME_Edad);
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
		if (EdoCivil == null) throw new IllegalArgumentException ("EdoCivil is mandatory");
		if (EdoCivil.equals("C") || EdoCivil.equals("S") || EdoCivil.equals("D") || EdoCivil.equals("V") || EdoCivil.equals("U") || EdoCivil.equals("O") || EdoCivil.equals("P")); else throw new IllegalArgumentException ("EdoCivil Invalid value - " + EdoCivil + " - Reference_ID=1000000 - C - S - D - V - U - O - P");		set_Value (COLUMNNAME_EdoCivil, EdoCivil);
	}

	/** Get Marital Status.
		@return Marital Status
	  */
	public String getEdoCivil () 
	{
		return (String)get_Value(COLUMNNAME_EdoCivil);
	}

	/** Set Eligibility Application.
		@param EligibilityApplication Eligibility Application	  */
	public void setEligibilityApplication (String EligibilityApplication)
	{
		set_Value (COLUMNNAME_EligibilityApplication, EligibilityApplication);
	}

	/** Get Eligibility Application.
		@return Eligibility Application	  */
	public String getEligibilityApplication () 
	{
		return (String)get_Value(COLUMNNAME_EligibilityApplication);
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

	/** Set Company.
		@param Empresa_Lab_Pac 
		Company that employs to the patient
	  */
	public void setEmpresa_Lab_Pac (String Empresa_Lab_Pac)
	{
		set_Value (COLUMNNAME_Empresa_Lab_Pac, Empresa_Lab_Pac);
	}

	/** Get Company.
		@return Company that employs to the patient
	  */
	public String getEmpresa_Lab_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Empresa_Lab_Pac);
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

	/** Set Height.
		@param Estatura 
		Height
	  */
	public void setEstatura (BigDecimal Estatura)
	{
		set_Value (COLUMNNAME_Estatura, Estatura);
	}

	/** Get Height.
		@return Height
	  */
	public BigDecimal getEstatura () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Estatura);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Clinical File.
		@param EXME_ArchClinico_ID 
		Clinical File
	  */
	public void setEXME_ArchClinico_ID (int EXME_ArchClinico_ID)
	{
		if (EXME_ArchClinico_ID < 1) 
			set_Value (COLUMNNAME_EXME_ArchClinico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ArchClinico_ID, Integer.valueOf(EXME_ArchClinico_ID));
	}

	/** Get Clinical File.
		@return Clinical File
	  */
	public int getEXME_ArchClinico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ArchClinico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Birth Zone.
		@param EXME_Delegacion_Nacimiento_ID Birth Zone	  */
	public void setEXME_Delegacion_Nacimiento_ID (int EXME_Delegacion_Nacimiento_ID)
	{
		if (EXME_Delegacion_Nacimiento_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_Nacimiento_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_Nacimiento_ID, Integer.valueOf(EXME_Delegacion_Nacimiento_ID));
	}

	/** Get Birth Zone.
		@return Birth Zone	  */
	public int getEXME_Delegacion_Nacimiento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_Nacimiento_ID);
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

	/** Set Discharge Diagnostic Description.
		@param EXME_Diagnostico_Egreso_Descr 
		Discharge Diagnostic Description
	  */
	public void setEXME_Diagnostico_Egreso_Descr (String EXME_Diagnostico_Egreso_Descr)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico_Egreso_Descr, EXME_Diagnostico_Egreso_Descr);
	}

	/** Get Discharge Diagnostic Description.
		@return Discharge Diagnostic Description
	  */
	public String getEXME_Diagnostico_Egreso_Descr () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico_Egreso_Descr);
	}

	/** Set Addmission Diagnostic Description.
		@param EXME_Diagnostico_Ingreso_Descr 
		Addmission Diagnostic Description
	  */
	public void setEXME_Diagnostico_Ingreso_Descr (String EXME_Diagnostico_Ingreso_Descr)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico_Ingreso_Descr, EXME_Diagnostico_Ingreso_Descr);
	}

	/** Get Addmission Diagnostic Description.
		@return Addmission Diagnostic Description
	  */
	public String getEXME_Diagnostico_Ingreso_Descr () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico_Ingreso_Descr);
	}

	/** Set Employment Information.
		@param EXME_Employment_ID 
		Employment Information
	  */
	public void setEXME_Employment_ID (int EXME_Employment_ID)
	{
		if (EXME_Employment_ID < 1) 
			set_Value (COLUMNNAME_EXME_Employment_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Employment_ID, Integer.valueOf(EXME_Employment_ID));
	}

	/** Get Employment Information.
		@return Employment Information
	  */
	public int getEXME_Employment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Employment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Clinical Record.
		@param EXME_Expediente_ID Clinical Record	  */
	public void setEXME_Expediente_ID (int EXME_Expediente_ID)
	{
		if (EXME_Expediente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Expediente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Expediente_ID, Integer.valueOf(EXME_Expediente_ID));
	}

	/** Get Clinical Record.
		@return Clinical Record	  */
	public int getEXME_Expediente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Expediente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GpoEtnico getEXME_GpoEtnico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GpoEtnico.Table_Name);
        I_EXME_GpoEtnico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GpoEtnico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GpoEtnico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Ethnicity.
		@param EXME_GpoEtnico_ID 
		Ethnicity
	  */
	public void setEXME_GpoEtnico_ID (int EXME_GpoEtnico_ID)
	{
		if (EXME_GpoEtnico_ID < 1) 
			set_Value (COLUMNNAME_EXME_GpoEtnico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GpoEtnico_ID, Integer.valueOf(EXME_GpoEtnico_ID));
	}

	/** Get Ethnicity.
		@return Ethnicity
	  */
	public int getEXME_GpoEtnico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GpoEtnico_ID);
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

	/** Set Service Facility.
		@param EXME_Institucion_ID 
		Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Institucion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Service Facility.
		@return Service Facility
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Institution Name.
		@param EXME_Institucion_Names Institution Name	  */
	public void setEXME_Institucion_Names (String EXME_Institucion_Names)
	{
		set_Value (COLUMNNAME_EXME_Institucion_Names, EXME_Institucion_Names);
	}

	/** Get Institution Name.
		@return Institution Name	  */
	public String getEXME_Institucion_Names () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Institucion_Names);
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

	/** Set Other Institution.
		@param EXME_Otra_Inst 
		Other Institution
	  */
	public void setEXME_Otra_Inst (String EXME_Otra_Inst)
	{
		set_Value (COLUMNNAME_EXME_Otra_Inst, EXME_Otra_Inst);
	}

	/** Get Other Institution.
		@return Other Institution
	  */
	public String getEXME_Otra_Inst () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Otra_Inst);
	}

	/** Set Patient's Relative.
		@param EXME_PacienteFam_ID Patient's Relative	  */
	public void setEXME_PacienteFam_ID (int EXME_PacienteFam_ID)
	{
		if (EXME_PacienteFam_ID < 1) 
			set_Value (COLUMNNAME_EXME_PacienteFam_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PacienteFam_ID, Integer.valueOf(EXME_PacienteFam_ID));
	}

	/** Get Patient's Relative.
		@return Patient's Relative	  */
	public int getEXME_PacienteFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteFam_ID);
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

	/** Set Patient (social work).
		@param EXME_Paciente_TS_ID 
		Social Work's Patient
	  */
	public void setEXME_Paciente_TS_ID (int EXME_Paciente_TS_ID)
	{
		throw new IllegalArgumentException ("EXME_Paciente_TS_ID is virtual column");	}

	/** Get Patient (social work).
		@return Social Work's Patient
	  */
	public int getEXME_Paciente_TS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_TS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Relative Kinship.
		@param EXME_ParentescoFam_ID Relative Kinship	  */
	public void setEXME_ParentescoFam_ID (int EXME_ParentescoFam_ID)
	{
		if (EXME_ParentescoFam_ID < 1) 
			set_Value (COLUMNNAME_EXME_ParentescoFam_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ParentescoFam_ID, Integer.valueOf(EXME_ParentescoFam_ID));
	}

	/** Get Relative Kinship.
		@return Relative Kinship	  */
	public int getEXME_ParentescoFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParentescoFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_Razas getEXME_Razas() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Razas.Table_Name);
        I_EXME_Razas result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Razas)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Razas_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Race.
		@param EXME_Razas_ID 
		Races
	  */
	public void setEXME_Razas_ID (int EXME_Razas_ID)
	{
		if (EXME_Razas_ID < 1) 
			set_Value (COLUMNNAME_EXME_Razas_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Razas_ID, Integer.valueOf(EXME_Razas_ID));
	}

	/** Get Race.
		@return Races
	  */
	public int getEXME_Razas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Razas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_Refer getEXME_Refer() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Refer.Table_Name);
        I_EXME_Refer result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Refer)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Refer_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's External Admission.
		@param EXME_Refer_ID Patient's External Admission	  */
	public void setEXME_Refer_ID (int EXME_Refer_ID)
	{
		if (EXME_Refer_ID < 1) 
			set_Value (COLUMNNAME_EXME_Refer_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Refer_ID, Integer.valueOf(EXME_Refer_ID));
	}

	/** Get Patient's External Admission.
		@return Patient's External Admission	  */
	public int getEXME_Refer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Refer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_RFC getEXME_RFC() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RFC.Table_Name);
        I_EXME_RFC result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RFC)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RFC_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set RFC.
		@param EXME_RFC_ID RFC	  */
	public void setEXME_RFC_ID (int EXME_RFC_ID)
	{
		if (EXME_RFC_ID < 1) 
			set_Value (COLUMNNAME_EXME_RFC_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RFC_ID, Integer.valueOf(EXME_RFC_ID));
	}

	/** Get RFC.
		@return RFC	  */
	public int getEXME_RFC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RFC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Suffix getEXME_Suffix() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Suffix.Table_Name);
        I_EXME_Suffix result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Suffix)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Suffix_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Suffix.
		@param EXME_Suffix_ID 
		Suffix
	  */
	public void setEXME_Suffix_ID (int EXME_Suffix_ID)
	{
		if (EXME_Suffix_ID < 1) 
			set_Value (COLUMNNAME_EXME_Suffix_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Suffix_ID, Integer.valueOf(EXME_Suffix_ID));
	}

	/** Get Suffix.
		@return Suffix
	  */
	public int getEXME_Suffix_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Suffix_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Clinical Record.
		@param Expediente 
		Clinical Record
	  */
	public void setExpediente (String Expediente)
	{
		set_ValueNoCheck (COLUMNNAME_Expediente, Expediente);
	}

	/** Get Clinical Record.
		@return Clinical Record
	  */
	public String getExpediente () 
	{
		return (String)get_Value(COLUMNNAME_Expediente);
	}

	/** FamiliarComCode AD_Reference_ID=1200466 */
	public static final int FAMILIARCOMCODE_AD_Reference_ID=1200466;
	/** Primary Residence Number = PRN */
	public static final String FAMILIARCOMCODE_PrimaryResidenceNumber = "PRN";
	/** Other Residence Number = ORN */
	public static final String FAMILIARCOMCODE_OtherResidenceNumber = "ORN";
	/** Work Number = WPN */
	public static final String FAMILIARCOMCODE_WorkNumber = "WPN";
	/** Vacation Home Number = VHN */
	public static final String FAMILIARCOMCODE_VacationHomeNumber = "VHN";
	/** Answering Service Number = ASN */
	public static final String FAMILIARCOMCODE_AnsweringServiceNumber = "ASN";
	/** Emergency Number = EMR */
	public static final String FAMILIARCOMCODE_EmergencyNumber = "EMR";
	/** Email Address = NET */
	public static final String FAMILIARCOMCODE_EmailAddress = "NET";
	/** Beeper Number = BPN */
	public static final String FAMILIARCOMCODE_BeeperNumber = "BPN";
	/** Mobile Phone Number = MPN */
	public static final String FAMILIARCOMCODE_MobilePhoneNumber = "MPN";
	/** Set Telecommunication Code.
		@param FamiliarComCode 
		Telecommunication Code Use Familiar Phone
	  */
	public void setFamiliarComCode (String FamiliarComCode)
	{
		if (FamiliarComCode == null) throw new IllegalArgumentException ("FamiliarComCode is mandatory");
		if (FamiliarComCode.equals("PRN") || FamiliarComCode.equals("ORN") || FamiliarComCode.equals("WPN") || FamiliarComCode.equals("VHN") || FamiliarComCode.equals("ASN") || FamiliarComCode.equals("EMR") || FamiliarComCode.equals("NET") || FamiliarComCode.equals("BPN") || FamiliarComCode.equals("MPN")); else throw new IllegalArgumentException ("FamiliarComCode Invalid value - " + FamiliarComCode + " - Reference_ID=1200466 - PRN - ORN - WPN - VHN - ASN - EMR - NET - BPN - MPN");		set_Value (COLUMNNAME_FamiliarComCode, FamiliarComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Code Use Familiar Phone
	  */
	public String getFamiliarComCode () 
	{
		return (String)get_Value(COLUMNNAME_FamiliarComCode);
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

	/** Set Final Date.
		@param FechaFin_Seg 
		Insurance's Final Date
	  */
	public void setFechaFin_Seg (Timestamp FechaFin_Seg)
	{
		set_Value (COLUMNNAME_FechaFin_Seg, FechaFin_Seg);
	}

	/** Get Final Date.
		@return Insurance's Final Date
	  */
	public Timestamp getFechaFin_Seg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin_Seg);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** Set Initial Date.
		@param FechaIni_Seg 
		Insurance's Initial Date
	  */
	public void setFechaIni_Seg (Timestamp FechaIni_Seg)
	{
		set_Value (COLUMNNAME_FechaIni_Seg, FechaIni_Seg);
	}

	/** Get Initial Date.
		@return Insurance's Initial Date
	  */
	public Timestamp getFechaIni_Seg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni_Seg);
	}

	/** Set Date of Death.
		@param Fecha_Muerte 
		Date of Death
	  */
	public void setFecha_Muerte (Timestamp Fecha_Muerte)
	{
		set_Value (COLUMNNAME_Fecha_Muerte, Fecha_Muerte);
	}

	/** Get Date of Death.
		@return Date of Death
	  */
	public Timestamp getFecha_Muerte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Muerte);
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

	/** Set Maintain Change Log.
		@param IsChangeLog 
		Maintain a log of changes
	  */
	public void setIsChangeLog (String IsChangeLog)
	{
		if (IsChangeLog == null)
			throw new IllegalArgumentException ("IsChangeLog is mandatory.");
		set_Value (COLUMNNAME_IsChangeLog, IsChangeLog);
	}

	/** Get Maintain Change Log.
		@return Maintain a log of changes
	  */
	public String getIsChangeLog () 
	{
		return (String)get_Value(COLUMNNAME_IsChangeLog);
	}

	/** Set Is Donor.
		@param IsDonador Is Donor	  */
	public void setIsDonador (boolean IsDonador)
	{
		set_Value (COLUMNNAME_IsDonador, Boolean.valueOf(IsDonador));
	}

	/** Get Is Donor.
		@return Is Donor	  */
	public boolean isDonador () 
	{
		Object oo = get_Value(COLUMNNAME_IsDonador);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Billing multiple.
		@param IsFactEspec Billing multiple	  */
	public void setIsFactEspec (boolean IsFactEspec)
	{
		set_Value (COLUMNNAME_IsFactEspec, Boolean.valueOf(IsFactEspec));
	}

	/** Get Billing multiple.
		@return Billing multiple	  */
	public boolean isFactEspec () 
	{
		Object oo = get_Value(COLUMNNAME_IsFactEspec);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsNSS.
		@param IsNSS IsNSS	  */
	public void setIsNSS (boolean IsNSS)
	{
		set_Value (COLUMNNAME_IsNSS, Boolean.valueOf(IsNSS));
	}

	/** Get IsNSS.
		@return IsNSS	  */
	public boolean isNSS () 
	{
		Object oo = get_Value(COLUMNNAME_IsNSS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pension.
		@param IsPension Pension	  */
	public void setIsPension (boolean IsPension)
	{
		set_Value (COLUMNNAME_IsPension, Boolean.valueOf(IsPension));
	}

	/** Get Pension.
		@return Pension	  */
	public boolean isPension () 
	{
		Object oo = get_Value(COLUMNNAME_IsPension);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		if (IsPrinted == null)
			throw new IllegalArgumentException ("IsPrinted is mandatory.");
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Is Receiver.
		@param IsReceptor Is Receiver	  */
	public void setIsReceptor (boolean IsReceptor)
	{
		set_Value (COLUMNNAME_IsReceptor, Boolean.valueOf(IsReceptor));
	}

	/** Get Is Receiver.
		@return Is Receiver	  */
	public boolean isReceptor () 
	{
		Object oo = get_Value(COLUMNNAME_IsReceptor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is External Patient.
		@param IsRefer Is External Patient	  */
	public void setIsRefer (boolean IsRefer)
	{
		set_Value (COLUMNNAME_IsRefer, Boolean.valueOf(IsRefer));
	}

	/** Get Is External Patient.
		@return Is External Patient	  */
	public boolean isRefer () 
	{
		Object oo = get_Value(COLUMNNAME_IsRefer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_ValueE (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_ValueE(COLUMNNAME_Name);
	}

	/** Set No Known Drug Allergies.
		@param NoAlergiasMed 
		No Known Drug Allergies
	  */
	public void setNoAlergiasMed (boolean NoAlergiasMed)
	{
		set_Value (COLUMNNAME_NoAlergiasMed, Boolean.valueOf(NoAlergiasMed));
	}

	/** Get No Known Drug Allergies.
		@return No Known Drug Allergies
	  */
	public boolean isNoAlergiasMed () 
	{
		Object oo = get_Value(COLUMNNAME_NoAlergiasMed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Relative's Number.
		@param NoFamiliar 
		Relative's Number
	  */
	public void setNoFamiliar (String NoFamiliar)
	{
		set_Value (COLUMNNAME_NoFamiliar, NoFamiliar);
	}

	/** Get Relative's Number.
		@return Relative's Number
	  */
	public String getNoFamiliar () 
	{
		return (String)get_Value(COLUMNNAME_NoFamiliar);
	}

	/** Set Middle Name.
		@param Nombre2 
		Middle name
	  */
	public void setNombre2 (String Nombre2)
	{
		set_ValueE (COLUMNNAME_Nombre2, Nombre2);
	}

	/** Get Middle Name.
		@return Middle name
	  */
	public String getNombre2 () 
	{
		return (String)get_ValueE(COLUMNNAME_Nombre2);
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
		set_ValueE (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_ValueE(COLUMNNAME_Nombre_Pac);
	}

	/** Set Payroll.
		@param Nomina 
		Payroll
	  */
	public void setNomina (String Nomina)
	{
		set_Value (COLUMNNAME_Nomina, Nomina);
	}

	/** Get Payroll.
		@return Payroll
	  */
	public String getNomina () 
	{
		return (String)get_Value(COLUMNNAME_Nomina);
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

	/** ParticularComCode AD_Reference_ID=1200466 */
	public static final int PARTICULARCOMCODE_AD_Reference_ID=1200466;
	/** Primary Residence Number = PRN */
	public static final String PARTICULARCOMCODE_PrimaryResidenceNumber = "PRN";
	/** Other Residence Number = ORN */
	public static final String PARTICULARCOMCODE_OtherResidenceNumber = "ORN";
	/** Work Number = WPN */
	public static final String PARTICULARCOMCODE_WorkNumber = "WPN";
	/** Vacation Home Number = VHN */
	public static final String PARTICULARCOMCODE_VacationHomeNumber = "VHN";
	/** Answering Service Number = ASN */
	public static final String PARTICULARCOMCODE_AnsweringServiceNumber = "ASN";
	/** Emergency Number = EMR */
	public static final String PARTICULARCOMCODE_EmergencyNumber = "EMR";
	/** Email Address = NET */
	public static final String PARTICULARCOMCODE_EmailAddress = "NET";
	/** Beeper Number = BPN */
	public static final String PARTICULARCOMCODE_BeeperNumber = "BPN";
	/** Mobile Phone Number = MPN */
	public static final String PARTICULARCOMCODE_MobilePhoneNumber = "MPN";
	/** Set Telecommunication Code.
		@param ParticularComCode 
		Telecommunication Use Code Phone
	  */
	public void setParticularComCode (String ParticularComCode)
	{
		if (ParticularComCode == null) throw new IllegalArgumentException ("ParticularComCode is mandatory");
		if (ParticularComCode.equals("PRN") || ParticularComCode.equals("ORN") || ParticularComCode.equals("WPN") || ParticularComCode.equals("VHN") || ParticularComCode.equals("ASN") || ParticularComCode.equals("EMR") || ParticularComCode.equals("NET") || ParticularComCode.equals("BPN") || ParticularComCode.equals("MPN")); else throw new IllegalArgumentException ("ParticularComCode Invalid value - " + ParticularComCode + " - Reference_ID=1200466 - PRN - ORN - WPN - VHN - ASN - EMR - NET - BPN - MPN");		set_Value (COLUMNNAME_ParticularComCode, ParticularComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Use Code Phone
	  */
	public String getParticularComCode () 
	{
		return (String)get_Value(COLUMNNAME_ParticularComCode);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set ZIP.
		@param Postal 
		Postal code
	  */
	public void setPostal (int Postal)
	{
		throw new IllegalArgumentException ("Postal is virtual column");	}

	/** Get ZIP.
		@return Postal code
	  */
	public int getPostal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Postal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Active medication.
		@param PrescActivas 
		Active medication
	  */
	public void setPrescActivas (boolean PrescActivas)
	{
		set_Value (COLUMNNAME_PrescActivas, Boolean.valueOf(PrescActivas));
	}

	/** Get Active medication.
		@return Active medication
	  */
	public boolean isPrescActivas () 
	{
		Object oo = get_Value(COLUMNNAME_PrescActivas);
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

	/** Set Employment.
		@param Puesto_Lab_Pac 
		Patient's Employment
	  */
	public void setPuesto_Lab_Pac (String Puesto_Lab_Pac)
	{
		set_Value (COLUMNNAME_Puesto_Lab_Pac, Puesto_Lab_Pac);
	}

	/** Get Employment.
		@return Patient's Employment
	  */
	public String getPuesto_Lab_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Puesto_Lab_Pac);
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

	/** Set RFC.
		@param RFC_Resp 
		Responsible's Federal Register Taxpayer
	  */
	public void setRFC_Resp (String RFC_Resp)
	{
		set_Value (COLUMNNAME_RFC_Resp, RFC_Resp);
	}

	/** Get RFC.
		@return Responsible's Federal Register Taxpayer
	  */
	public String getRFC_Resp () 
	{
		return (String)get_Value(COLUMNNAME_RFC_Resp);
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

	/** Set Send/Retrieve Information.
		@param sendInformation 
		Send/Retrieve Information
	  */
	public void setsendInformation (String sendInformation)
	{
		set_Value (COLUMNNAME_sendInformation, sendInformation);
	}

	/** Get Send/Retrieve Information.
		@return Send/Retrieve Information
	  */
	public String getsendInformation () 
	{
		return (String)get_Value(COLUMNNAME_sendInformation);
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

	/** Set Support EMail.
		@param SupportEMail 
		EMail address to send support information and updates to
	  */
	public void setSupportEMail (String SupportEMail)
	{
		set_Value (COLUMNNAME_SupportEMail, SupportEMail);
	}

	/** Get Support EMail.
		@return EMail address to send support information and updates to
	  */
	public String getSupportEMail () 
	{
		return (String)get_Value(COLUMNNAME_SupportEMail);
	}

	/** Set Height.
		@param Talla 
		Height
	  */
	public void setTalla (BigDecimal Talla)
	{
		set_Value (COLUMNNAME_Talla, Talla);
	}

	/** Get Height.
		@return Height
	  */
	public BigDecimal getTalla () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Talla);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cell Phone.
		@param TelCelular 
		Cell Phone
	  */
	public void setTelCelular (String TelCelular)
	{
		set_Value (COLUMNNAME_TelCelular, TelCelular);
	}

	/** Get Cell Phone.
		@return Cell Phone
	  */
	public String getTelCelular () 
	{
		return (String)get_Value(COLUMNNAME_TelCelular);
	}

	/** Set Company's Telephone.
		@param Telefono_Lab_Pac1 
		Company's Telephone
	  */
	public void setTelefono_Lab_Pac1 (String Telefono_Lab_Pac1)
	{
		set_Value (COLUMNNAME_Telefono_Lab_Pac1, Telefono_Lab_Pac1);
	}

	/** Get Company's Telephone.
		@return Company's Telephone
	  */
	public String getTelefono_Lab_Pac1 () 
	{
		return (String)get_Value(COLUMNNAME_Telefono_Lab_Pac1);
	}

	/** Set Company's Telephone 2.
		@param Telefono_Lab_Pac2 
		Company's Telephone 2
	  */
	public void setTelefono_Lab_Pac2 (String Telefono_Lab_Pac2)
	{
		set_Value (COLUMNNAME_Telefono_Lab_Pac2, Telefono_Lab_Pac2);
	}

	/** Get Company's Telephone 2.
		@return Company's Telephone 2
	  */
	public String getTelefono_Lab_Pac2 () 
	{
		return (String)get_Value(COLUMNNAME_Telefono_Lab_Pac2);
	}

	/** Set Company's Telephone 3.
		@param Telefono_Lab_Pac3 
		Company's Telephone 3
	  */
	public void setTelefono_Lab_Pac3 (String Telefono_Lab_Pac3)
	{
		set_Value (COLUMNNAME_Telefono_Lab_Pac3, Telefono_Lab_Pac3);
	}

	/** Get Company's Telephone 3.
		@return Company's Telephone 3
	  */
	public String getTelefono_Lab_Pac3 () 
	{
		return (String)get_Value(COLUMNNAME_Telefono_Lab_Pac3);
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

	/** Set Work Phone.
		@param TelTrabajo 
		Work Phone
	  */
	public void setTelTrabajo (String TelTrabajo)
	{
		set_Value (COLUMNNAME_TelTrabajo, TelTrabajo);
	}

	/** Get Work Phone.
		@return Work Phone
	  */
	public String getTelTrabajo () 
	{
		return (String)get_Value(COLUMNNAME_TelTrabajo);
	}

	/** TipoSangre AD_Reference_ID=1200172 */
	public static final int TIPOSANGRE_AD_Reference_ID=1200172;
	/** AB+ = AB+ */
	public static final String TIPOSANGRE_ABPlus = "AB+";
	/** AB- = AB- */
	public static final String TIPOSANGRE_AB_ = "AB-";
	/** A+ = A+ */
	public static final String TIPOSANGRE_APlus = "A+";
	/** A- = A- */
	public static final String TIPOSANGRE_A_ = "A-";
	/** B+ = B+ */
	public static final String TIPOSANGRE_BPlus = "B+";
	/** B- = B- */
	public static final String TIPOSANGRE_B_ = "B-";
	/** O+ = O+ */
	public static final String TIPOSANGRE_OPlus = "O+";
	/** O- = O- */
	public static final String TIPOSANGRE_O_ = "O-";
	/** Unknown = UK */
	public static final String TIPOSANGRE_Unknown = "UK";
	/** Set Blood Type.
		@param TipoSangre Blood Type	  */
	public void setTipoSangre (String TipoSangre)
	{

		if (TipoSangre == null || TipoSangre.equals("AB+") || TipoSangre.equals("AB-") || TipoSangre.equals("A+") || TipoSangre.equals("A-") || TipoSangre.equals("B+") || TipoSangre.equals("B-") || TipoSangre.equals("O+") || TipoSangre.equals("O-") || TipoSangre.equals("UK")); else throw new IllegalArgumentException ("TipoSangre Invalid value - " + TipoSangre + " - Reference_ID=1200172 - AB+ - AB- - A+ - A- - B+ - B- - O+ - O- - UK");		set_Value (COLUMNNAME_TipoSangre, TipoSangre);
	}

	/** Get Blood Type.
		@return Blood Type	  */
	public String getTipoSangre () 
	{
		return (String)get_Value(COLUMNNAME_TipoSangre);
	}

	/** Title AD_Reference_ID=1200507 */
	public static final int TITLE_AD_Reference_ID=1200507;
	/** MR. = MR. */
	public static final String TITLE_MR = "MR.";
	/** MRS = MRS */
	public static final String TITLE_MRS = "MRS";
	/** MS. = MS. */
	public static final String TITLE_MS = "MS.";
	/** DR. = DR. */
	public static final String TITLE_DR = "DR.";
	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{

		if (Title == null || Title.equals("MR.") || Title.equals("MRS") || Title.equals("MS.") || Title.equals("DR.")); else throw new IllegalArgumentException ("Title Invalid value - " + Title + " - Reference_ID=1200507 - MR. - MRS - MS. - DR.");		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** Set Verified.
		@param Verificado Verified	  */
	public void setVerificado (boolean Verificado)
	{
		set_Value (COLUMNNAME_Verificado, Boolean.valueOf(Verificado));
	}

	/** Get Verified.
		@return Verified	  */
	public boolean isVerificado () 
	{
		Object oo = get_Value(COLUMNNAME_Verificado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Work1ComCode AD_Reference_ID=1200466 */
	public static final int WORK1COMCODE_AD_Reference_ID=1200466;
	/** Primary Residence Number = PRN */
	public static final String WORK1COMCODE_PrimaryResidenceNumber = "PRN";
	/** Other Residence Number = ORN */
	public static final String WORK1COMCODE_OtherResidenceNumber = "ORN";
	/** Work Number = WPN */
	public static final String WORK1COMCODE_WorkNumber = "WPN";
	/** Vacation Home Number = VHN */
	public static final String WORK1COMCODE_VacationHomeNumber = "VHN";
	/** Answering Service Number = ASN */
	public static final String WORK1COMCODE_AnsweringServiceNumber = "ASN";
	/** Emergency Number = EMR */
	public static final String WORK1COMCODE_EmergencyNumber = "EMR";
	/** Email Address = NET */
	public static final String WORK1COMCODE_EmailAddress = "NET";
	/** Beeper Number = BPN */
	public static final String WORK1COMCODE_BeeperNumber = "BPN";
	/** Mobile Phone Number = MPN */
	public static final String WORK1COMCODE_MobilePhoneNumber = "MPN";
	/** Set Telecommunication Code.
		@param Work1ComCode 
		Telecommunication Use Code Work Phone 1
	  */
	public void setWork1ComCode (String Work1ComCode)
	{
		if (Work1ComCode == null) throw new IllegalArgumentException ("Work1ComCode is mandatory");
		if (Work1ComCode.equals("PRN") || Work1ComCode.equals("ORN") || Work1ComCode.equals("WPN") || Work1ComCode.equals("VHN") || Work1ComCode.equals("ASN") || Work1ComCode.equals("EMR") || Work1ComCode.equals("NET") || Work1ComCode.equals("BPN") || Work1ComCode.equals("MPN")); else throw new IllegalArgumentException ("Work1ComCode Invalid value - " + Work1ComCode + " - Reference_ID=1200466 - PRN - ORN - WPN - VHN - ASN - EMR - NET - BPN - MPN");		set_Value (COLUMNNAME_Work1ComCode, Work1ComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Use Code Work Phone 1
	  */
	public String getWork1ComCode () 
	{
		return (String)get_Value(COLUMNNAME_Work1ComCode);
	}

	/** Work2ComCode AD_Reference_ID=1200466 */
	public static final int WORK2COMCODE_AD_Reference_ID=1200466;
	/** Primary Residence Number = PRN */
	public static final String WORK2COMCODE_PrimaryResidenceNumber = "PRN";
	/** Other Residence Number = ORN */
	public static final String WORK2COMCODE_OtherResidenceNumber = "ORN";
	/** Work Number = WPN */
	public static final String WORK2COMCODE_WorkNumber = "WPN";
	/** Vacation Home Number = VHN */
	public static final String WORK2COMCODE_VacationHomeNumber = "VHN";
	/** Answering Service Number = ASN */
	public static final String WORK2COMCODE_AnsweringServiceNumber = "ASN";
	/** Emergency Number = EMR */
	public static final String WORK2COMCODE_EmergencyNumber = "EMR";
	/** Email Address = NET */
	public static final String WORK2COMCODE_EmailAddress = "NET";
	/** Beeper Number = BPN */
	public static final String WORK2COMCODE_BeeperNumber = "BPN";
	/** Mobile Phone Number = MPN */
	public static final String WORK2COMCODE_MobilePhoneNumber = "MPN";
	/** Set Telecommunication Code.
		@param Work2ComCode 
		Telecommunication Code Work Phone 2
	  */
	public void setWork2ComCode (String Work2ComCode)
	{
		if (Work2ComCode == null) throw new IllegalArgumentException ("Work2ComCode is mandatory");
		if (Work2ComCode.equals("PRN") || Work2ComCode.equals("ORN") || Work2ComCode.equals("WPN") || Work2ComCode.equals("VHN") || Work2ComCode.equals("ASN") || Work2ComCode.equals("EMR") || Work2ComCode.equals("NET") || Work2ComCode.equals("BPN") || Work2ComCode.equals("MPN")); else throw new IllegalArgumentException ("Work2ComCode Invalid value - " + Work2ComCode + " - Reference_ID=1200466 - PRN - ORN - WPN - VHN - ASN - EMR - NET - BPN - MPN");		set_Value (COLUMNNAME_Work2ComCode, Work2ComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Code Work Phone 2
	  */
	public String getWork2ComCode () 
	{
		return (String)get_Value(COLUMNNAME_Work2ComCode);
	}

	/** Work3ComCode AD_Reference_ID=1200466 */
	public static final int WORK3COMCODE_AD_Reference_ID=1200466;
	/** Primary Residence Number = PRN */
	public static final String WORK3COMCODE_PrimaryResidenceNumber = "PRN";
	/** Other Residence Number = ORN */
	public static final String WORK3COMCODE_OtherResidenceNumber = "ORN";
	/** Work Number = WPN */
	public static final String WORK3COMCODE_WorkNumber = "WPN";
	/** Vacation Home Number = VHN */
	public static final String WORK3COMCODE_VacationHomeNumber = "VHN";
	/** Answering Service Number = ASN */
	public static final String WORK3COMCODE_AnsweringServiceNumber = "ASN";
	/** Emergency Number = EMR */
	public static final String WORK3COMCODE_EmergencyNumber = "EMR";
	/** Email Address = NET */
	public static final String WORK3COMCODE_EmailAddress = "NET";
	/** Beeper Number = BPN */
	public static final String WORK3COMCODE_BeeperNumber = "BPN";
	/** Mobile Phone Number = MPN */
	public static final String WORK3COMCODE_MobilePhoneNumber = "MPN";
	/** Set Telecommunication Code.
		@param Work3ComCode 
		Telecommunication Code Work Phone 3
	  */
	public void setWork3ComCode (String Work3ComCode)
	{
		if (Work3ComCode == null) throw new IllegalArgumentException ("Work3ComCode is mandatory");
		if (Work3ComCode.equals("PRN") || Work3ComCode.equals("ORN") || Work3ComCode.equals("WPN") || Work3ComCode.equals("VHN") || Work3ComCode.equals("ASN") || Work3ComCode.equals("EMR") || Work3ComCode.equals("NET") || Work3ComCode.equals("BPN") || Work3ComCode.equals("MPN")); else throw new IllegalArgumentException ("Work3ComCode Invalid value - " + Work3ComCode + " - Reference_ID=1200466 - PRN - ORN - WPN - VHN - ASN - EMR - NET - BPN - MPN");		set_Value (COLUMNNAME_Work3ComCode, Work3ComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Code Work Phone 3
	  */
	public String getWork3ComCode () 
	{
		return (String)get_Value(COLUMNNAME_Work3ComCode);
	}
}