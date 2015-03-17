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

/** Generated Model for EXME_AudListaPrecio
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AudListaPrecio extends PO implements I_EXME_AudListaPrecio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AudListaPrecio (Properties ctx, int EXME_AudListaPrecio_ID, String trxName)
    {
      super (ctx, EXME_AudListaPrecio_ID, trxName);
      /** if (EXME_AudListaPrecio_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_UOM_ID (0);
			setEXME_AudListaPrecio_ID (0);
			setEXME_ConfigEC_ID (0);
			setM_PriceList_ID (0);
			setM_PriceList_Version_ID (0);
			setM_Product_ID (0);
			setPrecioAnterior (Env.ZERO);
			setPrecioNuevo (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_AudListaPrecio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AudListaPrecio[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
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

	/** Set Audit Price List.
		@param EXME_AudListaPrecio_ID Audit Price List	  */
	public void setEXME_AudListaPrecio_ID (int EXME_AudListaPrecio_ID)
	{
		if (EXME_AudListaPrecio_ID < 1)
			 throw new IllegalArgumentException ("EXME_AudListaPrecio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AudListaPrecio_ID, Integer.valueOf(EXME_AudListaPrecio_ID));
	}

	/** Get Audit Price List.
		@return Audit Price List	  */
	public int getEXME_AudListaPrecio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AudListaPrecio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ConfigEC getEXME_ConfigEC() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConfigEC.Table_Name);
        I_EXME_ConfigEC result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConfigEC)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConfigEC_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EMR Configuration.
		@param EXME_ConfigEC_ID 
		EMR Configuration
	  */
	public void setEXME_ConfigEC_ID (int EXME_ConfigEC_ID)
	{
		if (EXME_ConfigEC_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEC_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ConfigEC_ID, Integer.valueOf(EXME_ConfigEC_ID));
	}

	/** Get EMR Configuration.
		@return EMR Configuration
	  */
	public int getEXME_ConfigEC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
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
			 throw new IllegalArgumentException ("M_PriceList_Version_ID is mandatory.");
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

	/** Set Old Price.
		@param PrecioAnterior Old Price	  */
	public void setPrecioAnterior (BigDecimal PrecioAnterior)
	{
		if (PrecioAnterior == null)
			throw new IllegalArgumentException ("PrecioAnterior is mandatory.");
		set_Value (COLUMNNAME_PrecioAnterior, PrecioAnterior);
	}

	/** Get Old Price.
		@return Old Price	  */
	public BigDecimal getPrecioAnterior () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PrecioAnterior);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set New Price.
		@param PrecioNuevo New Price	  */
	public void setPrecioNuevo (BigDecimal PrecioNuevo)
	{
		if (PrecioNuevo == null)
			throw new IllegalArgumentException ("PrecioNuevo is mandatory.");
		set_Value (COLUMNNAME_PrecioNuevo, PrecioNuevo);
	}

	/** Get New Price.
		@return New Price	  */
	public BigDecimal getPrecioNuevo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PrecioNuevo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}