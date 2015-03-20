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

/** Generated Model for I_UOM_Conversion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_UOM_Conversion extends PO implements I_I_UOM_Conversion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_UOM_Conversion (Properties ctx, int I_UOM_Conversion_ID, String trxName)
    {
      super (ctx, I_UOM_Conversion_ID, trxName);
      /** if (I_UOM_Conversion_ID == 0)
        {
			setI_IsImported (false);
// N
			setI_UOM_Conversion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_UOM_Conversion (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_UOM_Conversion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM_Conversion getC_UOM_Conversion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM_Conversion.Table_Name);
        I_C_UOM_Conversion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM_Conversion)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_Conversion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM Conversion.
		@param C_UOM_Conversion_ID 
		Unit of Measure Conversion
	  */
	public void setC_UOM_Conversion_ID (int C_UOM_Conversion_ID)
	{
		if (C_UOM_Conversion_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Conversion_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Conversion_ID, Integer.valueOf(C_UOM_Conversion_ID));
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

	/** Set UoM To.
		@param C_UOM_To_ID 
		Target or destination Unit of Measure
	  */
	public void setC_UOM_To_ID (int C_UOM_To_ID)
	{
		if (C_UOM_To_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_To_ID, null);
		else 
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

	/** Set Imported Conversion of Unit of Measure.
		@param I_UOM_Conversion_ID 
		Imported Conversion of unit of Measure
	  */
	public void setI_UOM_Conversion_ID (int I_UOM_Conversion_ID)
	{
		if (I_UOM_Conversion_ID < 1)
			 throw new IllegalArgumentException ("I_UOM_Conversion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_UOM_Conversion_ID, Integer.valueOf(I_UOM_Conversion_ID));
	}

	/** Get Imported Conversion of Unit of Measure.
		@return Imported Conversion of unit of Measure
	  */
	public int getI_UOM_Conversion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_UOM_Conversion_ID);
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

	/** Set Multiply Rate.
		@param MultiplyRate 
		Rate to multiple the source by to calculate the target.
	  */
	public void setMultiplyRate (BigDecimal MultiplyRate)
	{
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

	/** Set UOM Code.
		@param X12DE355 
		UOM EDI X12 Code
	  */
	public void setX12DE355 (String X12DE355)
	{
		set_Value (COLUMNNAME_X12DE355, X12DE355);
	}

	/** Get UOM Code.
		@return UOM EDI X12 Code
	  */
	public String getX12DE355 () 
	{
		return (String)get_Value(COLUMNNAME_X12DE355);
	}

	/** Set Destin EDI code.
		@param X12DE355To 
		Destin EDI of UoM code
	  */
	public void setX12DE355To (String X12DE355To)
	{
		set_Value (COLUMNNAME_X12DE355To, X12DE355To);
	}

	/** Get Destin EDI code.
		@return Destin EDI of UoM code
	  */
	public String getX12DE355To () 
	{
		return (String)get_Value(COLUMNNAME_X12DE355To);
	}
}