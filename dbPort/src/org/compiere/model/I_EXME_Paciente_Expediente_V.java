/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Paciente_Expediente_V
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Paciente_Expediente_V 
{

    /** TableName=EXME_Paciente_Expediente_V */
    public static final String Table_Name = "EXME_Paciente_Expediente_V";

    /** AD_Table_ID=1201155 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name EXME_Paciente_Expediente_V_ID */
    public static final String COLUMNNAME_EXME_Paciente_Expediente_V_ID = "EXME_Paciente_Expediente_V_ID";

	/** Set MRN_Patient_ID	  */
	public void setEXME_Paciente_Expediente_V_ID (int EXME_Paciente_Expediente_V_ID);

	/** Get MRN_Patient_ID	  */
	public int getEXME_Paciente_Expediente_V_ID();

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

    /** Column name Historia */
    public static final String COLUMNNAME_Historia = "Historia";

	/** Set Unique Patient Identification.
	  * Unique Patient Identification
	  */
	public void setHistoria (String Historia);

	/** Get Unique Patient Identification.
	  * Unique Patient Identification
	  */
	public String getHistoria();

    /** Column name MRN */
    public static final String COLUMNNAME_MRN = "MRN";

	/** Set Medical Record Number.
	  * Medical Record Number
	  */
	public void setMRN (String MRN);

	/** Get Medical Record Number.
	  * Medical Record Number
	  */
	public String getMRN();

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

    /** Column name NSS */
    public static final String COLUMNNAME_NSS = "NSS";

	/** Set Social Security Number	  */
	public void setNSS (String NSS);

	/** Get Social Security Number	  */
	public String getNSS();

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

    /** Column name SuffixNSS */
    public static final String COLUMNNAME_SuffixNSS = "SuffixNSS";

	/** Set SSN	  */
	public void setSuffixNSS (String SuffixNSS);

	/** Get SSN	  */
	public String getSuffixNSS();

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
