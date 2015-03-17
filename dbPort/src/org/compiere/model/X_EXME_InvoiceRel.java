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

/** Generated Model for EXME_InvoiceRel
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_InvoiceRel extends PO implements I_EXME_InvoiceRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InvoiceRel (Properties ctx, int EXME_InvoiceRel_ID, String trxName)
    {
      super (ctx, EXME_InvoiceRel_ID, trxName);
      /** if (EXME_InvoiceRel_ID == 0)
        {
			setC_Invoice_ID (0);
			setEXME_InvoiceRel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_InvoiceRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_InvoiceRel[")
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

	public I_C_Conversion_Rate getC_Conversion_Rate() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Conversion_Rate.Table_Name);
        I_C_Conversion_Rate result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Conversion_Rate)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Conversion_Rate_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Conversion Rate.
		@param C_Conversion_Rate_ID 
		Rate used for converting currencies
	  */
	public void setC_Conversion_Rate_ID (int C_Conversion_Rate_ID)
	{
		if (C_Conversion_Rate_ID < 1) 
			set_Value (COLUMNNAME_C_Conversion_Rate_ID, null);
		else 
			set_Value (COLUMNNAME_C_Conversion_Rate_ID, Integer.valueOf(C_Conversion_Rate_ID));
	}

	/** Get Conversion Rate.
		@return Rate used for converting currencies
	  */
	public int getC_Conversion_Rate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Conversion_Rate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1)
			 throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
		set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Relation Invoice - Notes.
		@param EXME_InvoiceRel_ID 
		Relation Invoice - Notes
	  */
	public void setEXME_InvoiceRel_ID (int EXME_InvoiceRel_ID)
	{
		if (EXME_InvoiceRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_InvoiceRel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_InvoiceRel_ID, Integer.valueOf(EXME_InvoiceRel_ID));
	}

	/** Get Relation Invoice - Notes.
		@return Relation Invoice - Notes
	  */
	public int getEXME_InvoiceRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InvoiceRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ref_Conversion_Rate.
		@param Ref_Conversion_Rate_ID Ref_Conversion_Rate	  */
	public void setRef_Conversion_Rate_ID (int Ref_Conversion_Rate_ID)
	{
		if (Ref_Conversion_Rate_ID < 1) 
			set_Value (COLUMNNAME_Ref_Conversion_Rate_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Conversion_Rate_ID, Integer.valueOf(Ref_Conversion_Rate_ID));
	}

	/** Get Ref_Conversion_Rate.
		@return Ref_Conversion_Rate	  */
	public int getRef_Conversion_Rate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Conversion_Rate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Referenced Invoice.
		@param Ref_Invoice_ID Referenced Invoice	  */
	public void setRef_Invoice_ID (int Ref_Invoice_ID)
	{
		if (Ref_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Ref_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Invoice_ID, Integer.valueOf(Ref_Invoice_ID));
	}

	/** Get Referenced Invoice.
		@return Referenced Invoice	  */
	public int getRef_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TrxType AD_Reference_ID=1200669 */
	public static final int TRXTYPE_AD_Reference_ID=1200669;
	/** AL = AL */
	public static final String TRXTYPE_Allocation = "AL";
	/** CA = CA */
	public static final String TRXTYPE_Cancellation = "CA";
	/** Set Transaction Type.
		@param TrxType 
		Type of  transaction 
	  */
	public void setTrxType (String TrxType)
	{

		if (TrxType == null || TrxType.equals("AL") || TrxType.equals("CA")); else throw new IllegalArgumentException ("TrxType Invalid value - " + TrxType + " - Reference_ID=1200669 - AL - CA");		set_Value (COLUMNNAME_TrxType, TrxType);
	}

	/** Get Transaction Type.
		@return Type of  transaction 
	  */
	public String getTrxType () 
	{
		return (String)get_Value(COLUMNNAME_TrxType);
	}
}