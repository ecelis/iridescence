/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigMedico
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_ConfigMedico 
{

    /** TableName=EXME_ConfigMedico */
    public static final String Table_Name = "EXME_ConfigMedico";

    /** AD_Table_ID=1200889 */
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

    /** Column name DrugAller */
    public static final String COLUMNNAME_DrugAller = "DrugAller";

	/** Set Drug to Allergy Validation	  */
	public void setDrugAller (boolean DrugAller);

	/** Get Drug to Allergy Validation	  */
	public boolean isDrugAller();

    /** Column name DrugAllerLevel */
    public static final String COLUMNNAME_DrugAllerLevel = "DrugAllerLevel";

	/** Set Level of Severity	  */
	public void setDrugAllerLevel (String DrugAllerLevel);

	/** Get Level of Severity	  */
	public String getDrugAllerLevel();

    /** Column name DrugDrug */
    public static final String COLUMNNAME_DrugDrug = "DrugDrug";

	/** Set Drug to Drug Validation	  */
	public void setDrugDrug (boolean DrugDrug);

	/** Get Drug to Drug Validation	  */
	public boolean isDrugDrug();

    /** Column name DrugDrugLevel */
    public static final String COLUMNNAME_DrugDrugLevel = "DrugDrugLevel";

	/** Set Level of Severity	  */
	public void setDrugDrugLevel (String DrugDrugLevel);

	/** Get Level of Severity	  */
	public String getDrugDrugLevel();

    /** Column name DrugFood */
    public static final String COLUMNNAME_DrugFood = "DrugFood";

	/** Set Drug to Food Validation	  */
	public void setDrugFood (boolean DrugFood);

	/** Get Drug to Food Validation	  */
	public boolean isDrugFood();

    /** Column name DrugFoodLevel */
    public static final String COLUMNNAME_DrugFoodLevel = "DrugFoodLevel";

	/** Set Level of Severity	  */
	public void setDrugFoodLevel (String DrugFoodLevel);

	/** Get Level of Severity	  */
	public String getDrugFoodLevel();

    /** Column name DuplicateTherapy */
    public static final String COLUMNNAME_DuplicateTherapy = "DuplicateTherapy";

	/** Set DuplicateTherapy Validation	  */
	public void setDuplicateTherapy (boolean DuplicateTherapy);

	/** Get DuplicateTherapy Validation	  */
	public boolean isDuplicateTherapy();

    /** Column name DuplicateTherapyAllow */
    public static final String COLUMNNAME_DuplicateTherapyAllow = "DuplicateTherapyAllow";

	/** Set Allow Duplicate Therapy	  */
	public void setDuplicateTherapyAllow (boolean DuplicateTherapyAllow);

	/** Get Allow Duplicate Therapy	  */
	public boolean isDuplicateTherapyAllow();

    /** Column name EXME_ConfigMedico_ID */
    public static final String COLUMNNAME_EXME_ConfigMedico_ID = "EXME_ConfigMedico_ID";

	/** Set Doctor Configuration	  */
	public void setEXME_ConfigMedico_ID (int EXME_ConfigMedico_ID);

	/** Get Doctor Configuration	  */
	public int getEXME_ConfigMedico_ID();

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

    /** Column name MedicalCondition */
    public static final String COLUMNNAME_MedicalCondition = "MedicalCondition";

	/** Set Medical Condition Validation	  */
	public void setMedicalCondition (boolean MedicalCondition);

	/** Get Medical Condition Validation	  */
	public boolean isMedicalCondition();

    /** Column name MedicalConditionLevel */
    public static final String COLUMNNAME_MedicalConditionLevel = "MedicalConditionLevel";

	/** Set Level of Severity	  */
	public void setMedicalConditionLevel (String MedicalConditionLevel);

	/** Get Level of Severity	  */
	public String getMedicalConditionLevel();

    /** Column name MultiWindow */
    public static final String COLUMNNAME_MultiWindow = "MultiWindow";

	/** Set MultiWindow	  */
	public void setMultiWindow (boolean MultiWindow);

	/** Get MultiWindow	  */
	public boolean isMultiWindow();

    /** Column name SimpleWindow */
    public static final String COLUMNNAME_SimpleWindow = "SimpleWindow";

	/** Set SimpleWindow	  */
	public void setSimpleWindow (boolean SimpleWindow);

	/** Get SimpleWindow	  */
	public boolean isSimpleWindow();
}
