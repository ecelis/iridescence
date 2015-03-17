/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SalidaGastoLineMA
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SalidaGastoLineMA 
{

    /** TableName=EXME_SalidaGastoLineMA */
    public static final String Table_Name = "EXME_SalidaGastoLineMA";

    /** AD_Table_ID=1200210 */
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

    /** Column name EXME_SalidaGastoLine_ID */
    public static final String COLUMNNAME_EXME_SalidaGastoLine_ID = "EXME_SalidaGastoLine_ID";

	/** Set Internal Use Inventory Line	  */
	public void setEXME_SalidaGastoLine_ID (int EXME_SalidaGastoLine_ID);

	/** Get Internal Use Inventory Line	  */
	public int getEXME_SalidaGastoLine_ID();

	public I_EXME_SalidaGastoLine getEXME_SalidaGastoLine() throws RuntimeException;

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

    /** Column name MovementQty */
    public static final String COLUMNNAME_MovementQty = "MovementQty";

	/** Set Movement Quantity.
	  * Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty);

	/** Get Movement Quantity.
	  * Quantity of a product moved.
	  */
	public BigDecimal getMovementQty();
}
