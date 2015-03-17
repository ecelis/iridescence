/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ServPublico
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ServPublico 
{

    /** TableName=EXME_ServPublico */
    public static final String Table_Name = "EXME_ServPublico";

    /** AD_Table_ID=1000203 */
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

    /** Column name EXME_ServPublico_ID */
    public static final String COLUMNNAME_EXME_ServPublico_ID = "EXME_ServPublico_ID";

	/** Set Public Services.
	  * Public Services
	  */
	public void setEXME_ServPublico_ID (int EXME_ServPublico_ID);

	/** Get Public Services.
	  * Public Services
	  */
	public int getEXME_ServPublico_ID();

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

    /** Column name Pts */
    public static final String COLUMNNAME_Pts = "Pts";

	/** Set Points.
	  * Points
	  */
	public void setPts (int Pts);

	/** Get Points.
	  * Points
	  */
	public int getPts();

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
