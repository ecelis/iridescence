/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Colonia
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Colonia 
{

    /** TableName=I_EXME_Colonia */
    public static final String Table_Name = "I_EXME_Colonia";

    /** AD_Table_ID=1200290 */
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

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public I_C_Country getC_Country() throws RuntimeException;

    /** Column name Ciudad */
    public static final String COLUMNNAME_Ciudad = "Ciudad";

	/** Set City.
	  * description of a city
	  */
	public void setCiudad (String Ciudad);

	/** Get City.
	  * description of a city
	  */
	public String getCiudad();

    /** Column name Codigo_Postal */
    public static final String COLUMNNAME_Codigo_Postal = "Codigo_Postal";

	/** Set Postal Code.
	  * Postal Code as a key
	  */
	public void setCodigo_Postal (String Codigo_Postal);

	/** Get Postal Code.
	  * Postal Code as a key
	  */
	public String getCodigo_Postal();

    /** Column name Colonia */
    public static final String COLUMNNAME_Colonia = "Colonia";

	/** Set Suburb / District.
	  * Suburb / District
	  */
	public void setColonia (String Colonia);

	/** Get Suburb / District.
	  * Suburb / District
	  */
	public String getColonia();

    /** Column name Country_Value */
    public static final String COLUMNNAME_Country_Value = "Country_Value";

	/** Set Country_Value	  */
	public void setCountry_Value (String Country_Value);

	/** Get Country_Value	  */
	public String getCountry_Value();

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

	public I_C_Region getC_Region() throws RuntimeException;

    /** Column name EXME_Colonia_ID */
    public static final String COLUMNNAME_EXME_Colonia_ID = "EXME_Colonia_ID";

	/** Set Suburb / District.
	  * Suburb / District
	  */
	public void setEXME_Colonia_ID (int EXME_Colonia_ID);

	/** Get Suburb / District.
	  * Suburb / District
	  */
	public int getEXME_Colonia_ID();

	public I_EXME_Colonia getEXME_Colonia() throws RuntimeException;

    /** Column name EXME_TownCouncil_ID */
    public static final String COLUMNNAME_EXME_TownCouncil_ID = "EXME_TownCouncil_ID";

	/** Set County.
	  * County
	  */
	public void setEXME_TownCouncil_ID (int EXME_TownCouncil_ID);

	/** Get County.
	  * County
	  */
	public int getEXME_TownCouncil_ID();

	public I_EXME_TownCouncil getEXME_TownCouncil() throws RuntimeException;

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Colonia_ID */
    public static final String COLUMNNAME_I_EXME_Colonia_ID = "I_EXME_Colonia_ID";

	/** Set I_EXME_Colonia_ID	  */
	public void setI_EXME_Colonia_ID (int I_EXME_Colonia_ID);

	/** Get I_EXME_Colonia_ID	  */
	public int getI_EXME_Colonia_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name RegionName */
    public static final String COLUMNNAME_RegionName = "RegionName";

	/** Set Region.
	  * Name of the Region
	  */
	public void setRegionName (String RegionName);

	/** Get Region.
	  * Name of the Region
	  */
	public String getRegionName();

    /** Column name TownCouncilName */
    public static final String COLUMNNAME_TownCouncilName = "TownCouncilName";

	/** Set Town Council Name	  */
	public void setTownCouncilName (String TownCouncilName);

	/** Get Town Council Name	  */
	public String getTownCouncilName();
}
