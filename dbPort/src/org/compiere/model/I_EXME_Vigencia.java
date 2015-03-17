/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Vigencia
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Vigencia 
{

    /** TableName=EXME_Vigencia */
    public static final String Table_Name = "EXME_Vigencia";

    /** AD_Table_ID=1000197 */
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

    /** Column name Anios_Vigencia */
    public static final String COLUMNNAME_Anios_Vigencia = "Anios_Vigencia";

	/** Set Years of Vigency.
	  * Years of Vigency
	  */
	public void setAnios_Vigencia (int Anios_Vigencia);

	/** Get Years of Vigency.
	  * Years of Vigency
	  */
	public int getAnios_Vigencia();

    /** Column name EXME_Vigencia_ID */
    public static final String COLUMNNAME_EXME_Vigencia_ID = "EXME_Vigencia_ID";

	/** Set Validity.
	  * Duration in Years for the Study of a Patient
	  */
	public void setEXME_Vigencia_ID (int EXME_Vigencia_ID);

	/** Get Validity.
	  * Duration in Years for the Study of a Patient
	  */
	public int getEXME_Vigencia_ID();

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
