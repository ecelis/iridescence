/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_City
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_City 
{

    /** TableName=C_City */
    public static final String Table_Name = "C_City";

    /** AD_Table_ID=186 */
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

    /** Column name AreaCode */
    public static final String COLUMNNAME_AreaCode = "AreaCode";

	/** Set Area Code.
	  * Phone Area Code
	  */
	public void setAreaCode (String AreaCode);

	/** Get Area Code.
	  * Phone Area Code
	  */
	public String getAreaCode();

    /** Column name C_City_ID */
    public static final String COLUMNNAME_C_City_ID = "C_City_ID";

	/** Set City.
	  * City
	  */
	public void setC_City_ID (int C_City_ID);

	/** Get City.
	  * City
	  */
	public int getC_City_ID();

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

    /** Column name Coordinates */
    public static final String COLUMNNAME_Coordinates = "Coordinates";

	/** Set Coordinates.
	  * Location coordinate
	  */
	public void setCoordinates (String Coordinates);

	/** Get Coordinates.
	  * Location coordinate
	  */
	public String getCoordinates();

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

    /** Column name Locode */
    public static final String COLUMNNAME_Locode = "Locode";

	/** Set Locode.
	  * Location code - UN/LOCODE 
	  */
	public void setLocode (String Locode);

	/** Get Locode.
	  * Location code - UN/LOCODE 
	  */
	public String getLocode();

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
}
