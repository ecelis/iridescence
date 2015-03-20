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

/** Generated Model for EXME_Tratamientos_Productos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Tratamientos_Productos extends PO implements I_EXME_Tratamientos_Productos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos_Productos (Properties ctx, int EXME_Tratamientos_Productos_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_Productos_ID, trxName);
      /** if (EXME_Tratamientos_Productos_ID == 0)
        {
			setEXME_Tratamientos_Productos_ID (0);
			setName (null);
			setSessionNo (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos_Productos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos_Productos[")
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
			set_Value (COLUMNNAME_EXME_Tratamientos_Detalle_ID, null);
		else 
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

	/** Set Treatments Products.
		@param EXME_Tratamientos_Productos_ID Treatments Products	  */
	public void setEXME_Tratamientos_Productos_ID (int EXME_Tratamientos_Productos_ID)
	{
		if (EXME_Tratamientos_Productos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Productos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_Productos_ID, Integer.valueOf(EXME_Tratamientos_Productos_ID));
	}

	/** Get Treatments Products.
		@return Treatments Products	  */
	public int getEXME_Tratamientos_Productos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Productos_ID);
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

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Data Type.
		@param TipoDato 
		Data Type
	  */
	public void setTipoDato (String TipoDato)
	{
		set_Value (COLUMNNAME_TipoDato, TipoDato);
	}

	/** Get Data Type.
		@return Data Type
	  */
	public String getTipoDato () 
	{
		return (String)get_Value(COLUMNNAME_TipoDato);
	}
}