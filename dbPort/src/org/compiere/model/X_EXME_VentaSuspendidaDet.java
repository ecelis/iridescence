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

/** Generated Model for EXME_VentaSuspendidaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_VentaSuspendidaDet extends PO implements I_EXME_VentaSuspendidaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_VentaSuspendidaDet (Properties ctx, int EXME_VentaSuspendidaDet_ID, String trxName)
    {
      super (ctx, EXME_VentaSuspendidaDet_ID, trxName);
      /** if (EXME_VentaSuspendidaDet_ID == 0)
        {
			setEXME_VentaSuspendidaDet_ID (0);
			setEXME_VentaSuspendida_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_VentaSuspendidaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_VentaSuspendidaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Suspended Sale Detail.
		@param EXME_VentaSuspendidaDet_ID 
		Suspended Sale Detail
	  */
	public void setEXME_VentaSuspendidaDet_ID (int EXME_VentaSuspendidaDet_ID)
	{
		if (EXME_VentaSuspendidaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_VentaSuspendidaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_VentaSuspendidaDet_ID, Integer.valueOf(EXME_VentaSuspendidaDet_ID));
	}

	/** Get Suspended Sale Detail.
		@return Suspended Sale Detail
	  */
	public int getEXME_VentaSuspendidaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VentaSuspendidaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_VentaSuspendida getEXME_VentaSuspendida() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_VentaSuspendida.Table_Name);
        I_EXME_VentaSuspendida result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_VentaSuspendida)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_VentaSuspendida_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Suspended Sale.
		@param EXME_VentaSuspendida_ID Suspended Sale	  */
	public void setEXME_VentaSuspendida_ID (int EXME_VentaSuspendida_ID)
	{
		if (EXME_VentaSuspendida_ID < 1)
			 throw new IllegalArgumentException ("EXME_VentaSuspendida_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_VentaSuspendida_ID, Integer.valueOf(EXME_VentaSuspendida_ID));
	}

	/** Get Suspended Sale.
		@return Suspended Sale	  */
	public int getEXME_VentaSuspendida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VentaSuspendida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
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

	/** Set Warehouse Relation.
		@param M_WarehouseRel_ID 
		Warehouse Relation
	  */
	public void setM_WarehouseRel_ID (int M_WarehouseRel_ID)
	{
		if (M_WarehouseRel_ID < 1) 
			set_Value (COLUMNNAME_M_WarehouseRel_ID, null);
		else 
			set_Value (COLUMNNAME_M_WarehouseRel_ID, Integer.valueOf(M_WarehouseRel_ID));
	}

	/** Get Warehouse Relation.
		@return Warehouse Relation
	  */
	public int getM_WarehouseRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}