/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Unidad
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Unidad 
{

    /** TableName=EXME_Unidad */
    public static final String Table_Name = "EXME_Unidad";

    /** AD_Table_ID=1200420 */
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

    /** Column name EXME_EscalonSanitario_ID */
    public static final String COLUMNNAME_EXME_EscalonSanitario_ID = "EXME_EscalonSanitario_ID";

	/** Set Medical Unit	  */
	public void setEXME_EscalonSanitario_ID (int EXME_EscalonSanitario_ID);

	/** Get Medical Unit	  */
	public int getEXME_EscalonSanitario_ID();

    /** Column name EXME_Unidad_ID */
    public static final String COLUMNNAME_EXME_Unidad_ID = "EXME_Unidad_ID";

	/** Set Unit.
	  * Militar Unit
	  */
	public void setEXME_Unidad_ID (int EXME_Unidad_ID);

	/** Get Unit.
	  * Militar Unit
	  */
	public int getEXME_Unidad_ID();

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
