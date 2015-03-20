/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteAseg
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PacienteAseg 
{

    /** TableName=EXME_PacienteAseg */
    public static final String Table_Name = "EXME_PacienteAseg";

    /** AD_Table_ID=1201051 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Celular */
    public static final String COLUMNNAME_Celular = "Celular";

	/** Set Cellular Phone.
	  * Cellular Phone
	  */
	public void setCelular (String Celular);

	/** Get Cellular Phone.
	  * Cellular Phone
	  */
	public String getCelular();

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name C_LocationInsurance_ID */
    public static final String COLUMNNAME_C_LocationInsurance_ID = "C_LocationInsurance_ID";

	/** Set C_LocationInsurance_ID	  */
	public void setC_LocationInsurance_ID (int C_LocationInsurance_ID);

	/** Get C_LocationInsurance_ID	  */
	public int getC_LocationInsurance_ID();

    /** Column name C_LocationPhys_ID */
    public static final String COLUMNNAME_C_LocationPhys_ID = "C_LocationPhys_ID";

	/** Set Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID);

	/** Get Physical Address	  */
	public int getC_LocationPhys_ID();

    /** Column name Company */
    public static final String COLUMNNAME_Company = "Company";

	/** Set Company	  */
	public void setCompany (String Company);

	/** Get Company	  */
	public String getCompany();

    /** Column name CopyLocation */
    public static final String COLUMNNAME_CopyLocation = "CopyLocation";

	/** Set Copy Location.
	  * Copy the location
	  */
	public void setCopyLocation (boolean CopyLocation);

	/** Get Copy Location.
	  * Copy the location
	  */
	public boolean isCopyLocation();

    /** Column name C_RegionDriverLic_ID */
    public static final String COLUMNNAME_C_RegionDriverLic_ID = "C_RegionDriverLic_ID";

	/** Set Driver License Region.
	  * Driver License Region
	  */
	public void setC_RegionDriverLic_ID (int C_RegionDriverLic_ID);

	/** Get Driver License Region.
	  * Driver License Region
	  */
	public int getC_RegionDriverLic_ID();

    /** Column name DriverLicense */
    public static final String COLUMNNAME_DriverLicense = "DriverLicense";

	/** Set Driver License.
	  * Driver License
	  */
	public void setDriverLicense (String DriverLicense);

	/** Get Driver License.
	  * Driver License
	  */
	public String getDriverLicense();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Employment_ID */
    public static final String COLUMNNAME_EXME_Employment_ID = "EXME_Employment_ID";

	/** Set Employment Information.
	  * Employment Information
	  */
	public void setEXME_Employment_ID (int EXME_Employment_ID);

	/** Get Employment Information.
	  * Employment Information
	  */
	public int getEXME_Employment_ID();

    /** Column name EXME_PacienteAseg_ID */
    public static final String COLUMNNAME_EXME_PacienteAseg_ID = "EXME_PacienteAseg_ID";

	/** Set Patient's Insurance	  */
	public void setEXME_PacienteAseg_ID (int EXME_PacienteAseg_ID);

	/** Get Patient's Insurance	  */
	public int getEXME_PacienteAseg_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name EXME_Paciente1_ID */
    public static final String COLUMNNAME_EXME_Paciente1_ID = "EXME_Paciente1_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente1_ID (int EXME_Paciente1_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente1_ID();

    /** Column name EXME_Paciente2_ID */
    public static final String COLUMNNAME_EXME_Paciente2_ID = "EXME_Paciente2_ID";

	/** Set Related Patient.
	  * Related Patient
	  */
	public void setEXME_Paciente2_ID (int EXME_Paciente2_ID);

	/** Get Related Patient.
	  * Related Patient
	  */
	public int getEXME_Paciente2_ID();

    /** Column name EXME_Parentesco_ID */
    public static final String COLUMNNAME_EXME_Parentesco_ID = "EXME_Parentesco_ID";

	/** Set Kinship.
	  * Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID);

	/** Get Kinship.
	  * Kinship
	  */
	public int getEXME_Parentesco_ID();

    /** Column name EXME_Parentesco2_ID */
    public static final String COLUMNNAME_EXME_Parentesco2_ID = "EXME_Parentesco2_ID";

	/** Set Kinship.
	  * Kinship
	  */
	public void setEXME_Parentesco2_ID (int EXME_Parentesco2_ID);

	/** Get Kinship.
	  * Kinship
	  */
	public int getEXME_Parentesco2_ID();

    /** Column name EXME_PlanAseg_ID */
    public static final String COLUMNNAME_EXME_PlanAseg_ID = "EXME_PlanAseg_ID";

	/** Set Insurance Plans	  */
	public void setEXME_PlanAseg_ID (int EXME_PlanAseg_ID);

	/** Get Insurance Plans	  */
	public int getEXME_PlanAseg_ID();

	public I_EXME_PlanAseg getEXME_PlanAseg() throws RuntimeException;

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name FechaNac */
    public static final String COLUMNNAME_FechaNac = "FechaNac";

	/** Set Birth Date.
	  * Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac);

	/** Get Birth Date.
	  * Birth Date
	  */
	public Timestamp getFechaNac();

    /** Column name Grupo */
    public static final String COLUMNNAME_Grupo = "Grupo";

	/** Set Group	  */
	public void setGrupo (String Grupo);

	/** Get Group	  */
	public String getGrupo();

    /** Column name InsuranceName */
    public static final String COLUMNNAME_InsuranceName = "InsuranceName";

	/** Set InsuranceName	  */
	public void setInsuranceName (String InsuranceName);

	/** Get InsuranceName	  */
	public String getInsuranceName();

    /** Column name InsuranceTaxID */
    public static final String COLUMNNAME_InsuranceTaxID = "InsuranceTaxID";

	/** Set InsuranceTaxID	  */
	public void setInsuranceTaxID (String InsuranceTaxID);

	/** Get InsuranceTaxID	  */
	public String getInsuranceTaxID();

    /** Column name IsMain */
    public static final String COLUMNNAME_IsMain = "IsMain";

	/** Set Main insurance.
	  * Is the main insurance
	  */
	public void setIsMain (boolean IsMain);

	/** Get Main insurance.
	  * Is the main insurance
	  */
	public boolean isMain();

    /** Column name IsPolicyHolder */
    public static final String COLUMNNAME_IsPolicyHolder = "IsPolicyHolder";

	/** Set Is the patient the policy holder	  */
	public void setIsPolicyHolder (boolean IsPolicyHolder);

	/** Get Is the patient the policy holder	  */
	public boolean isPolicyHolder();

    /** Column name Last_Name */
    public static final String COLUMNNAME_Last_Name = "Last_Name";

	/** Set Last_Name	  */
	public void setLast_Name (String Last_Name);

	/** Get Last_Name	  */
	public String getLast_Name();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NamePac */
    public static final String COLUMNNAME_NamePac = "NamePac";

	/** Set Patient Name	  */
	public void setNamePac (String NamePac);

	/** Get Patient Name	  */
	public String getNamePac();

    /** Column name ParticularComCode */
    public static final String COLUMNNAME_ParticularComCode = "ParticularComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Use Code Phone
	  */
	public void setParticularComCode (String ParticularComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Use Code Phone
	  */
	public String getParticularComCode();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Main Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Main Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Poliza */
    public static final String COLUMNNAME_Poliza = "Poliza";

	/** Set Insurance Policy.
	  * Insurance Policy
	  */
	public void setPoliza (String Poliza);

	/** Get Insurance Policy.
	  * Insurance Policy
	  */
	public String getPoliza();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority();

    /** Column name Rfc */
    public static final String COLUMNNAME_Rfc = "Rfc";

	/** Set Taxpayer Identification Number.
	  * Taxpayer Identification Number
	  */
	public void setRfc (String Rfc);

	/** Get Taxpayer Identification Number.
	  * Taxpayer Identification Number
	  */
	public String getRfc();

    /** Column name Sex */
    public static final String COLUMNNAME_Sex = "Sex";

	/** Set Sex	  */
	public void setSex (String Sex);

	/** Get Sex	  */
	public String getSex();

    /** Column name SupportBilling */
    public static final String COLUMNNAME_SupportBilling = "SupportBilling";

	/** Set Support Billing	  */
	public void setSupportBilling (String SupportBilling);

	/** Get Support Billing	  */
	public String getSupportBilling();

    /** Column name TelefonoTrabajo */
    public static final String COLUMNNAME_TelefonoTrabajo = "TelefonoTrabajo";

	/** Set Work Phone	  */
	public void setTelefonoTrabajo (String TelefonoTrabajo);

	/** Get Work Phone	  */
	public String getTelefonoTrabajo();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (boolean Type);

	/** Get Type.
	  * Type of Validation
	  */
	public boolean isType();

    /** Column name Type2 */
    public static final String COLUMNNAME_Type2 = "Type2";

	/** Set Type.
	  * Type
	  */
	public void setType2 (String Type2);

	/** Get Type.
	  * Type
	  */
	public String getType2();
}
