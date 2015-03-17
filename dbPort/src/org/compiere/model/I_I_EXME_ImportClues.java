/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_ImportClues
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_ImportClues 
{

    /** TableName=I_EXME_ImportClues */
    public static final String Table_Name = "I_EXME_ImportClues";

    /** AD_Table_ID=1200347 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name EXME_Localidad_ID */
    public static final String COLUMNNAME_EXME_Localidad_ID = "EXME_Localidad_ID";

	/** Set Locality.
	  * Locality
	  */
	public void setEXME_Localidad_ID (int EXME_Localidad_ID);

	/** Get Locality.
	  * Locality
	  */
	public int getEXME_Localidad_ID();

	public I_EXME_Localidad getEXME_Localidad() throws RuntimeException;

    /** Column name EXME_Localidad_Name */
    public static final String COLUMNNAME_EXME_Localidad_Name = "EXME_Localidad_Name";

	/** Set Locality Name.
	  * Locality Name
	  */
	public void setEXME_Localidad_Name (String EXME_Localidad_Name);

	/** Get Locality Name.
	  * Locality Name
	  */
	public String getEXME_Localidad_Name();

    /** Column name EXME_Localidad_Value */
    public static final String COLUMNNAME_EXME_Localidad_Value = "EXME_Localidad_Value";

	/** Set Locality Value.
	  * Locality
	  */
	public void setEXME_Localidad_Value (String EXME_Localidad_Value);

	/** Get Locality Value.
	  * Locality
	  */
	public String getEXME_Localidad_Value();

    /** Column name EXME_Tipologia_ID */
    public static final String COLUMNNAME_EXME_Tipologia_ID = "EXME_Tipologia_ID";

	/** Set Typology	  */
	public void setEXME_Tipologia_ID (int EXME_Tipologia_ID);

	/** Get Typology	  */
	public int getEXME_Tipologia_ID();

	public I_EXME_Tipologia getEXME_Tipologia() throws RuntimeException;

    /** Column name EXME_Tipologia_Name */
    public static final String COLUMNNAME_EXME_Tipologia_Name = "EXME_Tipologia_Name";

	/** Set Typology's name	  */
	public void setEXME_Tipologia_Name (String EXME_Tipologia_Name);

	/** Get Typology's name	  */
	public String getEXME_Tipologia_Name();

    /** Column name EXME_Tipologia_Value */
    public static final String COLUMNNAME_EXME_Tipologia_Value = "EXME_Tipologia_Value";

	/** Set Typology	  */
	public void setEXME_Tipologia_Value (String EXME_Tipologia_Value);

	/** Get Typology	  */
	public String getEXME_Tipologia_Value();

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

    /** Column name EXME_TownCouncil_Name */
    public static final String COLUMNNAME_EXME_TownCouncil_Name = "EXME_TownCouncil_Name";

	/** Set Town Council Name.
	  * Town Council Name
	  */
	public void setEXME_TownCouncil_Name (String EXME_TownCouncil_Name);

	/** Get Town Council Name.
	  * Town Council Name
	  */
	public String getEXME_TownCouncil_Name();

    /** Column name EXME_TownCouncil_Value */
    public static final String COLUMNNAME_EXME_TownCouncil_Value = "EXME_TownCouncil_Value";

	/** Set EXME_TownCouncil_Value	  */
	public void setEXME_TownCouncil_Value (String EXME_TownCouncil_Value);

	/** Get EXME_TownCouncil_Value	  */
	public String getEXME_TownCouncil_Value();

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

    /** Column name I_EXME_ImportClues_ID */
    public static final String COLUMNNAME_I_EXME_ImportClues_ID = "I_EXME_ImportClues_ID";

	/** Set Import CLUES.
	  * Import CLUES
	  */
	public void setI_EXME_ImportClues_ID (int I_EXME_ImportClues_ID);

	/** Get Import CLUES.
	  * Import CLUES
	  */
	public int getI_EXME_ImportClues_ID();

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

    /** Column name Org_ID */
    public static final String COLUMNNAME_Org_ID = "Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getOrg_ID();

    /** Column name OrgName */
    public static final String COLUMNNAME_OrgName = "OrgName";

	/** Set Organization Name.
	  * Name of the Organization
	  */
	public void setOrgName (String OrgName);

	/** Get Organization Name.
	  * Name of the Organization
	  */
	public String getOrgName();

    /** Column name OrgValue */
    public static final String COLUMNNAME_OrgValue = "OrgValue";

	/** Set Organization Key.
	  * Key of the Organization
	  */
	public void setOrgValue (String OrgValue);

	/** Get Organization Key.
	  * Key of the Organization
	  */
	public String getOrgValue();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

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

    /** Column name Region */
    public static final String COLUMNNAME_Region = "Region";

	/** Set Region	  */
	public void setRegion (String Region);

	/** Get Region	  */
	public String getRegion();

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

    /** Column name TipoInstitucion */
    public static final String COLUMNNAME_TipoInstitucion = "TipoInstitucion";

	/** Set Institution Type	  */
	public void setTipoInstitucion (String TipoInstitucion);

	/** Get Institution Type	  */
	public String getTipoInstitucion();

    /** Column name TipoUnidad */
    public static final String COLUMNNAME_TipoUnidad = "TipoUnidad";

	/** Set Unity Type	  */
	public void setTipoUnidad (String TipoUnidad);

	/** Get Unity Type	  */
	public String getTipoUnidad();
}
