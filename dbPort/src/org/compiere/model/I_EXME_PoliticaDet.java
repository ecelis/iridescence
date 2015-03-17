/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PoliticaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PoliticaDet 
{

    /** TableName=EXME_PoliticaDet */
    public static final String Table_Name = "EXME_PoliticaDet";

    /** AD_Table_ID=1000055 */
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

    /** Column name AplicaPorc */
    public static final String COLUMNNAME_AplicaPorc = "AplicaPorc";

	/** Set Apply Percentage.
	  * Apply Percentage
	  */
	public void setAplicaPorc (boolean AplicaPorc);

	/** Get Apply Percentage.
	  * Apply Percentage
	  */
	public boolean isAplicaPorc();

    /** Column name EXME_PoliticaDet_ID */
    public static final String COLUMNNAME_EXME_PoliticaDet_ID = "EXME_PoliticaDet_ID";

	/** Set Detail of Policy.
	  * Detail of policy
	  */
	public void setEXME_PoliticaDet_ID (int EXME_PoliticaDet_ID);

	/** Get Detail of Policy.
	  * Detail of policy
	  */
	public int getEXME_PoliticaDet_ID();

    /** Column name EXME_Politica_ID */
    public static final String COLUMNNAME_EXME_Politica_ID = "EXME_Politica_ID";

	/** Set Policy.
	  * Policy
	  */
	public void setEXME_Politica_ID (int EXME_Politica_ID);

	/** Get Policy.
	  * Policy
	  */
	public int getEXME_Politica_ID();

	public I_EXME_Politica getEXME_Politica() throws RuntimeException;

    /** Column name EXME_TipoProd_ID */
    public static final String COLUMNNAME_EXME_TipoProd_ID = "EXME_TipoProd_ID";

	/** Set Product Subtype.
	  * Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID);

	/** Get Product Subtype.
	  * Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID();
}
