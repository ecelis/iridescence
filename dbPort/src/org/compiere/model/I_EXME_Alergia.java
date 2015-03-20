/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Alergia
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Alergia 
{

    /** TableName=EXME_Alergia */
    public static final String Table_Name = "EXME_Alergia";

    /** AD_Table_ID=1200651 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name Alergenico */
    public static final String COLUMNNAME_Alergenico = "Alergenico";

	/** Set Allergenic.
	  * Allergenic
	  */
	public void setAlergenico (String Alergenico);

	/** Get Allergenic.
	  * Allergenic
	  */
	public String getAlergenico();

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

    /** Column name EXME_Alergia_ID */
    public static final String COLUMNNAME_EXME_Alergia_ID = "EXME_Alergia_ID";

	/** Set Allergy.
	  * Allergy
	  */
	public void setEXME_Alergia_ID (int EXME_Alergia_ID);

	/** Get Allergy.
	  * Allergy
	  */
	public int getEXME_Alergia_ID();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

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

    /** Column name TipoAlergia */
    public static final String COLUMNNAME_TipoAlergia = "TipoAlergia";

	/** Set Alergy Type.
	  * Alergy Type
	  */
	public void setTipoAlergia (String TipoAlergia);

	/** Get Alergy Type.
	  * Alergy Type
	  */
	public String getTipoAlergia();

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
