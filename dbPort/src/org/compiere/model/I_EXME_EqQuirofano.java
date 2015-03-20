/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EqQuirofano
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EqQuirofano 
{

    /** TableName=EXME_EqQuirofano */
    public static final String Table_Name = "EXME_EqQuirofano";

    /** AD_Table_ID=1000043 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

    /** Column name EXME_EqQuirofano_ID */
    public static final String COLUMNNAME_EXME_EqQuirofano_ID = "EXME_EqQuirofano_ID";

	/** Set Surgical Room- Equipment.
	  * Surgical room - Equipment
	  */
	public void setEXME_EqQuirofano_ID (int EXME_EqQuirofano_ID);

	/** Get Surgical Room- Equipment.
	  * Surgical room - Equipment
	  */
	public int getEXME_EqQuirofano_ID();

    /** Column name EXME_Equipo_ID */
    public static final String COLUMNNAME_EXME_Equipo_ID = "EXME_Equipo_ID";

	/** Set Equipment.
	  * Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID);

	/** Get Equipment.
	  * Equipment
	  */
	public int getEXME_Equipo_ID();

    /** Column name EXME_Quirofano_ID */
    public static final String COLUMNNAME_EXME_Quirofano_ID = "EXME_Quirofano_ID";

	/** Set Surgery Room.
	  * Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID);

	/** Get Surgery Room.
	  * Surgey Room
	  */
	public int getEXME_Quirofano_ID();
}
