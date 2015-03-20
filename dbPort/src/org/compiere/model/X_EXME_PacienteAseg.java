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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PacienteAseg
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PacienteAseg extends PO implements I_EXME_PacienteAseg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteAseg (Properties ctx, int EXME_PacienteAseg_ID, String trxName)
    {
      super (ctx, EXME_PacienteAseg_ID, trxName);
      /** if (EXME_PacienteAseg_ID == 0)
        {
			setC_BPartner_ID (0);
			setEXME_PacienteAseg_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Parentesco_ID (0);
			setIsMain (false);
			setPriority (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteAseg (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteAseg[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
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

	/** Set C_LocationInsurance_ID.
		@param C_LocationInsurance_ID C_LocationInsurance_ID	  */
	public void setC_LocationInsurance_ID (int C_LocationInsurance_ID)
	{
		if (C_LocationInsurance_ID < 1) 
			set_Value (COLUMNNAME_C_LocationInsurance_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationInsurance_ID, Integer.valueOf(C_LocationInsurance_ID));
	}

	/** Get C_LocationInsurance_ID.
		@return C_LocationInsurance_ID	  */
	public int getC_LocationInsurance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationInsurance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Physical Address.
		@param C_LocationPhys_ID Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID)
	{
		if (C_LocationPhys_ID < 1) 
			set_Value (COLUMNNAME_C_LocationPhys_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationPhys_ID, Integer.valueOf(C_LocationPhys_ID));
	}

	/** Get Physical Address.
		@return Physical Address	  */
	public int getC_LocationPhys_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationPhys_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company.
		@param Company Company	  */
	public void setCompany (String Company)
	{
		set_Value (COLUMNNAME_Company, Company);
	}

	/** Get Company.
		@return Company	  */
	public String getCompany () 
	{
		return (String)get_Value(COLUMNNAME_Company);
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

	/** Set Driver License.
		@param DriverLicense 
		Driver License
	  */
	public void setDriverLicense (String DriverLicense)
	{
		set_Value (COLUMNNAME_DriverLicense, DriverLicense);
	}

	/** Get Driver License.
		@return Driver License
	  */
	public String getDriverLicense () 
	{
		return (String)get_Value(COLUMNNAME_DriverLicense);
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Patient's Insurance.
		@param EXME_PacienteAseg_ID Patient's Insurance	  */
	public void setEXME_PacienteAseg_ID (int EXME_PacienteAseg_ID)
	{
		if (EXME_PacienteAseg_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteAseg_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacienteAseg_ID, Integer.valueOf(EXME_PacienteAseg_ID));
	}

	/** Get Patient's Insurance.
		@return Patient's Insurance	  */
	public int getEXME_PacienteAseg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteAseg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Patient.
		@param EXME_Paciente1_ID 
		Patient
	  */
	public void setEXME_Paciente1_ID (int EXME_Paciente1_ID)
	{
		if (EXME_Paciente1_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente1_ID, Integer.valueOf(EXME_Paciente1_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Related Patient.
		@param EXME_Paciente2_ID 
		Related Patient
	  */
	public void setEXME_Paciente2_ID (int EXME_Paciente2_ID)
	{
		if (EXME_Paciente2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente2_ID, Integer.valueOf(EXME_Paciente2_ID));
	}

	/** Get Related Patient.
		@return Related Patient
	  */
	public int getEXME_Paciente2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente2_ID);
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
			 throw new IllegalArgumentException ("EXME_Parentesco_ID is mandatory.");
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

	/** Set Kinship.
		@param EXME_Parentesco2_ID 
		Kinship
	  */
	public void setEXME_Parentesco2_ID (int EXME_Parentesco2_ID)
	{
		if (EXME_Parentesco2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Parentesco2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Parentesco2_ID, Integer.valueOf(EXME_Parentesco2_ID));
	}

	/** Get Kinship.
		@return Kinship
	  */
	public int getEXME_Parentesco2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanAseg getEXME_PlanAseg() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanAseg.Table_Name);
        I_EXME_PlanAseg result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanAseg)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanAseg_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Insurance Plans.
		@param EXME_PlanAseg_ID Insurance Plans	  */
	public void setEXME_PlanAseg_ID (int EXME_PlanAseg_ID)
	{
		if (EXME_PlanAseg_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanAseg_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanAseg_ID, Integer.valueOf(EXME_PlanAseg_ID));
	}

	/** Get Insurance Plans.
		@return Insurance Plans	  */
	public int getEXME_PlanAseg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanAseg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
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

	/** Set Group.
		@param Grupo Group	  */
	public void setGrupo (String Grupo)
	{
		set_Value (COLUMNNAME_Grupo, Grupo);
	}

	/** Get Group.
		@return Group	  */
	public String getGrupo () 
	{
		return (String)get_Value(COLUMNNAME_Grupo);
	}

	/** Set InsuranceName.
		@param InsuranceName InsuranceName	  */
	public void setInsuranceName (String InsuranceName)
	{
		set_Value (COLUMNNAME_InsuranceName, InsuranceName);
	}

	/** Get InsuranceName.
		@return InsuranceName	  */
	public String getInsuranceName () 
	{
		return (String)get_Value(COLUMNNAME_InsuranceName);
	}

	/** Set InsuranceTaxID.
		@param InsuranceTaxID InsuranceTaxID	  */
	public void setInsuranceTaxID (String InsuranceTaxID)
	{
		set_Value (COLUMNNAME_InsuranceTaxID, InsuranceTaxID);
	}

	/** Get InsuranceTaxID.
		@return InsuranceTaxID	  */
	public String getInsuranceTaxID () 
	{
		return (String)get_Value(COLUMNNAME_InsuranceTaxID);
	}

	/** Set Main insurance.
		@param IsMain 
		Is the main insurance
	  */
	public void setIsMain (boolean IsMain)
	{
		set_Value (COLUMNNAME_IsMain, Boolean.valueOf(IsMain));
	}

	/** Get Main insurance.
		@return Is the main insurance
	  */
	public boolean isMain () 
	{
		Object oo = get_Value(COLUMNNAME_IsMain);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is the patient the policy holder.
		@param IsPolicyHolder Is the patient the policy holder	  */
	public void setIsPolicyHolder (boolean IsPolicyHolder)
	{
		set_Value (COLUMNNAME_IsPolicyHolder, Boolean.valueOf(IsPolicyHolder));
	}

	/** Get Is the patient the policy holder.
		@return Is the patient the policy holder	  */
	public boolean isPolicyHolder () 
	{
		Object oo = get_Value(COLUMNNAME_IsPolicyHolder);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Last_Name.
		@param Last_Name Last_Name	  */
	public void setLast_Name (String Last_Name)
	{
		set_Value (COLUMNNAME_Last_Name, Last_Name);
	}

	/** Get Last_Name.
		@return Last_Name	  */
	public String getLast_Name () 
	{
		return (String)get_Value(COLUMNNAME_Last_Name);
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

	/** Set Patient Name.
		@param NamePac Patient Name	  */
	public void setNamePac (String NamePac)
	{
		set_Value (COLUMNNAME_NamePac, NamePac);
	}

	/** Get Patient Name.
		@return Patient Name	  */
	public String getNamePac () 
	{
		return (String)get_Value(COLUMNNAME_NamePac);
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

		if (ParticularComCode == null || ParticularComCode.equals("PRN") || ParticularComCode.equals("ORN") || ParticularComCode.equals("WPN") || ParticularComCode.equals("VHN") || ParticularComCode.equals("ASN") || ParticularComCode.equals("EMR") || ParticularComCode.equals("NET") || ParticularComCode.equals("BPN") || ParticularComCode.equals("MPN")); else throw new IllegalArgumentException ("ParticularComCode Invalid value - " + ParticularComCode + " - Reference_ID=1200466 - PRN - ORN - WPN - VHN - ASN - EMR - NET - BPN - MPN");		set_Value (COLUMNNAME_ParticularComCode, ParticularComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Use Code Phone
	  */
	public String getParticularComCode () 
	{
		return (String)get_Value(COLUMNNAME_ParticularComCode);
	}

	/** Set Main Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Main Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
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

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority)
	{
		set_Value (COLUMNNAME_Priority, Integer.valueOf(Priority));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Priority);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Sex AD_Reference_ID=1000018 */
	public static final int SEX_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String SEX_Female = "F";
	/** Male = M */
	public static final String SEX_Male = "M";
	/** Unassigned = U */
	public static final String SEX_Unassigned = "U";
	/** Ambiguous = A */
	public static final String SEX_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String SEX_NotApplicable = "N";
	/** Other = O */
	public static final String SEX_Other = "O";
	/** Set Sex.
		@param Sex Sex	  */
	public void setSex (String Sex)
	{

		if (Sex == null || Sex.equals("F") || Sex.equals("M") || Sex.equals("U") || Sex.equals("A") || Sex.equals("N") || Sex.equals("O")); else throw new IllegalArgumentException ("Sex Invalid value - " + Sex + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sex, Sex);
	}

	/** Get Sex.
		@return Sex	  */
	public String getSex () 
	{
		return (String)get_Value(COLUMNNAME_Sex);
	}

	/** SupportBilling AD_Reference_ID=1200578 */
	public static final int SUPPORTBILLING_AD_Reference_ID=1200578;
	/** Professional = PR */
	public static final String SUPPORTBILLING_Professional = "PR";
	/** Institucional = IN */
	public static final String SUPPORTBILLING_Institucional = "IN";
	/** Both = BO */
	public static final String SUPPORTBILLING_Both = "BO";
	/** Set Support Billing.
		@param SupportBilling Support Billing	  */
	public void setSupportBilling (String SupportBilling)
	{

		if (SupportBilling == null || SupportBilling.equals("PR") || SupportBilling.equals("IN") || SupportBilling.equals("BO")); else throw new IllegalArgumentException ("SupportBilling Invalid value - " + SupportBilling + " - Reference_ID=1200578 - PR - IN - BO");		set_Value (COLUMNNAME_SupportBilling, SupportBilling);
	}

	/** Get Support Billing.
		@return Support Billing	  */
	public String getSupportBilling () 
	{
		return (String)get_Value(COLUMNNAME_SupportBilling);
	}

	/** Set Work Phone.
		@param TelefonoTrabajo Work Phone	  */
	public void setTelefonoTrabajo (String TelefonoTrabajo)
	{
		set_Value (COLUMNNAME_TelefonoTrabajo, TelefonoTrabajo);
	}

	/** Get Work Phone.
		@return Work Phone	  */
	public String getTelefonoTrabajo () 
	{
		return (String)get_Value(COLUMNNAME_TelefonoTrabajo);
	}

	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (boolean Type)
	{
		set_Value (COLUMNNAME_Type, Boolean.valueOf(Type));
	}

	/** Get Type.
		@return Type of Validation
	  */
	public boolean isType () 
	{
		Object oo = get_Value(COLUMNNAME_Type);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Type2 AD_Reference_ID=1200476 */
	public static final int TYPE2_AD_Reference_ID=1200476;
	/** Responsible = R */
	public static final String TYPE2_Responsible = "R";
	/** Emergency = E */
	public static final String TYPE2_Emergency = "E";
	/** Policy Holder = I */
	public static final String TYPE2_PolicyHolder = "I";
	/** Responsible & Policy Holder = P */
	public static final String TYPE2_ResponsiblePolicyHolder = "P";
	/** Set Type.
		@param Type2 
		Type
	  */
	public void setType2 (String Type2)
	{

		if (Type2 == null || Type2.equals("R") || Type2.equals("E") || Type2.equals("I") || Type2.equals("P")); else throw new IllegalArgumentException ("Type2 Invalid value - " + Type2 + " - Reference_ID=1200476 - R - E - I - P");		set_Value (COLUMNNAME_Type2, Type2);
	}

	/** Get Type.
		@return Type
	  */
	public String getType2 () 
	{
		return (String)get_Value(COLUMNNAME_Type2);
	}
}