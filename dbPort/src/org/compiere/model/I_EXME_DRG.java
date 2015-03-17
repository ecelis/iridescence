/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DRG
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_DRG 
{

    /** TableName=EXME_DRG */
    public static final String Table_Name = "EXME_DRG";

    /** AD_Table_ID=1201254 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name Arithmetic */
    public static final String COLUMNNAME_Arithmetic = "Arithmetic";

	/** Set Arithmetic	  */
	public void setArithmetic (BigDecimal Arithmetic);

	/** Get Arithmetic	  */
	public BigDecimal getArithmetic();

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

    /** Column name EXME_DRG_ID */
    public static final String COLUMNNAME_EXME_DRG_ID = "EXME_DRG_ID";

	/** Set DRG Code ID	  */
	public void setEXME_DRG_ID (int EXME_DRG_ID);

	/** Get DRG Code ID	  */
	public int getEXME_DRG_ID();

    /** Column name Geometric */
    public static final String COLUMNNAME_Geometric = "Geometric";

	/** Set Geometric	  */
	public void setGeometric (BigDecimal Geometric);

	/** Get Geometric	  */
	public BigDecimal getGeometric();

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

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (int Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public int getVersion();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();

    /** Column name Year */
    public static final String COLUMNNAME_Year = "Year";

	/** Set Year.
	  * Calendar Year
	  */
	public void setYear (int Year);

	/** Get Year.
	  * Calendar Year
	  */
	public int getYear();
}
