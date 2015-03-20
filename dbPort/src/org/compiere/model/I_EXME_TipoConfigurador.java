/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoConfigurador
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_TipoConfigurador 
{

    /** TableName=EXME_TipoConfigurador */
    public static final String Table_Name = "EXME_TipoConfigurador";

    /** AD_Table_ID=1200900 */
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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_Configurador_ID */
    public static final String COLUMNNAME_EXME_Configurador_ID = "EXME_Configurador_ID";

	/** Set Configurator	  */
	public void setEXME_Configurador_ID (int EXME_Configurador_ID);

	/** Get Configurator	  */
	public int getEXME_Configurador_ID();

	public I_EXME_Configurador getEXME_Configurador() throws RuntimeException;

    /** Column name EXME_EULA_ID */
    public static final String COLUMNNAME_EXME_EULA_ID = "EXME_EULA_ID";

	/** Set End User Agreement License.
	  * The End User Agreement License
	  */
	public void setEXME_EULA_ID (int EXME_EULA_ID);

	/** Get End User Agreement License.
	  * The End User Agreement License
	  */
	public int getEXME_EULA_ID();

	public I_EXME_EULA getEXME_EULA() throws RuntimeException;

    /** Column name EXME_TipoConfigurador_ID */
    public static final String COLUMNNAME_EXME_TipoConfigurador_ID = "EXME_TipoConfigurador_ID";

	/** Set Configuration Type	  */
	public void setEXME_TipoConfigurador_ID (int EXME_TipoConfigurador_ID);

	/** Get Configuration Type	  */
	public int getEXME_TipoConfigurador_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
