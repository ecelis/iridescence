/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Concepto
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Concepto 
{

    /** TableName=EXME_Concepto */
    public static final String Table_Name = "EXME_Concepto";

    /** AD_Table_ID=1200319 */
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

    /** Column name EXME_Capitulo_ID */
    public static final String COLUMNNAME_EXME_Capitulo_ID = "EXME_Capitulo_ID";

	/** Set Chapter.
	  * Chapter
	  */
	public void setEXME_Capitulo_ID (int EXME_Capitulo_ID);

	/** Get Chapter.
	  * Chapter
	  */
	public int getEXME_Capitulo_ID();

	public I_EXME_Capitulo getEXME_Capitulo() throws RuntimeException;

    /** Column name EXME_Concepto_ID */
    public static final String COLUMNNAME_EXME_Concepto_ID = "EXME_Concepto_ID";

	/** Set Concept.
	  * Concept
	  */
	public void setEXME_Concepto_ID (int EXME_Concepto_ID);

	/** Get Concept.
	  * Concept
	  */
	public int getEXME_Concepto_ID();

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
