/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigPre
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigPre 
{

    /** TableName=EXME_ConfigPre */
    public static final String Table_Name = "EXME_ConfigPre";

    /** AD_Table_ID=1000121 */
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

    /** Column name AplicaServSinPrec */
    public static final String COLUMNNAME_AplicaServSinPrec = "AplicaServSinPrec";

	/** Set Apply a clinical study without a price	  */
	public void setAplicaServSinPrec (boolean AplicaServSinPrec);

	/** Get Apply a clinical study without a price	  */
	public boolean isAplicaServSinPrec();

    /** Column name BajarPrecioVta */
    public static final String COLUMNNAME_BajarPrecioVta = "BajarPrecioVta";

	/** Set Lower Sale Price.
	  * Lower Sale Price
	  */
	public void setBajarPrecioVta (boolean BajarPrecioVta);

	/** Get Lower Sale Price.
	  * Lower Sale Price
	  */
	public boolean isBajarPrecioVta();

    /** Column name BusquedaPrecio */
    public static final String COLUMNNAME_BusquedaPrecio = "BusquedaPrecio";

	/** Set Search Price	  */
	public void setBusquedaPrecio (String BusquedaPrecio);

	/** Get Search Price	  */
	public String getBusquedaPrecio();

    /** Column name CalculoAut */
    public static final String COLUMNNAME_CalculoAut = "CalculoAut";

	/** Set Automatic Calculation.
	  * Automatic Calculation
	  */
	public void setCalculoAut (boolean CalculoAut);

	/** Get Automatic Calculation.
	  * Automatic Calculation
	  */
	public boolean isCalculoAut();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public I_C_BankAccount getC_BankAccount() throws RuntimeException;

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

    /** Column name C_BPartner_Sales_ID */
    public static final String COLUMNNAME_C_BPartner_Sales_ID = "C_BPartner_Sales_ID";

	/** Set Partner sales	  */
	public void setC_BPartner_Sales_ID (int C_BPartner_Sales_ID);

	/** Get Partner sales	  */
	public int getC_BPartner_Sales_ID();

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Company Group.
	  * Company Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Company Group.
	  * Company Group
	  */
	public int getC_BP_Group_ID();

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name Coaseguro_ID */
    public static final String COLUMNNAME_Coaseguro_ID = "Coaseguro_ID";

	/** Set Coinsurance.
	  * Product/Service corresponding to the coinsurance
	  */
	public void setCoaseguro_ID (int Coaseguro_ID);

	/** Get Coinsurance.
	  * Product/Service corresponding to the coinsurance
	  */
	public int getCoaseguro_ID();

    /** Column name CoaseguroMed_ID */
    public static final String COLUMNNAME_CoaseguroMed_ID = "CoaseguroMed_ID";

	/** Set Coinsurance Professional	  */
	public void setCoaseguroMed_ID (int CoaseguroMed_ID);

	/** Get Coinsurance Professional	  */
	public int getCoaseguroMed_ID();

    /** Column name Com_SC_C_BP_Group_ID */
    public static final String COLUMNNAME_Com_SC_C_BP_Group_ID = "Com_SC_C_BP_Group_ID";

	/** Set Social Comm. BP Group.
	  * Social Comm. BP Group
	  */
	public void setCom_SC_C_BP_Group_ID (int Com_SC_C_BP_Group_ID);

	/** Get Social Comm. BP Group.
	  * Social Comm. BP Group
	  */
	public int getCom_SC_C_BP_Group_ID();

    /** Column name Cons_Ext_C_BP_Group_ID */
    public static final String COLUMNNAME_Cons_Ext_C_BP_Group_ID = "Cons_Ext_C_BP_Group_ID";

	/** Set Outpatient Clinic BP Group.
	  * Outpatient Clinic BP Group
	  */
	public void setCons_Ext_C_BP_Group_ID (int Cons_Ext_C_BP_Group_ID);

	/** Get Outpatient Clinic BP Group.
	  * Outpatient Clinic BP Group
	  */
	public int getCons_Ext_C_BP_Group_ID();

    /** Column name Convenios_C_BP_Group_ID */
    public static final String COLUMNNAME_Convenios_C_BP_Group_ID = "Convenios_C_BP_Group_ID";

	/** Set Agreements BP Group.
	  * Agreements BP Group
	  */
	public void setConvenios_C_BP_Group_ID (int Convenios_C_BP_Group_ID);

	/** Get Agreements BP Group.
	  * Agreements BP Group
	  */
	public int getConvenios_C_BP_Group_ID();

    /** Column name CoPago_ID */
    public static final String COLUMNNAME_CoPago_ID = "CoPago_ID";

	/** Set Copayment	  */
	public void setCoPago_ID (int CoPago_ID);

	/** Get Copayment	  */
	public int getCoPago_ID();

    /** Column name CreaActividad */
    public static final String COLUMNNAME_CreaActividad = "CreaActividad";

	/** Set Create Activity	  */
	public void setCreaActividad (boolean CreaActividad);

	/** Get Create Activity	  */
	public boolean isCreaActividad();

    /** Column name CreateInventory */
    public static final String COLUMNNAME_CreateInventory = "CreateInventory";

	/** Set Allow negative stock.
	  * Allow negative stock
	  */
	public void setCreateInventory (boolean CreateInventory);

	/** Get Allow negative stock.
	  * Allow negative stock
	  */
	public boolean isCreateInventory();

    /** Column name Deducible_ID */
    public static final String COLUMNNAME_Deducible_ID = "Deducible_ID";

	/** Set Deductible.
	  * Product/Service corresponding to the deductible
	  */
	public void setDeducible_ID (int Deducible_ID);

	/** Get Deductible.
	  * Product/Service corresponding to the deductible
	  */
	public int getDeducible_ID();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_ConfigPre_ID */
    public static final String COLUMNNAME_EXME_ConfigPre_ID = "EXME_ConfigPre_ID";

	/** Set Price Configuration.
	  * Price Configuration
	  */
	public void setEXME_ConfigPre_ID (int EXME_ConfigPre_ID);

	/** Get Price Configuration.
	  * Price Configuration
	  */
	public int getEXME_ConfigPre_ID();

    /** Column name EXME_ModifiersProf_ID */
    public static final String COLUMNNAME_EXME_ModifiersProf_ID = "EXME_ModifiersProf_ID";

	/** Set Professional Modifier	  */
	public void setEXME_ModifiersProf_ID (int EXME_ModifiersProf_ID);

	/** Get Professional Modifier	  */
	public int getEXME_ModifiersProf_ID();

    /** Column name Hospitalizacion_C_BP_Group_ID */
    public static final String COLUMNNAME_Hospitalizacion_C_BP_Group_ID = "Hospitalizacion_C_BP_Group_ID";

	/** Set Inpatient BP Group.
	  * Inpatient BP Group
	  */
	public void setHospitalizacion_C_BP_Group_ID (int Hospitalizacion_C_BP_Group_ID);

	/** Get Inpatient BP Group.
	  * Inpatient BP Group
	  */
	public int getHospitalizacion_C_BP_Group_ID();

    /** Column name IncluirDesctos */
    public static final String COLUMNNAME_IncluirDesctos = "IncluirDesctos";

	/** Set Include Discounts.
	  * Include Discounts
	  */
	public void setIncluirDesctos (boolean IncluirDesctos);

	/** Get Include Discounts.
	  * Include Discounts
	  */
	public boolean isIncluirDesctos();

    /** Column name MaxDiscount */
    public static final String COLUMNNAME_MaxDiscount = "MaxDiscount";

	/** Set Max. discount %.
	  * Maximum discount percentage
	  */
	public void setMaxDiscount (BigDecimal MaxDiscount);

	/** Get Max. discount %.
	  * Maximum discount percentage
	  */
	public BigDecimal getMaxDiscount();

    /** Column name MaxDiscountAmt */
    public static final String COLUMNNAME_MaxDiscountAmt = "MaxDiscountAmt";

	/** Set Max. discount amount.
	  * Maximum discount amount
	  */
	public void setMaxDiscountAmt (BigDecimal MaxDiscountAmt);

	/** Get Max. discount amount.
	  * Maximum discount amount
	  */
	public BigDecimal getMaxDiscountAmt();

    /** Column name MaxLinFactura */
    public static final String COLUMNNAME_MaxLinFactura = "MaxLinFactura";

	/** Set Línes in Invoice.
	  * Maximum lines in invoice
	  */
	public void setMaxLinFactura (int MaxLinFactura);

	/** Get Línes in Invoice.
	  * Maximum lines in invoice
	  */
	public int getMaxLinFactura();

    /** Column name Medicaid_C_BP_Group_ID */
    public static final String COLUMNNAME_Medicaid_C_BP_Group_ID = "Medicaid_C_BP_Group_ID";

	/** Set Medicaid BP Group	  */
	public void setMedicaid_C_BP_Group_ID (int Medicaid_C_BP_Group_ID);

	/** Get Medicaid BP Group	  */
	public int getMedicaid_C_BP_Group_ID();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PriceVariation */
    public static final String COLUMNNAME_PriceVariation = "PriceVariation";

	/** Set Price Variation %	  */
	public void setPriceVariation (BigDecimal PriceVariation);

	/** Get Price Variation %	  */
	public BigDecimal getPriceVariation();

    /** Column name PrintRecibo */
    public static final String COLUMNNAME_PrintRecibo = "PrintRecibo";

	/** Set Print receipt	  */
	public void setPrintRecibo (boolean PrintRecibo);

	/** Get Print receipt	  */
	public boolean isPrintRecibo();

    /** Column name RecalculaPre */
    public static final String COLUMNNAME_RecalculaPre = "RecalculaPre";

	/** Set Recalculate Prices	  */
	public void setRecalculaPre (boolean RecalculaPre);

	/** Get Recalculate Prices	  */
	public boolean isRecalculaPre();

    /** Column name Tab_SC_C_BP_Group_ID */
    public static final String COLUMNNAME_Tab_SC_C_BP_Group_ID = "Tab_SC_C_BP_Group_ID";

	/** Set Social Work BP Group.
	  * Social Work BP Group
	  */
	public void setTab_SC_C_BP_Group_ID (int Tab_SC_C_BP_Group_ID);

	/** Get Social Work BP Group.
	  * Social Work BP Group
	  */
	public int getTab_SC_C_BP_Group_ID();

    /** Column name Urgencias_C_BP_Group_ID */
    public static final String COLUMNNAME_Urgencias_C_BP_Group_ID = "Urgencias_C_BP_Group_ID";

	/** Set Urgencies BP Group.
	  * Urgencies BP Group
	  */
	public void setUrgencias_C_BP_Group_ID (int Urgencias_C_BP_Group_ID);

	/** Get Urgencies BP Group.
	  * Urgencies BP Group
	  */
	public int getUrgencias_C_BP_Group_ID();

    /** Column name UsarFactor */
    public static final String COLUMNNAME_UsarFactor = "UsarFactor";

	/** Set Use Factor.
	  * Use factor
	  */
	public void setUsarFactor (boolean UsarFactor);

	/** Get Use Factor.
	  * Use factor
	  */
	public boolean isUsarFactor();

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
