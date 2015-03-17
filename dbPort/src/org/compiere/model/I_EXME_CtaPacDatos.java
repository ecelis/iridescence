/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacDatos
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_CtaPacDatos 
{

    /** TableName=EXME_CtaPacDatos */
    public static final String Table_Name = "EXME_CtaPacDatos";

    /** AD_Table_ID=1200070 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AdmitSource */
    public static final String COLUMNNAME_AdmitSource = "AdmitSource";

	/** Set Admit Source.
	  * Admit Source
	  */
	public void setAdmitSource (int AdmitSource);

	/** Get Admit Source.
	  * Admit Source
	  */
	public int getAdmitSource();

    /** Column name AdmitType */
    public static final String COLUMNNAME_AdmitType = "AdmitType";

	/** Set Admit Type.
	  * Admit Type
	  */
	public void setAdmitType (int AdmitType);

	/** Get Admit Type.
	  * Admit Type
	  */
	public int getAdmitType();

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

    /** Column name ArrivalDate */
    public static final String COLUMNNAME_ArrivalDate = "ArrivalDate";

	/** Set Arrival Date.
	  * Arrival Date
	  */
	public void setArrivalDate (Timestamp ArrivalDate);

	/** Get Arrival Date.
	  * Arrival Date
	  */
	public Timestamp getArrivalDate();

    /** Column name ArrivalMode */
    public static final String COLUMNNAME_ArrivalMode = "ArrivalMode";

	/** Set Arrival Mode.
	  * Arrival Mode
	  */
	public void setArrivalMode (int ArrivalMode);

	/** Get Arrival Mode.
	  * Arrival Mode
	  */
	public int getArrivalMode();

    /** Column name Autorizacion */
    public static final String COLUMNNAME_Autorizacion = "Autorizacion";

	/** Set Authorization.
	  * Authorization
	  */
	public void setAutorizacion (String Autorizacion);

	/** Get Authorization.
	  * Authorization
	  */
	public String getAutorizacion();

    /** Column name EXME_CtaPacDatos_ID */
    public static final String COLUMNNAME_EXME_CtaPacDatos_ID = "EXME_CtaPacDatos_ID";

	/** Set Complementary Data.
	  * Complementary Data
	  */
	public void setEXME_CtaPacDatos_ID (int EXME_CtaPacDatos_ID);

	/** Get Complementary Data.
	  * Complementary Data
	  */
	public int getEXME_CtaPacDatos_ID();

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

    /** Column name EXME_DiagnosticoFin_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoFin_ID = "EXME_DiagnosticoFin_ID";

	/** Set Final Diagnostic.
	  * Final Diagnostic
	  */
	public void setEXME_DiagnosticoFin_ID (int EXME_DiagnosticoFin_ID);

	/** Get Final Diagnostic.
	  * Final Diagnostic
	  */
	public int getEXME_DiagnosticoFin_ID();

    /** Column name EXME_DiagnosticoIni_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoIni_ID = "EXME_DiagnosticoIni_ID";

	/** Set Initial Diagnostic.
	  * Initial Diagnostic
	  */
	public void setEXME_DiagnosticoIni_ID (int EXME_DiagnosticoIni_ID);

	/** Get Initial Diagnostic.
	  * Initial Diagnostic
	  */
	public int getEXME_DiagnosticoIni_ID();

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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

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

    /** Column name EXME_TypeOfBill_ID */
    public static final String COLUMNNAME_EXME_TypeOfBill_ID = "EXME_TypeOfBill_ID";

	/** Set EXME_TypeOfBill_ID.
	  * EXME_TypeOfBill_ID
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID);

	/** Get EXME_TypeOfBill_ID.
	  * EXME_TypeOfBill_ID
	  */
	public int getEXME_TypeOfBill_ID();

	public I_EXME_TypeOfBill getEXME_TypeOfBill() throws RuntimeException;

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

    /** Column name FechaSintoma */
    public static final String COLUMNNAME_FechaSintoma = "FechaSintoma";

	/** Set Date of Symptom Onset	  */
	public void setFechaSintoma (Timestamp FechaSintoma);

	/** Get Date of Symptom Onset	  */
	public Timestamp getFechaSintoma();

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (String Folio);

	/** Get Folio	  */
	public String getFolio();

    /** Column name IsStudent */
    public static final String COLUMNNAME_IsStudent = "IsStudent";

	/** Set IsStudent	  */
	public void setIsStudent (boolean IsStudent);

	/** Get IsStudent	  */
	public boolean isStudent();

    /** Column name MedicoCia */
    public static final String COLUMNNAME_MedicoCia = "MedicoCia";

	/** Set Company Physician.
	  * Company Physician
	  */
	public void setMedicoCia (String MedicoCia);

	/** Get Company Physician.
	  * Company Physician
	  */
	public String getMedicoCia();

    /** Column name RelatedTo */
    public static final String COLUMNNAME_RelatedTo = "RelatedTo";

	/** Set Related To	  */
	public void setRelatedTo (String RelatedTo);

	/** Get Related To	  */
	public String getRelatedTo();

    /** Column name Service */
    public static final String COLUMNNAME_Service = "Service";

	/** Set Service.
	  * Service
	  */
	public void setService (int Service);

	/** Get Service.
	  * Service
	  */
	public int getService();

    /** Column name State */
    public static final String COLUMNNAME_State = "State";

	/** Set State	  */
	public void setState (int State);

	/** Get State	  */
	public int getState();

    /** Column name TypeStudent */
    public static final String COLUMNNAME_TypeStudent = "TypeStudent";

	/** Set Type Student	  */
	public void setTypeStudent (String TypeStudent);

	/** Get Type Student	  */
	public String getTypeStudent();
}
