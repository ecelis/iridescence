/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Paciente_ADT_V
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Paciente_ADT_V 
{

    /** TableName=EXME_Paciente_ADT_V */
    public static final String Table_Name = "EXME_Paciente_ADT_V";

    /** AD_Table_ID=1201398 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name EdoCivil */
    public static final String COLUMNNAME_EdoCivil = "EdoCivil";

	/** Set Marital Status.
	  * Marital Status
	  */
	public void setEdoCivil (boolean EdoCivil);

	/** Get Marital Status.
	  * Marital Status
	  */
	public boolean isEdoCivil();

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

    /** Column name EXME_Paciente_ADT_V_ID */
    public static final String COLUMNNAME_EXME_Paciente_ADT_V_ID = "EXME_Paciente_ADT_V_ID";

	/** Set Patient Data View for ADT Messages	  */
	public void setEXME_Paciente_ADT_V_ID (int EXME_Paciente_ADT_V_ID);

	/** Get Patient Data View for ADT Messages	  */
	public int getEXME_Paciente_ADT_V_ID();

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

    /** Column name GpoEtnico */
    public static final String COLUMNNAME_GpoEtnico = "GpoEtnico";

	/** Set Ethnic Group	  */
	public void setGpoEtnico (String GpoEtnico);

	/** Get Ethnic Group	  */
	public String getGpoEtnico();

    /** Column name IsMuerto */
    public static final String COLUMNNAME_IsMuerto = "IsMuerto";

	/** Set Is Death?	  */
	public void setIsMuerto (String IsMuerto);

	/** Get Is Death?	  */
	public String getIsMuerto();

    /** Column name LenguaNativa */
    public static final String COLUMNNAME_LenguaNativa = "LenguaNativa";

	/** Set Native Language	  */
	public void setLenguaNativa (String LenguaNativa);

	/** Get Native Language	  */
	public String getLenguaNativa();

    /** Column name Nacionalidad */
    public static final String COLUMNNAME_Nacionalidad = "Nacionalidad";

	/** Set Nationality	  */
	public void setNacionalidad (String Nacionalidad);

	/** Get Nationality	  */
	public String getNacionalidad();

    /** Column name Nombre */
    public static final String COLUMNNAME_Nombre = "Nombre";

	/** Set Name.
	  * Name of friend
	  */
	public void setNombre (String Nombre);

	/** Get Name.
	  * Name of friend
	  */
	public String getNombre();

    /** Column name NSS */
    public static final String COLUMNNAME_NSS = "NSS";

	/** Set Social Security Number	  */
	public void setNSS (String NSS);

	/** Get Social Security Number	  */
	public String getNSS();

    /** Column name Raza */
    public static final String COLUMNNAME_Raza = "Raza";

	/** Set Race	  */
	public void setRaza (String Raza);

	/** Get Race	  */
	public String getRaza();

    /** Column name Religion */
    public static final String COLUMNNAME_Religion = "Religion";

	/** Set Religion	  */
	public void setReligion (String Religion);

	/** Get Religion	  */
	public String getReligion();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (boolean Sexo);

	/** Get Sex.
	  * Sex
	  */
	public boolean isSexo();

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
}
