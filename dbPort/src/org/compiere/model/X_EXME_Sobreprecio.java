/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Sobreprecio
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Sobreprecio extends PO implements I_EXME_Sobreprecio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Sobreprecio (Properties ctx, int EXME_Sobreprecio_ID, String trxName)
    {
      super (ctx, EXME_Sobreprecio_ID, trxName);
      /** if (EXME_Sobreprecio_ID == 0)
        {
			setEXME_Sobreprecio_ID (0);
			setM_Warehouse_ID (0);
			setM_WarehouseSur_ID (0);
			setName (null);
			setPorcentaje (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Sobreprecio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Sobreprecio[")
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

	/** Set Overprice.
		@param EXME_Sobreprecio_ID 
		Overprice
	  */
	public void setEXME_Sobreprecio_ID (int EXME_Sobreprecio_ID)
	{
		if (EXME_Sobreprecio_ID < 1)
			 throw new IllegalArgumentException ("EXME_Sobreprecio_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Sobreprecio_ID, Integer.valueOf(EXME_Sobreprecio_ID));
	}

	/** Get Overprice.
		@return Overprice
	  */
	public int getEXME_Sobreprecio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Sobreprecio_ID);
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
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
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

	/** Set Completed Warehouse.
		@param M_WarehouseSur_ID 
		Completed warehouse
	  */
	public void setM_WarehouseSur_ID (int M_WarehouseSur_ID)
	{
		if (M_WarehouseSur_ID < 1)
			 throw new IllegalArgumentException ("M_WarehouseSur_ID is mandatory.");
		set_Value (COLUMNNAME_M_WarehouseSur_ID, Integer.valueOf(M_WarehouseSur_ID));
	}

	/** Get Completed Warehouse.
		@return Completed warehouse
	  */
	public int getM_WarehouseSur_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSur_ID);
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

	/** Set Percentage.
		@param Porcentaje 
		percentage
	  */
	public void setPorcentaje (int Porcentaje)
	{
		set_Value (COLUMNNAME_Porcentaje, Integer.valueOf(Porcentaje));
	}

	/** Get Percentage.
		@return percentage
	  */
	public int getPorcentaje () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Porcentaje);
		if (ii == null)
			 return 0;
		return ii.intValue();
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