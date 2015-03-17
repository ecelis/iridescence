/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for T_Replenish
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_T_Replenish extends PO implements I_T_Replenish, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_T_Replenish (Properties ctx, int T_Replenish_ID, String trxName)
    {
      super (ctx, T_Replenish_ID, trxName);
      /** if (T_Replenish_ID == 0)
        {
			setAD_PInstance_ID (0);
			setC_BPartner_ID (0);
			setLevel_Max (Env.ZERO);
			setLevel_Max_Vol (Env.ZERO);
			setLevel_Min (Env.ZERO);
			setLevel_Min_Vol (Env.ZERO);
			setM_Product_ID (0);
			setM_Warehouse_ID (0);
			setOrder_Min_Vol (Env.ZERO);
			setOrder_Pack_Vol (Env.ZERO);
			setQtyOnHand_Vol (Env.ZERO);
			setQtyOrdered_Vol (Env.ZERO);
			setQtyReserved_Vol (Env.ZERO);
			setQtyToOrder_Vol (Env.ZERO);
			setReplenishType (null);
			setSeqNoInsert (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_T_Replenish (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_T_Replenish[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_PInstance getAD_PInstance() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PInstance.Table_Name);
        I_AD_PInstance result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PInstance)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PInstance_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1)
			 throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 1) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Level.
		@param Level_Max 
		Maximum Inventory level for this product
	  */
	public void setLevel_Max (BigDecimal Level_Max)
	{
		if (Level_Max == null)
			throw new IllegalArgumentException ("Level_Max is mandatory.");
		set_Value (COLUMNNAME_Level_Max, Level_Max);
	}

	/** Get Maximum Level.
		@return Maximum Inventory level for this product
	  */
	public BigDecimal getLevel_Max () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Level_Max);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Packing Maximum Level.
		@param Level_Max_Vol 
		Maximum Inventory level of packing for this product
	  */
	public void setLevel_Max_Vol (BigDecimal Level_Max_Vol)
	{
		if (Level_Max_Vol == null)
			throw new IllegalArgumentException ("Level_Max_Vol is mandatory.");
		set_Value (COLUMNNAME_Level_Max_Vol, Level_Max_Vol);
	}

	/** Get Packing Maximum Level.
		@return Maximum Inventory level of packing for this product
	  */
	public BigDecimal getLevel_Max_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Level_Max_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Level.
		@param Level_Min 
		Minimum Inventory level for this product
	  */
	public void setLevel_Min (BigDecimal Level_Min)
	{
		if (Level_Min == null)
			throw new IllegalArgumentException ("Level_Min is mandatory.");
		set_Value (COLUMNNAME_Level_Min, Level_Min);
	}

	/** Get Minimum Level.
		@return Minimum Inventory level for this product
	  */
	public BigDecimal getLevel_Min () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Level_Min);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Packing Minimum Level.
		@param Level_Min_Vol 
		Minimum Inventory level of packing for this product
	  */
	public void setLevel_Min_Vol (BigDecimal Level_Min_Vol)
	{
		if (Level_Min_Vol == null)
			throw new IllegalArgumentException ("Level_Min_Vol is mandatory.");
		set_Value (COLUMNNAME_Level_Min_Vol, Level_Min_Vol);
	}

	/** Get Packing Minimum Level.
		@return Minimum Inventory level of packing for this product
	  */
	public BigDecimal getLevel_Min_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Level_Min_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
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

	/** Set Source Warehouse.
		@param M_WarehouseSource_ID 
		Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
	{
		if (M_WarehouseSource_ID < 1) 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, null);
		else 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, Integer.valueOf(M_WarehouseSource_ID));
	}

	/** Get Source Warehouse.
		@return Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Minimum Order Qty.
		@param Order_Min 
		Minimum order quantity in UOM
	  */
	public void setOrder_Min (BigDecimal Order_Min)
	{
		set_Value (COLUMNNAME_Order_Min, Order_Min);
	}

	/** Get Minimum Order Qty.
		@return Minimum order quantity in UOM
	  */
	public BigDecimal getOrder_Min () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Min);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Order Qty Pack.
		@param Order_Min_Vol 
		Minimum Order Qty Pack
	  */
	public void setOrder_Min_Vol (BigDecimal Order_Min_Vol)
	{
		if (Order_Min_Vol == null)
			throw new IllegalArgumentException ("Order_Min_Vol is mandatory.");
		set_Value (COLUMNNAME_Order_Min_Vol, Order_Min_Vol);
	}

	/** Get Minimum Order Qty Pack.
		@return Minimum Order Qty Pack
	  */
	public BigDecimal getOrder_Min_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Min_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Pack Qty.
		@param Order_Pack 
		Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (BigDecimal Order_Pack)
	{
		set_Value (COLUMNNAME_Order_Pack, Order_Pack);
	}

	/** Get Order Pack Qty.
		@return Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Pack);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Pack Qty.
		@param Order_Pack_Vol 
		Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack_Vol (BigDecimal Order_Pack_Vol)
	{
		if (Order_Pack_Vol == null)
			throw new IllegalArgumentException ("Order_Pack_Vol is mandatory.");
		set_Value (COLUMNNAME_Order_Pack_Vol, Order_Pack_Vol);
	}

	/** Get Order Pack Qty.
		@return Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Pack_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set On Hand Quantity.
		@param QtyOnHand 
		On Hand Quantity
	  */
	public void setQtyOnHand (BigDecimal QtyOnHand)
	{
		set_Value (COLUMNNAME_QtyOnHand, QtyOnHand);
	}

	/** Get On Hand Quantity.
		@return On Hand Quantity
	  */
	public BigDecimal getQtyOnHand () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOnHand);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set On Hand Quantity Pack.
		@param QtyOnHand_Vol 
		On Hand Quantity Pack
	  */
	public void setQtyOnHand_Vol (BigDecimal QtyOnHand_Vol)
	{
		if (QtyOnHand_Vol == null)
			throw new IllegalArgumentException ("QtyOnHand_Vol is mandatory.");
		set_Value (COLUMNNAME_QtyOnHand_Vol, QtyOnHand_Vol);
	}

	/** Get On Hand Quantity Pack.
		@return On Hand Quantity Pack
	  */
	public BigDecimal getQtyOnHand_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOnHand_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Qty Pack.
		@param QtyOrdered_Vol 
		Ordered Quantity Packs ordered
	  */
	public void setQtyOrdered_Vol (BigDecimal QtyOrdered_Vol)
	{
		if (QtyOrdered_Vol == null)
			throw new IllegalArgumentException ("QtyOrdered_Vol is mandatory.");
		set_Value (COLUMNNAME_QtyOrdered_Vol, QtyOrdered_Vol);
	}

	/** Get Ordered Qty Pack.
		@return Ordered Quantity Packs ordered
	  */
	public BigDecimal getQtyOrdered_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reserved Quantity.
		@param QtyReserved 
		Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved)
	{
		set_Value (COLUMNNAME_QtyReserved, QtyReserved);
	}

	/** Get Reserved Quantity.
		@return Reserved Quantity
	  */
	public BigDecimal getQtyReserved () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReserved);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyReserved_Vol.
		@param QtyReserved_Vol 
		Reserved Quantity Package
	  */
	public void setQtyReserved_Vol (BigDecimal QtyReserved_Vol)
	{
		if (QtyReserved_Vol == null)
			throw new IllegalArgumentException ("QtyReserved_Vol is mandatory.");
		set_Value (COLUMNNAME_QtyReserved_Vol, QtyReserved_Vol);
	}

	/** Get QtyReserved_Vol.
		@return Reserved Quantity Package
	  */
	public BigDecimal getQtyReserved_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReserved_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity to Order.
		@param QtyToOrder Quantity to Order	  */
	public void setQtyToOrder (BigDecimal QtyToOrder)
	{
		set_Value (COLUMNNAME_QtyToOrder, QtyToOrder);
	}

	/** Get Quantity to Order.
		@return Quantity to Order	  */
	public BigDecimal getQtyToOrder () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyToOrder);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Pack to Order.
		@param QtyToOrder_Vol 
		Quantity Pack to Order
	  */
	public void setQtyToOrder_Vol (BigDecimal QtyToOrder_Vol)
	{
		if (QtyToOrder_Vol == null)
			throw new IllegalArgumentException ("QtyToOrder_Vol is mandatory.");
		set_Value (COLUMNNAME_QtyToOrder_Vol, QtyToOrder_Vol);
	}

	/** Get Quantity Pack to Order.
		@return Quantity Pack to Order
	  */
	public BigDecimal getQtyToOrder_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyToOrder_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ReplenishmentCreate AD_Reference_ID=329 */
	public static final int REPLENISHMENTCREATE_AD_Reference_ID=329;
	/** Purchase Order = POO */
	public static final String REPLENISHMENTCREATE_PurchaseOrder = "POO";
	/** Requisition = POR */
	public static final String REPLENISHMENTCREATE_Requisition = "POR";
	/** Inventory Move = MMM */
	public static final String REPLENISHMENTCREATE_InventoryMove = "MMM";
	/** Distribution Order = DOO */
	public static final String REPLENISHMENTCREATE_DistributionOrder = "DOO";
	/** Set Create.
		@param ReplenishmentCreate 
		Create from Replenishment
	  */
	public void setReplenishmentCreate (String ReplenishmentCreate)
	{

		if (ReplenishmentCreate == null || ReplenishmentCreate.equals("POO") || ReplenishmentCreate.equals("POR") || ReplenishmentCreate.equals("MMM") || ReplenishmentCreate.equals("DOO")); else throw new IllegalArgumentException ("ReplenishmentCreate Invalid value - " + ReplenishmentCreate + " - Reference_ID=329 - POO - POR - MMM - DOO");		set_Value (COLUMNNAME_ReplenishmentCreate, ReplenishmentCreate);
	}

	/** Get Create.
		@return Create from Replenishment
	  */
	public String getReplenishmentCreate () 
	{
		return (String)get_Value(COLUMNNAME_ReplenishmentCreate);
	}

	/** ReplenishType AD_Reference_ID=164 */
	public static final int REPLENISHTYPE_AD_Reference_ID=164;
	/** Maintain Maximum Level = 2 */
	public static final String REPLENISHTYPE_MaintainMaximumLevel = "2";
	/** Manual = 0 */
	public static final String REPLENISHTYPE_Manual = "0";
	/** Reorder below Minimum Level = 1 */
	public static final String REPLENISHTYPE_ReorderBelowMinimumLevel = "1";
	/** Custom = 9 */
	public static final String REPLENISHTYPE_Custom = "9";
	/** Set Replenish Type.
		@param ReplenishType 
		Method for re-ordering a product
	  */
	public void setReplenishType (String ReplenishType)
	{
		if (ReplenishType == null) throw new IllegalArgumentException ("ReplenishType is mandatory");
		if (ReplenishType.equals("2") || ReplenishType.equals("0") || ReplenishType.equals("1") || ReplenishType.equals("9")); else throw new IllegalArgumentException ("ReplenishType Invalid value - " + ReplenishType + " - Reference_ID=164 - 2 - 0 - 1 - 9");		set_Value (COLUMNNAME_ReplenishType, ReplenishType);
	}

	/** Get Replenish Type.
		@return Method for re-ordering a product
	  */
	public String getReplenishType () 
	{
		return (String)get_Value(COLUMNNAME_ReplenishType);
	}

	/** Set SeqNoInsert.
		@param SeqNoInsert 
		Number of the sequence to insert
	  */
	public void setSeqNoInsert (BigDecimal SeqNoInsert)
	{
		if (SeqNoInsert == null)
			throw new IllegalArgumentException ("SeqNoInsert is mandatory.");
		set_Value (COLUMNNAME_SeqNoInsert, SeqNoInsert);
	}

	/** Get SeqNoInsert.
		@return Number of the sequence to insert
	  */
	public BigDecimal getSeqNoInsert () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SeqNoInsert);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}