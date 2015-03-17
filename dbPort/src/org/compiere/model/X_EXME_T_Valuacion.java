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

/** Generated Model for EXME_T_Valuacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_Valuacion extends PO implements I_EXME_T_Valuacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_Valuacion (Properties ctx, int EXME_T_Valuacion_ID, String trxName)
    {
      super (ctx, EXME_T_Valuacion_ID, trxName);
      /** if (EXME_T_Valuacion_ID == 0)
        {
			setAD_Session_ID (0);
			setCantidad (Env.ZERO);
			setEXME_T_Valuacion_ID (0);
			setName (null);
			setPrecio (Env.ZERO);
			setTotal (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_Valuacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_Valuacion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Session getAD_Session() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Session.Table_Name);
        I_AD_Session result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Session)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Session_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Session.
		@param AD_Session_ID 
		User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID)
	{
		if (AD_Session_ID < 1)
			 throw new IllegalArgumentException ("AD_Session_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Session_ID, Integer.valueOf(AD_Session_ID));
	}

	/** Get Session.
		@return User Session Online or Web
	  */
	public int getAD_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse.
		@param Almacen Warehouse	  */
	public void setAlmacen (String Almacen)
	{
		set_Value (COLUMNNAME_Almacen, Almacen);
	}

	/** Get Warehouse.
		@return Warehouse	  */
	public String getAlmacen () 
	{
		return (String)get_Value(COLUMNNAME_Almacen);
	}

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		if (Cantidad == null)
			throw new IllegalArgumentException ("Cantidad is mandatory.");
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valuation.
		@param EXME_T_Valuacion_ID Valuation	  */
	public void setEXME_T_Valuacion_ID (int EXME_T_Valuacion_ID)
	{
		if (EXME_T_Valuacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_Valuacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_Valuacion_ID, Integer.valueOf(EXME_T_Valuacion_ID));
	}

	/** Get Valuation.
		@return Valuation	  */
	public int getEXME_T_Valuacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_Valuacion_ID);
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

	/** Set Price.
		@param Precio Price	  */
	public void setPrecio (BigDecimal Precio)
	{
		if (Precio == null)
			throw new IllegalArgumentException ("Precio is mandatory.");
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

	/** Set Total.
		@param Total Total	  */
	public void setTotal (BigDecimal Total)
	{
		if (Total == null)
			throw new IllegalArgumentException ("Total is mandatory.");
		set_Value (COLUMNNAME_Total, Total);
	}

	/** Get Total.
		@return Total	  */
	public BigDecimal getTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Total);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}