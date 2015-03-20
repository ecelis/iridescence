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

/** Generated Model for EXME_T_SaldoCtaPacDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_SaldoCtaPacDet extends PO implements I_EXME_T_SaldoCtaPacDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_SaldoCtaPacDet (Properties ctx, int EXME_T_SaldoCtaPacDet_ID, String trxName)
    {
      super (ctx, EXME_T_SaldoCtaPacDet_ID, trxName);
      /** if (EXME_T_SaldoCtaPacDet_ID == 0)
        {
			setAD_Session_ID (0);
			setEXME_T_SaldoCtaPacDet_ID (0);
			setEXME_T_SaldoCtaPac_ID (0);
			setM_Product_Category_ID (0);
			setSaldo (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_SaldoCtaPacDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_SaldoCtaPacDet[")
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

	/** Set Temporal Balance Details of the Patient's Account.
		@param EXME_T_SaldoCtaPacDet_ID 
		Temporal Balance Details of the Patient's Account
	  */
	public void setEXME_T_SaldoCtaPacDet_ID (int EXME_T_SaldoCtaPacDet_ID)
	{
		if (EXME_T_SaldoCtaPacDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_SaldoCtaPacDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_SaldoCtaPacDet_ID, Integer.valueOf(EXME_T_SaldoCtaPacDet_ID));
	}

	/** Get Temporal Balance Details of the Patient's Account.
		@return Temporal Balance Details of the Patient's Account
	  */
	public int getEXME_T_SaldoCtaPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_SaldoCtaPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_T_SaldoCtaPac getEXME_T_SaldoCtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_T_SaldoCtaPac.Table_Name);
        I_EXME_T_SaldoCtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_T_SaldoCtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_T_SaldoCtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account Balance.
		@param EXME_T_SaldoCtaPac_ID 
		The Balance of the Patient Account
	  */
	public void setEXME_T_SaldoCtaPac_ID (int EXME_T_SaldoCtaPac_ID)
	{
		if (EXME_T_SaldoCtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_SaldoCtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_T_SaldoCtaPac_ID, Integer.valueOf(EXME_T_SaldoCtaPac_ID));
	}

	/** Get Patient Account Balance.
		@return The Balance of the Patient Account
	  */
	public int getEXME_T_SaldoCtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_SaldoCtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product_Category getM_Product_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1)
			 throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Balance.
		@param Saldo 
		Balance of Patient Account
	  */
	public void setSaldo (BigDecimal Saldo)
	{
		if (Saldo == null)
			throw new IllegalArgumentException ("Saldo is mandatory.");
		set_Value (COLUMNNAME_Saldo, Saldo);
	}

	/** Get Balance.
		@return Balance of Patient Account
	  */
	public BigDecimal getSaldo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Saldo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}