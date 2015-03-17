/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Tratamientos_Serv
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Tratamientos_Serv extends PO implements I_EXME_Tratamientos_Serv, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos_Serv (Properties ctx, int EXME_Tratamientos_Serv_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_Serv_ID, trxName);
      /** if (EXME_Tratamientos_Serv_ID == 0)
        {
			setAlmacen_Surt_ID (0);
			setEXME_Tratamientos_Detalle_ID (0);
			setEXME_Tratamientos_Serv_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos_Serv (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos_Serv[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Warehouse that Applies_ID.
		@param Almacen_Surt_ID Warehouse that Applies_ID	  */
	public void setAlmacen_Surt_ID (int Almacen_Surt_ID)
	{
		if (Almacen_Surt_ID < 1)
			 throw new IllegalArgumentException ("Almacen_Surt_ID is mandatory.");
		set_Value (COLUMNNAME_Almacen_Surt_ID, Integer.valueOf(Almacen_Surt_ID));
	}

	/** Get Warehouse that Applies_ID.
		@return Warehouse that Applies_ID	  */
	public int getAlmacen_Surt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Almacen_Surt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Detalle.Table_Name);
        I_EXME_Tratamientos_Detalle result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Detalle)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Detalle_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments Detail.
		@param EXME_Tratamientos_Detalle_ID Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID)
	{
		if (EXME_Tratamientos_Detalle_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Detalle_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Tratamientos_Detalle_ID, Integer.valueOf(EXME_Tratamientos_Detalle_ID));
	}

	/** Get Treatments Detail.
		@return Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Detalle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment Services.
		@param EXME_Tratamientos_Serv_ID Treatment Services	  */
	public void setEXME_Tratamientos_Serv_ID (int EXME_Tratamientos_Serv_ID)
	{
		if (EXME_Tratamientos_Serv_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Serv_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_Serv_ID, Integer.valueOf(EXME_Tratamientos_Serv_ID));
	}

	/** Get Treatment Services.
		@return Treatment Services	  */
	public int getEXME_Tratamientos_Serv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Serv_ID);
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

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Session Number.
		@param SessionNo 
		Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo)
	{
		set_Value (COLUMNNAME_SessionNo, Integer.valueOf(SessionNo));
	}

	/** Get Session Number.
		@return Session Number of a treatment
	  */
	public int getSessionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SessionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}