/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CDS_Rules
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CDS_Rules 
{

    /** TableName=EXME_CDS_Rules */
    public static final String Table_Name = "EXME_CDS_Rules";

    /** AD_Table_ID=1200885 */
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

    /** Column name CharValue */
    public static final String COLUMNNAME_CharValue = "CharValue";

	/** Set List Value	  */
	public void setCharValue (String CharValue);

	/** Get List Value	  */
	public String getCharValue();

    /** Column name ComparisonOperator */
    public static final String COLUMNNAME_ComparisonOperator = "ComparisonOperator";

	/** Set Comparison Operator	  */
	public void setComparisonOperator (String ComparisonOperator);

	/** Get Comparison Operator	  */
	public String getComparisonOperator();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name DemoField */
    public static final String COLUMNNAME_DemoField = "DemoField";

	/** Set Demographic Field	  */
	public void setDemoField (String DemoField);

	/** Get Demographic Field	  */
	public String getDemoField();

    /** Column name DoubleValue */
    public static final String COLUMNNAME_DoubleValue = "DoubleValue";

	/** Set Floating-Point Number	  */
	public void setDoubleValue (BigDecimal DoubleValue);

	/** Get Floating-Point Number	  */
	public BigDecimal getDoubleValue();

    /** Column name EXME_CDS_ID */
    public static final String COLUMNNAME_EXME_CDS_ID = "EXME_CDS_ID";

	/** Set Clinical Decision Support.
	  * Clinical Decision Support
	  */
	public void setEXME_CDS_ID (int EXME_CDS_ID);

	/** Get Clinical Decision Support.
	  * Clinical Decision Support
	  */
	public int getEXME_CDS_ID();

	public I_EXME_CDS getEXME_CDS() throws RuntimeException;

    /** Column name EXME_CDS_Rules_ID */
    public static final String COLUMNNAME_EXME_CDS_Rules_ID = "EXME_CDS_Rules_ID";

	/** Set Clinical Decision Support Rules.
	  * Clinical Decision Support Rules
	  */
	public void setEXME_CDS_Rules_ID (int EXME_CDS_Rules_ID);

	/** Get Clinical Decision Support Rules.
	  * Clinical Decision Support Rules
	  */
	public int getEXME_CDS_Rules_ID();

    /** Column name EXME_DiagnosticoConMed_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoConMed_ID = "EXME_DiagnosticoConMed_ID";

	/** Set Medical Condition	  */
	public void setEXME_DiagnosticoConMed_ID (int EXME_DiagnosticoConMed_ID);

	/** Get Medical Condition	  */
	public int getEXME_DiagnosticoConMed_ID();

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

    /** Column name EXME_GpoEtnico_ID */
    public static final String COLUMNNAME_EXME_GpoEtnico_ID = "EXME_GpoEtnico_ID";

	/** Set Ethnicity.
	  * Ethnicity
	  */
	public void setEXME_GpoEtnico_ID (int EXME_GpoEtnico_ID);

	/** Get Ethnicity.
	  * Ethnicity
	  */
	public int getEXME_GpoEtnico_ID();

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

    /** Column name EXME_Nacionalidad_ID */
    public static final String COLUMNNAME_EXME_Nacionalidad_ID = "EXME_Nacionalidad_ID";

	/** Set Nationality.
	  * Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID);

	/** Get Nationality.
	  * Nationality
	  */
	public int getEXME_Nacionalidad_ID();

    /** Column name EXME_Razas_ID */
    public static final String COLUMNNAME_EXME_Razas_ID = "EXME_Razas_ID";

	/** Set Race.
	  * Races
	  */
	public void setEXME_Razas_ID (int EXME_Razas_ID);

	/** Get Race.
	  * Races
	  */
	public int getEXME_Razas_ID();

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

    /** Column name IntValue */
    public static final String COLUMNNAME_IntValue = "IntValue";

	/** Set Integer Value	  */
	public void setIntValue (int IntValue);

	/** Get Integer Value	  */
	public int getIntValue();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

    /** Column name RuleType */
    public static final String COLUMNNAME_RuleType = "RuleType";

	/** Set Rule Type	  */
	public void setRuleType (String RuleType);

	/** Get Rule Type	  */
	public String getRuleType();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name StringValue */
    public static final String COLUMNNAME_StringValue = "StringValue";

	/** Set System Code	  */
	public void setStringValue (String StringValue);

	/** Get System Code	  */
	public String getStringValue();
}
