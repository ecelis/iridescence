/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_AdjustmentType
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_AdjustmentType extends PO implements I_EXME_AdjustmentType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AdjustmentType (Properties ctx, int EXME_AdjustmentType_ID, String trxName)
    {
      super (ctx, EXME_AdjustmentType_ID, trxName);
      /** if (EXME_AdjustmentType_ID == 0)
        {
			setEXME_AdjustmentType_ID (0);
			setName (null);
			setNextInvoice (false);
			setSign (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AdjustmentType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_AdjustmentType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 1) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
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

	/** Set Adjustment Type.
		@param EXME_AdjustmentType_ID Adjustment Type	  */
	public void setEXME_AdjustmentType_ID (int EXME_AdjustmentType_ID)
	{
		if (EXME_AdjustmentType_ID < 1)
			 throw new IllegalArgumentException ("EXME_AdjustmentType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AdjustmentType_ID, Integer.valueOf(EXME_AdjustmentType_ID));
	}

	/** Get Adjustment Type.
		@return Adjustment Type	  */
	public int getEXME_AdjustmentType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdjustmentType_ID);
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

	/** Sign AD_Reference_ID=1200593 */
	public static final int SIGN_AD_Reference_ID=1200593;
	/** Add = + */
	public static final String SIGN_Add = "+";
	/** Subtract = - */
	public static final String SIGN_Subtract = "-";
	/** Set Sign.
		@param Sign Sign	  */
	public void setSign (String Sign)
	{
		if (Sign == null) throw new IllegalArgumentException ("Sign is mandatory");
		if (Sign.equals("+") || Sign.equals("-")); else throw new IllegalArgumentException ("Sign Invalid value - " + Sign + " - Reference_ID=1200593 - + - -");		set_Value (COLUMNNAME_Sign, Sign);
	}

	/** Get Sign.
		@return Sign	  */
	public String getSign () 
	{
		return (String)get_Value(COLUMNNAME_Sign);
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
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("C") || Type.equals("D") || Type.equals("O") || Type.equals("I") || Type.equals("P") || Type.equals("A") || Type.equals("Y") || Type.equals("E") || Type.equals("N") || Type.equals("T") || Type.equals("B") || Type.equals("G") || Type.equals("M")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200525 - C - D - O - I - P - A - Y - E - N - T - B - G - M");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
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