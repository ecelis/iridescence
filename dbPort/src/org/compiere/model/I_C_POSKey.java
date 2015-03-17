/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_POSKey
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_POSKey 
{

    /** TableName=C_POSKey */
    public static final String Table_Name = "C_POSKey";

    /** AD_Table_ID=750 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name AD_PrintColor_ID */
    public static final String COLUMNNAME_AD_PrintColor_ID = "AD_PrintColor_ID";

	/** Set Print Color.
	  * Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID);

	/** Get Print Color.
	  * Color used for printing and display
	  */
	public int getAD_PrintColor_ID();

	public I_AD_PrintColor getAD_PrintColor() throws RuntimeException;

    /** Column name C_POSKey_ID */
    public static final String COLUMNNAME_C_POSKey_ID = "C_POSKey_ID";

	/** Set POS Key.
	  * POS Function Key
	  */
	public void setC_POSKey_ID (int C_POSKey_ID);

	/** Get POS Key.
	  * POS Function Key
	  */
	public int getC_POSKey_ID();

    /** Column name C_POSKeyLayout_ID */
    public static final String COLUMNNAME_C_POSKeyLayout_ID = "C_POSKeyLayout_ID";

	/** Set POS Key Layout.
	  * POS Function Key Layout
	  */
	public void setC_POSKeyLayout_ID (int C_POSKeyLayout_ID);

	/** Get POS Key Layout.
	  * POS Function Key Layout
	  */
	public int getC_POSKeyLayout_ID();

	public I_C_POSKeyLayout getC_POSKeyLayout() throws RuntimeException;

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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
