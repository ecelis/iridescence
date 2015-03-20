/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Colonia
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Colonia 
{

    /** TableName=EXME_Colonia */
    public static final String Table_Name = "EXME_Colonia";

    /** AD_Table_ID=1200286 */
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
}
