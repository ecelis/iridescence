/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_PaqCtaPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_PaqCtaPac 
{

    /** TableName=EXME_T_PaqCtaPac */
    public static final String Table_Name = "EXME_T_PaqCtaPac";

    /** AD_Table_ID=1200213 */
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

    /** Column name CostoExtras */
    public static final String COLUMNNAME_CostoExtras = "CostoExtras";

	/** Set Cost of the Extras.
	  * Cost of the Extras
	  */
	public void setCostoExtras (BigDecimal CostoExtras);

	/** Get Cost of the Extras.
	  * Cost of the Extras
	  */
	public BigDecimal getCostoExtras();

    /** Column name CostoPaq */
    public static final String COLUMNNAME_CostoPaq = "CostoPaq";

	/** Set Cost in the Package.
	  * Cost in the Package
	  */
	public void setCostoPaq (BigDecimal CostoPaq);

	/** Get Cost in the Package.
	  * Cost in the Package
	  */
	public BigDecimal getCostoPaq();

    /** Column name DifferenceQty */
    public static final String COLUMNNAME_DifferenceQty = "DifferenceQty";

	/** Set Difference.
	  * Difference Quantity
	  */
	public void setDifferenceQty (BigDecimal DifferenceQty);

	/** Get Difference.
	  * Difference Quantity
	  */
	public BigDecimal getDifferenceQty();

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

    /** Column name EXME_T_PaqCtaPac_ID */
    public static final String COLUMNNAME_EXME_T_PaqCtaPac_ID = "EXME_T_PaqCtaPac_ID";

	/** Set Package Vs Patient Account.
	  * Package Vs Patient Account
	  */
	public void setEXME_T_PaqCtaPac_ID (int EXME_T_PaqCtaPac_ID);

	/** Get Package Vs Patient Account.
	  * Package Vs Patient Account
	  */
	public int getEXME_T_PaqCtaPac_ID();

    /** Column name Extras */
    public static final String COLUMNNAME_Extras = "Extras";

	/** Set Extras.
	  * Extras
	  */
	public void setExtras (BigDecimal Extras);

	/** Get Extras.
	  * Extras
	  */
	public BigDecimal getExtras();

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

    /** Column name QtyAplied */
    public static final String COLUMNNAME_QtyAplied = "QtyAplied";

	/** Set QtyAplied	  */
	public void setQtyAplied (BigDecimal QtyAplied);

	/** Get QtyAplied	  */
	public BigDecimal getQtyAplied();

    /** Column name QtyPaquete */
    public static final String COLUMNNAME_QtyPaquete = "QtyPaquete";

	/** Set Package Quantity.
	  * Package Quantity
	  */
	public void setQtyPaquete (BigDecimal QtyPaquete);

	/** Get Package Quantity.
	  * Package Quantity
	  */
	public BigDecimal getQtyPaquete();

    /** Column name QtySustituto */
    public static final String COLUMNNAME_QtySustituto = "QtySustituto";

	/** Set Sustitute Quantity.
	  * Sustitute Quantity
	  */
	public void setQtySustituto (BigDecimal QtySustituto);

	/** Get Sustitute Quantity.
	  * Sustitute Quantity
	  */
	public BigDecimal getQtySustituto();

    /** Column name Substitute_ID */
    public static final String COLUMNNAME_Substitute_ID = "Substitute_ID";

	/** Set Substitute.
	  * Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID);

	/** Get Substitute.
	  * Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID();

    /** Column name UsadoCostoPaq */
    public static final String COLUMNNAME_UsadoCostoPaq = "UsadoCostoPaq";

	/** Set Used Package Cost Amount.
	  * Used Package Cost Amount
	  */
	public void setUsadoCostoPaq (BigDecimal UsadoCostoPaq);

	/** Get Used Package Cost Amount.
	  * Used Package Cost Amount
	  */
	public BigDecimal getUsadoCostoPaq();

    /** Column name UsadoUtilidad */
    public static final String COLUMNNAME_UsadoUtilidad = "UsadoUtilidad";

	/** Set Used in Utility.
	  * Used in Utility
	  */
	public void setUsadoUtilidad (BigDecimal UsadoUtilidad);

	/** Get Used in Utility.
	  * Used in Utility
	  */
	public BigDecimal getUsadoUtilidad();

    /** Column name UsadoVenta */
    public static final String COLUMNNAME_UsadoVenta = "UsadoVenta";

	/** Set Used in Sale Amount.
	  * Used in Sale Amount
	  */
	public void setUsadoVenta (BigDecimal UsadoVenta);

	/** Get Used in Sale Amount.
	  * Used in Sale Amount
	  */
	public BigDecimal getUsadoVenta();

    /** Column name Utilidad */
    public static final String COLUMNNAME_Utilidad = "Utilidad";

	/** Set Utility.
	  * Utility
	  */
	public void setUtilidad (BigDecimal Utilidad);

	/** Get Utility.
	  * Utility
	  */
	public BigDecimal getUtilidad();

    /** Column name VentaPaq */
    public static final String COLUMNNAME_VentaPaq = "VentaPaq";

	/** Set Package Sale Amount.
	  * Package Sale Amount
	  */
	public void setVentaPaq (BigDecimal VentaPaq);

	/** Get Package Sale Amount.
	  * Package Sale Amount
	  */
	public BigDecimal getVentaPaq();
}
