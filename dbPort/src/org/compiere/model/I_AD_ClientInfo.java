/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_ClientInfo
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_AD_ClientInfo 
{

    /** TableName=AD_ClientInfo */
    public static final String Table_Name = "AD_ClientInfo";

    /** AD_Table_ID=227 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name AD_Tree_Activity_ID */
    public static final String COLUMNNAME_AD_Tree_Activity_ID = "AD_Tree_Activity_ID";

	/** Set Activity Tree.
	  * Tree to determine activity hierarchy
	  */
	public void setAD_Tree_Activity_ID (int AD_Tree_Activity_ID);

	/** Get Activity Tree.
	  * Tree to determine activity hierarchy
	  */
	public int getAD_Tree_Activity_ID();

    /** Column name AD_Tree_BPartner_ID */
    public static final String COLUMNNAME_AD_Tree_BPartner_ID = "AD_Tree_BPartner_ID";

	/** Set BPartner Tree.
	  * Tree to determine business partner hierarchy
	  */
	public void setAD_Tree_BPartner_ID (int AD_Tree_BPartner_ID);

	/** Get BPartner Tree.
	  * Tree to determine business partner hierarchy
	  */
	public int getAD_Tree_BPartner_ID();

    /** Column name AD_Tree_Campaign_ID */
    public static final String COLUMNNAME_AD_Tree_Campaign_ID = "AD_Tree_Campaign_ID";

	/** Set Campaign Tree.
	  * Tree to determine marketing campaign hierarchy
	  */
	public void setAD_Tree_Campaign_ID (int AD_Tree_Campaign_ID);

	/** Get Campaign Tree.
	  * Tree to determine marketing campaign hierarchy
	  */
	public int getAD_Tree_Campaign_ID();

    /** Column name AD_Tree_Menu_ID */
    public static final String COLUMNNAME_AD_Tree_Menu_ID = "AD_Tree_Menu_ID";

	/** Set Menu Tree.
	  * Tree of the menu
	  */
	public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID);

	/** Get Menu Tree.
	  * Tree of the menu
	  */
	public int getAD_Tree_Menu_ID();

    /** Column name AD_Tree_Org_ID */
    public static final String COLUMNNAME_AD_Tree_Org_ID = "AD_Tree_Org_ID";

	/** Set Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public void setAD_Tree_Org_ID (int AD_Tree_Org_ID);

	/** Get Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public int getAD_Tree_Org_ID();

    /** Column name AD_Tree_Product_ID */
    public static final String COLUMNNAME_AD_Tree_Product_ID = "AD_Tree_Product_ID";

	/** Set Product Tree.
	  * Tree to determine product hierarchy
	  */
	public void setAD_Tree_Product_ID (int AD_Tree_Product_ID);

	/** Get Product Tree.
	  * Tree to determine product hierarchy
	  */
	public int getAD_Tree_Product_ID();

    /** Column name AD_Tree_Project_ID */
    public static final String COLUMNNAME_AD_Tree_Project_ID = "AD_Tree_Project_ID";

	/** Set Project Tree.
	  * Tree to determine project hierarchy
	  */
	public void setAD_Tree_Project_ID (int AD_Tree_Project_ID);

	/** Get Project Tree.
	  * Tree to determine project hierarchy
	  */
	public int getAD_Tree_Project_ID();

    /** Column name AD_Tree_SalesRegion_ID */
    public static final String COLUMNNAME_AD_Tree_SalesRegion_ID = "AD_Tree_SalesRegion_ID";

	/** Set Sales Region Tree.
	  * Tree to determine sales regional hierarchy
	  */
	public void setAD_Tree_SalesRegion_ID (int AD_Tree_SalesRegion_ID);

	/** Get Sales Region Tree.
	  * Tree to determine sales regional hierarchy
	  */
	public int getAD_Tree_SalesRegion_ID();

    /** Column name BI_Server_URL */
    public static final String COLUMNNAME_BI_Server_URL = "BI_Server_URL";

	/** Set BI Server URL.
	  * BI Server URL
	  */
	public void setBI_Server_URL (String BI_Server_URL);

	/** Get BI Server URL.
	  * BI Server URL
	  */
	public String getBI_Server_URL();

    /** Column name C_AcctSchema1_ID */
    public static final String COLUMNNAME_C_AcctSchema1_ID = "C_AcctSchema1_ID";

	/** Set Primary Accounting Schema.
	  * Primary rules for accounting
	  */
	public void setC_AcctSchema1_ID (int C_AcctSchema1_ID);

	/** Get Primary Accounting Schema.
	  * Primary rules for accounting
	  */
	public int getC_AcctSchema1_ID();

    /** Column name C_BPartnerCashTrx_ID */
    public static final String COLUMNNAME_C_BPartnerCashTrx_ID = "C_BPartnerCashTrx_ID";

	/** Set Template B.Partner.
	  * Business Partner used for creating new Business Partners on the fly
	  */
	public void setC_BPartnerCashTrx_ID (int C_BPartnerCashTrx_ID);

	/** Get Template B.Partner.
	  * Business Partner used for creating new Business Partners on the fly
	  */
	public int getC_BPartnerCashTrx_ID();

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

    /** Column name CONTACTUSER */
    public static final String COLUMNNAME_CONTACTUSER = "CONTACTUSER";

	/** Set ContactUser.
	  * Usuario de Contacto Administrativo
	  */
	public void setCONTACTUSER (int CONTACTUSER);

	/** Get ContactUser.
	  * Usuario de Contacto Administrativo
	  */
	public int getCONTACTUSER();

    /** Column name C_UOM_Length_ID */
    public static final String COLUMNNAME_C_UOM_Length_ID = "C_UOM_Length_ID";

	/** Set UOM for Length.
	  * Standard Unit of Measure for Length
	  */
	public void setC_UOM_Length_ID (int C_UOM_Length_ID);

	/** Get UOM for Length.
	  * Standard Unit of Measure for Length
	  */
	public int getC_UOM_Length_ID();

    /** Column name C_UOM_Time_ID */
    public static final String COLUMNNAME_C_UOM_Time_ID = "C_UOM_Time_ID";

	/** Set UOM for Time.
	  * Standard Unit of Measure for Time
	  */
	public void setC_UOM_Time_ID (int C_UOM_Time_ID);

	/** Get UOM for Time.
	  * Standard Unit of Measure for Time
	  */
	public int getC_UOM_Time_ID();

    /** Column name C_UOM_Volume_ID */
    public static final String COLUMNNAME_C_UOM_Volume_ID = "C_UOM_Volume_ID";

	/** Set UOM for Volume.
	  * Standard Unit of Measure for Volume
	  */
	public void setC_UOM_Volume_ID (int C_UOM_Volume_ID);

	/** Get UOM for Volume.
	  * Standard Unit of Measure for Volume
	  */
	public int getC_UOM_Volume_ID();

    /** Column name C_UOM_Weight_ID */
    public static final String COLUMNNAME_C_UOM_Weight_ID = "C_UOM_Weight_ID";

	/** Set UOM for Weight.
	  * Standard Unit of Measure for Weight
	  */
	public void setC_UOM_Weight_ID (int C_UOM_Weight_ID);

	/** Get UOM for Weight.
	  * Standard Unit of Measure for Weight
	  */
	public int getC_UOM_Weight_ID();

    /** Column name Derechohabiente */
    public static final String COLUMNNAME_Derechohabiente = "Derechohabiente";

	/** Set Right Holder.
	  * Right Holder
	  */
	public void setDerechohabiente (boolean Derechohabiente);

	/** Get Right Holder.
	  * Right Holder
	  */
	public boolean isDerechohabiente();

    /** Column name IsDiscountLineAmt */
    public static final String COLUMNNAME_IsDiscountLineAmt = "IsDiscountLineAmt";

	/** Set Discount calculated from Line Amounts.
	  * Payment Discount calculation does not include Taxes and Charges
	  */
	public void setIsDiscountLineAmt (boolean IsDiscountLineAmt);

	/** Get Discount calculated from Line Amounts.
	  * Payment Discount calculation does not include Taxes and Charges
	  */
	public boolean isDiscountLineAmt();

    /** Column name KeepLogDays */
    public static final String COLUMNNAME_KeepLogDays = "KeepLogDays";

	/** Set Days to keep Log.
	  * Number of days to keep the log entries
	  */
	public void setKeepLogDays (int KeepLogDays);

	/** Get Days to keep Log.
	  * Number of days to keep the log entries
	  */
	public int getKeepLogDays();

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name LogoReport_ID */
    public static final String COLUMNNAME_LogoReport_ID = "LogoReport_ID";

	/** Set Logo Report	  */
	public void setLogoReport_ID (int LogoReport_ID);

	/** Get Logo Report	  */
	public int getLogoReport_ID();

    /** Column name LogoWeb_ID */
    public static final String COLUMNNAME_LogoWeb_ID = "LogoWeb_ID";

	/** Set Logo Web	  */
	public void setLogoWeb_ID (int LogoWeb_ID);

	/** Get Logo Web	  */
	public int getLogoWeb_ID();

    /** Column name MatchRequirementI */
    public static final String COLUMNNAME_MatchRequirementI = "MatchRequirementI";

	/** Set Invoice Match Requirement.
	  * Matching Requirement for Invoice
	  */
	public void setMatchRequirementI (String MatchRequirementI);

	/** Get Invoice Match Requirement.
	  * Matching Requirement for Invoice
	  */
	public String getMatchRequirementI();

    /** Column name MatchRequirementR */
    public static final String COLUMNNAME_MatchRequirementR = "MatchRequirementR";

	/** Set Receipt Match Requirement.
	  * Matching Requirement for Receipts
	  */
	public void setMatchRequirementR (String MatchRequirementR);

	/** Get Receipt Match Requirement.
	  * Matching Requirement for Receipts
	  */
	public String getMatchRequirementR();

    /** Column name M_ProductFreight_ID */
    public static final String COLUMNNAME_M_ProductFreight_ID = "M_ProductFreight_ID";

	/** Set Product for Freight	  */
	public void setM_ProductFreight_ID (int M_ProductFreight_ID);

	/** Get Product for Freight	  */
	public int getM_ProductFreight_ID();

    /** Column name Ramo */
    public static final String COLUMNNAME_Ramo = "Ramo";

	/** Set Branch.
	  * Branch
	  */
	public void setRamo (String Ramo);

	/** Get Branch.
	  * Branch
	  */
	public String getRamo();

    /** Column name UnidadResponsable */
    public static final String COLUMNNAME_UnidadResponsable = "UnidadResponsable";

	/** Set Unit responsible	  */
	public void setUnidadResponsable (String UnidadResponsable);

	/** Get Unit responsible	  */
	public String getUnidadResponsable();
}
