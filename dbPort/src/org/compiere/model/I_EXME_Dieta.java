/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Dieta
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Dieta 
{

    /** TableName=EXME_Dieta */
    public static final String Table_Name = "EXME_Dieta";

    /** AD_Table_ID=1200140 */
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

    /** Column name EXME_Dieta_ID */
    public static final String COLUMNNAME_EXME_Dieta_ID = "EXME_Dieta_ID";

	/** Set Diet	  */
	public void setEXME_Dieta_ID (int EXME_Dieta_ID);

	/** Get Diet	  */
	public int getEXME_Dieta_ID();

    /** Column name IsSubDiet */
    public static final String COLUMNNAME_IsSubDiet = "IsSubDiet";

	/** Set Sub diet	  */
	public void setIsSubDiet (boolean IsSubDiet);

	/** Get Sub diet	  */
	public boolean isSubDiet();

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
