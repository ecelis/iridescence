/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Descuentos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Descuentos 
{

    /** TableName=EXME_Descuentos */
    public static final String Table_Name = "EXME_Descuentos";

    /** AD_Table_ID=1200296 */
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

    /** Column name EXME_Descuentos_ID */
    public static final String COLUMNNAME_EXME_Descuentos_ID = "EXME_Descuentos_ID";

	/** Set Discount	  */
	public void setEXME_Descuentos_ID (int EXME_Descuentos_ID);

	/** Get Discount	  */
	public int getEXME_Descuentos_ID();

    /** Column name Max_Desc */
    public static final String COLUMNNAME_Max_Desc = "Max_Desc";

	/** Set Maximum Discount	  */
	public void setMax_Desc (BigDecimal Max_Desc);

	/** Get Maximum Discount	  */
	public BigDecimal getMax_Desc();

    /** Column name Min_Desc */
    public static final String COLUMNNAME_Min_Desc = "Min_Desc";

	/** Set Minimum Discount	  */
	public void setMin_Desc (BigDecimal Min_Desc);

	/** Get Minimum Discount	  */
	public BigDecimal getMin_Desc();

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
