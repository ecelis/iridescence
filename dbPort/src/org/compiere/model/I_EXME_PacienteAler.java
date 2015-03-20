/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteAler
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacienteAler 
{

    /** TableName=EXME_PacienteAler */
    public static final String Table_Name = "EXME_PacienteAler";

    /** AD_Table_ID=1000102 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - Client to System
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

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

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException;

    /** Column name EXME_Alergia_ID */
    public static final String COLUMNNAME_EXME_Alergia_ID = "EXME_Alergia_ID";

	/** Set Alergy.
	  * Alergy
	  */
	public void setEXME_Alergia_ID (int EXME_Alergia_ID);

	/** Get Alergy.
	  * Alergy
	  */
	public int getEXME_Alergia_ID();

	public I_EXME_Alergia getEXME_Alergia() throws RuntimeException;

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

    /** Column name EXME_PacienteAler_ID */
    public static final String COLUMNNAME_EXME_PacienteAler_ID = "EXME_PacienteAler_ID";

	/** Set Allergies Patient.
	  * Allergies Patient
	  */
	public void setEXME_PacienteAler_ID (int EXME_PacienteAler_ID);

	/** Get Allergies Patient.
	  * Allergies Patient
	  */
	public int getEXME_PacienteAler_ID();

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

    /** Column name EXME_SActiva_ID */
    public static final String COLUMNNAME_EXME_SActiva_ID = "EXME_SActiva_ID";

	/** Set Active Substance.
	  * Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID);

	/** Get Active Substance.
	  * Active Substance
	  */
	public int getEXME_SActiva_ID();

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException;

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

    /** Column name Reaccion */
    public static final String COLUMNNAME_Reaccion = "Reaccion";

	/** Set Reaction	  */
	public void setReaccion (String Reaccion);

	/** Get Reaction	  */
	public String getReaccion();

    /** Column name Severidad */
    public static final String COLUMNNAME_Severidad = "Severidad";

	/** Set Severity	  */
	public void setSeveridad (String Severidad);

	/** Get Severity	  */
	public String getSeveridad();

    /** Column name TipoAlergia */
    public static final String COLUMNNAME_TipoAlergia = "TipoAlergia";

	/** Set Alergy Type.
	  * Alergy Type
	  */
	public void setTipoAlergia (String TipoAlergia);

	/** Get Alergy Type.
	  * Alergy Type
	  */
	public String getTipoAlergia();
}
