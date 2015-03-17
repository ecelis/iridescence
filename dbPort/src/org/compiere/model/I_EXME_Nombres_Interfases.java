/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Nombres_Interfases
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Nombres_Interfases 
{

    /** TableName=EXME_Nombres_Interfases */
    public static final String Table_Name = "EXME_Nombres_Interfases";

    /** AD_Table_ID=1200185 */
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

    /** Column name ArticulosGeneral */
    public static final String COLUMNNAME_ArticulosGeneral = "ArticulosGeneral";

	/** Set General Articles.
	  * General Articles
	  */
	public void setArticulosGeneral (String ArticulosGeneral);

	/** Get General Articles.
	  * General Articles
	  */
	public String getArticulosGeneral();

    /** Column name ArticulosNivelesPrecio */
    public static final String COLUMNNAME_ArticulosNivelesPrecio = "ArticulosNivelesPrecio";

	/** Set Articles Price Levels.
	  * Articles Price Levels
	  */
	public void setArticulosNivelesPrecio (String ArticulosNivelesPrecio);

	/** Get Articles Price Levels.
	  * Articles Price Levels
	  */
	public String getArticulosNivelesPrecio();

    /** Column name Clientes */
    public static final String COLUMNNAME_Clientes = "Clientes";

	/** Set Clients.
	  * Clients
	  */
	public void setClientes (String Clientes);

	/** Get Clients.
	  * Clients
	  */
	public String getClientes();

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

    /** Column name DescuentosFacturar */
    public static final String COLUMNNAME_DescuentosFacturar = "DescuentosFacturar";

	/** Set Invoice Discount	  */
	public void setDescuentosFacturar (String DescuentosFacturar);

	/** Get Invoice Discount	  */
	public String getDescuentosFacturar();

    /** Column name DevolucionOrdenCompra */
    public static final String COLUMNNAME_DevolucionOrdenCompra = "DevolucionOrdenCompra";

	/** Set Purchase Order Devolution	  */
	public void setDevolucionOrdenCompra (String DevolucionOrdenCompra);

	/** Get Purchase Order Devolution	  */
	public String getDevolucionOrdenCompra();

    /** Column name EXME_Nombres_Interfases_ID */
    public static final String COLUMNNAME_EXME_Nombres_Interfases_ID = "EXME_Nombres_Interfases_ID";

	/** Set Interface Name.
	  * Interface Name
	  */
	public void setEXME_Nombres_Interfases_ID (int EXME_Nombres_Interfases_ID);

	/** Get Interface Name.
	  * Interface Name
	  */
	public int getEXME_Nombres_Interfases_ID();

    /** Column name EXME_Ruta_Interfases_ID */
    public static final String COLUMNNAME_EXME_Ruta_Interfases_ID = "EXME_Ruta_Interfases_ID";

	/** Set Interfaces Path	  */
	public void setEXME_Ruta_Interfases_ID (int EXME_Ruta_Interfases_ID);

	/** Get Interfaces Path	  */
	public int getEXME_Ruta_Interfases_ID();

	public I_EXME_Ruta_Interfases getEXME_Ruta_Interfases() throws RuntimeException;

    /** Column name FacturasNotasCredito */
    public static final String COLUMNNAME_FacturasNotasCredito = "FacturasNotasCredito";

	/** Set Invoices and Credit Notes	  */
	public void setFacturasNotasCredito (String FacturasNotasCredito);

	/** Get Invoices and Credit Notes	  */
	public String getFacturasNotasCredito();

    /** Column name Hora */
    public static final String COLUMNNAME_Hora = "Hora";

	/** Set Hour.
	  * Hour
	  */
	public void setHora (String Hora);

	/** Get Hour.
	  * Hour
	  */
	public String getHora();

    /** Column name MovimientosAlmacenes */
    public static final String COLUMNNAME_MovimientosAlmacenes = "MovimientosAlmacenes";

	/** Set Warehouse Movements	  */
	public void setMovimientosAlmacenes (String MovimientosAlmacenes);

	/** Get Warehouse Movements	  */
	public String getMovimientosAlmacenes();

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

    /** Column name OrdenCompra */
    public static final String COLUMNNAME_OrdenCompra = "OrdenCompra";

	/** Set Purchase Order	  */
	public void setOrdenCompra (String OrdenCompra);

	/** Get Purchase Order	  */
	public String getOrdenCompra();

    /** Column name OrdenesCompraDetalle */
    public static final String COLUMNNAME_OrdenesCompraDetalle = "OrdenesCompraDetalle";

	/** Set Purchase Order Detail	  */
	public void setOrdenesCompraDetalle (String OrdenesCompraDetalle);

	/** Get Purchase Order Detail	  */
	public String getOrdenesCompraDetalle();

    /** Column name PagosRelacionadosCajaCon */
    public static final String COLUMNNAME_PagosRelacionadosCajaCon = "PagosRelacionadosCajaCon";

	/** Set Cashbook Relationed Pays and Consolidates.
	  * Cashbook Relationed Pays and Consolidates
	  */
	public void setPagosRelacionadosCajaCon (String PagosRelacionadosCajaCon);

	/** Get Cashbook Relationed Pays and Consolidates.
	  * Cashbook Relationed Pays and Consolidates
	  */
	public String getPagosRelacionadosCajaCon();

    /** Column name Paquetes */
    public static final String COLUMNNAME_Paquetes = "Paquetes";

	/** Set Packages	  */
	public void setPaquetes (String Paquetes);

	/** Get Packages	  */
	public String getPaquetes();

    /** Column name Proveedores */
    public static final String COLUMNNAME_Proveedores = "Proveedores";

	/** Set Suppliers	  */
	public void setProveedores (String Proveedores);

	/** Get Suppliers	  */
	public String getProveedores();

    /** Column name ProvisionVenta */
    public static final String COLUMNNAME_ProvisionVenta = "ProvisionVenta";

	/** Set Sale Provision	  */
	public void setProvisionVenta (String ProvisionVenta);

	/** Get Sale Provision	  */
	public String getProvisionVenta();

    /** Column name RecepcionesArticulos */
    public static final String COLUMNNAME_RecepcionesArticulos = "RecepcionesArticulos";

	/** Set Reception of Articles	  */
	public void setRecepcionesArticulos (String RecepcionesArticulos);

	/** Get Reception of Articles	  */
	public String getRecepcionesArticulos();

    /** Column name RecepcionesArticulosDetalle */
    public static final String COLUMNNAME_RecepcionesArticulosDetalle = "RecepcionesArticulosDetalle";

	/** Set Article Reception Detail.
	  * product reception detail
	  */
	public void setRecepcionesArticulosDetalle (String RecepcionesArticulosDetalle);

	/** Get Article Reception Detail.
	  * product reception detail
	  */
	public String getRecepcionesArticulosDetalle();

    /** Column name RelacionArticuloAlmacen */
    public static final String COLUMNNAME_RelacionArticuloAlmacen = "RelacionArticuloAlmacen";

	/** Set Warehouse Article Relation.
	  * Warehouse Article Relation
	  */
	public void setRelacionArticuloAlmacen (String RelacionArticuloAlmacen);

	/** Get Warehouse Article Relation.
	  * Warehouse Article Relation
	  */
	public String getRelacionArticuloAlmacen();

    /** Column name RequisicionesArticulos */
    public static final String COLUMNNAME_RequisicionesArticulos = "RequisicionesArticulos";

	/** Set Article Requisition	  */
	public void setRequisicionesArticulos (String RequisicionesArticulos);

	/** Get Article Requisition	  */
	public String getRequisicionesArticulos();
}
