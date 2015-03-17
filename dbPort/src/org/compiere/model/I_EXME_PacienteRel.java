/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteRel
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PacienteRel 
{

    /** TableName=EXME_PacienteRel */
    public static final String Table_Name = "EXME_PacienteRel";

    /** AD_Table_ID=1000033 */
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

    /** Column name C_LocationPhys_ID */
    public static final String COLUMNNAME_C_LocationPhys_ID = "C_LocationPhys_ID";

	/** Set Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID);

	/** Get Physical Address	  */
	public int getC_LocationPhys_ID();

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

    /** Column name DocType */
    public static final String COLUMNNAME_DocType = "DocType";

	/** Set Document Type	  */
	public void setDocType (String DocType);

	/** Get Document Type	  */
	public String getDocType();

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

    /** Column name EXME_PacienteRel_ID */
    public static final String COLUMNNAME_EXME_PacienteRel_ID = "EXME_PacienteRel_ID";

	/** Set Patient Relations.
	  * Patient Relations
	  */
	public void setEXME_PacienteRel_ID (int EXME_PacienteRel_ID);

	/** Get Patient Relations.
	  * Patient Relations
	  */
	public int getEXME_PacienteRel_ID();

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

    /** Column name IsBusinessGuarantor */
    public static final String COLUMNNAME_IsBusinessGuarantor = "IsBusinessGuarantor";

	/** Set Is a Business Guarantor.
	  * Is a Business Guarantor
	  */
	public void setIsBusinessGuarantor (boolean IsBusinessGuarantor);

	/** Get Is a Business Guarantor.
	  * Is a Business Guarantor
	  */
	public boolean isBusinessGuarantor();

    /** Column name Last_Name */
    public static final String COLUMNNAME_Last_Name = "Last_Name";

	/** Set Last_Name	  */
	public void setLast_Name (String Last_Name);

	/** Get Last_Name	  */
	public String getLast_Name();

    /** Column name Lastname2 */
    public static final String COLUMNNAME_Lastname2 = "Lastname2";

	/** Set Lastname2	  */
	public void setLastname2 (String Lastname2);

	/** Get Lastname2	  */
	public String getLastname2();

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
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();

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
