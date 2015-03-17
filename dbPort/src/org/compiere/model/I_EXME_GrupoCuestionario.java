/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GrupoCuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_GrupoCuestionario 
{

    /** TableName=EXME_GrupoCuestionario */
    public static final String Table_Name = "EXME_GrupoCuestionario";

    /** AD_Table_ID=1201234 */
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

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set Form Group.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get Form Group.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

    /** Column name MODIFIED_BY */
    public static final String COLUMNNAME_MODIFIED_BY = "MODIFIED_BY";

	/** Set MODIFIED_BY	  */
	public void setMODIFIED_BY (int MODIFIED_BY);

	/** Get MODIFIED_BY	  */
	public int getMODIFIED_BY();

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
