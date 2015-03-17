/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AreaConfigurador
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_AreaConfigurador 
{

    /** TableName=EXME_AreaConfigurador */
    public static final String Table_Name = "EXME_AreaConfigurador";

    /** AD_Table_ID=1201184 */
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

    /** Column name EXME_AreaConfigurador_ID */
    public static final String COLUMNNAME_EXME_AreaConfigurador_ID = "EXME_AreaConfigurador_ID";

	/** Set Configurator Area	  */
	public void setEXME_AreaConfigurador_ID (int EXME_AreaConfigurador_ID);

	/** Get Configurator Area	  */
	public int getEXME_AreaConfigurador_ID();

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Area.
	  * Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Area.
	  * Area
	  */
	public int getEXME_Area_ID();

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();
}
