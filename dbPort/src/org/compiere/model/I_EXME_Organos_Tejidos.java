/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Organos_Tejidos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Organos_Tejidos 
{

    /** TableName=EXME_Organos_Tejidos */
    public static final String Table_Name = "EXME_Organos_Tejidos";

    /** AD_Table_ID=1200861 */
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

    /** Column name Comentario */
    public static final String COLUMNNAME_Comentario = "Comentario";

	/** Set Comment	  */
	public void setComentario (String Comentario);

	/** Get Comment	  */
	public String getComentario();

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

    /** Column name EXME_Organos_Tejidos_ID */
    public static final String COLUMNNAME_EXME_Organos_Tejidos_ID = "EXME_Organos_Tejidos_ID";

	/** Set Organs/Tissues .
	  * ID de table organs and tissues
	  */
	public void setEXME_Organos_Tejidos_ID (int EXME_Organos_Tejidos_ID);

	/** Get Organs/Tissues .
	  * ID de table organs and tissues
	  */
	public int getEXME_Organos_Tejidos_ID();

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
