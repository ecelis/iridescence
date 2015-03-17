/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_CtaPacPag
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtaPacPag extends PO implements I_EXME_CtaPacPag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacPag (Properties ctx, int EXME_CtaPacPag_ID, String trxName)
    {
      super (ctx, EXME_CtaPacPag_ID, trxName);
      /** if (EXME_CtaPacPag_ID == 0)
        {
			setC_Payment_ID (0);
			setEXME_CtaPacExt_ID (0);
			setEXME_CtaPacPag_ID (0);
			setIsPay (false);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacPag (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacPag[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Applied Amount.
		@param Aplicado 
		Applied Amount
	  */
	public void setAplicado (BigDecimal Aplicado)
	{
		set_Value (COLUMNNAME_Aplicado, Aplicado);
	}

	/** Get Applied Amount.
		@return Applied Amount
	  */
	public BigDecimal getAplicado () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Aplicado);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1)
			 throw new IllegalArgumentException ("C_Payment_ID is mandatory.");
		set_Value (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Available.
		@param Disponible 
		Available
	  */
	public void setDisponible (BigDecimal Disponible)
	{
		set_Value (COLUMNNAME_Disponible, Disponible);
	}

	/** Get Available.
		@return Available
	  */
	public BigDecimal getDisponible () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Disponible);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Encounter Extension.
		@param EXME_CtaPacExt_ID 
		Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacExt_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Encounter Extension.
		@return Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Payment.
		@param EXME_CtaPacPag_ID 
		Indentifier of patient acct payment
	  */
	public void setEXME_CtaPacPag_ID (int EXME_CtaPacPag_ID)
	{
		if (EXME_CtaPacPag_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacPag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacPag_ID, Integer.valueOf(EXME_CtaPacPag_ID));
	}

	/** Get Encounter Payment.
		@return Indentifier of patient acct payment
	  */
	public int getEXME_CtaPacPag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacPag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pay.
		@param IsPay 
		Registry is pay
	  */
	public void setIsPay (boolean IsPay)
	{
		set_Value (COLUMNNAME_IsPay, Boolean.valueOf(IsPay));
	}

	/** Get Pay.
		@return Registry is pay
	  */
	public boolean isPay () 
	{
		Object oo = get_Value(COLUMNNAME_IsPay);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Was Returned.
		@param SeDevolvio Was Returned	  */
	public void setSeDevolvio (boolean SeDevolvio)
	{
		set_Value (COLUMNNAME_SeDevolvio, Boolean.valueOf(SeDevolvio));
	}

	/** Get Was Returned.
		@return Was Returned	  */
	public boolean isSeDevolvio () 
	{
		Object oo = get_Value(COLUMNNAME_SeDevolvio);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}