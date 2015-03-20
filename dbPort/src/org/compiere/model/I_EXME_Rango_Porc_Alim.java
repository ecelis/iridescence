/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Rango_Porc_Alim
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Rango_Porc_Alim 
{

    /** TableName=EXME_Rango_Porc_Alim */
    public static final String Table_Name = "EXME_Rango_Porc_Alim";

    /** AD_Table_ID=1000205 */
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

    /** Column name EXME_Rango_Porc_Alim_ID */
    public static final String COLUMNNAME_EXME_Rango_Porc_Alim_ID = "EXME_Rango_Porc_Alim_ID";

	/** Set Feeding and score percentage rank.
	  * Feeding and score percentage rank
	  */
	public void setEXME_Rango_Porc_Alim_ID (int EXME_Rango_Porc_Alim_ID);

	/** Get Feeding and score percentage rank.
	  * Feeding and score percentage rank
	  */
	public int getEXME_Rango_Porc_Alim_ID();

    /** Column name Porcentaje_Final */
    public static final String COLUMNNAME_Porcentaje_Final = "Porcentaje_Final";

	/** Set Final Percentage.
	  * Final Percentage
	  */
	public void setPorcentaje_Final (BigDecimal Porcentaje_Final);

	/** Get Final Percentage.
	  * Final Percentage
	  */
	public BigDecimal getPorcentaje_Final();

    /** Column name Porcentaje_Inicial */
    public static final String COLUMNNAME_Porcentaje_Inicial = "Porcentaje_Inicial";

	/** Set Percentage Initial.
	  * Percentage Initial
	  */
	public void setPorcentaje_Inicial (BigDecimal Porcentaje_Inicial);

	/** Get Percentage Initial.
	  * Percentage Initial
	  */
	public BigDecimal getPorcentaje_Inicial();

    /** Column name Puntaje */
    public static final String COLUMNNAME_Puntaje = "Puntaje";

	/** Set Score.
	  * Score
	  */
	public void setPuntaje (BigDecimal Puntaje);

	/** Get Score.
	  * Score
	  */
	public BigDecimal getPuntaje();
}
