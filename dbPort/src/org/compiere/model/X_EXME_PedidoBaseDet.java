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

/** Generated Model for EXME_PedidoBaseDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PedidoBaseDet extends PO implements I_EXME_PedidoBaseDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PedidoBaseDet (Properties ctx, int EXME_PedidoBaseDet_ID, String trxName)
    {
      super (ctx, EXME_PedidoBaseDet_ID, trxName);
      /** if (EXME_PedidoBaseDet_ID == 0)
        {
			setC_UOM_ID (0);
			setEXME_PedidoBaseDet_ID (0);
			setEXME_PedidoBase_ID (0);
			setM_Locator_ID (0);
			setM_LocatorTo_ID (0);
// @M_Locator_ID@
			setMovementQty (Env.ZERO);
			setMovementQty_Vol (Env.ZERO);
			setM_Product_ID (0);
			setProcessed (false);
			setScrappedQty_Vol (Env.ZERO);
			setTargetQty_Vol (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_PedidoBaseDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PedidoBaseDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Confirmed Quantity.
		@param ConfirmedQty 
		Confirmation of a received quantity
	  */
	public void setConfirmedQty (int ConfirmedQty)
	{
		set_Value (COLUMNNAME_ConfirmedQty, Integer.valueOf(ConfirmedQty));
	}

	/** Get Confirmed Quantity.
		@return Confirmation of a received quantity
	  */
	public int getConfirmedQty () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConfirmedQty);
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

	/** Set Detail Base Order.
		@param EXME_PedidoBaseDet_ID 
		Detail Base Order
	  */
	public void setEXME_PedidoBaseDet_ID (int EXME_PedidoBaseDet_ID)
	{
		if (EXME_PedidoBaseDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PedidoBaseDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PedidoBaseDet_ID, Integer.valueOf(EXME_PedidoBaseDet_ID));
	}

	/** Get Detail Base Order.
		@return Detail Base Order
	  */
	public int getEXME_PedidoBaseDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PedidoBaseDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Base Order.
		@param EXME_PedidoBase_ID 
		Base Order
	  */
	public void setEXME_PedidoBase_ID (int EXME_PedidoBase_ID)
	{
		if (EXME_PedidoBase_ID < 1)
			 throw new IllegalArgumentException ("EXME_PedidoBase_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PedidoBase_ID, Integer.valueOf(EXME_PedidoBase_ID));
	}

	/** Get Base Order.
		@return Base Order
	  */
	public int getEXME_PedidoBase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PedidoBase_ID);
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

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			 throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locator To.
		@param M_LocatorTo_ID 
		Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID)
	{
		if (M_LocatorTo_ID < 1)
			 throw new IllegalArgumentException ("M_LocatorTo_ID is mandatory.");
		set_Value (COLUMNNAME_M_LocatorTo_ID, Integer.valueOf(M_LocatorTo_ID));
	}

	/** Get Locator To.
		@return Location inventory is moved to
	  */
	public int getM_LocatorTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LocatorTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Quantity.
		@param MovementQty 
		Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty)
	{
		if (MovementQty == null)
			throw new IllegalArgumentException ("MovementQty is mandatory.");
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Movement Qty Pack.
		@param MovementQty_Vol 
		Quantity of a product moved in UOM Pack
	  */
	public void setMovementQty_Vol (BigDecimal MovementQty_Vol)
	{
		if (MovementQty_Vol == null)
			throw new IllegalArgumentException ("MovementQty_Vol is mandatory.");
		set_Value (COLUMNNAME_MovementQty_Vol, MovementQty_Vol);
	}

	/** Get Movement Qty Pack.
		@return Quantity of a product moved in UOM Pack
	  */
	public BigDecimal getMovementQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set UOM Selection.
		@param Op_Uom 
		UOM Selection
	  */
	public void setOp_Uom (int Op_Uom)
	{
		set_Value (COLUMNNAME_Op_Uom, Integer.valueOf(Op_Uom));
	}

	/** Get UOM Selection.
		@return UOM Selection
	  */
	public int getOp_Uom () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Op_Uom);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Scrapped Quantity.
		@param ScrappedQty 
		The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (int ScrappedQty)
	{
		set_Value (COLUMNNAME_ScrappedQty, Integer.valueOf(ScrappedQty));
	}

	/** Get Scrapped Quantity.
		@return The Quantity scrapped due to QA issues
	  */
	public int getScrappedQty () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ScrappedQty);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scrapped Qty Pack.
		@param ScrappedQty_Vol 
		The Quantity scrapped due to QA issues in UOM Pack
	  */
	public void setScrappedQty_Vol (BigDecimal ScrappedQty_Vol)
	{
		if (ScrappedQty_Vol == null)
			throw new IllegalArgumentException ("ScrappedQty_Vol is mandatory.");
		set_Value (COLUMNNAME_ScrappedQty_Vol, ScrappedQty_Vol);
	}

	/** Get Scrapped Qty Pack.
		@return The Quantity scrapped due to QA issues in UOM Pack
	  */
	public BigDecimal getScrappedQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScrappedQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Target Quantity.
		@param TargetQty 
		Target Movement Quantity
	  */
	public void setTargetQty (int TargetQty)
	{
		set_Value (COLUMNNAME_TargetQty, Integer.valueOf(TargetQty));
	}

	/** Get Target Quantity.
		@return Target Movement Quantity
	  */
	public int getTargetQty () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TargetQty);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Target Qty Pack.
		@param TargetQty_Vol 
		Target Quantity Pack
	  */
	public void setTargetQty_Vol (BigDecimal TargetQty_Vol)
	{
		if (TargetQty_Vol == null)
			throw new IllegalArgumentException ("TargetQty_Vol is mandatory.");
		set_Value (COLUMNNAME_TargetQty_Vol, TargetQty_Vol);
	}

	/** Get Target Qty Pack.
		@return Target Quantity Pack
	  */
	public BigDecimal getTargetQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TargetQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}