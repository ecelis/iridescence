/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Papeleta
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Papeleta 
{

    /** TableName=EXME_Papeleta */
    public static final String Table_Name = "EXME_Papeleta";

    /** AD_Table_ID=1200545 */
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

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (int Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public int getCantidad();

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

    /** Column name EXME_DestinoRH_ID */
    public static final String COLUMNNAME_EXME_DestinoRH_ID = "EXME_DestinoRH_ID";

	/** Set Destiny	  */
	public void setEXME_DestinoRH_ID (int EXME_DestinoRH_ID);

	/** Get Destiny	  */
	public int getEXME_DestinoRH_ID();

    /** Column name EXME_Grado_ID */
    public static final String COLUMNNAME_EXME_Grado_ID = "EXME_Grado_ID";

	/** Set Grade.
	  * Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID);

	/** Get Grade.
	  * Militar Grade
	  */
	public int getEXME_Grado_ID();

    /** Column name EXME_Papeleta_ID */
    public static final String COLUMNNAME_EXME_Papeleta_ID = "EXME_Papeleta_ID";

	/** Set Ballot	  */
	public void setEXME_Papeleta_ID (int EXME_Papeleta_ID);

	/** Get Ballot	  */
	public int getEXME_Papeleta_ID();

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFecha_Fin();

    /** Column name Fecha_Ini */
    public static final String COLUMNNAME_Fecha_Ini = "Fecha_Ini";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFecha_Ini();

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
