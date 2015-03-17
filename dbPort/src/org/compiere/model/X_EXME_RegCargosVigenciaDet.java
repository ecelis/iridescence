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

/** Generated Model for EXME_RegCargosVigenciaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RegCargosVigenciaDet extends PO implements I_EXME_RegCargosVigenciaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RegCargosVigenciaDet (Properties ctx, int EXME_RegCargosVigenciaDet_ID, String trxName)
    {
      super (ctx, EXME_RegCargosVigenciaDet_ID, trxName);
      /** if (EXME_RegCargosVigenciaDet_ID == 0)
        {
			setEXME_RegCargosVigenciaDet_ID (0);
			setEXME_RegCargosVigencia_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RegCargosVigenciaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RegCargosVigenciaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Detail of Charges to Patient.
		@param EXME_RegCargosVigenciaDet_ID Detail of Charges to Patient	  */
	public void setEXME_RegCargosVigenciaDet_ID (int EXME_RegCargosVigenciaDet_ID)
	{
		if (EXME_RegCargosVigenciaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_RegCargosVigenciaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RegCargosVigenciaDet_ID, Integer.valueOf(EXME_RegCargosVigenciaDet_ID));
	}

	/** Get Detail of Charges to Patient.
		@return Detail of Charges to Patient	  */
	public int getEXME_RegCargosVigenciaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RegCargosVigenciaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RegCargosVigencia getEXME_RegCargosVigencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RegCargosVigencia.Table_Name);
        I_EXME_RegCargosVigencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RegCargosVigencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RegCargosVigencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Officials Patient records Funded.
		@param EXME_RegCargosVigencia_ID Officials Patient records Funded	  */
	public void setEXME_RegCargosVigencia_ID (int EXME_RegCargosVigencia_ID)
	{
		if (EXME_RegCargosVigencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_RegCargosVigencia_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_RegCargosVigencia_ID, Integer.valueOf(EXME_RegCargosVigencia_ID));
	}

	/** Get Officials Patient records Funded.
		@return Officials Patient records Funded	  */
	public int getEXME_RegCargosVigencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RegCargosVigencia_ID);
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

	/** Set Pending Quantity.
		@param QtyPendiente 
		Pending Quantity
	  */
	public void setQtyPendiente (BigDecimal QtyPendiente)
	{
		set_Value (COLUMNNAME_QtyPendiente, QtyPendiente);
	}

	/** Get Pending Quantity.
		@return Pending Quantity
	  */
	public BigDecimal getQtyPendiente () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPendiente);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}