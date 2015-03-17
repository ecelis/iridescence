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
import org.compiere.util.KeyNamePair;

/** Generated Model for C_Charge
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_Charge extends PO implements I_C_Charge, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Charge (Properties ctx, int C_Charge_ID, String trxName)
    {
      super (ctx, C_Charge_ID, trxName);
      /** if (C_Charge_ID == 0)
        {
			setC_Charge_ID (0);
			setChargeAmt (Env.ZERO);
			setExcepcion (null);
// P
			setInvoiceRequired (false);
			setIsDefault (false);
			setIsSameCurrency (false);
			setIsSameTax (false);
			setIsTaxIncluded (false);
// N
			setName (null);
			setNextInvoice (false);
			setPercentageApplied (Env.ZERO);
			setSameTaxCategory (false);
			setSign (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_Charge (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_Charge[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AcctType AD_Reference_ID=1200681 */
	public static final int ACCTTYPE_AD_Reference_ID=1200681;
	/** Expense = E */
	public static final String ACCTTYPE_Expense = "E";
	/** Revenue = R */
	public static final String ACCTTYPE_Revenue = "R";
	/** Set Revenue/Expense.
		@param AcctType Revenue/Expense	  */
	public void setAcctType (String AcctType)
	{

		if (AcctType == null || AcctType.equals("E") || AcctType.equals("R")); else throw new IllegalArgumentException ("AcctType Invalid value - " + AcctType + " - Reference_ID=1200681 - E - R");		set_Value (COLUMNNAME_AcctType, AcctType);
	}

	/** Get Revenue/Expense.
		@return Revenue/Expense	  */
	public String getAcctType () 
	{
		return (String)get_Value(COLUMNNAME_AcctType);
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1)
			 throw new IllegalArgumentException ("C_Charge_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ChargeType getC_ChargeType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_ChargeType.Table_Name);
        I_C_ChargeType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ChargeType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ChargeType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Charge Type.
		@param C_ChargeType_ID Charge Type	  */
	public void setC_ChargeType_ID (int C_ChargeType_ID)
	{
		if (C_ChargeType_ID < 1) 
			set_Value (COLUMNNAME_C_ChargeType_ID, null);
		else 
			set_Value (COLUMNNAME_C_ChargeType_ID, Integer.valueOf(C_ChargeType_ID));
	}

	/** Get Charge Type.
		@return Charge Type	  */
	public int getC_ChargeType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ChargeType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Currency getC_Currency() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
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

	/** Set Charge amount.
		@param ChargeAmt 
		Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt)
	{
		if (ChargeAmt == null)
			throw new IllegalArgumentException ("ChargeAmt is mandatory.");
		set_Value (COLUMNNAME_ChargeAmt, ChargeAmt);
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

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_TaxCategory.Table_Name);
        I_C_TaxCategory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_TaxCategory)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_TaxCategory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tax Category.
		@param C_TaxCategory_ID 
		Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID)
	{
		if (C_TaxCategory_ID < 1) 
			set_Value (COLUMNNAME_C_TaxCategory_ID, null);
		else 
			set_Value (COLUMNNAME_C_TaxCategory_ID, Integer.valueOf(C_TaxCategory_ID));
	}

	/** Get Tax Category.
		@return Tax Category
	  */
	public int getC_TaxCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxCategory_ID);
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

	/** Set Exception.
		@param Excepcion 
		Exception
	  */
	public void setExcepcion (String Excepcion)
	{
		if (Excepcion == null)
			throw new IllegalArgumentException ("Excepcion is mandatory.");
		set_Value (COLUMNNAME_Excepcion, Excepcion);
	}

	/** Get Exception.
		@return Exception
	  */
	public String getExcepcion () 
	{
		return (String)get_Value(COLUMNNAME_Excepcion);
	}

	/** Set Requires invoice?.
		@param InvoiceRequired Requires invoice?	  */
	public void setInvoiceRequired (boolean InvoiceRequired)
	{
		set_Value (COLUMNNAME_InvoiceRequired, Boolean.valueOf(InvoiceRequired));
	}

	/** Get Requires invoice?.
		@return Requires invoice?	  */
	public boolean isInvoiceRequired () 
	{
		Object oo = get_Value(COLUMNNAME_InvoiceRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Same Currency.
		@param IsSameCurrency Same Currency	  */
	public void setIsSameCurrency (boolean IsSameCurrency)
	{
		set_Value (COLUMNNAME_IsSameCurrency, Boolean.valueOf(IsSameCurrency));
	}

	/** Get Same Currency.
		@return Same Currency	  */
	public boolean isSameCurrency () 
	{
		Object oo = get_Value(COLUMNNAME_IsSameCurrency);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Same Tax.
		@param IsSameTax 
		Use the same tax as the main transaction
	  */
	public void setIsSameTax (boolean IsSameTax)
	{
		set_Value (COLUMNNAME_IsSameTax, Boolean.valueOf(IsSameTax));
	}

	/** Get Same Tax.
		@return Use the same tax as the main transaction
	  */
	public boolean isSameTax () 
	{
		Object oo = get_Value(COLUMNNAME_IsSameTax);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Price includes Tax.
		@param IsTaxIncluded 
		Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_Value (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}

	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Next invoice.
		@param NextInvoice Next invoice	  */
	public void setNextInvoice (boolean NextInvoice)
	{
		set_Value (COLUMNNAME_NextInvoice, Boolean.valueOf(NextInvoice));
	}

	/** Get Next invoice.
		@return Next invoice	  */
	public boolean isNextInvoice () 
	{
		Object oo = get_Value(COLUMNNAME_NextInvoice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set % to be applied.
		@param PercentageApplied % to be applied	  */
	public void setPercentageApplied (BigDecimal PercentageApplied)
	{
		if (PercentageApplied == null)
			throw new IllegalArgumentException ("PercentageApplied is mandatory.");
		set_Value (COLUMNNAME_PercentageApplied, PercentageApplied);
	}

	/** Get % to be applied.
		@return % to be applied	  */
	public BigDecimal getPercentageApplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PercentageApplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** PriceType AD_Reference_ID=1200684 */
	public static final int PRICETYPE_AD_Reference_ID=1200684;
	/** Fixed amount = FA */
	public static final String PRICETYPE_FixedAmount = "FA";
	/** Variable amount = VA */
	public static final String PRICETYPE_VariableAmount = "VA";
	/** % Price Product = PP */
	public static final String PRICETYPE_PriceProduct = "PP";
	/** Set Price type.
		@param PriceType Price type	  */
	public void setPriceType (String PriceType)
	{

		if (PriceType == null || PriceType.equals("FA") || PriceType.equals("VA") || PriceType.equals("PP")); else throw new IllegalArgumentException ("PriceType Invalid value - " + PriceType + " - Reference_ID=1200684 - FA - VA - PP");		set_Value (COLUMNNAME_PriceType, PriceType);
	}

	/** Get Price type.
		@return Price type	  */
	public String getPriceType () 
	{
		return (String)get_Value(COLUMNNAME_PriceType);
	}

	/** Set Print invoice description.
		@param PrintInvoiceDescription Print invoice description	  */
	public void setPrintInvoiceDescription (String PrintInvoiceDescription)
	{
		set_Value (COLUMNNAME_PrintInvoiceDescription, PrintInvoiceDescription);
	}

	/** Get Print invoice description.
		@return Print invoice description	  */
	public String getPrintInvoiceDescription () 
	{
		return (String)get_Value(COLUMNNAME_PrintInvoiceDescription);
	}

	/** Set Product Class.
		@param ProductClass Product Class	  */
	public void setProductClass (String ProductClass)
	{
		set_Value (COLUMNNAME_ProductClass, ProductClass);
	}

	/** Get Product Class.
		@return Product Class	  */
	public String getProductClass () 
	{
		return (String)get_Value(COLUMNNAME_ProductClass);
	}

	/** Set Same tax category.
		@param SameTaxCategory Same tax category	  */
	public void setSameTaxCategory (boolean SameTaxCategory)
	{
		set_Value (COLUMNNAME_SameTaxCategory, Boolean.valueOf(SameTaxCategory));
	}

	/** Get Same tax category.
		@return Same tax category	  */
	public boolean isSameTaxCategory () 
	{
		Object oo = get_Value(COLUMNNAME_SameTaxCategory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sign.
		@param Sign Sign	  */
	public void setSign (boolean Sign)
	{
		set_Value (COLUMNNAME_Sign, Boolean.valueOf(Sign));
	}

	/** Get Sign.
		@return Sign	  */
	public boolean isSign () 
	{
		Object oo = get_Value(COLUMNNAME_Sign);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Type AD_Reference_ID=1200525 */
	public static final int TYPE_AD_Reference_ID=1200525;
	/** CoPay = C */
	public static final String TYPE_CoPay = "C";
	/** Deductible = D */
	public static final String TYPE_Deductible = "D";
	/** Others = O */
	public static final String TYPE_Others = "O";
	/** Coinsurance = I */
	public static final String TYPE_Coinsurance = "I";
	/** Payment = P */
	public static final String TYPE_Payment = "P";
	/** Adjustment = A */
	public static final String TYPE_Adjustment = "A";
	/** CopayPayment = Y */
	public static final String TYPE_CopayPayment = "Y";
	/** DeductiblePayment = E */
	public static final String TYPE_DeductiblePayment = "E";
	/** CoinsurancePayment = N */
	public static final String TYPE_CoinsurancePayment = "N";
	/** OthersPayment = T */
	public static final String TYPE_OthersPayment = "T";
	/** Bad Debt Adjustment = B */
	public static final String TYPE_BadDebtAdjustment = "B";
	/** Patient Adjustments = G */
	public static final String TYPE_PatientAdjustments = "G";
	/** Insurance Payments = M */
	public static final String TYPE_InsurancePayments = "M";
	/** Global Invoice = F */
	public static final String TYPE_GlobalInvoice = "F";
	/** Credit Note Prompt Payment = H */
	public static final String TYPE_CreditNotePromptPayment = "H";
	/** Credit Note Pacial Payment = J */
	public static final String TYPE_CreditNotePacialPayment = "J";
	/** Internal Use Inventory = S */
	public static final String TYPE_InternalUseInventory = "S";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("C") || Type.equals("D") || Type.equals("O") || Type.equals("I") || Type.equals("P") || Type.equals("A") || Type.equals("Y") || Type.equals("E") || Type.equals("N") || Type.equals("T") || Type.equals("B") || Type.equals("G") || Type.equals("M") || Type.equals("F") || Type.equals("H") || Type.equals("J") || Type.equals("S")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200525 - C - D - O - I - P - A - Y - E - N - T - B - G - M - F - H - J - S");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}