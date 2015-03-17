/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CuestTitulo
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_CuestTitulo 
{

    /** TableName=EXME_CuestTitulo */
    public static final String Table_Name = "EXME_CuestTitulo";

    /** AD_Table_ID=1201158 */
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

    /** Column name EXME_Cuestionario_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ID = "EXME_Cuestionario_ID";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public int getEXME_Cuestionario_ID();

    /** Column name EXME_CuestTitulo_ID */
    public static final String COLUMNNAME_EXME_CuestTitulo_ID = "EXME_CuestTitulo_ID";

	/** Set Title	  */
	public void setEXME_CuestTitulo_ID (int EXME_CuestTitulo_ID);

	/** Get Title	  */
	public int getEXME_CuestTitulo_ID();

    /** Column name IsOBXReviewed */
    public static final String COLUMNNAME_IsOBXReviewed = "IsOBXReviewed";

	/** Set Is OBX Reviewed.
	  * Is OBX Reviewed
	  */
	public void setIsOBXReviewed (boolean IsOBXReviewed);

	/** Get Is OBX Reviewed.
	  * Is OBX Reviewed
	  */
	public boolean isOBXReviewed();

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
