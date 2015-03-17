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

/** Generated Model for EXME_SalidaGastoLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_SalidaGastoLine extends PO implements I_EXME_SalidaGastoLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SalidaGastoLine (Properties ctx, int EXME_SalidaGastoLine_ID, String trxName)
    {
      super (ctx, EXME_SalidaGastoLine_ID, trxName);
      /** if (EXME_SalidaGastoLine_ID == 0)
        {
			setCostAverage (Env.ZERO);
			setEXME_SalidaGasto_ID (0);
			setEXME_SalidaGastoLine_ID (0);
			setInventoryType (null);
			setM_Locator_ID (0);
			setM_Product_ID (0);
			setOriginalQty (Env.ZERO);
			setProcessed (false);
			setQtyBook (Env.ZERO);
			setQtyCount (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_SalidaGastoLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SalidaGastoLine[")
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

	/** Set Average Cost.
		@param CostAverage 
		Weighted average costs
	  */
	public void setCostAverage (BigDecimal CostAverage)
	{
		if (CostAverage == null)
			throw new IllegalArgumentException ("CostAverage is mandatory.");
		set_Value (COLUMNNAME_CostAverage, CostAverage);
	}

	/** Get Average Cost.
		@return Weighted average costs
	  */
	public BigDecimal getCostAverage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostAverage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_EXME_SalidaGasto getEXME_SalidaGasto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SalidaGasto.Table_Name);
        I_EXME_SalidaGasto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SalidaGasto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SalidaGasto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Internal Use Inventory.
		@param EXME_SalidaGasto_ID Internal Use Inventory	  */
	public void setEXME_SalidaGasto_ID (int EXME_SalidaGasto_ID)
	{
		if (EXME_SalidaGasto_ID < 1)
			 throw new IllegalArgumentException ("EXME_SalidaGasto_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SalidaGasto_ID, Integer.valueOf(EXME_SalidaGasto_ID));
	}

	/** Get Internal Use Inventory.
		@return Internal Use Inventory	  */
	public int getEXME_SalidaGasto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SalidaGasto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Internal Use Inventory Line.
		@param EXME_SalidaGastoLine_ID Internal Use Inventory Line	  */
	public void setEXME_SalidaGastoLine_ID (int EXME_SalidaGastoLine_ID)
	{
		if (EXME_SalidaGastoLine_ID < 1)
			 throw new IllegalArgumentException ("EXME_SalidaGastoLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SalidaGastoLine_ID, Integer.valueOf(EXME_SalidaGastoLine_ID));
	}

	/** Get Internal Use Inventory Line.
		@return Internal Use Inventory Line	  */
	public int getEXME_SalidaGastoLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SalidaGastoLine_ID);
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

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
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

	/** Set Original Quantity.
		@param OriginalQty Original Quantity	  */
	public void setOriginalQty (BigDecimal OriginalQty)
	{
		if (OriginalQty == null)
			throw new IllegalArgumentException ("OriginalQty is mandatory.");
		set_Value (COLUMNNAME_OriginalQty, OriginalQty);
	}

	/** Get Original Quantity.
		@return Original Quantity	  */
	public BigDecimal getOriginalQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OriginalQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
		set_Value (COLUMNNAME_QtyBook, QtyBook);
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
}