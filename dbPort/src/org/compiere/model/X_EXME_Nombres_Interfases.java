/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Nombres_Interfases
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Nombres_Interfases extends PO implements I_EXME_Nombres_Interfases, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Nombres_Interfases (Properties ctx, int EXME_Nombres_Interfases_ID, String trxName)
    {
      super (ctx, EXME_Nombres_Interfases_ID, trxName);
      /** if (EXME_Nombres_Interfases_ID == 0)
        {
			setEXME_Nombres_Interfases_ID (0);
			setEXME_Ruta_Interfases_ID (0);
			setHora (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Nombres_Interfases (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Nombres_Interfases[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set General Articles.
		@param ArticulosGeneral 
		General Articles
	  */
	public void setArticulosGeneral (String ArticulosGeneral)
	{
		set_Value (COLUMNNAME_ArticulosGeneral, ArticulosGeneral);
	}

	/** Get General Articles.
		@return General Articles
	  */
	public String getArticulosGeneral () 
	{
		return (String)get_Value(COLUMNNAME_ArticulosGeneral);
	}

	/** Set Articles Price Levels.
		@param ArticulosNivelesPrecio 
		Articles Price Levels
	  */
	public void setArticulosNivelesPrecio (String ArticulosNivelesPrecio)
	{
		set_Value (COLUMNNAME_ArticulosNivelesPrecio, ArticulosNivelesPrecio);
	}

	/** Get Articles Price Levels.
		@return Articles Price Levels
	  */
	public String getArticulosNivelesPrecio () 
	{
		return (String)get_Value(COLUMNNAME_ArticulosNivelesPrecio);
	}

	/** Set Clients.
		@param Clientes 
		Clients
	  */
	public void setClientes (String Clientes)
	{
		set_Value (COLUMNNAME_Clientes, Clientes);
	}

	/** Get Clients.
		@return Clients
	  */
	public String getClientes () 
	{
		return (String)get_Value(COLUMNNAME_Clientes);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Invoice Discount.
		@param DescuentosFacturar Invoice Discount	  */
	public void setDescuentosFacturar (String DescuentosFacturar)
	{
		set_Value (COLUMNNAME_DescuentosFacturar, DescuentosFacturar);
	}

	/** Get Invoice Discount.
		@return Invoice Discount	  */
	public String getDescuentosFacturar () 
	{
		return (String)get_Value(COLUMNNAME_DescuentosFacturar);
	}

	/** Set Purchase Order Devolution.
		@param DevolucionOrdenCompra Purchase Order Devolution	  */
	public void setDevolucionOrdenCompra (String DevolucionOrdenCompra)
	{
		set_Value (COLUMNNAME_DevolucionOrdenCompra, DevolucionOrdenCompra);
	}

	/** Get Purchase Order Devolution.
		@return Purchase Order Devolution	  */
	public String getDevolucionOrdenCompra () 
	{
		return (String)get_Value(COLUMNNAME_DevolucionOrdenCompra);
	}

	/** Set Interface Name.
		@param EXME_Nombres_Interfases_ID 
		Interface Name
	  */
	public void setEXME_Nombres_Interfases_ID (int EXME_Nombres_Interfases_ID)
	{
		if (EXME_Nombres_Interfases_ID < 1)
			 throw new IllegalArgumentException ("EXME_Nombres_Interfases_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Nombres_Interfases_ID, Integer.valueOf(EXME_Nombres_Interfases_ID));
	}

	/** Get Interface Name.
		@return Interface Name
	  */
	public int getEXME_Nombres_Interfases_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Nombres_Interfases_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Ruta_Interfases getEXME_Ruta_Interfases() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ruta_Interfases.Table_Name);
        I_EXME_Ruta_Interfases result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ruta_Interfases)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ruta_Interfases_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Interfaces Path.
		@param EXME_Ruta_Interfases_ID Interfaces Path	  */
	public void setEXME_Ruta_Interfases_ID (int EXME_Ruta_Interfases_ID)
	{
		if (EXME_Ruta_Interfases_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ruta_Interfases_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Ruta_Interfases_ID, Integer.valueOf(EXME_Ruta_Interfases_ID));
	}

	/** Get Interfaces Path.
		@return Interfaces Path	  */
	public int getEXME_Ruta_Interfases_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ruta_Interfases_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoices and Credit Notes.
		@param FacturasNotasCredito Invoices and Credit Notes	  */
	public void setFacturasNotasCredito (String FacturasNotasCredito)
	{
		set_Value (COLUMNNAME_FacturasNotasCredito, FacturasNotasCredito);
	}

	/** Get Invoices and Credit Notes.
		@return Invoices and Credit Notes	  */
	public String getFacturasNotasCredito () 
	{
		return (String)get_Value(COLUMNNAME_FacturasNotasCredito);
	}

	/** Set Hour.
		@param Hora 
		Hour
	  */
	public void setHora (String Hora)
	{
		if (Hora == null)
			throw new IllegalArgumentException ("Hora is mandatory.");
		set_Value (COLUMNNAME_Hora, Hora);
	}

	/** Get Hour.
		@return Hour
	  */
	public String getHora () 
	{
		return (String)get_Value(COLUMNNAME_Hora);
	}

	/** Set Warehouse Movements.
		@param MovimientosAlmacenes Warehouse Movements	  */
	public void setMovimientosAlmacenes (String MovimientosAlmacenes)
	{
		set_Value (COLUMNNAME_MovimientosAlmacenes, MovimientosAlmacenes);
	}

	/** Get Warehouse Movements.
		@return Warehouse Movements	  */
	public String getMovimientosAlmacenes () 
	{
		return (String)get_Value(COLUMNNAME_MovimientosAlmacenes);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Purchase Order.
		@param OrdenCompra Purchase Order	  */
	public void setOrdenCompra (String OrdenCompra)
	{
		set_Value (COLUMNNAME_OrdenCompra, OrdenCompra);
	}

	/** Get Purchase Order.
		@return Purchase Order	  */
	public String getOrdenCompra () 
	{
		return (String)get_Value(COLUMNNAME_OrdenCompra);
	}

	/** Set Purchase Order Detail.
		@param OrdenesCompraDetalle Purchase Order Detail	  */
	public void setOrdenesCompraDetalle (String OrdenesCompraDetalle)
	{
		set_Value (COLUMNNAME_OrdenesCompraDetalle, OrdenesCompraDetalle);
	}

	/** Get Purchase Order Detail.
		@return Purchase Order Detail	  */
	public String getOrdenesCompraDetalle () 
	{
		return (String)get_Value(COLUMNNAME_OrdenesCompraDetalle);
	}

	/** Set Cashbook Relationed Pays and Consolidates.
		@param PagosRelacionadosCajaCon 
		Cashbook Relationed Pays and Consolidates
	  */
	public void setPagosRelacionadosCajaCon (String PagosRelacionadosCajaCon)
	{
		set_Value (COLUMNNAME_PagosRelacionadosCajaCon, PagosRelacionadosCajaCon);
	}

	/** Get Cashbook Relationed Pays and Consolidates.
		@return Cashbook Relationed Pays and Consolidates
	  */
	public String getPagosRelacionadosCajaCon () 
	{
		return (String)get_Value(COLUMNNAME_PagosRelacionadosCajaCon);
	}

	/** Set Packages.
		@param Paquetes Packages	  */
	public void setPaquetes (String Paquetes)
	{
		set_Value (COLUMNNAME_Paquetes, Paquetes);
	}

	/** Get Packages.
		@return Packages	  */
	public String getPaquetes () 
	{
		return (String)get_Value(COLUMNNAME_Paquetes);
	}

	/** Set Suppliers.
		@param Proveedores Suppliers	  */
	public void setProveedores (String Proveedores)
	{
		set_Value (COLUMNNAME_Proveedores, Proveedores);
	}

	/** Get Suppliers.
		@return Suppliers	  */
	public String getProveedores () 
	{
		return (String)get_Value(COLUMNNAME_Proveedores);
	}

	/** Set Sale Provision.
		@param ProvisionVenta Sale Provision	  */
	public void setProvisionVenta (String ProvisionVenta)
	{
		set_Value (COLUMNNAME_ProvisionVenta, ProvisionVenta);
	}

	/** Get Sale Provision.
		@return Sale Provision	  */
	public String getProvisionVenta () 
	{
		return (String)get_Value(COLUMNNAME_ProvisionVenta);
	}

	/** Set Reception of Articles.
		@param RecepcionesArticulos Reception of Articles	  */
	public void setRecepcionesArticulos (String RecepcionesArticulos)
	{
		set_Value (COLUMNNAME_RecepcionesArticulos, RecepcionesArticulos);
	}

	/** Get Reception of Articles.
		@return Reception of Articles	  */
	public String getRecepcionesArticulos () 
	{
		return (String)get_Value(COLUMNNAME_RecepcionesArticulos);
	}

	/** Set Article Reception Detail.
		@param RecepcionesArticulosDetalle 
		product reception detail
	  */
	public void setRecepcionesArticulosDetalle (String RecepcionesArticulosDetalle)
	{
		set_Value (COLUMNNAME_RecepcionesArticulosDetalle, RecepcionesArticulosDetalle);
	}

	/** Get Article Reception Detail.
		@return product reception detail
	  */
	public String getRecepcionesArticulosDetalle () 
	{
		return (String)get_Value(COLUMNNAME_RecepcionesArticulosDetalle);
	}

	/** Set Warehouse Article Relation.
		@param RelacionArticuloAlmacen 
		Warehouse Article Relation
	  */
	public void setRelacionArticuloAlmacen (String RelacionArticuloAlmacen)
	{
		set_Value (COLUMNNAME_RelacionArticuloAlmacen, RelacionArticuloAlmacen);
	}

	/** Get Warehouse Article Relation.
		@return Warehouse Article Relation
	  */
	public String getRelacionArticuloAlmacen () 
	{
		return (String)get_Value(COLUMNNAME_RelacionArticuloAlmacen);
	}

	/** Set Article Requisition.
		@param RequisicionesArticulos Article Requisition	  */
	public void setRequisicionesArticulos (String RequisicionesArticulos)
	{
		set_Value (COLUMNNAME_RequisicionesArticulos, RequisicionesArticulos);
	}

	/** Get Article Requisition.
		@return Article Requisition	  */
	public String getRequisicionesArticulos () 
	{
		return (String)get_Value(COLUMNNAME_RequisicionesArticulos);
	}
}