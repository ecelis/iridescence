/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Rango_Punt_Clas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Rango_Punt_Clas 
{

    /** TableName=EXME_Rango_Punt_Clas */
    public static final String Table_Name = "EXME_Rango_Punt_Clas";

    /** AD_Table_ID=1000204 */
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

    /** Column name EXME_ClasCliente_ID */
    public static final String COLUMNNAME_EXME_ClasCliente_ID = "EXME_ClasCliente_ID";

	/** Set Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID);

	/** Get Classification and Customer	  */
	public int getEXME_ClasCliente_ID();

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException;

    /** Column name EXME_Rango_Punt_Clas_ID */
    public static final String COLUMNNAME_EXME_Rango_Punt_Clas_ID = "EXME_Rango_Punt_Clas_ID";

	/** Set Score Rank.
	  * Rank of Classification and Score
	  */
	public void setEXME_Rango_Punt_Clas_ID (int EXME_Rango_Punt_Clas_ID);

	/** Get Score Rank.
	  * Rank of Classification and Score
	  */
	public int getEXME_Rango_Punt_Clas_ID();

    /** Column name Puntaje_Final */
    public static final String COLUMNNAME_Puntaje_Final = "Puntaje_Final";

	/** Set Final Score.
	  * Final Score
	  */
	public void setPuntaje_Final (BigDecimal Puntaje_Final);

	/** Get Final Score.
	  * Final Score
	  */
	public BigDecimal getPuntaje_Final();

    /** Column name Puntaje_Inicial */
    public static final String COLUMNNAME_Puntaje_Inicial = "Puntaje_Inicial";

	/** Set Original Score.
	  * Original Score
	  */
	public void setPuntaje_Inicial (BigDecimal Puntaje_Inicial);

	/** Get Original Score.
	  * Original Score
	  */
	public BigDecimal getPuntaje_Inicial();
}
