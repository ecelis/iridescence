/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Addenda_Para
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Addenda_Para 
{

    /** TableName=EXME_Addenda_Para */
    public static final String Table_Name = "EXME_Addenda_Para";

    /** AD_Table_ID=1201397 */
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

    /** Column name DataType */
    public static final String COLUMNNAME_DataType = "DataType";

	/** Set Data Type.
	  * Type of data
	  */
	public void setDataType (String DataType);

	/** Get Data Type.
	  * Type of data
	  */
	public String getDataType();

    /** Column name DefaultValue */
    public static final String COLUMNNAME_DefaultValue = "DefaultValue";

	/** Set Default Logic.
	  * Default value hierarchy, separated by ;

	  */
	public void setDefaultValue (String DefaultValue);

	/** Get Default Logic.
	  * Default value hierarchy, separated by ;

	  */
	public String getDefaultValue();

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

    /** Column name EXME_Addenda_ID */
    public static final String COLUMNNAME_EXME_Addenda_ID = "EXME_Addenda_ID";

	/** Set Addendum Settings for Clients	  */
	public void setEXME_Addenda_ID (int EXME_Addenda_ID);

	/** Get Addendum Settings for Clients	  */
	public int getEXME_Addenda_ID();

	public I_EXME_Addenda getEXME_Addenda() throws RuntimeException;

    /** Column name EXME_Addenda_Para_ID */
    public static final String COLUMNNAME_EXME_Addenda_Para_ID = "EXME_Addenda_Para_ID";

	/** Set Addendum Settings Parameters for Clients	  */
	public void setEXME_Addenda_Para_ID (int EXME_Addenda_Para_ID);

	/** Get Addendum Settings Parameters for Clients	  */
	public int getEXME_Addenda_Para_ID();

    /** Column name IsRequired */
    public static final String COLUMNNAME_IsRequired = "IsRequired";

	/** Set Required.
	  * Required
	  */
	public void setIsRequired (boolean IsRequired);

	/** Get Required.
	  * Required
	  */
	public boolean isRequired();

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
}
