/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Vacacion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Vacacion 
{

    /** TableName=EXME_Vacacion */
    public static final String Table_Name = "EXME_Vacacion";

    /** AD_Table_ID=1200462 */
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

    /** Column name EXME_Emp_ID */
    public static final String COLUMNNAME_EXME_Emp_ID = "EXME_Emp_ID";

	/** Set Employee.
	  * Employee
	  */
	public void setEXME_Emp_ID (int EXME_Emp_ID);

	/** Get Employee.
	  * Employee
	  */
	public int getEXME_Emp_ID();

    /** Column name EXME_PerVacD_ID */
    public static final String COLUMNNAME_EXME_PerVacD_ID = "EXME_PerVacD_ID";

	/** Set Vacational Period Details.
	  * Vacational Period Details
	  */
	public void setEXME_PerVacD_ID (int EXME_PerVacD_ID);

	/** Get Vacational Period Details.
	  * Vacational Period Details
	  */
	public int getEXME_PerVacD_ID();

    /** Column name EXME_PerVacH_ID */
    public static final String COLUMNNAME_EXME_PerVacH_ID = "EXME_PerVacH_ID";

	/** Set Vacational Period.
	  * Vacational Period
	  */
	public void setEXME_PerVacH_ID (int EXME_PerVacH_ID);

	/** Get Vacational Period.
	  * Vacational Period
	  */
	public int getEXME_PerVacH_ID();

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

    /** Column name EXME_Vacacion_ID */
    public static final String COLUMNNAME_EXME_Vacacion_ID = "EXME_Vacacion_ID";

	/** Set Vacation	  */
	public void setEXME_Vacacion_ID (int EXME_Vacacion_ID);

	/** Get Vacation	  */
	public int getEXME_Vacacion_ID();
}
