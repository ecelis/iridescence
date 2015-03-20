/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SystemBody
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SystemBody 
{

    /** TableName=EXME_SystemBody */
    public static final String Table_Name = "EXME_SystemBody";

    /** AD_Table_ID=1200894 */
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

    /** Column name BackImage */
    public static final String COLUMNNAME_BackImage = "BackImage";

	/** Set Back Image	  */
	public void setBackImage (String BackImage);

	/** Get Back Image	  */
	public String getBackImage();

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

    /** Column name EXME_SystemBody_ID */
    public static final String COLUMNNAME_EXME_SystemBody_ID = "EXME_SystemBody_ID";

	/** Set System Body	  */
	public void setEXME_SystemBody_ID (int EXME_SystemBody_ID);

	/** Get System Body	  */
	public int getEXME_SystemBody_ID();

    /** Column name FrontImage */
    public static final String COLUMNNAME_FrontImage = "FrontImage";

	/** Set Front Image.
	  * Front Image
	  */
	public void setFrontImage (String FrontImage);

	/** Get Front Image.
	  * Front Image
	  */
	public String getFrontImage();

    /** Column name LeftImage */
    public static final String COLUMNNAME_LeftImage = "LeftImage";

	/** Set Left Image	  */
	public void setLeftImage (String LeftImage);

	/** Get Left Image	  */
	public String getLeftImage();

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

    /** Column name RightImage */
    public static final String COLUMNNAME_RightImage = "RightImage";

	/** Set Right Image	  */
	public void setRightImage (String RightImage);

	/** Get Right Image	  */
	public String getRightImage();

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
