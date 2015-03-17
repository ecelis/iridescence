/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_Contacto
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_Contacto 
{

    /** TableName=PHR_Contacto */
    public static final String Table_Name = "PHR_Contacto";

    /** AD_Table_ID=1200930 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
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

    /** Column name EsPrincipal */
    public static final String COLUMNNAME_EsPrincipal = "EsPrincipal";

	/** Set Primary physician.
	  * Primary physician
	  */
	public void setEsPrincipal (boolean EsPrincipal);

	/** Get Primary physician.
	  * Primary physician
	  */
	public boolean isEsPrincipal();

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

    /** Column name PHR_Contacto_ID */
    public static final String COLUMNNAME_PHR_Contacto_ID = "PHR_Contacto_ID";

	/** Set Emergency Contacts	  */
	public void setPHR_Contacto_ID (int PHR_Contacto_ID);

	/** Get Emergency Contacts	  */
	public int getPHR_Contacto_ID();

    /** Column name TelefonoCasa */
    public static final String COLUMNNAME_TelefonoCasa = "TelefonoCasa";

	/** Set Home Phone	  */
	public void setTelefonoCasa (String TelefonoCasa);

	/** Get Home Phone	  */
	public String getTelefonoCasa();

    /** Column name TelefonoMovil */
    public static final String COLUMNNAME_TelefonoMovil = "TelefonoMovil";

	/** Set Mobile Phone	  */
	public void setTelefonoMovil (String TelefonoMovil);

	/** Get Mobile Phone	  */
	public String getTelefonoMovil();

    /** Column name TelefonoTrabajo */
    public static final String COLUMNNAME_TelefonoTrabajo = "TelefonoTrabajo";

	/** Set Work Phone	  */
	public void setTelefonoTrabajo (String TelefonoTrabajo);

	/** Get Work Phone	  */
	public String getTelefonoTrabajo();
}
