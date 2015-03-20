/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PatientDocument
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PatientDocument 
{

    /** TableName=EXME_PatientDocument */
    public static final String Table_Name = "EXME_PatientDocument";

    /** AD_Table_ID=1201192 */
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

    /** Column name Action */
    public static final String COLUMNNAME_Action = "Action";

	/** Set Action.
	  * Indicates the Action to be performed
	  */
	public void setAction (String Action);

	/** Get Action.
	  * Indicates the Action to be performed
	  */
	public String getAction();

    /** Column name Archivo */
    public static final String COLUMNNAME_Archivo = "Archivo";

	/** Set File	  */
	public void setArchivo (byte[] Archivo);

	/** Get File	  */
	public byte[] getArchivo();

    /** Column name DeliveryDate */
    public static final String COLUMNNAME_DeliveryDate = "DeliveryDate";

	/** Set Delivery Date	  */
	public void setDeliveryDate (Timestamp DeliveryDate);

	/** Get Delivery Date	  */
	public Timestamp getDeliveryDate();

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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

    /** Column name EXME_PatientDocument_ID */
    public static final String COLUMNNAME_EXME_PatientDocument_ID = "EXME_PatientDocument_ID";

	/** Set Type of Document Patient	  */
	public void setEXME_PatientDocument_ID (int EXME_PatientDocument_ID);

	/** Get Type of Document Patient	  */
	public int getEXME_PatientDocument_ID();

    /** Column name FormatoArchivo */
    public static final String COLUMNNAME_FormatoArchivo = "FormatoArchivo";

	/** Set File Format.
	  * File Format
	  */
	public void setFormatoArchivo (String FormatoArchivo);

	/** Get File Format.
	  * File Format
	  */
	public String getFormatoArchivo();

    /** Column name NombreArchivo */
    public static final String COLUMNNAME_NombreArchivo = "NombreArchivo";

	/** Set File Name.
	  * File Name
	  */
	public void setNombreArchivo (String NombreArchivo);

	/** Get File Name.
	  * File Name
	  */
	public String getNombreArchivo();

    /** Column name RequestDate */
    public static final String COLUMNNAME_RequestDate = "RequestDate";

	/** Set Request Date	  */
	public void setRequestDate (Timestamp RequestDate);

	/** Get Request Date	  */
	public Timestamp getRequestDate();

    /** Column name Requester */
    public static final String COLUMNNAME_Requester = "Requester";

	/** Set Requester	  */
	public void setRequester (String Requester);

	/** Get Requester	  */
	public String getRequester();

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
}
