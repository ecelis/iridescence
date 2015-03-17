/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_OrderSet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_OrderSet 
{

    /** TableName=EXME_OrderSet */
    public static final String Table_Name = "EXME_OrderSet";

    /** AD_Table_ID=1201138 */
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

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set Form Group.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get Form Group.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException;

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_OrderCategory_ID */
    public static final String COLUMNNAME_EXME_OrderCategory_ID = "EXME_OrderCategory_ID";

	/** Set Orders Set Category	  */
	public void setEXME_OrderCategory_ID (int EXME_OrderCategory_ID);

	/** Get Orders Set Category	  */
	public int getEXME_OrderCategory_ID();

	public I_EXME_OrderCategory getEXME_OrderCategory() throws RuntimeException;

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

    /** Column name IsCondicionNormal */
    public static final String COLUMNNAME_IsCondicionNormal = "IsCondicionNormal";

	/** Set IsNormalCondition	  */
	public void setIsCondicionNormal (boolean IsCondicionNormal);

	/** Get IsNormalCondition	  */
	public boolean isCondicionNormal();

    /** Column name isMultiSys */
    public static final String COLUMNNAME_isMultiSys = "isMultiSys";

	/** Set Multi System.
	  * Indicates whether or not is multi system
	  */
	public void setisMultiSys (boolean isMultiSys);

	/** Get Multi System.
	  * Indicates whether or not is multi system
	  */
	public boolean isMultiSys();

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

    /** Column name OrderType */
    public static final String COLUMNNAME_OrderType = "OrderType";

	/** Set OrderType	  */
	public void setOrderType (String OrderType);

	/** Get OrderType	  */
	public String getOrderType();

    /** Column name SystemType */
    public static final String COLUMNNAME_SystemType = "SystemType";

	/** Set System Type	  */
	public void setSystemType (String SystemType);

	/** Get System Type	  */
	public String getSystemType();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

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
