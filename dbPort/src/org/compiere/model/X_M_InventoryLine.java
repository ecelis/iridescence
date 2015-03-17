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
import org.compiere.util.KeyNamePair;

/** Generated Model for M_InventoryLine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_InventoryLine extends PO implements I_M_InventoryLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_InventoryLine (Properties ctx, int M_InventoryLine_ID, String trxName)
    {
      super (ctx, M_InventoryLine_ID, trxName);
      /** if (M_InventoryLine_ID == 0)
        {
			setInventoryType (null);
// D
			setM_Inventory_ID (0);
			setM_InventoryLine_ID (0);
			setM_Locator_ID (0);
			setM_Product_ID (0);
			setProcessed (false);
			setQtyBook (Env.ZERO);
			setQtyBook_Uom (Env.ZERO);
			setQtyBook_Vol (Env.ZERO);
			setQtyCount (Env.ZERO);
			setQtyCount_Uom (Env.ZERO);
			setQtyCount_Vol (Env.ZERO);
			setQtyCsv (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_InventoryLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_InventoryLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Charge getC_Charge() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Charge.Table_Name);
        I_C_Charge result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Charge)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Charge_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
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

	/** Set Pack UOM.
		@param C_UOMVolume_ID 
		Unit of measure of volume or packing
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID)
	{
		if (C_UOMVolume_ID < 1) 
			set_Value (COLUMNNAME_C_UOMVolume_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMVolume_ID, Integer.valueOf(C_UOMVolume_ID));
	}

	/** Get Pack UOM.
		@return Unit of measure of volume or packing
	  */
	public int getC_UOMVolume_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMVolume_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Product class and Fact. acct..
		@param EXME_ProductClassFact_ID 
		Product class and Fact. acct.
	  */
	public void setEXME_ProductClassFact_ID (int EXME_ProductClassFact_ID)
	{
		if (EXME_ProductClassFact_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProductClassFact_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProductClassFact_ID, Integer.valueOf(EXME_ProductClassFact_ID));
	}

	/** Get Product class and Fact. acct..
		@return Product class and Fact. acct.
	  */
	public int getEXME_ProductClassFact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductClassFact_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** InventoryType AD_Reference_ID=292 */
	public static final int INVENTORYTYPE_AD_Reference_ID=292;
	/** Inventory Difference = D */
	public static final String INVENTORYTYPE_InventoryDifference = "D";
	/** Charge Account = C */
	public static final String INVENTORYTYPE_ChargeAccount = "C";
	/** Set Inventory Type.
		@param InventoryType 
		Type of inventory difference
	  */
	public void setInventoryType (String InventoryType)
	{
		if (InventoryType == null) throw new IllegalArgumentException ("InventoryType is mandatory");
		if (InventoryType.equals("D") || InventoryType.equals("C")); else throw new IllegalArgumentException ("InventoryType Invalid value - " + InventoryType + " - Reference_ID=292 - D - C");		set_Value (COLUMNNAME_InventoryType, InventoryType);
	}

	/** Get Inventory Type.
		@return Type of inventory difference
	  */
	public String getInventoryType () 
	{
		return (String)get_Value(COLUMNNAME_InventoryType);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getLine()));
    }

	/** Set Lot's information.
		@param LotInfo Lot's information	  */
	public void setLotInfo (String LotInfo)
	{
		set_Value (COLUMNNAME_LotInfo, LotInfo);
	}

	/** Get Lot's information.
		@return Lot's information	  */
	public String getLotInfo () 
	{
		return (String)get_Value(COLUMNNAME_LotInfo);
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Inventory getM_Inventory() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Inventory.Table_Name);
        I_M_Inventory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Inventory)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Inventory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Phys.Inventory.
		@param M_Inventory_ID 
		Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID)
	{
		if (M_Inventory_ID < 1)
			 throw new IllegalArgumentException ("M_Inventory_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Inventory_ID, Integer.valueOf(M_Inventory_ID));
	}

	/** Get Phys.Inventory.
		@return Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Inventory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Phys.Inventory Line.
		@param M_InventoryLine_ID 
		Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID)
	{
		if (M_InventoryLine_ID < 1)
			 throw new IllegalArgumentException ("M_InventoryLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_InventoryLine_ID, Integer.valueOf(M_InventoryLine_ID));
	}

	/** Get Phys.Inventory Line.
		@return Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InventoryLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			 throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
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

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Quantity book.
		@param QtyBook 
		Book Quantity
	  */
	public void setQtyBook (BigDecimal QtyBook)
	{
		if (QtyBook == null)
			throw new IllegalArgumentException ("QtyBook is mandatory.");
		set_ValueNoCheck (COLUMNNAME_QtyBook, QtyBook);
	}

	/** Get Quantity book.
		@return Book Quantity
	  */
	public BigDecimal getQtyBook () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBook);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity book.
		@param QtyBook_Uom 
		Book Quantity
	  */
	public void setQtyBook_Uom (BigDecimal QtyBook_Uom)
	{
		if (QtyBook_Uom == null)
			throw new IllegalArgumentException ("QtyBook_Uom is mandatory.");
		set_Value (COLUMNNAME_QtyBook_Uom, QtyBook_Uom);
	}

	/** Get Quantity book.
		@return Book Quantity
	  */
	public BigDecimal getQtyBook_Uom () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBook_Uom);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity book Pack.
		@param QtyBook_Vol 
		Book Quantity
	  */
	public void setQtyBook_Vol (BigDecimal QtyBook_Vol)
	{
		if (QtyBook_Vol == null)
			throw new IllegalArgumentException ("QtyBook_Vol is mandatory.");
		set_Value (COLUMNNAME_QtyBook_Vol, QtyBook_Vol);
	}

	/** Get Quantity book Pack.
		@return Book Quantity
	  */
	public BigDecimal getQtyBook_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBook_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity count.
		@param QtyCount 
		Counted Quantity
	  */
	public void setQtyCount (BigDecimal QtyCount)
	{
		if (QtyCount == null)
			throw new IllegalArgumentException ("QtyCount is mandatory.");
		set_Value (COLUMNNAME_QtyCount, QtyCount);
	}

	/** Get Quantity count.
		@return Counted Quantity
	  */
	public BigDecimal getQtyCount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyCount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity count.
		@param QtyCount_Uom 
		Counted Quantity
	  */
	public void setQtyCount_Uom (BigDecimal QtyCount_Uom)
	{
		if (QtyCount_Uom == null)
			throw new IllegalArgumentException ("QtyCount_Uom is mandatory.");
		set_Value (COLUMNNAME_QtyCount_Uom, QtyCount_Uom);
	}

	/** Get Quantity count.
		@return Counted Quantity
	  */
	public BigDecimal getQtyCount_Uom () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyCount_Uom);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity count Pack.
		@param QtyCount_Vol 
		Counted Quantity Pack
	  */
	public void setQtyCount_Vol (BigDecimal QtyCount_Vol)
	{
		if (QtyCount_Vol == null)
			throw new IllegalArgumentException ("QtyCount_Vol is mandatory.");
		set_Value (COLUMNNAME_QtyCount_Vol, QtyCount_Vol);
	}

	/** Get Quantity count Pack.
		@return Counted Quantity Pack
	  */
	public BigDecimal getQtyCount_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyCount_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Csv.
		@param QtyCsv Qty Csv	  */
	public void setQtyCsv (BigDecimal QtyCsv)
	{
		if (QtyCsv == null)
			throw new IllegalArgumentException ("QtyCsv is mandatory.");
		set_Value (COLUMNNAME_QtyCsv, QtyCsv);
	}

	/** Get Qty Csv.
		@return Qty Csv	  */
	public BigDecimal getQtyCsv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyCsv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Internal Use Qty.
		@param QtyInternalUse 
		Internal Use Quantity removed from Inventory
	  */
	public void setQtyInternalUse (BigDecimal QtyInternalUse)
	{
		set_Value (COLUMNNAME_QtyInternalUse, QtyInternalUse);
	}

	/** Get Internal Use Qty.
		@return Internal Use Quantity removed from Inventory
	  */
	public BigDecimal getQtyInternalUse () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInternalUse);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Internal Use(UOM).
		@param QtyInternalUse_Uom 
		Internal Use Quantity removed from Inventory(UOM)
	  */
	public void setQtyInternalUse_Uom (BigDecimal QtyInternalUse_Uom)
	{
		set_Value (COLUMNNAME_QtyInternalUse_Uom, QtyInternalUse_Uom);
	}

	/** Get Internal Use(UOM).
		@return Internal Use Quantity removed from Inventory(UOM)
	  */
	public BigDecimal getQtyInternalUse_Uom () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInternalUse_Uom);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyInternalUse_Vol.
		@param QtyInternalUse_Vol 
		Internal Use Quantity removed from Inventory(UOM)
	  */
	public void setQtyInternalUse_Vol (BigDecimal QtyInternalUse_Vol)
	{
		set_Value (COLUMNNAME_QtyInternalUse_Vol, QtyInternalUse_Vol);
	}

	/** Get QtyInternalUse_Vol.
		@return Internal Use Quantity removed from Inventory(UOM)
	  */
	public BigDecimal getQtyInternalUse_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInternalUse_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reversal Line.
		@param ReversalLine_ID 
		Use to keep the reversal line ID for reversing costing purpose
	  */
	public void setReversalLine_ID (int ReversalLine_ID)
	{
		if (ReversalLine_ID < 1) 
			set_Value (COLUMNNAME_ReversalLine_ID, null);
		else 
			set_Value (COLUMNNAME_ReversalLine_ID, Integer.valueOf(ReversalLine_ID));
	}

	/** Get Reversal Line.
		@return Use to keep the reversal line ID for reversing costing purpose
	  */
	public int getReversalLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReversalLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UPC/EAN.
		@param UPC 
		Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC)
	{
		throw new IllegalArgumentException ("UPC is virtual column");	}

	/** Get UPC/EAN.
		@return Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC () 
	{
		return (String)get_Value(COLUMNNAME_UPC);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		throw new IllegalArgumentException ("Value is virtual column");	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}