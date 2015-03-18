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

/** Generated Model for C_UOM_Conversion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_UOM_Conversion extends PO implements I_C_UOM_Conversion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_UOM_Conversion (Properties ctx, int C_UOM_Conversion_ID, String trxName)
    {
      super (ctx, C_UOM_Conversion_ID, trxName);
      /** if (C_UOM_Conversion_ID == 0)
        {
			setC_UOM_Conversion_ID (0);
			setC_UOM_ID (0);
			setC_UOM_To_ID (0);
			setDivideRate (Env.ZERO);
			setMultiplyRate (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_C_UOM_Conversion (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_UOM_Conversion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set UOM Conversion.
		@param C_UOM_Conversion_ID 
		Unit of Measure Conversion
	  */
	public void setC_UOM_Conversion_ID (int C_UOM_Conversion_ID)
	{
		if (C_UOM_Conversion_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_Conversion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_UOM_Conversion_ID, Integer.valueOf(C_UOM_Conversion_ID));
	}

	/** Get UOM Conversion.
		@return Unit of Measure Conversion
	  */
	public int getC_UOM_Conversion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Conversion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_UOM_Conversion_ID()));
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
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
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

	/** Set UoM To.
		@param C_UOM_To_ID 
		Target or destination Unit of Measure
	  */
	public void setC_UOM_To_ID (int C_UOM_To_ID)
	{
		if (C_UOM_To_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_To_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_To_ID, Integer.valueOf(C_UOM_To_ID));
	}

	/** Get UoM To.
		@return Target or destination Unit of Measure
	  */
	public int getC_UOM_To_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_To_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Divide Rate.
		@param DivideRate 
		To convert Source number to Target number, the Source is divided
	  */
	public void setDivideRate (BigDecimal DivideRate)
	{
		if (DivideRate == null)
			throw new IllegalArgumentException ("DivideRate is mandatory.");
		set_Value (COLUMNNAME_DivideRate, DivideRate);
	}

	/** Get Divide Rate.
		@return To convert Source number to Target number, the Source is divided
	  */
	public BigDecimal getDivideRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DivideRate);
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

	/** Set Multiply Rate.
		@param MultiplyRate 
		Rate to multiple the source by to calculate the target.
	  */
	public void setMultiplyRate (BigDecimal MultiplyRate)
	{
		if (MultiplyRate == null)
			throw new IllegalArgumentException ("MultiplyRate is mandatory.");
		set_Value (COLUMNNAME_MultiplyRate, MultiplyRate);
	}

	/** Get Multiply Rate.
		@return Rate to multiple the source by to calculate the target.
	  */
	public BigDecimal getMultiplyRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MultiplyRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}