/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_ConfigEspecial
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigEspecial extends PO implements I_EXME_ConfigEspecial, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigEspecial (Properties ctx, int EXME_ConfigEspecial_ID, String trxName)
    {
      super (ctx, EXME_ConfigEspecial_ID, trxName);
      /** if (EXME_ConfigEspecial_ID == 0)
        {
			setAutorizaAseg (false);
			setCaptDatosLab (false);
			setCheckVoucher (false);
			setEditValuePac (false);
			setEXME_ConfigEspecial_ID (0);
			setFactAlmOrg (false);
// N
			setIsDonativo (false);
// N
			setIsPension (false);
// N
			setLoginFact (false);
// N
			setPrintPreFac (false);
			setPrintRecibo (false);
			setRutasServ (false);
			setTipoCliente (false);
			setVerInfMilitar (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigEspecial (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigEspecial[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Require Insurer Authorization.
		@param AutorizaAseg 
		Require Insurer Authorization
	  */
	public void setAutorizaAseg (boolean AutorizaAseg)
	{
		set_Value (COLUMNNAME_AutorizaAseg, Boolean.valueOf(AutorizaAseg));
	}

	/** Get Require Insurer Authorization.
		@return Require Insurer Authorization
	  */
	public boolean isAutorizaAseg () 
	{
		Object oo = get_Value(COLUMNNAME_AutorizaAseg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Capture Labor Data.
		@param CaptDatosLab 
		Capture Labor Data
	  */
	public void setCaptDatosLab (boolean CaptDatosLab)
	{
		set_Value (COLUMNNAME_CaptDatosLab, Boolean.valueOf(CaptDatosLab));
	}

	/** Get Capture Labor Data.
		@return Capture Labor Data
	  */
	public boolean isCaptDatosLab () 
	{
		Object oo = get_Value(COLUMNNAME_CaptDatosLab);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requires Check Insurer and Vouchers.
		@param CheckVoucher Requires Check Insurer and Vouchers	  */
	public void setCheckVoucher (boolean CheckVoucher)
	{
		set_Value (COLUMNNAME_CheckVoucher, Boolean.valueOf(CheckVoucher));
	}

	/** Get Requires Check Insurer and Vouchers.
		@return Requires Check Insurer and Vouchers	  */
	public boolean isCheckVoucher () 
	{
		Object oo = get_Value(COLUMNNAME_CheckVoucher);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Edit Patient History.
		@param EditValuePac Edit Patient History	  */
	public void setEditValuePac (boolean EditValuePac)
	{
		set_Value (COLUMNNAME_EditValuePac, Boolean.valueOf(EditValuePac));
	}

	/** Get Edit Patient History.
		@return Edit Patient History	  */
	public boolean isEditValuePac () 
	{
		Object oo = get_Value(COLUMNNAME_EditValuePac);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Special Configuration.
		@param EXME_ConfigEspecial_ID 
		Special Configuration
	  */
	public void setEXME_ConfigEspecial_ID (int EXME_ConfigEspecial_ID)
	{
		if (EXME_ConfigEspecial_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEspecial_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigEspecial_ID, Integer.valueOf(EXME_ConfigEspecial_ID));
	}

	/** Get Special Configuration.
		@return Special Configuration
	  */
	public int getEXME_ConfigEspecial_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEspecial_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoice origin warehouse.
		@param FactAlmOrg Invoice origin warehouse	  */
	public void setFactAlmOrg (boolean FactAlmOrg)
	{
		set_Value (COLUMNNAME_FactAlmOrg, Boolean.valueOf(FactAlmOrg));
	}

	/** Get Invoice origin warehouse.
		@return Invoice origin warehouse	  */
	public boolean isFactAlmOrg () 
	{
		Object oo = get_Value(COLUMNNAME_FactAlmOrg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Cash Balance Date.
		@param FechaCorte 
		Cash Balance Date
	  */
	public void setFechaCorte (Timestamp FechaCorte)
	{
		set_Value (COLUMNNAME_FechaCorte, FechaCorte);
	}

	/** Get Cash Balance Date.
		@return Cash Balance Date
	  */
	public Timestamp getFechaCorte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCorte);
	}

	/** Set Donation.
		@param IsDonativo Donation	  */
	public void setIsDonativo (boolean IsDonativo)
	{
		set_Value (COLUMNNAME_IsDonativo, Boolean.valueOf(IsDonativo));
	}

	/** Get Donation.
		@return Donation	  */
	public boolean isDonativo () 
	{
		Object oo = get_Value(COLUMNNAME_IsDonativo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pension.
		@param IsPension Pension	  */
	public void setIsPension (boolean IsPension)
	{
		set_Value (COLUMNNAME_IsPension, Boolean.valueOf(IsPension));
	}

	/** Get Pension.
		@return Pension	  */
	public boolean isPension () 
	{
		Object oo = get_Value(COLUMNNAME_IsPension);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Login invoice.
		@param LoginFact Login invoice	  */
	public void setLoginFact (boolean LoginFact)
	{
		set_Value (COLUMNNAME_LoginFact, Boolean.valueOf(LoginFact));
	}

	/** Get Login invoice.
		@return Login invoice	  */
	public boolean isLoginFact () 
	{
		Object oo = get_Value(COLUMNNAME_LoginFact);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Pre-Invoice.
		@param PrintPreFac Print Pre-Invoice	  */
	public void setPrintPreFac (boolean PrintPreFac)
	{
		set_Value (COLUMNNAME_PrintPreFac, Boolean.valueOf(PrintPreFac));
	}

	/** Get Print Pre-Invoice.
		@return Print Pre-Invoice	  */
	public boolean isPrintPreFac () 
	{
		Object oo = get_Value(COLUMNNAME_PrintPreFac);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print receipt.
		@param PrintRecibo Print receipt	  */
	public void setPrintRecibo (boolean PrintRecibo)
	{
		set_Value (COLUMNNAME_PrintRecibo, Boolean.valueOf(PrintRecibo));
	}

	/** Get Print receipt.
		@return Print receipt	  */
	public boolean isPrintRecibo () 
	{
		Object oo = get_Value(COLUMNNAME_PrintRecibo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set RutasServ.
		@param RutasServ RutasServ	  */
	public void setRutasServ (boolean RutasServ)
	{
		set_Value (COLUMNNAME_RutasServ, Boolean.valueOf(RutasServ));
	}

	/** Get RutasServ.
		@return RutasServ	  */
	public boolean isRutasServ () 
	{
		Object oo = get_Value(COLUMNNAME_RutasServ);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Theme.
		@param Theme 
		Theme
	  */
	public void setTheme (String Theme)
	{
		set_Value (COLUMNNAME_Theme, Theme);
	}

	/** Get Theme.
		@return Theme
	  */
	public String getTheme () 
	{
		return (String)get_Value(COLUMNNAME_Theme);
	}

	/** Set Client Type.
		@param TipoCliente Client Type	  */
	public void setTipoCliente (boolean TipoCliente)
	{
		set_Value (COLUMNNAME_TipoCliente, Boolean.valueOf(TipoCliente));
	}

	/** Get Client Type.
		@return Client Type	  */
	public boolean isTipoCliente () 
	{
		Object oo = get_Value(COLUMNNAME_TipoCliente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set VerInfMilitar.
		@param VerInfMilitar VerInfMilitar	  */
	public void setVerInfMilitar (boolean VerInfMilitar)
	{
		set_Value (COLUMNNAME_VerInfMilitar, Boolean.valueOf(VerInfMilitar));
	}

	/** Get VerInfMilitar.
		@return VerInfMilitar	  */
	public boolean isVerInfMilitar () 
	{
		Object oo = get_Value(COLUMNNAME_VerInfMilitar);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}