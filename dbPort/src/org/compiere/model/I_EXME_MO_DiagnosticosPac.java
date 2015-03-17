/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_DiagnosticosPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_DiagnosticosPac 
{

    /** TableName=EXME_MO_DiagnosticosPac */
    public static final String Table_Name = "EXME_MO_DiagnosticosPac";

    /** AD_Table_ID=1200396 */
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

    /** Column name EsOdontograma */
    public static final String COLUMNNAME_EsOdontograma = "EsOdontograma";

	/** Set Is Odontogram.
	  * Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma);

	/** Get Is Odontogram.
	  * Is Odontogram
	  */
	public boolean isEsOdontograma();

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

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_MO_DiagnosticosPac_ID */
    public static final String COLUMNNAME_EXME_MO_DiagnosticosPac_ID = "EXME_MO_DiagnosticosPac_ID";

	/** Set Patient Diagnosis	  */
	public void setEXME_MO_DiagnosticosPac_ID (int EXME_MO_DiagnosticosPac_ID);

	/** Get Patient Diagnosis	  */
	public int getEXME_MO_DiagnosticosPac_ID();

    /** Column name EXME_MO_PiezaDental_ID */
    public static final String COLUMNNAME_EXME_MO_PiezaDental_ID = "EXME_MO_PiezaDental_ID";

	/** Set Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID);

	/** Get Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID();

	public I_EXME_MO_PiezaDental getEXME_MO_PiezaDental() throws RuntimeException;

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

    /** Column name Fecha_Diagnostico */
    public static final String COLUMNNAME_Fecha_Diagnostico = "Fecha_Diagnostico";

	/** Set Diagnostic Date.
	  * Diagnostic Date
	  */
	public void setFecha_Diagnostico (Timestamp Fecha_Diagnostico);

	/** Get Diagnostic Date.
	  * Diagnostic Date
	  */
	public Timestamp getFecha_Diagnostico();
}
