/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoConfiguradorDet
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_TipoConfiguradorDet 
{

    /** TableName=EXME_TipoConfiguradorDet */
    public static final String Table_Name = "EXME_TipoConfiguradorDet";

    /** AD_Table_ID=1200901 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_Configurador_ID */
    public static final String COLUMNNAME_EXME_Configurador_ID = "EXME_Configurador_ID";

	/** Set Configurator	  */
	public void setEXME_Configurador_ID (int EXME_Configurador_ID);

	/** Get Configurator	  */
	public int getEXME_Configurador_ID();

	public I_EXME_Configurador getEXME_Configurador() throws RuntimeException;

    /** Column name EXME_TipoConfiguradorDet_ID */
    public static final String COLUMNNAME_EXME_TipoConfiguradorDet_ID = "EXME_TipoConfiguradorDet_ID";

	/** Set Configuration Type Detail	  */
	public void setEXME_TipoConfiguradorDet_ID (int EXME_TipoConfiguradorDet_ID);

	/** Get Configuration Type Detail	  */
	public int getEXME_TipoConfiguradorDet_ID();

    /** Column name EXME_TipoConfigurador_ID */
    public static final String COLUMNNAME_EXME_TipoConfigurador_ID = "EXME_TipoConfigurador_ID";

	/** Set Configuration Type	  */
	public void setEXME_TipoConfigurador_ID (int EXME_TipoConfigurador_ID);

	/** Get Configuration Type	  */
	public int getEXME_TipoConfigurador_ID();

	public I_EXME_TipoConfigurador getEXME_TipoConfigurador() throws RuntimeException;
}
