/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigTrasplante
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigTrasplante 
{

    /** TableName=EXME_ConfigTrasplante */
    public static final String Table_Name = "EXME_ConfigTrasplante";

    /** AD_Table_ID=1200274 */
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

    /** Column name EXME_ConfigTrasplante_ID */
    public static final String COLUMNNAME_EXME_ConfigTrasplante_ID = "EXME_ConfigTrasplante_ID";

	/** Set Transplant Configuration	  */
	public void setEXME_ConfigTrasplante_ID (int EXME_ConfigTrasplante_ID);

	/** Get Transplant Configuration	  */
	public int getEXME_ConfigTrasplante_ID();

    /** Column name TiempoEspera */
    public static final String COLUMNNAME_TiempoEspera = "TiempoEspera";

	/** Set Waiting Time.
	  * Waiting Time (in months)
	  */
	public void setTiempoEspera (int TiempoEspera);

	/** Get Waiting Time.
	  * Waiting Time (in months)
	  */
	public int getTiempoEspera();
}
