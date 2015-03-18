/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for SM_Solicitud
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_SM_Solicitud extends PO implements I_SM_Solicitud, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_SM_Solicitud (Properties ctx, int SM_Solicitud_ID, String trxName)
    {
      super (ctx, SM_Solicitud_ID, trxName);
      /** if (SM_Solicitud_ID == 0)
        {
			setCantidad (Env.ZERO);
			setDocumentNo (null);
			setM_Movement_ID (0);
			setM_MovementLine_ID (0);
			setMovementDate (new Timestamp( System.currentTimeMillis() ));
			setM_Product_ID (0);
			setProducto_Value (null);
			setSM_Solicitud_ID (0);
			setTransferido (false);
			setWHS_Sol_Value (null);
			setWHS_Sur_Value (null);
        } */
    }

    /** Load Constructor */
    public X_SM_Solicitud (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_SM_Solicitud[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		if (Cantidad == null)
			throw new IllegalArgumentException ("Cantidad is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Error  Number.
		@param EXME_Error_ID Error  Number	  */
	public void setEXME_Error_ID (String EXME_Error_ID)
	{
		set_ValueNoCheck (COLUMNNAME_EXME_Error_ID, EXME_Error_ID);
	}

	/** Get Error  Number.
		@return Error  Number	  */
	public String getEXME_Error_ID () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Error_ID);
	}

	/** Set Database Message.
		@param Mensajes_BD Database Message	  */
	public void setMensajes_BD (String Mensajes_BD)
	{
		set_ValueNoCheck (COLUMNNAME_Mensajes_BD, Mensajes_BD);
	}

	/** Get Database Message.
		@return Database Message	  */
	public String getMensajes_BD () 
	{
		return (String)get_Value(COLUMNNAME_Mensajes_BD);
	}

	public I_M_Locator getM_Locator() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Locator.Table_Name);
        I_M_Locator result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Locator)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Locator_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locator To.
		@param M_LocatorTo_ID 
		Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID)
	{
		if (M_LocatorTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_LocatorTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_LocatorTo_ID, Integer.valueOf(M_LocatorTo_ID));
	}

	/** Get Locator To.
		@return Location inventory is moved to
	  */
	public int getM_LocatorTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LocatorTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Movement getM_Movement() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Movement.Table_Name);
        I_M_Movement result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Movement)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Movement_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Inventory Move.
		@param M_Movement_ID 
		Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID)
	{
		if (M_Movement_ID < 1)
			 throw new IllegalArgumentException ("M_Movement_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Movement_ID, Integer.valueOf(M_Movement_ID));
	}

	/** Get Inventory Move.
		@return Movement of Inventory
	  */
	public int getM_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_MovementLine getM_MovementLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_MovementLine.Table_Name);
        I_M_MovementLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_MovementLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_MovementLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1)
			 throw new IllegalArgumentException ("M_MovementLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		if (MovementDate == null)
			throw new IllegalArgumentException ("MovementDate is mandatory.");
		set_ValueNoCheck (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse To.
		@param M_WarehouseTo_ID 
		Warehouse
	  */
	public void setM_WarehouseTo_ID (int M_WarehouseTo_ID)
	{
		if (M_WarehouseTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_WarehouseTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_WarehouseTo_ID, Integer.valueOf(M_WarehouseTo_ID));
	}

	/** Get Warehouse To.
		@return Warehouse
	  */
	public int getM_WarehouseTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Key.
		@param Producto_Value Product Key	  */
	public void setProducto_Value (String Producto_Value)
	{
		if (Producto_Value == null)
			throw new IllegalArgumentException ("Producto_Value is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Producto_Value, Producto_Value);
	}

	/** Get Product Key.
		@return Product Key	  */
	public String getProducto_Value () 
	{
		return (String)get_Value(COLUMNNAME_Producto_Value);
	}

	/** Set Virtual Request.
		@param SM_Solicitud_ID Virtual Request	  */
	public void setSM_Solicitud_ID (int SM_Solicitud_ID)
	{
		if (SM_Solicitud_ID < 1)
			 throw new IllegalArgumentException ("SM_Solicitud_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_SM_Solicitud_ID, Integer.valueOf(SM_Solicitud_ID));
	}

	/** Get Virtual Request.
		@return Virtual Request	  */
	public int getSM_Solicitud_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SM_Solicitud_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transferred.
		@param Transferido Transferred	  */
	public void setTransferido (boolean Transferido)
	{
		set_ValueNoCheck (COLUMNNAME_Transferido, Boolean.valueOf(Transferido));
	}

	/** Get Transferred.
		@return Transferred	  */
	public boolean isTransferido () 
	{
		Object oo = get_Value(COLUMNNAME_Transferido);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Uom Key.
		@param UOM_Value Uom Key	  */
	public void setUOM_Value (String UOM_Value)
	{
		set_ValueNoCheck (COLUMNNAME_UOM_Value, UOM_Value);
	}

	/** Get Uom Key.
		@return Uom Key	  */
	public String getUOM_Value () 
	{
		return (String)get_Value(COLUMNNAME_UOM_Value);
	}

	/** Set Warehouse Requesting.
		@param WHS_Sol_Value Warehouse Requesting	  */
	public void setWHS_Sol_Value (String WHS_Sol_Value)
	{
		if (WHS_Sol_Value == null)
			throw new IllegalArgumentException ("WHS_Sol_Value is mandatory.");
		set_ValueNoCheck (COLUMNNAME_WHS_Sol_Value, WHS_Sol_Value);
	}

	/** Get Warehouse Requesting.
		@return Warehouse Requesting	  */
	public String getWHS_Sol_Value () 
	{
		return (String)get_Value(COLUMNNAME_WHS_Sol_Value);
	}

	/** Set Warehouse Supplier.
		@param WHS_Sur_Value Warehouse Supplier	  */
	public void setWHS_Sur_Value (String WHS_Sur_Value)
	{
		if (WHS_Sur_Value == null)
			throw new IllegalArgumentException ("WHS_Sur_Value is mandatory.");
		set_ValueNoCheck (COLUMNNAME_WHS_Sur_Value, WHS_Sur_Value);
	}

	/** Get Warehouse Supplier.
		@return Warehouse Supplier	  */
	public String getWHS_Sur_Value () 
	{
		return (String)get_Value(COLUMNNAME_WHS_Sur_Value);
	}
}