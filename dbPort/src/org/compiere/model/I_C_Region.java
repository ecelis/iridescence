/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Region
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_Region 
{

    /** TableName=C_Region */
    public static final String Table_Name = "C_Region";

    /** AD_Table_ID=164 */
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

    /** Column name Entidad */
    public static final String COLUMNNAME_Entidad = "Entidad";

	/** Set State.
	  * State
	  */
	public void setEntidad (String Entidad);

	/** Get State.
	  * State
	  */
	public String getEntidad();

    /** Column name HasTownCouncil */
    public static final String COLUMNNAME_HasTownCouncil = "HasTownCouncil";

	/** Set HasTownCouncil	  */
	public void setHasTownCouncil (boolean HasTownCouncil);

	/** Get HasTownCouncil	  */
	public boolean isHasTownCouncil();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

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

    /** Column name TownCouncilName */
    public static final String COLUMNNAME_TownCouncilName = "TownCouncilName";

	/** Set Town Council Name	  */
	public void setTownCouncilName (String TownCouncilName);

	/** Get Town Council Name	  */
	public String getTownCouncilName();

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
