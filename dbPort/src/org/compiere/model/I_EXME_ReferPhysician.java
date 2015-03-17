/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ReferPhysician
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ReferPhysician 
{

    /** TableName=EXME_ReferPhysician */
    public static final String Table_Name = "EXME_ReferPhysician";

    /** AD_Table_ID=1201146 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_Dest_ID */
    public static final String COLUMNNAME_AD_Org_Dest_ID = "AD_Org_Dest_ID";

	/** Set Target Organization.
	  * The organization to refer to
	  */
	public void setAD_Org_Dest_ID (int AD_Org_Dest_ID);

	/** Get Target Organization.
	  * The organization to refer to
	  */
	public int getAD_Org_Dest_ID();

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

    /** Column name Comentarios */
    public static final String COLUMNNAME_Comentarios = "Comentarios";

	/** Set Notes	  */
	public void setComentarios (String Comentarios);

	/** Get Notes	  */
	public String getComentarios();

    /** Column name EXME_CitaMedDest_ID */
    public static final String COLUMNNAME_EXME_CitaMedDest_ID = "EXME_CitaMedDest_ID";

	/** Set Target Office Visit.
	  * The generated office visit from the referral
	  */
	public void setEXME_CitaMedDest_ID (int EXME_CitaMedDest_ID);

	/** Get Target Office Visit.
	  * The generated office visit from the referral
	  */
	public int getEXME_CitaMedDest_ID();

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

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Institution.
	  * Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Institution.
	  * Institution
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

    /** Column name EXME_MedicoDest_ID */
    public static final String COLUMNNAME_EXME_MedicoDest_ID = "EXME_MedicoDest_ID";

	/** Set Target Physician.
	  * The physician to refer to
	  */
	public void setEXME_MedicoDest_ID (int EXME_MedicoDest_ID);

	/** Get Target Physician.
	  * The physician to refer to
	  */
	public int getEXME_MedicoDest_ID();

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

    /** Column name EXME_ReferPhysician_ID */
    public static final String COLUMNNAME_EXME_ReferPhysician_ID = "EXME_ReferPhysician_ID";

	/** Set Reference Physician.
	  * Reference Physician
	  */
	public void setEXME_ReferPhysician_ID (int EXME_ReferPhysician_ID);

	/** Get Reference Physician.
	  * Reference Physician
	  */
	public int getEXME_ReferPhysician_ID();

    /** Column name FacilityOID */
    public static final String COLUMNNAME_FacilityOID = "FacilityOID";

	/** Set Facility OID	  */
	public void setFacilityOID (String FacilityOID);

	/** Get Facility OID	  */
	public String getFacilityOID();

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

    /** Column name Motivo */
    public static final String COLUMNNAME_Motivo = "Motivo";

	/** Set Motive.
	  * Motive / Reason
	  */
	public void setMotivo (String Motivo);

	/** Get Motive.
	  * Motive / Reason
	  */
	public String getMotivo();

    /** Column name Nombre_Med */
    public static final String COLUMNNAME_Nombre_Med = "Nombre_Med";

	/** Set Medical Name.
	  * Medical complete name
	  */
	public void setNombre_Med (String Nombre_Med);

	/** Get Medical Name.
	  * Medical complete name
	  */
	public String getNombre_Med();

    /** Column name NumTratamiento */
    public static final String COLUMNNAME_NumTratamiento = "NumTratamiento";

	/** Set Number of Treatments.
	  * Number of Treatments
	  */
	public void setNumTratamiento (int NumTratamiento);

	/** Get Number of Treatments.
	  * Number of Treatments
	  */
	public int getNumTratamiento();

    /** Column name Otros */
    public static final String COLUMNNAME_Otros = "Otros";

	/** Set Others	  */
	public void setOtros (String Otros);

	/** Get Others	  */
	public String getOtros();

    /** Column name Proveedor */
    public static final String COLUMNNAME_Proveedor = "Proveedor";

	/** Set Supplier	  */
	public void setProveedor (String Proveedor);

	/** Get Supplier	  */
	public String getProveedor();

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

    /** Column name TipoConsulta */
    public static final String COLUMNNAME_TipoConsulta = "TipoConsulta";

	/** Set Type of Consultation.
	  * To the consultant
	  */
	public void setTipoConsulta (String TipoConsulta);

	/** Get Type of Consultation.
	  * To the consultant
	  */
	public String getTipoConsulta();
}
