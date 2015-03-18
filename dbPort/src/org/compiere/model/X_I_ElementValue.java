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

/** Generated Model for I_ElementValue
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_ElementValue extends PO implements I_I_ElementValue, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_ElementValue (Properties ctx, int I_ElementValue_ID, String trxName)
    {
      super (ctx, I_ElementValue_ID, trxName);
      /** if (I_ElementValue_ID == 0)
        {
			setI_ElementValue_ID (0);
			setI_IsImported (false);
        } */
    }

    /** Load Constructor */
    public X_I_ElementValue (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_ElementValue[")
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

		if (AccountSign == null || AccountSign.equals("N") || AccountSign.equals("D") || AccountSign.equals("C")); else throw new IllegalArgumentException ("AccountSign Invalid value - " + AccountSign + " - Reference_ID=118 - N - D - C");		set_Value (COLUMNNAME_AccountSign, AccountSign);
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

		if (AccountType == null || AccountType.equals("A") || AccountType.equals("L") || AccountType.equals("R") || AccountType.equals("E") || AccountType.equals("O") || AccountType.equals("M")); else throw new IllegalArgumentException ("AccountType Invalid value - " + AccountType + " - Reference_ID=117 - A - L - R - E - O - M");		set_Value (COLUMNNAME_AccountType, AccountType);
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
	public void setAcct_Level (BigDecimal Acct_Level)
	{
		set_Value (COLUMNNAME_Acct_Level, Acct_Level);
	}

	/** Get Account Level.
		@return The account level in the account schema
	  */
	public BigDecimal getAcct_Level () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Acct_Level);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
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
			set_Value (COLUMNNAME_C_Element_ID, null);
		else 
			set_Value (COLUMNNAME_C_Element_ID, Integer.valueOf(C_Element_ID));
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

	public I_C_ElementValue getC_ElementValue() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_ElementValue.Table_Name);
        I_C_ElementValue result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ElementValue)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ElementValue_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
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

	/** Set Default Account.
		@param Default_Account 
		Name of the Default Account Column
	  */
	public void setDefault_Account (String Default_Account)
	{
		set_Value (COLUMNNAME_Default_Account, Default_Account);
	}

	/** Get Default Account.
		@return Name of the Default Account Column
	  */
	public String getDefault_Account () 
	{
		return (String)get_Value(COLUMNNAME_Default_Account);
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

	/** Set Element Name.
		@param ElementName 
		Name of the Element
	  */
	public void setElementName (String ElementName)
	{
		set_Value (COLUMNNAME_ElementName, ElementName);
	}

	/** Get Element Name.
		@return Name of the Element
	  */
	public String getElementName () 
	{
		return (String)get_Value(COLUMNNAME_ElementName);
	}

	public I_EXME_GroupAcct getEXME_GroupAcct() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GroupAcct.Table_Name);
        I_EXME_GroupAcct result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GroupAcct)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GroupAcct_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Group Account Value.
		@param GroupAcct_Value Group Account Value	  */
	public void setGroupAcct_Value (String GroupAcct_Value)
	{
		set_Value (COLUMNNAME_GroupAcct_Value, GroupAcct_Value);
	}

	/** Get Group Account Value.
		@return Group Account Value	  */
	public String getGroupAcct_Value () 
	{
		return (String)get_Value(COLUMNNAME_GroupAcct_Value);
	}

	/** Set Import Account.
		@param I_ElementValue_ID 
		Import Account Value
	  */
	public void setI_ElementValue_ID (int I_ElementValue_ID)
	{
		if (I_ElementValue_ID < 1)
			 throw new IllegalArgumentException ("I_ElementValue_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_ElementValue_ID, Integer.valueOf(I_ElementValue_ID));
	}

	/** Get Import Account.
		@return Import Account Value
	  */
	public int getI_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
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

	/** Set Parent Key.
		@param ParentValue 
		Key if the Parent
	  */
	public void setParentValue (String ParentValue)
	{
		set_Value (COLUMNNAME_ParentValue, ParentValue);
	}

	/** Get Parent Key.
		@return Key if the Parent
	  */
	public String getParentValue () 
	{
		return (String)get_Value(COLUMNNAME_ParentValue);
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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