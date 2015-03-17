/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PerformingLab
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PerformingLab 
{

    /** TableName=EXME_PerformingLab */
    public static final String Table_Name = "EXME_PerformingLab";

    /** AD_Table_ID=1201332 */
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

    /** Column name Director_Medico */
    public static final String COLUMNNAME_Director_Medico = "Director_Medico";

	/** Set Director_Medico.
	  * Director_Medico
	  */
	public void setDirector_Medico (String Director_Medico);

	/** Get Director_Medico.
	  * Director_Medico
	  */
	public String getDirector_Medico();

    /** Column name EXME_PerformingLab_ID */
    public static final String COLUMNNAME_EXME_PerformingLab_ID = "EXME_PerformingLab_ID";

	/** Set Performing Lab	  */
	public void setEXME_PerformingLab_ID (int EXME_PerformingLab_ID);

	/** Get Performing Lab	  */
	public int getEXME_PerformingLab_ID();

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

    /** Column name License */
    public static final String COLUMNNAME_License = "License";

	/** Set License	  */
	public void setLicense (String License);

	/** Get License	  */
	public String getLicense();

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
}
