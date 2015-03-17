/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GenProduct_Lexi
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_GenProduct_Lexi 
{

    /** TableName=EXME_GenProduct_Lexi */
    public static final String Table_Name = "EXME_GenProduct_Lexi";

    /** AD_Table_ID=1201377 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name CSA_Code */
    public static final String COLUMNNAME_CSA_Code = "CSA_Code";

	/** Set CSA Code	  */
	public void setCSA_Code (String CSA_Code);

	/** Get CSA Code	  */
	public String getCSA_Code();

    /** Column name Drug_ID */
    public static final String COLUMNNAME_Drug_ID = "Drug_ID";

	/** Set Drug	  */
	public void setDrug_ID (String Drug_ID);

	/** Get Drug	  */
	public String getDrug_ID();

    /** Column name EXME_DoseForm_ID */
    public static final String COLUMNNAME_EXME_DoseForm_ID = "EXME_DoseForm_ID";

	/** Set DoseForm.
	  * DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID);

	/** Get DoseForm.
	  * DoseForm
	  */
	public int getEXME_DoseForm_ID();

    /** Column name EXME_GenDrug_ID */
    public static final String COLUMNNAME_EXME_GenDrug_ID = "EXME_GenDrug_ID";

	/** Set Generic Drug	  */
	public void setEXME_GenDrug_ID (int EXME_GenDrug_ID);

	/** Get Generic Drug	  */
	public int getEXME_GenDrug_ID();

    /** Column name exme_genproduct_lexi_id */
    public static final String COLUMNNAME_exme_genproduct_lexi_id = "exme_genproduct_lexi_id";

	/** Set exme_genproduct_lexi_id	  */
	public void setexme_genproduct_lexi_id (int exme_genproduct_lexi_id);

	/** Get exme_genproduct_lexi_id	  */
	public int getexme_genproduct_lexi_id();

    /** Column name EXME_ProductStrength_ID */
    public static final String COLUMNNAME_EXME_ProductStrength_ID = "EXME_ProductStrength_ID";

	/** Set Product Strength	  */
	public void setEXME_ProductStrength_ID (int EXME_ProductStrength_ID);

	/** Get Product Strength	  */
	public int getEXME_ProductStrength_ID();

    /** Column name EXME_Route_ID */
    public static final String COLUMNNAME_EXME_Route_ID = "EXME_Route_ID";

	/** Set Route.
	  * Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID);

	/** Get Route.
	  * Route
	  */
	public int getEXME_Route_ID();

    /** Column name Generic_Product_Name */
    public static final String COLUMNNAME_Generic_Product_Name = "Generic_Product_Name";

	/** Set Generic Product Name	  */
	public void setGeneric_Product_Name (String Generic_Product_Name);

	/** Get Generic Product Name	  */
	public String getGeneric_Product_Name();

    /** Column name GenProduct_ID */
    public static final String COLUMNNAME_GenProduct_ID = "GenProduct_ID";

	/** Set Generic Product	  */
	public void setGenProduct_ID (int GenProduct_ID);

	/** Get Generic Product	  */
	public int getGenProduct_ID();

    /** Column name GLOBAL_ID */
    public static final String COLUMNNAME_GLOBAL_ID = "GLOBAL_ID";

	/** Set GLOBAL_ID	  */
	public void setGLOBAL_ID (int GLOBAL_ID);

	/** Get GLOBAL_ID	  */
	public int getGLOBAL_ID();

    /** Column name JCode */
    public static final String COLUMNNAME_JCode = "JCode";

	/** Set JCode	  */
	public void setJCode (String JCode);

	/** Get JCode	  */
	public String getJCode();

    /** Column name JCode_Description */
    public static final String COLUMNNAME_JCode_Description = "JCode_Description";

	/** Set JCode Description	  */
	public void setJCode_Description (String JCode_Description);

	/** Get JCode Description	  */
	public String getJCode_Description();

    /** Column name LEXI_ONLINE_DOC_ID */
    public static final String COLUMNNAME_LEXI_ONLINE_DOC_ID = "LEXI_ONLINE_DOC_ID";

	/** Set LEXI_ONLINE_DOC_ID	  */
	public void setLEXI_ONLINE_DOC_ID (int LEXI_ONLINE_DOC_ID);

	/** Get LEXI_ONLINE_DOC_ID	  */
	public int getLEXI_ONLINE_DOC_ID();

    /** Column name Manufact_Generic_Product_Name */
    public static final String COLUMNNAME_Manufact_Generic_Product_Name = "Manufact_Generic_Product_Name";

	/** Set Manufact Generic Product Name	  */
	public void setManufact_Generic_Product_Name (String Manufact_Generic_Product_Name);

	/** Get Manufact Generic Product Name	  */
	public String getManufact_Generic_Product_Name();

    /** Column name Obsolete_Date */
    public static final String COLUMNNAME_Obsolete_Date = "Obsolete_Date";

	/** Set Obsolete Date	  */
	public void setObsolete_Date (Timestamp Obsolete_Date);

	/** Get Obsolete Date	  */
	public Timestamp getObsolete_Date();

    /** Column name RXCUI */
    public static final String COLUMNNAME_RXCUI = "RXCUI";

	/** Set RXCUI	  */
	public void setRXCUI (String RXCUI);

	/** Get RXCUI	  */
	public String getRXCUI();

    /** Column name RX_OTC_Status */
    public static final String COLUMNNAME_RX_OTC_Status = "RX_OTC_Status";

	/** Set RX OTC Status	  */
	public void setRX_OTC_Status (String RX_OTC_Status);

	/** Get RX OTC Status	  */
	public String getRX_OTC_Status();

    /** Column name Trade_Name */
    public static final String COLUMNNAME_Trade_Name = "Trade_Name";

	/** Set Trade Name	  */
	public void setTrade_Name (String Trade_Name);

	/** Get Trade Name	  */
	public String getTrade_Name();

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
