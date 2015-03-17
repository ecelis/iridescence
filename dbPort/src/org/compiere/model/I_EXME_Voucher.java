/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Voucher
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Voucher 
{

    /** TableName=EXME_Voucher */
    public static final String Table_Name = "EXME_Voucher";

    /** AD_Table_ID=1200156 */
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

    /** Column name EXME_Voucher_ID */
    public static final String COLUMNNAME_EXME_Voucher_ID = "EXME_Voucher_ID";

	/** Set Voucher	  */
	public void setEXME_Voucher_ID (int EXME_Voucher_ID);

	/** Get Voucher	  */
	public int getEXME_Voucher_ID();

    /** Column name MetodoPago */
    public static final String COLUMNNAME_MetodoPago = "MetodoPago";

	/** Set Payment method.
	  * Payment method
	  */
	public void setMetodoPago (String MetodoPago);

	/** Get Payment method.
	  * Payment method
	  */
	public String getMetodoPago();

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
