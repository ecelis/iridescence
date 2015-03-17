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

/** Generated Model for M_ProductOperation
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_M_ProductOperation extends PO implements I_M_ProductOperation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_ProductOperation (Properties ctx, int M_ProductOperation_ID, String trxName)
    {
      super (ctx, M_ProductOperation_ID, trxName);
      /** if (M_ProductOperation_ID == 0)
        {
			setM_Product_ID (0);
			setM_ProductOperation_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_M_ProductOperation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_ProductOperation[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	/** Set Product Operation.
		@param M_ProductOperation_ID 
		Product Manufacturing Operation
	  */
	public void setM_ProductOperation_ID (int M_ProductOperation_ID)
	{
		if (M_ProductOperation_ID < 1)
			 throw new IllegalArgumentException ("M_ProductOperation_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_ProductOperation_ID, Integer.valueOf(M_ProductOperation_ID));
	}

	/** Get Product Operation.
		@return Product Manufacturing Operation
	  */
	public int getM_ProductOperation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductOperation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
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

	/** Set Setup Time.
		@param SetupTime 
		Setup time before starting Production
	  */
	public void setSetupTime (BigDecimal SetupTime)
	{
		set_Value (COLUMNNAME_SetupTime, SetupTime);
	}

	/** Get Setup Time.
		@return Setup time before starting Production
	  */
	public BigDecimal getSetupTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SetupTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Teardown Time.
		@param TeardownTime 
		Time at the end of the operation
	  */
	public void setTeardownTime (BigDecimal TeardownTime)
	{
		set_Value (COLUMNNAME_TeardownTime, TeardownTime);
	}

	/** Get Teardown Time.
		@return Time at the end of the operation
	  */
	public BigDecimal getTeardownTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TeardownTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Runtime per Unit.
		@param UnitRuntime 
		Time to produce one unit
	  */
	public void setUnitRuntime (BigDecimal UnitRuntime)
	{
		set_Value (COLUMNNAME_UnitRuntime, UnitRuntime);
	}

	/** Get Runtime per Unit.
		@return Time to produce one unit
	  */
	public BigDecimal getUnitRuntime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitRuntime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}