/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_PaqBase_Version
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PaqBase_Version extends PO implements I_EXME_PaqBase_Version, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PaqBase_Version (Properties ctx, int EXME_PaqBase_Version_ID, String trxName)
    {
      super (ctx, EXME_PaqBase_Version_ID, trxName);
      /** if (EXME_PaqBase_Version_ID == 0)
        {
			setBaseAmt (Env.ZERO);
			setC_Currency_ID (0);
			setC_Tax_ID (0);
			setDiscount (Env.ZERO);
			setEsq_Precio (null);
// VA
			setEXME_PaqBase_ID (0);
			setEXME_PaqBase_Version_ID (0);
			setGeneral (false);
			setM_PriceList_ID (0);
			setM_Product_ID (0);
			setName (null);
			setTaxAmt (Env.ZERO);
			setTipo (null);
// AL
			setTotalAmt (Env.ZERO);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_PaqBase_Version (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PaqBase_Version[")
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

	/** Set Base Amount.
		@param BaseAmt 
		Base Amount
	  */
	public void setBaseAmt (BigDecimal BaseAmt)
	{
		if (BaseAmt == null)
			throw new IllegalArgumentException ("BaseAmt is mandatory.");
		set_Value (COLUMNNAME_BaseAmt, BaseAmt);
	}

	/** Get Base Amount.
		@return Base Amount
	  */
	public BigDecimal getBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1)
			 throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
		set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
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

	/** Set Discount %.
		@param Discount 
		Discount in percent
	  */
	public void setDiscount (BigDecimal Discount)
	{
		if (Discount == null)
			throw new IllegalArgumentException ("Discount is mandatory.");
		set_Value (COLUMNNAME_Discount, Discount);
	}

	/** Get Discount %.
		@return Discount in percent
	  */
	public BigDecimal getDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Esq_Precio AD_Reference_ID=1200528 */
	public static final int ESQ_PRECIO_AD_Reference_ID=1200528;
	/** Fixed = FI */
	public static final String ESQ_PRECIO_Fixed = "FI";
	/** Variable = VA */
	public static final String ESQ_PRECIO_Variable = "VA";
	/** Set Price Scheme.
		@param Esq_Precio Price Scheme	  */
	public void setEsq_Precio (String Esq_Precio)
	{
		if (Esq_Precio == null) throw new IllegalArgumentException ("Esq_Precio is mandatory");
		if (Esq_Precio.equals("FI") || Esq_Precio.equals("VA")); else throw new IllegalArgumentException ("Esq_Precio Invalid value - " + Esq_Precio + " - Reference_ID=1200528 - FI - VA");		set_Value (COLUMNNAME_Esq_Precio, Esq_Precio);
	}

	/** Get Price Scheme.
		@return Price Scheme	  */
	public String getEsq_Precio () 
	{
		return (String)get_Value(COLUMNNAME_Esq_Precio);
	}

	/** Set Base Package.
		@param EXME_PaqBase_ID 
		Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID)
	{
		if (EXME_PaqBase_ID < 1)
			 throw new IllegalArgumentException ("EXME_PaqBase_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PaqBase_ID, Integer.valueOf(EXME_PaqBase_ID));
	}

	/** Get Base Package.
		@return Base Package
	  */
	public int getEXME_PaqBase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1)
			 throw new IllegalArgumentException ("EXME_PaqBase_Version_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set General.
		@param General General	  */
	public void setGeneral (boolean General)
	{
		set_Value (COLUMNNAME_General, Boolean.valueOf(General));
	}

	/** Get General.
		@return General	  */
	public boolean isGeneral () 
	{
		Object oo = get_Value(COLUMNNAME_General);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
		set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		if (TaxAmt == null)
			throw new IllegalArgumentException ("TaxAmt is mandatory.");
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Tipo AD_Reference_ID=1200527 */
	public static final int TIPO_AD_Reference_ID=1200527;
	/** SpendingOutput = SE */
	public static final String TIPO_SpendingOutput = "SE";
	/** ChargesToEncounter = ME */
	public static final String TIPO_ChargesToEncounter = "ME";
	/** All = AL */
	public static final String TIPO_All = "AL";
	/** Set Type.
		@param Tipo 
		GL
	  */
	public void setTipo (String Tipo)
	{
		if (Tipo == null) throw new IllegalArgumentException ("Tipo is mandatory");
		if (Tipo.equals("SE") || Tipo.equals("ME") || Tipo.equals("AL")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200527 - SE - ME - AL");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return GL
	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
	}

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		if (TotalAmt == null)
			throw new IllegalArgumentException ("TotalAmt is mandatory.");
		set_Value (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		if (ValidFrom == null)
			throw new IllegalArgumentException ("ValidFrom is mandatory.");
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}