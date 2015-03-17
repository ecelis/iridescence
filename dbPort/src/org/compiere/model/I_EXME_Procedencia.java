/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Procedencia
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Procedencia 
{

    /** TableName=EXME_Procedencia */
    public static final String Table_Name = "EXME_Procedencia";

    /** AD_Table_ID=1000206 */
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

    /** Column name EXME_Procedencia_ID */
    public static final String COLUMNNAME_EXME_Procedencia_ID = "EXME_Procedencia_ID";

	/** Set Provenance.
	  * Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID);

	/** Get Provenance.
	  * Provenance
	  */
	public int getEXME_Procedencia_ID();

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
