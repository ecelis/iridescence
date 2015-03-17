/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigOdonto
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigOdonto 
{

    /** TableName=EXME_ConfigOdonto */
    public static final String Table_Name = "EXME_ConfigOdonto";

    /** AD_Table_ID=1200625 */
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

    /** Column name EXME_ConfigOdonto_ID */
    public static final String COLUMNNAME_EXME_ConfigOdonto_ID = "EXME_ConfigOdonto_ID";

	/** Set Odontology Configuration.
	  * Odontology Configuration
	  */
	public void setEXME_ConfigOdonto_ID (int EXME_ConfigOdonto_ID);

	/** Get Odontology Configuration.
	  * Odontology Configuration
	  */
	public int getEXME_ConfigOdonto_ID();

    /** Column name EXME_Cuestionario_ATM_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ATM_ID = "EXME_Cuestionario_ATM_ID";

	/** Set Questionnaire (TMJ).
	  * Medical Report of  Temporomandibular joint
	  */
	public void setEXME_Cuestionario_ATM_ID (int EXME_Cuestionario_ATM_ID);

	/** Get Questionnaire (TMJ).
	  * Medical Report of  Temporomandibular joint
	  */
	public int getEXME_Cuestionario_ATM_ID();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

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
