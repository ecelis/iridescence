/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_CDS_Rules
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CDS_Rules extends PO implements I_EXME_CDS_Rules, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CDS_Rules (Properties ctx, int EXME_CDS_Rules_ID, String trxName)
    {
      super (ctx, EXME_CDS_Rules_ID, trxName);
      /** if (EXME_CDS_Rules_ID == 0)
        {
			setEXME_CDS_ID (0);
			setEXME_CDS_Rules_ID (0);
			setRuleType (null);
			setSeqNo (0);
// 0
        } */
    }

    /** Load Constructor */
    public X_EXME_CDS_Rules (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CDS_Rules[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** CharValue AD_Reference_ID=1000018 */
	public static final int CHARVALUE_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String CHARVALUE_Female = "F";
	/** Male = M */
	public static final String CHARVALUE_Male = "M";
	/** Unassigned = U */
	public static final String CHARVALUE_Unassigned = "U";
	/** Ambiguous = A */
	public static final String CHARVALUE_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String CHARVALUE_NotApplicable = "N";
	/** Other = O */
	public static final String CHARVALUE_Other = "O";
	/** Set List Value.
		@param CharValue List Value	  */
	public void setCharValue (String CharValue)
	{

		if (CharValue == null || CharValue.equals("F") || CharValue.equals("M") || CharValue.equals("U") || CharValue.equals("A") || CharValue.equals("N") || CharValue.equals("O")); else throw new IllegalArgumentException ("CharValue Invalid value - " + CharValue + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_CharValue, CharValue);
	}

	/** Get List Value.
		@return List Value	  */
	public String getCharValue () 
	{
		return (String)get_Value(COLUMNNAME_CharValue);
	}

	/** ComparisonOperator AD_Reference_ID=1200406 */
	public static final int COMPARISONOPERATOR_AD_Reference_ID=1200406;
	/** < = < */
	public static final String COMPARISONOPERATOR_Le = "<";
	/** = = = */
	public static final String COMPARISONOPERATOR_Eq = "=";
	/** > = > */
	public static final String COMPARISONOPERATOR_Gt = ">";
	/** >= = >= */
	public static final String COMPARISONOPERATOR_GtEq = ">=";
	/** <= = <= */
	public static final String COMPARISONOPERATOR_LeEq = "<=";
	/** <> = <> */
	public static final String COMPARISONOPERATOR_ = "<>";
	/** Set Comparison Operator.
		@param ComparisonOperator Comparison Operator	  */
	public void setComparisonOperator (String ComparisonOperator)
	{

		if (ComparisonOperator == null || ComparisonOperator.equals("<") || ComparisonOperator.equals("=") || ComparisonOperator.equals(">") || ComparisonOperator.equals(">=") || ComparisonOperator.equals("<=") || ComparisonOperator.equals("<>")); else throw new IllegalArgumentException ("ComparisonOperator Invalid value - " + ComparisonOperator + " - Reference_ID=1200406 - < - = - > - >= - <= - <>");		set_Value (COLUMNNAME_ComparisonOperator, ComparisonOperator);
	}

	/** Get Comparison Operator.
		@return Comparison Operator	  */
	public String getComparisonOperator () 
	{
		return (String)get_Value(COLUMNNAME_ComparisonOperator);
	}

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DemoField AD_Reference_ID=1200402 */
	public static final int DEMOFIELD_AD_Reference_ID=1200402;
	/** Age = A */
	public static final String DEMOFIELD_Age = "A";
	/** Weight = W */
	public static final String DEMOFIELD_Weight = "W";
	/** Height = H */
	public static final String DEMOFIELD_Height = "H";
	/** Sex = S */
	public static final String DEMOFIELD_Sex = "S";
	/** Race = R */
	public static final String DEMOFIELD_Race = "R";
	/** Nacionality = N */
	public static final String DEMOFIELD_Nacionality = "N";
	/** Ethnic Group = E */
	public static final String DEMOFIELD_EthnicGroup = "E";
	/** Set Demographic Field.
		@param DemoField Demographic Field	  */
	public void setDemoField (String DemoField)
	{

		if (DemoField == null || DemoField.equals("A") || DemoField.equals("W") || DemoField.equals("H") || DemoField.equals("S") || DemoField.equals("R") || DemoField.equals("N") || DemoField.equals("E")); else throw new IllegalArgumentException ("DemoField Invalid value - " + DemoField + " - Reference_ID=1200402 - A - W - H - S - R - N - E");		set_Value (COLUMNNAME_DemoField, DemoField);
	}

	/** Get Demographic Field.
		@return Demographic Field	  */
	public String getDemoField () 
	{
		return (String)get_Value(COLUMNNAME_DemoField);
	}

	/** Set Floating-Point Number.
		@param DoubleValue Floating-Point Number	  */
	public void setDoubleValue (BigDecimal DoubleValue)
	{
		set_Value (COLUMNNAME_DoubleValue, DoubleValue);
	}

	/** Get Floating-Point Number.
		@return Floating-Point Number	  */
	public BigDecimal getDoubleValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DoubleValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_CDS getEXME_CDS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CDS.Table_Name);
        I_EXME_CDS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CDS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CDS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Clinical Decision Support.
		@param EXME_CDS_ID 
		Clinical Decision Support
	  */
	public void setEXME_CDS_ID (int EXME_CDS_ID)
	{
		if (EXME_CDS_ID < 1)
			 throw new IllegalArgumentException ("EXME_CDS_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CDS_ID, Integer.valueOf(EXME_CDS_ID));
	}

	/** Get Clinical Decision Support.
		@return Clinical Decision Support
	  */
	public int getEXME_CDS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CDS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Clinical Decision Support Rules.
		@param EXME_CDS_Rules_ID 
		Clinical Decision Support Rules
	  */
	public void setEXME_CDS_Rules_ID (int EXME_CDS_Rules_ID)
	{
		if (EXME_CDS_Rules_ID < 1)
			 throw new IllegalArgumentException ("EXME_CDS_Rules_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CDS_Rules_ID, Integer.valueOf(EXME_CDS_Rules_ID));
	}

	/** Get Clinical Decision Support Rules.
		@return Clinical Decision Support Rules
	  */
	public int getEXME_CDS_Rules_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CDS_Rules_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Condition.
		@param EXME_DiagnosticoConMed_ID Medical Condition	  */
	public void setEXME_DiagnosticoConMed_ID (int EXME_DiagnosticoConMed_ID)
	{
		if (EXME_DiagnosticoConMed_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiagnosticoConMed_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiagnosticoConMed_ID, Integer.valueOf(EXME_DiagnosticoConMed_ID));
	}

	/** Get Medical Condition.
		@return Medical Condition	  */
	public int getEXME_DiagnosticoConMed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoConMed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ethnicity.
		@param EXME_GpoEtnico_ID 
		Ethnicity
	  */
	public void setEXME_GpoEtnico_ID (int EXME_GpoEtnico_ID)
	{
		if (EXME_GpoEtnico_ID < 1) 
			set_Value (COLUMNNAME_EXME_GpoEtnico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GpoEtnico_ID, Integer.valueOf(EXME_GpoEtnico_ID));
	}

	/** Get Ethnicity.
		@return Ethnicity
	  */
	public int getEXME_GpoEtnico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GpoEtnico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LOINC Code.
		@param EXME_Loinc_ID LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID)
	{
		if (EXME_Loinc_ID < 1) 
			set_Value (COLUMNNAME_EXME_Loinc_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Loinc_ID, Integer.valueOf(EXME_Loinc_ID));
	}

	/** Get LOINC Code.
		@return LOINC Code	  */
	public int getEXME_Loinc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Loinc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nationality.
		@param EXME_Nacionalidad_ID 
		Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID)
	{
		if (EXME_Nacionalidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Nacionalidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Nacionalidad_ID, Integer.valueOf(EXME_Nacionalidad_ID));
	}

	/** Get Nationality.
		@return Nationality
	  */
	public int getEXME_Nacionalidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Nacionalidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Race.
		@param EXME_Razas_ID 
		Races
	  */
	public void setEXME_Razas_ID (int EXME_Razas_ID)
	{
		if (EXME_Razas_ID < 1) 
			set_Value (COLUMNNAME_EXME_Razas_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Razas_ID, Integer.valueOf(EXME_Razas_ID));
	}

	/** Get Race.
		@return Races
	  */
	public int getEXME_Razas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Razas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SActiva.Table_Name);
        I_EXME_SActiva result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SActiva)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SActiva_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Active Substance.
		@param EXME_SActiva_ID 
		Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID)
	{
		if (EXME_SActiva_ID < 1) 
			set_Value (COLUMNNAME_EXME_SActiva_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
	}

	/** Get Active Substance.
		@return Active Substance
	  */
	public int getEXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Integer Value.
		@param IntValue Integer Value	  */
	public void setIntValue (int IntValue)
	{
		set_Value (COLUMNNAME_IntValue, Integer.valueOf(IntValue));
	}

	/** Get Integer Value.
		@return Integer Value	  */
	public int getIntValue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntValue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** RuleType AD_Reference_ID=1200403 */
	public static final int RULETYPE_AD_Reference_ID=1200403;
	/** Patient Demographics = PD */
	public static final String RULETYPE_PatientDemographics = "PD";
	/** Specific Patient Diagnostic = SD */
	public static final String RULETYPE_SpecificPatientDiagnostic = "SD";
	/** Medical Condition = MC */
	public static final String RULETYPE_MedicalCondition = "MC";
	/** Laboratory Test Results = DT */
	public static final String RULETYPE_LaboratoryTestResults = "DT";
	/** Medication List = ML */
	public static final String RULETYPE_MedicationList = "ML";
	/** Patient Allergies = PA */
	public static final String RULETYPE_PatientAllergies = "PA";
	/** Communication Preferences = PCP */
	public static final String RULETYPE_CommunicationPreferences = "PCP";
	/** Set Rule Type.
		@param RuleType Rule Type	  */
	public void setRuleType (String RuleType)
	{
		if (RuleType == null) throw new IllegalArgumentException ("RuleType is mandatory");
		if (RuleType.equals("PD") || RuleType.equals("SD") || RuleType.equals("MC") || RuleType.equals("DT") || RuleType.equals("ML") || RuleType.equals("PA") || RuleType.equals("PCP")); else throw new IllegalArgumentException ("RuleType Invalid value - " + RuleType + " - Reference_ID=1200403 - PD - SD - MC - DT - ML - PA - PCP");		set_Value (COLUMNNAME_RuleType, RuleType);
	}

	/** Get Rule Type.
		@return Rule Type	  */
	public String getRuleType () 
	{
		return (String)get_Value(COLUMNNAME_RuleType);
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** StringValue AD_Reference_ID=1200407 */
	public static final int STRINGVALUE_AD_Reference_ID=1200407;
	/** LOINC = LN */
	public static final String STRINGVALUE_LOINC = "LN";
	/** Set System Code.
		@param StringValue System Code	  */
	public void setStringValue (String StringValue)
	{

		if (StringValue == null || StringValue.equals("LN")); else throw new IllegalArgumentException ("StringValue Invalid value - " + StringValue + " - Reference_ID=1200407 - LN");		set_Value (COLUMNNAME_StringValue, StringValue);
	}

	/** Get System Code.
		@return System Code	  */
	public String getStringValue () 
	{
		return (String)get_Value(COLUMNNAME_StringValue);
	}
}