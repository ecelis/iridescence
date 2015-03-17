/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Rel_Source_Trans
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Rel_Source_Trans 
{

    /** TableName=EXME_Rel_Source_Trans */
    public static final String Table_Name = "EXME_Rel_Source_Trans";

    /** AD_Table_ID=1200226 */
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

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

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

    /** Column name EXME_Rel_Source_Trans_ID */
    public static final String COLUMNNAME_EXME_Rel_Source_Trans_ID = "EXME_Rel_Source_Trans_ID";

	/** Set Source Relationship and Inventories Transaction.
	  * Source Relationship and Inventories Transaction
	  */
	public void setEXME_Rel_Source_Trans_ID (int EXME_Rel_Source_Trans_ID);

	/** Get Source Relationship and Inventories Transaction.
	  * Source Relationship and Inventories Transaction
	  */
	public int getEXME_Rel_Source_Trans_ID();

    /** Column name EXME_Source_Code_ID */
    public static final String COLUMNNAME_EXME_Source_Code_ID = "EXME_Source_Code_ID";

	/** Set Codigo Fuente.
	  * Codigo Fuente
	  */
	public void setEXME_Source_Code_ID (int EXME_Source_Code_ID);

	/** Get Codigo Fuente.
	  * Codigo Fuente
	  */
	public int getEXME_Source_Code_ID();

	public I_EXME_Source_Code getEXME_Source_Code() throws RuntimeException;
}
