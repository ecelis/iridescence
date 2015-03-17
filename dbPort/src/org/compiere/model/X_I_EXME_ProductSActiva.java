/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_ProductSActiva
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_ProductSActiva extends PO implements I_I_EXME_ProductSActiva, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_ProductSActiva (Properties ctx, int I_EXME_ProductSActiva_ID, String trxName)
    {
      super (ctx, I_EXME_ProductSActiva_ID, trxName);
      /** if (I_EXME_ProductSActiva_ID == 0)
        {
			setEXME_SActiva_Value (null);
			setI_EXME_ProductSActiva_ID (0);
			setI_IsImported (false);
// N
			setM_Product_Value (null);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_ProductSActiva (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_ProductSActiva[")
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

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SActiva.Table_Name);
        I_EXME_SActiva result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SActiva)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SActiva_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Active Substance.
		@param EXME_SActiva_ID 
		Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID)
	{
		if (EXME_SActiva_ID < 1) 
			set_Value (COLUMNNAME_EXME_SActiva_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
	}

	/** Get Active Substance.
		@return Active Substance
	  */
	public int getEXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Active Substance Code.
		@param EXME_SActiva_Value 
		Active Substance search Code
	  */
	public void setEXME_SActiva_Value (String EXME_SActiva_Value)
	{
		if (EXME_SActiva_Value == null)
			throw new IllegalArgumentException ("EXME_SActiva_Value is mandatory.");
		set_Value (COLUMNNAME_EXME_SActiva_Value, EXME_SActiva_Value);
	}

	/** Get Active Substance Code.
		@return Active Substance search Code
	  */
	public String getEXME_SActiva_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_SActiva_Value);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_ValueNoCheck (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Product - Imported Active Substance.
		@param I_EXME_ProductSActiva_ID 
		Product -Imported Active Substance
	  */
	public void setI_EXME_ProductSActiva_ID (int I_EXME_ProductSActiva_ID)
	{
		if (I_EXME_ProductSActiva_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_ProductSActiva_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_ProductSActiva_ID, Integer.valueOf(I_EXME_ProductSActiva_ID));
	}

	/** Get Product - Imported Active Substance.
		@return Product -Imported Active Substance
	  */
	public int getI_EXME_ProductSActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_ProductSActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		if (M_Product_Value == null)
			throw new IllegalArgumentException ("M_Product_Value is mandatory.");
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
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
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