/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Beneficios
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Beneficios 
{

    /** TableName=EXME_Beneficios */
    public static final String Table_Name = "EXME_Beneficios";

    /** AD_Table_ID=1200913 */
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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name EXME_BeneficiosH_ID */
    public static final String COLUMNNAME_EXME_BeneficiosH_ID = "EXME_BeneficiosH_ID";

	/** Set EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID);

	/** Get EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID();

	public I_EXME_BeneficiosH getEXME_BeneficiosH() throws RuntimeException;

    /** Column name EXME_Beneficios_ID */
    public static final String COLUMNNAME_EXME_Beneficios_ID = "EXME_Beneficios_ID";

	/** Set Benefits.
	  * Benefits
	  */
	public void setEXME_Beneficios_ID (int EXME_Beneficios_ID);

	/** Get Benefits.
	  * Benefits
	  */
	public int getEXME_Beneficios_ID();

    /** Column name EXME_CoverageType_ID */
    public static final String COLUMNNAME_EXME_CoverageType_ID = "EXME_CoverageType_ID";

	/** Set Coverage Type	  */
	public void setEXME_CoverageType_ID (int EXME_CoverageType_ID);

	/** Get Coverage Type	  */
	public int getEXME_CoverageType_ID();

	public I_EXME_CoverageType getEXME_CoverageType() throws RuntimeException;

    /** Column name EXME_InsuranceType_ID */
    public static final String COLUMNNAME_EXME_InsuranceType_ID = "EXME_InsuranceType_ID";

	/** Set Insurance Type	  */
	public void setEXME_InsuranceType_ID (int EXME_InsuranceType_ID);

	/** Get Insurance Type	  */
	public int getEXME_InsuranceType_ID();

	public I_EXME_InsuranceType getEXME_InsuranceType() throws RuntimeException;

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

    /** Column name EXME_PACIENTE_S_ID */
    public static final String COLUMNNAME_EXME_PACIENTE_S_ID = "EXME_PACIENTE_S_ID";

	/** Set EXME_PACIENTE_S_ID	  */
	public void setEXME_PACIENTE_S_ID (int EXME_PACIENTE_S_ID);

	/** Get EXME_PACIENTE_S_ID	  */
	public int getEXME_PACIENTE_S_ID();

    /** Column name EXME_PeriodQual_ID */
    public static final String COLUMNNAME_EXME_PeriodQual_ID = "EXME_PeriodQual_ID";

	/** Set Period Qualifier	  */
	public void setEXME_PeriodQual_ID (int EXME_PeriodQual_ID);

	/** Get Period Qualifier	  */
	public int getEXME_PeriodQual_ID();

	public I_EXME_PeriodQual getEXME_PeriodQual() throws RuntimeException;

    /** Column name EXME_QuantityQual_ID */
    public static final String COLUMNNAME_EXME_QuantityQual_ID = "EXME_QuantityQual_ID";

	/** Set Quantity Qualifier	  */
	public void setEXME_QuantityQual_ID (int EXME_QuantityQual_ID);

	/** Get Quantity Qualifier	  */
	public int getEXME_QuantityQual_ID();

	public I_EXME_QuantityQual getEXME_QuantityQual() throws RuntimeException;

    /** Column name EXME_StatusEleg_ID */
    public static final String COLUMNNAME_EXME_StatusEleg_ID = "EXME_StatusEleg_ID";

	/** Set Elegibility Status	  */
	public void setEXME_StatusEleg_ID (int EXME_StatusEleg_ID);

	/** Get Elegibility Status	  */
	public int getEXME_StatusEleg_ID();

	public I_EXME_StatusEleg getEXME_StatusEleg() throws RuntimeException;

    /** Column name EXME_TipoProd_ID */
    public static final String COLUMNNAME_EXME_TipoProd_ID = "EXME_TipoProd_ID";

	/** Set Product Subtype.
	  * Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID);

	/** Get Product Subtype.
	  * Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID();

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException;

    /** Column name IsPlanNetwork */
    public static final String COLUMNNAME_IsPlanNetwork = "IsPlanNetwork";

	/** Set Is Plan Network	  */
	public void setIsPlanNetwork (boolean IsPlanNetwork);

	/** Get Is Plan Network	  */
	public boolean isPlanNetwork();

    /** Column name Percentage */
    public static final String COLUMNNAME_Percentage = "Percentage";

	/** Set Percentage.
	  * Percent of the entire amount
	  */
	public void setPercentage (BigDecimal Percentage);

	/** Get Percentage.
	  * Percent of the entire amount
	  */
	public BigDecimal getPercentage();

    /** Column name PlanCoverage */
    public static final String COLUMNNAME_PlanCoverage = "PlanCoverage";

	/** Set Plan Coverage	  */
	public void setPlanCoverage (String PlanCoverage);

	/** Get Plan Coverage	  */
	public String getPlanCoverage();

    /** Column name Quantity */
    public static final String COLUMNNAME_Quantity = "Quantity";

	/** Set Quantity	  */
	public void setQuantity (int Quantity);

	/** Get Quantity	  */
	public int getQuantity();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();
}
