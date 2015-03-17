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

/** Generated Model for I_Cost
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_Cost extends PO implements I_I_Cost, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_Cost (Properties ctx, int I_Cost_ID, String trxName)
    {
      super (ctx, I_Cost_ID, trxName);
      /** if (I_Cost_ID == 0)
        {
			setI_Cost_ID (0);
			setI_IsImported (false);
        } */
    }

    /** Load Constructor */
    public X_I_Cost (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_Cost[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_Value (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	/** Set Name of Accounting Schema.
		@param C_AcctSchema_Name 
		Name of Accounting Schema
	  */
	public void setC_AcctSchema_Name (String C_AcctSchema_Name)
	{
		set_Value (COLUMNNAME_C_AcctSchema_Name, C_AcctSchema_Name);
	}

	/** Get Name of Accounting Schema.
		@return Name of Accounting Schema
	  */
	public String getC_AcctSchema_Name () 
	{
		return (String)get_Value(COLUMNNAME_C_AcctSchema_Name);
	}

	/** Set Current Cost Price.
		@param CurrentCostPrice 
		The currently used cost price
	  */
	public void setCurrentCostPrice (BigDecimal CurrentCostPrice)
	{
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

	/** Set Import Cost.
		@param I_Cost_ID Import Cost	  */
	public void setI_Cost_ID (int I_Cost_ID)
	{
		if (I_Cost_ID < 1)
			 throw new IllegalArgumentException ("I_Cost_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_Cost_ID, Integer.valueOf(I_Cost_ID));
	}

	/** Get Import Cost.
		@return Import Cost	  */
	public int getI_Cost_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Cost_ID);
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
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
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

	/** Set Product Code.
		@param M_Product_Value 
		product search Code
	  */
	public void setM_Product_Value (String M_Product_Value)
	{
		set_Value (COLUMNNAME_M_Product_Value, M_Product_Value);
	}

	/** Get Product Code.
		@return product search Code
	  */
	public String getM_Product_Value () 
	{
		return (String)get_Value(COLUMNNAME_M_Product_Value);
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
}