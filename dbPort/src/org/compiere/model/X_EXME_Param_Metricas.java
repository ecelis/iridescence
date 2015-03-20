/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Param_Metricas
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Param_Metricas extends PO implements I_EXME_Param_Metricas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Param_Metricas (Properties ctx, int EXME_Param_Metricas_ID, String trxName)
    {
      super (ctx, EXME_Param_Metricas_ID, trxName);
      /** if (EXME_Param_Metricas_ID == 0)
        {
			setEXME_Param_Metricas_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Param_Metricas (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_EXME_Param_Metricas[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Age BMI.
		@param Age_BMI Age BMI	  */
	public void setAge_BMI (int Age_BMI)
	{
		set_Value (COLUMNNAME_Age_BMI, Integer.valueOf(Age_BMI));
	}

	/** Get Age BMI.
		@return Age BMI	  */
	public int getAge_BMI () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_BMI);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Breast Cancer From.
		@param Age_BreastFrom Age Breast Cancer From	  */
	public void setAge_BreastFrom (int Age_BreastFrom)
	{
		set_Value (COLUMNNAME_Age_BreastFrom, Integer.valueOf(Age_BreastFrom));
	}

	/** Get Age Breast Cancer From.
		@return Age Breast Cancer From	  */
	public int getAge_BreastFrom () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_BreastFrom);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Breast Cancer To.
		@param Age_BreastTo Age Breast Cancer To	  */
	public void setAge_BreastTo (int Age_BreastTo)
	{
		set_Value (COLUMNNAME_Age_BreastTo, Integer.valueOf(Age_BreastTo));
	}

	/** Get Age Breast Cancer To.
		@return Age Breast Cancer To	  */
	public int getAge_BreastTo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_BreastTo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Childhood Immunization.
		@param Age_Childhood Age Childhood Immunization	  */
	public void setAge_Childhood (int Age_Childhood)
	{
		set_Value (COLUMNNAME_Age_Childhood, Integer.valueOf(Age_Childhood));
	}

	/** Get Age Childhood Immunization.
		@return Age Childhood Immunization	  */
	public int getAge_Childhood () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_Childhood);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Colorectal Cancer From.
		@param Age_ColorectalFrom Age Colorectal Cancer From	  */
	public void setAge_ColorectalFrom (int Age_ColorectalFrom)
	{
		set_Value (COLUMNNAME_Age_ColorectalFrom, Integer.valueOf(Age_ColorectalFrom));
	}

	/** Get Age Colorectal Cancer From.
		@return Age Colorectal Cancer From	  */
	public int getAge_ColorectalFrom () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_ColorectalFrom);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Colorectal Cancer To.
		@param Age_ColorectalTo Age Colorectal Cancer To	  */
	public void setAge_ColorectalTo (int Age_ColorectalTo)
	{
		set_Value (COLUMNNAME_Age_ColorectalTo, Integer.valueOf(Age_ColorectalTo));
	}

	/** Get Age Colorectal Cancer To.
		@return Age Colorectal Cancer To	  */
	public int getAge_ColorectalTo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_ColorectalTo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Hypertension.
		@param Age_Hypertension Age Hypertension	  */
	public void setAge_Hypertension (int Age_Hypertension)
	{
		set_Value (COLUMNNAME_Age_Hypertension, Integer.valueOf(Age_Hypertension));
	}

	/** Get Age Hypertension.
		@return Age Hypertension	  */
	public int getAge_Hypertension () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_Hypertension);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Influenza Immunization.
		@param Age_Immunization Age Influenza Immunization	  */
	public void setAge_Immunization (int Age_Immunization)
	{
		set_Value (COLUMNNAME_Age_Immunization, Integer.valueOf(Age_Immunization));
	}

	/** Get Age Influenza Immunization.
		@return Age Influenza Immunization	  */
	public int getAge_Immunization () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_Immunization);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Pneumonia.
		@param Age_Pneumonia Age Pneumonia	  */
	public void setAge_Pneumonia (int Age_Pneumonia)
	{
		set_Value (COLUMNNAME_Age_Pneumonia, Integer.valueOf(Age_Pneumonia));
	}

	/** Get Age Pneumonia.
		@return Age Pneumonia	  */
	public int getAge_Pneumonia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_Pneumonia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Tobacco.
		@param Age_Tobacco Age Tobacco	  */
	public void setAge_Tobacco (int Age_Tobacco)
	{
		set_Value (COLUMNNAME_Age_Tobacco, Integer.valueOf(Age_Tobacco));
	}

	/** Get Age Tobacco.
		@return Age Tobacco	  */
	public int getAge_Tobacco () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_Tobacco);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Weight Assessment From.
		@param Age_Weight Age Weight Assessment From	  */
	public void setAge_Weight (int Age_Weight)
	{
		set_Value (COLUMNNAME_Age_Weight, Integer.valueOf(Age_Weight));
	}

	/** Get Age Weight Assessment From.
		@return Age Weight Assessment From	  */
	public int getAge_Weight () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_Weight);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age Weight Assessment To.
		@param Age_WeightTo Age Weight Assessment To	  */
	public void setAge_WeightTo (int Age_WeightTo)
	{
		set_Value (COLUMNNAME_Age_WeightTo, Integer.valueOf(Age_WeightTo));
	}

	/** Get Age Weight Assessment To.
		@return Age Weight Assessment To	  */
	public int getAge_WeightTo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Age_WeightTo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Antiplatelet.
		@param Antiplatelet Antiplatelet	  */
	public void setAntiplatelet (int Antiplatelet)
	{
		set_Value (COLUMNNAME_Antiplatelet, Integer.valueOf(Antiplatelet));
	}

	/** Get Antiplatelet.
		@return Antiplatelet	  */
	public int getAntiplatelet () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Antiplatelet);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asthma.
		@param Asthma Asthma	  */
	public void setAsthma (int Asthma)
	{
		set_Value (COLUMNNAME_Asthma, Integer.valueOf(Asthma));
	}

	/** Get Asthma.
		@return Asthma	  */
	public int getAsthma () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Asthma);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ateriosclerosis Diagnostic.
		@param Ateriosclerosis_Diag Ateriosclerosis Diagnostic	  */
	public void setAteriosclerosis_Diag (int Ateriosclerosis_Diag)
	{
		set_Value (COLUMNNAME_Ateriosclerosis_Diag, Integer.valueOf(Ateriosclerosis_Diag));
	}

	/** Get Ateriosclerosis Diagnostic.
		@return Ateriosclerosis Diagnostic	  */
	public int getAteriosclerosis_Diag () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ateriosclerosis_Diag);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Atrial Fibrillation.
		@param Atrial_Diagnostic Atrial Fibrillation	  */
	public void setAtrial_Diagnostic (int Atrial_Diagnostic)
	{
		set_Value (COLUMNNAME_Atrial_Diagnostic, Integer.valueOf(Atrial_Diagnostic));
	}

	/** Get Atrial Fibrillation.
		@return Atrial Fibrillation	  */
	public int getAtrial_Diagnostic () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Atrial_Diagnostic);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Carotid Intervention.
		@param Carotid_Intervention Carotid Intervention	  */
	public void setCarotid_Intervention (int Carotid_Intervention)
	{
		set_Value (COLUMNNAME_Carotid_Intervention, Integer.valueOf(Carotid_Intervention));
	}

	/** Get Carotid Intervention.
		@return Carotid Intervention	  */
	public int getCarotid_Intervention () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Carotid_Intervention);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cessation Agents.
		@param CessationAgents Cessation Agents	  */
	public void setCessationAgents (int CessationAgents)
	{
		set_Value (COLUMNNAME_CessationAgents, Integer.valueOf(CessationAgents));
	}

	/** Get Cessation Agents.
		@return Cessation Agents	  */
	public int getCessationAgents () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CessationAgents);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Coronary Artery Disease.
		@param Coronary Coronary Artery Disease	  */
	public void setCoronary (int Coronary)
	{
		set_Value (COLUMNNAME_Coronary, Integer.valueOf(Coronary));
	}

	/** Get Coronary Artery Disease.
		@return Coronary Artery Disease	  */
	public int getCoronary () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Coronary);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Counseling for Nutrition.
		@param CounselingNutrition Counseling for Nutrition	  */
	public void setCounselingNutrition (int CounselingNutrition)
	{
		set_Value (COLUMNNAME_CounselingNutrition, Integer.valueOf(CounselingNutrition));
	}

	/** Get Counseling for Nutrition.
		@return Counseling for Nutrition	  */
	public int getCounselingNutrition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CounselingNutrition);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diabetes.
		@param Diabetes Diabetes	  */
	public void setDiabetes (int Diabetes)
	{
		set_Value (COLUMNNAME_Diabetes, Integer.valueOf(Diabetes));
	}

	/** Get Diabetes.
		@return Diabetes	  */
	public int getDiabetes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Diabetes);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diabetes Medication.
		@param DiabetesMedication Diabetes Medication	  */
	public void setDiabetesMedication (int DiabetesMedication)
	{
		set_Value (COLUMNNAME_DiabetesMedication, Integer.valueOf(DiabetesMedication));
	}

	/** Get Diabetes Medication.
		@return Diabetes Medication	  */
	public int getDiabetesMedication () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DiabetesMedication);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Elective Reason.
		@param Elective_Reason Elective Reason	  */
	public void setElective_Reason (int Elective_Reason)
	{
		set_Value (COLUMNNAME_Elective_Reason, Integer.valueOf(Elective_Reason));
	}

	/** Get Elective Reason.
		@return Elective Reason	  */
	public int getElective_Reason () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Elective_Reason);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Influenza.
		@param EncounterInfluenza Encounter Influenza	  */
	public void setEncounterInfluenza (int EncounterInfluenza)
	{
		set_Value (COLUMNNAME_EncounterInfluenza, Integer.valueOf(EncounterInfluenza));
	}

	/** Get Encounter Influenza.
		@return Encounter Influenza	  */
	public int getEncounterInfluenza () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EncounterInfluenza);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ESRD.
		@param ESRD ESRD	  */
	public void setESRD (int ESRD)
	{
		set_Value (COLUMNNAME_ESRD, Integer.valueOf(ESRD));
	}

	/** Get ESRD.
		@return ESRD	  */
	public int getESRD () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ESRD);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Anticoagulation VTE Therapy.
		@param EXME_AnticoagulanteVte_ID Anticoagulation VTE Therapy	  */
	public void setEXME_AnticoagulanteVte_ID (int EXME_AnticoagulanteVte_ID)
	{
		if (EXME_AnticoagulanteVte_ID < 1) 
			set_Value (COLUMNNAME_EXME_AnticoagulanteVte_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AnticoagulanteVte_ID, Integer.valueOf(EXME_AnticoagulanteVte_ID));
	}

	/** Get Anticoagulation VTE Therapy.
		@return Anticoagulation VTE Therapy	  */
	public int getEXME_AnticoagulanteVte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AnticoagulanteVte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Diagnostic Atrial Fibrillation/Futter.
		@param EXME_AtrialFin_ID Final Diagnostic Atrial Fibrillation/Futter	  */
	public void setEXME_AtrialFin_ID (int EXME_AtrialFin_ID)
	{
		if (EXME_AtrialFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_AtrialFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AtrialFin_ID, Integer.valueOf(EXME_AtrialFin_ID));
	}

	/** Get Final Diagnostic Atrial Fibrillation/Futter.
		@return Final Diagnostic Atrial Fibrillation/Futter	  */
	public int getEXME_AtrialFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AtrialFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial Diagnostic Atrial fibrillation/futter.
		@param EXME_AtrialIni_ID Initial Diagnostic Atrial fibrillation/futter	  */
	public void setEXME_AtrialIni_ID (int EXME_AtrialIni_ID)
	{
		if (EXME_AtrialIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_AtrialIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AtrialIni_ID, Integer.valueOf(EXME_AtrialIni_ID));
	}

	/** Get Initial Diagnostic Atrial fibrillation/futter.
		@return Initial Diagnostic Atrial fibrillation/futter	  */
	public int getEXME_AtrialIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AtrialIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Atrial Fibrillation/Flutter.
		@param EXME_Diagnostico_Arritmia_ID Atrial Fibrillation/Flutter	  */
	public void setEXME_Diagnostico_Arritmia_ID (int EXME_Diagnostico_Arritmia_ID)
	{
		if (EXME_Diagnostico_Arritmia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_Arritmia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_Arritmia_ID, Integer.valueOf(EXME_Diagnostico_Arritmia_ID));
	}

	/** Get Atrial Fibrillation/Flutter.
		@return Atrial Fibrillation/Flutter	  */
	public int getEXME_Diagnostico_Arritmia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_Arritmia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ischemic Stroke.
		@param EXME_Diagnostico_Derrame_ID Ischemic Stroke	  */
	public void setEXME_Diagnostico_Derrame_ID (int EXME_Diagnostico_Derrame_ID)
	{
		if (EXME_Diagnostico_Derrame_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_Derrame_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_Derrame_ID, Integer.valueOf(EXME_Diagnostico_Derrame_ID));
	}

	/** Get Ischemic Stroke.
		@return Ischemic Stroke	  */
	public int getEXME_Diagnostico_Derrame_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_Derrame_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inpatient Service Station .
		@param EXME_EstServ_Hosp_ID Inpatient Service Station 	  */
	public void setEXME_EstServ_Hosp_ID (int EXME_EstServ_Hosp_ID)
	{
		if (EXME_EstServ_Hosp_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_Hosp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_Hosp_ID, Integer.valueOf(EXME_EstServ_Hosp_ID));
	}

	/** Get Inpatient Service Station .
		@return Inpatient Service Station 	  */
	public int getEXME_EstServ_Hosp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_Hosp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ICU Service Station.
		@param EXME_EstServIcu_ID ICU Service Station	  */
	public void setEXME_EstServIcu_ID (int EXME_EstServIcu_ID)
	{
		if (EXME_EstServIcu_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServIcu_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServIcu_ID, Integer.valueOf(EXME_EstServIcu_ID));
	}

	/** Get ICU Service Station.
		@return ICU Service Station	  */
	public int getEXME_EstServIcu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServIcu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Emergency Service Station.
		@param EXME_EstServ_Urgencias_ID Emergency Service Station	  */
	public void setEXME_EstServ_Urgencias_ID (int EXME_EstServ_Urgencias_ID)
	{
		if (EXME_EstServ_Urgencias_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_Urgencias_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_Urgencias_ID, Integer.valueOf(EXME_EstServ_Urgencias_ID));
	}

	/** Get Emergency Service Station.
		@return Emergency Service Station	  */
	public int getEXME_EstServ_Urgencias_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_Urgencias_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Diagnostic Hemorragic Stroke.
		@param EXME_HemorragicFin_ID Final Diagnostic Hemorragic Stroke	  */
	public void setEXME_HemorragicFin_ID (int EXME_HemorragicFin_ID)
	{
		if (EXME_HemorragicFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_HemorragicFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_HemorragicFin_ID, Integer.valueOf(EXME_HemorragicFin_ID));
	}

	/** Get Final Diagnostic Hemorragic Stroke.
		@return Final Diagnostic Hemorragic Stroke	  */
	public int getEXME_HemorragicFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HemorragicFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial Diagnostic Hemorragic Stroke.
		@param EXME_HemorragicIni_ID Initial Diagnostic Hemorragic Stroke	  */
	public void setEXME_HemorragicIni_ID (int EXME_HemorragicIni_ID)
	{
		if (EXME_HemorragicIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_HemorragicIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_HemorragicIni_ID, Integer.valueOf(EXME_HemorragicIni_ID));
	}

	/** Get Initial Diagnostic Hemorragic Stroke.
		@return Initial Diagnostic Hemorragic Stroke	  */
	public int getEXME_HemorragicIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HemorragicIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Diagnostic Hypertension.
		@param EXME_HypertensionFin_ID Final Diagnostic Hypertension	  */
	public void setEXME_HypertensionFin_ID (int EXME_HypertensionFin_ID)
	{
		if (EXME_HypertensionFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_HypertensionFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_HypertensionFin_ID, Integer.valueOf(EXME_HypertensionFin_ID));
	}

	/** Get Final Diagnostic Hypertension.
		@return Final Diagnostic Hypertension	  */
	public int getEXME_HypertensionFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HypertensionFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial Diagnostic Hypertension.
		@param EXME_HypertensionIni_ID Initial Diagnostic Hypertension	  */
	public void setEXME_HypertensionIni_ID (int EXME_HypertensionIni_ID)
	{
		if (EXME_HypertensionIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_HypertensionIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_HypertensionIni_ID, Integer.valueOf(EXME_HypertensionIni_ID));
	}

	/** Get Initial Diagnostic Hypertension.
		@return Initial Diagnostic Hypertension	  */
	public int getEXME_HypertensionIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HypertensionIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final CPT LDL.
		@param EXME_LdlFin_ID Final CPT LDL	  */
	public void setEXME_LdlFin_ID (int EXME_LdlFin_ID)
	{
		if (EXME_LdlFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_LdlFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_LdlFin_ID, Integer.valueOf(EXME_LdlFin_ID));
	}

	/** Get Final CPT LDL.
		@return Final CPT LDL	  */
	public int getEXME_LdlFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LdlFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial CPT LDL.
		@param EXME_LdlIni_ID Initial CPT LDL	  */
	public void setEXME_LdlIni_ID (int EXME_LdlIni_ID)
	{
		if (EXME_LdlIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_LdlIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_LdlIni_ID, Integer.valueOf(EXME_LdlIni_ID));
	}

	/** Get Initial CPT LDL.
		@return Initial CPT LDL	  */
	public int getEXME_LdlIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LdlIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Metrics parameter.
		@param EXME_Param_Metricas_ID Metrics parameter	  */
	public void setEXME_Param_Metricas_ID (int EXME_Param_Metricas_ID)
	{
		if (EXME_Param_Metricas_ID < 1)
			 throw new IllegalArgumentException ("EXME_Param_Metricas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Param_Metricas_ID, Integer.valueOf(EXME_Param_Metricas_ID));
	}

	/** Get Metrics parameter.
		@return Metrics parameter	  */
	public int getEXME_Param_Metricas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Param_Metricas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final CPT Platelet Counts Monitored .
		@param EXME_PlaquetaFin_ID Final CPT Platelet Counts Monitored 	  */
	public void setEXME_PlaquetaFin_ID (int EXME_PlaquetaFin_ID)
	{
		if (EXME_PlaquetaFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlaquetaFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlaquetaFin_ID, Integer.valueOf(EXME_PlaquetaFin_ID));
	}

	/** Get Final CPT Platelet Counts Monitored .
		@return Final CPT Platelet Counts Monitored 	  */
	public int getEXME_PlaquetaFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlaquetaFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial CPT Platelet Counts Monitored .
		@param EXME_PlaquetaIni_ID Initial CPT Platelet Counts Monitored 	  */
	public void setEXME_PlaquetaIni_ID (int EXME_PlaquetaIni_ID)
	{
		if (EXME_PlaquetaIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlaquetaIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlaquetaIni_ID, Integer.valueOf(EXME_PlaquetaIni_ID));
	}

	/** Get Initial CPT Platelet Counts Monitored .
		@return Initial CPT Platelet Counts Monitored 	  */
	public int getEXME_PlaquetaIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlaquetaIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set VTE prophylaxis.
		@param EXME_ProphylaxisVte_ID VTE prophylaxis	  */
	public void setEXME_ProphylaxisVte_ID (int EXME_ProphylaxisVte_ID)
	{
		if (EXME_ProphylaxisVte_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProphylaxisVte_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProphylaxisVte_ID, Integer.valueOf(EXME_ProphylaxisVte_ID));
	}

	/** Get VTE prophylaxis.
		@return VTE prophylaxis	  */
	public int getEXME_ProphylaxisVte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProphylaxisVte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final CPT Rehabilitation Services.
		@param EXME_RehabilitaFin_ID Final CPT Rehabilitation Services	  */
	public void setEXME_RehabilitaFin_ID (int EXME_RehabilitaFin_ID)
	{
		if (EXME_RehabilitaFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_RehabilitaFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RehabilitaFin_ID, Integer.valueOf(EXME_RehabilitaFin_ID));
	}

	/** Get Final CPT Rehabilitation Services.
		@return Final CPT Rehabilitation Services	  */
	public int getEXME_RehabilitaFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RehabilitaFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial CPT Rehabilitation Services.
		@param EXME_RehabilitaIni_ID Initial CPT Rehabilitation Services	  */
	public void setEXME_RehabilitaIni_ID (int EXME_RehabilitaIni_ID)
	{
		if (EXME_RehabilitaIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_RehabilitaIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RehabilitaIni_ID, Integer.valueOf(EXME_RehabilitaIni_ID));
	}

	/** Get Initial CPT Rehabilitation Services.
		@return Initial CPT Rehabilitation Services	  */
	public int getEXME_RehabilitaIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RehabilitaIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Diagnostic Ischemic Stroke.
		@param EXME_SchemicFin_ID Final Diagnostic Ischemic Stroke	  */
	public void setEXME_SchemicFin_ID (int EXME_SchemicFin_ID)
	{
		if (EXME_SchemicFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_SchemicFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SchemicFin_ID, Integer.valueOf(EXME_SchemicFin_ID));
	}

	/** Get Final Diagnostic Ischemic Stroke.
		@return Final Diagnostic Ischemic Stroke	  */
	public int getEXME_SchemicFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SchemicFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial Diagnostic Ischemic Stroke.
		@param EXME_SchemicIni_ID Initial Diagnostic Ischemic Stroke	  */
	public void setEXME_SchemicIni_ID (int EXME_SchemicIni_ID)
	{
		if (EXME_SchemicIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_SchemicIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SchemicIni_ID, Integer.valueOf(EXME_SchemicIni_ID));
	}

	/** Get Initial Diagnostic Ischemic Stroke.
		@return Initial Diagnostic Ischemic Stroke	  */
	public int getEXME_SchemicIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SchemicIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diastolic Vital Sign.
		@param EXME_SignoVitalDiastolic_ID Diastolic Vital Sign	  */
	public void setEXME_SignoVitalDiastolic_ID (int EXME_SignoVitalDiastolic_ID)
	{
		if (EXME_SignoVitalDiastolic_ID < 1) 
			set_Value (COLUMNNAME_EXME_SignoVitalDiastolic_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SignoVitalDiastolic_ID, Integer.valueOf(EXME_SignoVitalDiastolic_ID));
	}

	/** Get Diastolic Vital Sign.
		@return Diastolic Vital Sign	  */
	public int getEXME_SignoVitalDiastolic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SignoVitalDiastolic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Systolic Vital Sign.
		@param EXME_SignoVitalSystolic_ID Systolic Vital Sign	  */
	public void setEXME_SignoVitalSystolic_ID (int EXME_SignoVitalSystolic_ID)
	{
		if (EXME_SignoVitalSystolic_ID < 1) 
			set_Value (COLUMNNAME_EXME_SignoVitalSystolic_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SignoVitalSystolic_ID, Integer.valueOf(EXME_SignoVitalSystolic_ID));
	}

	/** Get Systolic Vital Sign.
		@return Systolic Vital Sign	  */
	public int getEXME_SignoVitalSystolic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SignoVitalSystolic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Anticoagulation Therapy.
		@param EXME_TerapiaAnticoagulante_ID Anticoagulation Therapy	  */
	public void setEXME_TerapiaAnticoagulante_ID (int EXME_TerapiaAnticoagulante_ID)
	{
		if (EXME_TerapiaAnticoagulante_ID < 1) 
			set_Value (COLUMNNAME_EXME_TerapiaAnticoagulante_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TerapiaAnticoagulante_ID, Integer.valueOf(EXME_TerapiaAnticoagulante_ID));
	}

	/** Get Anticoagulation Therapy.
		@return Anticoagulation Therapy	  */
	public int getEXME_TerapiaAnticoagulante_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TerapiaAnticoagulante_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Antithrombolytic Therapy.
		@param EXME_TerapiaAntitrombotic_ID Antithrombolytic Therapy	  */
	public void setEXME_TerapiaAntitrombotic_ID (int EXME_TerapiaAntitrombotic_ID)
	{
		if (EXME_TerapiaAntitrombotic_ID < 1) 
			set_Value (COLUMNNAME_EXME_TerapiaAntitrombotic_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TerapiaAntitrombotic_ID, Integer.valueOf(EXME_TerapiaAntitrombotic_ID));
	}

	/** Get Antithrombolytic Therapy.
		@return Antithrombolytic Therapy	  */
	public int getEXME_TerapiaAntitrombotic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TerapiaAntitrombotic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Heparin (UFH) Therapy.
		@param EXME_TerapiaHeparin_ID Heparin (UFH) Therapy	  */
	public void setEXME_TerapiaHeparin_ID (int EXME_TerapiaHeparin_ID)
	{
		if (EXME_TerapiaHeparin_ID < 1) 
			set_Value (COLUMNNAME_EXME_TerapiaHeparin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TerapiaHeparin_ID, Integer.valueOf(EXME_TerapiaHeparin_ID));
	}

	/** Get Heparin (UFH) Therapy.
		@return Heparin (UFH) Therapy	  */
	public int getEXME_TerapiaHeparin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TerapiaHeparin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Statin Medication.
		@param EXME_TerapiaStatin_ID Statin Medication	  */
	public void setEXME_TerapiaStatin_ID (int EXME_TerapiaStatin_ID)
	{
		if (EXME_TerapiaStatin_ID < 1) 
			set_Value (COLUMNNAME_EXME_TerapiaStatin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TerapiaStatin_ID, Integer.valueOf(EXME_TerapiaStatin_ID));
	}

	/** Get Statin Medication.
		@return Statin Medication	  */
	public int getEXME_TerapiaStatin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TerapiaStatin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set THROMBOLYTIC (T-PA) Therapy.
		@param EXME_TerapiaThrombolitic_ID THROMBOLYTIC (T-PA) Therapy	  */
	public void setEXME_TerapiaThrombolitic_ID (int EXME_TerapiaThrombolitic_ID)
	{
		if (EXME_TerapiaThrombolitic_ID < 1) 
			set_Value (COLUMNNAME_EXME_TerapiaThrombolitic_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TerapiaThrombolitic_ID, Integer.valueOf(EXME_TerapiaThrombolitic_ID));
	}

	/** Get THROMBOLYTIC (T-PA) Therapy.
		@return THROMBOLYTIC (T-PA) Therapy	  */
	public int getEXME_TerapiaThrombolitic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TerapiaThrombolitic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warfarin Therapy.
		@param EXME_TerapiaWarafina_ID Warfarin Therapy	  */
	public void setEXME_TerapiaWarafina_ID (int EXME_TerapiaWarafina_ID)
	{
		if (EXME_TerapiaWarafina_ID < 1) 
			set_Value (COLUMNNAME_EXME_TerapiaWarafina_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TerapiaWarafina_ID, Integer.valueOf(EXME_TerapiaWarafina_ID));
	}

	/** Get Warfarin Therapy.
		@return Warfarin Therapy	  */
	public int getEXME_TerapiaWarafina_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TerapiaWarafina_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Acellular Vaccine.
		@param EXME_VacunaAcellular_ID Acellular Vaccine	  */
	public void setEXME_VacunaAcellular_ID (int EXME_VacunaAcellular_ID)
	{
		if (EXME_VacunaAcellular_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaAcellular_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaAcellular_ID, Integer.valueOf(EXME_VacunaAcellular_ID));
	}

	/** Get Acellular Vaccine.
		@return Acellular Vaccine	  */
	public int getEXME_VacunaAcellular_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaAcellular_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Chicken Pox (VZV) Vaccine .
		@param EXME_VacunaChickenPox_ID Chicken Pox (VZV) Vaccine 	  */
	public void setEXME_VacunaChickenPox_ID (int EXME_VacunaChickenPox_ID)
	{
		if (EXME_VacunaChickenPox_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaChickenPox_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaChickenPox_ID, Integer.valueOf(EXME_VacunaChickenPox_ID));
	}

	/** Get Chicken Pox (VZV) Vaccine .
		@return Chicken Pox (VZV) Vaccine 	  */
	public int getEXME_VacunaChickenPox_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaChickenPox_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diphteria Vaccine.
		@param EXME_VacunaDiphteria_ID Diphteria Vaccine	  */
	public void setEXME_VacunaDiphteria_ID (int EXME_VacunaDiphteria_ID)
	{
		if (EXME_VacunaDiphteria_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaDiphteria_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaDiphteria_ID, Integer.valueOf(EXME_VacunaDiphteria_ID));
	}

	/** Get Diphteria Vaccine.
		@return Diphteria Vaccine	  */
	public int getEXME_VacunaDiphteria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaDiphteria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Influenza (Flu) Vaccine.
		@param EXME_VacunaFlu_ID Influenza (Flu) Vaccine	  */
	public void setEXME_VacunaFlu_ID (int EXME_VacunaFlu_ID)
	{
		if (EXME_VacunaFlu_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaFlu_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaFlu_ID, Integer.valueOf(EXME_VacunaFlu_ID));
	}

	/** Get Influenza (Flu) Vaccine.
		@return Influenza (Flu) Vaccine	  */
	public int getEXME_VacunaFlu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaFlu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hepatitis A Vaccine.
		@param EXME_VacunaHepatitisA_ID Hepatitis A Vaccine	  */
	public void setEXME_VacunaHepatitisA_ID (int EXME_VacunaHepatitisA_ID)
	{
		if (EXME_VacunaHepatitisA_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaHepatitisA_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaHepatitisA_ID, Integer.valueOf(EXME_VacunaHepatitisA_ID));
	}

	/** Get Hepatitis A Vaccine.
		@return Hepatitis A Vaccine	  */
	public int getEXME_VacunaHepatitisA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaHepatitisA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hepatitis B Vaccine.
		@param EXME_VacunaHepatitisB_ID Hepatitis B Vaccine	  */
	public void setEXME_VacunaHepatitisB_ID (int EXME_VacunaHepatitisB_ID)
	{
		if (EXME_VacunaHepatitisB_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaHepatitisB_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaHepatitisB_ID, Integer.valueOf(EXME_VacunaHepatitisB_ID));
	}

	/** Get Hepatitis B Vaccine.
		@return Hepatitis B Vaccine	  */
	public int getEXME_VacunaHepatitisB_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaHepatitisB_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Influenza Type B Vaccine.
		@param EXME_VacunaInfluenzaB_ID Influenza Type B Vaccine	  */
	public void setEXME_VacunaInfluenzaB_ID (int EXME_VacunaInfluenzaB_ID)
	{
		if (EXME_VacunaInfluenzaB_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaInfluenzaB_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaInfluenzaB_ID, Integer.valueOf(EXME_VacunaInfluenzaB_ID));
	}

	/** Get Influenza Type B Vaccine.
		@return Influenza Type B Vaccine	  */
	public int getEXME_VacunaInfluenzaB_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaInfluenzaB_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Influenza Vaccine.
		@param EXME_VacunaInfluenza_ID Influenza Vaccine	  */
	public void setEXME_VacunaInfluenza_ID (int EXME_VacunaInfluenza_ID)
	{
		if (EXME_VacunaInfluenza_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaInfluenza_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaInfluenza_ID, Integer.valueOf(EXME_VacunaInfluenza_ID));
	}

	/** Get Influenza Vaccine.
		@return Influenza Vaccine	  */
	public int getEXME_VacunaInfluenza_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaInfluenza_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Measles Vaccine.
		@param EXME_VacunaMeasles_ID Measles Vaccine	  */
	public void setEXME_VacunaMeasles_ID (int EXME_VacunaMeasles_ID)
	{
		if (EXME_VacunaMeasles_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaMeasles_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaMeasles_ID, Integer.valueOf(EXME_VacunaMeasles_ID));
	}

	/** Get Measles Vaccine.
		@return Measles Vaccine	  */
	public int getEXME_VacunaMeasles_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaMeasles_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mumps Vaccine.
		@param EXME_VacunaMumps_ID Mumps Vaccine	  */
	public void setEXME_VacunaMumps_ID (int EXME_VacunaMumps_ID)
	{
		if (EXME_VacunaMumps_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaMumps_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaMumps_ID, Integer.valueOf(EXME_VacunaMumps_ID));
	}

	/** Get Mumps Vaccine.
		@return Mumps Vaccine	  */
	public int getEXME_VacunaMumps_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaMumps_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pneumococcal Vaccine.
		@param EXME_VacunaPneumococcal_ID Pneumococcal Vaccine	  */
	public void setEXME_VacunaPneumococcal_ID (int EXME_VacunaPneumococcal_ID)
	{
		if (EXME_VacunaPneumococcal_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaPneumococcal_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaPneumococcal_ID, Integer.valueOf(EXME_VacunaPneumococcal_ID));
	}

	/** Get Pneumococcal Vaccine.
		@return Pneumococcal Vaccine	  */
	public int getEXME_VacunaPneumococcal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaPneumococcal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pneumonia Vaccine.
		@param EXME_VacunaPneumonia_ID Pneumonia Vaccine	  */
	public void setEXME_VacunaPneumonia_ID (int EXME_VacunaPneumonia_ID)
	{
		if (EXME_VacunaPneumonia_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaPneumonia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaPneumonia_ID, Integer.valueOf(EXME_VacunaPneumonia_ID));
	}

	/** Get Pneumonia Vaccine.
		@return Pneumonia Vaccine	  */
	public int getEXME_VacunaPneumonia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaPneumonia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Polio Vaccine.
		@param EXME_VacunaPolio_ID Polio Vaccine	  */
	public void setEXME_VacunaPolio_ID (int EXME_VacunaPolio_ID)
	{
		if (EXME_VacunaPolio_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaPolio_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaPolio_ID, Integer.valueOf(EXME_VacunaPolio_ID));
	}

	/** Get Polio Vaccine.
		@return Polio Vaccine	  */
	public int getEXME_VacunaPolio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaPolio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rotavirus Vaccine.
		@param EXME_VacunaRotavirus_ID Rotavirus Vaccine	  */
	public void setEXME_VacunaRotavirus_ID (int EXME_VacunaRotavirus_ID)
	{
		if (EXME_VacunaRotavirus_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaRotavirus_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaRotavirus_ID, Integer.valueOf(EXME_VacunaRotavirus_ID));
	}

	/** Get Rotavirus Vaccine.
		@return Rotavirus Vaccine	  */
	public int getEXME_VacunaRotavirus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaRotavirus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rubella Vaccine.
		@param EXME_VacunaRubella_ID Rubella Vaccine	  */
	public void setEXME_VacunaRubella_ID (int EXME_VacunaRubella_ID)
	{
		if (EXME_VacunaRubella_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaRubella_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaRubella_ID, Integer.valueOf(EXME_VacunaRubella_ID));
	}

	/** Get Rubella Vaccine.
		@return Rubella Vaccine	  */
	public int getEXME_VacunaRubella_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaRubella_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tetanus Vaccine.
		@param EXME_VacunaTetanus_ID Tetanus Vaccine	  */
	public void setEXME_VacunaTetanus_ID (int EXME_VacunaTetanus_ID)
	{
		if (EXME_VacunaTetanus_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaTetanus_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaTetanus_ID, Integer.valueOf(EXME_VacunaTetanus_ID));
	}

	/** Get Tetanus Vaccine.
		@return Tetanus Vaccine	  */
	public int getEXME_VacunaTetanus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaTetanus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Diagnostic VTE.
		@param EXME_VteFin_ID Final Diagnostic VTE	  */
	public void setEXME_VteFin_ID (int EXME_VteFin_ID)
	{
		if (EXME_VteFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_VteFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VteFin_ID, Integer.valueOf(EXME_VteFin_ID));
	}

	/** Get Final Diagnostic VTE.
		@return Final Diagnostic VTE	  */
	public int getEXME_VteFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VteFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial Diagnostic VTE.
		@param EXME_VteIni_ID Initial Diagnostic VTE	  */
	public void setEXME_VteIni_ID (int EXME_VteIni_ID)
	{
		if (EXME_VteIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_VteIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VteIni_ID, Integer.valueOf(EXME_VteIni_ID));
	}

	/** Get Initial Diagnostic VTE.
		@return Initial Diagnostic VTE	  */
	public int getEXME_VteIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VteIni_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Date Breast Cancer.
		@param Fecha_Fin_Breast Final Date Breast Cancer	  */
	public void setFecha_Fin_Breast (Timestamp Fecha_Fin_Breast)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Breast, Fecha_Fin_Breast);
	}

	/** Get Final Date Breast Cancer.
		@return Final Date Breast Cancer	  */
	public Timestamp getFecha_Fin_Breast () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Breast);
	}

	/** Set Final Date Colorectal Cancer.
		@param Fecha_Fin_Colorectal Final Date Colorectal Cancer	  */
	public void setFecha_Fin_Colorectal (Timestamp Fecha_Fin_Colorectal)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Colorectal, Fecha_Fin_Colorectal);
	}

	/** Get Final Date Colorectal Cancer.
		@return Final Date Colorectal Cancer	  */
	public Timestamp getFecha_Fin_Colorectal () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Colorectal);
	}

	/** Set Final Date Hypertension.
		@param Fecha_Fin_Hypertension Final Date Hypertension	  */
	public void setFecha_Fin_Hypertension (Timestamp Fecha_Fin_Hypertension)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Hypertension, Fecha_Fin_Hypertension);
	}

	/** Get Final Date Hypertension.
		@return Final Date Hypertension	  */
	public Timestamp getFecha_Fin_Hypertension () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Hypertension);
	}

	/** Set Final Date IMC.
		@param Fecha_Fin_Imc Final Date IMC	  */
	public void setFecha_Fin_Imc (Timestamp Fecha_Fin_Imc)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Imc, Fecha_Fin_Imc);
	}

	/** Get Final Date IMC.
		@return Final Date IMC	  */
	public Timestamp getFecha_Fin_Imc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Imc);
	}

	/** Set Final Date Influenza.
		@param Fecha_Fin_Influenza Final Date Influenza	  */
	public void setFecha_Fin_Influenza (Timestamp Fecha_Fin_Influenza)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Influenza, Fecha_Fin_Influenza);
	}

	/** Get Final Date Influenza.
		@return Final Date Influenza	  */
	public Timestamp getFecha_Fin_Influenza () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Influenza);
	}

	/** Set Final Date Pneumonia.
		@param Fecha_Fin_Neumonia Final Date Pneumonia	  */
	public void setFecha_Fin_Neumonia (Timestamp Fecha_Fin_Neumonia)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Neumonia, Fecha_Fin_Neumonia);
	}

	/** Get Final Date Pneumonia.
		@return Final Date Pneumonia	  */
	public Timestamp getFecha_Fin_Neumonia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Neumonia);
	}

	/** Set Fecha Fin NQF 1.
		@param Fecha_Fin_NQF1 Fecha Fin NQF 1	  */
	public void setFecha_Fin_NQF1 (Timestamp Fecha_Fin_NQF1)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF1, Fecha_Fin_NQF1);
	}

	/** Get Fecha Fin NQF 1.
		@return Fecha Fin NQF 1	  */
	public Timestamp getFecha_Fin_NQF1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF1);
	}

	/** Set Fecha Fin NQF 18.
		@param Fecha_Fin_NQF18 Fecha Fin NQF 18	  */
	public void setFecha_Fin_NQF18 (Timestamp Fecha_Fin_NQF18)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF18, Fecha_Fin_NQF18);
	}

	/** Get Fecha Fin NQF 18.
		@return Fecha Fin NQF 18	  */
	public Timestamp getFecha_Fin_NQF18 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF18);
	}

	/** Set Fecha Fin NQF 27.
		@param Fecha_Fin_NQF27 Fecha Fin NQF 27	  */
	public void setFecha_Fin_NQF27 (Timestamp Fecha_Fin_NQF27)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF27, Fecha_Fin_NQF27);
	}

	/** Get Fecha Fin NQF 27.
		@return Fecha Fin NQF 27	  */
	public Timestamp getFecha_Fin_NQF27 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF27);
	}

	/** Set Fecha Fin NQF 31.
		@param Fecha_Fin_NQF31 Fecha Fin NQF 31	  */
	public void setFecha_Fin_NQF31 (Timestamp Fecha_Fin_NQF31)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF31, Fecha_Fin_NQF31);
	}

	/** Get Fecha Fin NQF 31.
		@return Fecha Fin NQF 31	  */
	public Timestamp getFecha_Fin_NQF31 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF31);
	}

	/** Set Fecha Fin NQF 43.
		@param Fecha_Fin_NQF43 Fecha Fin NQF 43	  */
	public void setFecha_Fin_NQF43 (Timestamp Fecha_Fin_NQF43)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF43, Fecha_Fin_NQF43);
	}

	/** Get Fecha Fin NQF 43.
		@return Fecha Fin NQF 43	  */
	public Timestamp getFecha_Fin_NQF43 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF43);
	}

	/** Set Fecha Fin NQF 52.
		@param Fecha_Fin_NQF52 Fecha Fin NQF 52	  */
	public void setFecha_Fin_NQF52 (Timestamp Fecha_Fin_NQF52)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF52, Fecha_Fin_NQF52);
	}

	/** Get Fecha Fin NQF 52.
		@return Fecha Fin NQF 52	  */
	public Timestamp getFecha_Fin_NQF52 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF52);
	}

	/** Set Fecha Fin NQF 59.
		@param Fecha_Fin_NQF59 Fecha Fin NQF 59	  */
	public void setFecha_Fin_NQF59 (Timestamp Fecha_Fin_NQF59)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF59, Fecha_Fin_NQF59);
	}

	/** Get Fecha Fin NQF 59.
		@return Fecha Fin NQF 59	  */
	public Timestamp getFecha_Fin_NQF59 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF59);
	}

	/** Set Fecha Fin NQF 67.
		@param Fecha_Fin_NQF67 Fecha Fin NQF 67	  */
	public void setFecha_Fin_NQF67 (Timestamp Fecha_Fin_NQF67)
	{
		set_Value (COLUMNNAME_Fecha_Fin_NQF67, Fecha_Fin_NQF67);
	}

	/** Get Fecha Fin NQF 67.
		@return Fecha Fin NQF 67	  */
	public Timestamp getFecha_Fin_NQF67 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_NQF67);
	}

	/** Set Final Date Weight.
		@param Fecha_Fin_Peso Final Date Weight	  */
	public void setFecha_Fin_Peso (Timestamp Fecha_Fin_Peso)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Peso, Fecha_Fin_Peso);
	}

	/** Get Final Date Weight.
		@return Final Date Weight	  */
	public Timestamp getFecha_Fin_Peso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Peso);
	}

	/** Set Final Date Smoke Status.
		@param Fecha_Fin_Smoke Final Date Smoke Status	  */
	public void setFecha_Fin_Smoke (Timestamp Fecha_Fin_Smoke)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Smoke, Fecha_Fin_Smoke);
	}

	/** Get Final Date Smoke Status.
		@return Final Date Smoke Status	  */
	public Timestamp getFecha_Fin_Smoke () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Smoke);
	}

	/** Set Date End (S10).
		@param Fecha_Fin_Stroke10 Date End (S10)	  */
	public void setFecha_Fin_Stroke10 (Timestamp Fecha_Fin_Stroke10)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Stroke10, Fecha_Fin_Stroke10);
	}

	/** Get Date End (S10).
		@return Date End (S10)	  */
	public Timestamp getFecha_Fin_Stroke10 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Stroke10);
	}

	/** Set Date End (S4).
		@param Fecha_Fin_Stroke4 Date End (S4)	  */
	public void setFecha_Fin_Stroke4 (Timestamp Fecha_Fin_Stroke4)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Stroke4, Fecha_Fin_Stroke4);
	}

	/** Get Date End (S4).
		@return Date End (S4)	  */
	public Timestamp getFecha_Fin_Stroke4 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Stroke4);
	}

	/** Set Date End (S5).
		@param Fecha_Fin_Stroke5 Date End (S5)	  */
	public void setFecha_Fin_Stroke5 (Timestamp Fecha_Fin_Stroke5)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Stroke5, Fecha_Fin_Stroke5);
	}

	/** Get Date End (S5).
		@return Date End (S5)	  */
	public Timestamp getFecha_Fin_Stroke5 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Stroke5);
	}

	/** Set Date End (S6).
		@param Fecha_Fin_Stroke6 Date End (S6)	  */
	public void setFecha_Fin_Stroke6 (Timestamp Fecha_Fin_Stroke6)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Stroke6, Fecha_Fin_Stroke6);
	}

	/** Get Date End (S6).
		@return Date End (S6)	  */
	public Timestamp getFecha_Fin_Stroke6 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Stroke6);
	}

	/** Set Date End (S8).
		@param Fecha_Fin_Stroke8 Date End (S8)	  */
	public void setFecha_Fin_Stroke8 (Timestamp Fecha_Fin_Stroke8)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Stroke8, Fecha_Fin_Stroke8);
	}

	/** Get Date End (S8).
		@return Date End (S8)	  */
	public Timestamp getFecha_Fin_Stroke8 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Stroke8);
	}

	/** Set Final Date Tobbaco.
		@param Fecha_Fin_Tabaco Final Date Tobbaco	  */
	public void setFecha_Fin_Tabaco (Timestamp Fecha_Fin_Tabaco)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Tabaco, Fecha_Fin_Tabaco);
	}

	/** Get Final Date Tobbaco.
		@return Final Date Tobbaco	  */
	public Timestamp getFecha_Fin_Tabaco () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Tabaco);
	}

	/** Set Date End Emergency.
		@param Fecha_Fin_Urgencia Date End Emergency	  */
	public void setFecha_Fin_Urgencia (Timestamp Fecha_Fin_Urgencia)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Urgencia, Fecha_Fin_Urgencia);
	}

	/** Get Date End Emergency.
		@return Date End Emergency	  */
	public Timestamp getFecha_Fin_Urgencia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Urgencia);
	}

	/** Set Date End (ED).
		@param Fecha_Fin_Urgencia_ED Date End (ED)	  */
	public void setFecha_Fin_Urgencia_ED (Timestamp Fecha_Fin_Urgencia_ED)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Urgencia_ED, Fecha_Fin_Urgencia_ED);
	}

	/** Get Date End (ED).
		@return Date End (ED)	  */
	public Timestamp getFecha_Fin_Urgencia_ED () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Urgencia_ED);
	}

	/** Set Date End (S2).
		@param Fecha_Fin_Urgencia_S2 Date End (S2)	  */
	public void setFecha_Fin_Urgencia_S2 (Timestamp Fecha_Fin_Urgencia_S2)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Urgencia_S2, Fecha_Fin_Urgencia_S2);
	}

	/** Get Date End (S2).
		@return Date End (S2)	  */
	public Timestamp getFecha_Fin_Urgencia_S2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Urgencia_S2);
	}

	/** Set Date End (S3).
		@param Fecha_Fin_Urgencia_S3 Date End (S3)	  */
	public void setFecha_Fin_Urgencia_S3 (Timestamp Fecha_Fin_Urgencia_S3)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Urgencia_S3, Fecha_Fin_Urgencia_S3);
	}

	/** Get Date End (S3).
		@return Date End (S3)	  */
	public Timestamp getFecha_Fin_Urgencia_S3 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Urgencia_S3);
	}

	/** Set Final Date Vaccine.
		@param Fecha_Fin_Vacunas Final Date Vaccine	  */
	public void setFecha_Fin_Vacunas (Timestamp Fecha_Fin_Vacunas)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vacunas, Fecha_Fin_Vacunas);
	}

	/** Get Final Date Vaccine.
		@return Final Date Vaccine	  */
	public Timestamp getFecha_Fin_Vacunas () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vacunas);
	}

	/** Set Date End (V1).
		@param Fecha_Fin_Vte1 Date End (V1)	  */
	public void setFecha_Fin_Vte1 (Timestamp Fecha_Fin_Vte1)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vte1, Fecha_Fin_Vte1);
	}

	/** Get Date End (V1).
		@return Date End (V1)	  */
	public Timestamp getFecha_Fin_Vte1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vte1);
	}

	/** Set Date End (V2).
		@param Fecha_Fin_Vte2 Date End (V2)	  */
	public void setFecha_Fin_Vte2 (Timestamp Fecha_Fin_Vte2)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vte2, Fecha_Fin_Vte2);
	}

	/** Get Date End (V2).
		@return Date End (V2)	  */
	public Timestamp getFecha_Fin_Vte2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vte2);
	}

	/** Set Date End (V3).
		@param Fecha_Fin_Vte3 Date End (V3)	  */
	public void setFecha_Fin_Vte3 (Timestamp Fecha_Fin_Vte3)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vte3, Fecha_Fin_Vte3);
	}

	/** Get Date End (V3).
		@return Date End (V3)	  */
	public Timestamp getFecha_Fin_Vte3 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vte3);
	}

	/** Set Date End (V4).
		@param Fecha_Fin_Vte4 Date End (V4)	  */
	public void setFecha_Fin_Vte4 (Timestamp Fecha_Fin_Vte4)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vte4, Fecha_Fin_Vte4);
	}

	/** Get Date End (V4).
		@return Date End (V4)	  */
	public Timestamp getFecha_Fin_Vte4 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vte4);
	}

	/** Set Date End (V5).
		@param Fecha_Fin_Vte5 Date End (V5)	  */
	public void setFecha_Fin_Vte5 (Timestamp Fecha_Fin_Vte5)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vte5, Fecha_Fin_Vte5);
	}

	/** Get Date End (V5).
		@return Date End (V5)	  */
	public Timestamp getFecha_Fin_Vte5 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vte5);
	}

	/** Set Date End (V6).
		@param Fecha_Fin_Vte6 Date End (V6)	  */
	public void setFecha_Fin_Vte6 (Timestamp Fecha_Fin_Vte6)
	{
		set_Value (COLUMNNAME_Fecha_Fin_Vte6, Fecha_Fin_Vte6);
	}

	/** Get Date End (V6).
		@return Date End (V6)	  */
	public Timestamp getFecha_Fin_Vte6 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Vte6);
	}

	/** Set Initial Date Breast Cancer.
		@param Fecha_Ini_Breast Initial Date Breast Cancer	  */
	public void setFecha_Ini_Breast (Timestamp Fecha_Ini_Breast)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Breast, Fecha_Ini_Breast);
	}

	/** Get Initial Date Breast Cancer.
		@return Initial Date Breast Cancer	  */
	public Timestamp getFecha_Ini_Breast () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Breast);
	}

	/** Set Initial Date Colorectal Cancer.
		@param Fecha_Ini_Colorectal Initial Date Colorectal Cancer	  */
	public void setFecha_Ini_Colorectal (Timestamp Fecha_Ini_Colorectal)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Colorectal, Fecha_Ini_Colorectal);
	}

	/** Get Initial Date Colorectal Cancer.
		@return Initial Date Colorectal Cancer	  */
	public Timestamp getFecha_Ini_Colorectal () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Colorectal);
	}

	/** Set Initial Date Hypertension.
		@param Fecha_Ini_Hypertension Initial Date Hypertension	  */
	public void setFecha_Ini_Hypertension (Timestamp Fecha_Ini_Hypertension)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Hypertension, Fecha_Ini_Hypertension);
	}

	/** Get Initial Date Hypertension.
		@return Initial Date Hypertension	  */
	public Timestamp getFecha_Ini_Hypertension () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Hypertension);
	}

	/** Set Initial Date IMC.
		@param Fecha_Ini_Imc Initial Date IMC	  */
	public void setFecha_Ini_Imc (Timestamp Fecha_Ini_Imc)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Imc, Fecha_Ini_Imc);
	}

	/** Get Initial Date IMC.
		@return Initial Date IMC	  */
	public Timestamp getFecha_Ini_Imc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Imc);
	}

	/** Set Initial Date Influenza.
		@param Fecha_Ini_Influenza Initial Date Influenza	  */
	public void setFecha_Ini_Influenza (Timestamp Fecha_Ini_Influenza)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Influenza, Fecha_Ini_Influenza);
	}

	/** Get Initial Date Influenza.
		@return Initial Date Influenza	  */
	public Timestamp getFecha_Ini_Influenza () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Influenza);
	}

	/** Set Initial Date Pneumonia.
		@param Fecha_Ini_Neumonia Initial Date Pneumonia	  */
	public void setFecha_Ini_Neumonia (Timestamp Fecha_Ini_Neumonia)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Neumonia, Fecha_Ini_Neumonia);
	}

	/** Get Initial Date Pneumonia.
		@return Initial Date Pneumonia	  */
	public Timestamp getFecha_Ini_Neumonia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Neumonia);
	}

	/** Set Fecha Ini NQF 1.
		@param Fecha_Ini_NQF1 Fecha Ini NQF 1	  */
	public void setFecha_Ini_NQF1 (Timestamp Fecha_Ini_NQF1)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF1, Fecha_Ini_NQF1);
	}

	/** Get Fecha Ini NQF 1.
		@return Fecha Ini NQF 1	  */
	public Timestamp getFecha_Ini_NQF1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF1);
	}

	/** Set Fecha Ini NQF 18.
		@param Fecha_Ini_NQF18 Fecha Ini NQF 18	  */
	public void setFecha_Ini_NQF18 (Timestamp Fecha_Ini_NQF18)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF18, Fecha_Ini_NQF18);
	}

	/** Get Fecha Ini NQF 18.
		@return Fecha Ini NQF 18	  */
	public Timestamp getFecha_Ini_NQF18 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF18);
	}

	/** Set Fecha Ini NQF 27.
		@param Fecha_Ini_NQF27 Fecha Ini NQF 27	  */
	public void setFecha_Ini_NQF27 (Timestamp Fecha_Ini_NQF27)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF27, Fecha_Ini_NQF27);
	}

	/** Get Fecha Ini NQF 27.
		@return Fecha Ini NQF 27	  */
	public Timestamp getFecha_Ini_NQF27 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF27);
	}

	/** Set Fecha Ini NQF 31.
		@param Fecha_Ini_NQF31 Fecha Ini NQF 31	  */
	public void setFecha_Ini_NQF31 (Timestamp Fecha_Ini_NQF31)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF31, Fecha_Ini_NQF31);
	}

	/** Get Fecha Ini NQF 31.
		@return Fecha Ini NQF 31	  */
	public Timestamp getFecha_Ini_NQF31 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF31);
	}

	/** Set Fecha Ini NQF 43.
		@param Fecha_Ini_NQF43 Fecha Ini NQF 43	  */
	public void setFecha_Ini_NQF43 (Timestamp Fecha_Ini_NQF43)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF43, Fecha_Ini_NQF43);
	}

	/** Get Fecha Ini NQF 43.
		@return Fecha Ini NQF 43	  */
	public Timestamp getFecha_Ini_NQF43 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF43);
	}

	/** Set Fecha Ini NQF 52.
		@param Fecha_Ini_NQF52 Fecha Ini NQF 52	  */
	public void setFecha_Ini_NQF52 (Timestamp Fecha_Ini_NQF52)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF52, Fecha_Ini_NQF52);
	}

	/** Get Fecha Ini NQF 52.
		@return Fecha Ini NQF 52	  */
	public Timestamp getFecha_Ini_NQF52 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF52);
	}

	/** Set Fecha Ini NQF 59.
		@param Fecha_Ini_NQF59 Fecha Ini NQF 59	  */
	public void setFecha_Ini_NQF59 (Timestamp Fecha_Ini_NQF59)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF59, Fecha_Ini_NQF59);
	}

	/** Get Fecha Ini NQF 59.
		@return Fecha Ini NQF 59	  */
	public Timestamp getFecha_Ini_NQF59 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF59);
	}

	/** Set Fecha Ini NQF 67.
		@param Fecha_Ini_NQF67 Fecha Ini NQF 67	  */
	public void setFecha_Ini_NQF67 (Timestamp Fecha_Ini_NQF67)
	{
		set_Value (COLUMNNAME_Fecha_Ini_NQF67, Fecha_Ini_NQF67);
	}

	/** Get Fecha Ini NQF 67.
		@return Fecha Ini NQF 67	  */
	public Timestamp getFecha_Ini_NQF67 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_NQF67);
	}

	/** Set Initial Date Weight.
		@param Fecha_Ini_Peso Initial Date Weight	  */
	public void setFecha_Ini_Peso (Timestamp Fecha_Ini_Peso)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Peso, Fecha_Ini_Peso);
	}

	/** Get Initial Date Weight.
		@return Initial Date Weight	  */
	public Timestamp getFecha_Ini_Peso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Peso);
	}

	/** Set Initial Date Smoke.
		@param Fecha_Ini_Smoke Initial Date Smoke	  */
	public void setFecha_Ini_Smoke (Timestamp Fecha_Ini_Smoke)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Smoke, Fecha_Ini_Smoke);
	}

	/** Get Initial Date Smoke.
		@return Initial Date Smoke	  */
	public Timestamp getFecha_Ini_Smoke () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Smoke);
	}

	/** Set Date Initial (S10).
		@param Fecha_Ini_Stroke10 Date Initial (S10)	  */
	public void setFecha_Ini_Stroke10 (Timestamp Fecha_Ini_Stroke10)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Stroke10, Fecha_Ini_Stroke10);
	}

	/** Get Date Initial (S10).
		@return Date Initial (S10)	  */
	public Timestamp getFecha_Ini_Stroke10 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Stroke10);
	}

	/** Set Date Initial (S4).
		@param Fecha_Ini_Stroke4 Date Initial (S4)	  */
	public void setFecha_Ini_Stroke4 (Timestamp Fecha_Ini_Stroke4)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Stroke4, Fecha_Ini_Stroke4);
	}

	/** Get Date Initial (S4).
		@return Date Initial (S4)	  */
	public Timestamp getFecha_Ini_Stroke4 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Stroke4);
	}

	/** Set Date Initial (S5).
		@param Fecha_Ini_Stroke5 Date Initial (S5)	  */
	public void setFecha_Ini_Stroke5 (Timestamp Fecha_Ini_Stroke5)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Stroke5, Fecha_Ini_Stroke5);
	}

	/** Get Date Initial (S5).
		@return Date Initial (S5)	  */
	public Timestamp getFecha_Ini_Stroke5 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Stroke5);
	}

	/** Set Date Initial (S6).
		@param Fecha_Ini_Stroke6 Date Initial (S6)	  */
	public void setFecha_Ini_Stroke6 (Timestamp Fecha_Ini_Stroke6)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Stroke6, Fecha_Ini_Stroke6);
	}

	/** Get Date Initial (S6).
		@return Date Initial (S6)	  */
	public Timestamp getFecha_Ini_Stroke6 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Stroke6);
	}

	/** Set Date Initial (S8).
		@param Fecha_Ini_Stroke8 Date Initial (S8)	  */
	public void setFecha_Ini_Stroke8 (Timestamp Fecha_Ini_Stroke8)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Stroke8, Fecha_Ini_Stroke8);
	}

	/** Get Date Initial (S8).
		@return Date Initial (S8)	  */
	public Timestamp getFecha_Ini_Stroke8 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Stroke8);
	}

	/** Set Initial Date Tobacco.
		@param Fecha_Ini_Tabaco Initial Date Tobacco	  */
	public void setFecha_Ini_Tabaco (Timestamp Fecha_Ini_Tabaco)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Tabaco, Fecha_Ini_Tabaco);
	}

	/** Get Initial Date Tobacco.
		@return Initial Date Tobacco	  */
	public Timestamp getFecha_Ini_Tabaco () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Tabaco);
	}

	/** Set Date Initial Emergency.
		@param Fecha_Ini_Urgencia Date Initial Emergency	  */
	public void setFecha_Ini_Urgencia (Timestamp Fecha_Ini_Urgencia)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Urgencia, Fecha_Ini_Urgencia);
	}

	/** Get Date Initial Emergency.
		@return Date Initial Emergency	  */
	public Timestamp getFecha_Ini_Urgencia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Urgencia);
	}

	/** Set Date Initial Emergency (ED).
		@param Fecha_Ini_Urgencia_ED Date Initial Emergency (ED)	  */
	public void setFecha_Ini_Urgencia_ED (Timestamp Fecha_Ini_Urgencia_ED)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Urgencia_ED, Fecha_Ini_Urgencia_ED);
	}

	/** Get Date Initial Emergency (ED).
		@return Date Initial Emergency (ED)	  */
	public Timestamp getFecha_Ini_Urgencia_ED () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Urgencia_ED);
	}

	/** Set Date Initial Emergency (S2).
		@param Fecha_Ini_Urgencia_S2 Date Initial Emergency (S2)	  */
	public void setFecha_Ini_Urgencia_S2 (Timestamp Fecha_Ini_Urgencia_S2)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Urgencia_S2, Fecha_Ini_Urgencia_S2);
	}

	/** Get Date Initial Emergency (S2).
		@return Date Initial Emergency (S2)	  */
	public Timestamp getFecha_Ini_Urgencia_S2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Urgencia_S2);
	}

	/** Set Date Initial (S3).
		@param Fecha_Ini_Urgencia_S3 Date Initial (S3)	  */
	public void setFecha_Ini_Urgencia_S3 (Timestamp Fecha_Ini_Urgencia_S3)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Urgencia_S3, Fecha_Ini_Urgencia_S3);
	}

	/** Get Date Initial (S3).
		@return Date Initial (S3)	  */
	public Timestamp getFecha_Ini_Urgencia_S3 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Urgencia_S3);
	}

	/** Set Initial Date Vaccine.
		@param Fecha_Ini_Vacunas Initial Date Vaccine	  */
	public void setFecha_Ini_Vacunas (Timestamp Fecha_Ini_Vacunas)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vacunas, Fecha_Ini_Vacunas);
	}

	/** Get Initial Date Vaccine.
		@return Initial Date Vaccine	  */
	public Timestamp getFecha_Ini_Vacunas () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vacunas);
	}

	/** Set Date Initial (V1).
		@param Fecha_Ini_Vte1 Date Initial (V1)	  */
	public void setFecha_Ini_Vte1 (Timestamp Fecha_Ini_Vte1)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vte1, Fecha_Ini_Vte1);
	}

	/** Get Date Initial (V1).
		@return Date Initial (V1)	  */
	public Timestamp getFecha_Ini_Vte1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vte1);
	}

	/** Set Date Initial (V2).
		@param Fecha_Ini_Vte2 Date Initial (V2)	  */
	public void setFecha_Ini_Vte2 (Timestamp Fecha_Ini_Vte2)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vte2, Fecha_Ini_Vte2);
	}

	/** Get Date Initial (V2).
		@return Date Initial (V2)	  */
	public Timestamp getFecha_Ini_Vte2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vte2);
	}

	/** Set Date Initial (V3).
		@param Fecha_Ini_Vte3 Date Initial (V3)	  */
	public void setFecha_Ini_Vte3 (Timestamp Fecha_Ini_Vte3)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vte3, Fecha_Ini_Vte3);
	}

	/** Get Date Initial (V3).
		@return Date Initial (V3)	  */
	public Timestamp getFecha_Ini_Vte3 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vte3);
	}

	/** Set Date Initial (V4).
		@param Fecha_Ini_Vte4 Date Initial (V4)	  */
	public void setFecha_Ini_Vte4 (Timestamp Fecha_Ini_Vte4)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vte4, Fecha_Ini_Vte4);
	}

	/** Get Date Initial (V4).
		@return Date Initial (V4)	  */
	public Timestamp getFecha_Ini_Vte4 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vte4);
	}

	/** Set Date Initial (V5).
		@param Fecha_Ini_Vte5 Date Initial (V5)	  */
	public void setFecha_Ini_Vte5 (Timestamp Fecha_Ini_Vte5)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vte5, Fecha_Ini_Vte5);
	}

	/** Get Date Initial (V5).
		@return Date Initial (V5)	  */
	public Timestamp getFecha_Ini_Vte5 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vte5);
	}

	/** Set Date Initial (V6).
		@param Fecha_Ini_Vte6 Date Initial (V6)	  */
	public void setFecha_Ini_Vte6 (Timestamp Fecha_Ini_Vte6)
	{
		set_Value (COLUMNNAME_Fecha_Ini_Vte6, Fecha_Ini_Vte6);
	}

	/** Get Date Initial (V6).
		@return Date Initial (V6)	  */
	public Timestamp getFecha_Ini_Vte6 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Vte6);
	}

	/** Set Follow Plan BMI.
		@param FollowPlan Follow Plan BMI	  */
	public void setFollowPlan (int FollowPlan)
	{
		set_Value (COLUMNNAME_FollowPlan, Integer.valueOf(FollowPlan));
	}

	/** Get Follow Plan BMI.
		@return Follow Plan BMI	  */
	public int getFollowPlan () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FollowPlan);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HBA1C.
		@param HBA1C HBA1C	  */
	public void setHBA1C (int HBA1C)
	{
		set_Value (COLUMNNAME_HBA1C, Integer.valueOf(HBA1C));
	}

	/** Get HBA1C.
		@return HBA1C	  */
	public int getHBA1C () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HBA1C);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hemorrhagic Stroke.
		@param Hemorrhagic_Stroke Hemorrhagic Stroke	  */
	public void setHemorrhagic_Stroke (int Hemorrhagic_Stroke)
	{
		set_Value (COLUMNNAME_Hemorrhagic_Stroke, Integer.valueOf(Hemorrhagic_Stroke));
	}

	/** Get Hemorrhagic Stroke.
		@return Hemorrhagic Stroke	  */
	public int getHemorrhagic_Stroke () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Hemorrhagic_Stroke);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hypertension.
		@param Hypertension Hypertension	  */
	public void setHypertension (int Hypertension)
	{
		set_Value (COLUMNNAME_Hypertension, Integer.valueOf(Hypertension));
	}

	/** Get Hypertension.
		@return Hypertension	  */
	public int getHypertension () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Hypertension);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set INR Loinc.
		@param INR_Loinc INR Loinc	  */
	public void setINR_Loinc (int INR_Loinc)
	{
		set_Value (COLUMNNAME_INR_Loinc, Integer.valueOf(INR_Loinc));
	}

	/** Get INR Loinc.
		@return INR Loinc	  */
	public int getINR_Loinc () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_INR_Loinc);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set INR Measure.
		@param INR_Measure INR Measure	  */
	public void setINR_Measure (int INR_Measure)
	{
		set_Value (COLUMNNAME_INR_Measure, Integer.valueOf(INR_Measure));
	}

	/** Get INR Measure.
		@return INR Measure	  */
	public int getINR_Measure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_INR_Measure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set INR Procedure.
		@param INR_Procedure INR Procedure	  */
	public void setINR_Procedure (int INR_Procedure)
	{
		set_Value (COLUMNNAME_INR_Procedure, Integer.valueOf(INR_Procedure));
	}

	/** Get INR Procedure.
		@return INR Procedure	  */
	public int getINR_Procedure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_INR_Procedure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ischemic Stroke.
		@param Ischemic_Stroke Ischemic Stroke	  */
	public void setIschemic_Stroke (int Ischemic_Stroke)
	{
		set_Value (COLUMNNAME_Ischemic_Stroke, Integer.valueOf(Ischemic_Stroke));
	}

	/** Get Ischemic Stroke.
		@return Ischemic Stroke	  */
	public int getIschemic_Stroke () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ischemic_Stroke);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LDL Measure.
		@param LDL_Measure LDL Measure	  */
	public void setLDL_Measure (int LDL_Measure)
	{
		set_Value (COLUMNNAME_LDL_Measure, Integer.valueOf(LDL_Measure));
	}

	/** Get LDL Measure.
		@return LDL Measure	  */
	public int getLDL_Measure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LDL_Measure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lipid Loinc.
		@param Lipid_Loinc Lipid Loinc	  */
	public void setLipid_Loinc (int Lipid_Loinc)
	{
		set_Value (COLUMNNAME_Lipid_Loinc, Integer.valueOf(Lipid_Loinc));
	}

	/** Get Lipid Loinc.
		@return Lipid Loinc	  */
	public int getLipid_Loinc () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lipid_Loinc);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lipid Medication.
		@param Lipid_Medication Lipid Medication	  */
	public void setLipid_Medication (int Lipid_Medication)
	{
		set_Value (COLUMNNAME_Lipid_Medication, Integer.valueOf(Lipid_Medication));
	}

	/** Get Lipid Medication.
		@return Lipid Medication	  */
	public int getLipid_Medication () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lipid_Medication);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lipid Procedure.
		@param Lipid_Procedure Lipid Procedure	  */
	public void setLipid_Procedure (int Lipid_Procedure)
	{
		set_Value (COLUMNNAME_Lipid_Procedure, Integer.valueOf(Lipid_Procedure));
	}

	/** Get Lipid Procedure.
		@return Lipid Procedure	  */
	public int getLipid_Procedure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lipid_Procedure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Low Back Pain.
		@param LowBackPain Low Back Pain	  */
	public void setLowBackPain (int LowBackPain)
	{
		set_Value (COLUMNNAME_LowBackPain, Integer.valueOf(LowBackPain));
	}

	/** Get Low Back Pain.
		@return Low Back Pain	  */
	public int getLowBackPain () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LowBackPain);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mental Patient.
		@param MentalPatient Mental Patient	  */
	public void setMentalPatient (int MentalPatient)
	{
		set_Value (COLUMNNAME_MentalPatient, Integer.valueOf(MentalPatient));
	}

	/** Get Mental Patient.
		@return Mental Patient	  */
	public int getMentalPatient () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MentalPatient);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Anticoagulants.
		@param M_Product_Anticoagulante_ID Anticoagulants	  */
	public void setM_Product_Anticoagulante_ID (int M_Product_Anticoagulante_ID)
	{
		if (M_Product_Anticoagulante_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Anticoagulante_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Anticoagulante_ID, Integer.valueOf(M_Product_Anticoagulante_ID));
	}

	/** Get Anticoagulants.
		@return Anticoagulants	  */
	public int getM_Product_Anticoagulante_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Anticoagulante_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Antithrombotic.
		@param M_Product_Antitrombosis_ID Antithrombotic	  */
	public void setM_Product_Antitrombosis_ID (int M_Product_Antitrombosis_ID)
	{
		if (M_Product_Antitrombosis_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Antitrombosis_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Antitrombosis_ID, Integer.valueOf(M_Product_Antitrombosis_ID));
	}

	/** Get Antithrombotic.
		@return Antithrombotic	  */
	public int getM_Product_Antitrombosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Antitrombosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Breast Cancer Screening.
		@param M_Product_BreastCancer_ID Breast Cancer Screening	  */
	public void setM_Product_BreastCancer_ID (int M_Product_BreastCancer_ID)
	{
		if (M_Product_BreastCancer_ID < 1) 
			set_Value (COLUMNNAME_M_Product_BreastCancer_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_BreastCancer_ID, Integer.valueOf(M_Product_BreastCancer_ID));
	}

	/** Get Breast Cancer Screening.
		@return Breast Cancer Screening	  */
	public int getM_Product_BreastCancer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_BreastCancer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Colorectal Cancer Screening.
		@param M_Product_ColorectalCancer_ID Colorectal Cancer Screening	  */
	public void setM_Product_ColorectalCancer_ID (int M_Product_ColorectalCancer_ID)
	{
		if (M_Product_ColorectalCancer_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ColorectalCancer_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ColorectalCancer_ID, Integer.valueOf(M_Product_ColorectalCancer_ID));
	}

	/** Get Colorectal Cancer Screening.
		@return Colorectal Cancer Screening	  */
	public int getM_Product_ColorectalCancer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ColorectalCancer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Acellular Vaccines.
		@param NumAcellular Number Acellular Vaccines	  */
	public void setNumAcellular (int NumAcellular)
	{
		set_Value (COLUMNNAME_NumAcellular, Integer.valueOf(NumAcellular));
	}

	/** Get Number Acellular Vaccines.
		@return Number Acellular Vaccines	  */
	public int getNumAcellular () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumAcellular);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Chicken Pox Vaccines.
		@param NumChickenPox Number Chicken Pox Vaccines	  */
	public void setNumChickenPox (int NumChickenPox)
	{
		set_Value (COLUMNNAME_NumChickenPox, Integer.valueOf(NumChickenPox));
	}

	/** Get Number Chicken Pox Vaccines.
		@return Number Chicken Pox Vaccines	  */
	public int getNumChickenPox () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumChickenPox);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Diphteria Vaccines.
		@param NumDiphteria Number Diphteria Vaccines	  */
	public void setNumDiphteria (int NumDiphteria)
	{
		set_Value (COLUMNNAME_NumDiphteria, Integer.valueOf(NumDiphteria));
	}

	/** Get Number Diphteria Vaccines.
		@return Number Diphteria Vaccines	  */
	public int getNumDiphteria () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumDiphteria);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Influenza Flu Vaccines.
		@param NumFlu Number Influenza Flu Vaccines	  */
	public void setNumFlu (int NumFlu)
	{
		set_Value (COLUMNNAME_NumFlu, Integer.valueOf(NumFlu));
	}

	/** Get Number Influenza Flu Vaccines.
		@return Number Influenza Flu Vaccines	  */
	public int getNumFlu () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumFlu);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Hepatitis A Vaccines.
		@param NumHepatitisA Number Hepatitis A Vaccines	  */
	public void setNumHepatitisA (int NumHepatitisA)
	{
		set_Value (COLUMNNAME_NumHepatitisA, Integer.valueOf(NumHepatitisA));
	}

	/** Get Number Hepatitis A Vaccines.
		@return Number Hepatitis A Vaccines	  */
	public int getNumHepatitisA () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumHepatitisA);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Hepatitis B Vaccines.
		@param NumHepatitisB Number Hepatitis B Vaccines	  */
	public void setNumHepatitisB (int NumHepatitisB)
	{
		set_Value (COLUMNNAME_NumHepatitisB, Integer.valueOf(NumHepatitisB));
	}

	/** Get Number Hepatitis B Vaccines.
		@return Number Hepatitis B Vaccines	  */
	public int getNumHepatitisB () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumHepatitisB);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Influenza B Vaccines.
		@param NumInfluenzaB Number Influenza B Vaccines	  */
	public void setNumInfluenzaB (int NumInfluenzaB)
	{
		set_Value (COLUMNNAME_NumInfluenzaB, Integer.valueOf(NumInfluenzaB));
	}

	/** Get Number Influenza B Vaccines.
		@return Number Influenza B Vaccines	  */
	public int getNumInfluenzaB () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumInfluenzaB);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Measles Vaccine.
		@param NumMeasles Number Measles Vaccine	  */
	public void setNumMeasles (int NumMeasles)
	{
		set_Value (COLUMNNAME_NumMeasles, Integer.valueOf(NumMeasles));
	}

	/** Get Number Measles Vaccine.
		@return Number Measles Vaccine	  */
	public int getNumMeasles () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumMeasles);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Mumps Vaccine.
		@param NumMumps Number Mumps Vaccine	  */
	public void setNumMumps (int NumMumps)
	{
		set_Value (COLUMNNAME_NumMumps, Integer.valueOf(NumMumps));
	}

	/** Get Number Mumps Vaccine.
		@return Number Mumps Vaccine	  */
	public int getNumMumps () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumMumps);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Pneumococcal Vaccine.
		@param NumPneumococcal Number Pneumococcal Vaccine	  */
	public void setNumPneumococcal (int NumPneumococcal)
	{
		set_Value (COLUMNNAME_NumPneumococcal, Integer.valueOf(NumPneumococcal));
	}

	/** Get Number Pneumococcal Vaccine.
		@return Number Pneumococcal Vaccine	  */
	public int getNumPneumococcal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumPneumococcal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Polio Vaccines.
		@param NumPolio Number Polio Vaccines	  */
	public void setNumPolio (int NumPolio)
	{
		set_Value (COLUMNNAME_NumPolio, Integer.valueOf(NumPolio));
	}

	/** Get Number Polio Vaccines.
		@return Number Polio Vaccines	  */
	public int getNumPolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumPolio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Rotavirus Vaccines.
		@param NumRotavirus Number Rotavirus Vaccines	  */
	public void setNumRotavirus (int NumRotavirus)
	{
		set_Value (COLUMNNAME_NumRotavirus, Integer.valueOf(NumRotavirus));
	}

	/** Get Number Rotavirus Vaccines.
		@return Number Rotavirus Vaccines	  */
	public int getNumRotavirus () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumRotavirus);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Rubella Vaccine.
		@param NumRubella Number Rubella Vaccine	  */
	public void setNumRubella (int NumRubella)
	{
		set_Value (COLUMNNAME_NumRubella, Integer.valueOf(NumRubella));
	}

	/** Get Number Rubella Vaccine.
		@return Number Rubella Vaccine	  */
	public int getNumRubella () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumRubella);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number Tetatus Vaccine.
		@param NumTetanus Number Tetatus Vaccine	  */
	public void setNumTetanus (int NumTetanus)
	{
		set_Value (COLUMNNAME_NumTetanus, Integer.valueOf(NumTetanus));
	}

	/** Get Number Tetatus Vaccine.
		@return Number Tetatus Vaccine	  */
	public int getNumTetanus () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumTetanus);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Observation Patient.
		@param ObsPatient Observation Patient	  */
	public void setObsPatient (int ObsPatient)
	{
		set_Value (COLUMNNAME_ObsPatient, Integer.valueOf(ObsPatient));
	}

	/** Get Observation Patient.
		@return Observation Patient	  */
	public int getObsPatient () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ObsPatient);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Obstretics.
		@param Obstretics 
		Obstretics
	  */
	public void setObstretics (int Obstretics)
	{
		set_Value (COLUMNNAME_Obstretics, Integer.valueOf(Obstretics));
	}

	/** Get Obstretics.
		@return Obstretics
	  */
	public int getObstretics () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Obstretics);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Outpatient.
		@param Outpatient Outpatient	  */
	public void setOutpatient (int Outpatient)
	{
		set_Value (COLUMNNAME_Outpatient, Integer.valueOf(Outpatient));
	}

	/** Get Outpatient.
		@return Outpatient	  */
	public int getOutpatient () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Outpatient);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Counseling for Physical Activity.
		@param PhysicalActivity Counseling for Physical Activity	  */
	public void setPhysicalActivity (int PhysicalActivity)
	{
		set_Value (COLUMNNAME_PhysicalActivity, Integer.valueOf(PhysicalActivity));
	}

	/** Get Counseling for Physical Activity.
		@return Counseling for Physical Activity	  */
	public int getPhysicalActivity () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PhysicalActivity);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Platelet Loinc.
		@param Platelet_Loinc Platelet Loinc	  */
	public void setPlatelet_Loinc (int Platelet_Loinc)
	{
		set_Value (COLUMNNAME_Platelet_Loinc, Integer.valueOf(Platelet_Loinc));
	}

	/** Get Platelet Loinc.
		@return Platelet Loinc	  */
	public int getPlatelet_Loinc () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Platelet_Loinc);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Platelet Measure.
		@param Platelet_Measure Platelet Measure	  */
	public void setPlatelet_Measure (int Platelet_Measure)
	{
		set_Value (COLUMNNAME_Platelet_Measure, Integer.valueOf(Platelet_Measure));
	}

	/** Get Platelet Measure.
		@return Platelet Measure	  */
	public int getPlatelet_Measure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Platelet_Measure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Platelet Procedure.
		@param Platelet_Procedure Platelet Procedure	  */
	public void setPlatelet_Procedure (int Platelet_Procedure)
	{
		set_Value (COLUMNNAME_Platelet_Procedure, Integer.valueOf(Platelet_Procedure));
	}

	/** Get Platelet Procedure.
		@return Platelet Procedure	  */
	public int getPlatelet_Procedure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Platelet_Procedure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pregnancy.
		@param Pregnancy Pregnancy	  */
	public void setPregnancy (int Pregnancy)
	{
		set_Value (COLUMNNAME_Pregnancy, Integer.valueOf(Pregnancy));
	}

	/** Get Pregnancy.
		@return Pregnancy	  */
	public int getPregnancy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Pregnancy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Range (World Organization Health code).
		@param RangeFinAtrial Final Range (World Organization Health code)	  */
	public void setRangeFinAtrial (String RangeFinAtrial)
	{
		set_Value (COLUMNNAME_RangeFinAtrial, RangeFinAtrial);
	}

	/** Get Final Range (World Organization Health code).
		@return Final Range (World Organization Health code)	  */
	public String getRangeFinAtrial () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinAtrial);
	}

	/** Set Final Range (World Organization Health code).
		@param RangeFinCode Final Range (World Organization Health code)	  */
	public void setRangeFinCode (String RangeFinCode)
	{
		set_Value (COLUMNNAME_RangeFinCode, RangeFinCode);
	}

	/** Get Final Range (World Organization Health code).
		@return Final Range (World Organization Health code)	  */
	public String getRangeFinCode () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinCode);
	}

	/** Set Final Range (World Organization Health Code).
		@param RangeFinHemorragic Final Range (World Organization Health Code)	  */
	public void setRangeFinHemorragic (String RangeFinHemorragic)
	{
		set_Value (COLUMNNAME_RangeFinHemorragic, RangeFinHemorragic);
	}

	/** Get Final Range (World Organization Health Code).
		@return Final Range (World Organization Health Code)	  */
	public String getRangeFinHemorragic () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinHemorragic);
	}

	/** Set Final Range (World Organization Health Code).
		@param RangeFinIstroke2 Final Range (World Organization Health Code)	  */
	public void setRangeFinIstroke2 (String RangeFinIstroke2)
	{
		set_Value (COLUMNNAME_RangeFinIstroke2, RangeFinIstroke2);
	}

	/** Get Final Range (World Organization Health Code).
		@return Final Range (World Organization Health Code)	  */
	public String getRangeFinIstroke2 () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinIstroke2);
	}

	/** Set Final Range LDL.
		@param RangeFinLdl Final Range LDL	  */
	public void setRangeFinLdl (String RangeFinLdl)
	{
		set_Value (COLUMNNAME_RangeFinLdl, RangeFinLdl);
	}

	/** Get Final Range LDL.
		@return Final Range LDL	  */
	public String getRangeFinLdl () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinLdl);
	}

	/** Set Final Range.
		@param RangeFinPlaqueta Final Range	  */
	public void setRangeFinPlaqueta (String RangeFinPlaqueta)
	{
		set_Value (COLUMNNAME_RangeFinPlaqueta, RangeFinPlaqueta);
	}

	/** Get Final Range.
		@return Final Range	  */
	public String getRangeFinPlaqueta () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinPlaqueta);
	}

	/** Set Final Range.
		@param RangeFinRehabilita Final Range	  */
	public void setRangeFinRehabilita (String RangeFinRehabilita)
	{
		set_Value (COLUMNNAME_RangeFinRehabilita, RangeFinRehabilita);
	}

	/** Get Final Range.
		@return Final Range	  */
	public String getRangeFinRehabilita () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinRehabilita);
	}

	/** Set Final Range VTE.
		@param RangeFinVte Final Range VTE	  */
	public void setRangeFinVte (String RangeFinVte)
	{
		set_Value (COLUMNNAME_RangeFinVte, RangeFinVte);
	}

	/** Get Final Range VTE.
		@return Final Range VTE	  */
	public String getRangeFinVte () 
	{
		return (String)get_Value(COLUMNNAME_RangeFinVte);
	}

	/** Set Initial Range (World Organization Health Code).
		@param RangeIniAtrial Initial Range (World Organization Health Code)	  */
	public void setRangeIniAtrial (String RangeIniAtrial)
	{
		set_Value (COLUMNNAME_RangeIniAtrial, RangeIniAtrial);
	}

	/** Get Initial Range (World Organization Health Code).
		@return Initial Range (World Organization Health Code)	  */
	public String getRangeIniAtrial () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniAtrial);
	}

	/** Set Initial Range (World Organization Health Code).
		@param RangeIniCode Initial Range (World Organization Health Code)	  */
	public void setRangeIniCode (String RangeIniCode)
	{
		set_Value (COLUMNNAME_RangeIniCode, RangeIniCode);
	}

	/** Get Initial Range (World Organization Health Code).
		@return Initial Range (World Organization Health Code)	  */
	public String getRangeIniCode () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniCode);
	}

	/** Set Initial Range (World Organization Health Code).
		@param RangeIniHemorragic Initial Range (World Organization Health Code)	  */
	public void setRangeIniHemorragic (String RangeIniHemorragic)
	{
		set_Value (COLUMNNAME_RangeIniHemorragic, RangeIniHemorragic);
	}

	/** Get Initial Range (World Organization Health Code).
		@return Initial Range (World Organization Health Code)	  */
	public String getRangeIniHemorragic () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniHemorragic);
	}

	/** Set Initial Range (World Organization Health Code).
		@param RangeIniIstroke2 Initial Range (World Organization Health Code)	  */
	public void setRangeIniIstroke2 (String RangeIniIstroke2)
	{
		set_Value (COLUMNNAME_RangeIniIstroke2, RangeIniIstroke2);
	}

	/** Get Initial Range (World Organization Health Code).
		@return Initial Range (World Organization Health Code)	  */
	public String getRangeIniIstroke2 () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniIstroke2);
	}

	/** Set Initial Range LDL.
		@param RangeIniLdl Initial Range LDL	  */
	public void setRangeIniLdl (String RangeIniLdl)
	{
		set_Value (COLUMNNAME_RangeIniLdl, RangeIniLdl);
	}

	/** Get Initial Range LDL.
		@return Initial Range LDL	  */
	public String getRangeIniLdl () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniLdl);
	}

	/** Set Initial Range.
		@param RangeIniPlaqueta Initial Range	  */
	public void setRangeIniPlaqueta (String RangeIniPlaqueta)
	{
		set_Value (COLUMNNAME_RangeIniPlaqueta, RangeIniPlaqueta);
	}

	/** Get Initial Range.
		@return Initial Range	  */
	public String getRangeIniPlaqueta () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniPlaqueta);
	}

	/** Set Initial Range.
		@param RangeIniRehabilita Initial Range	  */
	public void setRangeIniRehabilita (String RangeIniRehabilita)
	{
		set_Value (COLUMNNAME_RangeIniRehabilita, RangeIniRehabilita);
	}

	/** Get Initial Range.
		@return Initial Range	  */
	public String getRangeIniRehabilita () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniRehabilita);
	}

	/** Set Initial Range VTE.
		@param RangeIniVte Initial Range VTE	  */
	public void setRangeIniVte (String RangeIniVte)
	{
		set_Value (COLUMNNAME_RangeIniVte, RangeIniVte);
	}

	/** Get Initial Range VTE.
		@return Initial Range VTE	  */
	public String getRangeIniVte () 
	{
		return (String)get_Value(COLUMNNAME_RangeIniVte);
	}

	/** Set Rehabilitation Procedure.
		@param Rehab_Procedure Rehabilitation Procedure	  */
	public void setRehab_Procedure (int Rehab_Procedure)
	{
		set_Value (COLUMNNAME_Rehab_Procedure, Integer.valueOf(Rehab_Procedure));
	}

	/** Get Rehabilitation Procedure.
		@return Rehabilitation Procedure	  */
	public int getRehab_Procedure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rehab_Procedure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imaging Study Spinal.
		@param StudySpinal Imaging Study Spinal	  */
	public void setStudySpinal (int StudySpinal)
	{
		set_Value (COLUMNNAME_StudySpinal, Integer.valueOf(StudySpinal));
	}

	/** Get Imaging Study Spinal.
		@return Imaging Study Spinal	  */
	public int getStudySpinal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StudySpinal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tobacco Use Cessation Counseling.
		@param TobaccoCessationCounseling Tobacco Use Cessation Counseling	  */
	public void setTobaccoCessationCounseling (int TobaccoCessationCounseling)
	{
		set_Value (COLUMNNAME_TobaccoCessationCounseling, Integer.valueOf(TobaccoCessationCounseling));
	}

	/** Get Tobacco Use Cessation Counseling.
		@return Tobacco Use Cessation Counseling	  */
	public int getTobaccoCessationCounseling () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TobaccoCessationCounseling);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trial Reason.
		@param Trial_Reason Trial Reason	  */
	public void setTrial_Reason (int Trial_Reason)
	{
		set_Value (COLUMNNAME_Trial_Reason, Integer.valueOf(Trial_Reason));
	}

	/** Get Trial Reason.
		@return Trial Reason	  */
	public int getTrial_Reason () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Trial_Reason);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set VTE Diagnostic.
		@param VTE_Diagnostic VTE Diagnostic	  */
	public void setVTE_Diagnostic (int VTE_Diagnostic)
	{
		set_Value (COLUMNNAME_VTE_Diagnostic, Integer.valueOf(VTE_Diagnostic));
	}

	/** Get VTE Diagnostic.
		@return VTE Diagnostic	  */
	public int getVTE_Diagnostic () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VTE_Diagnostic);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warfarin Procedure.
		@param Warfarin_Procedure Warfarin Procedure	  */
	public void setWarfarin_Procedure (int Warfarin_Procedure)
	{
		set_Value (COLUMNNAME_Warfarin_Procedure, Integer.valueOf(Warfarin_Procedure));
	}

	/** Get Warfarin Procedure.
		@return Warfarin Procedure	  */
	public int getWarfarin_Procedure () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Warfarin_Procedure);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}