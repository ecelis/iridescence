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

/** Generated Model for M_MovementLineConfirm
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_MovementLineConfirm extends PO implements I_M_MovementLineConfirm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_MovementLineConfirm (Properties ctx, int M_MovementLineConfirm_ID, String trxName)
    {
      super (ctx, M_MovementLineConfirm_ID, trxName);
      /** if (M_MovementLineConfirm_ID == 0)
        {
			setConfirmedQty (Env.ZERO);
			setConfirmedQty_Vol (Env.ZERO);
			setDifferenceQty (Env.ZERO);
			setDifferenceQty_Vol (Env.ZERO);
			setM_MovementConfirm_ID (0);
			setM_MovementLineConfirm_ID (0);
			setM_MovementLine_ID (0);
			setProcessed (false);
			setScrappedQty (Env.ZERO);
			setScrappedQty_Vol (Env.ZERO);
			setTargetQty (Env.ZERO);
			setTargetQty_Vol (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_MovementLineConfirm (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_MovementLineConfirm[")
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
		if (ConfirmedQty == null)
			throw new IllegalArgumentException ("ConfirmedQty is mandatory.");
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

	/** Set Difference.
		@param DifferenceQty 
		Difference Quantity
	  */
	public void setDifferenceQty (BigDecimal DifferenceQty)
	{
		if (DifferenceQty == null)
			throw new IllegalArgumentException ("DifferenceQty is mandatory.");
		set_Value (COLUMNNAME_DifferenceQty, DifferenceQty);
	}

	/** Get Difference.
		@return Difference Quantity
	  */
	public BigDecimal getDifferenceQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DifferenceQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Difference Qty Pack.
		@param DifferenceQty_Vol Difference Qty Pack	  */
	public void setDifferenceQty_Vol (BigDecimal DifferenceQty_Vol)
	{
		if (DifferenceQty_Vol == null)
			throw new IllegalArgumentException ("DifferenceQty_Vol is mandatory.");
		set_Value (COLUMNNAME_DifferenceQty_Vol, DifferenceQty_Vol);
	}

	/** Get Difference Qty Pack.
		@return Difference Qty Pack	  */
	public BigDecimal getDifferenceQty_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DifferenceQty_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_InventoryLine getM_InventoryLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_InventoryLine.Table_Name);
        I_M_InventoryLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_InventoryLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_InventoryLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Phys.Inventory Line.
		@param M_InventoryLine_ID 
		Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID)
	{
		if (M_InventoryLine_ID < 1) 
			set_Value (COLUMNNAME_M_InventoryLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InventoryLine_ID, Integer.valueOf(M_InventoryLine_ID));
	}

	/** Get Phys.Inventory Line.
		@return Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InventoryLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_MovementConfirm getM_MovementConfirm() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_MovementConfirm.Table_Name);
        I_M_MovementConfirm result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_MovementConfirm)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_MovementConfirm_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Move Confirm.
		@param M_MovementConfirm_ID 
		Inventory Move Confirmation
	  */
	public void setM_MovementConfirm_ID (int M_MovementConfirm_ID)
	{
		if (M_MovementConfirm_ID < 1)
			 throw new IllegalArgumentException ("M_MovementConfirm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_MovementConfirm_ID, Integer.valueOf(M_MovementConfirm_ID));
	}

	/** Get Move Confirm.
		@return Inventory Move Confirmation
	  */
	public int getM_MovementConfirm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementConfirm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_MovementConfirm_ID()));
    }

	/** Set Move Line Confirm.
		@param M_MovementLineConfirm_ID 
		Inventory Move Line Confirmation
	  */
	public void setM_MovementLineConfirm_ID (int M_MovementLineConfirm_ID)
	{
		if (M_MovementLineConfirm_ID < 1)
			 throw new IllegalArgumentException ("M_MovementLineConfirm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_MovementLineConfirm_ID, Integer.valueOf(M_MovementLineConfirm_ID));
	}

	/** Get Move Line Confirm.
		@return Inventory Move Line Confirmation
	  */
	public int getM_MovementLineConfirm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLineConfirm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_MovementLine getM_MovementLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_MovementLine.Table_Name);
        I_M_MovementLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_MovementLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_MovementLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1)
			 throw new IllegalArgumentException ("M_MovementLine_ID is mandatory.");
		set_Value (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
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
	public void setScrappedQty (BigDecimal ScrappedQty)
	{
		if (ScrappedQty == null)
			throw new IllegalArgumentException ("ScrappedQty is mandatory.");
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
}