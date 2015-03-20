/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GenDrug
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_GenDrug 
{

    /** TableName=EXME_GenDrug */
    public static final String Table_Name = "EXME_GenDrug";

    /** AD_Table_ID=1201144 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Drug_ID */
    public static final String COLUMNNAME_Drug_ID = "Drug_ID";

	/** Set Drug	  */
	public void setDrug_ID (int Drug_ID);

	/** Get Drug	  */
	public int getDrug_ID();

    /** Column name EXME_GenDrug_ID */
    public static final String COLUMNNAME_EXME_GenDrug_ID = "EXME_GenDrug_ID";

	/** Set Generic Drug	  */
	public void setEXME_GenDrug_ID (int EXME_GenDrug_ID);

	/** Get Generic Drug	  */
	public int getEXME_GenDrug_ID();

    /** Column name Generic_Name */
    public static final String COLUMNNAME_Generic_Name = "Generic_Name";

	/** Set Generic Name	  */
	public void setGeneric_Name (String Generic_Name);

	/** Get Generic Name	  */
	public String getGeneric_Name();

    /** Column name HALF_LIFE_HOURS */
    public static final String COLUMNNAME_HALF_LIFE_HOURS = "HALF_LIFE_HOURS";

	/** Set HALF_LIFE_HOURS	  */
	public void setHALF_LIFE_HOURS (int HALF_LIFE_HOURS);

	/** Get HALF_LIFE_HOURS	  */
	public int getHALF_LIFE_HOURS();

    /** Column name HALF_LIFE_IS_EMPIRICAL */
    public static final String COLUMNNAME_HALF_LIFE_IS_EMPIRICAL = "HALF_LIFE_IS_EMPIRICAL";

	/** Set HALF_LIFE_IS_EMPIRICAL	  */
	public void setHALF_LIFE_IS_EMPIRICAL (BigDecimal HALF_LIFE_IS_EMPIRICAL);

	/** Get HALF_LIFE_IS_EMPIRICAL	  */
	public BigDecimal getHALF_LIFE_IS_EMPIRICAL();

    /** Column name IS_INGREDIENT */
    public static final String COLUMNNAME_IS_INGREDIENT = "IS_INGREDIENT";

	/** Set IS_INGREDIENT	  */
	public void setIS_INGREDIENT (boolean IS_INGREDIENT);

	/** Get IS_INGREDIENT	  */
	public boolean is_INGREDIENT();

    /** Column name IS_PRODUCT */
    public static final String COLUMNNAME_IS_PRODUCT = "IS_PRODUCT";

	/** Set IS_PRODUCT	  */
	public void setIS_PRODUCT (boolean IS_PRODUCT);

	/** Get IS_PRODUCT	  */
	public boolean is_PRODUCT();

    /** Column name MANUFACTURER_GENERIC_NAME */
    public static final String COLUMNNAME_MANUFACTURER_GENERIC_NAME = "MANUFACTURER_GENERIC_NAME";

	/** Set Manufacturer Generic Name	  */
	public void setMANUFACTURER_GENERIC_NAME (String MANUFACTURER_GENERIC_NAME);

	/** Get Manufacturer Generic Name	  */
	public String getMANUFACTURER_GENERIC_NAME();

    /** Column name Obsolete_Date */
    public static final String COLUMNNAME_Obsolete_Date = "Obsolete_Date";

	/** Set Obsolete Date	  */
	public void setObsolete_Date (Timestamp Obsolete_Date);

	/** Get Obsolete Date	  */
	public Timestamp getObsolete_Date();

    /** Column name PREGNANCY_RISK_FACTOR */
    public static final String COLUMNNAME_PREGNANCY_RISK_FACTOR = "PREGNANCY_RISK_FACTOR";

	/** Set PREGNANCY_RISK_FACTOR	  */
	public void setPREGNANCY_RISK_FACTOR (String PREGNANCY_RISK_FACTOR);

	/** Get PREGNANCY_RISK_FACTOR	  */
	public String getPREGNANCY_RISK_FACTOR();

    /** Column name RXCUI */
    public static final String COLUMNNAME_RXCUI = "RXCUI";

	/** Set RXCUI	  */
	public void setRXCUI (String RXCUI);

	/** Get RXCUI	  */
	public String getRXCUI();

    /** Column name RX_OTC_STATUS_CODE */
    public static final String COLUMNNAME_RX_OTC_STATUS_CODE = "RX_OTC_STATUS_CODE";

	/** Set RX_OTC_STATUS_CODE	  */
	public void setRX_OTC_STATUS_CODE (String RX_OTC_STATUS_CODE);

	/** Get RX_OTC_STATUS_CODE	  */
	public String getRX_OTC_STATUS_CODE();

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
