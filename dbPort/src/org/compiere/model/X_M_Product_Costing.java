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

/** Generated Model for M_Product_Costing
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_M_Product_Costing extends PO implements I_M_Product_Costing, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Product_Costing (Properties ctx, int M_Product_Costing_ID, String trxName)
    {
      super (ctx, M_Product_Costing_ID, trxName);
      /** if (M_Product_Costing_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setCostAverage (Env.ZERO);
			setCostAverageCumAmt (Env.ZERO);
			setCostAverageCumQty (Env.ZERO);
			setCostStandard (Env.ZERO);
			setCostStandardCumAmt (Env.ZERO);
			setCostStandardCumQty (Env.ZERO);
			setCostStandardPOAmt (Env.ZERO);
			setCostStandardPOQty (Env.ZERO);
			setCurrentCostPrice (Env.ZERO);
			setFutureCostPrice (Env.ZERO);
			setM_Product_ID (0);
			setPriceActual (Env.ZERO);
// 0
			setPriceLastInv (Env.ZERO);
			setPriceLastPO (Env.ZERO);
			setPriceList (Env.ZERO);
// 0
			setTotalInvAmt (Env.ZERO);
			setTotalInvQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_Product_Costing (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_M_Product_Costing[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
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
		set_ValueNoCheck (COLUMNNAME_CostAverage, CostAverage);
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

	/** Set Average Cost Amount Sum.
		@param CostAverageCumAmt 
		Cumulative average cost amounts (internal)
	  */
	public void setCostAverageCumAmt (BigDecimal CostAverageCumAmt)
	{
		if (CostAverageCumAmt == null)
			throw new IllegalArgumentException ("CostAverageCumAmt is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostAverageCumAmt, CostAverageCumAmt);
	}

	/** Get Average Cost Amount Sum.
		@return Cumulative average cost amounts (internal)
	  */
	public BigDecimal getCostAverageCumAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostAverageCumAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Average Cost Quantity Sum.
		@param CostAverageCumQty 
		Cumulative average cost quantities (internal)
	  */
	public void setCostAverageCumQty (BigDecimal CostAverageCumQty)
	{
		if (CostAverageCumQty == null)
			throw new IllegalArgumentException ("CostAverageCumQty is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostAverageCumQty, CostAverageCumQty);
	}

	/** Get Average Cost Quantity Sum.
		@return Cumulative average cost quantities (internal)
	  */
	public BigDecimal getCostAverageCumQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostAverageCumQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard Cost.
		@param CostStandard 
		Standard Costs
	  */
	public void setCostStandard (BigDecimal CostStandard)
	{
		if (CostStandard == null)
			throw new IllegalArgumentException ("CostStandard is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostStandard, CostStandard);
	}

	/** Get Standard Cost.
		@return Standard Costs
	  */
	public BigDecimal getCostStandard () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandard);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Std Cost Amount Sum.
		@param CostStandardCumAmt 
		Standard Cost Invoice Amount Sum (internal)
	  */
	public void setCostStandardCumAmt (BigDecimal CostStandardCumAmt)
	{
		if (CostStandardCumAmt == null)
			throw new IllegalArgumentException ("CostStandardCumAmt is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostStandardCumAmt, CostStandardCumAmt);
	}

	/** Get Std Cost Amount Sum.
		@return Standard Cost Invoice Amount Sum (internal)
	  */
	public BigDecimal getCostStandardCumAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandardCumAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Std Cost Quantity Sum.
		@param CostStandardCumQty 
		Standard Cost Invoice Quantity Sum (internal)
	  */
	public void setCostStandardCumQty (BigDecimal CostStandardCumQty)
	{
		if (CostStandardCumQty == null)
			throw new IllegalArgumentException ("CostStandardCumQty is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostStandardCumQty, CostStandardCumQty);
	}

	/** Get Std Cost Quantity Sum.
		@return Standard Cost Invoice Quantity Sum (internal)
	  */
	public BigDecimal getCostStandardCumQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandardCumQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Std PO Cost Amount Sum.
		@param CostStandardPOAmt 
		Standard Cost Purchase Order Amount Sum (internal)
	  */
	public void setCostStandardPOAmt (BigDecimal CostStandardPOAmt)
	{
		if (CostStandardPOAmt == null)
			throw new IllegalArgumentException ("CostStandardPOAmt is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostStandardPOAmt, CostStandardPOAmt);
	}

	/** Get Std PO Cost Amount Sum.
		@return Standard Cost Purchase Order Amount Sum (internal)
	  */
	public BigDecimal getCostStandardPOAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandardPOAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Std PO Cost Quantity Sum.
		@param CostStandardPOQty 
		Standard Cost Purchase Order Quantity Sum (internal)
	  */
	public void setCostStandardPOQty (BigDecimal CostStandardPOQty)
	{
		if (CostStandardPOQty == null)
			throw new IllegalArgumentException ("CostStandardPOQty is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CostStandardPOQty, CostStandardPOQty);
	}

	/** Get Std PO Cost Quantity Sum.
		@return Standard Cost Purchase Order Quantity Sum (internal)
	  */
	public BigDecimal getCostStandardPOQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandardPOQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Cost Price.
		@param CurrentCostPrice 
		The currently used cost price
	  */
	public void setCurrentCostPrice (BigDecimal CurrentCostPrice)
	{
		if (CurrentCostPrice == null)
			throw new IllegalArgumentException ("CurrentCostPrice is mandatory.");
		set_Value (COLUMNNAME_CurrentCostPrice, CurrentCostPrice);
	}

	/** Get Current Cost Price.
		@return The currently used cost price
	  */
	public BigDecimal getCurrentCostPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentCostPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Future Cost Price.
		@param FutureCostPrice Future Cost Price	  */
	public void setFutureCostPrice (BigDecimal FutureCostPrice)
	{
		if (FutureCostPrice == null)
			throw new IllegalArgumentException ("FutureCostPrice is mandatory.");
		set_Value (COLUMNNAME_FutureCostPrice, FutureCostPrice);
	}

	/** Get Future Cost Price.
		@return Future Cost Price	  */
	public BigDecimal getFutureCostPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FutureCostPrice);
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

	/** Set Unit Price.
		@param PriceActual 
		Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual)
	{
		if (PriceActual == null)
			throw new IllegalArgumentException ("PriceActual is mandatory.");
		set_Value (COLUMNNAME_PriceActual, PriceActual);
	}

	/** Get Unit Price.
		@return Actual Price 
	  */
	public BigDecimal getPriceActual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last Invoice Price.
		@param PriceLastInv 
		Price of the last invoice for the product
	  */
	public void setPriceLastInv (BigDecimal PriceLastInv)
	{
		if (PriceLastInv == null)
			throw new IllegalArgumentException ("PriceLastInv is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PriceLastInv, PriceLastInv);
	}

	/** Get Last Invoice Price.
		@return Price of the last invoice for the product
	  */
	public BigDecimal getPriceLastInv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLastInv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last PO Price.
		@param PriceLastPO 
		Price of the last purchase order for the product
	  */
	public void setPriceLastPO (BigDecimal PriceLastPO)
	{
		if (PriceLastPO == null)
			throw new IllegalArgumentException ("PriceLastPO is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PriceLastPO, PriceLastPO);
	}

	/** Get Last PO Price.
		@return Price of the last purchase order for the product
	  */
	public BigDecimal getPriceLastPO () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLastPO);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		if (PriceList == null)
			throw new IllegalArgumentException ("PriceList is mandatory.");
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Invoice Amount.
		@param TotalInvAmt 
		Cumulative total lifetime invoice amount
	  */
	public void setTotalInvAmt (BigDecimal TotalInvAmt)
	{
		if (TotalInvAmt == null)
			throw new IllegalArgumentException ("TotalInvAmt is mandatory.");
		set_ValueNoCheck (COLUMNNAME_TotalInvAmt, TotalInvAmt);
	}

	/** Get Total Invoice Amount.
		@return Cumulative total lifetime invoice amount
	  */
	public BigDecimal getTotalInvAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalInvAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Invoice Quantity.
		@param TotalInvQty 
		Cumulative total lifetime invoice quantity
	  */
	public void setTotalInvQty (BigDecimal TotalInvQty)
	{
		if (TotalInvQty == null)
			throw new IllegalArgumentException ("TotalInvQty is mandatory.");
		set_ValueNoCheck (COLUMNNAME_TotalInvQty, TotalInvQty);
	}

	/** Get Total Invoice Quantity.
		@return Cumulative total lifetime invoice quantity
	  */
	public BigDecimal getTotalInvQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalInvQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}