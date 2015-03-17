/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EquipoServ
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EquipoServ 
{

    /** TableName=EXME_EquipoServ */
    public static final String Table_Name = "EXME_EquipoServ";

    /** AD_Table_ID=1200338 */
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

    /** Column name EXME_EquipoServ_ID */
    public static final String COLUMNNAME_EXME_EquipoServ_ID = "EXME_EquipoServ_ID";

	/** Set Service Equipment	  */
	public void setEXME_EquipoServ_ID (int EXME_EquipoServ_ID);

	/** Get Service Equipment	  */
	public int getEXME_EquipoServ_ID();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();
}
