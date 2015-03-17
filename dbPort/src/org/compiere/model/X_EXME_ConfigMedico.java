/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ConfigMedico
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_ConfigMedico extends PO implements I_EXME_ConfigMedico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigMedico (Properties ctx, int EXME_ConfigMedico_ID, String trxName)
    {
      super (ctx, EXME_ConfigMedico_ID, trxName);
      /** if (EXME_ConfigMedico_ID == 0)
        {
			setDrugAller (false);
			setDrugDrug (false);
			setDrugFood (false);
			setDuplicateTherapy (false);
			setDuplicateTherapyAllow (false);
			setEXME_ConfigMedico_ID (0);
			setEXME_Medico_ID (0);
			setMedicalCondition (false);
			setMultiWindow (false);
			setSimpleWindow (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigMedico (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigMedico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Drug to Allergy Validation.
		@param DrugAller Drug to Allergy Validation	  */
	public void setDrugAller (boolean DrugAller)
	{
		set_Value (COLUMNNAME_DrugAller, Boolean.valueOf(DrugAller));
	}

	/** Get Drug to Allergy Validation.
		@return Drug to Allergy Validation	  */
	public boolean isDrugAller () 
	{
		Object oo = get_Value(COLUMNNAME_DrugAller);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Level of Severity.
		@param DrugAllerLevel Level of Severity	  */
	public void setDrugAllerLevel (String DrugAllerLevel)
	{
		set_Value (COLUMNNAME_DrugAllerLevel, DrugAllerLevel);
	}

	/** Get Level of Severity.
		@return Level of Severity	  */
	public String getDrugAllerLevel () 
	{
		return (String)get_Value(COLUMNNAME_DrugAllerLevel);
	}

	/** Set Drug to Drug Validation.
		@param DrugDrug Drug to Drug Validation	  */
	public void setDrugDrug (boolean DrugDrug)
	{
		set_Value (COLUMNNAME_DrugDrug, Boolean.valueOf(DrugDrug));
	}

	/** Get Drug to Drug Validation.
		@return Drug to Drug Validation	  */
	public boolean isDrugDrug () 
	{
		Object oo = get_Value(COLUMNNAME_DrugDrug);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** DrugDrugLevel AD_Reference_ID=1200517 */
	public static final int DRUGDRUGLEVEL_AD_Reference_ID=1200517;
	/** Minor Drug Interaction = 1 */
	public static final String DRUGDRUGLEVEL_MinorDrugInteraction = "1";
	/** Moderate Drug Interaction = 2 */
	public static final String DRUGDRUGLEVEL_ModerateDrugInteraction = "2";
	/** Major Drug Interaction = 3 */
	public static final String DRUGDRUGLEVEL_MajorDrugInteraction = "3";
	/** Set Level of Severity.
		@param DrugDrugLevel Level of Severity	  */
	public void setDrugDrugLevel (String DrugDrugLevel)
	{

		if (DrugDrugLevel == null || DrugDrugLevel.equals("1") || DrugDrugLevel.equals("2") || DrugDrugLevel.equals("3")); else throw new IllegalArgumentException ("DrugDrugLevel Invalid value - " + DrugDrugLevel + " - Reference_ID=1200517 - 1 - 2 - 3");		set_Value (COLUMNNAME_DrugDrugLevel, DrugDrugLevel);
	}

	/** Get Level of Severity.
		@return Level of Severity	  */
	public String getDrugDrugLevel () 
	{
		return (String)get_Value(COLUMNNAME_DrugDrugLevel);
	}

	/** Set Drug to Food Validation.
		@param DrugFood Drug to Food Validation	  */
	public void setDrugFood (boolean DrugFood)
	{
		set_Value (COLUMNNAME_DrugFood, Boolean.valueOf(DrugFood));
	}

	/** Get Drug to Food Validation.
		@return Drug to Food Validation	  */
	public boolean isDrugFood () 
	{
		Object oo = get_Value(COLUMNNAME_DrugFood);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** DrugFoodLevel AD_Reference_ID=1200517 */
	public static final int DRUGFOODLEVEL_AD_Reference_ID=1200517;
	/** Minor Drug Interaction = 1 */
	public static final String DRUGFOODLEVEL_MinorDrugInteraction = "1";
	/** Moderate Drug Interaction = 2 */
	public static final String DRUGFOODLEVEL_ModerateDrugInteraction = "2";
	/** Major Drug Interaction = 3 */
	public static final String DRUGFOODLEVEL_MajorDrugInteraction = "3";
	/** Set Level of Severity.
		@param DrugFoodLevel Level of Severity	  */
	public void setDrugFoodLevel (String DrugFoodLevel)
	{

		if (DrugFoodLevel == null || DrugFoodLevel.equals("1") || DrugFoodLevel.equals("2") || DrugFoodLevel.equals("3")); else throw new IllegalArgumentException ("DrugFoodLevel Invalid value - " + DrugFoodLevel + " - Reference_ID=1200517 - 1 - 2 - 3");		set_Value (COLUMNNAME_DrugFoodLevel, DrugFoodLevel);
	}

	/** Get Level of Severity.
		@return Level of Severity	  */
	public String getDrugFoodLevel () 
	{
		return (String)get_Value(COLUMNNAME_DrugFoodLevel);
	}

	/** Set DuplicateTherapy Validation.
		@param DuplicateTherapy DuplicateTherapy Validation	  */
	public void setDuplicateTherapy (boolean DuplicateTherapy)
	{
		set_Value (COLUMNNAME_DuplicateTherapy, Boolean.valueOf(DuplicateTherapy));
	}

	/** Get DuplicateTherapy Validation.
		@return DuplicateTherapy Validation	  */
	public boolean isDuplicateTherapy () 
	{
		Object oo = get_Value(COLUMNNAME_DuplicateTherapy);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Duplicate Therapy.
		@param DuplicateTherapyAllow Allow Duplicate Therapy	  */
	public void setDuplicateTherapyAllow (boolean DuplicateTherapyAllow)
	{
		set_Value (COLUMNNAME_DuplicateTherapyAllow, Boolean.valueOf(DuplicateTherapyAllow));
	}

	/** Get Allow Duplicate Therapy.
		@return Allow Duplicate Therapy	  */
	public boolean isDuplicateTherapyAllow () 
	{
		Object oo = get_Value(COLUMNNAME_DuplicateTherapyAllow);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Doctor Configuration.
		@param EXME_ConfigMedico_ID Doctor Configuration	  */
	public void setEXME_ConfigMedico_ID (int EXME_ConfigMedico_ID)
	{
		if (EXME_ConfigMedico_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigMedico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigMedico_ID, Integer.valueOf(EXME_ConfigMedico_ID));
	}

	/** Get Doctor Configuration.
		@return Doctor Configuration	  */
	public int getEXME_ConfigMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Condition Validation.
		@param MedicalCondition Medical Condition Validation	  */
	public void setMedicalCondition (boolean MedicalCondition)
	{
		set_Value (COLUMNNAME_MedicalCondition, Boolean.valueOf(MedicalCondition));
	}

	/** Get Medical Condition Validation.
		@return Medical Condition Validation	  */
	public boolean isMedicalCondition () 
	{
		Object oo = get_Value(COLUMNNAME_MedicalCondition);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** MedicalConditionLevel AD_Reference_ID=1200518 */
	public static final int MEDICALCONDITIONLEVEL_AD_Reference_ID=1200518;
	/** Minor Potential Hazard = 21 */
	public static final String MEDICALCONDITIONLEVEL_MinorPotentialHazard = "21";
	/** Moderate Potential Hazard = 22 */
	public static final String MEDICALCONDITIONLEVEL_ModeratePotentialHazard = "22";
	/** Severe Potential Hazard = 23 */
	public static final String MEDICALCONDITIONLEVEL_SeverePotentialHazard = "23";
	/** Set Level of Severity.
		@param MedicalConditionLevel Level of Severity	  */
	public void setMedicalConditionLevel (String MedicalConditionLevel)
	{

		if (MedicalConditionLevel == null || MedicalConditionLevel.equals("21") || MedicalConditionLevel.equals("22") || MedicalConditionLevel.equals("23")); else throw new IllegalArgumentException ("MedicalConditionLevel Invalid value - " + MedicalConditionLevel + " - Reference_ID=1200518 - 21 - 22 - 23");		set_Value (COLUMNNAME_MedicalConditionLevel, MedicalConditionLevel);
	}

	/** Get Level of Severity.
		@return Level of Severity	  */
	public String getMedicalConditionLevel () 
	{
		return (String)get_Value(COLUMNNAME_MedicalConditionLevel);
	}

	/** Set MultiWindow.
		@param MultiWindow MultiWindow	  */
	public void setMultiWindow (boolean MultiWindow)
	{
		set_Value (COLUMNNAME_MultiWindow, Boolean.valueOf(MultiWindow));
	}

	/** Get MultiWindow.
		@return MultiWindow	  */
	public boolean isMultiWindow () 
	{
		Object oo = get_Value(COLUMNNAME_MultiWindow);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SimpleWindow.
		@param SimpleWindow SimpleWindow	  */
	public void setSimpleWindow (boolean SimpleWindow)
	{
		set_Value (COLUMNNAME_SimpleWindow, Boolean.valueOf(SimpleWindow));
	}

	/** Get SimpleWindow.
		@return SimpleWindow	  */
	public boolean isSimpleWindow () 
	{
		Object oo = get_Value(COLUMNNAME_SimpleWindow);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}