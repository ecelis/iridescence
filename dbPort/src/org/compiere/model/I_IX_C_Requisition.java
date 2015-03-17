/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for IX_C_Requisition
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_IX_C_Requisition 
{

    /** TableName=IX_C_Requisition */
    public static final String Table_Name = "IX_C_Requisition";

    /** AD_Table_ID=1200256 */
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

    /** Column name IX_C_Requisition_ID */
    public static final String COLUMNNAME_IX_C_Requisition_ID = "IX_C_Requisition_ID";

	/** Set Requisition.
	  * Requisition
	  */
	public void setIX_C_Requisition_ID (int IX_C_Requisition_ID);

	/** Get Requisition.
	  * Requisition
	  */
	public int getIX_C_Requisition_ID();

    /** Column name M_Requisition_ID */
    public static final String COLUMNNAME_M_Requisition_ID = "M_Requisition_ID";

	/** Set Requisition.
	  * Material Requisition
	  */
	public void setM_Requisition_ID (int M_Requisition_ID);

	/** Get Requisition.
	  * Material Requisition
	  */
	public int getM_Requisition_ID();

	public I_M_Requisition getM_Requisition() throws RuntimeException;

    /** Column name NombreArchivoInterfase */
    public static final String COLUMNNAME_NombreArchivoInterfase = "NombreArchivoInterfase";

	/** Set Interface File Name	  */
	public void setNombreArchivoInterfase (String NombreArchivoInterfase);

	/** Get Interface File Name	  */
	public String getNombreArchivoInterfase();
}
