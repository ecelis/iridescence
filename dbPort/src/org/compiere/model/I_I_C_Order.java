/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_C_Order
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_C_Order 
{

    /** TableName=I_C_Order */
    public static final String Table_Name = "I_C_Order";

    /** AD_Table_ID=1200252 */
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

    /** Column name Almacen */
    public static final String COLUMNNAME_Almacen = "Almacen";

	/** Set Warehouse	  */
	public void setAlmacen (String Almacen);

	/** Get Warehouse	  */
	public String getAlmacen();

    /** Column name CodigoTermino */
    public static final String COLUMNNAME_CodigoTermino = "CodigoTermino";

	/** Set CodigoTermino	  */
	public void setCodigoTermino (String CodigoTermino);

	/** Get CodigoTermino	  */
	public String getCodigoTermino();

    /** Column name Comprador */
    public static final String COLUMNNAME_Comprador = "Comprador";

	/** Set Buyer	  */
	public void setComprador (String Comprador);

	/** Get Buyer	  */
	public String getComprador();

    /** Column name Descuento1 */
    public static final String COLUMNNAME_Descuento1 = "Descuento1";

	/** Set Discount 1	  */
	public void setDescuento1 (BigDecimal Descuento1);

	/** Get Discount 1	  */
	public BigDecimal getDescuento1();

    /** Column name Descuento2 */
    public static final String COLUMNNAME_Descuento2 = "Descuento2";

	/** Set Discount 2	  */
	public void setDescuento2 (BigDecimal Descuento2);

	/** Get Discount 2	  */
	public BigDecimal getDescuento2();

    /** Column name Descuento3 */
    public static final String COLUMNNAME_Descuento3 = "Descuento3";

	/** Set Discount 3	  */
	public void setDescuento3 (BigDecimal Descuento3);

	/** Get Discount 3	  */
	public BigDecimal getDescuento3();

    /** Column name Embarque */
    public static final String COLUMNNAME_Embarque = "Embarque";

	/** Set Shipment	  */
	public void setEmbarque (String Embarque);

	/** Get Shipment	  */
	public String getEmbarque();

    /** Column name EsOCDeGasto */
    public static final String COLUMNNAME_EsOCDeGasto = "EsOCDeGasto";

	/** Set Is Purchase Order of Expense	  */
	public void setEsOCDeGasto (boolean EsOCDeGasto);

	/** Get Is Purchase Order of Expense	  */
	public boolean isEsOCDeGasto();

    /** Column name FechaOrden */
    public static final String COLUMNNAME_FechaOrden = "FechaOrden";

	/** Set FechaOrden	  */
	public void setFechaOrden (Timestamp FechaOrden);

	/** Get FechaOrden	  */
	public Timestamp getFechaOrden();

    /** Column name FechaPromesa */
    public static final String COLUMNNAME_FechaPromesa = "FechaPromesa";

	/** Set FechaPromesa	  */
	public void setFechaPromesa (Timestamp FechaPromesa);

	/** Get FechaPromesa	  */
	public Timestamp getFechaPromesa();

    /** Column name I_C_Order_ID */
    public static final String COLUMNNAME_I_C_Order_ID = "I_C_Order_ID";

	/** Set I_C_Order_ID	  */
	public void setI_C_Order_ID (int I_C_Order_ID);

	/** Get I_C_Order_ID	  */
	public int getI_C_Order_ID();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Moneda */
    public static final String COLUMNNAME_Moneda = "Moneda";

	/** Set Coin	  */
	public void setMoneda (String Moneda);

	/** Get Coin	  */
	public String getMoneda();

    /** Column name Orden */
    public static final String COLUMNNAME_Orden = "Orden";

	/** Set Order	  */
	public void setOrden (int Orden);

	/** Get Order	  */
	public int getOrden();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Proveedor */
    public static final String COLUMNNAME_Proveedor = "Proveedor";

	/** Set Supplier	  */
	public void setProveedor (String Proveedor);

	/** Get Supplier	  */
	public String getProveedor();

    /** Column name Requisicion */
    public static final String COLUMNNAME_Requisicion = "Requisicion";

	/** Set Requisicion	  */
	public void setRequisicion (int Requisicion);

	/** Get Requisicion	  */
	public int getRequisicion();

    /** Column name Unidad_Operativa */
    public static final String COLUMNNAME_Unidad_Operativa = "Unidad_Operativa";

	/** Set Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa);

	/** Get Operational Unit	  */
	public String getUnidad_Operativa();
}
