/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PacienteRel
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PacienteRel extends PO implements I_EXME_PacienteRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteRel (Properties ctx, int EXME_PacienteRel_ID, String trxName)
    {
      super (ctx, EXME_PacienteRel_ID, trxName);
      /** if (EXME_PacienteRel_ID == 0)
        {
			setEXME_Paciente1_ID (0);
			setEXME_PacienteRel_ID (0);
			setEXME_Parentesco_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteRel[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** DocType AD_Reference_ID=1200679 */
	public static final int DOCTYPE_AD_Reference_ID=1200679;
	/** National_ID = DNI */
	public static final String DOCTYPE_National_ID = "DNI";
	/** Passport = PAS */
	public static final String DOCTYPE_Passport = "PAS";
	/** Set Document Type.
		@param DocType Document Type	  */
	public void setDocType (String DocType)
	{

		if (DocType == null || DocType.equals("DNI") || DocType.equals("PAS")); else throw new IllegalArgumentException ("DocType Invalid value - " + DocType + " - Reference_ID=1200679 - DNI - PAS");		set_Value (COLUMNNAME_DocType, DocType);
	}

	/** Get Document Type.
		@return Document Type	  */
	public String getDocType () 
	{
		return (String)get_Value(COLUMNNAME_DocType);
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

	/** Set Patient.
		@param EXME_Paciente1_ID 
		Patient
	  */
	public void setEXME_Paciente1_ID (int EXME_Paciente1_ID)
	{
		if (EXME_Paciente1_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente1_ID is mandatory.");
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

	/** Set Patient Relations.
		@param EXME_PacienteRel_ID 
		Patient Relations
	  */
	public void setEXME_PacienteRel_ID (int EXME_PacienteRel_ID)
	{
		if (EXME_PacienteRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteRel_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PacienteRel_ID, Integer.valueOf(EXME_PacienteRel_ID));
	}

	/** Get Patient Relations.
		@return Patient Relations
	  */
	public int getEXME_PacienteRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteRel_ID);
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

	/** Set Is a Business Guarantor.
		@param IsBusinessGuarantor 
		Is a Business Guarantor
	  */
	public void setIsBusinessGuarantor (boolean IsBusinessGuarantor)
	{
		set_Value (COLUMNNAME_IsBusinessGuarantor, Boolean.valueOf(IsBusinessGuarantor));
	}

	/** Get Is a Business Guarantor.
		@return Is a Business Guarantor
	  */
	public boolean isBusinessGuarantor () 
	{
		Object oo = get_Value(COLUMNNAME_IsBusinessGuarantor);
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
		set_ValueE (COLUMNNAME_Last_Name, Last_Name);
	}

	/** Get Last_Name.
		@return Last_Name	  */
	public String getLast_Name () 
	{
		return (String)get_ValueE(COLUMNNAME_Last_Name);
	}

	/** Set Lastname2.
		@param Lastname2 Lastname2	  */
	public void setLastname2 (String Lastname2)
	{
		set_Value (COLUMNNAME_Lastname2, Lastname2);
	}

	/** Get Lastname2.
		@return Lastname2	  */
	public String getLastname2 () 
	{
		return (String)get_Value(COLUMNNAME_Lastname2);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_ValueE (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_ValueE(COLUMNNAME_Name);
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

	/** Type AD_Reference_ID=1200476 */
	public static final int TYPE_AD_Reference_ID=1200476;
	/** Responsible = R */
	public static final String TYPE_Responsible = "R";
	/** Emergency = E */
	public static final String TYPE_Emergency = "E";
	/** Policy Holder = I */
	public static final String TYPE_PolicyHolder = "I";
	/** Responsible & Policy Holder = P */
	public static final String TYPE_ResponsiblePolicyHolder = "P";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("R") || Type.equals("E") || Type.equals("I") || Type.equals("P")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200476 - R - E - I - P");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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