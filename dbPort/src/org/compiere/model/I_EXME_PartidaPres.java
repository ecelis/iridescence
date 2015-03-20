/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PartidaPres
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PartidaPres 
{

    /** TableName=EXME_PartidaPres */
    public static final String Table_Name = "EXME_PartidaPres";

    /** AD_Table_ID=1201347 */
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

    /** Column name EXME_ConceptoGasto_ID */
    public static final String COLUMNNAME_EXME_ConceptoGasto_ID = "EXME_ConceptoGasto_ID";

	/** Set Concept of household	  */
	public void setEXME_ConceptoGasto_ID (int EXME_ConceptoGasto_ID);

	/** Get Concept of household	  */
	public int getEXME_ConceptoGasto_ID();

	public I_EXME_ConceptoGasto getEXME_ConceptoGasto() throws RuntimeException;

    /** Column name EXME_PartidaPres_ID */
    public static final String COLUMNNAME_EXME_PartidaPres_ID = "EXME_PartidaPres_ID";

	/** Set Budget Item.
	  * Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID);

	/** Get Budget Item.
	  * Budget Item
	  */
	public int getEXME_PartidaPres_ID();

    /** Column name EXME_TipoGasto_ID */
    public static final String COLUMNNAME_EXME_TipoGasto_ID = "EXME_TipoGasto_ID";

	/** Set Type of expenditure.
	  * Type of expenditure
	  */
	public void setEXME_TipoGasto_ID (int EXME_TipoGasto_ID);

	/** Get Type of expenditure.
	  * Type of expenditure
	  */
	public int getEXME_TipoGasto_ID();

	public I_EXME_TipoGasto getEXME_TipoGasto() throws RuntimeException;

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
