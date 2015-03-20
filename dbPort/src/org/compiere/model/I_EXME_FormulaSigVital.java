/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FormulaSigVital
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_FormulaSigVital 
{

    /** TableName=EXME_FormulaSigVital */
    public static final String Table_Name = "EXME_FormulaSigVital";

    /** AD_Table_ID=1200362 */
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

    /** Column name EXME_FormulaSigVital_ID */
    public static final String COLUMNNAME_EXME_FormulaSigVital_ID = "EXME_FormulaSigVital_ID";

	/** Set Vital Signs Formula	  */
	public void setEXME_FormulaSigVital_ID (int EXME_FormulaSigVital_ID);

	/** Get Vital Signs Formula	  */
	public int getEXME_FormulaSigVital_ID();

    /** Column name Formula */
    public static final String COLUMNNAME_Formula = "Formula";

	/** Set Formule.
	  * Substance Formule
	  */
	public void setFormula (String Formula);

	/** Get Formule.
	  * Substance Formule
	  */
	public String getFormula();

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
