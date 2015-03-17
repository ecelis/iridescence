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

/** Generated Model for EXME_ClaimCharges
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimCharges extends PO implements I_EXME_ClaimCharges, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimCharges (Properties ctx, int EXME_ClaimCharges_ID, String trxName)
    {
      super (ctx, EXME_ClaimCharges_ID, trxName);
      /** if (EXME_ClaimCharges_ID == 0)
        {
			setCode (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimCharges (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimCharges[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Charge amount.
		@param ChargeAmt 
		Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt)
	{
		set_ValueNoCheck (COLUMNNAME_ChargeAmt, ChargeAmt);
	}

	/** Get Charge amount.
		@return Charge Amount
	  */
	public BigDecimal getChargeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChargeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		if (Code == null)
			throw new IllegalArgumentException ("Code is mandatory.");
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set Date Delivered.
		@param DateDelivered 
		Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_DateDelivered, DateDelivered);
	}

	/** Get Date Delivered.
		@return Date when the product was delivered
	  */
	public Timestamp getDateDelivered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDelivered);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set EXME_ClaimCharges_ID.
		@param EXME_ClaimCharges_ID EXME_ClaimCharges_ID	  */
	public void setEXME_ClaimCharges_ID (int EXME_ClaimCharges_ID)
	{
		if (EXME_ClaimCharges_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_ClaimCharges_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_ClaimCharges_ID, Integer.valueOf(EXME_ClaimCharges_ID));
	}

	/** Get EXME_ClaimCharges_ID.
		@return EXME_ClaimCharges_ID	  */
	public int getEXME_ClaimCharges_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimCharges_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
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

	/** Set List Details.
		@param ListDetails 
		List document details
	  */
	public void setListDetails (String ListDetails)
	{
		set_Value (COLUMNNAME_ListDetails, ListDetails);
	}

	/** Get List Details.
		@return List document details
	  */
	public String getListDetails () 
	{
		return (String)get_Value(COLUMNNAME_ListDetails);
	}

	/** Set Non_Covered_Charges.
		@param Non_Covered_Charges Non_Covered_Charges	  */
	public void setNon_Covered_Charges (BigDecimal Non_Covered_Charges)
	{
		set_ValueNoCheck (COLUMNNAME_Non_Covered_Charges, Non_Covered_Charges);
	}

	/** Get Non_Covered_Charges.
		@return Non_Covered_Charges	  */
	public BigDecimal getNon_Covered_Charges () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Non_Covered_Charges);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_ValueNoCheck (COLUMNNAME_Qty, Qty);
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

	/** Set Revenue Code.
		@param RevenueCode 
		Revenue Code
	  */
	public void setRevenueCode (String RevenueCode)
	{
		set_ValueNoCheck (COLUMNNAME_RevenueCode, RevenueCode);
	}

	/** Get Revenue Code.
		@return Revenue Code
	  */
	public String getRevenueCode () 
	{
		return (String)get_Value(COLUMNNAME_RevenueCode);
	}
}