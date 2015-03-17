/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_GenProd_Trade
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_T_GenProd_Trade 
{

    /** TableName=EXME_T_GenProd_Trade */
    public static final String Table_Name = "EXME_T_GenProd_Trade";

    /** AD_Table_ID=1201171 */
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

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

    /** Column name EXME_T_GenProd_Trade_ID */
    public static final String COLUMNNAME_EXME_T_GenProd_Trade_ID = "EXME_T_GenProd_Trade_ID";

	/** Set Generic Product And Trade Name Id	  */
	public void setEXME_T_GenProd_Trade_ID (int EXME_T_GenProd_Trade_ID);

	/** Get Generic Product And Trade Name Id	  */
	public int getEXME_T_GenProd_Trade_ID();

    /** Column name Generic_Product_Name */
    public static final String COLUMNNAME_Generic_Product_Name = "Generic_Product_Name";

	/** Set Generic Product Name	  */
	public void setGeneric_Product_Name (String Generic_Product_Name);

	/** Get Generic Product Name	  */
	public String getGeneric_Product_Name();

    /** Column name IsFormulary */
    public static final String COLUMNNAME_IsFormulary = "IsFormulary";

	/** Set In formulary.
	  * In formulary
	  */
	public void setIsFormulary (boolean IsFormulary);

	/** Get In formulary.
	  * In formulary
	  */
	public boolean isFormulary();

    /** Column name IsPrefer */
    public static final String COLUMNNAME_IsPrefer = "IsPrefer";

	/** Set Is Prefered Medication.
	  * Is Prefered Medication
	  */
	public void setIsPrefer (boolean IsPrefer);

	/** Get Is Prefered Medication.
	  * Is Prefered Medication
	  */
	public boolean isPrefer();

    /** Column name Trade_Name */
    public static final String COLUMNNAME_Trade_Name = "Trade_Name";

	/** Set Trade Name	  */
	public void setTrade_Name (String Trade_Name);

	/** Get Trade Name	  */
	public String getTrade_Name();
}
