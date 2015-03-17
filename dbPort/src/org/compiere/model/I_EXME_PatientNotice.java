/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PatientNotice
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PatientNotice 
{

    /** TableName=EXME_PatientNotice */
    public static final String Table_Name = "EXME_PatientNotice";

    /** AD_Table_ID=1201285 */
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

    /** Column name EXME_Alerta_ID */
    public static final String COLUMNNAME_EXME_Alerta_ID = "EXME_Alerta_ID";

	/** Set EXME_Alerta_ID	  */
	public void setEXME_Alerta_ID (int EXME_Alerta_ID);

	/** Get EXME_Alerta_ID	  */
	public int getEXME_Alerta_ID();

	public I_EXME_Alerta getEXME_Alerta() throws RuntimeException;

    /** Column name EXME_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_CitaMedica_ID = "EXME_CitaMedica_ID";

	/** Set Medical Appointment.
	  * Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID);

	/** Get Medical Appointment.
	  * Medical appointment
	  */
	public int getEXME_CitaMedica_ID();

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException;

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

    /** Column name EXME_PatientNotice_ID */
    public static final String COLUMNNAME_EXME_PatientNotice_ID = "EXME_PatientNotice_ID";

	/** Set Patient Notice	  */
	public void setEXME_PatientNotice_ID (int EXME_PatientNotice_ID);

	/** Get Patient Notice	  */
	public int getEXME_PatientNotice_ID();

    /** Column name Valid_To */
    public static final String COLUMNNAME_Valid_To = "Valid_To";

	/** Set Valid to	  */
	public void setValid_To (Timestamp Valid_To);

	/** Get Valid to	  */
	public Timestamp getValid_To();
}
