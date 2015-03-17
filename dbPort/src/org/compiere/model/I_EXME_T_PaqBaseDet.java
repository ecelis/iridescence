/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_PaqBaseDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_PaqBaseDet 
{

    /** TableName=EXME_T_PaqBaseDet */
    public static final String Table_Name = "EXME_T_PaqBaseDet";

    /** AD_Table_ID=1200187 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException;

    /** Column name EXME_T_PaqBaseDet_ID */
    public static final String COLUMNNAME_EXME_T_PaqBaseDet_ID = "EXME_T_PaqBaseDet_ID";

	/** Set Temporal Table of Base Package Detail.
	  * Temporal Table of Base Package Detail
	  */
	public void setEXME_T_PaqBaseDet_ID (int EXME_T_PaqBaseDet_ID);

	/** Get Temporal Table of Base Package Detail.
	  * Temporal Table of Base Package Detail
	  */
	public int getEXME_T_PaqBaseDet_ID();

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

    /** Column name Procesado */
    public static final String COLUMNNAME_Procesado = "Procesado";

	/** Set Processed	  */
	public void setProcesado (boolean Procesado);

	/** Get Processed	  */
	public boolean isProcesado();

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

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();
}
