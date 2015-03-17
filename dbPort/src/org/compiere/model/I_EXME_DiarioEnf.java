/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DiarioEnf
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_DiarioEnf 
{

    /** TableName=EXME_DiarioEnf */
    public static final String Table_Name = "EXME_DiarioEnf";

    /** AD_Table_ID=1200147 */
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

    /** Column name EXME_ApoyoVentilatorio_ID */
    public static final String COLUMNNAME_EXME_ApoyoVentilatorio_ID = "EXME_ApoyoVentilatorio_ID";

	/** Set Ventilatory support	  */
	public void setEXME_ApoyoVentilatorio_ID (int EXME_ApoyoVentilatorio_ID);

	/** Get Ventilatory support	  */
	public int getEXME_ApoyoVentilatorio_ID();

	public I_EXME_ApoyoVentilatorio getEXME_ApoyoVentilatorio() throws RuntimeException;

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

    /** Column name EXME_DiagnosticoEgreso_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoEgreso_ID = "EXME_DiagnosticoEgreso_ID";

	/** Set Exit Diagnosis	  */
	public void setEXME_DiagnosticoEgreso_ID (int EXME_DiagnosticoEgreso_ID);

	/** Get Exit Diagnosis	  */
	public int getEXME_DiagnosticoEgreso_ID();

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

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

    /** Column name EXME_EdoConciencia_ID */
    public static final String COLUMNNAME_EXME_EdoConciencia_ID = "EXME_EdoConciencia_ID";

	/** Set Conscience	  */
	public void setEXME_EdoConciencia_ID (int EXME_EdoConciencia_ID);

	/** Get Conscience	  */
	public int getEXME_EdoConciencia_ID();

	public I_EXME_EdoConciencia getEXME_EdoConciencia() throws RuntimeException;

    /** Column name EXME_Enfermeria_Egr */
    public static final String COLUMNNAME_EXME_Enfermeria_Egr = "EXME_Enfermeria_Egr";

	/** Set Nursing Discharge	  */
	public void setEXME_Enfermeria_Egr (int EXME_Enfermeria_Egr);

	/** Get Nursing Discharge	  */
	public int getEXME_Enfermeria_Egr();

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name EXME_Enfermeria_Ing */
    public static final String COLUMNNAME_EXME_Enfermeria_Ing = "EXME_Enfermeria_Ing";

	/** Set Nursing Admission	  */
	public void setEXME_Enfermeria_Ing (int EXME_Enfermeria_Ing);

	/** Get Nursing Admission	  */
	public int getEXME_Enfermeria_Ing();

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Unit.
	  * Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Unit.
	  * Service Unit
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_FnRespiratoria_ID */
    public static final String COLUMNNAME_EXME_FnRespiratoria_ID = "EXME_FnRespiratoria_ID";

	/** Set Respiratory Function	  */
	public void setEXME_FnRespiratoria_ID (int EXME_FnRespiratoria_ID);

	/** Get Respiratory Function	  */
	public int getEXME_FnRespiratoria_ID();

	public I_EXME_FnRespiratoria getEXME_FnRespiratoria() throws RuntimeException;

    /** Column name EXME_MotivoEgreso_ID */
    public static final String COLUMNNAME_EXME_MotivoEgreso_ID = "EXME_MotivoEgreso_ID";

	/** Set Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID);

	/** Get Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID();

	public I_EXME_MotivoEgreso getEXME_MotivoEgreso() throws RuntimeException;

    /** Column name EXME_MotivoTraslado_ID */
    public static final String COLUMNNAME_EXME_MotivoTraslado_ID = "EXME_MotivoTraslado_ID";

	/** Set Motive of Movement.
	  * Motive of Movement
	  */
	public void setEXME_MotivoTraslado_ID (int EXME_MotivoTraslado_ID);

	/** Get Motive of Movement.
	  * Motive of Movement
	  */
	public int getEXME_MotivoTraslado_ID();

	public I_EXME_MotivoTraslado getEXME_MotivoTraslado() throws RuntimeException;

    /** Column name EXME_NivelUlcera_ID */
    public static final String COLUMNNAME_EXME_NivelUlcera_ID = "EXME_NivelUlcera_ID";

	/** Set Ulcer Level.
	  * Ulcer Level
	  */
	public void setEXME_NivelUlcera_ID (int EXME_NivelUlcera_ID);

	/** Get Ulcer Level.
	  * Ulcer Level
	  */
	public int getEXME_NivelUlcera_ID();

	public I_EXME_NivelUlcera getEXME_NivelUlcera() throws RuntimeException;

    /** Column name EXME_ProcEsp_ID */
    public static final String COLUMNNAME_EXME_ProcEsp_ID = "EXME_ProcEsp_ID";

	/** Set Special Procedures	  */
	public void setEXME_ProcEsp_ID (int EXME_ProcEsp_ID);

	/** Get Special Procedures	  */
	public int getEXME_ProcEsp_ID();

	public I_EXME_ProcEsp getEXME_ProcEsp() throws RuntimeException;

    /** Column name EXME_Procedencia_Egreso_ID */
    public static final String COLUMNNAME_EXME_Procedencia_Egreso_ID = "EXME_Procedencia_Egreso_ID";

	/** Set Origin Discharge	  */
	public void setEXME_Procedencia_Egreso_ID (int EXME_Procedencia_Egreso_ID);

	/** Get Origin Discharge	  */
	public int getEXME_Procedencia_Egreso_ID();

    /** Column name EXME_Procedencia_ID */
    public static final String COLUMNNAME_EXME_Procedencia_ID = "EXME_Procedencia_ID";

	/** Set Provenance.
	  * Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID);

	/** Get Provenance.
	  * Provenance
	  */
	public int getEXME_Procedencia_ID();

	public I_EXME_Procedencia getEXME_Procedencia() throws RuntimeException;

    /** Column name EXME_TipoHerida_ID */
    public static final String COLUMNNAME_EXME_TipoHerida_ID = "EXME_TipoHerida_ID";

	/** Set Type of wound	  */
	public void setEXME_TipoHerida_ID (int EXME_TipoHerida_ID);

	/** Get Type of wound	  */
	public int getEXME_TipoHerida_ID();

	public I_EXME_TipoHerida getEXME_TipoHerida() throws RuntimeException;

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

    /** Column name Fecha_Defuncion */
    public static final String COLUMNNAME_Fecha_Defuncion = "Fecha_Defuncion";

	/** Set Fecha_Defuncion	  */
	public void setFecha_Defuncion (Timestamp Fecha_Defuncion);

	/** Get Fecha_Defuncion	  */
	public Timestamp getFecha_Defuncion();

    /** Column name Fecha_Egreso */
    public static final String COLUMNNAME_Fecha_Egreso = "Fecha_Egreso";

	/** Set Discharge Date	  */
	public void setFecha_Egreso (Timestamp Fecha_Egreso);

	/** Get Discharge Date	  */
	public Timestamp getFecha_Egreso();

    /** Column name Fecha_Ingreso */
    public static final String COLUMNNAME_Fecha_Ingreso = "Fecha_Ingreso";

	/** Set Entrance Date	  */
	public void setFecha_Ingreso (Timestamp Fecha_Ingreso);

	/** Get Entrance Date	  */
	public Timestamp getFecha_Ingreso();

    /** Column name Glasgow_Motora */
    public static final String COLUMNNAME_Glasgow_Motora = "Glasgow_Motora";

	/** Set Glasgow_Motora.
	  * Motor Glasgow Valuation
	  */
	public void setGlasgow_Motora (String Glasgow_Motora);

	/** Get Glasgow_Motora.
	  * Motor Glasgow Valuation
	  */
	public String getGlasgow_Motora();

    /** Column name Glasgow_Ocular */
    public static final String COLUMNNAME_Glasgow_Ocular = "Glasgow_Ocular";

	/** Set Glasgow_Ocular.
	  * Eye Glasgow Valuation
	  */
	public void setGlasgow_Ocular (String Glasgow_Ocular);

	/** Get Glasgow_Ocular.
	  * Eye Glasgow Valuation
	  */
	public String getGlasgow_Ocular();

    /** Column name Glasgow_Verbal */
    public static final String COLUMNNAME_Glasgow_Verbal = "Glasgow_Verbal";

	/** Set Glasgow_Verbal.
	  * Verbal Glasgow Valuation
	  */
	public void setGlasgow_Verbal (String Glasgow_Verbal);

	/** Get Glasgow_Verbal.
	  * Verbal Glasgow Valuation
	  */
	public String getGlasgow_Verbal();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name Motivo_Caida_E */
    public static final String COLUMNNAME_Motivo_Caida_E = "Motivo_Caida_E";

	/** Set Fall Reason.
	  * Fall Reason
	  */
	public void setMotivo_Caida_E (String Motivo_Caida_E);

	/** Get Fall Reason.
	  * Fall Reason
	  */
	public String getMotivo_Caida_E();

    /** Column name Motivo_Caida_I */
    public static final String COLUMNNAME_Motivo_Caida_I = "Motivo_Caida_I";

	/** Set Fall Reason.
	  * Fall Reason
	  */
	public void setMotivo_Caida_I (String Motivo_Caida_I);

	/** Get Fall Reason.
	  * Fall Reason
	  */
	public String getMotivo_Caida_I();

    /** Column name Motivo_Ingreso */
    public static final String COLUMNNAME_Motivo_Ingreso = "Motivo_Ingreso";

	/** Set Admission Reason	  */
	public void setMotivo_Ingreso (String Motivo_Ingreso);

	/** Get Admission Reason	  */
	public String getMotivo_Ingreso();

    /** Column name Niv_Caida */
    public static final String COLUMNNAME_Niv_Caida = "Niv_Caida";

	/** Set Fall Level	  */
	public void setNiv_Caida (int Niv_Caida);

	/** Get Fall Level	  */
	public int getNiv_Caida();

    /** Column name Niv_Caida_E */
    public static final String COLUMNNAME_Niv_Caida_E = "Niv_Caida_E";

	/** Set Discharge Fall Level	  */
	public void setNiv_Caida_E (int Niv_Caida_E);

	/** Get Discharge Fall Level	  */
	public int getNiv_Caida_E();

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

    /** Column name Observaciones_Ingreso */
    public static final String COLUMNNAME_Observaciones_Ingreso = "Observaciones_Ingreso";

	/** Set Admission Notes	  */
	public void setObservaciones_Ingreso (String Observaciones_Ingreso);

	/** Get Admission Notes	  */
	public String getObservaciones_Ingreso();

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

    /** Column name Pupilas */
    public static final String COLUMNNAME_Pupilas = "Pupilas";

	/** Set Pupilas.
	  * Pupil
	  */
	public void setPupilas (String Pupilas);

	/** Get Pupilas.
	  * Pupil
	  */
	public String getPupilas();

    /** Column name Riesgo_Caida_E */
    public static final String COLUMNNAME_Riesgo_Caida_E = "Riesgo_Caida_E";

	/** Set Risk of a fall in the discharge of the Patient 	  */
	public void setRiesgo_Caida_E (String Riesgo_Caida_E);

	/** Get Risk of a fall in the discharge of the Patient 	  */
	public String getRiesgo_Caida_E();

    /** Column name Riesgo_Caida_I */
    public static final String COLUMNNAME_Riesgo_Caida_I = "Riesgo_Caida_I";

	/** Set Risk of a fall in the Income of the Patient 	  */
	public void setRiesgo_Caida_I (String Riesgo_Caida_I);

	/** Get Risk of a fall in the Income of the Patient 	  */
	public String getRiesgo_Caida_I();

    /** Column name TipoSangre */
    public static final String COLUMNNAME_TipoSangre = "TipoSangre";

	/** Set Blood Type	  */
	public void setTipoSangre (String TipoSangre);

	/** Get Blood Type	  */
	public String getTipoSangre();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
