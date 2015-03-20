/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Param_Metricas
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Param_Metricas 
{

    /** TableName=EXME_Param_Metricas */
    public static final String Table_Name = "EXME_Param_Metricas";

    /** AD_Table_ID=1201050 */
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

    /** Column name Age_BMI */
    public static final String COLUMNNAME_Age_BMI = "Age_BMI";

	/** Set Age BMI	  */
	public void setAge_BMI (int Age_BMI);

	/** Get Age BMI	  */
	public int getAge_BMI();

    /** Column name Age_BreastFrom */
    public static final String COLUMNNAME_Age_BreastFrom = "Age_BreastFrom";

	/** Set Age Breast Cancer From	  */
	public void setAge_BreastFrom (int Age_BreastFrom);

	/** Get Age Breast Cancer From	  */
	public int getAge_BreastFrom();

    /** Column name Age_BreastTo */
    public static final String COLUMNNAME_Age_BreastTo = "Age_BreastTo";

	/** Set Age Breast Cancer To	  */
	public void setAge_BreastTo (int Age_BreastTo);

	/** Get Age Breast Cancer To	  */
	public int getAge_BreastTo();

    /** Column name Age_Childhood */
    public static final String COLUMNNAME_Age_Childhood = "Age_Childhood";

	/** Set Age Childhood Immunization	  */
	public void setAge_Childhood (int Age_Childhood);

	/** Get Age Childhood Immunization	  */
	public int getAge_Childhood();

    /** Column name Age_ColorectalFrom */
    public static final String COLUMNNAME_Age_ColorectalFrom = "Age_ColorectalFrom";

	/** Set Age Colorectal Cancer From	  */
	public void setAge_ColorectalFrom (int Age_ColorectalFrom);

	/** Get Age Colorectal Cancer From	  */
	public int getAge_ColorectalFrom();

    /** Column name Age_ColorectalTo */
    public static final String COLUMNNAME_Age_ColorectalTo = "Age_ColorectalTo";

	/** Set Age Colorectal Cancer To	  */
	public void setAge_ColorectalTo (int Age_ColorectalTo);

	/** Get Age Colorectal Cancer To	  */
	public int getAge_ColorectalTo();

    /** Column name Age_Hypertension */
    public static final String COLUMNNAME_Age_Hypertension = "Age_Hypertension";

	/** Set Age Hypertension	  */
	public void setAge_Hypertension (int Age_Hypertension);

	/** Get Age Hypertension	  */
	public int getAge_Hypertension();

    /** Column name Age_Immunization */
    public static final String COLUMNNAME_Age_Immunization = "Age_Immunization";

	/** Set Age Influenza Immunization	  */
	public void setAge_Immunization (int Age_Immunization);

	/** Get Age Influenza Immunization	  */
	public int getAge_Immunization();

    /** Column name Age_Pneumonia */
    public static final String COLUMNNAME_Age_Pneumonia = "Age_Pneumonia";

	/** Set Age Pneumonia	  */
	public void setAge_Pneumonia (int Age_Pneumonia);

	/** Get Age Pneumonia	  */
	public int getAge_Pneumonia();

    /** Column name Age_Tobacco */
    public static final String COLUMNNAME_Age_Tobacco = "Age_Tobacco";

	/** Set Age Tobacco	  */
	public void setAge_Tobacco (int Age_Tobacco);

	/** Get Age Tobacco	  */
	public int getAge_Tobacco();

    /** Column name Age_Weight */
    public static final String COLUMNNAME_Age_Weight = "Age_Weight";

	/** Set Age Weight Assessment From	  */
	public void setAge_Weight (int Age_Weight);

	/** Get Age Weight Assessment From	  */
	public int getAge_Weight();

    /** Column name Age_WeightTo */
    public static final String COLUMNNAME_Age_WeightTo = "Age_WeightTo";

	/** Set Age Weight Assessment To	  */
	public void setAge_WeightTo (int Age_WeightTo);

	/** Get Age Weight Assessment To	  */
	public int getAge_WeightTo();

    /** Column name Antiplatelet */
    public static final String COLUMNNAME_Antiplatelet = "Antiplatelet";

	/** Set Antiplatelet	  */
	public void setAntiplatelet (int Antiplatelet);

	/** Get Antiplatelet	  */
	public int getAntiplatelet();

    /** Column name Asthma */
    public static final String COLUMNNAME_Asthma = "Asthma";

	/** Set Asthma	  */
	public void setAsthma (int Asthma);

	/** Get Asthma	  */
	public int getAsthma();

    /** Column name Ateriosclerosis_Diag */
    public static final String COLUMNNAME_Ateriosclerosis_Diag = "Ateriosclerosis_Diag";

	/** Set Ateriosclerosis Diagnostic	  */
	public void setAteriosclerosis_Diag (int Ateriosclerosis_Diag);

	/** Get Ateriosclerosis Diagnostic	  */
	public int getAteriosclerosis_Diag();

    /** Column name Atrial_Diagnostic */
    public static final String COLUMNNAME_Atrial_Diagnostic = "Atrial_Diagnostic";

	/** Set Atrial Fibrillation	  */
	public void setAtrial_Diagnostic (int Atrial_Diagnostic);

	/** Get Atrial Fibrillation	  */
	public int getAtrial_Diagnostic();

    /** Column name Carotid_Intervention */
    public static final String COLUMNNAME_Carotid_Intervention = "Carotid_Intervention";

	/** Set Carotid Intervention	  */
	public void setCarotid_Intervention (int Carotid_Intervention);

	/** Get Carotid Intervention	  */
	public int getCarotid_Intervention();

    /** Column name CessationAgents */
    public static final String COLUMNNAME_CessationAgents = "CessationAgents";

	/** Set Cessation Agents	  */
	public void setCessationAgents (int CessationAgents);

	/** Get Cessation Agents	  */
	public int getCessationAgents();

    /** Column name Coronary */
    public static final String COLUMNNAME_Coronary = "Coronary";

	/** Set Coronary Artery Disease	  */
	public void setCoronary (int Coronary);

	/** Get Coronary Artery Disease	  */
	public int getCoronary();

    /** Column name CounselingNutrition */
    public static final String COLUMNNAME_CounselingNutrition = "CounselingNutrition";

	/** Set Counseling for Nutrition	  */
	public void setCounselingNutrition (int CounselingNutrition);

	/** Get Counseling for Nutrition	  */
	public int getCounselingNutrition();

    /** Column name Diabetes */
    public static final String COLUMNNAME_Diabetes = "Diabetes";

	/** Set Diabetes	  */
	public void setDiabetes (int Diabetes);

	/** Get Diabetes	  */
	public int getDiabetes();

    /** Column name DiabetesMedication */
    public static final String COLUMNNAME_DiabetesMedication = "DiabetesMedication";

	/** Set Diabetes Medication	  */
	public void setDiabetesMedication (int DiabetesMedication);

	/** Get Diabetes Medication	  */
	public int getDiabetesMedication();

    /** Column name Elective_Reason */
    public static final String COLUMNNAME_Elective_Reason = "Elective_Reason";

	/** Set Elective Reason	  */
	public void setElective_Reason (int Elective_Reason);

	/** Get Elective Reason	  */
	public int getElective_Reason();

    /** Column name EncounterInfluenza */
    public static final String COLUMNNAME_EncounterInfluenza = "EncounterInfluenza";

	/** Set Encounter Influenza	  */
	public void setEncounterInfluenza (int EncounterInfluenza);

	/** Get Encounter Influenza	  */
	public int getEncounterInfluenza();

    /** Column name ESRD */
    public static final String COLUMNNAME_ESRD = "ESRD";

	/** Set ESRD	  */
	public void setESRD (int ESRD);

	/** Get ESRD	  */
	public int getESRD();

    /** Column name EXME_AnticoagulanteVte_ID */
    public static final String COLUMNNAME_EXME_AnticoagulanteVte_ID = "EXME_AnticoagulanteVte_ID";

	/** Set Anticoagulation VTE Therapy	  */
	public void setEXME_AnticoagulanteVte_ID (int EXME_AnticoagulanteVte_ID);

	/** Get Anticoagulation VTE Therapy	  */
	public int getEXME_AnticoagulanteVte_ID();

    /** Column name EXME_AtrialFin_ID */
    public static final String COLUMNNAME_EXME_AtrialFin_ID = "EXME_AtrialFin_ID";

	/** Set Final Diagnostic Atrial Fibrillation/Futter	  */
	public void setEXME_AtrialFin_ID (int EXME_AtrialFin_ID);

	/** Get Final Diagnostic Atrial Fibrillation/Futter	  */
	public int getEXME_AtrialFin_ID();

    /** Column name EXME_AtrialIni_ID */
    public static final String COLUMNNAME_EXME_AtrialIni_ID = "EXME_AtrialIni_ID";

	/** Set Initial Diagnostic Atrial fibrillation/futter	  */
	public void setEXME_AtrialIni_ID (int EXME_AtrialIni_ID);

	/** Get Initial Diagnostic Atrial fibrillation/futter	  */
	public int getEXME_AtrialIni_ID();

    /** Column name EXME_Diagnostico_Arritmia_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_Arritmia_ID = "EXME_Diagnostico_Arritmia_ID";

	/** Set Atrial Fibrillation/Flutter	  */
	public void setEXME_Diagnostico_Arritmia_ID (int EXME_Diagnostico_Arritmia_ID);

	/** Get Atrial Fibrillation/Flutter	  */
	public int getEXME_Diagnostico_Arritmia_ID();

    /** Column name EXME_Diagnostico_Derrame_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_Derrame_ID = "EXME_Diagnostico_Derrame_ID";

	/** Set Ischemic Stroke	  */
	public void setEXME_Diagnostico_Derrame_ID (int EXME_Diagnostico_Derrame_ID);

	/** Get Ischemic Stroke	  */
	public int getEXME_Diagnostico_Derrame_ID();

    /** Column name EXME_EstServ_Hosp_ID */
    public static final String COLUMNNAME_EXME_EstServ_Hosp_ID = "EXME_EstServ_Hosp_ID";

	/** Set Inpatient Service Station 	  */
	public void setEXME_EstServ_Hosp_ID (int EXME_EstServ_Hosp_ID);

	/** Get Inpatient Service Station 	  */
	public int getEXME_EstServ_Hosp_ID();

    /** Column name EXME_EstServIcu_ID */
    public static final String COLUMNNAME_EXME_EstServIcu_ID = "EXME_EstServIcu_ID";

	/** Set ICU Service Station	  */
	public void setEXME_EstServIcu_ID (int EXME_EstServIcu_ID);

	/** Get ICU Service Station	  */
	public int getEXME_EstServIcu_ID();

    /** Column name EXME_EstServ_Urgencias_ID */
    public static final String COLUMNNAME_EXME_EstServ_Urgencias_ID = "EXME_EstServ_Urgencias_ID";

	/** Set Emergency Service Station	  */
	public void setEXME_EstServ_Urgencias_ID (int EXME_EstServ_Urgencias_ID);

	/** Get Emergency Service Station	  */
	public int getEXME_EstServ_Urgencias_ID();

    /** Column name EXME_HemorragicFin_ID */
    public static final String COLUMNNAME_EXME_HemorragicFin_ID = "EXME_HemorragicFin_ID";

	/** Set Final Diagnostic Hemorragic Stroke	  */
	public void setEXME_HemorragicFin_ID (int EXME_HemorragicFin_ID);

	/** Get Final Diagnostic Hemorragic Stroke	  */
	public int getEXME_HemorragicFin_ID();

    /** Column name EXME_HemorragicIni_ID */
    public static final String COLUMNNAME_EXME_HemorragicIni_ID = "EXME_HemorragicIni_ID";

	/** Set Initial Diagnostic Hemorragic Stroke	  */
	public void setEXME_HemorragicIni_ID (int EXME_HemorragicIni_ID);

	/** Get Initial Diagnostic Hemorragic Stroke	  */
	public int getEXME_HemorragicIni_ID();

    /** Column name EXME_HypertensionFin_ID */
    public static final String COLUMNNAME_EXME_HypertensionFin_ID = "EXME_HypertensionFin_ID";

	/** Set Final Diagnostic Hypertension	  */
	public void setEXME_HypertensionFin_ID (int EXME_HypertensionFin_ID);

	/** Get Final Diagnostic Hypertension	  */
	public int getEXME_HypertensionFin_ID();

    /** Column name EXME_HypertensionIni_ID */
    public static final String COLUMNNAME_EXME_HypertensionIni_ID = "EXME_HypertensionIni_ID";

	/** Set Initial Diagnostic Hypertension	  */
	public void setEXME_HypertensionIni_ID (int EXME_HypertensionIni_ID);

	/** Get Initial Diagnostic Hypertension	  */
	public int getEXME_HypertensionIni_ID();

    /** Column name EXME_LdlFin_ID */
    public static final String COLUMNNAME_EXME_LdlFin_ID = "EXME_LdlFin_ID";

	/** Set Final CPT LDL	  */
	public void setEXME_LdlFin_ID (int EXME_LdlFin_ID);

	/** Get Final CPT LDL	  */
	public int getEXME_LdlFin_ID();

    /** Column name EXME_LdlIni_ID */
    public static final String COLUMNNAME_EXME_LdlIni_ID = "EXME_LdlIni_ID";

	/** Set Initial CPT LDL	  */
	public void setEXME_LdlIni_ID (int EXME_LdlIni_ID);

	/** Get Initial CPT LDL	  */
	public int getEXME_LdlIni_ID();

    /** Column name EXME_Param_Metricas_ID */
    public static final String COLUMNNAME_EXME_Param_Metricas_ID = "EXME_Param_Metricas_ID";

	/** Set Metrics parameter	  */
	public void setEXME_Param_Metricas_ID (int EXME_Param_Metricas_ID);

	/** Get Metrics parameter	  */
	public int getEXME_Param_Metricas_ID();

    /** Column name EXME_PlaquetaFin_ID */
    public static final String COLUMNNAME_EXME_PlaquetaFin_ID = "EXME_PlaquetaFin_ID";

	/** Set Final CPT Platelet Counts Monitored 	  */
	public void setEXME_PlaquetaFin_ID (int EXME_PlaquetaFin_ID);

	/** Get Final CPT Platelet Counts Monitored 	  */
	public int getEXME_PlaquetaFin_ID();

    /** Column name EXME_PlaquetaIni_ID */
    public static final String COLUMNNAME_EXME_PlaquetaIni_ID = "EXME_PlaquetaIni_ID";

	/** Set Initial CPT Platelet Counts Monitored 	  */
	public void setEXME_PlaquetaIni_ID (int EXME_PlaquetaIni_ID);

	/** Get Initial CPT Platelet Counts Monitored 	  */
	public int getEXME_PlaquetaIni_ID();

    /** Column name EXME_ProphylaxisVte_ID */
    public static final String COLUMNNAME_EXME_ProphylaxisVte_ID = "EXME_ProphylaxisVte_ID";

	/** Set VTE prophylaxis	  */
	public void setEXME_ProphylaxisVte_ID (int EXME_ProphylaxisVte_ID);

	/** Get VTE prophylaxis	  */
	public int getEXME_ProphylaxisVte_ID();

    /** Column name EXME_RehabilitaFin_ID */
    public static final String COLUMNNAME_EXME_RehabilitaFin_ID = "EXME_RehabilitaFin_ID";

	/** Set Final CPT Rehabilitation Services	  */
	public void setEXME_RehabilitaFin_ID (int EXME_RehabilitaFin_ID);

	/** Get Final CPT Rehabilitation Services	  */
	public int getEXME_RehabilitaFin_ID();

    /** Column name EXME_RehabilitaIni_ID */
    public static final String COLUMNNAME_EXME_RehabilitaIni_ID = "EXME_RehabilitaIni_ID";

	/** Set Initial CPT Rehabilitation Services	  */
	public void setEXME_RehabilitaIni_ID (int EXME_RehabilitaIni_ID);

	/** Get Initial CPT Rehabilitation Services	  */
	public int getEXME_RehabilitaIni_ID();

    /** Column name EXME_SchemicFin_ID */
    public static final String COLUMNNAME_EXME_SchemicFin_ID = "EXME_SchemicFin_ID";

	/** Set Final Diagnostic Ischemic Stroke	  */
	public void setEXME_SchemicFin_ID (int EXME_SchemicFin_ID);

	/** Get Final Diagnostic Ischemic Stroke	  */
	public int getEXME_SchemicFin_ID();

    /** Column name EXME_SchemicIni_ID */
    public static final String COLUMNNAME_EXME_SchemicIni_ID = "EXME_SchemicIni_ID";

	/** Set Initial Diagnostic Ischemic Stroke	  */
	public void setEXME_SchemicIni_ID (int EXME_SchemicIni_ID);

	/** Get Initial Diagnostic Ischemic Stroke	  */
	public int getEXME_SchemicIni_ID();

    /** Column name EXME_SignoVitalDiastolic_ID */
    public static final String COLUMNNAME_EXME_SignoVitalDiastolic_ID = "EXME_SignoVitalDiastolic_ID";

	/** Set Diastolic Vital Sign	  */
	public void setEXME_SignoVitalDiastolic_ID (int EXME_SignoVitalDiastolic_ID);

	/** Get Diastolic Vital Sign	  */
	public int getEXME_SignoVitalDiastolic_ID();

    /** Column name EXME_SignoVitalSystolic_ID */
    public static final String COLUMNNAME_EXME_SignoVitalSystolic_ID = "EXME_SignoVitalSystolic_ID";

	/** Set Systolic Vital Sign	  */
	public void setEXME_SignoVitalSystolic_ID (int EXME_SignoVitalSystolic_ID);

	/** Get Systolic Vital Sign	  */
	public int getEXME_SignoVitalSystolic_ID();

    /** Column name EXME_TerapiaAnticoagulante_ID */
    public static final String COLUMNNAME_EXME_TerapiaAnticoagulante_ID = "EXME_TerapiaAnticoagulante_ID";

	/** Set Anticoagulation Therapy	  */
	public void setEXME_TerapiaAnticoagulante_ID (int EXME_TerapiaAnticoagulante_ID);

	/** Get Anticoagulation Therapy	  */
	public int getEXME_TerapiaAnticoagulante_ID();

    /** Column name EXME_TerapiaAntitrombotic_ID */
    public static final String COLUMNNAME_EXME_TerapiaAntitrombotic_ID = "EXME_TerapiaAntitrombotic_ID";

	/** Set Antithrombolytic Therapy	  */
	public void setEXME_TerapiaAntitrombotic_ID (int EXME_TerapiaAntitrombotic_ID);

	/** Get Antithrombolytic Therapy	  */
	public int getEXME_TerapiaAntitrombotic_ID();

    /** Column name EXME_TerapiaHeparin_ID */
    public static final String COLUMNNAME_EXME_TerapiaHeparin_ID = "EXME_TerapiaHeparin_ID";

	/** Set Heparin (UFH) Therapy	  */
	public void setEXME_TerapiaHeparin_ID (int EXME_TerapiaHeparin_ID);

	/** Get Heparin (UFH) Therapy	  */
	public int getEXME_TerapiaHeparin_ID();

    /** Column name EXME_TerapiaStatin_ID */
    public static final String COLUMNNAME_EXME_TerapiaStatin_ID = "EXME_TerapiaStatin_ID";

	/** Set Statin Medication	  */
	public void setEXME_TerapiaStatin_ID (int EXME_TerapiaStatin_ID);

	/** Get Statin Medication	  */
	public int getEXME_TerapiaStatin_ID();

    /** Column name EXME_TerapiaThrombolitic_ID */
    public static final String COLUMNNAME_EXME_TerapiaThrombolitic_ID = "EXME_TerapiaThrombolitic_ID";

	/** Set THROMBOLYTIC (T-PA) Therapy	  */
	public void setEXME_TerapiaThrombolitic_ID (int EXME_TerapiaThrombolitic_ID);

	/** Get THROMBOLYTIC (T-PA) Therapy	  */
	public int getEXME_TerapiaThrombolitic_ID();

    /** Column name EXME_TerapiaWarafina_ID */
    public static final String COLUMNNAME_EXME_TerapiaWarafina_ID = "EXME_TerapiaWarafina_ID";

	/** Set Warfarin Therapy	  */
	public void setEXME_TerapiaWarafina_ID (int EXME_TerapiaWarafina_ID);

	/** Get Warfarin Therapy	  */
	public int getEXME_TerapiaWarafina_ID();

    /** Column name EXME_VacunaAcellular_ID */
    public static final String COLUMNNAME_EXME_VacunaAcellular_ID = "EXME_VacunaAcellular_ID";

	/** Set Acellular Vaccine	  */
	public void setEXME_VacunaAcellular_ID (int EXME_VacunaAcellular_ID);

	/** Get Acellular Vaccine	  */
	public int getEXME_VacunaAcellular_ID();

    /** Column name EXME_VacunaChickenPox_ID */
    public static final String COLUMNNAME_EXME_VacunaChickenPox_ID = "EXME_VacunaChickenPox_ID";

	/** Set Chicken Pox (VZV) Vaccine 	  */
	public void setEXME_VacunaChickenPox_ID (int EXME_VacunaChickenPox_ID);

	/** Get Chicken Pox (VZV) Vaccine 	  */
	public int getEXME_VacunaChickenPox_ID();

    /** Column name EXME_VacunaDiphteria_ID */
    public static final String COLUMNNAME_EXME_VacunaDiphteria_ID = "EXME_VacunaDiphteria_ID";

	/** Set Diphteria Vaccine	  */
	public void setEXME_VacunaDiphteria_ID (int EXME_VacunaDiphteria_ID);

	/** Get Diphteria Vaccine	  */
	public int getEXME_VacunaDiphteria_ID();

    /** Column name EXME_VacunaFlu_ID */
    public static final String COLUMNNAME_EXME_VacunaFlu_ID = "EXME_VacunaFlu_ID";

	/** Set Influenza (Flu) Vaccine	  */
	public void setEXME_VacunaFlu_ID (int EXME_VacunaFlu_ID);

	/** Get Influenza (Flu) Vaccine	  */
	public int getEXME_VacunaFlu_ID();

    /** Column name EXME_VacunaHepatitisA_ID */
    public static final String COLUMNNAME_EXME_VacunaHepatitisA_ID = "EXME_VacunaHepatitisA_ID";

	/** Set Hepatitis A Vaccine	  */
	public void setEXME_VacunaHepatitisA_ID (int EXME_VacunaHepatitisA_ID);

	/** Get Hepatitis A Vaccine	  */
	public int getEXME_VacunaHepatitisA_ID();

    /** Column name EXME_VacunaHepatitisB_ID */
    public static final String COLUMNNAME_EXME_VacunaHepatitisB_ID = "EXME_VacunaHepatitisB_ID";

	/** Set Hepatitis B Vaccine	  */
	public void setEXME_VacunaHepatitisB_ID (int EXME_VacunaHepatitisB_ID);

	/** Get Hepatitis B Vaccine	  */
	public int getEXME_VacunaHepatitisB_ID();

    /** Column name EXME_VacunaInfluenzaB_ID */
    public static final String COLUMNNAME_EXME_VacunaInfluenzaB_ID = "EXME_VacunaInfluenzaB_ID";

	/** Set Influenza Type B Vaccine	  */
	public void setEXME_VacunaInfluenzaB_ID (int EXME_VacunaInfluenzaB_ID);

	/** Get Influenza Type B Vaccine	  */
	public int getEXME_VacunaInfluenzaB_ID();

    /** Column name EXME_VacunaInfluenza_ID */
    public static final String COLUMNNAME_EXME_VacunaInfluenza_ID = "EXME_VacunaInfluenza_ID";

	/** Set Influenza Vaccine	  */
	public void setEXME_VacunaInfluenza_ID (int EXME_VacunaInfluenza_ID);

	/** Get Influenza Vaccine	  */
	public int getEXME_VacunaInfluenza_ID();

    /** Column name EXME_VacunaMeasles_ID */
    public static final String COLUMNNAME_EXME_VacunaMeasles_ID = "EXME_VacunaMeasles_ID";

	/** Set Measles Vaccine	  */
	public void setEXME_VacunaMeasles_ID (int EXME_VacunaMeasles_ID);

	/** Get Measles Vaccine	  */
	public int getEXME_VacunaMeasles_ID();

    /** Column name EXME_VacunaMumps_ID */
    public static final String COLUMNNAME_EXME_VacunaMumps_ID = "EXME_VacunaMumps_ID";

	/** Set Mumps Vaccine	  */
	public void setEXME_VacunaMumps_ID (int EXME_VacunaMumps_ID);

	/** Get Mumps Vaccine	  */
	public int getEXME_VacunaMumps_ID();

    /** Column name EXME_VacunaPneumococcal_ID */
    public static final String COLUMNNAME_EXME_VacunaPneumococcal_ID = "EXME_VacunaPneumococcal_ID";

	/** Set Pneumococcal Vaccine	  */
	public void setEXME_VacunaPneumococcal_ID (int EXME_VacunaPneumococcal_ID);

	/** Get Pneumococcal Vaccine	  */
	public int getEXME_VacunaPneumococcal_ID();

    /** Column name EXME_VacunaPneumonia_ID */
    public static final String COLUMNNAME_EXME_VacunaPneumonia_ID = "EXME_VacunaPneumonia_ID";

	/** Set Pneumonia Vaccine	  */
	public void setEXME_VacunaPneumonia_ID (int EXME_VacunaPneumonia_ID);

	/** Get Pneumonia Vaccine	  */
	public int getEXME_VacunaPneumonia_ID();

    /** Column name EXME_VacunaPolio_ID */
    public static final String COLUMNNAME_EXME_VacunaPolio_ID = "EXME_VacunaPolio_ID";

	/** Set Polio Vaccine	  */
	public void setEXME_VacunaPolio_ID (int EXME_VacunaPolio_ID);

	/** Get Polio Vaccine	  */
	public int getEXME_VacunaPolio_ID();

    /** Column name EXME_VacunaRotavirus_ID */
    public static final String COLUMNNAME_EXME_VacunaRotavirus_ID = "EXME_VacunaRotavirus_ID";

	/** Set Rotavirus Vaccine	  */
	public void setEXME_VacunaRotavirus_ID (int EXME_VacunaRotavirus_ID);

	/** Get Rotavirus Vaccine	  */
	public int getEXME_VacunaRotavirus_ID();

    /** Column name EXME_VacunaRubella_ID */
    public static final String COLUMNNAME_EXME_VacunaRubella_ID = "EXME_VacunaRubella_ID";

	/** Set Rubella Vaccine	  */
	public void setEXME_VacunaRubella_ID (int EXME_VacunaRubella_ID);

	/** Get Rubella Vaccine	  */
	public int getEXME_VacunaRubella_ID();

    /** Column name EXME_VacunaTetanus_ID */
    public static final String COLUMNNAME_EXME_VacunaTetanus_ID = "EXME_VacunaTetanus_ID";

	/** Set Tetanus Vaccine	  */
	public void setEXME_VacunaTetanus_ID (int EXME_VacunaTetanus_ID);

	/** Get Tetanus Vaccine	  */
	public int getEXME_VacunaTetanus_ID();

    /** Column name EXME_VteFin_ID */
    public static final String COLUMNNAME_EXME_VteFin_ID = "EXME_VteFin_ID";

	/** Set Final Diagnostic VTE	  */
	public void setEXME_VteFin_ID (int EXME_VteFin_ID);

	/** Get Final Diagnostic VTE	  */
	public int getEXME_VteFin_ID();

    /** Column name EXME_VteIni_ID */
    public static final String COLUMNNAME_EXME_VteIni_ID = "EXME_VteIni_ID";

	/** Set Initial Diagnostic VTE	  */
	public void setEXME_VteIni_ID (int EXME_VteIni_ID);

	/** Get Initial Diagnostic VTE	  */
	public int getEXME_VteIni_ID();

    /** Column name Fecha_Fin_Breast */
    public static final String COLUMNNAME_Fecha_Fin_Breast = "Fecha_Fin_Breast";

	/** Set Final Date Breast Cancer	  */
	public void setFecha_Fin_Breast (Timestamp Fecha_Fin_Breast);

	/** Get Final Date Breast Cancer	  */
	public Timestamp getFecha_Fin_Breast();

    /** Column name Fecha_Fin_Colorectal */
    public static final String COLUMNNAME_Fecha_Fin_Colorectal = "Fecha_Fin_Colorectal";

	/** Set Final Date Colorectal Cancer	  */
	public void setFecha_Fin_Colorectal (Timestamp Fecha_Fin_Colorectal);

	/** Get Final Date Colorectal Cancer	  */
	public Timestamp getFecha_Fin_Colorectal();

    /** Column name Fecha_Fin_Hypertension */
    public static final String COLUMNNAME_Fecha_Fin_Hypertension = "Fecha_Fin_Hypertension";

	/** Set Final Date Hypertension	  */
	public void setFecha_Fin_Hypertension (Timestamp Fecha_Fin_Hypertension);

	/** Get Final Date Hypertension	  */
	public Timestamp getFecha_Fin_Hypertension();

    /** Column name Fecha_Fin_Imc */
    public static final String COLUMNNAME_Fecha_Fin_Imc = "Fecha_Fin_Imc";

	/** Set Final Date IMC	  */
	public void setFecha_Fin_Imc (Timestamp Fecha_Fin_Imc);

	/** Get Final Date IMC	  */
	public Timestamp getFecha_Fin_Imc();

    /** Column name Fecha_Fin_Influenza */
    public static final String COLUMNNAME_Fecha_Fin_Influenza = "Fecha_Fin_Influenza";

	/** Set Final Date Influenza	  */
	public void setFecha_Fin_Influenza (Timestamp Fecha_Fin_Influenza);

	/** Get Final Date Influenza	  */
	public Timestamp getFecha_Fin_Influenza();

    /** Column name Fecha_Fin_Neumonia */
    public static final String COLUMNNAME_Fecha_Fin_Neumonia = "Fecha_Fin_Neumonia";

	/** Set Final Date Pneumonia	  */
	public void setFecha_Fin_Neumonia (Timestamp Fecha_Fin_Neumonia);

	/** Get Final Date Pneumonia	  */
	public Timestamp getFecha_Fin_Neumonia();

    /** Column name Fecha_Fin_NQF1 */
    public static final String COLUMNNAME_Fecha_Fin_NQF1 = "Fecha_Fin_NQF1";

	/** Set Fecha Fin NQF 1	  */
	public void setFecha_Fin_NQF1 (Timestamp Fecha_Fin_NQF1);

	/** Get Fecha Fin NQF 1	  */
	public Timestamp getFecha_Fin_NQF1();

    /** Column name Fecha_Fin_NQF18 */
    public static final String COLUMNNAME_Fecha_Fin_NQF18 = "Fecha_Fin_NQF18";

	/** Set Fecha Fin NQF 18	  */
	public void setFecha_Fin_NQF18 (Timestamp Fecha_Fin_NQF18);

	/** Get Fecha Fin NQF 18	  */
	public Timestamp getFecha_Fin_NQF18();

    /** Column name Fecha_Fin_NQF27 */
    public static final String COLUMNNAME_Fecha_Fin_NQF27 = "Fecha_Fin_NQF27";

	/** Set Fecha Fin NQF 27	  */
	public void setFecha_Fin_NQF27 (Timestamp Fecha_Fin_NQF27);

	/** Get Fecha Fin NQF 27	  */
	public Timestamp getFecha_Fin_NQF27();

    /** Column name Fecha_Fin_NQF31 */
    public static final String COLUMNNAME_Fecha_Fin_NQF31 = "Fecha_Fin_NQF31";

	/** Set Fecha Fin NQF 31	  */
	public void setFecha_Fin_NQF31 (Timestamp Fecha_Fin_NQF31);

	/** Get Fecha Fin NQF 31	  */
	public Timestamp getFecha_Fin_NQF31();

    /** Column name Fecha_Fin_NQF43 */
    public static final String COLUMNNAME_Fecha_Fin_NQF43 = "Fecha_Fin_NQF43";

	/** Set Fecha Fin NQF 43	  */
	public void setFecha_Fin_NQF43 (Timestamp Fecha_Fin_NQF43);

	/** Get Fecha Fin NQF 43	  */
	public Timestamp getFecha_Fin_NQF43();

    /** Column name Fecha_Fin_NQF52 */
    public static final String COLUMNNAME_Fecha_Fin_NQF52 = "Fecha_Fin_NQF52";

	/** Set Fecha Fin NQF 52	  */
	public void setFecha_Fin_NQF52 (Timestamp Fecha_Fin_NQF52);

	/** Get Fecha Fin NQF 52	  */
	public Timestamp getFecha_Fin_NQF52();

    /** Column name Fecha_Fin_NQF59 */
    public static final String COLUMNNAME_Fecha_Fin_NQF59 = "Fecha_Fin_NQF59";

	/** Set Fecha Fin NQF 59	  */
	public void setFecha_Fin_NQF59 (Timestamp Fecha_Fin_NQF59);

	/** Get Fecha Fin NQF 59	  */
	public Timestamp getFecha_Fin_NQF59();

    /** Column name Fecha_Fin_NQF67 */
    public static final String COLUMNNAME_Fecha_Fin_NQF67 = "Fecha_Fin_NQF67";

	/** Set Fecha Fin NQF 67	  */
	public void setFecha_Fin_NQF67 (Timestamp Fecha_Fin_NQF67);

	/** Get Fecha Fin NQF 67	  */
	public Timestamp getFecha_Fin_NQF67();

    /** Column name Fecha_Fin_Peso */
    public static final String COLUMNNAME_Fecha_Fin_Peso = "Fecha_Fin_Peso";

	/** Set Final Date Weight	  */
	public void setFecha_Fin_Peso (Timestamp Fecha_Fin_Peso);

	/** Get Final Date Weight	  */
	public Timestamp getFecha_Fin_Peso();

    /** Column name Fecha_Fin_Smoke */
    public static final String COLUMNNAME_Fecha_Fin_Smoke = "Fecha_Fin_Smoke";

	/** Set Final Date Smoke Status	  */
	public void setFecha_Fin_Smoke (Timestamp Fecha_Fin_Smoke);

	/** Get Final Date Smoke Status	  */
	public Timestamp getFecha_Fin_Smoke();

    /** Column name Fecha_Fin_Stroke10 */
    public static final String COLUMNNAME_Fecha_Fin_Stroke10 = "Fecha_Fin_Stroke10";

	/** Set Date End (S10)	  */
	public void setFecha_Fin_Stroke10 (Timestamp Fecha_Fin_Stroke10);

	/** Get Date End (S10)	  */
	public Timestamp getFecha_Fin_Stroke10();

    /** Column name Fecha_Fin_Stroke4 */
    public static final String COLUMNNAME_Fecha_Fin_Stroke4 = "Fecha_Fin_Stroke4";

	/** Set Date End (S4)	  */
	public void setFecha_Fin_Stroke4 (Timestamp Fecha_Fin_Stroke4);

	/** Get Date End (S4)	  */
	public Timestamp getFecha_Fin_Stroke4();

    /** Column name Fecha_Fin_Stroke5 */
    public static final String COLUMNNAME_Fecha_Fin_Stroke5 = "Fecha_Fin_Stroke5";

	/** Set Date End (S5)	  */
	public void setFecha_Fin_Stroke5 (Timestamp Fecha_Fin_Stroke5);

	/** Get Date End (S5)	  */
	public Timestamp getFecha_Fin_Stroke5();

    /** Column name Fecha_Fin_Stroke6 */
    public static final String COLUMNNAME_Fecha_Fin_Stroke6 = "Fecha_Fin_Stroke6";

	/** Set Date End (S6)	  */
	public void setFecha_Fin_Stroke6 (Timestamp Fecha_Fin_Stroke6);

	/** Get Date End (S6)	  */
	public Timestamp getFecha_Fin_Stroke6();

    /** Column name Fecha_Fin_Stroke8 */
    public static final String COLUMNNAME_Fecha_Fin_Stroke8 = "Fecha_Fin_Stroke8";

	/** Set Date End (S8)	  */
	public void setFecha_Fin_Stroke8 (Timestamp Fecha_Fin_Stroke8);

	/** Get Date End (S8)	  */
	public Timestamp getFecha_Fin_Stroke8();

    /** Column name Fecha_Fin_Tabaco */
    public static final String COLUMNNAME_Fecha_Fin_Tabaco = "Fecha_Fin_Tabaco";

	/** Set Final Date Tobbaco	  */
	public void setFecha_Fin_Tabaco (Timestamp Fecha_Fin_Tabaco);

	/** Get Final Date Tobbaco	  */
	public Timestamp getFecha_Fin_Tabaco();

    /** Column name Fecha_Fin_Urgencia */
    public static final String COLUMNNAME_Fecha_Fin_Urgencia = "Fecha_Fin_Urgencia";

	/** Set Date End Emergency	  */
	public void setFecha_Fin_Urgencia (Timestamp Fecha_Fin_Urgencia);

	/** Get Date End Emergency	  */
	public Timestamp getFecha_Fin_Urgencia();

    /** Column name Fecha_Fin_Urgencia_ED */
    public static final String COLUMNNAME_Fecha_Fin_Urgencia_ED = "Fecha_Fin_Urgencia_ED";

	/** Set Date End (ED)	  */
	public void setFecha_Fin_Urgencia_ED (Timestamp Fecha_Fin_Urgencia_ED);

	/** Get Date End (ED)	  */
	public Timestamp getFecha_Fin_Urgencia_ED();

    /** Column name Fecha_Fin_Urgencia_S2 */
    public static final String COLUMNNAME_Fecha_Fin_Urgencia_S2 = "Fecha_Fin_Urgencia_S2";

	/** Set Date End (S2)	  */
	public void setFecha_Fin_Urgencia_S2 (Timestamp Fecha_Fin_Urgencia_S2);

	/** Get Date End (S2)	  */
	public Timestamp getFecha_Fin_Urgencia_S2();

    /** Column name Fecha_Fin_Urgencia_S3 */
    public static final String COLUMNNAME_Fecha_Fin_Urgencia_S3 = "Fecha_Fin_Urgencia_S3";

	/** Set Date End (S3)	  */
	public void setFecha_Fin_Urgencia_S3 (Timestamp Fecha_Fin_Urgencia_S3);

	/** Get Date End (S3)	  */
	public Timestamp getFecha_Fin_Urgencia_S3();

    /** Column name Fecha_Fin_Vacunas */
    public static final String COLUMNNAME_Fecha_Fin_Vacunas = "Fecha_Fin_Vacunas";

	/** Set Final Date Vaccine	  */
	public void setFecha_Fin_Vacunas (Timestamp Fecha_Fin_Vacunas);

	/** Get Final Date Vaccine	  */
	public Timestamp getFecha_Fin_Vacunas();

    /** Column name Fecha_Fin_Vte1 */
    public static final String COLUMNNAME_Fecha_Fin_Vte1 = "Fecha_Fin_Vte1";

	/** Set Date End (V1)	  */
	public void setFecha_Fin_Vte1 (Timestamp Fecha_Fin_Vte1);

	/** Get Date End (V1)	  */
	public Timestamp getFecha_Fin_Vte1();

    /** Column name Fecha_Fin_Vte2 */
    public static final String COLUMNNAME_Fecha_Fin_Vte2 = "Fecha_Fin_Vte2";

	/** Set Date End (V2)	  */
	public void setFecha_Fin_Vte2 (Timestamp Fecha_Fin_Vte2);

	/** Get Date End (V2)	  */
	public Timestamp getFecha_Fin_Vte2();

    /** Column name Fecha_Fin_Vte3 */
    public static final String COLUMNNAME_Fecha_Fin_Vte3 = "Fecha_Fin_Vte3";

	/** Set Date End (V3)	  */
	public void setFecha_Fin_Vte3 (Timestamp Fecha_Fin_Vte3);

	/** Get Date End (V3)	  */
	public Timestamp getFecha_Fin_Vte3();

    /** Column name Fecha_Fin_Vte4 */
    public static final String COLUMNNAME_Fecha_Fin_Vte4 = "Fecha_Fin_Vte4";

	/** Set Date End (V4)	  */
	public void setFecha_Fin_Vte4 (Timestamp Fecha_Fin_Vte4);

	/** Get Date End (V4)	  */
	public Timestamp getFecha_Fin_Vte4();

    /** Column name Fecha_Fin_Vte5 */
    public static final String COLUMNNAME_Fecha_Fin_Vte5 = "Fecha_Fin_Vte5";

	/** Set Date End (V5)	  */
	public void setFecha_Fin_Vte5 (Timestamp Fecha_Fin_Vte5);

	/** Get Date End (V5)	  */
	public Timestamp getFecha_Fin_Vte5();

    /** Column name Fecha_Fin_Vte6 */
    public static final String COLUMNNAME_Fecha_Fin_Vte6 = "Fecha_Fin_Vte6";

	/** Set Date End (V6)	  */
	public void setFecha_Fin_Vte6 (Timestamp Fecha_Fin_Vte6);

	/** Get Date End (V6)	  */
	public Timestamp getFecha_Fin_Vte6();

    /** Column name Fecha_Ini_Breast */
    public static final String COLUMNNAME_Fecha_Ini_Breast = "Fecha_Ini_Breast";

	/** Set Initial Date Breast Cancer	  */
	public void setFecha_Ini_Breast (Timestamp Fecha_Ini_Breast);

	/** Get Initial Date Breast Cancer	  */
	public Timestamp getFecha_Ini_Breast();

    /** Column name Fecha_Ini_Colorectal */
    public static final String COLUMNNAME_Fecha_Ini_Colorectal = "Fecha_Ini_Colorectal";

	/** Set Initial Date Colorectal Cancer	  */
	public void setFecha_Ini_Colorectal (Timestamp Fecha_Ini_Colorectal);

	/** Get Initial Date Colorectal Cancer	  */
	public Timestamp getFecha_Ini_Colorectal();

    /** Column name Fecha_Ini_Hypertension */
    public static final String COLUMNNAME_Fecha_Ini_Hypertension = "Fecha_Ini_Hypertension";

	/** Set Initial Date Hypertension	  */
	public void setFecha_Ini_Hypertension (Timestamp Fecha_Ini_Hypertension);

	/** Get Initial Date Hypertension	  */
	public Timestamp getFecha_Ini_Hypertension();

    /** Column name Fecha_Ini_Imc */
    public static final String COLUMNNAME_Fecha_Ini_Imc = "Fecha_Ini_Imc";

	/** Set Initial Date IMC	  */
	public void setFecha_Ini_Imc (Timestamp Fecha_Ini_Imc);

	/** Get Initial Date IMC	  */
	public Timestamp getFecha_Ini_Imc();

    /** Column name Fecha_Ini_Influenza */
    public static final String COLUMNNAME_Fecha_Ini_Influenza = "Fecha_Ini_Influenza";

	/** Set Initial Date Influenza	  */
	public void setFecha_Ini_Influenza (Timestamp Fecha_Ini_Influenza);

	/** Get Initial Date Influenza	  */
	public Timestamp getFecha_Ini_Influenza();

    /** Column name Fecha_Ini_Neumonia */
    public static final String COLUMNNAME_Fecha_Ini_Neumonia = "Fecha_Ini_Neumonia";

	/** Set Initial Date Pneumonia	  */
	public void setFecha_Ini_Neumonia (Timestamp Fecha_Ini_Neumonia);

	/** Get Initial Date Pneumonia	  */
	public Timestamp getFecha_Ini_Neumonia();

    /** Column name Fecha_Ini_NQF1 */
    public static final String COLUMNNAME_Fecha_Ini_NQF1 = "Fecha_Ini_NQF1";

	/** Set Fecha Ini NQF 1	  */
	public void setFecha_Ini_NQF1 (Timestamp Fecha_Ini_NQF1);

	/** Get Fecha Ini NQF 1	  */
	public Timestamp getFecha_Ini_NQF1();

    /** Column name Fecha_Ini_NQF18 */
    public static final String COLUMNNAME_Fecha_Ini_NQF18 = "Fecha_Ini_NQF18";

	/** Set Fecha Ini NQF 18	  */
	public void setFecha_Ini_NQF18 (Timestamp Fecha_Ini_NQF18);

	/** Get Fecha Ini NQF 18	  */
	public Timestamp getFecha_Ini_NQF18();

    /** Column name Fecha_Ini_NQF27 */
    public static final String COLUMNNAME_Fecha_Ini_NQF27 = "Fecha_Ini_NQF27";

	/** Set Fecha Ini NQF 27	  */
	public void setFecha_Ini_NQF27 (Timestamp Fecha_Ini_NQF27);

	/** Get Fecha Ini NQF 27	  */
	public Timestamp getFecha_Ini_NQF27();

    /** Column name Fecha_Ini_NQF31 */
    public static final String COLUMNNAME_Fecha_Ini_NQF31 = "Fecha_Ini_NQF31";

	/** Set Fecha Ini NQF 31	  */
	public void setFecha_Ini_NQF31 (Timestamp Fecha_Ini_NQF31);

	/** Get Fecha Ini NQF 31	  */
	public Timestamp getFecha_Ini_NQF31();

    /** Column name Fecha_Ini_NQF43 */
    public static final String COLUMNNAME_Fecha_Ini_NQF43 = "Fecha_Ini_NQF43";

	/** Set Fecha Ini NQF 43	  */
	public void setFecha_Ini_NQF43 (Timestamp Fecha_Ini_NQF43);

	/** Get Fecha Ini NQF 43	  */
	public Timestamp getFecha_Ini_NQF43();

    /** Column name Fecha_Ini_NQF52 */
    public static final String COLUMNNAME_Fecha_Ini_NQF52 = "Fecha_Ini_NQF52";

	/** Set Fecha Ini NQF 52	  */
	public void setFecha_Ini_NQF52 (Timestamp Fecha_Ini_NQF52);

	/** Get Fecha Ini NQF 52	  */
	public Timestamp getFecha_Ini_NQF52();

    /** Column name Fecha_Ini_NQF59 */
    public static final String COLUMNNAME_Fecha_Ini_NQF59 = "Fecha_Ini_NQF59";

	/** Set Fecha Ini NQF 59	  */
	public void setFecha_Ini_NQF59 (Timestamp Fecha_Ini_NQF59);

	/** Get Fecha Ini NQF 59	  */
	public Timestamp getFecha_Ini_NQF59();

    /** Column name Fecha_Ini_NQF67 */
    public static final String COLUMNNAME_Fecha_Ini_NQF67 = "Fecha_Ini_NQF67";

	/** Set Fecha Ini NQF 67	  */
	public void setFecha_Ini_NQF67 (Timestamp Fecha_Ini_NQF67);

	/** Get Fecha Ini NQF 67	  */
	public Timestamp getFecha_Ini_NQF67();

    /** Column name Fecha_Ini_Peso */
    public static final String COLUMNNAME_Fecha_Ini_Peso = "Fecha_Ini_Peso";

	/** Set Initial Date Weight	  */
	public void setFecha_Ini_Peso (Timestamp Fecha_Ini_Peso);

	/** Get Initial Date Weight	  */
	public Timestamp getFecha_Ini_Peso();

    /** Column name Fecha_Ini_Smoke */
    public static final String COLUMNNAME_Fecha_Ini_Smoke = "Fecha_Ini_Smoke";

	/** Set Initial Date Smoke	  */
	public void setFecha_Ini_Smoke (Timestamp Fecha_Ini_Smoke);

	/** Get Initial Date Smoke	  */
	public Timestamp getFecha_Ini_Smoke();

    /** Column name Fecha_Ini_Stroke10 */
    public static final String COLUMNNAME_Fecha_Ini_Stroke10 = "Fecha_Ini_Stroke10";

	/** Set Date Initial (S10)	  */
	public void setFecha_Ini_Stroke10 (Timestamp Fecha_Ini_Stroke10);

	/** Get Date Initial (S10)	  */
	public Timestamp getFecha_Ini_Stroke10();

    /** Column name Fecha_Ini_Stroke4 */
    public static final String COLUMNNAME_Fecha_Ini_Stroke4 = "Fecha_Ini_Stroke4";

	/** Set Date Initial (S4)	  */
	public void setFecha_Ini_Stroke4 (Timestamp Fecha_Ini_Stroke4);

	/** Get Date Initial (S4)	  */
	public Timestamp getFecha_Ini_Stroke4();

    /** Column name Fecha_Ini_Stroke5 */
    public static final String COLUMNNAME_Fecha_Ini_Stroke5 = "Fecha_Ini_Stroke5";

	/** Set Date Initial (S5)	  */
	public void setFecha_Ini_Stroke5 (Timestamp Fecha_Ini_Stroke5);

	/** Get Date Initial (S5)	  */
	public Timestamp getFecha_Ini_Stroke5();

    /** Column name Fecha_Ini_Stroke6 */
    public static final String COLUMNNAME_Fecha_Ini_Stroke6 = "Fecha_Ini_Stroke6";

	/** Set Date Initial (S6)	  */
	public void setFecha_Ini_Stroke6 (Timestamp Fecha_Ini_Stroke6);

	/** Get Date Initial (S6)	  */
	public Timestamp getFecha_Ini_Stroke6();

    /** Column name Fecha_Ini_Stroke8 */
    public static final String COLUMNNAME_Fecha_Ini_Stroke8 = "Fecha_Ini_Stroke8";

	/** Set Date Initial (S8)	  */
	public void setFecha_Ini_Stroke8 (Timestamp Fecha_Ini_Stroke8);

	/** Get Date Initial (S8)	  */
	public Timestamp getFecha_Ini_Stroke8();

    /** Column name Fecha_Ini_Tabaco */
    public static final String COLUMNNAME_Fecha_Ini_Tabaco = "Fecha_Ini_Tabaco";

	/** Set Initial Date Tobacco	  */
	public void setFecha_Ini_Tabaco (Timestamp Fecha_Ini_Tabaco);

	/** Get Initial Date Tobacco	  */
	public Timestamp getFecha_Ini_Tabaco();

    /** Column name Fecha_Ini_Urgencia */
    public static final String COLUMNNAME_Fecha_Ini_Urgencia = "Fecha_Ini_Urgencia";

	/** Set Date Initial Emergency	  */
	public void setFecha_Ini_Urgencia (Timestamp Fecha_Ini_Urgencia);

	/** Get Date Initial Emergency	  */
	public Timestamp getFecha_Ini_Urgencia();

    /** Column name Fecha_Ini_Urgencia_ED */
    public static final String COLUMNNAME_Fecha_Ini_Urgencia_ED = "Fecha_Ini_Urgencia_ED";

	/** Set Date Initial Emergency (ED)	  */
	public void setFecha_Ini_Urgencia_ED (Timestamp Fecha_Ini_Urgencia_ED);

	/** Get Date Initial Emergency (ED)	  */
	public Timestamp getFecha_Ini_Urgencia_ED();

    /** Column name Fecha_Ini_Urgencia_S2 */
    public static final String COLUMNNAME_Fecha_Ini_Urgencia_S2 = "Fecha_Ini_Urgencia_S2";

	/** Set Date Initial Emergency (S2)	  */
	public void setFecha_Ini_Urgencia_S2 (Timestamp Fecha_Ini_Urgencia_S2);

	/** Get Date Initial Emergency (S2)	  */
	public Timestamp getFecha_Ini_Urgencia_S2();

    /** Column name Fecha_Ini_Urgencia_S3 */
    public static final String COLUMNNAME_Fecha_Ini_Urgencia_S3 = "Fecha_Ini_Urgencia_S3";

	/** Set Date Initial (S3)	  */
	public void setFecha_Ini_Urgencia_S3 (Timestamp Fecha_Ini_Urgencia_S3);

	/** Get Date Initial (S3)	  */
	public Timestamp getFecha_Ini_Urgencia_S3();

    /** Column name Fecha_Ini_Vacunas */
    public static final String COLUMNNAME_Fecha_Ini_Vacunas = "Fecha_Ini_Vacunas";

	/** Set Initial Date Vaccine	  */
	public void setFecha_Ini_Vacunas (Timestamp Fecha_Ini_Vacunas);

	/** Get Initial Date Vaccine	  */
	public Timestamp getFecha_Ini_Vacunas();

    /** Column name Fecha_Ini_Vte1 */
    public static final String COLUMNNAME_Fecha_Ini_Vte1 = "Fecha_Ini_Vte1";

	/** Set Date Initial (V1)	  */
	public void setFecha_Ini_Vte1 (Timestamp Fecha_Ini_Vte1);

	/** Get Date Initial (V1)	  */
	public Timestamp getFecha_Ini_Vte1();

    /** Column name Fecha_Ini_Vte2 */
    public static final String COLUMNNAME_Fecha_Ini_Vte2 = "Fecha_Ini_Vte2";

	/** Set Date Initial (V2)	  */
	public void setFecha_Ini_Vte2 (Timestamp Fecha_Ini_Vte2);

	/** Get Date Initial (V2)	  */
	public Timestamp getFecha_Ini_Vte2();

    /** Column name Fecha_Ini_Vte3 */
    public static final String COLUMNNAME_Fecha_Ini_Vte3 = "Fecha_Ini_Vte3";

	/** Set Date Initial (V3)	  */
	public void setFecha_Ini_Vte3 (Timestamp Fecha_Ini_Vte3);

	/** Get Date Initial (V3)	  */
	public Timestamp getFecha_Ini_Vte3();

    /** Column name Fecha_Ini_Vte4 */
    public static final String COLUMNNAME_Fecha_Ini_Vte4 = "Fecha_Ini_Vte4";

	/** Set Date Initial (V4)	  */
	public void setFecha_Ini_Vte4 (Timestamp Fecha_Ini_Vte4);

	/** Get Date Initial (V4)	  */
	public Timestamp getFecha_Ini_Vte4();

    /** Column name Fecha_Ini_Vte5 */
    public static final String COLUMNNAME_Fecha_Ini_Vte5 = "Fecha_Ini_Vte5";

	/** Set Date Initial (V5)	  */
	public void setFecha_Ini_Vte5 (Timestamp Fecha_Ini_Vte5);

	/** Get Date Initial (V5)	  */
	public Timestamp getFecha_Ini_Vte5();

    /** Column name Fecha_Ini_Vte6 */
    public static final String COLUMNNAME_Fecha_Ini_Vte6 = "Fecha_Ini_Vte6";

	/** Set Date Initial (V6)	  */
	public void setFecha_Ini_Vte6 (Timestamp Fecha_Ini_Vte6);

	/** Get Date Initial (V6)	  */
	public Timestamp getFecha_Ini_Vte6();

    /** Column name FollowPlan */
    public static final String COLUMNNAME_FollowPlan = "FollowPlan";

	/** Set Follow Plan BMI	  */
	public void setFollowPlan (int FollowPlan);

	/** Get Follow Plan BMI	  */
	public int getFollowPlan();

    /** Column name HBA1C */
    public static final String COLUMNNAME_HBA1C = "HBA1C";

	/** Set HBA1C	  */
	public void setHBA1C (int HBA1C);

	/** Get HBA1C	  */
	public int getHBA1C();

    /** Column name Hemorrhagic_Stroke */
    public static final String COLUMNNAME_Hemorrhagic_Stroke = "Hemorrhagic_Stroke";

	/** Set Hemorrhagic Stroke	  */
	public void setHemorrhagic_Stroke (int Hemorrhagic_Stroke);

	/** Get Hemorrhagic Stroke	  */
	public int getHemorrhagic_Stroke();

    /** Column name Hypertension */
    public static final String COLUMNNAME_Hypertension = "Hypertension";

	/** Set Hypertension	  */
	public void setHypertension (int Hypertension);

	/** Get Hypertension	  */
	public int getHypertension();

    /** Column name INR_Loinc */
    public static final String COLUMNNAME_INR_Loinc = "INR_Loinc";

	/** Set INR Loinc	  */
	public void setINR_Loinc (int INR_Loinc);

	/** Get INR Loinc	  */
	public int getINR_Loinc();

    /** Column name INR_Measure */
    public static final String COLUMNNAME_INR_Measure = "INR_Measure";

	/** Set INR Measure	  */
	public void setINR_Measure (int INR_Measure);

	/** Get INR Measure	  */
	public int getINR_Measure();

    /** Column name INR_Procedure */
    public static final String COLUMNNAME_INR_Procedure = "INR_Procedure";

	/** Set INR Procedure	  */
	public void setINR_Procedure (int INR_Procedure);

	/** Get INR Procedure	  */
	public int getINR_Procedure();

    /** Column name Ischemic_Stroke */
    public static final String COLUMNNAME_Ischemic_Stroke = "Ischemic_Stroke";

	/** Set Ischemic Stroke	  */
	public void setIschemic_Stroke (int Ischemic_Stroke);

	/** Get Ischemic Stroke	  */
	public int getIschemic_Stroke();

    /** Column name LDL_Measure */
    public static final String COLUMNNAME_LDL_Measure = "LDL_Measure";

	/** Set LDL Measure	  */
	public void setLDL_Measure (int LDL_Measure);

	/** Get LDL Measure	  */
	public int getLDL_Measure();

    /** Column name Lipid_Loinc */
    public static final String COLUMNNAME_Lipid_Loinc = "Lipid_Loinc";

	/** Set Lipid Loinc	  */
	public void setLipid_Loinc (int Lipid_Loinc);

	/** Get Lipid Loinc	  */
	public int getLipid_Loinc();

    /** Column name Lipid_Medication */
    public static final String COLUMNNAME_Lipid_Medication = "Lipid_Medication";

	/** Set Lipid Medication	  */
	public void setLipid_Medication (int Lipid_Medication);

	/** Get Lipid Medication	  */
	public int getLipid_Medication();

    /** Column name Lipid_Procedure */
    public static final String COLUMNNAME_Lipid_Procedure = "Lipid_Procedure";

	/** Set Lipid Procedure	  */
	public void setLipid_Procedure (int Lipid_Procedure);

	/** Get Lipid Procedure	  */
	public int getLipid_Procedure();

    /** Column name LowBackPain */
    public static final String COLUMNNAME_LowBackPain = "LowBackPain";

	/** Set Low Back Pain	  */
	public void setLowBackPain (int LowBackPain);

	/** Get Low Back Pain	  */
	public int getLowBackPain();

    /** Column name MentalPatient */
    public static final String COLUMNNAME_MentalPatient = "MentalPatient";

	/** Set Mental Patient	  */
	public void setMentalPatient (int MentalPatient);

	/** Get Mental Patient	  */
	public int getMentalPatient();

    /** Column name M_Product_Anticoagulante_ID */
    public static final String COLUMNNAME_M_Product_Anticoagulante_ID = "M_Product_Anticoagulante_ID";

	/** Set Anticoagulants	  */
	public void setM_Product_Anticoagulante_ID (int M_Product_Anticoagulante_ID);

	/** Get Anticoagulants	  */
	public int getM_Product_Anticoagulante_ID();

    /** Column name M_Product_Antitrombosis_ID */
    public static final String COLUMNNAME_M_Product_Antitrombosis_ID = "M_Product_Antitrombosis_ID";

	/** Set Antithrombotic	  */
	public void setM_Product_Antitrombosis_ID (int M_Product_Antitrombosis_ID);

	/** Get Antithrombotic	  */
	public int getM_Product_Antitrombosis_ID();

    /** Column name M_Product_BreastCancer_ID */
    public static final String COLUMNNAME_M_Product_BreastCancer_ID = "M_Product_BreastCancer_ID";

	/** Set Breast Cancer Screening	  */
	public void setM_Product_BreastCancer_ID (int M_Product_BreastCancer_ID);

	/** Get Breast Cancer Screening	  */
	public int getM_Product_BreastCancer_ID();

    /** Column name M_Product_ColorectalCancer_ID */
    public static final String COLUMNNAME_M_Product_ColorectalCancer_ID = "M_Product_ColorectalCancer_ID";

	/** Set Colorectal Cancer Screening	  */
	public void setM_Product_ColorectalCancer_ID (int M_Product_ColorectalCancer_ID);

	/** Get Colorectal Cancer Screening	  */
	public int getM_Product_ColorectalCancer_ID();

    /** Column name NumAcellular */
    public static final String COLUMNNAME_NumAcellular = "NumAcellular";

	/** Set Number Acellular Vaccines	  */
	public void setNumAcellular (int NumAcellular);

	/** Get Number Acellular Vaccines	  */
	public int getNumAcellular();

    /** Column name NumChickenPox */
    public static final String COLUMNNAME_NumChickenPox = "NumChickenPox";

	/** Set Number Chicken Pox Vaccines	  */
	public void setNumChickenPox (int NumChickenPox);

	/** Get Number Chicken Pox Vaccines	  */
	public int getNumChickenPox();

    /** Column name NumDiphteria */
    public static final String COLUMNNAME_NumDiphteria = "NumDiphteria";

	/** Set Number Diphteria Vaccines	  */
	public void setNumDiphteria (int NumDiphteria);

	/** Get Number Diphteria Vaccines	  */
	public int getNumDiphteria();

    /** Column name NumFlu */
    public static final String COLUMNNAME_NumFlu = "NumFlu";

	/** Set Number Influenza Flu Vaccines	  */
	public void setNumFlu (int NumFlu);

	/** Get Number Influenza Flu Vaccines	  */
	public int getNumFlu();

    /** Column name NumHepatitisA */
    public static final String COLUMNNAME_NumHepatitisA = "NumHepatitisA";

	/** Set Number Hepatitis A Vaccines	  */
	public void setNumHepatitisA (int NumHepatitisA);

	/** Get Number Hepatitis A Vaccines	  */
	public int getNumHepatitisA();

    /** Column name NumHepatitisB */
    public static final String COLUMNNAME_NumHepatitisB = "NumHepatitisB";

	/** Set Number Hepatitis B Vaccines	  */
	public void setNumHepatitisB (int NumHepatitisB);

	/** Get Number Hepatitis B Vaccines	  */
	public int getNumHepatitisB();

    /** Column name NumInfluenzaB */
    public static final String COLUMNNAME_NumInfluenzaB = "NumInfluenzaB";

	/** Set Number Influenza B Vaccines	  */
	public void setNumInfluenzaB (int NumInfluenzaB);

	/** Get Number Influenza B Vaccines	  */
	public int getNumInfluenzaB();

    /** Column name NumMeasles */
    public static final String COLUMNNAME_NumMeasles = "NumMeasles";

	/** Set Number Measles Vaccine	  */
	public void setNumMeasles (int NumMeasles);

	/** Get Number Measles Vaccine	  */
	public int getNumMeasles();

    /** Column name NumMumps */
    public static final String COLUMNNAME_NumMumps = "NumMumps";

	/** Set Number Mumps Vaccine	  */
	public void setNumMumps (int NumMumps);

	/** Get Number Mumps Vaccine	  */
	public int getNumMumps();

    /** Column name NumPneumococcal */
    public static final String COLUMNNAME_NumPneumococcal = "NumPneumococcal";

	/** Set Number Pneumococcal Vaccine	  */
	public void setNumPneumococcal (int NumPneumococcal);

	/** Get Number Pneumococcal Vaccine	  */
	public int getNumPneumococcal();

    /** Column name NumPolio */
    public static final String COLUMNNAME_NumPolio = "NumPolio";

	/** Set Number Polio Vaccines	  */
	public void setNumPolio (int NumPolio);

	/** Get Number Polio Vaccines	  */
	public int getNumPolio();

    /** Column name NumRotavirus */
    public static final String COLUMNNAME_NumRotavirus = "NumRotavirus";

	/** Set Number Rotavirus Vaccines	  */
	public void setNumRotavirus (int NumRotavirus);

	/** Get Number Rotavirus Vaccines	  */
	public int getNumRotavirus();

    /** Column name NumRubella */
    public static final String COLUMNNAME_NumRubella = "NumRubella";

	/** Set Number Rubella Vaccine	  */
	public void setNumRubella (int NumRubella);

	/** Get Number Rubella Vaccine	  */
	public int getNumRubella();

    /** Column name NumTetanus */
    public static final String COLUMNNAME_NumTetanus = "NumTetanus";

	/** Set Number Tetatus Vaccine	  */
	public void setNumTetanus (int NumTetanus);

	/** Get Number Tetatus Vaccine	  */
	public int getNumTetanus();

    /** Column name ObsPatient */
    public static final String COLUMNNAME_ObsPatient = "ObsPatient";

	/** Set Observation Patient	  */
	public void setObsPatient (int ObsPatient);

	/** Get Observation Patient	  */
	public int getObsPatient();

    /** Column name Obstretics */
    public static final String COLUMNNAME_Obstretics = "Obstretics";

	/** Set Obstretics.
	  * Obstretics
	  */
	public void setObstretics (int Obstretics);

	/** Get Obstretics.
	  * Obstretics
	  */
	public int getObstretics();

    /** Column name Outpatient */
    public static final String COLUMNNAME_Outpatient = "Outpatient";

	/** Set Outpatient	  */
	public void setOutpatient (int Outpatient);

	/** Get Outpatient	  */
	public int getOutpatient();

    /** Column name PhysicalActivity */
    public static final String COLUMNNAME_PhysicalActivity = "PhysicalActivity";

	/** Set Counseling for Physical Activity	  */
	public void setPhysicalActivity (int PhysicalActivity);

	/** Get Counseling for Physical Activity	  */
	public int getPhysicalActivity();

    /** Column name Platelet_Loinc */
    public static final String COLUMNNAME_Platelet_Loinc = "Platelet_Loinc";

	/** Set Platelet Loinc	  */
	public void setPlatelet_Loinc (int Platelet_Loinc);

	/** Get Platelet Loinc	  */
	public int getPlatelet_Loinc();

    /** Column name Platelet_Measure */
    public static final String COLUMNNAME_Platelet_Measure = "Platelet_Measure";

	/** Set Platelet Measure	  */
	public void setPlatelet_Measure (int Platelet_Measure);

	/** Get Platelet Measure	  */
	public int getPlatelet_Measure();

    /** Column name Platelet_Procedure */
    public static final String COLUMNNAME_Platelet_Procedure = "Platelet_Procedure";

	/** Set Platelet Procedure	  */
	public void setPlatelet_Procedure (int Platelet_Procedure);

	/** Get Platelet Procedure	  */
	public int getPlatelet_Procedure();

    /** Column name Pregnancy */
    public static final String COLUMNNAME_Pregnancy = "Pregnancy";

	/** Set Pregnancy	  */
	public void setPregnancy (int Pregnancy);

	/** Get Pregnancy	  */
	public int getPregnancy();

    /** Column name RangeFinAtrial */
    public static final String COLUMNNAME_RangeFinAtrial = "RangeFinAtrial";

	/** Set Final Range (World Organization Health code)	  */
	public void setRangeFinAtrial (String RangeFinAtrial);

	/** Get Final Range (World Organization Health code)	  */
	public String getRangeFinAtrial();

    /** Column name RangeFinCode */
    public static final String COLUMNNAME_RangeFinCode = "RangeFinCode";

	/** Set Final Range (World Organization Health code)	  */
	public void setRangeFinCode (String RangeFinCode);

	/** Get Final Range (World Organization Health code)	  */
	public String getRangeFinCode();

    /** Column name RangeFinHemorragic */
    public static final String COLUMNNAME_RangeFinHemorragic = "RangeFinHemorragic";

	/** Set Final Range (World Organization Health Code)	  */
	public void setRangeFinHemorragic (String RangeFinHemorragic);

	/** Get Final Range (World Organization Health Code)	  */
	public String getRangeFinHemorragic();

    /** Column name RangeFinIstroke2 */
    public static final String COLUMNNAME_RangeFinIstroke2 = "RangeFinIstroke2";

	/** Set Final Range (World Organization Health Code)	  */
	public void setRangeFinIstroke2 (String RangeFinIstroke2);

	/** Get Final Range (World Organization Health Code)	  */
	public String getRangeFinIstroke2();

    /** Column name RangeFinLdl */
    public static final String COLUMNNAME_RangeFinLdl = "RangeFinLdl";

	/** Set Final Range LDL	  */
	public void setRangeFinLdl (String RangeFinLdl);

	/** Get Final Range LDL	  */
	public String getRangeFinLdl();

    /** Column name RangeFinPlaqueta */
    public static final String COLUMNNAME_RangeFinPlaqueta = "RangeFinPlaqueta";

	/** Set Final Range	  */
	public void setRangeFinPlaqueta (String RangeFinPlaqueta);

	/** Get Final Range	  */
	public String getRangeFinPlaqueta();

    /** Column name RangeFinRehabilita */
    public static final String COLUMNNAME_RangeFinRehabilita = "RangeFinRehabilita";

	/** Set Final Range	  */
	public void setRangeFinRehabilita (String RangeFinRehabilita);

	/** Get Final Range	  */
	public String getRangeFinRehabilita();

    /** Column name RangeFinVte */
    public static final String COLUMNNAME_RangeFinVte = "RangeFinVte";

	/** Set Final Range VTE	  */
	public void setRangeFinVte (String RangeFinVte);

	/** Get Final Range VTE	  */
	public String getRangeFinVte();

    /** Column name RangeIniAtrial */
    public static final String COLUMNNAME_RangeIniAtrial = "RangeIniAtrial";

	/** Set Initial Range (World Organization Health Code)	  */
	public void setRangeIniAtrial (String RangeIniAtrial);

	/** Get Initial Range (World Organization Health Code)	  */
	public String getRangeIniAtrial();

    /** Column name RangeIniCode */
    public static final String COLUMNNAME_RangeIniCode = "RangeIniCode";

	/** Set Initial Range (World Organization Health Code)	  */
	public void setRangeIniCode (String RangeIniCode);

	/** Get Initial Range (World Organization Health Code)	  */
	public String getRangeIniCode();

    /** Column name RangeIniHemorragic */
    public static final String COLUMNNAME_RangeIniHemorragic = "RangeIniHemorragic";

	/** Set Initial Range (World Organization Health Code)	  */
	public void setRangeIniHemorragic (String RangeIniHemorragic);

	/** Get Initial Range (World Organization Health Code)	  */
	public String getRangeIniHemorragic();

    /** Column name RangeIniIstroke2 */
    public static final String COLUMNNAME_RangeIniIstroke2 = "RangeIniIstroke2";

	/** Set Initial Range (World Organization Health Code)	  */
	public void setRangeIniIstroke2 (String RangeIniIstroke2);

	/** Get Initial Range (World Organization Health Code)	  */
	public String getRangeIniIstroke2();

    /** Column name RangeIniLdl */
    public static final String COLUMNNAME_RangeIniLdl = "RangeIniLdl";

	/** Set Initial Range LDL	  */
	public void setRangeIniLdl (String RangeIniLdl);

	/** Get Initial Range LDL	  */
	public String getRangeIniLdl();

    /** Column name RangeIniPlaqueta */
    public static final String COLUMNNAME_RangeIniPlaqueta = "RangeIniPlaqueta";

	/** Set Initial Range	  */
	public void setRangeIniPlaqueta (String RangeIniPlaqueta);

	/** Get Initial Range	  */
	public String getRangeIniPlaqueta();

    /** Column name RangeIniRehabilita */
    public static final String COLUMNNAME_RangeIniRehabilita = "RangeIniRehabilita";

	/** Set Initial Range	  */
	public void setRangeIniRehabilita (String RangeIniRehabilita);

	/** Get Initial Range	  */
	public String getRangeIniRehabilita();

    /** Column name RangeIniVte */
    public static final String COLUMNNAME_RangeIniVte = "RangeIniVte";

	/** Set Initial Range VTE	  */
	public void setRangeIniVte (String RangeIniVte);

	/** Get Initial Range VTE	  */
	public String getRangeIniVte();

    /** Column name Rehab_Procedure */
    public static final String COLUMNNAME_Rehab_Procedure = "Rehab_Procedure";

	/** Set Rehabilitation Procedure	  */
	public void setRehab_Procedure (int Rehab_Procedure);

	/** Get Rehabilitation Procedure	  */
	public int getRehab_Procedure();

    /** Column name StudySpinal */
    public static final String COLUMNNAME_StudySpinal = "StudySpinal";

	/** Set Imaging Study Spinal	  */
	public void setStudySpinal (int StudySpinal);

	/** Get Imaging Study Spinal	  */
	public int getStudySpinal();

    /** Column name TobaccoCessationCounseling */
    public static final String COLUMNNAME_TobaccoCessationCounseling = "TobaccoCessationCounseling";

	/** Set Tobacco Use Cessation Counseling	  */
	public void setTobaccoCessationCounseling (int TobaccoCessationCounseling);

	/** Get Tobacco Use Cessation Counseling	  */
	public int getTobaccoCessationCounseling();

    /** Column name Trial_Reason */
    public static final String COLUMNNAME_Trial_Reason = "Trial_Reason";

	/** Set Trial Reason	  */
	public void setTrial_Reason (int Trial_Reason);

	/** Get Trial Reason	  */
	public int getTrial_Reason();

    /** Column name VTE_Diagnostic */
    public static final String COLUMNNAME_VTE_Diagnostic = "VTE_Diagnostic";

	/** Set VTE Diagnostic	  */
	public void setVTE_Diagnostic (int VTE_Diagnostic);

	/** Get VTE Diagnostic	  */
	public int getVTE_Diagnostic();

    /** Column name Warfarin_Procedure */
    public static final String COLUMNNAME_Warfarin_Procedure = "Warfarin_Procedure";

	/** Set Warfarin Procedure	  */
	public void setWarfarin_Procedure (int Warfarin_Procedure);

	/** Get Warfarin Procedure	  */
	public int getWarfarin_Procedure();
}
