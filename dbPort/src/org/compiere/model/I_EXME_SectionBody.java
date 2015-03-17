/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SectionBody
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SectionBody 
{

    /** TableName=EXME_SectionBody */
    public static final String Table_Name = "EXME_SectionBody";

    /** AD_Table_ID=1200896 */
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

    /** Column name BackMap */
    public static final String COLUMNNAME_BackMap = "BackMap";

	/** Set Back Map	  */
	public void setBackMap (String BackMap);

	/** Get Back Map	  */
	public String getBackMap();

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

    /** Column name DiagFin */
    public static final String COLUMNNAME_DiagFin = "DiagFin";

	/** Set Final Diagnosis.
	  * Rank of Final Diagnosis
	  */
	public void setDiagFin (String DiagFin);

	/** Get Final Diagnosis.
	  * Rank of Final Diagnosis
	  */
	public String getDiagFin();

    /** Column name DiagIni */
    public static final String COLUMNNAME_DiagIni = "DiagIni";

	/** Set Initial Diagnosis.
	  * Rank of Initial Diagnosis
	  */
	public void setDiagIni (String DiagIni);

	/** Get Initial Diagnosis.
	  * Rank of Initial Diagnosis
	  */
	public String getDiagIni();

    /** Column name EXME_SectionBody_ID */
    public static final String COLUMNNAME_EXME_SectionBody_ID = "EXME_SectionBody_ID";

	/** Set Section Body	  */
	public void setEXME_SectionBody_ID (int EXME_SectionBody_ID);

	/** Get Section Body	  */
	public int getEXME_SectionBody_ID();

    /** Column name EXME_SystemBody_ID */
    public static final String COLUMNNAME_EXME_SystemBody_ID = "EXME_SystemBody_ID";

	/** Set System Body	  */
	public void setEXME_SystemBody_ID (int EXME_SystemBody_ID);

	/** Get System Body	  */
	public int getEXME_SystemBody_ID();

	public I_EXME_SystemBody getEXME_SystemBody() throws RuntimeException;

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

    /** Column name FrontMap */
    public static final String COLUMNNAME_FrontMap = "FrontMap";

	/** Set Front Map	  */
	public void setFrontMap (String FrontMap);

	/** Get Front Map	  */
	public String getFrontMap();

    /** Column name LeftImage */
    public static final String COLUMNNAME_LeftImage = "LeftImage";

	/** Set Left Image	  */
	public void setLeftImage (String LeftImage);

	/** Get Left Image	  */
	public String getLeftImage();

    /** Column name LeftMap */
    public static final String COLUMNNAME_LeftMap = "LeftMap";

	/** Set Left Map	  */
	public void setLeftMap (String LeftMap);

	/** Get Left Map	  */
	public String getLeftMap();

    /** Column name Map */
    public static final String COLUMNNAME_Map = "Map";

	/** Set Map	  */
	public void setMap (String Map);

	/** Get Map	  */
	public String getMap();

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

    /** Column name REF_SectionBody_ID */
    public static final String COLUMNNAME_REF_SectionBody_ID = "REF_SectionBody_ID";

	/** Set Reference to Exme_SectionBody	  */
	public void setREF_SectionBody_ID (int REF_SectionBody_ID);

	/** Get Reference to Exme_SectionBody	  */
	public int getREF_SectionBody_ID();

    /** Column name RightImage */
    public static final String COLUMNNAME_RightImage = "RightImage";

	/** Set Right Image	  */
	public void setRightImage (String RightImage);

	/** Get Right Image	  */
	public String getRightImage();

    /** Column name RightMap */
    public static final String COLUMNNAME_RightMap = "RightMap";

	/** Set Right Map	  */
	public void setRightMap (String RightMap);

	/** Get Right Map	  */
	public String getRightMap();

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
