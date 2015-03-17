/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Institucion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Institucion 
{

    /** TableName=EXME_Institucion */
    public static final String Table_Name = "EXME_Institucion";

    /** AD_Table_ID=1000191 */
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

    /** Column name C_LocationPhys_ID */
    public static final String COLUMNNAME_C_LocationPhys_ID = "C_LocationPhys_ID";

	/** Set Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID);

	/** Get Physical Address	  */
	public int getC_LocationPhys_ID();

    /** Column name ContactName */
    public static final String COLUMNNAME_ContactName = "ContactName";

	/** Set Contact Name.
	  * Business Partner Contact Name
	  */
	public void setContactName (String ContactName);

	/** Get Contact Name.
	  * Business Partner Contact Name
	  */
	public String getContactName();

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

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Service Facility.
	  * Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Service Facility.
	  * Service Facility
	  */
	public int getEXME_Institucion_ID();

    /** Column name FaxNumber */
    public static final String COLUMNNAME_FaxNumber = "FaxNumber";

	/** Set Fax Number.
	  * Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public void setFaxNumber (String FaxNumber);

	/** Get Fax Number.
	  * Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public String getFaxNumber();

    /** Column name LastName */
    public static final String COLUMNNAME_LastName = "LastName";

	/** Set LastName	  */
	public void setLastName (String LastName);

	/** Get LastName	  */
	public String getLastName();

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

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Main Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Main Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

    /** Column name TaxonomyID */
    public static final String COLUMNNAME_TaxonomyID = "TaxonomyID";

	/** Set TaxonomyID	  */
	public void setTaxonomyID (String TaxonomyID);

	/** Get TaxonomyID	  */
	public String getTaxonomyID();

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
