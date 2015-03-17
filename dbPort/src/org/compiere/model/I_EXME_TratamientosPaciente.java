/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TratamientosPaciente
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TratamientosPaciente 
{

    /** TableName=EXME_TratamientosPaciente */
    public static final String Table_Name = "EXME_TratamientosPaciente";

    /** AD_Table_ID=1200393 */
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

    /** Column name CitaNo */
    public static final String COLUMNNAME_CitaNo = "CitaNo";

	/** Set Appointment Number.
	  * Appointment number
	  */
	public void setCitaNo (BigDecimal CitaNo);

	/** Get Appointment Number.
	  * Appointment number
	  */
	public BigDecimal getCitaNo();

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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

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

    /** Column name EXME_Tratamientos_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_ID = "EXME_Tratamientos_ID";

	/** Set Treatments.
	  * Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID);

	/** Get Treatments.
	  * Treatments
	  */
	public int getEXME_Tratamientos_ID();

	public I_EXME_Tratamientos getEXME_Tratamientos() throws RuntimeException;

    /** Column name EXME_TratamientosPaciente_ID */
    public static final String COLUMNNAME_EXME_TratamientosPaciente_ID = "EXME_TratamientosPaciente_ID";

	/** Set Patient Treatments	  */
	public void setEXME_TratamientosPaciente_ID (int EXME_TratamientosPaciente_ID);

	/** Get Patient Treatments	  */
	public int getEXME_TratamientosPaciente_ID();

    /** Column name Fecha_Tratamiento */
    public static final String COLUMNNAME_Fecha_Tratamiento = "Fecha_Tratamiento";

	/** Set Treatment Date	  */
	public void setFecha_Tratamiento (Timestamp Fecha_Tratamiento);

	/** Get Treatment Date	  */
	public Timestamp getFecha_Tratamiento();

    /** Column name ProgramarCitas */
    public static final String COLUMNNAME_ProgramarCitas = "ProgramarCitas";

	/** Set Schedule Appointments?	  */
	public void setProgramarCitas (boolean ProgramarCitas);

	/** Get Schedule Appointments?	  */
	public boolean isProgramarCitas();

    /** Column name Ref_EXME_Especialidad_ID */
    public static final String COLUMNNAME_Ref_EXME_Especialidad_ID = "Ref_EXME_Especialidad_ID";

	/** Set Specialty Reference	  */
	public void setRef_EXME_Especialidad_ID (int Ref_EXME_Especialidad_ID);

	/** Get Specialty Reference	  */
	public int getRef_EXME_Especialidad_ID();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();
}
