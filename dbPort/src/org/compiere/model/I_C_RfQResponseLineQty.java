/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_RfQResponseLineQty
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_RfQResponseLineQty 
{

    /** TableName=C_RfQResponseLineQty */
    public static final String Table_Name = "C_RfQResponseLineQty";

    /** AD_Table_ID=672 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name C_RfQLineQty_ID */
    public static final String COLUMNNAME_C_RfQLineQty_ID = "C_RfQLineQty_ID";

	/** Set RfQ Line Quantity.
	  * Request for Quotation Line Quantity
	  */
	public void setC_RfQLineQty_ID (int C_RfQLineQty_ID);

	/** Get RfQ Line Quantity.
	  * Request for Quotation Line Quantity
	  */
	public int getC_RfQLineQty_ID();

	public I_C_RfQLineQty getC_RfQLineQty() throws RuntimeException;

    /** Column name C_RfQResponseLine_ID */
    public static final String COLUMNNAME_C_RfQResponseLine_ID = "C_RfQResponseLine_ID";

	/** Set RfQ Response Line.
	  * Request for Quotation Response Line
	  */
	public void setC_RfQResponseLine_ID (int C_RfQResponseLine_ID);

	/** Get RfQ Response Line.
	  * Request for Quotation Response Line
	  */
	public int getC_RfQResponseLine_ID();

	public I_C_RfQResponseLine getC_RfQResponseLine() throws RuntimeException;

    /** Column name C_RfQResponseLineQty_ID */
    public static final String COLUMNNAME_C_RfQResponseLineQty_ID = "C_RfQResponseLineQty_ID";

	/** Set RfQ Response Line Qty.
	  * Request for Quotation Response Line Quantity
	  */
	public void setC_RfQResponseLineQty_ID (int C_RfQResponseLineQty_ID);

	/** Get RfQ Response Line Qty.
	  * Request for Quotation Response Line Quantity
	  */
	public int getC_RfQResponseLineQty_ID();

    /** Column name Discount */
    public static final String COLUMNNAME_Discount = "Discount";

	/** Set Discount %.
	  * Discount in percent
	  */
	public void setDiscount (BigDecimal Discount);

	/** Get Discount %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount();

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";

	/** Set Price.
	  * Price
	  */
	public void setPrice (BigDecimal Price);

	/** Get Price.
	  * Price
	  */
	public BigDecimal getPrice();

    /** Column name Ranking */
    public static final String COLUMNNAME_Ranking = "Ranking";

	/** Set Ranking.
	  * Relative Rank Number
	  */
	public void setRanking (int Ranking);

	/** Get Ranking.
	  * Relative Rank Number
	  */
	public int getRanking();
}
