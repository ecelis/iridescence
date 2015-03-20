/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for I_C_Order
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_C_Order extends PO implements I_I_C_Order, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_C_Order (Properties ctx, int I_C_Order_ID, String trxName)
    {
      super (ctx, I_C_Order_ID, trxName);
      /** if (I_C_Order_ID == 0)
        {
			setI_C_Order_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_C_Order (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_I_C_Order[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Warehouse.
		@param Almacen Warehouse	  */
	public void setAlmacen (String Almacen)
	{
		set_Value (COLUMNNAME_Almacen, Almacen);
	}

	/** Get Warehouse.
		@return Warehouse	  */
	public String getAlmacen () 
	{
		return (String)get_Value(COLUMNNAME_Almacen);
	}

	/** Set CodigoTermino.
		@param CodigoTermino CodigoTermino	  */
	public void setCodigoTermino (String CodigoTermino)
	{
		set_Value (COLUMNNAME_CodigoTermino, CodigoTermino);
	}

	/** Get CodigoTermino.
		@return CodigoTermino	  */
	public String getCodigoTermino () 
	{
		return (String)get_Value(COLUMNNAME_CodigoTermino);
	}

	/** Set Buyer.
		@param Comprador Buyer	  */
	public void setComprador (String Comprador)
	{
		set_Value (COLUMNNAME_Comprador, Comprador);
	}

	/** Get Buyer.
		@return Buyer	  */
	public String getComprador () 
	{
		return (String)get_Value(COLUMNNAME_Comprador);
	}

	/** Set Discount 1.
		@param Descuento1 Discount 1	  */
	public void setDescuento1 (BigDecimal Descuento1)
	{
		set_Value (COLUMNNAME_Descuento1, Descuento1);
	}

	/** Get Discount 1.
		@return Discount 1	  */
	public BigDecimal getDescuento1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Descuento1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Discount 2.
		@param Descuento2 Discount 2	  */
	public void setDescuento2 (BigDecimal Descuento2)
	{
		set_Value (COLUMNNAME_Descuento2, Descuento2);
	}

	/** Get Discount 2.
		@return Discount 2	  */
	public BigDecimal getDescuento2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Descuento2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Discount 3.
		@param Descuento3 Discount 3	  */
	public void setDescuento3 (BigDecimal Descuento3)
	{
		set_Value (COLUMNNAME_Descuento3, Descuento3);
	}

	/** Get Discount 3.
		@return Discount 3	  */
	public BigDecimal getDescuento3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Descuento3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Shipment.
		@param Embarque Shipment	  */
	public void setEmbarque (String Embarque)
	{
		set_Value (COLUMNNAME_Embarque, Embarque);
	}

	/** Get Shipment.
		@return Shipment	  */
	public String getEmbarque () 
	{
		return (String)get_Value(COLUMNNAME_Embarque);
	}

	/** Set Is Purchase Order of Expense.
		@param EsOCDeGasto Is Purchase Order of Expense	  */
	public void setEsOCDeGasto (boolean EsOCDeGasto)
	{
		set_Value (COLUMNNAME_EsOCDeGasto, Boolean.valueOf(EsOCDeGasto));
	}

	/** Get Is Purchase Order of Expense.
		@return Is Purchase Order of Expense	  */
	public boolean isEsOCDeGasto () 
	{
		Object oo = get_Value(COLUMNNAME_EsOCDeGasto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set FechaOrden.
		@param FechaOrden FechaOrden	  */
	public void setFechaOrden (Timestamp FechaOrden)
	{
		set_Value (COLUMNNAME_FechaOrden, FechaOrden);
	}

	/** Get FechaOrden.
		@return FechaOrden	  */
	public Timestamp getFechaOrden () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaOrden);
	}

	/** Set FechaPromesa.
		@param FechaPromesa FechaPromesa	  */
	public void setFechaPromesa (Timestamp FechaPromesa)
	{
		set_Value (COLUMNNAME_FechaPromesa, FechaPromesa);
	}

	/** Get FechaPromesa.
		@return FechaPromesa	  */
	public Timestamp getFechaPromesa () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaPromesa);
	}

	/** Set I_C_Order_ID.
		@param I_C_Order_ID I_C_Order_ID	  */
	public void setI_C_Order_ID (int I_C_Order_ID)
	{
		if (I_C_Order_ID < 1)
			 throw new IllegalArgumentException ("I_C_Order_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_C_Order_ID, Integer.valueOf(I_C_Order_ID));
	}

	/** Get I_C_Order_ID.
		@return I_C_Order_ID	  */
	public int getI_C_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Coin.
		@param Moneda Coin	  */
	public void setMoneda (String Moneda)
	{
		set_Value (COLUMNNAME_Moneda, Moneda);
	}

	/** Get Coin.
		@return Coin	  */
	public String getMoneda () 
	{
		return (String)get_Value(COLUMNNAME_Moneda);
	}

	/** Set Order.
		@param Orden Order	  */
	public void setOrden (int Orden)
	{
		set_Value (COLUMNNAME_Orden, Integer.valueOf(Orden));
	}

	/** Get Order.
		@return Order	  */
	public int getOrden () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Orden);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Supplier.
		@param Proveedor Supplier	  */
	public void setProveedor (String Proveedor)
	{
		set_Value (COLUMNNAME_Proveedor, Proveedor);
	}

	/** Get Supplier.
		@return Supplier	  */
	public String getProveedor () 
	{
		return (String)get_Value(COLUMNNAME_Proveedor);
	}

	/** Set Requisicion.
		@param Requisicion Requisicion	  */
	public void setRequisicion (int Requisicion)
	{
		set_Value (COLUMNNAME_Requisicion, Integer.valueOf(Requisicion));
	}

	/** Get Requisicion.
		@return Requisicion	  */
	public int getRequisicion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Requisicion);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Operational Unit.
		@param Unidad_Operativa Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa)
	{
		set_Value (COLUMNNAME_Unidad_Operativa, Unidad_Operativa);
	}

	/** Get Operational Unit.
		@return Operational Unit	  */
	public String getUnidad_Operativa () 
	{
		return (String)get_Value(COLUMNNAME_Unidad_Operativa);
	}
}