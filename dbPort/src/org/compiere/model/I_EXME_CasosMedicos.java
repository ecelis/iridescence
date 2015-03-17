/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CasosMedicos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CasosMedicos 
{

    /** TableName=EXME_CasosMedicos */
    public static final String Table_Name = "EXME_CasosMedicos";

    /** AD_Table_ID=1000215 */
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

    /** Column name AverPrev */
    public static final String COLUMNNAME_AverPrev = "AverPrev";

	/** Set Preliminary Investigation..
	  * Preliminary Investigation.
	  */
	public void setAverPrev (boolean AverPrev);

	/** Get Preliminary Investigation..
	  * Preliminary Investigation.
	  */
	public boolean isAverPrev();

    /** Column name Consecutivo */
    public static final String COLUMNNAME_Consecutivo = "Consecutivo";

	/** Set Consecutive.
	  * Consecutive
	  */
	public void setConsecutivo (int Consecutivo);

	/** Get Consecutive.
	  * Consecutive
	  */
	public int getConsecutivo();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name Diagnostico */
    public static final String COLUMNNAME_Diagnostico = "Diagnostico";

	/** Set Diagnostic.
	  * Diagnostic
	  */
	public void setDiagnostico (String Diagnostico);

	/** Get Diagnostic.
	  * Diagnostic
	  */
	public String getDiagnostico();

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

    /** Column name EXME_CasosMedicos_ID */
    public static final String COLUMNNAME_EXME_CasosMedicos_ID = "EXME_CasosMedicos_ID";

	/** Set Medical-Legal Cases.
	  * Medical-Legal Cases
	  */
	public void setEXME_CasosMedicos_ID (int EXME_CasosMedicos_ID);

	/** Get Medical-Legal Cases.
	  * Medical-Legal Cases
	  */
	public int getEXME_CasosMedicos_ID();

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

    /** Column name EXME_Hist_Exp_ID */
    public static final String COLUMNNAME_EXME_Hist_Exp_ID = "EXME_Hist_Exp_ID";

	/** Set Medical File.
	  * Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID);

	/** Get Medical File.
	  * Medical File
	  */
	public int getEXME_Hist_Exp_ID();

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

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name Juridico */
    public static final String COLUMNNAME_Juridico = "Juridico";

	/** Set Legal.
	  * Legal
	  */
	public void setJuridico (boolean Juridico);

	/** Get Legal.
	  * Legal
	  */
	public boolean isJuridico();

    /** Column name Medico */
    public static final String COLUMNNAME_Medico = "Medico";

	/** Set Physician	  */
	public void setMedico (String Medico);

	/** Get Physician	  */
	public String getMedico();

    /** Column name MinStPub */
    public static final String COLUMNNAME_MinStPub = "MinStPub";

	/** Set Public  Prosecutor.
	  * Public  Prosecutor
	  */
	public void setMinStPub (boolean MinStPub);

	/** Get Public  Prosecutor.
	  * Public  Prosecutor
	  */
	public boolean isMinStPub();

    /** Column name MotivoAlta */
    public static final String COLUMNNAME_MotivoAlta = "MotivoAlta";

	/** Set Discharge Reason.
	  * Discharge Reason
	  */
	public void setMotivoAlta (String MotivoAlta);

	/** Get Discharge Reason.
	  * Discharge Reason
	  */
	public String getMotivoAlta();

    /** Column name NoAverPrev */
    public static final String COLUMNNAME_NoAverPrev = "NoAverPrev";

	/** Set Preliminary investigation Number.
	  * Preliminary investigation Number
	  */
	public void setNoAverPrev (String NoAverPrev);

	/** Get Preliminary investigation Number.
	  * Preliminary investigation Number
	  */
	public String getNoAverPrev();

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

    /** Column name Observacion */
    public static final String COLUMNNAME_Observacion = "Observacion";

	/** Set Observation.
	  * Observation
	  */
	public void setObservacion (String Observacion);

	/** Get Observation.
	  * Observation
	  */
	public String getObservacion();

    /** Column name TSCama */
    public static final String COLUMNNAME_TSCama = "TSCama";

	/** Set Social Work Bed	  */
	public void setTSCama (String TSCama);

	/** Get Social Work Bed	  */
	public String getTSCama();

    /** Column name TSClasificacion */
    public static final String COLUMNNAME_TSClasificacion = "TSClasificacion";

	/** Set Social Work Clasification	  */
	public void setTSClasificacion (String TSClasificacion);

	/** Get Social Work Clasification	  */
	public String getTSClasificacion();

    /** Column name TSClinico */
    public static final String COLUMNNAME_TSClinico = "TSClinico";

	/** Set Social Work Clinical.
	  * Social Work Clinical
	  */
	public void setTSClinico (String TSClinico);

	/** Get Social Work Clinical.
	  * Social Work Clinical
	  */
	public String getTSClinico();

    /** Column name TSSexo */
    public static final String COLUMNNAME_TSSexo = "TSSexo";

	/** Set Sex	  */
	public void setTSSexo (String TSSexo);

	/** Get Sex	  */
	public String getTSSexo();
}
