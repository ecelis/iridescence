/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Medico
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Medico 
{

    /** TableName=EXME_Medico */
    public static final String Table_Name = "EXME_Medico";

    /** AD_Table_ID=1000016 */
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

    /** Column name CedProfesional */
    public static final String COLUMNNAME_CedProfesional = "CedProfesional";

	/** Set Professional Identity Number.
	  * Professional Identity Number
	  */
	public void setCedProfesional (String CedProfesional);

	/** Get Professional Identity Number.
	  * Professional Identity Number
	  */
	public String getCedProfesional();

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

    /** Column name C_Location_Cons_ID */
    public static final String COLUMNNAME_C_Location_Cons_ID = "C_Location_Cons_ID";

	/** Set Doctor`s office address.
	  * Doctor's office address
	  */
	public void setC_Location_Cons_ID (int C_Location_Cons_ID);

	/** Get Doctor`s office address.
	  * Doctor's office address
	  */
	public int getC_Location_Cons_ID();

    /** Column name C_Location_Fam_ID */
    public static final String COLUMNNAME_C_Location_Fam_ID = "C_Location_Fam_ID";

	/** Set Family location.
	  * Family location
	  */
	public void setC_Location_Fam_ID (int C_Location_Fam_ID);

	/** Get Family location.
	  * Family location
	  */
	public int getC_Location_Fam_ID();

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

    /** Column name CodSanidad */
    public static final String COLUMNNAME_CodSanidad = "CodSanidad";

	/** Set Health Code.
	  * Health Code
	  */
	public void setCodSanidad (String CodSanidad);

	/** Get Health Code.
	  * Health Code
	  */
	public String getCodSanidad();

    /** Column name Contact */
    public static final String COLUMNNAME_Contact = "Contact";

	/** Set Contact	  */
	public void setContact (String Contact);

	/** Get Contact	  */
	public String getContact();

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

    /** Column name DEANumber */
    public static final String COLUMNNAME_DEANumber = "DEANumber";

	/** Set DEA Number	  */
	public void setDEANumber (String DEANumber);

	/** Get DEA Number	  */
	public String getDEANumber();

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

    /** Column name EsInterno */
    public static final String COLUMNNAME_EsInterno = "EsInterno";

	/** Set Is internal.
	  * is Internal
	  */
	public void setEsInterno (boolean EsInterno);

	/** Get Is internal.
	  * is Internal
	  */
	public boolean isEsInterno();

    /** Column name EstaCertifConsejo */
    public static final String COLUMNNAME_EstaCertifConsejo = "EstaCertifConsejo";

	/** Set Is Certified by the Board.
	  * Certified
	  */
	public void setEstaCertifConsejo (boolean EstaCertifConsejo);

	/** Get Is Certified by the Board.
	  * Certified
	  */
	public boolean isEstaCertifConsejo();

    /** Column name EstaRecertificado */
    public static final String COLUMNNAME_EstaRecertificado = "EstaRecertificado";

	/** Set Is Recertified.
	  * Recertified
	  */
	public void setEstaRecertificado (boolean EstaRecertificado);

	/** Get Is Recertified.
	  * Recertified
	  */
	public boolean isEstaRecertificado();

    /** Column name EstaSuspendido */
    public static final String COLUMNNAME_EstaSuspendido = "EstaSuspendido";

	/** Set Suspended.
	  * Suspended
	  */
	public void setEstaSuspendido (boolean EstaSuspendido);

	/** Get Suspended.
	  * Suspended
	  */
	public boolean isEstaSuspendido();

    /** Column name EXME_AMASpecialty_ID */
    public static final String COLUMNNAME_EXME_AMASpecialty_ID = "EXME_AMASpecialty_ID";

	/** Set AMA Speciality	  */
	public void setEXME_AMASpecialty_ID (int EXME_AMASpecialty_ID);

	/** Get AMA Speciality	  */
	public int getEXME_AMASpecialty_ID();

	public I_EXME_AMASpecialty getEXME_AMASpecialty() throws RuntimeException;

    /** Column name EXME_CentroMedico_ID */
    public static final String COLUMNNAME_EXME_CentroMedico_ID = "EXME_CentroMedico_ID";

	/** Set Medical Center.
	  * medical Center
	  */
	public void setEXME_CentroMedico_ID (int EXME_CentroMedico_ID);

	/** Get Medical Center.
	  * medical Center
	  */
	public int getEXME_CentroMedico_ID();

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

    /** Column name EXME_Medico_Sust_ID */
    public static final String COLUMNNAME_EXME_Medico_Sust_ID = "EXME_Medico_Sust_ID";

	/** Set Substitute Doctor.
	  * Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID);

	/** Get Substitute Doctor.
	  * Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID();

	public I_EXME_Medico_Sust getEXME_Medico_Sust() throws RuntimeException;

    /** Column name EXME_MotivoBaja_ID */
    public static final String COLUMNNAME_EXME_MotivoBaja_ID = "EXME_MotivoBaja_ID";

	/** Set Motive of discharge.
	  * Motive of discharge
	  */
	public void setEXME_MotivoBaja_ID (int EXME_MotivoBaja_ID);

	/** Get Motive of discharge.
	  * Motive of discharge
	  */
	public int getEXME_MotivoBaja_ID();

    /** Column name EXME_TipoMedico_ID */
    public static final String COLUMNNAME_EXME_TipoMedico_ID = "EXME_TipoMedico_ID";

	/** Set Type of Doctor.
	  * Type of Doctor
	  */
	public void setEXME_TipoMedico_ID (int EXME_TipoMedico_ID);

	/** Get Type of Doctor.
	  * Type of Doctor
	  */
	public int getEXME_TipoMedico_ID();

    /** Column name EXME_Turnos_ID */
    public static final String COLUMNNAME_EXME_Turnos_ID = "EXME_Turnos_ID";

	/** Set Shift.
	  * Shift
	  */
	public void setEXME_Turnos_ID (int EXME_Turnos_ID);

	/** Get Shift.
	  * Shift
	  */
	public int getEXME_Turnos_ID();

    /** Column name EXME_Universidad_ID */
    public static final String COLUMNNAME_EXME_Universidad_ID = "EXME_Universidad_ID";

	/** Set University.
	  * University
	  */
	public void setEXME_Universidad_ID (int EXME_Universidad_ID);

	/** Get University.
	  * University
	  */
	public int getEXME_Universidad_ID();

    /** Column name FaxNumber */
    public static final String COLUMNNAME_FaxNumber = "FaxNumber";

	/** Set Fax Number.
	  * Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public void setFaxNumber (String FaxNumber);

	/** Get Fax Number.
	  * Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public String getFaxNumber();

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

    /** Column name FechaCertifConsejo */
    public static final String COLUMNNAME_FechaCertifConsejo = "FechaCertifConsejo";

	/** Set Cerificate Date.
	  * Cerificate Date
	  */
	public void setFechaCertifConsejo (Timestamp FechaCertifConsejo);

	/** Get Cerificate Date.
	  * Cerificate Date
	  */
	public Timestamp getFechaCertifConsejo();

    /** Column name FechaIngreso */
    public static final String COLUMNNAME_FechaIngreso = "FechaIngreso";

	/** Set Admission Date.
	  * Admission Date
	  */
	public void setFechaIngreso (Timestamp FechaIngreso);

	/** Get Admission Date.
	  * Admission Date
	  */
	public Timestamp getFechaIngreso();

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

    /** Column name FechaTitulo */
    public static final String COLUMNNAME_FechaTitulo = "FechaTitulo";

	/** Set Title Date.
	  * Title Date
	  */
	public void setFechaTitulo (Timestamp FechaTitulo);

	/** Get Title Date.
	  * Title Date
	  */
	public Timestamp getFechaTitulo();

    /** Column name FechaVencimCertif */
    public static final String COLUMNNAME_FechaVencimCertif = "FechaVencimCertif";

	/** Set Certif Expire Date.
	  * Cerificate Expire Date
	  */
	public void setFechaVencimCertif (Timestamp FechaVencimCertif);

	/** Get Certif Expire Date.
	  * Cerificate Expire Date
	  */
	public Timestamp getFechaVencimCertif();

    /** Column name IntervaloConsulta */
    public static final String COLUMNNAME_IntervaloConsulta = "IntervaloConsulta";

	/** Set Consult Interval	  */
	public void setIntervaloConsulta (int IntervaloConsulta);

	/** Get Consult Interval	  */
	public int getIntervaloConsulta();

    /** Column name ItemClass */
    public static final String COLUMNNAME_ItemClass = "ItemClass";

	/** Set Item Class.
	  * Item Class
	  */
	public void setItemClass (String ItemClass);

	/** Get Item Class.
	  * Item Class
	  */
	public String getItemClass();

    /** Column name MaxCitas */
    public static final String COLUMNNAME_MaxCitas = "MaxCitas";

	/** Set Max Appointments per Days	  */
	public void setMaxCitas (BigDecimal MaxCitas);

	/** Get Max Appointments per Days	  */
	public BigDecimal getMaxCitas();

    /** Column name MedicaidNo */
    public static final String COLUMNNAME_MedicaidNo = "MedicaidNo";

	/** Set Medicaid Number	  */
	public void setMedicaidNo (String MedicaidNo);

	/** Get Medicaid Number	  */
	public String getMedicaidNo();

    /** Column name MedicareNo */
    public static final String COLUMNNAME_MedicareNo = "MedicareNo";

	/** Set Medicare Number	  */
	public void setMedicareNo (String MedicareNo);

	/** Get Medicare Number	  */
	public String getMedicareNo();

    /** Column name MensajeMedico */
    public static final String COLUMNNAME_MensajeMedico = "MensajeMedico";

	/** Set Medical Message.
	  * Medical Message
	  */
	public void setMensajeMedico (String MensajeMedico);

	/** Get Medical Message.
	  * Medical Message
	  */
	public String getMensajeMedico();

    /** Column name ModificaEnFactura */
    public static final String COLUMNNAME_ModificaEnFactura = "ModificaEnFactura";

	/** Set Modify In Invoice	  */
	public void setModificaEnFactura (boolean ModificaEnFactura);

	/** Get Modify In Invoice	  */
	public boolean isModificaEnFactura();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

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

    /** Column name NoConsultorio */
    public static final String COLUMNNAME_NoConsultorio = "NoConsultorio";

	/** Set Doctor's Office No..
	  * Doctor's office No.
	  */
	public void setNoConsultorio (String NoConsultorio);

	/** Get Doctor's Office No..
	  * Doctor's office No.
	  */
	public String getNoConsultorio();

    /** Column name Nombre_Med */
    public static final String COLUMNNAME_Nombre_Med = "Nombre_Med";

	/** Set Medical Name.
	  * Medical complete name
	  */
	public void setNombre_Med (String Nombre_Med);

	/** Get Medical Name.
	  * Medical complete name
	  */
	public String getNombre_Med();

    /** Column name PhoneExt */
    public static final String COLUMNNAME_PhoneExt = "PhoneExt";

	/** Set Telephone extension.
	  * Telephone extension
	  */
	public void setPhoneExt (int PhoneExt);

	/** Get Telephone extension.
	  * Telephone extension
	  */
	public int getPhoneExt();

    /** Column name Radio */
    public static final String COLUMNNAME_Radio = "Radio";

	/** Set Radio	  */
	public void setRadio (String Radio);

	/** Get Radio	  */
	public String getRadio();

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

    /** Column name ScaleMin */
    public static final String COLUMNNAME_ScaleMin = "ScaleMin";

	/** Set Pixels - Minute Scale.
	  * Pixels - Minute Scale for Daily Medical Agenda
	  */
	public void setScaleMin (int ScaleMin);

	/** Get Pixels - Minute Scale.
	  * Pixels - Minute Scale for Daily Medical Agenda
	  */
	public int getScaleMin();

    /** Column name ServiceLevel */
    public static final String COLUMNNAME_ServiceLevel = "ServiceLevel";

	/** Set Service Level	  */
	public void setServiceLevel (String ServiceLevel);

	/** Get Service Level	  */
	public String getServiceLevel();

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

    /** Column name StateLicenseNumber */
    public static final String COLUMNNAME_StateLicenseNumber = "StateLicenseNumber";

	/** Set State License Number	  */
	public void setStateLicenseNumber (String StateLicenseNumber);

	/** Get State License Number	  */
	public String getStateLicenseNumber();

    /** Column name TelConsultorio */
    public static final String COLUMNNAME_TelConsultorio = "TelConsultorio";

	/** Set Doctor's Office Telephone.
	  * doctor's office telephone
	  */
	public void setTelConsultorio (String TelConsultorio);

	/** Get Doctor's Office Telephone.
	  * doctor's office telephone
	  */
	public String getTelConsultorio();

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

    /** Column name TiempoEspera */
    public static final String COLUMNNAME_TiempoEspera = "TiempoEspera";

	/** Set Waiting Time.
	  * Waiting Time (in months)
	  */
	public void setTiempoEspera (BigDecimal TiempoEspera);

	/** Get Waiting Time.
	  * Waiting Time (in months)
	  */
	public BigDecimal getTiempoEspera();

    /** Column name TieneIncentivo */
    public static final String COLUMNNAME_TieneIncentivo = "TieneIncentivo";

	/** Set Incentive.
	  * Has incentive
	  */
	public void setTieneIncentivo (boolean TieneIncentivo);

	/** Get Incentive.
	  * Has incentive
	  */
	public boolean isTieneIncentivo();

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
