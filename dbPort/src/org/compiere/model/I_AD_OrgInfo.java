/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_OrgInfo
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_AD_OrgInfo 
{

    /** TableName=AD_OrgInfo */
    public static final String Table_Name = "AD_OrgInfo";

    /** AD_Table_ID=228 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_OrgType_ID */
    public static final String COLUMNNAME_AD_OrgType_ID = "AD_OrgType_ID";

	/** Set Organization Type.
	  * Organization Type allows you to categorize your organizations
	  */
	public void setAD_OrgType_ID (int AD_OrgType_ID);

	/** Get Organization Type.
	  * Organization Type allows you to categorize your organizations
	  */
	public int getAD_OrgType_ID();

	public I_AD_OrgType getAD_OrgType() throws RuntimeException;

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

    /** Column name AD_PrimaryOrg_ID */
    public static final String COLUMNNAME_AD_PrimaryOrg_ID = "AD_PrimaryOrg_ID";

	/** Set Primary Org	  */
	public void setAD_PrimaryOrg_ID (int AD_PrimaryOrg_ID);

	/** Get Primary Org	  */
	public int getAD_PrimaryOrg_ID();

    /** Column name C_Calendar_ID */
    public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

	/** Set Calendar.
	  * Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID);

	/** Get Calendar.
	  * Accounting Calendar Name
	  */
	public int getC_Calendar_ID();

	public I_C_Calendar getC_Calendar() throws RuntimeException;

    /** Column name C_LocationPhys_ID */
    public static final String COLUMNNAME_C_LocationPhys_ID = "C_LocationPhys_ID";

	/** Set Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID);

	/** Get Physical Address	  */
	public int getC_LocationPhys_ID();

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name ClaimType */
    public static final String COLUMNNAME_ClaimType = "ClaimType";

	/** Set Claim Type	  */
	public void setClaimType (boolean ClaimType);

	/** Get Claim Type	  */
	public boolean isClaimType();

    /** Column name DUNS */
    public static final String COLUMNNAME_DUNS = "DUNS";

	/** Set D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public void setDUNS (String DUNS);

	/** Get D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public String getDUNS();

    /** Column name DropShip_Warehouse_ID */
    public static final String COLUMNNAME_DropShip_Warehouse_ID = "DropShip_Warehouse_ID";

	/** Set Drop Ship Warehouse.
	  * The (logical) warehouse to use for recording drop ship receipts and shipments.
	  */
	public void setDropShip_Warehouse_ID (int DropShip_Warehouse_ID);

	/** Get Drop Ship Warehouse.
	  * The (logical) warehouse to use for recording drop ship receipts and shipments.
	  */
	public int getDropShip_Warehouse_ID();

    /** Column name EXME_EULA_ID */
    public static final String COLUMNNAME_EXME_EULA_ID = "EXME_EULA_ID";

	/** Set End User Agreement License.
	  * The End User Agreement License
	  */
	public void setEXME_EULA_ID (int EXME_EULA_ID);

	/** Get End User Agreement License.
	  * The End User Agreement License
	  */
	public int getEXME_EULA_ID();

	public I_EXME_EULA getEXME_EULA() throws RuntimeException;

    /** Column name EXME_InstitucionExt_ID */
    public static final String COLUMNNAME_EXME_InstitucionExt_ID = "EXME_InstitucionExt_ID";

	/** Set External Service Facility	  */
	public void setEXME_InstitucionExt_ID (int EXME_InstitucionExt_ID);

	/** Get External Service Facility	  */
	public int getEXME_InstitucionExt_ID();

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Service Facility.
	  * Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Service Facility.
	  * Service Facility
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

    /** Column name EXME_Tipologia_ID */
    public static final String COLUMNNAME_EXME_Tipologia_ID = "EXME_Tipologia_ID";

	/** Set Typology	  */
	public void setEXME_Tipologia_ID (int EXME_Tipologia_ID);

	/** Get Typology	  */
	public int getEXME_Tipologia_ID();

	public I_EXME_Tipologia getEXME_Tipologia() throws RuntimeException;

    /** Column name EXME_TypeOfBill_ID */
    public static final String COLUMNNAME_EXME_TypeOfBill_ID = "EXME_TypeOfBill_ID";

	/** Set Type Of Bill.
	  * Type Of Bill
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID);

	/** Get Type Of Bill.
	  * Type Of Bill
	  */
	public int getEXME_TypeOfBill_ID();

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name NPI */
    public static final String COLUMNNAME_NPI = "NPI";

	/** Set NPI	  */
	public void setNPI (String NPI);

	/** Get NPI	  */
	public String getNPI();

    /** Column name PA_Goal_ID */
    public static final String COLUMNNAME_PA_Goal_ID = "PA_Goal_ID";

	/** Set Goal.
	  * Performance Goal
	  */
	public void setPA_Goal_ID (int PA_Goal_ID);

	/** Get Goal.
	  * Performance Goal
	  */
	public int getPA_Goal_ID();

	public I_PA_Goal getPA_Goal() throws RuntimeException;

    /** Column name Parent_Org_ID */
    public static final String COLUMNNAME_Parent_Org_ID = "Parent_Org_ID";

	/** Set Parent Organization.
	  * Parent (superior) Organization 
	  */
	public void setParent_Org_ID (int Parent_Org_ID);

	/** Get Parent Organization.
	  * Parent (superior) Organization 
	  */
	public int getParent_Org_ID();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Main Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Main Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name ReceiptFooterMsg */
    public static final String COLUMNNAME_ReceiptFooterMsg = "ReceiptFooterMsg";

	/** Set Receipt Footer Msg.
	  * This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public void setReceiptFooterMsg (String ReceiptFooterMsg);

	/** Get Receipt Footer Msg.
	  * This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public String getReceiptFooterMsg();

    /** Column name SSN */
    public static final String COLUMNNAME_SSN = "SSN";

	/** Set SSN	  */
	public void setSSN (BigDecimal SSN);

	/** Get SSN	  */
	public BigDecimal getSSN();

    /** Column name Supervisor_ID */
    public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

	/** Set Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID);

	/** Get Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID();

    /** Column name SupportBilling */
    public static final String COLUMNNAME_SupportBilling = "SupportBilling";

	/** Set Support Billing	  */
	public void setSupportBilling (String SupportBilling);

	/** Get Support Billing	  */
	public String getSupportBilling();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

    /** Column name TaxRegime */
    public static final String COLUMNNAME_TaxRegime = "TaxRegime";

	/** Set Tax Regime.
	  * Tax Regime
	  */
	public void setTaxRegime (String TaxRegime);

	/** Get Tax Regime.
	  * Tax Regime
	  */
	public String getTaxRegime();

    /** Column name TimeZone */
    public static final String COLUMNNAME_TimeZone = "TimeZone";

	/** Set Time Zone	  */
	public void setTimeZone (String TimeZone);

	/** Get Time Zone	  */
	public String getTimeZone();

    /** Column name TipoCliente */
    public static final String COLUMNNAME_TipoCliente = "TipoCliente";

	/** Set Client Type	  */
	public void setTipoCliente (String TipoCliente);

	/** Get Client Type	  */
	public String getTipoCliente();

    /** Column name Tipo_Institucion */
    public static final String COLUMNNAME_Tipo_Institucion = "Tipo_Institucion";

	/** Set Institution Type.
	  * Institution Type
	  */
	public void setTipo_Institucion (String Tipo_Institucion);

	/** Get Institution Type.
	  * Institution Type
	  */
	public String getTipo_Institucion();

    /** Column name Tipo_Unidad */
    public static final String COLUMNNAME_Tipo_Unidad = "Tipo_Unidad";

	/** Set Unity Type.
	  * Unity Type
	  */
	public void setTipo_Unidad (String Tipo_Unidad);

	/** Get Unity Type.
	  * Unity Type
	  */
	public String getTipo_Unidad();

    /** Column name Tipologia */
    public static final String COLUMNNAME_Tipologia = "Tipologia";

	/** Set Tipology.
	  * Tipology
	  */
	public void setTipologia (String Tipologia);

	/** Get Tipology.
	  * Tipology
	  */
	public String getTipologia();

    /** Column name TransferBank_ID */
    public static final String COLUMNNAME_TransferBank_ID = "TransferBank_ID";

	/** Set Bank for transfers.
	  * Bank account depending on currency will be used from this bank for doing transfers
	  */
	public void setTransferBank_ID (int TransferBank_ID);

	/** Get Bank for transfers.
	  * Bank account depending on currency will be used from this bank for doing transfers
	  */
	public int getTransferBank_ID();

    /** Column name TransferCashBook_ID */
    public static final String COLUMNNAME_TransferCashBook_ID = "TransferCashBook_ID";

	/** Set CashBook for transfers	  */
	public void setTransferCashBook_ID (int TransferCashBook_ID);

	/** Get CashBook for transfers	  */
	public int getTransferCashBook_ID();
}
