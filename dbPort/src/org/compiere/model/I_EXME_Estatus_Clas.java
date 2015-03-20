/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Estatus_Clas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Estatus_Clas 
{

    /** TableName=EXME_Estatus_Clas */
    public static final String Table_Name = "EXME_Estatus_Clas";

    /** AD_Table_ID=1000195 */
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

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

    /** Column name EXME_Estatus_Clas_ID */
    public static final String COLUMNNAME_EXME_Estatus_Clas_ID = "EXME_Estatus_Clas_ID";

	/** Set Status.
	  * Status
	  */
	public void setEXME_Estatus_Clas_ID (int EXME_Estatus_Clas_ID);

	/** Get Status.
	  * Status
	  */
	public int getEXME_Estatus_Clas_ID();
}
