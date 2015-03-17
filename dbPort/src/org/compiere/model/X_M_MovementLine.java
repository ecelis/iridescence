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

/** Generated Model for M_MovementLine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_MovementLine extends PO implements I_M_MovementLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_MovementLine (Properties ctx, int M_MovementLine_ID, String trxName)
    {
      super (ctx, M_MovementLine_ID, trxName);
      /** if (M_MovementLine_ID == 0)
        {
			setConfirmedQty_Vol (Env.ZERO);
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_MovementLine WHERE M_Movement_ID=@M_Movement_ID@
			setM_AttributeSetInstance_ID (0);
			setM_Locator_ID (0);
// @M_Locator_ID@
			setM_LocatorTo_ID (0);
// @M_LocatorTo_ID@
			setM_Movement_ID (0);
			setM_MovementLine_ID (0);
			setMovementQty (Env.ZERO);
// 1
			setMovementQty_Vol (Env.ZERO);
			setM_Product_ID (0);
			setOriginalQty_Vol (Env.ZERO);
			setPriceList (Env.ZERO);
			setProcessed (false);
			setReturnQty (Env.ZERO);
			setReturnQty_Vol (Env.ZERO);
			setScrappedQty_Vol (Env.ZERO);
			setTargetQty (Env.ZERO);
// 0
			setTargetQty_Vol (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_MovementLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_MovementLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Confirmed Quantity.
		@param ConfirmedQty 
		Confirmation of a received quantity
	  */
	public void setConfirmedQty (BigDecimal ConfirmedQty)
	{
		set_Value (COLUMNNAME_ConfirmedQty, ConfirmedQty);
	}

	/** Get Confirmed Quantity.
		@return Confirmation of a received quantity
	  */
	public BigDecimal getConfirmedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ConfirmedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Confirmed Qty Pack.
		@param ConfirmedQty_Vol 
		Confirmation of a received quantity in the Pack UOM
	  */
	public void setConfirmedQty_Vol (BigDecimal ConfirmedQty_Vol)
	{
		if (ConfirmedQty_Vol == null)
			throw new IllegalArgumentException ("ConfirmedQty_Vol is mandatory.");
		set_Value (COLUMNNAME_ConfirmedQty_Vol, ConfirmedQty_Vol);
	}

	/** Get Confirmed Qty Pack.
		@return Confirmation of a received quantity in the Pack UOM
	  */
	public BigDecimal getConfirmedQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ConfirmedQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_DD_OrderLine getDD_OrderLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_DD_OrderLine.Table_Name);
        I_DD_OrderLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_DD_OrderLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getDD_OrderLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Distribution Order Line.
		@param DD_OrderLine_ID Distribution Order Line	  */
	public void setDD_OrderLine_ID (int DD_OrderLine_ID)
	{
		if (DD_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_DD_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_DD_OrderLine_ID, Integer.valueOf(DD_OrderLine_ID));
	}

	/** Get Distribution Order Line.
		@return Distribution Order Line	  */
	public int getDD_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_OrderLine_ID);
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product class.
		@param EXME_ProductClassFact_ID 
		Product class
	  */
	public void setEXME_ProductClassFact_ID (int EXME_ProductClassFact_ID)
	{
		if (EXME_ProductClassFact_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProductClassFact_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProductClassFact_ID, Integer.valueOf(EXME_ProductClassFact_ID));
	}

	/** Get Product class.
		@return Product class
	  */
	public int getEXME_ProductClassFact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductClassFact_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getLine()));
    }

	/** Set Lot's information.
		@param LotInfo Lot's information	  */
	public void setLotInfo (String LotInfo)
	{
		set_Value (COLUMNNAME_LotInfo, LotInfo);
	}

	/** Get Lot's information.
		@return Lot's information	  */
	public String getLotInfo () 
	{
		return (String)get_Value(COLUMNNAME_LotInfo);
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0)
			 throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
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

	/** Set Attribute Set Instance To.
		@param M_AttributeSetInstanceTo_ID 
		Target Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstanceTo_ID (int M_AttributeSetInstanceTo_ID)
	{
		if (M_AttributeSetInstanceTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstanceTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstanceTo_ID, Integer.valueOf(M_AttributeSetInstanceTo_ID));
	}

	/** Get Attribute Set Instance To.
		@return Target Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstanceTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstanceTo_ID);
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

	public I_M_Movement getM_Movement() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Movement.Table_Name);
        I_M_Movement result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Movement)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Movement_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Inventory Move.
		@param M_Movement_ID 
		Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID)
	{
		if (M_Movement_ID < 1)
			 throw new IllegalArgumentException ("M_Movement_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Movement_ID, Integer.valueOf(M_Movement_ID));
	}

	/** Get Inventory Move.
		@return Movement of Inventory
	  */
	public int getM_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1)
			 throw new IllegalArgumentException ("M_MovementLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
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

	/** Set Serial number.
		@param NumSerie Serial number	  */
	public void setNumSerie (String NumSerie)
	{
		set_Value (COLUMNNAME_NumSerie, NumSerie);
	}

	/** Get Serial number.
		@return Serial number	  */
	public String getNumSerie () 
	{
		return (String)get_Value(COLUMNNAME_NumSerie);
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

	/** Set Original Quantity.
		@param OriginalQty Original Quantity	  */
	public void setOriginalQty (BigDecimal OriginalQty)
	{
		set_Value (COLUMNNAME_OriginalQty, OriginalQty);
	}

	/** Get Original Quantity.
		@return Original Quantity	  */
	public BigDecimal getOriginalQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OriginalQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Original Quantity Pack.
		@param OriginalQty_Vol Original Quantity Pack	  */
	public void setOriginalQty_Vol (BigDecimal OriginalQty_Vol)
	{
		if (OriginalQty_Vol == null)
			throw new IllegalArgumentException ("OriginalQty_Vol is mandatory.");
		set_Value (COLUMNNAME_OriginalQty_Vol, OriginalQty_Vol);
	}

	/** Get Original Quantity Pack.
		@return Original Quantity Pack	  */
	public BigDecimal getOriginalQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OriginalQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		if (PriceList == null)
			throw new IllegalArgumentException ("PriceList is mandatory.");
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
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

	/** Set Reference to the line of movement.
		@param Ref_MovementLine_ID Reference to the line of movement	  */
	public void setRef_MovementLine_ID (int Ref_MovementLine_ID)
	{
		if (Ref_MovementLine_ID < 1) 
			set_Value (COLUMNNAME_Ref_MovementLine_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_MovementLine_ID, Integer.valueOf(Ref_MovementLine_ID));
	}

	/** Get Reference to the line of movement.
		@return Reference to the line of movement	  */
	public int getRef_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Return quantity.
		@param ReturnQty Return quantity	  */
	public void setReturnQty (BigDecimal ReturnQty)
	{
		if (ReturnQty == null)
			throw new IllegalArgumentException ("ReturnQty is mandatory.");
		set_Value (COLUMNNAME_ReturnQty, ReturnQty);
	}

	/** Get Return quantity.
		@return Return quantity	  */
	public BigDecimal getReturnQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ReturnQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Return quantity (Pack).
		@param ReturnQty_Vol Return quantity (Pack)	  */
	public void setReturnQty_Vol (BigDecimal ReturnQty_Vol)
	{
		if (ReturnQty_Vol == null)
			throw new IllegalArgumentException ("ReturnQty_Vol is mandatory.");
		set_Value (COLUMNNAME_ReturnQty_Vol, ReturnQty_Vol);
	}

	/** Get Return quantity (Pack).
		@return Return quantity (Pack)	  */
	public BigDecimal getReturnQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ReturnQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reversal Line.
		@param ReversalLine_ID 
		Use to keep the reversal line ID for reversing costing purpose
	  */
	public void setReversalLine_ID (int ReversalLine_ID)
	{
		if (ReversalLine_ID < 1) 
			set_Value (COLUMNNAME_ReversalLine_ID, null);
		else 
			set_Value (COLUMNNAME_ReversalLine_ID, Integer.valueOf(ReversalLine_ID));
	}

	/** Get Reversal Line.
		@return Use to keep the reversal line ID for reversing costing purpose
	  */
	public int getReversalLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReversalLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scrapped Quantity.
		@param ScrappedQty 
		The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (BigDecimal ScrappedQty)
	{
		set_Value (COLUMNNAME_ScrappedQty, ScrappedQty);
	}

	/** Get Scrapped Quantity.
		@return The Quantity scrapped due to QA issues
	  */
	public BigDecimal getScrappedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScrappedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
	public void setTargetQty (BigDecimal TargetQty)
	{
		if (TargetQty == null)
			throw new IllegalArgumentException ("TargetQty is mandatory.");
		set_Value (COLUMNNAME_TargetQty, TargetQty);
	}

	/** Get Target Quantity.
		@return Target Movement Quantity
	  */
	public BigDecimal getTargetQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TargetQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		throw new IllegalArgumentException ("Value is virtual column");	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}