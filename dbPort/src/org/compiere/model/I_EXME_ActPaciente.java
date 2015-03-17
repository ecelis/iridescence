/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPaciente
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ActPaciente 
{

    /** TableName=EXME_ActPaciente */
    public static final String Table_Name = "EXME_ActPaciente";

    /** AD_Table_ID=1000066 */
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

    /** Column name EXME_ActPaciente_ID */
    public static final String COLUMNNAME_EXME_ActPaciente_ID = "EXME_ActPaciente_ID";

	/** Set Patient Activity.
	  * Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID);

	/** Get Patient Activity.
	  * Patient Activity
	  */
	public int getEXME_ActPaciente_ID();

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

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

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

    /** Column name EXME_MotivoCita_ID */
    public static final String COLUMNNAME_EXME_MotivoCita_ID = "EXME_MotivoCita_ID";

	/** Set Motive of appointment.
	  * Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID);

	/** Get Motive of appointment.
	  * Motive of appointment
	  */
	public int getEXME_MotivoCita_ID();

	public I_EXME_MotivoCita getEXME_MotivoCita() throws RuntimeException;

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

    /** Column name EXME_ProgQuiro_ID */
    public static final String COLUMNNAME_EXME_ProgQuiro_ID = "EXME_ProgQuiro_ID";

	/** Set Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID);

	/** Get Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID();

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException;

    /** Column name EstadoSalud */
    public static final String COLUMNNAME_EstadoSalud = "EstadoSalud";

	/** Set Health 	  */
	public void setEstadoSalud (String EstadoSalud);

	/** Get Health 	  */
	public String getEstadoSalud();

    /** Column name Estatura */
    public static final String COLUMNNAME_Estatura = "Estatura";

	/** Set Height.
	  * Height
	  */
	public void setEstatura (BigDecimal Estatura);

	/** Get Height.
	  * Height
	  */
	public BigDecimal getEstatura();

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name IsPregnant */
    public static final String COLUMNNAME_IsPregnant = "IsPregnant";

	/** Set Is Pregnant.
	  * Indicates pregnancy
	  */
	public void setIsPregnant (boolean IsPregnant);

	/** Get Is Pregnant.
	  * Indicates pregnancy
	  */
	public boolean isPregnant();

    /** Column name MasaCorporal */
    public static final String COLUMNNAME_MasaCorporal = "MasaCorporal";

	/** Set Corporal Mass.
	  * Corporal Mass of the patient
	  */
	public void setMasaCorporal (BigDecimal MasaCorporal);

	/** Get Corporal Mass.
	  * Corporal Mass of the patient
	  */
	public BigDecimal getMasaCorporal();

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

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (BigDecimal Peso);

	/** Get Weight.
	  * Weight
	  */
	public BigDecimal getPeso();

    /** Column name PregnancyMonths */
    public static final String COLUMNNAME_PregnancyMonths = "PregnancyMonths";

	/** Set Pregnancy Months.
	  * Pregnancy Months
	  */
	public void setPregnancyMonths (BigDecimal PregnancyMonths);

	/** Get Pregnancy Months.
	  * Pregnancy Months
	  */
	public BigDecimal getPregnancyMonths();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

    /** Column name VacunaPendiente */
    public static final String COLUMNNAME_VacunaPendiente = "VacunaPendiente";

	/** Set Vaccine Pending	  */
	public void setVacunaPendiente (boolean VacunaPendiente);

	/** Get Vaccine Pending	  */
	public boolean isVacunaPendiente();

    /** Column name VigenciaHC */
    public static final String COLUMNNAME_VigenciaHC = "VigenciaHC";

	/** Set Effective Clinical History	  */
	public void setVigenciaHC (Timestamp VigenciaHC);

	/** Get Effective Clinical History	  */
	public Timestamp getVigenciaHC();
}
