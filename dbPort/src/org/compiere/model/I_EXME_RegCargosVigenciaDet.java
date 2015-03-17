/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RegCargosVigenciaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RegCargosVigenciaDet 
{

    /** TableName=EXME_RegCargosVigenciaDet */
    public static final String Table_Name = "EXME_RegCargosVigenciaDet";

    /** AD_Table_ID=1200599 */
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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name EXME_RegCargosVigenciaDet_ID */
    public static final String COLUMNNAME_EXME_RegCargosVigenciaDet_ID = "EXME_RegCargosVigenciaDet_ID";

	/** Set Detail of Charges to Patient	  */
	public void setEXME_RegCargosVigenciaDet_ID (int EXME_RegCargosVigenciaDet_ID);

	/** Get Detail of Charges to Patient	  */
	public int getEXME_RegCargosVigenciaDet_ID();

    /** Column name EXME_RegCargosVigencia_ID */
    public static final String COLUMNNAME_EXME_RegCargosVigencia_ID = "EXME_RegCargosVigencia_ID";

	/** Set Officials Patient records Funded	  */
	public void setEXME_RegCargosVigencia_ID (int EXME_RegCargosVigencia_ID);

	/** Get Officials Patient records Funded	  */
	public int getEXME_RegCargosVigencia_ID();

	public I_EXME_RegCargosVigencia getEXME_RegCargosVigencia() throws RuntimeException;

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

    /** Column name QtyPendiente */
    public static final String COLUMNNAME_QtyPendiente = "QtyPendiente";

	/** Set Pending Quantity.
	  * Pending Quantity
	  */
	public void setQtyPendiente (BigDecimal QtyPendiente);

	/** Get Pending Quantity.
	  * Pending Quantity
	  */
	public BigDecimal getQtyPendiente();
}
