/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_Prescripcion_Det
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_Prescripcion_Det 
{

    /** TableName=EXME_MO_Prescripcion_Det */
    public static final String Table_Name = "EXME_MO_Prescripcion_Det";

    /** AD_Table_ID=1200381 */
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

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (int Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public int getCantidad();

    /** Column name Cant_Tomar */
    public static final String COLUMNNAME_Cant_Tomar = "Cant_Tomar";

	/** Set Number to take	  */
	public void setCant_Tomar (int Cant_Tomar);

	/** Get Number to take	  */
	public int getCant_Tomar();

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

    /** Column name EXME_MO_Prescripcion_Det_ID */
    public static final String COLUMNNAME_EXME_MO_Prescripcion_Det_ID = "EXME_MO_Prescripcion_Det_ID";

	/** Set Detail Prescription	  */
	public void setEXME_MO_Prescripcion_Det_ID (int EXME_MO_Prescripcion_Det_ID);

	/** Get Detail Prescription	  */
	public int getEXME_MO_Prescripcion_Det_ID();

    /** Column name EXME_MO_Prescripcion_ID */
    public static final String COLUMNNAME_EXME_MO_Prescripcion_ID = "EXME_MO_Prescripcion_ID";

	/** Set Prescription	  */
	public void setEXME_MO_Prescripcion_ID (int EXME_MO_Prescripcion_ID);

	/** Get Prescription	  */
	public int getEXME_MO_Prescripcion_ID();

	public I_EXME_MO_Prescripcion getEXME_MO_Prescripcion() throws RuntimeException;

    /** Column name Indicaciones */
    public static final String COLUMNNAME_Indicaciones = "Indicaciones";

	/** Set Indications	  */
	public void setIndicaciones (String Indicaciones);

	/** Get Indications	  */
	public String getIndicaciones();

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

    /** Column name Num_Dias */
    public static final String COLUMNNAME_Num_Dias = "Num_Dias";

	/** Set Number of days	  */
	public void setNum_Dias (int Num_Dias);

	/** Get Number of days	  */
	public int getNum_Dias();

    /** Column name Veces_Dia */
    public static final String COLUMNNAME_Veces_Dia = "Veces_Dia";

	/** Set times on a day	  */
	public void setVeces_Dia (int Veces_Dia);

	/** Get times on a day	  */
	public int getVeces_Dia();
}
