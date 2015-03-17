/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_TaxPostal
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_TaxPostal 
{

    /** TableName=C_TaxPostal */
    public static final String Table_Name = "C_TaxPostal";

    /** AD_Table_ID=701 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name C_TaxPostal_ID */
    public static final String COLUMNNAME_C_TaxPostal_ID = "C_TaxPostal_ID";

	/** Set Tax ZIP.
	  * Tax Postal/ZIP
	  */
	public void setC_TaxPostal_ID (int C_TaxPostal_ID);

	/** Get Tax ZIP.
	  * Tax Postal/ZIP
	  */
	public int getC_TaxPostal_ID();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name Postal_To */
    public static final String COLUMNNAME_Postal_To = "Postal_To";

	/** Set ZIP To.
	  * Postal code to
	  */
	public void setPostal_To (String Postal_To);

	/** Get ZIP To.
	  * Postal code to
	  */
	public String getPostal_To();
}
