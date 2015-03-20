/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_CreditCardRel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CreditCardRel extends PO implements I_EXME_CreditCardRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CreditCardRel (Properties ctx, int EXME_CreditCardRel_ID, String trxName)
    {
      super (ctx, EXME_CreditCardRel_ID, trxName);
      /** if (EXME_CreditCardRel_ID == 0)
        {
			setEXME_CreditCardRel_ID (0);
			setEXME_FormaPago_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CreditCardRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CreditCardRel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Commission %.
		@param Commission 
		Commission stated as a percentage
	  */
	public void setCommission (BigDecimal Commission)
	{
		set_Value (COLUMNNAME_Commission, Commission);
	}

	/** Get Commission %.
		@return Commission stated as a percentage
	  */
	public BigDecimal getCommission () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Commission);
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

	/** Set CreditCardRel.
		@param EXME_CreditCardRel_ID 
		Credit Card Relation
	  */
	public void setEXME_CreditCardRel_ID (int EXME_CreditCardRel_ID)
	{
		if (EXME_CreditCardRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_CreditCardRel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CreditCardRel_ID, Integer.valueOf(EXME_CreditCardRel_ID));
	}

	/** Get CreditCardRel.
		@return Credit Card Relation
	  */
	public int getEXME_CreditCardRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CreditCardRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment Form.
		@param EXME_FormaPago_ID 
		Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID)
	{
		if (EXME_FormaPago_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormaPago_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FormaPago_ID, Integer.valueOf(EXME_FormaPago_ID));
	}

	/** Get Payment Form.
		@return Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormaPago_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Commission %.
		@param IVA 
		Tax Commission %
	  */
	public void setIVA (BigDecimal IVA)
	{
		set_Value (COLUMNNAME_IVA, IVA);
	}

	/** Get Tax Commission %.
		@return Tax Commission %
	  */
	public BigDecimal getIVA () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IVA);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax Comission.
		@param IVAComision 
		Tax Comission
	  */
	public void setIVAComision (String IVAComision)
	{
		set_Value (COLUMNNAME_IVAComision, IVAComision);
	}

	/** Get Tax Comission.
		@return Tax Comission
	  */
	public String getIVAComision () 
	{
		return (String)get_Value(COLUMNNAME_IVAComision);
	}

	/** Set Commission Amount.
		@param MontoComision 
		Commission Amount
	  */
	public void setMontoComision (String MontoComision)
	{
		set_Value (COLUMNNAME_MontoComision, MontoComision);
	}

	/** Get Commission Amount.
		@return Commission Amount
	  */
	public String getMontoComision () 
	{
		return (String)get_Value(COLUMNNAME_MontoComision);
	}

	/** Set Operation Amount.
		@param MontoOperacion 
		Operation Amount
	  */
	public void setMontoOperacion (String MontoOperacion)
	{
		set_Value (COLUMNNAME_MontoOperacion, MontoOperacion);
	}

	/** Get Operation Amount.
		@return Operation Amount
	  */
	public String getMontoOperacion () 
	{
		return (String)get_Value(COLUMNNAME_MontoOperacion);
	}
}