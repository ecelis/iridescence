/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConceptoFin
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConceptoFin 
{

    /** TableName=EXME_ConceptoFin */
    public static final String Table_Name = "EXME_ConceptoFin";

    /** AD_Table_ID=1200322 */
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

    /** Column name EXME_ConceptoFin_ID */
    public static final String COLUMNNAME_EXME_ConceptoFin_ID = "EXME_ConceptoFin_ID";

	/** Set Financial Concept	  */
	public void setEXME_ConceptoFin_ID (int EXME_ConceptoFin_ID);

	/** Get Financial Concept	  */
	public int getEXME_ConceptoFin_ID();

    /** Column name Monto */
    public static final String COLUMNNAME_Monto = "Monto";

	/** Set Amount	  */
	public void setMonto (int Monto);

	/** Get Amount	  */
	public int getMonto();

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
