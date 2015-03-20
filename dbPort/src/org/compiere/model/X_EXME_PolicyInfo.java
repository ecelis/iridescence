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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PolicyInfo
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PolicyInfo extends PO implements I_EXME_PolicyInfo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PolicyInfo (Properties ctx, int EXME_PolicyInfo_ID, String trxName)
    {
      super (ctx, EXME_PolicyInfo_ID, trxName);
      /** if (EXME_PolicyInfo_ID == 0)
        {
			setAuxType (null);
// N
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
			setEXME_PolicyInfo_ID (0);
			setPolicyType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PolicyInfo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PolicyInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** AuxType AD_Reference_ID=1200674 */
	public static final int AUXTYPE_AD_Reference_ID=1200674;
	/** Invoice = I */
	public static final String AUXTYPE_Invoice = "I";
	/** Check = C */
	public static final String AUXTYPE_Check = "C";
	/** Transfer = T */
	public static final String AUXTYPE_Transfer = "T";
	/** None = N */
	public static final String AUXTYPE_None = "N";
	/** Set AuxType.
		@param AuxType AuxType	  */
	public void setAuxType (String AuxType)
	{
		if (AuxType == null) throw new IllegalArgumentException ("AuxType is mandatory");
		if (AuxType.equals("I") || AuxType.equals("C") || AuxType.equals("T") || AuxType.equals("N")); else throw new IllegalArgumentException ("AuxType Invalid value - " + AuxType + " - Reference_ID=1200674 - I - C - T - N");		set_Value (COLUMNNAME_AuxType, AuxType);
	}

	/** Get AuxType.
		@return AuxType	  */
	public String getAuxType () 
	{
		return (String)get_Value(COLUMNNAME_AuxType);
	}

	/** Set Destination Bank Account.
		@param C_BankAccountDest_ID Destination Bank Account	  */
	public void setC_BankAccountDest_ID (int C_BankAccountDest_ID)
	{
		if (C_BankAccountDest_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccountDest_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccountDest_ID, Integer.valueOf(C_BankAccountDest_ID));
	}

	/** Get Destination Bank Account.
		@return Destination Bank Account	  */
	public int getC_BankAccountDest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccountDest_ID);
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

	/** Set Rate.
		@param CurrencyRate 
		Currency Conversion Rate
	  */
	public void setCurrencyRate (BigDecimal CurrencyRate)
	{
		set_Value (COLUMNNAME_CurrencyRate, CurrencyRate);
	}

	/** Get Rate.
		@return Currency Conversion Rate
	  */
	public BigDecimal getCurrencyRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrencyRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		if (DateTrx == null)
			throw new IllegalArgumentException ("DateTrx is mandatory.");
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Document Number.
		@param DocNo 
		Document Number
	  */
	public void setDocNo (String DocNo)
	{
		set_Value (COLUMNNAME_DocNo, DocNo);
	}

	/** Get Document Number.
		@return Document Number
	  */
	public String getDocNo () 
	{
		return (String)get_Value(COLUMNNAME_DocNo);
	}

	/** Set Policy Info.
		@param EXME_PolicyInfo_ID Policy Info	  */
	public void setEXME_PolicyInfo_ID (int EXME_PolicyInfo_ID)
	{
		if (EXME_PolicyInfo_ID < 1)
			 throw new IllegalArgumentException ("EXME_PolicyInfo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PolicyInfo_ID, Integer.valueOf(EXME_PolicyInfo_ID));
	}

	/** Get Policy Info.
		@return Policy Info	  */
	public int getEXME_PolicyInfo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PolicyInfo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	public I_GL_Category getGL_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_GL_Category.Table_Name);
        I_GL_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_GL_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getGL_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set GL Category.
		@param GL_Category_ID 
		General Ledger Category
	  */
	public void setGL_Category_ID (int GL_Category_ID)
	{
		if (GL_Category_ID < 1) 
			set_Value (COLUMNNAME_GL_Category_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Category_ID, Integer.valueOf(GL_Category_ID));
	}

	/** Get GL Category.
		@return General Ledger Category
	  */
	public int getGL_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Category_ID);
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

	/** Set Notes.
		@param Notes Notes	  */
	public void setNotes (String Notes)
	{
		set_Value (COLUMNNAME_Notes, Notes);
	}

	/** Get Notes.
		@return Notes	  */
	public String getNotes () 
	{
		return (String)get_Value(COLUMNNAME_Notes);
	}

	/** Set Policiy Number.
		@param PolicyNo Policiy Number	  */
	public void setPolicyNo (int PolicyNo)
	{
		set_Value (COLUMNNAME_PolicyNo, Integer.valueOf(PolicyNo));
	}

	/** Get Policiy Number.
		@return Policiy Number	  */
	public int getPolicyNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PolicyNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PolicyType AD_Reference_ID=1200673 */
	public static final int POLICYTYPE_AD_Reference_ID=1200673;
	/** None = N */
	public static final String POLICYTYPE_None = "N";
	/** Journal = J */
	public static final String POLICYTYPE_Journal = "J";
	/** Income = I */
	public static final String POLICYTYPE_Income = "I";
	/** Expense = E */
	public static final String POLICYTYPE_Expense = "E";
	/** Set Policy Type.
		@param PolicyType Policy Type	  */
	public void setPolicyType (String PolicyType)
	{
		if (PolicyType == null) throw new IllegalArgumentException ("PolicyType is mandatory");
		if (PolicyType.equals("N") || PolicyType.equals("J") || PolicyType.equals("I") || PolicyType.equals("E")); else throw new IllegalArgumentException ("PolicyType Invalid value - " + PolicyType + " - Reference_ID=1200673 - N - J - I - E");		set_Value (COLUMNNAME_PolicyType, PolicyType);
	}

	/** Get Policy Type.
		@return Policy Type	  */
	public String getPolicyType () 
	{
		return (String)get_Value(COLUMNNAME_PolicyType);
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Universally Unique Identifier.
		@param UUID Universally Unique Identifier	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Universally Unique Identifier.
		@return Universally Unique Identifier	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}