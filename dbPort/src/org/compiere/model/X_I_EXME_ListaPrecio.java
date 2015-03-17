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

/** Generated Model for I_EXME_ListaPrecio
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_EXME_ListaPrecio extends PO implements I_I_EXME_ListaPrecio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_ListaPrecio (Properties ctx, int I_EXME_ListaPrecio_ID, String trxName)
    {
      super (ctx, I_EXME_ListaPrecio_ID, trxName);
      /** if (I_EXME_ListaPrecio_ID == 0)
        {
			setI_EXME_ListaPrecio_ID (0);
			setI_IsImported (false);
			setPriceLimit_Vol (Env.ZERO);
			setPriceList_Vol (Env.ZERO);
			setPriceStd_Vol (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_ListaPrecio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_ListaPrecio[")
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

	/** Set UOM Name.
		@param C_UOM_Name UOM Name	  */
	public void setC_UOM_Name (String C_UOM_Name)
	{
		set_Value (COLUMNNAME_C_UOM_Name, C_UOM_Name);
	}

	/** Get UOM Name.
		@return UOM Name	  */
	public String getC_UOM_Name () 
	{
		return (String)get_Value(COLUMNNAME_C_UOM_Name);
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

	/** Set Load Date.
		@param Fecha_Carga Load Date	  */
	public void setFecha_Carga (Timestamp Fecha_Carga)
	{
		set_Value (COLUMNNAME_Fecha_Carga, Fecha_Carga);
	}

	/** Get Load Date.
		@return Load Date	  */
	public Timestamp getFecha_Carga () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Carga);
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

	/** Set Price List.
		@param I_EXME_ListaPrecio_ID Price List	  */
	public void setI_EXME_ListaPrecio_ID (int I_EXME_ListaPrecio_ID)
	{
		if (I_EXME_ListaPrecio_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_ListaPrecio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_ListaPrecio_ID, Integer.valueOf(I_EXME_ListaPrecio_ID));
	}

	/** Get Price List.
		@return Price List	  */
	public int getI_EXME_ListaPrecio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_ListaPrecio_ID);
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

	public I_M_PriceList getM_PriceList() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PriceList.Table_Name);
        I_M_PriceList result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PriceList)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PriceList_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name Price List.
		@param M_PriceList_Name 
		Name Price List
	  */
	public void setM_PriceList_Name (String M_PriceList_Name)
	{
		set_Value (COLUMNNAME_M_PriceList_Name, M_PriceList_Name);
	}

	/** Get Name Price List.
		@return Name Price List
	  */
	public String getM_PriceList_Name () 
	{
		return (String)get_Value(COLUMNNAME_M_PriceList_Name);
	}

	public I_M_PriceList_Version getM_PriceList_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PriceList_Version.Table_Name);
        I_M_PriceList_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PriceList_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PriceList_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price List Version.
		@param M_PriceList_Version_ID 
		Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, Integer.valueOf(M_PriceList_Version_ID));
	}

	/** Get Price List Version.
		@return Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price List Version Name.
		@param M_PriceList_Version_Name Price List Version Name	  */
	public void setM_PriceList_Version_Name (String M_PriceList_Version_Name)
	{
		set_Value (COLUMNNAME_M_PriceList_Version_Name, M_PriceList_Version_Name);
	}

	/** Get Price List Version Name.
		@return Price List Version Name	  */
	public String getM_PriceList_Version_Name () 
	{
		return (String)get_Value(COLUMNNAME_M_PriceList_Version_Name);
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

	/** Set Price.
		@param Precio Price	  */
	public void setPrecio (BigDecimal Precio)
	{
		set_Value (COLUMNNAME_Precio, Precio);
	}

	/** Get Price.
		@return Price	  */
	public BigDecimal getPrecio () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Precio);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pack Limit Price.
		@param PriceLimit_Vol 
		Limit Price for the Pack UOM
	  */
	public void setPriceLimit_Vol (BigDecimal PriceLimit_Vol)
	{
		if (PriceLimit_Vol == null)
			throw new IllegalArgumentException ("PriceLimit_Vol is mandatory.");
		set_Value (COLUMNNAME_PriceLimit_Vol, PriceLimit_Vol);
	}

	/** Get Pack Limit Price.
		@return Limit Price for the Pack UOM
	  */
	public BigDecimal getPriceLimit_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pack Price List.
		@param PriceList_Vol 
		Price List for the Pack UOM
	  */
	public void setPriceList_Vol (BigDecimal PriceList_Vol)
	{
		if (PriceList_Vol == null)
			throw new IllegalArgumentException ("PriceList_Vol is mandatory.");
		set_Value (COLUMNNAME_PriceList_Vol, PriceList_Vol);
	}

	/** Get Pack Price List.
		@return Price List for the Pack UOM
	  */
	public BigDecimal getPriceList_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pack Standard Price.
		@param PriceStd_Vol 
		Standard Price for the Pack UOM
	  */
	public void setPriceStd_Vol (BigDecimal PriceStd_Vol)
	{
		if (PriceStd_Vol == null)
			throw new IllegalArgumentException ("PriceStd_Vol is mandatory.");
		set_Value (COLUMNNAME_PriceStd_Vol, PriceStd_Vol);
	}

	/** Get Pack Standard Price.
		@return Standard Price for the Pack UOM
	  */
	public BigDecimal getPriceStd_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceStd_Vol);
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

	/** Set Product Description.
		@param ProductDescription 
		Product Description
	  */
	public void setProductDescription (String ProductDescription)
	{
		set_Value (COLUMNNAME_ProductDescription, ProductDescription);
	}

	/** Get Product Description.
		@return Product Description
	  */
	public String getProductDescription () 
	{
		return (String)get_Value(COLUMNNAME_ProductDescription);
	}

	/** Set Product Key.
		@param ProductValue 
		Key of the Product
	  */
	public void setProductValue (String ProductValue)
	{
		set_Value (COLUMNNAME_ProductValue, ProductValue);
	}

	/** Get Product Key.
		@return Key of the Product
	  */
	public String getProductValue () 
	{
		return (String)get_Value(COLUMNNAME_ProductValue);
	}
}