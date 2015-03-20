/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Salarios_Min_Mes
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Salarios_Min_Mes 
{

    /** TableName=EXME_Salarios_Min_Mes */
    public static final String Table_Name = "EXME_Salarios_Min_Mes";

    /** AD_Table_ID=1000208 */
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

    /** Column name EXME_Salarios_Min_Mes_ID */
    public static final String COLUMNNAME_EXME_Salarios_Min_Mes_ID = "EXME_Salarios_Min_Mes_ID";

	/** Set Minimum Monthly Salaries.
	  * Minimum Monthly Salaries
	  */
	public void setEXME_Salarios_Min_Mes_ID (int EXME_Salarios_Min_Mes_ID);

	/** Get Minimum Monthly Salaries.
	  * Minimum Monthly Salaries
	  */
	public int getEXME_Salarios_Min_Mes_ID();

    /** Column name Num_Miem_Final */
    public static final String COLUMNNAME_Num_Miem_Final = "Num_Miem_Final";

	/** Set No. Miembro Final.
	  * No. Miembro Final
	  */
	public void setNum_Miem_Final (int Num_Miem_Final);

	/** Get No. Miembro Final.
	  * No. Miembro Final
	  */
	public int getNum_Miem_Final();

    /** Column name Num_Miem_Inicial */
    public static final String COLUMNNAME_Num_Miem_Inicial = "Num_Miem_Inicial";

	/** Set No. Miembro Inicial.
	  * No. Miembro Inicial
	  */
	public void setNum_Miem_Inicial (int Num_Miem_Inicial);

	/** Get No. Miembro Inicial.
	  * No. Miembro Inicial
	  */
	public int getNum_Miem_Inicial();

    /** Column name Puntaje */
    public static final String COLUMNNAME_Puntaje = "Puntaje";

	/** Set Score.
	  * Score
	  */
	public void setPuntaje (int Puntaje);

	/** Get Score.
	  * Score
	  */
	public int getPuntaje();

    /** Column name Sal_Min_Final */
    public static final String COLUMNNAME_Sal_Min_Final = "Sal_Min_Final";

	/** Set Final Minimum Wage.
	  * Final Minimum Wage
	  */
	public void setSal_Min_Final (BigDecimal Sal_Min_Final);

	/** Get Final Minimum Wage.
	  * Final Minimum Wage
	  */
	public BigDecimal getSal_Min_Final();

    /** Column name Sal_Min_Inicial */
    public static final String COLUMNNAME_Sal_Min_Inicial = "Sal_Min_Inicial";

	/** Set Initial Minimum Wage.
	  * Initial Minimum Wage
	  */
	public void setSal_Min_Inicial (BigDecimal Sal_Min_Inicial);

	/** Get Initial Minimum Wage.
	  * Initial Minimum Wage
	  */
	public BigDecimal getSal_Min_Inicial();
}
