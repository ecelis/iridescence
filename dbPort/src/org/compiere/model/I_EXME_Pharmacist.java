/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Pharmacist
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Pharmacist 
{

    /** TableName=EXME_Pharmacist */
    public static final String Table_Name = "EXME_Pharmacist";

    /** AD_Table_ID=1201136 */
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

    /** Column name EMailWork */
    public static final String COLUMNNAME_EMailWork = "EMailWork";

	/** Set Work email address.
	  * Work email address
	  */
	public void setEMailWork (String EMailWork);

	/** Get Work email address.
	  * Work email address
	  */
	public String getEMailWork();

    /** Column name EXME_Farmacia_ID */
    public static final String COLUMNNAME_EXME_Farmacia_ID = "EXME_Farmacia_ID";

	/** Set Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID);

	/** Get Pharmacy	  */
	public int getEXME_Farmacia_ID();

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException;

    /** Column name EXME_Pharmacist_ID */
    public static final String COLUMNNAME_EXME_Pharmacist_ID = "EXME_Pharmacist_ID";

	/** Set Pharmacist	  */
	public void setEXME_Pharmacist_ID (int EXME_Pharmacist_ID);

	/** Get Pharmacist	  */
	public int getEXME_Pharmacist_ID();

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

	public I_EXME_Turnos getEXME_Turnos() throws RuntimeException;

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

    /** Column name FechaVencimiento */
    public static final String COLUMNNAME_FechaVencimiento = "FechaVencimiento";

	/** Set Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento);

	/** Get Termination Date	  */
	public Timestamp getFechaVencimiento();

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

    /** Column name Pager */
    public static final String COLUMNNAME_Pager = "Pager";

	/** Set Pager	  */
	public void setPager (String Pager);

	/** Get Pager	  */
	public String getPager();

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

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name Suffix */
    public static final String COLUMNNAME_Suffix = "Suffix";

	/** Set Suffix.
	  * Suffix after the number
	  */
	public void setSuffix (String Suffix);

	/** Get Suffix.
	  * Suffix after the number
	  */
	public String getSuffix();

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

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

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
