/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigSeguridad
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigSeguridad 
{

    /** TableName=EXME_ConfigSeguridad */
    public static final String Table_Name = "EXME_ConfigSeguridad";

    /** AD_Table_ID=1200626 */
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

    /** Column name Dias */
    public static final String COLUMNNAME_Dias = "Dias";

	/** Set Days	  */
	public void setDias (int Dias);

	/** Get Days	  */
	public int getDias();

    /** Column name DiasCambiaPwd */
    public static final String COLUMNNAME_DiasCambiaPwd = "DiasCambiaPwd";

	/** Set Days to request password change.
	  * Days to request password change
	  */
	public void setDiasCambiaPwd (int DiasCambiaPwd);

	/** Get Days to request password change.
	  * Days to request password change
	  */
	public int getDiasCambiaPwd();

    /** Column name EXME_ConfigSeguridad_ID */
    public static final String COLUMNNAME_EXME_ConfigSeguridad_ID = "EXME_ConfigSeguridad_ID";

	/** Set Security Settings	  */
	public void setEXME_ConfigSeguridad_ID (int EXME_ConfigSeguridad_ID);

	/** Get Security Settings	  */
	public int getEXME_ConfigSeguridad_ID();

    /** Column name Intentos */
    public static final String COLUMNNAME_Intentos = "Intentos";

	/** Set Attempts	  */
	public void setIntentos (int Intentos);

	/** Get Attempts	  */
	public int getIntentos();

    /** Column name TimeOut */
    public static final String COLUMNNAME_TimeOut = "TimeOut";

	/** Set Time Out	  */
	public void setTimeOut (int TimeOut);

	/** Get Time Out	  */
	public int getTimeOut();
}
