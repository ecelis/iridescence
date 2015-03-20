/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for IX_C_Invoice
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_IX_C_Invoice 
{

    /** TableName=IX_C_Invoice */
    public static final String Table_Name = "IX_C_Invoice";

    /** AD_Table_ID=1200255 */
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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name IX_C_Invoice_ID */
    public static final String COLUMNNAME_IX_C_Invoice_ID = "IX_C_Invoice_ID";

	/** Set Invoice	  */
	public void setIX_C_Invoice_ID (int IX_C_Invoice_ID);

	/** Get Invoice	  */
	public int getIX_C_Invoice_ID();

    /** Column name NombreArchivoInterfase */
    public static final String COLUMNNAME_NombreArchivoInterfase = "NombreArchivoInterfase";

	/** Set Interface File Name	  */
	public void setNombreArchivoInterfase (String NombreArchivoInterfase);

	/** Get Interface File Name	  */
	public String getNombreArchivoInterfase();
}
