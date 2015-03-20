/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Grupo_Edades
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Grupo_Edades 
{

    /** TableName=EXME_Grupo_Edades */
    public static final String Table_Name = "EXME_Grupo_Edades";

    /** AD_Table_ID=1200541 */
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

    /** Column name EdadFin */
    public static final String COLUMNNAME_EdadFin = "EdadFin";

	/** Set To Age	  */
	public void setEdadFin (BigDecimal EdadFin);

	/** Get To Age	  */
	public BigDecimal getEdadFin();

    /** Column name EdadIni */
    public static final String COLUMNNAME_EdadIni = "EdadIni";

	/** Set Age Initial	  */
	public void setEdadIni (BigDecimal EdadIni);

	/** Get Age Initial	  */
	public BigDecimal getEdadIni();

    /** Column name EXME_Grupo_Edades_ID */
    public static final String COLUMNNAME_EXME_Grupo_Edades_ID = "EXME_Grupo_Edades_ID";

	/** Set Age Group	  */
	public void setEXME_Grupo_Edades_ID (int EXME_Grupo_Edades_ID);

	/** Get Age Group	  */
	public int getEXME_Grupo_Edades_ID();

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
