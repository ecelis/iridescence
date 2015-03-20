/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FactorPreDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_FactorPreDet 
{

    /** TableName=EXME_FactorPreDet */
    public static final String Table_Name = "EXME_FactorPreDet";

    /** AD_Table_ID=1000122 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name EXME_FactorPreDet_ID */
    public static final String COLUMNNAME_EXME_FactorPreDet_ID = "EXME_FactorPreDet_ID";

	/** Set Sales Price Factor ( Detail ).
	  * Sales price factor  ( Detail ) 
	  */
	public void setEXME_FactorPreDet_ID (int EXME_FactorPreDet_ID);

	/** Get Sales Price Factor ( Detail ).
	  * Sales price factor  ( Detail ) 
	  */
	public int getEXME_FactorPreDet_ID();

    /** Column name EXME_FactorPre_ID */
    public static final String COLUMNNAME_EXME_FactorPre_ID = "EXME_FactorPre_ID";

	/** Set Price Factor.
	  * Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID);

	/** Get Price Factor.
	  * Sales Price Factor
	  */
	public int getEXME_FactorPre_ID();

    /** Column name Linea */
    public static final String COLUMNNAME_Linea = "Linea";

	/** Set Line.
	  * Line
	  */
	public void setLinea (int Linea);

	/** Get Line.
	  * Line
	  */
	public int getLinea();

    /** Column name NivelSuperior */
    public static final String COLUMNNAME_NivelSuperior = "NivelSuperior";

	/** Set Superior Level.
	  * Superior Level
	  */
	public void setNivelSuperior (BigDecimal NivelSuperior);

	/** Get Superior Level.
	  * Superior Level
	  */
	public BigDecimal getNivelSuperior();

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
}
