/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Politica
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Politica 
{

    /** TableName=EXME_Politica */
    public static final String Table_Name = "EXME_Politica";

    /** AD_Table_ID=1000054 */
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

    /** Column name Porcentaje */
    public static final String COLUMNNAME_Porcentaje = "Porcentaje";

	/** Set Percentage.
	  * percentage
	  */
	public void setPorcentaje (BigDecimal Porcentaje);

	/** Get Percentage.
	  * percentage
	  */
	public BigDecimal getPorcentaje();

    /** Column name WhsSolicita */
    public static final String COLUMNNAME_WhsSolicita = "WhsSolicita";

	/** Set Requesting warehouse.
	  * Requesting warehouse
	  */
	public void setWhsSolicita (int WhsSolicita);

	/** Get Requesting warehouse.
	  * Requesting warehouse
	  */
	public int getWhsSolicita();

    /** Column name WhsSurte */
    public static final String COLUMNNAME_WhsSurte = "WhsSurte";

	/** Set Deliver Warehouse.
	  * Deliver warehouse
	  */
	public void setWhsSurte (int WhsSurte);

	/** Get Deliver Warehouse.
	  * Deliver warehouse
	  */
	public int getWhsSurte();
}
