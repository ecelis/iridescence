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

/** Generated Model for EXME_Buyer
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Buyer extends PO implements I_EXME_Buyer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Buyer (Properties ctx, int EXME_Buyer_ID, String trxName)
    {
      super (ctx, EXME_Buyer_ID, trxName);
      /** if (EXME_Buyer_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_Buyer_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Buyer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Buyer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ad_usersuperior_id.
		@param ad_usersuperior_id ad_usersuperior_id	  */
	public void setad_usersuperior_id (int ad_usersuperior_id)
	{
		set_Value (COLUMNNAME_ad_usersuperior_id, Integer.valueOf(ad_usersuperior_id));
	}

	/** Get ad_usersuperior_id.
		@return ad_usersuperior_id	  */
	public int getad_usersuperior_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ad_usersuperior_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Buyer user.
		@param EXME_Buyer_ID Buyer user	  */
	public void setEXME_Buyer_ID (int EXME_Buyer_ID)
	{
		if (EXME_Buyer_ID < 1)
			 throw new IllegalArgumentException ("EXME_Buyer_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Buyer_ID, Integer.valueOf(EXME_Buyer_ID));
	}

	/** Get Buyer user.
		@return Buyer user	  */
	public int getEXME_Buyer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Buyer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set qtyauthorized.
		@param qtyauthorized qtyauthorized	  */
	public void setqtyauthorized (BigDecimal qtyauthorized)
	{
		set_Value (COLUMNNAME_qtyauthorized, qtyauthorized);
	}

	/** Get qtyauthorized.
		@return qtyauthorized	  */
	public BigDecimal getqtyauthorized () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_qtyauthorized);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}