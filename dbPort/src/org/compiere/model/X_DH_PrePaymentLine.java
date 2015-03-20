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

/** Generated Model for DH_PrePaymentLine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_DH_PrePaymentLine extends PO implements I_DH_PrePaymentLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_DH_PrePaymentLine (Properties ctx, int DH_PrePaymentLine_ID, String trxName)
    {
      super (ctx, DH_PrePaymentLine_ID, trxName);
      /** if (DH_PrePaymentLine_ID == 0)
        {
			setDH_PrePayment_ID (0);
			setDH_PrePaymentLine_ID (0);
			setIsPaid (false);
        } */
    }

    /** Load Constructor */
    public X_DH_PrePaymentLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DH_PrePaymentLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_DH_PrePayment getDH_PrePayment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_DH_PrePayment.Table_Name);
        I_DH_PrePayment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_DH_PrePayment)constructor.newInstance(new Object[] {getCtx(), new Integer(getDH_PrePayment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pre Payment.
		@param DH_PrePayment_ID Pre Payment	  */
	public void setDH_PrePayment_ID (int DH_PrePayment_ID)
	{
		if (DH_PrePayment_ID < 1)
			 throw new IllegalArgumentException ("DH_PrePayment_ID is mandatory.");
		set_Value (COLUMNNAME_DH_PrePayment_ID, Integer.valueOf(DH_PrePayment_ID));
	}

	/** Get Pre Payment.
		@return Pre Payment	  */
	public int getDH_PrePayment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_PrePayment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pre Payment Line.
		@param DH_PrePaymentLine_ID Pre Payment Line	  */
	public void setDH_PrePaymentLine_ID (int DH_PrePaymentLine_ID)
	{
		if (DH_PrePaymentLine_ID < 1)
			 throw new IllegalArgumentException ("DH_PrePaymentLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_DH_PrePaymentLine_ID, Integer.valueOf(DH_PrePaymentLine_ID));
	}

	/** Get Pre Payment Line.
		@return Pre Payment Line	  */
	public int getDH_PrePaymentLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_PrePaymentLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DH_Voucher getDH_Voucher() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_DH_Voucher.Table_Name);
        I_DH_Voucher result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_DH_Voucher)constructor.newInstance(new Object[] {getCtx(), new Integer(getDH_Voucher_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Voucher.
		@param DH_Voucher_ID Voucher	  */
	public void setDH_Voucher_ID (int DH_Voucher_ID)
	{
		if (DH_Voucher_ID < 1) 
			set_Value (COLUMNNAME_DH_Voucher_ID, null);
		else 
			set_Value (COLUMNNAME_DH_Voucher_ID, Integer.valueOf(DH_Voucher_ID));
	}

	/** Get Voucher.
		@return Voucher	  */
	public int getDH_Voucher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_Voucher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exterior Document No..
		@param DocumentNoExt 
		Exterior Document No.
	  */
	public void setDocumentNoExt (String DocumentNoExt)
	{
		set_Value (COLUMNNAME_DocumentNoExt, DocumentNoExt);
	}

	/** Get Exterior Document No..
		@return Exterior Document No.
	  */
	public String getDocumentNoExt () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNoExt);
	}

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
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

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_Value (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	public boolean isPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line Total.
		@param LineTotalAmt 
		Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt)
	{
		set_Value (COLUMNNAME_LineTotalAmt, LineTotalAmt);
	}

	/** Get Line Total.
		@return Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineTotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payment date.
		@param PaymentDate Payment date	  */
	public void setPaymentDate (Timestamp PaymentDate)
	{
		set_Value (COLUMNNAME_PaymentDate, PaymentDate);
	}

	/** Get Payment date.
		@return Payment date	  */
	public Timestamp getPaymentDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PaymentDate);
	}

	/** PaymentType AD_Reference_ID=1200675 */
	public static final int PAYMENTTYPE_AD_Reference_ID=1200675;
	/** Credit = C */
	public static final String PAYMENTTYPE_Credit = "C";
	/** Cash = E */
	public static final String PAYMENTTYPE_Cash = "E";
	/** Savings = A */
	public static final String PAYMENTTYPE_Savings = "A";
	/** Set PaymentType.
		@param PaymentType 
		Billing Payment
	  */
	public void setPaymentType (String PaymentType)
	{

		if (PaymentType == null || PaymentType.equals("C") || PaymentType.equals("E") || PaymentType.equals("A")); else throw new IllegalArgumentException ("PaymentType Invalid value - " + PaymentType + " - Reference_ID=1200675 - C - E - A");		set_Value (COLUMNNAME_PaymentType, PaymentType);
	}

	/** Get PaymentType.
		@return Billing Payment
	  */
	public String getPaymentType () 
	{
		return (String)get_Value(COLUMNNAME_PaymentType);
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (int QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, Integer.valueOf(QtyEntered));
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public int getQtyEntered () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QtyEntered);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
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

	/** Set Transaction Code.
		@param TransactionCode 
		The transaction code represents the search definition
	  */
	public void setTransactionCode (String TransactionCode)
	{
		set_Value (COLUMNNAME_TransactionCode, TransactionCode);
	}

	/** Get Transaction Code.
		@return The transaction code represents the search definition
	  */
	public String getTransactionCode () 
	{
		return (String)get_Value(COLUMNNAME_TransactionCode);
	}
}