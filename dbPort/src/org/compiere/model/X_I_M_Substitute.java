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
import org.compiere.util.KeyNamePair;

/** Generated Model for I_M_Substitute
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_M_Substitute extends PO implements I_I_M_Substitute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_M_Substitute (Properties ctx, int I_M_Substitute_ID, String trxName)
    {
      super (ctx, I_M_Substitute_ID, trxName);
      /** if (I_M_Substitute_ID == 0)
        {
			setI_IsImported (false);
			setI_M_Substitute_ID (0);
			setQtyOrigin (Env.ZERO);
			setQtyTarget (Env.ZERO);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_I_M_Substitute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_M_Substitute[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set C_UOM_Value.
		@param C_UOM_Value C_UOM_Value	  */
	public void setC_UOM_Value (String C_UOM_Value)
	{
		set_Value (COLUMNNAME_C_UOM_Value, C_UOM_Value);
	}

	/** Get C_UOM_Value.
		@return C_UOM_Value	  */
	public String getC_UOM_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_UOM_Value);
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

	/** Set I_M_Substitute_ID.
		@param I_M_Substitute_ID I_M_Substitute_ID	  */
	public void setI_M_Substitute_ID (int I_M_Substitute_ID)
	{
		if (I_M_Substitute_ID < 1)
			 throw new IllegalArgumentException ("I_M_Substitute_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_M_Substitute_ID, Integer.valueOf(I_M_Substitute_ID));
	}

	/** Get I_M_Substitute_ID.
		@return I_M_Substitute_ID	  */
	public int getI_M_Substitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_M_Substitute_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	/** Set Product Substitute.
		@param Prod_Substitute_ID 
		Product Substitute
	  */
	public void setProd_Substitute_ID (int Prod_Substitute_ID)
	{
		if (Prod_Substitute_ID < 1) 
			set_Value (COLUMNNAME_Prod_Substitute_ID, null);
		else 
			set_Value (COLUMNNAME_Prod_Substitute_ID, Integer.valueOf(Prod_Substitute_ID));
	}

	/** Get Product Substitute.
		@return Product Substitute
	  */
	public int getProd_Substitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Prod_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Substitute Value.
		@param Prod_Substitute_Value Product Substitute Value	  */
	public void setProd_Substitute_Value (String Prod_Substitute_Value)
	{
		set_Value (COLUMNNAME_Prod_Substitute_Value, Prod_Substitute_Value);
	}

	/** Get Product Substitute Value.
		@return Product Substitute Value	  */
	public String getProd_Substitute_Value () 
	{
		return (String)get_Value(COLUMNNAME_Prod_Substitute_Value);
	}

	/** Set Quantity Origin.
		@param QtyOrigin Quantity Origin	  */
	public void setQtyOrigin (BigDecimal QtyOrigin)
	{
		if (QtyOrigin == null)
			throw new IllegalArgumentException ("QtyOrigin is mandatory.");
		set_Value (COLUMNNAME_QtyOrigin, QtyOrigin);
	}

	/** Get Quantity Origin.
		@return Quantity Origin	  */
	public BigDecimal getQtyOrigin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrigin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Target.
		@param QtyTarget Quantity Target	  */
	public void setQtyTarget (BigDecimal QtyTarget)
	{
		if (QtyTarget == null)
			throw new IllegalArgumentException ("QtyTarget is mandatory.");
		set_Value (COLUMNNAME_QtyTarget, QtyTarget);
	}

	/** Get Quantity Target.
		@return Quantity Target	  */
	public BigDecimal getQtyTarget () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyTarget);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UOM Substitute.
		@param UOM_Substitute_ID UOM Substitute	  */
	public void setUOM_Substitute_ID (int UOM_Substitute_ID)
	{
		if (UOM_Substitute_ID < 1) 
			set_Value (COLUMNNAME_UOM_Substitute_ID, null);
		else 
			set_Value (COLUMNNAME_UOM_Substitute_ID, Integer.valueOf(UOM_Substitute_ID));
	}

	/** Get UOM Substitute.
		@return UOM Substitute	  */
	public int getUOM_Substitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UOM_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM_Substitute_Value.
		@param UOM_Substitute_Value UOM_Substitute_Value	  */
	public void setUOM_Substitute_Value (String UOM_Substitute_Value)
	{
		set_Value (COLUMNNAME_UOM_Substitute_Value, UOM_Substitute_Value);
	}

	/** Get UOM_Substitute_Value.
		@return UOM_Substitute_Value	  */
	public String getUOM_Substitute_Value () 
	{
		return (String)get_Value(COLUMNNAME_UOM_Substitute_Value);
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		if (ValidFrom == null)
			throw new IllegalArgumentException ("ValidFrom is mandatory.");
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}