/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Paciente
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_I_EXME_Paciente 
{

    /** TableName=I_EXME_Paciente */
    public static final String Table_Name = "I_EXME_Paciente";

    /** AD_Table_ID=1200066 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Adress1Reg */
    public static final String COLUMNNAME_Adress1Reg = "Adress1Reg";

	/** Set Address 1 of Registration.
	  * Address 1 of Registrarion
	  */
	public void setAdress1Reg (String Adress1Reg);

	/** Get Address 1 of Registration.
	  * Address 1 of Registrarion
	  */
	public String getAdress1Reg();

    /** Column name Adress2Reg */
    public static final String COLUMNNAME_Adress2Reg = "Adress2Reg";

	/** Set Address 2 of Registration.
	  * Address 2 of Registration
	  */
	public void setAdress2Reg (String Adress2Reg);

	/** Get Address 2 of Registration.
	  * Address 2 of Registration
	  */
	public String getAdress2Reg();

    /** Column name Antiguedad_Fam */
    public static final String COLUMNNAME_Antiguedad_Fam = "Antiguedad_Fam";

	/** Set Antiquity.
	  * Antiquity of the relative's actual job
	  */
	public void setAntiguedad_Fam (int Antiguedad_Fam);

	/** Get Antiquity.
	  * Antiquity of the relative's actual job
	  */
	public int getAntiguedad_Fam();

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

    /** Column name Arma */
    public static final String COLUMNNAME_Arma = "Arma";

	/** Set Weapon	  */
	public void setArma (String Arma);

	/** Get Weapon	  */
	public String getArma();

    /** Column name CalleNumero */
    public static final String COLUMNNAME_CalleNumero = "CalleNumero";

	/** Set Street and Number	  */
	public void setCalleNumero (String CalleNumero);

	/** Get Street and Number	  */
	public String getCalleNumero();

    /** Column name CalleNumero_Nac */
    public static final String COLUMNNAME_CalleNumero_Nac = "CalleNumero_Nac";

	/** Set Birth Street and Number	  */
	public void setCalleNumero_Nac (String CalleNumero_Nac);

	/** Get Birth Street and Number	  */
	public String getCalleNumero_Nac();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Business Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Business Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_BPartner_Location_Value */
    public static final String COLUMNNAME_C_BPartner_Location_Value = "C_BPartner_Location_Value";

	/** Set Business Partner Location Value.
	  * Business Partner Location Value
	  */
	public void setC_BPartner_Location_Value (String C_BPartner_Location_Value);

	/** Get Business Partner Location Value.
	  * Business Partner Location Value
	  */
	public String getC_BPartner_Location_Value();

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

    /** Column name C_BPartner_Seg_Value */
    public static final String COLUMNNAME_C_BPartner_Seg_Value = "C_BPartner_Seg_Value";

	/** Set Business Partner Insurance Value	  */
	public void setC_BPartner_Seg_Value (String C_BPartner_Seg_Value);

	/** Get Business Partner Insurance Value	  */
	public String getC_BPartner_Seg_Value();

    /** Column name C_BPartner_Value */
    public static final String COLUMNNAME_C_BPartner_Value = "C_BPartner_Value";

	/** Set Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value);

	/** Get Business Partner Value	  */
	public String getC_BPartner_Value();

    /** Column name C_Country_IDReg */
    public static final String COLUMNNAME_C_Country_IDReg = "C_Country_IDReg";

	/** Set Country of Registration.
	  * Country of Registration
	  */
	public void setC_Country_IDReg (int C_Country_IDReg);

	/** Get Country of Registration.
	  * Country of Registration
	  */
	public int getC_Country_IDReg();

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

    /** Column name CityReg */
    public static final String COLUMNNAME_CityReg = "CityReg";

	/** Set CityReg.
	  * City of registration
	  */
	public void setCityReg (String CityReg);

	/** Get CityReg.
	  * City of registration
	  */
	public String getCityReg();

    /** Column name Ciudad */
    public static final String COLUMNNAME_Ciudad = "Ciudad";

	/** Set City.
	  * description of a city
	  */
	public void setCiudad (String Ciudad);

	/** Get City.
	  * description of a city
	  */
	public String getCiudad();

    /** Column name Ciudad_Nac */
    public static final String COLUMNNAME_Ciudad_Nac = "Ciudad_Nac";

	/** Set Birth City	  */
	public void setCiudad_Nac (String Ciudad_Nac);

	/** Get Birth City	  */
	public String getCiudad_Nac();

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

    /** Column name C_LocationReg_ID */
    public static final String COLUMNNAME_C_LocationReg_ID = "C_LocationReg_ID";

	/** Set Registered Address	  */
	public void setC_LocationReg_ID (int C_LocationReg_ID);

	/** Get Registered Address	  */
	public int getC_LocationReg_ID();

    /** Column name Colonia */
    public static final String COLUMNNAME_Colonia = "Colonia";

	/** Set Suburb / District.
	  * Suburb / District
	  */
	public void setColonia (String Colonia);

	/** Get Suburb / District.
	  * Suburb / District
	  */
	public String getColonia();

    /** Column name Colonia_Nac */
    public static final String COLUMNNAME_Colonia_Nac = "Colonia_Nac";

	/** Set Birth Colony	  */
	public void setColonia_Nac (String Colonia_Nac);

	/** Get Birth Colony	  */
	public String getColonia_Nac();

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

    /** Column name CountryReg */
    public static final String COLUMNNAME_CountryReg = "CountryReg";

	/** Set Country of Registration.
	  * Country of Registration
	  */
	public void setCountryReg (String CountryReg);

	/** Get Country of Registration.
	  * Country of Registration
	  */
	public String getCountryReg();

    /** Column name CP */
    public static final String COLUMNNAME_CP = "CP";

	/** Set Zip Code.
	  * Zip Code
	  */
	public void setCP (String CP);

	/** Get Zip Code.
	  * Zip Code
	  */
	public String getCP();

    /** Column name CP_Nac */
    public static final String COLUMNNAME_CP_Nac = "CP_Nac";

	/** Set Birth Postal Code	  */
	public void setCP_Nac (String CP_Nac);

	/** Get Birth Postal Code	  */
	public String getCP_Nac();

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

    /** Column name C_Region_IDReg */
    public static final String COLUMNNAME_C_Region_IDReg = "C_Region_IDReg";

	/** Set Region of Registration.
	  * Region of Registration
	  */
	public void setC_Region_IDReg (int C_Region_IDReg);

	/** Get Region of Registration.
	  * Region of Registration
	  */
	public int getC_Region_IDReg();

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

    /** Column name Degree */
    public static final String COLUMNNAME_Degree = "Degree";

	/** Set Degree	  */
	public void setDegree (String Degree);

	/** Get Degree	  */
	public String getDegree();

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

    /** Column name Diagnostico_Egreso_Descr */
    public static final String COLUMNNAME_Diagnostico_Egreso_Descr = "Diagnostico_Egreso_Descr";

	/** Set Diagnostico_Egreso_Descr	  */
	public void setDiagnostico_Egreso_Descr (String Diagnostico_Egreso_Descr);

	/** Get Diagnostico_Egreso_Descr	  */
	public String getDiagnostico_Egreso_Descr();

    /** Column name Diagnostico_Ingreso_Descr */
    public static final String COLUMNNAME_Diagnostico_Ingreso_Descr = "Diagnostico_Ingreso_Descr";

	/** Set Diagnostico_Ingreso_Descr	  */
	public void setDiagnostico_Ingreso_Descr (String Diagnostico_Ingreso_Descr);

	/** Get Diagnostico_Ingreso_Descr	  */
	public String getDiagnostico_Ingreso_Descr();

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

    /** Column name Escolaridad_Value */
    public static final String COLUMNNAME_Escolaridad_Value = "Escolaridad_Value";

	/** Set Value Schooling	  */
	public void setEscolaridad_Value (String Escolaridad_Value);

	/** Get Value Schooling	  */
	public String getEscolaridad_Value();

    /** Column name Especialidad_TS_Value */
    public static final String COLUMNNAME_Especialidad_TS_Value = "Especialidad_TS_Value";

	/** Set Value Schooling TS	  */
	public void setEspecialidad_TS_Value (String Especialidad_TS_Value);

	/** Get Value Schooling TS	  */
	public String getEspecialidad_TS_Value();

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

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setEXME_ArchClinico_ID (int EXME_ArchClinico_ID);

	/** Get Medical Record.
	  * Medical Record
	  */
	public int getEXME_ArchClinico_ID();

    /** Column name EXME_ArchClinico_Value */
    public static final String COLUMNNAME_EXME_ArchClinico_Value = "EXME_ArchClinico_Value";

	/** Set Clinical File Value	  */
	public void setEXME_ArchClinico_Value (String EXME_ArchClinico_Value);

	/** Get Clinical File Value	  */
	public String getEXME_ArchClinico_Value();

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

    /** Column name EXME_Delegacion_Paciente_ID */
    public static final String COLUMNNAME_EXME_Delegacion_Paciente_ID = "EXME_Delegacion_Paciente_ID";

	/** Set Patient Zone	  */
	public void setEXME_Delegacion_Paciente_ID (int EXME_Delegacion_Paciente_ID);

	/** Get Patient Zone	  */
	public int getEXME_Delegacion_Paciente_ID();

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

	public I_EXME_Escolaridad getEXME_Escolaridad() throws RuntimeException;

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

	public I_EXME_Especialidad_TS getEXME_Especialidad_TS() throws RuntimeException;

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

	/** Set Institution.
	  * Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Institution.
	  * Institution
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

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

	public I_EXME_Nacionalidad getEXME_Nacionalidad() throws RuntimeException;

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

    /** Column name EXME_Ocupacion_Fam_Value */
    public static final String COLUMNNAME_EXME_Ocupacion_Fam_Value = "EXME_Ocupacion_Fam_Value";

	/** Set Value Occupation	  */
	public void setEXME_Ocupacion_Fam_Value (String EXME_Ocupacion_Fam_Value);

	/** Get Value Occupation	  */
	public String getEXME_Ocupacion_Fam_Value();

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

	public I_EXME_Ocupacion getEXME_Ocupacion() throws RuntimeException;

    /** Column name EXME_Ocupacion_Value */
    public static final String COLUMNNAME_EXME_Ocupacion_Value = "EXME_Ocupacion_Value";

	/** Set Value Occupation	  */
	public void setEXME_Ocupacion_Value (String EXME_Ocupacion_Value);

	/** Get Value Occupation	  */
	public String getEXME_Ocupacion_Value();

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

	public I_EXME_Parentesco getEXME_Parentesco() throws RuntimeException;

    /** Column name EXME_Parentesco_Value */
    public static final String COLUMNNAME_EXME_Parentesco_Value = "EXME_Parentesco_Value";

	/** Set Kinship_Value	  */
	public void setEXME_Parentesco_Value (String EXME_Parentesco_Value);

	/** Get Kinship_Value	  */
	public String getEXME_Parentesco_Value();

    /** Column name EXME_PatientClass_ID */
    public static final String COLUMNNAME_EXME_PatientClass_ID = "EXME_PatientClass_ID";

	/** Set Patient Classification	  */
	public void setEXME_PatientClass_ID (int EXME_PatientClass_ID);

	/** Get Patient Classification	  */
	public int getEXME_PatientClass_ID();

	public I_EXME_PatientClass getEXME_PatientClass() throws RuntimeException;

    /** Column name EXME_PatientClass_Value */
    public static final String COLUMNNAME_EXME_PatientClass_Value = "EXME_PatientClass_Value";

	/** Set Patient Class Value.
	  * Patient Class Value
	  */
	public void setEXME_PatientClass_Value (String EXME_PatientClass_Value);

	/** Get Patient Class Value.
	  * Patient Class Value
	  */
	public String getEXME_PatientClass_Value();

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

	public I_EXME_Referencia getEXME_Referencia() throws RuntimeException;

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

	public I_EXME_Referencia_Int getEXME_Referencia_Int() throws RuntimeException;

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

	public I_EXME_Religion getEXME_Religion() throws RuntimeException;

    /** Column name EXME_Religion_Value */
    public static final String COLUMNNAME_EXME_Religion_Value = "EXME_Religion_Value";

	/** Set Value Religion	  */
	public void setEXME_Religion_Value (String EXME_Religion_Value);

	/** Get Value Religion	  */
	public String getEXME_Religion_Value();

    /** Column name EXME_TownCouncil_Name */
    public static final String COLUMNNAME_EXME_TownCouncil_Name = "EXME_TownCouncil_Name";

	/** Set Town Council Name.
	  * Town Council Name
	  */
	public void setEXME_TownCouncil_Name (String EXME_TownCouncil_Name);

	/** Get Town Council Name.
	  * Town Council Name
	  */
	public String getEXME_TownCouncil_Name();

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

    /** Column name GroupEsp */
    public static final String COLUMNNAME_GroupEsp = "GroupEsp";

	/** Set GroupEsp	  */
	public void setGroupEsp (String GroupEsp);

	/** Get GroupEsp	  */
	public String getGroupEsp();

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

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Paciente_ID */
    public static final String COLUMNNAME_I_EXME_Paciente_ID = "I_EXME_Paciente_ID";

	/** Set Patient	  */
	public void setI_EXME_Paciente_ID (int I_EXME_Paciente_ID);

	/** Get Patient	  */
	public int getI_EXME_Paciente_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

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

    /** Column name Institucion_Names */
    public static final String COLUMNNAME_Institucion_Names = "Institucion_Names";

	/** Set Institucion_Names	  */
	public void setInstitucion_Names (String Institucion_Names);

	/** Get Institucion_Names	  */
	public String getInstitucion_Names();

    /** Column name Institucion_Value */
    public static final String COLUMNNAME_Institucion_Value = "Institucion_Value";

	/** Set Value Institution	  */
	public void setInstitucion_Value (String Institucion_Value);

	/** Get Value Institution	  */
	public String getInstitucion_Value();

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

    /** Column name Nacionalidad_Value */
    public static final String COLUMNNAME_Nacionalidad_Value = "Nacionalidad_Value";

	/** Set Nacionality Value	  */
	public void setNacionalidad_Value (String Nacionalidad_Value);

	/** Get Nacionality Value	  */
	public String getNacionalidad_Value();

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

    /** Column name NumExtReg */
    public static final String COLUMNNAME_NumExtReg = "NumExtReg";

	/** Set Record Number of Foreign.
	  * Record Number of Foreign
	  */
	public void setNumExtReg (String NumExtReg);

	/** Get Record Number of Foreign.
	  * Record Number of Foreign
	  */
	public String getNumExtReg();

    /** Column name NumInReg */
    public static final String COLUMNNAME_NumInReg = "NumInReg";

	/** Set Number interior of Registration.
	  * Number interior of Registration
	  */
	public void setNumInReg (String NumInReg);

	/** Get Number interior of Registration.
	  * Number interior of Registration
	  */
	public String getNumInReg();

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

    /** Column name Otra_Inst */
    public static final String COLUMNNAME_Otra_Inst = "Otra_Inst";

	/** Set Another Institution	  */
	public void setOtra_Inst (String Otra_Inst);

	/** Get Another Institution	  */
	public String getOtra_Inst();

    /** Column name Pais */
    public static final String COLUMNNAME_Pais = "Pais";

	/** Set Pais	  */
	public void setPais (String Pais);

	/** Get Pais	  */
	public String getPais();

    /** Column name Pais_Nac */
    public static final String COLUMNNAME_Pais_Nac = "Pais_Nac";

	/** Set Pais_Nac	  */
	public void setPais_Nac (String Pais_Nac);

	/** Get Pais_Nac	  */
	public String getPais_Nac();

    /** Column name Pais_PersResp */
    public static final String COLUMNNAME_Pais_PersResp = "Pais_PersResp";

	/** Set Pais_PersResp	  */
	public void setPais_PersResp (String Pais_PersResp);

	/** Get Pais_PersResp	  */
	public String getPais_PersResp();

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

    /** Column name PostalReg */
    public static final String COLUMNNAME_PostalReg = "PostalReg";

	/** Set Postal Code Registration.
	  * Postal Code Registration
	  */
	public void setPostalReg (String PostalReg);

	/** Get Postal Code Registration.
	  * Postal Code Registration
	  */
	public String getPostalReg();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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

    /** Column name Referencia_Int_Value */
    public static final String COLUMNNAME_Referencia_Int_Value = "Referencia_Int_Value";

	/** Set Internal Reference Type	  */
	public void setReferencia_Int_Value (String Referencia_Int_Value);

	/** Get Internal Reference Type	  */
	public String getReferencia_Int_Value();

    /** Column name Referencia_Value */
    public static final String COLUMNNAME_Referencia_Value = "Referencia_Value";

	/** Set Reference Value	  */
	public void setReferencia_Value (String Referencia_Value);

	/** Get Reference Value	  */
	public String getReferencia_Value();

    /** Column name Region */
    public static final String COLUMNNAME_Region = "Region";

	/** Set Region	  */
	public void setRegion (String Region);

	/** Get Region	  */
	public String getRegion();

    /** Column name Region_Nac */
    public static final String COLUMNNAME_Region_Nac = "Region_Nac";

	/** Set Birthplace	  */
	public void setRegion_Nac (String Region_Nac);

	/** Get Birthplace	  */
	public String getRegion_Nac();

    /** Column name Region_PersResp */
    public static final String COLUMNNAME_Region_PersResp = "Region_PersResp";

	/** Set Responsible Person Region	  */
	public void setRegion_PersResp (String Region_PersResp);

	/** Get Responsible Person Region	  */
	public String getRegion_PersResp();

    /** Column name RegionReg */
    public static final String COLUMNNAME_RegionReg = "RegionReg";

	/** Set RegionReg.
	  * Region of Registration
	  */
	public void setRegionReg (String RegionReg);

	/** Get RegionReg.
	  * Region of Registration
	  */
	public String getRegionReg();

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

    /** Column name Titular_Value */
    public static final String COLUMNNAME_Titular_Value = "Titular_Value";

	/** Set Titular Value	  */
	public void setTitular_Value (String Titular_Value);

	/** Get Titular Value	  */
	public String getTitular_Value();

    /** Column name Unidad */
    public static final String COLUMNNAME_Unidad = "Unidad";

	/** Set Unity	  */
	public void setUnidad (String Unidad);

	/** Get Unity	  */
	public String getUnidad();

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
}
