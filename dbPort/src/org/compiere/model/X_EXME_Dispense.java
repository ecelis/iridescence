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

/** Generated Model for EXME_Dispense
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Dispense extends PO implements I_EXME_Dispense, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Dispense (Properties ctx, int EXME_Dispense_ID, String trxName)
    {
      super (ctx, EXME_Dispense_ID, trxName);
      /** if (EXME_Dispense_ID == 0)
        {
			setDeliveredQty (Env.ZERO);
			setEXME_Dispense_ID (0);
			setEXME_Pharmacist_ID (0);
			setEXME_PrescRXDet_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setUndeliveredQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_Dispense (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Dispense[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Delivered qty.
		@param DeliveredQty Delivered qty	  */
	public void setDeliveredQty (BigDecimal DeliveredQty)
	{
		if (DeliveredQty == null)
			throw new IllegalArgumentException ("DeliveredQty is mandatory.");
		set_Value (COLUMNNAME_DeliveredQty, DeliveredQty);
	}

	/** Get Delivered qty.
		@return Delivered qty	  */
	public BigDecimal getDeliveredQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeliveredQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Dispense.
		@param EXME_Dispense_ID Dispense	  */
	public void setEXME_Dispense_ID (int EXME_Dispense_ID)
	{
		if (EXME_Dispense_ID < 1)
			 throw new IllegalArgumentException ("EXME_Dispense_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Dispense_ID, Integer.valueOf(EXME_Dispense_ID));
	}

	/** Get Dispense.
		@return Dispense	  */
	public int getEXME_Dispense_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dispense_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Pharmacist getEXME_Pharmacist() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pharmacist.Table_Name);
        I_EXME_Pharmacist result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pharmacist)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pharmacist_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacist.
		@param EXME_Pharmacist_ID Pharmacist	  */
	public void setEXME_Pharmacist_ID (int EXME_Pharmacist_ID)
	{
		if (EXME_Pharmacist_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pharmacist_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Pharmacist_ID, Integer.valueOf(EXME_Pharmacist_ID));
	}

	/** Get Pharmacist.
		@return Pharmacist	  */
	public int getEXME_Pharmacist_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pharmacist_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PrescRXDet getEXME_PrescRXDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PrescRXDet.Table_Name);
        I_EXME_PrescRXDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PrescRXDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PrescRXDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set RXNorm Prescription Detail.
		@param EXME_PrescRXDet_ID 
		RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID)
	{
		if (EXME_PrescRXDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescRXDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PrescRXDet_ID, Integer.valueOf(EXME_PrescRXDet_ID));
	}

	/** Get RXNorm Prescription Detail.
		@return RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescRXDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Undelivered Qty.
		@param UndeliveredQty Undelivered Qty	  */
	public void setUndeliveredQty (BigDecimal UndeliveredQty)
	{
		if (UndeliveredQty == null)
			throw new IllegalArgumentException ("UndeliveredQty is mandatory.");
		set_Value (COLUMNNAME_UndeliveredQty, UndeliveredQty);
	}

	/** Get Undelivered Qty.
		@return Undelivered Qty	  */
	public BigDecimal getUndeliveredQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UndeliveredQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}