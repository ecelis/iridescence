/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for I_UOM
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_UOM extends PO implements I_I_UOM, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_UOM (Properties ctx, int I_UOM_ID, String trxName)
    {
      super (ctx, I_UOM_ID, trxName);
      /** if (I_UOM_ID == 0)
        {
			setI_IsImported (false);
			setI_UOM_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_UOM (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_UOM[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Costing Precision.
		@param CostingPrecision 
		Rounding used costing calculations
	  */
	public void setCostingPrecision (int CostingPrecision)
	{
		set_Value (COLUMNNAME_CostingPrecision, Integer.valueOf(CostingPrecision));
	}

	/** Get Costing Precision.
		@return Rounding used costing calculations
	  */
	public int getCostingPrecision () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CostingPrecision);
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

	/** Set UoM Imported.
		@param I_UOM_ID 
		Imported Unit of Measure
	  */
	public void setI_UOM_ID (int I_UOM_ID)
	{
		if (I_UOM_ID < 1)
			 throw new IllegalArgumentException ("I_UOM_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_UOM_ID, Integer.valueOf(I_UOM_ID));
	}

	/** Get UoM Imported.
		@return Imported Unit of Measure
	  */
	public int getI_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_UOM_ID);
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
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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

	/** Set Standard Precision.
		@param StdPrecision 
		Rule for rounding  calculated amounts
	  */
	public void setStdPrecision (int StdPrecision)
	{
		set_Value (COLUMNNAME_StdPrecision, Integer.valueOf(StdPrecision));
	}

	/** Get Standard Precision.
		@return Rule for rounding  calculated amounts
	  */
	public int getStdPrecision () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StdPrecision);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Symbol.
		@param UOMSymbol 
		Symbol for a Unit of Measure
	  */
	public void setUOMSymbol (String UOMSymbol)
	{
		set_Value (COLUMNNAME_UOMSymbol, UOMSymbol);
	}

	/** Get Symbol.
		@return Symbol for a Unit of Measure
	  */
	public String getUOMSymbol () 
	{
		return (String)get_Value(COLUMNNAME_UOMSymbol);
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
}