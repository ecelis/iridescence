/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DocMedsys
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_DocMedsys 
{

    /** TableName=EXME_DocMedsys */
    public static final String Table_Name = "EXME_DocMedsys";

    /** AD_Table_ID=1200041 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_DocMedsys_ID */
    public static final String COLUMNNAME_EXME_DocMedsys_ID = "EXME_DocMedsys_ID";

	/** Set Document of Medsys.
	  * Document of Medsys
	  */
	public void setEXME_DocMedsys_ID (int EXME_DocMedsys_ID);

	/** Get Document of Medsys.
	  * Document of Medsys
	  */
	public int getEXME_DocMedsys_ID();

    /** Column name EXME_Formato_ID */
    public static final String COLUMNNAME_EXME_Formato_ID = "EXME_Formato_ID";

	/** Set Format	  */
	public void setEXME_Formato_ID (int EXME_Formato_ID);

	/** Get Format	  */
	public int getEXME_Formato_ID();

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
}
