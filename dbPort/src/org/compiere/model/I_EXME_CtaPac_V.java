/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPac_V
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CtaPac_V 
{

    /** TableName=EXME_CtaPac_V */
    public static final String Table_Name = "EXME_CtaPac_V";

    /** AD_Table_ID=1201326 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AdmitDate */
    public static final String COLUMNNAME_AdmitDate = "AdmitDate";

	/** Set Admit Date.
	  * Admit Date
	  */
	public void setAdmitDate (Timestamp AdmitDate);

	/** Get Admit Date.
	  * Admit Date
	  */
	public Timestamp getAdmitDate();

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

    /** Column name Encounter */
    public static final String COLUMNNAME_Encounter = "Encounter";

	/** Set Document No.
	  * Document No
	  */
	public void setEncounter (String Encounter);

	/** Get Document No.
	  * Document No
	  */
	public String getEncounter();

    /** Column name EncounterStatus */
    public static final String COLUMNNAME_EncounterStatus = "EncounterStatus";

	/** Set Encounter Status	  */
	public void setEncounterStatus (boolean EncounterStatus);

	/** Get Encounter Status	  */
	public boolean isEncounterStatus();

    /** Column name EXME_CtaPac_V_ID */
    public static final String COLUMNNAME_EXME_CtaPac_V_ID = "EXME_CtaPac_V_ID";

	/** Set Encounter View	  */
	public void setEXME_CtaPac_V_ID (int EXME_CtaPac_V_ID);

	/** Get Encounter View	  */
	public int getEXME_CtaPac_V_ID();

    /** Column name FechaAlta */
    public static final String COLUMNNAME_FechaAlta = "FechaAlta";

	/** Set Discharge Date.
	  * Discharge Date
	  */
	public void setFechaAlta (Timestamp FechaAlta);

	/** Get Discharge Date.
	  * Discharge Date
	  */
	public Timestamp getFechaAlta();

    /** Column name FechaCierre */
    public static final String COLUMNNAME_FechaCierre = "FechaCierre";

	/** Set Closing Date.
	  * Date of Intervention Closing
	  */
	public void setFechaCierre (Timestamp FechaCierre);

	/** Get Closing Date.
	  * Date of Intervention Closing
	  */
	public Timestamp getFechaCierre();

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
}
