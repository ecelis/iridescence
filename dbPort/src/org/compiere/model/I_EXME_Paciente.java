/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Paciente
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Paciente 
{

    /** TableName=EXME_Paciente */
    public static final String Table_Name = "EXME_Paciente";

    /** AD_Table_ID=1000024 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

    /** Load Meta Data */

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

    /** Column name AD_Org_Elig_ID */
    public static final String COLUMNNAME_AD_Org_Elig_ID = "AD_Org_Elig_ID";

	/** Set AD_Org_Elig_ID	  */
	public void setAD_Org_Elig_ID (int AD_Org_Elig_ID);

	/** Get AD_Org_Elig_ID	  */
	public int getAD_Org_Elig_ID();

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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name Antiguedad_Fam */
    public static final String COLUMNNAME_Antiguedad_Fam = "Antiguedad_Fam";

	/** Set Antiquity.
	  * Antiquity of the relative's actual job
	  */
	public void setAntiguedad_Fam (BigDecimal Antiguedad_Fam);

	/** Get Antiquity.
	  * Antiquity of the relative's actual job
	  */
	public BigDecimal getAntiguedad_Fam();

    /** Column name Apellido1 */
    public static final String COLUMNNAME_Apellido1 = "Apellido1";

	/** Set Last Name.
	  * Last Name
	  */
	public void setApellido1 (String Apellido1);

	/** Get Last Name.
	  * Last Name
	  */
	public String getApellido1();

    /** Column name Apellido1_Fam */
    public static final String COLUMNNAME_Apellido1_Fam = "Apellido1_Fam";

	/** Set Last Name.
	  * Relative's Last Name
	  */
	public void setApellido1_Fam (String Apellido1_Fam);

	/** Get Last Name.
	  * Relative's Last Name
	  */
	public String getApellido1_Fam();

    /** Column name Apellido2 */
    public static final String COLUMNNAME_Apellido2 = "Apellido2";

	/** Set Mother's Maiden Name.
	  * Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2);

	/** Get Mother's Maiden Name.
	  * Mother's Maiden Name
	  */
	public String getApellido2();

    /** Column name Apellido2_Fam */
    public static final String COLUMNNAME_Apellido2_Fam = "Apellido2_Fam";

	/** Set Mother's maiden Name.
	  * Mother's maiden Name
	  */
	public void setApellido2_Fam (String Apellido2_Fam);

	/** Get Mother's maiden Name.
	  * Mother's maiden Name
	  */
	public String getApellido2_Fam();

    /** Column name Apellido3 */
    public static final String COLUMNNAME_Apellido3 = "Apellido3";

	/** Set Married Name.
	  * Married Name
	  */
	public void setApellido3 (String Apellido3);

	/** Get Married Name.
	  * Married Name
	  */
	public String getApellido3();

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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID();

    /** Column name C_BPartnerPac_ID */
    public static final String COLUMNNAME_C_BPartnerPac_ID = "C_BPartnerPac_ID";

	/** Set Company of the Patient	  */
	public void setC_BPartnerPac_ID (int C_BPartnerPac_ID);

	/** Get Company of the Patient	  */
	public int getC_BPartnerPac_ID();

    /** Column name C_BPartner_Seg_ID */
    public static final String COLUMNNAME_C_BPartner_Seg_ID = "C_BPartner_Seg_ID";

	/** Set Insurance.
	  * Business Partner Insurance
	  */
	public void setC_BPartner_Seg_ID (int C_BPartner_Seg_ID);

	/** Get Insurance.
	  * Business Partner Insurance
	  */
	public int getC_BPartner_Seg_ID();

    /** Column name C_Country_Nac_ID */
    public static final String COLUMNNAME_C_Country_Nac_ID = "C_Country_Nac_ID";

	/** Set Birth Country.
	  * Birth Country
	  */
	public void setC_Country_Nac_ID (int C_Country_Nac_ID);

	/** Get Birth Country.
	  * Birth Country
	  */
	public int getC_Country_Nac_ID();

    /** Column name C_Country_PersResp_ID */
    public static final String COLUMNNAME_C_Country_PersResp_ID = "C_Country_PersResp_ID";

	/** Set Country of responsible person.
	  * Country of responsible person
	  */
	public void setC_Country_PersResp_ID (int C_Country_PersResp_ID);

	/** Get Country of responsible person.
	  * Country of responsible person
	  */
	public int getC_Country_PersResp_ID();

    /** Column name CiudadPersResp */
    public static final String COLUMNNAME_CiudadPersResp = "CiudadPersResp";

	/** Set City of Responsible person.
	  * City of Responsible person
	  */
	public void setCiudadPersResp (String CiudadPersResp);

	/** Get City of Responsible person.
	  * City of Responsible person
	  */
	public String getCiudadPersResp();

    /** Column name C_LocationFam_ID */
    public static final String COLUMNNAME_C_LocationFam_ID = "C_LocationFam_ID";

	/** Set Relative address	  */
	public void setC_LocationFam_ID (int C_LocationFam_ID);

	/** Get Relative address	  */
	public int getC_LocationFam_ID();

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

    /** Column name C_Location_Mail_ID */
    public static final String COLUMNNAME_C_Location_Mail_ID = "C_Location_Mail_ID";

	/** Set Mailing Address	  */
	public void setC_Location_Mail_ID (int C_Location_Mail_ID);

	/** Get Mailing Address	  */
	public int getC_Location_Mail_ID();

    /** Column name C_Location_Nac_ID */
    public static final String COLUMNNAME_C_Location_Nac_ID = "C_Location_Nac_ID";

	/** Set Birth Place.
	  * Birth Place
	  */
	public void setC_Location_Nac_ID (int C_Location_Nac_ID);

	/** Get Birth Place.
	  * Birth Place
	  */
	public int getC_Location_Nac_ID();

    /** Column name C_LocationPerResp_ID */
    public static final String COLUMNNAME_C_LocationPerResp_ID = "C_LocationPerResp_ID";

	/** Set Address of responsible person.
	  * Address of responsible person
	  */
	public void setC_LocationPerResp_ID (int C_LocationPerResp_ID);

	/** Get Address of responsible person.
	  * Address of responsible person
	  */
	public int getC_LocationPerResp_ID();

    /** Column name C_LocationReg_ID */
    public static final String COLUMNNAME_C_LocationReg_ID = "C_LocationReg_ID";

	/** Set Registered Address	  */
	public void setC_LocationReg_ID (int C_LocationReg_ID);

	/** Get Registered Address	  */
	public int getC_LocationReg_ID();

    /** Column name ColoniaPersResp */
    public static final String COLUMNNAME_ColoniaPersResp = "ColoniaPersResp";

	/** Set Address Colony of Responsible Person.
	  * Address of responsible person
	  */
	public void setColoniaPersResp (String ColoniaPersResp);

	/** Get Address Colony of Responsible Person.
	  * Address of responsible person
	  */
	public String getColoniaPersResp();

    /** Column name copyKin */
    public static final String COLUMNNAME_copyKin = "copyKin";

	/** Set Copy.
	  * Copy Kin Information
	  */
	public void setcopyKin (boolean copyKin);

	/** Get Copy.
	  * Copy Kin Information
	  */
	public boolean iscopyKin();

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

    /** Column name CopyMail */
    public static final String COLUMNNAME_CopyMail = "CopyMail";

	/** Set Copy mailing address.
	  * Copy mailing address
	  */
	public void setCopyMail (boolean CopyMail);

	/** Get Copy mailing address.
	  * Copy mailing address
	  */
	public boolean isCopyMail();

    /** Column name copyResponsible */
    public static final String COLUMNNAME_copyResponsible = "copyResponsible";

	/** Set Copy.
	  * Copy Responsible Information
	  */
	public void setcopyResponsible (boolean copyResponsible);

	/** Get Copy.
	  * Copy Responsible Information
	  */
	public boolean iscopyResponsible();

    /** Column name CpPersResp */
    public static final String COLUMNNAME_CpPersResp = "CpPersResp";

	/** Set Zip code Of Responsible Person.
	  * Postal code of responsible person
	  */
	public void setCpPersResp (String CpPersResp);

	/** Get Zip code Of Responsible Person.
	  * Postal code of responsible person
	  */
	public String getCpPersResp();

    /** Column name CreateBeneficiary */
    public static final String COLUMNNAME_CreateBeneficiary = "CreateBeneficiary";

	/** Set Create Beneficiary	  */
	public void setCreateBeneficiary (String CreateBeneficiary);

	/** Get Create Beneficiary	  */
	public String getCreateBeneficiary();

    /** Column name CreateReport */
    public static final String COLUMNNAME_CreateReport = "CreateReport";

	/** Set View Report.
	  * View Report of patient data
	  */
	public void setCreateReport (String CreateReport);

	/** Get View Report.
	  * View Report of patient data
	  */
	public String getCreateReport();

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

    /** Column name C_Region_Nac_ID */
    public static final String COLUMNNAME_C_Region_Nac_ID = "C_Region_Nac_ID";

	/** Set Birth State.
	  * Birth State
	  */
	public void setC_Region_Nac_ID (int C_Region_Nac_ID);

	/** Get Birth State.
	  * Birth State
	  */
	public int getC_Region_Nac_ID();

    /** Column name C_Region_PersResp_ID */
    public static final String COLUMNNAME_C_Region_PersResp_ID = "C_Region_PersResp_ID";

	/** Set Region of responsible person.
	  * Region of responsible person
	  */
	public void setC_Region_PersResp_ID (int C_Region_PersResp_ID);

	/** Get Region of responsible person.
	  * Region of responsible person
	  */
	public int getC_Region_PersResp_ID();

    /** Column name Curp */
    public static final String COLUMNNAME_Curp = "Curp";

	/** Set National Identification Number.
	  * National Identification Number
	  */
	public void setCurp (String Curp);

	/** Get National Identification Number.
	  * National Identification Number
	  */
	public String getCurp();

    /** Column name Derechohabiente */
    public static final String COLUMNNAME_Derechohabiente = "Derechohabiente";

	/** Set Right Holder.
	  * Right Holder
	  */
	public void setDerechohabiente (boolean Derechohabiente);

	/** Get Right Holder.
	  * Right Holder
	  */
	public boolean isDerechohabiente();

    /** Column name DerechohabienteOtro */
    public static final String COLUMNNAME_DerechohabienteOtro = "DerechohabienteOtro";

	/** Set Right Holder of Other Institution.
	  * Righr holder of other institution
	  */
	public void setDerechohabienteOtro (boolean DerechohabienteOtro);

	/** Get Right Holder of Other Institution.
	  * Righr holder of other institution
	  */
	public boolean isDerechohabienteOtro();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DiagConocido */
    public static final String COLUMNNAME_DiagConocido = "DiagConocido";

	/** Set Recognized Diagnosis.
	  * Recognized Diagnosis
	  */
	public void setDiagConocido (boolean DiagConocido);

	/** Get Recognized Diagnosis.
	  * Recognized Diagnosis
	  */
	public boolean isDiagConocido();

    /** Column name Direccion_Lab_Pac */
    public static final String COLUMNNAME_Direccion_Lab_Pac = "Direccion_Lab_Pac";

	/** Set Company Address.
	  * Company Address
	  */
	public void setDireccion_Lab_Pac (String Direccion_Lab_Pac);

	/** Get Company Address.
	  * Company Address
	  */
	public String getDireccion_Lab_Pac();

    /** Column name DireccionNac */
    public static final String COLUMNNAME_DireccionNac = "DireccionNac";

	/** Set Birth Address	  */
	public void setDireccionNac (String DireccionNac);

	/** Get Birth Address	  */
	public String getDireccionNac();

    /** Column name DirFamiliar */
    public static final String COLUMNNAME_DirFamiliar = "DirFamiliar";

	/** Set Address.
	  * Address of relative
	  */
	public void setDirFamiliar (String DirFamiliar);

	/** Get Address.
	  * Address of relative
	  */
	public String getDirFamiliar();

    /** Column name DirPersResp */
    public static final String COLUMNNAME_DirPersResp = "DirPersResp";

	/** Set Address of Responsible Person.
	  * Address of responsible person
	  */
	public void setDirPersResp (String DirPersResp);

	/** Get Address of Responsible Person.
	  * Address of responsible person
	  */
	public String getDirPersResp();

    /** Column name DirTrabPersResp */
    public static final String COLUMNNAME_DirTrabPersResp = "DirTrabPersResp";

	/** Set Work Address.
	  * Work address of responsible person
	  */
	public void setDirTrabPersResp (String DirTrabPersResp);

	/** Get Work Address.
	  * Work address of responsible person
	  */
	public String getDirTrabPersResp();

    /** Column name DocumentoConvenio */
    public static final String COLUMNNAME_DocumentoConvenio = "DocumentoConvenio";

	/** Set AgreementDocument	  */
	public void setDocumentoConvenio (String DocumentoConvenio);

	/** Get AgreementDocument	  */
	public String getDocumentoConvenio();

    /** Column name DrugEligibilityApplication */
    public static final String COLUMNNAME_DrugEligibilityApplication = "DrugEligibilityApplication";

	/** Set Drug Eligibility Application	  */
	public void setDrugEligibilityApplication (String DrugEligibilityApplication);

	/** Get Drug Eligibility Application	  */
	public String getDrugEligibilityApplication();

    /** Column name Edad */
    public static final String COLUMNNAME_Edad = "Edad";

	/** Set Age.
	  * Age
	  */
	public void setEdad (String Edad);

	/** Get Age.
	  * Age
	  */
	public String getEdad();

    /** Column name EdoCivil */
    public static final String COLUMNNAME_EdoCivil = "EdoCivil";

	/** Set Marital Status.
	  * Marital Status
	  */
	public void setEdoCivil (String EdoCivil);

	/** Get Marital Status.
	  * Marital Status
	  */
	public String getEdoCivil();

    /** Column name EligibilityApplication */
    public static final String COLUMNNAME_EligibilityApplication = "EligibilityApplication";

	/** Set Eligibility Application	  */
	public void setEligibilityApplication (String EligibilityApplication);

	/** Get Eligibility Application	  */
	public String getEligibilityApplication();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name Empresa_Lab_Pac */
    public static final String COLUMNNAME_Empresa_Lab_Pac = "Empresa_Lab_Pac";

	/** Set Company.
	  * Company that employs to the patient
	  */
	public void setEmpresa_Lab_Pac (String Empresa_Lab_Pac);

	/** Get Company.
	  * Company that employs to the patient
	  */
	public String getEmpresa_Lab_Pac();

    /** Column name EsAsegurado */
    public static final String COLUMNNAME_EsAsegurado = "EsAsegurado";

	/** Set Insured.
	  * the patient is insured
	  */
	public void setEsAsegurado (boolean EsAsegurado);

	/** Get Insured.
	  * the patient is insured
	  */
	public boolean isEsAsegurado();

    /** Column name Estatura */
    public static final String COLUMNNAME_Estatura = "Estatura";

	/** Set Height.
	  * Height
	  */
	public void setEstatura (BigDecimal Estatura);

	/** Get Height.
	  * Height
	  */
	public BigDecimal getEstatura();

    /** Column name EsTitular */
    public static final String COLUMNNAME_EsTitular = "EsTitular";

	/** Set Is Title Holder.
	  * The right holder is the title holder
	  */
	public void setEsTitular (boolean EsTitular);

	/** Get Is Title Holder.
	  * The right holder is the title holder
	  */
	public boolean isEsTitular();

    /** Column name EXME_ArchClinico_ID */
    public static final String COLUMNNAME_EXME_ArchClinico_ID = "EXME_ArchClinico_ID";

	/** Set Clinical File.
	  * Clinical File
	  */
	public void setEXME_ArchClinico_ID (int EXME_ArchClinico_ID);

	/** Get Clinical File.
	  * Clinical File
	  */
	public int getEXME_ArchClinico_ID();

    /** Column name EXME_Arma_ID */
    public static final String COLUMNNAME_EXME_Arma_ID = "EXME_Arma_ID";

	/** Set Weapon.
	  * Militar Weapon
	  */
	public void setEXME_Arma_ID (int EXME_Arma_ID);

	/** Get Weapon.
	  * Militar Weapon
	  */
	public int getEXME_Arma_ID();

	public I_EXME_Arma getEXME_Arma() throws RuntimeException;

    /** Column name EXME_Delegacion_Nacimiento_ID */
    public static final String COLUMNNAME_EXME_Delegacion_Nacimiento_ID = "EXME_Delegacion_Nacimiento_ID";

	/** Set Birth Zone	  */
	public void setEXME_Delegacion_Nacimiento_ID (int EXME_Delegacion_Nacimiento_ID);

	/** Get Birth Zone	  */
	public int getEXME_Delegacion_Nacimiento_ID();

    /** Column name EXME_Delegacion_Paciente_ID */
    public static final String COLUMNNAME_EXME_Delegacion_Paciente_ID = "EXME_Delegacion_Paciente_ID";

	/** Set Patient Zone	  */
	public void setEXME_Delegacion_Paciente_ID (int EXME_Delegacion_Paciente_ID);

	/** Get Patient Zone	  */
	public int getEXME_Delegacion_Paciente_ID();

    /** Column name EXME_Diagnostico_Egreso_Descr */
    public static final String COLUMNNAME_EXME_Diagnostico_Egreso_Descr = "EXME_Diagnostico_Egreso_Descr";

	/** Set Discharge Diagnostic Description.
	  * Discharge Diagnostic Description
	  */
	public void setEXME_Diagnostico_Egreso_Descr (String EXME_Diagnostico_Egreso_Descr);

	/** Get Discharge Diagnostic Description.
	  * Discharge Diagnostic Description
	  */
	public String getEXME_Diagnostico_Egreso_Descr();

    /** Column name EXME_Diagnostico_Ingreso_Descr */
    public static final String COLUMNNAME_EXME_Diagnostico_Ingreso_Descr = "EXME_Diagnostico_Ingreso_Descr";

	/** Set Addmission Diagnostic Description.
	  * Addmission Diagnostic Description
	  */
	public void setEXME_Diagnostico_Ingreso_Descr (String EXME_Diagnostico_Ingreso_Descr);

	/** Get Addmission Diagnostic Description.
	  * Addmission Diagnostic Description
	  */
	public String getEXME_Diagnostico_Ingreso_Descr();

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

    /** Column name EXME_Escolaridad_ID */
    public static final String COLUMNNAME_EXME_Escolaridad_ID = "EXME_Escolaridad_ID";

	/** Set Schooling.
	  * Schooling
	  */
	public void setEXME_Escolaridad_ID (int EXME_Escolaridad_ID);

	/** Get Schooling.
	  * Schooling
	  */
	public int getEXME_Escolaridad_ID();

    /** Column name EXME_Especialidad_TS_ID */
    public static final String COLUMNNAME_EXME_Especialidad_TS_ID = "EXME_Especialidad_TS_ID";

	/** Set Social Work Specialty.
	  * Social Work Specialty
	  */
	public void setEXME_Especialidad_TS_ID (int EXME_Especialidad_TS_ID);

	/** Get Social Work Specialty.
	  * Social Work Specialty
	  */
	public int getEXME_Especialidad_TS_ID();

    /** Column name EXME_Expediente_ID */
    public static final String COLUMNNAME_EXME_Expediente_ID = "EXME_Expediente_ID";

	/** Set Clinical Record	  */
	public void setEXME_Expediente_ID (int EXME_Expediente_ID);

	/** Get Clinical Record	  */
	public int getEXME_Expediente_ID();

    /** Column name EXME_GpoEtnico_ID */
    public static final String COLUMNNAME_EXME_GpoEtnico_ID = "EXME_GpoEtnico_ID";

	/** Set Ethnicity.
	  * Ethnicity
	  */
	public void setEXME_GpoEtnico_ID (int EXME_GpoEtnico_ID);

	/** Get Ethnicity.
	  * Ethnicity
	  */
	public int getEXME_GpoEtnico_ID();

	public I_EXME_GpoEtnico getEXME_GpoEtnico() throws RuntimeException;

    /** Column name EXME_Grado_ID */
    public static final String COLUMNNAME_EXME_Grado_ID = "EXME_Grado_ID";

	/** Set Grade.
	  * Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID);

	/** Get Grade.
	  * Militar Grade
	  */
	public int getEXME_Grado_ID();

	public I_EXME_Grado getEXME_Grado() throws RuntimeException;

    /** Column name EXME_Grupo_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Grupo_Especialidad_ID = "EXME_Grupo_Especialidad_ID";

	/** Set Speciality Group.
	  * Militar Speciality Group
	  */
	public void setEXME_Grupo_Especialidad_ID (int EXME_Grupo_Especialidad_ID);

	/** Get Speciality Group.
	  * Militar Speciality Group
	  */
	public int getEXME_Grupo_Especialidad_ID();

	public I_EXME_Grupo_Especialidad getEXME_Grupo_Especialidad() throws RuntimeException;

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Service Facility.
	  * Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Service Facility.
	  * Service Facility
	  */
	public int getEXME_Institucion_ID();

    /** Column name EXME_Institucion_Names */
    public static final String COLUMNNAME_EXME_Institucion_Names = "EXME_Institucion_Names";

	/** Set Institution Name	  */
	public void setEXME_Institucion_Names (String EXME_Institucion_Names);

	/** Get Institution Name	  */
	public String getEXME_Institucion_Names();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Nacionalidad_ID */
    public static final String COLUMNNAME_EXME_Nacionalidad_ID = "EXME_Nacionalidad_ID";

	/** Set Nationality.
	  * Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID);

	/** Get Nationality.
	  * Nationality
	  */
	public int getEXME_Nacionalidad_ID();

    /** Column name EXME_Ocupacion_Fam_ID */
    public static final String COLUMNNAME_EXME_Ocupacion_Fam_ID = "EXME_Ocupacion_Fam_ID";

	/** Set Ocupation.
	  * Relative Ocupation
	  */
	public void setEXME_Ocupacion_Fam_ID (int EXME_Ocupacion_Fam_ID);

	/** Get Ocupation.
	  * Relative Ocupation
	  */
	public int getEXME_Ocupacion_Fam_ID();

    /** Column name EXME_Ocupacion_ID */
    public static final String COLUMNNAME_EXME_Ocupacion_ID = "EXME_Ocupacion_ID";

	/** Set Ocupation.
	  * Ocupation
	  */
	public void setEXME_Ocupacion_ID (int EXME_Ocupacion_ID);

	/** Get Ocupation.
	  * Ocupation
	  */
	public int getEXME_Ocupacion_ID();

    /** Column name EXME_Otra_Inst */
    public static final String COLUMNNAME_EXME_Otra_Inst = "EXME_Otra_Inst";

	/** Set Other Institution.
	  * Other Institution
	  */
	public void setEXME_Otra_Inst (String EXME_Otra_Inst);

	/** Get Other Institution.
	  * Other Institution
	  */
	public String getEXME_Otra_Inst();

    /** Column name EXME_PacienteFam_ID */
    public static final String COLUMNNAME_EXME_PacienteFam_ID = "EXME_PacienteFam_ID";

	/** Set Patient's Relative	  */
	public void setEXME_PacienteFam_ID (int EXME_PacienteFam_ID);

	/** Get Patient's Relative	  */
	public int getEXME_PacienteFam_ID();

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

    /** Column name EXME_Paciente_TS_ID */
    public static final String COLUMNNAME_EXME_Paciente_TS_ID = "EXME_Paciente_TS_ID";

	/** Set Patient (social work).
	  * Social Work's Patient
	  */
	public void setEXME_Paciente_TS_ID (int EXME_Paciente_TS_ID);

	/** Get Patient (social work).
	  * Social Work's Patient
	  */
	public int getEXME_Paciente_TS_ID();

    /** Column name EXME_ParentescoFam_ID */
    public static final String COLUMNNAME_EXME_ParentescoFam_ID = "EXME_ParentescoFam_ID";

	/** Set Relative Kinship	  */
	public void setEXME_ParentescoFam_ID (int EXME_ParentescoFam_ID);

	/** Get Relative Kinship	  */
	public int getEXME_ParentescoFam_ID();

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

    /** Column name EXME_PatientClass_ID */
    public static final String COLUMNNAME_EXME_PatientClass_ID = "EXME_PatientClass_ID";

	/** Set Patient Classification	  */
	public void setEXME_PatientClass_ID (int EXME_PatientClass_ID);

	/** Get Patient Classification	  */
	public int getEXME_PatientClass_ID();

	public I_EXME_PatientClass getEXME_PatientClass() throws RuntimeException;

    /** Column name EXME_Razas_ID */
    public static final String COLUMNNAME_EXME_Razas_ID = "EXME_Razas_ID";

	/** Set Race.
	  * Races
	  */
	public void setEXME_Razas_ID (int EXME_Razas_ID);

	/** Get Race.
	  * Races
	  */
	public int getEXME_Razas_ID();

	public I_EXME_Razas getEXME_Razas() throws RuntimeException;

    /** Column name EXME_Referencia_ID */
    public static final String COLUMNNAME_EXME_Referencia_ID = "EXME_Referencia_ID";

	/** Set Patient Reference.
	  * Reference to which the patient belongs.
	  */
	public void setEXME_Referencia_ID (int EXME_Referencia_ID);

	/** Get Patient Reference.
	  * Reference to which the patient belongs.
	  */
	public int getEXME_Referencia_ID();

    /** Column name EXME_Referencia_Int_ID */
    public static final String COLUMNNAME_EXME_Referencia_Int_ID = "EXME_Referencia_Int_ID";

	/** Set Internal Reference.
	  * Patient's Internal Reference
	  */
	public void setEXME_Referencia_Int_ID (int EXME_Referencia_Int_ID);

	/** Get Internal Reference.
	  * Patient's Internal Reference
	  */
	public int getEXME_Referencia_Int_ID();

    /** Column name EXME_Refer_ID */
    public static final String COLUMNNAME_EXME_Refer_ID = "EXME_Refer_ID";

	/** Set Patient's External Admission	  */
	public void setEXME_Refer_ID (int EXME_Refer_ID);

	/** Get Patient's External Admission	  */
	public int getEXME_Refer_ID();

	public I_EXME_Refer getEXME_Refer() throws RuntimeException;

    /** Column name EXME_Religion_ID */
    public static final String COLUMNNAME_EXME_Religion_ID = "EXME_Religion_ID";

	/** Set Religion.
	  * Religion
	  */
	public void setEXME_Religion_ID (int EXME_Religion_ID);

	/** Get Religion.
	  * Religion
	  */
	public int getEXME_Religion_ID();

    /** Column name EXME_RFC_ID */
    public static final String COLUMNNAME_EXME_RFC_ID = "EXME_RFC_ID";

	/** Set RFC	  */
	public void setEXME_RFC_ID (int EXME_RFC_ID);

	/** Get RFC	  */
	public int getEXME_RFC_ID();

	public I_EXME_RFC getEXME_RFC() throws RuntimeException;

    /** Column name EXME_Suffix_ID */
    public static final String COLUMNNAME_EXME_Suffix_ID = "EXME_Suffix_ID";

	/** Set Suffix.
	  * Suffix
	  */
	public void setEXME_Suffix_ID (int EXME_Suffix_ID);

	/** Get Suffix.
	  * Suffix
	  */
	public int getEXME_Suffix_ID();

	public I_EXME_Suffix getEXME_Suffix() throws RuntimeException;

    /** Column name EXME_Unidad_ID */
    public static final String COLUMNNAME_EXME_Unidad_ID = "EXME_Unidad_ID";

	/** Set Unit.
	  * Militar Unit
	  */
	public void setEXME_Unidad_ID (int EXME_Unidad_ID);

	/** Get Unit.
	  * Militar Unit
	  */
	public int getEXME_Unidad_ID();

	public I_EXME_Unidad getEXME_Unidad() throws RuntimeException;

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Clinical Record.
	  * Clinical Record
	  */
	public void setExpediente (String Expediente);

	/** Get Clinical Record.
	  * Clinical Record
	  */
	public String getExpediente();

    /** Column name FamiliarComCode */
    public static final String COLUMNNAME_FamiliarComCode = "FamiliarComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Code Use Familiar Phone
	  */
	public void setFamiliarComCode (String FamiliarComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Code Use Familiar Phone
	  */
	public String getFamiliarComCode();

    /** Column name FechaBaja */
    public static final String COLUMNNAME_FechaBaja = "FechaBaja";

	/** Set Drop Date.
	  * Drop Date
	  */
	public void setFechaBaja (Timestamp FechaBaja);

	/** Get Drop Date.
	  * Drop Date
	  */
	public Timestamp getFechaBaja();

    /** Column name FechaFin_Seg */
    public static final String COLUMNNAME_FechaFin_Seg = "FechaFin_Seg";

	/** Set Final Date.
	  * Insurance's Final Date
	  */
	public void setFechaFin_Seg (Timestamp FechaFin_Seg);

	/** Get Final Date.
	  * Insurance's Final Date
	  */
	public Timestamp getFechaFin_Seg();

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

    /** Column name FechaIni_Seg */
    public static final String COLUMNNAME_FechaIni_Seg = "FechaIni_Seg";

	/** Set Initial Date.
	  * Insurance's Initial Date
	  */
	public void setFechaIni_Seg (Timestamp FechaIni_Seg);

	/** Get Initial Date.
	  * Insurance's Initial Date
	  */
	public Timestamp getFechaIni_Seg();

    /** Column name Fecha_Muerte */
    public static final String COLUMNNAME_Fecha_Muerte = "Fecha_Muerte";

	/** Set Date of Death.
	  * Date of Death
	  */
	public void setFecha_Muerte (Timestamp Fecha_Muerte);

	/** Get Date of Death.
	  * Date of Death
	  */
	public Timestamp getFecha_Muerte();

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

    /** Column name FechaRegistro */
    public static final String COLUMNNAME_FechaRegistro = "FechaRegistro";

	/** Set Registration Date.
	  * Registration Date
	  */
	public void setFechaRegistro (Timestamp FechaRegistro);

	/** Get Registration Date.
	  * Registration Date
	  */
	public Timestamp getFechaRegistro();

    /** Column name FechaVencimiento */
    public static final String COLUMNNAME_FechaVencimiento = "FechaVencimiento";

	/** Set Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento);

	/** Get Termination Date	  */
	public Timestamp getFechaVencimiento();

    /** Column name FechaVigencia */
    public static final String COLUMNNAME_FechaVigencia = "FechaVigencia";

	/** Set Vali Date.
	  * Valid Date
	  */
	public void setFechaVigencia (Timestamp FechaVigencia);

	/** Get Vali Date.
	  * Valid Date
	  */
	public Timestamp getFechaVigencia();

    /** Column name HoraNac */
    public static final String COLUMNNAME_HoraNac = "HoraNac";

	/** Set Birth Hour.
	  * Birth Hour
	  */
	public void setHoraNac (String HoraNac);

	/** Get Birth Hour.
	  * Birth Hour
	  */
	public String getHoraNac();

    /** Column name Imagen */
    public static final String COLUMNNAME_Imagen = "Imagen";

	/** Set Image.
	  * Name of stored image
	  */
	public void setImagen (String Imagen);

	/** Get Image.
	  * Name of stored image
	  */
	public String getImagen();

    /** Column name Imss */
    public static final String COLUMNNAME_Imss = "Imss";

	/** Set Social Security Number.
	  * Social Security Number
	  */
	public void setImss (String Imss);

	/** Get Social Security Number.
	  * Social Security Number
	  */
	public String getImss();

    /** Column name IsChangeLog */
    public static final String COLUMNNAME_IsChangeLog = "IsChangeLog";

	/** Set Maintain Change Log.
	  * Maintain a log of changes
	  */
	public void setIsChangeLog (String IsChangeLog);

	/** Get Maintain Change Log.
	  * Maintain a log of changes
	  */
	public String getIsChangeLog();

    /** Column name IsDonador */
    public static final String COLUMNNAME_IsDonador = "IsDonador";

	/** Set Is Donor	  */
	public void setIsDonador (boolean IsDonador);

	/** Get Is Donor	  */
	public boolean isDonador();

    /** Column name IsFactEspec */
    public static final String COLUMNNAME_IsFactEspec = "IsFactEspec";

	/** Set Billing multiple	  */
	public void setIsFactEspec (boolean IsFactEspec);

	/** Get Billing multiple	  */
	public boolean isFactEspec();

    /** Column name IsNSS */
    public static final String COLUMNNAME_IsNSS = "IsNSS";

	/** Set IsNSS	  */
	public void setIsNSS (boolean IsNSS);

	/** Get IsNSS	  */
	public boolean isNSS();

    /** Column name IsPension */
    public static final String COLUMNNAME_IsPension = "IsPension";

	/** Set Pension	  */
	public void setIsPension (boolean IsPension);

	/** Get Pension	  */
	public boolean isPension();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name IsReceptor */
    public static final String COLUMNNAME_IsReceptor = "IsReceptor";

	/** Set Is Receiver	  */
	public void setIsReceptor (boolean IsReceptor);

	/** Get Is Receiver	  */
	public boolean isReceptor();

    /** Column name IsRefer */
    public static final String COLUMNNAME_IsRefer = "IsRefer";

	/** Set Is External Patient	  */
	public void setIsRefer (boolean IsRefer);

	/** Get Is External Patient	  */
	public boolean isRefer();

    /** Column name LimCredDerechoh */
    public static final String COLUMNNAME_LimCredDerechoh = "LimCredDerechoh";

	/** Set Credit Limit Of Claimholders	  */
	public void setLimCredDerechoh (BigDecimal LimCredDerechoh);

	/** Get Credit Limit Of Claimholders	  */
	public BigDecimal getLimCredDerechoh();

    /** Column name LimiteCredito */
    public static final String COLUMNNAME_LimiteCredito = "LimiteCredito";

	/** Set Credit Limit.
	  * Credit Limit
	  */
	public void setLimiteCredito (BigDecimal LimiteCredito);

	/** Get Credit Limit.
	  * Credit Limit
	  */
	public BigDecimal getLimiteCredito();

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

    /** Column name NoAlergiasMed */
    public static final String COLUMNNAME_NoAlergiasMed = "NoAlergiasMed";

	/** Set No Known Drug Allergies.
	  * No Known Drug Allergies
	  */
	public void setNoAlergiasMed (boolean NoAlergiasMed);

	/** Get No Known Drug Allergies.
	  * No Known Drug Allergies
	  */
	public boolean isNoAlergiasMed();

    /** Column name NoAutorizacion */
    public static final String COLUMNNAME_NoAutorizacion = "NoAutorizacion";

	/** Set Authorizarion No..
	  * Authorization Number
	  */
	public void setNoAutorizacion (String NoAutorizacion);

	/** Get Authorizarion No..
	  * Authorization Number
	  */
	public String getNoAutorizacion();

    /** Column name NoFamiliar */
    public static final String COLUMNNAME_NoFamiliar = "NoFamiliar";

	/** Set Relative's Number.
	  * Relative's Number
	  */
	public void setNoFamiliar (String NoFamiliar);

	/** Get Relative's Number.
	  * Relative's Number
	  */
	public String getNoFamiliar();

    /** Column name Nombre2 */
    public static final String COLUMNNAME_Nombre2 = "Nombre2";

	/** Set Middle Name.
	  * Middle name
	  */
	public void setNombre2 (String Nombre2);

	/** Get Middle Name.
	  * Middle name
	  */
	public String getNombre2();

    /** Column name Nombre_Fam */
    public static final String COLUMNNAME_Nombre_Fam = "Nombre_Fam";

	/** Set Name.
	  * Relative Name
	  */
	public void setNombre_Fam (String Nombre_Fam);

	/** Get Name.
	  * Relative Name
	  */
	public String getNombre_Fam();

    /** Column name NombreFamiliar */
    public static final String COLUMNNAME_NombreFamiliar = "NombreFamiliar";

	/** Set Name.
	  * Relative Name
	  */
	public void setNombreFamiliar (String NombreFamiliar);

	/** Get Name.
	  * Relative Name
	  */
	public String getNombreFamiliar();

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

    /** Column name Nomina */
    public static final String COLUMNNAME_Nomina = "Nomina";

	/** Set Payroll.
	  * Payroll
	  */
	public void setNomina (String Nomina);

	/** Get Payroll.
	  * Payroll
	  */
	public String getNomina();

    /** Column name NoSiniestro */
    public static final String COLUMNNAME_NoSiniestro = "NoSiniestro";

	/** Set Sinister No..
	  * Sinister Number
	  */
	public void setNoSiniestro (String NoSiniestro);

	/** Get Sinister No..
	  * Sinister Number
	  */
	public String getNoSiniestro();

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

    /** Column name Particular */
    public static final String COLUMNNAME_Particular = "Particular";

	/** Set Private.
	  * Private
	  */
	public void setParticular (boolean Particular);

	/** Get Private.
	  * Private
	  */
	public boolean isParticular();

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

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (BigDecimal Peso);

	/** Get Weight.
	  * Weight
	  */
	public BigDecimal getPeso();

    /** Column name PoblacionAbierta */
    public static final String COLUMNNAME_PoblacionAbierta = "PoblacionAbierta";

	/** Set Open Population.
	  * Open Population
	  */
	public void setPoblacionAbierta (boolean PoblacionAbierta);

	/** Get Open Population.
	  * Open Population
	  */
	public boolean isPoblacionAbierta();

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

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (int Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public int getPostal();

    /** Column name PrescActivas */
    public static final String COLUMNNAME_PrescActivas = "PrescActivas";

	/** Set Active medication.
	  * Active medication
	  */
	public void setPrescActivas (boolean PrescActivas);

	/** Get Active medication.
	  * Active medication
	  */
	public boolean isPrescActivas();

    /** Column name Puesto_Fam */
    public static final String COLUMNNAME_Puesto_Fam = "Puesto_Fam";

	/** Set Job.
	  * Relative Job
	  */
	public void setPuesto_Fam (String Puesto_Fam);

	/** Get Job.
	  * Relative Job
	  */
	public String getPuesto_Fam();

    /** Column name Puesto_Lab_Pac */
    public static final String COLUMNNAME_Puesto_Lab_Pac = "Puesto_Lab_Pac";

	/** Set Employment.
	  * Patient's Employment
	  */
	public void setPuesto_Lab_Pac (String Puesto_Lab_Pac);

	/** Get Employment.
	  * Patient's Employment
	  */
	public String getPuesto_Lab_Pac();

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

    /** Column name RFC_Fam */
    public static final String COLUMNNAME_RFC_Fam = "RFC_Fam";

	/** Set Relative's RFC.
	  * Relative's RFC
	  */
	public void setRFC_Fam (String RFC_Fam);

	/** Get Relative's RFC.
	  * Relative's RFC
	  */
	public String getRFC_Fam();

    /** Column name RFC_Resp */
    public static final String COLUMNNAME_RFC_Resp = "RFC_Resp";

	/** Set RFC.
	  * Responsible's Federal Register Taxpayer
	  */
	public void setRFC_Resp (String RFC_Resp);

	/** Get RFC.
	  * Responsible's Federal Register Taxpayer
	  */
	public String getRFC_Resp();

    /** Column name SeguroPopular */
    public static final String COLUMNNAME_SeguroPopular = "SeguroPopular";

	/** Set Medicaid.
	  * Medicaid number
	  */
	public void setSeguroPopular (String SeguroPopular);

	/** Get Medicaid.
	  * Medicaid number
	  */
	public String getSeguroPopular();

    /** Column name sendInformation */
    public static final String COLUMNNAME_sendInformation = "sendInformation";

	/** Set Send/Retrieve Information.
	  * Send/Retrieve Information
	  */
	public void setsendInformation (String sendInformation);

	/** Get Send/Retrieve Information.
	  * Send/Retrieve Information
	  */
	public String getsendInformation();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (String Sexo);

	/** Get Sex.
	  * Sex
	  */
	public String getSexo();

    /** Column name SuffixNSS */
    public static final String COLUMNNAME_SuffixNSS = "SuffixNSS";

	/** Set SSN	  */
	public void setSuffixNSS (String SuffixNSS);

	/** Get SSN	  */
	public String getSuffixNSS();

    /** Column name SupportEMail */
    public static final String COLUMNNAME_SupportEMail = "SupportEMail";

	/** Set Support EMail.
	  * EMail address to send support information and updates to
	  */
	public void setSupportEMail (String SupportEMail);

	/** Get Support EMail.
	  * EMail address to send support information and updates to
	  */
	public String getSupportEMail();

    /** Column name Talla */
    public static final String COLUMNNAME_Talla = "Talla";

	/** Set Height.
	  * Height
	  */
	public void setTalla (BigDecimal Talla);

	/** Get Height.
	  * Height
	  */
	public BigDecimal getTalla();

    /** Column name TelCelular */
    public static final String COLUMNNAME_TelCelular = "TelCelular";

	/** Set Cell Phone.
	  * Cell Phone
	  */
	public void setTelCelular (String TelCelular);

	/** Get Cell Phone.
	  * Cell Phone
	  */
	public String getTelCelular();

    /** Column name Telefono_Lab_Pac1 */
    public static final String COLUMNNAME_Telefono_Lab_Pac1 = "Telefono_Lab_Pac1";

	/** Set Company's Telephone.
	  * Company's Telephone
	  */
	public void setTelefono_Lab_Pac1 (String Telefono_Lab_Pac1);

	/** Get Company's Telephone.
	  * Company's Telephone
	  */
	public String getTelefono_Lab_Pac1();

    /** Column name Telefono_Lab_Pac2 */
    public static final String COLUMNNAME_Telefono_Lab_Pac2 = "Telefono_Lab_Pac2";

	/** Set Company's Telephone 2.
	  * Company's Telephone 2
	  */
	public void setTelefono_Lab_Pac2 (String Telefono_Lab_Pac2);

	/** Get Company's Telephone 2.
	  * Company's Telephone 2
	  */
	public String getTelefono_Lab_Pac2();

    /** Column name Telefono_Lab_Pac3 */
    public static final String COLUMNNAME_Telefono_Lab_Pac3 = "Telefono_Lab_Pac3";

	/** Set Company's Telephone 3.
	  * Company's Telephone 3
	  */
	public void setTelefono_Lab_Pac3 (String Telefono_Lab_Pac3);

	/** Get Company's Telephone 3.
	  * Company's Telephone 3
	  */
	public String getTelefono_Lab_Pac3();

    /** Column name TelFamiliar */
    public static final String COLUMNNAME_TelFamiliar = "TelFamiliar";

	/** Set Relative's Phone.
	  * Relative's Phone
	  */
	public void setTelFamiliar (String TelFamiliar);

	/** Get Relative's Phone.
	  * Relative's Phone
	  */
	public String getTelFamiliar();

    /** Column name TelParticular */
    public static final String COLUMNNAME_TelParticular = "TelParticular";

	/** Set Home Phone.
	  * Home Phone
	  */
	public void setTelParticular (String TelParticular);

	/** Get Home Phone.
	  * Home Phone
	  */
	public String getTelParticular();

    /** Column name TelParticular_Fam */
    public static final String COLUMNNAME_TelParticular_Fam = "TelParticular_Fam";

	/** Set Relative's home phone.
	  * Relative's home phone
	  */
	public void setTelParticular_Fam (String TelParticular_Fam);

	/** Get Relative's home phone.
	  * Relative's home phone
	  */
	public String getTelParticular_Fam();

    /** Column name TelTrabajo */
    public static final String COLUMNNAME_TelTrabajo = "TelTrabajo";

	/** Set Work Phone.
	  * Work Phone
	  */
	public void setTelTrabajo (String TelTrabajo);

	/** Get Work Phone.
	  * Work Phone
	  */
	public String getTelTrabajo();

    /** Column name TipoSangre */
    public static final String COLUMNNAME_TipoSangre = "TipoSangre";

	/** Set Blood Type	  */
	public void setTipoSangre (String TipoSangre);

	/** Get Blood Type	  */
	public String getTipoSangre();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

    /** Column name Titular */
    public static final String COLUMNNAME_Titular = "Titular";

	/** Set Title Holder.
	  * Title Holder
	  */
	public void setTitular (String Titular);

	/** Get Title Holder.
	  * Title Holder
	  */
	public String getTitular();

    /** Column name Titular_ID */
    public static final String COLUMNNAME_Titular_ID = "Titular_ID";

	/** Set Title Holder.
	  * Title Holder
	  */
	public void setTitular_ID (int Titular_ID);

	/** Get Title Holder.
	  * Title Holder
	  */
	public int getTitular_ID();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name Verificado */
    public static final String COLUMNNAME_Verificado = "Verificado";

	/** Set Verified	  */
	public void setVerificado (boolean Verificado);

	/** Get Verified	  */
	public boolean isVerificado();

    /** Column name Work1ComCode */
    public static final String COLUMNNAME_Work1ComCode = "Work1ComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Use Code Work Phone 1
	  */
	public void setWork1ComCode (String Work1ComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Use Code Work Phone 1
	  */
	public String getWork1ComCode();

    /** Column name Work2ComCode */
    public static final String COLUMNNAME_Work2ComCode = "Work2ComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Code Work Phone 2
	  */
	public void setWork2ComCode (String Work2ComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Code Work Phone 2
	  */
	public String getWork2ComCode();

    /** Column name Work3ComCode */
    public static final String COLUMNNAME_Work3ComCode = "Work3ComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Code Work Phone 3
	  */
	public void setWork3ComCode (String Work3ComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Code Work Phone 3
	  */
	public String getWork3ComCode();
}
