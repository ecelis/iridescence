/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_ElementValue
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_ElementValue extends PO implements I_C_ElementValue, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_ElementValue (Properties ctx, int C_ElementValue_ID, String trxName)
    {
      super (ctx, C_ElementValue_ID, trxName);
      /** if (C_ElementValue_ID == 0)
        {
			setAccountSign (null);
// N
			setAccountType (null);
// E
			setC_Element_ID (0);
			setC_ElementValue_ID (0);
			setIsSummary (false);
			setName (null);
			setPostActual (true);
// Y
			setPostBudget (true);
// Y
			setPostEncumbrance (true);
// Y
			setPostStatistical (true);
// Y
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_ElementValue (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_ElementValue[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AccountSign AD_Reference_ID=118 */
	public static final int ACCOUNTSIGN_AD_Reference_ID=118;
	/** Natural = N */
	public static final String ACCOUNTSIGN_Natural = "N";
	/** Debit = D */
	public static final String ACCOUNTSIGN_Debit = "D";
	/** Credit = C */
	public static final String ACCOUNTSIGN_Credit = "C";
	/** Set Account Sign.
		@param AccountSign 
		Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	public void setAccountSign (String AccountSign)
	{
		if (AccountSign == null) throw new IllegalArgumentException ("AccountSign is mandatory");
		if (AccountSign.equals("N") || AccountSign.equals("D") || AccountSign.equals("C")); else throw new IllegalArgumentException ("AccountSign Invalid value - " + AccountSign + " - Reference_ID=118 - N - D - C");		set_Value (COLUMNNAME_AccountSign, AccountSign);
	}

	/** Get Account Sign.
		@return Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	public String getAccountSign () 
	{
		return (String)get_Value(COLUMNNAME_AccountSign);
	}

	/** AccountType AD_Reference_ID=117 */
	public static final int ACCOUNTTYPE_AD_Reference_ID=117;
	/** Asset = A */
	public static final String ACCOUNTTYPE_Asset = "A";
	/** Liability = L */
	public static final String ACCOUNTTYPE_Liability = "L";
	/** Revenue = R */
	public static final String ACCOUNTTYPE_Revenue = "R";
	/** Expense = E */
	public static final String ACCOUNTTYPE_Expense = "E";
	/** Owner's Equity = O */
	public static final String ACCOUNTTYPE_OwnerSEquity = "O";
	/** Memo = M */
	public static final String ACCOUNTTYPE_Memo = "M";
	/** Set Account Type.
		@param AccountType 
		Indicates the type of account
	  */
	public void setAccountType (String AccountType)
	{
		if (AccountType == null) throw new IllegalArgumentException ("AccountType is mandatory");
		if (AccountType.equals("A") || AccountType.equals("L") || AccountType.equals("R") || AccountType.equals("E") || AccountType.equals("O") || AccountType.equals("M")); else throw new IllegalArgumentException ("AccountType Invalid value - " + AccountType + " - Reference_ID=117 - A - L - R - E - O - M");		set_Value (COLUMNNAME_AccountType, AccountType);
	}

	/** Get Account Type.
		@return Indicates the type of account
	  */
	public String getAccountType () 
	{
		return (String)get_Value(COLUMNNAME_AccountType);
	}

	/** Set Account Level.
		@param Acct_Level 
		The account level in the account schema
	  */
	public void setAcct_Level (int Acct_Level)
	{
		set_Value (COLUMNNAME_Acct_Level, Integer.valueOf(Acct_Level));
	}

	/** Get Account Level.
		@return The account level in the account schema
	  */
	public int getAcct_Level () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Acct_Level);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BankAccount getC_BankAccount() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BankAccount.Table_Name);
        I_C_BankAccount result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BankAccount)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BankAccount_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
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

	public I_C_Element getC_Element() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Element.Table_Name);
        I_C_Element result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Element)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Element_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Element.
		@param C_Element_ID 
		Accounting Element
	  */
	public void setC_Element_ID (int C_Element_ID)
	{
		if (C_Element_ID < 1)
			 throw new IllegalArgumentException ("C_Element_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Element_ID, Integer.valueOf(C_Element_ID));
	}

	/** Get Element.
		@return Accounting Element
	  */
	public int getC_Element_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Element_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1)
			 throw new IllegalArgumentException ("C_ElementValue_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
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

	/** Set Group Acct.
		@param EXME_GroupAcct_ID Group Acct	  */
	public void setEXME_GroupAcct_ID (int EXME_GroupAcct_ID)
	{
		if (EXME_GroupAcct_ID < 1) 
			set_Value (COLUMNNAME_EXME_GroupAcct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GroupAcct_ID, Integer.valueOf(EXME_GroupAcct_ID));
	}

	/** Get Group Acct.
		@return Group Acct	  */
	public int getEXME_GroupAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GroupAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Partida getEXME_Partida() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Partida.Table_Name);
        I_EXME_Partida result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Partida)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Partida_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget Item.
		@param EXME_Partida_ID 
		Budget Item
	  */
	public void setEXME_Partida_ID (int EXME_Partida_ID)
	{
		if (EXME_Partida_ID < 1) 
			set_Value (COLUMNNAME_EXME_Partida_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Partida_ID, Integer.valueOf(EXME_Partida_ID));
	}

	/** Get Budget Item.
		@return Budget Item
	  */
	public int getEXME_Partida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Partida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Account.
		@param IsBankAccount 
		Indicates if this is the Bank Account
	  */
	public void setIsBankAccount (boolean IsBankAccount)
	{
		set_Value (COLUMNNAME_IsBankAccount, Boolean.valueOf(IsBankAccount));
	}

	/** Get Bank Account.
		@return Indicates if this is the Bank Account
	  */
	public boolean isBankAccount () 
	{
		Object oo = get_Value(COLUMNNAME_IsBankAccount);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Document Controlled.
		@param IsDocControlled 
		Control account - If an account is controlled by a document, you cannot post manually to it
	  */
	public void setIsDocControlled (boolean IsDocControlled)
	{
		set_Value (COLUMNNAME_IsDocControlled, Boolean.valueOf(IsDocControlled));
	}

	/** Get Document Controlled.
		@return Control account - If an account is controlled by a document, you cannot post manually to it
	  */
	public boolean isDocControlled () 
	{
		Object oo = get_Value(COLUMNNAME_IsDocControlled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set isdoccontrolled2.
		@param isdoccontrolled2 isdoccontrolled2	  */
	public void setisdoccontrolled2 (boolean isdoccontrolled2)
	{
		set_Value (COLUMNNAME_isdoccontrolled2, Boolean.valueOf(isdoccontrolled2));
	}

	/** Get isdoccontrolled2.
		@return isdoccontrolled2	  */
	public boolean isdoccontrolled2 () 
	{
		Object oo = get_Value(COLUMNNAME_isdoccontrolled2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Foreign Currency Account.
		@param IsForeignCurrency 
		Balances in foreign currency accounts are held in the nominated currency
	  */
	public void setIsForeignCurrency (boolean IsForeignCurrency)
	{
		set_Value (COLUMNNAME_IsForeignCurrency, Boolean.valueOf(IsForeignCurrency));
	}

	/** Get Foreign Currency Account.
		@return Balances in foreign currency accounts are held in the nominated currency
	  */
	public boolean isForeignCurrency () 
	{
		Object oo = get_Value(COLUMNNAME_IsForeignCurrency);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Parent Account.
		@param ParentElementValue_ID 
		The parent (summary) account
	  */
	public void setParentElementValue_ID (int ParentElementValue_ID)
	{
		if (ParentElementValue_ID < 1) 
			set_Value (COLUMNNAME_ParentElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_ParentElementValue_ID, Integer.valueOf(ParentElementValue_ID));
	}

	/** Get Parent Account.
		@return The parent (summary) account
	  */
	public int getParentElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ParentElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Post Actual.
		@param PostActual 
		Actual Values can be posted
	  */
	public void setPostActual (boolean PostActual)
	{
		set_Value (COLUMNNAME_PostActual, Boolean.valueOf(PostActual));
	}

	/** Get Post Actual.
		@return Actual Values can be posted
	  */
	public boolean isPostActual () 
	{
		Object oo = get_Value(COLUMNNAME_PostActual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post Budget.
		@param PostBudget 
		Budget values can be posted
	  */
	public void setPostBudget (boolean PostBudget)
	{
		set_Value (COLUMNNAME_PostBudget, Boolean.valueOf(PostBudget));
	}

	/** Get Post Budget.
		@return Budget values can be posted
	  */
	public boolean isPostBudget () 
	{
		Object oo = get_Value(COLUMNNAME_PostBudget);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post Encumbrance.
		@param PostEncumbrance 
		Post commitments to this account
	  */
	public void setPostEncumbrance (boolean PostEncumbrance)
	{
		set_Value (COLUMNNAME_PostEncumbrance, Boolean.valueOf(PostEncumbrance));
	}

	/** Get Post Encumbrance.
		@return Post commitments to this account
	  */
	public boolean isPostEncumbrance () 
	{
		Object oo = get_Value(COLUMNNAME_PostEncumbrance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post Statistical.
		@param PostStatistical 
		Post statistical quantities to this account?
	  */
	public void setPostStatistical (boolean PostStatistical)
	{
		set_Value (COLUMNNAME_PostStatistical, Boolean.valueOf(PostStatistical));
	}

	/** Get Post Statistical.
		@return Post statistical quantities to this account?
	  */
	public boolean isPostStatistical () 
	{
		Object oo = get_Value(COLUMNNAME_PostStatistical);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}