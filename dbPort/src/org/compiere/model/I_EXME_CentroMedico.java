/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CentroMedico
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CentroMedico 
{

    /** TableName=EXME_CentroMedico */
    public static final String Table_Name = "EXME_CentroMedico";

    /** AD_Table_ID=1000005 */
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

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name EXME_CentroMedico_ID */
    public static final String COLUMNNAME_EXME_CentroMedico_ID = "EXME_CentroMedico_ID";

	/** Set Medical Center.
	  * medical Center
	  */
	public void setEXME_CentroMedico_ID (int EXME_CentroMedico_ID);

	/** Get Medical Center.
	  * medical Center
	  */
	public int getEXME_CentroMedico_ID();

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

    /** Column name TelCentroMedico */
    public static final String COLUMNNAME_TelCentroMedico = "TelCentroMedico";

	/** Set Medical Center Telephone.
	  * Medical Center Telephone
	  */
	public void setTelCentroMedico (String TelCentroMedico);

	/** Get Medical Center Telephone.
	  * Medical Center Telephone
	  */
	public String getTelCentroMedico();

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
