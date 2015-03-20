/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigINER
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigINER 
{

    /** TableName=EXME_ConfigINER */
    public static final String Table_Name = "EXME_ConfigINER";

    /** AD_Table_ID=1200262 */
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

    /** Column name EXME_ConfigINER_ID */
    public static final String COLUMNNAME_EXME_ConfigINER_ID = "EXME_ConfigINER_ID";

	/** Set Configuration INER	  */
	public void setEXME_ConfigINER_ID (int EXME_ConfigINER_ID);

	/** Get Configuration INER	  */
	public int getEXME_ConfigINER_ID();

    /** Column name NotasMedicas */
    public static final String COLUMNNAME_NotasMedicas = "NotasMedicas";

	/** Set Medical Record	  */
	public void setNotasMedicas (boolean NotasMedicas);

	/** Get Medical Record	  */
	public boolean isNotasMedicas();
}
