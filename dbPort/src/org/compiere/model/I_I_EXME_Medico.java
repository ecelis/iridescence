/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Medico
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Medico 
{

    /** TableName=I_EXME_Medico */
    public static final String Table_Name = "I_EXME_Medico";

    /** AD_Table_ID=1000154 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address1Cons */
    public static final String COLUMNNAME_Address1Cons = "Address1Cons";

	/** Set Address 1 of Doctor's office .
	  * Address 1 of Doctor's office
	  */
	public void setAddress1Cons (String Address1Cons);

	/** Get Address 1 of Doctor's office .
	  * Address 1 of Doctor's office
	  */
	public String getAddress1Cons();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name Address2Cons */
    public static final String COLUMNNAME_Address2Cons = "Address2Cons";

	/** Set Address 2 of Doctor's office.
	  * Address 2 of Doctor's office
	  */
	public void setAddress2Cons (String Address2Cons);

	/** Get Address 2 of Doctor's office.
	  * Address 2 of Doctor's office
	  */
	public String getAddress2Cons();

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

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

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

    /** Column name C_Country_Cons_ID */
    public static final String COLUMNNAME_C_Country_Cons_ID = "C_Country_Cons_ID";

	/** Set Doctor's office Country.
	  * Doctor's office Country
	  */
	public void setC_Country_Cons_ID (int C_Country_Cons_ID);

	/** Get Doctor's office Country.
	  * Doctor's office Country
	  */
	public int getC_Country_Cons_ID();

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public I_C_Country getC_Country() throws RuntimeException;

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

    /** Column name CentroMedico_Value */
    public static final String COLUMNNAME_CentroMedico_Value = "CentroMedico_Value";

	/** Set Medical Center Key.
	  * Medical Center Search Key
	  */
	public void setCentroMedico_Value (String CentroMedico_Value);

	/** Get Medical Center Key.
	  * Medical Center Search Key
	  */
	public String getCentroMedico_Value();

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name CityCons */
    public static final String COLUMNNAME_CityCons = "CityCons";

	/** Set Doctor's Office City.
	  * Doctors' office city
	  */
	public void setCityCons (String CityCons);

	/** Get Doctor's Office City.
	  * Doctors' office city
	  */
	public String getCityCons();

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
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

    /** Column name ContactName */
    public static final String COLUMNNAME_ContactName = "ContactName";

	/** Set Contact Name.
	  * Business Partner Contact Name
	  */
	public void setContactName (String ContactName);

	/** Get Contact Name.
	  * Business Partner Contact Name
	  */
	public String getContactName();

    /** Column name CountryCode */
    public static final String COLUMNNAME_CountryCode = "CountryCode";

	/** Set ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode);

	/** Get ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode();

    /** Column name CountryCodeCons */
    public static final String COLUMNNAME_CountryCodeCons = "CountryCodeCons";

	/** Set Doctor's Office Country Code.
	  * Two capital letters to see the country code ISO according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.htm
	  */
	public void setCountryCodeCons (String CountryCodeCons);

	/** Get Doctor's Office Country Code.
	  * Two capital letters to see the country code ISO according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.htm
	  */
	public String getCountryCodeCons();

    /** Column name C_Region_Cons_ID */
    public static final String COLUMNNAME_C_Region_Cons_ID = "C_Region_Cons_ID";

	/** Set State/Region of Doctor's office.
	  * State/Region of Doctor's office
	  */
	public void setC_Region_Cons_ID (int C_Region_Cons_ID);

	/** Get State/Region of Doctor's office.
	  * State/Region of Doctor's office
	  */
	public int getC_Region_Cons_ID();

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

	public I_C_Region getC_Region() throws RuntimeException;

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

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

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

    /** Column name EXME_TipoMedico_Value */
    public static final String COLUMNNAME_EXME_TipoMedico_Value = "EXME_TipoMedico_Value";

	/** Set Type of Doctor Code.
	  * Type of Doctor search code
	  */
	public void setEXME_TipoMedico_Value (String EXME_TipoMedico_Value);

	/** Get Type of Doctor Code.
	  * Type of Doctor search code
	  */
	public String getEXME_TipoMedico_Value();

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

    /** Column name EXME_Universidad_Value */
    public static final String COLUMNNAME_EXME_Universidad_Value = "EXME_Universidad_Value";

	/** Set University Code.
	  * University Search Code
	  */
	public void setEXME_Universidad_Value (String EXME_Universidad_Value);

	/** Get University Code.
	  * University Search Code
	  */
	public String getEXME_Universidad_Value();

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

    /** Column name I_EXME_Medico_ID */
    public static final String COLUMNNAME_I_EXME_Medico_ID = "I_EXME_Medico_ID";

	/** Set Imported Doctor.
	  * Imported Doctor
	  */
	public void setI_EXME_Medico_ID (int I_EXME_Medico_ID);

	/** Get Imported Doctor.
	  * Imported Doctor
	  */
	public int getI_EXME_Medico_ID();

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

    /** Column name IntervaloConsulta */
    public static final String COLUMNNAME_IntervaloConsulta = "IntervaloConsulta";

	/** Set Consult Interval	  */
	public void setIntervaloConsulta (int IntervaloConsulta);

	/** Get Consult Interval	  */
	public int getIntervaloConsulta();

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

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Product_Value */
    public static final String COLUMNNAME_M_Product_Value = "M_Product_Value";

	/** Set Product Code.
	  * product search Code
	  */
	public void setM_Product_Value (String M_Product_Value);

	/** Get Product Code.
	  * product search Code
	  */
	public String getM_Product_Value();

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

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name PostalCons */
    public static final String COLUMNNAME_PostalCons = "PostalCons";

	/** Set Doctor's Office Postal Code.
	  * Doctor's office Postal Code
	  */
	public void setPostalCons (String PostalCons);

	/** Get Doctor's Office Postal Code.
	  * Doctor's office Postal Code
	  */
	public String getPostalCons();

    /** Column name Precio_Consulta */
    public static final String COLUMNNAME_Precio_Consulta = "Precio_Consulta";

	/** Set Price by Consulting.
	  * Price by consulting
	  */
	public void setPrecio_Consulta (BigDecimal Precio_Consulta);

	/** Get Price by Consulting.
	  * Price by consulting
	  */
	public BigDecimal getPrecio_Consulta();

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

    /** Column name Radio */
    public static final String COLUMNNAME_Radio = "Radio";

	/** Set Radio	  */
	public void setRadio (String Radio);

	/** Get Radio	  */
	public String getRadio();

    /** Column name RegionName */
    public static final String COLUMNNAME_RegionName = "RegionName";

	/** Set Region.
	  * Name of the Region
	  */
	public void setRegionName (String RegionName);

	/** Get Region.
	  * Name of the Region
	  */
	public String getRegionName();

    /** Column name RegionNameCons */
    public static final String COLUMNNAME_RegionNameCons = "RegionNameCons";

	/** Set Doctor's Office Region.
	  * Name of Region
	  */
	public void setRegionNameCons (String RegionNameCons);

	/** Get Doctor's Office Region.
	  * Name of Region
	  */
	public String getRegionNameCons();

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

	/** Set Private Telephone.
	  * Private telephone
	  */
	public void setTelParticular (String TelParticular);

	/** Get Private Telephone.
	  * Private telephone
	  */
	public String getTelParticular();

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

    /** Column name Turnos_Value */
    public static final String COLUMNNAME_Turnos_Value = "Turnos_Value";

	/** Set Shift Code.
	  * Shift search code
	  */
	public void setTurnos_Value (String Turnos_Value);

	/** Get Shift Code.
	  * Shift search code
	  */
	public String getTurnos_Value();

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
